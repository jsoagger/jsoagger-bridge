/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.utils;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;




/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class ServerStatusCheckerTask  {

  private ResourceBundle		bundle			= ResourceBundle.getBundle("MessageBundle");
  private Locale				defaultLocale	= Locale.getDefault();
  //private ApplicationContext	applicationContext;


  /**
   * Constructor
   */
  public ServerStatusCheckerTask() {
  }


  /**
   * @{inheritedDoc}
   */
  public void checkServerStatus() {
    try {

      ClientStatus clientStatus = null;
      boolean userWasLoggedIn = clientStatus.isLoggedIn();
      if (userWasLoggedIn) {

        //logR.debug("Calling heartbeat service with authenticated user");
        // CloudServicesLocator.heartbeatApi.salama();
      }
      else {

        //logR.debug("Calling heartbeat service with non authenticated user");
        // CloudServicesLocator.heartbeatApi.ping();
      }

      if (clientStatus.downSince() != null) {
        clientStatus.setDownSince(null);
      }
    }
    catch (Exception e) {
      ClientStatus clientStatus = null;
      if (clientStatus != null && clientStatus.downSince() == null) {
        clientStatus.setDownSince(LocalDateTime.now());
      }
    }
  }
}
