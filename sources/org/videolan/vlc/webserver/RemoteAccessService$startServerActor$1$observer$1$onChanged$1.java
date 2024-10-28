package org.videolan.vlc.webserver;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessService$startServerActor$1$observer$1$onChanged$1", f = "RemoteAccessService.kt", i = {}, l = {71}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessService.kt */
final class RemoteAccessService$startServerActor$1$observer$1$onChanged$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ RemoteAccessService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessService$startServerActor$1$observer$1$onChanged$1(RemoteAccessService remoteAccessService, Continuation<? super RemoteAccessService$startServerActor$1$observer$1$onChanged$1> continuation) {
        super(2, continuation);
        this.this$0 = remoteAccessService;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessService$startServerActor$1$observer$1$onChanged$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteAccessService$startServerActor$1$observer$1$onChanged$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            RemoteAccessServer access$getServer$p = this.this$0.server;
            if (access$getServer$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("server");
                access$getServer$p = null;
            }
            this.label = 1;
            if (access$getServer$p.start(this) == coroutine_suspended) {
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
