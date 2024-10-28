package io.netty.handler.codec.http2;

import androidx.core.app.NotificationCompat;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.DefaultChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.VoidChannelPromise;
import io.netty.channel.socket.ChannelInputShutdownReadComplete;
import io.netty.channel.socket.ChannelOutputShutdownEvent;
import io.netty.handler.codec.http2.Http2FrameCodec;
import io.netty.handler.ssl.SslCloseCompletionEvent;
import io.netty.util.DefaultAttributeMap;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

abstract class AbstractHttp2StreamChannel extends DefaultAttributeMap implements Http2StreamChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final Http2FrameStreamVisitor CHANNEL_INPUT_SHUTDOWN_READ_COMPLETE_VISITOR = new UserEventStreamVisitor(ChannelInputShutdownReadComplete.INSTANCE);
    static final Http2FrameStreamVisitor CHANNEL_OUTPUT_SHUTDOWN_EVENT_VISITOR = new UserEventStreamVisitor(ChannelOutputShutdownEvent.INSTANCE);
    private static final ChannelMetadata METADATA = new ChannelMetadata(false, 16);
    private static final int MIN_HTTP2_FRAME_SIZE = 9;
    static final Http2FrameStreamVisitor SSL_CLOSE_COMPLETION_EVENT_VISITOR = new UserEventStreamVisitor(SslCloseCompletionEvent.SUCCESS);
    private static final AtomicLongFieldUpdater<AbstractHttp2StreamChannel> TOTAL_PENDING_SIZE_UPDATER;
    private static final AtomicIntegerFieldUpdater<AbstractHttp2StreamChannel> UNWRITABLE_UPDATER;
    static final Http2FrameStreamVisitor WRITABLE_VISITOR = new Http2FrameStreamVisitor() {
        public boolean visit(Http2FrameStream http2FrameStream) {
            ((AbstractHttp2StreamChannel) ((Http2FrameCodec.DefaultHttp2FrameStream) http2FrameStream).attachment).trySetWritable();
            return true;
        }
    };
    /* access modifiers changed from: private */
    public static final InternalLogger logger;
    private final ChannelId channelId;
    /* access modifiers changed from: private */
    public final ChannelPromise closePromise;
    /* access modifiers changed from: private */
    public final Http2StreamChannelConfig config = new Http2StreamChannelConfig(this);
    private Runnable fireChannelWritabilityChangedTask;
    /* access modifiers changed from: private */
    public boolean firstFrameWritten;
    /* access modifiers changed from: private */
    public int flowControlledBytes;
    /* access modifiers changed from: private */
    public Queue<Object> inboundBuffer;
    /* access modifiers changed from: private */
    public boolean outboundClosed;
    /* access modifiers changed from: private */
    public final ChannelPipeline pipeline;
    /* access modifiers changed from: private */
    public boolean readCompletePending;
    /* access modifiers changed from: private */
    public ReadStatus readStatus = ReadStatus.IDLE;
    /* access modifiers changed from: private */
    public volatile boolean registered;
    /* access modifiers changed from: private */
    public final Http2FrameCodec.DefaultHttp2FrameStream stream;
    private volatile long totalPendingSize;
    /* access modifiers changed from: private */
    public final Http2ChannelUnsafe unsafe = new Http2ChannelUnsafe();
    private volatile int unwritable;
    /* access modifiers changed from: private */
    public final ChannelFutureListener windowUpdateFrameWriteListener = new ChannelFutureListener() {
        public void operationComplete(ChannelFuture channelFuture) {
            AbstractHttp2StreamChannel.windowUpdateFrameWriteComplete(channelFuture, AbstractHttp2StreamChannel.this);
        }
    };

    private enum ReadStatus {
        IDLE,
        IN_PROGRESS,
        REQUESTED
    }

    /* access modifiers changed from: protected */
    public abstract void addChannelToReadCompletePendingQueue();

    public boolean equals(Object obj) {
        return this == obj;
    }

    /* access modifiers changed from: protected */
    public abstract boolean isParentReadInProgress();

    /* access modifiers changed from: protected */
    public abstract ChannelHandlerContext parentContext();

    static {
        Class<AbstractHttp2StreamChannel> cls = AbstractHttp2StreamChannel.class;
        logger = InternalLoggerFactory.getInstance((Class<?>) cls);
        TOTAL_PENDING_SIZE_UPDATER = AtomicLongFieldUpdater.newUpdater(cls, "totalPendingSize");
        UNWRITABLE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(cls, "unwritable");
    }

    private static final class UserEventStreamVisitor implements Http2FrameStreamVisitor {
        private final Object event;

        UserEventStreamVisitor(Object obj) {
            this.event = ObjectUtil.checkNotNull(obj, NotificationCompat.CATEGORY_EVENT);
        }

        public boolean visit(Http2FrameStream http2FrameStream) {
            ((AbstractHttp2StreamChannel) ((Http2FrameCodec.DefaultHttp2FrameStream) http2FrameStream).attachment).pipeline().fireUserEventTriggered(this.event);
            return true;
        }
    }

    private static final class FlowControlledFrameSizeEstimator implements MessageSizeEstimator {
        /* access modifiers changed from: private */
        public static final MessageSizeEstimator.Handle HANDLE_INSTANCE = new MessageSizeEstimator.Handle() {
            public int size(Object obj) {
                if (obj instanceof Http2DataFrame) {
                    return (int) Math.min(2147483647L, ((long) ((Http2DataFrame) obj).initialFlowControlledBytes()) + 9);
                }
                return 9;
            }
        };
        static final FlowControlledFrameSizeEstimator INSTANCE = new FlowControlledFrameSizeEstimator();

        private FlowControlledFrameSizeEstimator() {
        }

        public MessageSizeEstimator.Handle newHandle() {
            return HANDLE_INSTANCE;
        }
    }

    /* access modifiers changed from: private */
    public static void windowUpdateFrameWriteComplete(ChannelFuture channelFuture, Channel channel) {
        Throwable cause;
        Throwable cause2 = channelFuture.cause();
        if (cause2 != null) {
            if ((cause2 instanceof Http2FrameStreamException) && (cause = cause2.getCause()) != null) {
                cause2 = cause;
            }
            channel.pipeline().fireExceptionCaught(cause2);
            channel.unsafe().close(channel.unsafe().voidPromise());
        }
    }

    AbstractHttp2StreamChannel(Http2FrameCodec.DefaultHttp2FrameStream defaultHttp2FrameStream, int i, ChannelHandler channelHandler) {
        this.stream = defaultHttp2FrameStream;
        defaultHttp2FrameStream.attachment = this;
        AnonymousClass3 r3 = new DefaultChannelPipeline(this) {
            /* access modifiers changed from: protected */
            public void incrementPendingOutboundBytes(long j) {
                AbstractHttp2StreamChannel.this.incrementPendingOutboundBytes(j, true);
            }

            /* access modifiers changed from: protected */
            public void decrementPendingOutboundBytes(long j) {
                AbstractHttp2StreamChannel.this.decrementPendingOutboundBytes(j, true);
            }

            /* access modifiers changed from: protected */
            public void onUnhandledInboundException(Throwable th) {
                if (th instanceof Http2FrameStreamException) {
                    AbstractHttp2StreamChannel.this.closeWithError(((Http2FrameStreamException) th).error());
                    return;
                }
                Http2Exception embeddedHttp2Exception = Http2CodecUtil.getEmbeddedHttp2Exception(th);
                if (embeddedHttp2Exception != null) {
                    AbstractHttp2StreamChannel.this.closeWithError(embeddedHttp2Exception.error());
                } else {
                    super.onUnhandledInboundException(th);
                }
            }
        };
        this.pipeline = r3;
        this.closePromise = r3.newPromise();
        this.channelId = new Http2StreamChannelId(parent().id(), i);
        if (channelHandler != null) {
            r3.addLast(channelHandler);
        }
    }

    /* access modifiers changed from: private */
    public void incrementPendingOutboundBytes(long j, boolean z) {
        if (j != 0 && TOTAL_PENDING_SIZE_UPDATER.addAndGet(this, j) > ((long) config().getWriteBufferHighWaterMark())) {
            setUnwritable(z);
        }
    }

    /* access modifiers changed from: private */
    public void decrementPendingOutboundBytes(long j, boolean z) {
        if (j != 0 && TOTAL_PENDING_SIZE_UPDATER.addAndGet(this, -j) < ((long) config().getWriteBufferLowWaterMark()) && parent().isWritable()) {
            setWritable(z);
        }
    }

    /* access modifiers changed from: package-private */
    public final void trySetWritable() {
        if (this.totalPendingSize < ((long) config().getWriteBufferLowWaterMark())) {
            setWritable(false);
        }
    }

    private void setWritable(boolean z) {
        int i;
        int i2;
        do {
            i = this.unwritable;
            i2 = i & -2;
        } while (!UNWRITABLE_UPDATER.compareAndSet(this, i, i2));
        if (i != 0 && i2 == 0) {
            fireChannelWritabilityChanged(z);
        }
    }

    private void setUnwritable(boolean z) {
        int i;
        do {
            i = this.unwritable;
        } while (!UNWRITABLE_UPDATER.compareAndSet(this, i, i | 1));
        if (i == 0) {
            fireChannelWritabilityChanged(z);
        }
    }

    private void fireChannelWritabilityChanged(boolean z) {
        final ChannelPipeline pipeline2 = pipeline();
        if (z) {
            Runnable runnable = this.fireChannelWritabilityChangedTask;
            if (runnable == null) {
                runnable = new Runnable() {
                    public void run() {
                        pipeline2.fireChannelWritabilityChanged();
                    }
                };
                this.fireChannelWritabilityChangedTask = runnable;
            }
            eventLoop().execute(runnable);
            return;
        }
        pipeline2.fireChannelWritabilityChanged();
    }

    public Http2FrameStream stream() {
        return this.stream;
    }

    /* access modifiers changed from: package-private */
    public void closeOutbound() {
        this.outboundClosed = true;
    }

    /* access modifiers changed from: package-private */
    public void streamClosed() {
        this.unsafe.readEOS();
        this.unsafe.doBeginRead();
    }

    public ChannelMetadata metadata() {
        return METADATA;
    }

    public ChannelConfig config() {
        return this.config;
    }

    public boolean isOpen() {
        return !this.closePromise.isDone();
    }

    public boolean isActive() {
        return isOpen();
    }

    public boolean isWritable() {
        return this.unwritable == 0;
    }

    public ChannelId id() {
        return this.channelId;
    }

    public EventLoop eventLoop() {
        return parent().eventLoop();
    }

    public Channel parent() {
        return parentContext().channel();
    }

    public boolean isRegistered() {
        return this.registered;
    }

    public SocketAddress localAddress() {
        return parent().localAddress();
    }

    public SocketAddress remoteAddress() {
        return parent().remoteAddress();
    }

    public ChannelFuture closeFuture() {
        return this.closePromise;
    }

    public long bytesBeforeUnwritable() {
        long writeBufferHighWaterMark = (((long) config().getWriteBufferHighWaterMark()) - this.totalPendingSize) + 1;
        if (writeBufferHighWaterMark <= 0 || !isWritable()) {
            return 0;
        }
        return writeBufferHighWaterMark;
    }

    public long bytesBeforeWritable() {
        long writeBufferLowWaterMark = (this.totalPendingSize - ((long) config().getWriteBufferLowWaterMark())) + 1;
        if (writeBufferLowWaterMark <= 0 || isWritable()) {
            return 0;
        }
        return writeBufferLowWaterMark;
    }

    public Channel.Unsafe unsafe() {
        return this.unsafe;
    }

    public ChannelPipeline pipeline() {
        return this.pipeline;
    }

    public ByteBufAllocator alloc() {
        return config().getAllocator();
    }

    public Channel read() {
        pipeline().read();
        return this;
    }

    public Channel flush() {
        pipeline().flush();
        return this;
    }

    public ChannelFuture bind(SocketAddress socketAddress) {
        return pipeline().bind(socketAddress);
    }

    public ChannelFuture connect(SocketAddress socketAddress) {
        return pipeline().connect(socketAddress);
    }

    public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2) {
        return pipeline().connect(socketAddress, socketAddress2);
    }

    public ChannelFuture disconnect() {
        return pipeline().disconnect();
    }

    public ChannelFuture close() {
        return pipeline().close();
    }

    public ChannelFuture deregister() {
        return pipeline().deregister();
    }

    public ChannelFuture bind(SocketAddress socketAddress, ChannelPromise channelPromise) {
        return pipeline().bind(socketAddress, channelPromise);
    }

    public ChannelFuture connect(SocketAddress socketAddress, ChannelPromise channelPromise) {
        return pipeline().connect(socketAddress, channelPromise);
    }

    public ChannelFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
        return pipeline().connect(socketAddress, socketAddress2, channelPromise);
    }

    public ChannelFuture disconnect(ChannelPromise channelPromise) {
        return pipeline().disconnect(channelPromise);
    }

    public ChannelFuture close(ChannelPromise channelPromise) {
        return pipeline().close(channelPromise);
    }

    public ChannelFuture deregister(ChannelPromise channelPromise) {
        return pipeline().deregister(channelPromise);
    }

    public ChannelFuture write(Object obj) {
        return pipeline().write(obj);
    }

    public ChannelFuture write(Object obj, ChannelPromise channelPromise) {
        return pipeline().write(obj, channelPromise);
    }

    public ChannelFuture writeAndFlush(Object obj, ChannelPromise channelPromise) {
        return pipeline().writeAndFlush(obj, channelPromise);
    }

    public ChannelFuture writeAndFlush(Object obj) {
        return pipeline().writeAndFlush(obj);
    }

    public ChannelPromise newPromise() {
        return pipeline().newPromise();
    }

    public ChannelProgressivePromise newProgressivePromise() {
        return pipeline().newProgressivePromise();
    }

    public ChannelFuture newSucceededFuture() {
        return pipeline().newSucceededFuture();
    }

    public ChannelFuture newFailedFuture(Throwable th) {
        return pipeline().newFailedFuture(th);
    }

    public ChannelPromise voidPromise() {
        return pipeline().voidPromise();
    }

    public int hashCode() {
        return id().hashCode();
    }

    public int compareTo(Channel channel) {
        if (this == channel) {
            return 0;
        }
        return id().compareTo(channel.id());
    }

    public String toString() {
        return parent().toString() + "(H2 - " + this.stream + ')';
    }

    /* access modifiers changed from: package-private */
    public void fireChildRead(Http2Frame http2Frame) {
        if (!isActive()) {
            ReferenceCountUtil.release(http2Frame);
        } else if (this.readStatus != ReadStatus.IDLE) {
            RecvByteBufAllocator.Handle recvBufAllocHandle = this.unsafe.recvBufAllocHandle();
            this.unsafe.doRead0(http2Frame, recvBufAllocHandle);
            if (recvBufAllocHandle.continueReading()) {
                maybeAddChannelToReadCompletePendingQueue();
            } else {
                this.unsafe.notifyReadComplete(recvBufAllocHandle, true);
            }
        } else {
            if (this.inboundBuffer == null) {
                this.inboundBuffer = new ArrayDeque(4);
            }
            this.inboundBuffer.add(http2Frame);
        }
    }

    /* access modifiers changed from: package-private */
    public void fireChildReadComplete() {
        Http2ChannelUnsafe http2ChannelUnsafe = this.unsafe;
        http2ChannelUnsafe.notifyReadComplete(http2ChannelUnsafe.recvBufAllocHandle(), false);
    }

    /* access modifiers changed from: package-private */
    public final void closeWithError(Http2Error http2Error) {
        Http2ChannelUnsafe http2ChannelUnsafe = this.unsafe;
        http2ChannelUnsafe.close(http2ChannelUnsafe.voidPromise(), http2Error);
    }

    private final class Http2ChannelUnsafe implements Channel.Unsafe {
        private boolean closeInitiated;
        private boolean readEOS;
        private RecvByteBufAllocator.Handle recvHandle;
        private final VoidChannelPromise unsafeVoidPromise;
        private boolean writeDoneAndNoFlush;

        public ChannelOutboundBuffer outboundBuffer() {
            return null;
        }

        private Http2ChannelUnsafe() {
            this.unsafeVoidPromise = new VoidChannelPromise(AbstractHttp2StreamChannel.this, false);
        }

        public void connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
            if (channelPromise.setUncancellable()) {
                channelPromise.setFailure(new UnsupportedOperationException());
            }
        }

        public RecvByteBufAllocator.Handle recvBufAllocHandle() {
            if (this.recvHandle == null) {
                RecvByteBufAllocator.Handle newHandle = AbstractHttp2StreamChannel.this.config().getRecvByteBufAllocator().newHandle();
                this.recvHandle = newHandle;
                newHandle.reset(AbstractHttp2StreamChannel.this.config());
            }
            return this.recvHandle;
        }

        public SocketAddress localAddress() {
            return AbstractHttp2StreamChannel.this.parent().unsafe().localAddress();
        }

        public SocketAddress remoteAddress() {
            return AbstractHttp2StreamChannel.this.parent().unsafe().remoteAddress();
        }

        public void register(EventLoop eventLoop, ChannelPromise channelPromise) {
            if (channelPromise.setUncancellable()) {
                if (AbstractHttp2StreamChannel.this.registered) {
                    channelPromise.setFailure(new UnsupportedOperationException("Re-register is not supported"));
                    return;
                }
                boolean unused = AbstractHttp2StreamChannel.this.registered = true;
                channelPromise.setSuccess();
                AbstractHttp2StreamChannel.this.pipeline().fireChannelRegistered();
                if (AbstractHttp2StreamChannel.this.isActive()) {
                    AbstractHttp2StreamChannel.this.pipeline().fireChannelActive();
                }
            }
        }

        public void bind(SocketAddress socketAddress, ChannelPromise channelPromise) {
            if (channelPromise.setUncancellable()) {
                channelPromise.setFailure(new UnsupportedOperationException());
            }
        }

        public void disconnect(ChannelPromise channelPromise) {
            close(channelPromise);
        }

        public void close(ChannelPromise channelPromise) {
            close(channelPromise, Http2Error.CANCEL);
        }

        /* access modifiers changed from: package-private */
        public void close(final ChannelPromise channelPromise, Http2Error http2Error) {
            if (channelPromise.setUncancellable()) {
                if (!this.closeInitiated) {
                    this.closeInitiated = true;
                    boolean unused = AbstractHttp2StreamChannel.this.readCompletePending = false;
                    boolean isActive = AbstractHttp2StreamChannel.this.isActive();
                    if (AbstractHttp2StreamChannel.this.parent().isActive() && !this.readEOS && Http2CodecUtil.isStreamIdValid(AbstractHttp2StreamChannel.this.stream.id())) {
                        write(new DefaultHttp2ResetFrame(http2Error).stream(AbstractHttp2StreamChannel.this.stream()), AbstractHttp2StreamChannel.this.unsafe().voidPromise());
                        flush();
                    }
                    if (AbstractHttp2StreamChannel.this.inboundBuffer != null) {
                        while (true) {
                            Object poll = AbstractHttp2StreamChannel.this.inboundBuffer.poll();
                            if (poll == null) {
                                break;
                            }
                            ReferenceCountUtil.release(poll);
                        }
                        Queue unused2 = AbstractHttp2StreamChannel.this.inboundBuffer = null;
                    }
                    boolean unused3 = AbstractHttp2StreamChannel.this.outboundClosed = true;
                    AbstractHttp2StreamChannel.this.closePromise.setSuccess();
                    channelPromise.setSuccess();
                    fireChannelInactiveAndDeregister(voidPromise(), isActive);
                } else if (AbstractHttp2StreamChannel.this.closePromise.isDone()) {
                    channelPromise.setSuccess();
                } else if (!(channelPromise instanceof VoidChannelPromise)) {
                    AbstractHttp2StreamChannel.this.closePromise.addListener(new ChannelFutureListener() {
                        public void operationComplete(ChannelFuture channelFuture) {
                            channelPromise.setSuccess();
                        }
                    });
                }
            }
        }

        public void closeForcibly() {
            close(AbstractHttp2StreamChannel.this.unsafe().voidPromise());
        }

        public void deregister(ChannelPromise channelPromise) {
            fireChannelInactiveAndDeregister(channelPromise, false);
        }

        private void fireChannelInactiveAndDeregister(final ChannelPromise channelPromise, final boolean z) {
            if (channelPromise.setUncancellable()) {
                if (!AbstractHttp2StreamChannel.this.registered) {
                    channelPromise.setSuccess();
                } else {
                    invokeLater(new Runnable() {
                        public void run() {
                            if (z) {
                                AbstractHttp2StreamChannel.this.pipeline.fireChannelInactive();
                            }
                            if (AbstractHttp2StreamChannel.this.registered) {
                                boolean unused = AbstractHttp2StreamChannel.this.registered = false;
                                AbstractHttp2StreamChannel.this.pipeline.fireChannelUnregistered();
                            }
                            Http2ChannelUnsafe.this.safeSetSuccess(channelPromise);
                        }
                    });
                }
            }
        }

        /* access modifiers changed from: private */
        public void safeSetSuccess(ChannelPromise channelPromise) {
            if (!(channelPromise instanceof VoidChannelPromise) && !channelPromise.trySuccess()) {
                AbstractHttp2StreamChannel.logger.warn("Failed to mark a promise as success because it is done already: {}", (Object) channelPromise);
            }
        }

        private void invokeLater(Runnable runnable) {
            try {
                AbstractHttp2StreamChannel.this.eventLoop().execute(runnable);
            } catch (RejectedExecutionException e) {
                AbstractHttp2StreamChannel.logger.warn("Can't invoke task later as EventLoop rejected it", (Throwable) e);
            }
        }

        public void beginRead() {
            if (AbstractHttp2StreamChannel.this.isActive()) {
                updateLocalWindowIfNeeded();
                int i = AnonymousClass5.$SwitchMap$io$netty$handler$codec$http2$AbstractHttp2StreamChannel$ReadStatus[AbstractHttp2StreamChannel.this.readStatus.ordinal()];
                if (i == 1) {
                    ReadStatus unused = AbstractHttp2StreamChannel.this.readStatus = ReadStatus.IN_PROGRESS;
                    doBeginRead();
                } else if (i == 2) {
                    ReadStatus unused2 = AbstractHttp2StreamChannel.this.readStatus = ReadStatus.REQUESTED;
                }
            }
        }

        private Object pollQueuedMessage() {
            if (AbstractHttp2StreamChannel.this.inboundBuffer == null) {
                return null;
            }
            return AbstractHttp2StreamChannel.this.inboundBuffer.poll();
        }

        /* access modifiers changed from: package-private */
        public void doBeginRead() {
            boolean z;
            while (AbstractHttp2StreamChannel.this.readStatus != ReadStatus.IDLE) {
                Object pollQueuedMessage = pollQueuedMessage();
                if (pollQueuedMessage == null) {
                    if (this.readEOS) {
                        AbstractHttp2StreamChannel.this.unsafe.closeForcibly();
                    }
                    flush();
                    return;
                }
                RecvByteBufAllocator.Handle recvBufAllocHandle = recvBufAllocHandle();
                recvBufAllocHandle.reset(AbstractHttp2StreamChannel.this.config());
                boolean z2 = false;
                while (true) {
                    doRead0((Http2Frame) pollQueuedMessage, recvBufAllocHandle);
                    if (!this.readEOS) {
                        z = recvBufAllocHandle.continueReading();
                        if (!z) {
                            break;
                        }
                        z2 = z;
                    }
                    pollQueuedMessage = pollQueuedMessage();
                    if (pollQueuedMessage == null) {
                        z = z2;
                        break;
                    }
                }
                if (!z || !AbstractHttp2StreamChannel.this.isParentReadInProgress() || this.readEOS) {
                    notifyReadComplete(recvBufAllocHandle, true);
                } else {
                    AbstractHttp2StreamChannel.this.maybeAddChannelToReadCompletePendingQueue();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void readEOS() {
            this.readEOS = true;
        }

        private void updateLocalWindowIfNeeded() {
            if (AbstractHttp2StreamChannel.this.flowControlledBytes != 0) {
                int access$1600 = AbstractHttp2StreamChannel.this.flowControlledBytes;
                int unused = AbstractHttp2StreamChannel.this.flowControlledBytes = 0;
                AbstractHttp2StreamChannel abstractHttp2StreamChannel = AbstractHttp2StreamChannel.this;
                ChannelFuture write0 = abstractHttp2StreamChannel.write0(abstractHttp2StreamChannel.parentContext(), new DefaultHttp2WindowUpdateFrame(access$1600).stream((Http2FrameStream) AbstractHttp2StreamChannel.this.stream));
                this.writeDoneAndNoFlush = true;
                if (write0.isDone()) {
                    AbstractHttp2StreamChannel.windowUpdateFrameWriteComplete(write0, AbstractHttp2StreamChannel.this);
                } else {
                    write0.addListener(AbstractHttp2StreamChannel.this.windowUpdateFrameWriteListener);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void notifyReadComplete(RecvByteBufAllocator.Handle handle, boolean z) {
            if (AbstractHttp2StreamChannel.this.readCompletePending || z) {
                boolean unused = AbstractHttp2StreamChannel.this.readCompletePending = false;
                if (AbstractHttp2StreamChannel.this.readStatus == ReadStatus.REQUESTED) {
                    ReadStatus unused2 = AbstractHttp2StreamChannel.this.readStatus = ReadStatus.IN_PROGRESS;
                } else {
                    ReadStatus unused3 = AbstractHttp2StreamChannel.this.readStatus = ReadStatus.IDLE;
                }
                handle.readComplete();
                AbstractHttp2StreamChannel.this.pipeline().fireChannelReadComplete();
                flush();
                if (this.readEOS) {
                    AbstractHttp2StreamChannel.this.unsafe.closeForcibly();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void doRead0(Http2Frame http2Frame, RecvByteBufAllocator.Handle handle) {
            int i;
            if (http2Frame instanceof Http2DataFrame) {
                i = ((Http2DataFrame) http2Frame).initialFlowControlledBytes();
                AbstractHttp2StreamChannel abstractHttp2StreamChannel = AbstractHttp2StreamChannel.this;
                int unused = abstractHttp2StreamChannel.flowControlledBytes = abstractHttp2StreamChannel.flowControlledBytes + i;
            } else {
                i = 9;
            }
            handle.attemptedBytesRead(i);
            handle.lastBytesRead(i);
            handle.incMessagesRead(1);
            AbstractHttp2StreamChannel.this.pipeline().fireChannelRead(http2Frame);
        }

        public void write(Object obj, ChannelPromise channelPromise) {
            if (!channelPromise.setUncancellable()) {
                ReferenceCountUtil.release(obj);
            } else if (!AbstractHttp2StreamChannel.this.isActive() || (AbstractHttp2StreamChannel.this.outboundClosed && ((obj instanceof Http2HeadersFrame) || (obj instanceof Http2DataFrame)))) {
                ReferenceCountUtil.release(obj);
                channelPromise.setFailure(new ClosedChannelException());
            } else {
                try {
                    if (obj instanceof Http2StreamFrame) {
                        writeHttp2StreamFrame(validateStreamFrame((Http2StreamFrame) obj).stream(AbstractHttp2StreamChannel.this.stream()), channelPromise);
                        return;
                    }
                    String obj2 = obj.toString();
                    ReferenceCountUtil.release(obj);
                    channelPromise.setFailure(new IllegalArgumentException("Message must be an " + StringUtil.simpleClassName((Class<?>) Http2StreamFrame.class) + ": " + obj2));
                } catch (Throwable th) {
                    channelPromise.tryFailure(th);
                }
            }
        }

        private void writeHttp2StreamFrame(Http2StreamFrame http2StreamFrame, ChannelPromise channelPromise) {
            final boolean z;
            if (AbstractHttp2StreamChannel.this.firstFrameWritten || Http2CodecUtil.isStreamIdValid(AbstractHttp2StreamChannel.this.stream().id()) || (http2StreamFrame instanceof Http2HeadersFrame)) {
                if (AbstractHttp2StreamChannel.this.firstFrameWritten) {
                    z = false;
                } else {
                    z = AbstractHttp2StreamChannel.this.firstFrameWritten = true;
                }
                AbstractHttp2StreamChannel abstractHttp2StreamChannel = AbstractHttp2StreamChannel.this;
                ChannelFuture write0 = abstractHttp2StreamChannel.write0(abstractHttp2StreamChannel.parentContext(), http2StreamFrame);
                if (!write0.isDone()) {
                    final long size = (long) FlowControlledFrameSizeEstimator.HANDLE_INSTANCE.size(http2StreamFrame);
                    AbstractHttp2StreamChannel.this.incrementPendingOutboundBytes(size, false);
                    final ChannelPromise channelPromise2 = channelPromise;
                    write0.addListener(new ChannelFutureListener() {
                        public void operationComplete(ChannelFuture channelFuture) {
                            if (z) {
                                Http2ChannelUnsafe.this.firstWriteComplete(channelFuture, channelPromise2);
                            } else {
                                Http2ChannelUnsafe.this.writeComplete(channelFuture, channelPromise2);
                            }
                            AbstractHttp2StreamChannel.this.decrementPendingOutboundBytes(size, false);
                        }
                    });
                    this.writeDoneAndNoFlush = true;
                } else if (z) {
                    firstWriteComplete(write0, channelPromise);
                } else {
                    writeComplete(write0, channelPromise);
                }
            } else {
                ReferenceCountUtil.release(http2StreamFrame);
                channelPromise.setFailure(new IllegalArgumentException("The first frame must be a headers frame. Was: " + http2StreamFrame.name()));
            }
        }

        /* access modifiers changed from: private */
        public void firstWriteComplete(ChannelFuture channelFuture, ChannelPromise channelPromise) {
            Throwable cause = channelFuture.cause();
            if (cause == null) {
                channelPromise.setSuccess();
                return;
            }
            closeForcibly();
            channelPromise.setFailure(wrapStreamClosedError(cause));
        }

        /* access modifiers changed from: private */
        public void writeComplete(ChannelFuture channelFuture, ChannelPromise channelPromise) {
            Throwable cause = channelFuture.cause();
            if (cause == null) {
                channelPromise.setSuccess();
                return;
            }
            Throwable wrapStreamClosedError = wrapStreamClosedError(cause);
            if (wrapStreamClosedError instanceof IOException) {
                if (AbstractHttp2StreamChannel.this.config.isAutoClose()) {
                    closeForcibly();
                } else {
                    boolean unused = AbstractHttp2StreamChannel.this.outboundClosed = true;
                }
            }
            channelPromise.setFailure(wrapStreamClosedError);
        }

        private Throwable wrapStreamClosedError(Throwable th) {
            return (!(th instanceof Http2Exception) || ((Http2Exception) th).error() != Http2Error.STREAM_CLOSED) ? th : new ClosedChannelException().initCause(th);
        }

        private Http2StreamFrame validateStreamFrame(Http2StreamFrame http2StreamFrame) {
            if (http2StreamFrame.stream() == null || http2StreamFrame.stream() == AbstractHttp2StreamChannel.this.stream) {
                return http2StreamFrame;
            }
            String obj = http2StreamFrame.toString();
            ReferenceCountUtil.release(http2StreamFrame);
            throw new IllegalArgumentException("Stream " + http2StreamFrame.stream() + " must not be set on the frame: " + obj);
        }

        public void flush() {
            if (this.writeDoneAndNoFlush && !AbstractHttp2StreamChannel.this.isParentReadInProgress()) {
                this.writeDoneAndNoFlush = false;
                AbstractHttp2StreamChannel abstractHttp2StreamChannel = AbstractHttp2StreamChannel.this;
                abstractHttp2StreamChannel.flush0(abstractHttp2StreamChannel.parentContext());
            }
        }

        public ChannelPromise voidPromise() {
            return this.unsafeVoidPromise;
        }
    }

    /* renamed from: io.netty.handler.codec.http2.AbstractHttp2StreamChannel$5  reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http2$AbstractHttp2StreamChannel$ReadStatus;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                io.netty.handler.codec.http2.AbstractHttp2StreamChannel$ReadStatus[] r0 = io.netty.handler.codec.http2.AbstractHttp2StreamChannel.ReadStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$codec$http2$AbstractHttp2StreamChannel$ReadStatus = r0
                io.netty.handler.codec.http2.AbstractHttp2StreamChannel$ReadStatus r1 = io.netty.handler.codec.http2.AbstractHttp2StreamChannel.ReadStatus.IDLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$AbstractHttp2StreamChannel$ReadStatus     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.handler.codec.http2.AbstractHttp2StreamChannel$ReadStatus r1 = io.netty.handler.codec.http2.AbstractHttp2StreamChannel.ReadStatus.IN_PROGRESS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.AbstractHttp2StreamChannel.AnonymousClass5.<clinit>():void");
        }
    }

    private static final class Http2StreamChannelConfig extends DefaultChannelConfig {
        Http2StreamChannelConfig(Channel channel) {
            super(channel);
        }

        public MessageSizeEstimator getMessageSizeEstimator() {
            return FlowControlledFrameSizeEstimator.INSTANCE;
        }

        public ChannelConfig setMessageSizeEstimator(MessageSizeEstimator messageSizeEstimator) {
            throw new UnsupportedOperationException();
        }

        public ChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator recvByteBufAllocator) {
            if (recvByteBufAllocator.newHandle() instanceof RecvByteBufAllocator.ExtendedHandle) {
                super.setRecvByteBufAllocator(recvByteBufAllocator);
                return this;
            }
            throw new IllegalArgumentException("allocator.newHandle() must return an object of type: " + RecvByteBufAllocator.ExtendedHandle.class);
        }
    }

    /* access modifiers changed from: private */
    public void maybeAddChannelToReadCompletePendingQueue() {
        if (!this.readCompletePending) {
            this.readCompletePending = true;
            addChannelToReadCompletePendingQueue();
        }
    }

    /* access modifiers changed from: protected */
    public void flush0(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.flush();
    }

    /* access modifiers changed from: protected */
    public ChannelFuture write0(ChannelHandlerContext channelHandlerContext, Object obj) {
        ChannelPromise newPromise = channelHandlerContext.newPromise();
        channelHandlerContext.write(obj, newPromise);
        return newPromise;
    }
}
