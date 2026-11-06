package dev.capturekit.params;

import dev.capturekit.CaptureKitParams;
import dev.capturekit.CaptureKitSerializationException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Parameters for {@code GET / POST /v1/analyze}.
 *
 * <p>Generated from the CaptureKit OpenAPI document. Do not edit by hand.
 */
public final class AnalyzeParams implements CaptureKitParams {
  private final Map<String, Object> values;

  private AnalyzeParams(Map<String, Object> values) {
    this.values = values;
  }

  /** Returns a new builder. */
  public static Builder builder() {
    return new Builder();
  }

  @Override
  public Map<String, Object> toMap() {
    return values;
  }

  /** Builder for {@link AnalyzeParams}. */
  public static final class Builder {
    private final Map<String, Object> values = new LinkedHashMap<>();

    private Builder() {}

    /** Required. The URL of the webpage to capture. */
    public Builder url(String value) {
      values.put("url", value);
      return this;
    }

    /** Pass custom response fields to be included in the AI response. */
    public Builder customResponse(String value) {
      values.put("custom_response", value);
      return this;
    }

    /** Add your own instructions to guide the AI’s analysis. */
    public Builder customPrompt(String value) {
      values.put("custom_prompt", value);
      return this;
    }

    /** Specify a proxy server to route your request through. */
    public Builder proxy(String value) {
      values.put("proxy", value);
      return this;
    }

    /** Cache the response. Default: false. */
    public Builder cache(Boolean value) {
      values.put("cache", value);
      return this;
    }

    /** Cache the response for a custom TTL (in seconds). Default: 2592000. */
    public Builder cacheTtl(Number value) {
      values.put("cache_ttl", value);
      return this;
    }

    /** Comma-separated list of fields to keep in the response, with dot notation for nested fields (e.g. name,location.city). Does not change the credit cost. */
    public Builder fields(String value) {
      values.put("fields", value);
      return this;
    }

    /** Process the request asynchronously: the response is a job_id to poll on /v1/jobs/{id} (see waitForJob). Default: false. */
    public Builder isAsync(Boolean value) {
      values.put("is_async", value);
      return this;
    }

    /** Return HTTP 200 even when an error occurs; the error is then in the response body. Default: false. */
    public Builder softFail(Boolean value) {
      values.put("soft_fail", value);
      return this;
    }

    /** Validates required parameters and returns the immutable {@link AnalyzeParams}. */
    public AnalyzeParams build() {
      if (values.get("url") == null) {
        throw new CaptureKitSerializationException("The \"url\" parameter is required.", "url");
      }
      return new AnalyzeParams(java.util.Collections.unmodifiableMap(new LinkedHashMap<>(values)));
    }
  }
}
