/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.preferences;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.operations.cache.PlatformOperationsCache;
import io.github.jsoagger.core.business.cloud.operations.cache.PlatformOperationsCache.CachedObjectType;
import io.github.jsoagger.core.business.cloud.operations.container.user.GetCurrentUserOperation;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.OperationData;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class LoadAllCurrentUserPreferencesValueOperation implements IOperation{

  /**
   * {@inheritDoc}
   */
  @Override
  public void doOperation(JsonObject query,
      Consumer<IOperationResult> resultHandler,
      Consumer<Throwable> exHandler) {

    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      SingleResult currentUser = (SingleResult) PlatformOperationsCache.get(GetCurrentUserOperation.CACHE_KEY, CachedObjectType.OPERATION_RESULT);
      String userLogin = (String) currentUser.getData().getAttributes().get("nickName");

      query.addProperty("userLogin", userLogin);

      IOperationResult result = CloudServicesLocator.preferenceApi.getAllUserPreferences(query);
      if(!result.hasBusinessError()) {
        if(resultHandler != null) resultHandler.accept(result);

        MultipleResult mr = (MultipleResult) result;
        for(OperationData d: mr.getData()) {
          String k = (String) d.getAttributes().get("key");
          GetPreferenceValueOperation.CACHE.put(k, d);
        }
      }
    }
    catch (Exception e) {
      if(exHandler != null) exHandler.accept(e);
    }
  }
}
