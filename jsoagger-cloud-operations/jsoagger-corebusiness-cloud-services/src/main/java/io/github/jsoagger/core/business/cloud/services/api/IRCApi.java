/**
 * 4 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 4 févr. 2018
 */
public interface IRCApi {

  IOperationResult masterByName(JsonObject query);


  IOperationResult checkin(JsonObject query);


  IOperationResult checkout(JsonObject query);


  IOperationResult undoCheckout(JsonObject query);


  IOperationResult revise(JsonObject query);


  IOperationResult allIterationsOf(JsonObject query);


  IOperationResult allIterationsOfVersion(JsonObject query);


  IOperationResult allVersionsOf(JsonObject query);


  IOperationResult getExactIterationVersionNumber(JsonObject query);


  IOperationResult getIterationWithMasterByOid(JsonObject query);


  IOperationResult getLatestIterationOfVersion(JsonObject query);


  IOperationResult getLatestVersion(JsonObject query);


  IOperationResult workingCopy(JsonObject query);

  IOperationResult deleteIteration(JsonObject query);

  IOperationResult masterByNumber(JsonObject query);
}
