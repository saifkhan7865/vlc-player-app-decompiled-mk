package org.videolan.television.ui.preferences;

import androidx.preference.CheckBoxPreference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesUi.kt */
final class PreferencesUi$onPreferenceTreeClick$dialog$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ PreferencesUi this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesUi$onPreferenceTreeClick$dialog$1(PreferencesUi preferencesUi) {
        super(0);
        this.this$0 = preferencesUi;
    }

    public final void invoke() {
        CheckBoxPreference access$getTvUiPref$p = this.this$0.tvUiPref;
        if (access$getTvUiPref$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvUiPref");
            access$getTvUiPref$p = null;
        }
        access$getTvUiPref$p.setChecked(false);
    }
}
