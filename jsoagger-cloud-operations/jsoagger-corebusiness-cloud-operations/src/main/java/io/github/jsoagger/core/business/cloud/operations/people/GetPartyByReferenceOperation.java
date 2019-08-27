/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.people;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public class GetPartyByReferenceOperation implements IOperation {

  /**
   * Constructor
   */
  public GetPartyByReferenceOperation() {
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
    }
    catch (Exception e) {
      if(exHandler != null) exHandler.accept(e);
    }
  }
}
