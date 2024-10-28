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
import org.videolan.resources.Constants;
import org.videolan.tools.SettingsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.benchmark.BenchActivity$onServiceChanged$1", f = "BenchActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BenchActivity.kt */
final class BenchActivity$onServiceChanged$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SharedPreferences $sharedPref;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BenchActivity$onServiceChanged$1(SharedPreferences sharedPreferences, Continuation<? super BenchActivity$onServiceChanged$1> continuation) {
        super(2, continuation);
        this.$sharedPref = sharedPreferences;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BenchActivity$onServiceChanged$1(this.$sharedPref, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BenchActivity$onServiceChanged$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SharedPreferences.Editor edit = this.$sharedPref.edit();
            edit.putString("opengl", Constants.GROUP_VIDEOS_FOLDER);
            edit.putBoolean(SettingsKt.PLAYBACK_HISTORY, false);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
