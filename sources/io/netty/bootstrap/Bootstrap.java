package io.netty.bootstrap;

import io.netty.bootstrap.AbstractBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoopGroup;
import io.netty.resolver.AddressResolver;
import io.netty.resolver.AddressResolverGroup;
import io.netty.resolver.DefaultAddressResolverGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collection;

public class Bootstrap extends AbstractBootstrap<Bootstrap, Channel> {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) Bootstrap.class);
    private final BootstrapConfig config = new BootstrapConfig(this);
    private volatile boolean disableResolver;
    private ExternalAddressResolver externalResolver;
    private volatile SocketAddress remoteAddress;

    public Bootstrap() {
    }

    private Bootstrap(Bootstrap bootstrap) {
        super(bootstrap);
        this.externalResolver = bootstrap.externalResolver;
        this.disableResolver = bootstrap.disableResolver;
        this.remoteAddress = bootstrap.remoteAddress;
    }

    public Bootstrap resolver(AddressResolverGroup<?> addressResolverGroup) {
        this.externalResolver = addressResolverGroup == null ? null : new ExternalAddressResolver(addressResolverGroup);
        this.disableResolver = false;
        return this;
    }

    public Bootstrap disableResolver() {
        this.externalResolver = null;
        this.disableResolver = true;
        return this;
    }

    public Bootstrap remoteAddress(SocketAddress socketAddress) {
        this.remoteAddress = socketAddress;
        return this;
    }

    public Bootstrap remoteAddress(String str, int i) {
        this.remoteAddress = InetSocketAddress.createUnresolved(str, i);
        return this;
    }

    public Bootstrap remoteAddress(InetAddress inetAddress, int i) {
        this.remoteAddress = new InetSocketAddress(inetAddress, i);
        return this;
    }

    public ChannelFuture connect() {
        validate();
        SocketAddress socketAddress = this.remoteAddress;
        if (socketAddress != null) {
            return doResolveAndConnect(socketAddress, this.config.localAddress());
        }
        throw new IllegalStateException("remoteAddress not set");
    }

    public ChannelFuture connect(String str, int i) {
        return connect(InetSocketAddress.createUnresolved(str, i));
    }

    public ChannelFuture connect(InetAddress inetAddress, int i) {
        return connect(new InetSocketAddress(inetAddress, i));
    }

    public ChannelFuture connect(SocketAddress socketAddress) {
        ObjectUtil.checkNotNull(socketAddress, "remoteAddress");
        validate();
        return doResolveAndConnect(socketAddress, this.config.localAddress());
    }

    public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2) {
        ObjectUtil.checkNotNull(socketAddress, "remoteAddress");
        validate();
        return doResolveAndConnect(socketAddress, socketAddress2);
    }

    private ChannelFuture doResolveAndConnect(SocketAddress socketAddress, SocketAddress socketAddress2) {
        ChannelFuture initAndRegister = initAndRegister();
        final Channel channel = initAndRegister.channel();
        if (!initAndRegister.isDone()) {
            AbstractBootstrap.PendingRegistrationPromise pendingRegistrationPromise = new AbstractBootstrap.PendingRegistrationPromise(channel);
            final AbstractBootstrap.PendingRegistrationPromise pendingRegistrationPromise2 = pendingRegistrationPromise;
            final SocketAddress socketAddress3 = socketAddress;
            final SocketAddress socketAddress4 = socketAddress2;
            initAndRegister.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    Throwable cause = channelFuture.cause();
                    if (cause != null) {
                        pendingRegistrationPromise2.setFailure(cause);
                        return;
                    }
                    pendingRegistrationPromise2.registered();
                    ChannelFuture unused = Bootstrap.this.doResolveAndConnect0(channel, socketAddress3, socketAddress4, pendingRegistrationPromise2);
                }
            });
            return pendingRegistrationPromise;
        } else if (!initAndRegister.isSuccess()) {
            return initAndRegister;
        } else {
            return doResolveAndConnect0(channel, socketAddress, socketAddress2, channel.newPromise());
        }
    }

    /* access modifiers changed from: private */
    public ChannelFuture doResolveAndConnect0(final Channel channel, SocketAddress socketAddress, final SocketAddress socketAddress2, final ChannelPromise channelPromise) {
        try {
            if (this.disableResolver) {
                doConnect(socketAddress, socketAddress2, channelPromise);
                return channelPromise;
            }
            AddressResolver<SocketAddress> resolver = ExternalAddressResolver.getOrDefault(this.externalResolver).getResolver(channel.eventLoop());
            if (resolver.isSupported(socketAddress)) {
                if (!resolver.isResolved(socketAddress)) {
                    Future<SocketAddress> resolve = resolver.resolve(socketAddress);
                    if (resolve.isDone()) {
                        Throwable cause = resolve.cause();
                        if (cause != null) {
                            channel.close();
                            channelPromise.setFailure(cause);
                        } else {
                            doConnect(resolve.getNow(), socketAddress2, channelPromise);
                        }
                        return channelPromise;
                    }
                    resolve.addListener(new FutureListener<SocketAddress>() {
                        public void operationComplete(Future<SocketAddress> future) throws Exception {
                            if (future.cause() != null) {
                                channel.close();
                                channelPromise.setFailure(future.cause());
                                return;
                            }
                            Bootstrap.doConnect(future.getNow(), socketAddress2, channelPromise);
                        }
                    });
                    return channelPromise;
                }
            }
            doConnect(socketAddress, socketAddress2, channelPromise);
            return channelPromise;
        } catch (Throwable th) {
            channelPromise.tryFailure(th);
        }
    }

    /* access modifiers changed from: private */
    public static void doConnect(final SocketAddress socketAddress, final SocketAddress socketAddress2, final ChannelPromise channelPromise) {
        final Channel channel = channelPromise.channel();
        channel.eventLoop().execute(new Runnable() {
            public void run() {
                SocketAddress socketAddress = socketAddress2;
                if (socketAddress == null) {
                    channel.connect(socketAddress, channelPromise);
                } else {
                    channel.connect(socketAddress, socketAddress, channelPromise);
                }
                channelPromise.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void init(Channel channel) {
        channel.pipeline().addLast(this.config.handler());
        setChannelOptions(channel, newOptionsArray(), logger);
        setAttributes(channel, newAttributesArray());
        Collection<ChannelInitializerExtension> initializerExtensions = getInitializerExtensions();
        if (!initializerExtensions.isEmpty()) {
            for (ChannelInitializerExtension postInitializeClientChannel : initializerExtensions) {
                try {
                    postInitializeClientChannel.postInitializeClientChannel(channel);
                } catch (Exception e) {
                    logger.warn("Exception thrown from postInitializeClientChannel", (Throwable) e);
                }
            }
        }
    }

    public Bootstrap validate() {
        super.validate();
        if (this.config.handler() != null) {
            return this;
        }
        throw new IllegalStateException("handler not set");
    }

    public Bootstrap clone() {
        return new Bootstrap(this);
    }

    public Bootstrap clone(EventLoopGroup eventLoopGroup) {
        Bootstrap bootstrap = new Bootstrap(this);
        bootstrap.group = eventLoopGroup;
        return bootstrap;
    }

    public final BootstrapConfig config() {
        return this.config;
    }

    /* access modifiers changed from: package-private */
    public final SocketAddress remoteAddress() {
        return this.remoteAddress;
    }

    /* access modifiers changed from: package-private */
    public final AddressResolverGroup<?> resolver() {
        if (this.disableResolver) {
            return null;
        }
        return ExternalAddressResolver.getOrDefault(this.externalResolver);
    }

    static final class ExternalAddressResolver {
        final AddressResolverGroup<SocketAddress> resolverGroup;

        ExternalAddressResolver(AddressResolverGroup<?> addressResolverGroup) {
            this.resolverGroup = addressResolverGroup;
        }

        static AddressResolverGroup<SocketAddress> getOrDefault(ExternalAddressResolver externalAddressResolver) {
            if (externalAddressResolver == null) {
                return DefaultAddressResolverGroup.INSTANCE;
            }
            return externalAddressResolver.resolverGroup;
        }
    }
}
