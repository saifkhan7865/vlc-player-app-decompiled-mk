package org.videolan.vlc.gui.preferences;

import android.content.Intent;
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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.resources.VLCInstance;
import org.videolan.vlc.gui.browser.FilePickerFragmentKt;
import org.videolan.vlc.media.MediaUtils;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.preferences.PreferencesAudio$onActivityResult$1", f = "PreferencesAudio.kt", i = {}, l = {153, 154}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PreferencesAudio.kt */
final class PreferencesAudio$onActivityResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Intent $data;
    int label;
    final /* synthetic */ PreferencesAudio this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesAudio$onActivityResult$1(PreferencesAudio preferencesAudio, Intent intent, Continuation<? super PreferencesAudio$onActivityResult$1> continuation) {
        super(2, continuation);
        this.this$0 = preferencesAudio;
        this.$data = intent;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PreferencesAudio$onActivityResult$1(this.this$0, this.$data, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PreferencesAudio$onActivityResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MediaUtils mediaUtils = MediaUtils.INSTANCE;
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            Uri parse = Uri.parse(this.$data.getStringExtra(FilePickerFragmentKt.EXTRA_MRL));
            Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
            this.label = 1;
            if (mediaUtils.useAsSoundFont(requireActivity, parse, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.label = 2;
        if (VLCInstance.INSTANCE.restart(this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
