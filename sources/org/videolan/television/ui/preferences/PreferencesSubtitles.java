package org.videolan.television.ui.preferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.SeekBarPreference;
import com.jaredrummler.android.colorpicker.ColorPreferenceCompat;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.fusesource.jansi.AnsiConsole;
import org.videolan.resources.Constants;
import org.videolan.television.ui.ColorPickerActivity;
import org.videolan.television.ui.ColorPickerActivityKt;
import org.videolan.tools.LocalePair;
import org.videolan.tools.LocaleUtils;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.BuildConfig;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001e\u001a\u00020\u001fH\u0014J\b\u0010 \u001a\u00020\u001fH\u0014J\b\u0010!\u001a\u00020\"H\u0002J\"\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u001f2\b\u0010&\u001a\u0004\u0018\u00010'H\u0017J\u0012\u0010(\u001a\u00020\"2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u001c\u0010+\u001a\u00020\"2\b\u0010,\u001a\u0004\u0018\u00010\f2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020\"H\u0016J\b\u00100\u001a\u00020\"H\u0002J\b\u00101\u001a\u00020\"H\u0002J\b\u00102\u001a\u00020\"H\u0002R\u0012\u0010\u0005\u001a\u00020\u0006X\u0005¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\nX.¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lorg/videolan/television/ui/preferences/PreferencesSubtitles;", "Lorg/videolan/television/ui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "preferredSubtitleTrack", "Landroidx/preference/ListPreference;", "settings", "Landroid/content/SharedPreferences;", "subtitlesBackgroundColor", "Lcom/jaredrummler/android/colorpicker/ColorPreferenceCompat;", "subtitlesBackgroundEnabled", "Landroidx/preference/CheckBoxPreference;", "subtitlesBackgroundOpacity", "Landroidx/preference/SeekBarPreference;", "subtitlesBold", "subtitlesColor", "subtitlesOpacity", "subtitlesOutlineColor", "subtitlesOutlineEnabled", "subtitlesOutlineOpacity", "subtitlesOutlineSize", "subtitlesShadowColor", "subtitlesShadowEnabled", "subtitlesShadowOpacity", "subtitlesSize", "getTitleId", "", "getXml", "managePreferenceVisibilities", "", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSharedPreferenceChanged", "sharedPreferences", "key", "", "onStart", "prepareLocaleList", "resetAll", "updatePreferredSubtitleTrack", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesSubtitles.kt */
public final class PreferencesSubtitles extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener, CoroutineScope {
    private final /* synthetic */ CoroutineScope $$delegate_0 = CoroutineScopeKt.MainScope();
    private ListPreference preferredSubtitleTrack;
    private SharedPreferences settings;
    private ColorPreferenceCompat subtitlesBackgroundColor;
    private CheckBoxPreference subtitlesBackgroundEnabled;
    private SeekBarPreference subtitlesBackgroundOpacity;
    private CheckBoxPreference subtitlesBold;
    private ColorPreferenceCompat subtitlesColor;
    private SeekBarPreference subtitlesOpacity;
    private ColorPreferenceCompat subtitlesOutlineColor;
    private CheckBoxPreference subtitlesOutlineEnabled;
    private SeekBarPreference subtitlesOutlineOpacity;
    private ListPreference subtitlesOutlineSize;
    private ColorPreferenceCompat subtitlesShadowColor;
    private CheckBoxPreference subtitlesShadowEnabled;
    private SeekBarPreference subtitlesShadowOpacity;
    private ListPreference subtitlesSize;

    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    /* access modifiers changed from: protected */
    public int getXml() {
        return R.xml.preferences_subtitles;
    }

