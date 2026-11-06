package dev.capturekit.params;

import dev.capturekit.CaptureKitParams;
import dev.capturekit.CaptureKitSerializationException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Parameters for {@code GET / POST /v1/content}.
 *
 * <p>Generated from the CaptureKit OpenAPI document. Do not edit by hand.
 */
public final class ContentParams implements CaptureKitParams {
  private final Map<String, Object> values;

  private ContentParams(Map<String, Object> values) {
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

  /** Builder for {@link ContentParams}. */
  public static final class Builder {
    private final Map<String, Object> values = new LinkedHashMap<>();

    private Builder() {}

    /** Required. The URL of the webpage to capture. */
    public Builder url(String value) {
      values.put("url", value);
      return this;
    }

    /** Include the raw HTML of the webpage in the response. Default: false. */
    public Builder includeHtml(Boolean value) {
      values.put("include_html", value);
      return this;
    }

    /** Include the Markdown of the webpage in the response. Default: false. */
    public Builder includeMarkdown(Boolean value) {
      values.put("include_markdown", value);
      return this;
    }

    /** Include the sitemap of the website in the response. Default: false. */
    public Builder includeSitemap(Boolean value) {
      values.put("include_sitemap", value);
      return this;
    }

    /** Use Defuddle to clean and extract the main content from web pages. This popular library removes unnecessary elements like comments, sidebars, headers, footers, and other non-essential elements, leaving only the primary content. When enabled, the HTML response will be processed through Defuddle befo… Default: false. */
    public Builder useDefuddle(Boolean value) {
      values.put("use_defuddle", value);
      return this;
    }

    /** A comma-separated list of elements to hide before capturing (e.g., ads, popups). */
    public Builder removeSelectors(String value) {
      values.put("remove_selectors", value);
      return this;
    }

    /** Automatically remove cookie banners before capturing. Default: false. */
    public Builder removeCookieBanners(Boolean value) {
      values.put("remove_cookie_banners", value);
      return this;
    }

    /** Automatically remove ads before capturing. Default: false. */
    public Builder removeAds(Boolean value) {
      values.put("remove_ads", value);
      return this;
    }

    /** Comma-separated list of resource types to block (e.g., “image,stylesheet,font”). Allowed values: document, eventsource, fetch, font, image, manifest, media, other, script, stylesheet, texttrack, websocket, xhr. */
    public Builder blockResources(String value) {
      values.put("block_resources", value);
      return this;
    }

    /** Comma-separated list of URL patterns to block (e.g., “analytics,tracking,advertisement”). */
    public Builder blockUrls(String value) {
      values.put("block_urls", value);
      return this;
    }

    /** Delay in seconds before capturing the screenshot. Default: 0. */
    public Builder delay(Number value) {
      values.put("delay", value);
      return this;
    }

    /** Define when to capture. Allowed values: domcontentloaded, load, networkidle0, networkidle2. */
    public Builder waitUntil(List<String> value) {
      values.put("wait_until", value);
      return this;
    }

    /** Wait for a specific element to appear before taking the screenshot. */
    public Builder waitForSelector(String value) {
      values.put("wait_for_selector", value);
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

    /** Validates required parameters and returns the immutable {@link ContentParams}. */
    public ContentParams build() {
      if (values.get("url") == null) {
        throw new CaptureKitSerializationException("The \"url\" parameter is required.", "url");
      }
      return new ContentParams(java.util.Collections.unmodifiableMap(new LinkedHashMap<>(values)));
    }
  }
}
