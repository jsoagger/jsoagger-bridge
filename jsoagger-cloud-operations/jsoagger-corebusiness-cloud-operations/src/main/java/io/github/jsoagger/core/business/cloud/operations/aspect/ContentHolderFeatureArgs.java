/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.aspect;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class ContentHolderFeatureArgs {

  private boolean   primaryFormat = true;
  private boolean   attachmentsFormat = true;

  /**
   * Constructor
   */
  public ContentHolderFeatureArgs() {
  }

  /**
   * @return the primaryFormat
   */
  public boolean isPrimaryFormat() {
    return primaryFormat;
  }

  /**
   * @param primaryFormat the primaryFormat to set
   */
  public void setPrimaryFormat(boolean primaryFormat) {
    this.primaryFormat = primaryFormat;
  }

  /**
   * @return the attachmentsFormat
   */
  public boolean isAttachmentsFormat() {
    return attachmentsFormat;
  }

  /**
   * @param attachmentsFormat the attachmentsFormat to set
   */
  public void setAttachmentsFormat(boolean attachmentsFormat) {
    this.attachmentsFormat = attachmentsFormat;
  }
}
