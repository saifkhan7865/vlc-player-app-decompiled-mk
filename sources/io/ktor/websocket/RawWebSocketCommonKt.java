package io.ktor.websocket;

import io.ktor.utils.io.bits.DefaultAllocator;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a%\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a%\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"mask", "Lio/ktor/utils/io/core/ByteReadPacket;", "maskKey", "", "readFrame", "Lio/ktor/websocket/Frame;", "Lio/ktor/utils/io/ByteReadChannel;", "maxFrameSize", "", "lastOpcode", "(Lio/ktor/utils/io/ByteReadChannel;JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeFrame", "", "Lio/ktor/utils/io/ByteWriteChannel;", "frame", "masking", "", "(Lio/ktor/utils/io/ByteWriteChannel;Lio/ktor/websocket/Frame;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: RawWebSocketCommon.kt */
public final class RawWebSocketCommonKt {
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00d0, code lost:
        if (r11 >= 126) goto L_0x00d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00d2, code lost:
        r2 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00d7, code lost:
        if (r11 > 65535) goto L_0x00dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d9, code lost:
        r2 = 126;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00dc, code lost:
        r2 = 127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00de, code lost:
        if (r13 == false) goto L_0x00e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00e1, code lost:
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00e2, code lost:
        r0.L$0 = r10;
        r0.L$1 = r12;
        r0.Z$0 = r13;
        r0.I$0 = r11;
        r0.I$1 = r2;
        r0.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00f5, code lost:
        if (r10.writeByte((byte) (r5 | r2), r0) != r1) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00f7, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00f8, code lost:
        r5 = r10;
        r10 = r2;
        r2 = r12;
        r12 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00fc, code lost:
        if (r10 == 126) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00fe, code lost:
        if (r10 == 127) goto L_0x0101;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0101, code lost:
        r0.L$0 = r5;
        r0.L$1 = r2;
        r0.Z$0 = r12;
        r0.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x010f, code lost:
        if (r5.writeLong((long) r11, r0) != r1) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0111, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0112, code lost:
        r0.L$0 = r5;
        r0.L$1 = r2;
        r0.Z$0 = r12;
        r0.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0120, code lost:
        if (r5.writeShort((short) r11, r0) != r1) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0122, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0123, code lost:
        r10 = r12;
        r11 = r2;
        r12 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0126, code lost:
        r2 = r11;
        r5 = r12;
        r12 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0129, code lost:
        r10 = r2.getData();
        r11 = java.nio.ByteBuffer.wrap(r10, 0, r10.length);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, "wrap(array, offset, length)");
        r11 = io.ktor.utils.io.core.ByteReadPacketExtensionsKt.ByteReadPacket(r11, new io.ktor.websocket.RawWebSocketCommonKt$writeFrame$$inlined$ByteReadPacket$default$1(r10));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0142, code lost:
        if (r12 != true) goto L_0x0161;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0144, code lost:
        r10 = kotlin.random.Random.Default.nextInt();
        r0.L$0 = r5;
        r0.L$1 = r11;
        r0.I$0 = r10;
        r0.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0157, code lost:
        if (r5.writeInt(r10, r0) != r1) goto L_0x015a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0159, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x015a, code lost:
        r12 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x015b, code lost:
        r11 = mask(r11, r10);
        r5 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0161, code lost:
        if (r12 != false) goto L_0x0175;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0163, code lost:
        r0.L$0 = null;
        r0.L$1 = null;
        r0.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x016f, code lost:
        if (r5.writePacket(r11, r0) != r1) goto L_0x0172;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0171, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0174, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x017a, code lost:
        throw new kotlin.NoWhenBranchMatchedException();
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    @io.ktor.util.InternalAPI
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object writeFrame(io.ktor.utils.io.ByteWriteChannel r10, io.ktor.websocket.Frame r11, boolean r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            boolean r0 = r13 instanceof io.ktor.websocket.RawWebSocketCommonKt$writeFrame$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            io.ktor.websocket.RawWebSocketCommonKt$writeFrame$1 r0 = (io.ktor.websocket.RawWebSocketCommonKt$writeFrame$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            io.ktor.websocket.RawWebSocketCommonKt$writeFrame$1 r0 = new io.ktor.websocket.RawWebSocketCommonKt$writeFrame$1
            r0.<init>(r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 127(0x7f, float:1.78E-43)
            r5 = 128(0x80, float:1.794E-43)
            r6 = 126(0x7e, float:1.77E-43)
            r7 = 0
            switch(r2) {
                case 0: goto L_0x007e;
                case 1: goto L_0x006a;
                case 2: goto L_0x0057;
                case 3: goto L_0x0048;
                case 4: goto L_0x0048;
                case 5: goto L_0x0039;
                case 6: goto L_0x0034;
                default: goto L_0x002c;
            }
        L_0x002c:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0034:
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0172
        L_0x0039:
            int r10 = r0.I$0
            java.lang.Object r11 = r0.L$1
            io.ktor.utils.io.core.ByteReadPacket r11 = (io.ktor.utils.io.core.ByteReadPacket) r11
            java.lang.Object r12 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r12 = (io.ktor.utils.io.ByteWriteChannel) r12
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x015b
        L_0x0048:
            boolean r10 = r0.Z$0
            java.lang.Object r11 = r0.L$1
            io.ktor.websocket.Frame r11 = (io.ktor.websocket.Frame) r11
            java.lang.Object r12 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r12 = (io.ktor.utils.io.ByteWriteChannel) r12
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0126
        L_0x0057:
            int r10 = r0.I$1
            int r11 = r0.I$0
            boolean r12 = r0.Z$0
            java.lang.Object r2 = r0.L$1
            io.ktor.websocket.Frame r2 = (io.ktor.websocket.Frame) r2
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r5 = (io.ktor.utils.io.ByteWriteChannel) r5
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x00fc
        L_0x006a:
            int r10 = r0.I$0
            boolean r12 = r0.Z$0
            java.lang.Object r11 = r0.L$1
            io.ktor.websocket.Frame r11 = (io.ktor.websocket.Frame) r11
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r2 = (io.ktor.utils.io.ByteWriteChannel) r2
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = r12
            r12 = r11
            r11 = r10
            r10 = r2
            goto L_0x00d0
        L_0x007e:
            kotlin.ResultKt.throwOnFailure(r13)
            byte[] r13 = r11.getData()
            int r13 = r13.length
            boolean r2 = r11.getFin()
            if (r2 == 0) goto L_0x008f
            r2 = 128(0x80, float:1.794E-43)
            goto L_0x0090
        L_0x008f:
            r2 = 0
        L_0x0090:
            boolean r8 = r11.getRsv1()
            if (r8 == 0) goto L_0x0099
            r8 = 64
            goto L_0x009a
        L_0x0099:
            r8 = 0
        L_0x009a:
            r2 = r2 | r8
            boolean r8 = r11.getRsv2()
            if (r8 == 0) goto L_0x00a4
            r8 = 32
            goto L_0x00a5
        L_0x00a4:
            r8 = 0
        L_0x00a5:
            r2 = r2 | r8
            boolean r8 = r11.getRsv3()
            if (r8 == 0) goto L_0x00af
            r8 = 16
            goto L_0x00b0
        L_0x00af:
            r8 = 0
        L_0x00b0:
            r2 = r2 | r8
            io.ktor.websocket.FrameType r8 = r11.getFrameType()
            int r8 = r8.getOpcode()
            r2 = r2 | r8
            byte r2 = (byte) r2
            r0.L$0 = r10
            r0.L$1 = r11
            r0.Z$0 = r12
            r0.I$0 = r13
            r0.label = r3
            java.lang.Object r2 = r10.writeByte(r2, r0)
            if (r2 != r1) goto L_0x00cc
            return r1
        L_0x00cc:
            r9 = r12
            r12 = r11
            r11 = r13
            r13 = r9
        L_0x00d0:
            if (r11 >= r6) goto L_0x00d4
            r2 = r11
            goto L_0x00de
        L_0x00d4:
            r2 = 65535(0xffff, float:9.1834E-41)
            if (r11 > r2) goto L_0x00dc
            r2 = 126(0x7e, float:1.77E-43)
            goto L_0x00de
        L_0x00dc:
            r2 = 127(0x7f, float:1.78E-43)
        L_0x00de:
            if (r13 == 0) goto L_0x00e1
            goto L_0x00e2
        L_0x00e1:
            r5 = 0
        L_0x00e2:
            r5 = r5 | r2
            byte r5 = (byte) r5
            r0.L$0 = r10
            r0.L$1 = r12
            r0.Z$0 = r13
            r0.I$0 = r11
            r0.I$1 = r2
            r8 = 2
            r0.label = r8
            java.lang.Object r5 = r10.writeByte(r5, r0)
            if (r5 != r1) goto L_0x00f8
            return r1
        L_0x00f8:
            r5 = r10
            r10 = r2
            r2 = r12
            r12 = r13
        L_0x00fc:
            if (r10 == r6) goto L_0x0112
            if (r10 == r4) goto L_0x0101
            goto L_0x0129
        L_0x0101:
            long r10 = (long) r11
            r0.L$0 = r5
            r0.L$1 = r2
            r0.Z$0 = r12
            r13 = 4
            r0.label = r13
            java.lang.Object r10 = r5.writeLong(r10, r0)
            if (r10 != r1) goto L_0x0123
            return r1
        L_0x0112:
            short r10 = (short) r11
            r0.L$0 = r5
            r0.L$1 = r2
            r0.Z$0 = r12
            r11 = 3
            r0.label = r11
            java.lang.Object r10 = r5.writeShort(r10, r0)
            if (r10 != r1) goto L_0x0123
            return r1
        L_0x0123:
            r10 = r12
            r11 = r2
            r12 = r5
        L_0x0126:
            r2 = r11
            r5 = r12
            r12 = r10
        L_0x0129:
            byte[] r10 = r2.getData()
            int r11 = r10.length
            java.nio.ByteBuffer r11 = java.nio.ByteBuffer.wrap(r10, r7, r11)
            java.lang.String r13 = "wrap(array, offset, length)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r13)
            io.ktor.websocket.RawWebSocketCommonKt$writeFrame$$inlined$ByteReadPacket$default$1 r13 = new io.ktor.websocket.RawWebSocketCommonKt$writeFrame$$inlined$ByteReadPacket$default$1
            r13.<init>(r10)
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            io.ktor.utils.io.core.ByteReadPacket r11 = io.ktor.utils.io.core.ByteReadPacketExtensionsKt.ByteReadPacket(r11, r13)
            if (r12 != r3) goto L_0x0161
            kotlin.random.Random$Default r10 = kotlin.random.Random.Default
            int r10 = r10.nextInt()
            r0.L$0 = r5
            r0.L$1 = r11
            r0.I$0 = r10
            r12 = 5
            r0.label = r12
            java.lang.Object r12 = r5.writeInt(r10, r0)
            if (r12 != r1) goto L_0x015a
            return r1
        L_0x015a:
            r12 = r5
        L_0x015b:
            io.ktor.utils.io.core.ByteReadPacket r11 = mask(r11, r10)
            r5 = r12
            goto L_0x0163
        L_0x0161:
            if (r12 != 0) goto L_0x0175
        L_0x0163:
            r10 = 0
            r0.L$0 = r10
            r0.L$1 = r10
            r10 = 6
            r0.label = r10
            java.lang.Object r10 = r5.writePacket(r11, r0)
            if (r10 != r1) goto L_0x0172
            return r1
        L_0x0172:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x0175:
            kotlin.NoWhenBranchMatchedException r10 = new kotlin.NoWhenBranchMatchedException
            r10.<init>()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.RawWebSocketCommonKt.writeFrame(io.ktor.utils.io.ByteWriteChannel, io.ktor.websocket.Frame, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x021a, code lost:
        r19 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x021e, code lost:
        if ((r4 & 32) == 0) goto L_0x0223;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0220, code lost:
        r20 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0223, code lost:
        r20 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0227, code lost:
        if ((r4 & com.google.common.base.Ascii.DLE) == 0) goto L_0x022c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0229, code lost:
        r21 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x022c, code lost:
        r21 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0232, code lost:
        return r15.byType(r16, r17, r18, r19, r20, r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0238, code lost:
        throw new io.ktor.websocket.FrameTooBigException(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x023e, code lost:
        throw new kotlin.NoWhenBranchMatchedException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0252, code lost:
        throw new java.lang.IllegalStateException("Unsupported opcode: " + r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00bb, code lost:
        r4 = ((java.lang.Number) r4).byteValue();
        r2.L$0 = r0;
        r2.J$0 = r9;
        r2.I$0 = r1;
        r2.B$0 = r4;
        r2.label = 2;
        r11 = r0.readByte(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00d0, code lost:
        if (r11 != r3) goto L_0x00d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00d2, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00d3, code lost:
        r13 = r0;
        r22 = r4;
        r4 = r1;
        r1 = r11;
        r10 = r9;
        r9 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00db, code lost:
        r0 = ((java.lang.Number) r1).byteValue();
        r1 = r9 & com.google.common.base.Ascii.SI;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00e3, code lost:
        if (r1 != 0) goto L_0x00f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00e5, code lost:
        if (r4 == 0) goto L_0x00e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ef, code lost:
        throw new io.ktor.websocket.ProtocolViolationException("Can't continue finished frames");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00f0, code lost:
        if (r1 != 0) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00f2, code lost:
        r12 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00f4, code lost:
        r12 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00f5, code lost:
        r14 = io.ktor.websocket.FrameType.Companion.get(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00fb, code lost:
        if (r14 == null) goto L_0x023f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00fd, code lost:
        if (r1 == 0) goto L_0x0110;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ff, code lost:
        if (r4 == 0) goto L_0x0110;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0105, code lost:
        if (r14.getControlFrame() == false) goto L_0x0108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x010f, code lost:
        throw new io.ktor.websocket.ProtocolViolationException("Can't start new data frame before finishing previous one");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0112, code lost:
        if ((r9 & 128) == 0) goto L_0x0116;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0114, code lost:
        r1 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0116, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x011b, code lost:
        if (r14.getControlFrame() == false) goto L_0x0128;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x011d, code lost:
        if (r1 == 0) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0127, code lost:
        throw new io.ktor.websocket.ProtocolViolationException("control frames can't be fragmented");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0128, code lost:
        r4 = r0 & Byte.MAX_VALUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x012c, code lost:
        if (r4 == 126) goto L_0x0160;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0130, code lost:
        if (r4 == Byte.MAX_VALUE) goto L_0x013c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0132, code lost:
        r6 = (long) r4;
        r4 = r9;
        r11 = r10;
        r9 = r6;
        r22 = r14;
        r14 = r13;
        r13 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x013c, code lost:
        r2.L$0 = r13;
        r2.L$1 = r14;
        r2.J$0 = r10;
        r2.B$0 = r9;
        r2.B$1 = r0;
        r2.I$0 = r1;
        r2.label = 4;
        r4 = r13.readLong(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x014f, code lost:
        if (r4 != r3) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0151, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0152, code lost:
        r12 = r14;
        r22 = r4;
        r4 = r0;
        r0 = r1;
        r1 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0159, code lost:
        r6 = ((java.lang.Number) r1).longValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0160, code lost:
        r2.L$0 = r13;
        r2.L$1 = r14;
        r2.J$0 = r10;
        r2.B$0 = r9;
        r2.B$1 = r0;
        r2.I$0 = r1;
        r2.label = 3;
        r4 = r13.readShort(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0173, code lost:
        if (r4 != r3) goto L_0x0176;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0175, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0176, code lost:
        r12 = r14;
        r22 = r4;
        r4 = r0;
        r0 = r1;
        r1 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x017d, code lost:
        r6 = ((long) ((java.lang.Number) r1).shortValue()) & okhttp3.internal.ws.WebSocketProtocol.PAYLOAD_SHORT_MAX;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0188, code lost:
        r1 = r0;
        r0 = r4;
        r4 = r9;
        r14 = r13;
        r13 = r12;
        r11 = r10;
        r9 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0193, code lost:
        if (r13.getControlFrame() == false) goto L_0x01a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0199, code lost:
        if (r9 > 125) goto L_0x019c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01a3, code lost:
        throw new io.ktor.websocket.ProtocolViolationException("control frames can't be larger than 125 bytes");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01a6, code lost:
        if ((r0 & 128) == 0) goto L_0x01aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01a8, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01aa, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01ab, code lost:
        if (r0 != true) goto L_0x01d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01ad, code lost:
        r2.L$0 = r14;
        r2.L$1 = r13;
        r2.J$0 = r11;
        r2.B$0 = r4;
        r2.I$0 = r1;
        r2.J$1 = r9;
        r2.label = 5;
        r0 = r14.readInt(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01c0, code lost:
        if (r0 != r3) goto L_0x01c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01c2, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01c3, code lost:
        r22 = r1;
        r1 = r0;
        r0 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01c8, code lost:
        r22 = ((java.lang.Number) r1).intValue();
        r1 = r0;
        r0 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01d4, code lost:
        if (r0 != false) goto L_0x0239;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01d6, code lost:
        r0 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01dc, code lost:
        if (r9 > 2147483647L) goto L_0x0233;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01e0, code lost:
        if (r9 > r11) goto L_0x0233;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01e2, code lost:
        r2.L$0 = r13;
        r2.L$1 = null;
        r2.B$0 = r4;
        r2.I$0 = r1;
        r2.I$1 = r0;
        r2.label = 6;
        r2 = r14.readPacket((int) r9, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01f4, code lost:
        if (r2 != r3) goto L_0x01f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01f6, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01f7, code lost:
        r3 = r1;
        r1 = r2;
        r17 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01fb, code lost:
        r1 = (io.ktor.utils.io.core.ByteReadPacket) r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01fe, code lost:
        if (r0 != -1) goto L_0x0201;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0201, code lost:
        r1 = mask(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0205, code lost:
        r15 = io.ktor.websocket.Frame.Companion;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0208, code lost:
        if (r3 == 0) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x020a, code lost:
        r16 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x020d, code lost:
        r16 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x020f, code lost:
        r18 = io.ktor.utils.io.core.StringsKt.readBytes$default(r1, 0, 1, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0215, code lost:
        if ((r4 & com.google.common.primitives.SignedBytes.MAX_POWER_OF_TWO) == 0) goto L_0x021a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0217, code lost:
        r19 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    @io.ktor.util.InternalAPI
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object readFrame(io.ktor.utils.io.ByteReadChannel r23, long r24, int r26, kotlin.coroutines.Continuation<? super io.ktor.websocket.Frame> r27) {
        /*
            r0 = r23
            r1 = r27
            boolean r2 = r1 instanceof io.ktor.websocket.RawWebSocketCommonKt$readFrame$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            io.ktor.websocket.RawWebSocketCommonKt$readFrame$1 r2 = (io.ktor.websocket.RawWebSocketCommonKt$readFrame$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            io.ktor.websocket.RawWebSocketCommonKt$readFrame$1 r2 = new io.ktor.websocket.RawWebSocketCommonKt$readFrame$1
            r2.<init>(r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 0
            r8 = 1
            switch(r4) {
                case 0: goto L_0x00a5;
                case 1: goto L_0x0093;
                case 2: goto L_0x0082;
                case 3: goto L_0x006d;
                case 4: goto L_0x0058;
                case 5: goto L_0x0043;
                case 6: goto L_0x0032;
                default: goto L_0x002a;
            }
        L_0x002a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0032:
            int r0 = r2.I$1
            int r3 = r2.I$0
            byte r4 = r2.B$0
            java.lang.Object r2 = r2.L$0
            io.ktor.websocket.FrameType r2 = (io.ktor.websocket.FrameType) r2
            kotlin.ResultKt.throwOnFailure(r1)
            r17 = r2
            goto L_0x01fb
        L_0x0043:
            long r9 = r2.J$1
            int r0 = r2.I$0
            byte r4 = r2.B$0
            long r11 = r2.J$0
            java.lang.Object r13 = r2.L$1
            io.ktor.websocket.FrameType r13 = (io.ktor.websocket.FrameType) r13
            java.lang.Object r14 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r14 = (io.ktor.utils.io.ByteReadChannel) r14
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x01c8
        L_0x0058:
            int r0 = r2.I$0
            byte r4 = r2.B$1
            byte r9 = r2.B$0
            long r10 = r2.J$0
            java.lang.Object r12 = r2.L$1
            io.ktor.websocket.FrameType r12 = (io.ktor.websocket.FrameType) r12
            java.lang.Object r13 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r13 = (io.ktor.utils.io.ByteReadChannel) r13
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x0159
        L_0x006d:
            int r0 = r2.I$0
            byte r4 = r2.B$1
            byte r9 = r2.B$0
            long r10 = r2.J$0
            java.lang.Object r12 = r2.L$1
            io.ktor.websocket.FrameType r12 = (io.ktor.websocket.FrameType) r12
            java.lang.Object r13 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r13 = (io.ktor.utils.io.ByteReadChannel) r13
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x017d
        L_0x0082:
            byte r0 = r2.B$0
            int r4 = r2.I$0
            long r9 = r2.J$0
            java.lang.Object r11 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r11 = (io.ktor.utils.io.ByteReadChannel) r11
            kotlin.ResultKt.throwOnFailure(r1)
            r13 = r11
            r10 = r9
            r9 = r0
            goto L_0x00db
        L_0x0093:
            int r0 = r2.I$0
            long r9 = r2.J$0
            java.lang.Object r4 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r4 = (io.ktor.utils.io.ByteReadChannel) r4
            kotlin.ResultKt.throwOnFailure(r1)
            r22 = r1
            r1 = r0
            r0 = r4
            r4 = r22
            goto L_0x00bb
        L_0x00a5:
            kotlin.ResultKt.throwOnFailure(r1)
            r2.L$0 = r0
            r9 = r24
            r2.J$0 = r9
            r1 = r26
            r2.I$0 = r1
            r2.label = r8
            java.lang.Object r4 = r0.readByte(r2)
            if (r4 != r3) goto L_0x00bb
            return r3
        L_0x00bb:
            java.lang.Number r4 = (java.lang.Number) r4
            byte r4 = r4.byteValue()
            r2.L$0 = r0
            r2.J$0 = r9
            r2.I$0 = r1
            r2.B$0 = r4
            r11 = 2
            r2.label = r11
            java.lang.Object r11 = r0.readByte(r2)
            if (r11 != r3) goto L_0x00d3
            return r3
        L_0x00d3:
            r13 = r0
            r22 = r4
            r4 = r1
            r1 = r11
            r10 = r9
            r9 = r22
        L_0x00db:
            java.lang.Number r1 = (java.lang.Number) r1
            byte r0 = r1.byteValue()
            r1 = r9 & 15
            if (r1 != 0) goto L_0x00f0
            if (r4 == 0) goto L_0x00e8
            goto L_0x00f0
        L_0x00e8:
            io.ktor.websocket.ProtocolViolationException r0 = new io.ktor.websocket.ProtocolViolationException
            java.lang.String r1 = "Can't continue finished frames"
            r0.<init>(r1)
            throw r0
        L_0x00f0:
            if (r1 != 0) goto L_0x00f4
            r12 = r4
            goto L_0x00f5
        L_0x00f4:
            r12 = r1
        L_0x00f5:
            io.ktor.websocket.FrameType$Companion r14 = io.ktor.websocket.FrameType.Companion
            io.ktor.websocket.FrameType r14 = r14.get(r12)
            if (r14 == 0) goto L_0x023f
            if (r1 == 0) goto L_0x0110
            if (r4 == 0) goto L_0x0110
            boolean r1 = r14.getControlFrame()
            if (r1 == 0) goto L_0x0108
            goto L_0x0110
        L_0x0108:
            io.ktor.websocket.ProtocolViolationException r0 = new io.ktor.websocket.ProtocolViolationException
            java.lang.String r1 = "Can't start new data frame before finishing previous one"
            r0.<init>(r1)
            throw r0
        L_0x0110:
            r1 = r9 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x0116
            r1 = 1
            goto L_0x0117
        L_0x0116:
            r1 = 0
        L_0x0117:
            boolean r4 = r14.getControlFrame()
            if (r4 == 0) goto L_0x0128
            if (r1 == 0) goto L_0x0120
            goto L_0x0128
        L_0x0120:
            io.ktor.websocket.ProtocolViolationException r0 = new io.ktor.websocket.ProtocolViolationException
            java.lang.String r1 = "control frames can't be fragmented"
            r0.<init>(r1)
            throw r0
        L_0x0128:
            r4 = r0 & 127(0x7f, float:1.78E-43)
            r12 = 126(0x7e, float:1.77E-43)
            if (r4 == r12) goto L_0x0160
            r12 = 127(0x7f, float:1.78E-43)
            if (r4 == r12) goto L_0x013c
            long r6 = (long) r4
            r4 = r9
            r11 = r10
            r9 = r6
            r22 = r14
            r14 = r13
            r13 = r22
            goto L_0x018f
        L_0x013c:
            r2.L$0 = r13
            r2.L$1 = r14
            r2.J$0 = r10
            r2.B$0 = r9
            r2.B$1 = r0
            r2.I$0 = r1
            r4 = 4
            r2.label = r4
            java.lang.Object r4 = r13.readLong(r2)
            if (r4 != r3) goto L_0x0152
            return r3
        L_0x0152:
            r12 = r14
            r22 = r4
            r4 = r0
            r0 = r1
            r1 = r22
        L_0x0159:
            java.lang.Number r1 = (java.lang.Number) r1
            long r6 = r1.longValue()
            goto L_0x0188
        L_0x0160:
            r2.L$0 = r13
            r2.L$1 = r14
            r2.J$0 = r10
            r2.B$0 = r9
            r2.B$1 = r0
            r2.I$0 = r1
            r4 = 3
            r2.label = r4
            java.lang.Object r4 = r13.readShort(r2)
            if (r4 != r3) goto L_0x0176
            return r3
        L_0x0176:
            r12 = r14
            r22 = r4
            r4 = r0
            r0 = r1
            r1 = r22
        L_0x017d:
            java.lang.Number r1 = (java.lang.Number) r1
            short r1 = r1.shortValue()
            long r6 = (long) r1
            r15 = 65535(0xffff, double:3.23786E-319)
            long r6 = r6 & r15
        L_0x0188:
            r1 = r0
            r0 = r4
            r4 = r9
            r14 = r13
            r13 = r12
            r11 = r10
            r9 = r6
        L_0x018f:
            boolean r6 = r13.getControlFrame()
            if (r6 == 0) goto L_0x01a4
            r6 = 125(0x7d, double:6.2E-322)
            int r15 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r15 > 0) goto L_0x019c
            goto L_0x01a4
        L_0x019c:
            io.ktor.websocket.ProtocolViolationException r0 = new io.ktor.websocket.ProtocolViolationException
            java.lang.String r1 = "control frames can't be larger than 125 bytes"
            r0.<init>(r1)
            throw r0
        L_0x01a4:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x01aa
            r0 = 1
            goto L_0x01ab
        L_0x01aa:
            r0 = 0
        L_0x01ab:
            if (r0 != r8) goto L_0x01d4
            r2.L$0 = r14
            r2.L$1 = r13
            r2.J$0 = r11
            r2.B$0 = r4
            r2.I$0 = r1
            r2.J$1 = r9
            r0 = 5
            r2.label = r0
            java.lang.Object r0 = r14.readInt(r2)
            if (r0 != r3) goto L_0x01c3
            return r3
        L_0x01c3:
            r22 = r1
            r1 = r0
            r0 = r22
        L_0x01c8:
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            r22 = r1
            r1 = r0
            r0 = r22
            goto L_0x01d7
        L_0x01d4:
            if (r0 != 0) goto L_0x0239
            r0 = -1
        L_0x01d7:
            r6 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r15 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r15 > 0) goto L_0x0233
            int r6 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r6 > 0) goto L_0x0233
            int r6 = (int) r9
            r2.L$0 = r13
            r2.L$1 = r5
            r2.B$0 = r4
            r2.I$0 = r1
            r2.I$1 = r0
            r7 = 6
            r2.label = r7
            java.lang.Object r2 = r14.readPacket(r6, r2)
            if (r2 != r3) goto L_0x01f7
            return r3
        L_0x01f7:
            r3 = r1
            r1 = r2
            r17 = r13
        L_0x01fb:
            io.ktor.utils.io.core.ByteReadPacket r1 = (io.ktor.utils.io.core.ByteReadPacket) r1
            r2 = -1
            if (r0 != r2) goto L_0x0201
            goto L_0x0205
        L_0x0201:
            io.ktor.utils.io.core.ByteReadPacket r1 = mask(r1, r0)
        L_0x0205:
            io.ktor.websocket.Frame$Companion r15 = io.ktor.websocket.Frame.Companion
            r0 = 0
            if (r3 == 0) goto L_0x020d
            r16 = 1
            goto L_0x020f
        L_0x020d:
            r16 = 0
        L_0x020f:
            byte[] r18 = io.ktor.utils.io.core.StringsKt.readBytes$default(r1, r0, r8, r5)
            r1 = r4 & 64
            if (r1 == 0) goto L_0x021a
            r19 = 1
            goto L_0x021c
        L_0x021a:
            r19 = 0
        L_0x021c:
            r1 = r4 & 32
            if (r1 == 0) goto L_0x0223
            r20 = 1
            goto L_0x0225
        L_0x0223:
            r20 = 0
        L_0x0225:
            r1 = r4 & 16
            if (r1 == 0) goto L_0x022c
            r21 = 1
            goto L_0x022e
        L_0x022c:
            r21 = 0
        L_0x022e:
            io.ktor.websocket.Frame r0 = r15.byType(r16, r17, r18, r19, r20, r21)
            return r0
        L_0x0233:
            io.ktor.websocket.FrameTooBigException r0 = new io.ktor.websocket.FrameTooBigException
            r0.<init>(r9)
            throw r0
        L_0x0239:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        L_0x023f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unsupported opcode: "
            r1.<init>(r2)
            r1.append(r12)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.RawWebSocketCommonKt.readFrame(io.ktor.utils.io.ByteReadChannel, long, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final ByteReadPacket mask(ByteReadPacket byteReadPacket, int i) {
        BytePacketBuilder bytePacketBuilder;
        DefaultAllocator defaultAllocator = DefaultAllocator.INSTANCE;
        ByteBuffer r0 = defaultAllocator.m1506allocgFvZug((long) 4);
        try {
            r0.putInt(0, i);
            bytePacketBuilder = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
            int remaining = (int) byteReadPacket.getRemaining();
            for (int i2 = 0; i2 < remaining; i2++) {
                bytePacketBuilder.writeByte((byte) (byteReadPacket.readByte() ^ r0.get(i2 % 4)));
            }
            ByteReadPacket build = bytePacketBuilder.build();
            defaultAllocator.m1507free3GNKZMM(r0);
            return build;
        } catch (Throwable th) {
            defaultAllocator.m1507free3GNKZMM(r0);
            throw th;
        }
    }
}
