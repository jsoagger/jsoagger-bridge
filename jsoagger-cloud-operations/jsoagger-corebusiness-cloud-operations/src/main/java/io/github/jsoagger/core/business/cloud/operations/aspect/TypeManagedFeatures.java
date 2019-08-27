/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.aspect;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.OperationData;
import io.github.jsoagger.core.bridge.result.SingleResult;
import io.github.jsoagger.jfxcore.api.services.Services;
import com.google.gson.JsonObject;

/**
 * AOP for loading dynamical attributes.
 *
 * @author Ramilafananana  VONJISOA
 *
 */
public class TypeManagedFeatures {

  /**
   * Loads all atributes definition of {@link ATypeManaged}.
   * <p>
   *
   * @param typeManagedFullId
   * @param res
   * @return {@link IOperationResult}
   */
  public static IOperationResult loadAttributesDefinition(SingleResult res) {
    JsonObject q = new JsonObject();
    q.addProperty("fullId", res.getData().getAttributes().get("fullId").toString());

    List<IOperationResult> t = new ArrayList<>();

    IOperation getTmAttributeDefinitionsOperation = (IOperation) Services.getBean("GetTmAttributeDefinitionsOperation");
    getTmAttributeDefinitionsOperation.doOperation(q, r -> {
      MultipleResult mr = (MultipleResult) r;
      ((OperationData) res.rootData()).getBusinessType().put("softAttributes", mr.getData());
      t.add(r);
    }, ex -> {
      ex.printStackTrace();
    });

    return t.get(0);
  }


  /**
   * Load the type of {@link ATypeManaged}.
   * <p>
   *
   * @param typeManagedFullId
   * @param res
   * @return {@link IOperationResult}
   */
  public static IOperationResult loadType(SingleResult res) {
    JsonObject q = new JsonObject();
    q.addProperty("fullId", res.getData().getAttributes().get("fullId").toString());

    List<IOperationResult> t = new ArrayList<>();

    IOperation getTmTypeOperation = (IOperation) Services.getBean("GetTmTypeOperation");
    getTmTypeOperation.doOperation(q, r -> {
      t.add(r);
    }, ex -> {
      ex.printStackTrace();
    });

    res.getData().getBusinessType().putAll(((OperationData) t.get(0).rootData()).getAttributes());
    return t.get(0);
  }

  public static List<CompletableFuture<IOperationResult>> typeManagedFeatures(SingleResult res) {
    List<CompletableFuture<IOperationResult>> feat = new ArrayList<>();
    if (!res.hasBusinessError()) {

      if (isLoadType()) {
        CompletableFuture<IOperationResult> cp = CompletableFuture.supplyAsync(() -> {
          loadAttributesDefinition(res);
          return loadType(res);
        });

        feat.add(cp);
      }

      if (isLoadAttributes()) {
        CompletableFuture<IOperationResult> cp = CompletableFuture.supplyAsync(() -> null);
        feat.add(cp);
      }
    }

    return feat;
  }

  private static boolean isLoadType() {
    return true;
  }

  private static boolean isLoadAttributes() {
    return true;
  }


  /*public static List<CompletableFuture<IOperationResult>> typeManagedFeatures(ProceedingJoinPoint proceedingJoinPoint, SingleResult res) {
    ATypeManaged tm = proceedingJoinPoint.getTarget().getClass().getAnnotation(ATypeManaged.class);
    List<CompletableFuture<IOperationResult>> feat = new ArrayList<>();

    if (tm != null && !res.isError()) {

      if (tm.laodType()) {
        CompletableFuture<IOperationResult> cp = CompletableFuture.supplyAsync(() -> {
          loadAttributesDefinition(applicationContext, res);
          return loadType(applicationContext, res);
        });

        feat.add(cp);
      }

      if (tm.loadAttributeDefinitions()) {
        CompletableFuture<IOperationResult> cp = CompletableFuture.supplyAsync(() -> null);
        feat.add(cp);
      }
    }

    return feat;
  }*/
}
