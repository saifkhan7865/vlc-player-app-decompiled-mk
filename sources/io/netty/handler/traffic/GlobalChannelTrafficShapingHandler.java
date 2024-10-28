package io.netty.handler.traffic;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.traffic.AbstractTrafficShapingHandler;
import io.netty.util.Attribute;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.AbstractCollection;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@ChannelHandler.Sharable
public class GlobalChannelTrafficShapingHandler extends AbstractTrafficShapingHandler {
    private static final float DEFAULT_ACCELERATION = -0.1f;
    private static final float DEFAULT_DEVIATION = 0.1f;
    private static final float DEFAULT_SLOWDOWN = 0.4f;
    private static final float MAX_DEVIATION = 0.4f;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) GlobalChannelTrafficShapingHandler.class);
    private volatile float accelerationFactor;
    final ConcurrentMap<Integer, PerChannel> channelQueues = PlatformDependent.newConcurrentHashMap();
    private final AtomicLong cumulativeReadBytes = new AtomicLong();
    private final AtomicLong cumulativeWrittenBytes = new AtomicLong();
    private volatile float maxDeviation;
    volatile long maxGlobalWriteSize = 419430400;
    private final AtomicLong queuesSize = new AtomicLong();
    private volatile long readChannelLimit;
    private volatile boolean readDeviationActive;
    private volatile float slowDownFactor;
    private volatile long writeChannelLimit;
    private volatile boolean writeDeviationActive;

    /* access modifiers changed from: protected */
    public int userDefinedWritabilityIndex() {
        return 3;
    }

    static final class PerChannel {
        TrafficCounter channelTrafficCounter;
        long lastReadTimestamp;
        long lastWriteTimestamp;
        ArrayDeque<ToSend> messagesQueue;
        long queueSize;

        PerChannel() {
        }
    }

    /* access modifiers changed from: package-private */
    public void createGlobalTrafficCounter(ScheduledExecutorService scheduledExecutorService) {
        setMaxDeviation(0.1f, 0.4f, DEFAULT_ACCELERATION);
        ObjectUtil.checkNotNullWithIAE(scheduledExecutorService, "executor");
        GlobalChannelTrafficCounter globalChannelTrafficCounter = new GlobalChannelTrafficCounter(this, scheduledExecutorService, "GlobalChannelTC", this.checkInterval);
        setTrafficCounter(globalChannelTrafficCounter);
        globalChannelTrafficCounter.start();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GlobalChannelTrafficShapingHandler(ScheduledExecutorService scheduledExecutorService, long j, long j2, long j3, long j4, long j5, long j6) {
        super(j, j2, j5, j6);
        createGlobalTrafficCounter(scheduledExecutorService);
        this.writeChannelLimit = j3;
        this.readChannelLimit = j4;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GlobalChannelTrafficShapingHandler(ScheduledExecutorService scheduledExecutorService, long j, long j2, long j3, long j4, long j5) {
        super(j, j2, j5);
        this.writeChannelLimit = j3;
        this.readChannelLimit = j4;
        createGlobalTrafficCounter(scheduledExecutorService);
    }

    public GlobalChannelTrafficShapingHandler(ScheduledExecutorService scheduledExecutorService, long j, long j2, long j3, long j4) {
        super(j, j2);
        this.writeChannelLimit = j3;
        this.readChannelLimit = j4;
        createGlobalTrafficCounter(scheduledExecutorService);
    }

    public GlobalChannelTrafficShapingHandler(ScheduledExecutorService scheduledExecutorService, long j) {
        super(j);
        createGlobalTrafficCounter(scheduledExecutorService);
    }

    public GlobalChannelTrafficShapingHandler(ScheduledExecutorService scheduledExecutorService) {
        createGlobalTrafficCounter(scheduledExecutorService);
    }

    public float maxDeviation() {
        return this.maxDeviation;
    }

    public float accelerationFactor() {
        return this.accelerationFactor;
    }

    public float slowDownFactor() {
        return this.slowDownFactor;
    }

    public void setMaxDeviation(float f, float f2, float f3) {
        if (f <= 0.4f) {
            ObjectUtil.checkPositiveOrZero(f2, "slowDownFactor");
            if (f3 <= 0.0f) {
                this.maxDeviation = f;
                this.accelerationFactor = f3 + 1.0f;
                this.slowDownFactor = f2 + 1.0f;
                return;
            }
            throw new IllegalArgumentException("accelerationFactor must be <= 0");
        }
        throw new IllegalArgumentException("maxDeviation must be <= 0.4");
    }

    private void computeDeviationCumulativeBytes() {
        long j = 0;
        long j2 = Long.MAX_VALUE;
        long j3 = Long.MAX_VALUE;
        long j4 = 0;
        for (PerChannel perChannel : this.channelQueues.values()) {
            long cumulativeWrittenBytes2 = perChannel.channelTrafficCounter.cumulativeWrittenBytes();
            if (j < cumulativeWrittenBytes2) {
                j = cumulativeWrittenBytes2;
            }
            if (j2 > cumulativeWrittenBytes2) {
                j2 = cumulativeWrittenBytes2;
            }
            long cumulativeReadBytes2 = perChannel.channelTrafficCounter.cumulativeReadBytes();
            if (j4 < cumulativeReadBytes2) {
                j4 = cumulativeReadBytes2;
            }
            if (j3 > cumulativeReadBytes2) {
                j3 = cumulativeReadBytes2;
            }
        }
        boolean z = false;
        boolean z2 = this.channelQueues.size() > 1;
        this.readDeviationActive = z2 && j3 < j4 / 2;
        if (z2 && j2 < j / 2) {
            z = true;
        }
        this.writeDeviationActive = z;
        this.cumulativeWrittenBytes.set(j);
        this.cumulativeReadBytes.set(j4);
    }

    /* access modifiers changed from: protected */
    public void doAccounting(TrafficCounter trafficCounter) {
        computeDeviationCumulativeBytes();
        super.doAccounting(trafficCounter);
    }

    private long computeBalancedWait(float f, float f2, long j) {
        float f3;
        if (f2 == 0.0f) {
            return j;
        }
        float f4 = f / f2;
        if (f4 <= this.maxDeviation) {
            f3 = this.accelerationFactor;
        } else if (f4 < 1.0f - this.maxDeviation) {
            return j;
        } else {
            f3 = this.slowDownFactor;
            if (j < 10) {
                j = 10;
            }
        }
        return (long) (((float) j) * f3);
    }

    public long getMaxGlobalWriteSize() {
        return this.maxGlobalWriteSize;
    }

    public void setMaxGlobalWriteSize(long j) {
        this.maxGlobalWriteSize = ObjectUtil.checkPositive(j, "maxGlobalWriteSize");
    }

    public long queuesSize() {
        return this.queuesSize.get();
    }

    public void configureChannel(long j, long j2) {
        this.writeChannelLimit = j;
        this.readChannelLimit = j2;
        long milliSecondFromNano = TrafficCounter.milliSecondFromNano();
        for (PerChannel perChannel : this.channelQueues.values()) {
            perChannel.channelTrafficCounter.resetAccounting(milliSecondFromNano);
        }
    }

    public long getWriteChannelLimit() {
        return this.writeChannelLimit;
    }

    public void setWriteChannelLimit(long j) {
        this.writeChannelLimit = j;
        long milliSecondFromNano = TrafficCounter.milliSecondFromNano();
        for (PerChannel perChannel : this.channelQueues.values()) {
            perChannel.channelTrafficCounter.resetAccounting(milliSecondFromNano);
        }
    }

    public long getReadChannelLimit() {
        return this.readChannelLimit;
    }

    public void setReadChannelLimit(long j) {
        this.readChannelLimit = j;
        long milliSecondFromNano = TrafficCounter.milliSecondFromNano();
        for (PerChannel perChannel : this.channelQueues.values()) {
            perChannel.channelTrafficCounter.resetAccounting(milliSecondFromNano);
        }
    }

    public final void release() {
        this.trafficCounter.stop();
    }

    private PerChannel getOrSetPerChannel(ChannelHandlerContext channelHandlerContext) {
        Integer valueOf = Integer.valueOf(channelHandlerContext.channel().hashCode());
        PerChannel perChannel = (PerChannel) this.channelQueues.get(valueOf);
        if (perChannel != null) {
            return perChannel;
        }
        PerChannel perChannel2 = new PerChannel();
        perChannel2.messagesQueue = new ArrayDeque<>();
        perChannel2.channelTrafficCounter = new TrafficCounter(this, (ScheduledExecutorService) null, "ChannelTC" + channelHandlerContext.channel().hashCode(), this.checkInterval);
        perChannel2.queueSize = 0;
        perChannel2.lastReadTimestamp = TrafficCounter.milliSecondFromNano();
        perChannel2.lastWriteTimestamp = perChannel2.lastReadTimestamp;
        this.channelQueues.put(valueOf, perChannel2);
        return perChannel2;
    }

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        getOrSetPerChannel(channelHandlerContext);
        this.trafficCounter.resetCumulativeTime();
        super.handlerAdded(channelHandlerContext);
    }

    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.trafficCounter.resetCumulativeTime();
        Channel channel = channelHandlerContext.channel();
        PerChannel perChannel = (PerChannel) this.channelQueues.remove(Integer.valueOf(channel.hashCode()));
        if (perChannel != null) {
            synchronized (perChannel) {
                if (channel.isActive()) {
                    Iterator<ToSend> it = perChannel.messagesQueue.iterator();
                    while (it.hasNext()) {
                        ToSend next = it.next();
                        long calculateSize = calculateSize(next.toSend);
                        this.trafficCounter.bytesRealWriteFlowControl(calculateSize);
                        perChannel.channelTrafficCounter.bytesRealWriteFlowControl(calculateSize);
                        perChannel.queueSize -= calculateSize;
                        this.queuesSize.addAndGet(-calculateSize);
                        channelHandlerContext.write(next.toSend, next.promise);
                    }
                } else {
                    this.queuesSize.addAndGet(-perChannel.queueSize);
                    Iterator<ToSend> it2 = perChannel.messagesQueue.iterator();
                    while (it2.hasNext()) {
                        ToSend next2 = it2.next();
                        if (next2.toSend instanceof ByteBuf) {
                            ((ByteBuf) next2.toSend).release();
                        }
                    }
                }
                perChannel.messagesQueue.clear();
            }
        }
        releaseWriteSuspended(channelHandlerContext);
        releaseReadSuspended(channelHandlerContext);
        super.handlerRemoved(channelHandlerContext);
    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        long j;
        ChannelHandlerContext channelHandlerContext2 = channelHandlerContext;
        long calculateSize = calculateSize(obj);
        long milliSecondFromNano = TrafficCounter.milliSecondFromNano();
        long j2 = 0;
        if (calculateSize > 0) {
            long readTimeToWait = this.trafficCounter.readTimeToWait(calculateSize, getReadLimit(), this.maxTime, milliSecondFromNano);
            PerChannel perChannel = (PerChannel) this.channelQueues.get(Integer.valueOf(channelHandlerContext.channel().hashCode()));
            if (perChannel != null) {
                long j3 = calculateSize;
                PerChannel perChannel2 = perChannel;
                long readTimeToWait2 = perChannel.channelTrafficCounter.readTimeToWait(j3, this.readChannelLimit, this.maxTime, milliSecondFromNano);
                if (this.readDeviationActive) {
                    long cumulativeReadBytes2 = perChannel2.channelTrafficCounter.cumulativeReadBytes();
                    long j4 = this.cumulativeReadBytes.get();
                    if (cumulativeReadBytes2 > 0) {
                        j2 = cumulativeReadBytes2;
                    }
                    if (j4 < j2) {
                        j4 = j2;
                    }
                    j2 = computeBalancedWait((float) j2, (float) j4, readTimeToWait2);
                } else {
                    j2 = readTimeToWait2;
                }
            }
            if (j2 < readTimeToWait) {
                j2 = readTimeToWait;
            }
            j = milliSecondFromNano;
            long checkWaitReadTime = checkWaitReadTime(channelHandlerContext, j2, milliSecondFromNano);
            if (checkWaitReadTime >= 10) {
                Channel channel = channelHandlerContext.channel();
                ChannelConfig config = channel.config();
                InternalLogger internalLogger = logger;
                if (internalLogger.isDebugEnabled()) {
                    internalLogger.debug("Read Suspend: " + checkWaitReadTime + AbstractJsonLexerKt.COLON + config.isAutoRead() + AbstractJsonLexerKt.COLON + isHandlerActive(channelHandlerContext));
                }
                if (config.isAutoRead() && isHandlerActive(channelHandlerContext)) {
                    config.setAutoRead(false);
                    channel.attr(READ_SUSPENDED).set(true);
                    Attribute attr = channel.attr(REOPEN_TASK);
                    Runnable runnable = (Runnable) attr.get();
                    if (runnable == null) {
                        runnable = new AbstractTrafficShapingHandler.ReopenReadTimerTask(channelHandlerContext2);
                        attr.set(runnable);
                    }
                    channelHandlerContext.executor().schedule(runnable, checkWaitReadTime, TimeUnit.MILLISECONDS);
                    if (internalLogger.isDebugEnabled()) {
                        internalLogger.debug("Suspend final status => " + config.isAutoRead() + AbstractJsonLexerKt.COLON + isHandlerActive(channelHandlerContext) + " will reopened at: " + checkWaitReadTime);
                    }
                }
            }
        } else {
            j = milliSecondFromNano;
        }
        informReadOperation(channelHandlerContext2, j);
        channelHandlerContext.fireChannelRead(obj);
    }

    /* access modifiers changed from: protected */
    public long checkWaitReadTime(ChannelHandlerContext channelHandlerContext, long j, long j2) {
        PerChannel perChannel = (PerChannel) this.channelQueues.get(Integer.valueOf(channelHandlerContext.channel().hashCode()));
        return (perChannel == null || j <= this.maxTime || (j2 + j) - perChannel.lastReadTimestamp <= this.maxTime) ? j : this.maxTime;
    }

    /* access modifiers changed from: protected */
    public void informReadOperation(ChannelHandlerContext channelHandlerContext, long j) {
        PerChannel perChannel = (PerChannel) this.channelQueues.get(Integer.valueOf(channelHandlerContext.channel().hashCode()));
        if (perChannel != null) {
            perChannel.lastReadTimestamp = j;
        }
    }

    private static final class ToSend {
        final ChannelPromise promise;
        final long relativeTimeAction;
        final long size;
        final Object toSend;

        private ToSend(long j, Object obj, long j2, ChannelPromise channelPromise) {
            this.relativeTimeAction = j;
            this.toSend = obj;
            this.size = j2;
            this.promise = channelPromise;
        }
    }

    /* access modifiers changed from: protected */
    public long maximumCumulativeWrittenBytes() {
        return this.cumulativeWrittenBytes.get();
    }

    /* access modifiers changed from: protected */
    public long maximumCumulativeReadBytes() {
        return this.cumulativeReadBytes.get();
    }

    public Collection<TrafficCounter> channelTrafficCounters() {
        return new AbstractCollection<TrafficCounter>() {
            public Iterator<TrafficCounter> iterator() {
                return new Iterator<TrafficCounter>() {
                    final Iterator<PerChannel> iter;

                    {
                        this.iter = GlobalChannelTrafficShapingHandler.this.channelQueues.values().iterator();
                    }

                    public boolean hasNext() {
                        return this.iter.hasNext();
                    }

                    public TrafficCounter next() {
                        return this.iter.next().channelTrafficCounter;
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }

            public int size() {
                return GlobalChannelTrafficShapingHandler.this.channelQueues.size();
            }
        };
    }

    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        long calculateSize = calculateSize(obj);
        long milliSecondFromNano = TrafficCounter.milliSecondFromNano();
        long j = 0;
        if (calculateSize > 0) {
            long writeTimeToWait = this.trafficCounter.writeTimeToWait(calculateSize, getWriteLimit(), this.maxTime, milliSecondFromNano);
            PerChannel perChannel = (PerChannel) this.channelQueues.get(Integer.valueOf(channelHandlerContext.channel().hashCode()));
            if (perChannel != null) {
                long writeTimeToWait2 = perChannel.channelTrafficCounter.writeTimeToWait(calculateSize, this.writeChannelLimit, this.maxTime, milliSecondFromNano);
                if (this.writeDeviationActive) {
                    long cumulativeWrittenBytes2 = perChannel.channelTrafficCounter.cumulativeWrittenBytes();
                    long j2 = this.cumulativeWrittenBytes.get();
                    if (cumulativeWrittenBytes2 > 0) {
                        j = cumulativeWrittenBytes2;
                    }
                    j = computeBalancedWait((float) j, (float) (j2 < j ? j : j2), writeTimeToWait2);
                } else {
                    j = writeTimeToWait2;
                }
            }
            if (j >= writeTimeToWait) {
                writeTimeToWait = j;
            }
            if (writeTimeToWait >= 10) {
                InternalLogger internalLogger = logger;
                if (internalLogger.isDebugEnabled()) {
                    internalLogger.debug("Write suspend: " + writeTimeToWait + AbstractJsonLexerKt.COLON + channelHandlerContext.channel().config().isAutoRead() + AbstractJsonLexerKt.COLON + isHandlerActive(channelHandlerContext));
                }
                submitWrite(channelHandlerContext, obj, calculateSize, writeTimeToWait, milliSecondFromNano, channelPromise);
                return;
            }
        }
        submitWrite(channelHandlerContext, obj, calculateSize, 0, milliSecondFromNano, channelPromise);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x009f, code lost:
        if (r2 == false) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a1, code lost:
        setUserDefinedWritability(r0, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a4, code lost:
        r20 = r17;
        r21 = r12;
        r22 = r2.relativeTimeAction;
        r17.executor().schedule((java.lang.Runnable) new io.netty.handler.traffic.GlobalChannelTrafficShapingHandler.AnonymousClass2(r16), r13, java.util.concurrent.TimeUnit.MILLISECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00be, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void submitWrite(io.netty.channel.ChannelHandlerContext r17, java.lang.Object r18, long r19, long r21, long r23, io.netty.channel.ChannelPromise r25) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            r10 = r19
            r2 = r23
            io.netty.channel.Channel r4 = r17.channel()
            int r4 = r4.hashCode()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.util.concurrent.ConcurrentMap<java.lang.Integer, io.netty.handler.traffic.GlobalChannelTrafficShapingHandler$PerChannel> r5 = r1.channelQueues
            java.lang.Object r4 = r5.get(r4)
            io.netty.handler.traffic.GlobalChannelTrafficShapingHandler$PerChannel r4 = (io.netty.handler.traffic.GlobalChannelTrafficShapingHandler.PerChannel) r4
            if (r4 != 0) goto L_0x0022
            io.netty.handler.traffic.GlobalChannelTrafficShapingHandler$PerChannel r4 = r16.getOrSetPerChannel(r17)
        L_0x0022:
            r12 = r4
            monitor-enter(r12)
            r4 = 0
            int r6 = (r21 > r4 ? 1 : (r21 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0047
            java.util.ArrayDeque<io.netty.handler.traffic.GlobalChannelTrafficShapingHandler$ToSend> r4 = r12.messagesQueue     // Catch:{ all -> 0x00bf }
            boolean r4 = r4.isEmpty()     // Catch:{ all -> 0x00bf }
            if (r4 == 0) goto L_0x0047
            io.netty.handler.traffic.TrafficCounter r4 = r1.trafficCounter     // Catch:{ all -> 0x00bf }
            r4.bytesRealWriteFlowControl(r10)     // Catch:{ all -> 0x00bf }
            io.netty.handler.traffic.TrafficCounter r4 = r12.channelTrafficCounter     // Catch:{ all -> 0x00bf }
            r4.bytesRealWriteFlowControl(r10)     // Catch:{ all -> 0x00bf }
            r5 = r18
            r8 = r25
            r0.write(r5, r8)     // Catch:{ all -> 0x00bf }
            r12.lastWriteTimestamp = r2     // Catch:{ all -> 0x00bf }
            monitor-exit(r12)     // Catch:{ all -> 0x00bf }
            return
        L_0x0047:
            r5 = r18
            r8 = r25
            long r6 = r1.maxTime     // Catch:{ all -> 0x00bf }
            int r4 = (r21 > r6 ? 1 : (r21 == r6 ? 0 : -1))
            if (r4 <= 0) goto L_0x0060
            long r6 = r2 + r21
            long r13 = r12.lastWriteTimestamp     // Catch:{ all -> 0x00bf }
            long r6 = r6 - r13
            long r13 = r1.maxTime     // Catch:{ all -> 0x00bf }
            int r4 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            if (r4 <= 0) goto L_0x0060
            long r6 = r1.maxTime     // Catch:{ all -> 0x00bf }
            r13 = r6
            goto L_0x0062
        L_0x0060:
            r13 = r21
        L_0x0062:
            io.netty.handler.traffic.GlobalChannelTrafficShapingHandler$ToSend r15 = new io.netty.handler.traffic.GlobalChannelTrafficShapingHandler$ToSend     // Catch:{ all -> 0x00bf }
            long r6 = r13 + r2
            r9 = 0
            r2 = r15
            r3 = r6
            r5 = r18
            r6 = r19
            r8 = r25
            r2.<init>(r3, r5, r6, r8)     // Catch:{ all -> 0x00bf }
            java.util.ArrayDeque<io.netty.handler.traffic.GlobalChannelTrafficShapingHandler$ToSend> r2 = r12.messagesQueue     // Catch:{ all -> 0x00bf }
            r2.addLast(r15)     // Catch:{ all -> 0x00bf }
            long r2 = r12.queueSize     // Catch:{ all -> 0x00bf }
            long r2 = r2 + r10
            r12.queueSize = r2     // Catch:{ all -> 0x00bf }
            java.util.concurrent.atomic.AtomicLong r2 = r1.queuesSize     // Catch:{ all -> 0x00bf }
            r2.addAndGet(r10)     // Catch:{ all -> 0x00bf }
            long r2 = r12.queueSize     // Catch:{ all -> 0x00bf }
            r18 = r16
            r19 = r17
            r20 = r13
            r22 = r2
            r18.checkWriteSuspend(r19, r20, r22)     // Catch:{ all -> 0x00bf }
            java.util.concurrent.atomic.AtomicLong r2 = r1.queuesSize     // Catch:{ all -> 0x00bf }
            long r2 = r2.get()     // Catch:{ all -> 0x00bf }
            long r4 = r1.maxGlobalWriteSize     // Catch:{ all -> 0x00bf }
            r6 = 0
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 <= 0) goto L_0x009d
            r2 = 1
            goto L_0x009e
        L_0x009d:
            r2 = 0
        L_0x009e:
            monitor-exit(r12)     // Catch:{ all -> 0x00bf }
            if (r2 == 0) goto L_0x00a4
            r1.setUserDefinedWritability(r0, r6)
        L_0x00a4:
            long r2 = r15.relativeTimeAction
            io.netty.util.concurrent.EventExecutor r4 = r17.executor()
            io.netty.handler.traffic.GlobalChannelTrafficShapingHandler$2 r5 = new io.netty.handler.traffic.GlobalChannelTrafficShapingHandler$2
            r18 = r5
            r19 = r16
            r20 = r17
            r21 = r12
            r22 = r2
            r18.<init>(r20, r21, r22)
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS
            r4.schedule((java.lang.Runnable) r5, (long) r13, (java.util.concurrent.TimeUnit) r0)
            return
        L_0x00bf:
            r0 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x00bf }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.traffic.GlobalChannelTrafficShapingHandler.submitWrite(io.netty.channel.ChannelHandlerContext, java.lang.Object, long, long, long, io.netty.channel.ChannelPromise):void");
    }

    /* access modifiers changed from: private */
    public void sendAllValid(ChannelHandlerContext channelHandlerContext, PerChannel perChannel, long j) {
        synchronized (perChannel) {
            ToSend pollFirst = perChannel.messagesQueue.pollFirst();
            while (true) {
                if (pollFirst != null) {
                    if (pollFirst.relativeTimeAction > j) {
                        perChannel.messagesQueue.addFirst(pollFirst);
                        break;
                    }
                    long j2 = pollFirst.size;
                    this.trafficCounter.bytesRealWriteFlowControl(j2);
                    perChannel.channelTrafficCounter.bytesRealWriteFlowControl(j2);
                    perChannel.queueSize -= j2;
                    this.queuesSize.addAndGet(-j2);
                    channelHandlerContext.write(pollFirst.toSend, pollFirst.promise);
                    perChannel.lastWriteTimestamp = j;
                    pollFirst = perChannel.messagesQueue.pollFirst();
                } else {
                    break;
                }
            }
            if (perChannel.messagesQueue.isEmpty()) {
                releaseWriteSuspended(channelHandlerContext);
            }
        }
        channelHandlerContext.flush();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(340);
        sb.append(super.toString());
        sb.append(" Write Channel Limit: ");
        sb.append(this.writeChannelLimit);
        sb.append(" Read Channel Limit: ");
        sb.append(this.readChannelLimit);
        return sb.toString();
    }
}
