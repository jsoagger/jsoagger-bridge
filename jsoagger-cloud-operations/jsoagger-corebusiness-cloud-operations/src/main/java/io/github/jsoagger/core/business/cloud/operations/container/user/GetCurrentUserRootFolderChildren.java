/**
 * 20 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.operations.container.user;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 20 févr. 2018
 */
public class GetCurrentUserRootFolderChildren implements IOperation {

  private IOperation getFolderChildrenOperation;

  /**
   * Constructor
   */
  public GetCurrentUserRootFolderChildren() {
    super();
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
      IOperationResult result = CloudServicesLocator.userPrincipalApi.getCurrentUserAccount(query);

      JsonObject jo = new JsonObject();
      jo.addProperty("id", (String) ((SingleResult)result).getData().getAttributes().get("id"));
      jo.addProperty("containerId", query.get("containerId").getAsString());
      getFolderChildrenOperation.doOperation(jo, reso ->{
        resultHandler.accept(reso);
      });
    }
    catch (Exception e) {
      if(exHandler != null) exHandler.accept(e);
    }
  }


  public IOperation getGetFolderChildrenOperation() {
    return getFolderChildrenOperation;
  }


  public void setGetFolderChildrenOperation(IOperation getFolderChildrenOperation) {
    this.getFolderChildrenOperation = getFolderChildrenOperation;
  }


}
