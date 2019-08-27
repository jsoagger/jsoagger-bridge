/**
 * 23 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.operations.folder;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IBiOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 23 févr. 2018
 */
@SuppressWarnings("rawtypes")
public class GetFolderChildrenAndContentOperation implements IBiOperation {

  /**
   * Default Constructor
   */
  public GetFolderChildrenAndContentOperation() {}


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      IOperationResult content = CloudServicesLocator.folderApi.getFolderContent(query);
      // IOperationResult children =
      // CloudServicesLocator.folderApi.getFolderChidren(query);
      resultHandler.accept(content);
    } catch (Exception e) {
      e.printStackTrace();
      exHandler.accept(e);
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject firstQueryparams, Consumer<IOperationResult> firstHandler, JsonObject secondQueryParams, Consumer<IOperationResult> secondHandler, Consumer<Throwable> exHandler) {

    final CompletableFuture cp1 = CompletableFuture.runAsync(() -> {
      IOperationResult content = CloudServicesLocator.folderApi.getFolderContent(firstQueryparams);
      firstHandler.accept(content);
    });

    final CompletableFuture cp2 = CompletableFuture.runAsync(() -> {
      IOperationResult children = CloudServicesLocator.folderApi.getFolderChidren(secondQueryParams);
      secondHandler.accept(children);
    });
    CompletableFuture.allOf(cp1, cp2).join();

  }
}
