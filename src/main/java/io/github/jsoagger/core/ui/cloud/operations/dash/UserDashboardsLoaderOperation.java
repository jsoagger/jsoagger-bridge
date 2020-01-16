/**
 *
 */
package io.github.jsoagger.core.ui.cloud.operations.dash;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.ui.cloud.services.utils.UICloudServicesLocator;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class UserDashboardsLoaderOperation implements IOperation {


  /**
   * Constructor
   */
  public UserDashboardsLoaderOperation() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      IOperationResult result = UICloudServicesLocator.clientDashBoardApi.getUserDashboards(query);
      resultHandler.accept(result);
    }
    catch (Exception e) {
      exHandler.accept(e);
    }
  }
}
