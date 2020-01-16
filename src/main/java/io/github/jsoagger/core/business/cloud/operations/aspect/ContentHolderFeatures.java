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
 * @author Ramilafananana  VONJISOA
 *
 */
public class ContentHolderFeatures {

  public static IOperationResult getPrimaryContentFormat(SingleResult res) {
    final JsonObject q = new JsonObject();
    q.addProperty("fullId", res.getData().getAttributes().get("fullId").toString());
    q.addProperty("role", res.getData().getAttributes().get("primary").toString());

    final List<IOperationResult> t = new ArrayList<>();
    final IOperation getPrimaryContentFormat = (IOperation) Services.getBean("GetContentInfoOperation");

    getPrimaryContentFormat.doOperation(q, r -> {
      if (((MultipleResult) r).getData().size() > 0) {
        final OperationData data = ((MultipleResult) r).getData().get(0);
        res.getData().getLinks().put("primaryContent", data.getAttributes());
      }
    }, ex -> {
      ex.printStackTrace();
    });

    return t.size() > 0 ? (IOperationResult) t.get(0) : null;
  }

  public static IOperationResult getAllContentFormat(SingleResult res) {
    final JsonObject q = new JsonObject();
    q.addProperty("fullId", res.getData().getAttributes().get("fullId").toString());
    q.addProperty("role", res.getData().getAttributes().get("all").toString());

    final List<IOperationResult> t = new ArrayList<>();
    final IOperation getPrimaryContentFormat = (IOperation) Services.getBean("GetContentInfoOperation");

    getPrimaryContentFormat.doOperation(q, r -> {
      if (((MultipleResult) r).getData().size() > 0) {
        for (final OperationData cf : ((MultipleResult) r).getData()) {
          // primary content
          if ("primary".equalsIgnoreCase((String) cf.getAttributes().get("role"))) {
            res.getData().getLinks().put("primaryContent", cf.getAttributes());
          }

          // attachments
          else {
            res.getData().getLinks().put((String) cf.getAttributes().get("name"), cf.getAttributes());
          }
        }
      }

    }, ex -> {
      ex.printStackTrace();
    });

    return t.size() > 0 ? (IOperationResult) t.get(0) : null;
  }

  public static List<CompletableFuture<IOperationResult>> primaryContentFeatures(SingleResult res) {
    final List<CompletableFuture<IOperationResult>> feat = new ArrayList<>();

    if (!res.hasBusinessError()) {
      final CompletableFuture<IOperationResult> cp = CompletableFuture.supplyAsync(() -> {
        return getAllContentFormat(res);
      });

      feat.add(cp);
    }

    return feat;
  }
}
