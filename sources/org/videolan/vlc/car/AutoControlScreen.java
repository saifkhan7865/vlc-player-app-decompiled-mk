package org.videolan.vlc.car;

import android.content.SharedPreferences;
import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.ListTemplate;
import androidx.car.app.model.Row;
import androidx.car.app.model.Template;
import androidx.car.app.model.Toggle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.AppContextProvider;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J.\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0010"}, d2 = {"Lorg/videolan/vlc/car/AutoControlScreen;", "Landroidx/car/app/Screen;", "carContext", "Landroidx/car/app/CarContext;", "(Landroidx/car/app/CarContext;)V", "buildToggleRow", "Landroidx/car/app/model/Row;", "titleRes", "", "summaryRes", "key", "", "defValue", "", "onGetTemplate", "Landroidx/car/app/model/Template;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CarScreens.kt */
public final class AutoControlScreen extends Screen {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AutoControlScreen(CarContext carContext) {
        super(carContext);
        Intrinsics.checkNotNullParameter(carContext, "carContext");
    }

    public Template onGetTemplate() {
        ItemList.Builder builder = new ItemList.Builder();
        builder.addItem(buildToggleRow$default(this, R.string.playback_speed_title, R.string.playback_speed_summary, SettingsKt.KEY_PLAYBACK_SPEED_PERSIST, false, 8, (Object) null));
        builder.addItem(buildToggleRow$default(this, R.string.enable_android_auto_speed_buttons, R.string.enable_android_auto_speed_buttons_summary, SettingsKt.ENABLE_ANDROID_AUTO_SPEED_BUTTONS, false, 8, (Object) null));
        builder.addItem(buildToggleRow$default(this, R.string.enable_android_auto_seek_buttons, R.string.enable_android_auto_seek_buttons_summary, SettingsKt.ENABLE_ANDROID_AUTO_SEEK_BUTTONS, false, 8, (Object) null));
        ItemList build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        ListTemplate.Builder builder2 = new ListTemplate.Builder();
        builder2.setSingleList(build);
        builder2.setHeaderAction(Action.BACK);
        builder2.setTitle(AppContextProvider.INSTANCE.getAppContext().getString(R.string.audio));
        ListTemplate build2 = builder2.build();
        Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
        return build2;
    }

    static /* synthetic */ Row buildToggleRow$default(AutoControlScreen autoControlScreen, int i, int i2, String str, boolean z, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            z = false;
        }
        return autoControlScreen.buildToggleRow(i, i2, str, z);
    }

    private final Row buildToggleRow(int i, int i2, String str, boolean z) {
        Settings settings = Settings.INSTANCE;
        CarContext carContext = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext, "getCarContext(...)");
        SharedPreferences sharedPreferences = (SharedPreferences) settings.getInstance(carContext);
        AutoControlScreen$$ExternalSyntheticLambda0 autoControlScreen$$ExternalSyntheticLambda0 = new AutoControlScreen$$ExternalSyntheticLambda0(sharedPreferences, str);
        Row.Builder builder = new Row.Builder();
        builder.setTitle((CharSequence) AppContextProvider.INSTANCE.getAppContext().getString(i));
        builder.addText((CharSequence) AppContextProvider.INSTANCE.getAppContext().getString(i2));
        Toggle.Builder builder2 = new Toggle.Builder(autoControlScreen$$ExternalSyntheticLambda0);
        builder2.setChecked(sharedPreferences.getBoolean(str, z));
        builder.setToggle(builder2.build());
        Row build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    /* access modifiers changed from: private */
    public static final void buildToggleRow$lambda$3(SharedPreferences sharedPreferences, String str, boolean z) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "$settings");
        Intrinsics.checkNotNullParameter(str, "$key");
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(str, z);
        edit.apply();
        PlaybackService.Companion.updateState();
    }
}
