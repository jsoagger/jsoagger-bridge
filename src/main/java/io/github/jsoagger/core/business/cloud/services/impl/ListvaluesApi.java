/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IListvaluesApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.operation.JsonUtils;
import io.github.jsoagger.core.bridge.result.MultipleResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class ListvaluesApi extends AbstractClientApi implements IListvaluesApi {

  private static final String	URI		= "/jsoagger/base/v1/secured/api/listvalues/?name=%s&locale=%s&containerId=%s&includeParentItems=%s";


  /**
   * @{inheritedDoc}
   */
  @Override public IOperationResult getByName(JsonObject query) {
    try {
      String enumerationKey = query.get("enumerationKey").getAsString();
      String containerId = query.get("containerId").getAsString();
      String includeParentItems = query.get("includeParentItems").getAsString();
      String locale = JsonUtils.getJsonString(query, "locale", "en");

      String byPathUrl = getRootUrl().concat(String.format(URI, enumerationKey,locale, containerId, includeParentItems));
      IOperationResult result = doGet(query, byPathUrl, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }

}
