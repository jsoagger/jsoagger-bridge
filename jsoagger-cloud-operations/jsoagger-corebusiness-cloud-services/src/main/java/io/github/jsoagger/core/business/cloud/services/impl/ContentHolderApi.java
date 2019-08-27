/**
 * 4 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IContentHolderApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.OperationData;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 4 févr. 2018
 */
public class ContentHolderApi extends AbstractClientApi implements IContentHolderApi {

  private static final String DOWNLOAD_URL = "/api/contentHolder/%s/content?role=%s&contentItemId=%s&containerId=%s";
  private static final String CONTENT_FORMAT_PATH = "/api/contentHolder/%s/contentInfo/?role=%s&containerId=%s";
  private static final String setContentFile = "/api/contentHolder/%s/setContentFile/?role=%s&containerId=%s";
  private static final String setContentUrl = "/api/contentHolder/%s/setContentUrl/?role=%s&url=%s&containerId=%s";
  private static final String deleteContentUrl = "/api/contentHolder/%s/content/?role=%s&contentItemId=%s&containerId=%s";

  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult downloadAllByHolderOid(JsonObject query) {
    try {
      final Object oid = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(DOWNLOAD_URL, oid, "all", "", containerId));
      final IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult downloadAttachmentsByHolderOid(JsonObject query) {
    try {
      final Object oid = getFullId(query);
      final Object contentItemFullId = query.get("contentItemId").getAsString();
      String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(DOWNLOAD_URL, oid, "attachments", contentItemFullId, containerId));
      byte[] data = doGetByte(query,url);
      SingleResult res = new SingleResult();
      res.getMetaData().put("content", new String(data));
      res.getMetaData().put("__http_code", 200);
      return res;
    } catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult downloadPrimaryContentByHolderOid(JsonObject query) {
    try {
      final Object oid = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(DOWNLOAD_URL, oid, "primary", "", containerId));
      byte[] data = doGetByte(query,url);
      SingleResult res = new SingleResult();
      res.getMetaData().put("content", new String(data));
      res.getMetaData().put("__http_code", 200);
      return res;
    } catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getAllContentsByHolderOid(JsonObject query) {
    try {
      final Object oid = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(CONTENT_FORMAT_PATH, oid, "all", containerId));
      final IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.emptyMultipleResult();
    }
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getAttachmentsByHolderOid(JsonObject query) {
    try {
      final Object oid = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(CONTENT_FORMAT_PATH, oid, "attachments", containerId));
      final IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.emptyMultipleResult();
    }
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getPrimaryContentByHolderOid(JsonObject query) {
    try {
      final Object oid = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(CONTENT_FORMAT_PATH,oid, "primary", containerId));
      final IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getContentInfo(JsonObject query) {
    try {
      final String role = query.get("role").getAsString();
      final String fullId = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(CONTENT_FORMAT_PATH, fullId, role, containerId));
      final IOperationResult operationResult = doGet(query, url, MultipleResult.class);

      if(role.equalsIgnoreCase("primary")) {
        if(!operationResult.hasBusinessError()) {
          MultipleResult mr = (MultipleResult) operationResult;
          // should always be
          if(mr.getData().size() == 1) {
            OperationData data = mr.getData().get(0);
            boolean isnone = "NONE".equalsIgnoreCase((String) data.getAttributes().get("name"));
            if(isnone) {
              return IOperationResult.emptyMultipleResult();
            }
          }
        }
      }

      return operationResult;
    } catch (final Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult setPrimaryContentByHolderOid(JsonObject query) {
    try {
      final String fullId = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      JsonElement path = query.get("primary.content.file");
      JsonElement purl = query.get("primary.content.url");

      // if file
      if(path != null && !path.isJsonNull() && !path.getAsString().equals("null")) {
        File file = new File(path.getAsString());
        if(file.exists()  && file.isFile()) {
          Map<String, File> data = new HashMap<>();
          data.put("file", file);

          final String url = getRootUrl().concat(String.format(setContentFile, fullId, "primary", containerId));
          final IOperationResult operationResult = doPostMp(query, data, url, SingleResult.class);
          return operationResult;
        }
      }

      // must be no empty!!
      String nurl = purl.getAsString();
      final String url = getRootUrl().concat(String.format(setContentUrl, fullId, "primary", URLEncoder.encode(nurl)));
      final IOperationResult operationResult = doPost(query, url, SingleResult.class);
      return operationResult;
    } catch (final Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult addAttachmentContentByHolderOid(JsonObject query) {
    try {
      final String fullId = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      JsonElement path = query.get("primary.content.file");
      JsonElement purl = query.get("primary.content.url");

      // if file
      if(path != null && !path.isJsonNull() && !path.getAsString().equals("null")) {
        File file = new File(path.getAsString());
        if(file.exists()  && file.isFile()) {
          Map<String, File> data = new HashMap<>();
          data.put("file", file);

          final String url = getRootUrl().concat(String.format(setContentFile, fullId, "attachments", containerId));
          final IOperationResult operationResult = doPostMp(query, data, url, SingleResult.class);
          return operationResult;
        }
      }

      String nurl = purl.getAsString();
      final String url = getRootUrl().concat(String.format(setContentUrl, fullId, "attachments", URLEncoder.encode(nurl)));
      final IOperationResult operationResult = doPost(query, url, SingleResult.class);
      return operationResult;
    } catch (final Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult deleteContentItemByHolderOid(JsonObject query) {
    try {
      final String fullId = getFullId(query);
      final String role = query.get("role").getAsString();
      String containerId = query.get("containerId").getAsString();

      String contentItemFullId  = "";
      if("attachments".equalsIgnoreCase(role)) contentItemFullId = query.get("contentItemFullId").getAsString();

      final String url = getRootUrl().concat(String.format(deleteContentUrl, fullId, role, URLEncoder.encode(contentItemFullId), containerId));
      final IOperationResult operationResult = doDelete(query, url, SingleResult.class);
      return operationResult;
    } catch (final Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
