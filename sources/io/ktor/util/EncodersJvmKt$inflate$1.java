package io.ktor.util;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.WriterScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.util.EncodersJvmKt$inflate$1", f = "EncodersJvm.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6}, l = {68, 85, 161, 164, 103, 109, 121}, m = "invokeSuspend", n = {"$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "magic", "format", "flags", "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "magic", "format", "flags", "extraLen", "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "n$iv", "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "totalSize", "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "totalSize", "$this$writer", "readBuffer", "writeBuffer", "inflater", "checksum", "totalSize"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "S$0", "B$0", "B$1", "L$0", "L$1", "L$2", "L$3", "L$4", "S$0", "B$0", "B$1", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* compiled from: EncodersJvm.kt */
final class EncodersJvmKt$inflate$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $gzip;
    final /* synthetic */ ByteReadChannel $source;
    byte B$0;
    byte B$1;
    int I$0;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    short S$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EncodersJvmKt$inflate$1(boolean z, ByteReadChannel byteReadChannel, Continuation<? super EncodersJvmKt$inflate$1> continuation) {
        super(2, continuation);
        this.$gzip = z;
        this.$source = byteReadChannel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        EncodersJvmKt$inflate$1 encodersJvmKt$inflate$1 = new EncodersJvmKt$inflate$1(this.$gzip, this.$source, continuation);
        encodersJvmKt$inflate$1.L$0 = obj;
        return encodersJvmKt$inflate$1;
    }

    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((EncodersJvmKt$inflate$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v58, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v17, resolved type: io.ktor.utils.io.WriterScope} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v15, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v35, resolved type: java.util.zip.Inflater} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v16, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v24, resolved type: java.nio.ByteBuffer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v31, resolved type: java.nio.ByteBuffer} */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x037e, code lost:
        if (r6 != r0) goto L_0x0381;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0380, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0381, code lost:
        r7 = r5;
        r5 = r4;
        r4 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0384, code lost:
        r3.element = r2 + ((java.lang.Number) r6).intValue();
        r11.position(r11.limit() - r9.getRemaining());
        r3 = r4;
        r4 = r5;
        r5 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x039f, code lost:
        if (r1.$gzip == false) goto L_0x0421;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x03a7, code lost:
        if (r11.remaining() != 8) goto L_0x03fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x03a9, code lost:
        r11.order(java.nio.ByteOrder.LITTLE_ENDIAN);
        r0 = r11.getInt(r11.position());
        r2 = r11.getInt(r11.position() + 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x03c5, code lost:
        if (((int) r5.getValue()) != r0) goto L_0x03f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x03c9, code lost:
        if (r3.element != r2) goto L_0x03cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x03f0, code lost:
        throw new java.lang.IllegalStateException(("Gzip size invalid. Expected " + r2 + ", actual " + r3.element).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x03fc, code lost:
        throw new java.lang.IllegalStateException("Gzip checksum invalid.".toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0420, code lost:
        throw new java.lang.IllegalStateException(("Expected 8 bytes in the trailer. Actual: " + r11.remaining() + " $").toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0427, code lost:
        if ((!r11.hasRemaining()) == false) goto L_0x043d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0429, code lost:
        r9.end();
        io.ktor.util.cio.ByteBufferPoolKt.getKtorDefaultPool().recycle(r11);
        io.ktor.util.cio.ByteBufferPoolKt.getKtorDefaultPool().recycle(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x043c, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0448, code lost:
        throw new java.lang.IllegalStateException("Check failed.".toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0449, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x044a, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x044b, code lost:
        r10 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0154, code lost:
        r11 = (io.ktor.utils.io.core.ByteReadPacket) r11;
        r12 = r11;
        r13 = io.ktor.utils.io.core.InputLittleEndianKt.readShortLittleEndian(r12);
        r14 = r11.readByte();
        r11 = r11.readByte();
        io.ktor.utils.io.core.InputKt.discard(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x016a, code lost:
        if ((r11 & 4) == 0) goto L_0x01ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x016c, code lost:
        r1.L$0 = r10;
        r1.L$1 = r9;
        r1.L$2 = r7;
        r1.L$3 = r6;
        r1.L$4 = r2;
        r1.S$0 = r13;
        r1.B$0 = r14;
        r1.B$1 = r11;
        r1.label = 2;
        r12 = r1.$source.readShort(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0187, code lost:
        if (r12 != r0) goto L_0x018a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0189, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x018a, code lost:
        r15 = r7;
        r7 = r9;
        r9 = r2;
        r2 = r11;
        r11 = r14;
        r14 = r6;
        r6 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0191, code lost:
        r8 = (long) ((java.lang.Number) r12).shortValue();
        r1.L$0 = r6;
        r1.L$1 = r7;
        r1.L$2 = r15;
        r1.L$3 = r14;
        r5 = r9;
        r1.L$4 = r5;
        r1.S$0 = r13;
        r1.B$0 = r11;
        r1.B$1 = r2;
        r1.J$0 = r8;
        r20 = r2;
        r1.label = 3;
        r2 = r1.$source.discard(r8, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x01bc, code lost:
        if (r2 != r0) goto L_0x01bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x01be, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x01bf, code lost:
        r9 = r8;
        r12 = r13;
        r13 = r5;
        r5 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x01cc, code lost:
        if (((java.lang.Number) r2).longValue() != r9) goto L_0x01d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x01ce, code lost:
        r2 = r13;
        r13 = r12;
        r18 = r11;
        r11 = r5;
        r5 = r14;
        r14 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x01eb, code lost:
        throw new java.io.EOFException("Unable to discard " + r9 + " bytes");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x01ec, code lost:
        r5 = r6;
        r15 = r7;
        r7 = r9;
        r6 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x01f2, code lost:
        if (r13 != -29921) goto L_0x028c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x01f6, code lost:
        if (r14 != 8) goto L_0x026f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x01fe, code lost:
        if ((!io.ktor.util.EncodersJvmKt.has(r11, 8)) == false) goto L_0x0263;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0207, code lost:
        if ((!io.ktor.util.EncodersJvmKt.has(r11, 16)) == false) goto L_0x0257;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x020e, code lost:
        if (io.ktor.util.EncodersJvmKt.has(r11, 2) == false) goto L_0x0254;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0210, code lost:
        r1.L$0 = r6;
        r1.L$1 = r7;
        r1.L$2 = r15;
        r1.L$3 = r5;
        r1.L$4 = r2;
        r1.J$0 = 2;
        r1.label = 4;
        r8 = r1.$source.discard(2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x022a, code lost:
        if (r8 != r0) goto L_0x022d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x022c, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x022d, code lost:
        r13 = r6;
        r9 = 2;
        r11 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0238, code lost:
        if (((java.lang.Number) r8).longValue() != r9) goto L_0x023f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x023a, code lost:
        r9 = r5;
        r15 = r11;
        r10 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x023d, code lost:
        r11 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0253, code lost:
        throw new java.io.EOFException("Unable to discard " + r9 + " bytes");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0254, code lost:
        r9 = r5;
        r10 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0262, code lost:
        throw new java.lang.IllegalStateException("Gzip file comment not supported".toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x026e, code lost:
        throw new java.lang.IllegalStateException("Gzip file name not supported".toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x028b, code lost:
        throw new java.lang.IllegalStateException(("Deflater method unsupported: " + r14 + '.').toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x02a3, code lost:
        throw new java.lang.IllegalStateException(("GZIP magic invalid: " + r13).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x02ac, code lost:
        r4 = r10;
        r10 = r15;
        r3 = r2;
        r2 = new kotlin.jvm.internal.Ref.IntRef();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x02b9, code lost:
        if (r1.$source.isClosedForRead() != false) goto L_0x0348;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x02bb, code lost:
        r1.L$0 = r4;
        r1.L$1 = r11;
        r1.L$2 = r10;
        r1.L$3 = r9;
        r1.L$4 = r3;
        r1.L$5 = r2;
        r1.L$6 = null;
        r1.label = 5;
        r5 = r1.$source.readAvailable(r11, (kotlin.coroutines.Continuation<? super java.lang.Integer>) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x02d6, code lost:
        if (r5 != r0) goto L_0x02d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x02d8, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x02df, code lost:
        if (((java.lang.Number) r5).intValue() <= 0) goto L_0x02b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x02e1, code lost:
        r11.flip();
        r9.setInput(r11.array(), r11.position(), r11.remaining());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x02f7, code lost:
        if (r9.needsInput() != false) goto L_0x0343;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x02fd, code lost:
        if (r9.finished() != false) goto L_0x0343;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x02ff, code lost:
        r5 = r2.element;
        r1.L$0 = r4;
        r1.L$1 = r11;
        r1.L$2 = r10;
        r1.L$3 = r9;
        r1.L$4 = r3;
        r1.L$5 = r2;
        r1.L$6 = r2;
        r1.I$0 = r5;
        r1.label = 6;
        r6 = io.ktor.util.EncodersJvmKt.inflateTo(r9, r4.getChannel(), r10, r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0322, code lost:
        if (r6 != r0) goto L_0x0325;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0324, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0325, code lost:
        r7 = r4;
        r4 = r2;
        r2 = r5;
        r5 = r3;
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x032a, code lost:
        r3.element = r2 + ((java.lang.Number) r6).intValue();
        r11.position(r11.limit() - r9.getRemaining());
        r2 = r4;
        r3 = r5;
        r4 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0343, code lost:
        r11.compact();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0348, code lost:
        r5 = r1.$source.getClosedCause();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x034e, code lost:
        if (r5 != null) goto L_0x0449;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0350, code lost:
        r11.flip();
        r5 = r3;
        r3 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0359, code lost:
        if (r9.finished() != false) goto L_0x039d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x035b, code lost:
        r2 = r3.element;
        r1.L$0 = r4;
        r1.L$1 = r11;
        r1.L$2 = r10;
        r1.L$3 = r9;
        r1.L$4 = r5;
        r1.L$5 = r3;
        r1.L$6 = r3;
        r1.I$0 = r2;
        r1.label = 7;
        r6 = io.ktor.util.EncodersJvmKt.inflateTo(r9, r4.getChannel(), r10, r5, r1);
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) {
        /*
            r19 = this;
            r1 = r19
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            java.lang.String r3 = " bytes"
            java.lang.String r4 = "Unable to discard "
            r5 = 2
            r8 = 1
            switch(r2) {
                case 0: goto L_0x010e;
                case 1: goto L_0x00f4;
                case 2: goto L_0x00cd;
                case 3: goto L_0x00a9;
                case 4: goto L_0x008b;
                case 5: goto L_0x0066;
                case 6: goto L_0x0040;
                case 7: goto L_0x0019;
                default: goto L_0x0011;
            }
        L_0x0011:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0019:
            int r2 = r1.I$0
            java.lang.Object r3 = r1.L$6
            kotlin.jvm.internal.Ref$IntRef r3 = (kotlin.jvm.internal.Ref.IntRef) r3
            java.lang.Object r4 = r1.L$5
            kotlin.jvm.internal.Ref$IntRef r4 = (kotlin.jvm.internal.Ref.IntRef) r4
            java.lang.Object r5 = r1.L$4
            java.util.zip.CRC32 r5 = (java.util.zip.CRC32) r5
            java.lang.Object r9 = r1.L$3
            java.util.zip.Inflater r9 = (java.util.zip.Inflater) r9
            java.lang.Object r10 = r1.L$2
            java.nio.ByteBuffer r10 = (java.nio.ByteBuffer) r10
            java.lang.Object r11 = r1.L$1
            java.nio.ByteBuffer r11 = (java.nio.ByteBuffer) r11
            java.lang.Object r12 = r1.L$0
            io.ktor.utils.io.WriterScope r12 = (io.ktor.utils.io.WriterScope) r12
            kotlin.ResultKt.throwOnFailure(r20)     // Catch:{ all -> 0x0088 }
            r6 = r20
            r7 = r5
            r5 = r12
            goto L_0x0384
        L_0x0040:
            int r2 = r1.I$0
            java.lang.Object r3 = r1.L$6
            kotlin.jvm.internal.Ref$IntRef r3 = (kotlin.jvm.internal.Ref.IntRef) r3
            java.lang.Object r4 = r1.L$5
            kotlin.jvm.internal.Ref$IntRef r4 = (kotlin.jvm.internal.Ref.IntRef) r4
            java.lang.Object r5 = r1.L$4
            java.util.zip.CRC32 r5 = (java.util.zip.CRC32) r5
            java.lang.Object r9 = r1.L$3
            java.util.zip.Inflater r9 = (java.util.zip.Inflater) r9
            java.lang.Object r10 = r1.L$2
            java.nio.ByteBuffer r10 = (java.nio.ByteBuffer) r10
            java.lang.Object r11 = r1.L$1
            java.nio.ByteBuffer r11 = (java.nio.ByteBuffer) r11
            java.lang.Object r12 = r1.L$0
            io.ktor.utils.io.WriterScope r12 = (io.ktor.utils.io.WriterScope) r12
            kotlin.ResultKt.throwOnFailure(r20)     // Catch:{ all -> 0x0088 }
            r6 = r20
            r7 = r12
            goto L_0x032a
        L_0x0066:
            java.lang.Object r2 = r1.L$5
            kotlin.jvm.internal.Ref$IntRef r2 = (kotlin.jvm.internal.Ref.IntRef) r2
            java.lang.Object r3 = r1.L$4
            java.util.zip.CRC32 r3 = (java.util.zip.CRC32) r3
            java.lang.Object r4 = r1.L$3
            r9 = r4
            java.util.zip.Inflater r9 = (java.util.zip.Inflater) r9
            java.lang.Object r4 = r1.L$2
            r10 = r4
            java.nio.ByteBuffer r10 = (java.nio.ByteBuffer) r10
            java.lang.Object r4 = r1.L$1
            r11 = r4
            java.nio.ByteBuffer r11 = (java.nio.ByteBuffer) r11
            java.lang.Object r4 = r1.L$0
            io.ktor.utils.io.WriterScope r4 = (io.ktor.utils.io.WriterScope) r4
            kotlin.ResultKt.throwOnFailure(r20)     // Catch:{ all -> 0x0088 }
            r5 = r20
            goto L_0x02d9
        L_0x0088:
            r0 = move-exception
            goto L_0x044c
        L_0x008b:
            long r9 = r1.J$0
            java.lang.Object r2 = r1.L$4
            java.util.zip.CRC32 r2 = (java.util.zip.CRC32) r2
            java.lang.Object r5 = r1.L$3
            java.util.zip.Inflater r5 = (java.util.zip.Inflater) r5
            java.lang.Object r11 = r1.L$2
            java.nio.ByteBuffer r11 = (java.nio.ByteBuffer) r11
            java.lang.Object r12 = r1.L$1
            java.nio.ByteBuffer r12 = (java.nio.ByteBuffer) r12
            java.lang.Object r13 = r1.L$0
            io.ktor.utils.io.WriterScope r13 = (io.ktor.utils.io.WriterScope) r13
            kotlin.ResultKt.throwOnFailure(r20)
            r8 = r20
            r7 = r12
            goto L_0x0230
        L_0x00a9:
            long r9 = r1.J$0
            byte r2 = r1.B$1
            byte r11 = r1.B$0
            short r12 = r1.S$0
            java.lang.Object r13 = r1.L$4
            java.util.zip.CRC32 r13 = (java.util.zip.CRC32) r13
            java.lang.Object r14 = r1.L$3
            java.util.zip.Inflater r14 = (java.util.zip.Inflater) r14
            java.lang.Object r15 = r1.L$2
            java.nio.ByteBuffer r15 = (java.nio.ByteBuffer) r15
            java.lang.Object r7 = r1.L$1
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r6 = r1.L$0
            io.ktor.utils.io.WriterScope r6 = (io.ktor.utils.io.WriterScope) r6
            kotlin.ResultKt.throwOnFailure(r20)
            r5 = r2
            r2 = r20
            goto L_0x01c4
        L_0x00cd:
            byte r2 = r1.B$1
            byte r6 = r1.B$0
            short r7 = r1.S$0
            java.lang.Object r9 = r1.L$4
            java.util.zip.CRC32 r9 = (java.util.zip.CRC32) r9
            java.lang.Object r10 = r1.L$3
            java.util.zip.Inflater r10 = (java.util.zip.Inflater) r10
            java.lang.Object r11 = r1.L$2
            java.nio.ByteBuffer r11 = (java.nio.ByteBuffer) r11
            java.lang.Object r12 = r1.L$1
            java.nio.ByteBuffer r12 = (java.nio.ByteBuffer) r12
            java.lang.Object r13 = r1.L$0
            io.ktor.utils.io.WriterScope r13 = (io.ktor.utils.io.WriterScope) r13
            kotlin.ResultKt.throwOnFailure(r20)
            r14 = r10
            r15 = r11
            r11 = r6
            r6 = r13
            r13 = r7
            r7 = r12
            r12 = r20
            goto L_0x0191
        L_0x00f4:
            java.lang.Object r2 = r1.L$4
            java.util.zip.CRC32 r2 = (java.util.zip.CRC32) r2
            java.lang.Object r6 = r1.L$3
            java.util.zip.Inflater r6 = (java.util.zip.Inflater) r6
            java.lang.Object r7 = r1.L$2
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.lang.Object r9 = r1.L$1
            java.nio.ByteBuffer r9 = (java.nio.ByteBuffer) r9
            java.lang.Object r10 = r1.L$0
            io.ktor.utils.io.WriterScope r10 = (io.ktor.utils.io.WriterScope) r10
            kotlin.ResultKt.throwOnFailure(r20)
            r11 = r20
            goto L_0x0154
        L_0x010e:
            kotlin.ResultKt.throwOnFailure(r20)
            java.lang.Object r2 = r1.L$0
            r10 = r2
            io.ktor.utils.io.WriterScope r10 = (io.ktor.utils.io.WriterScope) r10
            io.ktor.utils.io.pool.ObjectPool r2 = io.ktor.util.cio.ByteBufferPoolKt.getKtorDefaultPool()
            java.lang.Object r2 = r2.borrow()
            r9 = r2
            java.nio.ByteBuffer r9 = (java.nio.ByteBuffer) r9
            io.ktor.utils.io.pool.ObjectPool r2 = io.ktor.util.cio.ByteBufferPoolKt.getKtorDefaultPool()
            java.lang.Object r2 = r2.borrow()
            r7 = r2
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            java.util.zip.Inflater r6 = new java.util.zip.Inflater
            r6.<init>(r8)
            java.util.zip.CRC32 r2 = new java.util.zip.CRC32
            r2.<init>()
            boolean r11 = r1.$gzip
            if (r11 == 0) goto L_0x02a4
            io.ktor.utils.io.ByteReadChannel r11 = r1.$source
            r12 = r1
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
            r1.L$0 = r10
            r1.L$1 = r9
            r1.L$2 = r7
            r1.L$3 = r6
            r1.L$4 = r2
            r1.label = r8
            r13 = 10
            java.lang.Object r11 = r11.readPacket(r13, r12)
            if (r11 != r0) goto L_0x0154
            return r0
        L_0x0154:
            io.ktor.utils.io.core.ByteReadPacket r11 = (io.ktor.utils.io.core.ByteReadPacket) r11
            r12 = r11
            io.ktor.utils.io.core.Input r12 = (io.ktor.utils.io.core.Input) r12
            short r13 = io.ktor.utils.io.core.InputLittleEndianKt.readShortLittleEndian((io.ktor.utils.io.core.Input) r12)
            byte r14 = r11.readByte()
            byte r11 = r11.readByte()
            io.ktor.utils.io.core.InputKt.discard(r12)
            r12 = r11 & 4
            if (r12 == 0) goto L_0x01ec
            io.ktor.utils.io.ByteReadChannel r12 = r1.$source
            r15 = r1
            kotlin.coroutines.Continuation r15 = (kotlin.coroutines.Continuation) r15
            r1.L$0 = r10
            r1.L$1 = r9
            r1.L$2 = r7
            r1.L$3 = r6
            r1.L$4 = r2
            r1.S$0 = r13
            r1.B$0 = r14
            r1.B$1 = r11
            r1.label = r5
            java.lang.Object r12 = r12.readShort(r15)
            if (r12 != r0) goto L_0x018a
            return r0
        L_0x018a:
            r15 = r7
            r7 = r9
            r9 = r2
            r2 = r11
            r11 = r14
            r14 = r6
            r6 = r10
        L_0x0191:
            java.lang.Number r12 = (java.lang.Number) r12
            short r10 = r12.shortValue()
            r20 = r9
            long r8 = (long) r10
            io.ktor.utils.io.ByteReadChannel r10 = r1.$source
            r12 = r1
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
            r1.L$0 = r6
            r1.L$1 = r7
            r1.L$2 = r15
            r1.L$3 = r14
            r5 = r20
            r1.L$4 = r5
            r1.S$0 = r13
            r1.B$0 = r11
            r1.B$1 = r2
            r1.J$0 = r8
            r20 = r2
            r2 = 3
            r1.label = r2
            java.lang.Object r2 = r10.discard(r8, r12)
            if (r2 != r0) goto L_0x01bf
            return r0
        L_0x01bf:
            r9 = r8
            r12 = r13
            r13 = r5
            r5 = r20
        L_0x01c4:
            java.lang.Number r2 = (java.lang.Number) r2
            long r16 = r2.longValue()
            int r2 = (r16 > r9 ? 1 : (r16 == r9 ? 0 : -1))
            if (r2 != 0) goto L_0x01d7
            r2 = r13
            r13 = r12
            r18 = r11
            r11 = r5
            r5 = r14
            r14 = r18
            goto L_0x01f0
        L_0x01d7:
            java.io.EOFException r0 = new java.io.EOFException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r4)
            r2.append(r9)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x01ec:
            r5 = r6
            r15 = r7
            r7 = r9
            r6 = r10
        L_0x01f0:
            r8 = -29921(0xffffffffffff8b1f, float:NaN)
            if (r13 != r8) goto L_0x028c
            r8 = 8
            if (r14 != r8) goto L_0x026f
            boolean r9 = io.ktor.util.EncodersJvmKt.has(r11, r8)
            r8 = 1
            r9 = r9 ^ r8
            if (r9 == 0) goto L_0x0263
            r9 = 16
            boolean r9 = io.ktor.util.EncodersJvmKt.has(r11, r9)
            r9 = r9 ^ r8
            if (r9 == 0) goto L_0x0257
            r8 = 2
            boolean r8 = io.ktor.util.EncodersJvmKt.has(r11, r8)
            if (r8 == 0) goto L_0x0254
            io.ktor.utils.io.ByteReadChannel r8 = r1.$source
            r9 = r1
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r1.L$0 = r6
            r1.L$1 = r7
            r1.L$2 = r15
            r1.L$3 = r5
            r1.L$4 = r2
            r10 = 2
            r1.J$0 = r10
            r13 = 4
            r1.label = r13
            java.lang.Object r8 = r8.discard(r10, r9)
            if (r8 != r0) goto L_0x022d
            return r0
        L_0x022d:
            r13 = r6
            r9 = r10
            r11 = r15
        L_0x0230:
            java.lang.Number r8 = (java.lang.Number) r8
            long r14 = r8.longValue()
            int r6 = (r14 > r9 ? 1 : (r14 == r9 ? 0 : -1))
            if (r6 != 0) goto L_0x023f
            r9 = r5
            r15 = r11
            r10 = r13
        L_0x023d:
            r11 = r7
            goto L_0x02a7
        L_0x023f:
            java.io.EOFException r0 = new java.io.EOFException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r4)
            r2.append(r9)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0254:
            r9 = r5
            r10 = r6
            goto L_0x023d
        L_0x0257:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "Gzip file comment not supported"
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0263:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "Gzip file name not supported"
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x026f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Deflater method unsupported: "
            r0.<init>(r2)
            r0.append(r14)
            r2 = 46
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r0 = r0.toString()
            r2.<init>(r0)
            throw r2
        L_0x028c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "GZIP magic invalid: "
            r0.<init>(r2)
            r0.append(r13)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r0 = r0.toString()
            r2.<init>(r0)
            throw r2
        L_0x02a4:
            r15 = r7
            r11 = r9
            r9 = r6
        L_0x02a7:
            kotlin.jvm.internal.Ref$IntRef r3 = new kotlin.jvm.internal.Ref$IntRef     // Catch:{ all -> 0x044a }
            r3.<init>()     // Catch:{ all -> 0x044a }
            r4 = r10
            r10 = r15
            r18 = r3
            r3 = r2
            r2 = r18
        L_0x02b3:
            io.ktor.utils.io.ByteReadChannel r5 = r1.$source     // Catch:{ all -> 0x0088 }
            boolean r5 = r5.isClosedForRead()     // Catch:{ all -> 0x0088 }
            if (r5 != 0) goto L_0x0348
            io.ktor.utils.io.ByteReadChannel r5 = r1.$source     // Catch:{ all -> 0x0088 }
            r6 = r1
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch:{ all -> 0x0088 }
            r1.L$0 = r4     // Catch:{ all -> 0x0088 }
            r1.L$1 = r11     // Catch:{ all -> 0x0088 }
            r1.L$2 = r10     // Catch:{ all -> 0x0088 }
            r1.L$3 = r9     // Catch:{ all -> 0x0088 }
            r1.L$4 = r3     // Catch:{ all -> 0x0088 }
            r1.L$5 = r2     // Catch:{ all -> 0x0088 }
            r7 = 0
            r1.L$6 = r7     // Catch:{ all -> 0x0088 }
            r7 = 5
            r1.label = r7     // Catch:{ all -> 0x0088 }
            java.lang.Object r5 = r5.readAvailable((java.nio.ByteBuffer) r11, (kotlin.coroutines.Continuation<? super java.lang.Integer>) r6)     // Catch:{ all -> 0x0088 }
            if (r5 != r0) goto L_0x02d9
            return r0
        L_0x02d9:
            java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ all -> 0x0088 }
            int r5 = r5.intValue()     // Catch:{ all -> 0x0088 }
            if (r5 <= 0) goto L_0x02b3
            r11.flip()     // Catch:{ all -> 0x0088 }
            byte[] r5 = r11.array()     // Catch:{ all -> 0x0088 }
            int r6 = r11.position()     // Catch:{ all -> 0x0088 }
            int r7 = r11.remaining()     // Catch:{ all -> 0x0088 }
            r9.setInput(r5, r6, r7)     // Catch:{ all -> 0x0088 }
        L_0x02f3:
            boolean r5 = r9.needsInput()     // Catch:{ all -> 0x0088 }
            if (r5 != 0) goto L_0x0343
            boolean r5 = r9.finished()     // Catch:{ all -> 0x0088 }
            if (r5 != 0) goto L_0x0343
            int r5 = r2.element     // Catch:{ all -> 0x0088 }
            io.ktor.utils.io.ByteWriteChannel r6 = r4.getChannel()     // Catch:{ all -> 0x0088 }
            r7 = r3
            java.util.zip.Checksum r7 = (java.util.zip.Checksum) r7     // Catch:{ all -> 0x0088 }
            r8 = r1
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8     // Catch:{ all -> 0x0088 }
            r1.L$0 = r4     // Catch:{ all -> 0x0088 }
            r1.L$1 = r11     // Catch:{ all -> 0x0088 }
            r1.L$2 = r10     // Catch:{ all -> 0x0088 }
            r1.L$3 = r9     // Catch:{ all -> 0x0088 }
            r1.L$4 = r3     // Catch:{ all -> 0x0088 }
            r1.L$5 = r2     // Catch:{ all -> 0x0088 }
            r1.L$6 = r2     // Catch:{ all -> 0x0088 }
            r1.I$0 = r5     // Catch:{ all -> 0x0088 }
            r13 = 6
            r1.label = r13     // Catch:{ all -> 0x0088 }
            java.lang.Object r6 = io.ktor.util.EncodersJvmKt.inflateTo(r9, r6, r10, r7, r8)     // Catch:{ all -> 0x0088 }
            if (r6 != r0) goto L_0x0325
            return r0
        L_0x0325:
            r7 = r4
            r4 = r2
            r2 = r5
            r5 = r3
            r3 = r4
        L_0x032a:
            java.lang.Number r6 = (java.lang.Number) r6     // Catch:{ all -> 0x0088 }
            int r6 = r6.intValue()     // Catch:{ all -> 0x0088 }
            int r2 = r2 + r6
            r3.element = r2     // Catch:{ all -> 0x0088 }
            int r2 = r11.limit()     // Catch:{ all -> 0x0088 }
            int r3 = r9.getRemaining()     // Catch:{ all -> 0x0088 }
            int r2 = r2 - r3
            r11.position(r2)     // Catch:{ all -> 0x0088 }
            r2 = r4
            r3 = r5
            r4 = r7
            goto L_0x02f3
        L_0x0343:
            r11.compact()     // Catch:{ all -> 0x0088 }
            goto L_0x02b3
        L_0x0348:
            io.ktor.utils.io.ByteReadChannel r5 = r1.$source     // Catch:{ all -> 0x0088 }
            java.lang.Throwable r5 = r5.getClosedCause()     // Catch:{ all -> 0x0088 }
            if (r5 != 0) goto L_0x0449
            r11.flip()     // Catch:{ all -> 0x0088 }
            r5 = r3
            r3 = r2
        L_0x0355:
            boolean r2 = r9.finished()     // Catch:{ all -> 0x0088 }
            if (r2 != 0) goto L_0x039d
            int r2 = r3.element     // Catch:{ all -> 0x0088 }
            io.ktor.utils.io.ByteWriteChannel r6 = r4.getChannel()     // Catch:{ all -> 0x0088 }
            r7 = r5
            java.util.zip.Checksum r7 = (java.util.zip.Checksum) r7     // Catch:{ all -> 0x0088 }
            r8 = r1
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8     // Catch:{ all -> 0x0088 }
            r1.L$0 = r4     // Catch:{ all -> 0x0088 }
            r1.L$1 = r11     // Catch:{ all -> 0x0088 }
            r1.L$2 = r10     // Catch:{ all -> 0x0088 }
            r1.L$3 = r9     // Catch:{ all -> 0x0088 }
            r1.L$4 = r5     // Catch:{ all -> 0x0088 }
            r1.L$5 = r3     // Catch:{ all -> 0x0088 }
            r1.L$6 = r3     // Catch:{ all -> 0x0088 }
            r1.I$0 = r2     // Catch:{ all -> 0x0088 }
            r13 = 7
            r1.label = r13     // Catch:{ all -> 0x0088 }
            java.lang.Object r6 = io.ktor.util.EncodersJvmKt.inflateTo(r9, r6, r10, r7, r8)     // Catch:{ all -> 0x0088 }
            if (r6 != r0) goto L_0x0381
            return r0
        L_0x0381:
            r7 = r5
            r5 = r4
            r4 = r3
        L_0x0384:
            java.lang.Number r6 = (java.lang.Number) r6     // Catch:{ all -> 0x0088 }
            int r6 = r6.intValue()     // Catch:{ all -> 0x0088 }
            int r2 = r2 + r6
            r3.element = r2     // Catch:{ all -> 0x0088 }
            int r2 = r11.limit()     // Catch:{ all -> 0x0088 }
            int r3 = r9.getRemaining()     // Catch:{ all -> 0x0088 }
            int r2 = r2 - r3
            r11.position(r2)     // Catch:{ all -> 0x0088 }
            r3 = r4
            r4 = r5
            r5 = r7
            goto L_0x0355
        L_0x039d:
            boolean r0 = r1.$gzip     // Catch:{ all -> 0x0088 }
            if (r0 == 0) goto L_0x0421
            int r0 = r11.remaining()     // Catch:{ all -> 0x0088 }
            r2 = 8
            if (r0 != r2) goto L_0x03fd
            java.nio.ByteOrder r0 = java.nio.ByteOrder.LITTLE_ENDIAN     // Catch:{ all -> 0x0088 }
            r11.order(r0)     // Catch:{ all -> 0x0088 }
            int r0 = r11.position()     // Catch:{ all -> 0x0088 }
            int r0 = r11.getInt(r0)     // Catch:{ all -> 0x0088 }
            int r2 = r11.position()     // Catch:{ all -> 0x0088 }
            r4 = 4
            int r2 = r2 + r4
            int r2 = r11.getInt(r2)     // Catch:{ all -> 0x0088 }
            long r4 = r5.getValue()     // Catch:{ all -> 0x0088 }
            int r5 = (int) r4     // Catch:{ all -> 0x0088 }
            if (r5 != r0) goto L_0x03f1
            int r0 = r3.element     // Catch:{ all -> 0x0088 }
            if (r0 != r2) goto L_0x03cc
            goto L_0x0429
        L_0x03cc:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0088 }
            r0.<init>()     // Catch:{ all -> 0x0088 }
            java.lang.String r4 = "Gzip size invalid. Expected "
            r0.append(r4)     // Catch:{ all -> 0x0088 }
            r0.append(r2)     // Catch:{ all -> 0x0088 }
            java.lang.String r2 = ", actual "
            r0.append(r2)     // Catch:{ all -> 0x0088 }
            int r2 = r3.element     // Catch:{ all -> 0x0088 }
            r0.append(r2)     // Catch:{ all -> 0x0088 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0088 }
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0088 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0088 }
            r2.<init>(r0)     // Catch:{ all -> 0x0088 }
            throw r2     // Catch:{ all -> 0x0088 }
        L_0x03f1:
            java.lang.String r0 = "Gzip checksum invalid."
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0088 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0088 }
            r2.<init>(r0)     // Catch:{ all -> 0x0088 }
            throw r2     // Catch:{ all -> 0x0088 }
        L_0x03fd:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0088 }
            r0.<init>()     // Catch:{ all -> 0x0088 }
            java.lang.String r2 = "Expected 8 bytes in the trailer. Actual: "
            r0.append(r2)     // Catch:{ all -> 0x0088 }
            int r2 = r11.remaining()     // Catch:{ all -> 0x0088 }
            r0.append(r2)     // Catch:{ all -> 0x0088 }
            java.lang.String r2 = " $"
            r0.append(r2)     // Catch:{ all -> 0x0088 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0088 }
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0088 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0088 }
            r2.<init>(r0)     // Catch:{ all -> 0x0088 }
            throw r2     // Catch:{ all -> 0x0088 }
        L_0x0421:
            boolean r0 = r11.hasRemaining()     // Catch:{ all -> 0x0088 }
            r2 = 1
            r0 = r0 ^ r2
            if (r0 == 0) goto L_0x043d
        L_0x0429:
            r9.end()
            io.ktor.utils.io.pool.ObjectPool r0 = io.ktor.util.cio.ByteBufferPoolKt.getKtorDefaultPool()
            r0.recycle(r11)
            io.ktor.utils.io.pool.ObjectPool r0 = io.ktor.util.cio.ByteBufferPoolKt.getKtorDefaultPool()
            r0.recycle(r10)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x043d:
            java.lang.String r0 = "Check failed."
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0088 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0088 }
            r2.<init>(r0)     // Catch:{ all -> 0x0088 }
            throw r2     // Catch:{ all -> 0x0088 }
        L_0x0449:
            throw r5     // Catch:{ all -> 0x0088 }
        L_0x044a:
            r0 = move-exception
            r10 = r15
        L_0x044c:
            throw r0     // Catch:{ all -> 0x044d }
        L_0x044d:
            r0 = move-exception
            r2 = r0
            r9.end()
            io.ktor.utils.io.pool.ObjectPool r0 = io.ktor.util.cio.ByteBufferPoolKt.getKtorDefaultPool()
            r0.recycle(r11)
            io.ktor.utils.io.pool.ObjectPool r0 = io.ktor.util.cio.ByteBufferPoolKt.getKtorDefaultPool()
            r0.recycle(r10)
            goto L_0x0462
        L_0x0461:
            throw r2
        L_0x0462:
            goto L_0x0461
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.EncodersJvmKt$inflate$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
