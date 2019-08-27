/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;


import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.SingleResult;
import io.github.jsoagger.core.business.cloud.services.api.IThumbedApi;
import com.google.gson.JsonObject;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class ThumbedApi extends AbstractClientApi implements IThumbedApi {

  private static final String	THUMB_URL			= "/api/thumbed/%s/thumb";
  private static final String	illustration_URL	= "/api/thumbed/%s/illustration";
  private static final String	format_URL			= "/api/thumbed/%s/format";


  /*
   * (non-Javadoc)
   *
   * @see io.github.jsoagger.core.business.cloud.services.api.IThumbedApi#getThumb(net.sf.json.
   * JsonObject)
   */
  @Override
  public byte[] getThumb(JsonObject query) {
    try {
      String fullId = query.get("fullId").getAsString();
      String url = getRootUrl().concat(String.format(THUMB_URL, fullId));
      byte[] data = doGetByte(query, url);
      return data;
    }
    catch (Exception e) {
      return null;
    }
  }


  /*
   * (non-Javadoc)
   *
   * @see
   * io.github.jsoagger.core.business.cloud.services.api.IThumbedApi#getIllustration(net.sf.
   * json.JsonObject)
   */
  @Override
  public byte[] getIllustration(JsonObject query) {
    try {
      String fullId = query.get("fullId").getAsString();
      String url = getRootUrl().concat(String.format(illustration_URL, fullId));
      byte[] data = doGetByte(query, url);
      return data;
    }
    catch (Exception e) {
      return null;
    }
  }


  /*
   * (non-Javadoc)
   *
   * @see
   * io.github.jsoagger.core.business.cloud.services.api.IThumbedApi#contentFormat(net.sf.json.
   * JsonObject)
   */
  @Override
  public IOperationResult contentFormat(JsonObject query) {
    try {
      String fullId = query.get("fullId").getAsString();
      String url = getRootUrl().concat(String.format(format_URL, fullId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

}
