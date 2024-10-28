package io.ktor.http;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.fusesource.jansi.AnsiRenderer;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003\u001a\u0012\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006*\u00020\u0003\u001a\u0012\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006*\u00020\u0007\u001a\u001f\u0010\u0004\u001a\u0004\u0018\u00010\b*\u00020\u00072\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0007¢\u0006\u0002\u0010\t\u001a\u0011\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u0003¢\u0006\u0002\u0010\f\u001a\u0011\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u0007¢\u0006\u0002\u0010\r\u001a\u0014\u0010\n\u001a\u00020\b*\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\f\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u0003\u001a\f\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u0007\u001a\u0012\u0010\u0010\u001a\u00020\b*\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0011\u001a\u0010\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0001*\u00020\u0007\u001a\f\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\u00020\u0003\u001a\f\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\u00020\u0007\u001a\u0012\u0010\u0017\u001a\u00020\b*\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0016\u001a\u0012\u0010\u0019\u001a\u00020\b*\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u000f\u001a\u0010\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00140\u0001*\u00020\u0003\u001a\u0012\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00160\u0001*\u00020\u0016H\u0000\u001a\u0012\u0010\u001d\u001a\u00020\b*\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u0016\u001a\u0012\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0001*\u00020\u0003\u001a\u0012\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0001*\u00020\u0007¨\u0006 "}, d2 = {"cacheControl", "", "Lio/ktor/http/HeaderValue;", "Lio/ktor/http/HttpMessage;", "charset", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "Lio/ktor/http/HttpMessageBuilder;", "", "(Lio/ktor/http/HttpMessageBuilder;Ljava/nio/charset/Charset;)Lkotlin/Unit;", "contentLength", "", "(Lio/ktor/http/HttpMessage;)Ljava/lang/Long;", "(Lio/ktor/http/HttpMessageBuilder;)Ljava/lang/Long;", "length", "", "contentType", "Lio/ktor/http/ContentType;", "type", "cookies", "Lio/ktor/http/Cookie;", "etag", "", "ifNoneMatch", "value", "maxAge", "seconds", "setCookie", "splitSetCookieHeader", "userAgent", "content", "vary", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpMessageProperties.kt */
public final class HttpMessagePropertiesKt {
    public static final void contentType(HttpMessageBuilder httpMessageBuilder, ContentType contentType) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        Intrinsics.checkNotNullParameter(contentType, "type");
        httpMessageBuilder.getHeaders().set(HttpHeaders.INSTANCE.getContentType(), contentType.toString());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Content-Length is controlled by underlying engine. Don't specify it explicitly.")
    public static final void contentLength(HttpMessageBuilder httpMessageBuilder, int i) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        httpMessageBuilder.getHeaders().set(HttpHeaders.INSTANCE.getContentLength(), String.valueOf(i));
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use content with particular content type and charset instead")
    public static final Unit charset(HttpMessageBuilder httpMessageBuilder, Charset charset) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        ContentType contentType = contentType(httpMessageBuilder);
        if (contentType == null) {
            return null;
        }
        contentType(httpMessageBuilder, ContentTypesKt.withCharset(contentType, charset));
        return Unit.INSTANCE;
    }

    public static final void maxAge(HttpMessageBuilder httpMessageBuilder, int i) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        HeadersBuilder headers = httpMessageBuilder.getHeaders();
        String cacheControl = HttpHeaders.INSTANCE.getCacheControl();
        headers.append(cacheControl, "max-age=" + i);
    }

    public static final void ifNoneMatch(HttpMessageBuilder httpMessageBuilder, String str) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        Intrinsics.checkNotNullParameter(str, "value");
        httpMessageBuilder.getHeaders().set(HttpHeaders.INSTANCE.getIfNoneMatch(), str);
    }

    public static final void userAgent(HttpMessageBuilder httpMessageBuilder, String str) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        Intrinsics.checkNotNullParameter(str, "content");
        httpMessageBuilder.getHeaders().set(HttpHeaders.INSTANCE.getUserAgent(), str);
    }

    public static final ContentType contentType(HttpMessageBuilder httpMessageBuilder) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        String str = httpMessageBuilder.getHeaders().get(HttpHeaders.INSTANCE.getContentType());
        if (str != null) {
            return ContentType.Companion.parse(str);
        }
        return null;
    }

    public static final Charset charset(HttpMessageBuilder httpMessageBuilder) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        ContentType contentType = contentType(httpMessageBuilder);
        if (contentType != null) {
            return ContentTypesKt.charset(contentType);
        }
        return null;
    }

    public static final String etag(HttpMessageBuilder httpMessageBuilder) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        return httpMessageBuilder.getHeaders().get(HttpHeaders.INSTANCE.getETag());
    }

    public static final List<String> vary(HttpMessageBuilder httpMessageBuilder) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        List<String> all = httpMessageBuilder.getHeaders().getAll(HttpHeaders.INSTANCE.getVary());
        if (all == null) {
            return null;
        }
        Collection arrayList = new ArrayList();
        for (String split$default : all) {
            Iterable<String> split$default2 = StringsKt.split$default((CharSequence) split$default, new String[]{AnsiRenderer.CODE_LIST_SEPARATOR}, false, 0, 6, (Object) null);
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(split$default2, 10));
            for (String trim : split$default2) {
                arrayList2.add(StringsKt.trim((CharSequence) trim).toString());
            }
            CollectionsKt.addAll(arrayList, (List) arrayList2);
        }
        return (List) arrayList;
    }

    public static final Long contentLength(HttpMessageBuilder httpMessageBuilder) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        String str = httpMessageBuilder.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
        if (str != null) {
            return Long.valueOf(Long.parseLong(str));
        }
        return null;
    }

    public static final ContentType contentType(HttpMessage httpMessage) {
        Intrinsics.checkNotNullParameter(httpMessage, "<this>");
        String str = httpMessage.getHeaders().get(HttpHeaders.INSTANCE.getContentType());
        if (str != null) {
            return ContentType.Companion.parse(str);
        }
        return null;
    }

    public static final Charset charset(HttpMessage httpMessage) {
        Intrinsics.checkNotNullParameter(httpMessage, "<this>");
        ContentType contentType = contentType(httpMessage);
        if (contentType != null) {
            return ContentTypesKt.charset(contentType);
        }
        return null;
    }

    public static final String etag(HttpMessage httpMessage) {
        Intrinsics.checkNotNullParameter(httpMessage, "<this>");
        return httpMessage.getHeaders().get(HttpHeaders.INSTANCE.getETag());
    }

    public static final List<String> vary(HttpMessage httpMessage) {
        Intrinsics.checkNotNullParameter(httpMessage, "<this>");
        List<String> all = httpMessage.getHeaders().getAll(HttpHeaders.INSTANCE.getVary());
        if (all == null) {
            return null;
        }
        Collection arrayList = new ArrayList();
        for (String split$default : all) {
            Iterable<String> split$default2 = StringsKt.split$default((CharSequence) split$default, new String[]{AnsiRenderer.CODE_LIST_SEPARATOR}, false, 0, 6, (Object) null);
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(split$default2, 10));
            for (String trim : split$default2) {
                arrayList2.add(StringsKt.trim((CharSequence) trim).toString());
            }
            CollectionsKt.addAll(arrayList, (List) arrayList2);
        }
        return (List) arrayList;
    }

    public static final Long contentLength(HttpMessage httpMessage) {
        Intrinsics.checkNotNullParameter(httpMessage, "<this>");
        String str = httpMessage.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
        if (str != null) {
            return Long.valueOf(Long.parseLong(str));
        }
        return null;
    }

    public static final List<Cookie> setCookie(HttpMessage httpMessage) {
        Intrinsics.checkNotNullParameter(httpMessage, "<this>");
        List<String> all = httpMessage.getHeaders().getAll(HttpHeaders.INSTANCE.getSetCookie());
        if (all == null) {
            return CollectionsKt.emptyList();
        }
        Collection arrayList = new ArrayList();
        for (String splitSetCookieHeader : all) {
            CollectionsKt.addAll(arrayList, splitSetCookieHeader(splitSetCookieHeader));
        }
        Iterable<String> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String parseServerSetCookieHeader : iterable) {
            arrayList2.add(CookieKt.parseServerSetCookieHeader(parseServerSetCookieHeader));
        }
        return (List) arrayList2;
    }

    public static final List<Cookie> cookies(HttpMessageBuilder httpMessageBuilder) {
        Intrinsics.checkNotNullParameter(httpMessageBuilder, "<this>");
        List<String> all = httpMessageBuilder.getHeaders().getAll(HttpHeaders.INSTANCE.getSetCookie());
        if (all == null) {
            return CollectionsKt.emptyList();
        }
        Iterable<String> iterable = all;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String parseServerSetCookieHeader : iterable) {
            arrayList.add(CookieKt.parseServerSetCookieHeader(parseServerSetCookieHeader));
        }
        return (List) arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0015, code lost:
        r1 = io.ktor.http.HttpHeaderValueParserKt.parseHeaderValue(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.util.List<io.ktor.http.HeaderValue> cacheControl(io.ktor.http.HttpMessage r1) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            io.ktor.http.Headers r1 = r1.getHeaders()
            io.ktor.http.HttpHeaders r0 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r0 = r0.getCacheControl()
            java.lang.String r1 = r1.get(r0)
            if (r1 == 0) goto L_0x001b
            java.util.List r1 = io.ktor.http.HttpHeaderValueParserKt.parseHeaderValue(r1)
            if (r1 != 0) goto L_0x001f
        L_0x001b:
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
        L_0x001f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.HttpMessagePropertiesKt.cacheControl(io.ktor.http.HttpMessage):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0072  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.util.List<java.lang.String> splitSetCookieHeader(java.lang.String r15) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            r0 = r15
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r5 = 6
            r6 = 0
            r2 = 44
            r3 = 0
            r4 = 0
            r1 = r0
            int r7 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r1, (char) r2, (int) r3, (boolean) r4, (int) r5, (java.lang.Object) r6)
            r8 = -1
            if (r7 != r8) goto L_0x001b
            java.util.List r15 = kotlin.collections.CollectionsKt.listOf(r15)
            return r15
        L_0x001b:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r9 = r1
            java.util.List r9 = (java.util.List) r9
            r5 = 4
            r6 = 0
            r2 = 61
            r4 = 0
            r1 = r0
            r3 = r7
            int r10 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r1, (char) r2, (int) r3, (boolean) r4, (int) r5, (java.lang.Object) r6)
            r2 = 59
            int r1 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r1, (char) r2, (int) r3, (boolean) r4, (int) r5, (java.lang.Object) r6)
            r2 = 0
            r11 = r7
            r7 = r1
            r1 = r10
            r10 = 0
        L_0x0039:
            int r2 = r15.length()
            java.lang.String r12 = "this as java.lang.String).substring(startIndex)"
            if (r10 >= r2) goto L_0x00a6
            if (r11 <= 0) goto L_0x00a6
            if (r1 >= r11) goto L_0x0050
            r5 = 4
            r6 = 0
            r2 = 61
            r4 = 0
            r1 = r0
            r3 = r11
            int r1 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r1, (char) r2, (int) r3, (boolean) r4, (int) r5, (java.lang.Object) r6)
        L_0x0050:
            r13 = r1
            int r3 = r11 + 1
            r5 = 4
            r6 = 0
            r2 = 44
            r4 = 0
            r1 = r0
            int r1 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r1, (char) r2, (int) r3, (boolean) r4, (int) r5, (java.lang.Object) r6)
        L_0x005d:
            r14 = r11
            r11 = r1
            if (r11 < 0) goto L_0x0070
            if (r11 >= r13) goto L_0x0070
            int r3 = r11 + 1
            r5 = 4
            r6 = 0
            r2 = 44
            r4 = 0
            r1 = r0
            int r1 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r1, (char) r2, (int) r3, (boolean) r4, (int) r5, (java.lang.Object) r6)
            goto L_0x005d
        L_0x0070:
            if (r7 >= r14) goto L_0x007e
            r5 = 4
            r6 = 0
            r2 = 59
            r4 = 0
            r1 = r0
            r3 = r14
            int r1 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r1, (char) r2, (int) r3, (boolean) r4, (int) r5, (java.lang.Object) r6)
            r7 = r1
        L_0x007e:
            if (r13 >= 0) goto L_0x008e
            r0 = r9
            java.util.Collection r0 = (java.util.Collection) r0
            java.lang.String r15 = r15.substring(r10)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r12)
            r0.add(r15)
            return r9
        L_0x008e:
            if (r7 == r8) goto L_0x0092
            if (r7 <= r13) goto L_0x00a4
        L_0x0092:
            r1 = r9
            java.util.Collection r1 = (java.util.Collection) r1
            java.lang.String r2 = r15.substring(r10, r14)
            java.lang.String r3 = "this as java.lang.String…ing(startIndex, endIndex)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r1.add(r2)
            int r14 = r14 + 1
            r10 = r14
        L_0x00a4:
            r1 = r13
            goto L_0x0039
        L_0x00a6:
            int r0 = r15.length()
            if (r10 >= r0) goto L_0x00b9
            r0 = r9
            java.util.Collection r0 = (java.util.Collection) r0
            java.lang.String r15 = r15.substring(r10)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r12)
            r0.add(r15)
        L_0x00b9:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.HttpMessagePropertiesKt.splitSetCookieHeader(java.lang.String):java.util.List");
    }
}
