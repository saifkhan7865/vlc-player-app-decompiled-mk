package androidx.car.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.utils.LogTags;
import androidx.car.app.utils.ThreadUtils;
import androidx.car.app.validation.HostValidator;
import j$.util.Objects;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public abstract class CarAppService extends Service {
    private static final String AUTO_DRIVE = "AUTO_DRIVE";
    @Deprecated
    public static final String CATEGORY_CHARGING_APP = "androidx.car.app.category.CHARGING";
    @RequiresCarApi(6)
    public static final String CATEGORY_FEATURE_CLUSTER = "androidx.car.app.category.FEATURE_CLUSTER";
    public static final String CATEGORY_IOT_APP = "androidx.car.app.category.IOT";
    public static final String CATEGORY_MESSAGING_APP = "androidx.car.app.category.MESSAGING";
    public static final String CATEGORY_NAVIGATION_APP = "androidx.car.app.category.NAVIGATION";
    @Deprecated
    public static final String CATEGORY_PARKING_APP = "androidx.car.app.category.PARKING";
    public static final String CATEGORY_POI_APP = "androidx.car.app.category.POI";
    public static final String CATEGORY_SETTINGS_APP = "androidx.car.app.category.SETTINGS";
    public static final String SERVICE_INTERFACE = "androidx.car.app.CarAppService";
    private AppInfo mAppInfo;
    private final Map<SessionInfo, CarAppBinder> mBinders = new HashMap();
    private HostInfo mHostInfo;

    public abstract HostValidator createHostValidator();

    public void onDestroy() {
        synchronized (this.mBinders) {
            for (CarAppBinder destroy : this.mBinders.values()) {
                destroy.destroy();
            }
            this.mBinders.clear();
        }
    }

    public final IBinder onBind(Intent intent) {
        SessionInfo sessionInfo;
        IBinder iBinder;
        if (SessionInfoIntentEncoder.containsSessionInfo(intent)) {
            sessionInfo = SessionInfoIntentEncoder.decode(intent);
        } else {
            sessionInfo = SessionInfo.DEFAULT_SESSION_INFO;
        }
        synchronized (this.mBinders) {
            if (!this.mBinders.containsKey(sessionInfo)) {
                this.mBinders.put(sessionInfo, new CarAppBinder(this, sessionInfo));
            }
            iBinder = (IBinder) Objects.requireNonNull(this.mBinders.get(sessionInfo));
        }
        return iBinder;
    }

    public final boolean onUnbind(Intent intent) {
        SessionInfo sessionInfo;
        if (Log.isLoggable(LogTags.TAG, 3)) {
            Log.d(LogTags.TAG, "onUnbind intent: " + intent);
        }
        if (SessionInfoIntentEncoder.containsSessionInfo(intent)) {
            sessionInfo = SessionInfoIntentEncoder.decode(intent);
        } else {
            sessionInfo = SessionInfo.DEFAULT_SESSION_INFO;
        }
        ThreadUtils.runOnMain(new CarAppService$$ExternalSyntheticLambda1(this, sessionInfo));
        if (!Log.isLoggable(LogTags.TAG, 3)) {
            return true;
        }
        Log.d(LogTags.TAG, "onUnbind completed");
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onUnbind$0$androidx-car-app-CarAppService  reason: not valid java name */
    public /* synthetic */ void m19lambda$onUnbind$0$androidxcarappCarAppService(SessionInfo sessionInfo) {
        synchronized (this.mBinders) {
            CarAppBinder remove = this.mBinders.remove(sessionInfo);
            if (remove != null) {
                remove.onDestroyLifecycle();
            }
        }
    }

    public Session onCreateSession() {
        throw new RuntimeException("Please override and implement CarAppService#onCreateSession(SessionInfo).");
    }

    @RequiresCarApi(6)
    public Session onCreateSession(SessionInfo sessionInfo) {
        return onCreateSession();
    }

    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(fileDescriptor, printWriter, strArr);
        for (String equals : strArr) {
            if (AUTO_DRIVE.equals(equals)) {
                ThreadUtils.runOnMain(new CarAppService$$ExternalSyntheticLambda0(this));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$dump$1$androidx-car-app-CarAppService  reason: not valid java name */
    public /* synthetic */ void m18lambda$dump$1$androidxcarappCarAppService() {
        synchronized (this.mBinders) {
            for (CarAppBinder next : this.mBinders.values()) {
                if (Log.isLoggable(LogTags.TAG, 3)) {
                    Log.d(LogTags.TAG, "Executing onAutoDriveEnabled for " + next.getCurrentSessionInfo());
                }
                next.onAutoDriveEnabled();
            }
        }
    }

    public final HostInfo getHostInfo() {
        return this.mHostInfo;
    }

    /* access modifiers changed from: package-private */
    public void setHostInfo(HostInfo hostInfo) {
        this.mHostInfo = hostInfo;
    }

    @Deprecated
    public final Session getCurrentSession() {
        synchronized (this.mBinders) {
            for (Map.Entry next : this.mBinders.entrySet()) {
                if (((SessionInfo) next.getKey()).getDisplayType() == 0) {
                    Session currentSession = ((CarAppBinder) next.getValue()).getCurrentSession();
                    return currentSession;
                }
            }
            return null;
        }
    }

    public final Session getSession(SessionInfo sessionInfo) {
        synchronized (this.mBinders) {
            CarAppBinder carAppBinder = this.mBinders.get(sessionInfo);
            if (carAppBinder == null) {
                return null;
            }
            Session currentSession = carAppBinder.getCurrentSession();
            return currentSession;
        }
    }

    /* access modifiers changed from: package-private */
    public AppInfo getAppInfo() {
        if (this.mAppInfo == null) {
            this.mAppInfo = AppInfo.create(this);
        }
        return this.mAppInfo;
    }

    /* access modifiers changed from: package-private */
    public void setAppInfo(AppInfo appInfo) {
        this.mAppInfo = appInfo;
    }

    /* access modifiers changed from: package-private */
    public void setBinder(SessionInfo sessionInfo, CarAppBinder carAppBinder) {
        if (carAppBinder == null) {
            this.mBinders.remove(sessionInfo);
        } else {
            this.mBinders.put(sessionInfo, carAppBinder);
        }
    }
}
