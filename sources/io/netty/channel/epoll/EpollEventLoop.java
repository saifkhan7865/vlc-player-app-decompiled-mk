package io.netty.channel.epoll;

import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.EventLoopTaskQueueFactory;
import io.netty.channel.SelectStrategy;
import io.netty.channel.SingleThreadEventLoop;
import io.netty.channel.epoll.AbstractEpollChannel;
import io.netty.channel.unix.FileDescriptor;
import io.netty.channel.unix.IovArray;
import io.netty.util.IntSupplier;
import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import io.netty.util.concurrent.RejectedExecutionHandler;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;

public class EpollEventLoop extends SingleThreadEventLoop {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long AWAKE = -1;
    private static final long EPOLL_WAIT_MILLIS_THRESHOLD = SystemPropertyUtil.getLong("io.netty.channel.epoll.epollWaitThreshold", 10);
    private static final long MAX_SCHEDULED_TIMERFD_NS = 999999999;
    private static final long NONE = Long.MAX_VALUE;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) EpollEventLoop.class);
    private final boolean allowGrowing;
    private final IntObjectMap<AbstractEpollChannel> channels = new IntObjectHashMap(4096);
    private NativeDatagramPacketArray datagramPacketArray;
    private FileDescriptor epollFd;
    private FileDescriptor eventFd;
    private final EpollEventArray events;
    private volatile int ioRatio = 50;
    private IovArray iovArray;
    private final AtomicLong nextWakeupNanos = new AtomicLong(-1);
    private boolean pendingWakeup;
    private final IntSupplier selectNowSupplier = new IntSupplier() {
        public int get() throws Exception {
            return EpollEventLoop.this.epollWaitNow();
        }
    };
    private final SelectStrategy selectStrategy;
    private FileDescriptor timerFd;

    static {
        Epoll.ensureAvailability();
    }

    EpollEventLoop(EventLoopGroup eventLoopGroup, Executor executor, int i, SelectStrategy selectStrategy2, RejectedExecutionHandler rejectedExecutionHandler, EventLoopTaskQueueFactory eventLoopTaskQueueFactory, EventLoopTaskQueueFactory eventLoopTaskQueueFactory2) {
        super(eventLoopGroup, executor, false, newTaskQueue(eventLoopTaskQueueFactory), newTaskQueue(eventLoopTaskQueueFactory2), rejectedExecutionHandler);
        this.selectStrategy = (SelectStrategy) ObjectUtil.checkNotNull(selectStrategy2, "strategy");
        if (i == 0) {
            this.allowGrowing = true;
            this.events = new EpollEventArray(4096);
        } else {
            this.allowGrowing = false;
            this.events = new EpollEventArray(i);
        }
        openFileDescriptors();
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0055 A[SYNTHETIC, Splitter:B:28:0x0055] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x005c A[SYNTHETIC, Splitter:B:32:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0063 A[SYNTHETIC, Splitter:B:36:0x0063] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void openFileDescriptors() {
        /*
            r8 = this;
            r0 = 0
            io.netty.channel.unix.FileDescriptor r1 = io.netty.channel.epoll.Native.newEpollCreate()     // Catch:{ all -> 0x0050 }
            r8.epollFd = r1     // Catch:{ all -> 0x004b }
            io.netty.channel.unix.FileDescriptor r2 = io.netty.channel.epoll.Native.newEventFd()     // Catch:{ all -> 0x004b }
            r8.eventFd = r2     // Catch:{ all -> 0x0046 }
            int r3 = r1.intValue()     // Catch:{ IOException -> 0x003d }
            int r4 = r2.intValue()     // Catch:{ IOException -> 0x003d }
            int r5 = io.netty.channel.epoll.Native.EPOLLIN     // Catch:{ IOException -> 0x003d }
            int r6 = io.netty.channel.epoll.Native.EPOLLET     // Catch:{ IOException -> 0x003d }
            r5 = r5 | r6
            io.netty.channel.epoll.Native.epollCtlAdd(r3, r4, r5)     // Catch:{ IOException -> 0x003d }
            io.netty.channel.unix.FileDescriptor r0 = io.netty.channel.epoll.Native.newTimerFd()     // Catch:{ all -> 0x0046 }
            r8.timerFd = r0     // Catch:{ all -> 0x0046 }
            int r3 = r1.intValue()     // Catch:{ IOException -> 0x0034 }
            int r4 = r0.intValue()     // Catch:{ IOException -> 0x0034 }
            int r5 = io.netty.channel.epoll.Native.EPOLLIN     // Catch:{ IOException -> 0x0034 }
            int r6 = io.netty.channel.epoll.Native.EPOLLET     // Catch:{ IOException -> 0x0034 }
            r5 = r5 | r6
            io.netty.channel.epoll.Native.epollCtlAdd(r3, r4, r5)     // Catch:{ IOException -> 0x0034 }
            return
        L_0x0034:
            r3 = move-exception
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0046 }
            java.lang.String r5 = "Unable to add timerFd filedescriptor to epoll"
            r4.<init>(r5, r3)     // Catch:{ all -> 0x0046 }
            throw r4     // Catch:{ all -> 0x0046 }
        L_0x003d:
            r3 = move-exception
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0046 }
            java.lang.String r5 = "Unable to add eventFd filedescriptor to epoll"
            r4.<init>(r5, r3)     // Catch:{ all -> 0x0046 }
            throw r4     // Catch:{ all -> 0x0046 }
        L_0x0046:
            r3 = move-exception
            r7 = r1
            r1 = r0
            r0 = r7
            goto L_0x0053
        L_0x004b:
            r3 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
            goto L_0x0053
        L_0x0050:
            r3 = move-exception
            r1 = r0
            r2 = r1
        L_0x0053:
            if (r0 == 0) goto L_0x005a
            r0.close()     // Catch:{ Exception -> 0x0059 }
            goto L_0x005a
        L_0x0059:
        L_0x005a:
            if (r2 == 0) goto L_0x0061
            r2.close()     // Catch:{ Exception -> 0x0060 }
            goto L_0x0061
        L_0x0060:
        L_0x0061:
            if (r1 == 0) goto L_0x0066
            r1.close()     // Catch:{ Exception -> 0x0066 }
        L_0x0066:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.epoll.EpollEventLoop.openFileDescriptors():void");
    }

    private static Queue<Runnable> newTaskQueue(EventLoopTaskQueueFactory eventLoopTaskQueueFactory) {
        if (eventLoopTaskQueueFactory == null) {
            return newTaskQueue0(DEFAULT_MAX_PENDING_TASKS);
        }
        return eventLoopTaskQueueFactory.newTaskQueue(DEFAULT_MAX_PENDING_TASKS);
    }

    /* access modifiers changed from: package-private */
    public IovArray cleanIovArray() {
        IovArray iovArray2 = this.iovArray;
        if (iovArray2 == null) {
            this.iovArray = new IovArray();
        } else {
            iovArray2.clear();
        }
        return this.iovArray;
    }

    /* access modifiers changed from: package-private */
    public NativeDatagramPacketArray cleanDatagramPacketArray() {
        NativeDatagramPacketArray nativeDatagramPacketArray = this.datagramPacketArray;
        if (nativeDatagramPacketArray == null) {
            this.datagramPacketArray = new NativeDatagramPacketArray();
        } else {
            nativeDatagramPacketArray.clear();
        }
        return this.datagramPacketArray;
    }

    /* access modifiers changed from: protected */
    public void wakeup(boolean z) {
        if (!z && this.nextWakeupNanos.getAndSet(-1) != -1) {
            Native.eventFdWrite(this.eventFd.intValue(), 1);
        }
    }

    /* access modifiers changed from: protected */
    public boolean beforeScheduledTaskSubmitted(long j) {
        return j < this.nextWakeupNanos.get();
    }

    /* access modifiers changed from: protected */
    public boolean afterScheduledTaskSubmitted(long j) {
        return j < this.nextWakeupNanos.get();
    }

    /* access modifiers changed from: package-private */
    public void add(AbstractEpollChannel abstractEpollChannel) throws IOException {
        int intValue = abstractEpollChannel.socket.intValue();
        Native.epollCtlAdd(this.epollFd.intValue(), intValue, abstractEpollChannel.flags);
        AbstractEpollChannel put = this.channels.put(intValue, abstractEpollChannel);
    }

    /* access modifiers changed from: package-private */
    public void modify(AbstractEpollChannel abstractEpollChannel) throws IOException {
        Native.epollCtlMod(this.epollFd.intValue(), abstractEpollChannel.socket.intValue(), abstractEpollChannel.flags);
    }

    /* access modifiers changed from: package-private */
    public void remove(AbstractEpollChannel abstractEpollChannel) throws IOException {
        int intValue = abstractEpollChannel.socket.intValue();
        AbstractEpollChannel remove = this.channels.remove(intValue);
        if (remove != null && remove != abstractEpollChannel) {
            this.channels.put(intValue, remove);
        } else if (abstractEpollChannel.isOpen()) {
            Native.epollCtlDel(this.epollFd.intValue(), intValue);
        }
    }

    /* access modifiers changed from: protected */
    public Queue<Runnable> newTaskQueue(int i) {
        return newTaskQueue0(i);
    }

    private static Queue<Runnable> newTaskQueue0(int i) {
        if (i == Integer.MAX_VALUE) {
            return PlatformDependent.newMpscQueue();
        }
        return PlatformDependent.newMpscQueue(i);
    }

    public int getIoRatio() {
        return this.ioRatio;
    }

    public void setIoRatio(int i) {
        if (i <= 0 || i > 100) {
            throw new IllegalArgumentException("ioRatio: " + i + " (expected: 0 < ioRatio <= 100)");
        }
        this.ioRatio = i;
    }

    public int registeredChannels() {
        return this.channels.size();
    }

    public Iterator<Channel> registeredChannelsIterator() {
        IntObjectMap<AbstractEpollChannel> intObjectMap = this.channels;
        if (intObjectMap.isEmpty()) {
            return SingleThreadEventLoop.ChannelsReadOnlyIterator.empty();
        }
        return new SingleThreadEventLoop.ChannelsReadOnlyIterator(intObjectMap.values());
    }

    private long epollWait(long j) throws IOException {
        if (j == Long.MAX_VALUE) {
            return Native.epollWait(this.epollFd, this.events, this.timerFd, Integer.MAX_VALUE, 0, EPOLL_WAIT_MILLIS_THRESHOLD);
        }
        long deadlineToDelayNanos = deadlineToDelayNanos(j);
        int min = (int) Math.min(deadlineToDelayNanos / 1000000000, 2147483647L);
        return Native.epollWait(this.epollFd, this.events, this.timerFd, min, (int) Math.min(deadlineToDelayNanos - (((long) min) * 1000000000), MAX_SCHEDULED_TIMERFD_NS), EPOLL_WAIT_MILLIS_THRESHOLD);
    }

    private int epollWaitNoTimerChange() throws IOException {
        return Native.epollWait(this.epollFd, this.events, false);
    }

    /* access modifiers changed from: private */
    public int epollWaitNow() throws IOException {
        return Native.epollWait(this.epollFd, this.events, true);
    }

    private int epollBusyWait() throws IOException {
        return Native.epollBusyWait(this.epollFd, this.events);
    }

    private int epollWaitTimeboxed() throws IOException {
        return Native.epollWait(this.epollFd, this.events, 1000);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ac, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ad, code lost:
        handleLoopException(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00b2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b3, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0129, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x012a, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ac A[ExcHandler: all (r4v3 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r2 
      PHI: (r2v4 long) = (r2v3 long), (r2v6 long), (r2v1 long) binds: [B:96:0x012f, B:86:0x0119, B:45:0x009b] A[DONT_GENERATE, DONT_INLINE], Splitter:B:45:0x009b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r12 = this;
            r0 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r2 = r0
        L_0x0006:
            io.netty.channel.SelectStrategy r4 = r12.selectStrategy     // Catch:{ Error -> 0x0141, all -> 0x012b }
            io.netty.util.IntSupplier r5 = r12.selectNowSupplier     // Catch:{ Error -> 0x0141, all -> 0x012b }
            boolean r6 = r12.hasTasks()     // Catch:{ Error -> 0x0141, all -> 0x012b }
            int r4 = r4.calculateStrategy(r5, r6)     // Catch:{ Error -> 0x0141, all -> 0x012b }
            r5 = -3
            if (r4 == r5) goto L_0x00b4
            r5 = -2
            if (r4 == r5) goto L_0x009b
            r5 = -1
            if (r4 == r5) goto L_0x001d
            goto L_0x00b8
        L_0x001d:
            boolean r5 = r12.pendingWakeup     // Catch:{ Error -> 0x0141, all -> 0x012b }
            if (r5 == 0) goto L_0x003b
            int r4 = r12.epollWaitTimeboxed()     // Catch:{ Error -> 0x0141, all -> 0x012b }
            if (r4 == 0) goto L_0x0029
            goto L_0x00b8
        L_0x0029:
            io.netty.util.internal.logging.InternalLogger r5 = logger     // Catch:{ Error -> 0x0141, all -> 0x012b }
            java.lang.String r6 = "Missed eventfd write (not seen after > 1 second)"
            r5.warn((java.lang.String) r6)     // Catch:{ Error -> 0x0141, all -> 0x012b }
            r5 = 0
            r12.pendingWakeup = r5     // Catch:{ Error -> 0x0141, all -> 0x012b }
            boolean r5 = r12.hasTasks()     // Catch:{ Error -> 0x0141, all -> 0x012b }
            if (r5 == 0) goto L_0x003b
            goto L_0x00b8
        L_0x003b:
            long r5 = r12.nextScheduledTaskDeadlineNanos()     // Catch:{ Error -> 0x0141, all -> 0x012b }
            r7 = -1
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x0046
            r5 = r0
        L_0x0046:
            java.util.concurrent.atomic.AtomicLong r9 = r12.nextWakeupNanos     // Catch:{ Error -> 0x0141, all -> 0x012b }
            r9.set(r5)     // Catch:{ Error -> 0x0141, all -> 0x012b }
            r9 = 1
            boolean r10 = r12.hasTasks()     // Catch:{ all -> 0x0083 }
            if (r10 != 0) goto L_0x006c
            int r4 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x005b
            int r4 = r12.epollWaitNoTimerChange()     // Catch:{ all -> 0x0083 }
            goto L_0x006c
        L_0x005b:
            long r10 = r12.epollWait(r5)     // Catch:{ all -> 0x0083 }
            int r4 = io.netty.channel.epoll.Native.epollReady(r10)     // Catch:{ all -> 0x0083 }
            boolean r2 = io.netty.channel.epoll.Native.epollTimerWasUsed(r10)     // Catch:{ all -> 0x0083 }
            if (r2 == 0) goto L_0x006a
            goto L_0x006b
        L_0x006a:
            r5 = r0
        L_0x006b:
            r2 = r5
        L_0x006c:
            java.util.concurrent.atomic.AtomicLong r5 = r12.nextWakeupNanos     // Catch:{ Error -> 0x0141, all -> 0x012b }
            long r5 = r5.get()     // Catch:{ Error -> 0x0141, all -> 0x012b }
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 == 0) goto L_0x0080
            java.util.concurrent.atomic.AtomicLong r5 = r12.nextWakeupNanos     // Catch:{ Error -> 0x0141, all -> 0x012b }
            long r5 = r5.getAndSet(r7)     // Catch:{ Error -> 0x0141, all -> 0x012b }
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 != 0) goto L_0x00b8
        L_0x0080:
            r12.pendingWakeup = r9     // Catch:{ Error -> 0x0141, all -> 0x012b }
            goto L_0x00b8
        L_0x0083:
            r4 = move-exception
            java.util.concurrent.atomic.AtomicLong r5 = r12.nextWakeupNanos     // Catch:{ Error -> 0x0141, all -> 0x012b }
            long r5 = r5.get()     // Catch:{ Error -> 0x0141, all -> 0x012b }
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 == 0) goto L_0x0098
            java.util.concurrent.atomic.AtomicLong r5 = r12.nextWakeupNanos     // Catch:{ Error -> 0x0141, all -> 0x012b }
            long r5 = r5.getAndSet(r7)     // Catch:{ Error -> 0x0141, all -> 0x012b }
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 != 0) goto L_0x009a
        L_0x0098:
            r12.pendingWakeup = r9     // Catch:{ Error -> 0x0141, all -> 0x012b }
        L_0x009a:
            throw r4     // Catch:{ Error -> 0x0141, all -> 0x012b }
        L_0x009b:
            boolean r4 = r12.isShuttingDown()     // Catch:{ Error -> 0x00b2, all -> 0x00ac }
            if (r4 == 0) goto L_0x0006
            r12.closeAll()     // Catch:{ Error -> 0x00b2, all -> 0x00ac }
            boolean r4 = r12.confirmShutdown()     // Catch:{ Error -> 0x00b2, all -> 0x00ac }
            if (r4 == 0) goto L_0x0006
            goto L_0x0153
        L_0x00ac:
            r4 = move-exception
            r12.handleLoopException(r4)
            goto L_0x0006
        L_0x00b2:
            r0 = move-exception
            throw r0
        L_0x00b4:
            int r4 = r12.epollBusyWait()     // Catch:{ Error -> 0x0141, all -> 0x012b }
        L_0x00b8:
            int r5 = r12.ioRatio     // Catch:{ Error -> 0x0141, all -> 0x012b }
            r6 = 100
            if (r5 != r6) goto L_0x00d3
            if (r4 <= 0) goto L_0x00cf
            io.netty.channel.epoll.EpollEventArray r5 = r12.events     // Catch:{ all -> 0x00ca }
            boolean r5 = r12.processReady(r5, r4)     // Catch:{ all -> 0x00ca }
            if (r5 == 0) goto L_0x00cf
            r2 = r0
            goto L_0x00cf
        L_0x00ca:
            r4 = move-exception
            r12.runAllTasks()     // Catch:{ Error -> 0x0141, all -> 0x012b }
            throw r4     // Catch:{ Error -> 0x0141, all -> 0x012b }
        L_0x00cf:
            r12.runAllTasks()     // Catch:{ Error -> 0x0141, all -> 0x012b }
            goto L_0x0108
        L_0x00d3:
            if (r4 <= 0) goto L_0x0103
            long r6 = java.lang.System.nanoTime()     // Catch:{ Error -> 0x0141, all -> 0x012b }
            io.netty.channel.epoll.EpollEventArray r8 = r12.events     // Catch:{ all -> 0x00f2 }
            boolean r8 = r12.processReady(r8, r4)     // Catch:{ all -> 0x00f2 }
            if (r8 == 0) goto L_0x00e2
            r2 = r0
        L_0x00e2:
            long r8 = java.lang.System.nanoTime()     // Catch:{ Error -> 0x0141, all -> 0x012b }
            long r8 = r8 - r6
            int r6 = 100 - r5
            long r6 = (long) r6     // Catch:{ Error -> 0x0141, all -> 0x012b }
            long r8 = r8 * r6
            long r5 = (long) r5     // Catch:{ Error -> 0x0141, all -> 0x012b }
            long r8 = r8 / r5
            r12.runAllTasks(r8)     // Catch:{ Error -> 0x0141, all -> 0x012b }
            goto L_0x0108
        L_0x00f2:
            r4 = move-exception
            long r8 = java.lang.System.nanoTime()     // Catch:{ Error -> 0x0141, all -> 0x012b }
            long r8 = r8 - r6
            int r6 = 100 - r5
            long r6 = (long) r6     // Catch:{ Error -> 0x0141, all -> 0x012b }
            long r8 = r8 * r6
            long r5 = (long) r5     // Catch:{ Error -> 0x0141, all -> 0x012b }
            long r8 = r8 / r5
            r12.runAllTasks(r8)     // Catch:{ Error -> 0x0141, all -> 0x012b }
            throw r4     // Catch:{ Error -> 0x0141, all -> 0x012b }
        L_0x0103:
            r5 = 0
            r12.runAllTasks(r5)     // Catch:{ Error -> 0x0141, all -> 0x012b }
        L_0x0108:
            boolean r5 = r12.allowGrowing     // Catch:{ Error -> 0x0141, all -> 0x012b }
            if (r5 == 0) goto L_0x0119
            io.netty.channel.epoll.EpollEventArray r5 = r12.events     // Catch:{ Error -> 0x0141, all -> 0x012b }
            int r5 = r5.length()     // Catch:{ Error -> 0x0141, all -> 0x012b }
            if (r4 != r5) goto L_0x0119
            io.netty.channel.epoll.EpollEventArray r4 = r12.events     // Catch:{ Error -> 0x0141, all -> 0x012b }
            r4.increase()     // Catch:{ Error -> 0x0141, all -> 0x012b }
        L_0x0119:
            boolean r4 = r12.isShuttingDown()     // Catch:{ Error -> 0x0129, all -> 0x00ac }
            if (r4 == 0) goto L_0x0006
            r12.closeAll()     // Catch:{ Error -> 0x0129, all -> 0x00ac }
            boolean r4 = r12.confirmShutdown()     // Catch:{ Error -> 0x0129, all -> 0x00ac }
            if (r4 == 0) goto L_0x0006
            goto L_0x0153
        L_0x0129:
            r0 = move-exception
            throw r0
        L_0x012b:
            r4 = move-exception
            r12.handleLoopException(r4)     // Catch:{ all -> 0x0143 }
            boolean r4 = r12.isShuttingDown()     // Catch:{ Error -> 0x013f, all -> 0x00ac }
            if (r4 == 0) goto L_0x0006
            r12.closeAll()     // Catch:{ Error -> 0x013f, all -> 0x00ac }
            boolean r4 = r12.confirmShutdown()     // Catch:{ Error -> 0x013f, all -> 0x00ac }
            if (r4 == 0) goto L_0x0006
            goto L_0x0153
        L_0x013f:
            r0 = move-exception
            throw r0
        L_0x0141:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0143 }
        L_0x0143:
            r0 = move-exception
            boolean r1 = r12.isShuttingDown()     // Catch:{ Error -> 0x0159, all -> 0x0154 }
            if (r1 == 0) goto L_0x0158
            r12.closeAll()     // Catch:{ Error -> 0x0159, all -> 0x0154 }
            boolean r1 = r12.confirmShutdown()     // Catch:{ Error -> 0x0159, all -> 0x0154 }
            if (r1 == 0) goto L_0x0158
        L_0x0153:
            return
        L_0x0154:
            r1 = move-exception
            r12.handleLoopException(r1)
        L_0x0158:
            throw r0
        L_0x0159:
            r0 = move-exception
            goto L_0x015c
        L_0x015b:
            throw r0
        L_0x015c:
            goto L_0x015b
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.epoll.EpollEventLoop.run():void");
    }

    /* access modifiers changed from: package-private */
    public void handleLoopException(Throwable th) {
        logger.warn("Unexpected exception in the selector loop.", th);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException unused) {
        }
    }

    private void closeAll() {
        for (AbstractEpollChannel abstractEpollChannel : (AbstractEpollChannel[]) this.channels.values().toArray(new AbstractEpollChannel[0])) {
            abstractEpollChannel.unsafe().close(abstractEpollChannel.unsafe().voidPromise());
        }
    }

    private boolean processReady(EpollEventArray epollEventArray, int i) {
        boolean z = false;
        for (int i2 = 0; i2 < i; i2++) {
            int fd = epollEventArray.fd(i2);
            if (fd == this.eventFd.intValue()) {
                this.pendingWakeup = false;
            } else if (fd == this.timerFd.intValue()) {
                z = true;
            } else {
                long events2 = (long) epollEventArray.events(i2);
                AbstractEpollChannel abstractEpollChannel = this.channels.get(fd);
                if (abstractEpollChannel != null) {
                    AbstractEpollChannel.AbstractEpollUnsafe abstractEpollUnsafe = (AbstractEpollChannel.AbstractEpollUnsafe) abstractEpollChannel.unsafe();
                    if ((((long) (Native.EPOLLERR | Native.EPOLLOUT)) & events2) != 0) {
                        abstractEpollUnsafe.epollOutReady();
                    }
                    if ((((long) (Native.EPOLLERR | Native.EPOLLIN)) & events2) != 0) {
                        abstractEpollUnsafe.epollInReady();
                    }
                    if ((events2 & ((long) Native.EPOLLRDHUP)) != 0) {
                        abstractEpollUnsafe.epollRdHupReady();
                    }
                } else {
                    try {
                        Native.epollCtlDel(this.epollFd.intValue(), fd);
                    } catch (IOException unused) {
                    }
                }
            }
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public void cleanup() {
        try {
            closeFileDescriptors();
            IovArray iovArray2 = this.iovArray;
            if (iovArray2 != null) {
                iovArray2.release();
                this.iovArray = null;
            }
            NativeDatagramPacketArray nativeDatagramPacketArray = this.datagramPacketArray;
            if (nativeDatagramPacketArray != null) {
                nativeDatagramPacketArray.release();
                this.datagramPacketArray = null;
            }
            this.events.free();
        } catch (Throwable th) {
            if (this.iovArray != null) {
                this.iovArray.release();
                this.iovArray = null;
            }
            NativeDatagramPacketArray nativeDatagramPacketArray2 = this.datagramPacketArray;
            if (nativeDatagramPacketArray2 != null) {
                nativeDatagramPacketArray2.release();
                this.datagramPacketArray = null;
            }
            this.events.free();
            throw th;
        }
    }

    public void closeFileDescriptors() {
        while (true) {
            if (this.pendingWakeup) {
                try {
                    int epollWaitTimeboxed = epollWaitTimeboxed();
                    if (epollWaitTimeboxed == 0) {
                        break;
                    }
                    int i = 0;
                    while (true) {
                        if (i >= epollWaitTimeboxed) {
                            break;
                        } else if (this.events.fd(i) == this.eventFd.intValue()) {
                            this.pendingWakeup = false;
                            break;
                        } else {
                            i++;
                        }
                    }
                } catch (IOException unused) {
                }
            }
        }
        try {
            this.eventFd.close();
        } catch (IOException e) {
            logger.warn("Failed to close the event fd.", (Throwable) e);
        }
        try {
            this.timerFd.close();
        } catch (IOException e2) {
            logger.warn("Failed to close the timer fd.", (Throwable) e2);
        }
        try {
            this.epollFd.close();
        } catch (IOException e3) {
            logger.warn("Failed to close the epoll fd.", (Throwable) e3);
        }
    }
}
