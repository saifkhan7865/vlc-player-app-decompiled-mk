package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.ObjectUtil;
import java.util.List;

public abstract class MessageAggregator<I, S, C extends ByteBufHolder, O extends ByteBufHolder> extends MessageToMessageDecoder<I> {
    private static final int DEFAULT_MAX_COMPOSITEBUFFER_COMPONENTS = 1024;
    private boolean aggregating;
    private ChannelFutureListener continueResponseWriteListener;
    private ChannelHandlerContext ctx;
    private O currentMessage;
    private boolean handleIncompleteAggregateDuringClose = true;
    private boolean handlingOversizedMessage;
    private final int maxContentLength;
    private int maxCumulationBufferComponents = 1024;

    /* access modifiers changed from: protected */
    public void aggregate(O o, C c) throws Exception {
    }

    /* access modifiers changed from: protected */
    public abstract O beginAggregation(S s, ByteBuf byteBuf) throws Exception;

    /* access modifiers changed from: protected */
    public abstract boolean closeAfterContinueResponse(Object obj) throws Exception;

    /* access modifiers changed from: protected */
    public void finishAggregation(O o) throws Exception {
    }

    /* access modifiers changed from: protected */
    public abstract boolean ignoreContentAfterContinueResponse(Object obj) throws Exception;

    /* access modifiers changed from: protected */
    public abstract boolean isAggregated(I i) throws Exception;

    /* access modifiers changed from: protected */
    public abstract boolean isContentLengthInvalid(S s, int i) throws Exception;

    /* access modifiers changed from: protected */
    public abstract boolean isContentMessage(I i) throws Exception;

    /* access modifiers changed from: protected */
    public abstract boolean isLastContentMessage(C c) throws Exception;

    /* access modifiers changed from: protected */
    public abstract boolean isStartMessage(I i) throws Exception;

    /* access modifiers changed from: protected */
    public abstract Object newContinueResponse(S s, int i, ChannelPipeline channelPipeline) throws Exception;

    protected MessageAggregator(int i) {
        validateMaxContentLength(i);
        this.maxContentLength = i;
    }

    protected MessageAggregator(int i, Class<? extends I> cls) {
        super(cls);
        validateMaxContentLength(i);
        this.maxContentLength = i;
    }

    private static void validateMaxContentLength(int i) {
        ObjectUtil.checkPositiveOrZero(i, "maxContentLength");
    }

    public boolean acceptInboundMessage(Object obj) throws Exception {
        if (!super.acceptInboundMessage(obj) || isAggregated(obj)) {
            return false;
        }
        if (isStartMessage(obj)) {
            return true;
        }
        if (!this.aggregating || !isContentMessage(obj)) {
            return false;
        }
        return true;
    }

    public final int maxContentLength() {
        return this.maxContentLength;
    }

    public final int maxCumulationBufferComponents() {
        return this.maxCumulationBufferComponents;
    }

    public final void setMaxCumulationBufferComponents(int i) {
        if (i < 2) {
            throw new IllegalArgumentException("maxCumulationBufferComponents: " + i + " (expected: >= 2)");
        } else if (this.ctx == null) {
            this.maxCumulationBufferComponents = i;
        } else {
            throw new IllegalStateException("decoder properties cannot be changed once the decoder is added to a pipeline.");
        }
    }

    @Deprecated
    public final boolean isHandlingOversizedMessage() {
        return this.handlingOversizedMessage;
    }

