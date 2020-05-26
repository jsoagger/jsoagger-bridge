/**
 * 3 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.ILifecycleApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 3 févr. 2018
 */
public class LifecycleApi extends AbstractClientApi implements ILifecycleApi {

  public static String GET_LFC_BY_OID_URL	= "/v1/secured/api/rc/iteration/%s/?containerId=%s";
  public static String GET_LFC_STATES_URL	= "/v1/secured/api/lifecycle/%s/states/?containerId=%s";

  public static String STATES_BY_PROMOTE_URL   = "/v1/secured/api/lifecycle/%s/stateByPromote/?fromState=%s&containerId=%s";
  public static String STATES_BY_DENOTE_URL   = "/v1/secured/api/lifecycle/%s/stateByDenote/?fromState=%s&containerId=%s";
  public static String STATES_BY_SET_STATE_URL   = "/v1/secured/api/lifecycle/%s/statesBySetState/?fromState=%s&containerId=%s";



  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult statesBySetState(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();
      String fromState = query.get("fromState").getAsString();

      String byPathUrl = getRootUrl().concat(String.format(STATES_BY_SET_STATE_URL, id, fromState, containerId));
      IOperationResult result = doGet(query, byPathUrl, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult stateByDenote(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();
      String fromState = query.get("fromState").getAsString();

      String byPathUrl = getRootUrl().concat(String.format(STATES_BY_DENOTE_URL, id, fromState, containerId));
      IOperationResult result = doGet(query, byPathUrl, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult stateByPromote(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();
      String fromState = query.get("fromState").getAsString();

      String byPathUrl = getRootUrl().concat(String.format(STATES_BY_PROMOTE_URL, id, fromState, containerId));
      IOperationResult result = doGet(query, byPathUrl, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getLifecycleByOid(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String byPathUrl = getRootUrl().concat(String.format(GET_LFC_BY_OID_URL, id, containerId));
      IOperationResult result = doGet(query, byPathUrl, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getLifecycleStates(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String byPathUrl = getRootUrl().concat(String.format(GET_LFC_STATES_URL, id, containerId));
      IOperationResult result = doGet(query, byPathUrl, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
