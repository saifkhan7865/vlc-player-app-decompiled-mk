package io.netty.handler.codec.http2;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.Http2RemoteFlowController;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.handler.codec.http2.StreamByteDistributor;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayDeque;
import java.util.Deque;

public class DefaultHttp2RemoteFlowController implements Http2RemoteFlowController {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MIN_WRITABLE_CHUNK = 32768;
    /* access modifiers changed from: private */
    public static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) DefaultHttp2RemoteFlowController.class);
    /* access modifiers changed from: private */
    public final Http2Connection connection;
    /* access modifiers changed from: private */
    public final FlowState connectionState;
    /* access modifiers changed from: private */
    public ChannelHandlerContext ctx;
    /* access modifiers changed from: private */
    public int initialWindowSize;
    /* access modifiers changed from: private */
    public WritabilityMonitor monitor;
    /* access modifiers changed from: private */
    public final Http2Connection.PropertyKey stateKey;
    /* access modifiers changed from: private */
    public final StreamByteDistributor streamByteDistributor;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DefaultHttp2RemoteFlowController(Http2Connection http2Connection) {
        this(http2Connection, (Http2RemoteFlowController.Listener) null);
        Http2RemoteFlowController.Listener listener = null;
    }

    public DefaultHttp2RemoteFlowController(Http2Connection http2Connection, StreamByteDistributor streamByteDistributor2) {
        this(http2Connection, streamByteDistributor2, (Http2RemoteFlowController.Listener) null);
    }

    public DefaultHttp2RemoteFlowController(Http2Connection http2Connection, Http2RemoteFlowController.Listener listener) {
        this(http2Connection, new WeightedFairQueueByteDistributor(http2Connection), listener);
    }

    public DefaultHttp2RemoteFlowController(Http2Connection http2Connection, StreamByteDistributor streamByteDistributor2, Http2RemoteFlowController.Listener listener) {
        this.initialWindowSize = 65535;
        this.connection = (Http2Connection) ObjectUtil.checkNotNull(http2Connection, "connection");
        this.streamByteDistributor = (StreamByteDistributor) ObjectUtil.checkNotNull(streamByteDistributor2, "streamWriteDistributor");
        Http2Connection.PropertyKey newKey = http2Connection.newKey();
        this.stateKey = newKey;
        FlowState flowState = new FlowState(http2Connection.connectionStream());
        this.connectionState = flowState;
        http2Connection.connectionStream().setProperty(newKey, flowState);
        listener(listener);
        this.monitor.windowSize(flowState, this.initialWindowSize);
        http2Connection.addListener(new Http2ConnectionAdapter() {
            public void onStreamAdded(Http2Stream http2Stream) {
                http2Stream.setProperty(DefaultHttp2RemoteFlowController.this.stateKey, new FlowState(http2Stream));
            }

            public void onStreamActive(Http2Stream http2Stream) {
                DefaultHttp2RemoteFlowController.this.monitor.windowSize(DefaultHttp2RemoteFlowController.this.state(http2Stream), DefaultHttp2RemoteFlowController.this.initialWindowSize);
            }

            public void onStreamClosed(Http2Stream http2Stream) {
                DefaultHttp2RemoteFlowController.this.state(http2Stream).cancel(Http2Error.STREAM_CLOSED, (Throwable) null);
            }

            public void onStreamHalfClosed(Http2Stream http2Stream) {
                if (Http2Stream.State.HALF_CLOSED_LOCAL == http2Stream.state()) {
                    DefaultHttp2RemoteFlowController.this.state(http2Stream).cancel(Http2Error.STREAM_CLOSED, (Throwable) null);
                }
            }
        });
    }

    public void channelHandlerContext(ChannelHandlerContext channelHandlerContext) throws Http2Exception {
        this.ctx = (ChannelHandlerContext) ObjectUtil.checkNotNull(channelHandlerContext, "ctx");
        channelWritabilityChanged();
        if (isChannelWritable()) {
            writePendingBytes();
        }
    }

    public ChannelHandlerContext channelHandlerContext() {
        return this.ctx;
    }

    public void initialWindowSize(int i) throws Http2Exception {
        this.monitor.initialWindowSize(i);
    }

    public int initialWindowSize() {
        return this.initialWindowSize;
    }

    public int windowSize(Http2Stream http2Stream) {
        return state(http2Stream).windowSize();
    }

    public boolean isWritable(Http2Stream http2Stream) {
        return this.monitor.isWritable(state(http2Stream));
    }

    public void channelWritabilityChanged() throws Http2Exception {
        this.monitor.channelWritabilityChange();
    }

    public void updateDependencyTree(int i, int i2, short s, boolean z) {
        this.streamByteDistributor.updateDependencyTree(i, i2, s, z);
    }

    /* access modifiers changed from: private */
    public boolean isChannelWritable() {
        return this.ctx != null && isChannelWritable0();
    }

    /* access modifiers changed from: private */
    public boolean isChannelWritable0() {
        return this.ctx.channel().isWritable();
    }

    public void listener(Http2RemoteFlowController.Listener listener) {
        this.monitor = listener == null ? new WritabilityMonitor() : new ListenerWritabilityMonitor(listener);
    }

    public void incrementWindowSize(Http2Stream http2Stream, int i) throws Http2Exception {
        this.monitor.incrementWindowSize(state(http2Stream), i);
    }

    public void addFlowControlled(Http2Stream http2Stream, Http2RemoteFlowController.FlowControlled flowControlled) {
        ObjectUtil.checkNotNull(flowControlled, TypedValues.AttributesType.S_FRAME);
        try {
            this.monitor.enqueueFrame(state(http2Stream), flowControlled);
        } catch (Throwable th) {
            flowControlled.error(this.ctx, th);
        }
    }

    public boolean hasFlowControlled(Http2Stream http2Stream) {
        return state(http2Stream).hasFrame();
    }

    /* access modifiers changed from: private */
    public FlowState state(Http2Stream http2Stream) {
        return (FlowState) http2Stream.getProperty(this.stateKey);
    }

    /* access modifiers changed from: private */
    public int connectionWindowSize() {
        return this.connectionState.windowSize();
    }

    private int minUsableChannelBytes() {
        return Math.max(this.ctx.channel().config().getWriteBufferLowWaterMark(), 32768);
    }

    private int maxUsableChannelBytes() {
        int min = (int) Math.min(2147483647L, this.ctx.channel().bytesBeforeUnwritable());
        return Math.min(this.connectionState.windowSize(), min > 0 ? Math.max(min, minUsableChannelBytes()) : 0);
    }

    /* access modifiers changed from: private */
    public int writableBytes() {
        return Math.min(connectionWindowSize(), maxUsableChannelBytes());
    }

    public void writePendingBytes() throws Http2Exception {
        this.monitor.writePendingBytes();
    }

    private final class FlowState implements StreamByteDistributor.StreamState {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean cancelled;
        private boolean markedWritable;
        private long pendingBytes;
        private final Deque<Http2RemoteFlowController.FlowControlled> pendingWriteQueue = new ArrayDeque(2);
        /* access modifiers changed from: private */
        public final Http2Stream stream;
        private int window;
        private boolean writing;

        static {
            Class<DefaultHttp2RemoteFlowController> cls = DefaultHttp2RemoteFlowController.class;
        }

        FlowState(Http2Stream http2Stream) {
            this.stream = http2Stream;
        }

        /* access modifiers changed from: package-private */
        public boolean isWritable() {
            return ((long) windowSize()) > pendingBytes() && !this.cancelled;
        }

        public Http2Stream stream() {
            return this.stream;
        }

        /* access modifiers changed from: package-private */
        public boolean markedWritability() {
            return this.markedWritable;
        }

        /* access modifiers changed from: package-private */
        public void markedWritability(boolean z) {
            this.markedWritable = z;
        }

        public int windowSize() {
            return this.window;
        }

        /* access modifiers changed from: package-private */
        public void windowSize(int i) {
            this.window = i;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x008d A[DONT_GENERATE] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int writeAllocatedBytes(int r9) {
            /*
                r8 = this;
                r0 = 1
                r1 = 0
                r2 = 0
                r8.writing = r0     // Catch:{ all -> 0x007c }
                r3 = r9
                r4 = 0
            L_0x0007:
                boolean r5 = r8.cancelled     // Catch:{ all -> 0x007a }
                if (r5 != 0) goto L_0x0051
                io.netty.handler.codec.http2.Http2RemoteFlowController$FlowControlled r5 = r8.peek()     // Catch:{ all -> 0x007a }
                if (r5 == 0) goto L_0x0051
                int r6 = r8.writableWindow()     // Catch:{ all -> 0x007a }
                int r6 = java.lang.Math.min(r3, r6)     // Catch:{ all -> 0x007a }
                if (r6 > 0) goto L_0x0022
                int r7 = r5.size()     // Catch:{ all -> 0x007a }
                if (r7 <= 0) goto L_0x0022
                goto L_0x0051
            L_0x0022:
                int r4 = r5.size()     // Catch:{ all -> 0x007a }
                io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController r7 = io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController.this     // Catch:{ all -> 0x0049 }
                io.netty.channel.ChannelHandlerContext r7 = r7.ctx     // Catch:{ all -> 0x0049 }
                int r6 = java.lang.Math.max(r1, r6)     // Catch:{ all -> 0x0049 }
                r5.write(r7, r6)     // Catch:{ all -> 0x0049 }
                int r6 = r5.size()     // Catch:{ all -> 0x0049 }
                if (r6 != 0) goto L_0x0041
                java.util.Deque<io.netty.handler.codec.http2.Http2RemoteFlowController$FlowControlled> r6 = r8.pendingWriteQueue     // Catch:{ all -> 0x0049 }
                r6.remove()     // Catch:{ all -> 0x0049 }
                r5.writeComplete()     // Catch:{ all -> 0x0049 }
            L_0x0041:
                int r5 = r5.size()     // Catch:{ all -> 0x007a }
                int r4 = r4 - r5
                int r3 = r3 - r4
                r4 = 1
                goto L_0x0007
            L_0x0049:
                r6 = move-exception
                int r5 = r5.size()     // Catch:{ all -> 0x007a }
                int r4 = r4 - r5
                int r3 = r3 - r4
                throw r6     // Catch:{ all -> 0x007a }
            L_0x0051:
                if (r4 != 0) goto L_0x0067
                r8.writing = r1
                int r9 = r9 - r3
                r8.decrementPendingBytes(r9, r1)
                r8.decrementFlowControlWindow(r9)
                boolean r9 = r8.cancelled
                if (r9 == 0) goto L_0x0065
                io.netty.handler.codec.http2.Http2Error r9 = io.netty.handler.codec.http2.Http2Error.INTERNAL_ERROR
                r8.cancel(r9, r2)
            L_0x0065:
                r9 = -1
                return r9
            L_0x0067:
                r8.writing = r1
                int r9 = r9 - r3
                r8.decrementPendingBytes(r9, r1)
                r8.decrementFlowControlWindow(r9)
                boolean r0 = r8.cancelled
                if (r0 == 0) goto L_0x0092
                io.netty.handler.codec.http2.Http2Error r0 = io.netty.handler.codec.http2.Http2Error.INTERNAL_ERROR
                r8.cancel(r0, r2)
                goto L_0x0092
            L_0x007a:
                r4 = move-exception
                goto L_0x007e
            L_0x007c:
                r4 = move-exception
                r3 = r9
            L_0x007e:
                r8.cancelled = r0     // Catch:{ all -> 0x0093 }
                r8.writing = r1
                int r9 = r9 - r3
                r8.decrementPendingBytes(r9, r1)
                r8.decrementFlowControlWindow(r9)
                boolean r0 = r8.cancelled
                if (r0 == 0) goto L_0x0092
                io.netty.handler.codec.http2.Http2Error r0 = io.netty.handler.codec.http2.Http2Error.INTERNAL_ERROR
                r8.cancel(r0, r4)
            L_0x0092:
                return r9
            L_0x0093:
                r0 = move-exception
                r8.writing = r1
                int r9 = r9 - r3
                r8.decrementPendingBytes(r9, r1)
                r8.decrementFlowControlWindow(r9)
                boolean r9 = r8.cancelled
                if (r9 == 0) goto L_0x00a6
                io.netty.handler.codec.http2.Http2Error r9 = io.netty.handler.codec.http2.Http2Error.INTERNAL_ERROR
                r8.cancel(r9, r2)
            L_0x00a6:
                goto L_0x00a8
            L_0x00a7:
                throw r0
            L_0x00a8:
                goto L_0x00a7
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController.FlowState.writeAllocatedBytes(int):int");
        }

        /* access modifiers changed from: package-private */
        public int incrementStreamWindow(int i) throws Http2Exception {
            if (i <= 0 || Integer.MAX_VALUE - i >= this.window) {
                this.window += i;
                DefaultHttp2RemoteFlowController.this.streamByteDistributor.updateStreamableBytes(this);
                return this.window;
            }
            throw Http2Exception.streamError(this.stream.id(), Http2Error.FLOW_CONTROL_ERROR, "Window size overflow for stream: %d", Integer.valueOf(this.stream.id()));
        }

        private int writableWindow() {
            return Math.min(this.window, DefaultHttp2RemoteFlowController.this.connectionWindowSize());
        }

        public long pendingBytes() {
            return this.pendingBytes;
        }

        /* access modifiers changed from: package-private */
        public void enqueueFrame(Http2RemoteFlowController.FlowControlled flowControlled) {
            Http2RemoteFlowController.FlowControlled peekLast = this.pendingWriteQueue.peekLast();
            if (peekLast == null) {
                enqueueFrameWithoutMerge(flowControlled);
                return;
            }
            int size = peekLast.size();
            if (peekLast.merge(DefaultHttp2RemoteFlowController.this.ctx, flowControlled)) {
                incrementPendingBytes(peekLast.size() - size, true);
            } else {
                enqueueFrameWithoutMerge(flowControlled);
            }
        }

        private void enqueueFrameWithoutMerge(Http2RemoteFlowController.FlowControlled flowControlled) {
            this.pendingWriteQueue.offer(flowControlled);
            incrementPendingBytes(flowControlled.size(), true);
        }

        public boolean hasFrame() {
            return !this.pendingWriteQueue.isEmpty();
        }

        private Http2RemoteFlowController.FlowControlled peek() {
            return this.pendingWriteQueue.peek();
        }

        /* access modifiers changed from: package-private */
        public void cancel(Http2Error http2Error, Throwable th) {
            this.cancelled = true;
            if (!this.writing) {
                Http2RemoteFlowController.FlowControlled poll = this.pendingWriteQueue.poll();
                if (poll != null) {
                    Http2Exception streamError = Http2Exception.streamError(this.stream.id(), http2Error, th, "Stream closed before write could take place", new Object[0]);
                    do {
                        writeError(poll, streamError);
                        poll = this.pendingWriteQueue.poll();
                    } while (poll != null);
                }
                DefaultHttp2RemoteFlowController.this.streamByteDistributor.updateStreamableBytes(this);
                DefaultHttp2RemoteFlowController.this.monitor.stateCancelled(this);
            }
        }

        private void incrementPendingBytes(int i, boolean z) {
            this.pendingBytes += (long) i;
            DefaultHttp2RemoteFlowController.this.monitor.incrementPendingBytes(i);
            if (z) {
                DefaultHttp2RemoteFlowController.this.streamByteDistributor.updateStreamableBytes(this);
            }
        }

        private void decrementPendingBytes(int i, boolean z) {
            incrementPendingBytes(-i, z);
        }

        private void decrementFlowControlWindow(int i) {
            int i2 = -i;
            try {
                DefaultHttp2RemoteFlowController.this.connectionState.incrementStreamWindow(i2);
                incrementStreamWindow(i2);
            } catch (Http2Exception e) {
                throw new IllegalStateException("Invalid window state when writing frame: " + e.getMessage(), e);
            }
        }

        private void writeError(Http2RemoteFlowController.FlowControlled flowControlled, Http2Exception http2Exception) {
            decrementPendingBytes(flowControlled.size(), true);
            flowControlled.error(DefaultHttp2RemoteFlowController.this.ctx, http2Exception);
        }
    }

    private class WritabilityMonitor implements StreamByteDistributor.Writer {
        private boolean inWritePendingBytes;
        private long totalPendingBytes;

        /* access modifiers changed from: package-private */
        public void channelWritabilityChange() throws Http2Exception {
        }

        /* access modifiers changed from: package-private */
        public void stateCancelled(FlowState flowState) {
        }

        private WritabilityMonitor() {
        }

        public final void write(Http2Stream http2Stream, int i) {
            DefaultHttp2RemoteFlowController.this.state(http2Stream).writeAllocatedBytes(i);
        }

        /* access modifiers changed from: package-private */
        public void windowSize(FlowState flowState, int i) {
            flowState.windowSize(i);
        }

        /* access modifiers changed from: package-private */
        public void incrementWindowSize(FlowState flowState, int i) throws Http2Exception {
            flowState.incrementStreamWindow(i);
        }

        /* access modifiers changed from: package-private */
        public void enqueueFrame(FlowState flowState, Http2RemoteFlowController.FlowControlled flowControlled) throws Http2Exception {
            flowState.enqueueFrame(flowControlled);
        }

        /* access modifiers changed from: package-private */
        public final void incrementPendingBytes(int i) {
            this.totalPendingBytes += (long) i;
        }

        /* access modifiers changed from: package-private */
        public final boolean isWritable(FlowState flowState) {
            return isWritableConnection() && flowState.isWritable();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:6:0x000f A[Catch:{ all -> 0x002e }, LOOP:0: B:6:0x000f->B:11:0x0029, LOOP_START, PHI: r1 
          PHI: (r1v3 int) = (r1v2 int), (r1v6 int) binds: [B:5:?, B:11:0x0029] A[DONT_GENERATE, DONT_INLINE]] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void writePendingBytes() throws io.netty.handler.codec.http2.Http2Exception {
            /*
                r3 = this;
                boolean r0 = r3.inWritePendingBytes
                if (r0 == 0) goto L_0x0005
                return
            L_0x0005:
                r0 = 1
                r3.inWritePendingBytes = r0
                r0 = 0
                io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController r1 = io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController.this     // Catch:{ all -> 0x002e }
                int r1 = r1.writableBytes()     // Catch:{ all -> 0x002e }
            L_0x000f:
                io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController r2 = io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController.this     // Catch:{ all -> 0x002e }
                io.netty.handler.codec.http2.StreamByteDistributor r2 = r2.streamByteDistributor     // Catch:{ all -> 0x002e }
                boolean r1 = r2.distribute(r1, r3)     // Catch:{ all -> 0x002e }
                if (r1 == 0) goto L_0x002b
                io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController r1 = io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController.this     // Catch:{ all -> 0x002e }
                int r1 = r1.writableBytes()     // Catch:{ all -> 0x002e }
                if (r1 <= 0) goto L_0x002b
                io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController r2 = io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController.this     // Catch:{ all -> 0x002e }
                boolean r2 = r2.isChannelWritable0()     // Catch:{ all -> 0x002e }
                if (r2 != 0) goto L_0x000f
            L_0x002b:
                r3.inWritePendingBytes = r0
                return
            L_0x002e:
                r1 = move-exception
                r3.inWritePendingBytes = r0
                goto L_0x0033
            L_0x0032:
                throw r1
            L_0x0033:
                goto L_0x0032
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController.WritabilityMonitor.writePendingBytes():void");
        }

        /* access modifiers changed from: package-private */
        public void initialWindowSize(int i) throws Http2Exception {
            ObjectUtil.checkPositiveOrZero(i, "newWindowSize");
            final int access$200 = i - DefaultHttp2RemoteFlowController.this.initialWindowSize;
            int unused = DefaultHttp2RemoteFlowController.this.initialWindowSize = i;
            DefaultHttp2RemoteFlowController.this.connection.forEachActiveStream(new Http2StreamVisitor() {
                public boolean visit(Http2Stream http2Stream) throws Http2Exception {
                    DefaultHttp2RemoteFlowController.this.state(http2Stream).incrementStreamWindow(access$200);
                    return true;
                }
            });
            if (access$200 > 0 && DefaultHttp2RemoteFlowController.this.isChannelWritable()) {
                writePendingBytes();
            }
        }

        /* access modifiers changed from: package-private */
        public final boolean isWritableConnection() {
            return ((long) DefaultHttp2RemoteFlowController.this.connectionState.windowSize()) - this.totalPendingBytes > 0 && DefaultHttp2RemoteFlowController.this.isChannelWritable();
        }
    }

    private final class ListenerWritabilityMonitor extends WritabilityMonitor implements Http2StreamVisitor {
        private final Http2RemoteFlowController.Listener listener;

        ListenerWritabilityMonitor(Http2RemoteFlowController.Listener listener2) {
            super();
            this.listener = listener2;
        }

        public boolean visit(Http2Stream http2Stream) throws Http2Exception {
            FlowState access$100 = DefaultHttp2RemoteFlowController.this.state(http2Stream);
            if (isWritable(access$100) == access$100.markedWritability()) {
                return true;
            }
            notifyWritabilityChanged(access$100);
            return true;
        }

        /* access modifiers changed from: package-private */
        public void windowSize(FlowState flowState, int i) {
            super.windowSize(flowState, i);
            try {
                checkStateWritability(flowState);
            } catch (Http2Exception e) {
                throw new RuntimeException("Caught unexpected exception from window", e);
            }
        }

        /* access modifiers changed from: package-private */
        public void incrementWindowSize(FlowState flowState, int i) throws Http2Exception {
            super.incrementWindowSize(flowState, i);
            checkStateWritability(flowState);
        }

        /* access modifiers changed from: package-private */
        public void initialWindowSize(int i) throws Http2Exception {
            super.initialWindowSize(i);
            if (isWritableConnection()) {
                checkAllWritabilityChanged();
            }
        }

        /* access modifiers changed from: package-private */
        public void enqueueFrame(FlowState flowState, Http2RemoteFlowController.FlowControlled flowControlled) throws Http2Exception {
            super.enqueueFrame(flowState, flowControlled);
            checkConnectionThenStreamWritabilityChanged(flowState);
        }

        /* access modifiers changed from: package-private */
        public void stateCancelled(FlowState flowState) {
            try {
                checkConnectionThenStreamWritabilityChanged(flowState);
            } catch (Http2Exception e) {
                throw new RuntimeException("Caught unexpected exception from checkAllWritabilityChanged", e);
            }
        }

        /* access modifiers changed from: package-private */
        public void channelWritabilityChange() throws Http2Exception {
            if (DefaultHttp2RemoteFlowController.this.connectionState.markedWritability() != DefaultHttp2RemoteFlowController.this.isChannelWritable()) {
                checkAllWritabilityChanged();
            }
        }

        private void checkStateWritability(FlowState flowState) throws Http2Exception {
            if (isWritable(flowState) == flowState.markedWritability()) {
                return;
            }
            if (flowState == DefaultHttp2RemoteFlowController.this.connectionState) {
                checkAllWritabilityChanged();
            } else {
                notifyWritabilityChanged(flowState);
            }
        }

        private void notifyWritabilityChanged(FlowState flowState) {
            flowState.markedWritability(!flowState.markedWritability());
            try {
                this.listener.writabilityChanged(flowState.stream);
            } catch (Throwable th) {
                DefaultHttp2RemoteFlowController.logger.error("Caught Throwable from listener.writabilityChanged", th);
            }
        }

        private void checkConnectionThenStreamWritabilityChanged(FlowState flowState) throws Http2Exception {
            if (isWritableConnection() != DefaultHttp2RemoteFlowController.this.connectionState.markedWritability()) {
                checkAllWritabilityChanged();
            } else if (isWritable(flowState) != flowState.markedWritability()) {
                notifyWritabilityChanged(flowState);
            }
        }

        private void checkAllWritabilityChanged() throws Http2Exception {
            DefaultHttp2RemoteFlowController.this.connectionState.markedWritability(isWritableConnection());
            DefaultHttp2RemoteFlowController.this.connection.forEachActiveStream(this);
        }
    }
}
