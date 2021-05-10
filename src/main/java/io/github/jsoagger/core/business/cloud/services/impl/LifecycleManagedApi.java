/**
 * 4 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.ILifecycleManagedApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 4 févr. 2018
 */
public class LifecycleManagedApi extends AbstractClientApi implements ILifecycleManagedApi {

  private static final String   REASSIGN_URL             = "/jsoagger/base/v1/secured/api/lifecycleManaged/%s/reassign/?containerId=%s&lifecycleId=%s";
  private static final String	DENOTE_URL			   = "/jsoagger/base/v1/secured/api/lifecycleManaged/%s/denote/?containerId=%s";
  private static final String	PROMOTE_URL			   = "/jsoagger/base/v1/secured/api/lifecycleManaged/%s/promote/?containerId=%s";
  private static final String	SET_STATE_URL		   = "/jsoagger/base/v1/secured/api/lifecycleManaged/%s/setState/?state=%s&containerId=%s";
  private static final String	LC_HISTORY_URL		   = "/jsoagger/base/v1/secured/api/lifecycleManaged/%s/lifecycleHistory/?containerId=%s";
  private static final String	STATE_OF_URL		   = "/jsoagger/base/v1/secured/api/lifecycleManaged/%s/state/?containerId=%s";
  private static final String	LIFECYCLE_NAME_URL		   = "/jsoagger/base/v1/secured/api/lifecycleManaged/%s/lifecycleName/?containerId=%s";
  private static final String	ALL_STATES_OF_URL		   = "/jsoagger/base/v1/secured/api/lifecycleManaged/%s/allStates/?containerId=%s";

  private static final String	STATE_BY_DENOTE_URL	   = "/jsoagger/base/v1/secured/api/lifecycleManaged/%s/stateByDenote/?containerId=%s";
  private static final String	STATE_BY_PROMOTE_URL   = "/jsoagger/base/v1/secured/api/lifecycleManaged/%s/stateByPromote/?containerId=%s";
  private static final String	STATES_BY_SETSTATE_URL = "/jsoagger/base/v1/secured/api/lifecycleManaged/%s/statesBySetState/?containerId=%s";


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult reassignLifecycle(JsonObject query) {
    try {
      String oid = getFullId(query);
      final String containerId = query.get("containerId").getAsString();
      final String lifecycleId = query.get("lifecycleId").getAsString();
      String byPathUrl = getRootUrl().concat(String.format(REASSIGN_URL, oid, containerId, lifecycleId));
      IOperationResult result = doPost(query, byPathUrl, SingleResult.class);
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
  public IOperationResult denote(JsonObject query) {
    try {
      String oid = getFullId(query);
      final String containerId = query.get("containerId").getAsString();
      String byPathUrl = getRootUrl().concat(String.format(DENOTE_URL, oid, containerId));
      IOperationResult result = doPost(query, byPathUrl, SingleResult.class);
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
  public IOperationResult promote(JsonObject query) {
    try {
      String oid = getFullId(query);
      final String containerId = query.get("containerId").getAsString();
      String byPathUrl = getRootUrl().concat(String.format(PROMOTE_URL, oid, containerId));
      IOperationResult result = doPost(query, byPathUrl, SingleResult.class);
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
  public IOperationResult setState(JsonObject query) {
    try {
      String oid = getFullId(query);
      String state = query.get("state").getAsString();
      final String containerId = query.get("containerId").getAsString();
      String byPathUrl = getRootUrl().concat(String.format(SET_STATE_URL, oid, state, containerId));
      IOperationResult result = doPost(query, byPathUrl, SingleResult.class);
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
  public IOperationResult getLifecycleHistory(JsonObject query) {
    try {
      String oid = getFullId(query);
      final String containerId = query.get("containerId").getAsString();
      String byPathUrl = getRootUrl().concat(String.format(LC_HISTORY_URL, oid, containerId));
      IOperationResult result = doGet(query, byPathUrl, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getLifecycleName(JsonObject query) {
    try {
      String oid = getFullId(query);
      final String containerId = query.get("containerId").getAsString();
      String byPathUrl = getRootUrl().concat(String.format(LIFECYCLE_NAME_URL, oid, containerId));
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
  public IOperationResult getLifecycleState(JsonObject query) {
    try {
      String oid = getFullId(query);
      final String containerId = query.get("containerId").getAsString();
      String byPathUrl = getRootUrl().concat(String.format(STATE_OF_URL, oid, containerId));
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
  public IOperationResult getAllLifecycleStates(JsonObject query) {
    try {
      String oid = getFullId(query);
      final String containerId = query.get("containerId").getAsString();
      String byPathUrl = getRootUrl().concat(String.format(ALL_STATES_OF_URL, oid, containerId));
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
  public IOperationResult getPossibleStatesByDenote(JsonObject query) {
    try {
      String oid = getFullId(query);
      final String containerId = query.get("containerId").getAsString();
      String byPathUrl = getRootUrl().concat(String.format(STATE_BY_DENOTE_URL, oid, containerId));
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
  public IOperationResult getPossibleStatesByPromote(JsonObject query) {
    try {
      String oid = getFullId(query);
      final String containerId = query.get("containerId").getAsString();
      String byPathUrl = getRootUrl().concat(String.format(STATE_BY_PROMOTE_URL, oid, containerId));
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
  public IOperationResult getPossibleStatesBySetState(JsonObject query) {
    try {
      String oid = getFullId(query);
      final String containerId = query.get("containerId").getAsString();
      String byPathUrl = getRootUrl().concat(String.format(STATES_BY_SETSTATE_URL, oid, containerId));
      IOperationResult result = doGet(query, byPathUrl, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }
}
