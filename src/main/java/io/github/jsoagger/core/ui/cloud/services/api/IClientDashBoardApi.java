/**
 *
 */
package io.github.jsoagger.core.ui.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IClientDashBoardApi {

  /**
   * Get list of dahsboard of current user.
   *
   * @param query
   * @return
   */
  IOperationResult getUserDashboards(JsonObject query);


  /**
   * Get all widgets of the referenced dahsboard.
   *
   * @param query
   * @return
   */
  IOperationResult getDashboardWidgets(JsonObject query);
}
