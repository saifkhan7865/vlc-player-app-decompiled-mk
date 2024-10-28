package io.ktor.server.request;

import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentType;
import io.ktor.http.ContentTypesKt;
import io.ktor.http.HeaderValue;
import io.ktor.http.HttpHeaderValueParserKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMethod;
import io.ktor.http.RangesKt;
import io.ktor.http.RangesSpecifier;
import io.ktor.server.plugins.OriginConnectionPointKt;
import io.netty.handler.codec.http.HttpHeaders;
import java.nio.charset.Charset;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u000b\u001a\u0004\u0018\u00010\u0006*\u00020\u0002\u001a\f\u0010\f\u001a\u0004\u0018\u00010\u0006*\u00020\u0002\u001a\u0010\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\u00020\u0002\u001a\f\u0010\u0010\u001a\u0004\u0018\u00010\u0006*\u00020\u0002\u001a\u0010\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\u00020\u0002\u001a\u0010\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\u00020\u0002\u001a\f\u0010\u0013\u001a\u0004\u0018\u00010\u0006*\u00020\u0002\u001a\u0010\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\u00020\u0002\u001a\f\u0010\u0015\u001a\u0004\u0018\u00010\u0006*\u00020\u0002\u001a\f\u0010\u0016\u001a\u0004\u0018\u00010\u0006*\u00020\u0002\u001a\u0012\u0010\u0017\u001a\n\u0018\u00010\u0018j\u0004\u0018\u0001`\u0019*\u00020\u0002\u001a\u0011\u0010\u001a\u001a\u0004\u0018\u00010\u001b*\u00020\u0002¢\u0006\u0002\u0010\u001c\u001a\n\u0010\u001d\u001a\u00020\u001e*\u00020\u0002\u001a\n\u0010\u001f\u001a\u00020\u0006*\u00020\u0002\u001a\u0014\u0010 \u001a\u0004\u0018\u00010\u0006*\u00020\u00022\u0006\u0010!\u001a\u00020\u0006\u001a\n\u0010\"\u001a\u00020\u0006*\u00020\u0002\u001a\n\u0010#\u001a\u00020$*\u00020\u0002\u001a\n\u0010%\u001a\u00020$*\u00020\u0002\u001a\f\u0010&\u001a\u0004\u0018\u00010\u0006*\u00020\u0002\u001a\n\u0010'\u001a\u00020\u0006*\u00020\u0002\u001a\n\u0010(\u001a\u00020)*\u00020\u0002\u001a\n\u0010*\u001a\u00020\u0006*\u00020\u0002\u001a\f\u0010+\u001a\u0004\u0018\u00010,*\u00020\u0002\u001a\f\u0010-\u001a\u0004\u0018\u00010\u0006*\u00020\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0015\u0010\t\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u0006."}, d2 = {"httpMethod", "Lio/ktor/http/HttpMethod;", "Lio/ktor/server/request/ApplicationRequest;", "getHttpMethod", "(Lio/ktor/server/request/ApplicationRequest;)Lio/ktor/http/HttpMethod;", "httpVersion", "", "getHttpVersion", "(Lio/ktor/server/request/ApplicationRequest;)Ljava/lang/String;", "uri", "getUri", "accept", "acceptCharset", "acceptCharsetItems", "", "Lio/ktor/http/HeaderValue;", "acceptEncoding", "acceptEncodingItems", "acceptItems", "acceptLanguage", "acceptLanguageItems", "authorization", "cacheControl", "contentCharset", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "contentLength", "", "(Lio/ktor/server/request/ApplicationRequest;)Ljava/lang/Long;", "contentType", "Lio/ktor/http/ContentType;", "document", "header", "name", "host", "isChunked", "", "isMultipart", "location", "path", "port", "", "queryString", "ranges", "Lio/ktor/http/RangesSpecifier;", "userAgent", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationRequestProperties.kt */
public final class ApplicationRequestPropertiesKt {
    public static final String header(ApplicationRequest applicationRequest, String str) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        return applicationRequest.getHeaders().get(str);
    }

    public static final String queryString(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return StringsKt.substringAfter(OriginConnectionPointKt.getOrigin(applicationRequest).getUri(), '?', "");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0011, code lost:
        r1 = io.ktor.http.ContentType.Companion.parse(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final io.ktor.http.ContentType contentType(io.ktor.server.request.ApplicationRequest r1) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            io.ktor.http.HttpHeaders r0 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r0 = r0.getContentType()
            java.lang.String r1 = header(r1, r0)
            if (r1 == 0) goto L_0x0019
            io.ktor.http.ContentType$Companion r0 = io.ktor.http.ContentType.Companion
            io.ktor.http.ContentType r1 = r0.parse(r1)
            if (r1 != 0) goto L_0x001f
        L_0x0019:
            io.ktor.http.ContentType$Companion r1 = io.ktor.http.ContentType.Companion
            io.ktor.http.ContentType r1 = r1.getAny()
        L_0x001f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.request.ApplicationRequestPropertiesKt.contentType(io.ktor.server.request.ApplicationRequest):io.ktor.http.ContentType");
    }

    public static final Long contentLength(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        String header = header(applicationRequest, HttpHeaders.INSTANCE.getContentLength());
        if (header != null) {
            return StringsKt.toLongOrNull(header);
        }
        return null;
    }

    public static final Charset contentCharset(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return ContentTypesKt.charset(contentType(applicationRequest));
    }

    public static final String document(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return StringsKt.substringAfterLast$default(path(applicationRequest), '/', (String) null, 2, (Object) null);
    }

    public static final String path(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return StringsKt.substringBefore$default(OriginConnectionPointKt.getOrigin(applicationRequest).getUri(), '?', (String) null, 2, (Object) null);
    }

    public static final String authorization(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return header(applicationRequest, HttpHeaders.INSTANCE.getAuthorization());
    }

    public static final String location(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return header(applicationRequest, HttpHeaders.INSTANCE.getLocation());
    }

    public static final String accept(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return header(applicationRequest, HttpHeaders.INSTANCE.getAccept());
    }

    public static final List<HeaderValue> acceptItems(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return HttpHeaderValueParserKt.parseAndSortContentTypeHeader(header(applicationRequest, HttpHeaders.INSTANCE.getAccept()));
    }

    public static final String acceptEncoding(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return header(applicationRequest, HttpHeaders.INSTANCE.getAcceptEncoding());
    }

    public static final List<HeaderValue> acceptEncodingItems(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return HttpHeaderValueParserKt.parseAndSortHeader(header(applicationRequest, HttpHeaders.INSTANCE.getAcceptEncoding()));
    }

    public static final String acceptLanguage(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return header(applicationRequest, HttpHeaders.INSTANCE.getAcceptLanguage());
    }

    public static final List<HeaderValue> acceptLanguageItems(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return HttpHeaderValueParserKt.parseAndSortHeader(header(applicationRequest, HttpHeaders.INSTANCE.getAcceptLanguage()));
    }

    public static final String acceptCharset(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return header(applicationRequest, HttpHeaders.INSTANCE.getAcceptCharset());
    }

    public static final List<HeaderValue> acceptCharsetItems(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return HttpHeaderValueParserKt.parseAndSortHeader(header(applicationRequest, HttpHeaders.INSTANCE.getAcceptCharset()));
    }

    public static final boolean isChunked(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        String header = header(applicationRequest, HttpHeaders.INSTANCE.getTransferEncoding());
        return header != null && StringsKt.compareTo(header, HttpHeaders.Values.CHUNKED, true) == 0;
    }

    public static final boolean isMultipart(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return contentType(applicationRequest).match(ContentType.MultiPart.INSTANCE.getAny());
    }

    public static final String userAgent(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return header(applicationRequest, io.ktor.http.HttpHeaders.INSTANCE.getUserAgent());
    }

    public static final String cacheControl(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return header(applicationRequest, io.ktor.http.HttpHeaders.INSTANCE.getCacheControl());
    }

    public static final String host(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return OriginConnectionPointKt.getOrigin(applicationRequest).getServerHost();
    }

    public static final int port(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return OriginConnectionPointKt.getOrigin(applicationRequest).getServerPort();
    }

    public static final RangesSpecifier ranges(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        String header = header(applicationRequest, io.ktor.http.HttpHeaders.INSTANCE.getRange());
        if (header != null) {
            return RangesKt.parseRangesSpecifier(header);
        }
        return null;
    }

    public static final String getUri(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return OriginConnectionPointKt.getOrigin(applicationRequest).getUri();
    }

    public static final HttpMethod getHttpMethod(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return OriginConnectionPointKt.getOrigin(applicationRequest).getMethod();
    }

    public static final String getHttpVersion(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return OriginConnectionPointKt.getOrigin(applicationRequest).getVersion();
    }
}
