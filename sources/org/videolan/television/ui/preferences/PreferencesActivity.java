package org.videolan.television.ui.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.television.R;
import org.videolan.television.ui.browser.BaseTvActivity;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.gui.PinCodeActivity;
import org.videolan.vlc.gui.PinCodeReason;
import videolan.org.commontools.LiveEvent;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0004J\"\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\u0012\u0010\u000f\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0004H\u0014J\u0006\u0010\u0016\u001a\u00020\u0004J\u0006\u0010\u0017\u001a\u00020\u0004¨\u0006\u0018"}, d2 = {"Lorg/videolan/television/ui/preferences/PreferencesActivity;", "Lorg/videolan/television/ui/browser/BaseTvActivity;", "()V", "applyTheme", "", "detectHeadset", "detect", "", "exitAndRescan", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "refresh", "setRestart", "setRestartApp", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesActivity.kt */
public final class PreferencesActivity extends BaseTvActivity {
    /* access modifiers changed from: protected */
    public void refresh() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.tv_preferences_activity);
        if (((SharedPreferences) Settings.INSTANCE.getInstance(this)).getBoolean(SettingsKt.KEY_RESTRICT_SETTINGS, false)) {
            startActivityForResult(PinCodeActivity.Companion.getIntent(this, PinCodeReason.CHECK), 0);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != -1) {
            finish();
        }
        super.onActivityResult(i, i2, intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        if (getFragmentManager().popBackStackImmediate()) {
            return true;
        }
        finish();
        return true;
    }

    private final void applyTheme() {
        if (((SharedPreferences) Settings.INSTANCE.getInstance(this)).getBoolean(SettingsKt.KEY_BLACK_THEME, false)) {
            setTheme(R.style.Theme_VLC_Black);
        }
    }

    public final void setRestart() {
        setResult(3);
    }

    public final void setRestartApp() {
        setResult(4);
    }

    public final void exitAndRescan() {
        setRestart();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public final void detectHeadset(boolean z) {
        LiveEvent<Boolean> headSetDetection = PlaybackService.Companion.getHeadSetDetection();
        if (headSetDetection.hasObservers()) {
            headSetDetection.setValue(Boolean.valueOf(z));
        }
    }
}
