package io.netty.handler.codec.http2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.socket.ChannelInputShutdownReadComplete;
import io.netty.channel.socket.ChannelOutputShutdownEvent;
import io.netty.handler.codec.http2.Http2FrameCodec;
import io.netty.handler.ssl.SslCloseCompletionEvent;
import java.util.ArrayDeque;
import java.util.Queue;

@Deprecated
public class Http2MultiplexCodec extends Http2FrameCodec {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    volatile ChannelHandlerContext ctx;
    private int idCount;
    private final ChannelHandler inboundStreamHandler;
    /* access modifiers changed from: private */
    public boolean parentReadInProgress;
    /* access modifiers changed from: private */
    public final Queue<AbstractHttp2StreamChannel> readCompletePendingQueue = new MaxCapacityQueue(new ArrayDeque(8), 100);
    private final ChannelHandler upgradeStreamHandler;

    static /* synthetic */ int access$004(Http2MultiplexCodec http2MultiplexCodec) {
        int i = http2MultiplexCodec.idCount + 1;
        http2MultiplexCodec.idCount = i;
        return i;
    }

    Http2MultiplexCodec(Http2ConnectionEncoder http2ConnectionEncoder, Http2ConnectionDecoder http2ConnectionDecoder, Http2Settings http2Settings, ChannelHandler channelHandler, ChannelHandler channelHandler2, boolean z, boolean z2) {
        super(http2ConnectionEncoder, http2ConnectionDecoder, http2Settings, z, z2);
        this.inboundStreamHandler = channelHandler;
        this.upgradeStreamHandler = channelHandler2;
    }

    public void onHttpClientUpgrade() throws Http2Exception {
        if (this.upgradeStreamHandler != null) {
            super.onHttpClientUpgrade();
            return;
        }
        throw Http2Exception.connectionError(Http2Error.INTERNAL_ERROR, "Client is misconfigured for upgrade requests", new Object[0]);
    }

    public final void handlerAdded0(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (channelHandlerContext.executor() == channelHandlerContext.channel().eventLoop()) {
            this.ctx = channelHandlerContext;
            return;
        }
        throw new IllegalStateException("EventExecutor must be EventLoop of Channel");
    }

    public final void handlerRemoved0(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.handlerRemoved0(channelHandlerContext);
        this.readCompletePendingQueue.clear();
    }

    /* access modifiers changed from: package-private */
    public final void onHttp2Frame(ChannelHandlerContext channelHandlerContext, Http2Frame http2Frame) {
        if (http2Frame instanceof Http2StreamFrame) {
            Http2StreamFrame http2StreamFrame = (Http2StreamFrame) http2Frame;
            ((AbstractHttp2StreamChannel) ((Http2FrameCodec.DefaultHttp2FrameStream) http2StreamFrame.stream()).attachment).fireChildRead(http2StreamFrame);
            return;
        }
        if (http2Frame instanceof Http2GoAwayFrame) {
            onHttp2GoAwayFrame(channelHandlerContext, (Http2GoAwayFrame) http2Frame);
        }
        channelHandlerContext.fireChannelRead(http2Frame);
    }

    /* renamed from: io.netty.handler.codec.http2.Http2MultiplexCodec$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                io.netty.handler.codec.http2.Http2Stream$State[] r0 = io.netty.handler.codec.http2.Http2Stream.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State = r0
                io.netty.handler.codec.http2.Http2Stream$State r1 = io.netty.handler.codec.http2.Http2Stream.State.HALF_CLOSED_LOCAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.handler.codec.http2.Http2Stream$State r1 = io.netty.handler.codec.http2.Http2Stream.State.HALF_CLOSED_REMOTE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.netty.handler.codec.http2.Http2Stream$State r1 = io.netty.handler.codec.http2.Http2Stream.State.OPEN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http2$Http2Stream$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.netty.handler.codec.http2.Http2Stream$State r1 = io.netty.handler.codec.http2.Http2Stream.State.CLOSED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http2.Http2MultiplexCodec.AnonymousClass2.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public final void onHttp2StreamStateChanged(ChannelHandlerContext channelHandlerContext, Http2FrameCodec.DefaultHttp2FrameStream defaultHttp2FrameStream) {
        Http2MultiplexCodecStreamChannel http2MultiplexCodecStreamChannel;
        AbstractHttp2StreamChannel abstractHttp2StreamChannel;
        int i = AnonymousClass2.$SwitchMap$io$netty$handler$codec$http2$Http2Stream$State[defaultHttp2FrameStream.state().ordinal()];
        if (i != 1) {
            if (!(i == 2 || i == 3)) {
                if (i == 4 && (abstractHttp2StreamChannel = (AbstractHttp2StreamChannel) defaultHttp2FrameStream.attachment) != null) {
                    abstractHttp2StreamChannel.streamClosed();
                    return;
                }
                return;
            }
        } else if (defaultHttp2FrameStream.id() != 1) {
            return;
        }
        if (defaultHttp2FrameStream.attachment == null) {
            if (defaultHttp2FrameStream.id() != 1 || connection().isServer()) {
                http2MultiplexCodecStreamChannel = new Http2MultiplexCodecStreamChannel(defaultHttp2FrameStream, this.inboundStreamHandler);
            } else {
                http2MultiplexCodecStreamChannel = new Http2MultiplexCodecStreamChannel(defaultHttp2FrameStream, this.upgradeStreamHandler);
                http2MultiplexCodecStreamChannel.closeOutbound();
            }
            ChannelFuture register = channelHandlerContext.channel().eventLoop().register((Channel) http2MultiplexCodecStreamChannel);
            if (register.isDone()) {
                Http2MultiplexHandler.registerDone(register);
            } else {
                register.addListener(Http2MultiplexHandler.CHILD_CHANNEL_REGISTRATION_LISTENER);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final Http2StreamChannel newOutboundStream() {
        return new Http2MultiplexCodecStreamChannel(newStream(), (ChannelHandler) null);
    }

    /* access modifiers changed from: package-private */
    public final void onHttp2FrameStreamException(ChannelHandlerContext channelHandlerContext, Http2FrameStreamException http2FrameStreamException) {
        AbstractHttp2StreamChannel abstractHttp2StreamChannel = (AbstractHttp2StreamChannel) ((Http2FrameCodec.DefaultHttp2FrameStream) http2FrameStreamException.stream()).attachment;
        try {
            abstractHttp2StreamChannel.pipeline().fireExceptionCaught(http2FrameStreamException.getCause());
        } finally {
            abstractHttp2StreamChannel.closeWithError(http2FrameStreamException.error());
        }
    }

