package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteOrder;

public class WebSocket08FrameDecoder extends ByteToMessageDecoder implements WebSocketFrameDecoder {
    private static final byte OPCODE_BINARY = 2;
    private static final byte OPCODE_CLOSE = 8;
    private static final byte OPCODE_CONT = 0;
    private static final byte OPCODE_PING = 9;
    private static final byte OPCODE_PONG = 10;
    private static final byte OPCODE_TEXT = 1;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) WebSocket08FrameDecoder.class);
    private final WebSocketDecoderConfig config;
    private int fragmentedFramesCount;
    private boolean frameFinalFlag;
    private boolean frameMasked;
    private int frameOpcode;
    private int framePayloadLen1;
    private long framePayloadLength;
    private int frameRsv;
    private int mask;
    private boolean receivedClosingHandshake;
    private State state;

    enum State {
        READING_FIRST,
        READING_SECOND,
        READING_SIZE,
        MASKING_KEY,
        PAYLOAD,
        CORRUPT
    }

    public WebSocket08FrameDecoder(boolean z, boolean z2, int i) {
        this(z, z2, i, false);
    }

    public WebSocket08FrameDecoder(boolean z, boolean z2, int i, boolean z3) {
        this(WebSocketDecoderConfig.newBuilder().expectMaskedFrames(z).allowExtensions(z2).maxFramePayloadLength(i).allowMaskMismatch(z3).build());
    }

    public WebSocket08FrameDecoder(WebSocketDecoderConfig webSocketDecoderConfig) {
        this.state = State.READING_FIRST;
        this.config = (WebSocketDecoderConfig) ObjectUtil.checkNotNull(webSocketDecoderConfig, "decoderConfig");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01ac, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01ad, code lost:
        r5 = logger;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01b3, code lost:
        if (r5.isTraceEnabled() == false) goto L_0x01c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01b5, code lost:
        r5.trace("Decoding WebSocket Frame length={}", (java.lang.Object) java.lang.Long.valueOf(r1.framePayloadLength));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x01c0, code lost:
        r1.state = io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.State.MASKING_KEY;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01c6, code lost:
        if (r1.frameMasked == false) goto L_0x01d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01cd, code lost:
        if (r20.readableBytes() >= 4) goto L_0x01d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01cf, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01d0, code lost:
        r1.mask = r20.readInt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x01d6, code lost:
        r1.state = io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.State.PAYLOAD;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x01e3, code lost:
        if (((long) r20.readableBytes()) >= r1.framePayloadLength) goto L_0x01e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x01e5, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x01e6, code lost:
        r5 = io.netty.buffer.Unpooled.EMPTY_BUFFER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x01ec, code lost:
        if (r1.framePayloadLength <= 0) goto L_0x01fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x01ee, code lost:
        r5 = io.netty.buffer.ByteBufUtil.readBytes(r19.alloc(), r2, toFrameLength(r1.framePayloadLength));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x01fc, code lost:
        r1.state = io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.State.READING_FIRST;
        r2 = r1.frameMasked;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0206, code lost:
        if (r1.framePayloadLength <= 0) goto L_0x020a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0208, code lost:
        r8 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x020a, code lost:
        r8 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x020c, code lost:
        if ((r2 & r8) == false) goto L_0x0211;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x020e, code lost:
        unmask(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0211, code lost:
        r2 = r1.frameOpcode;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0213, code lost:
        if (r2 != 9) goto L_0x0222;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0215, code lost:
        r3.add(new io.netty.handler.codec.http.websocketx.PingWebSocketFrame(r1.frameFinalFlag, r1.frameRsv, r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0221, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0222, code lost:
        if (r2 != 10) goto L_0x0231;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0224, code lost:
        r3.add(new io.netty.handler.codec.http.websocketx.PongWebSocketFrame(r1.frameFinalFlag, r1.frameRsv, r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0230, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0233, code lost:
        if (r2 != 8) goto L_0x0247;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0235, code lost:
        r1.receivedClosingHandshake = true;
        checkCloseFrameBody(r0, r5);
        r3.add(new io.netty.handler.codec.http.websocketx.CloseWebSocketFrame(r1.frameFinalFlag, r1.frameRsv, r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0246, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0249, code lost:
        if (r1.frameFinalFlag == false) goto L_0x024f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x024b, code lost:
        r1.fragmentedFramesCount = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x024f, code lost:
        r1.fragmentedFramesCount++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0254, code lost:
        if (r2 != 1) goto L_0x0263;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0256, code lost:
        r3.add(new io.netty.handler.codec.http.websocketx.TextWebSocketFrame(r1.frameFinalFlag, r1.frameRsv, r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0262, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0264, code lost:
        if (r2 != 2) goto L_0x0273;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0266, code lost:
        r3.add(new io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame(r1.frameFinalFlag, r1.frameRsv, r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x0272, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x0273, code lost:
        if (r2 != 0) goto L_0x0282;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0275, code lost:
        r3.add(new io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame(r1.frameFinalFlag, r1.frameRsv, r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0281, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0295, code lost:
        throw new java.lang.UnsupportedOperationException("Cannot decode web socket frame with opcode: " + r1.frameOpcode);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0296, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0297, code lost:
        if (r5 != null) goto L_0x0299;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0299, code lost:
        r5.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x029c, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007c, code lost:
        if (r20.isReadable() != false) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007e, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007f, code lost:
        r5 = r20.readByte();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0085, code lost:
        if ((r5 & 128) == 0) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0087, code lost:
        r8 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0089, code lost:
        r8 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008a, code lost:
        r1.frameMasked = r8;
        r1.framePayloadLen1 = r5 & Byte.MAX_VALUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0091, code lost:
        if (r1.frameRsv == 0) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0099, code lost:
        if (r1.config.allowExtensions() != false) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x009b, code lost:
        protocolViolation(r0, r2, "RSV != 0 and no extension negotiated, RSV:" + r1.frameRsv);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ae, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b5, code lost:
        if (r1.config.allowMaskMismatch() != false) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00bf, code lost:
        if (r1.config.expectMaskedFrames() == r1.frameMasked) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00c1, code lost:
        protocolViolation(r0, r2, "received a frame that is not masked as expected");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c6, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c7, code lost:
        r5 = r1.frameOpcode;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ca, code lost:
        if (r5 <= 7) goto L_0x0106;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ce, code lost:
        if (r1.frameFinalFlag != false) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d0, code lost:
        protocolViolation(r0, r2, "fragmented control frame");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d5, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00d6, code lost:
        r8 = r1.framePayloadLen1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00da, code lost:
        if (r8 <= 125) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00dc, code lost:
        protocolViolation(r0, r2, "control frame with payload length > 125 octets");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e1, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e2, code lost:
        if (r5 == 8) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e4, code lost:
        if (r5 == 9) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e6, code lost:
        if (r5 == 10) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00e8, code lost:
        protocolViolation(r0, r2, "control frame using reserved opcode " + r1.frameOpcode);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00fb, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00fc, code lost:
        if (r5 != 8) goto L_0x0136;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00fe, code lost:
        if (r8 != 1) goto L_0x0136;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0100, code lost:
        protocolViolation(r0, r2, "received close control frame with payload len 1");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0105, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0106, code lost:
        if (r5 == 0) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0108, code lost:
        if (r5 == 1) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x010a, code lost:
        if (r5 == 2) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x010c, code lost:
        protocolViolation(r0, r2, "data frame using reserved opcode " + r1.frameOpcode);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x011f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0120, code lost:
        r8 = r1.fragmentedFramesCount;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0122, code lost:
        if (r8 != 0) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0124, code lost:
        if (r5 != 0) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0126, code lost:
        protocolViolation(r0, r2, "received continuation data frame outside fragmented message");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x012b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x012c, code lost:
        if (r8 == 0) goto L_0x0136;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x012e, code lost:
        if (r5 == 0) goto L_0x0136;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0130, code lost:
        protocolViolation(r0, r2, "received non-continuation data frame while inside fragmented message");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0135, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0136, code lost:
        r1.state = io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.State.READING_SIZE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x013a, code lost:
        r5 = r1.framePayloadLen1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0140, code lost:
        if (r5 != 126) goto L_0x015a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0146, code lost:
        if (r20.readableBytes() >= 2) goto L_0x0149;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0148, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0149, code lost:
        r8 = (long) r20.readUnsignedShort();
        r1.framePayloadLength = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0154, code lost:
        if (r8 >= 126) goto L_0x0181;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0156, code lost:
        protocolViolation(r0, r2, "invalid data frame length (not using minimal length encoding)");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0159, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x015a, code lost:
        if (r5 != 127) goto L_0x017e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0160, code lost:
        if (r20.readableBytes() >= 8) goto L_0x0163;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0162, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0163, code lost:
        r8 = r20.readLong();
        r1.framePayloadLength = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x016b, code lost:
        if (r8 >= 0) goto L_0x0173;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x016d, code lost:
        protocolViolation(r0, r2, "invalid data frame length (negative length)");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0172, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0178, code lost:
        if (r8 >= 65536) goto L_0x0181;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x017a, code lost:
        protocolViolation(r0, r2, "invalid data frame length (not using minimal length encoding)");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x017d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x017e, code lost:
        r1.framePayloadLength = (long) r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x018c, code lost:
        if (r1.framePayloadLength <= ((long) r1.config.maxFramePayloadLength())) goto L_0x01ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x018e, code lost:
        protocolViolation(r0, r2, io.netty.handler.codec.http.websocketx.WebSocketCloseStatus.MESSAGE_TOO_BIG, "Max frame length of " + r1.config.maxFramePayloadLength() + " has been exceeded.");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void decode(io.netty.channel.ChannelHandlerContext r19, io.netty.buffer.ByteBuf r20, java.util.List<java.lang.Object> r21) throws java.lang.Exception {
        /*
            r18 = this;
            r1 = r18
            r0 = r19
            r2 = r20
            r3 = r21
            java.lang.String r4 = "Cannot decode web socket frame with opcode: "
            boolean r5 = r1.receivedClosingHandshake
            if (r5 == 0) goto L_0x0016
            int r0 = r18.actualReadableBytes()
            r2.skipBytes(r0)
            return
        L_0x0016:
            int[] r5 = io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.AnonymousClass1.$SwitchMap$io$netty$handler$codec$http$websocketx$WebSocket08FrameDecoder$State
            io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$State r6 = r1.state
            int r6 = r6.ordinal()
            r5 = r5[r6]
            r6 = 10
            r7 = 9
            r8 = 4
            r9 = 127(0x7f, float:1.78E-43)
            r10 = 2
            r11 = 8
            r13 = 0
            r15 = 1
            switch(r5) {
                case 1: goto L_0x0042;
                case 2: goto L_0x0078;
                case 3: goto L_0x013a;
                case 4: goto L_0x01c4;
                case 5: goto L_0x01da;
                case 6: goto L_0x0038;
                default: goto L_0x0030;
            }
        L_0x0030:
            java.lang.Error r0 = new java.lang.Error
            java.lang.String r2 = "Shouldn't reach here."
            r0.<init>(r2)
            throw r0
        L_0x0038:
            boolean r0 = r20.isReadable()
            if (r0 == 0) goto L_0x0041
            r20.readByte()
        L_0x0041:
            return
        L_0x0042:
            boolean r5 = r20.isReadable()
            if (r5 != 0) goto L_0x0049
            return
        L_0x0049:
            r1.framePayloadLength = r13
            byte r5 = r20.readByte()
            r12 = r5 & 128(0x80, float:1.794E-43)
            if (r12 == 0) goto L_0x0055
            r12 = 1
            goto L_0x0056
        L_0x0055:
            r12 = 0
        L_0x0056:
            r1.frameFinalFlag = r12
            r12 = r5 & 112(0x70, float:1.57E-43)
            int r12 = r12 >> r8
            r1.frameRsv = r12
            r5 = r5 & 15
            r1.frameOpcode = r5
            io.netty.util.internal.logging.InternalLogger r5 = logger
            boolean r12 = r5.isTraceEnabled()
            if (r12 == 0) goto L_0x0074
            int r12 = r1.frameOpcode
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            java.lang.String r8 = "Decoding WebSocket Frame opCode={}"
            r5.trace((java.lang.String) r8, (java.lang.Object) r12)
        L_0x0074:
            io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$State r5 = io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.State.READING_SECOND
            r1.state = r5
        L_0x0078:
            boolean r5 = r20.isReadable()
            if (r5 != 0) goto L_0x007f
            return
        L_0x007f:
            byte r5 = r20.readByte()
            r8 = r5 & 128(0x80, float:1.794E-43)
            if (r8 == 0) goto L_0x0089
            r8 = 1
            goto L_0x008a
        L_0x0089:
            r8 = 0
        L_0x008a:
            r1.frameMasked = r8
            r5 = r5 & r9
            r1.framePayloadLen1 = r5
            int r5 = r1.frameRsv
            if (r5 == 0) goto L_0x00af
            io.netty.handler.codec.http.websocketx.WebSocketDecoderConfig r5 = r1.config
            boolean r5 = r5.allowExtensions()
            if (r5 != 0) goto L_0x00af
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "RSV != 0 and no extension negotiated, RSV:"
            r3.<init>(r4)
            int r4 = r1.frameRsv
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r1.protocolViolation((io.netty.channel.ChannelHandlerContext) r0, (io.netty.buffer.ByteBuf) r2, (java.lang.String) r3)
            return
        L_0x00af:
            io.netty.handler.codec.http.websocketx.WebSocketDecoderConfig r5 = r1.config
            boolean r5 = r5.allowMaskMismatch()
            if (r5 != 0) goto L_0x00c7
            io.netty.handler.codec.http.websocketx.WebSocketDecoderConfig r5 = r1.config
            boolean r5 = r5.expectMaskedFrames()
            boolean r8 = r1.frameMasked
            if (r5 == r8) goto L_0x00c7
            java.lang.String r3 = "received a frame that is not masked as expected"
            r1.protocolViolation((io.netty.channel.ChannelHandlerContext) r0, (io.netty.buffer.ByteBuf) r2, (java.lang.String) r3)
            return
        L_0x00c7:
            int r5 = r1.frameOpcode
            r8 = 7
            if (r5 <= r8) goto L_0x0106
            boolean r8 = r1.frameFinalFlag
            if (r8 != 0) goto L_0x00d6
            java.lang.String r3 = "fragmented control frame"
            r1.protocolViolation((io.netty.channel.ChannelHandlerContext) r0, (io.netty.buffer.ByteBuf) r2, (java.lang.String) r3)
            return
        L_0x00d6:
            int r8 = r1.framePayloadLen1
            r12 = 125(0x7d, float:1.75E-43)
            if (r8 <= r12) goto L_0x00e2
            java.lang.String r3 = "control frame with payload length > 125 octets"
            r1.protocolViolation((io.netty.channel.ChannelHandlerContext) r0, (io.netty.buffer.ByteBuf) r2, (java.lang.String) r3)
            return
        L_0x00e2:
            if (r5 == r11) goto L_0x00fc
            if (r5 == r7) goto L_0x00fc
            if (r5 == r6) goto L_0x00fc
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "control frame using reserved opcode "
            r3.<init>(r4)
            int r4 = r1.frameOpcode
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r1.protocolViolation((io.netty.channel.ChannelHandlerContext) r0, (io.netty.buffer.ByteBuf) r2, (java.lang.String) r3)
            return
        L_0x00fc:
            if (r5 != r11) goto L_0x0136
            if (r8 != r15) goto L_0x0136
            java.lang.String r3 = "received close control frame with payload len 1"
            r1.protocolViolation((io.netty.channel.ChannelHandlerContext) r0, (io.netty.buffer.ByteBuf) r2, (java.lang.String) r3)
            return
        L_0x0106:
            if (r5 == 0) goto L_0x0120
            if (r5 == r15) goto L_0x0120
            if (r5 == r10) goto L_0x0120
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "data frame using reserved opcode "
            r3.<init>(r4)
            int r4 = r1.frameOpcode
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r1.protocolViolation((io.netty.channel.ChannelHandlerContext) r0, (io.netty.buffer.ByteBuf) r2, (java.lang.String) r3)
            return
        L_0x0120:
            int r8 = r1.fragmentedFramesCount
            if (r8 != 0) goto L_0x012c
            if (r5 != 0) goto L_0x012c
            java.lang.String r3 = "received continuation data frame outside fragmented message"
            r1.protocolViolation((io.netty.channel.ChannelHandlerContext) r0, (io.netty.buffer.ByteBuf) r2, (java.lang.String) r3)
            return
        L_0x012c:
            if (r8 == 0) goto L_0x0136
            if (r5 == 0) goto L_0x0136
            java.lang.String r3 = "received non-continuation data frame while inside fragmented message"
            r1.protocolViolation((io.netty.channel.ChannelHandlerContext) r0, (io.netty.buffer.ByteBuf) r2, (java.lang.String) r3)
            return
        L_0x0136:
            io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$State r5 = io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.State.READING_SIZE
            r1.state = r5
        L_0x013a:
            int r5 = r1.framePayloadLen1
            r8 = 126(0x7e, float:1.77E-43)
            java.lang.String r12 = "invalid data frame length (not using minimal length encoding)"
            if (r5 != r8) goto L_0x015a
            int r5 = r20.readableBytes()
            if (r5 >= r10) goto L_0x0149
            return
        L_0x0149:
            int r5 = r20.readUnsignedShort()
            long r8 = (long) r5
            r1.framePayloadLength = r8
            r16 = 126(0x7e, double:6.23E-322)
            int r5 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1))
            if (r5 >= 0) goto L_0x0181
            r1.protocolViolation((io.netty.channel.ChannelHandlerContext) r0, (io.netty.buffer.ByteBuf) r2, (java.lang.String) r12)
            return
        L_0x015a:
            if (r5 != r9) goto L_0x017e
            int r5 = r20.readableBytes()
            if (r5 >= r11) goto L_0x0163
            return
        L_0x0163:
            long r8 = r20.readLong()
            r1.framePayloadLength = r8
            int r5 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r5 >= 0) goto L_0x0173
            java.lang.String r3 = "invalid data frame length (negative length)"
            r1.protocolViolation((io.netty.channel.ChannelHandlerContext) r0, (io.netty.buffer.ByteBuf) r2, (java.lang.String) r3)
            return
        L_0x0173:
            r16 = 65536(0x10000, double:3.2379E-319)
            int r5 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1))
            if (r5 >= 0) goto L_0x0181
            r1.protocolViolation((io.netty.channel.ChannelHandlerContext) r0, (io.netty.buffer.ByteBuf) r2, (java.lang.String) r12)
            return
        L_0x017e:
            long r8 = (long) r5
            r1.framePayloadLength = r8
        L_0x0181:
            long r8 = r1.framePayloadLength
            io.netty.handler.codec.http.websocketx.WebSocketDecoderConfig r5 = r1.config
            int r5 = r5.maxFramePayloadLength()
            long r10 = (long) r5
            int r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r5 <= 0) goto L_0x01ad
            io.netty.handler.codec.http.websocketx.WebSocketCloseStatus r3 = io.netty.handler.codec.http.websocketx.WebSocketCloseStatus.MESSAGE_TOO_BIG
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Max frame length of "
            r4.<init>(r5)
            io.netty.handler.codec.http.websocketx.WebSocketDecoderConfig r5 = r1.config
            int r5 = r5.maxFramePayloadLength()
            r4.append(r5)
            java.lang.String r5 = " has been exceeded."
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r1.protocolViolation(r0, r2, r3, r4)
            return
        L_0x01ad:
            io.netty.util.internal.logging.InternalLogger r5 = logger
            boolean r8 = r5.isTraceEnabled()
            if (r8 == 0) goto L_0x01c0
            long r8 = r1.framePayloadLength
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            java.lang.String r9 = "Decoding WebSocket Frame length={}"
            r5.trace((java.lang.String) r9, (java.lang.Object) r8)
        L_0x01c0:
            io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$State r5 = io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.State.MASKING_KEY
            r1.state = r5
        L_0x01c4:
            boolean r5 = r1.frameMasked
            if (r5 == 0) goto L_0x01d6
            int r5 = r20.readableBytes()
            r8 = 4
            if (r5 >= r8) goto L_0x01d0
            return
        L_0x01d0:
            int r5 = r20.readInt()
            r1.mask = r5
        L_0x01d6:
            io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$State r5 = io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.State.PAYLOAD
            r1.state = r5
        L_0x01da:
            int r5 = r20.readableBytes()
            long r8 = (long) r5
            long r10 = r1.framePayloadLength
            int r5 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r5 >= 0) goto L_0x01e6
            return
        L_0x01e6:
            io.netty.buffer.ByteBuf r5 = io.netty.buffer.Unpooled.EMPTY_BUFFER
            long r8 = r1.framePayloadLength     // Catch:{ all -> 0x0296 }
            int r10 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r10 <= 0) goto L_0x01fc
            io.netty.buffer.ByteBufAllocator r8 = r19.alloc()     // Catch:{ all -> 0x0296 }
            long r9 = r1.framePayloadLength     // Catch:{ all -> 0x0296 }
            int r9 = toFrameLength(r9)     // Catch:{ all -> 0x0296 }
            io.netty.buffer.ByteBuf r5 = io.netty.buffer.ByteBufUtil.readBytes(r8, r2, r9)     // Catch:{ all -> 0x0296 }
        L_0x01fc:
            io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$State r2 = io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.State.READING_FIRST     // Catch:{ all -> 0x0296 }
            r1.state = r2     // Catch:{ all -> 0x0296 }
            boolean r2 = r1.frameMasked     // Catch:{ all -> 0x0296 }
            long r8 = r1.framePayloadLength     // Catch:{ all -> 0x0296 }
            int r10 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r10 <= 0) goto L_0x020a
            r8 = 1
            goto L_0x020b
        L_0x020a:
            r8 = 0
        L_0x020b:
            r2 = r2 & r8
            if (r2 == 0) goto L_0x0211
            r1.unmask(r5)     // Catch:{ all -> 0x0296 }
        L_0x0211:
            int r2 = r1.frameOpcode     // Catch:{ all -> 0x0296 }
            if (r2 != r7) goto L_0x0222
            io.netty.handler.codec.http.websocketx.PingWebSocketFrame r0 = new io.netty.handler.codec.http.websocketx.PingWebSocketFrame     // Catch:{ all -> 0x0296 }
            boolean r2 = r1.frameFinalFlag     // Catch:{ all -> 0x0296 }
            int r4 = r1.frameRsv     // Catch:{ all -> 0x0296 }
            r0.<init>(r2, r4, r5)     // Catch:{ all -> 0x0296 }
            r3.add(r0)     // Catch:{ all -> 0x0296 }
            return
        L_0x0222:
            if (r2 != r6) goto L_0x0231
            io.netty.handler.codec.http.websocketx.PongWebSocketFrame r0 = new io.netty.handler.codec.http.websocketx.PongWebSocketFrame     // Catch:{ all -> 0x0296 }
            boolean r2 = r1.frameFinalFlag     // Catch:{ all -> 0x0296 }
            int r4 = r1.frameRsv     // Catch:{ all -> 0x0296 }
            r0.<init>(r2, r4, r5)     // Catch:{ all -> 0x0296 }
            r3.add(r0)     // Catch:{ all -> 0x0296 }
            return
        L_0x0231:
            r6 = 8
            if (r2 != r6) goto L_0x0247
            r1.receivedClosingHandshake = r15     // Catch:{ all -> 0x0296 }
            r1.checkCloseFrameBody(r0, r5)     // Catch:{ all -> 0x0296 }
            io.netty.handler.codec.http.websocketx.CloseWebSocketFrame r0 = new io.netty.handler.codec.http.websocketx.CloseWebSocketFrame     // Catch:{ all -> 0x0296 }
            boolean r2 = r1.frameFinalFlag     // Catch:{ all -> 0x0296 }
            int r4 = r1.frameRsv     // Catch:{ all -> 0x0296 }
            r0.<init>(r2, r4, r5)     // Catch:{ all -> 0x0296 }
            r3.add(r0)     // Catch:{ all -> 0x0296 }
            return
        L_0x0247:
            boolean r0 = r1.frameFinalFlag     // Catch:{ all -> 0x0296 }
            if (r0 == 0) goto L_0x024f
            r0 = 0
            r1.fragmentedFramesCount = r0     // Catch:{ all -> 0x0296 }
            goto L_0x0254
        L_0x024f:
            int r0 = r1.fragmentedFramesCount     // Catch:{ all -> 0x0296 }
            int r0 = r0 + r15
            r1.fragmentedFramesCount = r0     // Catch:{ all -> 0x0296 }
        L_0x0254:
            if (r2 != r15) goto L_0x0263
            io.netty.handler.codec.http.websocketx.TextWebSocketFrame r0 = new io.netty.handler.codec.http.websocketx.TextWebSocketFrame     // Catch:{ all -> 0x0296 }
            boolean r2 = r1.frameFinalFlag     // Catch:{ all -> 0x0296 }
            int r4 = r1.frameRsv     // Catch:{ all -> 0x0296 }
            r0.<init>((boolean) r2, (int) r4, (io.netty.buffer.ByteBuf) r5)     // Catch:{ all -> 0x0296 }
            r3.add(r0)     // Catch:{ all -> 0x0296 }
            return
        L_0x0263:
            r0 = 2
            if (r2 != r0) goto L_0x0273
            io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame r0 = new io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame     // Catch:{ all -> 0x0296 }
            boolean r2 = r1.frameFinalFlag     // Catch:{ all -> 0x0296 }
            int r4 = r1.frameRsv     // Catch:{ all -> 0x0296 }
            r0.<init>(r2, r4, r5)     // Catch:{ all -> 0x0296 }
            r3.add(r0)     // Catch:{ all -> 0x0296 }
            return
        L_0x0273:
            if (r2 != 0) goto L_0x0282
            io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame r0 = new io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame     // Catch:{ all -> 0x0296 }
            boolean r2 = r1.frameFinalFlag     // Catch:{ all -> 0x0296 }
            int r4 = r1.frameRsv     // Catch:{ all -> 0x0296 }
            r0.<init>((boolean) r2, (int) r4, (io.netty.buffer.ByteBuf) r5)     // Catch:{ all -> 0x0296 }
            r3.add(r0)     // Catch:{ all -> 0x0296 }
            return
        L_0x0282:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ all -> 0x0296 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0296 }
            r2.<init>(r4)     // Catch:{ all -> 0x0296 }
            int r3 = r1.frameOpcode     // Catch:{ all -> 0x0296 }
            r2.append(r3)     // Catch:{ all -> 0x0296 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0296 }
            r0.<init>(r2)     // Catch:{ all -> 0x0296 }
            throw r0     // Catch:{ all -> 0x0296 }
        L_0x0296:
            r0 = move-exception
            if (r5 == 0) goto L_0x029c
            r5.release()
        L_0x029c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List):void");
    }

    /* renamed from: io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http$websocketx$WebSocket08FrameDecoder$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$State[] r0 = io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$codec$http$websocketx$WebSocket08FrameDecoder$State = r0
                io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$State r1 = io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.State.READING_FIRST     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$websocketx$WebSocket08FrameDecoder$State     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$State r1 = io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.State.READING_SECOND     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$websocketx$WebSocket08FrameDecoder$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$State r1 = io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.State.READING_SIZE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$websocketx$WebSocket08FrameDecoder$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$State r1 = io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.State.MASKING_KEY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$websocketx$WebSocket08FrameDecoder$State     // Catch:{ NoSuchFieldError -> 0x003e }
                io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$State r1 = io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.State.PAYLOAD     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$websocketx$WebSocket08FrameDecoder$State     // Catch:{ NoSuchFieldError -> 0x0049 }
                io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$State r1 = io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.State.CORRUPT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder.AnonymousClass1.<clinit>():void");
        }
    }

    private void unmask(ByteBuf byteBuf) {
        int readerIndex = byteBuf.readerIndex();
        int writerIndex = byteBuf.writerIndex();
        ByteOrder order = byteBuf.order();
        int i = this.mask;
        long j = ((long) i) & 4294967295L;
        long j2 = j | (j << 32);
        int i2 = writerIndex - 7;
        while (readerIndex < i2) {
            byteBuf.setLong(readerIndex, byteBuf.getLong(readerIndex) ^ j2);
            readerIndex += 8;
        }
        if (readerIndex < writerIndex - 3) {
            byteBuf.setInt(readerIndex, byteBuf.getInt(readerIndex) ^ ((int) j2));
            readerIndex += 4;
        }
        if (order == ByteOrder.LITTLE_ENDIAN) {
            i = Integer.reverseBytes(i);
        }
        int i3 = 0;
        while (readerIndex < writerIndex) {
            byteBuf.setByte(readerIndex, WebSocketUtil.byteAtIndex(i, i3 & 3) ^ byteBuf.getByte(readerIndex));
            readerIndex++;
            i3++;
        }
    }

    private void protocolViolation(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, String str) {
        protocolViolation(channelHandlerContext, byteBuf, WebSocketCloseStatus.PROTOCOL_ERROR, str);
    }

    private void protocolViolation(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, WebSocketCloseStatus webSocketCloseStatus, String str) {
        protocolViolation(channelHandlerContext, byteBuf, new CorruptedWebSocketFrameException(webSocketCloseStatus, str));
    }

    private void protocolViolation(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, CorruptedWebSocketFrameException corruptedWebSocketFrameException) {
        Object obj;
        this.state = State.CORRUPT;
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes > 0) {
            byteBuf.skipBytes(readableBytes);
        }
        if (channelHandlerContext.channel().isActive() && this.config.closeOnProtocolViolation()) {
            if (!this.receivedClosingHandshake) {
                WebSocketCloseStatus closeStatus = corruptedWebSocketFrameException.closeStatus();
                String message = corruptedWebSocketFrameException.getMessage();
                if (message == null) {
                    message = closeStatus.reasonText();
                }
                obj = new CloseWebSocketFrame(closeStatus, message);
            } else {
                obj = Unpooled.EMPTY_BUFFER;
            }
            channelHandlerContext.writeAndFlush(obj).addListener(ChannelFutureListener.CLOSE);
        }
        throw corruptedWebSocketFrameException;
    }

    private static int toFrameLength(long j) {
        if (j <= 2147483647L) {
            return (int) j;
        }
        throw new TooLongFrameException("Length:" + j);
    }

    /* access modifiers changed from: protected */
    public void checkCloseFrameBody(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        if (byteBuf != null && byteBuf.isReadable()) {
            if (byteBuf.readableBytes() < 2) {
                protocolViolation(channelHandlerContext, byteBuf, WebSocketCloseStatus.INVALID_PAYLOAD_DATA, "Invalid close frame body");
            }
            short s = byteBuf.getShort(byteBuf.readerIndex());
            if (!WebSocketCloseStatus.isValidStatusCode(s)) {
                protocolViolation(channelHandlerContext, byteBuf, "Invalid close frame getStatus code: " + s);
            }
            if (byteBuf.readableBytes() > 2) {
                try {
                    new Utf8Validator().check(byteBuf, byteBuf.readerIndex() + 2, byteBuf.readableBytes() - 2);
                } catch (CorruptedWebSocketFrameException e) {
                    protocolViolation(channelHandlerContext, byteBuf, e);
                }
            }
        }
    }
}
