package io.netty.util.concurrent;

import io.netty.util.internal.DefaultPriorityQueue;
import io.netty.util.internal.PriorityQueueNode;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

final class ScheduledFutureTask<V> extends PromiseTask<V> implements ScheduledFuture<V>, PriorityQueueNode {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private long deadlineNanos;
    private long id;
    private final long periodNanos;
    private int queueIndex = -1;

    ScheduledFutureTask(AbstractScheduledEventExecutor abstractScheduledEventExecutor, Runnable runnable, long j) {
        super((EventExecutor) abstractScheduledEventExecutor, runnable);
        this.deadlineNanos = j;
        this.periodNanos = 0;
    }

    ScheduledFutureTask(AbstractScheduledEventExecutor abstractScheduledEventExecutor, Runnable runnable, long j, long j2) {
        super((EventExecutor) abstractScheduledEventExecutor, runnable);
        this.deadlineNanos = j;
        this.periodNanos = validatePeriod(j2);
    }

    ScheduledFutureTask(AbstractScheduledEventExecutor abstractScheduledEventExecutor, Callable<V> callable, long j, long j2) {
        super((EventExecutor) abstractScheduledEventExecutor, callable);
        this.deadlineNanos = j;
        this.periodNanos = validatePeriod(j2);
    }

    ScheduledFutureTask(AbstractScheduledEventExecutor abstractScheduledEventExecutor, Callable<V> callable, long j) {
        super((EventExecutor) abstractScheduledEventExecutor, callable);
        this.deadlineNanos = j;
        this.periodNanos = 0;
    }

    private static long validatePeriod(long j) {
        if (j != 0) {
            return j;
        }
        throw new IllegalArgumentException("period: 0 (expected: != 0)");
    }

    /* access modifiers changed from: package-private */
    public ScheduledFutureTask<V> setId(long j) {
        if (this.id == 0) {
            this.id = j;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public EventExecutor executor() {
        return super.executor();
    }

    public long deadlineNanos() {
        return this.deadlineNanos;
    }

    /* access modifiers changed from: package-private */
    public void setConsumed() {
        if (this.periodNanos == 0) {
            this.deadlineNanos = 0;
        }
    }

    public long delayNanos() {
        return delayNanos(scheduledExecutor().getCurrentTimeNanos());
    }

    static long deadlineToDelayNanos(long j, long j2) {
        if (j2 == 0) {
            return 0;
        }
        return Math.max(0, j2 - j);
    }

    public long delayNanos(long j) {
        return deadlineToDelayNanos(j, this.deadlineNanos);
    }

    public long getDelay(TimeUnit timeUnit) {
        return timeUnit.convert(delayNanos(), TimeUnit.NANOSECONDS);
    }

    public int compareTo(Delayed delayed) {
        if (this == delayed) {
            return 0;
        }
        ScheduledFutureTask scheduledFutureTask = (ScheduledFutureTask) delayed;
        long deadlineNanos2 = deadlineNanos() - scheduledFutureTask.deadlineNanos();
        if (deadlineNanos2 < 0) {
            return -1;
        }
        return (deadlineNanos2 <= 0 && this.id < scheduledFutureTask.id) ? -1 : 1;
    }

    public void run() {
        try {
            if (delayNanos() > 0) {
                if (isCancelled()) {
                    scheduledExecutor().scheduledTaskQueue().removeTyped(this);
                } else {
                    scheduledExecutor().scheduleFromEventLoop(this);
                }
            } else if (this.periodNanos == 0) {
                if (setUncancellableInternal()) {
                    setSuccessInternal(runTask());
                }
            } else if (!isCancelled()) {
                runTask();
                if (!executor().isShutdown()) {
                    long j = this.periodNanos;
                    if (j > 0) {
                        this.deadlineNanos += j;
                    } else {
                        this.deadlineNanos = scheduledExecutor().getCurrentTimeNanos() - this.periodNanos;
                    }
                    if (!isCancelled()) {
                        scheduledExecutor().scheduledTaskQueue().add(this);
                    }
                }
            }
        } catch (Throwable th) {
            setFailureInternal(th);
        }
    }

    private AbstractScheduledEventExecutor scheduledExecutor() {
        return (AbstractScheduledEventExecutor) executor();
    }

    public boolean cancel(boolean z) {
        boolean cancel = super.cancel(z);
        if (cancel) {
            scheduledExecutor().removeScheduled(this);
        }
        return cancel;
    }

    /* access modifiers changed from: package-private */
    public boolean cancelWithoutRemove(boolean z) {
        return super.cancel(z);
    }

    /* access modifiers changed from: protected */
    public StringBuilder toStringBuilder() {
        StringBuilder stringBuilder = super.toStringBuilder();
        stringBuilder.setCharAt(stringBuilder.length() - 1, ',');
        stringBuilder.append(" deadline: ");
        stringBuilder.append(this.deadlineNanos);
        stringBuilder.append(", period: ");
        stringBuilder.append(this.periodNanos);
        stringBuilder.append(')');
        return stringBuilder;
    }

    public int priorityQueueIndex(DefaultPriorityQueue<?> defaultPriorityQueue) {
        return this.queueIndex;
    }

    public void priorityQueueIndex(DefaultPriorityQueue<?> defaultPriorityQueue, int i) {
        this.queueIndex = i;
    }
}
