/**
 *
 */
package io.github.jsoagger.core.business.offline.operations.notifications;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class CountOperation implements IOperation {

  /**
   * Constructor
   */
  public CountOperation() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override public void doOperation(JsonObject params, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
  }
}
