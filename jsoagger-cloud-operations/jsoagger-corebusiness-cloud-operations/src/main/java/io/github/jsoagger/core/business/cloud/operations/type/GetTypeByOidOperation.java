/**
 * 20 janv. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.operations.type;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.operations.cache.PlatformOperationsCache;
import io.github.jsoagger.core.business.cloud.operations.cache.PlatformOperationsCache.CachedObjectType;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 20 janv. 2018
 */
public class GetTypeByOidOperation implements IOperation {

  // PlatformOperationsCache needs
  private PlatformOperationsCache cache;


  /**
   * Default Constructor
   */
  public GetTypeByOidOperation() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      final String oid = query.get("oid").getAsString();
      IOperationResult res = null;
      if (cache.contains(oid, CachedObjectType.OPERATION_RESULT)) {
        res = cache.get(oid, CachedObjectType.OPERATION_RESULT);
        resultHandler.accept(res);
        return;
      }

      final IOperationResult result = CloudServicesLocator.persistableApi.loadBasicModel(query);

      if (result.hasBusinessError()) {
        resultHandler.accept(result);
        return;
      }

      cache.put(oid, result, CachedObjectType.OPERATION_RESULT);
      doOperation(query, resultHandler, exHandler);
    }
    catch (final Exception e) {
      if(exHandler != null) exHandler.accept(e);
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
