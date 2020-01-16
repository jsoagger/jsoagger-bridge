/**
 *
 */
package io.github.jsoagger.core.business.offline.services.notifications;

import io.github.jsoagger.core.business.offline.services.notifications.api.INotificationsApi;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class OffLineServiceLocator {

  public static INotificationsApi notificationsService;

  /**
   * Constructor
   */
  public OffLineServiceLocator() {
  }


  /**
   * Setter of notificationsService
   *
   * @param persistableApi
   *            the notificationsService to set
   */
  public void setNotificationsServiceApi(INotificationsApi notificationsService) {
    OffLineServiceLocator.notificationsService = notificationsService;
  }
}
