package io.netty.handler.codec.compression;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class EncoderUtil {
    private static final int THREAD_POOL_DELAY_SECONDS = 10;

    static void closeAfterFinishEncode(final ChannelHandlerContext channelHandlerContext, ChannelFuture channelFuture, final ChannelPromise channelPromise) {
        if (!channelFuture.isDone()) {
            final ScheduledFuture<?> schedule = channelHandlerContext.executor().schedule((Runnable) new Runnable() {
                public void run() {
                    channelHandlerContext.close(channelPromise);
                }
            }, 10, TimeUnit.SECONDS);
            channelFuture.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture channelFuture) {
                    schedule.cancel(true);
                    if (!channelPromise.isDone()) {
                        channelHandlerContext.close(channelPromise);
                    }
                }
            });
            return;
        }
        channelHandlerContext.close(channelPromise);
    }

    private EncoderUtil() {
    }
}
