package io.netty.handler.codec.http2;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import androidx.tvprovider.media.tv.TvContractCompat;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import j$.util.concurrent.ConcurrentHashMap;
import java.nio.channels.ClosedChannelException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;

public final class Http2StreamChannelBootstrap {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Map.Entry<AttributeKey<?>, Object>[] EMPTY_ATTRIBUTE_ARRAY = new Map.Entry[0];
    private static final Map.Entry<ChannelOption<?>, Object>[] EMPTY_OPTION_ARRAY = new Map.Entry[0];
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) Http2StreamChannelBootstrap.class);
    private final Map<AttributeKey<?>, Object> attrs = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public final Channel channel;
    private volatile ChannelHandler handler;
    private volatile ChannelHandlerContext multiplexCtx;
    private final Map<ChannelOption<?>, Object> options = new LinkedHashMap();

    public Http2StreamChannelBootstrap(Channel channel2) {
        this.channel = (Channel) ObjectUtil.checkNotNull(channel2, TvContractCompat.PARAM_CHANNEL);
    }

    public <T> Http2StreamChannelBootstrap option(ChannelOption<T> channelOption, T t) {
        ObjectUtil.checkNotNull(channelOption, DuplicationWarningDialog.OPTION_KEY);
        synchronized (this.options) {
            if (t == null) {
                this.options.remove(channelOption);
            } else {
                this.options.put(channelOption, t);
            }
        }
        return this;
    }

    public <T> Http2StreamChannelBootstrap attr(AttributeKey<T> attributeKey, T t) {
        ObjectUtil.checkNotNull(attributeKey, LeanbackPreferenceDialogFragment.ARG_KEY);
        if (t == null) {
            this.attrs.remove(attributeKey);
        } else {
            this.attrs.put(attributeKey, t);
        }
        return this;
    }

    public Http2StreamChannelBootstrap handler(ChannelHandler channelHandler) {
        this.handler = (ChannelHandler) ObjectUtil.checkNotNull(channelHandler, "handler");
        return this;
    }

    public Future<Http2StreamChannel> open() {
        return open(this.channel.eventLoop().newPromise());
    }

    public Future<Http2StreamChannel> open(final Promise<Http2StreamChannel> promise) {
        try {
            final ChannelHandlerContext findCtx = findCtx();
            EventExecutor executor = findCtx.executor();
            if (executor.inEventLoop()) {
                open0(findCtx, promise);
            } else {
                executor.execute(new Runnable() {
                    public void run() {
                        if (Http2StreamChannelBootstrap.this.channel.isActive()) {
                            Http2StreamChannelBootstrap.this.open0(findCtx, promise);
                        } else {
                            promise.setFailure(new ClosedChannelException());
                        }
                    }
                });
            }
        } catch (Throwable th) {
            promise.setFailure(th);
        }
        return promise;
    }

    private ChannelHandlerContext findCtx() throws ClosedChannelException {
        ChannelHandlerContext channelHandlerContext = this.multiplexCtx;
        if (channelHandlerContext != null && !channelHandlerContext.isRemoved()) {
            return channelHandlerContext;
        }
        ChannelPipeline pipeline = this.channel.pipeline();
        ChannelHandlerContext context = pipeline.context((Class<? extends ChannelHandler>) Http2MultiplexCodec.class);
        if (context == null) {
            context = pipeline.context((Class<? extends ChannelHandler>) Http2MultiplexHandler.class);
        }
        if (context != null) {
            this.multiplexCtx = context;
            return context;
        } else if (this.channel.isActive()) {
            throw new IllegalStateException(StringUtil.simpleClassName((Class<?>) Http2MultiplexCodec.class) + " or " + StringUtil.simpleClassName((Class<?>) Http2MultiplexHandler.class) + " must be in the ChannelPipeline of Channel " + this.channel);
        } else {
            throw new ClosedChannelException();
        }
    }

    @Deprecated
    public void open0(ChannelHandlerContext channelHandlerContext, final Promise<Http2StreamChannel> promise) {
        final Http2StreamChannel http2StreamChannel;
        if (promise.setUncancellable()) {
            try {
                if (channelHandlerContext.handler() instanceof Http2MultiplexCodec) {
                    http2StreamChannel = ((Http2MultiplexCodec) channelHandlerContext.handler()).newOutboundStream();
                } else {
                    http2StreamChannel = ((Http2MultiplexHandler) channelHandlerContext.handler()).newOutboundStream();
                }
                try {
                    init(http2StreamChannel);
                    channelHandlerContext.channel().eventLoop().register((Channel) http2StreamChannel).addListener(new ChannelFutureListener() {
                        public void operationComplete(ChannelFuture channelFuture) {
                            if (channelFuture.isSuccess()) {
                                promise.setSuccess(http2StreamChannel);
                            } else if (channelFuture.isCancelled()) {
                                promise.cancel(false);
                            } else {
                                if (http2StreamChannel.isRegistered()) {
                                    http2StreamChannel.close();
                                } else {
                                    http2StreamChannel.unsafe().closeForcibly();
                                }
                                promise.setFailure(channelFuture.cause());
                            }
                        }
                    });
                } catch (Exception e) {
                    http2StreamChannel.unsafe().closeForcibly();
                    promise.setFailure(e);
                }
            } catch (Exception e2) {
                promise.setFailure(e2);
            }
        }
    }

    private void init(Channel channel2) {
        Map.Entry[] entryArr;
        ChannelPipeline pipeline = channel2.pipeline();
        ChannelHandler channelHandler = this.handler;
        if (channelHandler != null) {
            pipeline.addLast(channelHandler);
        }
        synchronized (this.options) {
            entryArr = (Map.Entry[]) this.options.entrySet().toArray(EMPTY_OPTION_ARRAY);
        }
        setChannelOptions(channel2, entryArr);
        setAttributes(channel2, (Map.Entry[]) this.attrs.entrySet().toArray(EMPTY_ATTRIBUTE_ARRAY));
    }

    private static void setChannelOptions(Channel channel2, Map.Entry<ChannelOption<?>, Object>[] entryArr) {
        for (Map.Entry<ChannelOption<?>, Object> entry : entryArr) {
            setChannelOption(channel2, entry.getKey(), entry.getValue());
        }
    }

    private static void setChannelOption(Channel channel2, ChannelOption<?> channelOption, Object obj) {
        try {
            if (!channel2.config().setOption(channelOption, obj)) {
                logger.warn("Unknown channel option '{}' for channel '{}'", channelOption, channel2);
            }
        } catch (Throwable th) {
            logger.warn("Failed to set channel option '{}' with value '{}' for channel '{}'", channelOption, obj, channel2, th);
        }
    }

    private static void setAttributes(Channel channel2, Map.Entry<AttributeKey<?>, Object>[] entryArr) {
        for (Map.Entry<AttributeKey<?>, Object> entry : entryArr) {
            channel2.attr(entry.getKey()).set(entry.getValue());
        }
    }
}
