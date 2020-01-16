/**
 *
 */
package io.github.jsoagger.core.ui.cloud.operations.dash;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.ui.cloud.services.utils.UICloudServicesLocator;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class GetViewConfigurationDefinitionOperation implements IOperation {

  private static Map<String, IOperationResult>	CACHE	= new HashMap<>();


  /**
   * Constructor
   */
  public GetViewConfigurationDefinitionOperation() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      String veiwPath = query.get("path").getAsString();
      if (CACHE.containsKey(veiwPath)) {
        resultHandler.accept(CACHE.get(veiwPath));
      }

      IOperationResult result = UICloudServicesLocator.viewConfigurationApi.getViewConfiguration(query);
      resultHandler.accept(result);

      CACHE.put(veiwPath, result);
    }
    catch (Exception e) {
      exHandler.accept(e);
    }
  }
}