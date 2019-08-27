/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.aspect;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.OperationData;
import io.github.jsoagger.core.bridge.result.SingleResult;
import io.github.jsoagger.jfxcore.api.services.Services;
import com.google.gson.JsonObject;

/**
 * @author Ramilafananana  VONJISOA
 */
public class LifecycleManagedFeatures {

  /**
   * Load data relative to object lifecycle.
   *
   * @param applicationContext
   * @param res
   * @return
   */
  public static IOperationResult loadAllLifecycleStates(SingleResult res) {
    JsonObject q = new JsonObject();
    q.addProperty("fullId", res.getData().getAttributes().get("fullId").toString());

    List<IOperationResult> t = new ArrayList<>();

    IOperation getAllLifecycleStatesOperation = (IOperation) Services.getBean("GetAllLifecycleStatesOperation");
    getAllLifecycleStatesOperation.doOperation(q, r -> {
      OperationData data = (OperationData) r.rootData();
      if (data != null) {
        // data.links.lifecycleStates.allStates
        JsonObject lifecycleStates = new JsonObject();
        lifecycleStates.addProperty("allStates", data.getAttributes().get("allStates").toString());
        lifecycleStates.addProperty("stateByDenote", data.getAttributes().get("stateByDenote").toString());
        lifecycleStates.addProperty("stateByPromote", data.getAttributes().get("stateByPromote").toString());
        res.getData().getLinks().put("lifecycleStates", lifecycleStates);
      }
    }, ex -> {
      ex.printStackTrace();
    });

    return t.size() > 0 ? (IOperationResult) t.get(0) : null;
  }


  public static IOperationResult loadLifecycleName(SingleResult res) {
    JsonObject q = new JsonObject();
    q.addProperty("fullId", res.getData().getAttributes().get("fullId").toString());

    List<IOperationResult> t = new ArrayList<>();

    IOperation getLifecycleNameOperation = (IOperation) Services.getBean("GetLifecycleNameOperation");
    getLifecycleNameOperation.doOperation(q, r -> {
      t.add(r);
    }, ex -> {
      ex.printStackTrace();
    });

    return t.get(0);
  }


  public static List<CompletableFuture<IOperationResult>> lifecycleManagedFeatures(SingleResult res) {
    List<CompletableFuture<IOperationResult>> feat = new ArrayList<>();
    if (!res.hasBusinessError()) {
      CompletableFuture<IOperationResult> cp = CompletableFuture.supplyAsync(() -> loadAllLifecycleStates(res));
      feat.add(cp);
    }

    return feat;
  }
}
