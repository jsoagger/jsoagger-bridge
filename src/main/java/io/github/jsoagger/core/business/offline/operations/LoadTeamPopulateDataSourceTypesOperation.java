/**
 *
 */
package io.github.jsoagger.core.business.offline.operations;

import java.util.Locale;
import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class LoadTeamPopulateDataSourceTypesOperation implements IOperation {

  // needs CoreOfflineMessageSource
  //private MessageSource ms;


  /**
   * Constructor
   */
  public LoadTeamPopulateDataSourceTypesOperation() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject params, Consumer<IOperationResult> resultHandler,
      Consumer<Throwable> exHandler) {

    Locale locale = null;

    // POPULATE_TEAM_FROM_TEMPLATE
    // POPULATE_TEAM_FROM_EXISTING_USERS
    /*IOperationResult operationResult = new MultipleResult.Builder()
        .addData(new OperationData.Builder()
            .addAttribute("savedValue", 0)
            .addAttribute("description", ms.get("POPULATE_TEAM_FROM_TEMPLATE_DESC", null, locale))
            .addAttribute("value", ms.get("POPULATE_TEAM_FROM_TEMPLATE", null, locale)).build())
        .addData(new OperationData.Builder()
            .addAttribute("savedValue", 1)
            .addAttribute("description", ms.getMessage("POPULATE_TEAM_FROM_EXISTING_USERS_DESC", null, locale))
            .addAttribute("value", ms.get("POPULATE_TEAM_FROM_EXISTING_USERS", null, locale)).build())
        .build();*/

    resultHandler.accept(new MultipleResult());
  }
}