    /* access modifiers changed from: protected */
    public int getTitleId() {
        return R.string.subtitles_prefs_category;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Settings settings2 = Settings.INSTANCE;
        Activity activity = getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
        this.settings = (SharedPreferences) settings2.getInstance(activity);
        Preference findPreference = findPreference(SettingsKt.SUBTITLE_PREFERRED_LANGUAGE);
        Intrinsics.checkNotNull(findPreference);
        this.preferredSubtitleTrack = (ListPreference) findPreference;
        Preference findPreference2 = findPreference("subtitles_size");
        Intrinsics.checkNotNull(findPreference2);
        this.subtitlesSize = (ListPreference) findPreference2;
        Preference findPreference3 = findPreference("subtitles_bold");
        Intrinsics.checkNotNull(findPreference3);
        this.subtitlesBold = (CheckBoxPreference) findPreference3;
        Preference findPreference4 = findPreference("subtitles_color");
        Intrinsics.checkNotNull(findPreference4);
        this.subtitlesColor = (ColorPreferenceCompat) findPreference4;
        Preference findPreference5 = findPreference("subtitles_color_opacity");
        Intrinsics.checkNotNull(findPreference5);
        this.subtitlesOpacity = (SeekBarPreference) findPreference5;
        Preference findPreference6 = findPreference("subtitles_background");
        Intrinsics.checkNotNull(findPreference6);
        this.subtitlesBackgroundEnabled = (CheckBoxPreference) findPreference6;
        Preference findPreference7 = findPreference("subtitles_background_color");
        Intrinsics.checkNotNull(findPreference7);
        this.subtitlesBackgroundColor = (ColorPreferenceCompat) findPreference7;
        Preference findPreference8 = findPreference("subtitles_background_color_opacity");
        Intrinsics.checkNotNull(findPreference8);
        this.subtitlesBackgroundOpacity = (SeekBarPreference) findPreference8;
        Preference findPreference9 = findPreference("subtitles_shadow");
        Intrinsics.checkNotNull(findPreference9);
        this.subtitlesShadowEnabled = (CheckBoxPreference) findPreference9;
        Preference findPreference10 = findPreference("subtitles_shadow_color");
        Intrinsics.checkNotNull(findPreference10);
        this.subtitlesShadowColor = (ColorPreferenceCompat) findPreference10;
        Preference findPreference11 = findPreference("subtitles_shadow_color_opacity");
        Intrinsics.checkNotNull(findPreference11);
        this.subtitlesShadowOpacity = (SeekBarPreference) findPreference11;
        Preference findPreference12 = findPreference("subtitles_outline");
        Intrinsics.checkNotNull(findPreference12);
        this.subtitlesOutlineEnabled = (CheckBoxPreference) findPreference12;
        Preference findPreference13 = findPreference("subtitles_outline_size");
        Intrinsics.checkNotNull(findPreference13);
        this.subtitlesOutlineSize = (ListPreference) findPreference13;
        Preference findPreference14 = findPreference("subtitles_outline_color");
        Intrinsics.checkNotNull(findPreference14);
        this.subtitlesOutlineColor = (ColorPreferenceCompat) findPreference14;
        Preference findPreference15 = findPreference("subtitles_outline_color_opacity");
        Intrinsics.checkNotNull(findPreference15);
        this.subtitlesOutlineOpacity = (SeekBarPreference) findPreference15;
        Preference findPreference16 = findPreference("subtitles_presets");
        Intrinsics.checkNotNull(findPreference16);
        ListPreference listPreference = (ListPreference) findPreference16;
        listPreference.setValue(Constants.GROUP_VIDEOS_NONE);
        listPreference.setOnPreferenceChangeListener(new PreferencesSubtitles$$ExternalSyntheticLambda0(this));
        ColorPreferenceCompat colorPreferenceCompat = this.subtitlesColor;
        ColorPreferenceCompat colorPreferenceCompat2 = null;
        if (colorPreferenceCompat == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesColor");
            colorPreferenceCompat = null;
        }
        colorPreferenceCompat.setOnShowDialogListener(new PreferencesSubtitles$$ExternalSyntheticLambda1(this));
        ColorPreferenceCompat colorPreferenceCompat3 = this.subtitlesBackgroundColor;
        if (colorPreferenceCompat3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesBackgroundColor");
            colorPreferenceCompat3 = null;
        }
        colorPreferenceCompat3.setOnShowDialogListener(new PreferencesSubtitles$$ExternalSyntheticLambda2(this));
        ColorPreferenceCompat colorPreferenceCompat4 = this.subtitlesShadowColor;
        if (colorPreferenceCompat4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesShadowColor");
            colorPreferenceCompat4 = null;
        }
        colorPreferenceCompat4.setOnShowDialogListener(new PreferencesSubtitles$$ExternalSyntheticLambda3(this));
        ColorPreferenceCompat colorPreferenceCompat5 = this.subtitlesOutlineColor;
        if (colorPreferenceCompat5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesOutlineColor");
        } else {
            colorPreferenceCompat2 = colorPreferenceCompat5;
        }
        colorPreferenceCompat2.setOnShowDialogListener(new PreferencesSubtitles$$ExternalSyntheticLambda4(this));
        updatePreferredSubtitleTrack();
        prepareLocaleList();
        managePreferenceVisibilities();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: androidx.preference.CheckBoxPreference} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: com.jaredrummler.android.colorpicker.ColorPreferenceCompat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: androidx.preference.CheckBoxPreference} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: androidx.preference.CheckBoxPreference} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v9, types: [androidx.preference.ListPreference] */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean onCreate$lambda$0(org.videolan.television.ui.preferences.PreferencesSubtitles r9, androidx.preference.Preference r10, java.lang.Object r11) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "<anonymous parameter 0>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            r9.resetAll()
            java.lang.String r10 = "1"
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r10)
            java.lang.String r0 = "subtitlesSize"
            r1 = 0
            r2 = 0
            if (r10 == 0) goto L_0x0029
            androidx.preference.ListPreference r9 = r9.subtitlesSize
            if (r9 != 0) goto L_0x0021
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            goto L_0x0022
        L_0x0021:
            r2 = r9
        L_0x0022:
            java.lang.String r9 = "13"
            r2.setValue(r9)
            goto L_0x00f3
        L_0x0029:
            java.lang.String r10 = "2"
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r10)
            java.lang.String r3 = "subtitlesShadowEnabled"
            java.lang.String r4 = "subtitlesBackgroundOpacity"
            java.lang.String r5 = "subtitlesBackgroundEnabled"
            r6 = 1
            if (r10 == 0) goto L_0x0078
            androidx.preference.ListPreference r10 = r9.subtitlesSize
            if (r10 != 0) goto L_0x0040
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r10 = r2
        L_0x0040:
            java.lang.String r11 = "10"
            r10.setValue(r11)
            androidx.preference.CheckBoxPreference r10 = r9.subtitlesBackgroundEnabled
            if (r10 != 0) goto L_0x004d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r10 = r2
        L_0x004d:
            r10.setChecked(r6)
            androidx.preference.SeekBarPreference r10 = r9.subtitlesBackgroundOpacity
            if (r10 != 0) goto L_0x0058
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r10 = r2
        L_0x0058:
            r11 = 255(0xff, float:3.57E-43)
            r10.setValue(r11)
            androidx.preference.CheckBoxPreference r10 = r9.subtitlesShadowEnabled
            if (r10 != 0) goto L_0x0065
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r10 = r2
        L_0x0065:
            r10.setChecked(r1)
            androidx.preference.CheckBoxPreference r9 = r9.subtitlesOutlineEnabled
            if (r9 != 0) goto L_0x0072
            java.lang.String r9 = "subtitlesOutlineEnabled"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            goto L_0x0073
        L_0x0072:
            r2 = r9
        L_0x0073:
            r2.setChecked(r1)
            goto L_0x00f3
        L_0x0078:
            java.lang.String r10 = "3"
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r10)
            r0 = 128(0x80, float:1.794E-43)
            if (r10 == 0) goto L_0x00a5
            androidx.preference.CheckBoxPreference r10 = r9.subtitlesBackgroundEnabled
            if (r10 != 0) goto L_0x008a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r10 = r2
        L_0x008a:
            r10.setChecked(r6)
            androidx.preference.SeekBarPreference r10 = r9.subtitlesBackgroundOpacity
            if (r10 != 0) goto L_0x0095
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r10 = r2
        L_0x0095:
            r10.setValue(r0)
            androidx.preference.CheckBoxPreference r9 = r9.subtitlesShadowEnabled
            if (r9 != 0) goto L_0x00a0
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L_0x00a1
        L_0x00a0:
            r2 = r9
        L_0x00a1:
            r2.setChecked(r1)
            goto L_0x00f3
        L_0x00a5:
            java.lang.String r10 = "4"
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r10)
            java.lang.String r7 = "subtitlesColor"
            r8 = -256(0xffffffffffffff00, float:NaN)
            if (r10 == 0) goto L_0x00be
            com.jaredrummler.android.colorpicker.ColorPreferenceCompat r9 = r9.subtitlesColor
            if (r9 != 0) goto L_0x00b9
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            goto L_0x00ba
        L_0x00b9:
            r2 = r9
        L_0x00ba:
            r2.saveValue(r8)
            goto L_0x00f3
        L_0x00be:
            java.lang.String r10 = "5"
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r10)
            if (r10 == 0) goto L_0x00f3
            com.jaredrummler.android.colorpicker.ColorPreferenceCompat r10 = r9.subtitlesColor
            if (r10 != 0) goto L_0x00ce
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r10 = r2
        L_0x00ce:
            r10.saveValue(r8)
            androidx.preference.CheckBoxPreference r10 = r9.subtitlesBackgroundEnabled
            if (r10 != 0) goto L_0x00d9
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r10 = r2
        L_0x00d9:
            r10.setChecked(r6)
            androidx.preference.SeekBarPreference r10 = r9.subtitlesBackgroundOpacity
            if (r10 != 0) goto L_0x00e4
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r10 = r2
        L_0x00e4:
            r10.setValue(r0)
            androidx.preference.CheckBoxPreference r9 = r9.subtitlesShadowEnabled
            if (r9 != 0) goto L_0x00ef
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L_0x00f0
        L_0x00ef:
            r2 = r9
        L_0x00f0:
            r2.setChecked(r1)
        L_0x00f3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.preferences.PreferencesSubtitles.onCreate$lambda$0(org.videolan.television.ui.preferences.PreferencesSubtitles, androidx.preference.Preference, java.lang.Object):boolean");
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$1(PreferencesSubtitles preferencesSubtitles, String str, int i) {
        Intrinsics.checkNotNullParameter(preferencesSubtitles, "this$0");
        Intent intent = new Intent(preferencesSubtitles.getActivity(), ColorPickerActivity.class);
        intent.putExtra(ColorPickerActivityKt.COLOR_PICKER_SELECTED_COLOR, i);
        intent.putExtra(ColorPickerActivityKt.COLOR_PICKER_TITLE, preferencesSubtitles.getString(R.string.subtitles_color_title));
        preferencesSubtitles.startActivityForResult(intent, 1);
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$2(PreferencesSubtitles preferencesSubtitles, String str, int i) {
        Intrinsics.checkNotNullParameter(preferencesSubtitles, "this$0");
        Intent intent = new Intent(preferencesSubtitles.getActivity(), ColorPickerActivity.class);
        intent.putExtra(ColorPickerActivityKt.COLOR_PICKER_SELECTED_COLOR, i);
        intent.putExtra(ColorPickerActivityKt.COLOR_PICKER_TITLE, preferencesSubtitles.getString(R.string.subtitles_background_color_title));
        preferencesSubtitles.startActivityForResult(intent, 2);
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$3(PreferencesSubtitles preferencesSubtitles, String str, int i) {
        Intrinsics.checkNotNullParameter(preferencesSubtitles, "this$0");
        Intent intent = new Intent(preferencesSubtitles.getActivity(), ColorPickerActivity.class);
        intent.putExtra(ColorPickerActivityKt.COLOR_PICKER_SELECTED_COLOR, i);
        intent.putExtra(ColorPickerActivityKt.COLOR_PICKER_TITLE, preferencesSubtitles.getString(R.string.subtitles_shadow_title));
        preferencesSubtitles.startActivityForResult(intent, 3);
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$4(PreferencesSubtitles preferencesSubtitles, String str, int i) {
        Intrinsics.checkNotNullParameter(preferencesSubtitles, "this$0");
        Intent intent = new Intent(preferencesSubtitles.getActivity(), ColorPickerActivity.class);
        intent.putExtra(ColorPickerActivityKt.COLOR_PICKER_SELECTED_COLOR, i);
        intent.putExtra(ColorPickerActivityKt.COLOR_PICKER_TITLE, preferencesSubtitles.getString(R.string.subtitles_outline_title));
        preferencesSubtitles.startActivityForResult(intent, 4);
    }

    private final void resetAll() {
        ListPreference listPreference = this.subtitlesSize;
        SeekBarPreference seekBarPreference = null;
        if (listPreference == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesSize");
            listPreference = null;
        }
        listPreference.setValue(AnsiConsole.JANSI_COLORS_16);
        CheckBoxPreference checkBoxPreference = this.subtitlesBold;
        if (checkBoxPreference == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesBold");
            checkBoxPreference = null;
        }
        checkBoxPreference.setChecked(false);
        ColorPreferenceCompat colorPreferenceCompat = this.subtitlesColor;
        if (colorPreferenceCompat == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesColor");
            colorPreferenceCompat = null;
        }
        colorPreferenceCompat.saveValue(ContextCompat.getColor(getActivity(), R.color.white));
        SeekBarPreference seekBarPreference2 = this.subtitlesOpacity;
        if (seekBarPreference2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesOpacity");
            seekBarPreference2 = null;
        }
        seekBarPreference2.setValue(255);
        CheckBoxPreference checkBoxPreference2 = this.subtitlesBackgroundEnabled;
        if (checkBoxPreference2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesBackgroundEnabled");
            checkBoxPreference2 = null;
        }
        checkBoxPreference2.setChecked(false);
        ColorPreferenceCompat colorPreferenceCompat2 = this.subtitlesBackgroundColor;
        if (colorPreferenceCompat2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesBackgroundColor");
            colorPreferenceCompat2 = null;
        }
        colorPreferenceCompat2.saveValue(ContextCompat.getColor(getActivity(), R.color.black));
        SeekBarPreference seekBarPreference3 = this.subtitlesBackgroundOpacity;
        if (seekBarPreference3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesBackgroundOpacity");
            seekBarPreference3 = null;
        }
        seekBarPreference3.setValue(255);
        CheckBoxPreference checkBoxPreference3 = this.subtitlesShadowEnabled;
        if (checkBoxPreference3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesShadowEnabled");
            checkBoxPreference3 = null;
        }
        checkBoxPreference3.setChecked(true);
        ColorPreferenceCompat colorPreferenceCompat3 = this.subtitlesShadowColor;
        if (colorPreferenceCompat3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesShadowColor");
            colorPreferenceCompat3 = null;
        }
        colorPreferenceCompat3.saveValue(ContextCompat.getColor(getActivity(), R.color.black));
        SeekBarPreference seekBarPreference4 = this.subtitlesShadowOpacity;
        if (seekBarPreference4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesShadowOpacity");
            seekBarPreference4 = null;
        }
        seekBarPreference4.setValue(255);
        CheckBoxPreference checkBoxPreference4 = this.subtitlesOutlineEnabled;
        if (checkBoxPreference4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesOutlineEnabled");
            checkBoxPreference4 = null;
        }
        checkBoxPreference4.setChecked(true);
        ColorPreferenceCompat colorPreferenceCompat4 = this.subtitlesOutlineColor;
        if (colorPreferenceCompat4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesOutlineColor");
            colorPreferenceCompat4 = null;
        }
        colorPreferenceCompat4.saveValue(ContextCompat.getColor(getActivity(), R.color.black));
        SeekBarPreference seekBarPreference5 = this.subtitlesOutlineOpacity;
        if (seekBarPreference5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesOutlineOpacity");
        } else {
            seekBarPreference = seekBarPreference5;
        }
        seekBarPreference.setValue(255);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    @kotlin.Deprecated(message = "Deprecated in Java")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r3, int r4, android.content.Intent r5) {
        /*
            r2 = this;
            super.onActivityResult(r3, r4, r5)
            if (r5 != 0) goto L_0x0006
            return
        L_0x0006:
            r0 = -1
            if (r4 != r0) goto L_0x0052
            java.lang.String r4 = "color_picker_selected_color"
            boolean r0 = r5.hasExtra(r4)
            if (r0 == 0) goto L_0x0052
            r0 = 1
            r1 = 0
            if (r3 == r0) goto L_0x003d
            r0 = 2
            if (r3 == r0) goto L_0x0033
            r0 = 3
            if (r3 == r0) goto L_0x0029
            r0 = 4
            if (r3 == r0) goto L_0x001f
            goto L_0x0048
        L_0x001f:
            com.jaredrummler.android.colorpicker.ColorPreferenceCompat r3 = r2.subtitlesOutlineColor
            if (r3 != 0) goto L_0x0047
            java.lang.String r3 = "subtitlesOutlineColor"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L_0x0048
        L_0x0029:
            com.jaredrummler.android.colorpicker.ColorPreferenceCompat r3 = r2.subtitlesShadowColor
            if (r3 != 0) goto L_0x0047
            java.lang.String r3 = "subtitlesShadowColor"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L_0x0048
        L_0x0033:
            com.jaredrummler.android.colorpicker.ColorPreferenceCompat r3 = r2.subtitlesBackgroundColor
            if (r3 != 0) goto L_0x0047
            java.lang.String r3 = "subtitlesBackgroundColor"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L_0x0048
        L_0x003d:
            com.jaredrummler.android.colorpicker.ColorPreferenceCompat r3 = r2.subtitlesColor
            if (r3 != 0) goto L_0x0047
            java.lang.String r3 = "subtitlesColor"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L_0x0048
        L_0x0047:
            r1 = r3
        L_0x0048:
            if (r1 == 0) goto L_0x0052
            r3 = 0
            int r3 = r5.getIntExtra(r4, r3)
            r1.saveValue(r3)
        L_0x0052:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.preferences.PreferencesSubtitles.onActivityResult(int, int, android.content.Intent):void");
    }

    private final void updatePreferredSubtitleTrack() {
        Settings settings2 = Settings.INSTANCE;
        Activity activity = getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(...)");
        ListPreference listPreference = null;
        String string = ((SharedPreferences) settings2.getInstance(activity)).getString(SettingsKt.SUBTITLE_PREFERRED_LANGUAGE, (String) null);
        CharSequence charSequence = string;
        if (charSequence == null || charSequence.length() == 0) {
            ListPreference listPreference2 = this.preferredSubtitleTrack;
            if (listPreference2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("preferredSubtitleTrack");
            } else {
                listPreference = listPreference2;
            }
            listPreference.setSummary(getString(R.string.no_track_preference));
            return;
        }
        ListPreference listPreference3 = this.preferredSubtitleTrack;
        if (listPreference3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferredSubtitleTrack");
        } else {
            listPreference = listPreference3;
        }
        listPreference.setSummary(getString(R.string.track_preference, new Object[]{string}));
    }

    public void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        Intrinsics.checkNotNull(sharedPreferences);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (str != null) {
            switch (str.hashCode()) {
                case -2046955032:
                    if (!str.equals("subtitles_shadow_color")) {
                        return;
                    }
                    break;
                case -1469878462:
                    if (!str.equals("subtitles_outline_color")) {
                        return;
                    }
                    break;
                case -1397837762:
                    if (!str.equals("subtitle_text_encoding")) {
                        return;
                    }
                    break;
                case -1137660914:
                    if (!str.equals("subtitles_outline_color_opacity")) {
                        return;
                    }
                    break;
                case -1094574871:
                    if (!str.equals("subtitles_bold")) {
                        return;
                    }
                    break;
                case -1094073755:
                    if (!str.equals("subtitles_size")) {
                        return;
                    }
                    break;
                case -899270282:
                    if (!str.equals("subtitles_background_color")) {
                        return;
                    }
                    break;
                case -807319221:
                    if (!str.equals("subtitles_color_opacity")) {
                        return;
                    }
                    break;
                case -642776510:
                    if (!str.equals("subtitles_background_color_opacity")) {
                        return;
                    }
                    break;
                case -550904686:
                    if (!str.equals("subtitles_background")) {
                        return;
                    }
                    break;
                case 319016157:
                    if (str.equals(SettingsKt.SUBTITLE_PREFERRED_LANGUAGE)) {
                        updatePreferredSubtitleTrack();
                        return;
                    }
                    return;
                case 428841343:
                    if (!str.equals("subtitles_color")) {
                        return;
                    }
                    break;
                case 860443268:
                    if (!str.equals("subtitles_shadow")) {
                        return;
                    }
                    break;
                case 1149839540:
                    if (!str.equals("subtitles_shadow_color_opacity")) {
                        return;
                    }
                    break;
                case 2031265858:
                    if (!str.equals("subtitles_outline_size")) {
                        return;
                    }
                    break;
                case 2038848350:
                    if (!str.equals("subtitles_outline")) {
                        return;
                    }
                    break;
                default:
                    return;
            }
            Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PreferencesSubtitles$onSharedPreferenceChanged$1((Continuation<? super PreferencesSubtitles$onSharedPreferenceChanged$1>) null), 3, (Object) null);
            managePreferenceVisibilities();
        }
    }

    private final void managePreferenceVisibilities() {
        SharedPreferences sharedPreferences = this.settings;
        SeekBarPreference seekBarPreference = null;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences = null;
        }
        boolean z = sharedPreferences.getBoolean("subtitles_background", false);
        ColorPreferenceCompat colorPreferenceCompat = this.subtitlesBackgroundColor;
        if (colorPreferenceCompat == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesBackgroundColor");
            colorPreferenceCompat = null;
        }
        colorPreferenceCompat.setVisible(z);
        SeekBarPreference seekBarPreference2 = this.subtitlesBackgroundOpacity;
        if (seekBarPreference2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesBackgroundOpacity");
            seekBarPreference2 = null;
        }
        seekBarPreference2.setVisible(z);
        SharedPreferences sharedPreferences2 = this.settings;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences2 = null;
        }
        boolean z2 = sharedPreferences2.getBoolean("subtitles_shadow", true);
        ColorPreferenceCompat colorPreferenceCompat2 = this.subtitlesShadowColor;
        if (colorPreferenceCompat2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesShadowColor");
            colorPreferenceCompat2 = null;
        }
        colorPreferenceCompat2.setVisible(z2);
        SeekBarPreference seekBarPreference3 = this.subtitlesShadowOpacity;
        if (seekBarPreference3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesShadowOpacity");
            seekBarPreference3 = null;
        }
        seekBarPreference3.setVisible(z2);
        SharedPreferences sharedPreferences3 = this.settings;
        if (sharedPreferences3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences3 = null;
        }
        boolean z3 = sharedPreferences3.getBoolean("subtitles_outline", true);
        ColorPreferenceCompat colorPreferenceCompat3 = this.subtitlesOutlineColor;
        if (colorPreferenceCompat3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesOutlineColor");
            colorPreferenceCompat3 = null;
        }
        colorPreferenceCompat3.setVisible(z3);
        SeekBarPreference seekBarPreference4 = this.subtitlesOutlineOpacity;
        if (seekBarPreference4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subtitlesOutlineOpacity");
        } else {
            seekBarPreference = seekBarPreference4;
        }
        seekBarPreference.setVisible(z3);
    }

    private final void prepareLocaleList() {
        LocaleUtils localeUtils = LocaleUtils.INSTANCE;
        String[] strArr = BuildConfig.TRANSLATION_ARRAY;
        Intrinsics.checkNotNullExpressionValue(strArr, "TRANSLATION_ARRAY");
        String string = getString(R.string.no_track_preference);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        LocalePair localesUsedInProject = localeUtils.getLocalesUsedInProject(strArr, string);
        ListPreference listPreference = this.preferredSubtitleTrack;
        ListPreference listPreference2 = null;
        if (listPreference == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferredSubtitleTrack");
            listPreference = null;
        }
        listPreference.setEntries((CharSequence[]) localesUsedInProject.getLocaleEntries());
        ListPreference listPreference3 = this.preferredSubtitleTrack;
        if (listPreference3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferredSubtitleTrack");
        } else {
            listPreference2 = listPreference3;
        }
        listPreference2.setEntryValues((CharSequence[]) localesUsedInProject.getLocaleEntryValues());
    }
}
