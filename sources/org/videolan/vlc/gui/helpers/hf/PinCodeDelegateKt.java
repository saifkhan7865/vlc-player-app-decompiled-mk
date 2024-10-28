package org.videolan.vlc.gui.helpers.hf;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelLazy;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Reflection;
import org.videolan.tools.Settings;
import org.videolan.vlc.gui.DialogActivity;
import org.videolan.vlc.gui.video.VideoPlayerActivity;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\u001a\u001c\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0003H@¢\u0006\u0002\u0010\u0006\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0007²\u0006\n\u0010\b\u001a\u00020\tX\u0002"}, d2 = {"UNLOCK", "", "checkPIN", "", "Landroidx/fragment/app/FragmentActivity;", "unlock", "(Landroidx/fragment/app/FragmentActivity;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vlc-android_release", "model", "Lorg/videolan/vlc/gui/helpers/hf/PermissionViewmodel;"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: PinCodeDelegate.kt */
public final class PinCodeDelegateKt {
    private static final String UNLOCK = "unlock";

    public static /* synthetic */ Object checkPIN$default(FragmentActivity fragmentActivity, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return checkPIN(fragmentActivity, z, continuation);
    }

    public static final Object checkPIN(FragmentActivity fragmentActivity, boolean z, Continuation<? super Boolean> continuation) {
        if (fragmentActivity instanceof VideoPlayerActivity) {
            ((VideoPlayerActivity) fragmentActivity).setWaitingForPin(true);
        }
        if (!Settings.INSTANCE.getSafeMode()) {
            return Boxing.boxBoolean(true);
        }
        ComponentActivity componentActivity = fragmentActivity;
        Lazy viewModelLazy = new ViewModelLazy(Reflection.getOrCreateKotlinClass(PermissionViewmodel.class), new PinCodeDelegateKt$checkPIN$$inlined$viewModels$default$2(componentActivity), new PinCodeDelegateKt$checkPIN$$inlined$viewModels$default$1(componentActivity), new PinCodeDelegateKt$checkPIN$$inlined$viewModels$default$3((Function0) null, componentActivity));
        PinCodeDelegate pinCodeDelegate = new PinCodeDelegate();
        Bundle bundle = new Bundle();
        bundle.putBoolean(UNLOCK, z);
        pinCodeDelegate.setArguments(bundle);
        checkPIN$lambda$0(viewModelLazy).setupDeferred();
        fragmentActivity.getSupportFragmentManager().beginTransaction().add((Fragment) pinCodeDelegate, PinCodeDelegate.TAG).commitAllowingStateLoss();
        if (fragmentActivity instanceof DialogActivity) {
            ((DialogActivity) fragmentActivity).preventFinish();
        }
        return checkPIN$lambda$0(viewModelLazy).getDeferredGrant().await(continuation);
    }

    private static final PermissionViewmodel checkPIN$lambda$0(Lazy<PermissionViewmodel> lazy) {
        return lazy.getValue();
    }
}
