/**
 * 20 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import java.net.URLEncoder;
import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IFolderApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.operation.JsonUtils;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 20 févr. 2018
 */
public class FolderApi extends AbstractClientApi implements IFolderApi {

  private static final String	FOLDER_CHILDREN_URI			= "/jsoagger/base/v1/secured/api/folder/%s/children/?containerId=%s";
  private static final String	COUNT_FOLDER_CHILDREN_URI	= "/jsoagger/base/v1/secured/api/folder/%s/children/count/?containerId=%s";
  private static final String	FOLDER_CONTENT_URI			= "/jsoagger/base/v1/secured/api/folder/%s/content/?contentClass=%s&page=%s&pageSize=%s&containerId=%s";
  private static final String	COUNT_FOLDER_CONTENT_URI	= "/jsoagger/base/v1/secured/api/folder/%s/content/count/?contentClass=%s&containerId=%s";
  private static final String	FOLDER_CREATE_URI			= "/jsoagger/base/v1/secured/api/folder/%s/?containerId=%s";
  private static final String   FOLDER_DELETE_URI           = "/jsoagger/base/v1/secured/api/folder/%s/?containerId=%s";
  private static final String   FOLDER_GET_URI           = "/jsoagger/base/v1/secured/api/folder/%s/?containerId=%s";
  private static final String	FOLDER_BY_PATH_URI			= "/jsoagger/base/v1/secured/api/folder/byPath?path=%s&containerId=%s";

  private static final String	POPULATE_FROM_URI	= "/jsoagger/base/v1/secured/api/folder/%s/populate/?templateId=%s&containerId=%s";


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult createFolder(JsonObject query) {
    try {
      final String folderOid = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      final String createUrl = getRootUrl().concat(String.format(FOLDER_CREATE_URI, folderOid, containerId));
      final IOperationResult result = doPost(query, createUrl, SingleResult.class);
      return result;
    }
    catch (final Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult getFolder(JsonObject query) {
    try {
      final String folderOid = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      final String createUrl = getRootUrl().concat(String.format(FOLDER_GET_URI, folderOid, containerId));
      final IOperationResult result = doGet(query, createUrl, SingleResult.class);
      return result;
    }
    catch (final Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult deleteFolder(JsonObject query) {
    try {
      final String folderOid = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      final String createUrl = getRootUrl().concat(String.format(FOLDER_DELETE_URI, folderOid, containerId));
      final IOperationResult result = doDelete(query, createUrl, SingleResult.class);
      return result;
    }
    catch (final Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getFolderChidren(JsonObject query) {
    try {
      String folderOid = null;
      try {
        folderOid = getFullId(query);
      }
      catch (final Exception ex) {
        folderOid = getFullId(query);
      }

      String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(FOLDER_CHILDREN_URI, folderOid, containerId));
      final IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (final Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult countFolderChidren(JsonObject query) {
    try {
      final String folderOid = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(COUNT_FOLDER_CHILDREN_URI, folderOid, containerId));
      final IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (final Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult populateFromTemplate(JsonObject query) {
    try {
      final String folderOid = getFullId(query);
      final String templateOid = query.get("templateId").getAsString();
      String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(POPULATE_FROM_URI, folderOid, templateOid, containerId));
      final IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (final Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getFolderContent(JsonObject query) {
    try {
      final String folderOid = getFullId(query);
      final String contentClass = query.get("contentClass").getAsString();
      String containerId = query.get("containerId").getAsString();

      final Integer page = JsonUtils.getJsonInt(query, "page", 0);
      final Integer pageSize = JsonUtils.getJsonInt(query,"pageSize", 10);

      final String url = getRootUrl().concat(String.format(FOLDER_CONTENT_URI, folderOid, contentClass, page, pageSize, containerId));
      final IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (final Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void getFolderContentAsynch(JsonObject query, Consumer<IOperationResult> c) {
    try {
      final String folderOid = getFullId(query);
      final String contentClass = query.get("contentClass").getAsString();
      String containerId = query.get("containerId").getAsString();

      final Integer page = JsonUtils.getJsonInt(query, "page", 0);
      final Integer pageSize = JsonUtils.getJsonInt(query,"pageSize", 10);


      final String url = getRootUrl().concat(String.format(FOLDER_CONTENT_URI, folderOid, contentClass, page, pageSize, containerId));
      doGetAsynch(query, url, MultipleResult.class, c);
    }
    catch (final Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult countFolderContent(JsonObject query) {
    try {
      final String folderOid = getFullId(query);
      final String contentClass = "io.github.jsoagger.core.model.part.ElementMaster";
      String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(COUNT_FOLDER_CONTENT_URI, folderOid, contentClass, containerId));
      final IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (final Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult getFolderByPath(JsonObject query) {
    try {
      final String path = query.get("path").getAsString();
      String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(FOLDER_BY_PATH_URI, URLEncoder.encode(path), containerId));
      final IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (final Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
