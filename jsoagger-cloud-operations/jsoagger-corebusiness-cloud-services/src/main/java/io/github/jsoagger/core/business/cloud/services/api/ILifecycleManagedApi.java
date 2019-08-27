/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 3 févr. 2018
 */
public interface ILifecycleManagedApi {

  IOperationResult reassignLifecycle(JsonObject query);

  IOperationResult denote(JsonObject query);


  IOperationResult promote(JsonObject query);


  IOperationResult setState(JsonObject query);


  IOperationResult getLifecycleHistory(JsonObject query);


  IOperationResult getLifecycleState(JsonObject query);


  IOperationResult getPossibleStatesByDenote(JsonObject query);


  IOperationResult getPossibleStatesByPromote(JsonObject query);


  IOperationResult getPossibleStatesBySetState(JsonObject query);


  IOperationResult getAllLifecycleStates(JsonObject query);


  IOperationResult getLifecycleName(JsonObject query);
}
