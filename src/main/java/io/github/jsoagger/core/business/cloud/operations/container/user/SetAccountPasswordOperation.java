/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.container.user;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class SetAccountPasswordOperation implements IOperation {


  /**
   * Default Constructor
   */
  public SetAccountPasswordOperation() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
    }
    catch (Exception e) {
      if(exHandler != null) exHandler.accept(e);
    }
  }
}
