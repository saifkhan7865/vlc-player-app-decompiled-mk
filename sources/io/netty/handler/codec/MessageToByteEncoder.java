package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.TypeParameterMatcher;

public abstract class MessageToByteEncoder<I> extends ChannelOutboundHandlerAdapter {
    private final TypeParameterMatcher matcher;
    private final boolean preferDirect;

    /* access modifiers changed from: protected */
    public abstract void encode(ChannelHandlerContext channelHandlerContext, I i, ByteBuf byteBuf) throws Exception;

    protected MessageToByteEncoder() {
        this(true);
    }

    protected MessageToByteEncoder(Class<? extends I> cls) {
        this(cls, true);
    }

    protected MessageToByteEncoder(boolean z) {
        this.matcher = TypeParameterMatcher.find(this, MessageToByteEncoder.class, "I");
        this.preferDirect = z;
    }

    protected MessageToByteEncoder(Class<? extends I> cls, boolean z) {
        this.matcher = TypeParameterMatcher.get(cls);
        this.preferDirect = z;
    }

    public boolean acceptOutboundMessage(Object obj) throws Exception {
        return this.matcher.match(obj);
    }

    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        ByteBuf byteBuf = null;
        try {
            if (acceptOutboundMessage(obj)) {
                byteBuf = allocateBuffer(channelHandlerContext, obj, this.preferDirect);
                encode(channelHandlerContext, obj, byteBuf);
                ReferenceCountUtil.release(obj);
                if (byteBuf.isReadable()) {
                    channelHandlerContext.write(byteBuf, channelPromise);
                    return;
                }
                byteBuf.release();
                channelHandlerContext.write(Unpooled.EMPTY_BUFFER, channelPromise);
                return;
            }
            channelHandlerContext.write(obj, channelPromise);
        } catch (EncoderException e) {
            throw e;
        } catch (Throwable th) {
            try {
                throw new EncoderException(th);
            } catch (Throwable th2) {
                if (byteBuf != null) {
                    byteBuf.release();
                }
                throw th2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public ByteBuf allocateBuffer(ChannelHandlerContext channelHandlerContext, I i, boolean z) throws Exception {
        if (z) {
            return channelHandlerContext.alloc().ioBuffer();
        }
        return channelHandlerContext.alloc().heapBuffer();
    }

    /* access modifiers changed from: protected */
    public boolean isPreferDirect() {
        return this.preferDirect;
    }
}
