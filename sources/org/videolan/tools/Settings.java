package org.videolan.tools;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b3\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u000e\u0010[\u001a\u00020\u00022\u0006\u0010\\\u001a\u00020\u0003J\u0006\u0010]\u001a\u00020\u0007J\u0006\u0010^\u001a\u00020\u0007J\u0014\u0010_\u001a\u00020\u00072\f\u0010`\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\n\u0010a\u001a\u00020\u001d*\u00020\u0003R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u001e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012@BX.¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001f\"\u0004\b$\u0010!R\u001a\u0010%\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001f\"\u0004\b'\u0010!R\u001a\u0010(\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u000b\"\u0004\b*\u0010\rR\u001a\u0010+\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u001f\"\u0004\b-\u0010!R(\u0010.\u001a\u0010\u0012\f\u0012\n 0*\u0004\u0018\u00010\u001d0\u001d0/X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u00105\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u001f\"\u0004\b7\u0010!R\u001a\u00108\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u001f\"\u0004\b:\u0010!R\u001a\u0010;\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u001f\"\u0004\b=\u0010!R\u001a\u0010>\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\u001f\"\u0004\b@\u0010!R\u001a\u0010A\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\u001f\"\u0004\bC\u0010!R\u0011\u0010D\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\bE\u0010\u001fR\u001a\u0010F\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u001f\"\u0004\bH\u0010!R\u001a\u0010I\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u001f\"\u0004\bK\u0010!R\u001a\u0010L\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u001f\"\u0004\bN\u0010!R\u001a\u0010O\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010\u000b\"\u0004\bQ\u0010\rR\u001a\u0010R\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\u000b\"\u0004\bT\u0010\rR\u001a\u0010U\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010\u000b\"\u0004\bW\u0010\rR\u001a\u0010X\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010\u000b\"\u0004\bZ\u0010\r¨\u0006b"}, d2 = {"Lorg/videolan/tools/Settings;", "Lorg/videolan/tools/SingletonHolder;", "Landroid/content/SharedPreferences;", "Landroid/content/Context;", "()V", "audioControlsChangeListener", "Lkotlin/Function0;", "", "audioJumpDelay", "", "getAudioJumpDelay", "()I", "setAudioJumpDelay", "(I)V", "audioLongJumpDelay", "getAudioLongJumpDelay", "setAudioLongJumpDelay", "<set-?>", "Lorg/videolan/tools/DeviceInfo;", "device", "getDevice", "()Lorg/videolan/tools/DeviceInfo;", "fastplaySpeed", "", "getFastplaySpeed", "()F", "setFastplaySpeed", "(F)V", "firstRun", "", "getFirstRun", "()Z", "setFirstRun", "(Z)V", "includeMissing", "getIncludeMissing", "setIncludeMissing", "incognitoMode", "getIncognitoMode", "setIncognitoMode", "listTitleEllipsize", "getListTitleEllipsize", "setListTitleEllipsize", "overrideTvUI", "getOverrideTvUI", "setOverrideTvUI", "remoteAccessEnabled", "Landroidx/lifecycle/MutableLiveData;", "kotlin.jvm.PlatformType", "getRemoteAccessEnabled", "()Landroidx/lifecycle/MutableLiveData;", "setRemoteAccessEnabled", "(Landroidx/lifecycle/MutableLiveData;)V", "safeMode", "getSafeMode", "setSafeMode", "showAudioTrackInfo", "getShowAudioTrackInfo", "setShowAudioTrackInfo", "showHeaders", "getShowHeaders", "setShowHeaders", "showHiddenFiles", "getShowHiddenFiles", "setShowHiddenFiles", "showTrackNumber", "getShowTrackNumber", "setShowTrackNumber", "showTvUi", "getShowTvUi", "showVideoThumbs", "getShowVideoThumbs", "setShowVideoThumbs", "tvFoldersFirst", "getTvFoldersFirst", "setTvFoldersFirst", "tvUI", "getTvUI", "setTvUI", "videoDoubleTapJumpDelay", "getVideoDoubleTapJumpDelay", "setVideoDoubleTapJumpDelay", "videoHudDelay", "getVideoHudDelay", "setVideoHudDelay", "videoJumpDelay", "getVideoJumpDelay", "setVideoJumpDelay", "videoLongJumpDelay", "getVideoLongJumpDelay", "setVideoLongJumpDelay", "init", "context", "onAudioControlsChanged", "removeAudioControlsChangeListener", "setAudioControlsChangeListener", "listener", "isPinCodeSet", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Settings.kt */
public final class Settings extends SingletonHolder<SharedPreferences, Context> {
    public static final Settings INSTANCE = new Settings();
    private static Function0<Unit> audioControlsChangeListener;
    private static int audioJumpDelay = 10;
    private static int audioLongJumpDelay = 20;
    private static DeviceInfo device;
    private static float fastplaySpeed = 2.0f;
    private static boolean firstRun;
    private static boolean includeMissing = true;
    private static boolean incognitoMode;
    private static int listTitleEllipsize;
    private static boolean overrideTvUI;
    private static MutableLiveData<Boolean> remoteAccessEnabled = new MutableLiveData<>(false);
    private static boolean safeMode;
    private static boolean showAudioTrackInfo;
    private static boolean showHeaders = true;
    private static boolean showHiddenFiles;
    private static boolean showTrackNumber = true;
    private static boolean showVideoThumbs = true;
    private static boolean tvFoldersFirst = true;
    private static boolean tvUI;
    private static int videoDoubleTapJumpDelay = 10;
    private static int videoHudDelay = 2;
    private static int videoJumpDelay = 10;
    private static int videoLongJumpDelay = 20;

    private Settings() {
        super(AnonymousClass1.INSTANCE);
    }

    public final boolean getFirstRun() {
        return firstRun;
    }

    public final void setFirstRun(boolean z) {
        firstRun = z;
    }

    public final boolean getShowVideoThumbs() {
        return showVideoThumbs;
    }

    public final void setShowVideoThumbs(boolean z) {
        showVideoThumbs = z;
    }

    public final boolean getTvUI() {
        return tvUI;
    }

    public final void setTvUI(boolean z) {
        tvUI = z;
    }

    public final int getListTitleEllipsize() {
        return listTitleEllipsize;
    }

    public final void setListTitleEllipsize(int i) {
        listTitleEllipsize = i;
    }

    public final boolean getOverrideTvUI() {
        return overrideTvUI;
    }

    public final void setOverrideTvUI(boolean z) {
        overrideTvUI = z;
    }

    public final int getVideoHudDelay() {
        return videoHudDelay;
    }

    public final void setVideoHudDelay(int i) {
        videoHudDelay = i;
    }

    public final boolean getIncludeMissing() {
        return includeMissing;
    }

    public final void setIncludeMissing(boolean z) {
        includeMissing = z;
    }

    public final boolean getShowHeaders() {
        return showHeaders;
    }

    public final void setShowHeaders(boolean z) {
        showHeaders = z;
    }

    public final boolean getShowAudioTrackInfo() {
        return showAudioTrackInfo;
    }

    public final void setShowAudioTrackInfo(boolean z) {
        showAudioTrackInfo = z;
    }

    public final int getVideoJumpDelay() {
        return videoJumpDelay;
    }

    public final void setVideoJumpDelay(int i) {
        videoJumpDelay = i;
    }

    public final int getVideoLongJumpDelay() {
        return videoLongJumpDelay;
    }

    public final void setVideoLongJumpDelay(int i) {
        videoLongJumpDelay = i;
    }

    public final int getVideoDoubleTapJumpDelay() {
        return videoDoubleTapJumpDelay;
    }

    public final void setVideoDoubleTapJumpDelay(int i) {
        videoDoubleTapJumpDelay = i;
    }

    public final int getAudioJumpDelay() {
        return audioJumpDelay;
    }

    public final void setAudioJumpDelay(int i) {
        audioJumpDelay = i;
    }

    public final int getAudioLongJumpDelay() {
        return audioLongJumpDelay;
    }

    public final void setAudioLongJumpDelay(int i) {
        audioLongJumpDelay = i;
    }

    public final boolean getShowHiddenFiles() {
        return showHiddenFiles;
    }

    public final void setShowHiddenFiles(boolean z) {
        showHiddenFiles = z;
    }

    public final boolean getShowTrackNumber() {
        return showTrackNumber;
    }

    public final void setShowTrackNumber(boolean z) {
        showTrackNumber = z;
    }

    public final boolean getTvFoldersFirst() {
        return tvFoldersFirst;
    }

    public final void setTvFoldersFirst(boolean z) {
        tvFoldersFirst = z;
    }

    public final boolean getIncognitoMode() {
        return incognitoMode;
    }

    public final void setIncognitoMode(boolean z) {
        incognitoMode = z;
    }

    public final boolean getSafeMode() {
        return safeMode;
    }

    public final void setSafeMode(boolean z) {
        safeMode = z;
    }

    public final MutableLiveData<Boolean> getRemoteAccessEnabled() {
        return remoteAccessEnabled;
    }

    public final void setRemoteAccessEnabled(MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        remoteAccessEnabled = mutableLiveData;
    }

    public final float getFastplaySpeed() {
        return fastplaySpeed;
    }

    public final void setFastplaySpeed(float f) {
        fastplaySpeed = f;
    }

    public final DeviceInfo getDevice() {
        DeviceInfo deviceInfo = device;
        if (deviceInfo != null) {
            return deviceInfo;
        }
        Intrinsics.throwUninitializedPropertyAccessException("device");
        return null;
    }

    public final SharedPreferences init(Context context) {
        String string;
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean z = true;
        showVideoThumbs = defaultSharedPreferences.getBoolean(SettingsKt.SHOW_VIDEO_THUMBNAILS, true);
        tvUI = defaultSharedPreferences.getBoolean(SettingsKt.PREF_TV_UI, false);
        String string2 = defaultSharedPreferences.getString(SettingsKt.LIST_TITLE_ELLIPSIZE, Constants.GROUP_VIDEOS_FOLDER);
        listTitleEllipsize = string2 != null ? Integer.parseInt(string2) : 0;
        videoHudDelay = SettingsKt.coerceInOrDefault(defaultSharedPreferences.getInt(SettingsKt.VIDEO_HUD_TIMEOUT, 4), 1, 15, -1);
        device = new DeviceInfo(context);
        includeMissing = defaultSharedPreferences.getBoolean(SettingsKt.KEY_INCLUDE_MISSING, true);
        showHeaders = defaultSharedPreferences.getBoolean(SettingsKt.KEY_SHOW_HEADERS, true);
        showAudioTrackInfo = defaultSharedPreferences.getBoolean(SettingsKt.KEY_SHOW_TRACK_INFO, false);
        videoJumpDelay = defaultSharedPreferences.getInt(SettingsKt.KEY_VIDEO_JUMP_DELAY, 10);
        videoLongJumpDelay = defaultSharedPreferences.getInt(SettingsKt.KEY_VIDEO_LONG_JUMP_DELAY, 20);
        videoDoubleTapJumpDelay = defaultSharedPreferences.getInt(SettingsKt.KEY_VIDEO_DOUBLE_TAP_JUMP_DELAY, 10);
        audioJumpDelay = defaultSharedPreferences.getInt(SettingsKt.KEY_AUDIO_JUMP_DELAY, 10);
        audioLongJumpDelay = defaultSharedPreferences.getInt(SettingsKt.KEY_AUDIO_LONG_JUMP_DELAY, 20);
        showHiddenFiles = defaultSharedPreferences.getBoolean(SettingsKt.BROWSER_SHOW_HIDDEN_FILES, !tvUI);
        showTrackNumber = defaultSharedPreferences.getBoolean(SettingsKt.ALBUMS_SHOW_TRACK_NUMBER, true);
        tvFoldersFirst = defaultSharedPreferences.getBoolean(SettingsKt.TV_FOLDERS_FIRST, true);
        incognitoMode = defaultSharedPreferences.getBoolean(SettingsKt.KEY_INCOGNITO, false);
        if (!defaultSharedPreferences.getBoolean(SettingsKt.KEY_SAFE_MODE, false) || (string = defaultSharedPreferences.getString(SettingsKt.KEY_SAFE_MODE_PIN, "")) == null || !(!StringsKt.isBlank(string))) {
            z = false;
        }
        safeMode = z;
        remoteAccessEnabled.postValue(Boolean.valueOf(defaultSharedPreferences.getBoolean(SettingsKt.KEY_ENABLE_REMOTE_ACCESS, false)));
        String string3 = defaultSharedPreferences.getString(SettingsKt.FASTPLAY_SPEED, "2");
        fastplaySpeed = string3 != null ? Float.parseFloat(string3) : 2.0f;
        Intrinsics.checkNotNull(defaultSharedPreferences);
        return defaultSharedPreferences;
    }

    public final boolean isPinCodeSet(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        String string = ((SharedPreferences) INSTANCE.getInstance(context)).getString(SettingsKt.KEY_SAFE_MODE_PIN, "");
        return string != null && (StringsKt.isBlank(string) ^ true);
    }

    public final void onAudioControlsChanged() {
        Function0<Unit> function0 = audioControlsChangeListener;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final void setAudioControlsChangeListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "listener");
        audioControlsChangeListener = function0;
    }

    public final void removeAudioControlsChangeListener() {
        audioControlsChangeListener = null;
    }

    public final boolean getShowTvUi() {
        return (!overrideTvUI && getDevice().isTv()) || tvUI;
    }
}
