package io.ktor.http.cio;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.ReaderJob;
import io.ktor.utils.io.ReaderScope;
import io.ktor.utils.io.WriterJob;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

@Metadata(d1 = {"\u0000h\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a!\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H@ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a)\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H@ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a!\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001a%\u0010\u0016\u001a\u00060\u0019j\u0002`\u001a2\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cH@ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001a\u0018\u0010\f\u001a\u00060\u001ej\u0002`\u001f*\u00020 2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\u001e\u0010\f\u001a\u00060\u001ej\u0002`\u001f*\u00020 2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014\u001a\f\u0010!\u001a\u00020\r*\u00020\u000fH\u0002\u001a5\u0010\"\u001a\u00020\u0001*\u00020\u00112\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00012\u0006\u0010&\u001a\u00020\u0001H@ø\u0001\u0001ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b'\u0010(\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0018\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u0003X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000*\n\u0010)\"\u00020\u001e2\u00020\u001e*\n\u0010*\"\u00020\u00192\u00020\u0019\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006+"}, d2 = {"CHUNK_BUFFER_POOL_SIZE", "", "ChunkSizeBufferPool", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "CrLf", "", "CrLfShort", "", "LastChunkBytes", "MAX_CHUNK_SIZE_LENGTH", "decodeChunked", "", "input", "Lio/ktor/utils/io/ByteReadChannel;", "out", "Lio/ktor/utils/io/ByteWriteChannel;", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "contentLength", "", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "encodeChunked", "output", "(Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/utils/io/ByteReadChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/ktor/utils/io/ReaderJob;", "Lio/ktor/http/cio/EncoderJob;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/ktor/utils/io/WriterJob;", "Lio/ktor/http/cio/DecoderJob;", "Lkotlinx/coroutines/CoroutineScope;", "rethrowCloseCause", "writeChunk", "memory", "Lio/ktor/utils/io/bits/Memory;", "startIndex", "endIndex", "writeChunk-yRinSxo", "(Lio/ktor/utils/io/ByteWriteChannel;Ljava/nio/ByteBuffer;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "DecoderJob", "EncoderJob", "ktor-http-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ChunkedTransferEncoding.kt */
public final class ChunkedTransferEncodingKt {
    private static final int CHUNK_BUFFER_POOL_SIZE = 2048;
    private static final ObjectPool<StringBuilder> ChunkSizeBufferPool = new ChunkedTransferEncodingKt$ChunkSizeBufferPool$1();
    private static final byte[] CrLf;
    private static final short CrLfShort = 3338;
    private static final byte[] LastChunkBytes;
    private static final int MAX_CHUNK_SIZE_LENGTH = 128;

    public static /* synthetic */ void DecoderJob$annotations() {
    }

    public static /* synthetic */ void EncoderJob$annotations() {
    }

    static {
        byte[] bArr;
        byte[] bArr2;
        Charset charset = Charsets.UTF_8;
        if (Intrinsics.areEqual((Object) charset, (Object) Charsets.UTF_8)) {
            bArr = StringsKt.encodeToByteArray("\r\n");
        } else {
            CharsetEncoder newEncoder = charset.newEncoder();
            Intrinsics.checkNotNullExpressionValue(newEncoder, "charset.newEncoder()");
            bArr = CharsetJVMKt.encodeToByteArray(newEncoder, "\r\n", 0, 2);
        }
        CrLf = bArr;
        Charset charset2 = Charsets.UTF_8;
        if (Intrinsics.areEqual((Object) charset2, (Object) Charsets.UTF_8)) {
            bArr2 = StringsKt.encodeToByteArray("0\r\n\r\n");
        } else {
            CharsetEncoder newEncoder2 = charset2.newEncoder();
            Intrinsics.checkNotNullExpressionValue(newEncoder2, "charset.newEncoder()");
            bArr2 = CharsetJVMKt.encodeToByteArray(newEncoder2, "0\r\n\r\n", 0, 5);
        }
        LastChunkBytes = bArr2;
    }

    @Deprecated(message = "Specify content length if known or pass -1L", replaceWith = @ReplaceWith(expression = "decodeChunked(input, -1L)", imports = {}))
    public static final WriterJob decodeChunked(CoroutineScope coroutineScope, ByteReadChannel byteReadChannel) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(byteReadChannel, TvContractCompat.PARAM_INPUT);
        return decodeChunked(coroutineScope, byteReadChannel, -1);
    }

    public static final WriterJob decodeChunked(CoroutineScope coroutineScope, ByteReadChannel byteReadChannel, long j) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(byteReadChannel, TvContractCompat.PARAM_INPUT);
        return CoroutinesKt.writer$default(coroutineScope, coroutineScope.getCoroutineContext(), false, (Function2) new ChunkedTransferEncodingKt$decodeChunked$1(byteReadChannel, (Continuation<? super ChunkedTransferEncodingKt$decodeChunked$1>) null), 2, (Object) null);
    }

    public static final Object decodeChunked(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, Continuation<? super Unit> continuation) {
        Object decodeChunked = decodeChunked(byteReadChannel, byteWriteChannel, -1, continuation);
        return decodeChunked == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? decodeChunked : Unit.INSTANCE;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: io.ktor.utils.io.ByteWriteChannel} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a8 A[Catch:{ all -> 0x0075 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00fc A[Catch:{ all -> 0x0075 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0105 A[Catch:{ all -> 0x0075 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0129 A[Catch:{ all -> 0x0075 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x014d A[Catch:{ all -> 0x0075 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.ERROR, message = "The contentLength is ignored for chunked transfer encoding", replaceWith = @kotlin.ReplaceWith(expression = "decodeChunked(input, out)", imports = {}))
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object decodeChunked(io.ktor.utils.io.ByteReadChannel r10, io.ktor.utils.io.ByteWriteChannel r11, long r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            boolean r12 = r14 instanceof io.ktor.http.cio.ChunkedTransferEncodingKt$decodeChunked$3
            if (r12 == 0) goto L_0x0014
            r12 = r14
            io.ktor.http.cio.ChunkedTransferEncodingKt$decodeChunked$3 r12 = (io.ktor.http.cio.ChunkedTransferEncodingKt$decodeChunked$3) r12
            int r13 = r12.label
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r13 = r13 & r0
            if (r13 == 0) goto L_0x0014
            int r13 = r12.label
            int r13 = r13 - r0
            r12.label = r13
            goto L_0x0019
        L_0x0014:
            io.ktor.http.cio.ChunkedTransferEncodingKt$decodeChunked$3 r12 = new io.ktor.http.cio.ChunkedTransferEncodingKt$decodeChunked$3
            r12.<init>(r14)
        L_0x0019:
            java.lang.Object r13 = r12.result
            java.lang.Object r14 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r12.label
            r1 = 3
            r2 = 2
            r3 = 1
            r4 = 0
            if (r0 == 0) goto L_0x0078
            if (r0 == r3) goto L_0x0060
            if (r0 == r2) goto L_0x004b
            if (r0 != r1) goto L_0x0043
            long r10 = r12.J$1
            long r6 = r12.J$0
            java.lang.Object r0 = r12.L$2
            java.lang.StringBuilder r0 = (java.lang.StringBuilder) r0
            java.lang.Object r8 = r12.L$1
            io.ktor.utils.io.ByteWriteChannel r8 = (io.ktor.utils.io.ByteWriteChannel) r8
            java.lang.Object r9 = r12.L$0
            io.ktor.utils.io.ByteReadChannel r9 = (io.ktor.utils.io.ByteReadChannel) r9
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x0075 }
            goto L_0x00fd
        L_0x0043:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x004b:
            long r10 = r12.J$1
            long r6 = r12.J$0
            java.lang.Object r0 = r12.L$2
            java.lang.StringBuilder r0 = (java.lang.StringBuilder) r0
            java.lang.Object r8 = r12.L$1
            io.ktor.utils.io.ByteWriteChannel r8 = (io.ktor.utils.io.ByteWriteChannel) r8
            java.lang.Object r9 = r12.L$0
            io.ktor.utils.io.ByteReadChannel r9 = (io.ktor.utils.io.ByteReadChannel) r9
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x0075 }
            goto L_0x00e0
        L_0x0060:
            long r10 = r12.J$0
            java.lang.Object r0 = r12.L$2
            java.lang.StringBuilder r0 = (java.lang.StringBuilder) r0
            java.lang.Object r6 = r12.L$1
            r8 = r6
            io.ktor.utils.io.ByteWriteChannel r8 = (io.ktor.utils.io.ByteWriteChannel) r8
            java.lang.Object r6 = r12.L$0
            io.ktor.utils.io.ByteReadChannel r6 = (io.ktor.utils.io.ByteReadChannel) r6
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x0075 }
            r9 = r6
            r6 = r10
            goto L_0x00a0
        L_0x0075:
            r10 = move-exception
            goto L_0x0157
        L_0x0078:
            kotlin.ResultKt.throwOnFailure(r13)
            io.ktor.utils.io.pool.ObjectPool<java.lang.StringBuilder> r13 = ChunkSizeBufferPool
            java.lang.Object r13 = r13.borrow()
            java.lang.StringBuilder r13 = (java.lang.StringBuilder) r13
            r0 = r13
            r6 = r4
        L_0x0085:
            kotlin.text.StringsKt.clear(r0)     // Catch:{ all -> 0x0155 }
            r13 = r0
            java.lang.Appendable r13 = (java.lang.Appendable) r13     // Catch:{ all -> 0x0155 }
            r12.L$0 = r10     // Catch:{ all -> 0x0155 }
            r12.L$1 = r11     // Catch:{ all -> 0x0155 }
            r12.L$2 = r0     // Catch:{ all -> 0x0155 }
            r12.J$0 = r6     // Catch:{ all -> 0x0155 }
            r12.label = r3     // Catch:{ all -> 0x0155 }
            r8 = 128(0x80, float:1.794E-43)
            java.lang.Object r13 = r10.readUTF8LineTo(r13, r8, r12)     // Catch:{ all -> 0x0155 }
            if (r13 != r14) goto L_0x009e
            return r14
        L_0x009e:
            r9 = r10
            r8 = r11
        L_0x00a0:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ all -> 0x0075 }
            boolean r10 = r13.booleanValue()     // Catch:{ all -> 0x0075 }
            if (r10 == 0) goto L_0x014d
            r10 = r0
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ all -> 0x0075 }
            int r10 = r10.length()     // Catch:{ all -> 0x0075 }
            if (r10 == 0) goto L_0x0145
            int r10 = r0.length()     // Catch:{ all -> 0x0075 }
            if (r10 != r3) goto L_0x00c2
            r10 = 0
            char r10 = r0.charAt(r10)     // Catch:{ all -> 0x0075 }
            r11 = 48
            if (r10 != r11) goto L_0x00c2
            r10 = r4
            goto L_0x00c9
        L_0x00c2:
            r10 = r0
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ all -> 0x0075 }
            long r10 = io.ktor.http.cio.internals.CharsKt.parseHexLong(r10)     // Catch:{ all -> 0x0075 }
        L_0x00c9:
            int r13 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r13 <= 0) goto L_0x00e4
            r12.L$0 = r9     // Catch:{ all -> 0x0075 }
            r12.L$1 = r8     // Catch:{ all -> 0x0075 }
            r12.L$2 = r0     // Catch:{ all -> 0x0075 }
            r12.J$0 = r6     // Catch:{ all -> 0x0075 }
            r12.J$1 = r10     // Catch:{ all -> 0x0075 }
            r12.label = r2     // Catch:{ all -> 0x0075 }
            java.lang.Object r13 = io.ktor.utils.io.ByteReadChannelJVMKt.copyTo(r9, r8, r10, r12)     // Catch:{ all -> 0x0075 }
            if (r13 != r14) goto L_0x00e0
            return r14
        L_0x00e0:
            r8.flush()     // Catch:{ all -> 0x0075 }
            long r6 = r6 + r10
        L_0x00e4:
            kotlin.text.StringsKt.clear(r0)     // Catch:{ all -> 0x0075 }
            r13 = r0
            java.lang.Appendable r13 = (java.lang.Appendable) r13     // Catch:{ all -> 0x0075 }
            r12.L$0 = r9     // Catch:{ all -> 0x0075 }
            r12.L$1 = r8     // Catch:{ all -> 0x0075 }
            r12.L$2 = r0     // Catch:{ all -> 0x0075 }
            r12.J$0 = r6     // Catch:{ all -> 0x0075 }
            r12.J$1 = r10     // Catch:{ all -> 0x0075 }
            r12.label = r1     // Catch:{ all -> 0x0075 }
            java.lang.Object r13 = r9.readUTF8LineTo(r13, r2, r12)     // Catch:{ all -> 0x0075 }
            if (r13 != r14) goto L_0x00fd
            return r14
        L_0x00fd:
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ all -> 0x0075 }
            boolean r13 = r13.booleanValue()     // Catch:{ all -> 0x0075 }
            if (r13 == 0) goto L_0x0129
            r13 = r0
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13     // Catch:{ all -> 0x0075 }
            int r13 = r13.length()     // Catch:{ all -> 0x0075 }
            if (r13 > 0) goto L_0x0121
            int r13 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r13 != 0) goto L_0x011d
            io.ktor.utils.io.pool.ObjectPool<java.lang.StringBuilder> r10 = ChunkSizeBufferPool
            r10.recycle(r0)
            io.ktor.utils.io.ByteWriteChannelKt.close(r8)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x011d:
            r11 = r8
            r10 = r9
            goto L_0x0085
        L_0x0121:
            java.io.EOFException r10 = new java.io.EOFException     // Catch:{ all -> 0x0075 }
            java.lang.String r11 = "Invalid chunk: content block should end with CR+LF"
            r10.<init>(r11)     // Catch:{ all -> 0x0075 }
            throw r10     // Catch:{ all -> 0x0075 }
        L_0x0129:
            java.io.EOFException r12 = new java.io.EOFException     // Catch:{ all -> 0x0075 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x0075 }
            r13.<init>()     // Catch:{ all -> 0x0075 }
            java.lang.String r14 = "Invalid chunk: content block of size "
            r13.append(r14)     // Catch:{ all -> 0x0075 }
            r13.append(r10)     // Catch:{ all -> 0x0075 }
            java.lang.String r10 = " ended unexpectedly"
            r13.append(r10)     // Catch:{ all -> 0x0075 }
            java.lang.String r10 = r13.toString()     // Catch:{ all -> 0x0075 }
            r12.<init>(r10)     // Catch:{ all -> 0x0075 }
            throw r12     // Catch:{ all -> 0x0075 }
        L_0x0145:
            java.io.EOFException r10 = new java.io.EOFException     // Catch:{ all -> 0x0075 }
            java.lang.String r11 = "Invalid chunk size: empty"
            r10.<init>(r11)     // Catch:{ all -> 0x0075 }
            throw r10     // Catch:{ all -> 0x0075 }
        L_0x014d:
            java.io.EOFException r10 = new java.io.EOFException     // Catch:{ all -> 0x0075 }
            java.lang.String r11 = "Chunked stream has ended unexpectedly: no chunk size"
            r10.<init>(r11)     // Catch:{ all -> 0x0075 }
            throw r10     // Catch:{ all -> 0x0075 }
        L_0x0155:
            r10 = move-exception
            r8 = r11
        L_0x0157:
            r8.close(r10)     // Catch:{ all -> 0x015b }
            throw r10     // Catch:{ all -> 0x015b }
        L_0x015b:
            r10 = move-exception
            io.ktor.utils.io.pool.ObjectPool<java.lang.StringBuilder> r11 = ChunkSizeBufferPool
            r11.recycle(r0)
            io.ktor.utils.io.ByteWriteChannelKt.close(r8)
            goto L_0x0166
        L_0x0165:
            throw r10
        L_0x0166:
            goto L_0x0165
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.ChunkedTransferEncodingKt.decodeChunked(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object encodeChunked(ByteWriteChannel byteWriteChannel, CoroutineContext coroutineContext, Continuation<? super ReaderJob> continuation) {
        return CoroutinesKt.reader((CoroutineScope) GlobalScope.INSTANCE, coroutineContext, false, (Function2<? super ReaderScope, ? super Continuation<? super Unit>, ? extends Object>) new ChunkedTransferEncodingKt$encodeChunked$2(byteWriteChannel, (Continuation<? super ChunkedTransferEncodingKt$encodeChunked$2>) null));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005e, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005f, code lost:
        r2 = r3;
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0158, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0159, code lost:
        r2 = r12;
        r1 = r13;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00bd A[Catch:{ all -> 0x0158, all -> 0x005e }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d6 A[Catch:{ all -> 0x015c }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0132 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0175 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object encodeChunked(io.ktor.utils.io.ByteWriteChannel r18, io.ktor.utils.io.ByteReadChannel r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof io.ktor.http.cio.ChunkedTransferEncodingKt$encodeChunked$3
            if (r1 == 0) goto L_0x0016
            r1 = r0
            io.ktor.http.cio.ChunkedTransferEncodingKt$encodeChunked$3 r1 = (io.ktor.http.cio.ChunkedTransferEncodingKt$encodeChunked$3) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            io.ktor.http.cio.ChunkedTransferEncodingKt$encodeChunked$3 r1 = new io.ktor.http.cio.ChunkedTransferEncodingKt$encodeChunked$3
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            r5 = 5
            r6 = 4
            r7 = 3
            r8 = 2
            r9 = 1
            r10 = 0
            if (r3 == 0) goto L_0x00af
            if (r3 == r9) goto L_0x0094
            if (r3 == r8) goto L_0x007c
            if (r3 == r7) goto L_0x0062
            if (r3 == r6) goto L_0x004d
            if (r3 != r5) goto L_0x0045
            java.lang.Object r2 = r1.L$1
            io.ktor.utils.io.ByteReadChannel r2 = (io.ktor.utils.io.ByteReadChannel) r2
            java.lang.Object r1 = r1.L$0
            io.ktor.utils.io.ByteWriteChannel r1 = (io.ktor.utils.io.ByteWriteChannel) r1
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0042 }
            goto L_0x0176
        L_0x0042:
            r0 = move-exception
            goto L_0x017a
        L_0x0045:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x004d:
            java.lang.Object r2 = r1.L$2
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.Object r3 = r1.L$1
            io.ktor.utils.io.ByteReadChannel r3 = (io.ktor.utils.io.ByteReadChannel) r3
            java.lang.Object r1 = r1.L$0
            io.ktor.utils.io.ByteWriteChannel r1 = (io.ktor.utils.io.ByteWriteChannel) r1
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x005e }
            goto L_0x0157
        L_0x005e:
            r0 = move-exception
            r2 = r3
            goto L_0x017a
        L_0x0062:
            int r3 = r1.I$0
            java.lang.Object r3 = r1.L$3
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3
            java.lang.Object r11 = r1.L$2
            io.ktor.utils.io.ByteReadChannel r11 = (io.ktor.utils.io.ByteReadChannel) r11
            java.lang.Object r12 = r1.L$1
            io.ktor.utils.io.ByteReadChannel r12 = (io.ktor.utils.io.ByteReadChannel) r12
            java.lang.Object r13 = r1.L$0
            io.ktor.utils.io.ByteWriteChannel r13 = (io.ktor.utils.io.ByteWriteChannel) r13
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0091 }
        L_0x0077:
            r0 = r1
            r3 = r12
            r1 = r13
            goto L_0x0133
        L_0x007c:
            java.lang.Object r3 = r1.L$3
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3
            java.lang.Object r11 = r1.L$2
            io.ktor.utils.io.ByteReadChannel r11 = (io.ktor.utils.io.ByteReadChannel) r11
            java.lang.Object r12 = r1.L$1
            io.ktor.utils.io.ByteReadChannel r12 = (io.ktor.utils.io.ByteReadChannel) r12
            java.lang.Object r13 = r1.L$0
            io.ktor.utils.io.ByteWriteChannel r13 = (io.ktor.utils.io.ByteWriteChannel) r13
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0091 }
            goto L_0x011a
        L_0x0091:
            r0 = move-exception
            goto L_0x0142
        L_0x0094:
            java.lang.Object r3 = r1.L$2
            io.ktor.utils.io.ByteReadChannel r3 = (io.ktor.utils.io.ByteReadChannel) r3
            java.lang.Object r11 = r1.L$1
            io.ktor.utils.io.ByteReadChannel r11 = (io.ktor.utils.io.ByteReadChannel) r11
            java.lang.Object r12 = r1.L$0
            io.ktor.utils.io.ByteWriteChannel r12 = (io.ktor.utils.io.ByteWriteChannel) r12
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x00aa }
            r16 = r3
            r3 = r1
            r1 = r12
            r12 = r16
            goto L_0x00d2
        L_0x00aa:
            r0 = move-exception
            r2 = r11
            r1 = r12
            goto L_0x017a
        L_0x00af:
            kotlin.ResultKt.throwOnFailure(r0)
            r3 = r19
            r0 = r1
            r1 = r18
        L_0x00b7:
            boolean r11 = r3.isClosedForRead()     // Catch:{ all -> 0x005e }
            if (r11 != 0) goto L_0x015f
            r0.L$0 = r1     // Catch:{ all -> 0x005e }
            r0.L$1 = r3     // Catch:{ all -> 0x005e }
            r0.L$2 = r3     // Catch:{ all -> 0x005e }
            r0.L$3 = r10     // Catch:{ all -> 0x005e }
            r0.label = r9     // Catch:{ all -> 0x005e }
            java.lang.Object r11 = io.ktor.utils.io.ReadSessionKt.requestBuffer(r3, r9, r0)     // Catch:{ all -> 0x005e }
            if (r11 != r2) goto L_0x00ce
            return r2
        L_0x00ce:
            r12 = r3
            r3 = r0
            r0 = r11
            r11 = r12
        L_0x00d2:
            io.ktor.utils.io.core.Buffer r0 = (io.ktor.utils.io.core.Buffer) r0     // Catch:{ all -> 0x015c }
            if (r0 != 0) goto L_0x00dc
            io.ktor.utils.io.core.Buffer$Companion r0 = io.ktor.utils.io.core.Buffer.Companion     // Catch:{ all -> 0x015c }
            io.ktor.utils.io.core.Buffer r0 = r0.getEmpty()     // Catch:{ all -> 0x015c }
        L_0x00dc:
            r13 = r0
            java.nio.ByteBuffer r0 = r13.m155getMemorySK3TCg8()     // Catch:{ all -> 0x0136 }
            int r14 = r13.getReadPosition()     // Catch:{ all -> 0x0136 }
            long r14 = (long) r14     // Catch:{ all -> 0x0136 }
            int r9 = r13.getWritePosition()     // Catch:{ all -> 0x0136 }
            long r4 = (long) r9     // Catch:{ all -> 0x0136 }
            int r9 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r9 != 0) goto L_0x00fc
            r0 = 0
            r16 = r13
            r13 = r1
            r1 = r3
            r3 = r16
            r17 = r12
            r12 = r11
            r11 = r17
            goto L_0x0120
        L_0x00fc:
            int r9 = (int) r14     // Catch:{ all -> 0x0136 }
            int r5 = (int) r4     // Catch:{ all -> 0x0136 }
            r3.L$0 = r1     // Catch:{ all -> 0x0136 }
            r3.L$1 = r11     // Catch:{ all -> 0x0136 }
            r3.L$2 = r12     // Catch:{ all -> 0x0136 }
            r3.L$3 = r13     // Catch:{ all -> 0x0136 }
            r3.label = r8     // Catch:{ all -> 0x0136 }
            java.lang.Object r0 = m139writeChunkyRinSxo(r1, r0, r9, r5, r3)     // Catch:{ all -> 0x0136 }
            if (r0 != r2) goto L_0x010f
            return r2
        L_0x010f:
            r16 = r13
            r13 = r1
            r1 = r3
            r3 = r16
            r17 = r12
            r12 = r11
            r11 = r17
        L_0x011a:
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ all -> 0x0091 }
            int r0 = r0.intValue()     // Catch:{ all -> 0x0091 }
        L_0x0120:
            r1.L$0 = r13     // Catch:{ all -> 0x0091 }
            r1.L$1 = r12     // Catch:{ all -> 0x0091 }
            r1.L$2 = r11     // Catch:{ all -> 0x0091 }
            r1.L$3 = r3     // Catch:{ all -> 0x0091 }
            r1.I$0 = r0     // Catch:{ all -> 0x0091 }
            r1.label = r7     // Catch:{ all -> 0x0091 }
            java.lang.Object r0 = io.ktor.utils.io.ReadSessionKt.completeReadingFromBuffer(r11, r3, r0, r1)     // Catch:{ all -> 0x0091 }
            if (r0 != r2) goto L_0x0077
            return r2
        L_0x0133:
            r5 = 5
            r9 = 1
            goto L_0x00b7
        L_0x0136:
            r0 = move-exception
            r16 = r13
            r13 = r1
            r1 = r3
            r3 = r16
            r17 = r12
            r12 = r11
            r11 = r17
        L_0x0142:
            r1.L$0 = r13     // Catch:{ all -> 0x0158 }
            r1.L$1 = r12     // Catch:{ all -> 0x0158 }
            r1.L$2 = r0     // Catch:{ all -> 0x0158 }
            r1.L$3 = r10     // Catch:{ all -> 0x0158 }
            r1.label = r6     // Catch:{ all -> 0x0158 }
            r4 = 0
            java.lang.Object r1 = io.ktor.utils.io.ReadSessionKt.completeReadingFromBuffer(r11, r3, r4, r1)     // Catch:{ all -> 0x0158 }
            if (r1 != r2) goto L_0x0154
            return r2
        L_0x0154:
            r2 = r0
            r3 = r12
            r1 = r13
        L_0x0157:
            throw r2     // Catch:{ all -> 0x005e }
        L_0x0158:
            r0 = move-exception
            r2 = r12
            r1 = r13
            goto L_0x017a
        L_0x015c:
            r0 = move-exception
            r2 = r11
            goto L_0x017a
        L_0x015f:
            rethrowCloseCause(r3)     // Catch:{ all -> 0x005e }
            byte[] r4 = LastChunkBytes     // Catch:{ all -> 0x005e }
            r0.L$0 = r1     // Catch:{ all -> 0x005e }
            r0.L$1 = r3     // Catch:{ all -> 0x005e }
            r0.L$2 = r10     // Catch:{ all -> 0x005e }
            r0.L$3 = r10     // Catch:{ all -> 0x005e }
            r5 = 5
            r0.label = r5     // Catch:{ all -> 0x005e }
            java.lang.Object r0 = io.ktor.utils.io.ByteWriteChannelKt.writeFully(r1, r4, r0)     // Catch:{ all -> 0x005e }
            if (r0 != r2) goto L_0x0176
            return r2
        L_0x0176:
            r1.flush()
            goto L_0x0181
        L_0x017a:
            r1.close(r0)     // Catch:{ all -> 0x0184 }
            r2.cancel(r0)     // Catch:{ all -> 0x0184 }
            goto L_0x0176
        L_0x0181:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0184:
            r0 = move-exception
            r1.flush()
            goto L_0x018a
        L_0x0189:
            throw r0
        L_0x018a:
            goto L_0x0189
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.ChunkedTransferEncodingKt.encodeChunked(io.ktor.utils.io.ByteWriteChannel, io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final void rethrowCloseCause(ByteReadChannel byteReadChannel) {
        Throwable closedCause = byteReadChannel instanceof ByteChannel ? byteReadChannel.getClosedCause() : null;
        if (closedCause != null) {
            throw closedCause;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x009f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b4 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c4 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* renamed from: writeChunk-yRinSxo  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object m139writeChunkyRinSxo(io.ktor.utils.io.ByteWriteChannel r8, java.nio.ByteBuffer r9, int r10, int r11, kotlin.coroutines.Continuation<? super java.lang.Integer> r12) {
        /*
            boolean r0 = r12 instanceof io.ktor.http.cio.ChunkedTransferEncodingKt$writeChunk$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            io.ktor.http.cio.ChunkedTransferEncodingKt$writeChunk$1 r0 = (io.ktor.http.cio.ChunkedTransferEncodingKt$writeChunk$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            io.ktor.http.cio.ChunkedTransferEncodingKt$writeChunk$1 r0 = new io.ktor.http.cio.ChunkedTransferEncodingKt$writeChunk$1
            r0.<init>(r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L_0x0073
            if (r2 == r6) goto L_0x005f
            if (r2 == r5) goto L_0x004d
            if (r2 == r4) goto L_0x0042
            if (r2 != r3) goto L_0x003a
            int r8 = r0.I$0
            java.lang.Object r9 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r9 = (io.ktor.utils.io.ByteWriteChannel) r9
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00c5
        L_0x003a:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0042:
            int r8 = r0.I$0
            java.lang.Object r9 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r9 = (io.ktor.utils.io.ByteWriteChannel) r9
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00b6
        L_0x004d:
            int r8 = r0.I$2
            int r9 = r0.I$1
            int r10 = r0.I$0
            java.lang.Object r11 = r0.L$1
            java.nio.ByteBuffer r11 = (java.nio.ByteBuffer) r11
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r2 = (io.ktor.utils.io.ByteWriteChannel) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00a5
        L_0x005f:
            int r8 = r0.I$2
            int r11 = r0.I$1
            int r10 = r0.I$0
            java.lang.Object r9 = r0.L$1
            java.nio.ByteBuffer r9 = (java.nio.ByteBuffer) r9
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r2 = (io.ktor.utils.io.ByteWriteChannel) r2
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r8
            r8 = r2
            goto L_0x008b
        L_0x0073:
            kotlin.ResultKt.throwOnFailure(r12)
            int r12 = r11 - r10
            r0.L$0 = r8
            r0.L$1 = r9
            r0.I$0 = r10
            r0.I$1 = r11
            r0.I$2 = r12
            r0.label = r6
            java.lang.Object r2 = io.ktor.http.cio.internals.CharsKt.writeIntHex(r8, r12, r0)
            if (r2 != r1) goto L_0x008b
            return r1
        L_0x008b:
            r0.L$0 = r8
            r0.L$1 = r9
            r0.I$0 = r10
            r0.I$1 = r11
            r0.I$2 = r12
            r0.label = r5
            r2 = 3338(0xd0a, float:4.678E-42)
            java.lang.Object r2 = r8.writeShort(r2, r0)
            if (r2 != r1) goto L_0x00a0
            return r1
        L_0x00a0:
            r2 = r8
            r8 = r12
            r7 = r11
            r11 = r9
            r9 = r7
        L_0x00a5:
            r0.L$0 = r2
            r12 = 0
            r0.L$1 = r12
            r0.I$0 = r8
            r0.label = r4
            java.lang.Object r9 = r2.m154writeFullyJT6ljtQ(r11, r10, r9, r0)
            if (r9 != r1) goto L_0x00b5
            return r1
        L_0x00b5:
            r9 = r2
        L_0x00b6:
            byte[] r10 = CrLf
            r0.L$0 = r9
            r0.I$0 = r8
            r0.label = r3
            java.lang.Object r10 = io.ktor.utils.io.ByteWriteChannelKt.writeFully(r9, r10, r0)
            if (r10 != r1) goto L_0x00c5
            return r1
        L_0x00c5:
            r9.flush()
            java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.ChunkedTransferEncodingKt.m139writeChunkyRinSxo(io.ktor.utils.io.ByteWriteChannel, java.nio.ByteBuffer, int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
