package dev.capturekit;

import java.util.Map;

/** Parameters of one endpoint, in wire order. Implemented by every class in {@code dev.capturekit.params}. */
public interface CaptureKitParams {
  /** Wire name to value; null values are omitted from requests. */
  Map<String, Object> toMap();
}
