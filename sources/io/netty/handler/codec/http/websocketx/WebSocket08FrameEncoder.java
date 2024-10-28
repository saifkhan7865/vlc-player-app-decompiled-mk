package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteOrder;
import java.util.List;
import org.videolan.vlc.util.Permissions;

public class WebSocket08FrameEncoder extends MessageToMessageEncoder<WebSocketFrame> implements WebSocketFrameEncoder {
    private static final int GATHERING_WRITE_THRESHOLD = 1024;
    private static final byte OPCODE_BINARY = 2;
    private static final byte OPCODE_CLOSE = 8;
    private static final byte OPCODE_CONT = 0;
    private static final byte OPCODE_PING = 9;
    private static final byte OPCODE_PONG = 10;
    private static final byte OPCODE_TEXT = 1;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) WebSocket08FrameEncoder.class);
    private final boolean maskPayload;

    public WebSocket08FrameEncoder(boolean z) {
        this.maskPayload = z;
    }

    /* access modifiers changed from: protected */
    public void encode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List<Object> list) throws Exception {
        byte b;
        ByteBuf byteBuf;
        ByteBuf content = webSocketFrame.content();
        int i = 0;
        if (webSocketFrame instanceof TextWebSocketFrame) {
            b = 1;
        } else if (webSocketFrame instanceof PingWebSocketFrame) {
            b = 9;
        } else if (webSocketFrame instanceof PongWebSocketFrame) {
            b = 10;
        } else if (webSocketFrame instanceof CloseWebSocketFrame) {
            b = 8;
        } else if (webSocketFrame instanceof BinaryWebSocketFrame) {
            b = 2;
        } else if (webSocketFrame instanceof ContinuationWebSocketFrame) {
            b = 0;
        } else {
            throw new UnsupportedOperationException("Cannot encode frame of type: " + webSocketFrame.getClass().getName());
        }
        int readableBytes = content.readableBytes();
        InternalLogger internalLogger = logger;
        if (internalLogger.isTraceEnabled()) {
            internalLogger.trace("Encoding WebSocket Frame opCode={} length={}", Byte.valueOf(b), Integer.valueOf(readableBytes));
        }
        int rsv = ((webSocketFrame.rsv() % 8) << 4) | (webSocketFrame.isFinalFragment() ? 128 : 0) | (b % 128);
        if (b != 9 || readableBytes <= 125) {
            ByteBuf byteBuf2 = null;
            try {
                boolean z = this.maskPayload;
                int i2 = z ? 4 : 0;
                if (readableBytes <= 125) {
                    byteBuf = channelHandlerContext.alloc().buffer(i2 + 2 + readableBytes);
                    byteBuf.writeByte(rsv);
                    byteBuf.writeByte((byte) (this.maskPayload ? ((byte) readableBytes) | 128 : (byte) readableBytes));
                } else {
                    int i3 = 255;
                    if (readableBytes <= 65535) {
                        int i4 = i2 + 4;
                        if (z || readableBytes <= 1024) {
                            i4 += readableBytes;
                        }
                        byteBuf = channelHandlerContext.alloc().buffer(i4);
                        byteBuf.writeByte(rsv);
                        byteBuf.writeByte(this.maskPayload ? Permissions.PERMISSION_SETTINGS_TAG : 126);
                        byteBuf.writeByte((readableBytes >>> 8) & 255);
                        byteBuf.writeByte(readableBytes & 255);
                    } else {
                        int i5 = i2 + 10;
                        if (z) {
                            i5 += readableBytes;
                        }
                        byteBuf = channelHandlerContext.alloc().buffer(i5);
                        byteBuf.writeByte(rsv);
                        if (!this.maskPayload) {
                            i3 = 127;
                        }
                        byteBuf.writeByte(i3);
                        byteBuf.writeLong((long) readableBytes);
                    }
                }
                if (this.maskPayload) {
                    int nextInt = PlatformDependent.threadLocalRandom().nextInt(Integer.MAX_VALUE);
                    byteBuf.writeInt(nextInt);
                    if (content.isReadable()) {
                        ByteOrder order = content.order();
                        ByteOrder order2 = byteBuf.order();
                        int readerIndex = content.readerIndex();
                        int writerIndex = content.writerIndex();
                        if (order == order2) {
                            long j = ((long) nextInt) & 4294967295L;
                            long j2 = j | (j << 32);
                            if (order == ByteOrder.LITTLE_ENDIAN) {
                                j2 = Long.reverseBytes(j2);
                            }
                            int i6 = writerIndex - 7;
                            while (readerIndex < i6) {
                                byteBuf.writeLong(content.getLong(readerIndex) ^ j2);
                                readerIndex += 8;
                            }
                            if (readerIndex < writerIndex - 3) {
                                byteBuf.writeInt(content.getInt(readerIndex) ^ ((int) j2));
                                readerIndex += 4;
                            }
                        }
                        while (readerIndex < writerIndex) {
                            byteBuf.writeByte(content.getByte(readerIndex) ^ WebSocketUtil.byteAtIndex(nextInt, i & 3));
                            readerIndex++;
                            i++;
                        }
                    }
                    list.add(byteBuf);
                } else if (byteBuf.writableBytes() >= content.readableBytes()) {
                    byteBuf.writeBytes(content);
                    list.add(byteBuf);
                } else {
                    list.add(byteBuf);
                    list.add(content.retain());
                }
            } catch (Throwable th) {
                if (byteBuf2 != null) {
                    byteBuf2.release();
                }
                throw th;
            }
        } else {
            throw new TooLongFrameException("invalid payload for PING (payload length must be <= 125, was " + readableBytes);
        }
    }
}
