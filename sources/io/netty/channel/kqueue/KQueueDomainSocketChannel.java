package io.netty.channel.kqueue;

import io.netty.channel.Channel;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.kqueue.AbstractKQueueChannel;
import io.netty.channel.kqueue.AbstractKQueueStreamChannel;
import io.netty.channel.unix.DomainSocketAddress;
import io.netty.channel.unix.DomainSocketChannel;
import io.netty.channel.unix.FileDescriptor;
import io.netty.channel.unix.PeerCredentials;
import java.io.IOException;
import java.net.SocketAddress;

public final class KQueueDomainSocketChannel extends AbstractKQueueStreamChannel implements DomainSocketChannel {
    private final KQueueDomainSocketChannelConfig config;
    private volatile DomainSocketAddress local;
    private volatile DomainSocketAddress remote;

    public KQueueDomainSocketChannel() {
        super((Channel) null, BsdSocket.newSocketDomain(), false);
        this.config = new KQueueDomainSocketChannelConfig(this);
    }

    public KQueueDomainSocketChannel(int i) {
        this((Channel) null, new BsdSocket(i));
    }

    KQueueDomainSocketChannel(Channel channel, BsdSocket bsdSocket) {
        super(channel, bsdSocket, true);
        this.config = new KQueueDomainSocketChannelConfig(this);
        this.local = bsdSocket.localDomainSocketAddress();
        this.remote = bsdSocket.remoteDomainSocketAddress();
    }

    /* access modifiers changed from: protected */
    public AbstractKQueueChannel.AbstractKQueueUnsafe newUnsafe() {
        return new KQueueDomainUnsafe(this, (AnonymousClass1) null);
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

    public KQueueDomainSocketChannelConfig config() {
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

    private final class KQueueDomainUnsafe extends AbstractKQueueStreamChannel.KQueueStreamUnsafe {
        private KQueueDomainUnsafe() {
            super();
        }

        /* synthetic */ KQueueDomainUnsafe(KQueueDomainSocketChannel kQueueDomainSocketChannel, AnonymousClass1 r2) {
            this();
        }

        /* access modifiers changed from: package-private */
        public void readReady(KQueueRecvByteAllocatorHandle kQueueRecvByteAllocatorHandle) {
            int i = AnonymousClass1.$SwitchMap$io$netty$channel$unix$DomainSocketReadMode[KQueueDomainSocketChannel.this.config().getReadMode().ordinal()];
            if (i == 1) {
                super.readReady(kQueueRecvByteAllocatorHandle);
            } else if (i == 2) {
                readReadyFd();
            } else {
                throw new Error();
            }
        }

        private void readReadyFd() {
            if (KQueueDomainSocketChannel.this.socket.isInputShutdown()) {
                super.clearReadFilter0();
                return;
            }
            KQueueDomainSocketChannelConfig config = KQueueDomainSocketChannel.this.config();
            KQueueRecvByteAllocatorHandle recvBufAllocHandle = recvBufAllocHandle();
            ChannelPipeline pipeline = KQueueDomainSocketChannel.this.pipeline();
            recvBufAllocHandle.reset(config);
            readReadyBefore();
            while (true) {
                try {
                    int recvFd = KQueueDomainSocketChannel.this.socket.recvFd();
                    if (recvFd != -1) {
                        if (recvFd == 0) {
                            recvBufAllocHandle.lastBytesRead(0);
                            break;
                        }
                        recvBufAllocHandle.lastBytesRead(1);
                        recvBufAllocHandle.incMessagesRead(1);
                        this.readPending = false;
                        pipeline.fireChannelRead(new FileDescriptor(recvFd));
                        if (!recvBufAllocHandle.continueReading()) {
                            break;
                        }
                    } else {
                        recvBufAllocHandle.lastBytesRead(-1);
                        close(voidPromise());
                        readReadyFinally(config);
                        return;
                    }
                } catch (Throwable th) {
                    readReadyFinally(config);
                    throw th;
                }
            }
            recvBufAllocHandle.readComplete();
            pipeline.fireChannelReadComplete();
            readReadyFinally(config);
        }
    }

    /* renamed from: io.netty.channel.kqueue.KQueueDomainSocketChannel$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.kqueue.KQueueDomainSocketChannel.AnonymousClass1.<clinit>():void");
        }
    }
}
