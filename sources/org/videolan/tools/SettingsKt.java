package org.videolan.tools;

import android.content.SharedPreferences;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000e\n\u0002\bP\n\u0002\u0010\b\n\u0002\b;\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u001a&\u0010\u0001\u001a\u00020R*\u00020R2\u0007\u0010\u0001\u001a\u00020R2\u0007\u0010\u0001\u001a\u00020R2\u0007\u0010\u0001\u001a\u00020R\u001a \u0010\u0001\u001a\u00030\u0001*\u00030\u00012\u0007\u0010\u0001\u001a\u00020\u00012\b\u0010\u0001\u001a\u00030\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u001f\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010 \u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010!\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\"\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010#\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010$\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010%\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010&\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010'\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010(\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010)\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010*\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010+\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010,\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010-\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010.\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010/\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u00100\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u00101\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u00102\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u00103\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u00104\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u00105\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u00106\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u00107\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u00108\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u00109\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010:\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010;\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010<\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010=\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010>\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010?\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010@\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010A\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010B\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010C\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010D\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010E\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010F\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010G\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010H\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010I\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010J\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010K\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010L\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010M\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010N\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010O\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010P\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010Q\u001a\u00020RXT¢\u0006\u0002\n\u0000\"\u000e\u0010S\u001a\u00020RXT¢\u0006\u0002\n\u0000\"\u000e\u0010T\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010U\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010V\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010W\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010X\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010Y\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010Z\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010[\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\\\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010]\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010^\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010_\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010`\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010a\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010c\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010d\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010e\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010f\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010g\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010h\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010i\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010j\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010k\u001a\u00020RXT¢\u0006\u0002\n\u0000\"\u000e\u0010l\u001a\u00020RXT¢\u0006\u0002\n\u0000\"\u000e\u0010m\u001a\u00020RXT¢\u0006\u0002\n\u0000\"\u000e\u0010n\u001a\u00020RXT¢\u0006\u0002\n\u0000\"\u000e\u0010o\u001a\u00020RXT¢\u0006\u0002\n\u0000\"\u000e\u0010p\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010q\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010r\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010s\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010t\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010u\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010v\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010w\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010x\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010y\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010z\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010{\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010|\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010}\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010~\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000f\u0010\u0001\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000f\u0010\u0001\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000f\u0010\u0001\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000f\u0010\u0001\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000f\u0010\u0001\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000f\u0010\u0001\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000f\u0010\u0001\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000f\u0010\u0001\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000f\u0010\u0001\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0001"}, d2 = {"ALBUMS_SHOW_TRACK_NUMBER", "", "ALLOW_FOLD_AUTO_LAYOUT", "AUDIO_BOOST", "AUDIO_DELAY_GLOBAL", "AUDIO_DUCKING", "AUDIO_HINGE_ON_RIGHT", "AUDIO_PLAY_PROGRESS_MODE", "AUDIO_PREFERRED_LANGUAGE", "AUDIO_RESUME_PLAYBACK", "AUDIO_SHUFFLING", "AUDIO_STOP_AFTER", "BETA_WELCOME", "BRIGHTNESS_VALUE", "BROWSER_DISPLAY_IN_CARDS", "BROWSER_SHOW_HIDDEN_FILES", "BROWSER_SHOW_ONLY_MULTIMEDIA", "CRASH_DONT_ASK_AGAIN", "CUSTOM_POPUP_HEIGHT", "DAV1D_THREAD_NUMBER", "DISPLAY_UNDER_NOTCH", "ENABLE_ANDROID_AUTO_SEEK_BUTTONS", "ENABLE_ANDROID_AUTO_SPEED_BUTTONS", "ENABLE_BRIGHTNESS_GESTURE", "ENABLE_DOUBLE_TAP_PLAY", "ENABLE_DOUBLE_TAP_SEEK", "ENABLE_FASTPLAY", "ENABLE_SCALE_GESTURE", "ENABLE_SEEK_BUTTONS", "ENABLE_SWIPE_SEEK", "ENABLE_VOLUME_GESTURE", "ENCRYPTED_KEY_NAME", "FASTPLAY_SPEED", "FORCE_PLAY_ALL_AUDIO", "FORCE_PLAY_ALL_VIDEO", "HINGE_ON_RIGHT", "HTTP_USER_AGENT", "INITIAL_PERMISSION_ASKED", "KEYSTORE_PASSWORD", "KEYSTORE_PASSWORD_IV", "KEY_APP_THEME", "KEY_ARTISTS_SHOW_ALL", "KEY_AUDIO_CONFIRM_RESUME", "KEY_AUDIO_FORCE_SHUFFLE", "KEY_AUDIO_JUMP_DELAY", "KEY_AUDIO_LONG_JUMP_DELAY", "KEY_BLACK_THEME", "KEY_CURRENT_MAJOR_VERSION", "KEY_CURRENT_SETTINGS_VERSION", "KEY_DAYNIGHT", "KEY_ENABLE_REMOTE_ACCESS", "KEY_INCLUDE_MISSING", "KEY_INCOGNITO", "KEY_LAST_UPDATE_TIME", "KEY_LAST_WHATS_NEW", "KEY_MEDIALIBRARY_AUTO_RESCAN", "KEY_MEDIALIBRARY_SCAN", "KEY_PLAYBACK_RATE", "KEY_PLAYBACK_RATE_VIDEO", "KEY_PLAYBACK_SPEED_PERSIST", "KEY_PLAYBACK_SPEED_PERSIST_VIDEO", "KEY_REMOTE_ACCESS_ML_CONTENT", "KEY_RESTRICT_SETTINGS", "KEY_SAFE_MODE", "KEY_SAFE_MODE_PIN", "KEY_SHOW_HEADERS", "KEY_SHOW_TRACK_INFO", "KEY_SHOW_UPDATE", "KEY_SHOW_WHATS_NEW", "KEY_TV_ONBOARDING_DONE", "KEY_VIDEO_APP_SWITCH", "KEY_VIDEO_CONFIRM_RESUME", "KEY_VIDEO_DOUBLE_TAP_JUMP_DELAY", "KEY_VIDEO_JUMP_DELAY", "KEY_VIDEO_LONG_JUMP_DELAY", "LAST_LOCK_ORIENTATION", "LIST_TITLE_ELLIPSIZE", "LOCKSCREEN_COVER", "LOCK_USE_SENSOR", "LOGIN_STORE", "MEDIA_SHUFFLING", "ML_SCAN_OFF", "", "ML_SCAN_ON", "NOTIFICATION_PERMISSION_ASKED", "PERMISSION_NEVER_ASK", "PERMISSION_NEXT_ASK", "PLAYBACK_HISTORY", "PLAYLIST_REPLACE", "POPUP_FORCE_LEGACY", "POPUP_KEEPSCREEN", "POSITION_IN_AUDIO_LIST", "POSITION_IN_MEDIA", "POSITION_IN_MEDIA_LIST", "POSITION_IN_SONG", "PREF_AUDIOPLAYER_TIPS_SHOWN", "PREF_PLAYLIST_TIPS_SHOWN", "PREF_RESTORE_VIDEO_TIPS_SHOWN", "PREF_TIPS_SHOWN", "PREF_TV_UI", "PREF_WIDGETS_TIPS_SHOWN", "REMOTE_ACCESS_FILE_BROWSER_CONTENT", "REMOTE_ACCESS_HISTORY_CONTENT", "REMOTE_ACCESS_LOGS", "REMOTE_ACCESS_NETWORK_BROWSER_CONTENT", "REMOTE_ACCESS_PLAYBACK_CONTROL", "RESTORE_BACKGROUND_VIDEO", "RESULT_RESCAN", "RESULT_RESTART", "RESULT_RESTART_APP", "RESULT_UPDATE_ARTISTS", "RESULT_UPDATE_SEEN_MEDIA", "RESUME_PLAYBACK", "SAVE_BRIGHTNESS", "SCREENSHOT_MODE", "SCREEN_ORIENTATION", "SHOW_REMAINING_TIME", "SHOW_SEEK_IN_COMPACT_NOTIFICATION", "SHOW_VIDEO_THUMBNAILS", "SLEEP_TIMER_DEFAULT_INTERVAL", "SLEEP_TIMER_DEFAULT_RESET_INTERACTION", "SLEEP_TIMER_DEFAULT_WAIT", "SLEEP_TIMER_RESET_INTERACTION", "SLEEP_TIMER_WAIT", "SUBTITLE_PREFERRED_LANGUAGE", "TV_FOLDERS_FIRST", "VIDEO_HUD_TIMEOUT", "VIDEO_PAUSED", "VIDEO_RATIO", "VIDEO_RESUME_PLAYBACK", "VIDEO_RESUME_TIME", "VIDEO_RESUME_URI", "VIDEO_SPEED", "VIDEO_TRANSITION_SHOW", "WIDGETS_BACKGROUND_LAST_COLORS", "WIDGETS_FOREGROUND_LAST_COLORS", "WIDGETS_PREVIEW_PLAYING", "coerceInOrDefault", "min", "max", "defautValue", "putSingle", "", "Landroid/content/SharedPreferences;", "key", "value", "", "tools_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: Settings.kt */
public final class SettingsKt {
    public static final String ALBUMS_SHOW_TRACK_NUMBER = "albums_show_track_number";
    public static final String ALLOW_FOLD_AUTO_LAYOUT = "allow_fold_auto_layout";
    public static final String AUDIO_BOOST = "audio_boost";
    public static final String AUDIO_DELAY_GLOBAL = "audio_delay_global";
    public static final String AUDIO_DUCKING = "audio_ducking";
    public static final String AUDIO_HINGE_ON_RIGHT = "audio_hinge_on_right";
    public static final String AUDIO_PLAY_PROGRESS_MODE = "audio_play_progress_mode";
    public static final String AUDIO_PREFERRED_LANGUAGE = "audio_preferred_language";
    public static final String AUDIO_RESUME_PLAYBACK = "audio_resume_playback";
    public static final String AUDIO_SHUFFLING = "audio_shuffling";
    public static final String AUDIO_STOP_AFTER = "audio_stop_after";
    public static final String BETA_WELCOME = "beta_welcome";
    public static final String BRIGHTNESS_VALUE = "brightness_value";
    public static final String BROWSER_DISPLAY_IN_CARDS = "browser_display_in_cards";
    public static final String BROWSER_SHOW_HIDDEN_FILES = "browser_show_hidden_files";
    public static final String BROWSER_SHOW_ONLY_MULTIMEDIA = "browser_show_only_multimedia";
    public static final String CRASH_DONT_ASK_AGAIN = "crash_dont_ask_again";
    public static final String CUSTOM_POPUP_HEIGHT = "custom_popup_height";
    public static final String DAV1D_THREAD_NUMBER = "dav1d_thread_number";
    public static final String DISPLAY_UNDER_NOTCH = "display_under_notch";
    public static final String ENABLE_ANDROID_AUTO_SEEK_BUTTONS = "enable_android_auto_seek_buttons";
    public static final String ENABLE_ANDROID_AUTO_SPEED_BUTTONS = "enable_android_auto_speed_buttons";
    public static final String ENABLE_BRIGHTNESS_GESTURE = "enable_brightness_gesture";
    public static final String ENABLE_DOUBLE_TAP_PLAY = "enable_double_tap_play";
    public static final String ENABLE_DOUBLE_TAP_SEEK = "enable_double_tap_seek";
    public static final String ENABLE_FASTPLAY = "enable_fastplay";
    public static final String ENABLE_SCALE_GESTURE = "enable_scale_gesture";
    public static final String ENABLE_SEEK_BUTTONS = "enable_seek_buttons";
    public static final String ENABLE_SWIPE_SEEK = "enable_swipe_seek";
    public static final String ENABLE_VOLUME_GESTURE = "enable_volume_gesture";
    public static final String ENCRYPTED_KEY_NAME = "encryption_key";
    public static final String FASTPLAY_SPEED = "fastplay_speed";
    public static final String FORCE_PLAY_ALL_AUDIO = "force_play_all_audio";
    public static final String FORCE_PLAY_ALL_VIDEO = "force_play_all_video";
    public static final String HINGE_ON_RIGHT = "hinge_on_right";
    public static final String HTTP_USER_AGENT = "http_user_agent";
    public static final String INITIAL_PERMISSION_ASKED = "initial_permission_asked";
    public static final String KEYSTORE_PASSWORD = "keystore_encrypted_password";
    public static final String KEYSTORE_PASSWORD_IV = "keystore_encrypted_password_iv";
    public static final String KEY_APP_THEME = "app_theme";
    public static final String KEY_ARTISTS_SHOW_ALL = "artists_show_all";
    public static final String KEY_AUDIO_CONFIRM_RESUME = "audio_confirm_resume";
    public static final String KEY_AUDIO_FORCE_SHUFFLE = "audio_force_shuffle";
    public static final String KEY_AUDIO_JUMP_DELAY = "audio_jump_delay";
    public static final String KEY_AUDIO_LONG_JUMP_DELAY = "audio_long_jump_delay";
    public static final String KEY_BLACK_THEME = "enable_black_theme";
    public static final String KEY_CURRENT_MAJOR_VERSION = "key_current_major_version";
    public static final String KEY_CURRENT_SETTINGS_VERSION = "current_settings_version";
    public static final String KEY_DAYNIGHT = "daynight";
    public static final String KEY_ENABLE_REMOTE_ACCESS = "enable_remote_access";
    public static final String KEY_INCLUDE_MISSING = "include_missing";
    public static final String KEY_INCOGNITO = "incognito_mode";
    public static final String KEY_LAST_UPDATE_TIME = "last_update_time";
    public static final String KEY_LAST_WHATS_NEW = "last_whats_new";
    public static final String KEY_MEDIALIBRARY_AUTO_RESCAN = "auto_rescan";
    public static final String KEY_MEDIALIBRARY_SCAN = "ml_scan";
    public static final String KEY_PLAYBACK_RATE = "playback_rate";
    public static final String KEY_PLAYBACK_RATE_VIDEO = "playback_rate_video";
    public static final String KEY_PLAYBACK_SPEED_PERSIST = "playback_speed";
    public static final String KEY_PLAYBACK_SPEED_PERSIST_VIDEO = "playback_speed_video";
    public static final String KEY_REMOTE_ACCESS_ML_CONTENT = "remote_access_medialibrary_content";
    public static final String KEY_RESTRICT_SETTINGS = "restrict_settings";
    public static final String KEY_SAFE_MODE = "safe_mode";
    public static final String KEY_SAFE_MODE_PIN = "safe_mode_pin";
    public static final String KEY_SHOW_HEADERS = "show_headers";
    public static final String KEY_SHOW_TRACK_INFO = "show_track_info";
    public static final String KEY_SHOW_UPDATE = "show_update";
    public static final String KEY_SHOW_WHATS_NEW = "show_whats_new";
    public static final String KEY_TV_ONBOARDING_DONE = "key_tv_onboarding_done";
    public static final String KEY_VIDEO_APP_SWITCH = "video_action_switch";
    public static final String KEY_VIDEO_CONFIRM_RESUME = "video_confirm_resume";
    public static final String KEY_VIDEO_DOUBLE_TAP_JUMP_DELAY = "video_double_tap_jump_delay";
    public static final String KEY_VIDEO_JUMP_DELAY = "video_jump_delay";
    public static final String KEY_VIDEO_LONG_JUMP_DELAY = "video_long_jump_delay";
    public static final String LAST_LOCK_ORIENTATION = "last_lock_orientation";
    public static final String LIST_TITLE_ELLIPSIZE = "list_title_ellipsize";
    public static final String LOCKSCREEN_COVER = "lockscreen_cover";
    public static final String LOCK_USE_SENSOR = "lock_use_sensor";
    public static final String LOGIN_STORE = "store_login";
    public static final String MEDIA_SHUFFLING = "media_shuffling";
    public static final int ML_SCAN_OFF = 1;
    public static final int ML_SCAN_ON = 0;
    public static final String NOTIFICATION_PERMISSION_ASKED = "notification_permission_asked";
    public static final String PERMISSION_NEVER_ASK = "permission_never_ask";
    public static final String PERMISSION_NEXT_ASK = "permission_next_ask";
    public static final String PLAYBACK_HISTORY = "playback_history";
    public static final String PLAYLIST_REPLACE = "playlist_replace";
    public static final String POPUP_FORCE_LEGACY = "popup_force_legacy";
    public static final String POPUP_KEEPSCREEN = "popup_keepscreen";
    public static final String POSITION_IN_AUDIO_LIST = "position_in_audio_list";
    public static final String POSITION_IN_MEDIA = "position_in_media";
    public static final String POSITION_IN_MEDIA_LIST = "position_in_media_list";
    public static final String POSITION_IN_SONG = "position_in_song";
    public static final String PREF_AUDIOPLAYER_TIPS_SHOWN = "audioplayer_tips_shown";
    public static final String PREF_PLAYLIST_TIPS_SHOWN = "playlist_tips_shown";
    public static final String PREF_RESTORE_VIDEO_TIPS_SHOWN = "pref_restore_video_tips_shown";
    public static final String PREF_TIPS_SHOWN = "video_player_tips_shown";
    public static final String PREF_TV_UI = "tv_ui";
    public static final String PREF_WIDGETS_TIPS_SHOWN = "widgets_tips_shown";
    public static final String REMOTE_ACCESS_FILE_BROWSER_CONTENT = "remote_access_file_browser_content";
    public static final String REMOTE_ACCESS_HISTORY_CONTENT = "remote_access_history_content";
    public static final String REMOTE_ACCESS_LOGS = "remote_access_logs";
    public static final String REMOTE_ACCESS_NETWORK_BROWSER_CONTENT = "remote_access_network_browser_content";
    public static final String REMOTE_ACCESS_PLAYBACK_CONTROL = "remote_access_playback_control";
    public static final String RESTORE_BACKGROUND_VIDEO = "restore_background_video";
    public static final int RESULT_RESCAN = 2;
    public static final int RESULT_RESTART = 3;
    public static final int RESULT_RESTART_APP = 4;
    public static final int RESULT_UPDATE_ARTISTS = 6;
    public static final int RESULT_UPDATE_SEEN_MEDIA = 5;
    public static final String RESUME_PLAYBACK = "resume_playback";
    public static final String SAVE_BRIGHTNESS = "save_brightness";
    public static final String SCREENSHOT_MODE = "screenshot_mode";
    public static final String SCREEN_ORIENTATION = "screen_orientation";
    public static final String SHOW_REMAINING_TIME = "show_remaining_time";
    public static final String SHOW_SEEK_IN_COMPACT_NOTIFICATION = "show_seek_in_compact_notification";
    public static final String SHOW_VIDEO_THUMBNAILS = "show_video_thumbnails";
    public static final String SLEEP_TIMER_DEFAULT_INTERVAL = "sleep_timer_default_interval";
    public static final String SLEEP_TIMER_DEFAULT_RESET_INTERACTION = "sleep_timer_default_reset_interaction";
    public static final String SLEEP_TIMER_DEFAULT_WAIT = "sleep_timer_default_wait";
    public static final String SLEEP_TIMER_RESET_INTERACTION = "sleep_timer_reset_interaction";
    public static final String SLEEP_TIMER_WAIT = "sleep_timer_wait";
    public static final String SUBTITLE_PREFERRED_LANGUAGE = "subtitle_preferred_language";
    public static final String TV_FOLDERS_FIRST = "tv_folders_first";
    public static final String VIDEO_HUD_TIMEOUT = "video_hud_timeout_in_s";
    public static final String VIDEO_PAUSED = "VideoPaused";
    public static final String VIDEO_RATIO = "video_ratio";
    public static final String VIDEO_RESUME_PLAYBACK = "video_resume_playback";
    public static final String VIDEO_RESUME_TIME = "VideoResumeTime";
    public static final String VIDEO_RESUME_URI = "VideoResumeUri";
    public static final String VIDEO_SPEED = "VideoSpeed";
    public static final String VIDEO_TRANSITION_SHOW = "video_transition_show";
    public static final String WIDGETS_BACKGROUND_LAST_COLORS = "widgets_background_last_colors";
    public static final String WIDGETS_FOREGROUND_LAST_COLORS = "widgets_foreground_last_colors";
    public static final String WIDGETS_PREVIEW_PLAYING = "widgets_preview_playing";

    public static final int coerceInOrDefault(int i, int i2, int i3, int i4) {
        return (i < i2 || i > i3) ? i4 : i;
    }

    public static final void putSingle(SharedPreferences sharedPreferences, String str, Object obj) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<this>");
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(obj, "value");
        if (obj instanceof Boolean) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean(str, ((Boolean) obj).booleanValue());
            edit.apply();
        } else if (obj instanceof Integer) {
            SharedPreferences.Editor edit2 = sharedPreferences.edit();
            edit2.putInt(str, ((Number) obj).intValue());
            edit2.apply();
        } else if (obj instanceof Float) {
            SharedPreferences.Editor edit3 = sharedPreferences.edit();
            edit3.putFloat(str, ((Number) obj).floatValue());
            edit3.apply();
        } else if (obj instanceof Long) {
            SharedPreferences.Editor edit4 = sharedPreferences.edit();
            edit4.putLong(str, ((Number) obj).longValue());
            edit4.apply();
        } else if (obj instanceof String) {
            SharedPreferences.Editor edit5 = sharedPreferences.edit();
            edit5.putString(str, (String) obj);
            edit5.apply();
        } else if (obj instanceof List) {
            SharedPreferences.Editor edit6 = sharedPreferences.edit();
            Set set = CollectionsKt.toSet((Iterable) obj);
            Intrinsics.checkNotNull(set, "null cannot be cast to non-null type kotlin.collections.Set<kotlin.String>");
            edit6.putStringSet(str, set);
            edit6.apply();
        } else {
            throw new IllegalArgumentException("value class is invalid!");
        }
    }
}
