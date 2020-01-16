/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public interface IPeopleApi {

  /**
   * @param query
   * @return
   */
  public IOperationResult createPeople(JsonObject query);

  public IOperationResult getPartyByReference(JsonObject params);
}
