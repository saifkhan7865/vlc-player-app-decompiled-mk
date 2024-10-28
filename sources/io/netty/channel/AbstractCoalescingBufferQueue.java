package io.netty.channel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayDeque;

public abstract class AbstractCoalescingBufferQueue {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AbstractCoalescingBufferQueue.class);
    private final ArrayDeque<Object> bufAndListenerPairs;
    private int readableBytes;
    private final PendingBytesTracker tracker;

    /* access modifiers changed from: protected */
    public abstract ByteBuf compose(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, ByteBuf byteBuf2);

    /* access modifiers changed from: protected */
    public ByteBuf composeFirst(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf) {
        return byteBuf;
    }

    /* access modifiers changed from: protected */
    public abstract ByteBuf removeEmptyValue();

    protected AbstractCoalescingBufferQueue(Channel channel, int i) {
        PendingBytesTracker pendingBytesTracker;
        this.bufAndListenerPairs = new ArrayDeque<>(i);
        if (channel == null) {
            pendingBytesTracker = null;
        } else {
            pendingBytesTracker = PendingBytesTracker.newTracker(channel);
        }
        this.tracker = pendingBytesTracker;
    }

    public final void addFirst(ByteBuf byteBuf, ChannelPromise channelPromise) {
        addFirst(byteBuf, toChannelFutureListener(channelPromise));
    }

    private void addFirst(ByteBuf byteBuf, ChannelFutureListener channelFutureListener) {
        if (channelFutureListener != null) {
            this.bufAndListenerPairs.addFirst(channelFutureListener);
        }
        this.bufAndListenerPairs.addFirst(byteBuf);
        incrementReadableBytes(byteBuf.readableBytes());
    }

    public final void add(ByteBuf byteBuf) {
        ChannelFutureListener channelFutureListener = null;
        add(byteBuf, (ChannelFutureListener) null);
    }

    public final void add(ByteBuf byteBuf, ChannelPromise channelPromise) {
        add(byteBuf, toChannelFutureListener(channelPromise));
    }

    public final void add(ByteBuf byteBuf, ChannelFutureListener channelFutureListener) {
        this.bufAndListenerPairs.add(byteBuf);
        if (channelFutureListener != null) {
            this.bufAndListenerPairs.add(channelFutureListener);
        }
        incrementReadableBytes(byteBuf.readableBytes());
    }

    public final ByteBuf removeFirst(ChannelPromise channelPromise) {
        Object poll = this.bufAndListenerPairs.poll();
        if (poll == null) {
            return null;
        }
        ByteBuf byteBuf = (ByteBuf) poll;
        decrementReadableBytes(byteBuf.readableBytes());
        Object peek = this.bufAndListenerPairs.peek();
        if (peek instanceof ChannelFutureListener) {
            channelPromise.addListener((ChannelFutureListener) peek);
            this.bufAndListenerPairs.poll();
        }
        return byteBuf;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003b, code lost:
        r6.bufAndListenerPairs.addFirst(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0040, code lost:
        if (r1 <= 0) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0042, code lost:
        r0 = r3.readRetainedSlice(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0046, code lost:
        if (r2 != null) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r0 = compose(r7, r2, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004d, code lost:
        r1 = 0;
        r2 = r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final io.netty.buffer.ByteBuf remove(io.netty.buffer.ByteBufAllocator r7, int r8, io.netty.channel.ChannelPromise r9) {
        /*
            r6 = this;
            java.lang.String r0 = "bytes"
            io.netty.util.internal.ObjectUtil.checkPositiveOrZero((int) r8, (java.lang.String) r0)
            java.lang.String r0 = "aggregatePromise"
            io.netty.util.internal.ObjectUtil.checkNotNull(r9, r0)
            java.util.ArrayDeque<java.lang.Object> r0 = r6.bufAndListenerPairs
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0017
            io.netty.buffer.ByteBuf r7 = r6.removeEmptyValue()
            return r7
        L_0x0017:
            int r0 = r6.readableBytes
            int r8 = java.lang.Math.min(r8, r0)
            r0 = 0
            r1 = r8
            r2 = r0
        L_0x0020:
            java.util.ArrayDeque<java.lang.Object> r3 = r6.bufAndListenerPairs     // Catch:{ all -> 0x0066 }
            java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x0066 }
            if (r3 != 0) goto L_0x0029
            goto L_0x0073
        L_0x0029:
            boolean r4 = r3 instanceof io.netty.channel.ChannelFutureListener     // Catch:{ all -> 0x0066 }
            if (r4 == 0) goto L_0x0033
            io.netty.channel.ChannelFutureListener r3 = (io.netty.channel.ChannelFutureListener) r3     // Catch:{ all -> 0x0066 }
            r9.addListener(r3)     // Catch:{ all -> 0x0066 }
            goto L_0x0020
        L_0x0033:
            io.netty.buffer.ByteBuf r3 = (io.netty.buffer.ByteBuf) r3     // Catch:{ all -> 0x0066 }
            int r4 = r3.readableBytes()     // Catch:{ all -> 0x0063 }
            if (r4 <= r1) goto L_0x0050
            java.util.ArrayDeque<java.lang.Object> r0 = r6.bufAndListenerPairs     // Catch:{ all -> 0x0063 }
            r0.addFirst(r3)     // Catch:{ all -> 0x0063 }
            if (r1 <= 0) goto L_0x0073
            io.netty.buffer.ByteBuf r0 = r3.readRetainedSlice(r1)     // Catch:{ all -> 0x0063 }
            if (r2 != 0) goto L_0x0049
            goto L_0x004d
        L_0x0049:
            io.netty.buffer.ByteBuf r0 = r6.compose(r7, r2, r0)     // Catch:{ all -> 0x0066 }
        L_0x004d:
            r1 = 0
            r2 = r0
            goto L_0x0073
        L_0x0050:
            int r1 = r1 - r4
            if (r2 != 0) goto L_0x005e
            int r5 = r6.readableBytes     // Catch:{ all -> 0x0063 }
            if (r4 != r5) goto L_0x0058
            goto L_0x005c
        L_0x0058:
            io.netty.buffer.ByteBuf r3 = r6.composeFirst(r7, r3)     // Catch:{ all -> 0x0063 }
        L_0x005c:
            r2 = r3
            goto L_0x0020
        L_0x005e:
            io.netty.buffer.ByteBuf r2 = r6.compose(r7, r2, r3)     // Catch:{ all -> 0x0063 }
            goto L_0x0020
        L_0x0063:
            r7 = move-exception
            r0 = r3
            goto L_0x0067
        L_0x0066:
            r7 = move-exception
        L_0x0067:
            io.netty.util.ReferenceCountUtil.safeRelease(r0)
            io.netty.util.ReferenceCountUtil.safeRelease(r2)
            r9.setFailure(r7)
            io.netty.util.internal.PlatformDependent.throwException(r7)
        L_0x0073:
            int r8 = r8 - r1
            r6.decrementReadableBytes(r8)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.AbstractCoalescingBufferQueue.remove(io.netty.buffer.ByteBufAllocator, int, io.netty.channel.ChannelPromise):io.netty.buffer.ByteBuf");
    }

    public final int readableBytes() {
        return this.readableBytes;
    }

    public final boolean isEmpty() {
        return this.bufAndListenerPairs.isEmpty();
    }

    public final void releaseAndFailAll(ChannelOutboundInvoker channelOutboundInvoker, Throwable th) {
        releaseAndCompleteAll(channelOutboundInvoker.newFailedFuture(th));
    }

    public final void copyTo(AbstractCoalescingBufferQueue abstractCoalescingBufferQueue) {
        abstractCoalescingBufferQueue.bufAndListenerPairs.addAll(this.bufAndListenerPairs);
        abstractCoalescingBufferQueue.incrementReadableBytes(this.readableBytes);
    }

    public final void writeAndRemoveAll(ChannelHandlerContext channelHandlerContext) {
        ByteBuf byteBuf = null;
        Throwable th = null;
        while (true) {
            Object poll = this.bufAndListenerPairs.poll();
            if (poll == null) {
                if (byteBuf == null) {
                    break;
                }
                try {
                    decrementReadableBytes(byteBuf.readableBytes());
                    channelHandlerContext.write(byteBuf, channelHandlerContext.voidPromise());
                    break;
                } catch (Throwable th2) {
                    if (th == null) {
                        th = th2;
                    } else {
                        logger.info("Throwable being suppressed because Throwable {} is already pending", th, th2);
                    }
                }
            } else if (poll instanceof ByteBuf) {
                if (byteBuf != null) {
                    decrementReadableBytes(byteBuf.readableBytes());
                    channelHandlerContext.write(byteBuf, channelHandlerContext.voidPromise());
                }
                byteBuf = (ByteBuf) poll;
            } else {
                if (poll instanceof ChannelPromise) {
                    decrementReadableBytes(byteBuf.readableBytes());
                    channelHandlerContext.write(byteBuf, (ChannelPromise) poll);
                } else {
                    decrementReadableBytes(byteBuf.readableBytes());
                    channelHandlerContext.write(byteBuf).addListener((ChannelFutureListener) poll);
                }
                byteBuf = null;
            }
        }
        if (th != null) {
            throw new IllegalStateException(th);
        }
    }

    public String toString() {
        return "bytes: " + this.readableBytes + " buffers: " + (size() >> 1);
    }

    /* access modifiers changed from: protected */
    public final ByteBuf composeIntoComposite(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, ByteBuf byteBuf2) {
        CompositeByteBuf compositeBuffer = byteBufAllocator.compositeBuffer(size() + 2);
        try {
            compositeBuffer.addComponent(true, byteBuf);
            compositeBuffer.addComponent(true, byteBuf2);
        } catch (Throwable th) {
            compositeBuffer.release();
            ReferenceCountUtil.safeRelease(byteBuf2);
            PlatformDependent.throwException(th);
        }
        return compositeBuffer;
    }

    /* access modifiers changed from: protected */
    public final ByteBuf copyAndCompose(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, ByteBuf byteBuf2) {
        ByteBuf ioBuffer = byteBufAllocator.ioBuffer(byteBuf.readableBytes() + byteBuf2.readableBytes());
        try {
            ioBuffer.writeBytes(byteBuf).writeBytes(byteBuf2);
        } catch (Throwable th) {
            ioBuffer.release();
            ReferenceCountUtil.safeRelease(byteBuf2);
            PlatformDependent.throwException(th);
        }
        byteBuf.release();
        byteBuf2.release();
        return ioBuffer;
    }

    /* access modifiers changed from: protected */
    public final int size() {
        return this.bufAndListenerPairs.size();
    }

    private void releaseAndCompleteAll(ChannelFuture channelFuture) {
        Throwable th = null;
        while (true) {
            Object poll = this.bufAndListenerPairs.poll();
            if (poll == null) {
                break;
            }
            try {
                if (poll instanceof ByteBuf) {
                    ByteBuf byteBuf = (ByteBuf) poll;
                    decrementReadableBytes(byteBuf.readableBytes());
                    ReferenceCountUtil.safeRelease(byteBuf);
                } else {
                    ((ChannelFutureListener) poll).operationComplete(channelFuture);
                }
            } catch (Throwable th2) {
                if (th == null) {
                    th = th2;
                } else {
                    logger.info("Throwable being suppressed because Throwable {} is already pending", th, th2);
                }
            }
        }
        if (th != null) {
            throw new IllegalStateException(th);
        }
    }

    private void incrementReadableBytes(int i) {
        int i2 = this.readableBytes;
        int i3 = i2 + i;
        if (i3 >= i2) {
            this.readableBytes = i3;
            PendingBytesTracker pendingBytesTracker = this.tracker;
            if (pendingBytesTracker != null) {
                pendingBytesTracker.incrementPendingOutboundBytes((long) i);
                return;
            }
            return;
        }
        throw new IllegalStateException("buffer queue length overflow: " + this.readableBytes + " + " + i);
    }

    private void decrementReadableBytes(int i) {
        this.readableBytes -= i;
        PendingBytesTracker pendingBytesTracker = this.tracker;
        if (pendingBytesTracker != null) {
            pendingBytesTracker.decrementPendingOutboundBytes((long) i);
        }
    }

    private static ChannelFutureListener toChannelFutureListener(ChannelPromise channelPromise) {
        if (channelPromise.isVoid()) {
            return null;
        }
        return new DelegatingChannelPromiseNotifier(channelPromise);
    }
}
