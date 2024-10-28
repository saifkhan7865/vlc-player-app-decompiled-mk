package org.videolan.vlc.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.MainVersionKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H@¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u0019\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010\u001a\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H@¢\u0006\u0002\u0010\u0011R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001b"}, d2 = {"Lorg/videolan/vlc/util/VersionMigration;", "", "()V", "currentMajorVersion", "", "getCurrentMajorVersion", "()I", "migrateToVersion1", "", "settings", "Landroid/content/SharedPreferences;", "migrateToVersion10", "migrateToVersion11", "migrateToVersion12", "migrateToVersion2", "context", "Landroid/content/Context;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "migrateToVersion3", "migrateToVersion4", "migrateToVersion5", "migrateToVersion6", "migrateToVersion7", "migrateToVersion8", "migrateToVersion9", "migrateToVlc4", "migrateVersion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VersionMigration.kt */
public final class VersionMigration {
    public static final VersionMigration INSTANCE = new VersionMigration();
    private static final int currentMajorVersion = (MainVersionKt.isVLC4() ? 4 : 3);

    private VersionMigration() {
    }

    public final int getCurrentMajorVersion() {
        return currentMajorVersion;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object migrateVersion(android.content.Context r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof org.videolan.vlc.util.VersionMigration$migrateVersion$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            org.videolan.vlc.util.VersionMigration$migrateVersion$1 r0 = (org.videolan.vlc.util.VersionMigration$migrateVersion$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.util.VersionMigration$migrateVersion$1 r0 = new org.videolan.vlc.util.VersionMigration$migrateVersion$1
            r0.<init>(r11, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "key_current_major_version"
            java.lang.String r4 = "current_settings_version"
            r5 = 3
            r6 = 2
            r7 = 1
            if (r2 == 0) goto L_0x005e
            if (r2 == r7) goto L_0x0047
            if (r2 != r6) goto L_0x003f
            int r12 = r0.I$1
            int r1 = r0.I$0
            java.lang.Object r2 = r0.L$1
            android.content.SharedPreferences r2 = (android.content.SharedPreferences) r2
            java.lang.Object r0 = r0.L$0
            org.videolan.vlc.util.VersionMigration r0 = (org.videolan.vlc.util.VersionMigration) r0
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x00ab
        L_0x003f:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0047:
            int r12 = r0.I$1
            int r2 = r0.I$0
            java.lang.Object r7 = r0.L$2
            android.content.SharedPreferences r7 = (android.content.SharedPreferences) r7
            java.lang.Object r8 = r0.L$1
            android.content.Context r8 = (android.content.Context) r8
            java.lang.Object r9 = r0.L$0
            org.videolan.vlc.util.VersionMigration r9 = (org.videolan.vlc.util.VersionMigration) r9
            kotlin.ResultKt.throwOnFailure(r13)
            r10 = r8
            r8 = r12
            r12 = r10
            goto L_0x008e
        L_0x005e:
            kotlin.ResultKt.throwOnFailure(r13)
            org.videolan.tools.Settings r13 = org.videolan.tools.Settings.INSTANCE
            java.lang.Object r13 = r13.getInstance(r12)
            android.content.SharedPreferences r13 = (android.content.SharedPreferences) r13
            r2 = 0
            int r2 = r13.getInt(r4, r2)
            int r8 = r13.getInt(r3, r5)
            if (r2 >= r7) goto L_0x0077
            r11.migrateToVersion1(r13)
        L_0x0077:
            if (r2 >= r6) goto L_0x0090
            r0.L$0 = r11
            r0.L$1 = r12
            r0.L$2 = r13
            r0.I$0 = r2
            r0.I$1 = r8
            r0.label = r7
            java.lang.Object r7 = r11.migrateToVersion2(r12, r0)
            if (r7 != r1) goto L_0x008c
            return r1
        L_0x008c:
            r9 = r11
            r7 = r13
        L_0x008e:
            r13 = r7
            goto L_0x0091
        L_0x0090:
            r9 = r11
        L_0x0091:
            if (r2 >= r5) goto L_0x00af
            r0.L$0 = r9
            r0.L$1 = r13
            r7 = 0
            r0.L$2 = r7
            r0.I$0 = r2
            r0.I$1 = r8
            r0.label = r6
            java.lang.Object r12 = r9.migrateToVersion3(r12, r0)
            if (r12 != r1) goto L_0x00a7
            return r1
        L_0x00a7:
            r1 = r2
            r12 = r8
            r0 = r9
            r2 = r13
        L_0x00ab:
            r8 = r12
            r9 = r0
            r13 = r2
            r2 = r1
        L_0x00af:
            r12 = 4
            if (r2 >= r12) goto L_0x00b5
            r9.migrateToVersion4(r13)
        L_0x00b5:
            r0 = 5
            if (r2 >= r0) goto L_0x00bb
            r9.migrateToVersion5(r13)
        L_0x00bb:
            r0 = 6
            if (r2 >= r0) goto L_0x00c1
            r9.migrateToVersion6(r13)
        L_0x00c1:
            r0 = 7
            if (r2 >= r0) goto L_0x00c7
            r9.migrateToVersion7(r13)
        L_0x00c7:
            r0 = 8
            if (r2 >= r0) goto L_0x00ce
            r9.migrateToVersion8(r13)
        L_0x00ce:
            r0 = 9
            if (r2 >= r0) goto L_0x00d5
            r9.migrateToVersion9(r13)
        L_0x00d5:
            r0 = 10
            if (r2 >= r0) goto L_0x00dc
            r9.migrateToVersion10(r13)
        L_0x00dc:
            r0 = 11
            if (r2 >= r0) goto L_0x00e3
            r9.migrateToVersion11(r13)
        L_0x00e3:
            r0 = 12
            if (r2 >= r0) goto L_0x00ea
            r9.migrateToVersion12(r13)
        L_0x00ea:
            if (r8 != r5) goto L_0x00f3
            int r1 = currentMajorVersion
            if (r1 != r12) goto L_0x00f3
            r9.migrateToVlc4(r13)
        L_0x00f3:
            java.lang.Integer r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)
            org.videolan.tools.SettingsKt.putSingle(r13, r4, r12)
            int r12 = currentMajorVersion
            java.lang.Integer r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r12)
            org.videolan.tools.SettingsKt.putSingle(r13, r3, r12)
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.VersionMigration.migrateVersion(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void migrateToVersion1(SharedPreferences sharedPreferences) {
        Log.i(getClass().getSimpleName(), "Migrating preferences to Version 1");
        SharedPreferences.Editor edit = sharedPreferences.edit();
        int i = 0;
        if (sharedPreferences.getBoolean("dialog_confirm_resume", false)) {
            edit.putString(SettingsKt.KEY_VIDEO_CONFIRM_RESUME, "2");
        }
        edit.remove("dialog_confirm_resume");
        if (!sharedPreferences.contains(SettingsKt.KEY_APP_THEME)) {
            boolean z = sharedPreferences.getBoolean(SettingsKt.KEY_DAYNIGHT, false);
            if (sharedPreferences.getBoolean(SettingsKt.KEY_BLACK_THEME, false)) {
                i = 2;
            } else if (!z) {
                i = 1;
            }
            edit.putString(SettingsKt.KEY_APP_THEME, String.valueOf(i));
        }
        edit.remove(SettingsKt.KEY_DAYNIGHT);
        edit.remove(SettingsKt.KEY_BLACK_THEME);
        edit.apply();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object migrateToVersion2(android.content.Context r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof org.videolan.vlc.util.VersionMigration$migrateToVersion2$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            org.videolan.vlc.util.VersionMigration$migrateToVersion2$1 r0 = (org.videolan.vlc.util.VersionMigration$migrateToVersion2$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.util.VersionMigration$migrateToVersion2$1 r0 = new org.videolan.vlc.util.VersionMigration$migrateToVersion2$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L_0x003f
            if (r2 == r5) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00b2
        L_0x002f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0037:
            java.lang.Object r8 = r0.L$0
            android.content.Context r8 = (android.content.Context) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0067
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Class r9 = r7.getClass()
            java.lang.String r9 = r9.getSimpleName()
            java.lang.String r2 = "Migrating version to Version 2: flush all the video thumbnails"
            android.util.Log.i(r9, r2)
            kotlinx.coroutines.CoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            org.videolan.vlc.util.VersionMigration$migrateToVersion2$2 r2 = new org.videolan.vlc.util.VersionMigration$migrateToVersion2$2
            r2.<init>(r8, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r8
            r0.label = r5
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r9, r2, r0)
            if (r9 != r1) goto L_0x0067
            return r1
        L_0x0067:
            org.videolan.tools.Settings r9 = org.videolan.tools.Settings.INSTANCE
            java.lang.Object r9 = r9.getInstance(r8)
            android.content.SharedPreferences r9 = (android.content.SharedPreferences) r9
            java.lang.String r2 = "app_onboarding_done"
            r6 = 0
            boolean r2 = r9.getBoolean(r2, r6)
            r2 = r2 ^ r5
            org.videolan.resources.AndroidDevices r5 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r5 = r5.isAndroidTv()
            if (r5 != 0) goto L_0x00b5
            org.videolan.resources.AndroidDevices r5 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r5 = r5.isChromeBook()
            if (r5 != 0) goto L_0x008f
            org.videolan.resources.AndroidDevices r5 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r5 = r5.getHasTsp()
            if (r5 == 0) goto L_0x00b5
        L_0x008f:
            java.lang.String r5 = "tv_ui"
            boolean r9 = r9.getBoolean(r5, r6)
            if (r9 == 0) goto L_0x0098
            goto L_0x00b5
        L_0x0098:
            if (r2 != 0) goto L_0x00b5
            kotlinx.coroutines.CoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            org.videolan.vlc.util.VersionMigration$migrateToVersion2$$inlined$getFromMl$1 r2 = new org.videolan.vlc.util.VersionMigration$migrateToVersion2$$inlined$getFromMl$1
            r2.<init>(r8, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r9, r2, r0)
            if (r8 != r1) goto L_0x00b2
            return r1
        L_0x00b2:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x00b5:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.VersionMigration.migrateToVersion2(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final Object migrateToVersion3(Context context, Continuation<? super Unit> continuation) {
        Log.i(getClass().getSimpleName(), "Migrating to Version 3: remove all WatchNext programs");
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new VersionMigration$migrateToVersion3$2(context, (Continuation<? super VersionMigration$migrateToVersion3$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    private final void migrateToVersion4(SharedPreferences sharedPreferences) {
        Log.i(getClass().getSimpleName(), "Migrating to Version 4: migrate from video_hud_timeout to video_hud_timeout_in_s");
        String string = sharedPreferences.getString("video_hud_timeout", "2");
        int parseInt = string != null ? Integer.parseInt(string) : 2;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (parseInt < 0) {
            edit.putInt(SettingsKt.VIDEO_HUD_TIMEOUT, -1);
        } else if (parseInt == 2) {
            edit.putInt(SettingsKt.VIDEO_HUD_TIMEOUT, 4);
        } else if (parseInt == 3) {
            edit.putInt(SettingsKt.VIDEO_HUD_TIMEOUT, 8);
        } else {
            edit.putInt(SettingsKt.VIDEO_HUD_TIMEOUT, 2);
        }
        edit.remove("video_hud_timeout");
        edit.apply();
    }

    private final void migrateToVersion5(SharedPreferences sharedPreferences) {
        Log.i(getClass().getSimpleName(), "Migrating to Version 5: force the TV ui setting if device is TV");
        if (Settings.INSTANCE.getDevice().isTv() && sharedPreferences.getBoolean(SettingsKt.PREF_TV_UI, false) != sharedPreferences.getBoolean(SettingsKt.PREF_TV_UI, true)) {
            SettingsKt.putSingle(sharedPreferences, SettingsKt.PREF_TV_UI, true);
            Settings.INSTANCE.setTvUI(true);
        }
    }

    private final void migrateToVersion6(SharedPreferences sharedPreferences) {
        Log.i(getClass().getSimpleName(), "Migrating to Version 6: Migrate the Video hud timeout to the new range");
        if (sharedPreferences.getInt(SettingsKt.VIDEO_HUD_TIMEOUT, 4) == 0) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(SettingsKt.VIDEO_HUD_TIMEOUT, 16);
            edit.apply();
        }
        Settings.INSTANCE.setVideoHudDelay(SettingsKt.coerceInOrDefault(sharedPreferences.getInt(SettingsKt.VIDEO_HUD_TIMEOUT, 4), 1, 15, -1));
    }

    private final void migrateToVersion7(SharedPreferences sharedPreferences) {
        Log.i(getClass().getSimpleName(), "Migrating to Version 7: migrate PlaylistManager PLAYLIST_REPEASE_MODE_KEY to PLAYLIST_VIDEO_REPEAT_MODE_KEY and PLAYLIST_AUDIO_REPEAT_MODE_KEY");
        int i = sharedPreferences.getInt("audio_repeat_mode", -1);
        if (i != -1) {
            SettingsKt.putSingle(sharedPreferences, "video_repeat_mode", Integer.valueOf(i));
        }
    }

    private final void migrateToVersion8(SharedPreferences sharedPreferences) {
        Log.i(getClass().getSimpleName(), "Migration to Version 8: split force_play_all and add force_play_all_audio to separately handle video and audio");
        if (sharedPreferences.contains("force_play_all")) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            boolean z = sharedPreferences.getBoolean("force_play_all", false);
            edit.putBoolean(SettingsKt.FORCE_PLAY_ALL_VIDEO, z);
            edit.putBoolean(SettingsKt.FORCE_PLAY_ALL_AUDIO, z);
            edit.remove("force_play_all");
            edit.apply();
        }
    }

    private final void migrateToVersion9(SharedPreferences sharedPreferences) {
        Log.i(getClass().getSimpleName(), "Migration to Version 9: migrate the screenshot setting to a multiple entry value");
        if (sharedPreferences.contains("enable_screenshot_gesture")) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (sharedPreferences.getBoolean("enable_screenshot_gesture", false)) {
                edit.putString(SettingsKt.SCREENSHOT_MODE, "2");
            }
            edit.remove("enable_screenshot_gesture");
            edit.apply();
        }
    }

    private final void migrateToVersion10(SharedPreferences sharedPreferences) {
        Log.i(getClass().getSimpleName(), "Migration to Version 10: Migrate the subtitle color setting");
        if (sharedPreferences.contains("subtitles_color")) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            String string = sharedPreferences.getString("subtitles_color", "16777215");
            if (string != null) {
                try {
                    Intrinsics.checkNotNull(string);
                    int parseInt = Integer.parseInt(string);
                    edit.putInt("subtitles_color", Color.argb(255, Color.red(parseInt), Color.green(parseInt), Color.blue(parseInt)));
                } catch (Exception unused) {
                    edit.remove("subtitles_color");
                }
            }
            edit.commit();
        }
    }

    private final void migrateToVersion11(SharedPreferences sharedPreferences) {
        Log.i(getClass().getSimpleName(), "Migration to Version 11: Migrate the  playlists' display in grid setting");
        if (sharedPreferences.contains("display_mode_playlists")) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            boolean z = sharedPreferences.getBoolean("display_mode_playlists", true);
            try {
                int i = KotlinExtensionsKt.toInt(z);
                edit.putInt("subtitles_color", Color.argb(255, Color.red(i), Color.green(i), Color.blue(i)));
            } catch (Exception unused) {
            }
            edit.putBoolean("display_mode_playlists_" + Playlist.Type.Audio, z);
            edit.putBoolean("display_mode_playlists_" + Playlist.Type.Video, z);
            edit.remove("display_mode_playlists");
            edit.commit();
        }
    }

    private final void migrateToVersion12(SharedPreferences sharedPreferences) {
        Log.i(getClass().getSimpleName(), "Migration to Version 12: Migrate the show all files pref to show only multimedia files");
        if (sharedPreferences.contains("browser_show_all_files")) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean(SettingsKt.BROWSER_SHOW_ONLY_MULTIMEDIA, !sharedPreferences.getBoolean("browser_show_all_files", true));
            edit.remove("browser_show_all_files");
            edit.commit();
        }
    }

    private final void migrateToVlc4(SharedPreferences sharedPreferences) {
        Log.i(getClass().getSimpleName(), "Migration to VLC 4");
        if (sharedPreferences.contains("aout")) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.remove("aout");
            edit.commit();
        }
    }
}
