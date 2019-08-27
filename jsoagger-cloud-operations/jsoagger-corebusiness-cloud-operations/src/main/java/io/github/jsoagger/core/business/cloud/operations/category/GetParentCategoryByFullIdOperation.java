/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.category;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public class GetParentCategoryByFullIdOperation implements IOperation{

  /**
   * {@inheritDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler,
      Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      IOperationResult result = CloudServicesLocator.categoryApi.getParentCategoryByFullId(query);
      resultHandler.accept(result);
    }
    catch (Exception e) {
      if(exHandler != null) exHandler.accept(e);
      if(exHandler == null) e.printStackTrace();
    }
  }
}
