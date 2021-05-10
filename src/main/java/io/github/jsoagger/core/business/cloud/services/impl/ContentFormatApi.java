/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import java.net.URLEncoder;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IContentFormatApi;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class ContentFormatApi extends AbstractClientApi implements IContentFormatApi {

  private static final String MIME_TYPE_PATH = "/jsoagger/base/v1/secured/api/contentFormat/thumbnail?mimeType=%s";


  /**
   * {@inheritDoc}
   */
  @Override
  public byte[] getMimeTypeThumb(JsonObject query) {
    try {
      String mimeType = query.get("mimeType").getAsString();
      String url = getRootUrl().concat(String.format(MIME_TYPE_PATH, URLEncoder.encode(mimeType)));
      byte[] data = doGetByte(query, url);
      return data;
    }
    catch (Exception e) {
      return null;
    }
  }

}
