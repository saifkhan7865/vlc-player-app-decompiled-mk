package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.ObjectUtil;
import java.util.List;

public class DelimiterBasedFrameDecoder extends ByteToMessageDecoder {
    private final ByteBuf[] delimiters;
    private boolean discardingTooLongFrame;
    private final boolean failFast;
    private final LineBasedFrameDecoder lineBasedDecoder;
    private final int maxFrameLength;
    private final boolean stripDelimiter;
    private int tooLongFrameLength;

    public DelimiterBasedFrameDecoder(int i, ByteBuf byteBuf) {
        this(i, true, byteBuf);
    }

    public DelimiterBasedFrameDecoder(int i, boolean z, ByteBuf byteBuf) {
        this(i, z, true, byteBuf);
    }

    public DelimiterBasedFrameDecoder(int i, boolean z, boolean z2, ByteBuf byteBuf) {
        this(i, z, z2, byteBuf.slice(byteBuf.readerIndex(), byteBuf.readableBytes()));
    }

    public DelimiterBasedFrameDecoder(int i, ByteBuf... byteBufArr) {
        this(i, true, byteBufArr);
    }

    public DelimiterBasedFrameDecoder(int i, boolean z, ByteBuf... byteBufArr) {
        this(i, z, true, byteBufArr);
    }

    public DelimiterBasedFrameDecoder(int i, boolean z, boolean z2, ByteBuf... byteBufArr) {
        validateMaxFrameLength(i);
        ObjectUtil.checkNonEmpty((T[]) byteBufArr, "delimiters");
        if (!isLineBased(byteBufArr) || isSubclass()) {
            this.delimiters = new ByteBuf[byteBufArr.length];
            for (int i2 = 0; i2 < byteBufArr.length; i2++) {
                ByteBuf byteBuf = byteBufArr[i2];
                validateDelimiter(byteBuf);
                this.delimiters[i2] = byteBuf.slice(byteBuf.readerIndex(), byteBuf.readableBytes());
            }
            this.lineBasedDecoder = null;
        } else {
            this.lineBasedDecoder = new LineBasedFrameDecoder(i, z, z2);
            this.delimiters = null;
        }
        this.maxFrameLength = i;
        this.stripDelimiter = z;
        this.failFast = z2;
    }

    private static boolean isLineBased(ByteBuf[] byteBufArr) {
        if (byteBufArr.length != 2) {
            return false;
        }
        ByteBuf byteBuf = byteBufArr[0];
        ByteBuf byteBuf2 = byteBufArr[1];
        if (byteBuf.capacity() < byteBuf2.capacity()) {
            byteBuf = byteBufArr[1];
            byteBuf2 = byteBufArr[0];
        }
        if (byteBuf.capacity() == 2 && byteBuf2.capacity() == 1 && byteBuf.getByte(0) == 13 && byteBuf.getByte(1) == 10 && byteBuf2.getByte(0) == 10) {
            return true;
        }
        return false;
    }

    private boolean isSubclass() {
        return getClass() != DelimiterBasedFrameDecoder.class;
    }

    /* access modifiers changed from: protected */
    public final void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Object decode = decode(channelHandlerContext, byteBuf);
        if (decode != null) {
            list.add(decode);
        }
    }

    /* access modifiers changed from: protected */
    public Object decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        LineBasedFrameDecoder lineBasedFrameDecoder = this.lineBasedDecoder;
        if (lineBasedFrameDecoder != null) {
            return lineBasedFrameDecoder.decode(channelHandlerContext, byteBuf);
        }
        int i = Integer.MAX_VALUE;
        ByteBuf byteBuf2 = null;
        for (ByteBuf byteBuf3 : this.delimiters) {
            int indexOf = indexOf(byteBuf, byteBuf3);
            if (indexOf >= 0 && indexOf < i) {
                byteBuf2 = byteBuf3;
                i = indexOf;
            }
        }
        if (byteBuf2 != null) {
            int capacity = byteBuf2.capacity();
            if (this.discardingTooLongFrame) {
                this.discardingTooLongFrame = false;
                byteBuf.skipBytes(i + capacity);
                int i2 = this.tooLongFrameLength;
                this.tooLongFrameLength = 0;
                if (!this.failFast) {
                    fail((long) i2);
                }
                return null;
            } else if (i > this.maxFrameLength) {
                byteBuf.skipBytes(capacity + i);
                fail((long) i);
                return null;
            } else if (!this.stripDelimiter) {
                return byteBuf.readRetainedSlice(i + capacity);
            } else {
                ByteBuf readRetainedSlice = byteBuf.readRetainedSlice(i);
                byteBuf.skipBytes(capacity);
                return readRetainedSlice;
            }
        } else {
            if (this.discardingTooLongFrame) {
                this.tooLongFrameLength += byteBuf.readableBytes();
                byteBuf.skipBytes(byteBuf.readableBytes());
            } else if (byteBuf.readableBytes() > this.maxFrameLength) {
                this.tooLongFrameLength = byteBuf.readableBytes();
                byteBuf.skipBytes(byteBuf.readableBytes());
                this.discardingTooLongFrame = true;
                if (this.failFast) {
                    fail((long) this.tooLongFrameLength);
                }
            }
            return null;
        }
    }

    private void fail(long j) {
        if (j > 0) {
            throw new TooLongFrameException("frame length exceeds " + this.maxFrameLength + ": " + j + " - discarded");
        }
        throw new TooLongFrameException("frame length exceeds " + this.maxFrameLength + " - discarding");
    }

    private static int indexOf(ByteBuf byteBuf, ByteBuf byteBuf2) {
        int indexOf = ByteBufUtil.indexOf(byteBuf2, byteBuf);
        if (indexOf == -1) {
            return -1;
        }
        return indexOf - byteBuf.readerIndex();
    }

    private static void validateDelimiter(ByteBuf byteBuf) {
        ObjectUtil.checkNotNull(byteBuf, "delimiter");
        if (!byteBuf.isReadable()) {
            throw new IllegalArgumentException("empty delimiter");
        }
    }

    private static void validateMaxFrameLength(int i) {
        ObjectUtil.checkPositive(i, "maxFrameLength");
    }
}
