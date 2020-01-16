/**
 * 25 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.operations.typedLink;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * Get rolebs and link object associated to each roleB.
 *
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 25 févr. 2018
 */
public class GetPaginatedRoleBsWithLinksOperation implements IOperation {

  /**
   * Default Constructor
   */
  public GetPaginatedRoleBsWithLinksOperation() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      final IOperationResult result = CloudServicesLocator.typedObjectLinkApi.getPaginatedRoleBsWithLinksOperation(query);
      resultHandler.accept(result);
    }
    catch (final Exception e) {
      if(exHandler != null) exHandler.accept(e);
    }
  }
}