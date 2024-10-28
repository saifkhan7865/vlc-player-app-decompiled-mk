package org.videolan.vlc.gui;

import android.content.Intent;
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
@DebugMetadata(c = "org.videolan.vlc.gui.SendCrashActivity$onSaved$1", f = "SendCrashActivity.kt", i = {}, l = {87}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SendCrashActivity.kt */
final class SendCrashActivity$onSaved$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $path;
    int label;
    final /* synthetic */ SendCrashActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SendCrashActivity$onSaved$1(SendCrashActivity sendCrashActivity, String str, Continuation<? super SendCrashActivity$onSaved$1> continuation) {
        super(2, continuation);
        this.this$0 = sendCrashActivity;
        this.$path = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SendCrashActivity$onSaved$1(this.this$0, this.$path, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SendCrashActivity$onSaved$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new SendCrashActivity$onSaved$1$emailIntent$1(this.this$0, this.$path, (Continuation<? super SendCrashActivity$onSaved$1$emailIntent$1>) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Intent intent = (Intent) obj;
        if (intent != null) {
            this.this$0.startActivity(intent);
        }
        this.this$0.finish();
        return Unit.INSTANCE;
    }
}
