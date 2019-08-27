/**
 *
 */
package io.github.jsoagger.core.ui.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public interface IViewConfigurationApi {

  /**
   * Get list of dahsboard of current user.
   *
   * @param query
   * @return
   */
  IOperationResult getViewConfiguration(JsonObject query);
}
