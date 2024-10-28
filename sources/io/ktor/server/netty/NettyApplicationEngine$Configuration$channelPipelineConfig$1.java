package io.ktor.server.netty;

import io.netty.channel.ChannelPipeline;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/netty/channel/ChannelPipeline;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationEngine.kt */
final class NettyApplicationEngine$Configuration$channelPipelineConfig$1 extends Lambda implements Function1<ChannelPipeline, Unit> {
    public static final NettyApplicationEngine$Configuration$channelPipelineConfig$1 INSTANCE = new NettyApplicationEngine$Configuration$channelPipelineConfig$1();

    NettyApplicationEngine$Configuration$channelPipelineConfig$1() {
        super(1);
    }

    public final void invoke(ChannelPipeline channelPipeline) {
        Intrinsics.checkNotNullParameter(channelPipeline, "$this$null");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ChannelPipeline) obj);
        return Unit.INSTANCE;
    }
}
