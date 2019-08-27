/**
 * 24 janv. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.operations.decorated;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.operations.aspect.ContentHolderFeatureArgs;
import io.github.jsoagger.core.business.cloud.operations.aspect.LifecycleManagedFeatureArgs;
import io.github.jsoagger.core.business.cloud.operations.aspect.TypeManagedFeatureArgs;
import io.github.jsoagger.core.business.cloud.operations.cache.PlatformOperationsCache;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * Load a content holder with its content formats.
 *
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 24 janv. 2018
 */
public class PersistableLoadBasicContentHolderModelOperation implements IOperation {

  private IOperationResult result;
  private PlatformOperationsCache cache;
  private TypeManagedFeatureArgs typeManagedFeatureArgs = new TypeManagedFeatureArgs();
  private LifecycleManagedFeatureArgs lifecycleManagedFeatureArgs = new LifecycleManagedFeatureArgs();
  private ContentHolderFeatureArgs contentHolderFeatureArgs = new ContentHolderFeatureArgs();

  /**
   * Constructor
   */
  public PersistableLoadBasicContentHolderModelOperation() {
    typeManagedFeatureArgs.setLoadType(true);
    typeManagedFeatureArgs.setLoadAttributeDefinitions(true);

    lifecycleManagedFeatureArgs.setLoadAllLifecycleStates(true);
    lifecycleManagedFeatureArgs.setLoadLifecycleName(true);

    contentHolderFeatureArgs.setAttachmentsFormat(true);
    contentHolderFeatureArgs.setPrimaryFormat(true);
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler,
      Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      result = CloudServicesLocator.persistableApi.loadBasicRCModel(query);
      resultHandler.accept(result);
    } catch (final Exception e) {
      if (exHandler != null) {
        exHandler.accept(e);
      }
    }
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getResult() {
    return result;
  }

  /**
   * @return the typeManagedFeatureArgs
   */
  public TypeManagedFeatureArgs getTypeManagedFeatureArgs() {
    return typeManagedFeatureArgs;
  }

  /**
   * @param typeManagedFeatureArgs the typeManagedFeatureArgs to set
   */
  public void setTypeManagedFeatureArgs(TypeManagedFeatureArgs typeManagedFeatureArgs) {
    this.typeManagedFeatureArgs = typeManagedFeatureArgs;
  }

  /**
   * @return the lifecycleManagedFeatureArgs
   */
  public LifecycleManagedFeatureArgs getLifecycleManagedFeatureArgs() {
    return lifecycleManagedFeatureArgs;
  }

  /**
   * @param lifecycleManagedFeatureArgs the lifecycleManagedFeatureArgs to set
   */
  public void setLifecycleManagedFeatureArgs(LifecycleManagedFeatureArgs lifecycleManagedFeatureArgs) {
    this.lifecycleManagedFeatureArgs = lifecycleManagedFeatureArgs;
  }

  /**
   * @return the contentHolderFeatureArgs
   */
  public ContentHolderFeatureArgs getContentHolderFeatureArgs() {
    return contentHolderFeatureArgs;
  }

  /**
   * @param contentHolderFeatureArgs the contentHolderFeatureArgs to set
   */
  public void setContentHolderFeatureArgs(ContentHolderFeatureArgs contentHolderFeatureArgs) {
    this.contentHolderFeatureArgs = contentHolderFeatureArgs;
  }

  /**
   * @param result the result to set
   */
  public void setResult(IOperationResult result) {
    this.result = result;
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