/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.utils;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.result.OperationMessage;
import io.github.jsoagger.core.bridge.result.SingleResult;
import io.github.jsoagger.core.bridge.result.OperationMessage.OperationMessageBuilder;

/**
 * Default implementation of remote call. Check if the remote server is
 * reachable, if false, return non null CompletableFuture.
 *
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class CheckServerStatusAspect {

  private ResourceBundle	bundle			= ResourceBundle.getBundle("MessageBundle");
  private Locale			defaultLocale	= Locale.getDefault();

  // needs clientStatus
  protected ClientStatus clientStatus;


  /**
   * Pointcut around {@link IOperation#doOperation(java.util.Map)}
   *
   * @param pjp
   * @return
   * @throws Throwable
   */
  //@Around("execution (* public io.github.jsoagger.core.bridge.*.doOperation(..)")
  //public Object checkServerStatusBeforeCall(ProceedingJoinPoint pjp) throws Throwable {
  public Object checkServerStatusBeforeCall() throws Throwable {
    if (clientStatus.isDown()) {
      return CompletableFuture.supplyAsync(() -> {
        OperationMessage message = new OperationMessageBuilder()
            .title(bundle.getString("SERVER_DOWN_MESSAGE_TITLE"))
            .detail(bundle.getString("SERVER_DOWN_MESSAGE_BODY"))
            .build();

        return new SingleResult.Builder().addMessage(message).build();
      });
    }

    // return pjp.proceed();
    return null;
  }
}
