package io.ktor.network.sockets;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u000e\b\u0001\u0010\u0004 \u0001*\u00020\u0005*\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "J", "Lkotlinx/coroutines/Job;", "S", "Ljava/nio/channels/ByteChannel;", "Ljava/nio/channels/SelectableChannel;", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NIOSocketImpl.kt */
final class NIOSocketImpl$attachFor$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ NIOSocketImpl<S> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NIOSocketImpl$attachFor$1(NIOSocketImpl<? extends S> nIOSocketImpl) {
        super(1);
        this.this$0 = nIOSocketImpl;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        this.this$0.checkChannels();
    }
}
