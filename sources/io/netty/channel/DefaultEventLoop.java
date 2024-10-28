package io.netty.channel;

import io.netty.util.concurrent.DefaultThreadFactory;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

public class DefaultEventLoop extends SingleThreadEventLoop {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DefaultEventLoop() {
        this((EventLoopGroup) null);
        EventLoopGroup eventLoopGroup = null;
    }

    public DefaultEventLoop(ThreadFactory threadFactory) {
        this((EventLoopGroup) null, threadFactory);
    }

    public DefaultEventLoop(Executor executor) {
        this((EventLoopGroup) null, executor);
    }

    public DefaultEventLoop(EventLoopGroup eventLoopGroup) {
        this(eventLoopGroup, (ThreadFactory) new DefaultThreadFactory((Class<?>) DefaultEventLoop.class));
    }

    public DefaultEventLoop(EventLoopGroup eventLoopGroup, ThreadFactory threadFactory) {
        super(eventLoopGroup, threadFactory, true);
    }

    public DefaultEventLoop(EventLoopGroup eventLoopGroup, Executor executor) {
        super(eventLoopGroup, executor, true);
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
