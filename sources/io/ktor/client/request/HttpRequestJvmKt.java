package io.ktor.client.request;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.URLBuilder;
import io.ktor.http.URLUtilsJvmKt;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"invoke", "Lio/ktor/client/request/HttpRequestBuilder;", "Lio/ktor/client/request/HttpRequestBuilder$Companion;", "url", "Ljava/net/URL;", "Lio/ktor/http/URLBuilder;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpRequestJvm.kt */
public final class HttpRequestJvmKt {
    public static final URLBuilder url(HttpRequestBuilder httpRequestBuilder, URL url) {
        Intrinsics.checkNotNullParameter(httpRequestBuilder, "<this>");
        Intrinsics.checkNotNullParameter(url, RtspHeaders.Values.URL);
        return URLUtilsJvmKt.takeFrom(httpRequestBuilder.getUrl(), url);
    }

    public static final HttpRequestBuilder invoke(HttpRequestBuilder.Companion companion, URL url) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(url, RtspHeaders.Values.URL);
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        url(httpRequestBuilder, url);
        return httpRequestBuilder;
    }
}
