/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;


import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IBusinessRuleApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class BusinessRuleApi extends AbstractClientApi implements IBusinessRuleApi {

  private static final String	setRuleActiveState_URI		= "/api/businessRule/%s/activate?containerId=%s";
  private static final String	setRuleInactiveState_URI	= "/api/businessRule/%s/desactivate?containerId=%s";
  private static final String	deleteByIdentifier_URI		= "/api/businessRule/%s/delete?containerId=%s";
  private static final String   GetRule_URI      = "/api/businessRule/%s/?containerId=%s";
  private static final String	getByIdentifier_URI			= "/api/businessRule/getByIdentifier?identifier=%s&containerId=%s";
  private static final String  applicable_rules_URI         = "/api/businessRule/applicableRules/?eventKey=%s&businessClass=%s&containerId=%s";




  /*
   * (non-Javadoc)
   *
   * @see
   * io.github.jsoagger.core.business.cloud.services.api.IBusinessRuleApi#setRuleState(net.sf.
   * json.JsonObject)
   */
  @Override
  public IOperationResult setRuleState(JsonObject query) {
    try {
      boolean state = query.get("state").getAsBoolean();
      String identifier = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = null;
      if (state) {
        url = getRootUrl().concat(String.format(setRuleActiveState_URI, identifier,containerId));
      }
      else {
        url = getRootUrl().concat(String.format(setRuleInactiveState_URI, identifier,containerId));
      }

      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult applicableRules(JsonObject query) {
    String eventKey = query.get("eventKey").getAsString();
    String businessClass = query.get("businessClass").getAsString();
    String containerId = query.get("containerId").getAsString();

    try {
      String url = getRootUrl().concat(String.format(applicable_rules_URI, eventKey,businessClass, containerId));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.emptyMultipleResult();
    }
  }

  @Override
  public IOperationResult deleteRule(JsonObject query) {
    String identifier = getFullId(query);
    String containerId = query.get("containerId").getAsString();

    try {
      String url = getRootUrl().concat(String.format(deleteByIdentifier_URI, identifier, containerId));
      IOperationResult result = doDelete(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult getRuleByIdentifier(JsonObject query) {
    String identifier = query.get("identifier").getAsString();
    String containerId = query.get("containerId").getAsString();

    try {
      String url = getRootUrl().concat(String.format(getByIdentifier_URI, identifier, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult getRule(JsonObject query) {
    String id = getFullId(query);
    String containerId = query.get("containerId").getAsString();

    try {
      String url = getRootUrl().concat(String.format(GetRule_URI, id, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
