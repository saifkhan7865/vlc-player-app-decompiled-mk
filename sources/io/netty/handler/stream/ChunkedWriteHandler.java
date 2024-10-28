package io.netty.handler.stream;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayDeque;
import java.util.Queue;

public class ChunkedWriteHandler extends ChannelDuplexHandler {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ChunkedWriteHandler.class);
    private volatile ChannelHandlerContext ctx;
    private final Queue<PendingWrite> queue = new ArrayDeque();

    public ChunkedWriteHandler() {
    }

    @Deprecated
    public ChunkedWriteHandler(int i) {
        ObjectUtil.checkPositive(i, "maxPendingWrites");
    }

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
    }

    public void resumeTransfer() {
        final ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext != null) {
            if (channelHandlerContext.executor().inEventLoop()) {
                resumeTransfer0(channelHandlerContext);
            } else {
                channelHandlerContext.executor().execute(new Runnable() {
                    public void run() {
                        ChunkedWriteHandler.this.resumeTransfer0(channelHandlerContext);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void resumeTransfer0(ChannelHandlerContext channelHandlerContext) {
        try {
            doFlush(channelHandlerContext);
        } catch (Exception e) {
            logger.warn("Unexpected exception while sending chunks.", (Throwable) e);
        }
    }

    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        this.queue.add(new PendingWrite(obj, channelPromise));
    }

    public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        doFlush(channelHandlerContext);
    }

    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        doFlush(channelHandlerContext);
        channelHandlerContext.fireChannelInactive();
    }

    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (channelHandlerContext.channel().isWritable()) {
            doFlush(channelHandlerContext);
        }
        channelHandlerContext.fireChannelWritabilityChanged();
    }

    private void discard(Throwable th) {
        while (true) {
            PendingWrite poll = this.queue.poll();
            if (poll != null) {
                Object obj = poll.msg;
                if (obj instanceof ChunkedInput) {
                    ChunkedInput chunkedInput = (ChunkedInput) obj;
                    try {
                        boolean isEndOfInput = chunkedInput.isEndOfInput();
                        long length = chunkedInput.length();
                        closeInput(chunkedInput);
                        if (!isEndOfInput) {
                            if (th == null) {
                                th = new ClosedChannelException();
                            }
                            poll.fail(th);
                        } else {
                            poll.success(length);
                        }
                    } catch (Exception e) {
                        closeInput(chunkedInput);
                        poll.fail(e);
                        InternalLogger internalLogger = logger;
                        if (internalLogger.isWarnEnabled()) {
                            Class<ChunkedInput> cls = ChunkedInput.class;
                            internalLogger.warn("ChunkedInput failed", (Throwable) e);
                        }
                    }
                } else {
                    if (th == null) {
                        th = new ClosedChannelException();
                    }
                    poll.fail(th);
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001b, code lost:
        r5 = r9.queue.peek();
     */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doFlush(io.netty.channel.ChannelHandlerContext r10) {
        /*
            r9 = this;
            io.netty.channel.Channel r0 = r10.channel()
            boolean r1 = r0.isActive()
            r2 = 0
            if (r1 != 0) goto L_0x000f
            r9.discard(r2)
            return
        L_0x000f:
            io.netty.buffer.ByteBufAllocator r1 = r10.alloc()
            r3 = 1
            r4 = 1
        L_0x0015:
            boolean r5 = r0.isWritable()
            if (r5 == 0) goto L_0x00b8
            java.util.Queue<io.netty.handler.stream.ChunkedWriteHandler$PendingWrite> r5 = r9.queue
            java.lang.Object r5 = r5.peek()
            io.netty.handler.stream.ChunkedWriteHandler$PendingWrite r5 = (io.netty.handler.stream.ChunkedWriteHandler.PendingWrite) r5
            if (r5 != 0) goto L_0x0027
            goto L_0x00b8
        L_0x0027:
            io.netty.channel.ChannelPromise r6 = r5.promise
            boolean r6 = r6.isDone()
            if (r6 == 0) goto L_0x0035
            java.util.Queue<io.netty.handler.stream.ChunkedWriteHandler$PendingWrite> r5 = r9.queue
            r5.remove()
            goto L_0x0015
        L_0x0035:
            java.lang.Object r6 = r5.msg
            boolean r7 = r6 instanceof io.netty.handler.stream.ChunkedInput
            if (r7 == 0) goto L_0x009f
            io.netty.handler.stream.ChunkedInput r6 = (io.netty.handler.stream.ChunkedInput) r6
            java.lang.Object r7 = r6.readChunk((io.netty.buffer.ByteBufAllocator) r1)     // Catch:{ all -> 0x008d }
            boolean r6 = r6.isEndOfInput()     // Catch:{ all -> 0x008a }
            if (r7 != 0) goto L_0x004d
            r8 = r6 ^ 1
            if (r8 == 0) goto L_0x004d
            goto L_0x00b8
        L_0x004d:
            if (r7 != 0) goto L_0x0051
            io.netty.buffer.ByteBuf r7 = io.netty.buffer.Unpooled.EMPTY_BUFFER
        L_0x0051:
            if (r6 == 0) goto L_0x0058
            java.util.Queue<io.netty.handler.stream.ChunkedWriteHandler$PendingWrite> r4 = r9.queue
            r4.remove()
        L_0x0058:
            io.netty.channel.ChannelFuture r4 = r10.writeAndFlush(r7)
            if (r6 == 0) goto L_0x0071
            boolean r6 = r4.isDone()
            if (r6 == 0) goto L_0x0068
            handleEndOfInputFuture(r4, r5)
            goto L_0x0088
        L_0x0068:
            io.netty.handler.stream.ChunkedWriteHandler$2 r6 = new io.netty.handler.stream.ChunkedWriteHandler$2
            r6.<init>(r5)
            r4.addListener(r6)
            goto L_0x0088
        L_0x0071:
            boolean r6 = r0.isWritable()
            r6 = r6 ^ r3
            boolean r7 = r4.isDone()
            if (r7 == 0) goto L_0x0080
            r9.handleFuture(r4, r5, r6)
            goto L_0x0088
        L_0x0080:
            io.netty.handler.stream.ChunkedWriteHandler$3 r7 = new io.netty.handler.stream.ChunkedWriteHandler$3
            r7.<init>(r5, r6)
            r4.addListener(r7)
        L_0x0088:
            r4 = 0
            goto L_0x00aa
        L_0x008a:
            r0 = move-exception
            r2 = r7
            goto L_0x008e
        L_0x008d:
            r0 = move-exception
        L_0x008e:
            java.util.Queue<io.netty.handler.stream.ChunkedWriteHandler$PendingWrite> r1 = r9.queue
            r1.remove()
            if (r2 == 0) goto L_0x0098
            io.netty.util.ReferenceCountUtil.release(r2)
        L_0x0098:
            closeInput(r6)
            r5.fail(r0)
            goto L_0x00b8
        L_0x009f:
            java.util.Queue<io.netty.handler.stream.ChunkedWriteHandler$PendingWrite> r4 = r9.queue
            r4.remove()
            io.netty.channel.ChannelPromise r4 = r5.promise
            r10.write(r6, r4)
            r4 = 1
        L_0x00aa:
            boolean r5 = r0.isActive()
            if (r5 != 0) goto L_0x0015
            java.nio.channels.ClosedChannelException r0 = new java.nio.channels.ClosedChannelException
            r0.<init>()
            r9.discard(r0)
        L_0x00b8:
            if (r4 == 0) goto L_0x00bd
            r10.flush()
        L_0x00bd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.stream.ChunkedWriteHandler.doFlush(io.netty.channel.ChannelHandlerContext):void");
    }

    /* access modifiers changed from: private */
    public static void handleEndOfInputFuture(ChannelFuture channelFuture, PendingWrite pendingWrite) {
        ChunkedInput chunkedInput = (ChunkedInput) pendingWrite.msg;
        if (!channelFuture.isSuccess()) {
            closeInput(chunkedInput);
            pendingWrite.fail(channelFuture.cause());
            return;
        }
        long progress = chunkedInput.progress();
        long length = chunkedInput.length();
        closeInput(chunkedInput);
        pendingWrite.progress(progress, length);
        pendingWrite.success(length);
    }

    /* access modifiers changed from: private */
    public void handleFuture(ChannelFuture channelFuture, PendingWrite pendingWrite, boolean z) {
        ChunkedInput chunkedInput = (ChunkedInput) pendingWrite.msg;
        if (!channelFuture.isSuccess()) {
            closeInput(chunkedInput);
            pendingWrite.fail(channelFuture.cause());
            return;
        }
        pendingWrite.progress(chunkedInput.progress(), chunkedInput.length());
        if (z && channelFuture.channel().isWritable()) {
            resumeTransfer();
        }
    }

    private static void closeInput(ChunkedInput<?> chunkedInput) {
        try {
            chunkedInput.close();
        } catch (Throwable th) {
            if (logger.isWarnEnabled()) {
                logger.warn("Failed to close a chunked input.", th);
            }
        }
    }

    private static final class PendingWrite {
        final Object msg;
        final ChannelPromise promise;

        PendingWrite(Object obj, ChannelPromise channelPromise) {
            this.msg = obj;
            this.promise = channelPromise;
        }

        /* access modifiers changed from: package-private */
        public void fail(Throwable th) {
            ReferenceCountUtil.release(this.msg);
            this.promise.tryFailure(th);
        }

        /* access modifiers changed from: package-private */
        public void success(long j) {
            if (!this.promise.isDone()) {
                progress(j, j);
                this.promise.trySuccess();
            }
        }

        /* access modifiers changed from: package-private */
        public void progress(long j, long j2) {
            ChannelPromise channelPromise = this.promise;
            if (channelPromise instanceof ChannelProgressivePromise) {
                ((ChannelProgressivePromise) channelPromise).tryProgress(j, j2);
            }
        }
    }
}
