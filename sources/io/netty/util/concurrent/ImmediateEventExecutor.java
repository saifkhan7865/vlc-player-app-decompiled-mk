package io.netty.util.concurrent;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public final class ImmediateEventExecutor extends AbstractEventExecutor {
    private static final FastThreadLocal<Queue<Runnable>> DELAYED_RUNNABLES = new FastThreadLocal<Queue<Runnable>>() {
        /* access modifiers changed from: protected */
        public Queue<Runnable> initialValue() throws Exception {
            return new ArrayDeque();
        }
    };
    public static final ImmediateEventExecutor INSTANCE = new ImmediateEventExecutor();
    private static final FastThreadLocal<Boolean> RUNNING = new FastThreadLocal<Boolean>() {
        /* access modifiers changed from: protected */
        public Boolean initialValue() throws Exception {
            return false;
        }
    };
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ImmediateEventExecutor.class);
    private final Future<?> terminationFuture = new FailedFuture(GlobalEventExecutor.INSTANCE, new UnsupportedOperationException());

    public boolean awaitTermination(long j, TimeUnit timeUnit) {
        return false;
    }

    public boolean inEventLoop() {
        return true;
    }

    public boolean inEventLoop(Thread thread) {
        return true;
    }

    public boolean isShutdown() {
        return false;
    }

    public boolean isShuttingDown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }

    @Deprecated
    public void shutdown() {
    }

    private ImmediateEventExecutor() {
    }

    public Future<?> shutdownGracefully(long j, long j2, TimeUnit timeUnit) {
        return terminationFuture();
    }

    public Future<?> terminationFuture() {
        return this.terminationFuture;
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public void execute(java.lang.Runnable r7) {
        /*
            r6 = this;
            java.lang.String r0 = "Throwable caught while executing Runnable {}"
            java.lang.String r1 = "command"
            io.netty.util.internal.ObjectUtil.checkNotNull(r7, r1)
            io.netty.util.concurrent.FastThreadLocal<java.lang.Boolean> r1 = RUNNING
            java.lang.Object r2 = r1.get()
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 != 0) goto L_0x008d
            r2 = 1
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r1.set(r2)
            r1 = 0
            r7.run()     // Catch:{ all -> 0x0046 }
            io.netty.util.concurrent.FastThreadLocal<java.util.Queue<java.lang.Runnable>> r7 = DELAYED_RUNNABLES
            java.lang.Object r7 = r7.get()
            java.util.Queue r7 = (java.util.Queue) r7
        L_0x0029:
            java.lang.Object r2 = r7.poll()
            java.lang.Runnable r2 = (java.lang.Runnable) r2
            if (r2 == 0) goto L_0x003c
            r2.run()     // Catch:{ all -> 0x0035 }
            goto L_0x0029
        L_0x0035:
            r3 = move-exception
            io.netty.util.internal.logging.InternalLogger r4 = logger
            r4.info(r0, r2, r3)
            goto L_0x0029
        L_0x003c:
            io.netty.util.concurrent.FastThreadLocal<java.lang.Boolean> r7 = RUNNING
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            r7.set(r0)
            goto L_0x0098
        L_0x0046:
            r2 = move-exception
            io.netty.util.internal.logging.InternalLogger r3 = logger     // Catch:{ all -> 0x0067 }
            r3.info(r0, r7, r2)     // Catch:{ all -> 0x0067 }
            io.netty.util.concurrent.FastThreadLocal<java.util.Queue<java.lang.Runnable>> r7 = DELAYED_RUNNABLES
            java.lang.Object r7 = r7.get()
            java.util.Queue r7 = (java.util.Queue) r7
        L_0x0054:
            java.lang.Object r2 = r7.poll()
            java.lang.Runnable r2 = (java.lang.Runnable) r2
            if (r2 == 0) goto L_0x003c
            r2.run()     // Catch:{ all -> 0x0060 }
            goto L_0x0054
        L_0x0060:
            r3 = move-exception
            io.netty.util.internal.logging.InternalLogger r4 = logger
            r4.info(r0, r2, r3)
            goto L_0x0054
        L_0x0067:
            r7 = move-exception
            io.netty.util.concurrent.FastThreadLocal<java.util.Queue<java.lang.Runnable>> r2 = DELAYED_RUNNABLES
            java.lang.Object r2 = r2.get()
            java.util.Queue r2 = (java.util.Queue) r2
        L_0x0070:
            java.lang.Object r3 = r2.poll()
            java.lang.Runnable r3 = (java.lang.Runnable) r3
            if (r3 == 0) goto L_0x0083
            r3.run()     // Catch:{ all -> 0x007c }
            goto L_0x0070
        L_0x007c:
            r4 = move-exception
            io.netty.util.internal.logging.InternalLogger r5 = logger
            r5.info(r0, r3, r4)
            goto L_0x0070
        L_0x0083:
            io.netty.util.concurrent.FastThreadLocal<java.lang.Boolean> r0 = RUNNING
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r0.set(r1)
            throw r7
        L_0x008d:
            io.netty.util.concurrent.FastThreadLocal<java.util.Queue<java.lang.Runnable>> r0 = DELAYED_RUNNABLES
            java.lang.Object r0 = r0.get()
            java.util.Queue r0 = (java.util.Queue) r0
            r0.add(r7)
        L_0x0098:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.util.concurrent.ImmediateEventExecutor.execute(java.lang.Runnable):void");
    }

    public <V> Promise<V> newPromise() {
        return new ImmediatePromise(this);
    }

    public <V> ProgressivePromise<V> newProgressivePromise() {
        return new ImmediateProgressivePromise(this);
    }

    static class ImmediatePromise<V> extends DefaultPromise<V> {
        /* access modifiers changed from: protected */
        public void checkDeadLock() {
        }

        ImmediatePromise(EventExecutor eventExecutor) {
            super(eventExecutor);
        }
    }

    static class ImmediateProgressivePromise<V> extends DefaultProgressivePromise<V> {
        /* access modifiers changed from: protected */
        public void checkDeadLock() {
        }

        ImmediateProgressivePromise(EventExecutor eventExecutor) {
            super(eventExecutor);
        }
    }
}
