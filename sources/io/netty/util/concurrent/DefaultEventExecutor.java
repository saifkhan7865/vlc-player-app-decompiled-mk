package io.netty.util.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

public final class DefaultEventExecutor extends SingleThreadEventExecutor {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DefaultEventExecutor() {
        this((EventExecutorGroup) null);
        EventExecutorGroup eventExecutorGroup = null;
    }

    public DefaultEventExecutor(ThreadFactory threadFactory) {
        this((EventExecutorGroup) null, threadFactory);
    }

    public DefaultEventExecutor(Executor executor) {
        this((EventExecutorGroup) null, executor);
    }

    public DefaultEventExecutor(EventExecutorGroup eventExecutorGroup) {
        this(eventExecutorGroup, (ThreadFactory) new DefaultThreadFactory((Class<?>) DefaultEventExecutor.class));
    }

    public DefaultEventExecutor(EventExecutorGroup eventExecutorGroup, ThreadFactory threadFactory) {
        super(eventExecutorGroup, threadFactory, true);
    }

    public DefaultEventExecutor(EventExecutorGroup eventExecutorGroup, Executor executor) {
        super(eventExecutorGroup, executor, true);
    }

    public DefaultEventExecutor(EventExecutorGroup eventExecutorGroup, ThreadFactory threadFactory, int i, RejectedExecutionHandler rejectedExecutionHandler) {
        super(eventExecutorGroup, threadFactory, true, i, rejectedExecutionHandler);
    }

    public DefaultEventExecutor(EventExecutorGroup eventExecutorGroup, Executor executor, int i, RejectedExecutionHandler rejectedExecutionHandler) {
        super(eventExecutorGroup, executor, true, i, rejectedExecutionHandler);
    }

    /* access modifiers changed from: protected */
    public void run() {
        do {
            Runnable takeTask = takeTask();
            if (takeTask != null) {
                runTask(takeTask);
                updateLastExecutionTime();
            }
        } while (!confirmShutdown());
    }
}