    private void onHttp2GoAwayFrame(ChannelHandlerContext channelHandlerContext, final Http2GoAwayFrame http2GoAwayFrame) {
        if (http2GoAwayFrame.lastStreamId() != Integer.MAX_VALUE) {
            try {
                forEachActiveStream(new Http2FrameStreamVisitor() {
                    public boolean visit(Http2FrameStream http2FrameStream) {
                        int id = http2FrameStream.id();
                        AbstractHttp2StreamChannel abstractHttp2StreamChannel = (AbstractHttp2StreamChannel) ((Http2FrameCodec.DefaultHttp2FrameStream) http2FrameStream).attachment;
                        if (id <= http2GoAwayFrame.lastStreamId() || !Http2MultiplexCodec.this.connection().local().isValidStreamId(id)) {
                            return true;
                        }
                        abstractHttp2StreamChannel.pipeline().fireUserEventTriggered(http2GoAwayFrame.retainedDuplicate());
                        return true;
                    }
                });
            } catch (Http2Exception e) {
                channelHandlerContext.fireExceptionCaught(e);
                channelHandlerContext.close();
            }
        }
    }

    public final void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        processPendingReadCompleteQueue();
        channelReadComplete0(channelHandlerContext);
    }

    /* access modifiers changed from: private */
    public void processPendingReadCompleteQueue() {
        this.parentReadInProgress = true;
        while (true) {
            try {
                AbstractHttp2StreamChannel poll = this.readCompletePendingQueue.poll();
                if (poll != null) {
                    poll.fireChildReadComplete();
                } else {
                    return;
                }
            } finally {
                this.parentReadInProgress = false;
                this.readCompletePendingQueue.clear();
                flush0(this.ctx);
            }
        }
    }

    public final void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        this.parentReadInProgress = true;
        super.channelRead(channelHandlerContext, obj);
    }

    public final void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (channelHandlerContext.channel().isWritable()) {
            forEachActiveStream(AbstractHttp2StreamChannel.WRITABLE_VISITOR);
        }
        super.channelWritabilityChanged(channelHandlerContext);
    }

    /* access modifiers changed from: package-private */
    public final void onUserEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (obj == ChannelInputShutdownReadComplete.INSTANCE) {
            forEachActiveStream(AbstractHttp2StreamChannel.CHANNEL_INPUT_SHUTDOWN_READ_COMPLETE_VISITOR);
        } else if (obj == ChannelOutputShutdownEvent.INSTANCE) {
            forEachActiveStream(AbstractHttp2StreamChannel.CHANNEL_OUTPUT_SHUTDOWN_EVENT_VISITOR);
        } else if (obj == SslCloseCompletionEvent.SUCCESS) {
            forEachActiveStream(AbstractHttp2StreamChannel.SSL_CLOSE_COMPLETION_EVENT_VISITOR);
        }
        super.onUserEventTriggered(channelHandlerContext, obj);
    }

    /* access modifiers changed from: package-private */
    public final void flush0(ChannelHandlerContext channelHandlerContext) {
        flush(channelHandlerContext);
    }

    private final class Http2MultiplexCodecStreamChannel extends AbstractHttp2StreamChannel {
        Http2MultiplexCodecStreamChannel(Http2FrameCodec.DefaultHttp2FrameStream defaultHttp2FrameStream, ChannelHandler channelHandler) {
            super(defaultHttp2FrameStream, Http2MultiplexCodec.access$004(Http2MultiplexCodec.this), channelHandler);
        }

        /* access modifiers changed from: protected */
        public boolean isParentReadInProgress() {
            return Http2MultiplexCodec.this.parentReadInProgress;
        }

        /* access modifiers changed from: protected */
        public void addChannelToReadCompletePendingQueue() {
            while (!Http2MultiplexCodec.this.readCompletePendingQueue.offer(this)) {
                Http2MultiplexCodec.this.processPendingReadCompleteQueue();
            }
        }

        /* access modifiers changed from: protected */
        public ChannelHandlerContext parentContext() {
            return Http2MultiplexCodec.this.ctx;
        }

        /* access modifiers changed from: protected */
        public ChannelFuture write0(ChannelHandlerContext channelHandlerContext, Object obj) {
            ChannelPromise newPromise = channelHandlerContext.newPromise();
            Http2MultiplexCodec.this.write(channelHandlerContext, obj, newPromise);
            return newPromise;
        }

        /* access modifiers changed from: protected */
        public void flush0(ChannelHandlerContext channelHandlerContext) {
            Http2MultiplexCodec.this.flush0(channelHandlerContext);
        }
    }
}
