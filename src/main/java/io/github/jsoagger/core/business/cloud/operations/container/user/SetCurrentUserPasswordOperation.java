/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.container.user;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.operations.cache.PlatformOperationsCache;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class SetCurrentUserPasswordOperation implements IOperation {

  public static final String	CACHE_KEY	= "PLATFORM_CURRENT_USER";
  private int					tries		= 0;

  // needs PlatformOperationsCache
  private PlatformOperationsCache cache;


  /**
   * Default Constructor
   */
  public SetCurrentUserPasswordOperation() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      IOperationResult result = CloudServicesLocator.userPrincipalApi.updatePassword(query);
      resultHandler.accept(result);
    }
    catch (Exception e) {
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