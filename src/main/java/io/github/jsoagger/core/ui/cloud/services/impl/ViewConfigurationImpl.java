/**
 *
 */
package io.github.jsoagger.core.ui.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.ui.cloud.services.api.IViewConfigurationApi;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
//ViewConfigurationApi
public class ViewConfigurationImpl implements IViewConfigurationApi {

  static String URLX = "http://localhost:8080/jsoagger-viewconfig-services/serv/core/views/definition/?path=/comp/CoreActions.xml";


  /*
   * (non-Javadoc)
   *
   * @see io.github.jsoagger.core.cloud.services.ui.api.IViewConfigurationApi#
   * getViewConfigration(net.sf.json.JsonObject)
   */
  @Override
  public IOperationResult getViewConfiguration(JsonObject query) {
    return null;
  }


  /**
   * Do http get operation and add header to request before sending it
   *
   * @param query
   * @param url
   * @return IOperationResult
   */
  public JsonObject doGet(JsonObject query, String url, Class<?> clazz) {
    return null;
  }
}
