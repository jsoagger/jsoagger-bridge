/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IEmailingApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public class EmailingApi extends AbstractClientApi  implements IEmailingApi {

  private static final String NO_REPLY_URL = "/jsoagger/base/v1/secured/api/emailing/sendNoReply";


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult sendNoReply(JsonObject query) {
    try {
      String containerId = query.get("containerId").getAsString();
      String url = getRootUrl().concat(String.format(NO_REPLY_URL));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

}
