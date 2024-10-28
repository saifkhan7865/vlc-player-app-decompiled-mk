package io.netty.channel;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerMask;
import io.netty.util.internal.InternalThreadLocalMap;
import java.util.Map;

public abstract class ChannelHandlerAdapter implements ChannelHandler {
    boolean added;

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    /* access modifiers changed from: protected */
    public void ensureNotSharable() {
        if (isSharable()) {
            throw new IllegalStateException("ChannelHandler " + getClass().getName() + " is not allowed to be shared");
        }
    }

    public boolean isSharable() {
        Class<?> cls = getClass();
        Map<Class<?>, Boolean> handlerSharableCache = InternalThreadLocalMap.get().handlerSharableCache();
        Boolean bool = handlerSharableCache.get(cls);
        if (bool == null) {
            bool = Boolean.valueOf(cls.isAnnotationPresent(ChannelHandler.Sharable.class));
            handlerSharableCache.put(cls, bool);
        }
        return bool.booleanValue();
    }

    @ChannelHandlerMask.Skip
    @Deprecated
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        channelHandlerContext.fireExceptionCaught(th);
    }
}
