/**
 * 25 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.operations.typedLink;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * Get part usages may occurs from master or iteration. When getting rolebs from
 * master we must do additionnal treatment.
 *
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 25 févr. 2018
 */
public class GetDocUsagesOperation implements IOperation {

  /**
   * Default Constructor
   */
  public GetDocUsagesOperation() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      query.addProperty("linkClass", "io.github.jsoagger.core.model.document.DocumentDocMasterLink");
      IOperationResult result = CloudServicesLocator.typedObjectLinkApi.getUsages(query);
      resultHandler.accept(result);
    }
    catch (Exception e) {
      if(exHandler != null) exHandler.accept(e);
    }
  }
}
