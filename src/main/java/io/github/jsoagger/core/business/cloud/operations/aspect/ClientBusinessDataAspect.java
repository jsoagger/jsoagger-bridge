/**
 * 26 janv. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.operations.aspect;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * Loads business data asynchroniuously. Only apply to {@link SingleResult}. Do
 * not support operation returning {@link MultipleResult}.
 *
 * Interceps all method call from package
 * 'io.github.jsoagger.core.business.cloud.operations.decorated' and load business data of those
 * calls.
 *
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 26 janv. 2018
 */
public class ClientBusinessDataAspect {

  /**
   * Constructor
   */
  public ClientBusinessDataAspect() {
  }

  /**
   * @return
   * @throws Throwable
   */
  public Object loadBusinessDatas() throws Throwable {
    Object returnValue = null;

    try {

      //returnValue = proceedingJoinPoint.proceed();
      final IOperation target = null;//(IOperation) proceedingJoinPoint.getTarget();

      if (target != null && target.getResult() != null && target.getResult() instanceof SingleResult) {
        final SingleResult res = (SingleResult) target.getResult();

        final List<CompletableFuture<IOperationResult>> features = new ArrayList<>();

        // features.addAll(LifecycleManagedFeatures.lifecycleManagedFeatures(applicationContext,
        // proceedingJoinPoint, res));
        // features.addAll(TypeManagedFeatures.typeManagedFeatures(applicationContext,
        // proceedingJoinPoint, res));
        features.addAll(
            ContentHolderFeatures.primaryContentFeatures(res));

        // Create a combined Future using allOf()
        // and call join in order to launch loading
        CompletableFuture.allOf(features.toArray(new CompletableFuture[features.size()])).join();
      }
    } catch (final Throwable e) {
      e.printStackTrace();
      throw e;
    }

    return returnValue;
  }

}
