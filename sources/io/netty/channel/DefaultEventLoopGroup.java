package io.netty.channel;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

public class DefaultEventLoopGroup extends MultithreadEventLoopGroup {
    public DefaultEventLoopGroup() {
        this(0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DefaultEventLoopGroup(int i) {
        this(i, (ThreadFactory) null);
        ThreadFactory threadFactory = null;
    }

    public DefaultEventLoopGroup(ThreadFactory threadFactory) {
        this(0, threadFactory);
    }

    public DefaultEventLoopGroup(int i, ThreadFactory threadFactory) {
        super(i, threadFactory, new Object[0]);
    }

    public DefaultEventLoopGroup(int i, Executor executor) {
        super(i, executor, new Object[0]);
    }

    /* access modifiers changed from: protected */
    public EventLoop newChild(Executor executor, Object... objArr) throws Exception {
        return new DefaultEventLoop((EventLoopGroup) this, executor);
    }
}
