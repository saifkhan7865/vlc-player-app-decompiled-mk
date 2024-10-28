package org.videolan.vlc.gui.helpers.hf;

import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.hf.WriteExternalDelegate$Companion$askForExtWrite$1", f = "WriteExternalDelegate.kt", i = {}, l = {101}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: WriteExternalDelegate.kt */
final class WriteExternalDelegate$Companion$askForExtWrite$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ Runnable $cb;
    final /* synthetic */ Uri $uri;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WriteExternalDelegate$Companion$askForExtWrite$1(FragmentActivity fragmentActivity, Uri uri, Runnable runnable, Continuation<? super WriteExternalDelegate$Companion$askForExtWrite$1> continuation) {
        super(2, continuation);
        this.$activity = fragmentActivity;
        this.$uri = uri;
        this.$cb = runnable;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new WriteExternalDelegate$Companion$askForExtWrite$1(this.$activity, this.$uri, this.$cb, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WriteExternalDelegate$Companion$askForExtWrite$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Runnable runnable;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = WriteExternalDelegateKt.getExtWritePermission(this.$activity, this.$uri, (Continuation<? super Boolean>) this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (((Boolean) obj).booleanValue() && (runnable = this.$cb) != null) {
            runnable.run();
        }
        return Unit.INSTANCE;
    }
}
