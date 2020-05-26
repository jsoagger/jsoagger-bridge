/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.ITypeManagedApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana  VONJISOA
 */
public class TypeManagedApi extends AbstractClientApi implements ITypeManagedApi {

  private static final String attributeDefinitions_url = "/v1/secured/api/typeManaged/%s/attributeDefinitions/?containerId=%s";
  private static final String typeof_url = "/v1/secured/api/typeManaged/%s/typeOf/?containerId=%s";

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult attributeDefinitions(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String containerId = query.get("containerId").getAsString();
      String url = getRootUrl().concat(String.format(attributeDefinitions_url, fullId, containerId));
      IOperationResult result = doGet(null, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult typeOf(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String containerId = query.get("containerId").getAsString();
      String url = getRootUrl().concat(String.format(typeof_url, fullId, containerId));
      IOperationResult result = doGet(null, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
