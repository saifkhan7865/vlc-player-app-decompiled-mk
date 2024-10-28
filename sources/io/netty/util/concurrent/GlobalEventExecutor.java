package io.netty.util.concurrent;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PriorityQueue;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.ThreadExecutorMap;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public final class GlobalEventExecutor extends AbstractScheduledEventExecutor implements OrderedEventExecutor {
    public static final GlobalEventExecutor INSTANCE = new GlobalEventExecutor();
    private static final long SCHEDULE_QUIET_PERIOD_INTERVAL;
    /* access modifiers changed from: private */
    public static final InternalLogger logger;
    final ScheduledFutureTask<Void> quietPeriodTask;
    /* access modifiers changed from: private */
    public final AtomicBoolean started;
    final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue();
    private final TaskRunner taskRunner;
    private final Future<?> terminationFuture;
    volatile Thread thread;
    final ThreadFactory threadFactory;

    public boolean awaitTermination(long j, TimeUnit timeUnit) {
        return false;
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

    static {
        InternalLogger instance = InternalLoggerFactory.getInstance((Class<?>) GlobalEventExecutor.class);
        logger = instance;
        int i = 1;
        int i2 = SystemPropertyUtil.getInt("io.netty.globalEventExecutor.quietPeriodSeconds", 1);
        if (i2 > 0) {
            i = i2;
        }
        instance.debug("-Dio.netty.globalEventExecutor.quietPeriodSeconds: {}", (Object) Integer.valueOf(i));
        SCHEDULE_QUIET_PERIOD_INTERVAL = TimeUnit.SECONDS.toNanos((long) i);
    }

    private GlobalEventExecutor() {
        Callable callable = Executors.callable(new Runnable() {
            public void run() {
            }
        }, (Object) null);
        long currentTimeNanos = getCurrentTimeNanos();
        long j = SCHEDULE_QUIET_PERIOD_INTERVAL;
        ScheduledFutureTask scheduledFutureTask = new ScheduledFutureTask((AbstractScheduledEventExecutor) this, callable, deadlineNanos(currentTimeNanos, j), -j);
        this.quietPeriodTask = scheduledFutureTask;
        this.taskRunner = new TaskRunner();
        this.started = new AtomicBoolean();
        this.terminationFuture = new FailedFuture(this, new UnsupportedOperationException());
        scheduledTaskQueue().add(scheduledFutureTask);
        this.threadFactory = ThreadExecutorMap.apply((ThreadFactory) new DefaultThreadFactory(DefaultThreadFactory.toPoolName(getClass()), false, 5, (ThreadGroup) null), (EventExecutor) this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.Runnable} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Runnable takeTask() {
        /*
            r7 = this;
            java.util.concurrent.BlockingQueue<java.lang.Runnable> r0 = r7.taskQueue
        L_0x0002:
            io.netty.util.concurrent.ScheduledFutureTask r1 = r7.peekScheduledTask()
            r2 = 0
            if (r1 != 0) goto L_0x0011
            java.lang.Object r0 = r0.take()     // Catch:{ InterruptedException -> 0x0010 }
            java.lang.Runnable r0 = (java.lang.Runnable) r0     // Catch:{ InterruptedException -> 0x0010 }
            r2 = r0
        L_0x0010:
            return r2
        L_0x0011:
            long r3 = r1.delayNanos()
            r5 = 0
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 <= 0) goto L_0x0026
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ InterruptedException -> 0x0025 }
            java.lang.Object r1 = r0.poll(r3, r1)     // Catch:{ InterruptedException -> 0x0025 }
            java.lang.Runnable r1 = (java.lang.Runnable) r1     // Catch:{ InterruptedException -> 0x0025 }
            r2 = r1
            goto L_0x0026
        L_0x0025:
            return r2
        L_0x0026:
            if (r2 != 0) goto L_0x0032
            r7.fetchFromScheduledTaskQueue()
            java.lang.Object r1 = r0.poll()
            r2 = r1
            java.lang.Runnable r2 = (java.lang.Runnable) r2
        L_0x0032:
            if (r2 == 0) goto L_0x0002
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.util.concurrent.GlobalEventExecutor.takeTask():java.lang.Runnable");
    }

    private void fetchFromScheduledTaskQueue() {
        long currentTimeNanos = getCurrentTimeNanos();
        Runnable pollScheduledTask = pollScheduledTask(currentTimeNanos);
        while (pollScheduledTask != null) {
            this.taskQueue.add(pollScheduledTask);
            pollScheduledTask = pollScheduledTask(currentTimeNanos);
        }
    }

    public int pendingTasks() {
        return this.taskQueue.size();
    }

    private void addTask(Runnable runnable) {
        this.taskQueue.add(ObjectUtil.checkNotNull(runnable, "task"));
    }

    public boolean inEventLoop(Thread thread2) {
        return thread2 == this.thread;
    }

    public Future<?> shutdownGracefully(long j, long j2, TimeUnit timeUnit) {
        return terminationFuture();
    }

    public Future<?> terminationFuture() {
        return this.terminationFuture;
    }

    @Deprecated
    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    public boolean awaitInactivity(long j, TimeUnit timeUnit) throws InterruptedException {
        ObjectUtil.checkNotNull(timeUnit, "unit");
        Thread thread2 = this.thread;
        if (thread2 != null) {
            thread2.join(timeUnit.toMillis(j));
            return !thread2.isAlive();
        }
        throw new IllegalStateException("thread was not started");
    }

    public void execute(Runnable runnable) {
        execute0(runnable);
    }

    private void execute0(Runnable runnable) {
        addTask((Runnable) ObjectUtil.checkNotNull(runnable, "task"));
        if (!inEventLoop()) {
            startThread();
        }
    }

    private void startThread() {
        if (this.started.compareAndSet(false, true)) {
            final Thread newThread = this.threadFactory.newThread(this.taskRunner);
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    newThread.setContextClassLoader((ClassLoader) null);
                    return null;
                }
            });
            this.thread = newThread;
            newThread.start();
        }
    }

    final class TaskRunner implements Runnable {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<GlobalEventExecutor> cls = GlobalEventExecutor.class;
        }

        TaskRunner() {
        }

        public void run() {
            while (true) {
                Runnable takeTask = GlobalEventExecutor.this.takeTask();
                if (takeTask != null) {
                    try {
                        AbstractEventExecutor.runTask(takeTask);
                    } catch (Throwable th) {
                        GlobalEventExecutor.logger.warn("Unexpected exception from the global event executor: ", th);
                    }
                    if (takeTask != GlobalEventExecutor.this.quietPeriodTask) {
                        continue;
                    }
                }
                PriorityQueue priorityQueue = GlobalEventExecutor.this.scheduledTaskQueue;
                if (GlobalEventExecutor.this.taskQueue.isEmpty() && (priorityQueue == null || priorityQueue.size() == 1)) {
                    GlobalEventExecutor.this.started.compareAndSet(true, false);
                    if (GlobalEventExecutor.this.taskQueue.isEmpty() || !GlobalEventExecutor.this.started.compareAndSet(false, true)) {
                        return;
                    }
                }
            }
        }
    }
}
