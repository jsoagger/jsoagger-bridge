/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IContainedApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public class ContainedApi extends AbstractClientApi implements IContainedApi {

  private static final String   GET_CONTAINER_OF       = "/v1/secured/api/contained/%s/container";


  @Override
  public IOperationResult getContainerOf(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String byPathUrl = getRootUrl().concat(String.format(GET_CONTAINER_OF, id));
      final IOperationResult result = doGet(query, byPathUrl, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

}
