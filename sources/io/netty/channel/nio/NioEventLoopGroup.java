package io.netty.channel.nio;

import io.netty.channel.DefaultSelectStrategyFactory;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopTaskQueueFactory;
import io.netty.channel.MultithreadEventLoopGroup;
import io.netty.channel.SelectStrategyFactory;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.EventExecutorChooserFactory;
import io.netty.util.concurrent.RejectedExecutionHandler;
import io.netty.util.concurrent.RejectedExecutionHandlers;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

public class NioEventLoopGroup extends MultithreadEventLoopGroup {
    public NioEventLoopGroup() {
        this(0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NioEventLoopGroup(int i) {
        this(i, (Executor) null);
        Executor executor = null;
    }

    public NioEventLoopGroup(ThreadFactory threadFactory) {
        this(0, threadFactory, SelectorProvider.provider());
    }

    public NioEventLoopGroup(int i, ThreadFactory threadFactory) {
        this(i, threadFactory, SelectorProvider.provider());
    }

    public NioEventLoopGroup(int i, Executor executor) {
        this(i, executor, SelectorProvider.provider());
    }

    public NioEventLoopGroup(int i, ThreadFactory threadFactory, SelectorProvider selectorProvider) {
        this(i, threadFactory, selectorProvider, DefaultSelectStrategyFactory.INSTANCE);
    }

    public NioEventLoopGroup(int i, ThreadFactory threadFactory, SelectorProvider selectorProvider, SelectStrategyFactory selectStrategyFactory) {
        super(i, threadFactory, selectorProvider, selectStrategyFactory, RejectedExecutionHandlers.reject());
    }

    public NioEventLoopGroup(int i, Executor executor, SelectorProvider selectorProvider) {
        this(i, executor, selectorProvider, DefaultSelectStrategyFactory.INSTANCE);
    }

    public NioEventLoopGroup(int i, Executor executor, SelectorProvider selectorProvider, SelectStrategyFactory selectStrategyFactory) {
        super(i, executor, selectorProvider, selectStrategyFactory, RejectedExecutionHandlers.reject());
    }

    public NioEventLoopGroup(int i, Executor executor, EventExecutorChooserFactory eventExecutorChooserFactory, SelectorProvider selectorProvider, SelectStrategyFactory selectStrategyFactory) {
        super(i, executor, eventExecutorChooserFactory, selectorProvider, selectStrategyFactory, RejectedExecutionHandlers.reject());
    }

    public NioEventLoopGroup(int i, Executor executor, EventExecutorChooserFactory eventExecutorChooserFactory, SelectorProvider selectorProvider, SelectStrategyFactory selectStrategyFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i, executor, eventExecutorChooserFactory, selectorProvider, selectStrategyFactory, rejectedExecutionHandler);
    }

    public NioEventLoopGroup(int i, Executor executor, EventExecutorChooserFactory eventExecutorChooserFactory, SelectorProvider selectorProvider, SelectStrategyFactory selectStrategyFactory, RejectedExecutionHandler rejectedExecutionHandler, EventLoopTaskQueueFactory eventLoopTaskQueueFactory) {
        super(i, executor, eventExecutorChooserFactory, selectorProvider, selectStrategyFactory, rejectedExecutionHandler, eventLoopTaskQueueFactory);
    }

    public NioEventLoopGroup(int i, Executor executor, EventExecutorChooserFactory eventExecutorChooserFactory, SelectorProvider selectorProvider, SelectStrategyFactory selectStrategyFactory, RejectedExecutionHandler rejectedExecutionHandler, EventLoopTaskQueueFactory eventLoopTaskQueueFactory, EventLoopTaskQueueFactory eventLoopTaskQueueFactory2) {
        super(i, executor, eventExecutorChooserFactory, selectorProvider, selectStrategyFactory, rejectedExecutionHandler, eventLoopTaskQueueFactory, eventLoopTaskQueueFactory2);
    }

    public void setIoRatio(int i) {
        Iterator<EventExecutor> it = iterator();
        while (it.hasNext()) {
            ((NioEventLoop) it.next()).setIoRatio(i);
        }
    }

    public void rebuildSelectors() {
        Iterator<EventExecutor> it = iterator();
        while (it.hasNext()) {
            ((NioEventLoop) it.next()).rebuildSelector();
        }
    }

    /* access modifiers changed from: protected */
    public EventLoop newChild(Executor executor, Object... objArr) throws Exception {
        SelectorProvider selectorProvider = objArr[0];
        SelectStrategyFactory selectStrategyFactory = objArr[1];
        RejectedExecutionHandler rejectedExecutionHandler = objArr[2];
        int length = objArr.length;
        return new NioEventLoop(this, executor, selectorProvider, selectStrategyFactory.newSelectStrategy(), rejectedExecutionHandler, length > 3 ? objArr[3] : null, length > 4 ? objArr[4] : null);
    }
}
