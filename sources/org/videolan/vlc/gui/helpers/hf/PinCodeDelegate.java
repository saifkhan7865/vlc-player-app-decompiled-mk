package org.videolan.vlc.gui.helpers.hf;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.gui.DialogActivity;
import org.videolan.vlc.gui.PinCodeActivity;
import org.videolan.vlc.gui.PinCodeReason;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0017R(\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/videolan/vlc/gui/helpers/hf/PinCodeDelegate;", "Lorg/videolan/vlc/gui/helpers/hf/BaseHeadlessFragment;", "()V", "pinCodeResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "getPinCodeResult", "()Landroidx/activity/result/ActivityResultLauncher;", "setPinCodeResult", "(Landroidx/activity/result/ActivityResultLauncher;)V", "unlock", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PinCodeDelegate.kt */
public final class PinCodeDelegate extends BaseHeadlessFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VLC/PinCode";
    /* access modifiers changed from: private */
    public static final MutableLiveData<Boolean> pinUnlocked = new MutableLiveData<>(false);
    private ActivityResultLauncher<Intent> pinCodeResult;
    private boolean unlock;

    public PinCodeDelegate() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new PinCodeDelegate$$ExternalSyntheticLambda0(this));
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.pinCodeResult = registerForActivityResult;
    }

    public final ActivityResultLauncher<Intent> getPinCodeResult() {
        return this.pinCodeResult;
    }

    public final void setPinCodeResult(ActivityResultLauncher<Intent> activityResultLauncher) {
        Intrinsics.checkNotNullParameter(activityResultLauncher, "<set-?>");
        this.pinCodeResult = activityResultLauncher;
    }

    /* access modifiers changed from: private */
    public static final void pinCodeResult$lambda$0(PinCodeDelegate pinCodeDelegate, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(pinCodeDelegate, "this$0");
        pinCodeDelegate.getModel().complete(activityResult.getResultCode() == -1);
        if (activityResult.getResultCode() == -1 && pinCodeDelegate.unlock) {
            pinUnlocked.postValue(true);
        }
        pinCodeDelegate.exit();
        FragmentActivity activity = pinCodeDelegate.getActivity();
        DialogActivity dialogActivity = activity instanceof DialogActivity ? (DialogActivity) activity : null;
        if (dialogActivity != null) {
            dialogActivity.finish();
        }
    }

    public void onCreate(Bundle bundle) {
        Bundle arguments = getArguments();
        boolean z = false;
        if (arguments != null && arguments.getBoolean("unlock", false)) {
            z = true;
        }
        this.unlock = z;
        super.onCreate(bundle);
        PinCodeActivity.Companion companion = PinCodeActivity.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        this.pinCodeResult.launch(companion.getIntent(requireActivity, PinCodeReason.UNLOCK));
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001f\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/videolan/vlc/gui/helpers/hf/PinCodeDelegate$Companion;", "", "()V", "TAG", "", "pinUnlocked", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getPinUnlocked", "()Landroidx/lifecycle/MutableLiveData;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PinCodeDelegate.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MutableLiveData<Boolean> getPinUnlocked() {
            return PinCodeDelegate.pinUnlocked;
        }
    }
}
