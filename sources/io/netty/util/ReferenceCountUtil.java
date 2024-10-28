package io.netty.util;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public final class ReferenceCountUtil {
    /* access modifiers changed from: private */
    public static final InternalLogger logger;

    static {
        Class<ReferenceCountUtil> cls = ReferenceCountUtil.class;
        logger = InternalLoggerFactory.getInstance((Class<?>) cls);
        ResourceLeakDetector.addExclusions(cls, "touch");
    }

    public static <T> T retain(T t) {
        return t instanceof ReferenceCounted ? ((ReferenceCounted) t).retain() : t;
    }

    public static <T> T retain(T t, int i) {
        ObjectUtil.checkPositive(i, "increment");
        return t instanceof ReferenceCounted ? ((ReferenceCounted) t).retain(i) : t;
    }

    public static <T> T touch(T t) {
        return t instanceof ReferenceCounted ? ((ReferenceCounted) t).touch() : t;
    }

    public static <T> T touch(T t, Object obj) {
        return t instanceof ReferenceCounted ? ((ReferenceCounted) t).touch(obj) : t;
    }

    public static boolean release(Object obj) {
        if (obj instanceof ReferenceCounted) {
            return ((ReferenceCounted) obj).release();
        }
        return false;
    }

    public static boolean release(Object obj, int i) {
        ObjectUtil.checkPositive(i, "decrement");
        if (obj instanceof ReferenceCounted) {
            return ((ReferenceCounted) obj).release(i);
        }
        return false;
    }

    public static void safeRelease(Object obj) {
        try {
            release(obj);
        } catch (Throwable th) {
            logger.warn("Failed to release a message: {}", obj, th);
        }
    }

    public static void safeRelease(Object obj, int i) {
        try {
            ObjectUtil.checkPositive(i, "decrement");
            release(obj, i);
        } catch (Throwable th) {
            InternalLogger internalLogger = logger;
            if (internalLogger.isWarnEnabled()) {
                internalLogger.warn("Failed to release a message: {} (decrement: {})", obj, Integer.valueOf(i), th);
            }
        }
    }

    @Deprecated
    public static <T> T releaseLater(T t) {
        return releaseLater(t, 1);
    }

    @Deprecated
    public static <T> T releaseLater(T t, int i) {
        ObjectUtil.checkPositive(i, "decrement");
        if (t instanceof ReferenceCounted) {
            ThreadDeathWatcher.watch(Thread.currentThread(), new ReleasingTask((ReferenceCounted) t, i));
        }
        return t;
    }

    public static int refCnt(Object obj) {
        if (obj instanceof ReferenceCounted) {
            return ((ReferenceCounted) obj).refCnt();
        }
        return -1;
    }

    private static final class ReleasingTask implements Runnable {
        private final int decrement;
        private final ReferenceCounted obj;

        ReleasingTask(ReferenceCounted referenceCounted, int i) {
            this.obj = referenceCounted;
            this.decrement = i;
        }

        public void run() {
            try {
                if (!this.obj.release(this.decrement)) {
                    ReferenceCountUtil.logger.warn("Non-zero refCnt: {}", (Object) this);
                } else {
                    ReferenceCountUtil.logger.debug("Released: {}", (Object) this);
                }
            } catch (Exception e) {
                ReferenceCountUtil.logger.warn("Failed to release an object: {}", this.obj, e);
            }
        }

        public String toString() {
            return StringUtil.simpleClassName((Object) this.obj) + ".release(" + this.decrement + ") refCnt: " + this.obj.refCnt();
        }
    }

    private ReferenceCountUtil() {
    }
}
