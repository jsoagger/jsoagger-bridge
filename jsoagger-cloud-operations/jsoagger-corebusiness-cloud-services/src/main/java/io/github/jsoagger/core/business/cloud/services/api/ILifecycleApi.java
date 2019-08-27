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
public interface ILifecycleApi {


  public IOperationResult statesBySetState(JsonObject query);

  public IOperationResult stateByDenote(JsonObject query);

  public IOperationResult stateByPromote(JsonObject query);

  IOperationResult getLifecycleByOid(JsonObject query);

  IOperationResult getLifecycleStates(JsonObject query);
}
