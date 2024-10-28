package org.videolan.television.ui.preferences;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import androidx.leanback.preference.LeanbackPreferenceFragment;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceDialogFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.SecondaryActivity;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b&\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0004J\b\u0010\u0007\u001a\u00020\bH$J\b\u0010\t\u001a\u00020\bH$J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0004J\"\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u001c\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016¨\u0006\u0019"}, d2 = {"Lorg/videolan/television/ui/preferences/BasePreferenceFragment;", "Landroidx/leanback/preference/LeanbackPreferenceFragment;", "()V", "buildPreferenceDialogFragment", "Landroidx/preference/PreferenceDialogFragment;", "preference", "Landroidx/preference/Preference;", "getTitleId", "", "getXml", "loadFragment", "", "fragment", "Landroid/app/Fragment;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreatePreferences", "bundle", "Landroid/os/Bundle;", "s", "", "Companion", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BasePreferenceFragment.kt */
public abstract class BasePreferenceFragment extends LeanbackPreferenceFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String DIALOG_FRAGMENT_TAG = "androidx.preference.PreferenceFragment.DIALOG";

    /* access modifiers changed from: protected */
    public abstract int getTitleId();

    /* access modifiers changed from: protected */
    public abstract int getXml();

    public void onCreatePreferences(Bundle bundle, String str) {
        addPreferencesFromResource(getXml());
    }

    /* access modifiers changed from: protected */
    public final void loadFragment(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, SecondaryActivity.KEY_FRAGMENT);
        getActivity().getFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, fragment).addToBackStack("main").commit();
    }

    /* access modifiers changed from: protected */
    public final PreferenceDialogFragment buildPreferenceDialogFragment(Preference preference) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        if (!(preference instanceof EditTextPreference)) {
            return null;
        }
        CustomEditTextPreferenceDialogFragment newInstance = CustomEditTextPreferenceDialogFragment.Companion.newInstance(preference.getKey());
        newInstance.setTargetFragment(this, 0);
        newInstance.show(getFragmentManager(), DIALOG_FRAGMENT_TAG);
        return newInstance;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10001 && i2 == 1) {
            Process.killProcess(Process.myPid());
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/videolan/television/ui/preferences/BasePreferenceFragment$Companion;", "", "()V", "DIALOG_FRAGMENT_TAG", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BasePreferenceFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
