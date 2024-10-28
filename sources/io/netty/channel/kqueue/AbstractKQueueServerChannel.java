package io.netty.channel.kqueue;

import io.netty.channel.Channel;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.ServerChannel;
import io.netty.channel.kqueue.AbstractKQueueChannel;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public abstract class AbstractKQueueServerChannel extends AbstractKQueueChannel implements ServerChannel {
    private static final ChannelMetadata METADATA = new ChannelMetadata(false, 16);

    /* access modifiers changed from: package-private */
    public abstract Channel newChildChannel(int i, byte[] bArr, int i2, int i3) throws Exception;

    /* access modifiers changed from: protected */
    public InetSocketAddress remoteAddress0() {
        return null;
    }

    public /* bridge */ /* synthetic */ boolean isActive() {
        return super.isActive();
    }

    public /* bridge */ /* synthetic */ boolean isOpen() {
        return super.isOpen();
    }

    AbstractKQueueServerChannel(BsdSocket bsdSocket) {
        this(bsdSocket, isSoErrorZero(bsdSocket));
    }

    AbstractKQueueServerChannel(BsdSocket bsdSocket, boolean z) {
        super((Channel) null, bsdSocket, z);
    }

    public ChannelMetadata metadata() {
        return METADATA;
    }

    /* access modifiers changed from: protected */
    public boolean isCompatible(EventLoop eventLoop) {
        return eventLoop instanceof KQueueEventLoop;
    }

    /* access modifiers changed from: protected */
    public AbstractKQueueChannel.AbstractKQueueUnsafe newUnsafe() {
        return new KQueueServerSocketUnsafe();
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
    public boolean doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        throw new UnsupportedOperationException();
    }

    final class KQueueServerSocketUnsafe extends AbstractKQueueChannel.AbstractKQueueUnsafe {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final byte[] acceptedAddress = new byte[26];

        static {
            Class<AbstractKQueueServerChannel> cls = AbstractKQueueServerChannel.class;
        }

        KQueueServerSocketUnsafe() {
            super();
        }

        /* access modifiers changed from: package-private */
        public void readReady(KQueueRecvByteAllocatorHandle kQueueRecvByteAllocatorHandle) {
            KQueueChannelConfig config = AbstractKQueueServerChannel.this.config();
            if (AbstractKQueueServerChannel.this.shouldBreakReadReady(config)) {
                clearReadFilter0();
                return;
            }
            ChannelPipeline pipeline = AbstractKQueueServerChannel.this.pipeline();
            kQueueRecvByteAllocatorHandle.reset(config);
            kQueueRecvByteAllocatorHandle.attemptedBytesRead(1);
            readReadyBefore();
            while (true) {
                try {
                    int accept = AbstractKQueueServerChannel.this.socket.accept(this.acceptedAddress);
                    if (accept != -1) {
                        kQueueRecvByteAllocatorHandle.lastBytesRead(1);
                        kQueueRecvByteAllocatorHandle.incMessagesRead(1);
                        this.readPending = false;
                        AbstractKQueueServerChannel abstractKQueueServerChannel = AbstractKQueueServerChannel.this;
                        byte[] bArr = this.acceptedAddress;
                        pipeline.fireChannelRead(abstractKQueueServerChannel.newChildChannel(accept, bArr, 1, bArr[0]));
                        if (!kQueueRecvByteAllocatorHandle.continueReading()) {
                            break;
                        }
                    } else {
                        kQueueRecvByteAllocatorHandle.lastBytesRead(-1);
                        break;
                    }
                } catch (Throwable th) {
                    th = th;
                }
            }
            th = null;
            try {
                kQueueRecvByteAllocatorHandle.readComplete();
                pipeline.fireChannelReadComplete();
                if (th != null) {
                    pipeline.fireExceptionCaught(th);
                }
            } finally {
                readReadyFinally(config);
            }
        }
    }
}
