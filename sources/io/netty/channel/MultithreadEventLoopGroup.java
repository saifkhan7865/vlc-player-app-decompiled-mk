package io.netty.channel;

import io.netty.util.NettyRuntime;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.EventExecutorChooserFactory;
import io.netty.util.concurrent.MultithreadEventExecutorGroup;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

public abstract class MultithreadEventLoopGroup extends MultithreadEventExecutorGroup implements EventLoopGroup {
    private static final int DEFAULT_EVENT_LOOP_THREADS;
    private static final InternalLogger logger;

    /* access modifiers changed from: protected */
    public abstract EventLoop newChild(Executor executor, Object... objArr) throws Exception;

    static {
        InternalLogger instance = InternalLoggerFactory.getInstance((Class<?>) MultithreadEventLoopGroup.class);
        logger = instance;
        int max = Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        DEFAULT_EVENT_LOOP_THREADS = max;
        if (instance.isDebugEnabled()) {
            instance.debug("-Dio.netty.eventLoopThreads: {}", (Object) Integer.valueOf(max));
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected MultithreadEventLoopGroup(int i, Executor executor, Object... objArr) {
        super(i == 0 ? DEFAULT_EVENT_LOOP_THREADS : i, executor, objArr);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected MultithreadEventLoopGroup(int i, ThreadFactory threadFactory, Object... objArr) {
        super(i == 0 ? DEFAULT_EVENT_LOOP_THREADS : i, threadFactory, objArr);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected MultithreadEventLoopGroup(int i, Executor executor, EventExecutorChooserFactory eventExecutorChooserFactory, Object... objArr) {
        super(i == 0 ? DEFAULT_EVENT_LOOP_THREADS : i, executor, eventExecutorChooserFactory, objArr);
    }

    /* access modifiers changed from: protected */
    public ThreadFactory newDefaultThreadFactory() {
        return new DefaultThreadFactory(getClass(), 10);
    }

    public EventLoop next() {
        return (EventLoop) super.next();
    }

    public ChannelFuture register(Channel channel) {
        return next().register(channel);
    }

    public ChannelFuture register(ChannelPromise channelPromise) {
        return next().register(channelPromise);
    }

    @Deprecated
    public ChannelFuture register(Channel channel, ChannelPromise channelPromise) {
        return next().register(channel, channelPromise);
    }
}
