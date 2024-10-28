package io.ktor.server.netty.cio;

import io.ktor.server.netty.NettyApplicationCall;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyHttpResponsePipeline.kt */
final class NettyHttpResponsePipeline$processElement$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ NettyApplicationCall $call;
    final /* synthetic */ NettyHttpResponsePipeline this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NettyHttpResponsePipeline$processElement$1(NettyHttpResponsePipeline nettyHttpResponsePipeline, NettyApplicationCall nettyApplicationCall) {
        super(0);
        this.this$0 = nettyHttpResponsePipeline;
        this.$call = nettyApplicationCall;
    }

    public final void invoke() {
        try {
            this.this$0.handleRequestMessage(this.$call);
        } catch (Throwable th) {
            Job.DefaultImpls.cancel$default(this.$call.getResponseWriteJob(), (CancellationException) null, 1, (Object) null);
            throw th;
        }
        Job.DefaultImpls.cancel$default(this.$call.getResponseWriteJob(), (CancellationException) null, 1, (Object) null);
    }
}
