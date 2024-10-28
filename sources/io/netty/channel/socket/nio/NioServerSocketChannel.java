package io.netty.channel.socket.nio;

import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.nio.AbstractNioMessageChannel;
import io.netty.channel.socket.DefaultServerSocketChannelConfig;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.ServerSocketChannelConfig;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SocketUtils;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.List;
import java.util.Map;

public class NioServerSocketChannel extends AbstractNioMessageChannel implements ServerSocketChannel {
    private static final SelectorProvider DEFAULT_SELECTOR_PROVIDER = SelectorProvider.provider();
    private static final ChannelMetadata METADATA = new ChannelMetadata(false, 16);
    private static final Method OPEN_SERVER_SOCKET_CHANNEL_WITH_FAMILY = SelectorProviderUtil.findOpenMethod("openServerSocketChannel");
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) NioServerSocketChannel.class);
    private final ServerSocketChannelConfig config;

    public InetSocketAddress remoteAddress() {
        return null;
    }

    /* access modifiers changed from: protected */
    public SocketAddress remoteAddress0() {
        return null;
    }

    private static java.nio.channels.ServerSocketChannel newChannel(SelectorProvider selectorProvider, InternetProtocolFamily internetProtocolFamily) {
        try {
            java.nio.channels.ServerSocketChannel serverSocketChannel = (java.nio.channels.ServerSocketChannel) SelectorProviderUtil.newChannel(OPEN_SERVER_SOCKET_CHANNEL_WITH_FAMILY, selectorProvider, internetProtocolFamily);
            return serverSocketChannel == null ? selectorProvider.openServerSocketChannel() : serverSocketChannel;
        } catch (IOException e) {
            throw new ChannelException("Failed to open a socket.", e);
        }
    }

    public NioServerSocketChannel() {
        this(DEFAULT_SELECTOR_PROVIDER);
    }

    public NioServerSocketChannel(SelectorProvider selectorProvider) {
        this(selectorProvider, (InternetProtocolFamily) null);
    }

    public NioServerSocketChannel(SelectorProvider selectorProvider, InternetProtocolFamily internetProtocolFamily) {
        this(newChannel(selectorProvider, internetProtocolFamily));
    }

    public NioServerSocketChannel(java.nio.channels.ServerSocketChannel serverSocketChannel) {
        super((Channel) null, serverSocketChannel, 16);
        this.config = new NioServerSocketChannelConfig(this, javaChannel().socket());
    }

    public InetSocketAddress localAddress() {
        return (InetSocketAddress) super.localAddress();
    }

    public ChannelMetadata metadata() {
        return METADATA;
    }

    public ServerSocketChannelConfig config() {
        return this.config;
    }

    public boolean isActive() {
        return isOpen() && javaChannel().socket().isBound();
    }

    /* access modifiers changed from: protected */
    public java.nio.channels.ServerSocketChannel javaChannel() {
        return (java.nio.channels.ServerSocketChannel) super.javaChannel();
    }

    /* access modifiers changed from: protected */
    public SocketAddress localAddress0() {
        return SocketUtils.localSocketAddress(javaChannel().socket());
    }

    /* access modifiers changed from: protected */
    public void doBind(SocketAddress socketAddress) throws Exception {
        if (PlatformDependent.javaVersion() >= 7) {
            java.nio.channels.ServerSocketChannel unused = javaChannel().bind(socketAddress, this.config.getBacklog());
        } else {
            javaChannel().socket().bind(socketAddress, this.config.getBacklog());
        }
    }

    /* access modifiers changed from: protected */
    public void doClose() throws Exception {
        javaChannel().close();
    }

    /* access modifiers changed from: protected */
    public int doReadMessages(List<Object> list) throws Exception {
        SocketChannel accept = SocketUtils.accept(javaChannel());
        if (accept == null) {
            return 0;
        }
        try {
            list.add(new NioSocketChannel((Channel) this, accept));
            return 1;
        } catch (Throwable th) {
            logger.warn("Failed to close a socket.", th);
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    public boolean doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public void doFinishConnect() throws Exception {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public void doDisconnect() throws Exception {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public boolean doWriteMessage(Object obj, ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public final Object filterOutboundMessage(Object obj) throws Exception {
        throw new UnsupportedOperationException();
    }

    private final class NioServerSocketChannelConfig extends DefaultServerSocketChannelConfig {
        private NioServerSocketChannelConfig(NioServerSocketChannel nioServerSocketChannel, ServerSocket serverSocket) {
            super(nioServerSocketChannel, serverSocket);
        }

        /* access modifiers changed from: protected */
        public void autoReadCleared() {
            NioServerSocketChannel.this.clearReadPending();
        }

        public <T> boolean setOption(ChannelOption<T> channelOption, T t) {
            if (PlatformDependent.javaVersion() < 7 || !(channelOption instanceof NioChannelOption)) {
                return super.setOption(channelOption, t);
            }
            return NioChannelOption.setOption(jdkChannel(), (NioChannelOption) channelOption, t);
        }

        public <T> T getOption(ChannelOption<T> channelOption) {
            if (PlatformDependent.javaVersion() < 7 || !(channelOption instanceof NioChannelOption)) {
                return super.getOption(channelOption);
            }
            return NioChannelOption.getOption(jdkChannel(), (NioChannelOption) channelOption);
        }

        public Map<ChannelOption<?>, Object> getOptions() {
            if (PlatformDependent.javaVersion() >= 7) {
                return getOptions(super.getOptions(), NioChannelOption.getOptions(jdkChannel()));
            }
            return super.getOptions();
        }

        private java.nio.channels.ServerSocketChannel jdkChannel() {
            return ((NioServerSocketChannel) this.channel).javaChannel();
        }
    }

    /* access modifiers changed from: protected */
    public boolean closeOnReadError(Throwable th) {
        return super.closeOnReadError(th);
    }
}
