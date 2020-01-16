/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public interface IAppVersionApi {

  IOperationResult getById(JsonObject query);
  IOperationResult getVersionHistory(JsonObject query);
  IOperationResult delete(JsonObject query);
  IOperationResult create(JsonObject query);
  IOperationResult getApplicationVersion(JsonObject query);
  IOperationResult getInstallationStatus(JsonObject query);
  IOperationResult getPendingInstallation(JsonObject query);
  IOperationResult setInstalled(JsonObject query);
  IOperationResult setFailed(JsonObject query);


  IOperationResult getDataPatchInstallationStatus(JsonObject query);
  IOperationResult setDataPatchInstallationStatus(JsonObject query);
}
