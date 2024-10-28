package org.videolan.vlc.gui.helpers.hf;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelLazy;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import videolan.org.commontools.LiveEvent;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0006\u0010\u0007\u001a\u00020\u0004¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/helpers/hf/NotificationDelegate;", "Lorg/videolan/vlc/gui/helpers/hf/BaseHeadlessFragment;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "requestPermission", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NotificationDelegate.kt */
public final class NotificationDelegate extends BaseHeadlessFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VLC/NotificationDelegate";
    /* access modifiers changed from: private */
    public static final LiveEvent<Boolean> notificationAccessGranted = new LiveEvent<>();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            requestPermission();
        }
    }

    public final void requestPermission() {
        ActivityResultLauncher registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new NotificationDelegate$$ExternalSyntheticLambda0(this));
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        if (ContextCompat.checkSelfPermission(requireActivity(), "android.permission.POST_NOTIFICATIONS") == 0) {
            return;
        }
        if (shouldShowRequestPermissionRationale("android.permission.POST_NOTIFICATIONS")) {
            registerForActivityResult.launch("android.permission.POST_NOTIFICATIONS");
        } else {
            registerForActivityResult.launch("android.permission.POST_NOTIFICATIONS");
        }
    }

    /* access modifiers changed from: private */
    public static final void requestPermission$lambda$0(NotificationDelegate notificationDelegate, boolean z) {
        Intrinsics.checkNotNullParameter(notificationDelegate, "this$0");
        notificationDelegate.getModel().getDeferredGrant().complete(Boolean.valueOf(z));
        notificationDelegate.exit();
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u00020\u0007*\u00020\tH@¢\u0006\u0002\u0010\nR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b²\u0006\n\u0010\f\u001a\u00020\rX\u0002"}, d2 = {"Lorg/videolan/vlc/gui/helpers/hf/NotificationDelegate$Companion;", "", "()V", "TAG", "", "notificationAccessGranted", "Lvideolan/org/commontools/LiveEvent;", "", "getNotificationPermission", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vlc-android_release", "model", "Lorg/videolan/vlc/gui/helpers/hf/PermissionViewmodel;"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: NotificationDelegate.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Object getNotificationPermission(FragmentActivity fragmentActivity, Continuation<? super Boolean> continuation) {
            if (fragmentActivity.isFinishing()) {
                return Boxing.boxBoolean(false);
            }
            ComponentActivity componentActivity = fragmentActivity;
            Unit unit = null;
            Lazy viewModelLazy = new ViewModelLazy(Reflection.getOrCreateKotlinClass(PermissionViewmodel.class), new NotificationDelegate$Companion$getNotificationPermission$$inlined$viewModels$default$2(componentActivity), new NotificationDelegate$Companion$getNotificationPermission$$inlined$viewModels$default$1(componentActivity), new NotificationDelegate$Companion$getNotificationPermission$$inlined$viewModels$default$3((Function0) null, componentActivity));
            if (getNotificationPermission$lambda$0(viewModelLazy).isCompleted() && Intrinsics.areEqual(NotificationDelegate.notificationAccessGranted.getValue(), (Object) Boxing.boxBoolean(true))) {
                return getNotificationPermission$lambda$0(viewModelLazy).getDeferredGrant().getCompleted();
            }
            if (getNotificationPermission$lambda$0(viewModelLazy).getPermissionPending()) {
                Fragment findFragmentByTag = fragmentActivity.getSupportFragmentManager().findFragmentByTag(NotificationDelegate.TAG);
                NotificationDelegate notificationDelegate = findFragmentByTag instanceof NotificationDelegate ? (NotificationDelegate) findFragmentByTag : null;
                if (notificationDelegate != null) {
                    notificationDelegate.requestPermission();
                    unit = Unit.INSTANCE;
                }
                if (unit == null) {
                    return Boxing.boxBoolean(false);
                }
            } else {
                getNotificationPermission$lambda$0(viewModelLazy).setupDeferred();
                fragmentActivity.getSupportFragmentManager().beginTransaction().add((Fragment) new NotificationDelegate(), NotificationDelegate.TAG).commitAllowingStateLoss();
            }
            return getNotificationPermission$lambda$0(viewModelLazy).getDeferredGrant().await(continuation);
        }

        private static final PermissionViewmodel getNotificationPermission$lambda$0(Lazy<PermissionViewmodel> lazy) {
            return lazy.getValue();
        }
    }
}
