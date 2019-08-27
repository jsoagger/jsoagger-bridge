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
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class SetPreferenceValueOperation implements IOperation{

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

      String key = query.get("key").getAsString();
      query.addProperty("userLogin", userLogin);

      IOperationResult result = CloudServicesLocator.preferenceApi.setPreference(query);
      if(!result.hasBusinessError()) {
        GetPreferenceValueOperation.CACHE.put(key, ((SingleResult)result).getData());
        resultHandler.accept(result);
      }
    }
    catch (Exception e) {
      if(exHandler != null) exHandler.accept(e);
    }
  }
}
