/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;

import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;
import io.github.jsoagger.core.business.cloud.services.api.IAppVersionApi;


/**
 * @author Ramilafananana VONJISOA
 *
 */
public class AppVersionApi extends AbstractClientApi implements IAppVersionApi {

  static String PENDING_ISNTALLATION_URL = "/v1/secured/api/versionHistory/pendingInstallation/?buildModuleName=%s&buildModuleVersion=%s";
  static String INSTALLATION_STATUS_URL = "/v1/secured/api/versionHistory/installationStatus?buildModuleName=%s&buildModuleVersion=%s";
  static String VERSION_URL = "/v1/secured/api/versionHistory/version/?buildModuleName=%s";
  static String CREATE_URL = "/v1/secured/api/versionHistory/";
  static String GET_BY_ID_URL = "/v1/secured/api/versionHistory/%s";
  static String DELETE_URL = "/v1/secured/api/versionHistory/%s";
  static String SET_INSTALLED_URL = "/v1/secured/api/versionHistory/%s/installed";
  static String SET_FAILED_URL = "/v1/secured/api/versionHistory/%s/failed";
  static String GET_ALL_URL = "/v1/secured/api/versionHistory/?label=%s";

  static String getDataPatchInstallationStatusURL = "/v1/anon/dataPatch/installationStatus/?buildModuleVersion=%s&buildModuleName=%s";
  static String setDataPatchInstallationStatusURL = "/v1/secured/api/versionHistory/dataPatchInstallationStatus/";


  @Override
  public IOperationResult getDataPatchInstallationStatus(JsonObject query) {
    try {
      String buildVersion = query.get("buildModuleVersion").getAsString();
      String dataPatchName = query.get("buildModuleName").getAsString();
      String url = getRootUrl().concat(String.format(getDataPatchInstallationStatusURL, buildVersion, dataPatchName));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult setDataPatchInstallationStatus(JsonObject query) {
    try {
      String buildVersion = query.get("buildModuleVersion").getAsString();
      String dataPatchName = query.get("buildModuleName").getAsString();
      String status = query.get("status").getAsString();
      String url = getRootUrl().concat(String.format(setDataPatchInstallationStatusURL));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult getById(JsonObject query) {
    try {
      String identifier = getFullId(query);
      String url = getRootUrl().concat(String.format(GET_BY_ID_URL, identifier));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult getVersionHistory(JsonObject query) {
    try {
      String label = query.get("buildModuleName") != null ? query.get("buildModuleName").getAsString() : "";
      String url = getRootUrl().concat(String.format(GET_ALL_URL, label));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }

  @Override
  public IOperationResult delete(JsonObject query) {
    try {
      String identifier = getFullId(query);
      String url = getRootUrl().concat(String.format(DELETE_URL, identifier));
      IOperationResult result = doDelete(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult create(JsonObject query) {
    try {
      String url = getRootUrl().concat(String.format(CREATE_URL));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult getApplicationVersion(JsonObject query) {
    try {
      String label = query.get("label").getAsString();
      String url = getRootUrl().concat(String.format(VERSION_URL, label));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult getInstallationStatus(JsonObject query) {
    try {
      String buildModuleName = query.get("buildModuleName").getAsString();
      String buildModuleVersion = query.get("buildModuleVersion").getAsString();

      String url = getRootUrl().concat(String.format(INSTALLATION_STATUS_URL, buildModuleName, buildModuleVersion));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult getPendingInstallation(JsonObject query) {
    try {
      String buildModuleName = query.get("buildModuleName").getAsString();
      String buildModuleVersion = query.get("buildModuleVersion") != null ? query.get("buildModuleVersion").getAsString() : "";

      String url = getRootUrl().concat(String.format(PENDING_ISNTALLATION_URL, buildModuleName, buildModuleVersion));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult setInstalled(JsonObject query) {
    try {
      String id = getFullId(query);
      String url = getRootUrl().concat(String.format(SET_FAILED_URL, id));
      IOperationResult result = doPut(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult setFailed(JsonObject query) {
    try {
      String id = getFullId(query);
      String url = getRootUrl().concat(String.format(SET_INSTALLED_URL, id));
      IOperationResult result = doPut(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
