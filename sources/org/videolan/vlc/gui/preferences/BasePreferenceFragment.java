package org.videolan.vlc.gui.preferences;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceGroup;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.preferences.hack.MultiSelectListPreferenceDialogFragmentCompat;
import org.videolan.vlc.gui.preferences.search.PreferenceItem;
import org.videolan.vlc.gui.view.NumberPickerPreference;
import org.videolan.vlc.gui.view.NumberPickerPreferenceDialog;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H$J\b\u0010\u0005\u001a\u00020\u0004H$J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0004J\u001c\u0010\n\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0007H\u0016J\u001a\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u000eH\u0002¨\u0006\u001a"}, d2 = {"Lorg/videolan/vlc/gui/preferences/BasePreferenceFragment;", "Landroidx/preference/PreferenceFragmentCompat;", "()V", "getTitleId", "", "getXml", "loadFragment", "", "fragment", "Landroidx/fragment/app/Fragment;", "onCreatePreferences", "bundle", "Landroid/os/Bundle;", "s", "", "onDisplayPreferenceDialog", "preference", "Landroidx/preference/Preference;", "onStart", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "selectPreference", "key", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BasePreferenceFragment.kt */
public abstract class BasePreferenceFragment extends PreferenceFragmentCompat {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String DIALOG_FRAGMENT_TAG = "android.support.v7.preference.PreferenceFragment.DIALOG";

    /* access modifiers changed from: protected */
    public abstract int getTitleId();

    /* access modifiers changed from: protected */
    public abstract int getXml();

    public void onCreatePreferences(Bundle bundle, String str) {
        addPreferencesFromResource(getXml());
    }

    public void onStart() {
        super.onStart();
        FragmentActivity activity = getActivity();
        PreferencesActivity preferencesActivity = activity instanceof PreferencesActivity ? (PreferencesActivity) activity : null;
        if (preferencesActivity != null) {
            preferencesActivity.expandBar$vlc_android_release();
            if (preferencesActivity.getSupportActionBar() != null && getTitleId() != 0) {
                ActionBar supportActionBar = preferencesActivity.getSupportActionBar();
                Intrinsics.checkNotNull(supportActionBar);
                supportActionBar.setTitle((CharSequence) getString(getTitleId()));
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void loadFragment(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, SecondaryActivity.KEY_FRAGMENT);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, fragment).addToBackStack("main").commit();
    }

    public void onDisplayPreferenceDialog(Preference preference) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        if (preference instanceof MultiSelectListPreference) {
            MultiSelectListPreferenceDialogFragmentCompat.Companion companion = MultiSelectListPreferenceDialogFragmentCompat.Companion;
            String key = preference.getKey();
            Intrinsics.checkNotNullExpressionValue(key, "getKey(...)");
            MultiSelectListPreferenceDialogFragmentCompat newInstance = companion.newInstance(key);
            newInstance.setTargetFragment(this, 0);
            newInstance.show(getParentFragmentManager(), DIALOG_FRAGMENT_TAG);
        } else if (preference instanceof NumberPickerPreference) {
            NumberPickerPreferenceDialog.Companion companion2 = NumberPickerPreferenceDialog.Companion;
            String key2 = ((NumberPickerPreference) preference).getKey();
            Intrinsics.checkNotNullExpressionValue(key2, "getKey(...)");
            NumberPickerPreferenceDialog newInstance2 = companion2.newInstance(key2);
            newInstance2.setTargetFragment(this, 0);
            newInstance2.show(getParentFragmentManager(), DIALOG_FRAGMENT_TAG);
        } else {
            super.onDisplayPreferenceDialog(preference);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        Parcelable parcelable;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (Build.VERSION.SDK_INT >= 33) {
                parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(arguments, PreferencesActivityKt.EXTRA_PREF_END_POINT, PreferenceItem.class);
            } else {
                Parcelable parcelable2 = arguments.getParcelable(PreferencesActivityKt.EXTRA_PREF_END_POINT);
                if (!(parcelable2 instanceof PreferenceItem)) {
                    parcelable2 = null;
                }
                parcelable = (PreferenceItem) parcelable2;
            }
            PreferenceItem preferenceItem = (PreferenceItem) parcelable;
            if (preferenceItem != null) {
                selectPreference(preferenceItem.getKey());
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/preferences/BasePreferenceFragment$Companion;", "", "()V", "DIALOG_FRAGMENT_TAG", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BasePreferenceFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final void selectPreference(String str) {
        scrollToPreference(str);
        Preference findPreference = findPreference(str);
        if (findPreference != null) {
            findPreference.setSelectable(true);
        }
        RecyclerView listView = getListView();
        if (listView != null) {
            listView.postDelayed(new BasePreferenceFragment$$ExternalSyntheticLambda0(this, str), 200);
        }
    }

    /* access modifiers changed from: private */
    public static final void selectPreference$lambda$5(BasePreferenceFragment basePreferenceFragment, String str) {
        RecyclerView listView;
        RecyclerView.ViewHolder findViewHolderForAdapterPosition;
        View view;
        RecyclerView listView2;
        Intrinsics.checkNotNullParameter(basePreferenceFragment, "this$0");
        Intrinsics.checkNotNullParameter(str, "$key");
        RecyclerView listView3 = basePreferenceFragment.getListView();
        PreferenceGroup.PreferencePositionCallback preferencePositionCallback = null;
        RecyclerView.Adapter adapter = listView3 != null ? listView3.getAdapter() : null;
        if (adapter instanceof PreferenceGroup.PreferencePositionCallback) {
            preferencePositionCallback = (PreferenceGroup.PreferencePositionCallback) adapter;
        }
        if (preferencePositionCallback != null && (listView = basePreferenceFragment.getListView()) != null && (findViewHolderForAdapterPosition = listView.findViewHolderForAdapterPosition(preferencePositionCallback.getPreferenceAdapterPosition(str))) != null && (view = findViewHolderForAdapterPosition.itemView) != null && (listView2 = basePreferenceFragment.getListView()) != null) {
            listView2.postDelayed(new BasePreferenceFragment$$ExternalSyntheticLambda1(view), 600);
        }
    }

    /* access modifiers changed from: private */
    public static final void selectPreference$lambda$5$lambda$4$lambda$3$lambda$2(View view) {
        Intrinsics.checkNotNullParameter(view, "$itemView");
        view.setPressed(true);
    }
}
