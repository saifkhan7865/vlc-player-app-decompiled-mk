package io.netty.channel.epoll;

import io.netty.channel.Channel;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.epoll.AbstractEpollChannel;
import io.netty.channel.epoll.AbstractEpollStreamChannel;
import io.netty.channel.unix.DomainSocketAddress;
import io.netty.channel.unix.DomainSocketChannel;
import io.netty.channel.unix.FileDescriptor;
import io.netty.channel.unix.PeerCredentials;
import java.io.IOException;
import java.net.SocketAddress;

public final class EpollDomainSocketChannel extends AbstractEpollStreamChannel implements DomainSocketChannel {
    private final EpollDomainSocketChannelConfig config;
    private volatile DomainSocketAddress local;
    private volatile DomainSocketAddress remote;

    public EpollDomainSocketChannel() {
        super(LinuxSocket.newSocketDomain(), false);
        this.config = new EpollDomainSocketChannelConfig(this);
    }

    EpollDomainSocketChannel(Channel channel, FileDescriptor fileDescriptor) {
        this(channel, new LinuxSocket(fileDescriptor.intValue()));
    }

    public EpollDomainSocketChannel(int i) {
        super(i);
        this.config = new EpollDomainSocketChannelConfig(this);
    }

    public EpollDomainSocketChannel(Channel channel, LinuxSocket linuxSocket) {
        super(channel, linuxSocket);
        this.config = new EpollDomainSocketChannelConfig(this);
        this.local = linuxSocket.localDomainSocketAddress();
        this.remote = linuxSocket.remoteDomainSocketAddress();
    }

    public EpollDomainSocketChannel(int i, boolean z) {
        super(new LinuxSocket(i), z);
        this.config = new EpollDomainSocketChannelConfig(this);
    }

    /* access modifiers changed from: protected */
    public AbstractEpollChannel.AbstractEpollUnsafe newUnsafe() {
        return new EpollDomainUnsafe(this, (AnonymousClass1) null);
    }

    /* access modifiers changed from: protected */
    public DomainSocketAddress localAddress0() {
        return this.local;
    }

    /* access modifiers changed from: protected */
    public DomainSocketAddress remoteAddress0() {
        return this.remote;
    }

    /* access modifiers changed from: protected */
    public void doBind(SocketAddress socketAddress) throws Exception {
        this.socket.bind(socketAddress);
        this.local = (DomainSocketAddress) socketAddress;
    }

    public EpollDomainSocketChannelConfig config() {
        return this.config;
    }

    /* access modifiers changed from: protected */
    public boolean doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        if (!super.doConnect(socketAddress, socketAddress2)) {
            return false;
        }
        this.local = socketAddress2 != null ? (DomainSocketAddress) socketAddress2 : this.socket.localDomainSocketAddress();
        this.remote = (DomainSocketAddress) socketAddress;
        return true;
    }

    public DomainSocketAddress remoteAddress() {
        return (DomainSocketAddress) super.remoteAddress();
    }

    public DomainSocketAddress localAddress() {
        return (DomainSocketAddress) super.localAddress();
    }

    /* access modifiers changed from: protected */
    public int doWriteSingle(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        Object current = channelOutboundBuffer.current();
        if (!(current instanceof FileDescriptor) || this.socket.sendFd(((FileDescriptor) current).intValue()) <= 0) {
            return super.doWriteSingle(channelOutboundBuffer);
        }
        channelOutboundBuffer.remove();
        return 1;
    }

    /* access modifiers changed from: protected */
    public Object filterOutboundMessage(Object obj) {
        if (obj instanceof FileDescriptor) {
            return obj;
        }
        return super.filterOutboundMessage(obj);
    }

    public PeerCredentials peerCredentials() throws IOException {
        return this.socket.getPeerCredentials();
    }

    private final class EpollDomainUnsafe extends AbstractEpollStreamChannel.EpollStreamUnsafe {
        private EpollDomainUnsafe() {
            super();
        }

        /* synthetic */ EpollDomainUnsafe(EpollDomainSocketChannel epollDomainSocketChannel, AnonymousClass1 r2) {
            this();
        }

        /* access modifiers changed from: package-private */
        public void epollInReady() {
            int i = AnonymousClass1.$SwitchMap$io$netty$channel$unix$DomainSocketReadMode[EpollDomainSocketChannel.this.config().getReadMode().ordinal()];
            if (i == 1) {
                super.epollInReady();
            } else if (i == 2) {
                epollInReadFd();
            } else {
                throw new Error();
            }
        }

        private void epollInReadFd() {
            if (EpollDomainSocketChannel.this.socket.isInputShutdown()) {
                clearEpollIn0();
                return;
            }
            EpollDomainSocketChannelConfig config = EpollDomainSocketChannel.this.config();
            EpollRecvByteAllocatorHandle recvBufAllocHandle = recvBufAllocHandle();
            recvBufAllocHandle.edgeTriggered(EpollDomainSocketChannel.this.isFlagSet(Native.EPOLLET));
            ChannelPipeline pipeline = EpollDomainSocketChannel.this.pipeline();
            recvBufAllocHandle.reset(config);
            epollInBefore();
            do {
                try {
                    recvBufAllocHandle.lastBytesRead(EpollDomainSocketChannel.this.socket.recvFd());
                    int lastBytesRead = recvBufAllocHandle.lastBytesRead();
                    if (lastBytesRead != -1) {
                        if (lastBytesRead == 0) {
                            break;
                        }
                        recvBufAllocHandle.incMessagesRead(1);
                        this.readPending = false;
                        pipeline.fireChannelRead(new FileDescriptor(recvBufAllocHandle.lastBytesRead()));
                    } else {
                        close(voidPromise());
                        epollInFinally(config);
                        return;
                    }
                } catch (Throwable th) {
                    epollInFinally(config);
                    throw th;
                }
            } while (recvBufAllocHandle.continueReading());
            recvBufAllocHandle.readComplete();
            pipeline.fireChannelReadComplete();
            epollInFinally(config);
        }
    }

    /* renamed from: io.netty.channel.epoll.EpollDomainSocketChannel$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$channel$unix$DomainSocketReadMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                io.netty.channel.unix.DomainSocketReadMode[] r0 = io.netty.channel.unix.DomainSocketReadMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$channel$unix$DomainSocketReadMode = r0
                io.netty.channel.unix.DomainSocketReadMode r1 = io.netty.channel.unix.DomainSocketReadMode.BYTES     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$channel$unix$DomainSocketReadMode     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.channel.unix.DomainSocketReadMode r1 = io.netty.channel.unix.DomainSocketReadMode.FILE_DESCRIPTORS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.epoll.EpollDomainSocketChannel.AnonymousClass1.<clinit>():void");
        }
    }
}
