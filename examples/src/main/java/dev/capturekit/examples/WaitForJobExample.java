package dev.capturekit.examples;

import dev.capturekit.ApiResponse;
import dev.capturekit.CaptureKitClient;
import dev.capturekit.params.ContentParams;
import java.time.Duration;

/** Run with CAPTUREKIT_API_KEY set. */
public final class WaitForJobExample {
  private WaitForJobExample() {}

  public static void main(String[] args) throws Exception {
    CaptureKitClient client = CaptureKitClient.fromEnv();
    ApiResponse submitted = client.content(ContentParams.builder().url("https://example.com").isAsync(true).build());
    ApiResponse job = client.waitForJob(
        submitted.json().get("job_id").asText(),
        Duration.ofSeconds(3),
        Duration.ofHours(12),
        progress -> System.out.println(progress.path("status").asText() + " " + progress.path("progress").asInt()));
    System.out.println(job.json().toPrettyString());
  }
}
