package io.netty.util.concurrent;

import io.netty.util.internal.ObjectUtil;
import java.util.concurrent.TimeUnit;

public abstract class CompleteFuture<V> extends AbstractFuture<V> {
    private final EventExecutor executor;

    public Future<V> awaitUninterruptibly() {
        return this;
    }

    public boolean awaitUninterruptibly(long j) {
        return true;
    }

    public boolean awaitUninterruptibly(long j, TimeUnit timeUnit) {
        return true;
    }

    public boolean cancel(boolean z) {
        return false;
    }

    public boolean isCancellable() {
        return false;
    }

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return true;
    }

    public Future<V> removeListener(GenericFutureListener<? extends Future<? super V>> genericFutureListener) {
        return this;
    }

    public Future<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... genericFutureListenerArr) {
        return this;
    }

    public Future<V> sync() throws InterruptedException {
        return this;
    }

    public Future<V> syncUninterruptibly() {
        return this;
    }

    protected CompleteFuture(EventExecutor eventExecutor) {
        this.executor = eventExecutor;
    }

    /* access modifiers changed from: protected */
    public EventExecutor executor() {
        return this.executor;
    }

    public Future<V> addListener(GenericFutureListener<? extends Future<? super V>> genericFutureListener) {
        DefaultPromise.notifyListener(executor(), this, (GenericFutureListener) ObjectUtil.checkNotNull(genericFutureListener, "listener"));
        return this;
    }

    public Future<V> addListeners(GenericFutureListener<? extends Future<? super V>>... genericFutureListenerArr) {
        for (GenericFutureListener genericFutureListener : (GenericFutureListener[]) ObjectUtil.checkNotNull(genericFutureListenerArr, "listeners")) {
            if (genericFutureListener == null) {
                break;
            }
            DefaultPromise.notifyListener(executor(), this, genericFutureListener);
        }
        return this;
    }

    public Future<V> await() throws InterruptedException {
        if (!Thread.interrupted()) {
            return this;
        }
        throw new InterruptedException();
    }

    public boolean await(long j, TimeUnit timeUnit) throws InterruptedException {
        if (!Thread.interrupted()) {
            return true;
        }
        throw new InterruptedException();
    }

    public boolean await(long j) throws InterruptedException {
        if (!Thread.interrupted()) {
            return true;
        }
        throw new InterruptedException();
    }
}
