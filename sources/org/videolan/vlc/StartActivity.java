package org.videolan.vlc;

import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.Constants;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.AppScope;
import org.videolan.tools.LocaleUtilsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.gui.BetaWelcomeActivity;
import org.videolan.vlc.gui.onboarding.OnboardingActivityKt;
import org.videolan.vlc.gui.video.VideoPlayerActivity;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.util.FileUtils;
import videolan.org.commontools.TvChannelUtilsKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\b\u0010\u000b\u001a\u00020\nH\u0016J\"\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0012\u0010\u0011\u001a\u00020\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\bH\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J2\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u0016H\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0010H\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006 "}, d2 = {"Lorg/videolan/vlc/StartActivity;", "Landroidx/fragment/app/FragmentActivity;", "()V", "idFromShortcut", "", "getIdFromShortcut", "()I", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "getApplicationContext", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "resume", "showTvUi", "", "startApplication", "tv", "firstRun", "upgrade", "target", "removeDevices", "startPlaybackFromApp", "Lkotlinx/coroutines/Job;", "intent", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: StartActivity.kt */
public final class StartActivity extends FragmentActivity {
    private final int getIdFromShortcut() {
        if (!AndroidUtil.isNougatMR1OrLater) {
            return 0;
        }
        Intent intent = getIntent();
        String action = intent != null ? intent.getAction() : null;
        CharSequence charSequence = action;
        if (charSequence == null || charSequence.length() == 0) {
            return 0;
        }
        switch (action.hashCode()) {
            case -1617963807:
                if (!action.equals("vlc.shortcut.browser")) {
                    return 0;
                }
                return R.id.nav_directories;
            case -780181553:
                if (!action.equals("vlc.shortcut.audio")) {
                    return 0;
                }
                return R.id.nav_audio;
            case -761145228:
                if (!action.equals("vlc.shortcut.video")) {
                    return 0;
                }
                return R.id.nav_video;
            case 2056553300:
                if (!action.equals("vlc.shortcut.resume")) {
                    return 0;
                }
                return R.id.ml_menu_last_playlist;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context != null ? LocaleUtilsKt.getContextWithLocale(context, AppContextProvider.INSTANCE.getLocale()) : null);
    }

    public Context getApplicationContext() {
        Context applicationContext = super.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return LocaleUtilsKt.getContextWithLocale(applicationContext, AppContextProvider.INSTANCE.getLocale());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            if (!Settings.INSTANCE.getShowTvUi() && !((SharedPreferences) Settings.INSTANCE.getInstance(this)).getBoolean(SettingsKt.BETA_WELCOME, false)) {
                Intent intent = new Intent(this, BetaWelcomeActivity.class);
                intent.addFlags(65536);
                startActivityForResult(intent, 0);
                SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(this), SettingsKt.BETA_WELCOME, true);
                return;
            }
        } catch (Exception unused) {
        }
        resume();
    }

    private final void resume() {
        Intent intent = getIntent();
        String action = intent != null ? intent.getAction() : null;
        if (Intrinsics.areEqual((Object) "android.intent.action.VIEW", (Object) action) || Intrinsics.areEqual((Object) Constants.ACTION_VIEW_ARC, (Object) action)) {
            Uri data = intent.getData();
            if (!Intrinsics.areEqual((Object) TvChannelUtilsKt.TV_CHANNEL_SCHEME, (Object) data != null ? data.getScheme() : null)) {
                Intrinsics.checkNotNull(intent);
                startPlaybackFromApp(intent);
                return;
            }
        }
        if (Intrinsics.areEqual((Object) "android.intent.action.SEND", (Object) action)) {
            ClipData clipData = intent.getClipData();
            ClipData.Item itemAt = (clipData == null || clipData.getItemCount() <= 0) ? null : clipData.getItemAt(0);
            if (itemAt != null) {
                Uri uri = FileUtils.INSTANCE.getUri(itemAt.getUri());
                if (uri == null && itemAt.getText() != null) {
                    uri = Uri.parse(itemAt.getText().toString());
                }
                if (uri != null) {
                    MediaUtils.INSTANCE.openMediaNoUi(uri);
                    finish();
                    return;
                }
            }
        }
        if (intent.hasExtra(MLServiceLocator.EXTRA_TEST_STUBS) && intent.getBooleanExtra(MLServiceLocator.EXTRA_TEST_STUBS, false)) {
            MLServiceLocator.setLocatorMode(MLServiceLocator.LocatorMode.TESTS);
            Log.i("VLC/StartActivity", "onCreate: Setting test mode`");
        }
        SharedPreferences sharedPreferences = (SharedPreferences) Settings.INSTANCE.getInstance(this);
        int i = sharedPreferences.getInt(Constants.PREF_FIRST_RUN, -1);
        boolean z = i == -1;
        Settings.INSTANCE.setFirstRun(z);
        boolean z2 = z || i != 3050720;
        boolean showTvUi = showTvUi();
        if (z2 && (showTvUi || !z)) {
            SettingsKt.putSingle(sharedPreferences, Constants.PREF_FIRST_RUN, 3050720);
        }
        boolean z3 = 3028201 <= i && i < 3028400;
        if (Intrinsics.areEqual((Object) "android.intent.action.SEARCH", (Object) action) || Intrinsics.areEqual((Object) Constants.ACTION_SEARCH_GMS, (Object) action)) {
            intent.setClassName(getApplicationContext(), showTvUi ? Constants.TV_SEARCH_ACTIVITY : Constants.MOBILE_SEARCH_ACTIVITY);
            startActivity(intent);
            finish();
            return;
        }
        if (Intrinsics.areEqual((Object) "android.media.action.MEDIA_PLAY_FROM_SEARCH", (Object) action)) {
            Context context = this;
            Intent putExtra = new Intent(Constants.ACTION_PLAY_FROM_SEARCH, (Uri) null, context, PlaybackService.class).putExtra(Constants.EXTRA_SEARCH_BUNDLE, intent.getExtras());
            Intrinsics.checkNotNullExpressionValue(putExtra, "putExtra(...)");
            ExtensionsKt.launchForeground$default(context, putExtra, (Function0) null, 2, (Object) null);
        } else if (Intrinsics.areEqual((Object) "android.intent.action.VIEW", (Object) action) && intent.getData() != null) {
            Uri data2 = intent.getData();
            Intrinsics.checkNotNull(data2);
            String path = data2.getPath();
            if (Intrinsics.areEqual((Object) path, (Object) "/startapp")) {
                startApplication(showTvUi, z, z2, 0, z3);
            } else if (Intrinsics.areEqual((Object) path, (Object) "/video")) {
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                String queryParameter = data2.getQueryParameter(TvChannelUtilsKt.TV_CHANNEL_QUERY_VIDEO_ID);
                Intrinsics.checkNotNull(queryParameter);
                objectRef.element = Long.valueOf(queryParameter);
                Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), (CoroutineStart) null, new StartActivity$resume$1(objectRef, this, (Continuation<? super StartActivity$resume$1>) null), 2, (Object) null);
            }
        } else if (action != null && StringsKt.startsWith$default(action, "vlc.mediashortcut:", false, 2, (Object) null)) {
            List split$default = StringsKt.split$default((CharSequence) action, new String[]{":"}, false, 0, 6, (Object) null);
            Job unused2 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new StartActivity$resume$2(this, (String) split$default.get(split$default.size() - 2), (String) CollectionsKt.last(split$default), (Continuation<? super StartActivity$resume$2>) null), 3, (Object) null);
        } else if (action == null || !Intrinsics.areEqual((Object) action, (Object) "vlc.remoteaccess.share")) {
            int idFromShortcut = getIdFromShortcut();
            PlaybackService instance = PlaybackService.Companion.getInstance();
            if (idFromShortcut == R.id.ml_menu_last_playlist) {
                PlaybackService.Companion.loadLastAudio(this);
            } else if (instance == null || !Intrinsics.areEqual((Object) instance.isInPiPMode().getValue(), (Object) true)) {
                startApplication(showTvUi, z, z2, idFromShortcut, z3);
            } else {
                instance.isInPiPMode().setValue(false);
                Intent intent2 = new Intent(this, VideoPlayerActivity.class);
                intent2.setFlags(131072);
                startActivity(intent2);
            }
        } else {
            Intent intent3 = new Intent();
            intent3.setComponent(new ComponentName(this, "org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity"));
            startActivity(intent3);
        }
        FileUtils.INSTANCE.copyLua(getApplicationContext(), z2);
        FileUtils.INSTANCE.copyHrtfs(getApplicationContext(), z2);
        if (AndroidDevices.INSTANCE.getWatchDevices()) {
            StoragesMonitorKt.enableStorageMonitoring(this);
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0) {
            resume();
        }
        if (i == 1) {
            setResult(i2, intent);
            finish();
        }
    }

    static /* synthetic */ void startApplication$default(StartActivity startActivity, boolean z, boolean z2, boolean z3, int i, boolean z4, int i2, Object obj) {
        startActivity.startApplication(z, z2, z3, i, (i2 & 16) != 0 ? false : z4);
    }

    private final void startApplication(boolean z, boolean z2, boolean z3, int i, boolean z4) {
        SharedPreferences sharedPreferences = (SharedPreferences) Settings.INSTANCE.getInstance(this);
        boolean z5 = !sharedPreferences.getBoolean(z ? SettingsKt.KEY_TV_ONBOARDING_DONE : OnboardingActivityKt.ONBOARDING_DONE_KEY, false);
        if (!z5 || !z2) {
            new Thread(new StartActivity$$ExternalSyntheticLambda0(this, z2, z3, z4, z5, sharedPreferences)).start();
            Intent putExtra = new Intent("android.intent.action.VIEW").setClassName(getApplicationContext(), z ? Constants.TV_MAIN_ACTIVITY : Constants.MOBILE_MAIN_ACTIVITY).putExtra(Constants.EXTRA_FIRST_RUN, z2).putExtra(Constants.EXTRA_UPGRADE, z3);
            Intrinsics.checkNotNullExpressionValue(putExtra, "putExtra(...)");
            if (z && getIntent().hasExtra("extra_path")) {
                putExtra.putExtra("extra_path", getIntent().getStringExtra("extra_path"));
            }
            if (i != 0) {
                putExtra.putExtra("extra_parse", i);
            }
            startActivity(putExtra);
        } else if (!z) {
            OnboardingActivityKt.startOnboarding(this);
        } else {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setClassName(getApplicationContext(), Constants.TV_ONBOARDING_ACTIVITY);
            startActivity(intent);
        }
    }

    /* access modifiers changed from: private */
    public static final void startApplication$lambda$1(StartActivity startActivity, boolean z, boolean z2, boolean z3, boolean z4, SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(startActivity, "this$0");
        Intrinsics.checkNotNullParameter(sharedPreferences, "$settings");
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new StartActivity$startApplication$1$1(startActivity, z, z2, z3, z4, sharedPreferences, (Continuation<? super StartActivity$startApplication$1$1>) null), 3, (Object) null);
    }

    private final Job startPlaybackFromApp(Intent intent) {
        return BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new StartActivity$startPlaybackFromApp$1(this, intent, (Continuation<? super StartActivity$startPlaybackFromApp$1>) null), 1, (Object) null);
    }

    private final boolean showTvUi() {
        SharedPreferences sharedPreferences = (SharedPreferences) Settings.INSTANCE.getInstance(this);
        if (sharedPreferences.getInt(SettingsKt.KEY_CURRENT_SETTINGS_VERSION, 0) >= 5) {
            return sharedPreferences.getBoolean(SettingsKt.PREF_TV_UI, false);
        }
        if (AndroidDevices.INSTANCE.isAndroidTv() || ((!AndroidDevices.INSTANCE.isChromeBook() && !AndroidDevices.INSTANCE.getHasTsp()) || sharedPreferences.getBoolean(SettingsKt.PREF_TV_UI, false))) {
            return true;
        }
        return false;
    }
}
