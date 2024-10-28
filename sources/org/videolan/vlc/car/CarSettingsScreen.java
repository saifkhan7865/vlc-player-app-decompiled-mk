package org.videolan.vlc.car;

import android.content.Intent;
import androidx.car.app.CarContext;
import androidx.car.app.CarToast;
import androidx.car.app.Screen;
import androidx.car.app.model.Action;
import androidx.car.app.model.ItemList;
import androidx.car.app.model.ListTemplate;
import androidx.car.app.model.ParkedOnlyOnClickListener;
import androidx.car.app.model.Row;
import androidx.car.app.model.SectionedItemList;
import androidx.car.app.model.Template;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.AppContextProvider;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.preferences.PreferencesActivity;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0001H\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0002¨\u0006\u000e"}, d2 = {"Lorg/videolan/vlc/car/CarSettingsScreen;", "Landroidx/car/app/Screen;", "carContext", "Landroidx/car/app/CarContext;", "(Landroidx/car/app/CarContext;)V", "buildBrowsableRow", "Landroidx/car/app/model/Row;", "title", "", "screen", "onGetTemplate", "Landroidx/car/app/model/Template;", "openPreferencesOnPhone", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CarScreens.kt */
public final class CarSettingsScreen extends Screen {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CarSettingsScreen(CarContext carContext) {
        super(carContext);
        Intrinsics.checkNotNullParameter(carContext, "carContext");
    }

    public Template onGetTemplate() {
        ItemList.Builder builder = new ItemList.Builder();
        int i = R.string.audio;
        CarContext carContext = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext, "getCarContext(...)");
        builder.addItem(buildBrowsableRow(i, new AutoControlScreen(carContext)));
        ItemList build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        ItemList.Builder builder2 = new ItemList.Builder();
        int i2 = R.string.podcast_mode;
        CarContext carContext2 = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext2, "getCarContext(...)");
        builder2.addItem(buildBrowsableRow(i2, new LongMessageScreen(carContext2, R.string.podcast_mode, R.string.podcast_mode_help, Integer.valueOf(R.string.podcast_mode_genres))));
        int i3 = R.string.voice_control;
        CarContext carContext3 = getCarContext();
        Intrinsics.checkNotNullExpressionValue(carContext3, "getCarContext(...)");
        builder2.addItem(buildBrowsableRow(i3, new LongMessageScreen(carContext3, R.string.voice_control, R.string.voice_control_help, (Integer) null, 8, (DefaultConstructorMarker) null)));
        ItemList build2 = builder2.build();
        Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
        ItemList.Builder builder3 = new ItemList.Builder();
        Row.Builder builder4 = new Row.Builder();
        builder4.setTitle((CharSequence) AppContextProvider.INSTANCE.getAppContext().getString(R.string.open_on_phone));
        builder4.setOnClickListener(ParkedOnlyOnClickListener.create(new CarSettingsScreen$$ExternalSyntheticLambda0(this)));
        builder3.addItem(builder4.build());
        ItemList build3 = builder3.build();
        Intrinsics.checkNotNullExpressionValue(build3, "build(...)");
        ListTemplate.Builder builder5 = new ListTemplate.Builder();
        builder5.setHeaderAction(Action.BACK);
        builder5.setTitle(AppContextProvider.INSTANCE.getAppContext().getString(R.string.preferences));
        builder5.addSectionedList(SectionedItemList.create(build, AppContextProvider.INSTANCE.getAppContext().getString(R.string.controls_prefs_category)));
        builder5.addSectionedList(SectionedItemList.create(build2, AppContextProvider.INSTANCE.getAppContext().getString(R.string.help)));
        builder5.addSectionedList(SectionedItemList.create(build3, AppContextProvider.INSTANCE.getAppContext().getString(R.string.more_preferences)));
        ListTemplate build4 = builder5.build();
        Intrinsics.checkNotNullExpressionValue(build4, "build(...)");
        return build4;
    }

    private final Row buildBrowsableRow(int i, Screen screen) {
        Row.Builder builder = new Row.Builder();
        builder.setBrowsable(true);
        builder.setTitle((CharSequence) AppContextProvider.INSTANCE.getAppContext().getString(i));
        builder.setOnClickListener(new CarSettingsScreen$$ExternalSyntheticLambda1(this, screen));
        Row build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    /* access modifiers changed from: private */
    public static final void buildBrowsableRow$lambda$6$lambda$5(CarSettingsScreen carSettingsScreen, Screen screen) {
        Intrinsics.checkNotNullParameter(carSettingsScreen, "this$0");
        Intrinsics.checkNotNullParameter(screen, "$screen");
        carSettingsScreen.getScreenManager().push(screen);
    }

    /* access modifiers changed from: private */
    public final void openPreferencesOnPhone() {
        Intent intent = new Intent(getCarContext(), PreferencesActivity.class);
        intent.addFlags(335544320);
        getCarContext().startActivity(intent);
        CarToast.makeText(getCarContext(), AppContextProvider.INSTANCE.getAppContext().getText(R.string.prefs_opened_on_phone), 0).show();
    }
}
