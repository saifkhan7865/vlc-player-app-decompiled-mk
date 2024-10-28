package io.netty.channel.nio;

import io.netty.channel.Channel;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.AbstractNioChannel;
import java.io.IOException;
import java.net.PortUnreachableException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNioMessageChannel extends AbstractNioChannel {
    boolean inputShutdown;

    /* access modifiers changed from: protected */
    public boolean continueOnWriteError() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract int doReadMessages(List<Object> list) throws Exception;

    /* access modifiers changed from: protected */
    public abstract boolean doWriteMessage(Object obj, ChannelOutboundBuffer channelOutboundBuffer) throws Exception;

    protected AbstractNioMessageChannel(Channel channel, SelectableChannel selectableChannel, int i) {
        super(channel, selectableChannel, i);
    }

    /* access modifiers changed from: protected */
    public AbstractNioChannel.AbstractNioUnsafe newUnsafe() {
        return new NioMessageUnsafe();
    }

    /* access modifiers changed from: protected */
    public void doBeginRead() throws Exception {
        if (!this.inputShutdown) {
            super.doBeginRead();
        }
    }

    /* access modifiers changed from: protected */
    public boolean continueReading(RecvByteBufAllocator.Handle handle) {
        return handle.continueReading();
    }

    private final class NioMessageUnsafe extends AbstractNioChannel.AbstractNioUnsafe {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final List<Object> readBuf;

        static {
            Class<AbstractNioMessageChannel> cls = AbstractNioMessageChannel.class;
        }

        private NioMessageUnsafe() {
            super();
            this.readBuf = new ArrayList();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0035, code lost:
            r5 = false;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void read() {
            /*
                r10 = this;
                io.netty.channel.nio.AbstractNioMessageChannel r0 = io.netty.channel.nio.AbstractNioMessageChannel.this
                io.netty.channel.ChannelConfig r0 = r0.config()
                io.netty.channel.nio.AbstractNioMessageChannel r1 = io.netty.channel.nio.AbstractNioMessageChannel.this
                io.netty.channel.ChannelPipeline r1 = r1.pipeline()
                io.netty.channel.nio.AbstractNioMessageChannel r2 = io.netty.channel.nio.AbstractNioMessageChannel.this
                io.netty.channel.nio.AbstractNioChannel$NioUnsafe r2 = r2.unsafe()
                io.netty.channel.RecvByteBufAllocator$Handle r2 = r2.recvBufAllocHandle()
                r2.reset(r0)
            L_0x0019:
                r3 = 1
                r4 = 0
                io.netty.channel.nio.AbstractNioMessageChannel r5 = io.netty.channel.nio.AbstractNioMessageChannel.this     // Catch:{ all -> 0x0038 }
                java.util.List<java.lang.Object> r6 = r10.readBuf     // Catch:{ all -> 0x0038 }
                int r5 = r5.doReadMessages(r6)     // Catch:{ all -> 0x0038 }
                if (r5 != 0) goto L_0x0026
                goto L_0x0035
            L_0x0026:
                if (r5 >= 0) goto L_0x002a
                r5 = 1
                goto L_0x0036
            L_0x002a:
                r2.incMessagesRead(r5)     // Catch:{ all -> 0x0038 }
                io.netty.channel.nio.AbstractNioMessageChannel r5 = io.netty.channel.nio.AbstractNioMessageChannel.this     // Catch:{ all -> 0x0038 }
                boolean r5 = r5.continueReading(r2)     // Catch:{ all -> 0x0038 }
                if (r5 != 0) goto L_0x0019
            L_0x0035:
                r5 = 0
            L_0x0036:
                r6 = 0
                goto L_0x003b
            L_0x0038:
                r5 = move-exception
                r6 = r5
                r5 = 0
            L_0x003b:
                java.util.List<java.lang.Object> r7 = r10.readBuf     // Catch:{ all -> 0x008f }
                int r7 = r7.size()     // Catch:{ all -> 0x008f }
                r8 = 0
            L_0x0042:
                if (r8 >= r7) goto L_0x0054
                io.netty.channel.nio.AbstractNioMessageChannel r9 = io.netty.channel.nio.AbstractNioMessageChannel.this     // Catch:{ all -> 0x008f }
                r9.readPending = r4     // Catch:{ all -> 0x008f }
                java.util.List<java.lang.Object> r9 = r10.readBuf     // Catch:{ all -> 0x008f }
                java.lang.Object r9 = r9.get(r8)     // Catch:{ all -> 0x008f }
                r1.fireChannelRead(r9)     // Catch:{ all -> 0x008f }
                int r8 = r8 + 1
                goto L_0x0042
            L_0x0054:
                java.util.List<java.lang.Object> r4 = r10.readBuf     // Catch:{ all -> 0x008f }
                r4.clear()     // Catch:{ all -> 0x008f }
                r2.readComplete()     // Catch:{ all -> 0x008f }
                r1.fireChannelReadComplete()     // Catch:{ all -> 0x008f }
                if (r6 == 0) goto L_0x006a
                io.netty.channel.nio.AbstractNioMessageChannel r2 = io.netty.channel.nio.AbstractNioMessageChannel.this     // Catch:{ all -> 0x008f }
                boolean r5 = r2.closeOnReadError(r6)     // Catch:{ all -> 0x008f }
                r1.fireExceptionCaught(r6)     // Catch:{ all -> 0x008f }
            L_0x006a:
                if (r5 == 0) goto L_0x007f
                io.netty.channel.nio.AbstractNioMessageChannel r1 = io.netty.channel.nio.AbstractNioMessageChannel.this     // Catch:{ all -> 0x008f }
                r1.inputShutdown = r3     // Catch:{ all -> 0x008f }
                io.netty.channel.nio.AbstractNioMessageChannel r1 = io.netty.channel.nio.AbstractNioMessageChannel.this     // Catch:{ all -> 0x008f }
                boolean r1 = r1.isOpen()     // Catch:{ all -> 0x008f }
                if (r1 == 0) goto L_0x007f
                io.netty.channel.ChannelPromise r1 = r10.voidPromise()     // Catch:{ all -> 0x008f }
                r10.close(r1)     // Catch:{ all -> 0x008f }
            L_0x007f:
                io.netty.channel.nio.AbstractNioMessageChannel r1 = io.netty.channel.nio.AbstractNioMessageChannel.this
                boolean r1 = r1.readPending
                if (r1 != 0) goto L_0x008e
                boolean r0 = r0.isAutoRead()
                if (r0 != 0) goto L_0x008e
                r10.removeReadOp()
            L_0x008e:
                return
            L_0x008f:
                r1 = move-exception
                io.netty.channel.nio.AbstractNioMessageChannel r2 = io.netty.channel.nio.AbstractNioMessageChannel.this
                boolean r2 = r2.readPending
                if (r2 != 0) goto L_0x009f
                boolean r0 = r0.isAutoRead()
                if (r0 != 0) goto L_0x009f
                r10.removeReadOp()
            L_0x009f:
                goto L_0x00a1
            L_0x00a0:
                throw r1
            L_0x00a1:
                goto L_0x00a0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.nio.AbstractNioMessageChannel.NioMessageUnsafe.read():void");
        }
    }

    /* access modifiers changed from: protected */
    public void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        SelectionKey selectionKey = selectionKey();
        int interestOps = selectionKey.interestOps();
        int maxMessagesPerWrite = maxMessagesPerWrite();
        loop0:
        while (maxMessagesPerWrite > 0) {
            Object current = channelOutboundBuffer.current();
            if (current == null) {
                break;
            }
            try {
                int writeSpinCount = config().getWriteSpinCount() - 1;
                while (writeSpinCount >= 0) {
                    if (doWriteMessage(current, channelOutboundBuffer)) {
                        maxMessagesPerWrite--;
                        channelOutboundBuffer.remove();
                    } else {
                        writeSpinCount--;
                    }
                }
                break loop0;
            } catch (Exception e) {
                if (continueOnWriteError()) {
                    maxMessagesPerWrite--;
                    channelOutboundBuffer.remove(e);
                } else {
                    throw e;
                }
            }
        }
        if (channelOutboundBuffer.isEmpty()) {
            if ((interestOps & 4) != 0) {
                selectionKey.interestOps(interestOps & -5);
            }
        } else if ((interestOps & 4) == 0) {
            selectionKey.interestOps(interestOps | 4);
        }
    }

    /* access modifiers changed from: protected */
    public boolean closeOnReadError(Throwable th) {
        if (!isActive()) {
            return true;
        }
        if (th instanceof PortUnreachableException) {
            return false;
        }
        if (th instanceof IOException) {
            return !(this instanceof ServerChannel);
        }
        return true;
    }
}
