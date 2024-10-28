package io.netty.handler.traffic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.FileRegion;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.concurrent.TimeUnit;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public abstract class AbstractTrafficShapingHandler extends ChannelDuplexHandler {
    static final int CHANNEL_DEFAULT_USER_DEFINED_WRITABILITY_INDEX = 1;
    public static final long DEFAULT_CHECK_INTERVAL = 1000;
    static final long DEFAULT_MAX_SIZE = 4194304;
    public static final long DEFAULT_MAX_TIME = 15000;
    static final int GLOBALCHANNEL_DEFAULT_USER_DEFINED_WRITABILITY_INDEX = 3;
    static final int GLOBAL_DEFAULT_USER_DEFINED_WRITABILITY_INDEX = 2;
    static final long MINIMAL_WAIT = 10;
    static final AttributeKey<Boolean> READ_SUSPENDED;
    static final AttributeKey<Runnable> REOPEN_TASK;
    /* access modifiers changed from: private */
    public static final InternalLogger logger;
    protected volatile long checkInterval;
    protected volatile long maxTime;
    volatile long maxWriteDelay;
    volatile long maxWriteSize;
    private volatile long readLimit;
    protected TrafficCounter trafficCounter;
    final int userDefinedWritabilityIndex;
    private volatile long writeLimit;

    /* access modifiers changed from: package-private */
    public long checkWaitReadTime(ChannelHandlerContext channelHandlerContext, long j, long j2) {
        return j;
    }

    /* access modifiers changed from: protected */
    public void doAccounting(TrafficCounter trafficCounter2) {
    }

    /* access modifiers changed from: package-private */
    public void informReadOperation(ChannelHandlerContext channelHandlerContext, long j) {
    }

    /* access modifiers changed from: package-private */
    public abstract void submitWrite(ChannelHandlerContext channelHandlerContext, Object obj, long j, long j2, long j3, ChannelPromise channelPromise);

    /* access modifiers changed from: protected */
    public int userDefinedWritabilityIndex() {
        return 1;
    }

    static {
        Class<AbstractTrafficShapingHandler> cls = AbstractTrafficShapingHandler.class;
        logger = InternalLoggerFactory.getInstance((Class<?>) cls);
        READ_SUSPENDED = AttributeKey.valueOf(cls.getName() + ".READ_SUSPENDED");
        REOPEN_TASK = AttributeKey.valueOf(cls.getName() + ".REOPEN_TASK");
    }

    /* access modifiers changed from: package-private */
    public void setTrafficCounter(TrafficCounter trafficCounter2) {
        this.trafficCounter = trafficCounter2;
    }

    protected AbstractTrafficShapingHandler(long j, long j2, long j3, long j4) {
        this.maxTime = DEFAULT_MAX_TIME;
        this.checkInterval = 1000;
        this.maxWriteDelay = 4000;
        this.maxWriteSize = 4194304;
        this.maxTime = ObjectUtil.checkPositive(j4, "maxTime");
        this.userDefinedWritabilityIndex = userDefinedWritabilityIndex();
        this.writeLimit = j;
        this.readLimit = j2;
        this.checkInterval = j3;
    }

    protected AbstractTrafficShapingHandler(long j, long j2, long j3) {
        this(j, j2, j3, DEFAULT_MAX_TIME);
    }

    protected AbstractTrafficShapingHandler(long j, long j2) {
        this(j, j2, 1000, DEFAULT_MAX_TIME);
    }

    protected AbstractTrafficShapingHandler() {
        this(0, 0, 1000, DEFAULT_MAX_TIME);
    }

    protected AbstractTrafficShapingHandler(long j) {
        this(0, 0, j, DEFAULT_MAX_TIME);
    }

    public void configure(long j, long j2, long j3) {
        configure(j, j2);
        configure(j3);
    }

    public void configure(long j, long j2) {
        this.writeLimit = j;
        this.readLimit = j2;
        TrafficCounter trafficCounter2 = this.trafficCounter;
        if (trafficCounter2 != null) {
            trafficCounter2.resetAccounting(TrafficCounter.milliSecondFromNano());
        }
    }

    public void configure(long j) {
        this.checkInterval = j;
        TrafficCounter trafficCounter2 = this.trafficCounter;
        if (trafficCounter2 != null) {
            trafficCounter2.configure(this.checkInterval);
        }
    }

    public long getWriteLimit() {
        return this.writeLimit;
    }

    public void setWriteLimit(long j) {
        this.writeLimit = j;
        TrafficCounter trafficCounter2 = this.trafficCounter;
        if (trafficCounter2 != null) {
            trafficCounter2.resetAccounting(TrafficCounter.milliSecondFromNano());
        }
    }

    public long getReadLimit() {
        return this.readLimit;
    }

    public void setReadLimit(long j) {
        this.readLimit = j;
        TrafficCounter trafficCounter2 = this.trafficCounter;
        if (trafficCounter2 != null) {
            trafficCounter2.resetAccounting(TrafficCounter.milliSecondFromNano());
        }
    }

    public long getCheckInterval() {
        return this.checkInterval;
    }

    public void setCheckInterval(long j) {
        this.checkInterval = j;
        TrafficCounter trafficCounter2 = this.trafficCounter;
        if (trafficCounter2 != null) {
            trafficCounter2.configure(j);
        }
    }

    public void setMaxTimeWait(long j) {
        this.maxTime = ObjectUtil.checkPositive(j, "maxTime");
    }

    public long getMaxTimeWait() {
        return this.maxTime;
    }

    public long getMaxWriteDelay() {
        return this.maxWriteDelay;
    }

    public void setMaxWriteDelay(long j) {
        this.maxWriteDelay = ObjectUtil.checkPositive(j, "maxWriteDelay");
    }

    public long getMaxWriteSize() {
        return this.maxWriteSize;
    }

    public void setMaxWriteSize(long j) {
        this.maxWriteSize = j;
    }

    static final class ReopenReadTimerTask implements Runnable {
        final ChannelHandlerContext ctx;

        ReopenReadTimerTask(ChannelHandlerContext channelHandlerContext) {
            this.ctx = channelHandlerContext;
        }

        public void run() {
            Channel channel = this.ctx.channel();
            ChannelConfig config = channel.config();
            if (config.isAutoRead() || !AbstractTrafficShapingHandler.isHandlerActive(this.ctx)) {
                if (AbstractTrafficShapingHandler.logger.isDebugEnabled()) {
                    if (!config.isAutoRead() || AbstractTrafficShapingHandler.isHandlerActive(this.ctx)) {
                        if (AbstractTrafficShapingHandler.logger.isDebugEnabled()) {
                            InternalLogger access$000 = AbstractTrafficShapingHandler.logger;
                            access$000.debug("Normal unsuspend: " + config.isAutoRead() + AbstractJsonLexerKt.COLON + AbstractTrafficShapingHandler.isHandlerActive(this.ctx));
                        }
                    } else if (AbstractTrafficShapingHandler.logger.isDebugEnabled()) {
                        InternalLogger access$0002 = AbstractTrafficShapingHandler.logger;
                        access$0002.debug("Unsuspend: " + config.isAutoRead() + AbstractJsonLexerKt.COLON + AbstractTrafficShapingHandler.isHandlerActive(this.ctx));
                    }
                }
                channel.attr(AbstractTrafficShapingHandler.READ_SUSPENDED).set(false);
                config.setAutoRead(true);
                channel.read();
            } else {
                if (AbstractTrafficShapingHandler.logger.isDebugEnabled()) {
                    InternalLogger access$0003 = AbstractTrafficShapingHandler.logger;
                    access$0003.debug("Not unsuspend: " + config.isAutoRead() + AbstractJsonLexerKt.COLON + AbstractTrafficShapingHandler.isHandlerActive(this.ctx));
                }
                channel.attr(AbstractTrafficShapingHandler.READ_SUSPENDED).set(false);
            }
            if (AbstractTrafficShapingHandler.logger.isDebugEnabled()) {
                InternalLogger access$0004 = AbstractTrafficShapingHandler.logger;
                access$0004.debug("Unsuspend final status => " + config.isAutoRead() + AbstractJsonLexerKt.COLON + AbstractTrafficShapingHandler.isHandlerActive(this.ctx));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void releaseReadSuspended(ChannelHandlerContext channelHandlerContext) {
        Channel channel = channelHandlerContext.channel();
        channel.attr(READ_SUSPENDED).set(false);
        channel.config().setAutoRead(true);
    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        long calculateSize = calculateSize(obj);
        long milliSecondFromNano = TrafficCounter.milliSecondFromNano();
        if (calculateSize > 0) {
            long j = milliSecondFromNano;
            long checkWaitReadTime = checkWaitReadTime(channelHandlerContext, this.trafficCounter.readTimeToWait(calculateSize, this.readLimit, this.maxTime, j), j);
            if (checkWaitReadTime >= 10) {
                Channel channel = channelHandlerContext.channel();
                ChannelConfig config = channel.config();
                InternalLogger internalLogger = logger;
                if (internalLogger.isDebugEnabled()) {
                    internalLogger.debug("Read suspend: " + checkWaitReadTime + AbstractJsonLexerKt.COLON + config.isAutoRead() + AbstractJsonLexerKt.COLON + isHandlerActive(channelHandlerContext));
                }
                if (config.isAutoRead() && isHandlerActive(channelHandlerContext)) {
                    config.setAutoRead(false);
                    channel.attr(READ_SUSPENDED).set(true);
                    Attribute<Runnable> attr = channel.attr(REOPEN_TASK);
                    Runnable runnable = attr.get();
                    if (runnable == null) {
                        runnable = new ReopenReadTimerTask(channelHandlerContext);
                        attr.set(runnable);
                    }
                    channelHandlerContext.executor().schedule(runnable, checkWaitReadTime, TimeUnit.MILLISECONDS);
                    if (internalLogger.isDebugEnabled()) {
                        internalLogger.debug("Suspend final status => " + config.isAutoRead() + AbstractJsonLexerKt.COLON + isHandlerActive(channelHandlerContext) + " will reopened at: " + checkWaitReadTime);
                    }
                }
            }
        }
        informReadOperation(channelHandlerContext, milliSecondFromNano);
        channelHandlerContext.fireChannelRead(obj);
    }

    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        Channel channel = channelHandlerContext.channel();
        AttributeKey<Runnable> attributeKey = REOPEN_TASK;
        if (channel.hasAttr(attributeKey)) {
            channel.attr(attributeKey).set(null);
        }
        super.handlerRemoved(channelHandlerContext);
    }

    protected static boolean isHandlerActive(ChannelHandlerContext channelHandlerContext) {
        Boolean bool = channelHandlerContext.channel().attr(READ_SUSPENDED).get();
        return bool == null || Boolean.FALSE.equals(bool);
    }

    public void read(ChannelHandlerContext channelHandlerContext) {
        if (isHandlerActive(channelHandlerContext)) {
            channelHandlerContext.read();
        }
    }

    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        long calculateSize = calculateSize(obj);
        long milliSecondFromNano = TrafficCounter.milliSecondFromNano();
        if (calculateSize > 0) {
            long writeTimeToWait = this.trafficCounter.writeTimeToWait(calculateSize, this.writeLimit, this.maxTime, milliSecondFromNano);
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
    @Deprecated
    public void submitWrite(ChannelHandlerContext channelHandlerContext, Object obj, long j, ChannelPromise channelPromise) {
        submitWrite(channelHandlerContext, obj, calculateSize(obj), j, TrafficCounter.milliSecondFromNano(), channelPromise);
    }

    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        setUserDefinedWritability(channelHandlerContext, true);
        super.channelRegistered(channelHandlerContext);
    }

    /* access modifiers changed from: package-private */
    public void setUserDefinedWritability(ChannelHandlerContext channelHandlerContext, boolean z) {
        ChannelOutboundBuffer outboundBuffer = channelHandlerContext.channel().unsafe().outboundBuffer();
        if (outboundBuffer != null) {
            outboundBuffer.setUserDefinedWritability(this.userDefinedWritabilityIndex, z);
        }
    }

    /* access modifiers changed from: package-private */
    public void checkWriteSuspend(ChannelHandlerContext channelHandlerContext, long j, long j2) {
        if (j2 > this.maxWriteSize || j > this.maxWriteDelay) {
            setUserDefinedWritability(channelHandlerContext, false);
        }
    }

    /* access modifiers changed from: package-private */
    public void releaseWriteSuspended(ChannelHandlerContext channelHandlerContext) {
        setUserDefinedWritability(channelHandlerContext, true);
    }

    public TrafficCounter trafficCounter() {
        return this.trafficCounter;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(290);
        sb.append("TrafficShaping with Write Limit: ");
        sb.append(this.writeLimit);
        sb.append(" Read Limit: ");
        sb.append(this.readLimit);
        sb.append(" CheckInterval: ");
        sb.append(this.checkInterval);
        sb.append(" maxDelay: ");
        sb.append(this.maxWriteDelay);
        sb.append(" maxSize: ");
        sb.append(this.maxWriteSize);
        sb.append(" and Counter: ");
        TrafficCounter trafficCounter2 = this.trafficCounter;
        if (trafficCounter2 != null) {
            sb.append(trafficCounter2);
        } else {
            sb.append("none");
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public long calculateSize(Object obj) {
        if (obj instanceof ByteBuf) {
            return (long) ((ByteBuf) obj).readableBytes();
        }
        if (obj instanceof ByteBufHolder) {
            return (long) ((ByteBufHolder) obj).content().readableBytes();
        }
        if (obj instanceof FileRegion) {
            return ((FileRegion) obj).count();
        }
        return -1;
    }
}
