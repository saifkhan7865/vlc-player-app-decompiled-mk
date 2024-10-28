package io.ktor.http.cio;

import io.ktor.utils.io.ByteReadChannel;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Lio/ktor/http/cio/MultipartEvent;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.http.cio.MultipartKt$parseMultipart$1", f = "Multipart.kt", i = {0, 0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 9, 9, 9, 10, 10, 13}, l = {289, 292, 295, 302, 303, 306, 311, 315, 320, 330, 333, 342, 342, 345, 347}, m = "invokeSuspend", n = {"$this$produce", "firstBoundary", "preamble", "readBeforeParse", "$this$produce", "firstBoundary", "readBeforeParse", "$this$produce", "readBeforeParse", "$this$produce", "trailingBuffer", "readBeforeParse", "$this$produce", "trailingBuffer", "readBeforeParse", "$this$produce", "trailingBuffer", "readBeforeParse", "$this$produce", "trailingBuffer", "body", "headers", "readBeforeParse", "$this$produce", "trailingBuffer", "body", "headers", "readBeforeParse", "$this$produce", "trailingBuffer", "body", "headers", "hh", "readBeforeParse", "$this$produce", "trailingBuffer", "readBeforeParse", "$this$produce", "readBeforeParse", "$this$produce"}, s = {"L$0", "L$1", "L$2", "J$0", "L$0", "L$1", "J$0", "L$0", "J$0", "L$0", "L$1", "J$0", "L$0", "L$1", "J$0", "L$0", "L$1", "J$0", "L$0", "L$1", "L$2", "L$3", "J$0", "L$0", "L$1", "L$2", "L$3", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "J$0", "L$0", "L$1", "J$0", "L$0", "J$0", "L$0"})
/* compiled from: Multipart.kt */
final class MultipartKt$parseMultipart$1 extends SuspendLambda implements Function2<ProducerScope<? super MultipartEvent>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteBuffer $boundaryPrefixed;
    final /* synthetic */ ByteReadChannel $input;
    final /* synthetic */ Long $totalLength;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MultipartKt$parseMultipart$1(ByteReadChannel byteReadChannel, ByteBuffer byteBuffer, Long l, Continuation<? super MultipartKt$parseMultipart$1> continuation) {
        super(2, continuation);
        this.$input = byteReadChannel;
        this.$boundaryPrefixed = byteBuffer;
        this.$totalLength = l;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MultipartKt$parseMultipart$1 multipartKt$parseMultipart$1 = new MultipartKt$parseMultipart$1(this.$input, this.$boundaryPrefixed, this.$totalLength, continuation);
        multipartKt$parseMultipart$1.L$0 = obj;
        return multipartKt$parseMultipart$1;
    }

    public final Object invoke(ProducerScope<? super MultipartEvent> producerScope, Continuation<? super Unit> continuation) {
        return ((MultipartKt$parseMultipart$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v56, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v50, resolved type: kotlinx.coroutines.CompletableDeferred} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v57, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v30, resolved type: io.ktor.utils.io.ByteChannel} */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x031b, code lost:
        throw new java.io.IOException("Failed to parse multipart: prologue is too long");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x031c, code lost:
        r1.L$0 = r2;
        r1.L$1 = null;
        r1.label = 14;
        r3 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r1.$input, 0, r1, 1, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0331, code lost:
        if (r3 != r0) goto L_0x0334;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0333, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0334, code lost:
        r3 = (io.ktor.utils.io.core.ByteReadPacket) r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x033b, code lost:
        if ((!r3.getEndOfInput()) == false) goto L_0x0352;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x033d, code lost:
        r1.L$0 = null;
        r1.label = 15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x034f, code lost:
        if (r2.send(new io.ktor.http.cio.MultipartEvent.Epilogue(r3), r1) != r0) goto L_0x0352;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0351, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0354, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0355, code lost:
        r4 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:?, code lost:
        r11.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0362, code lost:
        throw new java.util.concurrent.CancellationException("Multipart processing has been cancelled");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0363, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0364, code lost:
        r6 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x037c, code lost:
        throw new java.io.IOException("Failed to parse multipart: boundary line is too long");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00b8, code lost:
        r10 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x014d, code lost:
        if (r2.getSize() <= 0) goto L_0x0171;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x014f, code lost:
        r1.L$0 = r10;
        r1.L$1 = r9;
        r1.L$2 = null;
        r1.J$0 = r7;
        r1.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0169, code lost:
        if (r10.send(new io.ktor.http.cio.MultipartEvent.Preamble(r2.build()), r1) != r0) goto L_0x016c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x016b, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x016c, code lost:
        r2 = r9;
        r4 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x016e, code lost:
        r9 = r2;
        r2 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0171, code lost:
        r2 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0172, code lost:
        r1.L$0 = r2;
        r1.L$1 = null;
        r1.L$2 = null;
        r1.J$0 = r7;
        r1.label = 3;
        r4 = io.ktor.http.cio.MultipartKt.skipBoundary(r9, r1.$input, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0186, code lost:
        if (r4 != r0) goto L_0x0189;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0188, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x018f, code lost:
        if (((java.lang.Boolean) r4).booleanValue() == false) goto L_0x0194;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0193, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0194, code lost:
        r4 = r2;
        r2 = io.ktor.http.cio.MultipartKt.BoundaryTrailingBuffer.duplicate();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x01a1, code lost:
        r9 = r1.$input;
        r10 = io.ktor.http.cio.MultipartKt.CrLf;
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, "trailingBuffer");
        r1.L$0 = r4;
        r1.L$1 = r2;
        r1.J$0 = r7;
        r1.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x01ba, code lost:
        if (io.ktor.utils.io.DelimitedKt.readUntilDelimiter(r9, r10, r2, r1) != r0) goto L_0x01bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x01bc, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x01bd, code lost:
        r9 = r1.$input;
        r10 = io.ktor.http.cio.MultipartKt.CrLf;
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, "trailingBuffer");
        r1.L$0 = r4;
        r1.L$1 = r2;
        r1.J$0 = r7;
        r1.label = 5;
        r9 = io.ktor.utils.io.DelimitedKt.readUntilDelimiter(r9, r10, r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x01d6, code lost:
        if (r9 != r0) goto L_0x01d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x01d8, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x01df, code lost:
        if (((java.lang.Number) r9).intValue() != 0) goto L_0x0374;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x01e1, code lost:
        r1.L$0 = r4;
        r1.L$1 = r2;
        r1.J$0 = r7;
        r1.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x01f7, code lost:
        if (io.ktor.utils.io.DelimitedKt.skipDelimiter(r1.$input, io.ktor.http.cio.MultipartKt.CrLf, r1) != r0) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x01f9, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x01fa, code lost:
        r4 = io.ktor.utils.io.ByteChannelKt.ByteChannel$default(false, 1, (java.lang.Object) null);
        r9 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default((kotlinx.coroutines.Job) null, 1, (java.lang.Object) null);
        r1.L$0 = r10;
        r1.L$1 = r2;
        r1.L$2 = r4;
        r1.L$3 = r9;
        r1.J$0 = r7;
        r1.label = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0222, code lost:
        if (r10.send(new io.ktor.http.cio.MultipartEvent.MultipartPart(r9, r4), r1) != r0) goto L_0x0225;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0224, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0225, code lost:
        r21 = r9;
        r9 = r4;
        r4 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        r1.L$0 = r10;
        r1.L$1 = r2;
        r1.L$2 = r9;
        r1.L$3 = r4;
        r1.J$0 = r7;
        r1.label = 8;
        r11 = io.ktor.http.cio.MultipartKt.parsePartHeadersImpl(r1.$input, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0241, code lost:
        if (r11 != r0) goto L_0x0244;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0243, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0244, code lost:
        r11 = (io.ktor.http.cio.HttpHeadersMap) r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x024a, code lost:
        if (r4.complete(r11) == false) goto L_0x0358;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x024c, code lost:
        r1.L$0 = r10;
        r1.L$1 = r2;
        r1.L$2 = r9;
        r1.L$3 = r4;
        r1.L$4 = r11;
        r1.J$0 = r7;
        r1.label = 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0272, code lost:
        if (io.ktor.http.cio.MultipartKt.parsePartBodyImpl$default(r1.$boundaryPrefixed, r1.$input, r9, r11, 0, r1, 16, (java.lang.Object) null) != r0) goto L_0x0275;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0274, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0275, code lost:
        r11 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0276, code lost:
        io.ktor.utils.io.ByteWriteChannelKt.close(r9);
        r1.L$0 = r11;
        r1.L$1 = r2;
        r1.L$2 = null;
        r1.L$3 = null;
        r1.L$4 = null;
        r1.J$0 = r7;
        r1.label = 10;
        r4 = io.ktor.http.cio.MultipartKt.skipBoundary(r1.$boundaryPrefixed, r1.$input, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0296, code lost:
        if (r4 != r0) goto L_0x0299;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0298, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x029f, code lost:
        if (((java.lang.Boolean) r4).booleanValue() == false) goto L_0x0355;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x02a7, code lost:
        if (r1.$input.getAvailableForRead() == 0) goto L_0x02c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x02a9, code lost:
        r1.L$0 = r11;
        r1.L$1 = null;
        r1.J$0 = r7;
        r1.label = 11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x02c0, code lost:
        if (io.ktor.utils.io.DelimitedKt.skipDelimiter(r1.$input, io.ktor.http.cio.MultipartKt.CrLf, r1) != r0) goto L_0x02c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x02c2, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x02c3, code lost:
        r2 = r7;
        r4 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x02c5, code lost:
        r7 = r2;
        r2 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x02c8, code lost:
        r2 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x02cb, code lost:
        if (r1.$totalLength == null) goto L_0x031c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x02cd, code lost:
        r7 = r1.$totalLength.longValue() - (r1.$input.getTotalBytesRead() - r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x02e0, code lost:
        if (r7 > 2147483647L) goto L_0x0314;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x02e6, code lost:
        if (r7 <= 0) goto L_0x0352;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x02e8, code lost:
        r1.L$0 = r2;
        r1.L$1 = null;
        r1.label = 12;
        r3 = r1.$input.readPacket((int) r7, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x02fa, code lost:
        if (r3 != r0) goto L_0x02fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x02fc, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x02fd, code lost:
        r1.L$0 = null;
        r1.label = 13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0311, code lost:
        if (r2.send(new io.ktor.http.cio.MultipartEvent.Epilogue((io.ktor.utils.io.core.ByteReadPacket) r3), r1) != r0) goto L_0x0352;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0313, code lost:
        return r0;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r23) {
        /*
            r22 = this;
            r1 = r22
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            java.lang.String r3 = "trailingBuffer"
            r4 = 2
            r5 = 1
            r6 = 0
            switch(r2) {
                case 0: goto L_0x0109;
                case 1: goto L_0x00f7;
                case 2: goto L_0x00e8;
                case 3: goto L_0x00db;
                case 4: goto L_0x00cc;
                case 5: goto L_0x00bb;
                case 6: goto L_0x00ab;
                case 7: goto L_0x008e;
                case 8: goto L_0x0070;
                case 9: goto L_0x0050;
                case 10: goto L_0x003e;
                case 11: goto L_0x0033;
                case 12: goto L_0x0028;
                case 13: goto L_0x0023;
                case 14: goto L_0x0018;
                case 15: goto L_0x0023;
                default: goto L_0x0010;
            }
        L_0x0010:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0018:
            java.lang.Object r2 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r2 = (kotlinx.coroutines.channels.ProducerScope) r2
            kotlin.ResultKt.throwOnFailure(r23)
            r3 = r23
            goto L_0x0334
        L_0x0023:
            kotlin.ResultKt.throwOnFailure(r23)
            goto L_0x0352
        L_0x0028:
            java.lang.Object r2 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r2 = (kotlinx.coroutines.channels.ProducerScope) r2
            kotlin.ResultKt.throwOnFailure(r23)
            r3 = r23
            goto L_0x02fd
        L_0x0033:
            long r2 = r1.J$0
            java.lang.Object r4 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.throwOnFailure(r23)
            goto L_0x02c5
        L_0x003e:
            long r7 = r1.J$0
            java.lang.Object r2 = r1.L$1
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            java.lang.Object r4 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.throwOnFailure(r23)
            r11 = r4
            r4 = r23
            goto L_0x0299
        L_0x0050:
            long r7 = r1.J$0
            java.lang.Object r2 = r1.L$4
            io.ktor.http.cio.HttpHeadersMap r2 = (io.ktor.http.cio.HttpHeadersMap) r2
            java.lang.Object r4 = r1.L$3
            kotlinx.coroutines.CompletableDeferred r4 = (kotlinx.coroutines.CompletableDeferred) r4
            java.lang.Object r9 = r1.L$2
            io.ktor.utils.io.ByteChannel r9 = (io.ktor.utils.io.ByteChannel) r9
            java.lang.Object r10 = r1.L$1
            java.nio.ByteBuffer r10 = (java.nio.ByteBuffer) r10
            java.lang.Object r11 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r11 = (kotlinx.coroutines.channels.ProducerScope) r11
            kotlin.ResultKt.throwOnFailure(r23)     // Catch:{ all -> 0x006c }
            r2 = r10
            goto L_0x0276
        L_0x006c:
            r0 = move-exception
            r6 = r2
            goto L_0x0365
        L_0x0070:
            long r7 = r1.J$0
            java.lang.Object r2 = r1.L$3
            r4 = r2
            kotlinx.coroutines.CompletableDeferred r4 = (kotlinx.coroutines.CompletableDeferred) r4
            java.lang.Object r2 = r1.L$2
            r9 = r2
            io.ktor.utils.io.ByteChannel r9 = (io.ktor.utils.io.ByteChannel) r9
            java.lang.Object r2 = r1.L$1
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            java.lang.Object r10 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r10 = (kotlinx.coroutines.channels.ProducerScope) r10
            kotlin.ResultKt.throwOnFailure(r23)     // Catch:{ all -> 0x008b }
            r11 = r23
            goto L_0x0244
        L_0x008b:
            r0 = move-exception
            goto L_0x0365
        L_0x008e:
            long r7 = r1.J$0
            java.lang.Object r2 = r1.L$3
            kotlinx.coroutines.CompletableDeferred r2 = (kotlinx.coroutines.CompletableDeferred) r2
            java.lang.Object r4 = r1.L$2
            io.ktor.utils.io.ByteChannel r4 = (io.ktor.utils.io.ByteChannel) r4
            java.lang.Object r9 = r1.L$1
            java.nio.ByteBuffer r9 = (java.nio.ByteBuffer) r9
            java.lang.Object r10 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r10 = (kotlinx.coroutines.channels.ProducerScope) r10
            kotlin.ResultKt.throwOnFailure(r23)
            r21 = r4
            r4 = r2
            r2 = r9
            r9 = r21
            goto L_0x022a
        L_0x00ab:
            long r7 = r1.J$0
            java.lang.Object r2 = r1.L$1
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            java.lang.Object r4 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.throwOnFailure(r23)
        L_0x00b8:
            r10 = r4
            goto L_0x01fa
        L_0x00bb:
            long r7 = r1.J$0
            java.lang.Object r2 = r1.L$1
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            java.lang.Object r4 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.throwOnFailure(r23)
            r9 = r23
            goto L_0x01d9
        L_0x00cc:
            long r7 = r1.J$0
            java.lang.Object r2 = r1.L$1
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            java.lang.Object r4 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.throwOnFailure(r23)
            goto L_0x01bd
        L_0x00db:
            long r7 = r1.J$0
            java.lang.Object r2 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r2 = (kotlinx.coroutines.channels.ProducerScope) r2
            kotlin.ResultKt.throwOnFailure(r23)
            r4 = r23
            goto L_0x0189
        L_0x00e8:
            long r7 = r1.J$0
            java.lang.Object r2 = r1.L$1
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            java.lang.Object r4 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
            kotlin.ResultKt.throwOnFailure(r23)
            goto L_0x016e
        L_0x00f7:
            long r7 = r1.J$0
            java.lang.Object r2 = r1.L$2
            io.ktor.utils.io.core.BytePacketBuilder r2 = (io.ktor.utils.io.core.BytePacketBuilder) r2
            java.lang.Object r9 = r1.L$1
            java.nio.ByteBuffer r9 = (java.nio.ByteBuffer) r9
            java.lang.Object r10 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r10 = (kotlinx.coroutines.channels.ProducerScope) r10
            kotlin.ResultKt.throwOnFailure(r23)
            goto L_0x0149
        L_0x0109:
            kotlin.ResultKt.throwOnFailure(r23)
            java.lang.Object r2 = r1.L$0
            kotlinx.coroutines.channels.ProducerScope r2 = (kotlinx.coroutines.channels.ProducerScope) r2
            io.ktor.utils.io.ByteReadChannel r7 = r1.$input
            long r7 = r7.getTotalBytesRead()
            java.nio.ByteBuffer r9 = r1.$boundaryPrefixed
            java.nio.ByteBuffer r9 = r9.duplicate()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            r9.position(r4)
            io.ktor.utils.io.core.BytePacketBuilder r15 = new io.ktor.utils.io.core.BytePacketBuilder
            r15.<init>(r6, r5, r6)
            io.ktor.utils.io.ByteReadChannel r11 = r1.$input
            r16 = r1
            kotlin.coroutines.Continuation r16 = (kotlin.coroutines.Continuation) r16
            r1.L$0 = r2
            r1.L$1 = r9
            r1.L$2 = r15
            r1.J$0 = r7
            r1.label = r5
            r13 = 8192(0x2000, double:4.0474E-320)
            r10 = r9
            r12 = r15
            r17 = r15
            r15 = r16
            java.lang.Object r10 = io.ktor.http.cio.MultipartKt.parsePreambleImpl(r10, r11, r12, r13, r15)
            if (r10 != r0) goto L_0x0146
            return r0
        L_0x0146:
            r10 = r2
            r2 = r17
        L_0x0149:
            int r11 = r2.getSize()
            if (r11 <= 0) goto L_0x0171
            io.ktor.http.cio.MultipartEvent$Preamble r11 = new io.ktor.http.cio.MultipartEvent$Preamble
            io.ktor.utils.io.core.ByteReadPacket r2 = r2.build()
            r11.<init>(r2)
            r2 = r1
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r1.L$0 = r10
            r1.L$1 = r9
            r1.L$2 = r6
            r1.J$0 = r7
            r1.label = r4
            java.lang.Object r2 = r10.send(r11, r2)
            if (r2 != r0) goto L_0x016c
            return r0
        L_0x016c:
            r2 = r9
            r4 = r10
        L_0x016e:
            r9 = r2
            r2 = r4
            goto L_0x0172
        L_0x0171:
            r2 = r10
        L_0x0172:
            io.ktor.utils.io.ByteReadChannel r4 = r1.$input
            r10 = r1
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r1.L$0 = r2
            r1.L$1 = r6
            r1.L$2 = r6
            r1.J$0 = r7
            r11 = 3
            r1.label = r11
            java.lang.Object r4 = io.ktor.http.cio.MultipartKt.skipBoundary(r9, r4, r10)
            if (r4 != r0) goto L_0x0189
            return r0
        L_0x0189:
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x0194
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0194:
            java.nio.ByteBuffer r4 = io.ktor.http.cio.MultipartKt.BoundaryTrailingBuffer
            java.nio.ByteBuffer r4 = r4.duplicate()
            r21 = r4
            r4 = r2
            r2 = r21
        L_0x01a1:
            io.ktor.utils.io.ByteReadChannel r9 = r1.$input
            java.nio.ByteBuffer r10 = io.ktor.http.cio.MultipartKt.CrLf
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r11 = r1
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r1.L$0 = r4
            r1.L$1 = r2
            r1.J$0 = r7
            r12 = 4
            r1.label = r12
            java.lang.Object r9 = io.ktor.utils.io.DelimitedKt.readUntilDelimiter(r9, r10, r2, r11)
            if (r9 != r0) goto L_0x01bd
            return r0
        L_0x01bd:
            io.ktor.utils.io.ByteReadChannel r9 = r1.$input
            java.nio.ByteBuffer r10 = io.ktor.http.cio.MultipartKt.CrLf
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            r11 = r1
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r1.L$0 = r4
            r1.L$1 = r2
            r1.J$0 = r7
            r12 = 5
            r1.label = r12
            java.lang.Object r9 = io.ktor.utils.io.DelimitedKt.readUntilDelimiter(r9, r10, r2, r11)
            if (r9 != r0) goto L_0x01d9
            return r0
        L_0x01d9:
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            if (r9 != 0) goto L_0x0374
            io.ktor.utils.io.ByteReadChannel r9 = r1.$input
            java.nio.ByteBuffer r10 = io.ktor.http.cio.MultipartKt.CrLf
            r11 = r1
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r1.L$0 = r4
            r1.L$1 = r2
            r1.J$0 = r7
            r12 = 6
            r1.label = r12
            java.lang.Object r9 = io.ktor.utils.io.DelimitedKt.skipDelimiter(r9, r10, r11)
            if (r9 != r0) goto L_0x00b8
            return r0
        L_0x01fa:
            r4 = 0
            io.ktor.utils.io.ByteChannel r4 = io.ktor.utils.io.ByteChannelKt.ByteChannel$default(r4, r5, r6)
            kotlinx.coroutines.CompletableDeferred r9 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(r6, r5, r6)
            io.ktor.http.cio.MultipartEvent$MultipartPart r11 = new io.ktor.http.cio.MultipartEvent$MultipartPart
            r12 = r9
            kotlinx.coroutines.Deferred r12 = (kotlinx.coroutines.Deferred) r12
            r13 = r4
            io.ktor.utils.io.ByteReadChannel r13 = (io.ktor.utils.io.ByteReadChannel) r13
            r11.<init>(r12, r13)
            r12 = r1
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
            r1.L$0 = r10
            r1.L$1 = r2
            r1.L$2 = r4
            r1.L$3 = r9
            r1.J$0 = r7
            r13 = 7
            r1.label = r13
            java.lang.Object r11 = r10.send(r11, r12)
            if (r11 != r0) goto L_0x0225
            return r0
        L_0x0225:
            r21 = r9
            r9 = r4
            r4 = r21
        L_0x022a:
            io.ktor.utils.io.ByteReadChannel r11 = r1.$input     // Catch:{ all -> 0x008b }
            r12 = r1
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12     // Catch:{ all -> 0x008b }
            r1.L$0 = r10     // Catch:{ all -> 0x008b }
            r1.L$1 = r2     // Catch:{ all -> 0x008b }
            r1.L$2 = r9     // Catch:{ all -> 0x008b }
            r1.L$3 = r4     // Catch:{ all -> 0x008b }
            r1.J$0 = r7     // Catch:{ all -> 0x008b }
            r13 = 8
            r1.label = r13     // Catch:{ all -> 0x008b }
            java.lang.Object r11 = io.ktor.http.cio.MultipartKt.parsePartHeadersImpl(r11, r12)     // Catch:{ all -> 0x008b }
            if (r11 != r0) goto L_0x0244
            return r0
        L_0x0244:
            io.ktor.http.cio.HttpHeadersMap r11 = (io.ktor.http.cio.HttpHeadersMap) r11     // Catch:{ all -> 0x008b }
            boolean r12 = r4.complete(r11)     // Catch:{ all -> 0x0363 }
            if (r12 == 0) goto L_0x0358
            java.nio.ByteBuffer r12 = r1.$boundaryPrefixed     // Catch:{ all -> 0x0363 }
            io.ktor.utils.io.ByteReadChannel r13 = r1.$input     // Catch:{ all -> 0x0363 }
            r14 = r9
            io.ktor.utils.io.ByteWriteChannel r14 = (io.ktor.utils.io.ByteWriteChannel) r14     // Catch:{ all -> 0x0363 }
            r18 = r1
            kotlin.coroutines.Continuation r18 = (kotlin.coroutines.Continuation) r18     // Catch:{ all -> 0x0363 }
            r1.L$0 = r10     // Catch:{ all -> 0x0363 }
            r1.L$1 = r2     // Catch:{ all -> 0x0363 }
            r1.L$2 = r9     // Catch:{ all -> 0x0363 }
            r1.L$3 = r4     // Catch:{ all -> 0x0363 }
            r1.L$4 = r11     // Catch:{ all -> 0x0363 }
            r1.J$0 = r7     // Catch:{ all -> 0x0363 }
            r15 = 9
            r1.label = r15     // Catch:{ all -> 0x0363 }
            r16 = 0
            r19 = 16
            r20 = 0
            r15 = r11
            java.lang.Object r4 = io.ktor.http.cio.MultipartKt.parsePartBodyImpl$default(r12, r13, r14, r15, r16, r18, r19, r20)     // Catch:{ all -> 0x0363 }
            if (r4 != r0) goto L_0x0275
            return r0
        L_0x0275:
            r11 = r10
        L_0x0276:
            io.ktor.utils.io.ByteWriteChannel r9 = (io.ktor.utils.io.ByteWriteChannel) r9
            io.ktor.utils.io.ByteWriteChannelKt.close(r9)
            java.nio.ByteBuffer r4 = r1.$boundaryPrefixed
            io.ktor.utils.io.ByteReadChannel r9 = r1.$input
            r10 = r1
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r1.L$0 = r11
            r1.L$1 = r2
            r1.L$2 = r6
            r1.L$3 = r6
            r1.L$4 = r6
            r1.J$0 = r7
            r12 = 10
            r1.label = r12
            java.lang.Object r4 = io.ktor.http.cio.MultipartKt.skipBoundary(r4, r9, r10)
            if (r4 != r0) goto L_0x0299
            return r0
        L_0x0299:
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x0355
            io.ktor.utils.io.ByteReadChannel r2 = r1.$input
            int r2 = r2.getAvailableForRead()
            if (r2 == 0) goto L_0x02c8
            io.ktor.utils.io.ByteReadChannel r2 = r1.$input
            java.nio.ByteBuffer r3 = io.ktor.http.cio.MultipartKt.CrLf
            r4 = r1
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r1.L$0 = r11
            r1.L$1 = r6
            r1.J$0 = r7
            r9 = 11
            r1.label = r9
            java.lang.Object r2 = io.ktor.utils.io.DelimitedKt.skipDelimiter(r2, r3, r4)
            if (r2 != r0) goto L_0x02c3
            return r0
        L_0x02c3:
            r2 = r7
            r4 = r11
        L_0x02c5:
            r7 = r2
            r2 = r4
            goto L_0x02c9
        L_0x02c8:
            r2 = r11
        L_0x02c9:
            java.lang.Long r3 = r1.$totalLength
            if (r3 == 0) goto L_0x031c
            io.ktor.utils.io.ByteReadChannel r3 = r1.$input
            long r3 = r3.getTotalBytesRead()
            long r3 = r3 - r7
            java.lang.Long r5 = r1.$totalLength
            long r7 = r5.longValue()
            long r7 = r7 - r3
            r3 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x0314
            r3 = 0
            int r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0352
            io.ktor.utils.io.ByteReadChannel r3 = r1.$input
            int r4 = (int) r7
            r5 = r1
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r1.L$0 = r2
            r1.L$1 = r6
            r7 = 12
            r1.label = r7
            java.lang.Object r3 = r3.readPacket(r4, r5)
            if (r3 != r0) goto L_0x02fd
            return r0
        L_0x02fd:
            io.ktor.utils.io.core.ByteReadPacket r3 = (io.ktor.utils.io.core.ByteReadPacket) r3
            io.ktor.http.cio.MultipartEvent$Epilogue r4 = new io.ktor.http.cio.MultipartEvent$Epilogue
            r4.<init>(r3)
            r3 = r1
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r1.L$0 = r6
            r5 = 13
            r1.label = r5
            java.lang.Object r2 = r2.send(r4, r3)
            if (r2 != r0) goto L_0x0352
            return r0
        L_0x0314:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r2 = "Failed to parse multipart: prologue is too long"
            r0.<init>(r2)
            throw r0
        L_0x031c:
            io.ktor.utils.io.ByteReadChannel r7 = r1.$input
            r10 = r1
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r1.L$0 = r2
            r1.L$1 = r6
            r3 = 14
            r1.label = r3
            r8 = 0
            r11 = 1
            r12 = 0
            java.lang.Object r3 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r7, r8, r10, r11, r12)
            if (r3 != r0) goto L_0x0334
            return r0
        L_0x0334:
            io.ktor.utils.io.core.ByteReadPacket r3 = (io.ktor.utils.io.core.ByteReadPacket) r3
            boolean r4 = r3.getEndOfInput()
            r4 = r4 ^ r5
            if (r4 == 0) goto L_0x0352
            io.ktor.http.cio.MultipartEvent$Epilogue r4 = new io.ktor.http.cio.MultipartEvent$Epilogue
            r4.<init>(r3)
            r3 = r1
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r1.L$0 = r6
            r5 = 15
            r1.label = r5
            java.lang.Object r2 = r2.send(r4, r3)
            if (r2 != r0) goto L_0x0352
            return r0
        L_0x0352:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0355:
            r4 = r11
            goto L_0x01a1
        L_0x0358:
            r11.release()     // Catch:{ all -> 0x0363 }
            java.util.concurrent.CancellationException r0 = new java.util.concurrent.CancellationException     // Catch:{ all -> 0x0363 }
            java.lang.String r2 = "Multipart processing has been cancelled"
            r0.<init>(r2)     // Catch:{ all -> 0x0363 }
            throw r0     // Catch:{ all -> 0x0363 }
        L_0x0363:
            r0 = move-exception
            r6 = r11
        L_0x0365:
            boolean r2 = r4.completeExceptionally(r0)
            if (r2 == 0) goto L_0x0370
            if (r6 == 0) goto L_0x0370
            r6.release()
        L_0x0370:
            r9.close(r0)
            throw r0
        L_0x0374:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r2 = "Failed to parse multipart: boundary line is too long"
            r0.<init>(r2)
            goto L_0x037d
        L_0x037c:
            throw r0
        L_0x037d:
            goto L_0x037c
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.MultipartKt$parseMultipart$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
