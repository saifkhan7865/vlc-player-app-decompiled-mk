package androidx.paging;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: SimpleChannelFlow.kt */
final class SimpleProducerScopeImpl$awaitClose$2$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ CancellableContinuation<Unit> $cont;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SimpleProducerScopeImpl$awaitClose$2$1(CancellableContinuation<? super Unit> cancellableContinuation) {
        super(1);
        this.$cont = cancellableContinuation;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        Result.Companion companion = Result.Companion;
        this.$cont.resumeWith(Result.m1774constructorimpl(Unit.INSTANCE));
    }
}
