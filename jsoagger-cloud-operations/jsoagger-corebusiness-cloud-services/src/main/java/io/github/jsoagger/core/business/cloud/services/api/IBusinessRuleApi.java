/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IBusinessRuleApi {

  IOperationResult applicableRules(JsonObject query);

  IOperationResult setRuleState(JsonObject query);

  IOperationResult deleteRule(JsonObject query);

  IOperationResult getRuleByIdentifier(JsonObject query);

  public IOperationResult getRule(JsonObject query);
}
