package io.netty.handler.codec.http2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http2.Http2Exception;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.concurrent.TimeUnit;

final class Http2MaxRstFrameListener extends Http2FrameListenerDecorator {
    private static final Http2Exception RST_FRAME_RATE_EXCEEDED;
    private static final InternalLogger logger;
    private long lastRstFrameNano = System.nanoTime();
    private final int maxRstFramesPerWindow;
    private final long nanosPerWindow;
    private int receivedRstInWindow;

    static {
        Class<Http2MaxRstFrameListener> cls = Http2MaxRstFrameListener.class;
        logger = InternalLoggerFactory.getInstance((Class<?>) cls);
        RST_FRAME_RATE_EXCEEDED = Http2Exception.newStatic(Http2Error.ENHANCE_YOUR_CALM, "Maximum number of RST frames reached", Http2Exception.ShutdownHint.HARD_SHUTDOWN, cls, "onRstStreamRead(..)");
    }

    Http2MaxRstFrameListener(Http2FrameListener http2FrameListener, int i, int i2) {
        super(http2FrameListener);
        this.maxRstFramesPerWindow = i;
        this.nanosPerWindow = TimeUnit.SECONDS.toNanos((long) i2);
    }

    public void onRstStreamRead(ChannelHandlerContext channelHandlerContext, int i, long j) throws Http2Exception {
        long nanoTime = System.nanoTime();
        if (nanoTime - this.lastRstFrameNano >= this.nanosPerWindow) {
            this.lastRstFrameNano = nanoTime;
            this.receivedRstInWindow = 1;
        } else {
            int i2 = this.receivedRstInWindow + 1;
            this.receivedRstInWindow = i2;
            if (i2 > this.maxRstFramesPerWindow) {
                InternalLogger internalLogger = logger;
                Channel channel = channelHandlerContext.channel();
                Integer valueOf = Integer.valueOf(this.maxRstFramesPerWindow);
                Long valueOf2 = Long.valueOf(TimeUnit.NANOSECONDS.toSeconds(this.nanosPerWindow));
                Http2Exception http2Exception = RST_FRAME_RATE_EXCEEDED;
                internalLogger.debug("{} Maximum number {} of RST frames reached within {} seconds, closing connection with {} error", channel, valueOf, valueOf2, http2Exception.error(), http2Exception);
                throw http2Exception;
            }
        }
        super.onRstStreamRead(channelHandlerContext, i, j);
    }
}
