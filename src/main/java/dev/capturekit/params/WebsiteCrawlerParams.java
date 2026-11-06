package dev.capturekit.params;

import dev.capturekit.CaptureKitParams;
import dev.capturekit.CaptureKitSerializationException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Parameters for {@code GET / POST /v1/website/crawler}.
 *
 * <p>Generated from the CaptureKit OpenAPI document. Do not edit by hand.
 */
public final class WebsiteCrawlerParams implements CaptureKitParams {
  private final Map<String, Object> values;

  private WebsiteCrawlerParams(Map<String, Object> values) {
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

  /** Builder for {@link WebsiteCrawlerParams}. */
  public static final class Builder {
    private final Map<String, Object> values = new LinkedHashMap<>();

    private Builder() {}

    /** Required. A website URL with either the http or https protocol. */
    public Builder query(String value) {
      values.put("query", value);
      return this;
    }

    /** If set to false, do not follow redirects. Default: true. */
    public Builder allowRedirects(Boolean value) {
      values.put("allow_redirects", value);
      return this;
    }

    /** If set to true, the response will be the HTML source of the page. Default: false. */
    public Builder returnPageSource(Boolean value) {
      values.put("return_page_source", value);
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

    /** Validates required parameters and returns the immutable {@link WebsiteCrawlerParams}. */
    public WebsiteCrawlerParams build() {
      if (values.get("query") == null) {
        throw new CaptureKitSerializationException("The \"query\" parameter is required.", "query");
      }
      return new WebsiteCrawlerParams(java.util.Collections.unmodifiableMap(new LinkedHashMap<>(values)));
    }
  }
}
