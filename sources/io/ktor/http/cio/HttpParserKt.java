package io.ktor.http.cio;

import io.ktor.http.HttpMethod;
import io.ktor.http.cio.internals.AsciiCharTree;
import io.ktor.http.cio.internals.CharArrayBuilder;
import io.ktor.http.cio.internals.CharsKt;
import io.ktor.http.cio.internals.MutableRange;
import io.ktor.http.cio.internals.TokenizerKt;
import io.ktor.utils.io.ByteReadChannel;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000l\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0002\u001a\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u0006H\u0002\u001a\u0018\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u001a\u0018\u0010\u0014\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0013H\u0000\u001a(\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0006H\u0002\u001a\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0013H\u0000\u001a\u0019\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH@ø\u0001\u0000¢\u0006\u0002\u0010\u001f\u001a-\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u00152\b\b\u0002\u0010\u0012\u001a\u00020\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010!\u001a\u0018\u0010\"\u001a\u00020#2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u001a\u0018\u0010$\u001a\u00020#2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u001a\u001b\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010\u001d\u001a\u00020\u001eH@ø\u0001\u0000¢\u0006\u0002\u0010\u001f\u001a\u001b\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010\u001d\u001a\u00020\u001eH@ø\u0001\u0000¢\u0006\u0002\u0010\u001f\u001a\u0018\u0010)\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u001a\u0018\u0010*\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u001a\u0018\u0010+\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u001a\u0010\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u0001H\u0002\u001a\u0010\u0010.\u001a\u00020\u000b2\u0006\u0010/\u001a\u00020\rH\u0002\u001a\u0010\u00100\u001a\u00020\u001a2\u0006\u00101\u001a\u00020\rH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u00062"}, d2 = {"HTTP_LINE_LIMIT", "", "HTTP_STATUS_CODE_MAX_RANGE", "HTTP_STATUS_CODE_MIN_RANGE", "hostForbiddenSymbols", "", "", "versions", "Lio/ktor/http/cio/internals/AsciiCharTree;", "", "characterIsNotAllowed", "", "text", "", "ch", "isDelimiter", "", "noColonFound", "range", "Lio/ktor/http/cio/internals/MutableRange;", "parseHeaderName", "Lio/ktor/http/cio/internals/CharArrayBuilder;", "parseHeaderNameFailed", "index", "start", "parseHeaderValue", "", "parseHeaders", "Lio/ktor/http/cio/HttpHeadersMap;", "input", "Lio/ktor/utils/io/ByteReadChannel;", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "builder", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/http/cio/internals/CharArrayBuilder;Lio/ktor/http/cio/internals/MutableRange;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parseHttpMethod", "Lio/ktor/http/HttpMethod;", "parseHttpMethodFull", "parseRequest", "Lio/ktor/http/cio/Request;", "parseResponse", "Lio/ktor/http/cio/Response;", "parseStatusCode", "parseUri", "parseVersion", "statusOutOfRange", "code", "unsupportedHttpVersion", "result", "validateHostHeader", "host", "ktor-http-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpParser.kt */
public final class HttpParserKt {
    private static final int HTTP_LINE_LIMIT = 8192;
    private static final int HTTP_STATUS_CODE_MAX_RANGE = 999;
    private static final int HTTP_STATUS_CODE_MIN_RANGE = 100;
    private static final Set<Character> hostForbiddenSymbols = SetsKt.setOf('/', '?', '#', '@');
    private static final AsciiCharTree<String> versions = AsciiCharTree.Companion.build(CollectionsKt.listOf("HTTP/1.0", "HTTP/1.1"));

    private static final boolean statusOutOfRange(int i) {
        return i < 100 || i > 999;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008b A[Catch:{ all -> 0x0042 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0097 A[Catch:{ all -> 0x0042 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00f3 A[Catch:{ all -> 0x0042 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f4 A[Catch:{ all -> 0x0042 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object parseRequest(io.ktor.utils.io.ByteReadChannel r14, kotlin.coroutines.Continuation<? super io.ktor.http.cio.Request> r15) {
        /*
            boolean r1 = r15 instanceof io.ktor.http.cio.HttpParserKt$parseRequest$1
            if (r1 == 0) goto L_0x0014
            r1 = r15
            io.ktor.http.cio.HttpParserKt$parseRequest$1 r1 = (io.ktor.http.cio.HttpParserKt$parseRequest$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0014
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x0019
        L_0x0014:
            io.ktor.http.cio.HttpParserKt$parseRequest$1 r1 = new io.ktor.http.cio.HttpParserKt$parseRequest$1
            r1.<init>(r15)
        L_0x0019:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            r6 = 0
            if (r3 == 0) goto L_0x0065
            if (r3 == r5) goto L_0x004d
            if (r3 != r4) goto L_0x0045
            java.lang.Object r2 = r1.L$3
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            java.lang.Object r3 = r1.L$2
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            java.lang.Object r4 = r1.L$1
            io.ktor.http.HttpMethod r4 = (io.ktor.http.HttpMethod) r4
            java.lang.Object r1 = r1.L$0
            io.ktor.http.cio.internals.CharArrayBuilder r1 = (io.ktor.http.cio.internals.CharArrayBuilder) r1
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0042 }
            r10 = r2
            r9 = r3
            r8 = r4
            goto L_0x00ee
        L_0x0042:
            r0 = move-exception
            goto L_0x0139
        L_0x0045:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x004d:
            java.lang.Object r3 = r1.L$2
            io.ktor.http.cio.internals.MutableRange r3 = (io.ktor.http.cio.internals.MutableRange) r3
            java.lang.Object r7 = r1.L$1
            io.ktor.http.cio.internals.CharArrayBuilder r7 = (io.ktor.http.cio.internals.CharArrayBuilder) r7
            java.lang.Object r8 = r1.L$0
            io.ktor.utils.io.ByteReadChannel r8 = (io.ktor.utils.io.ByteReadChannel) r8
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0061 }
            r13 = r3
            r3 = r1
            r1 = r7
            r7 = r13
            goto L_0x008e
        L_0x0061:
            r0 = move-exception
            r1 = r7
            goto L_0x0139
        L_0x0065:
            kotlin.ResultKt.throwOnFailure(r0)
            io.ktor.http.cio.internals.CharArrayBuilder r0 = new io.ktor.http.cio.internals.CharArrayBuilder
            r0.<init>(r6, r5, r6)
            io.ktor.http.cio.internals.MutableRange r3 = new io.ktor.http.cio.internals.MutableRange
            r7 = 0
            r3.<init>(r7, r7)
            r7 = r3
            r3 = r1
            r1 = r0
            r0 = r14
        L_0x0077:
            r8 = r1
            java.lang.Appendable r8 = (java.lang.Appendable) r8     // Catch:{ all -> 0x0042 }
            r3.L$0 = r0     // Catch:{ all -> 0x0042 }
            r3.L$1 = r1     // Catch:{ all -> 0x0042 }
            r3.L$2 = r7     // Catch:{ all -> 0x0042 }
            r3.label = r5     // Catch:{ all -> 0x0042 }
            r9 = 8192(0x2000, float:1.14794E-41)
            java.lang.Object r8 = r0.readUTF8LineTo(r8, r9, r3)     // Catch:{ all -> 0x0042 }
            if (r8 != r2) goto L_0x008b
            return r2
        L_0x008b:
            r13 = r8
            r8 = r0
            r0 = r13
        L_0x008e:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x0042 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0042 }
            if (r0 != 0) goto L_0x0097
            return r6
        L_0x0097:
            int r0 = r1.length()     // Catch:{ all -> 0x0042 }
            r7.setEnd(r0)     // Catch:{ all -> 0x0042 }
            int r0 = r7.getStart()     // Catch:{ all -> 0x0042 }
            int r9 = r7.getEnd()     // Catch:{ all -> 0x0042 }
            if (r0 == r9) goto L_0x0136
            r0 = r1
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch:{ all -> 0x0042 }
            io.ktor.http.HttpMethod r0 = parseHttpMethod(r0, r7)     // Catch:{ all -> 0x0042 }
            r5 = r1
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5     // Catch:{ all -> 0x0042 }
            java.lang.CharSequence r5 = parseUri(r5, r7)     // Catch:{ all -> 0x0042 }
            r9 = r1
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ all -> 0x0042 }
            java.lang.CharSequence r9 = parseVersion(r9, r7)     // Catch:{ all -> 0x0042 }
            r10 = r1
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ all -> 0x0042 }
            io.ktor.http.cio.internals.TokenizerKt.skipSpaces(r10, r7)     // Catch:{ all -> 0x0042 }
            int r10 = r7.getStart()     // Catch:{ all -> 0x0042 }
            int r11 = r7.getEnd()     // Catch:{ all -> 0x0042 }
            if (r10 != r11) goto L_0x010c
            int r10 = r5.length()     // Catch:{ all -> 0x0042 }
            if (r10 == 0) goto L_0x0104
            int r10 = r9.length()     // Catch:{ all -> 0x0042 }
            if (r10 == 0) goto L_0x00fc
            r3.L$0 = r1     // Catch:{ all -> 0x0042 }
            r3.L$1 = r0     // Catch:{ all -> 0x0042 }
            r3.L$2 = r5     // Catch:{ all -> 0x0042 }
            r3.L$3 = r9     // Catch:{ all -> 0x0042 }
            r3.label = r4     // Catch:{ all -> 0x0042 }
            java.lang.Object r3 = parseHeaders(r8, r1, r7, r3)     // Catch:{ all -> 0x0042 }
            if (r3 != r2) goto L_0x00ea
            return r2
        L_0x00ea:
            r8 = r0
            r0 = r3
            r10 = r9
            r9 = r5
        L_0x00ee:
            r11 = r0
            io.ktor.http.cio.HttpHeadersMap r11 = (io.ktor.http.cio.HttpHeadersMap) r11     // Catch:{ all -> 0x0042 }
            if (r11 != 0) goto L_0x00f4
            return r6
        L_0x00f4:
            io.ktor.http.cio.Request r0 = new io.ktor.http.cio.Request     // Catch:{ all -> 0x0042 }
            r7 = r0
            r12 = r1
            r7.<init>(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0042 }
            return r0
        L_0x00fc:
            io.ktor.http.cio.ParserException r0 = new io.ktor.http.cio.ParserException     // Catch:{ all -> 0x0042 }
            java.lang.String r2 = "HTTP version is not specified"
            r0.<init>(r2)     // Catch:{ all -> 0x0042 }
            throw r0     // Catch:{ all -> 0x0042 }
        L_0x0104:
            io.ktor.http.cio.ParserException r0 = new io.ktor.http.cio.ParserException     // Catch:{ all -> 0x0042 }
            java.lang.String r2 = "URI is not specified"
            r0.<init>(r2)     // Catch:{ all -> 0x0042 }
            throw r0     // Catch:{ all -> 0x0042 }
        L_0x010c:
            io.ktor.http.cio.ParserException r0 = new io.ktor.http.cio.ParserException     // Catch:{ all -> 0x0042 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0042 }
            r2.<init>()     // Catch:{ all -> 0x0042 }
            java.lang.String r3 = "Extra characters in request line: "
            r2.append(r3)     // Catch:{ all -> 0x0042 }
            r3 = r1
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch:{ all -> 0x0042 }
            int r4 = r7.getStart()     // Catch:{ all -> 0x0042 }
            int r5 = r7.getEnd()     // Catch:{ all -> 0x0042 }
            java.lang.CharSequence r3 = r3.subSequence(r4, r5)     // Catch:{ all -> 0x0042 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0042 }
            r2.append(r3)     // Catch:{ all -> 0x0042 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0042 }
            r0.<init>(r2)     // Catch:{ all -> 0x0042 }
            throw r0     // Catch:{ all -> 0x0042 }
        L_0x0136:
            r0 = r8
            goto L_0x0077
        L_0x0139:
            r1.release()
            goto L_0x013e
        L_0x013d:
            throw r0
        L_0x013e:
            goto L_0x013d
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.HttpParserKt.parseRequest(io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008e A[Catch:{ all -> 0x005b }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x008f A[Catch:{ all -> 0x005b }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d7 A[Catch:{ all -> 0x0040 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00de A[Catch:{ all -> 0x0040 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object parseResponse(io.ktor.utils.io.ByteReadChannel r14, kotlin.coroutines.Continuation<? super io.ktor.http.cio.Response> r15) {
        /*
            boolean r0 = r15 instanceof io.ktor.http.cio.HttpParserKt$parseResponse$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            io.ktor.http.cio.HttpParserKt$parseResponse$1 r0 = (io.ktor.http.cio.HttpParserKt$parseResponse$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            io.ktor.http.cio.HttpParserKt$parseResponse$1 r0 = new io.ktor.http.cio.HttpParserKt$parseResponse$1
            r0.<init>(r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L_0x005f
            if (r2 == r5) goto L_0x004b
            if (r2 != r3) goto L_0x0043
            int r14 = r0.I$0
            java.lang.Object r1 = r0.L$2
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            java.lang.Object r2 = r0.L$1
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            java.lang.Object r0 = r0.L$0
            io.ktor.http.cio.internals.CharArrayBuilder r0 = (io.ktor.http.cio.internals.CharArrayBuilder) r0
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ all -> 0x0040 }
            r9 = r14
            r10 = r1
            r8 = r2
            goto L_0x00d3
        L_0x0040:
            r14 = move-exception
            goto L_0x00e9
        L_0x0043:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L_0x004b:
            java.lang.Object r14 = r0.L$2
            io.ktor.http.cio.internals.MutableRange r14 = (io.ktor.http.cio.internals.MutableRange) r14
            java.lang.Object r2 = r0.L$1
            io.ktor.http.cio.internals.CharArrayBuilder r2 = (io.ktor.http.cio.internals.CharArrayBuilder) r2
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.ByteReadChannel r5 = (io.ktor.utils.io.ByteReadChannel) r5
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ all -> 0x005b }
            goto L_0x0086
        L_0x005b:
            r14 = move-exception
            r0 = r2
            goto L_0x00e9
        L_0x005f:
            kotlin.ResultKt.throwOnFailure(r15)
            io.ktor.http.cio.internals.CharArrayBuilder r15 = new io.ktor.http.cio.internals.CharArrayBuilder
            r15.<init>(r4, r5, r4)
            io.ktor.http.cio.internals.MutableRange r2 = new io.ktor.http.cio.internals.MutableRange
            r6 = 0
            r2.<init>(r6, r6)
            r6 = r15
            java.lang.Appendable r6 = (java.lang.Appendable) r6     // Catch:{ all -> 0x00e7 }
            r0.L$0 = r14     // Catch:{ all -> 0x00e7 }
            r0.L$1 = r15     // Catch:{ all -> 0x00e7 }
            r0.L$2 = r2     // Catch:{ all -> 0x00e7 }
            r0.label = r5     // Catch:{ all -> 0x00e7 }
            r5 = 8192(0x2000, float:1.14794E-41)
            java.lang.Object r5 = r14.readUTF8LineTo(r6, r5, r0)     // Catch:{ all -> 0x00e7 }
            if (r5 != r1) goto L_0x0081
            return r1
        L_0x0081:
            r13 = r5
            r5 = r14
            r14 = r2
            r2 = r15
            r15 = r13
        L_0x0086:
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ all -> 0x005b }
            boolean r15 = r15.booleanValue()     // Catch:{ all -> 0x005b }
            if (r15 != 0) goto L_0x008f
            return r4
        L_0x008f:
            int r15 = r2.length()     // Catch:{ all -> 0x005b }
            r14.setEnd(r15)     // Catch:{ all -> 0x005b }
            r15 = r2
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15     // Catch:{ all -> 0x005b }
            java.lang.CharSequence r15 = parseVersion(r15, r14)     // Catch:{ all -> 0x005b }
            r4 = r2
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch:{ all -> 0x005b }
            int r4 = parseStatusCode(r4, r14)     // Catch:{ all -> 0x005b }
            r6 = r2
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ all -> 0x005b }
            io.ktor.http.cio.internals.TokenizerKt.skipSpaces(r6, r14)     // Catch:{ all -> 0x005b }
            int r6 = r14.getStart()     // Catch:{ all -> 0x005b }
            int r7 = r14.getEnd()     // Catch:{ all -> 0x005b }
            java.lang.CharSequence r6 = r2.subSequence(r6, r7)     // Catch:{ all -> 0x005b }
            int r7 = r14.getEnd()     // Catch:{ all -> 0x005b }
            r14.setStart(r7)     // Catch:{ all -> 0x005b }
            r0.L$0 = r2     // Catch:{ all -> 0x005b }
            r0.L$1 = r15     // Catch:{ all -> 0x005b }
            r0.L$2 = r6     // Catch:{ all -> 0x005b }
            r0.I$0 = r4     // Catch:{ all -> 0x005b }
            r0.label = r3     // Catch:{ all -> 0x005b }
            java.lang.Object r14 = parseHeaders(r5, r2, r14, r0)     // Catch:{ all -> 0x005b }
            if (r14 != r1) goto L_0x00ce
            return r1
        L_0x00ce:
            r8 = r15
            r0 = r2
            r9 = r4
            r10 = r6
            r15 = r14
        L_0x00d3:
            io.ktor.http.cio.HttpHeadersMap r15 = (io.ktor.http.cio.HttpHeadersMap) r15     // Catch:{ all -> 0x0040 }
            if (r15 != 0) goto L_0x00de
            io.ktor.http.cio.HttpHeadersMap r14 = new io.ktor.http.cio.HttpHeadersMap     // Catch:{ all -> 0x0040 }
            r14.<init>(r0)     // Catch:{ all -> 0x0040 }
            r11 = r14
            goto L_0x00df
        L_0x00de:
            r11 = r15
        L_0x00df:
            io.ktor.http.cio.Response r14 = new io.ktor.http.cio.Response     // Catch:{ all -> 0x0040 }
            r7 = r14
            r12 = r0
            r7.<init>(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0040 }
            return r14
        L_0x00e7:
            r14 = move-exception
            r0 = r15
        L_0x00e9:
            r0.release()
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.HttpParserKt.parseResponse(io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object parseHeaders(io.ktor.utils.io.ByteReadChannel r8, kotlin.coroutines.Continuation<? super io.ktor.http.cio.HttpHeadersMap> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.http.cio.HttpParserKt$parseHeaders$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.http.cio.HttpParserKt$parseHeaders$1 r0 = (io.ktor.http.cio.HttpParserKt$parseHeaders$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.http.cio.HttpParserKt$parseHeaders$1 r0 = new io.ktor.http.cio.HttpParserKt$parseHeaders$1
            r0.<init>(r9)
        L_0x0019:
            r4 = r0
            java.lang.Object r9 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r2 = 1
            if (r1 == 0) goto L_0x0037
            if (r1 != r2) goto L_0x002f
            java.lang.Object r8 = r4.L$0
            io.ktor.http.cio.internals.CharArrayBuilder r8 = (io.ktor.http.cio.internals.CharArrayBuilder) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0053
        L_0x002f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r9)
            io.ktor.http.cio.internals.CharArrayBuilder r9 = new io.ktor.http.cio.internals.CharArrayBuilder
            r1 = 0
            r9.<init>(r1, r2, r1)
            r4.L$0 = r9
            r4.label = r2
            r3 = 0
            r5 = 4
            r6 = 0
            r1 = r8
            r2 = r9
            java.lang.Object r8 = parseHeaders$default(r1, r2, r3, r4, r5, r6)
            if (r8 != r0) goto L_0x0050
            return r0
        L_0x0050:
            r7 = r9
            r9 = r8
            r8 = r7
        L_0x0053:
            io.ktor.http.cio.HttpHeadersMap r9 = (io.ktor.http.cio.HttpHeadersMap) r9
            if (r9 != 0) goto L_0x005c
            io.ktor.http.cio.HttpHeadersMap r9 = new io.ktor.http.cio.HttpHeadersMap
            r9.<init>(r8)
        L_0x005c:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.HttpParserKt.parseHeaders(io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0077 A[Catch:{ all -> 0x00e7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0089 A[Catch:{ all -> 0x00e7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object parseHeaders(io.ktor.utils.io.ByteReadChannel r18, io.ktor.http.cio.internals.CharArrayBuilder r19, io.ktor.http.cio.internals.MutableRange r20, kotlin.coroutines.Continuation<? super io.ktor.http.cio.HttpHeadersMap> r21) {
        /*
            r0 = r21
            boolean r1 = r0 instanceof io.ktor.http.cio.HttpParserKt$parseHeaders$2
            if (r1 == 0) goto L_0x0016
            r1 = r0
            io.ktor.http.cio.HttpParserKt$parseHeaders$2 r1 = (io.ktor.http.cio.HttpParserKt$parseHeaders$2) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            io.ktor.http.cio.HttpParserKt$parseHeaders$2 r1 = new io.ktor.http.cio.HttpParserKt$parseHeaders$2
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 8192(0x2000, float:1.14794E-41)
            r5 = 1
            if (r3 == 0) goto L_0x0053
            if (r3 != r5) goto L_0x004b
            java.lang.Object r3 = r1.L$3
            io.ktor.http.cio.HttpHeadersMap r3 = (io.ktor.http.cio.HttpHeadersMap) r3
            java.lang.Object r6 = r1.L$2
            io.ktor.http.cio.internals.MutableRange r6 = (io.ktor.http.cio.internals.MutableRange) r6
            java.lang.Object r7 = r1.L$1
            io.ktor.http.cio.internals.CharArrayBuilder r7 = (io.ktor.http.cio.internals.CharArrayBuilder) r7
            java.lang.Object r8 = r1.L$0
            io.ktor.utils.io.ByteReadChannel r8 = (io.ktor.utils.io.ByteReadChannel) r8
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0048 }
            r16 = r6
            r6 = r1
            r1 = r16
            r17 = r7
            r7 = r3
            r3 = r17
            goto L_0x007c
        L_0x0048:
            r0 = move-exception
            goto L_0x00e9
        L_0x004b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0053:
            kotlin.ResultKt.throwOnFailure(r0)
            io.ktor.http.cio.HttpHeadersMap r0 = new io.ktor.http.cio.HttpHeadersMap
            r3 = r19
            r0.<init>(r3)
            r7 = r0
            r6 = r1
            r0 = r18
            r1 = r20
        L_0x0063:
            r8 = r3
            java.lang.Appendable r8 = (java.lang.Appendable) r8     // Catch:{ all -> 0x00e7 }
            r6.L$0 = r0     // Catch:{ all -> 0x00e7 }
            r6.L$1 = r3     // Catch:{ all -> 0x00e7 }
            r6.L$2 = r1     // Catch:{ all -> 0x00e7 }
            r6.L$3 = r7     // Catch:{ all -> 0x00e7 }
            r6.label = r5     // Catch:{ all -> 0x00e7 }
            java.lang.Object r8 = r0.readUTF8LineTo(r8, r4, r6)     // Catch:{ all -> 0x00e7 }
            if (r8 != r2) goto L_0x0077
            return r2
        L_0x0077:
            r16 = r8
            r8 = r0
            r0 = r16
        L_0x007c:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x00e7 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x00e7 }
            if (r0 != 0) goto L_0x0089
            r7.release()     // Catch:{ all -> 0x00e7 }
            r0 = 0
            return r0
        L_0x0089:
            int r0 = r3.length()     // Catch:{ all -> 0x00e7 }
            r1.setEnd(r0)     // Catch:{ all -> 0x00e7 }
            int r0 = r1.getEnd()     // Catch:{ all -> 0x00e7 }
            int r9 = r1.getStart()     // Catch:{ all -> 0x00e7 }
            int r0 = r0 - r9
            if (r0 == 0) goto L_0x00d7
            if (r0 >= r4) goto L_0x00cb
            int r12 = r1.getStart()     // Catch:{ all -> 0x00e7 }
            int r13 = parseHeaderName(r3, r1)     // Catch:{ all -> 0x00e7 }
            r0 = r3
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch:{ all -> 0x00e7 }
            int r10 = io.ktor.http.cio.internals.CharsKt.hashCodeLowerCase(r0, r12, r13)     // Catch:{ all -> 0x00e7 }
            int r0 = r1.getEnd()     // Catch:{ all -> 0x00e7 }
            parseHeaderValue(r3, r1)     // Catch:{ all -> 0x00e7 }
            int r14 = r1.getStart()     // Catch:{ all -> 0x00e7 }
            int r15 = r1.getEnd()     // Catch:{ all -> 0x00e7 }
            r9 = r3
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ all -> 0x00e7 }
            int r11 = io.ktor.http.cio.internals.CharsKt.hashCodeLowerCase(r9, r14, r15)     // Catch:{ all -> 0x00e7 }
            r1.setStart(r0)     // Catch:{ all -> 0x00e7 }
            r9 = r7
            r9.put(r10, r11, r12, r13, r14, r15)     // Catch:{ all -> 0x00e7 }
            r0 = r8
            goto L_0x0063
        L_0x00cb:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00e7 }
            java.lang.String r1 = "Header line length limit exceeded"
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00e7 }
            r0.<init>(r1)     // Catch:{ all -> 0x00e7 }
            throw r0     // Catch:{ all -> 0x00e7 }
        L_0x00d7:
            io.ktor.http.HttpHeaders r0 = io.ktor.http.HttpHeaders.INSTANCE     // Catch:{ all -> 0x00e7 }
            java.lang.String r0 = r0.getHost()     // Catch:{ all -> 0x00e7 }
            java.lang.CharSequence r0 = r7.get(r0)     // Catch:{ all -> 0x00e7 }
            if (r0 == 0) goto L_0x00e6
            validateHostHeader(r0)     // Catch:{ all -> 0x00e7 }
        L_0x00e6:
            return r7
        L_0x00e7:
            r0 = move-exception
            r3 = r7
        L_0x00e9:
            r3.release()
            goto L_0x00ee
        L_0x00ed:
            throw r0
        L_0x00ee:
            goto L_0x00ed
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.HttpParserKt.parseHeaders(io.ktor.utils.io.ByteReadChannel, io.ktor.http.cio.internals.CharArrayBuilder, io.ktor.http.cio.internals.MutableRange, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object parseHeaders$default(ByteReadChannel byteReadChannel, CharArrayBuilder charArrayBuilder, MutableRange mutableRange, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            mutableRange = new MutableRange(0, 0);
        }
        return parseHeaders(byteReadChannel, charArrayBuilder, mutableRange, continuation);
    }

    private static final void validateHostHeader(CharSequence charSequence) {
        int i = 0;
        if (!StringsKt.endsWith$default(charSequence, (CharSequence) ":", false, 2, (Object) null)) {
            while (i < charSequence.length()) {
                char charAt = charSequence.charAt(i);
                Set<Character> set = hostForbiddenSymbols;
                if (!set.contains(Character.valueOf(charAt))) {
                    i++;
                } else {
                    throw new ParserException("Host cannot contain any of the following symbols: " + set);
                }
            }
            return;
        }
        throw new ParserException("Host header with ':' should contains port: " + charSequence);
    }

    private static final HttpMethod parseHttpMethod(CharSequence charSequence, MutableRange mutableRange) {
        TokenizerKt.skipSpaces(charSequence, mutableRange);
        HttpMethod httpMethod = (HttpMethod) CollectionsKt.singleOrNull(AsciiCharTree.search$default(CharsKt.getDefaultHttpMethods(), charSequence, mutableRange.getStart(), mutableRange.getEnd(), false, HttpParserKt$parseHttpMethod$exact$1.INSTANCE, 8, (Object) null));
        if (httpMethod == null) {
            return parseHttpMethodFull(charSequence, mutableRange);
        }
        mutableRange.setStart(mutableRange.getStart() + httpMethod.getValue().length());
        return httpMethod;
    }

    private static final HttpMethod parseHttpMethodFull(CharSequence charSequence, MutableRange mutableRange) {
        return new HttpMethod(TokenizerKt.nextToken(charSequence, mutableRange).toString());
    }

    private static final CharSequence parseUri(CharSequence charSequence, MutableRange mutableRange) {
        TokenizerKt.skipSpaces(charSequence, mutableRange);
        int start = mutableRange.getStart();
        int findSpaceOrEnd = TokenizerKt.findSpaceOrEnd(charSequence, mutableRange);
        int i = findSpaceOrEnd - start;
        if (i <= 0) {
            return "";
        }
        if (i == 1 && charSequence.charAt(start) == '/') {
            mutableRange.setStart(findSpaceOrEnd);
            return "/";
        }
        CharSequence subSequence = charSequence.subSequence(start, findSpaceOrEnd);
        mutableRange.setStart(findSpaceOrEnd);
        return subSequence;
    }

    private static final CharSequence parseVersion(CharSequence charSequence, MutableRange mutableRange) {
        TokenizerKt.skipSpaces(charSequence, mutableRange);
        if (mutableRange.getStart() < mutableRange.getEnd()) {
            String str = (String) CollectionsKt.singleOrNull(AsciiCharTree.search$default(versions, charSequence, mutableRange.getStart(), mutableRange.getEnd(), false, HttpParserKt$parseVersion$exact$1.INSTANCE, 8, (Object) null));
            if (str != null) {
                mutableRange.setStart(mutableRange.getStart() + str.length());
                return str;
            }
            unsupportedHttpVersion(TokenizerKt.nextToken(charSequence, mutableRange));
            throw new KotlinNothingValueException();
        }
        throw new IllegalStateException(("Failed to parse version: " + charSequence).toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0077, code lost:
        r7.setStart(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007a, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final int parseStatusCode(java.lang.CharSequence r6, io.ktor.http.cio.internals.MutableRange r7) {
        /*
            io.ktor.http.cio.internals.TokenizerKt.skipSpaces(r6, r7)
            int r0 = r7.getEnd()
            int r1 = r7.getStart()
            int r2 = r7.getEnd()
            r3 = 0
        L_0x0010:
            if (r1 >= r2) goto L_0x0077
            char r4 = r6.charAt(r1)
            r5 = 32
            if (r4 != r5) goto L_0x003b
            boolean r6 = statusOutOfRange(r3)
            if (r6 != 0) goto L_0x0022
            r0 = r1
            goto L_0x0077
        L_0x0022:
            io.ktor.http.cio.ParserException r6 = new io.ktor.http.cio.ParserException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "Status-code must be 3-digit. Status received: "
            r7.<init>(r0)
            r7.append(r3)
            r0 = 46
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L_0x003b:
            r5 = 48
            if (r5 > r4) goto L_0x004b
            r5 = 58
            if (r4 >= r5) goto L_0x004b
            int r3 = r3 * 10
            int r4 = r4 + -48
            int r3 = r3 + r4
            int r1 = r1 + 1
            goto L_0x0010
        L_0x004b:
            int r0 = r7.getStart()
            int r7 = io.ktor.http.cio.internals.TokenizerKt.findSpaceOrEnd(r6, r7)
            java.lang.CharSequence r6 = r6.subSequence(r0, r7)
            java.lang.String r6 = r6.toString()
            java.lang.NumberFormatException r7 = new java.lang.NumberFormatException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Illegal digit "
            r0.<init>(r1)
            r0.append(r4)
            java.lang.String r1 = " in status code "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            r7.<init>(r6)
            throw r7
        L_0x0077:
            r7.setStart(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.HttpParserKt.parseStatusCode(java.lang.CharSequence, io.ktor.http.cio.internals.MutableRange):int");
    }

    public static final int parseHeaderName(CharArrayBuilder charArrayBuilder, MutableRange mutableRange) {
        Intrinsics.checkNotNullParameter(charArrayBuilder, "text");
        Intrinsics.checkNotNullParameter(mutableRange, "range");
        int start = mutableRange.getStart();
        int end = mutableRange.getEnd();
        while (start < end) {
            char charAt = charArrayBuilder.charAt(start);
            if (charAt == ':' && start != mutableRange.getStart()) {
                mutableRange.setStart(start + 1);
                return start;
            } else if (!isDelimiter(charAt)) {
                start++;
            } else {
                parseHeaderNameFailed(charArrayBuilder, start, mutableRange.getStart(), charAt);
                throw new KotlinNothingValueException();
            }
        }
        noColonFound(charArrayBuilder, mutableRange);
        throw new KotlinNothingValueException();
    }

    private static final Void parseHeaderNameFailed(CharArrayBuilder charArrayBuilder, int i, int i2, char c) {
        if (c == ':') {
            throw new ParserException("Empty header names are not allowed as per RFC7230.");
        } else if (i == i2) {
            throw new ParserException("Multiline headers via line folding is not supported since it is deprecated as per RFC7230.");
        } else {
            characterIsNotAllowed(charArrayBuilder, c);
            throw new KotlinNothingValueException();
        }
    }

    public static final void parseHeaderValue(CharArrayBuilder charArrayBuilder, MutableRange mutableRange) {
        Intrinsics.checkNotNullParameter(charArrayBuilder, "text");
        Intrinsics.checkNotNullParameter(mutableRange, "range");
        int start = mutableRange.getStart();
        int end = mutableRange.getEnd();
        int skipSpacesAndHorizontalTabs = TokenizerKt.skipSpacesAndHorizontalTabs(charArrayBuilder, start, end);
        if (skipSpacesAndHorizontalTabs >= end) {
            mutableRange.setStart(end);
            return;
        }
        int i = skipSpacesAndHorizontalTabs;
        int i2 = i;
        while (i < end) {
            char charAt = charArrayBuilder.charAt(i);
            if (!(charAt == 9 || charAt == ' ')) {
                if (charAt == 13 || charAt == 10) {
                    characterIsNotAllowed(charArrayBuilder, charAt);
                    throw new KotlinNothingValueException();
                }
                i2 = i;
            }
            i++;
        }
        mutableRange.setStart(skipSpacesAndHorizontalTabs);
        mutableRange.setEnd(i2 + 1);
    }

    private static final Void noColonFound(CharSequence charSequence, MutableRange mutableRange) {
        throw new ParserException("No colon in HTTP header in " + charSequence.subSequence(mutableRange.getStart(), mutableRange.getEnd()).toString() + " in builder: \n" + charSequence);
    }

    private static final Void characterIsNotAllowed(CharSequence charSequence, char c) {
        throw new ParserException("Character with code " + (c & 255) + " is not allowed in header names, \n" + charSequence);
    }

    private static final boolean isDelimiter(char c) {
        return Intrinsics.compare((int) c, 32) <= 0 || StringsKt.contains$default((CharSequence) "\"(),/:;<=>?@[\\]{}", c, false, 2, (Object) null);
    }

    private static final Void unsupportedHttpVersion(CharSequence charSequence) {
        throw new ParserException("Unsupported HTTP version: " + charSequence);
    }
}
