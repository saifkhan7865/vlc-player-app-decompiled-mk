package io.ktor.client.plugins.cache;

import io.ktor.client.statement.HttpResponse;
import io.ktor.http.DateUtilsKt;
import io.ktor.http.HeaderValue;
import io.ktor.http.Headers;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMessage;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.util.date.DateKt;
import io.ktor.util.date.GMTDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\u001a!\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000\u001a$\u0010\t\u001a\u00020\n*\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u0010H\u0000\u001a\u0018\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u0012*\u00020\u0005H\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"HttpCacheEntry", "Lio/ktor/client/plugins/cache/HttpCacheEntry;", "isShared", "", "response", "Lio/ktor/client/statement/HttpResponse;", "(ZLio/ktor/client/statement/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldValidate", "Lio/ktor/client/plugins/cache/ValidateStatus;", "cacheExpires", "Lio/ktor/util/date/GMTDate;", "responseHeaders", "Lio/ktor/http/Headers;", "request", "Lio/ktor/client/request/HttpRequestBuilder;", "fallback", "Lkotlin/Function0;", "varyKeys", "", "", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpCacheEntry.kt */
public final class HttpCacheEntryKt {
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object HttpCacheEntry(boolean r8, io.ktor.client.statement.HttpResponse r9, kotlin.coroutines.Continuation<? super io.ktor.client.plugins.cache.HttpCacheEntry> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.client.plugins.cache.HttpCacheEntryKt$HttpCacheEntry$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.client.plugins.cache.HttpCacheEntryKt$HttpCacheEntry$1 r0 = (io.ktor.client.plugins.cache.HttpCacheEntryKt$HttpCacheEntry$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.client.plugins.cache.HttpCacheEntryKt$HttpCacheEntry$1 r0 = new io.ktor.client.plugins.cache.HttpCacheEntryKt$HttpCacheEntry$1
            r0.<init>(r10)
        L_0x0019:
            r4 = r0
            java.lang.Object r10 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r7 = 1
            if (r1 == 0) goto L_0x0039
            if (r1 != r7) goto L_0x0031
            boolean r8 = r4.Z$0
            java.lang.Object r9 = r4.L$0
            io.ktor.client.statement.HttpResponse r9 = (io.ktor.client.statement.HttpResponse) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0051
        L_0x0031:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r10)
            io.ktor.utils.io.ByteReadChannel r1 = r9.getContent()
            r4.L$0 = r9
            r4.Z$0 = r8
            r4.label = r7
            r2 = 0
            r5 = 1
            r6 = 0
            java.lang.Object r10 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r1, r2, r4, r5, r6)
            if (r10 != r0) goto L_0x0051
            return r0
        L_0x0051:
            io.ktor.utils.io.core.ByteReadPacket r10 = (io.ktor.utils.io.core.ByteReadPacket) r10
            r0 = 0
            r1 = 0
            byte[] r10 = io.ktor.utils.io.core.StringsKt.readBytes$default(r10, r0, r7, r1)
            io.ktor.client.statement.HttpResponseKt.complete(r9)
            io.ktor.client.plugins.cache.HttpCacheEntry r0 = new io.ktor.client.plugins.cache.HttpCacheEntry
            r2 = 2
            io.ktor.util.date.GMTDate r8 = cacheExpires$default(r9, r8, r1, r2, r1)
            java.util.Map r1 = varyKeys(r9)
            r0.<init>(r8, r1, r9, r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.HttpCacheEntryKt.HttpCacheEntry(boolean, io.ktor.client.statement.HttpResponse, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Map<String, String> varyKeys(HttpResponse httpResponse) {
        Intrinsics.checkNotNullParameter(httpResponse, "<this>");
        List<String> vary = HttpMessagePropertiesKt.vary((HttpMessage) httpResponse);
        if (vary == null) {
            return MapsKt.emptyMap();
        }
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        Headers headers = httpResponse.getCall().getRequest().getHeaders();
        for (String next : vary) {
            String str = headers.get(next);
            if (str == null) {
                str = "";
            }
            linkedHashMap.put(next, str);
        }
        return linkedHashMap;
    }

    public static /* synthetic */ GMTDate cacheExpires$default(HttpResponse httpResponse, boolean z, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = HttpCacheEntryKt$cacheExpires$1.INSTANCE;
        }
        return cacheExpires(httpResponse, z, function0);
    }

    public static final GMTDate cacheExpires(HttpResponse httpResponse, boolean z, Function0<GMTDate> function0) {
        String str;
        Object obj;
        String value;
        List split$default;
        String str2;
        Intrinsics.checkNotNullParameter(httpResponse, "<this>");
        Intrinsics.checkNotNullParameter(function0, "fallback");
        List<HeaderValue> cacheControl = HttpMessagePropertiesKt.cacheControl(httpResponse);
        Integer num = null;
        if (z) {
            Iterable iterable = cacheControl;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                Iterator it = iterable.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    str = "s-maxage";
                    if (StringsKt.startsWith$default(((HeaderValue) it.next()).getValue(), str, false, 2, (Object) null)) {
                        break;
                    }
                }
            }
        }
        str = "max-age";
        Iterator it2 = cacheControl.iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj = null;
                break;
            }
            obj = it2.next();
            if (StringsKt.startsWith$default(((HeaderValue) obj).getValue(), str, false, 2, (Object) null)) {
                break;
            }
        }
        HeaderValue headerValue = (HeaderValue) obj;
        if (!(headerValue == null || (value = headerValue.getValue()) == null || (split$default = StringsKt.split$default((CharSequence) value, new String[]{"="}, false, 0, 6, (Object) null)) == null || (str2 = (String) split$default.get(1)) == null)) {
            num = Integer.valueOf(Integer.parseInt(str2));
        }
        if (num != null) {
            return DateKt.plus(httpResponse.getRequestTime(), ((long) num.intValue()) * 1000);
        }
        String str3 = httpResponse.getHeaders().get(HttpHeaders.INSTANCE.getExpires());
        if (str3 == null) {
            return function0.invoke();
        }
        if (Intrinsics.areEqual((Object) str3, (Object) Constants.GROUP_VIDEOS_FOLDER) || StringsKt.isBlank(str3)) {
            return function0.invoke();
        }
        try {
            return DateUtilsKt.fromHttpToGmtDate(str3);
        } catch (Throwable unused) {
            return function0.invoke();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: io.ktor.http.HeaderValue} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: io.ktor.http.HeaderValue} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: io.ktor.http.HeaderValue} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: io.ktor.http.HeaderValue} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final io.ktor.client.plugins.cache.ValidateStatus shouldValidate(io.ktor.util.date.GMTDate r17, io.ktor.http.Headers r18, io.ktor.client.request.HttpRequestBuilder r19) {
        /*
            r0 = r18
            java.lang.String r1 = "cacheExpires"
            r2 = r17
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            java.lang.String r1 = "responseHeaders"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            java.lang.String r1 = "request"
            r3 = r19
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r1)
            io.ktor.http.HeadersBuilder r1 = r19.getHeaders()
            io.ktor.http.HttpHeaders r4 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r4 = r4.getCacheControl()
            java.util.List r0 = r0.getAll(r4)
            java.lang.String r4 = ","
            r5 = 0
            if (r0 == 0) goto L_0x003b
            r6 = r0
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            r7 = r4
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            r13 = 62
            r14 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            java.lang.String r0 = kotlin.collections.CollectionsKt.joinToString$default(r6, r7, r8, r9, r10, r11, r12, r13, r14)
            goto L_0x003c
        L_0x003b:
            r0 = r5
        L_0x003c:
            java.util.List r0 = io.ktor.http.HttpHeaderValueParserKt.parseHeaderValue(r0)
            io.ktor.http.HttpHeaders r6 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r6 = r6.getCacheControl()
            java.util.List r1 = r1.getAll(r6)
            if (r1 == 0) goto L_0x005f
            r6 = r1
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            r7 = r4
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            r13 = 62
            r14 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            java.lang.String r1 = kotlin.collections.CollectionsKt.joinToString$default(r6, r7, r8, r9, r10, r11, r12, r13, r14)
            goto L_0x0060
        L_0x005f:
            r1 = r5
        L_0x0060:
            java.util.List r1 = io.ktor.http.HttpHeaderValueParserKt.parseHeaderValue(r1)
            io.ktor.client.plugins.cache.CacheControl r4 = io.ktor.client.plugins.cache.CacheControl.INSTANCE
            io.ktor.http.HeaderValue r4 = r4.getNO_CACHE$ktor_client_core()
            boolean r4 = r1.contains(r4)
            java.lang.String r6 = "\"no-cache\" is set for "
            java.lang.String r7 = ", should validate cached response"
            if (r4 == 0) goto L_0x0091
            org.slf4j.Logger r0 = io.ktor.client.plugins.cache.HttpCacheKt.getLOGGER()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r6)
            io.ktor.http.URLBuilder r2 = r19.getUrl()
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            r0.trace(r1)
            io.ktor.client.plugins.cache.ValidateStatus r0 = io.ktor.client.plugins.cache.ValidateStatus.ShouldValidate
            return r0
        L_0x0091:
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r4 = r1.iterator()
        L_0x0097:
            boolean r8 = r4.hasNext()
            r9 = 2
            r10 = 0
            if (r8 == 0) goto L_0x00b3
            java.lang.Object r8 = r4.next()
            r11 = r8
            io.ktor.http.HeaderValue r11 = (io.ktor.http.HeaderValue) r11
            java.lang.String r11 = r11.getValue()
            java.lang.String r12 = "max-age="
            boolean r11 = kotlin.text.StringsKt.startsWith$default(r11, r12, r10, r9, r5)
            if (r11 == 0) goto L_0x0097
            goto L_0x00b4
        L_0x00b3:
            r8 = r5
        L_0x00b4:
            io.ktor.http.HeaderValue r8 = (io.ktor.http.HeaderValue) r8
            if (r8 == 0) goto L_0x00ec
            java.lang.String r4 = r8.getValue()
            if (r4 == 0) goto L_0x00ec
            r11 = r4
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            r4 = 1
            java.lang.String[] r12 = new java.lang.String[r4]
            java.lang.String r8 = "="
            r12[r10] = r8
            r15 = 6
            r16 = 0
            r13 = 0
            r14 = 0
            java.util.List r8 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r11, (java.lang.String[]) r12, (boolean) r13, (int) r14, (int) r15, (java.lang.Object) r16)
            if (r8 == 0) goto L_0x00ec
            java.lang.Object r4 = r8.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L_0x00ec
            java.lang.Integer r4 = kotlin.text.StringsKt.toIntOrNull(r4)
            if (r4 == 0) goto L_0x00e6
            int r4 = r4.intValue()
            goto L_0x00e7
        L_0x00e6:
            r4 = 0
        L_0x00e7:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            goto L_0x00ed
        L_0x00ec:
            r4 = r5
        L_0x00ed:
            if (r4 != 0) goto L_0x00f0
            goto L_0x0115
        L_0x00f0:
            int r4 = r4.intValue()
            if (r4 != 0) goto L_0x0115
            org.slf4j.Logger r0 = io.ktor.client.plugins.cache.HttpCacheKt.getLOGGER()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "\"max-age\" is not set for "
            r1.<init>(r2)
            io.ktor.http.URLBuilder r2 = r19.getUrl()
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            r0.trace(r1)
            io.ktor.client.plugins.cache.ValidateStatus r0 = io.ktor.client.plugins.cache.ValidateStatus.ShouldValidate
            return r0
        L_0x0115:
            io.ktor.client.plugins.cache.CacheControl r4 = io.ktor.client.plugins.cache.CacheControl.INSTANCE
            io.ktor.http.HeaderValue r4 = r4.getNO_CACHE$ktor_client_core()
            boolean r4 = r0.contains(r4)
            if (r4 == 0) goto L_0x013e
            org.slf4j.Logger r0 = io.ktor.client.plugins.cache.HttpCacheKt.getLOGGER()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r6)
            io.ktor.http.URLBuilder r2 = r19.getUrl()
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            r0.trace(r1)
            io.ktor.client.plugins.cache.ValidateStatus r0 = io.ktor.client.plugins.cache.ValidateStatus.ShouldValidate
            return r0
        L_0x013e:
            long r11 = r17.getTimestamp()
            long r13 = io.ktor.util.date.DateJvmKt.getTimeMillis()
            long r11 = r11 - r13
            r13 = 0
            int r2 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r2 <= 0) goto L_0x016e
            org.slf4j.Logger r0 = io.ktor.client.plugins.cache.HttpCacheKt.getLOGGER()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Cached response is valid for "
            r1.<init>(r2)
            io.ktor.http.URLBuilder r2 = r19.getUrl()
            r1.append(r2)
            java.lang.String r2 = ", should not validate"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.trace(r1)
            io.ktor.client.plugins.cache.ValidateStatus r0 = io.ktor.client.plugins.cache.ValidateStatus.ShouldNotValidate
            return r0
        L_0x016e:
            io.ktor.client.plugins.cache.CacheControl r2 = io.ktor.client.plugins.cache.CacheControl.INSTANCE
            io.ktor.http.HeaderValue r2 = r2.getMUST_REVALIDATE$ktor_client_core()
            boolean r0 = r0.contains(r2)
            if (r0 == 0) goto L_0x0199
            org.slf4j.Logger r0 = io.ktor.client.plugins.cache.HttpCacheKt.getLOGGER()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "\"must-revalidate\" is set for "
            r1.<init>(r2)
            io.ktor.http.URLBuilder r2 = r19.getUrl()
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            r0.trace(r1)
            io.ktor.client.plugins.cache.ValidateStatus r0 = io.ktor.client.plugins.cache.ValidateStatus.ShouldValidate
            return r0
        L_0x0199:
            java.util.Iterator r0 = r1.iterator()
        L_0x019d:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x01b7
            java.lang.Object r1 = r0.next()
            r2 = r1
            io.ktor.http.HeaderValue r2 = (io.ktor.http.HeaderValue) r2
            java.lang.String r2 = r2.getValue()
            java.lang.String r4 = "max-stale="
            boolean r2 = kotlin.text.StringsKt.startsWith$default(r2, r4, r10, r9, r5)
            if (r2 == 0) goto L_0x019d
            r5 = r1
        L_0x01b7:
            io.ktor.http.HeaderValue r5 = (io.ktor.http.HeaderValue) r5
            if (r5 == 0) goto L_0x01d8
            java.lang.String r0 = r5.getValue()
            if (r0 == 0) goto L_0x01d8
            r1 = 10
            java.lang.String r0 = r0.substring(r1)
            java.lang.String r1 = "this as java.lang.String).substring(startIndex)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            if (r0 == 0) goto L_0x01d8
            java.lang.Integer r0 = kotlin.text.StringsKt.toIntOrNull(r0)
            if (r0 == 0) goto L_0x01d8
            int r10 = r0.intValue()
        L_0x01d8:
            long r0 = (long) r10
            r4 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 * r4
            long r11 = r11 + r0
            java.lang.String r0 = "Cached response is stale for "
            int r1 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r1 <= 0) goto L_0x0203
            org.slf4j.Logger r1 = io.ktor.client.plugins.cache.HttpCacheKt.getLOGGER()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r0)
            io.ktor.http.URLBuilder r0 = r19.getUrl()
            r2.append(r0)
            java.lang.String r0 = " but less than max-stale, should warn"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.trace(r0)
            io.ktor.client.plugins.cache.ValidateStatus r0 = io.ktor.client.plugins.cache.ValidateStatus.ShouldWarn
            return r0
        L_0x0203:
            org.slf4j.Logger r1 = io.ktor.client.plugins.cache.HttpCacheKt.getLOGGER()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r0)
            io.ktor.http.URLBuilder r0 = r19.getUrl()
            r2.append(r0)
            r2.append(r7)
            java.lang.String r0 = r2.toString()
            r1.trace(r0)
            io.ktor.client.plugins.cache.ValidateStatus r0 = io.ktor.client.plugins.cache.ValidateStatus.ShouldValidate
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.HttpCacheEntryKt.shouldValidate(io.ktor.util.date.GMTDate, io.ktor.http.Headers, io.ktor.client.request.HttpRequestBuilder):io.ktor.client.plugins.cache.ValidateStatus");
    }
}
