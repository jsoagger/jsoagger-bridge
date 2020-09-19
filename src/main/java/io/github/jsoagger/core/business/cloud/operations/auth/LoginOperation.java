/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.auth;

import java.time.LocalDateTime;
import java.util.function.Consumer;

import com.google.gson.JsonObject;

import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.SingleResult;
import io.github.jsoagger.core.business.cloud.operations.ICloudOperation;
import io.github.jsoagger.core.business.cloud.operations.utils.ClientStatus;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class LoginOperation implements ICloudOperation {

  // needs clientStatus
  private ClientStatus clientStatus;


  /**
   * Constructor
   */
  public LoginOperation() {
    super();
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query,
      Consumer<IOperationResult> resultHandler,
      Consumer<Throwable> exHandler) {
    if(query == null) throw new NullPointerException("Query can not be null");
    IOperationResult result = doLogin(query);
    resultHandler.accept(result);
  }


  private IOperationResult doLogin(JsonObject query) {
    boolean loggedIn = CloudServicesLocator.authenticationApi.login(query);
    if (loggedIn) {
      clientStatus.setLoggedIn(true);
      clientStatus.setLoggedInSince(LocalDateTime.now());
    }

    return new SingleResult ();
  }


  /**
   * @return the clientStatus
   */
  public ClientStatus getClientStatus() {
    return clientStatus;
  }


  /**
   * @param clientStatus the clientStatus to set
   */
  public void setClientStatus(ClientStatus clientStatus) {
    this.clientStatus = clientStatus;
  }
}
