package org.videolan.mobile.app;

import android.app.Application;
import kotlin.Metadata;
import org.videolan.resources.AppContextProvider;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\f\u0010\u0006\u001a\u00020\u0007*\u00020\bH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\t"}, d2 = {"Lorg/videolan/mobile/app/AppDelegate;", "", "appContextProvider", "Lorg/videolan/resources/AppContextProvider;", "getAppContextProvider", "()Lorg/videolan/resources/AppContextProvider;", "setupApplication", "", "Landroid/app/Application;", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AppSetupDelegate.kt */
public interface AppDelegate {
    AppContextProvider getAppContextProvider();

    void setupApplication(Application application);
}
