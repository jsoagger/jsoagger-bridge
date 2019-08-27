/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.container;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class GetContainerFolderTemplatesOperation implements IOperation {

  /**
   * Constructor
   */
  public GetContainerFolderTemplatesOperation() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    if(query == null) throw new NullPointerException("Query can not be null");
    IOperationResult result = CloudServicesLocator.containerApi.folderTemplates(query);
    resultHandler.accept(result);
  }
}
