package io.ktor.server.netty;

import io.netty.channel.EventLoopGroup;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/netty/channel/EventLoopGroup;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationEngine.kt */
final class NettyApplicationEngine$workerEventGroup$2 extends Lambda implements Function0<EventLoopGroup> {
    final /* synthetic */ NettyApplicationEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NettyApplicationEngine$workerEventGroup$2(NettyApplicationEngine nettyApplicationEngine) {
        super(0);
        this.this$0 = nettyApplicationEngine;
    }

    public final EventLoopGroup invoke() {
        EventLoopGroupProxy eventLoopGroupProxy;
        EventLoopGroup childGroup = this.this$0.getCustomBootstrap().config().childGroup();
        if (childGroup != null) {
            return childGroup;
        }
        if (this.this$0.configuration.getShareWorkGroup()) {
            eventLoopGroupProxy = EventLoopGroupProxy.Companion.create(this.this$0.configuration.getWorkerGroupSize() + this.this$0.configuration.getCallGroupSize());
        } else {
            eventLoopGroupProxy = EventLoopGroupProxy.Companion.create(this.this$0.configuration.getWorkerGroupSize());
        }
        return eventLoopGroupProxy;
    }
}
