package org.videolan.vlc.gui.preferences.hack;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.core.os.BundleKt;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import androidx.preference.DialogPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceDialogFragmentCompat;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0018\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001e2\u00020\u00012\u00020\u0002:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0003J#\u0010\u0012\u001a\u0004\u0018\u0001H\u0013\"\n\b\u0000\u0010\u0013*\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0002\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\rH\u0016J\u0010\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001dH\u0014R\u0014\u0010\u0004\u001a\u00020\u00058BX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000f8BX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, d2 = {"Lorg/videolan/vlc/gui/preferences/hack/MultiSelectListPreferenceDialogFragmentCompat;", "Landroidx/preference/PreferenceDialogFragmentCompat;", "Landroidx/preference/DialogPreference$TargetFragment;", "()V", "listPreference", "Landroidx/preference/MultiSelectListPreference;", "getListPreference", "()Landroidx/preference/MultiSelectListPreference;", "newValues", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "preferenceChanged", "", "selectedItems", "", "getSelectedItems", "()[Z", "findPreference", "T", "Landroidx/preference/Preference;", "key", "", "(Ljava/lang/CharSequence;)Landroidx/preference/Preference;", "onDialogClosed", "", "positiveResult", "onPrepareDialogBuilder", "builder", "Landroidx/appcompat/app/AlertDialog$Builder;", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MultiSelectListPreferenceDialogFragmentCompat.kt */
public final class MultiSelectListPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat implements DialogPreference.TargetFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final HashSet<String> newValues = new HashSet<>();
    private boolean preferenceChanged;

    private final MultiSelectListPreference getListPreference() {
        DialogPreference preference = getPreference();
        Intrinsics.checkNotNull(preference, "null cannot be cast to non-null type androidx.preference.MultiSelectListPreference");
        return (MultiSelectListPreference) preference;
    }

    private final boolean[] getSelectedItems() {
        MultiSelectListPreference listPreference = getListPreference();
        CharSequence[] entryValues = listPreference.getEntryValues();
        Set<String> values = listPreference.getValues();
        boolean[] zArr = new boolean[entryValues.length];
        int length = entryValues.length;
        for (int i = 0; i < length; i++) {
            zArr[i] = values.contains(entryValues[i].toString());
        }
        return zArr;
    }

    /* access modifiers changed from: protected */
    public void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        super.onPrepareDialogBuilder(builder);
        MultiSelectListPreference listPreference = getListPreference();
        if (listPreference.getEntries() == null || listPreference.getEntryValues() == null) {
            throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
        }
        builder.setMultiChoiceItems(listPreference.getEntries(), getSelectedItems(), (DialogInterface.OnMultiChoiceClickListener) new MultiSelectListPreferenceDialogFragmentCompat$$ExternalSyntheticLambda0(this, listPreference));
        this.newValues.clear();
        this.newValues.addAll(listPreference.getValues());
    }

    /* access modifiers changed from: private */
    public static final void onPrepareDialogBuilder$lambda$0(MultiSelectListPreferenceDialogFragmentCompat multiSelectListPreferenceDialogFragmentCompat, MultiSelectListPreference multiSelectListPreference, DialogInterface dialogInterface, int i, boolean z) {
        Intrinsics.checkNotNullParameter(multiSelectListPreferenceDialogFragmentCompat, "this$0");
        Intrinsics.checkNotNullParameter(multiSelectListPreference, "$preference");
        multiSelectListPreferenceDialogFragmentCompat.preferenceChanged = true;
        if (z) {
            multiSelectListPreferenceDialogFragmentCompat.newValues.add(multiSelectListPreference.getEntryValues()[i].toString());
        } else {
            multiSelectListPreferenceDialogFragmentCompat.newValues.remove(multiSelectListPreference.getEntryValues()[i].toString());
        }
    }

    public void onDialogClosed(boolean z) {
        MultiSelectListPreference listPreference = getListPreference();
        if (z && this.preferenceChanged) {
            HashSet<String> hashSet = this.newValues;
            if (listPreference.callChangeListener(hashSet)) {
                listPreference.setValues(hashSet);
            }
        }
        this.preferenceChanged = false;
    }

    public <T extends Preference> T findPreference(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, LeanbackPreferenceDialogFragment.ARG_KEY);
        T preference = getPreference();
        if (preference instanceof Preference) {
            return (Preference) preference;
        }
        return null;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/preferences/hack/MultiSelectListPreferenceDialogFragmentCompat$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/preferences/hack/MultiSelectListPreferenceDialogFragmentCompat;", "key", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MultiSelectListPreferenceDialogFragmentCompat.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MultiSelectListPreferenceDialogFragmentCompat newInstance(String str) {
            Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
            MultiSelectListPreferenceDialogFragmentCompat multiSelectListPreferenceDialogFragmentCompat = new MultiSelectListPreferenceDialogFragmentCompat();
            multiSelectListPreferenceDialogFragmentCompat.setArguments(BundleKt.bundleOf(TuplesKt.to(LeanbackPreferenceDialogFragment.ARG_KEY, str)));
            return multiSelectListPreferenceDialogFragmentCompat;
        }
    }
}
