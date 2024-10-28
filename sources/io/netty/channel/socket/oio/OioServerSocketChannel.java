package io.netty.channel.socket.oio;

import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.oio.AbstractOioMessageChannel;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.SocketUtils;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.List;

@Deprecated
public class OioServerSocketChannel extends AbstractOioMessageChannel implements ServerSocketChannel {
    private static final ChannelMetadata METADATA = new ChannelMetadata(false, 1);
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) OioServerSocketChannel.class);
    private final OioServerSocketChannelConfig config;
    final ServerSocket socket;

    public InetSocketAddress remoteAddress() {
        return null;
    }

    /* access modifiers changed from: protected */
    public SocketAddress remoteAddress0() {
        return null;
    }

    private static ServerSocket newServerSocket() {
        try {
            return new ServerSocket();
        } catch (IOException e) {
            throw new ChannelException("failed to create a server socket", e);
        }
    }

    public OioServerSocketChannel() {
        this(newServerSocket());
    }

    public OioServerSocketChannel(ServerSocket serverSocket) {
        super((Channel) null);
        ObjectUtil.checkNotNull(serverSocket, "socket");
        try {
            serverSocket.setSoTimeout(1000);
            this.socket = serverSocket;
            this.config = new DefaultOioServerSocketChannelConfig(this, serverSocket);
        } catch (IOException e) {
            throw new ChannelException("Failed to set the server socket timeout.", e);
        } catch (Throwable th) {
            try {
                serverSocket.close();
            } catch (IOException e2) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Failed to close a partially initialized socket.", (Throwable) e2);
                }
            }
            throw th;
        }
    }

    public InetSocketAddress localAddress() {
        return (InetSocketAddress) super.localAddress();
    }

    public ChannelMetadata metadata() {
        return METADATA;
    }

    public OioServerSocketChannelConfig config() {
        return this.config;
    }

    public boolean isOpen() {
        return !this.socket.isClosed();
    }

    public boolean isActive() {
        return isOpen() && this.socket.isBound();
    }

    /* access modifiers changed from: protected */
    public SocketAddress localAddress0() {
        return SocketUtils.localSocketAddress(this.socket);
    }

    /* access modifiers changed from: protected */
    public void doBind(SocketAddress socketAddress) throws Exception {
        this.socket.bind(socketAddress, this.config.getBacklog());
    }

    /* access modifiers changed from: protected */
    public void doClose() throws Exception {
        this.socket.close();
    }

    /* access modifiers changed from: protected */
    public int doReadMessages(List<Object> list) throws Exception {
        if (this.socket.isClosed()) {
            return -1;
        }
        try {
            Socket accept = this.socket.accept();
            try {
                list.add(new OioSocketChannel(this, accept));
                return 1;
            } catch (Throwable th) {
                logger.warn("Failed to close a socket.", th);
                return 0;
            }
        } catch (SocketTimeoutException unused) {
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    public void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public Object filterOutboundMessage(Object obj) throws Exception {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public void doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public void doDisconnect() throws Exception {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void setReadPending(boolean z) {
        super.setReadPending(z);
    }

    /* access modifiers changed from: package-private */
    public final void clearReadPending0() {
        super.clearReadPending();
    }
}
