package org.videolan.vlc.gui.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import androidx.preference.DialogPreference;
import androidx.preference.PreferenceDialogFragmentCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0014J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0014J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/videolan/vlc/gui/view/NumberPickerPreferenceDialog;", "Landroidx/preference/PreferenceDialogFragmentCompat;", "()V", "numberPicker", "Landroid/widget/NumberPicker;", "onBindDialogView", "", "view", "Landroid/view/View;", "onCreateDialogView", "context", "Landroid/content/Context;", "onDialogClosed", "positiveResult", "", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NumberPickerPreferenceDialog.kt */
public final class NumberPickerPreferenceDialog extends PreferenceDialogFragmentCompat {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private NumberPicker numberPicker;

    /* access modifiers changed from: protected */
    public View onCreateDialogView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        NumberPicker numberPicker2 = null;
        View inflate = getLayoutInflater().inflate(R.layout.pref_number_picker, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.number_picker);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        NumberPicker numberPicker3 = (NumberPicker) findViewById;
        this.numberPicker = numberPicker3;
        if (numberPicker3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("numberPicker");
            numberPicker3 = null;
        }
        numberPicker3.setMinValue(1);
        NumberPicker numberPicker4 = this.numberPicker;
        if (numberPicker4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("numberPicker");
        } else {
            numberPicker2 = numberPicker4;
        }
        numberPicker2.setMaxValue(100);
        Intrinsics.checkNotNull(inflate);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void onBindDialogView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onBindDialogView(view);
        NumberPicker numberPicker2 = this.numberPicker;
        if (numberPicker2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("numberPicker");
            numberPicker2 = null;
        }
        DialogPreference preference = getPreference();
        Intrinsics.checkNotNull(preference, "null cannot be cast to non-null type org.videolan.vlc.gui.view.NumberPickerPreference");
        numberPicker2.setValue(((NumberPickerPreference) preference).getPersistedInt());
    }

    public void onDialogClosed(boolean z) {
        if (z) {
            NumberPicker numberPicker2 = this.numberPicker;
            NumberPicker numberPicker3 = null;
            if (numberPicker2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("numberPicker");
                numberPicker2 = null;
            }
            numberPicker2.clearFocus();
            NumberPicker numberPicker4 = this.numberPicker;
            if (numberPicker4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("numberPicker");
            } else {
                numberPicker3 = numberPicker4;
            }
            int value = numberPicker3.getValue();
            if (getPreference().callChangeListener(Integer.valueOf(value))) {
                DialogPreference preference = getPreference();
                Intrinsics.checkNotNull(preference, "null cannot be cast to non-null type org.videolan.vlc.gui.view.NumberPickerPreference");
                ((NumberPickerPreference) preference).doPersistInt(value);
                getPreference().getSummary();
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/view/NumberPickerPreferenceDialog$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/view/NumberPickerPreferenceDialog;", "key", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: NumberPickerPreferenceDialog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NumberPickerPreferenceDialog newInstance(String str) {
            Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
            NumberPickerPreferenceDialog numberPickerPreferenceDialog = new NumberPickerPreferenceDialog();
            Bundle bundle = new Bundle(1);
            bundle.putString(LeanbackPreferenceDialogFragment.ARG_KEY, str);
            numberPickerPreferenceDialog.setArguments(bundle);
            return numberPickerPreferenceDialog;
        }
    }
}
