/**
 * 26 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.SingleResult;
import io.github.jsoagger.core.business.cloud.services.api.IPartApi;
import com.google.gson.JsonObject;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 26 févr. 2018
 */
public class PartApi extends AbstractClientApi implements IPartApi {

  private static final String CREATE_PART_UIR = "/jsoagger/part/v1/secured/api/part/";


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult createPart(JsonObject query) {
    try {
      String createUrl = getRootUrl().concat(String.format(CREATE_PART_UIR));
      IOperationResult result = doPost(query, createUrl, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

}
