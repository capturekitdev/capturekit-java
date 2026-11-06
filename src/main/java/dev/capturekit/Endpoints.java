package dev.capturekit;

import java.util.List;
import java.util.Map;

/**
 * Path, methods, response kind and parameter names of every documented operation.
 *
 * <p>Generated from the CaptureKit OpenAPI document. Do not edit by hand.
 */
public final class Endpoints {
  private Endpoints() {}

  /** One documented operation. */
  public record Endpoint(String path, List<String> methods, boolean json, List<String> required) {}

  /** Every documented operation keyed by its snake_case name. */
  public static final Map<String, Endpoint> ALL = Map.ofEntries(
      Map.entry("analyze", new Endpoint("/v1/analyze", List.of("GET", "POST"), true, List.of("url"))),
      Map.entry("capture", new Endpoint("/v1/capture", List.of("GET", "POST"), false, List.of("url"))),
      Map.entry("content", new Endpoint("/v1/content", List.of("GET", "POST"), true, List.of("url"))),
      Map.entry("jobs", new Endpoint("/v1/jobs/{id}", List.of("GET"), true, List.of("id"))),
      Map.entry("stats", new Endpoint("/v1/stats", List.of("GET"), true, List.of())),
      Map.entry("usage", new Endpoint("/v1/usage", List.of("GET"), true, List.of())),
      Map.entry("website_crawler", new Endpoint("/v1/website/crawler", List.of("GET", "POST"), false, List.of("query")))
  );
}
