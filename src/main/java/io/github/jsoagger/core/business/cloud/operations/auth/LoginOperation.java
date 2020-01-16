/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.auth;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.operations.ICloudOperation;
import io.github.jsoagger.core.business.cloud.operations.utils.ClientStatus;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class LoginOperation implements ICloudOperation {

  public static final String	SESSION_ID_SYSTEM_PROPERTY_NAME	= "shiro.session.id";
  private ResourceBundle		bundle							= ResourceBundle.getBundle("MessageBundle");
  private Locale				defaultLocale					= Locale.getDefault();

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
    IOperationResult result = CloudServicesLocator.authenticationApi.login(query);
    if (!result.hasMessage()) {
      Map object = result.getMetaData();
      System.setProperty(SESSION_ID_SYSTEM_PROPERTY_NAME, (String) object.get("session_id"));

      clientStatus.setLoggedIn(true);
      clientStatus.setLoggedInSince(LocalDateTime.now());
    }

    return result;
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
