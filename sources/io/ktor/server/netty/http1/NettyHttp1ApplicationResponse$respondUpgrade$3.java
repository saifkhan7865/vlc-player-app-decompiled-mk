package io.ktor.server.netty.http1;

import io.ktor.server.netty.cio.RequestBodyHandler;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteReadChannelKt;
import io.ktor.utils.io.ByteWriteChannelKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyHttp1ApplicationResponse.kt */
final class NettyHttp1ApplicationResponse$respondUpgrade$3 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ RequestBodyHandler $bodyHandler;
    final /* synthetic */ ByteReadChannel $upgradedReadChannel;
    final /* synthetic */ ByteChannel $upgradedWriteChannel;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NettyHttp1ApplicationResponse$respondUpgrade$3(ByteChannel byteChannel, RequestBodyHandler requestBodyHandler, ByteReadChannel byteReadChannel) {
        super(1);
        this.$upgradedWriteChannel = byteChannel;
        this.$bodyHandler = requestBodyHandler;
        this.$upgradedReadChannel = byteReadChannel;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        ByteWriteChannelKt.close(this.$upgradedWriteChannel);
        this.$bodyHandler.close();
        ByteReadChannelKt.cancel(this.$upgradedReadChannel);
    }
}
