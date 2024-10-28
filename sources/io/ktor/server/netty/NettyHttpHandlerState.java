package io.ktor.server.netty;

import io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\b\u0000\u0018\u00002\u00020\rB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\n\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/server/netty/NettyHttpHandlerState;", "", "runningLimit", "<init>", "(I)V", "Lio/netty/channel/ChannelHandlerContext;", "context", "", "onLastResponseMessage$ktor_server_netty", "(Lio/netty/channel/ChannelHandlerContext;)V", "onLastResponseMessage", "I", "ktor-server-netty", ""}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyHttpHandlerState.kt */
public final class NettyHttpHandlerState {
    public static final /* synthetic */ AtomicLongFieldUpdater activeRequests$FU$internal;
    public static final /* synthetic */ AtomicIntegerFieldUpdater isChannelReadCompleted$FU$internal;
    public static final /* synthetic */ AtomicIntegerFieldUpdater isCurrentRequestFullyRead$FU$internal;
    public static final /* synthetic */ AtomicIntegerFieldUpdater skippedRead$FU$internal;
    public volatile /* synthetic */ long activeRequests$internal = 0;
    public volatile /* synthetic */ int isChannelReadCompleted$internal = 0;
    public volatile /* synthetic */ int isCurrentRequestFullyRead$internal = 0;
    private final int runningLimit;
    public volatile /* synthetic */ int skippedRead$internal = 0;

    static {
        Class<NettyHttpHandlerState> cls = NettyHttpHandlerState.class;
        activeRequests$FU$internal = AtomicLongFieldUpdater.newUpdater(cls, "activeRequests$internal");
        isCurrentRequestFullyRead$FU$internal = AtomicIntegerFieldUpdater.newUpdater(cls, "isCurrentRequestFullyRead$internal");
        isChannelReadCompleted$FU$internal = AtomicIntegerFieldUpdater.newUpdater(cls, "isChannelReadCompleted$internal");
        skippedRead$FU$internal = AtomicIntegerFieldUpdater.newUpdater(cls, "skippedRead$internal");
    }

    public NettyHttpHandlerState(int i) {
        this.runningLimit = i;
    }

    public final void onLastResponseMessage$ktor_server_netty(ChannelHandlerContext channelHandlerContext) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        activeRequests$FU$internal.decrementAndGet(this);
        if (skippedRead$FU$internal.compareAndSet(this, 0, 1) && this.activeRequests$internal < ((long) this.runningLimit)) {
            channelHandlerContext.read();
        }
    }
}
