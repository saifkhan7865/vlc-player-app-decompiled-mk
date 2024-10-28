package io.netty.channel.epoll;

import io.netty.channel.Channel;
import io.netty.channel.EventLoop;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.unix.NativeInetAddress;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public final class EpollServerSocketChannel extends AbstractEpollServerChannel implements ServerSocketChannel {
    private final EpollServerSocketChannelConfig config;
    private volatile Collection<InetAddress> tcpMd5SigAddresses;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public EpollServerSocketChannel() {
        this((InternetProtocolFamily) null);
        InternetProtocolFamily internetProtocolFamily = null;
    }

    public EpollServerSocketChannel(InternetProtocolFamily internetProtocolFamily) {
        super(LinuxSocket.newSocketStream(internetProtocolFamily), false);
        this.tcpMd5SigAddresses = Collections.emptyList();
        this.config = new EpollServerSocketChannelConfig(this);
    }

    public EpollServerSocketChannel(int i) {
        this(new LinuxSocket(i));
    }

    EpollServerSocketChannel(LinuxSocket linuxSocket) {
        super(linuxSocket);
        this.tcpMd5SigAddresses = Collections.emptyList();
        this.config = new EpollServerSocketChannelConfig(this);
    }

    EpollServerSocketChannel(LinuxSocket linuxSocket, boolean z) {
        super(linuxSocket, z);
        this.tcpMd5SigAddresses = Collections.emptyList();
        this.config = new EpollServerSocketChannelConfig(this);
    }

    /* access modifiers changed from: protected */
    public boolean isCompatible(EventLoop eventLoop) {
        return eventLoop instanceof EpollEventLoop;
    }

    /* access modifiers changed from: protected */
    public void doBind(SocketAddress socketAddress) throws Exception {
        int tcpFastopen;
        super.doBind(socketAddress);
        if (Native.IS_SUPPORTING_TCP_FASTOPEN_SERVER && (tcpFastopen = this.config.getTcpFastopen()) > 0) {
            this.socket.setTcpFastOpen(tcpFastopen);
        }
        this.socket.listen(this.config.getBacklog());
        this.active = true;
    }

    public InetSocketAddress remoteAddress() {
        return (InetSocketAddress) super.remoteAddress();
    }

    public InetSocketAddress localAddress() {
        return (InetSocketAddress) super.localAddress();
    }

    public EpollServerSocketChannelConfig config() {
        return this.config;
    }

    /* access modifiers changed from: protected */
    public Channel newChildChannel(int i, byte[] bArr, int i2, int i3) throws Exception {
        return new EpollSocketChannel(this, new LinuxSocket(i), NativeInetAddress.address(bArr, i2, i3));
    }

    /* access modifiers changed from: package-private */
    public Collection<InetAddress> tcpMd5SigAddresses() {
        return this.tcpMd5SigAddresses;
    }

    /* access modifiers changed from: package-private */
    public void setTcpMd5Sig(Map<InetAddress, byte[]> map) throws IOException {
        synchronized (this) {
            this.tcpMd5SigAddresses = TcpMd5Util.newTcpMd5Sigs(this, this.tcpMd5SigAddresses, map);
        }
    }
}
