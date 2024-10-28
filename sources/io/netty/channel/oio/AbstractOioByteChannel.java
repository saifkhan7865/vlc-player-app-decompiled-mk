package io.netty.channel.oio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.FileRegion;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.socket.ChannelInputShutdownEvent;
import io.netty.channel.socket.ChannelInputShutdownReadComplete;
import io.netty.util.internal.StringUtil;
import java.io.IOException;

public abstract class AbstractOioByteChannel extends AbstractOioChannel {
    private static final String EXPECTED_TYPES = (" (expected: " + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ", " + StringUtil.simpleClassName((Class<?>) FileRegion.class) + ')');
    private static final ChannelMetadata METADATA = new ChannelMetadata(false);

    /* access modifiers changed from: protected */
    public abstract int available();

    /* access modifiers changed from: protected */
    public abstract int doReadBytes(ByteBuf byteBuf) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void doWriteBytes(ByteBuf byteBuf) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void doWriteFileRegion(FileRegion fileRegion) throws Exception;

    /* access modifiers changed from: protected */
    public abstract boolean isInputShutdown();

    /* access modifiers changed from: protected */
    public abstract ChannelFuture shutdownInput();

    protected AbstractOioByteChannel(Channel channel) {
        super(channel);
    }

    public ChannelMetadata metadata() {
        return METADATA;
    }

    private void closeOnRead(ChannelPipeline channelPipeline) {
        if (isOpen()) {
            if (Boolean.TRUE.equals(config().getOption(ChannelOption.ALLOW_HALF_CLOSURE))) {
                shutdownInput();
                channelPipeline.fireUserEventTriggered(ChannelInputShutdownEvent.INSTANCE);
            } else {
                unsafe().close(unsafe().voidPromise());
            }
            channelPipeline.fireUserEventTriggered(ChannelInputShutdownReadComplete.INSTANCE);
        }
    }

