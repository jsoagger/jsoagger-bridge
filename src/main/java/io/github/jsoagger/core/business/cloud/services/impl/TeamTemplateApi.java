/**
 * 14 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;


import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.ITeamTemplateApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.operation.JsonUtils;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 14 févr. 2018
 */
public class TeamTemplateApi extends AbstractClientApi implements ITeamTemplateApi {

  public static String	TEAM_TEMP_ROOT_URL	= "/v1/secured/api/teamTemplate/%s/?containerId=%s";
  public static String	BY_NAME_URL			= "/v1/secured/api/teamTemplate/byName?internalName=%s&containerId=%s";
  public static String	GET_BY_OID_URL		= "/v1/secured/api/teamTemplate/%s/?containerId=%s";
  public static String	GET_CLOB_URL		= "/v1/secured/api/teamTemplate/%s/template/?containerId=%s";
  public static String	MEMBERS_URL			= "/v1/secured/api/teamTemplate/%s/members/?containerId=%s";

  public static String	POPULATE_URL	= "/v1/secured/api/container/%s/populateMembers/?containerId=%s";
  public static String	GET_ALL_URL		= "/v1/secured/api/container/%s/teamTemplates/?&page=%s&pageSize=%s&sort=%s&containerId=%s";


  @Override
  public IOperationResult getUsersInTemplate(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(MEMBERS_URL, fullId, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult deleteTeamTemplate(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(TEAM_TEMP_ROOT_URL, fullId, containerId));
      IOperationResult result = doDelete(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult getByFullId(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(TEAM_TEMP_ROOT_URL, fullId, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult getByName(JsonObject query) {
    try {
      String internalName = query.get("internalName").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(BY_NAME_URL, internalName, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult getTeamTemplatesInContainer(JsonObject query) {
    try {
      String containerId = query.get("containerId").getAsString();
      Object page = JsonUtils.getJsonString(query, "page", "0");
      Object pageSize = JsonUtils.getJsonString(query, "pageSize", "5");
      Object sort = JsonUtils.getJsonString(query, "sort", "");

      String byPathUrl = getRootUrl().concat(String.format(GET_ALL_URL, containerId, page, pageSize, sort));
      IOperationResult result = doGet(query, byPathUrl, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }


  @Override
  public IOperationResult getTeamTemplateClob(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String byPathUrl = getRootUrl().concat(String.format(GET_CLOB_URL, containerId));
      IOperationResult result = doGet(query, byPathUrl, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult populateContainerMembersFromTemplate(JsonObject query) {
    try {
      String containerId = query.get("containerId").getAsString();
      query.get("teamTemplate");

      String url = getRootUrl().concat(String.format(POPULATE_URL, containerId));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

}
