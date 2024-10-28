package org.videolan.television.ui.preferences;

import android.app.Activity;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.resources.Constants;
import org.videolan.tools.Settings;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesAdvanced.kt */
final class PreferencesAdvanced$onPreferenceTreeClick$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ PreferencesAdvanced this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesAdvanced$onPreferenceTreeClick$2(PreferencesAdvanced preferencesAdvanced) {
        super(0);
        this.this$0 = preferencesAdvanced;
    }

    public final void invoke() {
        Medialibrary.getInstance().clearHistory(0);
        Settings settings = Settings.INSTANCE;
        Activity activity = this.this$0.getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
        ((SharedPreferences) settings.getInstance(activity)).edit().remove(Constants.KEY_AUDIO_LAST_PLAYLIST).remove("media_list").remove(Constants.KEY_MEDIA_LAST_PLAYLIST_RESUME).remove(Constants.KEY_CURRENT_AUDIO).remove(Constants.KEY_CURRENT_MEDIA).remove(Constants.KEY_CURRENT_MEDIA_RESUME).remove(Constants.KEY_CURRENT_AUDIO_RESUME_TITLE).remove(Constants.KEY_CURRENT_AUDIO_RESUME_ARTIST).remove(Constants.KEY_CURRENT_AUDIO_RESUME_THUMB).apply();
    }
}
