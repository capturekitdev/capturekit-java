package dev.capturekit.examples;

import dev.capturekit.ApiResponse;
import dev.capturekit.CaptureKitApiException;
import dev.capturekit.CaptureKitClient;
import java.util.Map;

/** Run with CAPTUREKIT_API_KEY set. */
public final class RequestExample {
  private RequestExample() {}

  public static void main(String[] args) throws Exception {
    CaptureKitClient client = CaptureKitClient.fromEnv();
    try {
      ApiResponse response = client.request("GET", "/v1/usage", Map.of());
      System.out.println(response.text());
    } catch (CaptureKitApiException e) {
      System.err.println("HTTP " + e.status() + ": " + e.getMessage());
    }
  }
}
