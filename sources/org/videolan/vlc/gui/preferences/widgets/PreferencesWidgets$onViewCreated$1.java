package org.videolan.vlc.gui.preferences.widgets;

import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.SeekBarPreference;
import com.google.android.material.color.DynamicColors;
import com.jaredrummler.android.colorpicker.ColorPreferenceCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.view.NumberPickerPreference;
import org.videolan.vlc.mediadb.models.Widget;
import org.videolan.vlc.widget.utils.WidgetType;
import org.videolan.vlc.widget.utils.WidgetUtils;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "widget", "Lorg/videolan/vlc/mediadb/models/Widget;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesWidgets.kt */
final class PreferencesWidgets$onViewCreated$1 extends Lambda implements Function1<Widget, Unit> {
    final /* synthetic */ CheckBoxPreference $configurationIcon;
    final /* synthetic */ ListPreference $themePreference;
    final /* synthetic */ ListPreference $typePreference;
    final /* synthetic */ PreferencesWidgets this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreferencesWidgets$onViewCreated$1(PreferencesWidgets preferencesWidgets, ListPreference listPreference, ListPreference listPreference2, CheckBoxPreference checkBoxPreference) {
        super(1);
        this.this$0 = preferencesWidgets;
        this.$themePreference = listPreference;
        this.$typePreference = listPreference2;
        this.$configurationIcon = checkBoxPreference;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Widget) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Widget widget) {
        if (widget != null) {
            boolean z = true;
            if (!DynamicColors.isDynamicColorAvailable() && widget.getTheme() == 0) {
                widget.setTheme(1);
                this.this$0.updateWidgetEntity();
            }
            this.$themePreference.setValue(String.valueOf(widget.getTheme()));
            this.$typePreference.setValue(String.valueOf(widget.getType()));
            ColorPreferenceCompat access$getBackgroundPreference$p = this.this$0.backgroundPreference;
            CheckBoxPreference checkBoxPreference = null;
            if (access$getBackgroundPreference$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("backgroundPreference");
                access$getBackgroundPreference$p = null;
            }
            access$getBackgroundPreference$p.setVisible(widget.getTheme() != 0);
            ColorPreferenceCompat access$getForegroundPreference$p = this.this$0.foregroundPreference;
            if (access$getForegroundPreference$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("foregroundPreference");
                access$getForegroundPreference$p = null;
            }
            access$getForegroundPreference$p.setVisible(widget.getTheme() != 0);
            ColorPreferenceCompat access$getBackgroundPreference$p2 = this.this$0.backgroundPreference;
            if (access$getBackgroundPreference$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("backgroundPreference");
                access$getBackgroundPreference$p2 = null;
            }
            access$getBackgroundPreference$p2.saveValue(widget.getBackgroundColor());
            ColorPreferenceCompat access$getForegroundPreference$p2 = this.this$0.foregroundPreference;
            if (access$getForegroundPreference$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("foregroundPreference");
                access$getForegroundPreference$p2 = null;
            }
            access$getForegroundPreference$p2.saveValue(widget.getForegroundColor());
            SeekBarPreference seekBarPreference = (SeekBarPreference) this.this$0.findPreference("opacity");
            if (seekBarPreference != null) {
                seekBarPreference.setValue(widget.getOpacity());
            }
            CheckBoxPreference access$getLightThemePreference$p = this.this$0.lightThemePreference;
            if (access$getLightThemePreference$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lightThemePreference");
                access$getLightThemePreference$p = null;
            }
            access$getLightThemePreference$p.setChecked(widget.getLightTheme());
            CheckBoxPreference access$getLightThemePreference$p2 = this.this$0.lightThemePreference;
            if (access$getLightThemePreference$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lightThemePreference");
                access$getLightThemePreference$p2 = null;
            }
            access$getLightThemePreference$p2.setVisible(widget.getTheme() != 2);
            this.$configurationIcon.setChecked(widget.getShowConfigure());
            WidgetType widgetType = WidgetUtils.INSTANCE.getWidgetType(widget);
            boolean z2 = (widgetType == WidgetType.MINI || widgetType == WidgetType.MACRO) && WidgetUtils.INSTANCE.hasEnoughSpaceForSeek(widget, widgetType);
            CheckBoxPreference access$getShowSeek$p = this.this$0.showSeek;
            if (access$getShowSeek$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("showSeek");
                access$getShowSeek$p = null;
            }
            access$getShowSeek$p.setVisible(z2);
            NumberPickerPreference access$getForwardDelay$p = this.this$0.forwardDelay;
            if (access$getForwardDelay$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("forwardDelay");
                access$getForwardDelay$p = null;
            }
            access$getForwardDelay$p.setVisible(z2);
            NumberPickerPreference access$getRewindDelay$p = this.this$0.rewindDelay;
            if (access$getRewindDelay$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rewindDelay");
                access$getRewindDelay$p = null;
            }
            access$getRewindDelay$p.setVisible(z2);
            CheckBoxPreference access$getShowCover$p = this.this$0.showCover;
            if (access$getShowCover$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("showCover");
            } else {
                checkBoxPreference = access$getShowCover$p;
            }
            if (widgetType != WidgetType.MINI) {
                z = false;
            }
            checkBoxPreference.setVisible(z);
        }
    }
}
