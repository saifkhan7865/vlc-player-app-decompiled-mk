package org.videolan.vlc.webserver;

import android.content.Context;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.vlc.webserver.RemoteAccessServer;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/vlc/webserver/RemoteAccessServer$PlayQueueItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$19$list$1", f = "RemoteAccessRouting.kt", i = {}, l = {1495}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$setupRouting$12$19$list$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends RemoteAccessServer.PlayQueueItem>>, Object> {
    final /* synthetic */ Context $appContext;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessRoutingKt$setupRouting$12$19$list$1(Context context, Continuation<? super RemoteAccessRoutingKt$setupRouting$12$19$list$1> continuation) {
        super(2, continuation);
        this.$appContext = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessRoutingKt$setupRouting$12$19$list$1(this.$appContext, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<RemoteAccessServer.PlayQueueItem>> continuation) {
        return ((RemoteAccessRoutingKt$setupRouting$12$19$list$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Context context = this.$appContext;
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new RemoteAccessRoutingKt$setupRouting$12$19$list$1$invokeSuspend$$inlined$getFromMl$1(context, (Continuation) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
