package io.netty.util.concurrent;

import io.netty.util.internal.ObjectUtil;

public final class PromiseCombiner {
    /* access modifiers changed from: private */
    public Promise<Void> aggregatePromise;
    /* access modifiers changed from: private */
    public Throwable cause;
    /* access modifiers changed from: private */
    public int doneCount;
    /* access modifiers changed from: private */
    public final EventExecutor executor;
    /* access modifiers changed from: private */
    public int expectedCount;
    private final GenericFutureListener<Future<?>> listener;

    static /* synthetic */ int access$204(PromiseCombiner promiseCombiner) {
        int i = promiseCombiner.doneCount + 1;
        promiseCombiner.doneCount = i;
        return i;
    }

    @Deprecated
    public PromiseCombiner() {
        this(ImmediateEventExecutor.INSTANCE);
    }

    public PromiseCombiner(EventExecutor eventExecutor) {
        this.listener = new GenericFutureListener<Future<?>>() {
            static final /* synthetic */ boolean $assertionsDisabled = false;

            static {
                Class<PromiseCombiner> cls = PromiseCombiner.class;
            }

            public void operationComplete(final Future<?> future) {
                if (PromiseCombiner.this.executor.inEventLoop()) {
                    operationComplete0(future);
                } else {
                    PromiseCombiner.this.executor.execute(new Runnable() {
                        public void run() {
                            AnonymousClass1.this.operationComplete0(future);
                        }
                    });
                }
            }

            /* access modifiers changed from: private */
            public void operationComplete0(Future<?> future) {
                PromiseCombiner.access$204(PromiseCombiner.this);
                if (!future.isSuccess() && PromiseCombiner.this.cause == null) {
                    Throwable unused = PromiseCombiner.this.cause = future.cause();
                }
                if (PromiseCombiner.this.doneCount == PromiseCombiner.this.expectedCount && PromiseCombiner.this.aggregatePromise != null) {
                    boolean unused2 = PromiseCombiner.this.tryPromise();
                }
            }
        };
        this.executor = (EventExecutor) ObjectUtil.checkNotNull(eventExecutor, "executor");
    }

    @Deprecated
    public void add(Promise promise) {
        add((Future) promise);
    }

    public void add(Future future) {
        checkAddAllowed();
        checkInEventLoop();
        this.expectedCount++;
        future.addListener(this.listener);
    }

    @Deprecated
    public void addAll(Promise... promiseArr) {
        addAll((Future[]) promiseArr);
    }

    public void addAll(Future... futureArr) {
        for (Future add : futureArr) {
            add(add);
        }
    }

    public void finish(Promise<Void> promise) {
        ObjectUtil.checkNotNull(promise, "aggregatePromise");
        checkInEventLoop();
        if (this.aggregatePromise == null) {
            this.aggregatePromise = promise;
            if (this.doneCount == this.expectedCount) {
                tryPromise();
                return;
            }
            return;
        }
        throw new IllegalStateException("Already finished");
    }

    private void checkInEventLoop() {
        if (!this.executor.inEventLoop()) {
            throw new IllegalStateException("Must be called from EventExecutor thread");
        }
    }

    /* access modifiers changed from: private */
    public boolean tryPromise() {
        Throwable th = this.cause;
        return th == null ? this.aggregatePromise.trySuccess(null) : this.aggregatePromise.tryFailure(th);
    }

    private void checkAddAllowed() {
        if (this.aggregatePromise != null) {
            throw new IllegalStateException("Adding promises is not allowed after finished adding");
        }
    }
}
