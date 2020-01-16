/**
 *
 */
package io.github.jsoagger.core.ui.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.OperationData;
import io.github.jsoagger.core.ui.cloud.services.api.IClientDashBoardApi;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
//ClientDashboardApi
public class ClientDashboardImpl implements IClientDashBoardApi {

  /**
   * Constructor
   */
  public ClientDashboardImpl() {
    super();
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getDashboardWidgets(JsonObject query) {
    return null;
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getUserDashboards(JsonObject query) {
    OperationData data = new OperationData.Builder().addAttribute("icon", "fa-bookmark:24").addAttribute("translatedLabel", "Test Dashboard1").build();
    OperationData data2 = new OperationData.Builder().addAttribute("icon", "fa-bookmark:24").addAttribute("translatedLabel", "Test Dashboard2").build();

    MultipleResult result = new MultipleResult.Builder().addData(data).addData(data2).build();
    result.addMetaData(IOperationResult.totalElements, 0);
    return result;
  }
}
