/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import java.net.URLEncoder;

import com.google.gson.JsonObject;

import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.operation.JsonUtils;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.OperationData;
import io.github.jsoagger.core.bridge.result.SingleResult;
import io.github.jsoagger.core.business.cloud.services.api.IContainerApi;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class ContainerApi extends AbstractClientApi implements IContainerApi {


  private static final String PATH = "path";
  private static final String rootContainerId =
      "MTppby5naXRodWIuanNvYWdnZXIuY29yZS5tb2RlbC5hcGkuY29tcG9zaXRlLkFwcGxpY2F0aW9uQ29udGFpbmVy";

  private static final String GET_CONTAINER_BY_PATH = "/api/container/?path=%s";
  private static final String GETCONTAINER = "/api/container/%s/";

  private static final String GET_CONTAINER_ADMIN = "/api/container/%s/admins";
  private static final String GET_SUBCONTAINER = "/api/container/%s/subContainers";
  private static final String ROOT_FOLDER_URI = "/api/container/%s/rootFolder";
  private static final String MEMBERS_URI =
      "/api/container/%s/members/?page=%s&pageSize=%s&token=%s&includeParentItems=%s";
  private static final String ROLES_URI =
      "/api/container/%s/roles/?page=%s&pageSize=%s&includeParentItems=%s";
  private static final String FOLDER_TEMPLATES_URI =
      "/api/container/%s/folderTemplates/?page=%s&pageSize=%s&includeParentItems=%s";
  private static final String LIFECYCLE_URI =
      "/api/container/%s/lifecycles/?page=%s&pageSize=%s&includeParentItems=%s";
  private static final String ROOT_LINK_TYPES_URI =
      "/api/container/%s/rootLinkTypes/?page=%s&pageSize=%s&includeParentItems=%s";
  private static final String ROOT_TYPES_URI =
      "/api/container/%s/rootTypes/?page=%s&pageSize=%s&includeParentItems=%s";
  private static final String SUBCONTAINERS_FOLDER_URI =
      "/api/container/%s/subContainers/?page=%s&pageSize=%s";
  private static final String rulesIdentifier_URI =
      "/api/container/%s/businessRuleIdentifiers/?includeParentItems=%s";
  private static final String enTemplates_URI =
      "/api/container/%s/enTemplates/?page=%s&pageSize=%s&includeParentItems=%s";
  private static final String permissions_URI =
      "/api/container/%s/permissions/?page=%s&pageSize=%s&";


  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult permissions(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String page = query.get("page").getAsString();
      final String pageSize = query.get("pageSize").getAsString();


      final String byPathUrl =
          getRootUrl().concat(String.format(permissions_URI, id, page, pageSize));
      final IOperationResult result = doGet(query, byPathUrl, MultipleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }

  @Override
  public IOperationResult accessibleSubcontainer(JsonObject query) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult rootLinkTypes(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String page = query.get("page").getAsString();
      final String pageSize = query.get("pageSize").getAsString();
      final String includeParentItems =
          JsonUtils.getJsonString(query, "includeParentItems", "true");


      final String byPathUrl = getRootUrl()
          .concat(String.format(ROOT_LINK_TYPES_URI, id, page, pageSize, includeParentItems));
      final IOperationResult result = doGet(query, byPathUrl, MultipleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult rootTypes(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String page = query.get("page").getAsString();
      final String pageSize = query.get("pageSize").getAsString();
      final String includeParentItems =
          JsonUtils.getJsonString(query, "includeParentItems", "true");

      final String byPathUrl = getRootUrl()
          .concat(String.format(ROOT_TYPES_URI, id, page, pageSize, includeParentItems));
      final IOperationResult result = doGet(query, byPathUrl, MultipleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult lifecycles(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String page = query.get("page").getAsString();
      final String pageSize = query.get("pageSize").getAsString();
      final String includeParentItems =
          JsonUtils.getJsonString(query, "includeParentItems", "true");

      final String byPathUrl =
          getRootUrl().concat(String.format(LIFECYCLE_URI, id, page, pageSize, includeParentItems));
      final IOperationResult result = doGet(query, byPathUrl, MultipleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult folderTemplates(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String page = query.get("page").getAsString();
      final String pageSize = query.get("pageSize").getAsString();
      final String includeParentItems =
          JsonUtils.getJsonString(query, "includeParentItems", "true");

      final String byPathUrl = getRootUrl()
          .concat(String.format(FOLDER_TEMPLATES_URI, id, page, pageSize, includeParentItems));
      final IOperationResult result = doGet(query, byPathUrl, MultipleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult roles(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String page = query.get("page").getAsString();
      final String pageSize = query.get("pageSize").getAsString();
      final String includeParentItems =
          JsonUtils.getJsonString(query, "includeParentItems", "true");

      final String byPathUrl =
          getRootUrl().concat(String.format(ROLES_URI, id, page, pageSize, includeParentItems));
      final IOperationResult result = doGet(query, byPathUrl, MultipleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult subContainers(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String page = query.get("page").getAsString();
      final String pageSize = query.get("pageSize").getAsString();

      final String byPathUrl =
          getRootUrl().concat(String.format(SUBCONTAINERS_FOLDER_URI, id, page, pageSize));
      final IOperationResult result = doGet(query, byPathUrl, MultipleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult members(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String page = query.get("page").getAsString();
      final String pageSize = query.get("pageSize").getAsString();
      final String includeParentItems =
          JsonUtils.getJsonString(query, "includeParentItems", "true");

      String token = "";
      if (query.get("token") != null) {
        token = query.get("token").getAsString();
      }

      final String byPathUrl = getRootUrl()
          .concat(String.format(MEMBERS_URI, id, page, pageSize, token, includeParentItems));
      final IOperationResult result = doGet(query, byPathUrl, MultipleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult rootFolder(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String byPathUrl = getRootUrl().concat(String.format(ROOT_FOLDER_URI, id));
      final IOperationResult result = doGet(query, byPathUrl, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public synchronized IOperationResult getContainerByPath(JsonObject query) {
    try {
      final String path = query.get(PATH).getAsString();
      final String byPathUrl =
          getRootUrl().concat(String.format(GET_CONTAINER_BY_PATH, URLEncoder.encode(path)));

      System.out.println("333333333333 : " + byPathUrl);
      final IOperationResult result = doGet(query, byPathUrl, SingleResult.class);
      System.out.println("444444444444444 : " + result);

      OperationData data = (OperationData) result.rootData();
      if (path.equalsIgnoreCase("/Application") && data == null
          || data.getAttributes().get("id") == null) {
        data.getAttributes().put("id", rootContainerId);
      }

      System.out.println("444444444444444 2: " + result);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public synchronized IOperationResult getContainer(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String byOidUrl = getRootUrl().concat(String.format(GETCONTAINER, id));
      final IOperationResult result = doGet(null, byOidUrl, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public synchronized IOperationResult getContainerAdmins(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String byOidUrl = getRootUrl().concat(String.format(GET_CONTAINER_ADMIN, id));
      final IOperationResult result = doGet(null, byOidUrl, MultipleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult createChildContainer(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String createUrl = getRootUrl().concat(String.format(GET_SUBCONTAINER, id));
      final IOperationResult result = doPost(query, createUrl, SingleResult.class);
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
  public IOperationResult getRulesIdentifier(JsonObject query) {
    String containerId = getFullId(query);
    String includeParentItems = query.get("includeParentItems").getAsString();

    try {
      String url =
          getRootUrl().concat(String.format(rulesIdentifier_URI, containerId, includeParentItems));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.emptyMultipleResult();
    }
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getEnTemplates(JsonObject query) {
    String containerId = getFullId(query);
    String includeParentItems = query.get("includeParentItems").getAsString();
    final String page = query.get("page").getAsString();
    final String pageSize = query.get("pageSize").getAsString();

    try {
      String url = getRootUrl()
          .concat(String.format(enTemplates_URI, containerId, page, pageSize, includeParentItems));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.emptyMultipleResult();
    }
  }
}
