package io.netty.channel.oio;

import io.netty.channel.ThreadPerChannelEventLoopGroup;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

@Deprecated
public class OioEventLoopGroup extends ThreadPerChannelEventLoopGroup {
    public OioEventLoopGroup() {
        this(0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public OioEventLoopGroup(int i) {
        this(i, (ThreadFactory) null);
        ThreadFactory threadFactory = null;
    }

    public OioEventLoopGroup(int i, Executor executor) {
        super(i, executor, new Object[0]);
    }

    public OioEventLoopGroup(int i, ThreadFactory threadFactory) {
        super(i, threadFactory, new Object[0]);
    }
}
