/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IPreferenceAPI;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class PreferenceApi extends AbstractClientApi implements IPreferenceAPI{

  private static final String GET_PREF_VALUE_API = "/v1/secured/api/account/%s/preference/?key=%s";
  private static final String SET_PREF_VALUE_API = "/v1/secured/api/account/%s/preference/?key=%s&value=%";
  private static final String GET_USER_PREFS_API = "/v1/secured/api/account/%s/preference/all";


  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult getAllUserPreferences(JsonObject query) {
    try {
      Object userLogin = query.get("userLogin").getAsString();
      String url = getRootUrl().concat(String.format(GET_USER_PREFS_API, userLogin));
      IOperationResult result = doGet(query, url, MultipleResult.class);
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
  public IOperationResult getPreference(JsonObject query){
    try {
      Object key = query.get("key").getAsString();
      Object userLogin = query.get("userLogin").getAsString();
      String url = getRootUrl().concat(String.format(GET_PREF_VALUE_API, userLogin, key));
      IOperationResult result = doGet(query, url, SingleResult.class);
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
  public IOperationResult setPreference(JsonObject query){
    try {
      String key = query.get("key").getAsString();
      String value = query.get("value").getAsString();
      Object userLogin = query.get("userLogin").getAsString();
      String url = getRootUrl().concat(String.format(SET_PREF_VALUE_API, userLogin, key, value));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
