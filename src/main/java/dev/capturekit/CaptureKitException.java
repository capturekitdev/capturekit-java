package dev.capturekit;

/** Base class for every SDK-defined exception. */
public class CaptureKitException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  /** Creates the exception. */
  public CaptureKitException(String message, Throwable cause) {
    super(message, cause);
  }
}
