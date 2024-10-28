package io.netty.handler.codec.protobuf;

import com.google.common.base.Ascii;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import java.util.List;

public class ProtobufVarint32FrameDecoder extends ByteToMessageDecoder {
    /* access modifiers changed from: protected */
    public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byteBuf.markReaderIndex();
        int readerIndex = byteBuf.readerIndex();
        int readRawVarint32 = readRawVarint32(byteBuf);
        if (readerIndex != byteBuf.readerIndex()) {
            if (readRawVarint32 < 0) {
                throw new CorruptedFrameException("negative length: " + readRawVarint32);
            } else if (byteBuf.readableBytes() < readRawVarint32) {
                byteBuf.resetReaderIndex();
            } else {
                list.add(byteBuf.readRetainedSlice(readRawVarint32));
            }
        }
    }

    private static int readRawVarint32(ByteBuf byteBuf) {
        int i;
        if (!byteBuf.isReadable()) {
            return 0;
        }
        byteBuf.markReaderIndex();
        byte readByte = byteBuf.readByte();
        if (readByte >= 0) {
            return readByte;
        }
        byte b = readByte & Byte.MAX_VALUE;
        if (!byteBuf.isReadable()) {
            byteBuf.resetReaderIndex();
            return 0;
        }
        byte readByte2 = byteBuf.readByte();
        if (readByte2 >= 0) {
            i = readByte2 << 7;
        } else {
            b |= (readByte2 & Byte.MAX_VALUE) << 7;
            if (!byteBuf.isReadable()) {
                byteBuf.resetReaderIndex();
                return 0;
            }
            byte readByte3 = byteBuf.readByte();
            if (readByte3 >= 0) {
                i = readByte3 << Ascii.SO;
            } else {
                b |= (readByte3 & Byte.MAX_VALUE) << Ascii.SO;
                if (!byteBuf.isReadable()) {
                    byteBuf.resetReaderIndex();
                    return 0;
                }
                byte readByte4 = byteBuf.readByte();
                if (readByte4 >= 0) {
                    i = readByte4 << Ascii.NAK;
                } else {
                    byte b2 = b | ((readByte4 & Byte.MAX_VALUE) << Ascii.NAK);
                    if (!byteBuf.isReadable()) {
                        byteBuf.resetReaderIndex();
                        return 0;
                    }
                    byte readByte5 = byteBuf.readByte();
                    byte b3 = b2 | (readByte5 << Ascii.FS);
                    if (readByte5 >= 0) {
                        return b3;
                    }
                    throw new CorruptedFrameException("malformed varint.");
                }
            }
        }
        return i | b;
    }
}
