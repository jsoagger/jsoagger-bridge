/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IContactableApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.operation.JsonUtils;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana VONJISOA
 */
@SuppressWarnings("exports")
public class ContactableApi extends AbstractClientApi implements IContactableApi {


  private static String GET_CONTACTS_URL =  "/api/contactable/%s/contacts/?role=%s&masterForRole=%s&fromDate=%s&thruDate=%s&containerId=%s";
  private static String DELETE_CONTACT_URL =  "/api/contactable/%s/contacts/%s/?containerId=%s";

  private static String SET_MASTER_FOR_ROLE =  "/api/contactable/%s/contacts/%s/setMasterForRole/?containerId=%s";
  private static String SET_EFFECTIVITY =  "/api/contactable/%s/contacts/%s/setEffectivity/?containerId=%s";
  private static String SET_PostalAddress_URL =  "/api/contactable/%s/contacts/%s/postal/?containerId=%s";
  private static String SET_WEB_URL =  "/api/contactable/%s/contacts/%s/web?add=%s&containerId=%s";
  private static String SET_TELECOM_URL =  "/api/contactable/%s/contacts/%s/telecom?add=%s&containerId=%s";
  private static String SET_ROLE_URL =  "/api/contactable/%s/contacts/%s/setRole?role=%s&containerId=%s";


  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult setContactRole(JsonObject query) {
    try {
      String id = query.get("id").getAsString();
      String contactMechanismId = query.get("contactMechanismId").getAsString();
      String role = query.get("role").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(SET_ROLE_URL, id, contactMechanismId, role, containerId));
      IOperationResult result = doPut(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult getContacts(JsonObject query) {
    try {
      String id = query.get("id").getAsString();
      String role = JsonUtils.getJsonString(query, "role", "");
      String masterForRole = JsonUtils.getJsonString(query, "masterForRole", "true");
      String fromDate = JsonUtils.getJsonString(query, "fromDate", "");
      String thruDate = JsonUtils.getJsonString(query, "thruDate", "");
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl()
          .concat(String.format(GET_CONTACTS_URL, id, role, masterForRole, fromDate, thruDate, containerId));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult setContactEffectivity(JsonObject query) {
    try {
      String id = query.get("id").getAsString();
      String contactMechanismId = query.get("contactMechanismId").getAsString();
      String fromDate =  query.get("fromDate").getAsString();
      String thruDate =  query.get("thruDate").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(SET_EFFECTIVITY, id, contactMechanismId, containerId));
      IOperationResult result = doPut(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult setMasterContactForRole(JsonObject query) {
    try {
      String id = query.get("id").getAsString();
      String contactMechanismId = query.get("contactMechanismId").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(SET_MASTER_FOR_ROLE, id, contactMechanismId, containerId));
      IOperationResult result = doPut(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult setPostalAddress(JsonObject query) {
    try {
      String id = query.get("id").getAsString();
      String contactMechanismId = query.get("contactMechanismId").getAsString();
      String containerId = query.get("containerId").getAsString();
      String payload = query.get("payload").getAsString();

      Gson gson = new Gson();
      JsonObject po = gson.fromJson(payload, JsonObject.class);

      String url = getRootUrl().concat(String.format(SET_PostalAddress_URL, id, contactMechanismId, containerId));
      IOperationResult result = doPut(po, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult setTelecomContacts(JsonObject query) {
    try {
      String id = query.get("id").getAsString();
      String contactMechanismId = query.get("contactMechanismId").getAsString();
      String containerId = query.get("containerId").getAsString();

      // replace is server default mode
      String add = JsonUtils.getJsonString(query, "add", "false");

      String payload = query.get("payload").getAsString();
      Gson gson = new Gson();
      JsonObject po = gson.fromJson(payload, JsonObject.class);

      String url = getRootUrl().concat(String.format(SET_TELECOM_URL, id, contactMechanismId, add, containerId));
      IOperationResult result = doPut(po, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult setWebContacts(JsonObject query) {
    try {
      String id = query.get("id").getAsString();
      String contactMechanismId = query.get("contactMechanismId").getAsString();
      String containerId = query.get("containerId").getAsString();

      // replace is server default mode
      String add = JsonUtils.getJsonString(query, "add", "false");

      String payload = query.get("payload").getAsString();
      Gson gson = new Gson();
      JsonObject po = gson.fromJson(payload, JsonObject.class);

      String url = getRootUrl().concat(String.format(SET_WEB_URL, id, contactMechanismId, add, containerId));
      IOperationResult result = doPut(po, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult deleteContact(JsonObject query) {
    try {
      String id = query.get("id").getAsString();
      String contactMechanismId = query.get("contactMechanismId").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(DELETE_CONTACT_URL, id, contactMechanismId, containerId));
      IOperationResult result = doDelete(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
