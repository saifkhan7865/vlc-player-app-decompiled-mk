package io.netty.handler.codec.compression;

import com.aayushatharva.brotli4j.encoder.BrotliEncoderChannel;
import com.aayushatharva.brotli4j.encoder.Encoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;

@ChannelHandler.Sharable
public final class BrotliEncoder extends MessageToByteEncoder<ByteBuf> {
    private static final AttributeKey<Writer> ATTR = AttributeKey.valueOf("BrotliEncoderWriter");
    private final boolean isSharable;
    private final Encoder.Parameters parameters;
    private Writer writer;

    /* access modifiers changed from: protected */
    public void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
    }

    public BrotliEncoder() {
        this(BrotliOptions.DEFAULT);
    }

    public BrotliEncoder(BrotliOptions brotliOptions) {
        this(brotliOptions.parameters());
    }

    public BrotliEncoder(Encoder.Parameters parameters2) {
        this(parameters2, true);
    }

    public BrotliEncoder(Encoder.Parameters parameters2, boolean z) {
        this.parameters = (Encoder.Parameters) ObjectUtil.checkNotNull(parameters2, "Parameters");
        this.isSharable = z;
    }

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        Writer writer2 = new Writer(this.parameters, channelHandlerContext);
        if (this.isSharable) {
            channelHandlerContext.channel().attr(ATTR).set(writer2);
        } else {
            this.writer = writer2;
        }
        super.handlerAdded(channelHandlerContext);
    }

    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        finish(channelHandlerContext);
        super.handlerRemoved(channelHandlerContext);
    }

    /* access modifiers changed from: protected */
    public ByteBuf allocateBuffer(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, boolean z) throws Exception {
        Writer writer2;
        if (!byteBuf.isReadable()) {
            return Unpooled.EMPTY_BUFFER;
        }
        if (this.isSharable) {
            writer2 = channelHandlerContext.channel().attr(ATTR).get();
        } else {
            writer2 = this.writer;
        }
        if (writer2 == null) {
            return Unpooled.EMPTY_BUFFER;
        }
        writer2.encode(byteBuf, z);
        return writer2.writableBuffer;
    }

    public boolean isSharable() {
        return this.isSharable;
    }

    public void finish(ChannelHandlerContext channelHandlerContext) throws IOException {
        finishEncode(channelHandlerContext, channelHandlerContext.newPromise());
    }

    private ChannelFuture finishEncode(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws IOException {
        Writer writer2;
        if (this.isSharable) {
            writer2 = channelHandlerContext.channel().attr(ATTR).getAndSet(null);
        } else {
            writer2 = this.writer;
        }
        if (writer2 != null) {
            writer2.close();
            this.writer = null;
        }
        return channelPromise;
    }

    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        EncoderUtil.closeAfterFinishEncode(channelHandlerContext, finishEncode(channelHandlerContext, channelHandlerContext.newPromise()), channelPromise);
    }

    private static final class Writer implements WritableByteChannel {
        private final BrotliEncoderChannel brotliEncoderChannel;
        private final ChannelHandlerContext ctx;
        private boolean isClosed;
        /* access modifiers changed from: private */
        public ByteBuf writableBuffer;

        private Writer(Encoder.Parameters parameters, ChannelHandlerContext channelHandlerContext) throws IOException {
            this.brotliEncoderChannel = new BrotliEncoderChannel(this, parameters);
            this.ctx = channelHandlerContext;
        }

        /* access modifiers changed from: private */
        public void encode(ByteBuf byteBuf, boolean z) throws Exception {
            try {
                allocate(z);
                ByteBuffer safeReadableNioBuffer = CompressionUtil.safeReadableNioBuffer(byteBuf);
                int position = safeReadableNioBuffer.position();
                this.brotliEncoderChannel.write(safeReadableNioBuffer);
                byteBuf.skipBytes(safeReadableNioBuffer.position() - position);
                this.brotliEncoderChannel.flush();
            } catch (Exception e) {
                ReferenceCountUtil.release(byteBuf);
                throw e;
            }
        }

        private void allocate(boolean z) {
            if (z) {
                this.writableBuffer = this.ctx.alloc().ioBuffer();
            } else {
                this.writableBuffer = this.ctx.alloc().buffer();
            }
        }

        public int write(ByteBuffer byteBuffer) throws IOException {
            if (isOpen()) {
                return this.writableBuffer.writeBytes(byteBuffer).readableBytes();
            }
            throw new ClosedChannelException();
        }

        public boolean isOpen() {
            return !this.isClosed;
        }

        public void close() {
            final ChannelPromise newPromise = this.ctx.newPromise();
            this.ctx.executor().execute(new Runnable() {
                public void run() {
                    try {
                        Writer.this.finish(newPromise);
                    } catch (IOException e) {
                        newPromise.setFailure(new IllegalStateException("Failed to finish encoding", e));
                    }
                }
            });
        }

        public void finish(ChannelPromise channelPromise) throws IOException {
            if (!this.isClosed) {
                allocate(true);
                try {
                    this.brotliEncoderChannel.close();
                    this.isClosed = true;
                    this.ctx.writeAndFlush(this.writableBuffer, channelPromise);
                } catch (Exception e) {
                    channelPromise.setFailure(e);
                    ReferenceCountUtil.release(this.writableBuffer);
                }
            }
        }
    }
}
