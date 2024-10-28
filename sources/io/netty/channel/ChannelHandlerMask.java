package io.netty.channel;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.SocketAddress;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Map;
import java.util.WeakHashMap;

final class ChannelHandlerMask {
    private static final FastThreadLocal<Map<Class<? extends ChannelHandler>, Integer>> MASKS = new FastThreadLocal<Map<Class<? extends ChannelHandler>, Integer>>() {
        /* access modifiers changed from: protected */
        public Map<Class<? extends ChannelHandler>, Integer> initialValue() {
            return new WeakHashMap(32);
        }
    };
    private static final int MASK_ALL_INBOUND = 511;
    private static final int MASK_ALL_OUTBOUND = 130561;
    static final int MASK_BIND = 512;
    static final int MASK_CHANNEL_ACTIVE = 8;
    static final int MASK_CHANNEL_INACTIVE = 16;
    static final int MASK_CHANNEL_READ = 32;
    static final int MASK_CHANNEL_READ_COMPLETE = 64;
    static final int MASK_CHANNEL_REGISTERED = 2;
    static final int MASK_CHANNEL_UNREGISTERED = 4;
    static final int MASK_CHANNEL_WRITABILITY_CHANGED = 256;
    static final int MASK_CLOSE = 4096;
    static final int MASK_CONNECT = 1024;
    static final int MASK_DEREGISTER = 8192;
    static final int MASK_DISCONNECT = 2048;
    static final int MASK_EXCEPTION_CAUGHT = 1;
    static final int MASK_FLUSH = 65536;
    static final int MASK_ONLY_INBOUND = 510;
    static final int MASK_ONLY_OUTBOUND = 130560;
    static final int MASK_READ = 16384;
    static final int MASK_USER_EVENT_TRIGGERED = 128;
    static final int MASK_WRITE = 32768;
    /* access modifiers changed from: private */
    public static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ChannelHandlerMask.class);

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Skip {
    }

    static int mask(Class<? extends ChannelHandler> cls) {
        Map map = MASKS.get();
        Integer num = (Integer) map.get(cls);
        if (num == null) {
            num = Integer.valueOf(mask0(cls));
            map.put(cls, num);
        }
        return num.intValue();
    }

    private static int mask0(Class<? extends ChannelHandler> cls) {
        int i;
        int i2 = 1;
        try {
            if (ChannelInboundHandler.class.isAssignableFrom(cls)) {
                i = 511;
                try {
                    if (isSkippable(cls, "channelRegistered", ChannelHandlerContext.class)) {
                        i = 509;
                    }
                } catch (Exception e) {
                    e = e;
                    i2 = 511;
                    PlatformDependent.throwException(e);
                    return i2;
                }
                try {
                    if (isSkippable(cls, "channelUnregistered", ChannelHandlerContext.class)) {
                        i &= -5;
                    }
                    if (isSkippable(cls, "channelActive", ChannelHandlerContext.class)) {
                        i &= -9;
                    }
                    if (isSkippable(cls, "channelInactive", ChannelHandlerContext.class)) {
                        i &= -17;
                    }
                    if (isSkippable(cls, "channelRead", ChannelHandlerContext.class, Object.class)) {
                        i &= -33;
                    }
                    if (isSkippable(cls, "channelReadComplete", ChannelHandlerContext.class)) {
                        i &= -65;
                    }
                    if (isSkippable(cls, "channelWritabilityChanged", ChannelHandlerContext.class)) {
                        i &= -257;
                    }
                    if (isSkippable(cls, "userEventTriggered", ChannelHandlerContext.class, Object.class)) {
                        i &= -129;
                    }
                } catch (Exception e2) {
                    e = e2;
                    i2 = i;
                    PlatformDependent.throwException(e);
                    return i2;
                }
            } else {
                i = 1;
            }
            if (ChannelOutboundHandler.class.isAssignableFrom(cls)) {
                int i3 = i | MASK_ALL_OUTBOUND;
                if (isSkippable(cls, "bind", ChannelHandlerContext.class, SocketAddress.class, ChannelPromise.class)) {
                    i3 &= -513;
                }
                Class<SocketAddress> cls2 = SocketAddress.class;
                if (isSkippable(cls, "connect", ChannelHandlerContext.class, cls2, cls2, ChannelPromise.class)) {
                    i3 &= -1025;
                }
                if (isSkippable(cls, "disconnect", ChannelHandlerContext.class, ChannelPromise.class)) {
                    i3 &= -2049;
                }
                if (isSkippable(cls, "close", ChannelHandlerContext.class, ChannelPromise.class)) {
                    i3 &= -4097;
                }
                if (isSkippable(cls, "deregister", ChannelHandlerContext.class, ChannelPromise.class)) {
                    i3 &= -8193;
                }
                if (isSkippable(cls, "read", ChannelHandlerContext.class)) {
                    i3 &= -16385;
                }
                if (isSkippable(cls, "write", ChannelHandlerContext.class, Object.class, ChannelPromise.class)) {
                    i3 &= -32769;
                }
                if (isSkippable(cls, "flush", ChannelHandlerContext.class)) {
                    i &= -65537;
                }
            }
            if (isSkippable(cls, "exceptionCaught", ChannelHandlerContext.class, Throwable.class)) {
                return i & -2;
            }
            return i;
        } catch (Exception e3) {
            e = e3;
            PlatformDependent.throwException(e);
            return i2;
        }
    }

    private static boolean isSkippable(final Class<?> cls, final String str, final Class<?>... clsArr) throws Exception {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedExceptionAction<Boolean>() {
            public Boolean run() throws Exception {
                try {
                    return Boolean.valueOf(cls.getMethod(str, clsArr).isAnnotationPresent(Skip.class));
                } catch (NoSuchMethodException e) {
                    if (ChannelHandlerMask.logger.isDebugEnabled()) {
                        ChannelHandlerMask.logger.debug("Class {} missing method {}, assume we can not skip execution", cls, str, e);
                    }
                    return false;
                }
            }
        })).booleanValue();
    }

    private ChannelHandlerMask() {
    }
}
