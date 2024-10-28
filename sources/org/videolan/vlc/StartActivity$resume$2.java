package org.videolan.vlc;

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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.StartActivity$resume$2", f = "StartActivity.kt", i = {}, l = {308}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: StartActivity.kt */
final class StartActivity$resume$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $id;
    final /* synthetic */ String $type;
    int label;
    final /* synthetic */ StartActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StartActivity$resume$2(StartActivity startActivity, String str, String str2, Continuation<? super StartActivity$resume$2> continuation) {
        super(2, continuation);
        this.this$0 = startActivity;
        this.$type = str;
        this.$id = str2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StartActivity$resume$2(this.this$0, this.$type, this.$id, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StartActivity$resume$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            StartActivity startActivity = this.this$0;
            String str = this.$type;
            String str2 = this.$id;
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new StartActivity$resume$2$invokeSuspend$$inlined$getFromMl$1(startActivity, (Continuation) null, str, str2, startActivity), this) == coroutine_suspended) {
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
