package io.netty.bootstrap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.util.AttributeKey;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ServerBootstrap extends AbstractBootstrap<ServerBootstrap, ServerChannel> {
    /* access modifiers changed from: private */
    public static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ServerBootstrap.class);
    private final Map<AttributeKey<?>, Object> childAttrs;
    private volatile EventLoopGroup childGroup;
    private volatile ChannelHandler childHandler;
    private final Map<ChannelOption<?>, Object> childOptions;
    /* access modifiers changed from: private */
    public final ServerBootstrapConfig config;

    public ServerBootstrap() {
        this.childOptions = new LinkedHashMap();
        this.childAttrs = new ConcurrentHashMap();
        this.config = new ServerBootstrapConfig(this);
    }

    private ServerBootstrap(ServerBootstrap serverBootstrap) {
        super(serverBootstrap);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.childOptions = linkedHashMap;
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        this.childAttrs = concurrentHashMap;
        this.config = new ServerBootstrapConfig(this);
        this.childGroup = serverBootstrap.childGroup;
        this.childHandler = serverBootstrap.childHandler;
        synchronized (serverBootstrap.childOptions) {
            linkedHashMap.putAll(serverBootstrap.childOptions);
        }
        concurrentHashMap.putAll(serverBootstrap.childAttrs);
    }

    public ServerBootstrap group(EventLoopGroup eventLoopGroup) {
        return group(eventLoopGroup, eventLoopGroup);
    }

    public ServerBootstrap group(EventLoopGroup eventLoopGroup, EventLoopGroup eventLoopGroup2) {
        super.group(eventLoopGroup);
        if (this.childGroup == null) {
            this.childGroup = (EventLoopGroup) ObjectUtil.checkNotNull(eventLoopGroup2, "childGroup");
            return this;
        }
        throw new IllegalStateException("childGroup set already");
    }

    public <T> ServerBootstrap childOption(ChannelOption<T> channelOption, T t) {
        ObjectUtil.checkNotNull(channelOption, "childOption");
        synchronized (this.childOptions) {
            if (t == null) {
                this.childOptions.remove(channelOption);
            } else {
                this.childOptions.put(channelOption, t);
            }
        }
        return this;
    }

    public <T> ServerBootstrap childAttr(AttributeKey<T> attributeKey, T t) {
        ObjectUtil.checkNotNull(attributeKey, "childKey");
        if (t == null) {
            this.childAttrs.remove(attributeKey);
        } else {
            this.childAttrs.put(attributeKey, t);
        }
        return this;
    }

    public ServerBootstrap childHandler(ChannelHandler channelHandler) {
        this.childHandler = (ChannelHandler) ObjectUtil.checkNotNull(channelHandler, "childHandler");
        return this;
    }

    /* access modifiers changed from: package-private */
    public void init(Channel channel) {
        setChannelOptions(channel, newOptionsArray(), logger);
        setAttributes(channel, newAttributesArray());
        ChannelPipeline pipeline = channel.pipeline();
        final EventLoopGroup eventLoopGroup = this.childGroup;
        final ChannelHandler channelHandler = this.childHandler;
        final Map.Entry[] newOptionsArray = newOptionsArray(this.childOptions);
        final Map.Entry[] newAttributesArray = newAttributesArray(this.childAttrs);
        Collection<ChannelInitializerExtension> initializerExtensions = getInitializerExtensions();
        final Collection<ChannelInitializerExtension> collection = initializerExtensions;
        pipeline.addLast(new ChannelInitializer<Channel>() {
            public void initChannel(final Channel channel) {
                final ChannelPipeline pipeline = channel.pipeline();
                ChannelHandler handler = ServerBootstrap.this.config.handler();
                if (handler != null) {
                    pipeline.addLast(handler);
                }
                channel.eventLoop().execute(new Runnable() {
                    public void run() {
                        pipeline.addLast(new ServerBootstrapAcceptor(channel, eventLoopGroup, channelHandler, newOptionsArray, newAttributesArray, collection));
                    }
                });
            }
        });
        if (!initializerExtensions.isEmpty() && (channel instanceof ServerChannel)) {
            ServerChannel serverChannel = (ServerChannel) channel;
            for (ChannelInitializerExtension postInitializeServerListenerChannel : initializerExtensions) {
                try {
                    postInitializeServerListenerChannel.postInitializeServerListenerChannel(serverChannel);
                } catch (Exception e) {
                    logger.warn("Exception thrown from postInitializeServerListenerChannel", (Throwable) e);
                }
            }
        }
    }

    public ServerBootstrap validate() {
        super.validate();
        if (this.childHandler != null) {
            if (this.childGroup == null) {
                logger.warn("childGroup is not set. Using parentGroup instead.");
                this.childGroup = this.config.group();
            }
            return this;
        }
        throw new IllegalStateException("childHandler not set");
    }

    private static class ServerBootstrapAcceptor extends ChannelInboundHandlerAdapter {
        private final Map.Entry<AttributeKey<?>, Object>[] childAttrs;
        private final EventLoopGroup childGroup;
        private final ChannelHandler childHandler;
        private final Map.Entry<ChannelOption<?>, Object>[] childOptions;
        private final Runnable enableAutoReadTask;
        private final Collection<ChannelInitializerExtension> extensions;

        ServerBootstrapAcceptor(final Channel channel, EventLoopGroup eventLoopGroup, ChannelHandler channelHandler, Map.Entry<ChannelOption<?>, Object>[] entryArr, Map.Entry<AttributeKey<?>, Object>[] entryArr2, Collection<ChannelInitializerExtension> collection) {
            this.childGroup = eventLoopGroup;
            this.childHandler = channelHandler;
            this.childOptions = entryArr;
            this.childAttrs = entryArr2;
            this.extensions = collection;
            this.enableAutoReadTask = new Runnable() {
                public void run() {
                    channel.config().setAutoRead(true);
                }
            };
        }

        public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) {
            final Channel channel = (Channel) obj;
            channel.pipeline().addLast(this.childHandler);
            AbstractBootstrap.setChannelOptions(channel, this.childOptions, ServerBootstrap.logger);
            AbstractBootstrap.setAttributes(channel, this.childAttrs);
            if (!this.extensions.isEmpty()) {
                for (ChannelInitializerExtension postInitializeServerChildChannel : this.extensions) {
                    try {
                        postInitializeServerChildChannel.postInitializeServerChildChannel(channel);
                    } catch (Exception e) {
                        ServerBootstrap.logger.warn("Exception thrown from postInitializeServerChildChannel", (Throwable) e);
                    }
                }
            }
            try {
                this.childGroup.register(channel).addListener(new ChannelFutureListener() {
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (!channelFuture.isSuccess()) {
                            ServerBootstrapAcceptor.forceClose(channel, channelFuture.cause());
                        }
                    }
                });
            } catch (Throwable th) {
                forceClose(channel, th);
            }
        }

        /* access modifiers changed from: private */
        public static void forceClose(Channel channel, Throwable th) {
            channel.unsafe().closeForcibly();
            ServerBootstrap.logger.warn("Failed to register an accepted channel: {}", channel, th);
        }

        public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
            ChannelConfig config = channelHandlerContext.channel().config();
            if (config.isAutoRead()) {
                config.setAutoRead(false);
                channelHandlerContext.channel().eventLoop().schedule(this.enableAutoReadTask, 1, TimeUnit.SECONDS);
            }
            channelHandlerContext.fireExceptionCaught(th);
        }
    }

    public ServerBootstrap clone() {
        return new ServerBootstrap(this);
    }

    @Deprecated
    public EventLoopGroup childGroup() {
        return this.childGroup;
    }

    /* access modifiers changed from: package-private */
    public final ChannelHandler childHandler() {
        return this.childHandler;
    }

    /* access modifiers changed from: package-private */
    public final Map<ChannelOption<?>, Object> childOptions() {
        Map<ChannelOption<?>, Object> copiedMap;
        synchronized (this.childOptions) {
            copiedMap = copiedMap(this.childOptions);
        }
        return copiedMap;
    }

    /* access modifiers changed from: package-private */
    public final Map<AttributeKey<?>, Object> childAttrs() {
        return copiedMap(this.childAttrs);
    }

    public final ServerBootstrapConfig config() {
        return this.config;
    }
}
