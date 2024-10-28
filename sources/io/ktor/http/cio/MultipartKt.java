package io.ktor.http.cio;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.http.cio.internals.CharsKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.LookAheadSession;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CharCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

@Metadata(d1 = {"\u0000\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a!\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001aW\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\"\u0010\u000f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u00102\b\b\u0002\u0010\u0014\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u0010\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0018H\u0007\u001a\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002\u001a\u0010\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u001cH\u0007\u001a\u0010\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u001cH\u0000\u001a?\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\f0 2\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010!\u001a\u00020\"2\b\b\u0002\u0010\u0014\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010#\u001a;\u0010$\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0014\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010%\u001a;\u0010&\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0014\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010%\u001a\u0019\u0010'\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0002\u0010(\u001a\u0019\u0010)\u001a\u00020\u00182\u0006\u0010\b\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0002\u0010(\u001a3\u0010*\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010!\u001a\u00020+2\b\b\u0002\u0010\u0014\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010,\u001a3\u0010-\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010!\u001a\u00020+2\b\b\u0002\u0010\u0014\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010,\u001a!\u0010.\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\u0014\u0010/\u001a\u00020\u001a*\u00020\u00012\u0006\u00100\u001a\u00020\u0001H\u0002\u001a \u00101\u001a\b\u0012\u0004\u0012\u00020302*\u0002042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0018\u001a/\u00101\u001a\b\u0012\u0004\u0012\u00020302*\u0002042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u00105\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u00106\u001a1\u00101\u001a\b\u0012\u0004\u0012\u00020302*\u0002042\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\b\u00107\u001a\u0004\u0018\u00010\fH\u0007¢\u0006\u0002\u00108\u001a\u001d\u00109\u001a\u00020\u0006*\u00020\t2\u0006\u0010:\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010;\u001a\u001e\u0010<\u001a\u00020\u0006*\u00020\u00012\u0006\u0010=\u001a\u00020\u00012\b\b\u0002\u0010>\u001a\u00020\u001aH\u0002\u001a\u0014\u0010?\u001a\u00020\u001a*\u00020@2\u0006\u0010:\u001a\u00020\u0001H\u0002\u001a\u0014\u0010A\u001a\u00020\u001a*\u00020@2\u0006\u0010:\u001a\u00020\u0001H\u0002\u001a\u001d\u0010B\u001a\u00020\u0006*\u00020\t2\u0006\u0010:\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010;\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006C"}, d2 = {"BoundaryTrailingBuffer", "Ljava/nio/ByteBuffer;", "CrLf", "PrefixChar", "", "boundary", "", "boundaryPrefixed", "input", "Lio/ktor/utils/io/ByteReadChannel;", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/ByteReadChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyUntilBoundary", "", "name", "", "writeFully", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "limit", "(Ljava/lang/String;Ljava/nio/ByteBuffer;Lio/ktor/utils/io/ByteReadChannel;Lkotlin/jvm/functions/Function2;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expectMultipart", "headers", "Lio/ktor/http/cio/HttpHeadersMap;", "findBoundary", "", "contentType", "", "parseBoundary", "parseBoundaryInternal", "parsePart", "Lkotlin/Pair;", "output", "Lio/ktor/utils/io/ByteWriteChannel;", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parsePartBody", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/http/cio/HttpHeadersMap;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parsePartBodyImpl", "parsePartHeaders", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parsePartHeadersImpl", "parsePreamble", "Lio/ktor/utils/io/core/BytePacketBuilder;", "(Ljava/nio/ByteBuffer;Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/core/BytePacketBuilder;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parsePreambleImpl", "skipBoundary", "indexOfPartial", "sub", "parseMultipart", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lio/ktor/http/cio/MultipartEvent;", "Lkotlinx/coroutines/CoroutineScope;", "contentLength", "(Lkotlinx/coroutines/CoroutineScope;Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/CharSequence;Ljava/lang/Long;)Lkotlinx/coroutines/channels/ReceiveChannel;", "totalLength", "(Lkotlinx/coroutines/CoroutineScope;Ljava/nio/ByteBuffer;Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/Long;)Lkotlinx/coroutines/channels/ReceiveChannel;", "skipDelimiterOrEof", "delimiter", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startsWith", "prefix", "prefixSkip", "startsWithDelimiter", "Lio/ktor/utils/io/LookAheadSession;", "tryEnsureDelimiter", "trySkipDelimiterSuspend", "ktor-http-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Multipart.kt */
public final class MultipartKt {
    /* access modifiers changed from: private */
    public static final ByteBuffer BoundaryTrailingBuffer;
    /* access modifiers changed from: private */
    public static final ByteBuffer CrLf;
    private static final byte PrefixChar = 45;

    public static /* synthetic */ Object parsePreamble$default(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, BytePacketBuilder bytePacketBuilder, long j, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            j = Long.MAX_VALUE;
        }
        return parsePreamble(byteBuffer, byteReadChannel, bytePacketBuilder, j, continuation);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This is going to be removed. Use parseMultipart instead.")
    public static final Object parsePreamble(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, BytePacketBuilder bytePacketBuilder, long j, Continuation<? super Long> continuation) {
        return parsePreambleImpl(byteBuffer, byteReadChannel, bytePacketBuilder, j, continuation);
    }

    static /* synthetic */ Object parsePreambleImpl$default(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, BytePacketBuilder bytePacketBuilder, long j, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            j = Long.MAX_VALUE;
        }
        return parsePreambleImpl(byteBuffer, byteReadChannel, bytePacketBuilder, j, continuation);
    }

    /* access modifiers changed from: private */
    public static final Object parsePreambleImpl(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, BytePacketBuilder bytePacketBuilder, long j, Continuation<? super Long> continuation) {
        return copyUntilBoundary("preamble/prologue", byteBuffer, byteReadChannel, new MultipartKt$parsePreambleImpl$2(bytePacketBuilder, (Continuation<? super MultipartKt$parsePreambleImpl$2>) null), j, continuation);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: io.ktor.utils.io.ByteWriteChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: io.ktor.utils.io.ByteReadChannel} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007b A[Catch:{ all -> 0x0032 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.ERROR, message = "This is going to be removed. Use parseMultipart instead.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object parsePart(java.nio.ByteBuffer r8, io.ktor.utils.io.ByteReadChannel r9, io.ktor.utils.io.ByteWriteChannel r10, long r11, kotlin.coroutines.Continuation<? super kotlin.Pair<io.ktor.http.cio.HttpHeadersMap, java.lang.Long>> r13) {
        /*
            boolean r0 = r13 instanceof io.ktor.http.cio.MultipartKt$parsePart$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            io.ktor.http.cio.MultipartKt$parsePart$1 r0 = (io.ktor.http.cio.MultipartKt$parsePart$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            io.ktor.http.cio.MultipartKt$parsePart$1 r0 = new io.ktor.http.cio.MultipartKt$parsePart$1
            r0.<init>(r13)
        L_0x0019:
            r7 = r0
            java.lang.Object r13 = r7.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0050
            if (r1 == r3) goto L_0x003c
            if (r1 != r2) goto L_0x0034
            java.lang.Object r8 = r7.L$0
            io.ktor.http.cio.HttpHeadersMap r8 = (io.ktor.http.cio.HttpHeadersMap) r8
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x0032 }
            goto L_0x007c
        L_0x0032:
            r9 = move-exception
            goto L_0x008c
        L_0x0034:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x003c:
            long r11 = r7.J$0
            java.lang.Object r8 = r7.L$2
            r10 = r8
            io.ktor.utils.io.ByteWriteChannel r10 = (io.ktor.utils.io.ByteWriteChannel) r10
            java.lang.Object r8 = r7.L$1
            r9 = r8
            io.ktor.utils.io.ByteReadChannel r9 = (io.ktor.utils.io.ByteReadChannel) r9
            java.lang.Object r8 = r7.L$0
            java.nio.ByteBuffer r8 = (java.nio.ByteBuffer) r8
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0064
        L_0x0050:
            kotlin.ResultKt.throwOnFailure(r13)
            r7.L$0 = r8
            r7.L$1 = r9
            r7.L$2 = r10
            r7.J$0 = r11
            r7.label = r3
            java.lang.Object r13 = parsePartHeadersImpl(r9, r7)
            if (r13 != r0) goto L_0x0064
            return r0
        L_0x0064:
            r1 = r8
            r3 = r10
            r5 = r11
            r8 = r13
            io.ktor.http.cio.HttpHeadersMap r8 = (io.ktor.http.cio.HttpHeadersMap) r8
            r7.L$0 = r8     // Catch:{ all -> 0x0032 }
            r10 = 0
            r7.L$1 = r10     // Catch:{ all -> 0x0032 }
            r7.L$2 = r10     // Catch:{ all -> 0x0032 }
            r7.label = r2     // Catch:{ all -> 0x0032 }
            r2 = r9
            r4 = r8
            java.lang.Object r13 = parsePartBodyImpl(r1, r2, r3, r4, r5, r7)     // Catch:{ all -> 0x0032 }
            if (r13 != r0) goto L_0x007c
            return r0
        L_0x007c:
            java.lang.Number r13 = (java.lang.Number) r13     // Catch:{ all -> 0x0032 }
            long r9 = r13.longValue()     // Catch:{ all -> 0x0032 }
            kotlin.Pair r11 = new kotlin.Pair     // Catch:{ all -> 0x0032 }
            java.lang.Long r9 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r9)     // Catch:{ all -> 0x0032 }
            r11.<init>(r8, r9)     // Catch:{ all -> 0x0032 }
            return r11
        L_0x008c:
            r8.release()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt.parsePart(java.nio.ByteBuffer, io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object parsePart$default(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, long j, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            j = Long.MAX_VALUE;
        }
        return parsePart(byteBuffer, byteReadChannel, byteWriteChannel, j, continuation);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This is going to be removed. Use parseMultipart instead.")
    public static final Object parsePartHeaders(ByteReadChannel byteReadChannel, Continuation<? super HttpHeadersMap> continuation) {
        return parsePartHeadersImpl(byteReadChannel, continuation);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0059 A[Catch:{ all -> 0x002f }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005a A[Catch:{ all -> 0x002f }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object parsePartHeadersImpl(io.ktor.utils.io.ByteReadChannel r8, kotlin.coroutines.Continuation<? super io.ktor.http.cio.HttpHeadersMap> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.http.cio.MultipartKt$parsePartHeadersImpl$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.http.cio.MultipartKt$parsePartHeadersImpl$1 r0 = (io.ktor.http.cio.MultipartKt$parsePartHeadersImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.http.cio.MultipartKt$parsePartHeadersImpl$1 r0 = new io.ktor.http.cio.MultipartKt$parsePartHeadersImpl$1
            r0.<init>(r9)
        L_0x0019:
            r4 = r0
            java.lang.Object r9 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r2 = 1
            if (r1 == 0) goto L_0x0039
            if (r1 != r2) goto L_0x0031
            java.lang.Object r8 = r4.L$0
            io.ktor.http.cio.internals.CharArrayBuilder r8 = (io.ktor.http.cio.internals.CharArrayBuilder) r8
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ all -> 0x002f }
            goto L_0x0055
        L_0x002f:
            r9 = move-exception
            goto L_0x0066
        L_0x0031:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r9)
            io.ktor.http.cio.internals.CharArrayBuilder r9 = new io.ktor.http.cio.internals.CharArrayBuilder
            r1 = 0
            r9.<init>(r1, r2, r1)
            r4.L$0 = r9     // Catch:{ all -> 0x0062 }
            r4.label = r2     // Catch:{ all -> 0x0062 }
            r3 = 0
            r5 = 4
            r6 = 0
            r1 = r8
            r2 = r9
            java.lang.Object r8 = io.ktor.http.cio.HttpParserKt.parseHeaders$default(r1, r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0062 }
            if (r8 != r0) goto L_0x0052
            return r0
        L_0x0052:
            r7 = r9
            r9 = r8
            r8 = r7
        L_0x0055:
            io.ktor.http.cio.HttpHeadersMap r9 = (io.ktor.http.cio.HttpHeadersMap) r9     // Catch:{ all -> 0x002f }
            if (r9 == 0) goto L_0x005a
            return r9
        L_0x005a:
            java.io.EOFException r9 = new java.io.EOFException     // Catch:{ all -> 0x002f }
            java.lang.String r0 = "Failed to parse multipart headers: unexpected end of stream"
            r9.<init>(r0)     // Catch:{ all -> 0x002f }
            throw r9     // Catch:{ all -> 0x002f }
        L_0x0062:
            r8 = move-exception
            r7 = r9
            r9 = r8
            r8 = r7
        L_0x0066:
            r8.release()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt.parsePartHeadersImpl(io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object parsePartBody$default(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, HttpHeadersMap httpHeadersMap, long j, Continuation continuation, int i, Object obj) {
        if ((i & 16) != 0) {
            j = Long.MAX_VALUE;
        }
        return parsePartBody(byteBuffer, byteReadChannel, byteWriteChannel, httpHeadersMap, j, continuation);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This is going to be removed. Use parseMultipart instead.")
    public static final Object parsePartBody(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, HttpHeadersMap httpHeadersMap, long j, Continuation<? super Long> continuation) {
        return parsePartBodyImpl(byteBuffer, byteReadChannel, byteWriteChannel, httpHeadersMap, j, continuation);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: io.ktor.utils.io.ByteWriteChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v9, resolved type: io.ktor.utils.io.ByteWriteChannel} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object parsePartBodyImpl(java.nio.ByteBuffer r8, io.ktor.utils.io.ByteReadChannel r9, io.ktor.utils.io.ByteWriteChannel r10, io.ktor.http.cio.HttpHeadersMap r11, long r12, kotlin.coroutines.Continuation<? super java.lang.Long> r14) {
        /*
            boolean r0 = r14 instanceof io.ktor.http.cio.MultipartKt$parsePartBodyImpl$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            io.ktor.http.cio.MultipartKt$parsePartBodyImpl$1 r0 = (io.ktor.http.cio.MultipartKt$parsePartBodyImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            io.ktor.http.cio.MultipartKt$parsePartBodyImpl$1 r0 = new io.ktor.http.cio.MultipartKt$parsePartBodyImpl$1
            r0.<init>(r14)
        L_0x0019:
            r7 = r0
            java.lang.Object r14 = r7.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0045
            if (r1 == r3) goto L_0x003c
            if (r1 != r2) goto L_0x0034
            java.lang.Object r8 = r7.L$0
            r10 = r8
            io.ktor.utils.io.ByteWriteChannel r10 = (io.ktor.utils.io.ByteWriteChannel) r10
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00b4
        L_0x0034:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x003c:
            java.lang.Object r8 = r7.L$0
            r10 = r8
            io.ktor.utils.io.ByteWriteChannel r10 = (io.ktor.utils.io.ByteWriteChannel) r10
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0074
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.String r14 = "Content-Length"
            java.lang.CharSequence r11 = r11.get(r14)
            r14 = 0
            if (r11 == 0) goto L_0x005a
            long r4 = io.ktor.http.cio.internals.CharsKt.parseDecLong(r11)
            java.lang.Long r11 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r4)
            goto L_0x005b
        L_0x005a:
            r11 = r14
        L_0x005b:
            if (r11 == 0) goto L_0x009c
            long r1 = r11.longValue()
            int r8 = (r1 > r12 ? 1 : (r1 == r12 ? 0 : -1))
            if (r8 > 0) goto L_0x007b
            long r11 = r11.longValue()
            r7.L$0 = r10
            r7.label = r3
            java.lang.Object r14 = io.ktor.utils.io.ByteReadChannelJVMKt.copyTo(r9, r10, r11, r7)
            if (r14 != r0) goto L_0x0074
            return r0
        L_0x0074:
            java.lang.Number r14 = (java.lang.Number) r14
            long r8 = r14.longValue()
            goto L_0x00ba
        L_0x007b:
            java.io.IOException r8 = new java.io.IOException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Multipart part content length limit of "
            r9.<init>(r10)
            r9.append(r12)
            java.lang.String r10 = " exceeded (actual size is "
            r9.append(r10)
            r9.append(r11)
            r10 = 41
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x009c:
            io.ktor.http.cio.MultipartKt$parsePartBodyImpl$size$1 r11 = new io.ktor.http.cio.MultipartKt$parsePartBodyImpl$size$1
            r11.<init>(r10, r14)
            r4 = r11
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r7.L$0 = r10
            r7.label = r2
            java.lang.String r1 = "part"
            r2 = r8
            r3 = r9
            r5 = r12
            java.lang.Object r14 = copyUntilBoundary(r1, r2, r3, r4, r5, r7)
            if (r14 != r0) goto L_0x00b4
            return r0
        L_0x00b4:
            java.lang.Number r14 = (java.lang.Number) r14
            long r8 = r14.longValue()
        L_0x00ba:
            r10.flush()
            java.lang.Long r8 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt.parsePartBodyImpl(java.nio.ByteBuffer, io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, io.ktor.http.cio.HttpHeadersMap, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object parsePartBodyImpl$default(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, HttpHeadersMap httpHeadersMap, long j, Continuation continuation, int i, Object obj) {
        if ((i & 16) != 0) {
            j = Long.MAX_VALUE;
        }
        return parsePartBodyImpl(byteBuffer, byteReadChannel, byteWriteChannel, httpHeadersMap, j, continuation);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This is going to be removed. Use parseMultipart instead.")
    public static final Object boundary(ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, Continuation<? super Boolean> continuation) {
        return skipBoundary(byteBuffer, byteReadChannel, continuation);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: io.ktor.utils.io.ByteReadChannel} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object skipBoundary(java.nio.ByteBuffer r5, io.ktor.utils.io.ByteReadChannel r6, kotlin.coroutines.Continuation<? super java.lang.Boolean> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.http.cio.MultipartKt$skipBoundary$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.http.cio.MultipartKt$skipBoundary$1 r0 = (io.ktor.http.cio.MultipartKt$skipBoundary$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.http.cio.MultipartKt$skipBoundary$1 r0 = new io.ktor.http.cio.MultipartKt$skipBoundary$1
            r0.<init>(r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0042
            if (r2 == r4) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r5 = r0.L$0
            kotlin.jvm.internal.Ref$BooleanRef r5 = (kotlin.jvm.internal.Ref.BooleanRef) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0075
        L_0x0031:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0039:
            java.lang.Object r5 = r0.L$0
            r6 = r5
            io.ktor.utils.io.ByteReadChannel r6 = (io.ktor.utils.io.ByteReadChannel) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0050
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = skipDelimiterOrEof(r6, r5, r0)
            if (r7 != r1) goto L_0x0050
            return r1
        L_0x0050:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r5 = r7.booleanValue()
            if (r5 != 0) goto L_0x005d
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r5
        L_0x005d:
            kotlin.jvm.internal.Ref$BooleanRef r5 = new kotlin.jvm.internal.Ref$BooleanRef
            r5.<init>()
            io.ktor.http.cio.MultipartKt$skipBoundary$2 r7 = new io.ktor.http.cio.MultipartKt$skipBoundary$2
            r2 = 0
            r7.<init>(r5, r2)
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r6.lookAheadSuspend(r7, r0)
            if (r6 != r1) goto L_0x0075
            return r1
        L_0x0075:
            boolean r5 = r5.element
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt.skipBoundary(java.nio.ByteBuffer, io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This is going to be removed.")
    public static final boolean expectMultipart(HttpHeadersMap httpHeadersMap) {
        Intrinsics.checkNotNullParameter(httpHeadersMap, "headers");
        CharSequence charSequence = httpHeadersMap.get("Content-Type");
        if (charSequence != null) {
            return StringsKt.startsWith$default(charSequence, (CharSequence) "multipart/", false, 2, (Object) null);
        }
        return false;
    }

    public static final ReceiveChannel<MultipartEvent> parseMultipart(CoroutineScope coroutineScope, ByteReadChannel byteReadChannel, HttpHeadersMap httpHeadersMap) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(byteReadChannel, TvContractCompat.PARAM_INPUT);
        Intrinsics.checkNotNullParameter(httpHeadersMap, "headers");
        CharSequence charSequence = httpHeadersMap.get("Content-Type");
        if (charSequence != null) {
            CharSequence charSequence2 = httpHeadersMap.get("Content-Length");
            return parseMultipart(coroutineScope, byteReadChannel, charSequence, charSequence2 != null ? Long.valueOf(CharsKt.parseDecLong(charSequence2)) : null);
        }
        throw new IOException("Failed to parse multipart: no Content-Type header");
    }

    public static final ReceiveChannel<MultipartEvent> parseMultipart(CoroutineScope coroutineScope, ByteReadChannel byteReadChannel, CharSequence charSequence, Long l) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(byteReadChannel, TvContractCompat.PARAM_INPUT);
        Intrinsics.checkNotNullParameter(charSequence, CMSAttributeTableGenerator.CONTENT_TYPE);
        if (StringsKt.startsWith$default(charSequence, (CharSequence) "multipart/", false, 2, (Object) null)) {
            return parseMultipart(coroutineScope, parseBoundaryInternal(charSequence), byteReadChannel, l);
        }
        throw new IOException("Failed to parse multipart: Content-Type should be multipart/* but it is " + charSequence);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This is going to be removed. Use parseMultipart(contentType) instead.")
    public static final ReceiveChannel<MultipartEvent> parseMultipart(CoroutineScope coroutineScope, ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, Long l) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(byteBuffer, "boundaryPrefixed");
        Intrinsics.checkNotNullParameter(byteReadChannel, TvContractCompat.PARAM_INPUT);
        return ProduceKt.produce$default(coroutineScope, (CoroutineContext) null, 0, new MultipartKt$parseMultipart$1(byteReadChannel, byteBuffer, l, (Continuation<? super MultipartKt$parseMultipart$1>) null), 3, (Object) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v9, resolved type: java.nio.ByteBuffer} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00cc A[Catch:{ all -> 0x0079 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object copyUntilBoundary(java.lang.String r20, java.nio.ByteBuffer r21, io.ktor.utils.io.ByteReadChannel r22, kotlin.jvm.functions.Function2<? super java.nio.ByteBuffer, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r23, long r24, kotlin.coroutines.Continuation<? super java.lang.Long> r26) {
        /*
            r0 = r26
            boolean r1 = r0 instanceof io.ktor.http.cio.MultipartKt$copyUntilBoundary$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            io.ktor.http.cio.MultipartKt$copyUntilBoundary$1 r1 = (io.ktor.http.cio.MultipartKt$copyUntilBoundary$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            io.ktor.http.cio.MultipartKt$copyUntilBoundary$1 r1 = new io.ktor.http.cio.MultipartKt$copyUntilBoundary$1
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x007c
            if (r3 == r5) goto L_0x005c
            if (r3 != r4) goto L_0x0054
            int r3 = r1.I$0
            long r6 = r1.J$1
            long r8 = r1.J$0
            java.lang.Object r10 = r1.L$4
            java.nio.ByteBuffer r10 = (java.nio.ByteBuffer) r10
            java.lang.Object r11 = r1.L$3
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            java.lang.Object r12 = r1.L$2
            io.ktor.utils.io.ByteReadChannel r12 = (io.ktor.utils.io.ByteReadChannel) r12
            java.lang.Object r13 = r1.L$1
            java.nio.ByteBuffer r13 = (java.nio.ByteBuffer) r13
            java.lang.Object r14 = r1.L$0
            java.lang.String r14 = (java.lang.String) r14
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0079 }
            r0 = r14
            r16 = r12
            r12 = r1
            r1 = r13
            r13 = r10
            r9 = r8
            r8 = r11
            r11 = r16
            goto L_0x00f5
        L_0x0054:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x005c:
            long r6 = r1.J$1
            long r8 = r1.J$0
            java.lang.Object r3 = r1.L$4
            r10 = r3
            java.nio.ByteBuffer r10 = (java.nio.ByteBuffer) r10
            java.lang.Object r3 = r1.L$3
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
            java.lang.Object r11 = r1.L$2
            io.ktor.utils.io.ByteReadChannel r11 = (io.ktor.utils.io.ByteReadChannel) r11
            java.lang.Object r12 = r1.L$1
            java.nio.ByteBuffer r12 = (java.nio.ByteBuffer) r12
            java.lang.Object r13 = r1.L$0
            java.lang.String r13 = (java.lang.String) r13
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0079 }
            goto L_0x00c4
        L_0x0079:
            r0 = move-exception
            goto L_0x013a
        L_0x007c:
            kotlin.ResultKt.throwOnFailure(r0)
            io.ktor.utils.io.pool.ObjectPool r0 = io.ktor.network.util.PoolsKt.getDefaultByteBufferPool()
            java.lang.Object r0 = r0.borrow()
            java.nio.ByteBuffer r0 = (java.nio.ByteBuffer) r0
            r6 = 0
            r3 = r22
            r12 = r0
            r9 = r1
            r10 = r6
            r0 = r20
            r1 = r21
            r6 = r23
            r7 = r24
        L_0x0098:
            r12.clear()     // Catch:{ all -> 0x0138 }
            r9.L$0 = r0     // Catch:{ all -> 0x0138 }
            r9.L$1 = r1     // Catch:{ all -> 0x0138 }
            r9.L$2 = r3     // Catch:{ all -> 0x0138 }
            r9.L$3 = r6     // Catch:{ all -> 0x0138 }
            r9.L$4 = r12     // Catch:{ all -> 0x0138 }
            r9.J$0 = r7     // Catch:{ all -> 0x0138 }
            r9.J$1 = r10     // Catch:{ all -> 0x0138 }
            r9.label = r5     // Catch:{ all -> 0x0138 }
            java.lang.Object r13 = io.ktor.utils.io.DelimitedKt.readUntilDelimiter(r3, r1, r12, r9)     // Catch:{ all -> 0x0138 }
            if (r13 != r2) goto L_0x00b2
            return r2
        L_0x00b2:
            r16 = r13
            r13 = r0
            r0 = r16
            r17 = r12
            r12 = r1
            r1 = r9
            r8 = r7
            r18 = r10
            r11 = r3
            r3 = r6
            r6 = r18
            r10 = r17
        L_0x00c4:
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ all -> 0x0079 }
            int r0 = r0.intValue()     // Catch:{ all -> 0x0079 }
            if (r0 <= 0) goto L_0x012c
            r10.flip()     // Catch:{ all -> 0x0079 }
            r1.L$0 = r13     // Catch:{ all -> 0x0079 }
            r1.L$1 = r12     // Catch:{ all -> 0x0079 }
            r1.L$2 = r11     // Catch:{ all -> 0x0079 }
            r1.L$3 = r3     // Catch:{ all -> 0x0079 }
            r1.L$4 = r10     // Catch:{ all -> 0x0079 }
            r1.J$0 = r8     // Catch:{ all -> 0x0079 }
            r1.J$1 = r6     // Catch:{ all -> 0x0079 }
            r1.I$0 = r0     // Catch:{ all -> 0x0079 }
            r1.label = r4     // Catch:{ all -> 0x0079 }
            java.lang.Object r14 = r3.invoke(r10, r1)     // Catch:{ all -> 0x0079 }
            if (r14 != r2) goto L_0x00e8
            return r2
        L_0x00e8:
            r16 = r3
            r3 = r0
            r0 = r13
            r13 = r10
            r9 = r8
            r8 = r16
            r17 = r12
            r12 = r1
            r1 = r17
        L_0x00f5:
            long r14 = (long) r3
            long r6 = r6 + r14
            int r3 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r3 > 0) goto L_0x0105
            r3 = r11
            r16 = r6
            r6 = r8
            r7 = r9
            r9 = r12
            r12 = r13
            r10 = r16
            goto L_0x0098
        L_0x0105:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ all -> 0x0129 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0129 }
            r2.<init>()     // Catch:{ all -> 0x0129 }
            java.lang.String r3 = "Multipart "
            r2.append(r3)     // Catch:{ all -> 0x0129 }
            r2.append(r0)     // Catch:{ all -> 0x0129 }
            java.lang.String r0 = " limit of "
            r2.append(r0)     // Catch:{ all -> 0x0129 }
            r2.append(r9)     // Catch:{ all -> 0x0129 }
            java.lang.String r0 = " bytes exceeded"
            r2.append(r0)     // Catch:{ all -> 0x0129 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0129 }
            r1.<init>(r0)     // Catch:{ all -> 0x0129 }
            throw r1     // Catch:{ all -> 0x0129 }
        L_0x0129:
            r0 = move-exception
            r10 = r13
            goto L_0x013a
        L_0x012c:
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r6)     // Catch:{ all -> 0x0079 }
            io.ktor.utils.io.pool.ObjectPool r1 = io.ktor.network.util.PoolsKt.getDefaultByteBufferPool()
            r1.recycle(r10)
            return r0
        L_0x0138:
            r0 = move-exception
            r10 = r12
        L_0x013a:
            io.ktor.utils.io.pool.ObjectPool r1 = io.ktor.network.util.PoolsKt.getDefaultByteBufferPool()
            r1.recycle(r10)
            goto L_0x0143
        L_0x0142:
            throw r0
        L_0x0143:
            goto L_0x0142
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt.copyUntilBoundary(java.lang.String, java.nio.ByteBuffer, io.ktor.utils.io.ByteReadChannel, kotlin.jvm.functions.Function2, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object copyUntilBoundary$default(String str, ByteBuffer byteBuffer, ByteReadChannel byteReadChannel, Function2 function2, long j, Continuation continuation, int i, Object obj) {
        if ((i & 16) != 0) {
            j = Long.MAX_VALUE;
        }
        return copyUntilBoundary(str, byteBuffer, byteReadChannel, function2, j, continuation);
    }

    private static final int findBoundary(CharSequence charSequence) {
        int length = charSequence.length();
        char c = 0;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = charSequence.charAt(i2);
            if (c != 0) {
                if (c != 1) {
                    if (c != 2) {
                        if (c != 3) {
                            if (c != 4) {
                            }
                        } else if (charAt != '\"') {
                            if (charAt == '\\') {
                                c = 4;
                            }
                        }
                    } else if (charAt != '\"') {
                        if (charAt != ',') {
                            if (charAt != ';') {
                            }
                        }
                    }
                    c = 3;
                } else {
                    if (charAt == '=') {
                        c = 2;
                    } else {
                        if (charAt != ';') {
                            if (charAt != ',') {
                                if (charAt == ' ') {
                                    continue;
                                } else if (i == 0 && StringsKt.startsWith(charSequence, "boundary=", i2, true)) {
                                    return i2;
                                } else {
                                    i++;
                                }
                            }
                        }
                        i = 0;
                    }
                }
                c = 0;
            } else if (charAt != ';') {
            }
            c = 1;
            i = 0;
        }
        return -1;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This is going to become internal. Use parseMultipart instead or file a ticket explaining why do you need this function.")
    public static final ByteBuffer parseBoundary(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, CMSAttributeTableGenerator.CONTENT_TYPE);
        return parseBoundaryInternal(charSequence);
    }

    public static final ByteBuffer parseBoundaryInternal(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, CMSAttributeTableGenerator.CONTENT_TYPE);
        int findBoundary = findBoundary(charSequence);
        if (findBoundary != -1) {
            int i = findBoundary + 9;
            ByteBuffer allocate = ByteBuffer.allocate(74);
            Intrinsics.checkNotNullExpressionValue(allocate, "allocate(74)");
            allocate.put((byte) 13);
            allocate.put((byte) 10);
            allocate.put(PrefixChar);
            allocate.put(PrefixChar);
            int length = charSequence.length();
            char c = 0;
            while (i < length) {
                char charAt = charSequence.charAt(i);
                char c2 = charAt & CharCompanionObject.MAX_VALUE;
                if ((65535 & charAt) <= 127) {
                    if (c != 0) {
                        if (c == 1) {
                            if (charAt == ' ' || charAt == ',' || charAt == ';') {
                                break;
                            } else if (allocate.hasRemaining()) {
                                allocate.put((byte) c2);
                                i++;
                            } else {
                                throw new IOException("Failed to parse multipart: boundary shouldn't be longer than 70 characters");
                            }
                        } else {
                            if (c != 2) {
                                if (c != 3) {
                                    continue;
                                } else if (allocate.hasRemaining()) {
                                    allocate.put((byte) c2);
                                } else {
                                    throw new IOException("Failed to parse multipart: boundary shouldn't be longer than 70 characters");
                                }
                            } else if (charAt == '\\') {
                                c = 3;
                            } else if (charAt == '\"') {
                                break;
                            } else if (allocate.hasRemaining()) {
                                allocate.put((byte) c2);
                            } else {
                                throw new IOException("Failed to parse multipart: boundary shouldn't be longer than 70 characters");
                            }
                            i++;
                        }
                    } else {
                        if (charAt != ' ') {
                            if (charAt != '\"') {
                                if (charAt == ';' || charAt == ',') {
                                    break;
                                }
                                allocate.put((byte) c2);
                                c = 1;
                            }
                        } else {
                            continue;
                        }
                        i++;
                    }
                    c = 2;
                    i++;
                } else {
                    StringBuilder sb = new StringBuilder("Failed to parse multipart: wrong boundary byte 0x");
                    String num = Integer.toString(c2, kotlin.text.CharsKt.checkRadix(16));
                    Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
                    sb.append(num);
                    sb.append(" - should be 7bit character");
                    throw new IOException(sb.toString());
                }
            }
            allocate.flip();
            if (allocate.remaining() != 4) {
                return allocate;
            }
            throw new IOException("Empty multipart boundary is not allowed");
        }
        throw new IOException("Failed to parse multipart: Content-Type's boundary parameter is missing");
    }

    public static final Object skipDelimiterOrEof(ByteReadChannel byteReadChannel, ByteBuffer byteBuffer, Continuation<? super Boolean> continuation) {
        if (!byteBuffer.hasRemaining()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        } else if (byteBuffer.remaining() <= 8192) {
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            byteReadChannel.lookAhead(new MultipartKt$skipDelimiterOrEof$3(booleanRef, byteBuffer));
            if (booleanRef.element) {
                return Boxing.boxBoolean(true);
            }
            return trySkipDelimiterSuspend(byteReadChannel, byteBuffer, continuation);
        } else {
            throw new IllegalArgumentException(("Delimiter of " + byteBuffer.remaining() + " bytes is too long: at most 8192 bytes could be checked").toString());
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object trySkipDelimiterSuspend(io.ktor.utils.io.ByteReadChannel r5, java.nio.ByteBuffer r6, kotlin.coroutines.Continuation<? super java.lang.Boolean> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.http.cio.MultipartKt$trySkipDelimiterSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.http.cio.MultipartKt$trySkipDelimiterSuspend$1 r0 = (io.ktor.http.cio.MultipartKt$trySkipDelimiterSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.http.cio.MultipartKt$trySkipDelimiterSuspend$1 r0 = new io.ktor.http.cio.MultipartKt$trySkipDelimiterSuspend$1
            r0.<init>(r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r5 = r0.L$0
            kotlin.jvm.internal.Ref$BooleanRef r5 = (kotlin.jvm.internal.Ref.BooleanRef) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0054
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlin.jvm.internal.Ref$BooleanRef r7 = new kotlin.jvm.internal.Ref$BooleanRef
            r7.<init>()
            r7.element = r3
            io.ktor.http.cio.MultipartKt$trySkipDelimiterSuspend$2 r2 = new io.ktor.http.cio.MultipartKt$trySkipDelimiterSuspend$2
            r4 = 0
            r2.<init>(r6, r7, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r5 = r5.lookAheadSuspend(r2, r0)
            if (r5 != r1) goto L_0x0053
            return r1
        L_0x0053:
            r5 = r7
        L_0x0054:
            boolean r5 = r5.element
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt.trySkipDelimiterSuspend(io.ktor.utils.io.ByteReadChannel, java.nio.ByteBuffer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final int tryEnsureDelimiter(LookAheadSession lookAheadSession, ByteBuffer byteBuffer) {
        int startsWithDelimiter = startsWithDelimiter(lookAheadSession, byteBuffer);
        if (startsWithDelimiter == -1) {
            throw new IOException("Failed to skip delimiter: actual bytes differ from delimiter bytes");
        } else if (startsWithDelimiter < byteBuffer.remaining()) {
            return startsWithDelimiter;
        } else {
            lookAheadSession.consumed(byteBuffer.remaining());
            return byteBuffer.remaining();
        }
    }

    static /* synthetic */ boolean startsWith$default(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return startsWith(byteBuffer, byteBuffer2, i);
    }

    private static final boolean startsWith(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i) {
        int min = Math.min(byteBuffer.remaining(), byteBuffer2.remaining() - i);
        if (min <= 0) {
            return false;
        }
        int position = byteBuffer.position();
        int position2 = byteBuffer2.position() + i;
        for (int i2 = 0; i2 < min; i2++) {
            if (byteBuffer.get(position + i2) != byteBuffer2.get(position2 + i2)) {
                return false;
            }
        }
        return true;
    }

    private static final int startsWithDelimiter(LookAheadSession lookAheadSession, ByteBuffer byteBuffer) {
        ByteBuffer request = lookAheadSession.request(0, 1);
        if (request == null) {
            return 0;
        }
        int indexOfPartial = indexOfPartial(request, byteBuffer);
        if (indexOfPartial != 0) {
            return -1;
        }
        int min = Math.min(request.remaining() - indexOfPartial, byteBuffer.remaining());
        int remaining = byteBuffer.remaining() - min;
        if (remaining > 0) {
            ByteBuffer request2 = lookAheadSession.request(indexOfPartial + min, remaining);
            if (request2 == null) {
                return min;
            }
            if (!startsWith(request2, byteBuffer, min)) {
                return -1;
            }
        }
        return byteBuffer.remaining();
    }

    private static final int indexOfPartial(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int position = byteBuffer2.position();
        int remaining = byteBuffer2.remaining();
        byte b = byteBuffer2.get(position);
        int limit = byteBuffer.limit();
        loop0:
        for (int position2 = byteBuffer.position(); position2 < limit; position2++) {
            if (byteBuffer.get(position2) == b) {
                int i = 1;
                while (i < remaining) {
                    int i2 = position2 + i;
                    if (i2 == limit) {
                        break loop0;
                    } else if (byteBuffer.get(i2) == byteBuffer2.get(position + i)) {
                        i++;
                    }
                }
                return position2 - byteBuffer.position();
            }
        }
        return -1;
    }

    static {
        byte[] bArr;
        Charset charset = Charsets.UTF_8;
        if (Intrinsics.areEqual((Object) charset, (Object) Charsets.UTF_8)) {
            bArr = StringsKt.encodeToByteArray("\r\n");
        } else {
            CharsetEncoder newEncoder = charset.newEncoder();
            Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
            bArr = CharsetJVMKt.encodeToByteArray(newEncoder, "\r\n", 0, 2);
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        Intrinsics.checkNotNull(wrap);
        CrLf = wrap;
        ByteBuffer allocate = ByteBuffer.allocate(8192);
        Intrinsics.checkNotNull(allocate);
        BoundaryTrailingBuffer = allocate;
    }
}
