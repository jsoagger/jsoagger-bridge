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
public interface IPreferenceAPI {

  public IOperationResult getPreference(JsonObject query);

  public IOperationResult setPreference(JsonObject query);

  public IOperationResult getAllUserPreferences(JsonObject params);
}
