/**
 *
 */
package io.github.jsoagger.core.ui.cloud.services.utils;

import io.github.jsoagger.core.ui.cloud.services.api.IClientDashBoardApi;
import io.github.jsoagger.core.ui.cloud.services.api.IViewConfigurationApi;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class UICloudServicesLocator {

  public static IClientDashBoardApi	clientDashBoardApi;
  public static IViewConfigurationApi	viewConfigurationApi;

  /**
   * Constructor
   */
  public UICloudServicesLocator() {
  }


  /**
   * Setter of clientDashBoardApi
   *
   * @param clientDashBoardApi
   *            the clientDashBoardApi to set
   */
  public void setClientDashBoardApi(IClientDashBoardApi clientDashBoardApi) {
    UICloudServicesLocator.clientDashBoardApi = clientDashBoardApi;
  }


  /**
   * @param viewConfigurationApi
   */
  public static void setViewConfigurationApi(IViewConfigurationApi viewConfigurationApi) {
    UICloudServicesLocator.viewConfigurationApi = viewConfigurationApi;
  }
}
