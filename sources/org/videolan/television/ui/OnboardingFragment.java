package org.videolan.television.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.app.OnboardingSupportFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;
import org.videolan.resources.util.HelpersKt;
import org.videolan.television.R;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.util.Permissions;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0014J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0014J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0016\u001a\u00020\nH\u0014J\u0018\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0014¨\u0006\u001a"}, d2 = {"Lorg/videolan/television/ui/OnboardingFragment;", "Landroidx/leanback/app/OnboardingSupportFragment;", "()V", "getPageCount", "", "getPageDescription", "", "pageIndex", "getPageTitle", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateBackgroundView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onCreateContentView", "onCreateForegroundView", "", "onFinishFragment", "onPageChanged", "newPage", "previousPage", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OnboardingFragment.kt */
public final class OnboardingFragment extends OnboardingSupportFragment {
    /* access modifiers changed from: protected */
    public int getPageCount() {
        return 3;
    }

    /* access modifiers changed from: protected */
    public Void onCreateForegroundView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStartButtonText(getString(R.string.start_vlc));
        setArrowColor(ContextCompat.getColor(requireActivity(), R.color.white));
    }

    /* access modifiers changed from: protected */
    public String getPageTitle(int i) {
        String str;
        if (i == 0) {
            str = getString(R.string.welcome_title);
        } else if (i != 1) {
            str = getString(R.string.onboarding_all_set);
        } else {
            str = getString(R.string.onboarding_scan_title);
        }
        Intrinsics.checkNotNull(str);
        return str;
    }

    /* access modifiers changed from: protected */
    public String getPageDescription(int i) {
        String str;
        if (i == 0) {
            str = getString(R.string.welcome_subtitle);
        } else if (i != 1) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            if (HelpersKt.canReadStorage(requireActivity)) {
                str = getString(R.string.onboarding_permission_given);
            } else {
                str = getString(R.string.permission_expanation_no_allow) + 10 + getString(R.string.permission_expanation_allow);
            }
        } else {
            str = getString(R.string.permission_media);
        }
        Intrinsics.checkNotNull(str);
        return str;
    }

    /* access modifiers changed from: protected */
    public View onCreateBackgroundView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view = new View(getActivity());
        view.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.onboarding_grey));
        return view;
    }

    /* access modifiers changed from: protected */
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(requireActivity());
        imageView.setImageResource(R.drawable.ic_launcher_foreground);
        return imageView;
    }

    /* access modifiers changed from: protected */
    public void onPageChanged(int i, int i2) {
        if (i == 1) {
            Permissions permissions = Permissions.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            if (!permissions.canReadStorage(requireActivity)) {
                Permissions permissions2 = Permissions.INSTANCE;
                FragmentActivity requireActivity2 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                Permissions.checkReadStoragePermission$default(permissions2, requireActivity2, false, 2, (Object) null);
            }
        }
        super.onPageChanged(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onFinishFragment() {
        super.onFinishFragment();
        Settings settings = Settings.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        SettingsKt.putSingle((SharedPreferences) settings.getInstance(requireActivity), SettingsKt.KEY_TV_ONBOARDING_DONE, true);
        requireActivity().finish();
        Intent className = new Intent("android.intent.action.VIEW").setClassName(requireActivity(), Constants.TV_MAIN_ACTIVITY);
        Intrinsics.checkNotNullExpressionValue(className, "setClassName(...)");
        startActivity(className);
    }
}
