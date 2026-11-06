# CaptureKit Java SDK

The official Java SDK for the [CaptureKit](https://www.capturekit.dev) screenshot API.

Capture websites as PNG, JPEG, WebP or PDF, extract their content, or analyse them with AI.

The SDK exposes **7 typed methods**, one per documented endpoint, each with a parameter builder, plus a generic `request()`. It uses `java.net.http.HttpClient` and Jackson.

## Requirements

- Java 17 or newer

## Installation

The SDK is not on Maven Central. Build it from source, which installs it in your local Maven repository:

```sh
git clone https://github.com/capturekitdev/capturekit-java.git
cd capturekit-java
mvn -q install -DskipTests
```

Then add it to your project. Maven:

```xml
<dependency>
  <groupId>dev.capturekit</groupId>
  <artifactId>capturekit</artifactId>
  <version>0.1.0</version>
</dependency>
```

Gradle, with `mavenLocal()` in your repositories:

```kotlin
implementation("dev.capturekit:capturekit:0.1.0")
```

## Get your API key

[Sign up for CaptureKit](https://app.capturekit.dev/register) or sign in, then copy your API key from the [dashboard](https://app.capturekit.dev). `CaptureKitClient.fromEnv()` reads the `CAPTUREKIT_API_KEY` environment variable.

## Quick start

```java
import dev.capturekit.ApiResponse;
import dev.capturekit.CaptureKitClient;
import dev.capturekit.params.ContentParams;

CaptureKitClient client = CaptureKitClient.fromEnv();
ApiResponse response = client.content(ContentParams.builder().url("https://example.com").build());
System.out.println(response.json().toPrettyString());
```

Parameter builders mirror the [API reference](https://app.capturekit.dev/openapi); `build()` checks required parameters. Use `response.as(MyType.class)` to map the JSON onto your own types.

## Binary responses

Endpoints that return a file (image, PDF…) give you the bytes; `isJson()` tells both cases apart:

```java
ApiResponse response = client.capture(CaptureParams.builder().url("https://example.com").format("png").build());
if (!response.isJson()) {
  java.nio.file.Files.write(java.nio.file.Path.of("screenshot.png"), response.bytes());
}
```

## Async jobs

Any call accepts `is_async: true` (useful past the 60-second server limit): the response is then a `job_id`. `waitForJob` polls `GET /v1/jobs/{id}` until a terminal status (`CompletedJob`, `Error`, `NotFound`, `BadRequest`):

```java
ApiResponse submitted = client.content(ContentParams.builder().url("https://example.com").isAsync(true).build());
ApiResponse job = client.waitForJob(
    submitted.json().get("job_id").asText(),
    Duration.ofSeconds(3),
    Duration.ofHours(12),
    progress -> System.out.println(progress.path("status").asText() + " " + progress.path("progress").asInt()));
```

## GET or POST

Methods use GET when the endpoint supports it (query string). POST sends the same parameters as JSON:

```java
client.analyze(AnalyzeParams.builder().url("test").build(), RequestOptions.method("POST"));
```

## Any endpoint

```java
ApiResponse response = client.request("GET", "/v1/usage", Map.of());
```

## Errors

```java
try {
  client.content(ContentParams.builder().url("https://example.com").build());
} catch (CaptureKitApiException e) {
  System.err.println(e.status() + ": " + e.getMessage());
}
```

| Exception | When |
| --- | --- |
| `CaptureKitConfigurationException` | Missing API key, invalid options or unsupported method. |
| `CaptureKitSerializationException` | A required parameter is missing or a value can't be sent. |
| `CaptureKitTransportException` | Network failure, timeout or interruption. |
| `CaptureKitApiException` | Non-2xx status (`status()`, `errorMessage()`, `responseBody()`, `rawResponse()`). |
| `CaptureKitResponseDecodingException` | A success response could not be decoded. |

All of them extend the unchecked `CaptureKitException`.

## Retries

Requests are retried up to 3 times after a network error, a `408`, `500`, `502`, `503` or `504`, or a rate limit — which the API returns as `401` (or `429`) with a `Rate limit exceeded` message. Per-key quota errors (`…: quota daily`) are not retried, nor are `400`, `402`, `404` or client-side timeouts. The delay starts at 1 second and grows 1.5× per retry (capped at 30 seconds); a `Retry-After` header wins.

```java
CaptureKitClient client = CaptureKitClient.builder().maxRetries(5).retryDelay(Duration.ofMillis(500)).build();
client.content(ContentParams.builder().url("https://example.com").build(), RequestOptions.builder().maxRetries(0).build()); // one request without retries
```

## Batch requests

`batch()` runs a function per item on 10 threads (`concurrency`) and returns one `BatchResult` per item, in input order. A failing item never stops the others.

```java
List<String> inputs = List.of("https://example.com", "https://www.capturekit.dev", "https://github.com");
List<BatchResult<ApiResponse>> results = client.batch(inputs, 5, value ->
    client.content(ContentParams.builder().url(value).build()));
for (BatchResult<ApiResponse> result : results) {
  if (result.ok()) {
    System.out.println(result.value().json());
  } else {
    System.err.println(inputs.get(result.index()) + ": " + result.error().getMessage());
  }
}
```

| Constant | Default | Description |
| --- | --- | --- |
| `CaptureKitClient.DEFAULT_CONCURRENCY` | `10` | Tasks run at the same time by batch |
| `CaptureKitClient.DEFAULT_TIMEOUT` | `Duration.ofSeconds(120)` | Request timeout |
| `CaptureKitClient.MAX_RETRIES` | `3` | Retry attempts after a network error, a 408/5xx or a rate limit |
| `CaptureKitClient.RETRY_BACKOFF_FACTOR` | `1.5` | Exponential backoff multiplier |
| `CaptureKitClient.DEFAULT_RETRY_DELAY` | `Duration.ofSeconds(1)` | Delay before the first retry |

## Configuration

```java
CaptureKitClient client = CaptureKitClient.builder()
    .apiKey(System.getenv("CAPTUREKIT_API_KEY"))
    .timeout(Duration.ofSeconds(60)) // default 120 s, null disables
    .header("X-Trace", "abc")
    .transport(CaptureKitTransport.fromHttpClient(myHttpClient)) // optional
    .build();
```

## Endpoints

7 methods, generated from the [OpenAPI document](https://app.capturekit.dev/openapi).

| Method | HTTP | Description |
| --- | --- | --- |
| `analyze()` | GET / POST `/v1/analyze` | AI Content API analyzes any webpage and returns AI insights summary, value proposition, features, categories, use cases, and optional conta… |
| `capture()` | GET / POST `/v1/capture` | Browser-like Screenshot API that captures webpages as PNG/JPEG/WebP or PDF for previews, QA, reporting, monitoring, and archiving no headle… |
| `content()` | GET / POST `/v1/content` | CaptureKit Content API extracts structured webpage data, metadata, links, HTML/Markdown: in one request for SEO, monitoring, indexing and a… |
| `jobs()` | GET `/v1/jobs/{id}` | Poll the status, progress (0–100) and result of a job started with is_async=true. Polling is free. Poll until status is terminal: Completed… |
| `stats()` | GET `/v1/stats` | Infrastructure health for an engine or endpoint over the last hour. Not scoped to your account or tenant. Enabled per account: HTTP 403 mea… |
| `usage()` | GET `/v1/usage` | Retrieve real-time usage statistics and credit balance for your CaptureKit account. |
| `websiteCrawler()` | GET / POST `/v1/website/crawler` | Website Crawler API fetches raw HTML from any webpage in request mode (no rendering) for fast scraping, audits, monitoring, and link/metada… |

## All CaptureKit SDKs

| SDK | Package | Install |
| --- | --- | --- |
| Node.js | [`@capturekitdev/sdk`](https://www.npmjs.com/package/@capturekitdev/sdk) | `npm install @capturekitdev/sdk` |
| Python | [`capturekitdev`](https://pypi.org/project/capturekitdev/) | `pip install capturekitdev` |
| Go | [`capturekit-go`](https://pkg.go.dev/github.com/capturekitdev/capturekit-go) | `go get github.com/capturekitdev/capturekit-go` |
| .NET | [`CaptureKit`](https://www.nuget.org/packages/CaptureKit) | `dotnet add package CaptureKit` |
| Ruby | [`capturekitdev`](https://rubygems.org/gems/capturekitdev) | `gem install capturekitdev` |
| PHP | [`capturekitdev/sdk`](https://packagist.org/packages/capturekitdev/sdk) | `composer require capturekitdev/sdk` |
| Java | [`capturekit-java`](https://github.com/capturekitdev/capturekit-java) | build from source, see its README |
| CLI | [`@capturekitdev/cli`](https://www.npmjs.com/package/@capturekitdev/cli) | `npm install -g @capturekitdev/cli` |
| MCP server | [`@capturekitdev/mcp`](https://www.npmjs.com/package/@capturekitdev/mcp) | `npx -y @capturekitdev/mcp` |

## License

MIT — see [LICENSE](LICENSE).
