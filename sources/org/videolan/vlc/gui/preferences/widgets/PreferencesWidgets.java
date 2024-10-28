package org.videolan.vlc.gui.preferences.widgets;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.google.android.material.color.DynamicColors;
import com.jaredrummler.android.colorpicker.ColorPreferenceCompat;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.resources.Constants;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.preferences.BasePreferenceFragment;
import org.videolan.vlc.gui.view.NumberPickerPreference;
import org.videolan.vlc.mediadb.models.Widget;
import org.videolan.vlc.widget.WidgetViewModel;

@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0019\u001a\u00020\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u001aH\u0014J\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u001c\u0010 \u001a\u00020\u001d2\b\u0010!\u001a\u0004\u0018\u00010\u00162\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u001dH\u0016J\b\u0010%\u001a\u00020\u001dH\u0016J\u001a\u0010&\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020(2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u0018\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u001aH\u0002J\u0010\u0010-\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020+H\u0002J\b\u0010.\u001a\u00020\u001dH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\rX.¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lorg/videolan/vlc/gui/preferences/widgets/PreferencesWidgets;", "Lorg/videolan/vlc/gui/preferences/BasePreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "()V", "backgroundPreference", "Lcom/jaredrummler/android/colorpicker/ColorPreferenceCompat;", "foregroundPreference", "forwardDelay", "Lorg/videolan/vlc/gui/view/NumberPickerPreference;", "jsonAdapter", "Lcom/squareup/moshi/JsonAdapter;", "Lorg/videolan/vlc/gui/preferences/widgets/SavedColors;", "lightThemePreference", "Landroidx/preference/CheckBoxPreference;", "model", "Lorg/videolan/vlc/widget/WidgetViewModel;", "getModel$vlc_android_release", "()Lorg/videolan/vlc/widget/WidgetViewModel;", "setModel$vlc_android_release", "(Lorg/videolan/vlc/widget/WidgetViewModel;)V", "rewindDelay", "settings", "Landroid/content/SharedPreferences;", "showCover", "showSeek", "getTitleId", "", "getXml", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSharedPreferenceChanged", "sharedPreferences", "key", "", "onStart", "onStop", "onViewCreated", "view", "Landroid/view/View;", "saveNewColor", "foreground", "", "newColor", "updateSavedColors", "updateWidgetEntity", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesWidgets.kt */
public final class PreferencesWidgets extends BasePreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    /* access modifiers changed from: private */
    public ColorPreferenceCompat backgroundPreference;
    /* access modifiers changed from: private */
    public ColorPreferenceCompat foregroundPreference;
    /* access modifiers changed from: private */
    public NumberPickerPreference forwardDelay;
    private JsonAdapter<SavedColors> jsonAdapter;
    /* access modifiers changed from: private */
    public CheckBoxPreference lightThemePreference;
    public WidgetViewModel model;
    /* access modifiers changed from: private */
    public NumberPickerPreference rewindDelay;
    private SharedPreferences settings;
    /* access modifiers changed from: private */
    public CheckBoxPreference showCover;
    /* access modifiers changed from: private */
    public CheckBoxPreference showSeek;

    public final WidgetViewModel getModel$vlc_android_release() {
        WidgetViewModel widgetViewModel = this.model;
        if (widgetViewModel != null) {
            return widgetViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("model");
        return null;
    }

    public final void setModel$vlc_android_release(WidgetViewModel widgetViewModel) {
        Intrinsics.checkNotNullParameter(widgetViewModel, "<set-?>");
        this.model = widgetViewModel;
    }

    /* access modifiers changed from: protected */
    public int getXml() {
        return R.xml.preferences_widgets;
    }

    /* access modifiers changed from: protected */
    public int getTitleId() {
        return R.string.widget_preferences;
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

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Moshi build = new Moshi.Builder().build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        JsonAdapter<SavedColors> adapter = build.adapter(SavedColors.class);
        Intrinsics.checkNotNullExpressionValue(adapter, "adapter(...)");
        this.jsonAdapter = adapter;
        Settings settings2 = Settings.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        this.settings = (SharedPreferences) settings2.getInstance(requireActivity);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Preference findPreference = findPreference("background_color");
        Intrinsics.checkNotNull(findPreference);
        this.backgroundPreference = (ColorPreferenceCompat) findPreference;
        Preference findPreference2 = findPreference("foreground_color");
        Intrinsics.checkNotNull(findPreference2);
        this.foregroundPreference = (ColorPreferenceCompat) findPreference2;
        Preference findPreference3 = findPreference("widget_light_theme");
        Intrinsics.checkNotNull(findPreference3);
        this.lightThemePreference = (CheckBoxPreference) findPreference3;
        Preference findPreference4 = findPreference("widget_show_cover");
        Intrinsics.checkNotNull(findPreference4);
        this.showCover = (CheckBoxPreference) findPreference4;
        Preference findPreference5 = findPreference("widget_show_seek");
        Intrinsics.checkNotNull(findPreference5);
        this.showSeek = (CheckBoxPreference) findPreference5;
        Preference findPreference6 = findPreference("widget_forward_delay");
        Intrinsics.checkNotNull(findPreference6);
        this.forwardDelay = (NumberPickerPreference) findPreference6;
        Preference findPreference7 = findPreference("widget_rewind_delay");
        Intrinsics.checkNotNull(findPreference7);
        this.rewindDelay = (NumberPickerPreference) findPreference7;
        Preference findPreference8 = findPreference("widget_show_configure");
        Intrinsics.checkNotNull(findPreference8);
        CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference8;
        Preference findPreference9 = findPreference("widget_theme");
        Intrinsics.checkNotNull(findPreference9);
        ListPreference listPreference = (ListPreference) findPreference9;
        Preference findPreference10 = findPreference("widget_type");
        Intrinsics.checkNotNull(findPreference10);
        ListPreference listPreference2 = (ListPreference) findPreference10;
        Bundle arguments = getArguments();
        int i = arguments != null ? arguments.getInt(PreferencesWidgetsKt.WIDGET_ID) : -2;
        if (i != -2) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            setModel$vlc_android_release((WidgetViewModel) new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) new WidgetViewModel.Factory(requireActivity, i)).get(WidgetViewModel.class));
            updateSavedColors(true);
            updateSavedColors(false);
            getModel$vlc_android_release().getWidget().observe(requireActivity(), new PreferencesWidgetsKt$sam$androidx_lifecycle_Observer$0(new PreferencesWidgets$onViewCreated$1(this, listPreference, listPreference2, checkBoxPreference)));
            if (!DynamicColors.isDynamicColorAvailable()) {
                CharSequence[] entryValues = listPreference.getEntryValues();
                Intrinsics.checkNotNullExpressionValue(entryValues, "getEntryValues(...)");
                Collection arrayList = new ArrayList();
                for (Object obj : (Object[]) entryValues) {
                    if (!Intrinsics.areEqual((Object) (CharSequence) obj, (Object) Constants.GROUP_VIDEOS_FOLDER)) {
                        arrayList.add(obj);
                    }
                }
                listPreference.setEntryValues((CharSequence[]) ((List) arrayList).toArray(new CharSequence[0]));
                CharSequence[] entries = listPreference.getEntries();
                Intrinsics.checkNotNullExpressionValue(entries, "getEntries(...)");
                Collection arrayList2 = new ArrayList();
                for (Object obj2 : (Object[]) entries) {
                    if (!Intrinsics.areEqual((Object) (CharSequence) obj2, (Object) getString(R.string.material_you))) {
                        arrayList2.add(obj2);
                    }
                }
                listPreference.setEntries((CharSequence[]) ((List) arrayList2).toArray(new CharSequence[0]));
                return;
            }
            return;
        }
        throw new IllegalStateException("Invalid widget id");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onSharedPreferenceChanged(android.content.SharedPreferences r11, java.lang.String r12) {
        /*
            r10 = this;
            if (r11 == 0) goto L_0x028a
            if (r12 != 0) goto L_0x0006
            goto L_0x028a
        L_0x0006:
            int r0 = r12.hashCode()
            java.lang.String r1 = "0"
            r2 = 10
            r3 = 0
            r4 = 1
            switch(r0) {
                case -1902461593: goto L_0x0257;
                case -1649651137: goto L_0x0235;
                case -1267206133: goto L_0x0210;
                case -941708082: goto L_0x01eb;
                case -923027371: goto L_0x0140;
                case 180691839: goto L_0x011b;
                case 385960144: goto L_0x00f6;
                case 1126881253: goto L_0x00d1;
                case 1254928154: goto L_0x00ac;
                case 1450405902: goto L_0x0049;
                case 2036780306: goto L_0x0015;
                default: goto L_0x0013;
            }
        L_0x0013:
            goto L_0x0287
        L_0x0015:
            java.lang.String r0 = "background_color"
            boolean r0 = r12.equals(r0)
            if (r0 != 0) goto L_0x001f
            goto L_0x0287
        L_0x001f:
            androidx.fragment.app.FragmentActivity r0 = r10.requireActivity()
            android.content.Context r0 = (android.content.Context) r0
            int r1 = org.videolan.vlc.R.color.black
            int r0 = androidx.core.content.ContextCompat.getColor(r0, r1)
            int r11 = r11.getInt(r12, r0)
            r10.saveNewColor(r3, r11)
            org.videolan.vlc.widget.WidgetViewModel r12 = r10.getModel$vlc_android_release()
            androidx.lifecycle.LiveData r12 = r12.getWidget()
            java.lang.Object r12 = r12.getValue()
            org.videolan.vlc.mediadb.models.Widget r12 = (org.videolan.vlc.mediadb.models.Widget) r12
            if (r12 != 0) goto L_0x0044
            goto L_0x0287
        L_0x0044:
            r12.setBackgroundColor(r11)
            goto L_0x0287
        L_0x0049:
            java.lang.String r0 = "widget_theme"
            boolean r0 = r12.equals(r0)
            if (r0 != 0) goto L_0x0053
            goto L_0x0287
        L_0x0053:
            java.lang.String r11 = r11.getString(r12, r1)
            if (r11 == 0) goto L_0x005e
            int r11 = java.lang.Integer.parseInt(r11)
            goto L_0x005f
        L_0x005e:
            r11 = 0
        L_0x005f:
            org.videolan.vlc.widget.WidgetViewModel r12 = r10.getModel$vlc_android_release()
            androidx.lifecycle.LiveData r12 = r12.getWidget()
            java.lang.Object r12 = r12.getValue()
            org.videolan.vlc.mediadb.models.Widget r12 = (org.videolan.vlc.mediadb.models.Widget) r12
            if (r12 != 0) goto L_0x0070
            goto L_0x0073
        L_0x0070:
            r12.setTheme(r11)
        L_0x0073:
            com.jaredrummler.android.colorpicker.ColorPreferenceCompat r12 = r10.backgroundPreference
            r0 = 0
            if (r12 != 0) goto L_0x007e
            java.lang.String r12 = "backgroundPreference"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r12 = r0
        L_0x007e:
            r1 = 2
            if (r11 != r1) goto L_0x0083
            r2 = 1
            goto L_0x0084
        L_0x0083:
            r2 = 0
        L_0x0084:
            r12.setVisible(r2)
            com.jaredrummler.android.colorpicker.ColorPreferenceCompat r12 = r10.foregroundPreference
            if (r12 != 0) goto L_0x0091
            java.lang.String r12 = "foregroundPreference"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r12 = r0
        L_0x0091:
            if (r11 != r1) goto L_0x0095
            r2 = 1
            goto L_0x0096
        L_0x0095:
            r2 = 0
        L_0x0096:
            r12.setVisible(r2)
            androidx.preference.CheckBoxPreference r12 = r10.lightThemePreference
            if (r12 != 0) goto L_0x00a3
            java.lang.String r12 = "lightThemePreference"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            goto L_0x00a4
        L_0x00a3:
            r0 = r12
        L_0x00a4:
            if (r11 == r1) goto L_0x00a7
            r3 = 1
        L_0x00a7:
            r0.setVisible(r3)
            goto L_0x0287
        L_0x00ac:
            java.lang.String r0 = "widget_rewind_delay"
            boolean r0 = r12.equals(r0)
            if (r0 != 0) goto L_0x00b6
            goto L_0x0287
        L_0x00b6:
            int r11 = r11.getInt(r12, r2)
            org.videolan.vlc.widget.WidgetViewModel r12 = r10.getModel$vlc_android_release()
            androidx.lifecycle.LiveData r12 = r12.getWidget()
            java.lang.Object r12 = r12.getValue()
            org.videolan.vlc.mediadb.models.Widget r12 = (org.videolan.vlc.mediadb.models.Widget) r12
            if (r12 != 0) goto L_0x00cc
            goto L_0x0287
        L_0x00cc:
            r12.setRewindDelay(r11)
            goto L_0x0287
        L_0x00d1:
            java.lang.String r0 = "widget_light_theme"
            boolean r0 = r12.equals(r0)
            if (r0 != 0) goto L_0x00db
            goto L_0x0287
        L_0x00db:
            boolean r11 = r11.getBoolean(r12, r4)
            org.videolan.vlc.widget.WidgetViewModel r12 = r10.getModel$vlc_android_release()
            androidx.lifecycle.LiveData r12 = r12.getWidget()
            java.lang.Object r12 = r12.getValue()
            org.videolan.vlc.mediadb.models.Widget r12 = (org.videolan.vlc.mediadb.models.Widget) r12
            if (r12 != 0) goto L_0x00f1
            goto L_0x0287
        L_0x00f1:
            r12.setLightTheme(r11)
            goto L_0x0287
        L_0x00f6:
            java.lang.String r0 = "widget_show_cover"
            boolean r0 = r12.equals(r0)
            if (r0 != 0) goto L_0x0100
            goto L_0x0287
        L_0x0100:
            boolean r11 = r11.getBoolean(r12, r4)
            org.videolan.vlc.widget.WidgetViewModel r12 = r10.getModel$vlc_android_release()
            androidx.lifecycle.LiveData r12 = r12.getWidget()
            java.lang.Object r12 = r12.getValue()
            org.videolan.vlc.mediadb.models.Widget r12 = (org.videolan.vlc.mediadb.models.Widget) r12
            if (r12 != 0) goto L_0x0116
            goto L_0x0287
        L_0x0116:
            r12.setShowCover(r11)
            goto L_0x0287
        L_0x011b:
            java.lang.String r0 = "widget_show_configure"
            boolean r0 = r12.equals(r0)
            if (r0 != 0) goto L_0x0125
            goto L_0x0287
        L_0x0125:
            boolean r11 = r11.getBoolean(r12, r4)
            org.videolan.vlc.widget.WidgetViewModel r12 = r10.getModel$vlc_android_release()
            androidx.lifecycle.LiveData r12 = r12.getWidget()
            java.lang.Object r12 = r12.getValue()
            org.videolan.vlc.mediadb.models.Widget r12 = (org.videolan.vlc.mediadb.models.Widget) r12
            if (r12 != 0) goto L_0x013b
            goto L_0x0287
        L_0x013b:
            r12.setShowConfigure(r11)
            goto L_0x0287
        L_0x0140:
            java.lang.String r0 = "widget_type"
            boolean r0 = r12.equals(r0)
            if (r0 != 0) goto L_0x014a
            goto L_0x0287
        L_0x014a:
            java.lang.String r11 = r11.getString(r12, r1)
            if (r11 == 0) goto L_0x0154
            int r3 = java.lang.Integer.parseInt(r11)
        L_0x0154:
            org.videolan.vlc.widget.WidgetViewModel r11 = r10.getModel$vlc_android_release()
            androidx.lifecycle.LiveData r11 = r11.getWidget()
            java.lang.Object r11 = r11.getValue()
            org.videolan.vlc.mediadb.models.Widget r11 = (org.videolan.vlc.mediadb.models.Widget) r11
            if (r11 != 0) goto L_0x0165
            goto L_0x0168
        L_0x0165:
            r11.setType(r3)
        L_0x0168:
            org.videolan.vlc.widget.WidgetViewModel r11 = r10.getModel$vlc_android_release()
            androidx.lifecycle.LiveData r11 = r11.getWidget()
            java.lang.Object r11 = r11.getValue()
            org.videolan.vlc.mediadb.models.Widget r11 = (org.videolan.vlc.mediadb.models.Widget) r11
            if (r11 == 0) goto L_0x0287
            org.videolan.vlc.widget.utils.WidgetSizeUtil r12 = org.videolan.vlc.widget.utils.WidgetSizeUtil.INSTANCE
            androidx.fragment.app.FragmentActivity r0 = r10.requireActivity()
            java.lang.String r1 = "requireActivity(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            android.content.Context r0 = (android.content.Context) r0
            int r2 = r11.getWidgetId()
            kotlin.Pair r12 = r12.getWidgetsSize(r0, r2)
            org.videolan.vlc.widget.utils.WidgetUtils r0 = org.videolan.vlc.widget.utils.WidgetUtils.INSTANCE
            org.videolan.vlc.widget.utils.WidgetUtils r2 = org.videolan.vlc.widget.utils.WidgetUtils.INSTANCE
            org.videolan.vlc.widget.utils.WidgetType r11 = r2.getWidgetType(r11)
            kotlin.Pair r11 = r0.getMinimalWidgetSize(r11)
            java.lang.Object r0 = r12.getFirst()
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            java.lang.Object r2 = r11.getFirst()
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            if (r0 < r2) goto L_0x01c5
            java.lang.Object r12 = r12.getSecond()
            java.lang.Number r12 = (java.lang.Number) r12
            int r12 = r12.intValue()
            java.lang.Object r11 = r11.getSecond()
            java.lang.Number r11 = (java.lang.Number) r11
            int r11 = r11.intValue()
            if (r12 >= r11) goto L_0x0287
        L_0x01c5:
            org.videolan.vlc.gui.helpers.UiTools r2 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            androidx.fragment.app.FragmentActivity r11 = r10.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r1)
            r3 = r11
            android.app.Activity r3 = (android.app.Activity) r3
            int r11 = org.videolan.vlc.R.string.widget_type_error
            java.lang.String r4 = r10.getString(r11)
            java.lang.String r11 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r11)
            org.videolan.vlc.gui.preferences.widgets.PreferencesWidgets$onSharedPreferenceChanged$1$1 r11 = org.videolan.vlc.gui.preferences.widgets.PreferencesWidgets$onSharedPreferenceChanged$1$1.INSTANCE
            r7 = r11
            kotlin.jvm.functions.Function0 r7 = (kotlin.jvm.functions.Function0) r7
            r8 = 12
            r9 = 0
            r5 = 0
            r6 = 0
            org.videolan.vlc.gui.helpers.UiTools.snackerConfirm$default(r2, r3, r4, r5, r6, r7, r8, r9)
            goto L_0x0287
        L_0x01eb:
            java.lang.String r0 = "widget_forward_delay"
            boolean r0 = r12.equals(r0)
            if (r0 != 0) goto L_0x01f5
            goto L_0x0287
        L_0x01f5:
            int r11 = r11.getInt(r12, r2)
            org.videolan.vlc.widget.WidgetViewModel r12 = r10.getModel$vlc_android_release()
            androidx.lifecycle.LiveData r12 = r12.getWidget()
            java.lang.Object r12 = r12.getValue()
            org.videolan.vlc.mediadb.models.Widget r12 = (org.videolan.vlc.mediadb.models.Widget) r12
            if (r12 != 0) goto L_0x020b
            goto L_0x0287
        L_0x020b:
            r12.setForwardDelay(r11)
            goto L_0x0287
        L_0x0210:
            java.lang.String r0 = "opacity"
            boolean r0 = r12.equals(r0)
            if (r0 != 0) goto L_0x021a
            goto L_0x0287
        L_0x021a:
            org.videolan.vlc.widget.WidgetViewModel r0 = r10.getModel$vlc_android_release()
            androidx.lifecycle.LiveData r0 = r0.getWidget()
            java.lang.Object r0 = r0.getValue()
            org.videolan.vlc.mediadb.models.Widget r0 = (org.videolan.vlc.mediadb.models.Widget) r0
            if (r0 != 0) goto L_0x022b
            goto L_0x0287
        L_0x022b:
            r1 = 100
            int r11 = r11.getInt(r12, r1)
            r0.setOpacity(r11)
            goto L_0x0287
        L_0x0235:
            java.lang.String r0 = "widget_show_seek"
            boolean r0 = r12.equals(r0)
            if (r0 != 0) goto L_0x023e
            goto L_0x0287
        L_0x023e:
            boolean r11 = r11.getBoolean(r12, r4)
            org.videolan.vlc.widget.WidgetViewModel r12 = r10.getModel$vlc_android_release()
            androidx.lifecycle.LiveData r12 = r12.getWidget()
            java.lang.Object r12 = r12.getValue()
            org.videolan.vlc.mediadb.models.Widget r12 = (org.videolan.vlc.mediadb.models.Widget) r12
            if (r12 != 0) goto L_0x0253
            goto L_0x0287
        L_0x0253:
            r12.setShowSeek(r11)
            goto L_0x0287
        L_0x0257:
            java.lang.String r0 = "foreground_color"
            boolean r0 = r12.equals(r0)
            if (r0 != 0) goto L_0x0260
            goto L_0x0287
        L_0x0260:
            androidx.fragment.app.FragmentActivity r0 = r10.requireActivity()
            android.content.Context r0 = (android.content.Context) r0
            int r1 = org.videolan.vlc.R.color.white
            int r0 = androidx.core.content.ContextCompat.getColor(r0, r1)
            int r11 = r11.getInt(r12, r0)
            r10.saveNewColor(r4, r11)
            org.videolan.vlc.widget.WidgetViewModel r12 = r10.getModel$vlc_android_release()
            androidx.lifecycle.LiveData r12 = r12.getWidget()
            java.lang.Object r12 = r12.getValue()
            org.videolan.vlc.mediadb.models.Widget r12 = (org.videolan.vlc.mediadb.models.Widget) r12
            if (r12 != 0) goto L_0x0284
            goto L_0x0287
        L_0x0284:
            r12.setForegroundColor(r11)
        L_0x0287:
            r10.updateWidgetEntity()
        L_0x028a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.preferences.widgets.PreferencesWidgets.onSharedPreferenceChanged(android.content.SharedPreferences, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0016  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0019  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void saveNewColor(boolean r9, int r10) {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto L_0x000a
            com.jaredrummler.android.colorpicker.ColorPreferenceCompat r1 = r8.foregroundPreference
            if (r1 != 0) goto L_0x0014
            java.lang.String r1 = "foregroundPreference"
            goto L_0x0010
        L_0x000a:
            com.jaredrummler.android.colorpicker.ColorPreferenceCompat r1 = r8.backgroundPreference
            if (r1 != 0) goto L_0x0014
            java.lang.String r1 = "backgroundPreference"
        L_0x0010:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r1 = r0
        L_0x0014:
            if (r9 == 0) goto L_0x0019
            java.lang.String r2 = "widgets_foreground_last_colors"
            goto L_0x001b
        L_0x0019:
            java.lang.String r2 = "widgets_background_last_colors"
        L_0x001b:
            int[] r1 = r1.getPresets()
            java.lang.String r3 = "getPresets(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            boolean r1 = kotlin.collections.ArraysKt.contains((int[]) r1, (int) r10)
            if (r1 != 0) goto L_0x00b0
            android.content.SharedPreferences r1 = r8.settings
            java.lang.String r3 = "settings"
            if (r1 != 0) goto L_0x0034
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r1 = r0
        L_0x0034:
            java.lang.String r4 = ""
            java.lang.String r1 = r1.getString(r2, r4)
            r4 = r1
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            java.lang.String r5 = "jsonAdapter"
            if (r4 == 0) goto L_0x0065
            boolean r4 = kotlin.text.StringsKt.isBlank(r4)
            if (r4 == 0) goto L_0x0048
            goto L_0x0065
        L_0x0048:
            java.util.ArrayList r4 = new java.util.ArrayList
            com.squareup.moshi.JsonAdapter<org.videolan.vlc.gui.preferences.widgets.SavedColors> r6 = r8.jsonAdapter
            if (r6 != 0) goto L_0x0052
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r6 = r0
        L_0x0052:
            java.lang.Object r1 = r6.fromJson((java.lang.String) r1)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            org.videolan.vlc.gui.preferences.widgets.SavedColors r1 = (org.videolan.vlc.gui.preferences.widgets.SavedColors) r1
            java.util.List r1 = r1.getColors()
            java.util.Collection r1 = (java.util.Collection) r1
            r4.<init>(r1)
            goto L_0x006a
        L_0x0065:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
        L_0x006a:
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r1 = 0
            r4.add(r1, r10)
            org.videolan.vlc.gui.preferences.widgets.SavedColors r10 = new org.videolan.vlc.gui.preferences.widgets.SavedColors
            int r6 = r4.size()
            r7 = 5
            if (r6 <= r7) goto L_0x0087
            java.util.List r4 = (java.util.List) r4
            kotlin.ranges.IntRange r6 = new kotlin.ranges.IntRange
            r6.<init>(r1, r7)
            java.util.List r1 = kotlin.collections.CollectionsKt.slice(r4, (kotlin.ranges.IntRange) r6)
            goto L_0x008a
        L_0x0087:
            r1 = r4
            java.util.List r1 = (java.util.List) r1
        L_0x008a:
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.List r1 = kotlin.collections.CollectionsKt.distinct(r1)
            r10.<init>(r1)
            android.content.SharedPreferences r1 = r8.settings
            if (r1 != 0) goto L_0x009b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r1 = r0
        L_0x009b:
            com.squareup.moshi.JsonAdapter<org.videolan.vlc.gui.preferences.widgets.SavedColors> r3 = r8.jsonAdapter
            if (r3 != 0) goto L_0x00a3
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            goto L_0x00a4
        L_0x00a3:
            r0 = r3
        L_0x00a4:
            java.lang.String r10 = r0.toJson(r10)
            java.lang.String r0 = "toJson(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r0)
            org.videolan.tools.SettingsKt.putSingle(r1, r2, r10)
        L_0x00b0:
            r8.updateSavedColors(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.preferences.widgets.PreferencesWidgets.saveNewColor(boolean, int):void");
    }

    private final void updateSavedColors(boolean z) {
        ArrayList arrayList;
        ColorPreferenceCompat colorPreferenceCompat;
        String str;
        SharedPreferences sharedPreferences = this.settings;
        ColorPreferenceCompat colorPreferenceCompat2 = null;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences = null;
        }
        String string = sharedPreferences.getString(z ? SettingsKt.WIDGETS_FOREGROUND_LAST_COLORS : SettingsKt.WIDGETS_BACKGROUND_LAST_COLORS, "");
        CharSequence charSequence = string;
        if (charSequence == null || StringsKt.isBlank(charSequence)) {
            arrayList = new ArrayList();
        } else {
            JsonAdapter<SavedColors> jsonAdapter2 = this.jsonAdapter;
            if (jsonAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("jsonAdapter");
                jsonAdapter2 = null;
            }
            SavedColors fromJson = jsonAdapter2.fromJson(string);
            Intrinsics.checkNotNull(fromJson);
            arrayList = new ArrayList(fromJson.getColors());
        }
        if (z) {
            colorPreferenceCompat = this.foregroundPreference;
            if (colorPreferenceCompat == null) {
                str = "foregroundPreference";
            }
            colorPreferenceCompat2 = colorPreferenceCompat;
            int[] presets = colorPreferenceCompat2.getPresets();
            Intrinsics.checkNotNullExpressionValue(presets, "getPresets(...)");
            List<Integer> mutableList = ArraysKt.toMutableList(presets);
            mutableList.addAll(arrayList);
            colorPreferenceCompat2.setPresets(CollectionsKt.toIntArray(CollectionsKt.distinct(mutableList)));
        }
        colorPreferenceCompat = this.backgroundPreference;
        if (colorPreferenceCompat == null) {
            str = "backgroundPreference";
        }
        colorPreferenceCompat2 = colorPreferenceCompat;
        int[] presets2 = colorPreferenceCompat2.getPresets();
        Intrinsics.checkNotNullExpressionValue(presets2, "getPresets(...)");
        List<Integer> mutableList2 = ArraysKt.toMutableList(presets2);
        mutableList2.addAll(arrayList);
        colorPreferenceCompat2.setPresets(CollectionsKt.toIntArray(CollectionsKt.distinct(mutableList2)));
        Intrinsics.throwUninitializedPropertyAccessException(str);
        int[] presets22 = colorPreferenceCompat2.getPresets();
        Intrinsics.checkNotNullExpressionValue(presets22, "getPresets(...)");
        List<Integer> mutableList22 = ArraysKt.toMutableList(presets22);
        mutableList22.addAll(arrayList);
        colorPreferenceCompat2.setPresets(CollectionsKt.toIntArray(CollectionsKt.distinct(mutableList22)));
    }

    /* access modifiers changed from: private */
    public final void updateWidgetEntity() {
        Widget value = getModel$vlc_android_release().getWidget().getValue();
        if (value != null) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new PreferencesWidgets$updateWidgetEntity$1$1(this, value, (Continuation<? super PreferencesWidgets$updateWidgetEntity$1$1>) null), 3, (Object) null);
        }
    }
}
