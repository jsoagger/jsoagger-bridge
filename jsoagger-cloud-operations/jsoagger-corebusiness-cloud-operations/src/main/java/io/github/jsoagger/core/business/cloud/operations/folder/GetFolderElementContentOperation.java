/**
 * 20 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.operations.folder;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 20 févr. 2018
 */
public class GetFolderElementContentOperation implements IOperation {

  /**
   * Constructor
   */
  public GetFolderElementContentOperation() {
    super();
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query,
      Consumer<IOperationResult> resultHandler,
      Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      query.addProperty("contentClass", "io.github.jsoagger.core.model.part.ElementMaster");
      IOperationResult result = CloudServicesLocator.folderApi.getFolderContent(query);
      resultHandler.accept(result);
    }
    catch (Exception e) {
      if(exHandler != null) exHandler.accept(e);
    }
  }
}
