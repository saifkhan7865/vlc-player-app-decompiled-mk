package org.videolan.vlc.gui.onboarding;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelLazy;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.resources.Constants;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.CoroutineContextProvider;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.MediaParsingService;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.MainActivity;
import org.videolan.vlc.util.Permissions;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\rH\u0002J\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J\u0012\u0010\u0012\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\rH\u0014J\b\u0010\u0016\u001a\u00020\rH\u0016J\b\u0010\u0017\u001a\u00020\rH\u0016J\u0018\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u001c"}, d2 = {"Lorg/videolan/vlc/gui/onboarding/OnboardingActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lorg/videolan/vlc/gui/onboarding/OnboardingFragmentListener;", "()V", "nextButton", "Landroid/widget/Button;", "viewModel", "Lorg/videolan/vlc/gui/onboarding/OnboardingViewModel;", "getViewModel", "()Lorg/videolan/vlc/gui/onboarding/OnboardingViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "askNotificationPermission", "", "askPermission", "manageNextVisibility", "visible", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDone", "onNext", "showFragment", "fragmentName", "Lorg/videolan/vlc/gui/onboarding/FragmentName;", "backward", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OnboardingActivity.kt */
public final class OnboardingActivity extends AppCompatActivity implements OnboardingFragmentListener {
    private Button nextButton;
    private final Lazy viewModel$delegate;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: OnboardingActivity.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|1|2|3|4|5|6|7|8|9|10|11|12|13|15) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                org.videolan.vlc.gui.onboarding.FragmentName[] r0 = org.videolan.vlc.gui.onboarding.FragmentName.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.gui.onboarding.FragmentName r1 = org.videolan.vlc.gui.onboarding.FragmentName.WELCOME     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.gui.onboarding.FragmentName r1 = org.videolan.vlc.gui.onboarding.FragmentName.ASK_PERMISSION     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.gui.onboarding.FragmentName r1 = org.videolan.vlc.gui.onboarding.FragmentName.SCAN     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                org.videolan.vlc.gui.onboarding.FragmentName r1 = org.videolan.vlc.gui.onboarding.FragmentName.NO_PERMISSION     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                org.videolan.vlc.gui.onboarding.FragmentName r1 = org.videolan.vlc.gui.onboarding.FragmentName.NOTIFICATION_PERMISSION     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                org.videolan.vlc.gui.onboarding.FragmentName r1 = org.videolan.vlc.gui.onboarding.FragmentName.THEME     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.onboarding.OnboardingActivity.WhenMappings.<clinit>():void");
        }
    }

    public OnboardingActivity() {
        ComponentActivity componentActivity = this;
        this.viewModel$delegate = new ViewModelLazy(Reflection.getOrCreateKotlinClass(OnboardingViewModel.class), new OnboardingActivity$special$$inlined$viewModels$default$2(componentActivity), new OnboardingActivity$special$$inlined$viewModels$default$1(componentActivity), new OnboardingActivity$special$$inlined$viewModels$default$3((Function0) null, componentActivity));
    }

    /* access modifiers changed from: private */
    public final OnboardingViewModel getViewModel() {
        return (OnboardingViewModel) this.viewModel$delegate.getValue();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_onboarding);
        showFragment$default(this, getViewModel().getCurrentFragment(), false, 2, (Object) null);
    }

    public static /* synthetic */ void showFragment$default(OnboardingActivity onboardingActivity, FragmentName fragmentName, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        onboardingActivity.showFragment(fragmentName, z);
    }

    public final void showFragment(FragmentName fragmentName, boolean z) {
        OnboardingFragment onboardingFragment;
        Intrinsics.checkNotNullParameter(fragmentName, "fragmentName");
        Fragment fragment = getSupportFragmentManager().getFragment(new Bundle(), fragmentName.name());
        if (fragment == null) {
            switch (WhenMappings.$EnumSwitchMapping$0[fragmentName.ordinal()]) {
                case 1:
                    onboardingFragment = OnboardingWelcomeFragment.Companion.newInstance();
                    break;
                case 2:
                    onboardingFragment = OnboardingPermissionFragment.Companion.newInstance();
                    break;
                case 3:
                    onboardingFragment = OnboardingScanningFragment.Companion.newInstance();
                    break;
                case 4:
                    onboardingFragment = OnboardingNoPermissionFragment.Companion.newInstance();
                    break;
                case 5:
                    onboardingFragment = OnboardingNotificationPermissionFragment.Companion.newInstance();
                    break;
                case 6:
                    onboardingFragment = OnboardingThemeFragment.Companion.newInstance();
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            fragment = onboardingFragment;
        }
        Intrinsics.checkNotNull(fragment);
        ((OnboardingFragment) fragment).setOnboardingFragmentListener(this);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "beginTransaction()");
        if (!z) {
            beginTransaction.setCustomAnimations(R.anim.anim_enter_right, R.anim.anim_leave_left, 17432576, 17432577);
        } else {
            beginTransaction.setCustomAnimations(R.anim.anim_enter_left, R.anim.anim_leave_right, 17432576, 17432577);
        }
        beginTransaction.replace(R.id.fragment_onboarding_placeholder, fragment, fragmentName.name());
        beginTransaction.commit();
        getViewModel().setCurrentFragment(fragmentName);
        findViewById(R.id.skip_button).setOnClickListener(new OnboardingActivity$$ExternalSyntheticLambda0(this));
        View findViewById = findViewById(R.id.next_button);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        Button button = (Button) findViewById;
        this.nextButton = button;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextButton");
            button = null;
        }
        button.setOnClickListener(new OnboardingActivity$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: private */
    public static final void showFragment$lambda$1(OnboardingActivity onboardingActivity, View view) {
        Intrinsics.checkNotNullParameter(onboardingActivity, "this$0");
        onboardingActivity.onDone();
    }

    /* access modifiers changed from: private */
    public static final void showFragment$lambda$2(OnboardingActivity onboardingActivity, View view) {
        Intrinsics.checkNotNullParameter(onboardingActivity, "this$0");
        onboardingActivity.onNext();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Dialog sAlertDialog = Permissions.INSTANCE.getSAlertDialog();
        if (sAlertDialog != null) {
            sAlertDialog.dismiss();
        }
    }

    public void onDone() {
        setResult(3);
        SharedPreferences.Editor edit = ((SharedPreferences) Settings.INSTANCE.getInstance(this)).edit();
        edit.putInt(Constants.PREF_FIRST_RUN, 3050720);
        edit.putBoolean(OnboardingActivityKt.ONBOARDING_DONE_KEY, true);
        edit.putInt(SettingsKt.KEY_MEDIALIBRARY_SCAN, getViewModel().getScanStorages() ^ true ? 1 : 0);
        edit.putInt("fragment_id", getViewModel().getScanStorages() ? R.id.nav_video : R.id.nav_directories);
        edit.putString(SettingsKt.KEY_APP_THEME, String.valueOf(getViewModel().getTheme()));
        edit.apply();
        if (!getViewModel().getScanStorages()) {
            MediaParsingService.Companion.getPreselectedStorages().clear();
        }
        Context context = this;
        ExtensionsKt.startMedialibrary$default(context, true, true, getViewModel().getScanStorages(), false, (CoroutineContextProvider) null, 24, (Object) null);
        Intent putExtra = new Intent(context, MainActivity.class).putExtra(Constants.EXTRA_FIRST_RUN, true).putExtra(Constants.EXTRA_UPGRADE, true);
        Intrinsics.checkNotNullExpressionValue(putExtra, "putExtra(...)");
        startActivity(putExtra);
        finish();
    }

    private final void askPermission() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new OnboardingActivity$askPermission$1(this, (Continuation<? super OnboardingActivity$askPermission$1>) null), 3, (Object) null);
    }

    private final void askNotificationPermission() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new OnboardingActivity$askNotificationPermission$1(this, (Continuation<? super OnboardingActivity$askNotificationPermission$1>) null), 3, (Object) null);
    }

    public void onNext() {
        FragmentName fragmentName;
        int i = WhenMappings.$EnumSwitchMapping$0[getViewModel().getCurrentFragment().ordinal()];
        Button button = null;
        if (i == 1) {
            showFragment$default(this, Permissions.INSTANCE.canReadStorage(this) ? FragmentName.SCAN : FragmentName.ASK_PERMISSION, false, 2, (Object) null);
        } else if (i != 2) {
            if (i == 3) {
                if (Build.VERSION.SDK_INT > 31) {
                    Permissions permissions = Permissions.INSTANCE;
                    Context applicationContext = getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                    if (!permissions.canSendNotifications(applicationContext)) {
                        fragmentName = FragmentName.NOTIFICATION_PERMISSION;
                        showFragment$default(this, fragmentName, false, 2, (Object) null);
                    }
                }
                fragmentName = FragmentName.THEME;
                showFragment$default(this, fragmentName, false, 2, (Object) null);
            } else if (i == 4) {
                Permissions permissions2 = Permissions.INSTANCE;
                Context applicationContext2 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
                showFragment$default(this, permissions2.canReadStorage(applicationContext2) ? FragmentName.SCAN : FragmentName.THEME, false, 2, (Object) null);
            } else if (i != 5) {
                onDone();
            } else {
                Permissions permissions3 = Permissions.INSTANCE;
                Context applicationContext3 = getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext3, "getApplicationContext(...)");
                if (permissions3.canSendNotifications(applicationContext3) || getViewModel().getNotificationPermissionAlreadyAsked()) {
                    showFragment$default(this, FragmentName.THEME, false, 2, (Object) null);
                } else {
                    askNotificationPermission();
                }
            }
        } else if (getViewModel().getPermissionType() == PermissionType.NONE || getViewModel().getPermissionAlreadyAsked()) {
            Permissions permissions4 = Permissions.INSTANCE;
            Context applicationContext4 = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext4, "getApplicationContext(...)");
            showFragment$default(this, permissions4.canReadStorage(applicationContext4) ? FragmentName.SCAN : FragmentName.NO_PERMISSION, false, 2, (Object) null);
        } else {
            askPermission();
        }
        if (getViewModel().getCurrentFragment() == FragmentName.THEME) {
            Button button2 = this.nextButton;
            if (button2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("nextButton");
            } else {
                button = button2;
            }
            button.setText(getString(R.string.done));
        }
    }

    public final void manageNextVisibility(boolean z) {
        Button button = this.nextButton;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextButton");
            button = null;
        }
        button.setVisibility(z ? 0 : 8);
    }
}
