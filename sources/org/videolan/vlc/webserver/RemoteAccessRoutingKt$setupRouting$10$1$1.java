package org.videolan.vlc.webserver;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0012\u0010\u0000\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$10$1$1", f = "RemoteAccessRouting.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$setupRouting$10$1$1 extends SuspendLambda implements Function1<Continuation<? super byte[]>, Object> {
    final /* synthetic */ byte[] $it;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessRoutingKt$setupRouting$10$1$1(byte[] bArr, Continuation<? super RemoteAccessRoutingKt$setupRouting$10$1$1> continuation) {
        super(1, continuation);
        this.$it = bArr;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new RemoteAccessRoutingKt$setupRouting$10$1$1(this.$it, continuation);
    }

    public final Object invoke(Continuation<? super byte[]> continuation) {
        return ((RemoteAccessRoutingKt$setupRouting$10$1$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return this.$it;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
