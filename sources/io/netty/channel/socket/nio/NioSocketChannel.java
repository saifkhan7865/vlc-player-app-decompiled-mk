package io.netty.channel.socket.nio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.FileRegion;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.nio.AbstractNioByteChannel;
import io.netty.channel.nio.AbstractNioChannel;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.socket.DefaultSocketChannelConfig;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.SocketChannelConfig;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SocketUtils;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Map;
import java.util.concurrent.Executor;

public class NioSocketChannel extends AbstractNioByteChannel implements SocketChannel {
    private static final SelectorProvider DEFAULT_SELECTOR_PROVIDER = SelectorProvider.provider();
    private static final Method OPEN_SOCKET_CHANNEL_WITH_FAMILY = SelectorProviderUtil.findOpenMethod("openSocketChannel");
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) NioSocketChannel.class);
    private final SocketChannelConfig config;

    private static java.nio.channels.SocketChannel newChannel(SelectorProvider selectorProvider, InternetProtocolFamily internetProtocolFamily) {
        try {
            java.nio.channels.SocketChannel socketChannel = (java.nio.channels.SocketChannel) SelectorProviderUtil.newChannel(OPEN_SOCKET_CHANNEL_WITH_FAMILY, selectorProvider, internetProtocolFamily);
            return socketChannel == null ? selectorProvider.openSocketChannel() : socketChannel;
        } catch (IOException e) {
            throw new ChannelException("Failed to open a socket.", e);
        }
    }

    public NioSocketChannel() {
        this(DEFAULT_SELECTOR_PROVIDER);
    }

    public NioSocketChannel(SelectorProvider selectorProvider) {
        this(selectorProvider, (InternetProtocolFamily) null);
    }

    public NioSocketChannel(SelectorProvider selectorProvider, InternetProtocolFamily internetProtocolFamily) {
        this(newChannel(selectorProvider, internetProtocolFamily));
    }

    public NioSocketChannel(java.nio.channels.SocketChannel socketChannel) {
        this((Channel) null, socketChannel);
    }

    public NioSocketChannel(Channel channel, java.nio.channels.SocketChannel socketChannel) {
        super(channel, socketChannel);
        this.config = new NioSocketChannelConfig(this, socketChannel.socket());
    }

    public ServerSocketChannel parent() {
        return (ServerSocketChannel) super.parent();
    }

    public SocketChannelConfig config() {
        return this.config;
    }

    /* access modifiers changed from: protected */
    public java.nio.channels.SocketChannel javaChannel() {
        return (java.nio.channels.SocketChannel) super.javaChannel();
    }

    public boolean isActive() {
        java.nio.channels.SocketChannel javaChannel = javaChannel();
        return javaChannel.isOpen() && javaChannel.isConnected();
    }

    public boolean isOutputShutdown() {
        return javaChannel().socket().isOutputShutdown() || !isActive();
    }

    public boolean isInputShutdown() {
        return javaChannel().socket().isInputShutdown() || !isActive();
    }

    public boolean isShutdown() {
        Socket socket = javaChannel().socket();
        return (socket.isInputShutdown() && socket.isOutputShutdown()) || !isActive();
    }

    public InetSocketAddress localAddress() {
        return (InetSocketAddress) super.localAddress();
    }

    public InetSocketAddress remoteAddress() {
        return (InetSocketAddress) super.remoteAddress();
    }

    /* access modifiers changed from: protected */
    public final void doShutdownOutput() throws Exception {
        if (PlatformDependent.javaVersion() >= 7) {
            java.nio.channels.SocketChannel unused = javaChannel().shutdownOutput();
        } else {
            javaChannel().socket().shutdownOutput();
        }
    }

    public ChannelFuture shutdownOutput() {
        return shutdownOutput(newPromise());
    }

    public ChannelFuture shutdownOutput(final ChannelPromise channelPromise) {
        NioEventLoop eventLoop = eventLoop();
        if (eventLoop.inEventLoop()) {
            ((AbstractChannel.AbstractUnsafe) unsafe()).shutdownOutput(channelPromise);
        } else {
            eventLoop.execute(new Runnable() {
                public void run() {
                    ((AbstractChannel.AbstractUnsafe) NioSocketChannel.this.unsafe()).shutdownOutput(channelPromise);
                }
            });
        }
        return channelPromise;
    }

    public ChannelFuture shutdownInput() {
        return shutdownInput(newPromise());
    }

    /* access modifiers changed from: protected */
    public boolean isInputShutdown0() {
        return isInputShutdown();
    }

    public ChannelFuture shutdownInput(final ChannelPromise channelPromise) {
        NioEventLoop eventLoop = eventLoop();
        if (eventLoop.inEventLoop()) {
            shutdownInput0(channelPromise);
        } else {
            eventLoop.execute(new Runnable() {
                public void run() {
                    NioSocketChannel.this.shutdownInput0(channelPromise);
                }
            });
        }
        return channelPromise;
    }

    public ChannelFuture shutdown() {
        return shutdown(newPromise());
    }

    public ChannelFuture shutdown(final ChannelPromise channelPromise) {
        ChannelFuture shutdownOutput = shutdownOutput();
        if (shutdownOutput.isDone()) {
            shutdownOutputDone(shutdownOutput, channelPromise);
        } else {
            shutdownOutput.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    NioSocketChannel.this.shutdownOutputDone(channelFuture, channelPromise);
                }
            });
        }
        return channelPromise;
    }

    /* access modifiers changed from: private */
    public void shutdownOutputDone(final ChannelFuture channelFuture, final ChannelPromise channelPromise) {
        ChannelFuture shutdownInput = shutdownInput();
        if (shutdownInput.isDone()) {
            shutdownDone(channelFuture, shutdownInput, channelPromise);
        } else {
            shutdownInput.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    NioSocketChannel.shutdownDone(channelFuture, channelFuture, channelPromise);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void shutdownDone(ChannelFuture channelFuture, ChannelFuture channelFuture2, ChannelPromise channelPromise) {
        Throwable cause = channelFuture.cause();
        Throwable cause2 = channelFuture2.cause();
        if (cause != null) {
            if (cause2 != null) {
                logger.debug("Exception suppressed because a previous exception occurred.", cause2);
            }
            channelPromise.setFailure(cause);
        } else if (cause2 != null) {
            channelPromise.setFailure(cause2);
        } else {
            channelPromise.setSuccess();
        }
    }

    /* access modifiers changed from: private */
    public void shutdownInput0(ChannelPromise channelPromise) {
        try {
            shutdownInput0();
            channelPromise.setSuccess();
        } catch (Throwable th) {
            channelPromise.setFailure(th);
        }
    }

    private void shutdownInput0() throws Exception {
        if (PlatformDependent.javaVersion() >= 7) {
            java.nio.channels.SocketChannel unused = javaChannel().shutdownInput();
        } else {
            javaChannel().socket().shutdownInput();
        }
    }

    /* access modifiers changed from: protected */
    public SocketAddress localAddress0() {
        return javaChannel().socket().getLocalSocketAddress();
    }

    /* access modifiers changed from: protected */
    public SocketAddress remoteAddress0() {
        return javaChannel().socket().getRemoteSocketAddress();
    }

    /* access modifiers changed from: protected */
    public void doBind(SocketAddress socketAddress) throws Exception {
        doBind0(socketAddress);
    }

    private void doBind0(SocketAddress socketAddress) throws Exception {
        if (PlatformDependent.javaVersion() >= 7) {
            SocketUtils.bind(javaChannel(), socketAddress);
        } else {
            SocketUtils.bind(javaChannel().socket(), socketAddress);
        }
    }

    /* access modifiers changed from: protected */
    public boolean doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        if (socketAddress2 != null) {
            doBind0(socketAddress2);
        }
        try {
            boolean connect = SocketUtils.connect(javaChannel(), socketAddress);
            if (!connect) {
                selectionKey().interestOps(8);
            }
            return connect;
        } catch (Throwable th) {
            doClose();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void doFinishConnect() throws Exception {
        if (!javaChannel().finishConnect()) {
            throw new Error();
        }
    }

    /* access modifiers changed from: protected */
    public void doDisconnect() throws Exception {
        doClose();
    }

    /* access modifiers changed from: protected */
    public void doClose() throws Exception {
        super.doClose();
        javaChannel().close();
    }

    /* access modifiers changed from: protected */
    public int doReadBytes(ByteBuf byteBuf) throws Exception {
        RecvByteBufAllocator.Handle recvBufAllocHandle = unsafe().recvBufAllocHandle();
        recvBufAllocHandle.attemptedBytesRead(byteBuf.writableBytes());
        return byteBuf.writeBytes((ScatteringByteChannel) javaChannel(), recvBufAllocHandle.attemptedBytesRead());
    }

    /* access modifiers changed from: protected */
    public int doWriteBytes(ByteBuf byteBuf) throws Exception {
        return byteBuf.readBytes((GatheringByteChannel) javaChannel(), byteBuf.readableBytes());
    }

    /* access modifiers changed from: protected */
    public long doWriteFileRegion(FileRegion fileRegion) throws Exception {
        return fileRegion.transferTo(javaChannel(), fileRegion.transferred());
    }

    private void adjustMaxBytesPerGatheringWrite(int i, int i2, int i3) {
        int i4;
        if (i == i2) {
            int i5 = i << 1;
            if (i5 > i3) {
                ((NioSocketChannelConfig) this.config).setMaxBytesPerGatheringWrite(i5);
            }
        } else if (i > 4096 && i2 < (i4 = i >>> 1)) {
            ((NioSocketChannelConfig) this.config).setMaxBytesPerGatheringWrite(i4);
        }
    }

    /* access modifiers changed from: protected */
    public void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        java.nio.channels.SocketChannel javaChannel = javaChannel();
        int writeSpinCount = config().getWriteSpinCount();
        while (!channelOutboundBuffer.isEmpty()) {
            int maxBytesPerGatheringWrite = ((NioSocketChannelConfig) this.config).getMaxBytesPerGatheringWrite();
            ByteBuffer[] nioBuffers = channelOutboundBuffer.nioBuffers(1024, (long) maxBytesPerGatheringWrite);
            int nioBufferCount = channelOutboundBuffer.nioBufferCount();
            boolean z = false;
            if (nioBufferCount != 0) {
                if (nioBufferCount != 1) {
                    long nioBufferSize = channelOutboundBuffer.nioBufferSize();
                    long write = javaChannel.write(nioBuffers, 0, nioBufferCount);
                    if (write <= 0) {
                        incompleteWrite(true);
                        return;
                    } else {
                        adjustMaxBytesPerGatheringWrite((int) nioBufferSize, (int) write, maxBytesPerGatheringWrite);
                        channelOutboundBuffer.removeBytes(write);
                    }
                } else {
                    ByteBuffer byteBuffer = nioBuffers[0];
                    int remaining = byteBuffer.remaining();
                    int write2 = javaChannel.write(byteBuffer);
                    if (write2 <= 0) {
                        incompleteWrite(true);
                        return;
                    } else {
                        adjustMaxBytesPerGatheringWrite(remaining, write2, maxBytesPerGatheringWrite);
                        channelOutboundBuffer.removeBytes((long) write2);
                    }
                }
                writeSpinCount--;
                continue;
            } else {
                writeSpinCount -= doWrite0(channelOutboundBuffer);
                continue;
            }
            if (writeSpinCount <= 0) {
                if (writeSpinCount < 0) {
                    z = true;
                }
                incompleteWrite(z);
                return;
            }
        }
        clearOpWrite();
    }

    /* access modifiers changed from: protected */
    public AbstractNioChannel.AbstractNioUnsafe newUnsafe() {
        return new NioSocketChannelUnsafe();
    }

    private final class NioSocketChannelUnsafe extends AbstractNioByteChannel.NioByteUnsafe {
        private NioSocketChannelUnsafe() {
            super();
        }

        /* access modifiers changed from: protected */
        public Executor prepareToClose() {
            try {
                if (!NioSocketChannel.this.javaChannel().isOpen() || NioSocketChannel.this.config().getSoLinger() <= 0) {
                    return null;
                }
                NioSocketChannel.this.doDeregister();
                return GlobalEventExecutor.INSTANCE;
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    private final class NioSocketChannelConfig extends DefaultSocketChannelConfig {
        private volatile int maxBytesPerGatheringWrite;

        private NioSocketChannelConfig(NioSocketChannel nioSocketChannel, Socket socket) {
            super(nioSocketChannel, socket);
            this.maxBytesPerGatheringWrite = Integer.MAX_VALUE;
            calculateMaxBytesPerGatheringWrite();
        }

        /* access modifiers changed from: protected */
        public void autoReadCleared() {
            NioSocketChannel.this.clearReadPending();
        }

        public NioSocketChannelConfig setSendBufferSize(int i) {
            super.setSendBufferSize(i);
            calculateMaxBytesPerGatheringWrite();
            return this;
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

        /* access modifiers changed from: package-private */
        public void setMaxBytesPerGatheringWrite(int i) {
            this.maxBytesPerGatheringWrite = i;
        }

        /* access modifiers changed from: package-private */
        public int getMaxBytesPerGatheringWrite() {
            return this.maxBytesPerGatheringWrite;
        }

        private void calculateMaxBytesPerGatheringWrite() {
            int sendBufferSize = getSendBufferSize() << 1;
            if (sendBufferSize > 0) {
                setMaxBytesPerGatheringWrite(sendBufferSize);
            }
        }

        private java.nio.channels.SocketChannel jdkChannel() {
            return ((NioSocketChannel) this.channel).javaChannel();
        }
    }
}
