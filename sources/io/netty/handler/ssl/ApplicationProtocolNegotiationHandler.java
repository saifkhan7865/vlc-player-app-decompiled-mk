package io.netty.handler.ssl;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DecoderException;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.RecyclableArrayList;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import javax.net.ssl.SSLException;

public abstract class ApplicationProtocolNegotiationHandler extends ChannelInboundHandlerAdapter {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ApplicationProtocolNegotiationHandler.class);
    private final RecyclableArrayList bufferedMessages = RecyclableArrayList.newInstance();
    private ChannelHandlerContext ctx;
    private final String fallbackProtocol;
    private boolean sslHandlerChecked;

    /* access modifiers changed from: protected */
    public abstract void configurePipeline(ChannelHandlerContext channelHandlerContext, String str) throws Exception;

    protected ApplicationProtocolNegotiationHandler(String str) {
        this.fallbackProtocol = (String) ObjectUtil.checkNotNull(str, "fallbackProtocol");
    }

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
        super.handlerAdded(channelHandlerContext);
    }

    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        fireBufferedMessages();
        this.bufferedMessages.recycle();
        super.handlerRemoved(channelHandlerContext);
    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        this.bufferedMessages.add(obj);
        if (!this.sslHandlerChecked) {
            this.sslHandlerChecked = true;
            if (channelHandlerContext.pipeline().get(SslHandler.class) == null) {
                removeSelfIfPresent(channelHandlerContext);
            }
        }
    }

    private void fireBufferedMessages() {
        if (!this.bufferedMessages.isEmpty()) {
            for (int i = 0; i < this.bufferedMessages.size(); i++) {
                this.ctx.fireChannelRead(this.bufferedMessages.get(i));
            }
            this.ctx.fireChannelReadComplete();
            this.bufferedMessages.clear();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0034, code lost:
        if (r0.isSuccess() != false) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0036, code lost:
        removeSelfIfPresent(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0042, code lost:
        if (r0.isSuccess() == false) goto L_0x0050;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void userEventTriggered(io.netty.channel.ChannelHandlerContext r4, java.lang.Object r5) throws java.lang.Exception {
        /*
            r3 = this;
            boolean r0 = r5 instanceof io.netty.handler.ssl.SslHandshakeCompletionEvent
            if (r0 == 0) goto L_0x0050
            r0 = r5
            io.netty.handler.ssl.SslHandshakeCompletionEvent r0 = (io.netty.handler.ssl.SslHandshakeCompletionEvent) r0
            boolean r1 = r0.isSuccess()     // Catch:{ all -> 0x003a }
            if (r1 == 0) goto L_0x0030
            io.netty.channel.ChannelPipeline r1 = r4.pipeline()     // Catch:{ all -> 0x003a }
            java.lang.Class<io.netty.handler.ssl.SslHandler> r2 = io.netty.handler.ssl.SslHandler.class
            io.netty.channel.ChannelHandler r1 = r1.get(r2)     // Catch:{ all -> 0x003a }
            io.netty.handler.ssl.SslHandler r1 = (io.netty.handler.ssl.SslHandler) r1     // Catch:{ all -> 0x003a }
            if (r1 == 0) goto L_0x0028
            java.lang.String r1 = r1.applicationProtocol()     // Catch:{ all -> 0x003a }
            if (r1 == 0) goto L_0x0022
            goto L_0x0024
        L_0x0022:
            java.lang.String r1 = r3.fallbackProtocol     // Catch:{ all -> 0x003a }
        L_0x0024:
            r3.configurePipeline(r4, r1)     // Catch:{ all -> 0x003a }
            goto L_0x0030
        L_0x0028:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x003a }
            java.lang.String r2 = "cannot find an SslHandler in the pipeline (required for application-level protocol negotiation)"
            r1.<init>(r2)     // Catch:{ all -> 0x003a }
            throw r1     // Catch:{ all -> 0x003a }
        L_0x0030:
            boolean r0 = r0.isSuccess()
            if (r0 == 0) goto L_0x0050
        L_0x0036:
            r3.removeSelfIfPresent(r4)
            goto L_0x0050
        L_0x003a:
            r1 = move-exception
            r3.exceptionCaught(r4, r1)     // Catch:{ all -> 0x0045 }
            boolean r0 = r0.isSuccess()
            if (r0 == 0) goto L_0x0050
            goto L_0x0036
        L_0x0045:
            r5 = move-exception
            boolean r0 = r0.isSuccess()
            if (r0 == 0) goto L_0x004f
            r3.removeSelfIfPresent(r4)
        L_0x004f:
            throw r5
        L_0x0050:
            boolean r0 = r5 instanceof io.netty.channel.socket.ChannelInputShutdownEvent
            if (r0 == 0) goto L_0x0057
            r3.fireBufferedMessages()
        L_0x0057:
            r4.fireUserEventTriggered(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.ApplicationProtocolNegotiationHandler.userEventTriggered(io.netty.channel.ChannelHandlerContext, java.lang.Object):void");
    }

    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        fireBufferedMessages();
        super.channelInactive(channelHandlerContext);
    }

    private void removeSelfIfPresent(ChannelHandlerContext channelHandlerContext) {
        ChannelPipeline pipeline = channelHandlerContext.pipeline();
        if (!channelHandlerContext.isRemoved()) {
            pipeline.remove((ChannelHandler) this);
        }
    }

    /* access modifiers changed from: protected */
    public void handshakeFailure(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        logger.warn("{} TLS handshake failed:", channelHandlerContext.channel(), th);
        channelHandlerContext.close();
    }

    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        if (th instanceof DecoderException) {
            Throwable cause = th.getCause();
            if (cause instanceof SSLException) {
                try {
                    handshakeFailure(channelHandlerContext, cause);
                    return;
                } finally {
                    removeSelfIfPresent(channelHandlerContext);
                }
            }
        }
        logger.warn("{} Failed to select the application-level protocol:", channelHandlerContext.channel(), th);
        channelHandlerContext.fireExceptionCaught(th);
        channelHandlerContext.close();
    }
}
