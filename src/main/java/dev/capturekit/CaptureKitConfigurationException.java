package dev.capturekit;

/** The client configuration is missing or unusable. */
public final class CaptureKitConfigurationException extends CaptureKitException {
  private static final long serialVersionUID = 1L;

  /** Creates the exception. */
  public CaptureKitConfigurationException(String message) {
    super(message, null);
  }

  /** Creates the exception with a cause. */
  public CaptureKitConfigurationException(String message, Throwable cause) {
    super(message, cause);
  }
}
