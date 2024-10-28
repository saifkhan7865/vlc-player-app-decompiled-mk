package io.netty.channel.epoll;

import io.netty.channel.Channel;
import io.netty.channel.unix.DomainSocketAddress;
import io.netty.channel.unix.FileDescriptor;
import io.netty.channel.unix.ServerDomainSocketChannel;
import io.netty.channel.unix.Socket;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.File;
import java.net.SocketAddress;

public final class EpollServerDomainSocketChannel extends AbstractEpollServerChannel implements ServerDomainSocketChannel {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) EpollServerDomainSocketChannel.class);
    private final EpollServerChannelConfig config = new EpollServerChannelConfig(this);
    private volatile DomainSocketAddress local;

    public EpollServerDomainSocketChannel() {
        super(LinuxSocket.newSocketDomain(), false);
    }

    public EpollServerDomainSocketChannel(int i) {
        super(i);
    }

    EpollServerDomainSocketChannel(LinuxSocket linuxSocket) {
        super(linuxSocket);
    }

    EpollServerDomainSocketChannel(LinuxSocket linuxSocket, boolean z) {
        super(linuxSocket, z);
    }

    /* access modifiers changed from: protected */
    public Channel newChildChannel(int i, byte[] bArr, int i2, int i3) throws Exception {
        return new EpollDomainSocketChannel((Channel) this, (FileDescriptor) new Socket(i));
    }

    /* access modifiers changed from: protected */
    public DomainSocketAddress localAddress0() {
        return this.local;
    }

    /* access modifiers changed from: protected */
    public void doBind(SocketAddress socketAddress) throws Exception {
        this.socket.bind(socketAddress);
        this.socket.listen(this.config.getBacklog());
        this.local = (DomainSocketAddress) socketAddress;
        this.active = true;
    }

    /* access modifiers changed from: protected */
    public void doClose() throws Exception {
        try {
            super.doClose();
        } finally {
            DomainSocketAddress domainSocketAddress = this.local;
            if (domainSocketAddress != null && !new File(domainSocketAddress.path()).delete()) {
                InternalLogger internalLogger = logger;
                if (internalLogger.isDebugEnabled()) {
                    internalLogger.debug("Failed to delete a domain socket file: {}", (Object) domainSocketAddress.path());
                }
            }
        }
    }

    public EpollServerChannelConfig config() {
        return this.config;
    }

    public DomainSocketAddress remoteAddress() {
        return (DomainSocketAddress) super.remoteAddress();
    }

    public DomainSocketAddress localAddress() {
        return (DomainSocketAddress) super.localAddress();
    }
}
