package org.videolan.vlc.gui.video.benchmark;

import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.resources.VLCInstance;
import org.videolan.tools.SettingsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.benchmark.BenchActivity$finish$1", f = "BenchActivity.kt", i = {}, l = {564}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BenchActivity.kt */
final class BenchActivity$finish$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SharedPreferences $sharedPref;
    int label;
    final /* synthetic */ BenchActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BenchActivity$finish$1(SharedPreferences sharedPreferences, BenchActivity benchActivity, Continuation<? super BenchActivity$finish$1> continuation) {
        super(2, continuation);
        this.$sharedPref = sharedPreferences;
        this.this$0 = benchActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BenchActivity$finish$1(this.$sharedPref, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BenchActivity$finish$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SharedPreferences.Editor edit = this.$sharedPref.edit();
            BenchActivity benchActivity = this.this$0;
            edit.putString("opengl", benchActivity.oldOpenglValue);
            edit.putBoolean(SettingsKt.PLAYBACK_HISTORY, benchActivity.oldHistoryBoolean);
            this.label = 1;
            if (VLCInstance.INSTANCE.restart(this) == coroutine_suspended) {
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
