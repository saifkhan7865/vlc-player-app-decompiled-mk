package io.ktor.http.cio;

import io.ktor.http.cio.internals.CharArrayBuilder;
import java.io.Closeable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0017\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0006\u0010\f\u001a\u00020\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Lio/ktor/http/cio/HttpMessage;", "Ljava/io/Closeable;", "Lio/ktor/utils/io/core/Closeable;", "headers", "Lio/ktor/http/cio/HttpHeadersMap;", "builder", "Lio/ktor/http/cio/internals/CharArrayBuilder;", "(Lio/ktor/http/cio/HttpHeadersMap;Lio/ktor/http/cio/internals/CharArrayBuilder;)V", "getHeaders", "()Lio/ktor/http/cio/HttpHeadersMap;", "close", "", "release", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RequestResponse.kt */
public abstract class HttpMessage implements Closeable {
    private final CharArrayBuilder builder;
    private final HttpHeadersMap headers;

    public HttpMessage(HttpHeadersMap httpHeadersMap, CharArrayBuilder charArrayBuilder) {
        Intrinsics.checkNotNullParameter(httpHeadersMap, "headers");
        Intrinsics.checkNotNullParameter(charArrayBuilder, "builder");
        this.headers = httpHeadersMap;
        this.builder = charArrayBuilder;
    }

    public final HttpHeadersMap getHeaders() {
        return this.headers;
    }

    public final void release() {
        this.builder.release();
        this.headers.release();
    }

    public void close() {
        release();
    }
}
