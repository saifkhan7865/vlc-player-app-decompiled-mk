package org.videolan.television.ui.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.Preference;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\nH\u0014J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001c\u0010\u0010\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\rH\u0016J\b\u0010\u0016\u001a\u00020\rH\u0016R\u0012\u0010\u0005\u001a\u00020\u0006X\u0005¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0017"}, d2 = {"Lorg/videolan/television/ui/preferences/PreferencesVideo;", "Lorg/videolan/television/ui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "getTitleId", "", "getXml", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSharedPreferenceChanged", "sharedPreferences", "Landroid/content/SharedPreferences;", "key", "", "onStart", "onStop", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesVideo.kt */
public final class PreferencesVideo extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener, CoroutineScope {
    private final /* synthetic */ CoroutineScope $$delegate_0 = CoroutineScopeKt.MainScope();

    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    /* access modifiers changed from: protected */
    public int getXml() {
        return R.xml.preferences_video;
    }

    /* access modifiers changed from: protected */
    public int getTitleId() {
        return R.string.video_prefs_category;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Preference findPreference = findPreference("secondary_display_category");
        if (findPreference != null) {
            findPreference.setVisible(false);
        }
        Preference findPreference2 = findPreference("secondary_display_category_summary");
        if (findPreference2 != null) {
            findPreference2.setVisible(false);
        }
        Preference findPreference3 = findPreference("enable_clone_mode");
        if (findPreference3 != null) {
            findPreference3.setVisible(false);
        }
        Preference findPreference4 = findPreference(SettingsKt.SAVE_BRIGHTNESS);
        if (findPreference4 != null) {
            findPreference4.setVisible(false);
        }
        Preference findPreference5 = findPreference(SettingsKt.POPUP_FORCE_LEGACY);
        if (findPreference5 != null) {
            findPreference5.setVisible(false);
        }
        Preference findPreference6 = findPreference(SettingsKt.LOCK_USE_SENSOR);
        if (findPreference6 != null) {
            findPreference6.setVisible(false);
        }
    }

    public void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        Intrinsics.checkNotNull(sharedPreferences);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    public void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        Intrinsics.checkNotNull(sharedPreferences);
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (sharedPreferences != null && str != null) {
            if (Intrinsics.areEqual((Object) str, (Object) "preferred_resolution")) {
                Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PreferencesVideo$onSharedPreferenceChanged$1((Continuation<? super PreferencesVideo$onSharedPreferenceChanged$1>) null), 3, (Object) null);
            } else if (Intrinsics.areEqual((Object) str, (Object) SettingsKt.KEY_PLAYBACK_SPEED_PERSIST_VIDEO)) {
                SettingsKt.putSingle(sharedPreferences, SettingsKt.KEY_PLAYBACK_RATE_VIDEO, Float.valueOf(1.0f));
            }
        }
    }
}
