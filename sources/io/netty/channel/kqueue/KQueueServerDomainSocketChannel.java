package io.netty.channel.kqueue;

import io.netty.channel.Channel;
import io.netty.channel.unix.DomainSocketAddress;
import io.netty.channel.unix.ServerDomainSocketChannel;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.File;
import java.net.SocketAddress;

public final class KQueueServerDomainSocketChannel extends AbstractKQueueServerChannel implements ServerDomainSocketChannel {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) KQueueServerDomainSocketChannel.class);
    private final KQueueServerChannelConfig config;
    private volatile DomainSocketAddress local;

    public KQueueServerDomainSocketChannel() {
        super(BsdSocket.newSocketDomain(), false);
        this.config = new KQueueServerChannelConfig(this);
    }

    public KQueueServerDomainSocketChannel(int i) {
        this(new BsdSocket(i), false);
    }

    KQueueServerDomainSocketChannel(BsdSocket bsdSocket, boolean z) {
        super(bsdSocket, z);
        this.config = new KQueueServerChannelConfig(this);
    }

    /* access modifiers changed from: protected */
    public Channel newChildChannel(int i, byte[] bArr, int i2, int i3) throws Exception {
        return new KQueueDomainSocketChannel(this, new BsdSocket(i));
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

    public KQueueServerChannelConfig config() {
        return this.config;
    }

    public DomainSocketAddress remoteAddress() {
        return (DomainSocketAddress) super.remoteAddress();
    }

    public DomainSocketAddress localAddress() {
        return (DomainSocketAddress) super.localAddress();
    }
}
