package org.videolan.resources;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Build;
import android.util.Log;
import androidx.core.content.ContextCompat;
import io.ktor.http.ContentDisposition;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.AnsiRenderer;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.libvlc.util.HWDecoderUtil;
import org.videolan.libvlc.util.VLCUtil;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.Preferences;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.MainVersionKt;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\fH\u0007J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\f2\u0006\u0010\u001b\u001a\u00020\u001cJ\u001a\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\fH\u0007J\u0010\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u0004H\u0002J\u000e\u0010!\u001a\u00020\"2\u0006\u0010\u0017\u001a\u00020\u0018J\u0012\u0010#\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u000e\u0010$\u001a\u00020\"2\u0006\u0010\u0017\u001a\u00020\u0018J\u001c\u0010%\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010&\u001a\u00020\"H\u0007J\u0012\u0010%\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u000e\u0010'\u001a\u00020(2\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010)\u001a\u00020\"2\u0006\u0010\u001b\u001a\u00020\u001cJ \u0010*\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010+\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\fH\u0007J2\u0010,\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010+\u001a\u0004\u0018\u00010\u001e2\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\"2\u0006\u0010/\u001a\u00020\"H\u0007J\u0016\u00100\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010.\u001a\u00020\"J&\u00101\u001a\u00020\u00162\u0006\u00102\u001a\u0002032\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\"R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fXT¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u00066"}, d2 = {"Lorg/videolan/resources/VLCOptions;", "", "()V", "AOUT_AAUDIO", "", "AOUT_AUDIOTRACK", "AOUT_OPENSLES", "HW_ACCELERATION_AUTOMATIC", "HW_ACCELERATION_DECODING", "HW_ACCELERATION_DISABLED", "HW_ACCELERATION_FULL", "TAG", "", "<set-?>", "audiotrackSessionId", "getAudiotrackSessionId", "()I", "libOptions", "Ljava/util/ArrayList;", "getLibOptions", "()Ljava/util/ArrayList;", "deleteCustomSet", "", "context", "Landroid/content/Context;", "customName", "getAout", "pref", "Landroid/content/SharedPreferences;", "getCustomSet", "Lorg/videolan/libvlc/MediaPlayer$Equalizer;", "getDeblocking", "deblocking", "getEqualizerEnabledState", "", "getEqualizerNameFromSettings", "getEqualizerSavedState", "getEqualizerSetFromSettings", "force", "getSoundFontFile", "Ljava/io/File;", "isAudioDigitalOutputEnabled", "saveCustomSet", "eq", "saveEqualizerInSettings", "name", "enabled", "saved", "setAudioDigitalOutputEnabled", "setMediaOptions", "media", "Lorg/videolan/libvlc/interfaces/IMedia;", "flags", "hasRenderer", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VLCOptions.kt */
public final class VLCOptions {
    private static final int AOUT_AAUDIO = 0;
    private static final int AOUT_AUDIOTRACK = 1;
    private static final int AOUT_OPENSLES = 2;
    private static final int HW_ACCELERATION_AUTOMATIC = -1;
    private static final int HW_ACCELERATION_DECODING = 1;
    private static final int HW_ACCELERATION_DISABLED = 0;
    private static final int HW_ACCELERATION_FULL = 2;
    public static final VLCOptions INSTANCE = new VLCOptions();
    private static final String TAG = "VLC/VLCConfig";
    private static int audiotrackSessionId;

    public final MediaPlayer.Equalizer getEqualizerSetFromSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getEqualizerSetFromSettings$default(this, context, false, 2, (Object) null);
    }

    private VLCOptions() {
    }

    public final int getAudiotrackSessionId() {
        return audiotrackSessionId;
    }

    public final ArrayList<String> getLibOptions() {
        int i;
        StringBuilder sb;
        String[] strArr;
        Context appContext = AppContextProvider.INSTANCE.getAppContext();
        SharedPreferences sharedPreferences = (SharedPreferences) Settings.INSTANCE.getInstance(appContext);
        if (Build.VERSION.SDK_INT >= 21 && audiotrackSessionId == 0) {
            Object systemService = ContextCompat.getSystemService(appContext, AudioManager.class);
            Intrinsics.checkNotNull(systemService);
            audiotrackSessionId = AppUtils$$ExternalSyntheticApiModelOutline0.m((AudioManager) systemService);
        }
        ArrayList<String> arrayList = new ArrayList<>(50);
        boolean z = sharedPreferences.getBoolean("enable_time_stretching_audio", appContext.getResources().getBoolean(R.bool.time_stretching_default));
        String string = sharedPreferences.getString("subtitle_text_encoding", "");
        if (string == null) {
            string = "";
        }
        boolean z2 = sharedPreferences.getBoolean("enable_frame_skip", false);
        boolean z3 = sharedPreferences.getBoolean("enable_verbose_mode", true);
        try {
            String string2 = sharedPreferences.getString("deblocking", Constants.GROUP_VIDEOS_NONE);
            Intrinsics.checkNotNull(string2);
            i = getDeblocking(Integer.parseInt(string2));
        } catch (NumberFormatException unused) {
            i = -1;
        }
        int coerceIn = RangesKt.coerceIn(sharedPreferences.getInt("network_caching_value", 0), 0, 60000);
        String string3 = sharedPreferences.getString("subtitles_size", AnsiConsole.JANSI_COLORS_16);
        boolean z4 = sharedPreferences.getBoolean("subtitles_bold", false);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("0x%06X", Arrays.copyOf(new Object[]{Integer.valueOf(sharedPreferences.getInt("subtitles_color", 16777215) & 16777215)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        boolean z5 = z3;
        Integer decode = Integer.decode(format);
        int i2 = sharedPreferences.getInt("subtitles_color_opacity", 255);
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        int i3 = i2;
        boolean z6 = z4;
        String str = string3;
        String format2 = String.format("0x%06X", Arrays.copyOf(new Object[]{Integer.valueOf(sharedPreferences.getInt("subtitles_background_color", 16777215) & 16777215)}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
        Integer decode2 = Integer.decode(format2);
        int i4 = sharedPreferences.getInt("subtitles_background_color_opacity", 255);
        boolean z7 = sharedPreferences.getBoolean("subtitles_background", false);
        int i5 = i4;
        boolean z8 = sharedPreferences.getBoolean("subtitles_outline", true);
        String string4 = sharedPreferences.getString("subtitles_outline_size", "4");
        StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
        String str2 = string4;
        Integer num = decode2;
        boolean z9 = z7;
        String format3 = String.format("0x%06X", Arrays.copyOf(new Object[]{Integer.valueOf(sharedPreferences.getInt("subtitles_outline_color", 0) & 16777215)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
        Integer decode3 = Integer.decode(format3);
        int i6 = sharedPreferences.getInt("subtitles_outline_color_opacity", 255);
        boolean z10 = sharedPreferences.getBoolean("subtitles_shadow", true);
        StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
        Context context = appContext;
        int i7 = i6;
        String format4 = String.format("0x%06X", Arrays.copyOf(new Object[]{Integer.valueOf(sharedPreferences.getInt("subtitles_shadow_color", ContextCompat.getColor(appContext, R.color.black)) & 16777215)}, 1));
        Intrinsics.checkNotNullExpressionValue(format4, "format(...)");
        Integer decode4 = Integer.decode(format4);
        int i8 = sharedPreferences.getInt("subtitles_shadow_color_opacity", 128);
        String string5 = sharedPreferences.getString("opengl", Constants.GROUP_VIDEOS_NONE);
        Intrinsics.checkNotNull(string5);
        int parseInt = Integer.parseInt(string5);
        arrayList.add(z ? "--audio-time-stretch" : "--no-audio-time-stretch");
        arrayList.add("--avcodec-skiploopfilter");
        arrayList.add("" + i);
        arrayList.add("--avcodec-skip-frame");
        String str3 = Constants.GROUP_VIDEOS_FOLDER;
        arrayList.add(z2 ? "2" : str3);
        arrayList.add("--avcodec-skip-idct");
        if (z2) {
            str3 = "2";
        }
        arrayList.add(str3);
        arrayList.add("--subsdec-encoding");
        arrayList.add(string);
        arrayList.add("--stats");
        if (coerceIn > 0) {
            arrayList.add("--network-caching=" + coerceIn);
        }
        arrayList.add("--audio-resampler");
        arrayList.add("soxr");
        arrayList.add("--audiotrack-session-id=" + audiotrackSessionId);
        if (MainVersionKt.isVLC4()) {
            sb = new StringBuilder("--sub-text-scale=");
            Intrinsics.checkNotNull(str);
            sb.append(((float) 1600) / Float.parseFloat(str));
        } else {
            sb = new StringBuilder("--freetype-rel-fontsize=");
            Intrinsics.checkNotNull(str);
            sb.append(str);
        }
        arrayList.add(sb.toString());
        if (z6) {
            arrayList.add("--freetype-bold");
        }
        arrayList.add("--freetype-color=" + decode);
        arrayList.add("--freetype-opacity=" + i3);
        if (z9) {
            arrayList.add("--freetype-background-color=" + num);
            arrayList.add("--freetype-background-opacity=" + i5);
        } else {
            arrayList.add("--freetype-background-opacity=0");
        }
        if (z10) {
            arrayList.add("--freetype-shadow-color=" + decode4);
            arrayList.add("--freetype-shadow-opacity=" + i8);
        } else {
            arrayList.add("--freetype-shadow-opacity=0");
        }
        if (z8) {
            arrayList.add("--freetype-outline-thickness=" + str2);
            arrayList.add("--freetype-outline-color=" + decode3);
            arrayList.add("--freetype-outline-opacity=" + i7);
        } else {
            arrayList.add("--freetype-outline-opacity=0");
        }
        if (parseInt == 0) {
            arrayList.add("--vout=android_display,none");
        } else if (parseInt == 1) {
            arrayList.add("--vout=gles2,none");
        }
        arrayList.add("--keystore");
        arrayList.add(AndroidUtil.isMarshMallowOrLater ? "file_crypt,none" : "file_plaintext,none");
        arrayList.add("--keystore-file");
        Context context2 = context;
        arrayList.add(new File(context2.getDir("keystore", 0), "file").getAbsolutePath());
        arrayList.add(z5 ? "-vv" : "-v");
        if (!MainVersionKt.isVLC4()) {
            if (sharedPreferences.getBoolean("casting_passthrough", false)) {
                arrayList.add("--sout-chromecast-audio-passthrough");
            } else {
                arrayList.add("--no-sout-chromecast-audio-passthrough");
            }
            StringBuilder sb2 = new StringBuilder("--sout-chromecast-conversion-quality=");
            String string6 = sharedPreferences.getString("casting_quality", "2");
            Intrinsics.checkNotNull(string6);
            sb2.append(string6);
            arrayList.add(sb2.toString());
        }
        arrayList.add("--sout-keep");
        CharSequence string7 = sharedPreferences.getString("custom_libvlc_options", (String) null);
        if (!(string7 == null || string7.length() == 0 || (strArr = (String[]) new Regex("\\r?\\n").split(string7, 0).toArray(new String[0])) == null || strArr.length == 0)) {
            Collections.addAll(arrayList, Arrays.copyOf(strArr, strArr.length));
        }
        if (sharedPreferences.getBoolean("prefer_smbv1", true)) {
            arrayList.add("--smb-force-v1");
        }
        if (!Settings.INSTANCE.getShowTvUi()) {
            File dir = context2.getDir("vlc", 0);
            arrayList.add("--spatialaudio-headphones");
            arrayList.add("--hrtf-file");
            arrayList.add(dir.getAbsolutePath() + "/.share/hrtfs/dodeca_and_7channel_3DSL_HRTF.sofa");
        }
        if (sharedPreferences.getBoolean("audio-replay-gain-enable", false)) {
            arrayList.add("--audio-replay-gain-mode=" + sharedPreferences.getString("audio-replay-gain-mode", "track"));
            arrayList.add("--audio-replay-gain-preamp=" + sharedPreferences.getString("audio-replay-gain-preamp", "0.0"));
            arrayList.add("--audio-replay-gain-default=" + sharedPreferences.getString("audio-replay-gain-default", "-7.0"));
            if (sharedPreferences.getBoolean("audio-replay-gain-peak-protection", true)) {
                arrayList.add("--audio-replay-gain-peak-protection");
            } else {
                arrayList.add("--no-audio-replay-gain-peak-protection");
            }
        }
        File soundFontFile = getSoundFontFile(context2);
        if (soundFontFile.exists()) {
            arrayList.add("--soundfont=" + soundFontFile.getPath());
        }
        StringBuilder sb3 = new StringBuilder("--preferred-resolution=");
        String string8 = sharedPreferences.getString("preferred_resolution", Constants.GROUP_VIDEOS_NONE);
        Intrinsics.checkNotNull(string8);
        sb3.append(string8);
        arrayList.add(sb3.toString());
        return arrayList;
    }

    public final boolean isAudioDigitalOutputEnabled(SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "pref");
        return sharedPreferences.getBoolean("audio_digital_output", false);
    }

    public final void setAudioDigitalOutputEnabled(SharedPreferences sharedPreferences, boolean z) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "pref");
        SettingsKt.putSingle(sharedPreferences, "audio_digital_output", Boolean.valueOf(z));
    }

    public final String getAout(SharedPreferences sharedPreferences) {
        int i;
        Intrinsics.checkNotNullParameter(sharedPreferences, "pref");
        try {
            String string = sharedPreferences.getString("aout", Constants.GROUP_VIDEOS_NONE);
            Intrinsics.checkNotNull(string);
            i = Integer.parseInt(string);
        } catch (NumberFormatException unused) {
            i = -1;
        }
        if (HWDecoderUtil.getAudioOutputFromDevice() == HWDecoderUtil.AudioOutput.OPENSLES) {
            i = 2;
        }
        if (i == 1) {
            return "audiotrack";
        }
        if (i != 2) {
            return null;
        }
        return "opensles";
    }

    private final int getDeblocking(int i) {
        if (i < 0) {
            VLCUtil.MachineSpecs machineSpecs = VLCUtil.getMachineSpecs();
            if (machineSpecs == null) {
                return i;
            }
            if ((machineSpecs.hasArmV6 && !machineSpecs.hasArmV7) || machineSpecs.hasMips) {
                return 4;
            }
            if (machineSpecs.frequency < 1200.0f || machineSpecs.processors <= 2) {
                if (machineSpecs.bogoMIPS >= 1200.0f && machineSpecs.processors > 2) {
                    Log.d(TAG, "Used bogoMIPS due to lack of frequency info");
                }
            }
            return 1;
        } else if (i > 4) {
            return 3;
        } else {
            return i;
        }
        return 3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setMediaOptions(org.videolan.libvlc.interfaces.IMedia r6, android.content.Context r7, int r8, boolean r9) {
        /*
            r5 = this;
            java.lang.String r0 = "media"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            r0 = r8 & 2
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0012
            r0 = 1
            goto L_0x0013
        L_0x0012:
            r0 = 0
        L_0x0013:
            r3 = r8 & 1
            if (r3 != 0) goto L_0x0019
            r3 = 1
            goto L_0x001a
        L_0x0019:
            r3 = 0
        L_0x001a:
            r8 = r8 & 4
            if (r8 == 0) goto L_0x0020
            r8 = 1
            goto L_0x0021
        L_0x0020:
            r8 = 0
        L_0x0021:
            org.videolan.tools.Settings r4 = org.videolan.tools.Settings.INSTANCE
            java.lang.Object r7 = r4.getInstance(r7)
            android.content.SharedPreferences r7 = (android.content.SharedPreferences) r7
            if (r0 != 0) goto L_0x003c
            java.lang.String r0 = "hardware_acceleration"
            java.lang.String r4 = "-1"
            java.lang.String r0 = r7.getString(r0, r4)     // Catch:{ NumberFormatException -> 0x003b }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ NumberFormatException -> 0x003b }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x003b }
            goto L_0x003d
        L_0x003b:
        L_0x003c:
            r0 = 0
        L_0x003d:
            if (r0 == 0) goto L_0x0055
            if (r0 == r2) goto L_0x0045
            r1 = 2
            if (r0 == r1) goto L_0x0045
            goto L_0x0058
        L_0x0045:
            r6.setHWDecoderEnabled(r2, r2)
            if (r0 != r2) goto L_0x0058
            java.lang.String r0 = ":no-mediacodec-dr"
            r6.addOption(r0)
            java.lang.String r0 = ":no-omxil-dr"
            r6.addOption(r0)
            goto L_0x0058
        L_0x0055:
            r6.setHWDecoderEnabled(r1, r1)
        L_0x0058:
            if (r3 == 0) goto L_0x005f
            java.lang.String r0 = ":no-video"
            r6.addOption(r0)
        L_0x005f:
            if (r8 == 0) goto L_0x0066
            java.lang.String r8 = ":start-paused"
            r6.addOption(r8)
        L_0x0066:
            java.lang.String r8 = "subtitles_autoload"
            boolean r8 = r7.getBoolean(r8, r2)
            if (r8 != 0) goto L_0x0073
            java.lang.String r8 = ":sub-language=none"
            r6.addOption(r8)
        L_0x0073:
            if (r9 == 0) goto L_0x00a8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = ":sout-chromecast-audio-passthrough="
            r8.<init>(r9)
            java.lang.String r9 = "casting_passthrough"
            boolean r9 = r7.getBoolean(r9, r2)
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r6.addOption(r8)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = ":sout-chromecast-conversion-quality="
            r8.<init>(r9)
            java.lang.String r9 = "casting_quality"
            java.lang.String r0 = "2"
            java.lang.String r7 = r7.getString(r9, r0)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            r6.addOption(r7)
        L_0x00a8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.resources.VLCOptions.setMediaOptions(org.videolan.libvlc.interfaces.IMedia, android.content.Context, int, boolean):void");
    }

    private final MediaPlayer.Equalizer getEqualizerSetFromSettings(SharedPreferences sharedPreferences) {
        float[] floatArray = Preferences.INSTANCE.getFloatArray(sharedPreferences, "equalizer_values");
        int i = 0;
        if (floatArray == null || !sharedPreferences.contains("equalizer_enabled")) {
            return MediaPlayer.Equalizer.createFromPreset(0);
        }
        int bandCount = MediaPlayer.Equalizer.getBandCount();
        if (floatArray.length != bandCount + 1) {
            return null;
        }
        MediaPlayer.Equalizer create = MediaPlayer.Equalizer.create();
        create.setPreAmp(floatArray[0]);
        while (i < bandCount) {
            int i2 = i + 1;
            create.setAmp(i, floatArray[i2]);
            i = i2;
        }
        return create;
    }

    public static /* synthetic */ MediaPlayer.Equalizer getEqualizerSetFromSettings$default(VLCOptions vLCOptions, Context context, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return vLCOptions.getEqualizerSetFromSettings(context, z);
    }

    public final MediaPlayer.Equalizer getEqualizerSetFromSettings(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = (SharedPreferences) Settings.INSTANCE.getInstance(context);
        if (z || sharedPreferences.getBoolean("equalizer_enabled", false)) {
            return getEqualizerSetFromSettings(sharedPreferences);
        }
        return null;
    }

    public final String getEqualizerNameFromSettings(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ((SharedPreferences) Settings.INSTANCE.getInstance(context)).getString("equalizer_set", "Flat");
    }

    public final void saveEqualizerInSettings(Context context, MediaPlayer.Equalizer equalizer, String str, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        SharedPreferences.Editor edit = ((SharedPreferences) Settings.INSTANCE.getInstance(context)).edit();
        int i = 0;
        if (equalizer != null) {
            edit.putBoolean("equalizer_enabled", z);
            int bandCount = MediaPlayer.Equalizer.getBandCount();
            float[] fArr = new float[(bandCount + 1)];
            fArr[0] = equalizer.getPreAmp();
            while (i < bandCount) {
                int i2 = i + 1;
                fArr[i2] = equalizer.getAmp(i);
                i = i2;
            }
            Preferences.INSTANCE.putFloatArray(edit, "equalizer_values", fArr);
            edit.putString("equalizer_set", str);
        } else {
            edit.putBoolean("equalizer_enabled", false);
        }
        edit.putBoolean("equalizer_saved", z2);
        edit.apply();
    }

    public final MediaPlayer.Equalizer getCustomSet(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "customName");
        try {
            float[] floatArray = Preferences.INSTANCE.getFloatArray((SharedPreferences) Settings.INSTANCE.getInstance(context), "custom_equalizer_" + StringsKt.replace$default(str, AnsiRenderer.CODE_TEXT_SEPARATOR, "_", false, 4, (Object) null));
            int bandCount = MediaPlayer.Equalizer.getBandCount();
            Intrinsics.checkNotNull(floatArray);
            if (floatArray.length != bandCount + 1) {
                return null;
            }
            MediaPlayer.Equalizer create = MediaPlayer.Equalizer.create();
            create.setPreAmp(floatArray[0]);
            int i = 0;
            while (i < bandCount) {
                int i2 = i + 1;
                create.setAmp(i, floatArray[i2]);
                i = i2;
            }
            return create;
        } catch (Exception unused) {
            return MediaPlayer.Equalizer.createFromPreset(0);
        }
    }

    public final void saveCustomSet(Context context, MediaPlayer.Equalizer equalizer, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(equalizer, "eq");
        Intrinsics.checkNotNullParameter(str, "customName");
        SharedPreferences.Editor edit = ((SharedPreferences) Settings.INSTANCE.getInstance(context)).edit();
        int i = 0;
        String str2 = "custom_equalizer_" + StringsKt.replace$default(str, AnsiRenderer.CODE_TEXT_SEPARATOR, "_", false, 4, (Object) null);
        int bandCount = MediaPlayer.Equalizer.getBandCount();
        float[] fArr = new float[(bandCount + 1)];
        fArr[0] = equalizer.getPreAmp();
        while (i < bandCount) {
            int i2 = i + 1;
            fArr[i2] = equalizer.getAmp(i);
            i = i2;
        }
        Preferences.INSTANCE.putFloatArray(edit, str2, fArr);
        edit.apply();
    }

    public final void deleteCustomSet(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "customName");
        SharedPreferences.Editor edit = ((SharedPreferences) Settings.INSTANCE.getInstance(context)).edit();
        edit.remove("custom_equalizer_" + StringsKt.replace$default(str, AnsiRenderer.CODE_TEXT_SEPARATOR, "_", false, 4, (Object) null));
        edit.apply();
    }

    public final boolean getEqualizerSavedState(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ((SharedPreferences) Settings.INSTANCE.getInstance(context)).getBoolean("equalizer_saved", true);
    }

    public final boolean getEqualizerEnabledState(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ((SharedPreferences) Settings.INSTANCE.getInstance(context)).getBoolean("equalizer_enabled", false);
    }

    public final File getSoundFontFile(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        StringBuilder sb = new StringBuilder();
        File dir = context.getDir("soundfont", 0);
        Intrinsics.checkNotNull(dir);
        sb.append(dir.getPath());
        sb.append("/soundfont.sf2");
        return new File(sb.toString());
    }
}
