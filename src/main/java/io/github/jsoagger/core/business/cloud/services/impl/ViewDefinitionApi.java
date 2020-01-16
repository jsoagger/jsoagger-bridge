/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IViewDefinitionApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public class ViewDefinitionApi extends AbstractClientApi implements IViewDefinitionApi {

  static String getAllSchemasByInternalName = "/anon/api/viewDefinition/getAllSchemasByInternalName?internalName=%s";
  static String getUISchemaByInternalName = "/anon/api/viewDefinition/getUISchemaByInternalName?internalName=%s&platform=%s";
  static String getJSONSchemaByInternalName = "/anon/api/viewDefinition/getJSONSchemaByInternalName?internalName=%s&platform=%s";
  static String getAll = "/api/viewDefinition/?platform=%s";
  static String getById = "/api/viewDefinition/%s";
  static String getByInternalName = "/api/viewDefinition/getByInternalName/?internalName=%s";

  //remote return jsonSchema + uiSchema (if platform is not null)
  @Override
  public IOperationResult allSchemas(JsonObject query) {
    try {
      String internalName = query.get("internalName").getAsString();
      String url = getRootUrl().concat(String.format(getAllSchemasByInternalName, internalName));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  // remote return jsonSchema + uiSchema (if platform is not null)
  @Override
  public IOperationResult jsonSchema(JsonObject query) {
    try {
      String internalName = query.get("internalName").getAsString();
      String platform = query.get("platform") != null ? query.get("platform").getAsString() : "";
      String url = getRootUrl().concat(String.format(getJSONSchemaByInternalName, internalName, platform));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  // remote return jsonSchema + uiSchema (platform is mandatory)
  @Override
  public IOperationResult uiSchema(JsonObject query) {
    try {
      String internalName = query.get("internalName").getAsString();
      String platform = query.get("platform").getAsString();
      String url = getRootUrl().concat(String.format(getUISchemaByInternalName, internalName, platform));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }

  @Override
  public IOperationResult getAllByPlatform(JsonObject query) {
    try {
      String platform = query.get("platform") != null ? query.get("platform").getAsString() : "";
      String url = getRootUrl().concat(String.format(getAll, platform));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }

  @Override
  public IOperationResult getById(JsonObject query) {
    try {
      String id = getFullId(query);
      String url = getRootUrl().concat(String.format(getById, id));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult getByInternalName(JsonObject query) {
    try {
      String internalName = query.get("internalName").getAsString();
      String url = getRootUrl().concat(String.format(getByInternalName, internalName));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult delete(JsonObject query) {
    try {
      String id = getFullId(query);
      String url = getRootUrl().concat(String.format(getById, id));
      IOperationResult result = doDelete(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult update(JsonObject query) {
    try {
      String id = getFullId(query);
      String url = getRootUrl().concat(String.format(getById, id));
      IOperationResult result = doPut(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
