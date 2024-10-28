package org.videolan.vlc.webserver.gui.remoteaccess.onboarding;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelLazy;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.webserver.viewmodels.RemoteAccessOnboardingViewModel;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0012\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u000eH\u0016J\b\u0010\u0015\u001a\u00020\u000eH\u0016J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u001a"}, d2 = {"Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/RemoteAccessOnboardingActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/OnboardingFragmentListener;", "()V", "nextButton", "Landroid/widget/Button;", "skipButton", "viewModel", "Lorg/videolan/vlc/webserver/viewmodels/RemoteAccessOnboardingViewModel;", "getViewModel", "()Lorg/videolan/vlc/webserver/viewmodels/RemoteAccessOnboardingViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "manageNextVisibility", "", "visible", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDone", "onNext", "showFragment", "fragmentName", "Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/FragmentName;", "backward", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessOnboardingActivity.kt */
public final class RemoteAccessOnboardingActivity extends AppCompatActivity implements OnboardingFragmentListener {
    private Button nextButton;
    private Button skipButton;
    private final Lazy viewModel$delegate;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RemoteAccessOnboardingActivity.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|8|9|10|11|13) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                org.videolan.vlc.webserver.gui.remoteaccess.onboarding.FragmentName[] r0 = org.videolan.vlc.webserver.gui.remoteaccess.onboarding.FragmentName.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.webserver.gui.remoteaccess.onboarding.FragmentName r1 = org.videolan.vlc.webserver.gui.remoteaccess.onboarding.FragmentName.WELCOME     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.webserver.gui.remoteaccess.onboarding.FragmentName r1 = org.videolan.vlc.webserver.gui.remoteaccess.onboarding.FragmentName.HOW     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.webserver.gui.remoteaccess.onboarding.FragmentName r1 = org.videolan.vlc.webserver.gui.remoteaccess.onboarding.FragmentName.SSL     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                org.videolan.vlc.webserver.gui.remoteaccess.onboarding.FragmentName r1 = org.videolan.vlc.webserver.gui.remoteaccess.onboarding.FragmentName.OTP     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                org.videolan.vlc.webserver.gui.remoteaccess.onboarding.FragmentName r1 = org.videolan.vlc.webserver.gui.remoteaccess.onboarding.FragmentName.CONTENT     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingActivity.WhenMappings.<clinit>():void");
        }
    }

    public RemoteAccessOnboardingActivity() {
        ComponentActivity componentActivity = this;
        this.viewModel$delegate = new ViewModelLazy(Reflection.getOrCreateKotlinClass(RemoteAccessOnboardingViewModel.class), new RemoteAccessOnboardingActivity$special$$inlined$viewModels$default$2(componentActivity), new RemoteAccessOnboardingActivity$special$$inlined$viewModels$default$1(componentActivity), new RemoteAccessOnboardingActivity$special$$inlined$viewModels$default$3((Function0) null, componentActivity));
    }

    private final RemoteAccessOnboardingViewModel getViewModel() {
        return (RemoteAccessOnboardingViewModel) this.viewModel$delegate.getValue();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_onboarding);
        showFragment$default(this, getViewModel().getCurrentFragment(), false, 2, (Object) null);
    }

    public static /* synthetic */ void showFragment$default(RemoteAccessOnboardingActivity remoteAccessOnboardingActivity, FragmentName fragmentName, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        remoteAccessOnboardingActivity.showFragment(fragmentName, z);
    }

    public final void showFragment(FragmentName fragmentName, boolean z) {
        RemoteAccessOnboardingFragment remoteAccessOnboardingFragment;
        Intrinsics.checkNotNullParameter(fragmentName, "fragmentName");
        Fragment fragment = getSupportFragmentManager().getFragment(new Bundle(), fragmentName.name());
        if (fragment == null) {
            int i = WhenMappings.$EnumSwitchMapping$0[fragmentName.ordinal()];
            if (i == 1) {
                remoteAccessOnboardingFragment = RemoteAccessOnboardingWelcomeFragment.Companion.newInstance();
            } else if (i == 2) {
                remoteAccessOnboardingFragment = RemoteAccessOnboardingHowFragment.Companion.newInstance();
            } else if (i == 3) {
                remoteAccessOnboardingFragment = RemoteAccessOnboardingSslFragment.Companion.newInstance();
            } else if (i == 4) {
                remoteAccessOnboardingFragment = RemoteAccessOnboardingOtpFragment.Companion.newInstance();
            } else if (i == 5) {
                remoteAccessOnboardingFragment = RemoteAccessOnboardingContentFragment.Companion.newInstance();
            } else {
                throw new NoWhenBranchMatchedException();
            }
            fragment = remoteAccessOnboardingFragment;
        }
        Intrinsics.checkNotNull(fragment);
        ((RemoteAccessOnboardingFragment) fragment).setOnboardingFragmentListener(this);
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
        View findViewById = findViewById(R.id.skip_button);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        Button button = (Button) findViewById;
        this.skipButton = button;
        Button button2 = null;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skipButton");
            button = null;
        }
        button.setOnClickListener(new RemoteAccessOnboardingActivity$$ExternalSyntheticLambda0(this));
        View findViewById2 = findViewById(R.id.next_button);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        Button button3 = (Button) findViewById2;
        this.nextButton = button3;
        if (button3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextButton");
        } else {
            button2 = button3;
        }
        button2.setOnClickListener(new RemoteAccessOnboardingActivity$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: private */
    public static final void showFragment$lambda$1(RemoteAccessOnboardingActivity remoteAccessOnboardingActivity, View view) {
        Intrinsics.checkNotNullParameter(remoteAccessOnboardingActivity, "this$0");
        remoteAccessOnboardingActivity.onDone();
    }

    /* access modifiers changed from: private */
    public static final void showFragment$lambda$2(RemoteAccessOnboardingActivity remoteAccessOnboardingActivity, View view) {
        Intrinsics.checkNotNullParameter(remoteAccessOnboardingActivity, "this$0");
        remoteAccessOnboardingActivity.onNext();
    }

    public void onDone() {
        finish();
    }

    public void onNext() {
        int i = WhenMappings.$EnumSwitchMapping$0[getViewModel().getCurrentFragment().ordinal()];
        Button button = null;
        if (i == 1) {
            showFragment$default(this, FragmentName.HOW, false, 2, (Object) null);
        } else if (i == 2) {
            showFragment$default(this, FragmentName.SSL, false, 2, (Object) null);
        } else if (i == 3) {
            showFragment$default(this, FragmentName.OTP, false, 2, (Object) null);
        } else if (i != 4) {
            onDone();
        } else {
            showFragment$default(this, FragmentName.CONTENT, false, 2, (Object) null);
        }
        if (getViewModel().getCurrentFragment() == FragmentName.CONTENT) {
            Button button2 = this.nextButton;
            if (button2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("nextButton");
                button2 = null;
            }
            button2.setText(getString(R.string.done));
            Button button3 = this.skipButton;
            if (button3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("skipButton");
            } else {
                button = button3;
            }
            KotlinExtensionsKt.setGone(button);
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
