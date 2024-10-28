package org.videolan.vlc.gui.preferences;

import android.content.SharedPreferences;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.CheckBoxPreference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.resources.Constants;
import org.videolan.tools.Settings;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesFragment.kt */
final class PreferencesFragment$onPreferenceTreeClick$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ CheckBoxPreference $audioResumePref;
    final /* synthetic */ PreferencesFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesFragment$onPreferenceTreeClick$1(PreferencesFragment preferencesFragment, CheckBoxPreference checkBoxPreference) {
        super(0);
        this.this$0 = preferencesFragment;
        this.$audioResumePref = checkBoxPreference;
    }

    public final void invoke() {
        Settings settings = Settings.INSTANCE;
        FragmentActivity requireActivity = this.this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        ((SharedPreferences) settings.getInstance(requireActivity)).edit().remove(Constants.KEY_AUDIO_LAST_PLAYLIST).remove(Constants.KEY_MEDIA_LAST_PLAYLIST_RESUME).remove(Constants.KEY_CURRENT_AUDIO_RESUME_TITLE).remove(Constants.KEY_CURRENT_AUDIO_RESUME_ARTIST).remove(Constants.KEY_CURRENT_AUDIO_RESUME_THUMB).remove(Constants.KEY_CURRENT_AUDIO).remove(Constants.KEY_CURRENT_MEDIA).remove(Constants.KEY_CURRENT_MEDIA_RESUME).apply();
        FragmentActivity activity = this.this$0.getActivity();
        if (activity != null) {
            activity.setResult(3);
        }
        this.$audioResumePref.setChecked(false);
    }
}
