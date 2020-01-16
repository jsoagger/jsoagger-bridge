/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public interface ICategoryApi {

  public IOperationResult deleteCategoryByFullId(JsonObject query) ;
  public IOperationResult getCategoryByFullId(JsonObject query) ;
  public IOperationResult getCategoryByName(JsonObject query) ;
  public IOperationResult getChildrenCategoriesByFullId(JsonObject query) ;
  public IOperationResult getParentCategoryByFullId(JsonObject query) ;
  public IOperationResult getRootCategories(JsonObject query) ;
  public IOperationResult getCategoryByNumber(JsonObject params);

}
