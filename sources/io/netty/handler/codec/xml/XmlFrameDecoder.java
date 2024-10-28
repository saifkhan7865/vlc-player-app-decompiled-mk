package io.netty.handler.codec.xml;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.util.internal.ObjectUtil;

public class XmlFrameDecoder extends ByteToMessageDecoder {
    private final int maxFrameLength;

    private static boolean isValidStartCharForXmlElement(byte b) {
        return (b >= 97 && b <= 122) || (b >= 65 && b <= 90) || b == 58 || b == 95;
    }

    public XmlFrameDecoder(int i) {
        this.maxFrameLength = ObjectUtil.checkPositive(i, "maxFrameLength");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x008e, code lost:
        if (r15 == 63) goto L_0x0090;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void decode(io.netty.channel.ChannelHandlerContext r19, io.netty.buffer.ByteBuf r20, java.util.List<java.lang.Object> r21) throws java.lang.Exception {
        /*
            r18 = this;
            r0 = r18
            r1 = r20
            int r2 = r20.writerIndex()
            int r3 = r0.maxFrameLength
            if (r2 <= r3) goto L_0x0018
            int r3 = r20.readableBytes()
            r1.skipBytes(r3)
            long r1 = (long) r2
            r0.fail((long) r1)
            return
        L_0x0018:
            int r3 = r20.readerIndex()
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r12 = 0
            r13 = 0
        L_0x0023:
            if (r3 >= r2) goto L_0x00e8
            byte r14 = r1.getByte(r3)
            if (r7 != 0) goto L_0x0035
            boolean r15 = java.lang.Character.isWhitespace(r14)
            if (r15 == 0) goto L_0x0035
            int r8 = r8 + 1
            goto L_0x00e4
        L_0x0035:
            r15 = 60
            if (r7 != 0) goto L_0x0046
            if (r14 == r15) goto L_0x0046
            fail((io.netty.channel.ChannelHandlerContext) r19)
            int r2 = r20.readableBytes()
            r1.skipBytes(r2)
            return
        L_0x0046:
            r4 = 63
            r5 = 47
            r6 = 62
            r16 = 1
            if (r9 != 0) goto L_0x0094
            if (r14 != r15) goto L_0x0094
            int r7 = r2 + -1
            r14 = 1
            if (r3 >= r7) goto L_0x0092
            int r15 = r3 + 1
            byte r15 = r1.getByte(r15)
            if (r15 != r5) goto L_0x006f
            int r4 = r3 + 2
        L_0x0061:
            if (r4 > r7) goto L_0x0092
            byte r5 = r1.getByte(r4)
            if (r5 != r6) goto L_0x006c
            long r10 = r10 - r16
            goto L_0x0092
        L_0x006c:
            int r4 = r4 + 1
            goto L_0x0061
        L_0x006f:
            boolean r5 = isValidStartCharForXmlElement(r15)
            if (r5 == 0) goto L_0x0079
            long r10 = r10 + r16
            r13 = 1
            goto L_0x0092
        L_0x0079:
            r5 = 33
            if (r15 != r5) goto L_0x008e
            boolean r4 = isCommentBlockStart(r1, r3)
            if (r4 == 0) goto L_0x0084
            goto L_0x0090
        L_0x0084:
            boolean r4 = isCDATABlockStart(r1, r3)
            if (r4 == 0) goto L_0x0092
            long r10 = r10 + r16
            r9 = 1
            goto L_0x0092
        L_0x008e:
            if (r15 != r4) goto L_0x0092
        L_0x0090:
            long r10 = r10 + r16
        L_0x0092:
            r7 = 1
            goto L_0x00e4
        L_0x0094:
            if (r9 != 0) goto L_0x00a7
            if (r14 != r5) goto L_0x00a7
            int r4 = r2 + -1
            if (r3 >= r4) goto L_0x00e4
            int r4 = r3 + 1
            byte r4 = r1.getByte(r4)
            if (r4 != r6) goto L_0x00e4
            long r10 = r10 - r16
            goto L_0x00e4
        L_0x00a7:
            if (r14 != r6) goto L_0x00e4
            int r12 = r3 + 1
            int r5 = r3 + -1
            r6 = -1
            if (r5 <= r6) goto L_0x00db
            byte r5 = r1.getByte(r5)
            if (r9 != 0) goto L_0x00ca
            if (r5 != r4) goto L_0x00bb
        L_0x00b8:
            long r10 = r10 - r16
            goto L_0x00db
        L_0x00bb:
            r4 = 45
            if (r5 != r4) goto L_0x00db
            int r5 = r3 + -2
            if (r5 <= r6) goto L_0x00db
            byte r5 = r1.getByte(r5)
            if (r5 != r4) goto L_0x00db
            goto L_0x00b8
        L_0x00ca:
            r4 = 93
            if (r5 != r4) goto L_0x00db
            int r5 = r3 + -2
            if (r5 <= r6) goto L_0x00db
            byte r5 = r1.getByte(r5)
            if (r5 != r4) goto L_0x00db
            long r10 = r10 - r16
            r9 = 0
        L_0x00db:
            if (r13 == 0) goto L_0x00e4
            r4 = 0
            int r6 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x00e4
            goto L_0x00e8
        L_0x00e4:
            int r3 = r3 + 1
            goto L_0x0023
        L_0x00e8:
            int r3 = r20.readerIndex()
            int r12 = r12 - r3
            r4 = 0
            int r6 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x010c
            if (r12 <= 0) goto L_0x010c
            int r4 = r3 + r12
            if (r4 < r2) goto L_0x00fd
            int r12 = r20.readableBytes()
        L_0x00fd:
            int r3 = r3 + r8
            int r2 = r12 - r8
            io.netty.buffer.ByteBuf r2 = extractFrame(r1, r3, r2)
            r1.skipBytes(r12)
            r1 = r21
            r1.add(r2)
        L_0x010c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.xml.XmlFrameDecoder.decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List):void");
    }

    private void fail(long j) {
        if (j > 0) {
            throw new TooLongFrameException("frame length exceeds " + this.maxFrameLength + ": " + j + " - discarded");
        }
        throw new TooLongFrameException("frame length exceeds " + this.maxFrameLength + " - discarding");
    }

    private static void fail(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.fireExceptionCaught(new CorruptedFrameException("frame contains content before the xml starts"));
    }

    private static ByteBuf extractFrame(ByteBuf byteBuf, int i, int i2) {
        return byteBuf.copy(i, i2);
    }

    private static boolean isCommentBlockStart(ByteBuf byteBuf, int i) {
        return i < byteBuf.writerIndex() + -3 && byteBuf.getByte(i + 2) == 45 && byteBuf.getByte(i + 3) == 45;
    }

    private static boolean isCDATABlockStart(ByteBuf byteBuf, int i) {
        return i < byteBuf.writerIndex() + -8 && byteBuf.getByte(i + 2) == 91 && byteBuf.getByte(i + 3) == 67 && byteBuf.getByte(i + 4) == 68 && byteBuf.getByte(i + 5) == 65 && byteBuf.getByte(i + 6) == 84 && byteBuf.getByte(i + 7) == 65 && byteBuf.getByte(i + 8) == 91;
    }
}
