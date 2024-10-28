package org.videolan.vlc.gui.preferences;

import androidx.preference.CheckBoxPreference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesOptional.kt */
final class PreferencesOptional$onPreferenceTreeClick$1$dialog$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ CheckBoxPreference $currentPreference;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesOptional$onPreferenceTreeClick$1$dialog$1(CheckBoxPreference checkBoxPreference) {
        super(0);
        this.$currentPreference = checkBoxPreference;
    }

    public final void invoke() {
        this.$currentPreference.setChecked(true);
    }
}
