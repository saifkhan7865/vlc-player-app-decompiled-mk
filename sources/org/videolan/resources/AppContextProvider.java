package org.videolan.resources;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.LocaleUtilsKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\u0004J\u0010\u0010\u0019\u001a\u00020\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u0013J\u0006\u0010\u001b\u001a\u00020\u0018R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001c"}, d2 = {"Lorg/videolan/resources/AppContextProvider;", "", "()V", "appContext", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "appResources", "Landroid/content/res/Resources;", "getAppResources", "()Landroid/content/res/Resources;", "context", "currentActivity", "Landroid/app/Activity;", "getCurrentActivity", "()Landroid/app/Activity;", "setCurrentActivity", "(Landroid/app/Activity;)V", "<set-?>", "", "locale", "getLocale", "()Ljava/lang/String;", "init", "", "setLocale", "newLocale", "updateContext", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AppContextProvider.kt */
public final class AppContextProvider {
    public static final AppContextProvider INSTANCE = new AppContextProvider();
    private static Context context;
    private static Activity currentActivity;
    private static String locale = "";

    private AppContextProvider() {
    }

    public final Activity getCurrentActivity() {
        return currentActivity;
    }

    public final void setCurrentActivity(Activity activity) {
        currentActivity = activity;
    }

    public final String getLocale() {
        return locale;
    }

    public final void setLocale(String str) {
        locale = str;
        updateContext();
    }

    public final Context getAppContext() {
        Context context2 = context;
        if (context2 == null) {
            try {
                Object invoke = Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication", (Class[]) null).invoke((Object) null, (Object[]) null);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                context = (Application) invoke;
            } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            }
            Context context3 = context;
            if (context3 != null) {
                return context3;
            }
            Intrinsics.throwUninitializedPropertyAccessException("context");
        } else if (context2 != null) {
            return context2;
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return null;
    }

    public final void init(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        context = context2;
    }

    public final void updateContext() {
        String str = locale;
        CharSequence charSequence = str;
        if (!(!(charSequence == null || charSequence.length() == 0))) {
            str = null;
        }
        if (str != null) {
            AppContextProvider appContextProvider = INSTANCE;
            appContextProvider.init(LocaleUtilsKt.wrap(new ContextWrapper(appContextProvider.getAppContext()), str));
        }
    }

    public final Resources getAppResources() {
        Resources resources = getAppContext().getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "getResources(...)");
        return resources;
    }
}
