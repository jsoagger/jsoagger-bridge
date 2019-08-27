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
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.OperationData;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * Get immediate children of a container root folder.
 *
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 20 févr. 2018
 */
public class GetContainerRootFolderChildren implements IOperation {


  /**
   * Constructor
   */
  public GetContainerRootFolderChildren() {
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
      SingleResult result = (SingleResult) CloudServicesLocator.containerApi.rootFolder(query);
      OperationData operationData = result.getData();

      MultipleResult multipleResult = new MultipleResult.Builder()
          .addData(operationData)
          .addMeta(MultipleResult.hasPreviousPage, false)
          .addMeta(MultipleResult.hasNextPage, false)
          .addMeta(MultipleResult.totalPages, 1)
          .addMeta(MultipleResult.pageNumber, 0)
          .addMeta(MultipleResult.totalPages, 1)
          .addMeta(MultipleResult.pageElements, 1)
          .addMeta(MultipleResult.totalElements, 1).build();
      // JsonObject jsonObject = new JsonObject();
      // jsonObject.addProperty("folderOid",
      // operationData.getAttributes().get("fullId"));
      // IOperationResult children =
      // CloudServicesLocator.folderApi.getFolderChidren(jsonObject);
      resultHandler.accept(multipleResult);
    }
    catch (Exception e) {
      if(exHandler != null) exHandler.accept(e);
    }
  }
}
