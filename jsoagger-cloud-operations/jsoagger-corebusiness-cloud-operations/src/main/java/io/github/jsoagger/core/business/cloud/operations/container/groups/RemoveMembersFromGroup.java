/**
 * 25 janv. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.operations.container.groups;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 25 janv. 2018
 */
public class RemoveMembersFromGroup implements IOperation {

  /**
   * Default Constructor
   */
  public RemoveMembersFromGroup() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      // force page size to negative number
      query.addProperty("pageSize", -1);
      IOperationResult result = CloudServicesLocator.userPrincipalApi.removeMembersFromGroup(query);
      resultHandler.accept(result);
    }
    catch (Exception e) {
      if(exHandler != null) {
        exHandler.accept(e);
      }
    }
  }
}
