package io.netty.util.concurrent;

public final class SucceededFuture<V> extends CompleteFuture<V> {
    private final V result;

    public Throwable cause() {
        return null;
    }

    public boolean isSuccess() {
        return true;
    }

    public SucceededFuture(EventExecutor eventExecutor, V v) {
        super(eventExecutor);
        this.result = v;
    }

    public V getNow() {
        return this.result;
    }
}
