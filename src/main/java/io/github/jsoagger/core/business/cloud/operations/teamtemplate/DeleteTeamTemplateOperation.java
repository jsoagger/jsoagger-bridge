/**
 * 3 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.operations.teamtemplate;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 3 févr. 2018
 */
public class DeleteTeamTemplateOperation implements IOperation {


  /**
   * Constructor
   */
  public DeleteTeamTemplateOperation() {
    super();
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      IOperationResult result = CloudServicesLocator.teamTemplateApi.deleteTeamTemplate(query);
      resultHandler.accept(result);
    }
    catch (Exception e) {
      if(exHandler != null) exHandler.accept(e);
    }
  }
}