    private void handleReadException(ChannelPipeline channelPipeline, ByteBuf byteBuf, Throwable th, boolean z, RecvByteBufAllocator.Handle handle) {
        if (byteBuf != null) {
            if (byteBuf.isReadable()) {
                this.readPending = false;
                channelPipeline.fireChannelRead(byteBuf);
            } else {
                byteBuf.release();
            }
        }
        handle.readComplete();
        channelPipeline.fireChannelReadComplete();
        channelPipeline.fireExceptionCaught(th);
        if (z || (th instanceof OutOfMemoryError) || (th instanceof IOException)) {
            closeOnRead(channelPipeline);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:101:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003e, code lost:
        if (r5.isReadable() != false) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0040, code lost:
        r5.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
        if (r7.lastBytesRead() >= 0) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004a, code lost:
        r9 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004b, code lost:
        if (r9 == false) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r11.readPending = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004f, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0051, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0052, code lost:
        r5 = r2;
        r1 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b4, code lost:
        r1 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doRead() {
        /*
            r11 = this;
            io.netty.channel.ChannelConfig r0 = r11.config()
            boolean r1 = r11.isInputShutdown()
            if (r1 != 0) goto L_0x010f
            boolean r1 = r11.readPending
            if (r1 != 0) goto L_0x0010
            goto L_0x010f
        L_0x0010:
            r1 = 0
            r11.readPending = r1
            io.netty.channel.ChannelPipeline r3 = r11.pipeline()
            io.netty.buffer.ByteBufAllocator r2 = r0.getAllocator()
            io.netty.channel.Channel$Unsafe r4 = r11.unsafe()
            io.netty.channel.RecvByteBufAllocator$Handle r7 = r4.recvBufAllocHandle()
            r7.reset(r0)
            r4 = 0
            io.netty.buffer.ByteBuf r5 = r7.allocate(r2)     // Catch:{ all -> 0x00db }
            r6 = 0
        L_0x002c:
            int r8 = r11.doReadBytes(r5)     // Catch:{ all -> 0x00d5 }
            r7.lastBytesRead(r8)     // Catch:{ all -> 0x00d5 }
            int r8 = r7.lastBytesRead()     // Catch:{ all -> 0x00d5 }
            r9 = 1
            if (r8 > 0) goto L_0x0058
            boolean r2 = r5.isReadable()     // Catch:{ all -> 0x00d5 }
            if (r2 != 0) goto L_0x0056
            r5.release()     // Catch:{ all -> 0x00d5 }
            int r2 = r7.lastBytesRead()     // Catch:{ all -> 0x0051 }
            if (r2 >= 0) goto L_0x004a
            goto L_0x004b
        L_0x004a:
            r9 = 0
        L_0x004b:
            if (r9 == 0) goto L_0x004f
            r11.readPending = r1     // Catch:{ all -> 0x00b4 }
        L_0x004f:
            r5 = r4
            goto L_0x0092
        L_0x0051:
            r2 = move-exception
            r5 = r2
            r1 = r6
            goto L_0x00dd
        L_0x0056:
            r9 = 0
            goto L_0x0092
        L_0x0058:
            int r6 = r11.available()     // Catch:{ all -> 0x00d1 }
            if (r6 > 0) goto L_0x0060
        L_0x005e:
            r6 = 1
            goto L_0x0056
        L_0x0060:
            boolean r8 = r5.isWritable()     // Catch:{ all -> 0x00d1 }
            if (r8 != 0) goto L_0x008b
            int r8 = r5.capacity()     // Catch:{ all -> 0x00d1 }
            int r10 = r5.maxCapacity()     // Catch:{ all -> 0x00d1 }
            if (r8 != r10) goto L_0x007d
            r7.incMessagesRead(r9)     // Catch:{ all -> 0x00d1 }
            r11.readPending = r1     // Catch:{ all -> 0x00d1 }
            r3.fireChannelRead(r5)     // Catch:{ all -> 0x00d1 }
            io.netty.buffer.ByteBuf r5 = r7.allocate(r2)     // Catch:{ all -> 0x00d1 }
            goto L_0x008b
        L_0x007d:
            int r8 = r5.writerIndex()     // Catch:{ all -> 0x00d1 }
            int r8 = r8 + r6
            if (r8 <= r10) goto L_0x0088
            r5.capacity(r10)     // Catch:{ all -> 0x00d1 }
            goto L_0x008b
        L_0x0088:
            r5.ensureWritable(r6)     // Catch:{ all -> 0x00d1 }
        L_0x008b:
            boolean r6 = r7.continueReading()     // Catch:{ all -> 0x00d1 }
            if (r6 != 0) goto L_0x00ce
            goto L_0x005e
        L_0x0092:
            if (r5 == 0) goto L_0x00aa
            boolean r2 = r5.isReadable()     // Catch:{ all -> 0x00a4 }
            if (r2 == 0) goto L_0x00a0
            r11.readPending = r1     // Catch:{ all -> 0x00a4 }
            r3.fireChannelRead(r5)     // Catch:{ all -> 0x00a4 }
            goto L_0x00ab
        L_0x00a0:
            r5.release()     // Catch:{ all -> 0x00a4 }
            goto L_0x00ab
        L_0x00a4:
            r1 = move-exception
            r4 = r5
        L_0x00a6:
            r5 = r1
            r1 = r6
            r6 = r9
            goto L_0x00de
        L_0x00aa:
            r4 = r5
        L_0x00ab:
            if (r6 == 0) goto L_0x00b6
            r7.readComplete()     // Catch:{ all -> 0x00b4 }
            r3.fireChannelReadComplete()     // Catch:{ all -> 0x00b4 }
            goto L_0x00b6
        L_0x00b4:
            r1 = move-exception
            goto L_0x00a6
        L_0x00b6:
            if (r9 == 0) goto L_0x00bb
            r11.closeOnRead(r3)     // Catch:{ all -> 0x00b4 }
        L_0x00bb:
            boolean r1 = r11.readPending
            if (r1 != 0) goto L_0x00f4
            boolean r0 = r0.isAutoRead()
            if (r0 != 0) goto L_0x00f4
            if (r6 != 0) goto L_0x00f7
            boolean r0 = r11.isActive()
            if (r0 == 0) goto L_0x00f7
            goto L_0x00f4
        L_0x00ce:
            r6 = 1
            goto L_0x002c
        L_0x00d1:
            r2 = move-exception
            r4 = r5
            r1 = 1
            goto L_0x00d8
        L_0x00d5:
            r2 = move-exception
            r4 = r5
            r1 = r6
        L_0x00d8:
            r6 = 0
            r5 = r2
            goto L_0x00de
        L_0x00db:
            r2 = move-exception
            r5 = r2
        L_0x00dd:
            r6 = 0
        L_0x00de:
            r2 = r11
            r2.handleReadException(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00f8 }
            boolean r2 = r11.readPending
            if (r2 != 0) goto L_0x00f4
            boolean r0 = r0.isAutoRead()
            if (r0 != 0) goto L_0x00f4
            if (r1 != 0) goto L_0x00f7
            boolean r0 = r11.isActive()
            if (r0 == 0) goto L_0x00f7
        L_0x00f4:
            r11.read()
        L_0x00f7:
            return
        L_0x00f8:
            r2 = move-exception
            boolean r3 = r11.readPending
            if (r3 != 0) goto L_0x010b
            boolean r0 = r0.isAutoRead()
            if (r0 != 0) goto L_0x010b
            if (r1 != 0) goto L_0x010e
            boolean r0 = r11.isActive()
            if (r0 == 0) goto L_0x010e
        L_0x010b:
            r11.read()
        L_0x010e:
            throw r2
        L_0x010f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.oio.AbstractOioByteChannel.doRead():void");
    }

    /* access modifiers changed from: protected */
    public void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        while (true) {
            Object current = channelOutboundBuffer.current();
            if (current != null) {
                if (current instanceof ByteBuf) {
                    ByteBuf byteBuf = (ByteBuf) current;
                    int readableBytes = byteBuf.readableBytes();
                    while (readableBytes > 0) {
                        doWriteBytes(byteBuf);
                        int readableBytes2 = byteBuf.readableBytes();
                        channelOutboundBuffer.progress((long) (readableBytes - readableBytes2));
                        readableBytes = readableBytes2;
                    }
                    channelOutboundBuffer.remove();
                } else if (current instanceof FileRegion) {
                    FileRegion fileRegion = (FileRegion) current;
                    long transferred = fileRegion.transferred();
                    doWriteFileRegion(fileRegion);
                    channelOutboundBuffer.progress(fileRegion.transferred() - transferred);
                    channelOutboundBuffer.remove();
                } else {
                    channelOutboundBuffer.remove(new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(current)));
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object filterOutboundMessage(Object obj) throws Exception {
        if ((obj instanceof ByteBuf) || (obj instanceof FileRegion)) {
            return obj;
        }
        throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(obj) + EXPECTED_TYPES);
    }
}
