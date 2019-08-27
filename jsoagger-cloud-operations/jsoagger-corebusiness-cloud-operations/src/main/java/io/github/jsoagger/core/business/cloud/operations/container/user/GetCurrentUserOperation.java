/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.container.user;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.operations.cache.PlatformOperationsCache;
import io.github.jsoagger.core.business.cloud.operations.cache.PlatformOperationsCache.CachedObjectType;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class GetCurrentUserOperation implements IOperation {

  public static final String	CACHE_KEY	= "PLATFORM_CURRENT_USER";
  private int					tries		= 0;

  // needs PlatformOperationsCache
  private PlatformOperationsCache cache;


  /**
   * Default Constructor
   */
  public GetCurrentUserOperation() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    if (!cache.contains(CACHE_KEY, CachedObjectType.OPERATION_RESULT)) {

      try {
        if(query == null) throw new NullPointerException("Query can not be null");
        final IOperationResult res = CloudServicesLocator.userPrincipalApi.getCurrentUserAccount(query);
        if (res.hasBusinessError()) {
          // sleep and retry
          Thread.sleep(1000);
          if (tries < 2) {
            tries++;
            doOperation(query, resultHandler, exHandler);
          }
        }
        else {
          cache.put(CACHE_KEY, res, CachedObjectType.OPERATION_RESULT);
          final IOperationResult clone = cache.get(CACHE_KEY, CachedObjectType.OPERATION_RESULT);
          if(resultHandler != null)resultHandler.accept(clone);
        }
      }
      catch (final Exception e) {
        e.printStackTrace();
      }
    }
    else {
      final IOperationResult clone = cache.get(CACHE_KEY, CachedObjectType.OPERATION_RESULT);
      if(resultHandler != null) resultHandler.accept(clone);
    }
  }


  /**
   * @return the cache
   */
  public PlatformOperationsCache getCache() {
    return cache;
  }


  /**
   * @param cache the cache to set
   */
  public void setCache(PlatformOperationsCache cache) {
    this.cache = cache;
  }
}