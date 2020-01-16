/**
 * 26 févr. 2018
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
 * Get all dynamical attributes of a given type by its typeLogicalPath
 *
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 26 févr. 2018
 */
public class GetDynamicalAttributesDefinitionOperation implements IOperation {

  // needs PlatformOperationsCache
  private PlatformOperationsCache cache;


  /**
   * Default Constructor
   */
  public GetDynamicalAttributesDefinitionOperation() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      final String id = query.get("id").getAsString();
      final String cacheKey = id + "__DYNA_ATTR_DEFINITIONS";

      IOperationResult res = null;
      if (cache.contains(cacheKey, CachedObjectType.OPERATION_RESULT)) {
        res = cache.get(cacheKey, CachedObjectType.OPERATION_RESULT);
        resultHandler.accept(res);
        return;
      }

      final IOperationResult result = CloudServicesLocator.typeApi.softAttributesDefinition(query);

      if (result.hasBusinessError()) {
        resultHandler.accept(result);
        return;
      }

      cache.put(cacheKey, result, CachedObjectType.OPERATION_RESULT);
      //doOperation(query, resultHandler, exHandler);
      resultHandler.accept(res);
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