    /* access modifiers changed from: protected */
    public final ChannelHandlerContext ctx() {
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext != null) {
            return channelHandlerContext;
        }
        throw new IllegalStateException("not added to a pipeline yet");
    }

    /* access modifiers changed from: protected */
    public void decode(final ChannelHandlerContext channelHandlerContext, I i, List<Object> list) throws Exception {
        boolean z;
        ByteBufHolder byteBufHolder;
        if (isStartMessage(i)) {
            this.aggregating = true;
            this.handlingOversizedMessage = false;
            O o = this.currentMessage;
            if (o == null) {
                Object newContinueResponse = newContinueResponse(i, this.maxContentLength, channelHandlerContext.pipeline());
                if (newContinueResponse != null) {
                    ChannelFutureListener channelFutureListener = this.continueResponseWriteListener;
                    if (channelFutureListener == null) {
                        channelFutureListener = new ChannelFutureListener() {
                            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                if (!channelFuture.isSuccess()) {
                                    channelHandlerContext.fireExceptionCaught(channelFuture.cause());
                                }
                            }
                        };
                        this.continueResponseWriteListener = channelFutureListener;
                    }
                    boolean closeAfterContinueResponse = closeAfterContinueResponse(newContinueResponse);
                    this.handlingOversizedMessage = ignoreContentAfterContinueResponse(newContinueResponse);
                    ChannelFuture addListener = channelHandlerContext.writeAndFlush(newContinueResponse).addListener(channelFutureListener);
                    if (closeAfterContinueResponse) {
                        this.handleIncompleteAggregateDuringClose = false;
                        addListener.addListener(ChannelFutureListener.CLOSE);
                        return;
                    } else if (this.handlingOversizedMessage) {
                        return;
                    }
                } else if (isContentLengthInvalid(i, this.maxContentLength)) {
                    invokeHandleOversizedMessage(channelHandlerContext, i);
                    return;
                }
                if (!(i instanceof DecoderResultProvider) || ((DecoderResultProvider) i).decoderResult().isSuccess()) {
                    CompositeByteBuf compositeBuffer = channelHandlerContext.alloc().compositeBuffer(this.maxCumulationBufferComponents);
                    if (i instanceof ByteBufHolder) {
                        appendPartialContent(compositeBuffer, ((ByteBufHolder) i).content());
                    }
                    this.currentMessage = beginAggregation(i, compositeBuffer);
                    return;
                }
                if (i instanceof ByteBufHolder) {
                    byteBufHolder = beginAggregation(i, ((ByteBufHolder) i).content().retain());
                } else {
                    byteBufHolder = beginAggregation(i, Unpooled.EMPTY_BUFFER);
                }
                finishAggregation0(byteBufHolder);
                list.add(byteBufHolder);
                return;
            }
            o.release();
            this.currentMessage = null;
            throw new MessageAggregationException();
        } else if (isContentMessage(i)) {
            O o2 = this.currentMessage;
            if (o2 != null) {
                CompositeByteBuf compositeByteBuf = (CompositeByteBuf) o2.content();
                ByteBufHolder byteBufHolder2 = (ByteBufHolder) i;
                if (compositeByteBuf.readableBytes() > this.maxContentLength - byteBufHolder2.content().readableBytes()) {
                    invokeHandleOversizedMessage(channelHandlerContext, this.currentMessage);
                    return;
                }
                appendPartialContent(compositeByteBuf, byteBufHolder2.content());
                aggregate(this.currentMessage, byteBufHolder2);
                if (byteBufHolder2 instanceof DecoderResultProvider) {
                    DecoderResult decoderResult = ((DecoderResultProvider) byteBufHolder2).decoderResult();
                    if (!decoderResult.isSuccess()) {
                        O o3 = this.currentMessage;
                        if (o3 instanceof DecoderResultProvider) {
                            ((DecoderResultProvider) o3).setDecoderResult(DecoderResult.failure(decoderResult.cause()));
                        }
                        finishAggregation0(this.currentMessage);
                        list.add(this.currentMessage);
                        this.currentMessage = null;
                    }
                    z = isLastContentMessage(byteBufHolder2);
                } else {
                    z = isLastContentMessage(byteBufHolder2);
                }
                if (!z) {
                    return;
                }
                finishAggregation0(this.currentMessage);
                list.add(this.currentMessage);
                this.currentMessage = null;
            }
        } else {
            throw new MessageAggregationException();
        }
    }

    private static void appendPartialContent(CompositeByteBuf compositeByteBuf, ByteBuf byteBuf) {
        if (byteBuf.isReadable()) {
            compositeByteBuf.addComponent(true, byteBuf.retain());
        }
    }

    private void finishAggregation0(O o) throws Exception {
        this.aggregating = false;
        finishAggregation(o);
    }

    private void invokeHandleOversizedMessage(ChannelHandlerContext channelHandlerContext, S s) throws Exception {
        this.handlingOversizedMessage = true;
        this.currentMessage = null;
        this.handleIncompleteAggregateDuringClose = false;
        try {
            handleOversizedMessage(channelHandlerContext, s);
        } finally {
            ReferenceCountUtil.release(s);
        }
    }

    /* access modifiers changed from: protected */
    public void handleOversizedMessage(ChannelHandlerContext channelHandlerContext, S s) throws Exception {
        channelHandlerContext.fireExceptionCaught(new TooLongFrameException("content length exceeded " + maxContentLength() + " bytes."));
    }

    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.currentMessage != null && !channelHandlerContext.channel().config().isAutoRead()) {
            channelHandlerContext.read();
        }
        channelHandlerContext.fireChannelReadComplete();
    }

    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.aggregating && this.handleIncompleteAggregateDuringClose) {
            channelHandlerContext.fireExceptionCaught(new PrematureChannelClosureException("Channel closed while still aggregating message"));
        }
        try {
            super.channelInactive(channelHandlerContext);
        } finally {
            releaseCurrentMessage();
        }
    }

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
    }

    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        try {
            super.handlerRemoved(channelHandlerContext);
        } finally {
            releaseCurrentMessage();
        }
    }

    private void releaseCurrentMessage() {
        O o = this.currentMessage;
        if (o != null) {
            o.release();
            this.currentMessage = null;
            this.handlingOversizedMessage = false;
            this.aggregating = false;
        }
    }
}
