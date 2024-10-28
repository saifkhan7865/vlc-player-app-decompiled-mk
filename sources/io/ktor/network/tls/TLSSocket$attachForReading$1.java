package io.ktor.network.tls;

import io.ktor.utils.io.WriterScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/WriterScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.tls.TLSSocket$attachForReading$1", f = "TLSClientSessionJvm.kt", i = {}, l = {43}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: TLSClientSessionJvm.kt */
final class TLSSocket$attachForReading$1 extends SuspendLambda implements Function2<WriterScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TLSSocket this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TLSSocket$attachForReading$1(TLSSocket tLSSocket, Continuation<? super TLSSocket$attachForReading$1> continuation) {
        super(2, continuation);
        this.this$0 = tLSSocket;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TLSSocket$attachForReading$1 tLSSocket$attachForReading$1 = new TLSSocket$attachForReading$1(this.this$0, continuation);
        tLSSocket$attachForReading$1.L$0 = obj;
        return tLSSocket$attachForReading$1;
    }

    public final Object invoke(WriterScope writerScope, Continuation<? super Unit> continuation) {
        return ((TLSSocket$attachForReading$1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.this$0.appDataInputLoop(((WriterScope) this.L$0).getChannel(), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
