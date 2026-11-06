package dev.capturekit;

/** The request failed before a complete HTTP response was received (network error, timeout). */
public final class CaptureKitTransportException extends CaptureKitException {
  private static final long serialVersionUID = 1L;

  /** Creates the exception. */
  public CaptureKitTransportException(String message, Throwable cause) {
    super(message, cause);
  }
}
