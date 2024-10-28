package io.netty.util.concurrent;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import io.ktor.server.auth.OAuth2RequestParameters;
import io.netty.util.concurrent.AbstractEventExecutor;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.ThreadExecutorMap;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public abstract class SingleThreadEventExecutor extends AbstractScheduledEventExecutor implements OrderedEventExecutor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int DEFAULT_MAX_PENDING_EXECUTOR_TASKS = Math.max(16, SystemPropertyUtil.getInt("io.netty.eventexecutor.maxPendingTasks", Integer.MAX_VALUE));
    private static final Runnable NOOP_TASK = new Runnable() {
        public void run() {
        }
    };
    private static final AtomicReferenceFieldUpdater<SingleThreadEventExecutor, ThreadProperties> PROPERTIES_UPDATER;
    private static final long SCHEDULE_PURGE_INTERVAL = TimeUnit.SECONDS.toNanos(1);
    /* access modifiers changed from: private */
    public static final AtomicIntegerFieldUpdater<SingleThreadEventExecutor> STATE_UPDATER;
    private static final int ST_NOT_STARTED = 1;
    private static final int ST_SHUTDOWN = 4;
    private static final int ST_SHUTTING_DOWN = 3;
    private static final int ST_STARTED = 2;
    private static final int ST_TERMINATED = 5;
    /* access modifiers changed from: private */
    public static final InternalLogger logger;
    private final boolean addTaskWakesUp;
    private final Executor executor;
    private volatile long gracefulShutdownQuietPeriod;
    /* access modifiers changed from: private */
    public long gracefulShutdownStartTime;
    private volatile long gracefulShutdownTimeout;
    /* access modifiers changed from: private */
    public volatile boolean interrupted;
    private long lastExecutionTime;
    private final int maxPendingTasks;
    private final RejectedExecutionHandler rejectedExecutionHandler;
    /* access modifiers changed from: private */
    public final Set<Runnable> shutdownHooks;
    /* access modifiers changed from: private */
    public volatile int state;
    private final Queue<Runnable> taskQueue;
    /* access modifiers changed from: private */
    public final Promise<?> terminationFuture;
    /* access modifiers changed from: private */
    public volatile Thread thread;
    /* access modifiers changed from: private */
    public final CountDownLatch threadLock;
    private volatile ThreadProperties threadProperties;

    @Deprecated
    protected interface NonWakeupRunnable extends AbstractEventExecutor.LazyRunnable {
    }

    /* access modifiers changed from: protected */
    public void afterRunningAllTasks() {
    }

    /* access modifiers changed from: protected */
    public void cleanup() {
    }

    /* access modifiers changed from: protected */
    public abstract void run();

    /* access modifiers changed from: protected */
    public boolean wakesUpForTask(Runnable runnable) {
        return true;
    }

    static {
        Class<SingleThreadEventExecutor> cls = SingleThreadEventExecutor.class;
        logger = InternalLoggerFactory.getInstance((Class<?>) cls);
        STATE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(cls, OAuth2RequestParameters.State);
        PROPERTIES_UPDATER = AtomicReferenceFieldUpdater.newUpdater(cls, ThreadProperties.class, "threadProperties");
    }

    protected SingleThreadEventExecutor(EventExecutorGroup eventExecutorGroup, ThreadFactory threadFactory, boolean z) {
        this(eventExecutorGroup, (Executor) new ThreadPerTaskExecutor(threadFactory), z);
    }

    protected SingleThreadEventExecutor(EventExecutorGroup eventExecutorGroup, ThreadFactory threadFactory, boolean z, int i, RejectedExecutionHandler rejectedExecutionHandler2) {
        this(eventExecutorGroup, (Executor) new ThreadPerTaskExecutor(threadFactory), z, i, rejectedExecutionHandler2);
    }

    protected SingleThreadEventExecutor(EventExecutorGroup eventExecutorGroup, Executor executor2, boolean z) {
        this(eventExecutorGroup, executor2, z, DEFAULT_MAX_PENDING_EXECUTOR_TASKS, RejectedExecutionHandlers.reject());
    }

    protected SingleThreadEventExecutor(EventExecutorGroup eventExecutorGroup, Executor executor2, boolean z, int i, RejectedExecutionHandler rejectedExecutionHandler2) {
        super(eventExecutorGroup);
        this.threadLock = new CountDownLatch(1);
        this.shutdownHooks = new LinkedHashSet();
        this.state = 1;
        this.terminationFuture = new DefaultPromise(GlobalEventExecutor.INSTANCE);
        this.addTaskWakesUp = z;
        int max = Math.max(16, i);
        this.maxPendingTasks = max;
        this.executor = ThreadExecutorMap.apply(executor2, (EventExecutor) this);
        this.taskQueue = newTaskQueue(max);
        this.rejectedExecutionHandler = (RejectedExecutionHandler) ObjectUtil.checkNotNull(rejectedExecutionHandler2, "rejectedHandler");
    }

    protected SingleThreadEventExecutor(EventExecutorGroup eventExecutorGroup, Executor executor2, boolean z, Queue<Runnable> queue, RejectedExecutionHandler rejectedExecutionHandler2) {
        super(eventExecutorGroup);
        this.threadLock = new CountDownLatch(1);
        this.shutdownHooks = new LinkedHashSet();
        this.state = 1;
        this.terminationFuture = new DefaultPromise(GlobalEventExecutor.INSTANCE);
        this.addTaskWakesUp = z;
        this.maxPendingTasks = DEFAULT_MAX_PENDING_EXECUTOR_TASKS;
        this.executor = ThreadExecutorMap.apply(executor2, (EventExecutor) this);
        this.taskQueue = (Queue) ObjectUtil.checkNotNull(queue, "taskQueue");
        this.rejectedExecutionHandler = (RejectedExecutionHandler) ObjectUtil.checkNotNull(rejectedExecutionHandler2, "rejectedHandler");
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public Queue<Runnable> newTaskQueue() {
        return newTaskQueue(this.maxPendingTasks);
    }

    /* access modifiers changed from: protected */
    public Queue<Runnable> newTaskQueue(int i) {
        return new LinkedBlockingQueue(i);
    }

    /* access modifiers changed from: protected */
    public void interruptThread() {
        Thread thread2 = this.thread;
        if (thread2 == null) {
            this.interrupted = true;
        } else {
            thread2.interrupt();
        }
    }

    /* access modifiers changed from: protected */
    public Runnable pollTask() {
        return pollTaskFrom(this.taskQueue);
    }

    protected static Runnable pollTaskFrom(Queue<Runnable> queue) {
        Runnable poll;
        do {
            poll = queue.poll();
        } while (poll == WAKEUP_TASK);
        return poll;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.Runnable} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Runnable takeTask() {
        /*
            r7 = this;
            java.util.Queue<java.lang.Runnable> r0 = r7.taskQueue
            boolean r1 = r0 instanceof java.util.concurrent.BlockingQueue
            if (r1 == 0) goto L_0x0040
            java.util.concurrent.BlockingQueue r0 = (java.util.concurrent.BlockingQueue) r0
        L_0x0008:
            io.netty.util.concurrent.ScheduledFutureTask r1 = r7.peekScheduledTask()
            r2 = 0
            if (r1 != 0) goto L_0x001c
            java.lang.Object r0 = r0.take()     // Catch:{ InterruptedException -> 0x001b }
            java.lang.Runnable r0 = (java.lang.Runnable) r0     // Catch:{ InterruptedException -> 0x001b }
            java.lang.Runnable r1 = WAKEUP_TASK     // Catch:{ InterruptedException -> 0x001a }
            if (r0 != r1) goto L_0x001a
            goto L_0x001b
        L_0x001a:
            r2 = r0
        L_0x001b:
            return r2
        L_0x001c:
            long r3 = r1.delayNanos()
            r5 = 0
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 <= 0) goto L_0x0031
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ InterruptedException -> 0x0030 }
            java.lang.Object r1 = r0.poll(r3, r1)     // Catch:{ InterruptedException -> 0x0030 }
            java.lang.Runnable r1 = (java.lang.Runnable) r1     // Catch:{ InterruptedException -> 0x0030 }
            r2 = r1
            goto L_0x0031
        L_0x0030:
            return r2
        L_0x0031:
            if (r2 != 0) goto L_0x003d
            r7.fetchFromScheduledTaskQueue()
            java.lang.Object r1 = r0.poll()
            r2 = r1
            java.lang.Runnable r2 = (java.lang.Runnable) r2
        L_0x003d:
            if (r2 == 0) goto L_0x0008
            return r2
        L_0x0040:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>()
            goto L_0x0047
        L_0x0046:
            throw r0
        L_0x0047:
            goto L_0x0046
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.util.concurrent.SingleThreadEventExecutor.takeTask():java.lang.Runnable");
    }

    private boolean fetchFromScheduledTaskQueue() {
        Runnable pollScheduledTask;
        if (this.scheduledTaskQueue == null || this.scheduledTaskQueue.isEmpty()) {
            return true;
        }
        long currentTimeNanos = getCurrentTimeNanos();
        do {
            pollScheduledTask = pollScheduledTask(currentTimeNanos);
            if (pollScheduledTask == null) {
                return true;
            }
        } while (this.taskQueue.offer(pollScheduledTask));
        this.scheduledTaskQueue.add((ScheduledFutureTask) pollScheduledTask);
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
        r2 = getCurrentTimeNanos();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean executeExpiredScheduledTasks() {
        /*
            r4 = this;
            io.netty.util.internal.PriorityQueue r0 = r4.scheduledTaskQueue
            r1 = 0
            if (r0 == 0) goto L_0x0024
            io.netty.util.internal.PriorityQueue r0 = r4.scheduledTaskQueue
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x000e
            goto L_0x0024
        L_0x000e:
            long r2 = r4.getCurrentTimeNanos()
            java.lang.Runnable r0 = r4.pollScheduledTask(r2)
            if (r0 != 0) goto L_0x0019
            return r1
        L_0x0019:
            safeExecute(r0)
            java.lang.Runnable r0 = r4.pollScheduledTask(r2)
            if (r0 != 0) goto L_0x0019
            r0 = 1
            return r0
        L_0x0024:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.util.concurrent.SingleThreadEventExecutor.executeExpiredScheduledTasks():boolean");
    }

    /* access modifiers changed from: protected */
    public Runnable peekTask() {
        return this.taskQueue.peek();
    }

    /* access modifiers changed from: protected */
    public boolean hasTasks() {
        return !this.taskQueue.isEmpty();
    }

    public int pendingTasks() {
        return this.taskQueue.size();
    }

    /* access modifiers changed from: protected */
    public void addTask(Runnable runnable) {
        ObjectUtil.checkNotNull(runnable, "task");
        if (!offerTask(runnable)) {
            reject(runnable);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean offerTask(Runnable runnable) {
        if (isShutdown()) {
            reject();
        }
        return this.taskQueue.offer(runnable);
    }

    /* access modifiers changed from: protected */
    public boolean removeTask(Runnable runnable) {
        return this.taskQueue.remove(ObjectUtil.checkNotNull(runnable, "task"));
    }

    /* access modifiers changed from: protected */
    public boolean runAllTasks() {
        boolean fetchFromScheduledTaskQueue;
        boolean z = false;
        do {
            fetchFromScheduledTaskQueue = fetchFromScheduledTaskQueue();
            if (runAllTasksFrom(this.taskQueue)) {
                z = true;
                continue;
            }
        } while (!fetchFromScheduledTaskQueue);
        if (z) {
            this.lastExecutionTime = getCurrentTimeNanos();
        }
        afterRunningAllTasks();
        return z;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x0002 A[LOOP:0: B:1:0x0002->B:4:0x0011, LOOP_START, PHI: r1 
      PHI: (r1v1 int) = (r1v0 int), (r1v3 int) binds: [B:0:0x0000, B:4:0x0011] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean runScheduledAndExecutorTasks(int r5) {
        /*
            r4 = this;
            r0 = 0
            r1 = 0
        L_0x0002:
            java.util.Queue<java.lang.Runnable> r2 = r4.taskQueue
            boolean r2 = r4.runExistingTasksFrom(r2)
            boolean r3 = r4.executeExpiredScheduledTasks()
            r2 = r2 | r3
            if (r2 == 0) goto L_0x0013
            int r1 = r1 + 1
            if (r1 < r5) goto L_0x0002
        L_0x0013:
            if (r1 <= 0) goto L_0x001b
            long r2 = r4.getCurrentTimeNanos()
            r4.lastExecutionTime = r2
        L_0x001b:
            r4.afterRunningAllTasks()
            if (r1 <= 0) goto L_0x0021
            r0 = 1
        L_0x0021:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.util.concurrent.SingleThreadEventExecutor.runScheduledAndExecutorTasks(int):boolean");
    }

    /* access modifiers changed from: protected */
    public final boolean runAllTasksFrom(Queue<Runnable> queue) {
        Runnable pollTaskFrom = pollTaskFrom(queue);
        if (pollTaskFrom == null) {
            return false;
        }
        do {
            safeExecute(pollTaskFrom);
            pollTaskFrom = pollTaskFrom(queue);
        } while (pollTaskFrom != null);
        return true;
    }

    private boolean runExistingTasksFrom(Queue<Runnable> queue) {
        Runnable poll;
        Runnable pollTaskFrom = pollTaskFrom(queue);
        if (pollTaskFrom == null) {
            return false;
        }
        int min = Math.min(this.maxPendingTasks, queue.size());
        safeExecute(pollTaskFrom);
        while (true) {
            int i = min - 1;
            if (min <= 0 || (poll = queue.poll()) == null) {
                return true;
            }
            safeExecute(poll);
            min = i;
        }
    }

    /* access modifiers changed from: protected */
    public boolean runAllTasks(long j) {
        long j2;
        fetchFromScheduledTaskQueue();
        Runnable pollTask = pollTask();
        if (pollTask == null) {
            afterRunningAllTasks();
            return false;
        }
        long currentTimeNanos = j > 0 ? getCurrentTimeNanos() + j : 0;
        long j3 = 0;
        while (true) {
            safeExecute(pollTask);
            j3++;
            if ((63 & j3) == 0) {
                j2 = getCurrentTimeNanos();
                if (j2 >= currentTimeNanos) {
                    break;
                }
            }
            pollTask = pollTask();
            if (pollTask == null) {
                j2 = getCurrentTimeNanos();
                break;
            }
        }
        afterRunningAllTasks();
        this.lastExecutionTime = j2;
        return true;
    }

    /* access modifiers changed from: protected */
    public long delayNanos(long j) {
        long initialNanoTime = j - initialNanoTime();
        ScheduledFutureTask<?> peekScheduledTask = peekScheduledTask();
        if (peekScheduledTask == null) {
            return SCHEDULE_PURGE_INTERVAL;
        }
        return peekScheduledTask.delayNanos(initialNanoTime);
    }

    /* access modifiers changed from: protected */
    public long deadlineNanos() {
        ScheduledFutureTask<?> peekScheduledTask = peekScheduledTask();
        if (peekScheduledTask == null) {
            return getCurrentTimeNanos() + SCHEDULE_PURGE_INTERVAL;
        }
        return peekScheduledTask.deadlineNanos();
    }

    /* access modifiers changed from: protected */
    public void updateLastExecutionTime() {
        this.lastExecutionTime = getCurrentTimeNanos();
    }

    /* access modifiers changed from: protected */
    public void wakeup(boolean z) {
        if (!z) {
            this.taskQueue.offer(WAKEUP_TASK);
        }
    }

    public boolean inEventLoop(Thread thread2) {
        return thread2 == this.thread;
    }

    public void addShutdownHook(final Runnable runnable) {
        if (inEventLoop()) {
            this.shutdownHooks.add(runnable);
        } else {
            execute(new Runnable() {
                public void run() {
                    SingleThreadEventExecutor.this.shutdownHooks.add(runnable);
                }
            });
        }
    }

    public void removeShutdownHook(final Runnable runnable) {
        if (inEventLoop()) {
            this.shutdownHooks.remove(runnable);
        } else {
            execute(new Runnable() {
                public void run() {
                    SingleThreadEventExecutor.this.shutdownHooks.remove(runnable);
                }
            });
        }
    }

    private boolean runShutdownHooks() {
        boolean z = false;
        while (!this.shutdownHooks.isEmpty()) {
            ArrayList<Runnable> arrayList = new ArrayList<>(this.shutdownHooks);
            this.shutdownHooks.clear();
            for (Runnable runTask : arrayList) {
                try {
                    runTask(runTask);
                } catch (Throwable th) {
                    logger.warn("Shutdown hook raised an exception.", th);
                }
                z = true;
            }
        }
        if (z) {
            this.lastExecutionTime = getCurrentTimeNanos();
        }
        return z;
    }

    public Future<?> shutdownGracefully(long j, long j2, TimeUnit timeUnit) {
        ObjectUtil.checkPositiveOrZero(j, "quietPeriod");
        if (j2 >= j) {
            ObjectUtil.checkNotNull(timeUnit, "unit");
            if (isShuttingDown()) {
                return terminationFuture();
            }
            boolean inEventLoop = inEventLoop();
            while (!isShuttingDown()) {
                int i = this.state;
                int i2 = 3;
                boolean z = true;
                if (!(inEventLoop || i == 1 || i == 2)) {
                    z = false;
                    i2 = i;
                }
                if (STATE_UPDATER.compareAndSet(this, i, i2)) {
                    this.gracefulShutdownQuietPeriod = timeUnit.toNanos(j);
                    this.gracefulShutdownTimeout = timeUnit.toNanos(j2);
                    if (ensureThreadStarted(i)) {
                        return this.terminationFuture;
                    }
                    if (z) {
                        this.taskQueue.offer(WAKEUP_TASK);
                        if (!this.addTaskWakesUp) {
                            wakeup(inEventLoop);
                        }
                    }
                    return terminationFuture();
                }
            }
            return terminationFuture();
        }
        throw new IllegalArgumentException("timeout: " + j2 + " (expected >= quietPeriod (" + j + "))");
    }

    public Future<?> terminationFuture() {
        return this.terminationFuture;
    }

    @Deprecated
    public void shutdown() {
        if (!isShutdown()) {
            boolean inEventLoop = inEventLoop();
            while (!isShuttingDown()) {
                int i = this.state;
                int i2 = 4;
                boolean z = true;
                if (!(inEventLoop || i == 1 || i == 2 || i == 3)) {
                    z = false;
                    i2 = i;
                }
                if (STATE_UPDATER.compareAndSet(this, i, i2)) {
                    if (!ensureThreadStarted(i) && z) {
                        this.taskQueue.offer(WAKEUP_TASK);
                        if (!this.addTaskWakesUp) {
                            wakeup(inEventLoop);
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
        }
    }

    public boolean isShuttingDown() {
        return this.state >= 3;
    }

    public boolean isShutdown() {
        return this.state >= 4;
    }

    public boolean isTerminated() {
        return this.state == 5;
    }

    /* access modifiers changed from: protected */
    public boolean confirmShutdown() {
        if (!isShuttingDown()) {
            return false;
        }
        if (inEventLoop()) {
            cancelScheduledTasks();
            if (this.gracefulShutdownStartTime == 0) {
                this.gracefulShutdownStartTime = getCurrentTimeNanos();
            }
            if (!runAllTasks() && !runShutdownHooks()) {
                long currentTimeNanos = getCurrentTimeNanos();
                if (isShutdown() || currentTimeNanos - this.gracefulShutdownStartTime > this.gracefulShutdownTimeout || currentTimeNanos - this.lastExecutionTime > this.gracefulShutdownQuietPeriod) {
                    return true;
                }
                this.taskQueue.offer(WAKEUP_TASK);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException unused) {
                }
                return false;
            } else if (isShutdown() || this.gracefulShutdownQuietPeriod == 0) {
                return true;
            } else {
                this.taskQueue.offer(WAKEUP_TASK);
                return false;
            }
        } else {
            throw new IllegalStateException("must be invoked from an event loop");
        }
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        ObjectUtil.checkNotNull(timeUnit, "unit");
        if (!inEventLoop()) {
            this.threadLock.await(j, timeUnit);
            return isTerminated();
        }
        throw new IllegalStateException("cannot await termination of the current thread");
    }

    public void execute(Runnable runnable) {
        execute0(runnable);
    }

    public void lazyExecute(Runnable runnable) {
        lazyExecute0(runnable);
    }

    private void execute0(Runnable runnable) {
        ObjectUtil.checkNotNull(runnable, "task");
        execute(runnable, wakesUpForTask(runnable));
    }

    private void lazyExecute0(Runnable runnable) {
        execute((Runnable) ObjectUtil.checkNotNull(runnable, "task"), false);
    }

    private void execute(Runnable runnable, boolean z) {
        boolean inEventLoop = inEventLoop();
        addTask(runnable);
        if (!inEventLoop) {
            startThread();
            if (isShutdown()) {
                try {
                    if (removeTask(runnable)) {
                        reject();
                    }
                } catch (UnsupportedOperationException unused) {
                }
            }
        }
        if (!this.addTaskWakesUp && z) {
            wakeup(inEventLoop);
        }
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
        throwIfInEventLoop("invokeAny");
        return super.invokeAny(collection);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        throwIfInEventLoop("invokeAny");
        return super.invokeAny(collection, j, timeUnit);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        throwIfInEventLoop("invokeAll");
        return super.invokeAll(collection);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        throwIfInEventLoop("invokeAll");
        return super.invokeAll(collection, j, timeUnit);
    }

    private void throwIfInEventLoop(String str) {
        if (inEventLoop()) {
            throw new RejectedExecutionException("Calling " + str + " from within the EventLoop is not allowed");
        }
    }

    public final ThreadProperties threadProperties() {
        ThreadProperties threadProperties2 = this.threadProperties;
        if (threadProperties2 != null) {
            return threadProperties2;
        }
        Thread thread2 = this.thread;
        if (thread2 == null) {
            submit(NOOP_TASK).syncUninterruptibly();
            thread2 = this.thread;
        }
        DefaultThreadProperties defaultThreadProperties = new DefaultThreadProperties(thread2);
        return !AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(PROPERTIES_UPDATER, this, (Object) null, defaultThreadProperties) ? this.threadProperties : defaultThreadProperties;
    }

    protected static void reject() {
        throw new RejectedExecutionException("event executor terminated");
    }

    /* access modifiers changed from: protected */
    public final void reject(Runnable runnable) {
        this.rejectedExecutionHandler.rejected(runnable, this);
    }

    private void startThread() {
        if (this.state == 1 && STATE_UPDATER.compareAndSet(this, 1, 2)) {
            try {
                doStartThread();
            } catch (Throwable th) {
                STATE_UPDATER.compareAndSet(this, 2, 1);
                throw th;
            }
        }
    }

    private boolean ensureThreadStarted(int i) {
        if (i != 1) {
            return false;
        }
        try {
            doStartThread();
            return false;
        } catch (Throwable th) {
            STATE_UPDATER.set(this, 5);
            this.terminationFuture.tryFailure(th);
            if (!(th instanceof Exception)) {
                PlatformDependent.throwException(th);
            }
            return true;
        }
    }

    private void doStartThread() {
        this.executor.execute(new Runnable() {
            /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
                jadx.core.utils.exceptions.JadxOverflowException: 
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
                	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
                */
            /* JADX WARNING: CFG modification limit reached, blocks count: 259 */
            public void run() {
                /*
                    r10 = this;
                    java.lang.String r0 = "An event executor terminated with non-empty task queue ("
                    io.netty.util.concurrent.SingleThreadEventExecutor r1 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    java.lang.Thread r2 = java.lang.Thread.currentThread()
                    java.lang.Thread unused = r1.thread = r2
                    io.netty.util.concurrent.SingleThreadEventExecutor r1 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    boolean r1 = r1.interrupted
                    if (r1 == 0) goto L_0x001c
                    io.netty.util.concurrent.SingleThreadEventExecutor r1 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    java.lang.Thread r1 = r1.thread
                    r1.interrupt()
                L_0x001c:
                    io.netty.util.concurrent.SingleThreadEventExecutor r1 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    r1.updateLastExecutionTime()
                    r1 = 4
                    r2 = 3
                    r3 = 41
                    r4 = 0
                    r5 = 5
                    io.netty.util.concurrent.SingleThreadEventExecutor r6 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x01ae }
                    r6.run()     // Catch:{ all -> 0x01ae }
                L_0x002c:
                    io.netty.util.concurrent.SingleThreadEventExecutor r6 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    int r6 = r6.state
                    if (r6 >= r2) goto L_0x0040
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r7 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER
                    io.netty.util.concurrent.SingleThreadEventExecutor r8 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    boolean r6 = r7.compareAndSet(r8, r6, r2)
                    if (r6 == 0) goto L_0x002c
                L_0x0040:
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    long r6 = r2.gracefulShutdownStartTime
                    r8 = 0
                    int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                    if (r2 != 0) goto L_0x0063
                    io.netty.util.internal.logging.InternalLogger r2 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    boolean r2 = r2.isErrorEnabled()
                    if (r2 == 0) goto L_0x0063
                    io.netty.util.internal.logging.InternalLogger r2 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    java.lang.Class<io.netty.util.concurrent.EventExecutor> r6 = io.netty.util.concurrent.EventExecutor.class
                    java.lang.Class<io.netty.util.concurrent.SingleThreadEventExecutor> r6 = io.netty.util.concurrent.SingleThreadEventExecutor.class
                    java.lang.String r6 = "Buggy EventExecutor implementation; SingleThreadEventExecutor.confirmShutdown() must be called before run() implementation terminates."
                    r2.error((java.lang.String) r6)
                L_0x0063:
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x0119 }
                    boolean r2 = r2.confirmShutdown()     // Catch:{ all -> 0x0119 }
                    if (r2 == 0) goto L_0x0063
                L_0x006b:
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x0119 }
                    int r2 = r2.state     // Catch:{ all -> 0x0119 }
                    if (r2 >= r1) goto L_0x007f
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r6 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER     // Catch:{ all -> 0x0119 }
                    io.netty.util.concurrent.SingleThreadEventExecutor r7 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x0119 }
                    boolean r2 = r6.compareAndSet(r7, r2, r1)     // Catch:{ all -> 0x0119 }
                    if (r2 == 0) goto L_0x006b
                L_0x007f:
                    io.netty.util.concurrent.SingleThreadEventExecutor r1 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x0119 }
                    r1.confirmShutdown()     // Catch:{ all -> 0x0119 }
                    io.netty.util.concurrent.SingleThreadEventExecutor r1 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x00d1 }
                    r1.cleanup()     // Catch:{ all -> 0x00d1 }
                    io.netty.util.concurrent.FastThreadLocal.removeAll()
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r1 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    r1.set(r2, r5)
                    io.netty.util.concurrent.SingleThreadEventExecutor r1 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    java.util.concurrent.CountDownLatch r1 = r1.threadLock
                    r1.countDown()
                    io.netty.util.concurrent.SingleThreadEventExecutor r1 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    int r1 = r1.drainTasks()
                    if (r1 <= 0) goto L_0x00c6
                    io.netty.util.internal.logging.InternalLogger r2 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    boolean r2 = r2.isWarnEnabled()
                    if (r2 == 0) goto L_0x00c6
                    io.netty.util.internal.logging.InternalLogger r2 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    r5.<init>(r0)
                L_0x00b9:
                    r5.append(r1)
                    r5.append(r3)
                    java.lang.String r0 = r5.toString()
                    r2.warn((java.lang.String) r0)
                L_0x00c6:
                    io.netty.util.concurrent.SingleThreadEventExecutor r0 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    io.netty.util.concurrent.Promise r0 = r0.terminationFuture
                    r0.setSuccess(r4)
                    goto L_0x0224
                L_0x00d1:
                    r1 = move-exception
                    io.netty.util.concurrent.FastThreadLocal.removeAll()
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r2 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER
                    io.netty.util.concurrent.SingleThreadEventExecutor r6 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    r2.set(r6, r5)
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    java.util.concurrent.CountDownLatch r2 = r2.threadLock
                    r2.countDown()
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    int r2 = r2.drainTasks()
                    if (r2 <= 0) goto L_0x010f
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    boolean r5 = r5.isWarnEnabled()
                    if (r5 == 0) goto L_0x010f
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder
                    r6.<init>(r0)
                    r6.append(r2)
                    r6.append(r3)
                    java.lang.String r0 = r6.toString()
                    r5.warn((java.lang.String) r0)
                L_0x010f:
                    io.netty.util.concurrent.SingleThreadEventExecutor r0 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    io.netty.util.concurrent.Promise r0 = r0.terminationFuture
                    r0.setSuccess(r4)
                    throw r1
                L_0x0119:
                    r1 = move-exception
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x0166 }
                    r2.cleanup()     // Catch:{ all -> 0x0166 }
                    io.netty.util.concurrent.FastThreadLocal.removeAll()
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r2 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER
                    io.netty.util.concurrent.SingleThreadEventExecutor r6 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    r2.set(r6, r5)
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    java.util.concurrent.CountDownLatch r2 = r2.threadLock
                    r2.countDown()
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    int r2 = r2.drainTasks()
                    if (r2 <= 0) goto L_0x015c
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    boolean r5 = r5.isWarnEnabled()
                    if (r5 == 0) goto L_0x015c
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder
                    r6.<init>(r0)
                    r6.append(r2)
                    r6.append(r3)
                    java.lang.String r0 = r6.toString()
                    r5.warn((java.lang.String) r0)
                L_0x015c:
                    io.netty.util.concurrent.SingleThreadEventExecutor r0 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    io.netty.util.concurrent.Promise r0 = r0.terminationFuture
                    r0.setSuccess(r4)
                    throw r1
                L_0x0166:
                    r1 = move-exception
                    io.netty.util.concurrent.FastThreadLocal.removeAll()
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r2 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER
                    io.netty.util.concurrent.SingleThreadEventExecutor r6 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    r2.set(r6, r5)
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    java.util.concurrent.CountDownLatch r2 = r2.threadLock
                    r2.countDown()
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    int r2 = r2.drainTasks()
                    if (r2 <= 0) goto L_0x01a4
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    boolean r5 = r5.isWarnEnabled()
                    if (r5 == 0) goto L_0x01a4
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder
                    r6.<init>(r0)
                    r6.append(r2)
                    r6.append(r3)
                    java.lang.String r0 = r6.toString()
                    r5.warn((java.lang.String) r0)
                L_0x01a4:
                    io.netty.util.concurrent.SingleThreadEventExecutor r0 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    io.netty.util.concurrent.Promise r0 = r0.terminationFuture
                    r0.setSuccess(r4)
                    throw r1
                L_0x01ae:
                    r6 = move-exception
                    io.netty.util.internal.logging.InternalLogger r7 = io.netty.util.concurrent.SingleThreadEventExecutor.logger     // Catch:{ all -> 0x0302 }
                    java.lang.String r8 = "Unexpected exception from an event executor: "
                    r7.warn((java.lang.String) r8, (java.lang.Throwable) r6)     // Catch:{ all -> 0x0302 }
                L_0x01b8:
                    io.netty.util.concurrent.SingleThreadEventExecutor r6 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    int r6 = r6.state
                    if (r6 >= r2) goto L_0x01cc
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r7 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER
                    io.netty.util.concurrent.SingleThreadEventExecutor r8 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    boolean r6 = r7.compareAndSet(r8, r6, r2)
                    if (r6 == 0) goto L_0x01b8
                L_0x01cc:
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x026d }
                    boolean r2 = r2.confirmShutdown()     // Catch:{ all -> 0x026d }
                    if (r2 == 0) goto L_0x01cc
                L_0x01d4:
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x026d }
                    int r2 = r2.state     // Catch:{ all -> 0x026d }
                    if (r2 >= r1) goto L_0x01e8
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r6 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER     // Catch:{ all -> 0x026d }
                    io.netty.util.concurrent.SingleThreadEventExecutor r7 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x026d }
                    boolean r2 = r6.compareAndSet(r7, r2, r1)     // Catch:{ all -> 0x026d }
                    if (r2 == 0) goto L_0x01d4
                L_0x01e8:
                    io.netty.util.concurrent.SingleThreadEventExecutor r1 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x026d }
                    r1.confirmShutdown()     // Catch:{ all -> 0x026d }
                    io.netty.util.concurrent.SingleThreadEventExecutor r1 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x0225 }
                    r1.cleanup()     // Catch:{ all -> 0x0225 }
                    io.netty.util.concurrent.FastThreadLocal.removeAll()
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r1 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    r1.set(r2, r5)
                    io.netty.util.concurrent.SingleThreadEventExecutor r1 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    java.util.concurrent.CountDownLatch r1 = r1.threadLock
                    r1.countDown()
                    io.netty.util.concurrent.SingleThreadEventExecutor r1 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    int r1 = r1.drainTasks()
                    if (r1 <= 0) goto L_0x00c6
                    io.netty.util.internal.logging.InternalLogger r2 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    boolean r2 = r2.isWarnEnabled()
                    if (r2 == 0) goto L_0x00c6
                    io.netty.util.internal.logging.InternalLogger r2 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    r5.<init>(r0)
                    goto L_0x00b9
                L_0x0224:
                    return
                L_0x0225:
                    r1 = move-exception
                    io.netty.util.concurrent.FastThreadLocal.removeAll()
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r2 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER
                    io.netty.util.concurrent.SingleThreadEventExecutor r6 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    r2.set(r6, r5)
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    java.util.concurrent.CountDownLatch r2 = r2.threadLock
                    r2.countDown()
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    int r2 = r2.drainTasks()
                    if (r2 <= 0) goto L_0x0263
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    boolean r5 = r5.isWarnEnabled()
                    if (r5 == 0) goto L_0x0263
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder
                    r6.<init>(r0)
                    r6.append(r2)
                    r6.append(r3)
                    java.lang.String r0 = r6.toString()
                    r5.warn((java.lang.String) r0)
                L_0x0263:
                    io.netty.util.concurrent.SingleThreadEventExecutor r0 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    io.netty.util.concurrent.Promise r0 = r0.terminationFuture
                    r0.setSuccess(r4)
                    throw r1
                L_0x026d:
                    r1 = move-exception
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x02ba }
                    r2.cleanup()     // Catch:{ all -> 0x02ba }
                    io.netty.util.concurrent.FastThreadLocal.removeAll()
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r2 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER
                    io.netty.util.concurrent.SingleThreadEventExecutor r6 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    r2.set(r6, r5)
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    java.util.concurrent.CountDownLatch r2 = r2.threadLock
                    r2.countDown()
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    int r2 = r2.drainTasks()
                    if (r2 <= 0) goto L_0x02b0
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    boolean r5 = r5.isWarnEnabled()
                    if (r5 == 0) goto L_0x02b0
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder
                    r6.<init>(r0)
                    r6.append(r2)
                    r6.append(r3)
                    java.lang.String r0 = r6.toString()
                    r5.warn((java.lang.String) r0)
                L_0x02b0:
                    io.netty.util.concurrent.SingleThreadEventExecutor r0 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    io.netty.util.concurrent.Promise r0 = r0.terminationFuture
                    r0.setSuccess(r4)
                    throw r1
                L_0x02ba:
                    r1 = move-exception
                    io.netty.util.concurrent.FastThreadLocal.removeAll()
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r2 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER
                    io.netty.util.concurrent.SingleThreadEventExecutor r6 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    r2.set(r6, r5)
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    java.util.concurrent.CountDownLatch r2 = r2.threadLock
                    r2.countDown()
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    int r2 = r2.drainTasks()
                    if (r2 <= 0) goto L_0x02f8
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    boolean r5 = r5.isWarnEnabled()
                    if (r5 == 0) goto L_0x02f8
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder
                    r6.<init>(r0)
                    r6.append(r2)
                    r6.append(r3)
                    java.lang.String r0 = r6.toString()
                    r5.warn((java.lang.String) r0)
                L_0x02f8:
                    io.netty.util.concurrent.SingleThreadEventExecutor r0 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    io.netty.util.concurrent.Promise r0 = r0.terminationFuture
                    r0.setSuccess(r4)
                    throw r1
                L_0x0302:
                    r6 = move-exception
                L_0x0303:
                    io.netty.util.concurrent.SingleThreadEventExecutor r7 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    int r7 = r7.state
                    if (r7 >= r2) goto L_0x0317
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r8 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER
                    io.netty.util.concurrent.SingleThreadEventExecutor r9 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    boolean r7 = r8.compareAndSet(r9, r7, r2)
                    if (r7 == 0) goto L_0x0303
                L_0x0317:
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x03cc }
                    boolean r2 = r2.confirmShutdown()     // Catch:{ all -> 0x03cc }
                    if (r2 == 0) goto L_0x0317
                L_0x031f:
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x03cc }
                    int r2 = r2.state     // Catch:{ all -> 0x03cc }
                    if (r2 >= r1) goto L_0x0333
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r7 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER     // Catch:{ all -> 0x03cc }
                    io.netty.util.concurrent.SingleThreadEventExecutor r8 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x03cc }
                    boolean r2 = r7.compareAndSet(r8, r2, r1)     // Catch:{ all -> 0x03cc }
                    if (r2 == 0) goto L_0x031f
                L_0x0333:
                    io.netty.util.concurrent.SingleThreadEventExecutor r1 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x03cc }
                    r1.confirmShutdown()     // Catch:{ all -> 0x03cc }
                    io.netty.util.concurrent.SingleThreadEventExecutor r1 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x0384 }
                    r1.cleanup()     // Catch:{ all -> 0x0384 }
                    io.netty.util.concurrent.FastThreadLocal.removeAll()
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r1 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    r1.set(r2, r5)
                    io.netty.util.concurrent.SingleThreadEventExecutor r1 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    java.util.concurrent.CountDownLatch r1 = r1.threadLock
                    r1.countDown()
                    io.netty.util.concurrent.SingleThreadEventExecutor r1 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    int r1 = r1.drainTasks()
                    if (r1 <= 0) goto L_0x037a
                    io.netty.util.internal.logging.InternalLogger r2 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    boolean r2 = r2.isWarnEnabled()
                    if (r2 == 0) goto L_0x037a
                    io.netty.util.internal.logging.InternalLogger r2 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    r5.<init>(r0)
                    r5.append(r1)
                    r5.append(r3)
                    java.lang.String r0 = r5.toString()
                    r2.warn((java.lang.String) r0)
                L_0x037a:
                    io.netty.util.concurrent.SingleThreadEventExecutor r0 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    io.netty.util.concurrent.Promise r0 = r0.terminationFuture
                    r0.setSuccess(r4)
                    throw r6
                L_0x0384:
                    r1 = move-exception
                    io.netty.util.concurrent.FastThreadLocal.removeAll()
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r2 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER
                    io.netty.util.concurrent.SingleThreadEventExecutor r6 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    r2.set(r6, r5)
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    java.util.concurrent.CountDownLatch r2 = r2.threadLock
                    r2.countDown()
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    int r2 = r2.drainTasks()
                    if (r2 <= 0) goto L_0x03c2
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    boolean r5 = r5.isWarnEnabled()
                    if (r5 == 0) goto L_0x03c2
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder
                    r6.<init>(r0)
                    r6.append(r2)
                    r6.append(r3)
                    java.lang.String r0 = r6.toString()
                    r5.warn((java.lang.String) r0)
                L_0x03c2:
                    io.netty.util.concurrent.SingleThreadEventExecutor r0 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    io.netty.util.concurrent.Promise r0 = r0.terminationFuture
                    r0.setSuccess(r4)
                    throw r1
                L_0x03cc:
                    r1 = move-exception
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this     // Catch:{ all -> 0x0419 }
                    r2.cleanup()     // Catch:{ all -> 0x0419 }
                    io.netty.util.concurrent.FastThreadLocal.removeAll()
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r2 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER
                    io.netty.util.concurrent.SingleThreadEventExecutor r6 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    r2.set(r6, r5)
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    java.util.concurrent.CountDownLatch r2 = r2.threadLock
                    r2.countDown()
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    int r2 = r2.drainTasks()
                    if (r2 <= 0) goto L_0x040f
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    boolean r5 = r5.isWarnEnabled()
                    if (r5 == 0) goto L_0x040f
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder
                    r6.<init>(r0)
                    r6.append(r2)
                    r6.append(r3)
                    java.lang.String r0 = r6.toString()
                    r5.warn((java.lang.String) r0)
                L_0x040f:
                    io.netty.util.concurrent.SingleThreadEventExecutor r0 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    io.netty.util.concurrent.Promise r0 = r0.terminationFuture
                    r0.setSuccess(r4)
                    throw r1
                L_0x0419:
                    r1 = move-exception
                    io.netty.util.concurrent.FastThreadLocal.removeAll()
                    java.util.concurrent.atomic.AtomicIntegerFieldUpdater r2 = io.netty.util.concurrent.SingleThreadEventExecutor.STATE_UPDATER
                    io.netty.util.concurrent.SingleThreadEventExecutor r6 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    r2.set(r6, r5)
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    java.util.concurrent.CountDownLatch r2 = r2.threadLock
                    r2.countDown()
                    io.netty.util.concurrent.SingleThreadEventExecutor r2 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    int r2 = r2.drainTasks()
                    if (r2 <= 0) goto L_0x0457
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    boolean r5 = r5.isWarnEnabled()
                    if (r5 == 0) goto L_0x0457
                    io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.concurrent.SingleThreadEventExecutor.logger
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder
                    r6.<init>(r0)
                    r6.append(r2)
                    r6.append(r3)
                    java.lang.String r0 = r6.toString()
                    r5.warn((java.lang.String) r0)
                L_0x0457:
                    io.netty.util.concurrent.SingleThreadEventExecutor r0 = io.netty.util.concurrent.SingleThreadEventExecutor.this
                    io.netty.util.concurrent.Promise r0 = r0.terminationFuture
                    r0.setSuccess(r4)
                    goto L_0x0462
                L_0x0461:
                    throw r1
                L_0x0462:
                    goto L_0x0461
                */
                throw new UnsupportedOperationException("Method not decompiled: io.netty.util.concurrent.SingleThreadEventExecutor.AnonymousClass4.run():void");
            }
        });
    }

    /* access modifiers changed from: package-private */
    public final int drainTasks() {
        int i = 0;
        while (true) {
            Runnable poll = this.taskQueue.poll();
            if (poll == null) {
                return i;
            }
            if (WAKEUP_TASK != poll) {
                i++;
            }
        }
    }

    private static final class DefaultThreadProperties implements ThreadProperties {
        private final Thread t;

        DefaultThreadProperties(Thread thread) {
            this.t = thread;
        }

        public Thread.State state() {
            return this.t.getState();
        }

        public int priority() {
            return this.t.getPriority();
        }

        public boolean isInterrupted() {
            return this.t.isInterrupted();
        }

        public boolean isDaemon() {
            return this.t.isDaemon();
        }

        public String name() {
            return this.t.getName();
        }

        public long id() {
            return this.t.getId();
        }

        public StackTraceElement[] stackTrace() {
            return this.t.getStackTrace();
        }

        public boolean isAlive() {
            return this.t.isAlive();
        }
    }
}
