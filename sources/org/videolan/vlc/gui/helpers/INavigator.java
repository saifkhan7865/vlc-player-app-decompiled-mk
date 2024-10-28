package org.videolan.vlc.gui.helpers;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationBarView;
import java.util.List;
import kotlin.Metadata;
import org.videolan.vlc.gui.MainActivity;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\tH&J\b\u0010\u0018\u001a\u00020\u0019H&J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001cH&J\b\u0010\u001d\u001a\u00020\u0016H&J\u0016\u0010\u001e\u001a\u00020\u0016*\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H&R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\tX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX¦\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\""}, d2 = {"Lorg/videolan/vlc/gui/helpers/INavigator;", "", "appbarLayout", "Lcom/google/android/material/appbar/AppBarLayout;", "getAppbarLayout", "()Lcom/google/android/material/appbar/AppBarLayout;", "setAppbarLayout", "(Lcom/google/android/material/appbar/AppBarLayout;)V", "currentFragmentId", "", "getCurrentFragmentId", "()I", "setCurrentFragmentId", "(I)V", "navigationView", "", "Lcom/google/android/material/navigation/NavigationBarView;", "getNavigationView", "()Ljava/util/List;", "setNavigationView", "(Ljava/util/List;)V", "configurationChanged", "", "size", "currentIdIsExtension", "", "getFragmentWidth", "activity", "Landroid/app/Activity;", "reloadPreferences", "setupNavigation", "Lorg/videolan/vlc/gui/MainActivity;", "state", "Landroid/os/Bundle;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Navigator.kt */
public interface INavigator {
    void configurationChanged(int i);

    boolean currentIdIsExtension();

    AppBarLayout getAppbarLayout();

    int getCurrentFragmentId();

    int getFragmentWidth(Activity activity);

    List<NavigationBarView> getNavigationView();

    void reloadPreferences();

    void setAppbarLayout(AppBarLayout appBarLayout);

    void setCurrentFragmentId(int i);

    void setNavigationView(List<? extends NavigationBarView> list);

    void setupNavigation(MainActivity mainActivity, Bundle bundle);
}
