/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IOrganizationApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public class OrganizationApi extends AbstractClientApi implements IOrganizationApi {

  private static final String   BY_SIRET_EQUALS             = "/api/organization/bySiretEquals/?siret=%s&containerId=%s";
  private static final String   BY_NAME_LIKE                = "/api/organization/byNameLike/?name=%s&containerId=%s";
  private static final String   RENAME                      = "/api/organization/%s/rename/?containerId=%s";

  @Override
  public IOperationResult createOrganization(JsonObject query) {
    return null;
  }

  @Override
  public IOperationResult rename(JsonObject query) {
    try {
      String id = query.get("id").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(RENAME, id, containerId));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult getByNameLike(JsonObject query) {
    try {
      String containerId = query.get("containerId").getAsString();
      String name = query.get("name").getAsString();

      String url = getRootUrl().concat(String.format(BY_NAME_LIKE, name, containerId));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }

  @Override
  public IOperationResult getBySiretEquals(JsonObject query) {
    try {
      String containerId = query.get("containerId").getAsString();
      String siret = query.get("siret").getAsString();

      String url = getRootUrl().concat(String.format(BY_SIRET_EQUALS, siret, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
