/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IFolderTemplateApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public class FolderTemplateApi extends AbstractClientApi implements IFolderTemplateApi {

  private static final String   FOLDER_TEMP_GET_WITH_NAME = "/api/folderTemplate/getWithName/?name=%s&containerId=%s";


  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult getWithName(JsonObject query) {
    try {
      String name = query.get("name").getAsString();
      String containerId = query.get("containerId").getAsString();

      final String createUrl = getRootUrl().concat(String.format(FOLDER_TEMP_GET_WITH_NAME, name.trim(), containerId));
      final IOperationResult result = doGet(query, createUrl, SingleResult.class);
      return result;
    }
    catch (final Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
