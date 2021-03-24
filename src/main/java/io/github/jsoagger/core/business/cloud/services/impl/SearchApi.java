/**
 * 27 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.ISearchApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 27 févr. 2018
 */
public class SearchApi extends AbstractClientApi implements ISearchApi {

  private static final String	SEARCH_ENTITIES				= "/transdev/base/v1/secured/api/search/basic/entity/?containerId=%s";
  private static final String	GET_SAVED_SEARCHS_URL		= "/transdev/base/v1/secured/api/search/basic/savedSearchs?audience=%s&containerId=%s";
  private static final String	DELETE_SAVED_SEARCHS_URL	= "/transdev/base/v1/secured/api/search/basic/savedSearchs/%s/?containerId=%s";

  private static final String	SEARCH_RC_ENTITIES_ITERATIONS	= "/transdev/base/v1/secured/api/search/basic/rcEntitySearchIterations/?containerId=%s";
  private static final String	SEARCH_RC_ENTITIES_MASTERS		= "/transdev/base/v1/secured/api/search/basic/rcEntitySearchMasters/?containerId=%s";


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult rcSearch(JsonObject query) {
    try {
      JsonElement qms = query.get("data.attributes.search_query.extra.queryMaster");
      boolean mst = qms == null ? false : Boolean.valueOf(String.valueOf(qms.getAsBoolean()));

      String sUrl = null;
      if (mst) {
        sUrl = getRootUrl().concat(String.format(SEARCH_RC_ENTITIES_MASTERS));
      }
      else {
        sUrl = getRootUrl().concat(String.format(SEARCH_RC_ENTITIES_ITERATIONS));
      }

      IOperationResult result = doPost(query, sUrl, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult entitySearch(JsonObject query) {
    try {
      // http get do not support requestbody
      // thats why post
      String sUrl = getRootUrl().concat(String.format(SEARCH_ENTITIES));
      IOperationResult result = doPost(query, sUrl, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult createSavedSearch(JsonObject query) {
    return null;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult deleteSavedSearch(JsonObject query) {
    try {

      String fullId = query.get("id").getAsString();

      // http get do not support requestbody
      // thats why post
      String sUrl = getRootUrl().concat(String.format(DELETE_SAVED_SEARCHS_URL, fullId));
      IOperationResult result = doDelete(query, sUrl, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult getAllSavedSearch(JsonObject query) {
    try {
      String sUrl = getRootUrl().concat(String.format(GET_SAVED_SEARCHS_URL, "0,1,2"));
      IOperationResult result = doGet(query, sUrl, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult getPublicSavedSearch(JsonObject query) {
    try {
      String sUrl = getRootUrl().concat(String.format(GET_SAVED_SEARCHS_URL, "1,2"));
      IOperationResult result = doGet(query, sUrl, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult getUserSavedSearch(JsonObject query) {
    try {
      String sUrl = getRootUrl().concat(String.format(GET_SAVED_SEARCHS_URL, "0"));
      IOperationResult result = doGet(query, sUrl, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }

  }
}
