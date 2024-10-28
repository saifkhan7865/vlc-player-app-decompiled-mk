package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.AppContextProvider;
import org.videolan.tools.LocaleUtilsKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J.\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J#\u0010\u0015\u001a\u0004\u0018\u0001H\u0016\"\n\b\u0000\u0010\u0016*\u0004\u0018\u00010\u00072\u0006\u0010\u0017\u001a\u00020\u0018H\u0016¢\u0006\u0002\u0010\u0019J\n\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0018H\u0016J\n\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\n\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0010\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u0018H\u0016J\b\u0010#\u001a\u00020\u0005H\u0016J\b\u0010$\u001a\u00020\u0005H\u0016J\b\u0010%\u001a\u00020\u000bH\u0016J\u0012\u0010&\u001a\u00020\u00052\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0012\u0010)\u001a\u00020\u00052\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010,\u001a\u00020\u0005H\u0016J\u0012\u0010-\u001a\u00020\u00052\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010.\u001a\u00020\u0005H\u0016J\u0012\u0010/\u001a\u00020\u00052\b\u00100\u001a\u0004\u0018\u00010+H\u0016J\b\u00101\u001a\u00020\u0005H\u0016J\b\u00102\u001a\u00020\u0005H\u0016J\u0010\u00103\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u0018H\u0016J\u0012\u00104\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u00104\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u00104\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u0018H\u0016J\u0010\u00106\u001a\u00020\u00052\u0006\u00107\u001a\u00020\u000bH\u0016J\u0010\u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00020\u0018H\u0016J\u0012\u0010:\u001a\u00020\u00052\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\u0010\u0010=\u001a\u00020\u00052\u0006\u0010>\u001a\u00020\u0018H\u0016J\u0012\u0010?\u001a\u00020\u00052\b\u0010@\u001a\u0004\u0018\u00010AH\u0016J\u0012\u0010B\u001a\u0004\u0018\u00010C2\u0006\u0010D\u001a\u00020EH\u0016J\u0010\u0010F\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Landroidx/appcompat/app/BaseContextWrappingDelegate;", "Landroidx/appcompat/app/AppCompatDelegate;", "superDelegate", "(Landroidx/appcompat/app/AppCompatDelegate;)V", "addContentView", "", "v", "Landroid/view/View;", "lp", "Landroid/view/ViewGroup$LayoutParams;", "applyDayNight", "", "attachBaseContext2", "Landroid/content/Context;", "context", "createView", "parent", "name", "", "attrs", "Landroid/util/AttributeSet;", "findViewById", "T", "id", "", "(I)Landroid/view/View;", "getDrawerToggleDelegate", "Landroidx/appcompat/app/ActionBarDrawerToggle$Delegate;", "getLocalNightMode", "getMenuInflater", "Landroid/view/MenuInflater;", "getSupportActionBar", "Landroidx/appcompat/app/ActionBar;", "hasWindowFeature", "featureId", "installViewFactory", "invalidateOptionsMenu", "isHandleNativeActionModesEnabled", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPostCreate", "onPostResume", "onSaveInstanceState", "outState", "onStart", "onStop", "requestWindowFeature", "setContentView", "resId", "setHandleNativeActionModesEnabled", "enabled", "setLocalNightMode", "mode", "setSupportActionBar", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "setTheme", "themeResId", "setTitle", "title", "", "startSupportActionMode", "Landroidx/appcompat/view/ActionMode;", "callback", "Landroidx/appcompat/view/ActionMode$Callback;", "wrap", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseContextWrappingDelegate.kt */
public final class BaseContextWrappingDelegate extends AppCompatDelegate {
    private final AppCompatDelegate superDelegate;

    public BaseContextWrappingDelegate(AppCompatDelegate appCompatDelegate) {
        Intrinsics.checkNotNullParameter(appCompatDelegate, "superDelegate");
        this.superDelegate = appCompatDelegate;
    }

    public ActionBar getSupportActionBar() {
        return this.superDelegate.getSupportActionBar();
    }

    public void setSupportActionBar(Toolbar toolbar) {
        this.superDelegate.setSupportActionBar(toolbar);
    }

    public MenuInflater getMenuInflater() {
        return this.superDelegate.getMenuInflater();
    }

    public void onCreate(Bundle bundle) {
        this.superDelegate.onCreate(bundle);
        AppCompatDelegate.removeActivityDelegate(this.superDelegate);
        AppCompatDelegate.addActiveDelegate(this);
    }

    public void onPostCreate(Bundle bundle) {
        this.superDelegate.onPostCreate(bundle);
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.superDelegate.onConfigurationChanged(configuration);
    }

    public void onStart() {
        this.superDelegate.onStart();
    }

    public void onStop() {
        this.superDelegate.onStop();
    }

    public void onPostResume() {
        this.superDelegate.onPostResume();
    }

    public void setTheme(int i) {
        this.superDelegate.setTheme(i);
    }

    public <T extends View> T findViewById(int i) {
        return this.superDelegate.findViewById(i);
    }

    public void setContentView(View view) {
        this.superDelegate.setContentView(view);
    }

    public void setContentView(int i) {
        this.superDelegate.setContentView(i);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.superDelegate.setContentView(view, layoutParams);
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.superDelegate.addContentView(view, layoutParams);
    }

    public Context attachBaseContext2(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Context attachBaseContext2 = this.superDelegate.attachBaseContext2(super.attachBaseContext2(context));
        Intrinsics.checkNotNullExpressionValue(attachBaseContext2, "attachBaseContext2(...)");
        return wrap(attachBaseContext2);
    }

    public void setTitle(CharSequence charSequence) {
        this.superDelegate.setTitle(charSequence);
    }

    public void invalidateOptionsMenu() {
        this.superDelegate.invalidateOptionsMenu();
    }

    public void onDestroy() {
        this.superDelegate.onDestroy();
        AppCompatDelegate.removeActivityDelegate(this);
    }

    public ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
        return this.superDelegate.getDrawerToggleDelegate();
    }

    public boolean requestWindowFeature(int i) {
        return this.superDelegate.requestWindowFeature(i);
    }

    public boolean hasWindowFeature(int i) {
        return this.superDelegate.hasWindowFeature(i);
    }

    public ActionMode startSupportActionMode(ActionMode.Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return this.superDelegate.startSupportActionMode(callback);
    }

    public void installViewFactory() {
        this.superDelegate.installViewFactory();
    }

    public View createView(View view, String str, Context context, AttributeSet attributeSet) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        return this.superDelegate.createView(view, str, context, attributeSet);
    }

    public void setHandleNativeActionModesEnabled(boolean z) {
        this.superDelegate.setHandleNativeActionModesEnabled(z);
    }

    public boolean isHandleNativeActionModesEnabled() {
        return this.superDelegate.isHandleNativeActionModesEnabled();
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.superDelegate.onSaveInstanceState(bundle);
    }

    public boolean applyDayNight() {
        return this.superDelegate.applyDayNight();
    }

    public void setLocalNightMode(int i) {
        this.superDelegate.setLocalNightMode(i);
    }

    public int getLocalNightMode() {
        return this.superDelegate.getLocalNightMode();
    }

    private final Context wrap(Context context) {
        return LocaleUtilsKt.getContextWithLocale(context, AppContextProvider.INSTANCE.getLocale());
    }
}
