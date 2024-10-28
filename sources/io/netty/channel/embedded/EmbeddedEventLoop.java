package io.netty.channel.embedded;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.util.concurrent.AbstractScheduledEventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.internal.ObjectUtil;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

final class EmbeddedEventLoop extends AbstractScheduledEventExecutor implements EventLoop {
    private long frozenTimestamp;
    private long startTime = initialNanoTime();
    private final Queue<Runnable> tasks = new ArrayDeque(2);
    private boolean timeFrozen;

    public boolean awaitTermination(long j, TimeUnit timeUnit) {
        return false;
    }

    public boolean inEventLoop() {
        return true;
    }

    public boolean inEventLoop(Thread thread) {
        return true;
    }

    public boolean isShutdown() {
        return false;
    }

    public boolean isShuttingDown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }

    EmbeddedEventLoop() {
    }

    public EventLoopGroup parent() {
        return (EventLoopGroup) super.parent();
    }

    public EventLoop next() {
        return (EventLoop) super.next();
    }

    public void execute(Runnable runnable) {
        this.tasks.add(ObjectUtil.checkNotNull(runnable, "command"));
    }

    /* access modifiers changed from: package-private */
    public void runTasks() {
        while (true) {
            Runnable poll = this.tasks.poll();
            if (poll != null) {
                poll.run();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasPendingNormalTasks() {
        return !this.tasks.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public long runScheduledTasks() {
        long currentTimeNanos = getCurrentTimeNanos();
        while (true) {
            Runnable pollScheduledTask = pollScheduledTask(currentTimeNanos);
            if (pollScheduledTask == null) {
                return nextScheduledTaskNano();
            }
            pollScheduledTask.run();
        }
    }

    /* access modifiers changed from: package-private */
    public long nextScheduledTask() {
        return nextScheduledTaskNano();
    }

    /* access modifiers changed from: protected */
    public long getCurrentTimeNanos() {
        if (this.timeFrozen) {
            return this.frozenTimestamp;
        }
        return System.nanoTime() - this.startTime;
    }

    /* access modifiers changed from: package-private */
    public void advanceTimeBy(long j) {
        if (this.timeFrozen) {
            this.frozenTimestamp += j;
        } else {
            this.startTime -= j;
        }
    }

    /* access modifiers changed from: package-private */
    public void freezeTime() {
        if (!this.timeFrozen) {
            this.frozenTimestamp = getCurrentTimeNanos();
            this.timeFrozen = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void unfreezeTime() {
        if (this.timeFrozen) {
            this.startTime = System.nanoTime() - this.frozenTimestamp;
            this.timeFrozen = false;
        }
    }

    /* access modifiers changed from: protected */
    public void cancelScheduledTasks() {
        super.cancelScheduledTasks();
    }

    public Future<?> shutdownGracefully(long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public Future<?> terminationFuture() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    public ChannelFuture register(Channel channel) {
        return register((ChannelPromise) new DefaultChannelPromise(channel, this));
    }

    public ChannelFuture register(ChannelPromise channelPromise) {
        ObjectUtil.checkNotNull(channelPromise, "promise");
        channelPromise.channel().unsafe().register(this, channelPromise);
        return channelPromise;
    }

    @Deprecated
    public ChannelFuture register(Channel channel, ChannelPromise channelPromise) {
        channel.unsafe().register(this, channelPromise);
        return channelPromise;
    }
}
