/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;


import com.google.gson.JsonObject;

import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.SingleResult;
import io.github.jsoagger.core.business.cloud.services.api.IThumbedApi;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public class ThumbedApi extends AbstractClientApi implements IThumbedApi {

  private static final String THUMB_URL = "/transdev/thumbed/v1/secured/api/thumbed/%s/thumb/?w=%s&h=%s";
  private static final String illustration_URL = "/transdev/thumbed/v1/secured/api/thumbed/%s/illustration/?w=%s&h=%s";
  private static final String format_URL = "/transdev/thumbed/v1/secured/api/thumbed/%s/format";


  @Override
  public byte[] getThumb(JsonObject query) {
    try {
      String fullId = query.get("fullId").getAsString();
      String width = query.get("width") != null ? query.get("width").getAsString() : "400";
      String height = query.get("width") != null ? query.get("width").getAsString() : "-1";

      String url = getRootUrl().concat(String.format(THUMB_URL, fullId, width, height));
      byte[] data = doGetByte(query, url);
      return data;
    } catch (Exception e) {
      return null;
    }
  }


  @Override
  public byte[] getIllustration(JsonObject query) {
    try {
      String fullId = query.get("fullId").getAsString();
      String width = query.get("width") != null ? query.get("width").getAsString() : "800";
      String height = query.get("width") != null ? query.get("width").getAsString() : "-1";

      String url = getRootUrl().concat(String.format(illustration_URL, fullId, width, height));
      byte[] data = doGetByte(query, url);
      return data;
    } catch (Exception e) {
      return null;
    }
  }


  /*
   * (non-Javadoc)
   *
   * @see io.github.jsoagger.core.business.cloud.services.api.IThumbedApi#contentFormat(net.sf.json.
   * JsonObject)
   */
  @Override
  public IOperationResult contentFormat(JsonObject query) {
    try {
      String fullId = query.get("fullId").getAsString();
      String url = getRootUrl().concat(String.format(format_URL, fullId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

}
