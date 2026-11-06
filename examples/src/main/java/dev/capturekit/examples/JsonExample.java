package dev.capturekit.examples;

import dev.capturekit.ApiResponse;
import dev.capturekit.CaptureKitClient;
import dev.capturekit.params.ContentParams;

/** Run with CAPTUREKIT_API_KEY set. */
public final class JsonExample {
  private JsonExample() {}

  public static void main(String[] args) throws Exception {
    CaptureKitClient client = CaptureKitClient.fromEnv();
    ApiResponse response = client.content(ContentParams.builder().url("https://example.com").build());
    System.out.println(response.json().toPrettyString());
  }
}
