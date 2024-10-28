package io.netty.channel;

@Deprecated
public class ThreadPerChannelEventLoop extends SingleThreadEventLoop {
    /* access modifiers changed from: private */
    public Channel ch;
    private final ThreadPerChannelEventLoopGroup parent;

    public int registeredChannels() {
        return 1;
    }

    public ThreadPerChannelEventLoop(ThreadPerChannelEventLoopGroup threadPerChannelEventLoopGroup) {
        super((EventLoopGroup) threadPerChannelEventLoopGroup, threadPerChannelEventLoopGroup.executor, true);
        this.parent = threadPerChannelEventLoopGroup;
    }

    public ChannelFuture register(ChannelPromise channelPromise) {
        return super.register(channelPromise).addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    Channel unused = ThreadPerChannelEventLoop.this.ch = channelFuture.channel();
                } else {
                    ThreadPerChannelEventLoop.this.deregister();
                }
            }
        });
    }

    @Deprecated
    public ChannelFuture register(Channel channel, ChannelPromise channelPromise) {
        return super.register(channel, channelPromise).addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    Channel unused = ThreadPerChannelEventLoop.this.ch = channelFuture.channel();
                } else {
                    ThreadPerChannelEventLoop.this.deregister();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void run() {
        while (true) {
            Runnable takeTask = takeTask();
            if (takeTask != null) {
                takeTask.run();
                updateLastExecutionTime();
            }
            Channel channel = this.ch;
            if (isShuttingDown()) {
                if (channel != null) {
                    channel.unsafe().close(channel.unsafe().voidPromise());
                }
                if (confirmShutdown()) {
                    return;
                }
            } else if (channel != null && !channel.isRegistered()) {
                runAllTasks();
                deregister();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void deregister() {
        this.ch = null;
        this.parent.activeChildren.remove(this);
        this.parent.idleChildren.add(this);
    }
}
