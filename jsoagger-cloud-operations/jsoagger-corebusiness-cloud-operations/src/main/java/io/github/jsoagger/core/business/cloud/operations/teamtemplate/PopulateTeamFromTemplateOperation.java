/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.teamtemplate;

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
public class PopulateTeamFromTemplateOperation implements IOperation {

  /**
   * Constructor
   */
  public PopulateTeamFromTemplateOperation() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {

    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      query.get("oid").getAsString();
      query.get("teamTemplate").getAsString();

      IOperationResult result = CloudServicesLocator.teamTemplateApi.populateContainerMembersFromTemplate(query);
      resultHandler.accept(result);
    }
    catch (Exception e) {
      if(exHandler != null) exHandler.accept(e);
    }
  }
}
