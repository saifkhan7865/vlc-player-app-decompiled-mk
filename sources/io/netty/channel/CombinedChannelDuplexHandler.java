package io.netty.channel;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.ThrowableUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.SocketAddress;

public class CombinedChannelDuplexHandler<I extends ChannelInboundHandler, O extends ChannelOutboundHandler> extends ChannelDuplexHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    /* access modifiers changed from: private */
    public static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) CombinedChannelDuplexHandler.class);
    private volatile boolean handlerAdded;
    private DelegatingChannelHandlerContext inboundCtx;
    private I inboundHandler;
    /* access modifiers changed from: private */
    public DelegatingChannelHandlerContext outboundCtx;
    /* access modifiers changed from: private */
    public O outboundHandler;

    protected CombinedChannelDuplexHandler() {
        ensureNotSharable();
    }

    public CombinedChannelDuplexHandler(I i, O o) {
        ensureNotSharable();
        init(i, o);
    }

    /* access modifiers changed from: protected */
    public final void init(I i, O o) {
        validate(i, o);
        this.inboundHandler = i;
        this.outboundHandler = o;
    }

    private void validate(I i, O o) {
        if (this.inboundHandler == null) {
            ObjectUtil.checkNotNull(i, "inboundHandler");
            ObjectUtil.checkNotNull(o, "outboundHandler");
            if (i instanceof ChannelOutboundHandler) {
                Class<ChannelOutboundHandler> cls = ChannelOutboundHandler.class;
                throw new IllegalArgumentException("inboundHandler must not implement ChannelOutboundHandler to get combined.");
            } else if (o instanceof ChannelInboundHandler) {
                Class<ChannelInboundHandler> cls2 = ChannelInboundHandler.class;
                throw new IllegalArgumentException("outboundHandler must not implement ChannelInboundHandler to get combined.");
            }
        } else {
            throw new IllegalStateException("init() can not be invoked if CombinedChannelDuplexHandler was constructed with non-default constructor.");
        }
    }

    /* access modifiers changed from: protected */
    public final I inboundHandler() {
        return this.inboundHandler;
    }

    /* access modifiers changed from: protected */
    public final O outboundHandler() {
        return this.outboundHandler;
    }

    private void checkAdded() {
        if (!this.handlerAdded) {
            throw new IllegalStateException("handler not added to pipeline yet");
        }
    }

    public final void removeInboundHandler() {
        checkAdded();
        this.inboundCtx.remove();
    }

    public final void removeOutboundHandler() {
        checkAdded();
        this.outboundCtx.remove();
    }

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.inboundHandler != null) {
            this.outboundCtx = new DelegatingChannelHandlerContext(channelHandlerContext, this.outboundHandler);
            this.inboundCtx = new DelegatingChannelHandlerContext(channelHandlerContext, this.inboundHandler) {
                public ChannelHandlerContext fireExceptionCaught(Throwable th) {
                    if (!CombinedChannelDuplexHandler.this.outboundCtx.removed) {
                        try {
                            CombinedChannelDuplexHandler.this.outboundHandler.exceptionCaught(CombinedChannelDuplexHandler.this.outboundCtx, th);
                        } catch (Throwable th2) {
                            if (CombinedChannelDuplexHandler.logger.isDebugEnabled()) {
                                CombinedChannelDuplexHandler.logger.debug("An exception {}was thrown by a user handler's exceptionCaught() method while handling the following exception:", ThrowableUtil.stackTraceToString(th2), th);
                            } else if (CombinedChannelDuplexHandler.logger.isWarnEnabled()) {
                                CombinedChannelDuplexHandler.logger.warn("An exception '{}' [enable DEBUG level for full stacktrace] was thrown by a user handler's exceptionCaught() method while handling the following exception:", th2, th);
                            }
                        }
                    } else {
                        super.fireExceptionCaught(th);
                    }
                    return this;
                }
            };
            this.handlerAdded = true;
            try {
                this.inboundHandler.handlerAdded(this.inboundCtx);
            } finally {
                this.outboundHandler.handlerAdded(this.outboundCtx);
            }
        } else {
            Class<ChannelPipeline> cls = ChannelPipeline.class;
            throw new IllegalStateException("init() must be invoked before being added to a ChannelPipeline if CombinedChannelDuplexHandler was constructed with the default constructor.");
        }
    }

    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        try {
            this.inboundCtx.remove();
        } finally {
            this.outboundCtx.remove();
        }
    }

    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.channelRegistered(this.inboundCtx);
        } else {
            this.inboundCtx.fireChannelRegistered();
        }
    }

    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.channelUnregistered(this.inboundCtx);
        } else {
            this.inboundCtx.fireChannelUnregistered();
        }
    }

    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.channelActive(this.inboundCtx);
        } else {
            this.inboundCtx.fireChannelActive();
        }
    }

    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.channelInactive(this.inboundCtx);
        } else {
            this.inboundCtx.fireChannelInactive();
        }
    }

    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.exceptionCaught(this.inboundCtx, th);
        } else {
            this.inboundCtx.fireExceptionCaught(th);
        }
    }

    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.userEventTriggered(this.inboundCtx, obj);
        } else {
            this.inboundCtx.fireUserEventTriggered(obj);
        }
    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.channelRead(this.inboundCtx, obj);
        } else {
            this.inboundCtx.fireChannelRead(obj);
        }
    }

    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.channelReadComplete(this.inboundCtx);
        } else {
            this.inboundCtx.fireChannelReadComplete();
        }
    }

    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.inboundCtx.removed) {
            this.inboundHandler.channelWritabilityChanged(this.inboundCtx);
        } else {
            this.inboundCtx.fireChannelWritabilityChanged();
        }
    }

    public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {
        if (!this.outboundCtx.removed) {
            this.outboundHandler.bind(this.outboundCtx, socketAddress, channelPromise);
        } else {
            this.outboundCtx.bind(socketAddress, channelPromise);
        }
    }

    public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) throws Exception {
        if (!this.outboundCtx.removed) {
            this.outboundHandler.connect(this.outboundCtx, socketAddress, socketAddress2, channelPromise);
        } else {
            this.outboundCtx.connect(socketAddress, socketAddress2, channelPromise);
        }
    }

    public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        if (!this.outboundCtx.removed) {
            this.outboundHandler.disconnect(this.outboundCtx, channelPromise);
        } else {
            this.outboundCtx.disconnect(channelPromise);
        }
    }

    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        if (!this.outboundCtx.removed) {
            this.outboundHandler.close(this.outboundCtx, channelPromise);
        } else {
            this.outboundCtx.close(channelPromise);
        }
    }

    public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        if (!this.outboundCtx.removed) {
            this.outboundHandler.deregister(this.outboundCtx, channelPromise);
        } else {
            this.outboundCtx.deregister(channelPromise);
        }
    }

    public void read(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.outboundCtx.removed) {
            this.outboundHandler.read(this.outboundCtx);
        } else {
            this.outboundCtx.read();
        }
    }

    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        if (!this.outboundCtx.removed) {
            this.outboundHandler.write(this.outboundCtx, obj, channelPromise);
        } else {
            this.outboundCtx.write(obj, channelPromise);
        }
    }

    public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.outboundCtx.removed) {
            this.outboundHandler.flush(this.outboundCtx);
        } else {
            this.outboundCtx.flush();
        }
    }

    private static class DelegatingChannelHandlerContext implements ChannelHandlerContext {
        /* access modifiers changed from: private */
        public final ChannelHandlerContext ctx;
        private final ChannelHandler handler;
        boolean removed;

        DelegatingChannelHandlerContext(ChannelHandlerContext channelHandlerContext, ChannelHandler channelHandler) {
            this.ctx = channelHandlerContext;
            this.handler = channelHandler;
        }

        public Channel channel() {
            return this.ctx.channel();
        }

        public EventExecutor executor() {
            return this.ctx.executor();
        }

        public String name() {
            return this.ctx.name();
        }

        public ChannelHandler handler() {
            return this.ctx.handler();
        }

        public boolean isRemoved() {
            return this.removed || this.ctx.isRemoved();
        }

        public ChannelHandlerContext fireChannelRegistered() {
            this.ctx.fireChannelRegistered();
            return this;
        }

        public ChannelHandlerContext fireChannelUnregistered() {
            this.ctx.fireChannelUnregistered();
            return this;
        }

        public ChannelHandlerContext fireChannelActive() {
            this.ctx.fireChannelActive();
            return this;
        }

        public ChannelHandlerContext fireChannelInactive() {
            this.ctx.fireChannelInactive();
            return this;
        }

        public ChannelHandlerContext fireExceptionCaught(Throwable th) {
            this.ctx.fireExceptionCaught(th);
            return this;
        }

        public ChannelHandlerContext fireUserEventTriggered(Object obj) {
            this.ctx.fireUserEventTriggered(obj);
            return this;
        }

        public ChannelHandlerContext fireChannelRead(Object obj) {
            this.ctx.fireChannelRead(obj);
            return this;
        }

        public ChannelHandlerContext fireChannelReadComplete() {
            this.ctx.fireChannelReadComplete();
            return this;
        }

        public ChannelHandlerContext fireChannelWritabilityChanged() {
            this.ctx.fireChannelWritabilityChanged();
            return this;
        }

        public ChannelFuture bind(SocketAddress socketAddress) {
            return this.ctx.bind(socketAddress);
        }

        public ChannelFuture connect(SocketAddress socketAddress) {
            return this.ctx.connect(socketAddress);
        }

        public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2) {
            return this.ctx.connect(socketAddress, socketAddress2);
        }

        public ChannelFuture disconnect() {
            return this.ctx.disconnect();
        }

        public ChannelFuture close() {
            return this.ctx.close();
        }

        public ChannelFuture deregister() {
            return this.ctx.deregister();
        }

        public ChannelFuture bind(SocketAddress socketAddress, ChannelPromise channelPromise) {
            return this.ctx.bind(socketAddress, channelPromise);
        }

        public ChannelFuture connect(SocketAddress socketAddress, ChannelPromise channelPromise) {
            return this.ctx.connect(socketAddress, channelPromise);
        }

        public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
            return this.ctx.connect(socketAddress, socketAddress2, channelPromise);
        }

        public ChannelFuture disconnect(ChannelPromise channelPromise) {
            return this.ctx.disconnect(channelPromise);
        }

        public ChannelFuture close(ChannelPromise channelPromise) {
            return this.ctx.close(channelPromise);
        }

        public ChannelFuture deregister(ChannelPromise channelPromise) {
            return this.ctx.deregister(channelPromise);
        }

        public ChannelHandlerContext read() {
            this.ctx.read();
            return this;
        }

        public ChannelFuture write(Object obj) {
            return this.ctx.write(obj);
        }

        public ChannelFuture write(Object obj, ChannelPromise channelPromise) {
            return this.ctx.write(obj, channelPromise);
        }

        public ChannelHandlerContext flush() {
            this.ctx.flush();
            return this;
        }

        public ChannelFuture writeAndFlush(Object obj, ChannelPromise channelPromise) {
            return this.ctx.writeAndFlush(obj, channelPromise);
        }

        public ChannelFuture writeAndFlush(Object obj) {
            return this.ctx.writeAndFlush(obj);
        }

        public ChannelPipeline pipeline() {
            return this.ctx.pipeline();
        }

        public ByteBufAllocator alloc() {
            return this.ctx.alloc();
        }

        public ChannelPromise newPromise() {
            return this.ctx.newPromise();
        }

        public ChannelProgressivePromise newProgressivePromise() {
            return this.ctx.newProgressivePromise();
        }

        public ChannelFuture newSucceededFuture() {
            return this.ctx.newSucceededFuture();
        }

        public ChannelFuture newFailedFuture(Throwable th) {
            return this.ctx.newFailedFuture(th);
        }

        public ChannelPromise voidPromise() {
            return this.ctx.voidPromise();
        }

        public <T> Attribute<T> attr(AttributeKey<T> attributeKey) {
            return this.ctx.channel().attr(attributeKey);
        }

        public <T> boolean hasAttr(AttributeKey<T> attributeKey) {
            return this.ctx.channel().hasAttr(attributeKey);
        }

        /* access modifiers changed from: package-private */
        public final void remove() {
            EventExecutor executor = executor();
            if (executor.inEventLoop()) {
                remove0();
            } else {
                executor.execute(new Runnable() {
                    public void run() {
                        DelegatingChannelHandlerContext.this.remove0();
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public void remove0() {
            if (!this.removed) {
                this.removed = true;
                try {
                    this.handler.handlerRemoved(this);
                } catch (Throwable th) {
                    fireExceptionCaught((Throwable) new ChannelPipelineException(this.handler.getClass().getName() + ".handlerRemoved() has thrown an exception.", th));
                }
            }
        }
    }
}
