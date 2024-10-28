package io.netty.util.concurrent;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.ThrowableUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class DefaultPromise<V> extends AbstractFuture<V> implements Promise<V> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final CauseHolder CANCELLATION_CAUSE_HOLDER;
    /* access modifiers changed from: private */
    public static final StackTraceElement[] CANCELLATION_STACK;
    private static final int MAX_LISTENER_STACK_DEPTH = Math.min(8, SystemPropertyUtil.getInt("io.netty.defaultPromise.maxListenerStackDepth", 8));
    private static final AtomicReferenceFieldUpdater<DefaultPromise, Object> RESULT_UPDATER;
    private static final Object SUCCESS = new Object();
    private static final Object UNCANCELLABLE = new Object();
    private static final InternalLogger logger;
    private static final InternalLogger rejectedExecutionLogger;
    private final EventExecutor executor;
    private GenericFutureListener<? extends Future<?>> listener;
    private DefaultFutureListeners listeners;
    private boolean notifyingListeners;
    private volatile Object result;
    private short waiters;

    static {
        Class<DefaultPromise> cls = DefaultPromise.class;
        logger = InternalLoggerFactory.getInstance((Class<?>) cls);
        rejectedExecutionLogger = InternalLoggerFactory.getInstance(cls.getName() + ".rejectedExecution");
        RESULT_UPDATER = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "result");
        CauseHolder causeHolder = new CauseHolder(StacklessCancellationException.newInstance(cls, "cancel(...)"));
        CANCELLATION_CAUSE_HOLDER = causeHolder;
        CANCELLATION_STACK = causeHolder.cause.getStackTrace();
    }

    public DefaultPromise(EventExecutor eventExecutor) {
        this.executor = (EventExecutor) ObjectUtil.checkNotNull(eventExecutor, "executor");
    }

    protected DefaultPromise() {
        this.executor = null;
    }

    public Promise<V> setSuccess(V v) {
        if (setSuccess0(v)) {
            return this;
        }
        throw new IllegalStateException("complete already: " + this);
    }

    public boolean trySuccess(V v) {
        return setSuccess0(v);
    }

    public Promise<V> setFailure(Throwable th) {
        if (setFailure0(th)) {
            return this;
        }
        throw new IllegalStateException("complete already: " + this, th);
    }

    public boolean tryFailure(Throwable th) {
        return setFailure0(th);
    }

    public boolean setUncancellable() {
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(RESULT_UPDATER, this, (Object) null, UNCANCELLABLE)) {
            return true;
        }
        Object obj = this.result;
        if (!isDone0(obj) || !isCancelled0(obj)) {
            return true;
        }
        return false;
    }

    public boolean isSuccess() {
        Object obj = this.result;
        return (obj == null || obj == UNCANCELLABLE || (obj instanceof CauseHolder)) ? false : true;
    }

    public boolean isCancellable() {
        return this.result == null;
    }

    private static final class LeanCancellationException extends CancellationException {
        private static final long serialVersionUID = 2794674970981187807L;

        private LeanCancellationException() {
        }

        public Throwable fillInStackTrace() {
            setStackTrace(DefaultPromise.CANCELLATION_STACK);
            return this;
        }

        public String toString() {
            return CancellationException.class.getName();
        }
    }

    public Throwable cause() {
        return cause0(this.result);
    }

    private Throwable cause0(Object obj) {
        if (!(obj instanceof CauseHolder)) {
            return null;
        }
        CauseHolder causeHolder = CANCELLATION_CAUSE_HOLDER;
        if (obj == causeHolder) {
            LeanCancellationException leanCancellationException = new LeanCancellationException();
            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(RESULT_UPDATER, this, causeHolder, new CauseHolder(leanCancellationException))) {
                return leanCancellationException;
            }
            obj = this.result;
        }
        return ((CauseHolder) obj).cause;
    }

    public Promise<V> addListener(GenericFutureListener<? extends Future<? super V>> genericFutureListener) {
        ObjectUtil.checkNotNull(genericFutureListener, "listener");
        synchronized (this) {
            addListener0(genericFutureListener);
        }
        if (isDone()) {
            notifyListeners();
        }
        return this;
    }

    public Promise<V> addListeners(GenericFutureListener<? extends Future<? super V>>... genericFutureListenerArr) {
        ObjectUtil.checkNotNull(genericFutureListenerArr, "listeners");
        synchronized (this) {
            int length = genericFutureListenerArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                GenericFutureListener<? extends Future<? super V>> genericFutureListener = genericFutureListenerArr[i];
                if (genericFutureListener == null) {
                    break;
                }
                addListener0(genericFutureListener);
                i++;
            }
        }
        if (isDone()) {
            notifyListeners();
        }
        return this;
    }

    public Promise<V> removeListener(GenericFutureListener<? extends Future<? super V>> genericFutureListener) {
        ObjectUtil.checkNotNull(genericFutureListener, "listener");
        synchronized (this) {
            removeListener0(genericFutureListener);
        }
        return this;
    }

    public Promise<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... genericFutureListenerArr) {
        ObjectUtil.checkNotNull(genericFutureListenerArr, "listeners");
        synchronized (this) {
            int length = genericFutureListenerArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                GenericFutureListener<? extends Future<? super V>> genericFutureListener = genericFutureListenerArr[i];
                if (genericFutureListener == null) {
                    break;
                }
                removeListener0(genericFutureListener);
                i++;
            }
        }
        return this;
    }

    /* JADX INFO: finally extract failed */
    public Promise<V> await() throws InterruptedException {
        if (isDone()) {
            return this;
        }
        if (!Thread.interrupted()) {
            checkDeadLock();
            synchronized (this) {
                while (!isDone()) {
                    incWaiters();
                    try {
                        wait();
                        decWaiters();
                    } catch (Throwable th) {
                        decWaiters();
                        throw th;
                    }
                }
            }
            return this;
        }
        throw new InterruptedException(toString());
    }

    public Promise<V> awaitUninterruptibly() {
        boolean z;
        if (isDone()) {
            return this;
        }
        checkDeadLock();
        synchronized (this) {
            z = false;
            while (!isDone()) {
                incWaiters();
                try {
                    wait();
                    decWaiters();
                } catch (InterruptedException unused) {
                    decWaiters();
                    z = true;
                } catch (Throwable th) {
                    decWaiters();
                    throw th;
                }
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return this;
    }

    public boolean await(long j, TimeUnit timeUnit) throws InterruptedException {
        return await0(timeUnit.toNanos(j), true);
    }

    public boolean await(long j) throws InterruptedException {
        return await0(TimeUnit.MILLISECONDS.toNanos(j), true);
    }

    public boolean awaitUninterruptibly(long j, TimeUnit timeUnit) {
        try {
            return await0(timeUnit.toNanos(j), false);
        } catch (InterruptedException unused) {
            throw new InternalError();
        }
    }

    public boolean awaitUninterruptibly(long j) {
        try {
            return await0(TimeUnit.MILLISECONDS.toNanos(j), false);
        } catch (InterruptedException unused) {
            throw new InternalError();
        }
    }

    public V getNow() {
        V v = this.result;
        if ((v instanceof CauseHolder) || v == SUCCESS || v == UNCANCELLABLE) {
            return null;
        }
        return v;
    }

    public V get() throws InterruptedException, ExecutionException {
        V v = this.result;
        if (!isDone0(v)) {
            await();
            v = this.result;
        }
        if (v == SUCCESS || v == UNCANCELLABLE) {
            return null;
        }
        Throwable cause0 = cause0(v);
        if (cause0 == null) {
            return v;
        }
        if (cause0 instanceof CancellationException) {
            throw ((CancellationException) cause0);
        }
        throw new ExecutionException(cause0);
    }

    public V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        V v = this.result;
        if (!isDone0(v)) {
            if (await(j, timeUnit)) {
                v = this.result;
            } else {
                throw new TimeoutException();
            }
        }
        if (v == SUCCESS || v == UNCANCELLABLE) {
            return null;
        }
        Throwable cause0 = cause0(v);
        if (cause0 == null) {
            return v;
        }
        if (cause0 instanceof CancellationException) {
            throw ((CancellationException) cause0);
        }
        throw new ExecutionException(cause0);
    }

    public boolean cancel(boolean z) {
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(RESULT_UPDATER, this, (Object) null, CANCELLATION_CAUSE_HOLDER)) {
            return false;
        }
        if (!checkNotifyWaiters()) {
            return true;
        }
        notifyListeners();
        return true;
    }

    public boolean isCancelled() {
        return isCancelled0(this.result);
    }

    public boolean isDone() {
        return isDone0(this.result);
    }

    public Promise<V> sync() throws InterruptedException {
        await();
        rethrowIfFailed();
        return this;
    }

    public Promise<V> syncUninterruptibly() {
        awaitUninterruptibly();
        rethrowIfFailed();
        return this;
    }

    public String toString() {
        return toStringBuilder().toString();
    }

    /* access modifiers changed from: protected */
    public StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(StringUtil.simpleClassName((Object) this));
        sb.append('@');
        sb.append(Integer.toHexString(hashCode()));
        Object obj = this.result;
        if (obj == SUCCESS) {
            sb.append("(success)");
        } else if (obj == UNCANCELLABLE) {
            sb.append("(uncancellable)");
        } else if (obj instanceof CauseHolder) {
            sb.append("(failure: ");
            sb.append(((CauseHolder) obj).cause);
            sb.append(')');
        } else if (obj != null) {
            sb.append("(success: ");
            sb.append(obj);
            sb.append(')');
        } else {
            sb.append("(incomplete)");
        }
        return sb;
    }

    /* access modifiers changed from: protected */
    public EventExecutor executor() {
        return this.executor;
    }

    /* access modifiers changed from: protected */
    public void checkDeadLock() {
        EventExecutor executor2 = executor();
        if (executor2 != null && executor2.inEventLoop()) {
            throw new BlockingOperationException(toString());
        }
    }

    protected static void notifyListener(EventExecutor eventExecutor, Future<?> future, GenericFutureListener<?> genericFutureListener) {
        notifyListenerWithStackOverFlowProtection((EventExecutor) ObjectUtil.checkNotNull(eventExecutor, "eventExecutor"), (Future) ObjectUtil.checkNotNull(future, "future"), (GenericFutureListener) ObjectUtil.checkNotNull(genericFutureListener, "listener"));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r1 = io.netty.util.internal.InternalThreadLocalMap.get();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void notifyListeners() {
        /*
            r4 = this;
            io.netty.util.concurrent.EventExecutor r0 = r4.executor()
            boolean r1 = r0.inEventLoop()
            if (r1 == 0) goto L_0x0027
            io.netty.util.internal.InternalThreadLocalMap r1 = io.netty.util.internal.InternalThreadLocalMap.get()
            int r2 = r1.futureListenerStackDepth()
            int r3 = MAX_LISTENER_STACK_DEPTH
            if (r2 >= r3) goto L_0x0027
            int r0 = r2 + 1
            r1.setFutureListenerStackDepth(r0)
            r4.notifyListenersNow()     // Catch:{ all -> 0x0022 }
            r1.setFutureListenerStackDepth(r2)
            return
        L_0x0022:
            r0 = move-exception
            r1.setFutureListenerStackDepth(r2)
            throw r0
        L_0x0027:
            io.netty.util.concurrent.DefaultPromise$1 r1 = new io.netty.util.concurrent.DefaultPromise$1
            r1.<init>()
            safeExecute(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.util.concurrent.DefaultPromise.notifyListeners():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = io.netty.util.internal.InternalThreadLocalMap.get();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void notifyListenerWithStackOverFlowProtection(io.netty.util.concurrent.EventExecutor r3, final io.netty.util.concurrent.Future<?> r4, final io.netty.util.concurrent.GenericFutureListener<?> r5) {
        /*
            boolean r0 = r3.inEventLoop()
            if (r0 == 0) goto L_0x0023
            io.netty.util.internal.InternalThreadLocalMap r0 = io.netty.util.internal.InternalThreadLocalMap.get()
            int r1 = r0.futureListenerStackDepth()
            int r2 = MAX_LISTENER_STACK_DEPTH
            if (r1 >= r2) goto L_0x0023
            int r3 = r1 + 1
            r0.setFutureListenerStackDepth(r3)
            notifyListener0(r4, r5)     // Catch:{ all -> 0x001e }
            r0.setFutureListenerStackDepth(r1)
            return
        L_0x001e:
            r3 = move-exception
            r0.setFutureListenerStackDepth(r1)
            throw r3
        L_0x0023:
            io.netty.util.concurrent.DefaultPromise$2 r0 = new io.netty.util.concurrent.DefaultPromise$2
            r0.<init>(r4, r5)
            safeExecute(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.util.concurrent.DefaultPromise.notifyListenerWithStackOverFlowProtection(io.netty.util.concurrent.EventExecutor, io.netty.util.concurrent.Future, io.netty.util.concurrent.GenericFutureListener):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        if (r0 == null) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        notifyListener0(r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0020, code lost:
        notifyListeners0(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0 = r3.listener;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0026, code lost:
        if (r0 != null) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002a, code lost:
        if (r3.listeners != null) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002c, code lost:
        r3.notifyingListeners = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002f, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0030, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0031, code lost:
        r1 = r3.listeners;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0033, code lost:
        if (r0 == null) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0035, code lost:
        r3.listener = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0038, code lost:
        r3.listeners = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x003a, code lost:
        monitor-exit(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifyListenersNow() {
        /*
            r3 = this;
            monitor-enter(r3)
            io.netty.util.concurrent.GenericFutureListener<? extends io.netty.util.concurrent.Future<?>> r0 = r3.listener     // Catch:{ all -> 0x0041 }
            io.netty.util.concurrent.DefaultFutureListeners r1 = r3.listeners     // Catch:{ all -> 0x0041 }
            boolean r2 = r3.notifyingListeners     // Catch:{ all -> 0x0041 }
            if (r2 != 0) goto L_0x003f
            if (r0 != 0) goto L_0x000e
            if (r1 != 0) goto L_0x000e
            goto L_0x003f
        L_0x000e:
            r2 = 1
            r3.notifyingListeners = r2     // Catch:{ all -> 0x0041 }
            r2 = 0
            if (r0 == 0) goto L_0x0017
            r3.listener = r2     // Catch:{ all -> 0x0041 }
            goto L_0x0019
        L_0x0017:
            r3.listeners = r2     // Catch:{ all -> 0x0041 }
        L_0x0019:
            monitor-exit(r3)     // Catch:{ all -> 0x0041 }
        L_0x001a:
            if (r0 == 0) goto L_0x0020
            notifyListener0(r3, r0)
            goto L_0x0023
        L_0x0020:
            r3.notifyListeners0(r1)
        L_0x0023:
            monitor-enter(r3)
            io.netty.util.concurrent.GenericFutureListener<? extends io.netty.util.concurrent.Future<?>> r0 = r3.listener     // Catch:{ all -> 0x003c }
            if (r0 != 0) goto L_0x0031
            io.netty.util.concurrent.DefaultFutureListeners r1 = r3.listeners     // Catch:{ all -> 0x003c }
            if (r1 != 0) goto L_0x0031
            r0 = 0
            r3.notifyingListeners = r0     // Catch:{ all -> 0x003c }
            monitor-exit(r3)     // Catch:{ all -> 0x003c }
            return
        L_0x0031:
            io.netty.util.concurrent.DefaultFutureListeners r1 = r3.listeners     // Catch:{ all -> 0x003c }
            if (r0 == 0) goto L_0x0038
            r3.listener = r2     // Catch:{ all -> 0x003c }
            goto L_0x003a
        L_0x0038:
            r3.listeners = r2     // Catch:{ all -> 0x003c }
        L_0x003a:
            monitor-exit(r3)     // Catch:{ all -> 0x003c }
            goto L_0x001a
        L_0x003c:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x003c }
            throw r0
        L_0x003f:
            monitor-exit(r3)     // Catch:{ all -> 0x0041 }
            return
        L_0x0041:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0041 }
            goto L_0x0045
        L_0x0044:
            throw r0
        L_0x0045:
            goto L_0x0044
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.util.concurrent.DefaultPromise.notifyListenersNow():void");
    }

    private void notifyListeners0(DefaultFutureListeners defaultFutureListeners) {
        GenericFutureListener[] listeners2 = defaultFutureListeners.listeners();
        int size = defaultFutureListeners.size();
        for (int i = 0; i < size; i++) {
            notifyListener0(this, listeners2[i]);
        }
    }

    /* access modifiers changed from: private */
    public static void notifyListener0(Future future, GenericFutureListener genericFutureListener) {
        try {
            genericFutureListener.operationComplete(future);
        } catch (Throwable th) {
            if (logger.isWarnEnabled()) {
                InternalLogger internalLogger = logger;
                internalLogger.warn("An exception was thrown by " + genericFutureListener.getClass().getName() + ".operationComplete()", th);
            }
        }
    }

    private void addListener0(GenericFutureListener<? extends Future<? super V>> genericFutureListener) {
        GenericFutureListener<? extends Future<?>> genericFutureListener2 = this.listener;
        if (genericFutureListener2 == null) {
            DefaultFutureListeners defaultFutureListeners = this.listeners;
            if (defaultFutureListeners == null) {
                this.listener = genericFutureListener;
            } else {
                defaultFutureListeners.add(genericFutureListener);
            }
        } else {
            this.listeners = new DefaultFutureListeners(genericFutureListener2, genericFutureListener);
            this.listener = null;
        }
    }

    private void removeListener0(GenericFutureListener<? extends Future<? super V>> genericFutureListener) {
        if (this.listener == genericFutureListener) {
            this.listener = null;
            return;
        }
        DefaultFutureListeners defaultFutureListeners = this.listeners;
        if (defaultFutureListeners != null) {
            defaultFutureListeners.remove(genericFutureListener);
            if (this.listeners.size() == 0) {
                this.listeners = null;
            }
        }
    }

    private boolean setSuccess0(V v) {
        if (v == null) {
            v = SUCCESS;
        }
        return setValue0(v);
    }

    private boolean setFailure0(Throwable th) {
        return setValue0(new CauseHolder((Throwable) ObjectUtil.checkNotNull(th, "cause")));
    }

    private boolean setValue0(Object obj) {
        AtomicReferenceFieldUpdater<DefaultPromise, Object> atomicReferenceFieldUpdater = RESULT_UPDATER;
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, (Object) null, obj) && !AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, UNCANCELLABLE, obj)) {
            return false;
        }
        if (!checkNotifyWaiters()) {
            return true;
        }
        notifyListeners();
        return true;
    }

    private synchronized boolean checkNotifyWaiters() {
        if (this.waiters > 0) {
            notifyAll();
        }
        return (this.listener == null && this.listeners == null) ? false : true;
    }

    private void incWaiters() {
        short s = this.waiters;
        if (s != Short.MAX_VALUE) {
            this.waiters = (short) (s + 1);
            return;
        }
        throw new IllegalStateException("too many waiters: " + this);
    }

    private void decWaiters() {
        this.waiters = (short) (this.waiters - 1);
    }

    private void rethrowIfFailed() {
        Throwable cause = cause();
        if (cause != null) {
            PlatformDependent.throwException(cause);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0064, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0082, code lost:
        return r13;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean await0(long r13, boolean r15) throws java.lang.InterruptedException {
        /*
            r12 = this;
            boolean r0 = r12.isDone()
            r1 = 1
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            r2 = 0
            int r0 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1))
            if (r0 > 0) goto L_0x0013
            boolean r13 = r12.isDone()
            return r13
        L_0x0013:
            if (r15 == 0) goto L_0x0026
            boolean r0 = java.lang.Thread.interrupted()
            if (r0 != 0) goto L_0x001c
            goto L_0x0026
        L_0x001c:
            java.lang.InterruptedException r13 = new java.lang.InterruptedException
            java.lang.String r14 = r12.toString()
            r13.<init>(r14)
            throw r13
        L_0x0026:
            r12.checkDeadLock()
            long r4 = java.lang.System.nanoTime()
            monitor-enter(r12)
            r0 = 0
            r6 = r13
        L_0x0030:
            boolean r8 = r12.isDone()     // Catch:{ all -> 0x0083 }
            if (r8 != 0) goto L_0x0074
            int r8 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r8 <= 0) goto L_0x0074
            r12.incWaiters()     // Catch:{ all -> 0x0083 }
            r8 = 1000000(0xf4240, double:4.940656E-318)
            long r10 = r6 / r8
            long r6 = r6 % r8
            int r7 = (int) r6     // Catch:{ InterruptedException -> 0x004d }
            r12.wait(r10, r7)     // Catch:{ InterruptedException -> 0x004d }
            r12.decWaiters()     // Catch:{ all -> 0x0083 }
            goto L_0x0054
        L_0x004b:
            r13 = move-exception
            goto L_0x0070
        L_0x004d:
            r6 = move-exception
            if (r15 != 0) goto L_0x006f
            r12.decWaiters()     // Catch:{ all -> 0x006d }
            r0 = 1
        L_0x0054:
            boolean r6 = r12.isDone()     // Catch:{ all -> 0x0083 }
            if (r6 == 0) goto L_0x0065
            if (r0 == 0) goto L_0x0063
            java.lang.Thread r13 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x008f }
            r13.interrupt()     // Catch:{ all -> 0x008f }
        L_0x0063:
            monitor-exit(r12)     // Catch:{ all -> 0x008f }
            return r1
        L_0x0065:
            long r6 = java.lang.System.nanoTime()     // Catch:{ all -> 0x0083 }
            long r6 = r6 - r4
            long r6 = r13 - r6
            goto L_0x0030
        L_0x006d:
            r13 = move-exception
            goto L_0x0085
        L_0x006f:
            throw r6     // Catch:{ all -> 0x004b }
        L_0x0070:
            r12.decWaiters()     // Catch:{ all -> 0x0083 }
            throw r13     // Catch:{ all -> 0x0083 }
        L_0x0074:
            boolean r13 = r12.isDone()     // Catch:{ all -> 0x0083 }
            if (r0 == 0) goto L_0x0081
            java.lang.Thread r14 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x008f }
            r14.interrupt()     // Catch:{ all -> 0x008f }
        L_0x0081:
            monitor-exit(r12)     // Catch:{ all -> 0x008f }
            return r13
        L_0x0083:
            r13 = move-exception
            r1 = r0
        L_0x0085:
            if (r1 == 0) goto L_0x008e
            java.lang.Thread r14 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x008f }
            r14.interrupt()     // Catch:{ all -> 0x008f }
        L_0x008e:
            throw r13     // Catch:{ all -> 0x008f }
        L_0x008f:
            r13 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x008f }
            goto L_0x0093
        L_0x0092:
            throw r13
        L_0x0093:
            goto L_0x0092
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.util.concurrent.DefaultPromise.await0(long, boolean):boolean");
    }

    /* access modifiers changed from: package-private */
    public void notifyProgressiveListeners(long j, long j2) {
        Object progressiveListeners = progressiveListeners();
        if (progressiveListeners != null) {
            final ProgressiveFuture progressiveFuture = (ProgressiveFuture) this;
            EventExecutor executor2 = executor();
            if (executor2.inEventLoop()) {
                if (progressiveListeners instanceof GenericProgressiveFutureListener[]) {
                    notifyProgressiveListeners0(progressiveFuture, (GenericProgressiveFutureListener[]) progressiveListeners, j, j2);
                    return;
                }
                notifyProgressiveListener0(progressiveFuture, (GenericProgressiveFutureListener) progressiveListeners, j, j2);
            } else if (progressiveListeners instanceof GenericProgressiveFutureListener[]) {
                final GenericProgressiveFutureListener[] genericProgressiveFutureListenerArr = (GenericProgressiveFutureListener[]) progressiveListeners;
                final long j3 = j;
                final long j4 = j2;
                safeExecute(executor2, new Runnable() {
                    public void run() {
                        DefaultPromise.notifyProgressiveListeners0(progressiveFuture, genericProgressiveFutureListenerArr, j3, j4);
                    }
                });
            } else {
                final GenericProgressiveFutureListener genericProgressiveFutureListener = (GenericProgressiveFutureListener) progressiveListeners;
                final long j5 = j;
                final long j6 = j2;
                safeExecute(executor2, new Runnable() {
                    public void run() {
                        DefaultPromise.notifyProgressiveListener0(progressiveFuture, genericProgressiveFutureListener, j5, j6);
                    }
                });
            }
        }
    }

    private synchronized Object progressiveListeners() {
        GenericFutureListener<? extends Future<?>> genericFutureListener = this.listener;
        DefaultFutureListeners defaultFutureListeners = this.listeners;
        if (genericFutureListener == null && defaultFutureListeners == null) {
            return null;
        }
        if (defaultFutureListeners != null) {
            int progressiveSize = defaultFutureListeners.progressiveSize();
            if (progressiveSize == 0) {
                return null;
            }
            int i = 0;
            if (progressiveSize != 1) {
                GenericFutureListener[] listeners2 = defaultFutureListeners.listeners();
                GenericProgressiveFutureListener[] genericProgressiveFutureListenerArr = new GenericProgressiveFutureListener[progressiveSize];
                int i2 = 0;
                while (i < progressiveSize) {
                    GenericFutureListener genericFutureListener2 = listeners2[i2];
                    if (genericFutureListener2 instanceof GenericProgressiveFutureListener) {
                        int i3 = i + 1;
                        genericProgressiveFutureListenerArr[i] = (GenericProgressiveFutureListener) genericFutureListener2;
                        i = i3;
                    }
                    i2++;
                }
                return genericProgressiveFutureListenerArr;
            }
            GenericFutureListener[] listeners3 = defaultFutureListeners.listeners();
            int length = listeners3.length;
            while (i < length) {
                GenericFutureListener genericFutureListener3 = listeners3[i];
                if (genericFutureListener3 instanceof GenericProgressiveFutureListener) {
                    return genericFutureListener3;
                }
                i++;
            }
            return null;
        } else if (genericFutureListener instanceof GenericProgressiveFutureListener) {
            return genericFutureListener;
        } else {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void notifyProgressiveListeners0(ProgressiveFuture<?> progressiveFuture, GenericProgressiveFutureListener<?>[] genericProgressiveFutureListenerArr, long j, long j2) {
        int length = genericProgressiveFutureListenerArr.length;
        int i = 0;
        while (i < length) {
            GenericProgressiveFutureListener<?> genericProgressiveFutureListener = genericProgressiveFutureListenerArr[i];
            if (genericProgressiveFutureListener != null) {
                notifyProgressiveListener0(progressiveFuture, genericProgressiveFutureListener, j, j2);
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public static void notifyProgressiveListener0(ProgressiveFuture progressiveFuture, GenericProgressiveFutureListener genericProgressiveFutureListener, long j, long j2) {
        try {
            genericProgressiveFutureListener.operationProgressed(progressiveFuture, j, j2);
        } catch (Throwable th) {
            if (logger.isWarnEnabled()) {
                InternalLogger internalLogger = logger;
                internalLogger.warn("An exception was thrown by " + genericProgressiveFutureListener.getClass().getName() + ".operationProgressed()", th);
            }
        }
    }

    private static boolean isCancelled0(Object obj) {
        return (obj instanceof CauseHolder) && (((CauseHolder) obj).cause instanceof CancellationException);
    }

    private static boolean isDone0(Object obj) {
        return (obj == null || obj == UNCANCELLABLE) ? false : true;
    }

    private static final class CauseHolder {
        final Throwable cause;

        CauseHolder(Throwable th) {
            this.cause = th;
        }
    }

    private static void safeExecute(EventExecutor eventExecutor, Runnable runnable) {
        try {
            eventExecutor.execute(runnable);
        } catch (Throwable th) {
            rejectedExecutionLogger.error("Failed to submit a listener notification task. Event loop shut down?", th);
        }
    }

    private static final class StacklessCancellationException extends CancellationException {
        private static final long serialVersionUID = -2974906711413716191L;

        public Throwable fillInStackTrace() {
            return this;
        }

        private StacklessCancellationException() {
        }

        static StacklessCancellationException newInstance(Class<?> cls, String str) {
            return (StacklessCancellationException) ThrowableUtil.unknownStackTrace(new StacklessCancellationException(), cls, str);
        }
    }
}
