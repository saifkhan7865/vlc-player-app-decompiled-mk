package org.videolan.television.ui.preferences;

import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import androidx.core.os.BundleKt;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import androidx.preference.EditTextPreferenceDialogFragment;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014J\u0019\u0010\r\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\bR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/videolan/television/ui/preferences/CustomEditTextPreferenceDialogFragment;", "Landroidx/preference/EditTextPreferenceDialogFragment;", "()V", "customFilters", "", "Landroid/text/InputFilter;", "[Landroid/text/InputFilter;", "customInputType", "", "onBindDialogView", "", "view", "Landroid/view/View;", "setFilters", "filters", "([Landroid/text/InputFilter;)V", "setInputType", "inputType", "Companion", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CustomEditTextPreferenceDialogFragment.kt */
public final class CustomEditTextPreferenceDialogFragment extends EditTextPreferenceDialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private InputFilter[] customFilters = new InputFilter[0];
    private int customInputType;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/television/ui/preferences/CustomEditTextPreferenceDialogFragment$Companion;", "", "()V", "newInstance", "Lorg/videolan/television/ui/preferences/CustomEditTextPreferenceDialogFragment;", "key", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CustomEditTextPreferenceDialogFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CustomEditTextPreferenceDialogFragment newInstance(String str) {
            CustomEditTextPreferenceDialogFragment customEditTextPreferenceDialogFragment = new CustomEditTextPreferenceDialogFragment();
            customEditTextPreferenceDialogFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(LeanbackPreferenceDialogFragment.ARG_KEY, str)));
            return customEditTextPreferenceDialogFragment;
        }
    }

    /* access modifiers changed from: protected */
    public void onBindDialogView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        EditText editText = (EditText) view.findViewById(16908291);
        InputFilter[] inputFilterArr = this.customFilters;
        if (!(inputFilterArr.length == 0)) {
            editText.setFilters(inputFilterArr);
        }
        int i = this.customInputType;
        if (i != 0) {
            editText.setInputType(i);
        }
        super.onBindDialogView(view);
    }

    public final void setFilters(InputFilter[] inputFilterArr) {
        Intrinsics.checkNotNullParameter(inputFilterArr, "filters");
        this.customFilters = inputFilterArr;
    }

    public final void setInputType(int i) {
        this.customInputType = i;
    }
}
