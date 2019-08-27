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
public interface IContactableApi {

  IOperationResult deleteContact(JsonObject query);

  IOperationResult getContacts(JsonObject query);

  IOperationResult setContactEffectivity(JsonObject query);

  IOperationResult setMasterContactForRole(JsonObject query);

  IOperationResult setPostalAddress(JsonObject query);

  IOperationResult setTelecomContacts(JsonObject query);

  IOperationResult setWebContacts(JsonObject query);

  IOperationResult setContactRole(JsonObject params);
}
