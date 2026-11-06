package dev.capturekit.examples;

import dev.capturekit.ApiResponse;
import dev.capturekit.CaptureKitClient;
import dev.capturekit.params.CaptureParams;
import java.nio.file.Files;
import java.nio.file.Path;

/** Run with CAPTUREKIT_API_KEY set. */
public final class BinaryExample {
  private BinaryExample() {}

  public static void main(String[] args) throws Exception {
    CaptureKitClient client = CaptureKitClient.fromEnv();
    ApiResponse response = client.capture(CaptureParams.builder().url("https://example.com").format("png").build());
    if (response.isJson()) {
      System.out.println(response.json().toPrettyString());
    } else {
      Files.write(Path.of("screenshot.png"), response.bytes());
      System.out.println("Saved screenshot.png (" + response.contentType() + ")");
    }
  }
}
