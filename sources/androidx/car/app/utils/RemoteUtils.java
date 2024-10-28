package androidx.car.app.utils;

import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import androidx.car.app.FailureResponse;
import androidx.car.app.HostException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.ISurfaceCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.SurfaceCallback;
import androidx.car.app.SurfaceContainer;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.serialization.Bundleable;
import androidx.car.app.serialization.BundlerException;
import androidx.lifecycle.Lifecycle;

public final class RemoteUtils {

    public interface HostCall {
        Object dispatch() throws BundlerException;
    }

    public interface RemoteCall<ReturnT> {
        ReturnT call() throws RemoteException;
    }

    public static <ReturnT> ReturnT dispatchCallToHostForResult(String str, RemoteCall<ReturnT> remoteCall) throws RemoteException {
        try {
            if (Log.isLoggable(LogTags.TAG, 3)) {
                Log.d(LogTags.TAG, "Dispatching call " + str + " to host");
            }
            return remoteCall.call();
        } catch (SecurityException e) {
            throw e;
        } catch (RuntimeException e2) {
            throw new HostException("Remote " + str + " call failed", e2);
        }
    }

    public static void dispatchCallToHost(String str, RemoteCall<?> remoteCall) {
        try {
            dispatchCallToHostForResult(str, remoteCall);
        } catch (RemoteException e) {
            Log.e(LogTags.TAG_DISPATCH, "Host unresponsive when dispatching call " + str, e);
        }
    }

    public static ISurfaceCallback stubSurfaceCallback(Lifecycle lifecycle, SurfaceCallback surfaceCallback) {
        if (surfaceCallback == null) {
            return null;
        }
        return new SurfaceCallbackStub(lifecycle, surfaceCallback);
    }

    public static void dispatchCallFromHost(IOnDoneCallback iOnDoneCallback, String str, HostCall hostCall) {
        ThreadUtils.runOnMain(new RemoteUtils$$ExternalSyntheticLambda3(iOnDoneCallback, str, hostCall));
    }

    static /* synthetic */ void lambda$dispatchCallFromHost$0(IOnDoneCallback iOnDoneCallback, String str, HostCall hostCall) {
        try {
            sendSuccessResponseToHost(iOnDoneCallback, str, hostCall.dispatch());
        } catch (RuntimeException e) {
            sendFailureResponseToHost(iOnDoneCallback, str, e);
            throw new RuntimeException(e);
        } catch (BundlerException e2) {
            sendFailureResponseToHost(iOnDoneCallback, str, e2);
        }
    }

    public static void dispatchCallFromHost(Lifecycle lifecycle, IOnDoneCallback iOnDoneCallback, String str, HostCall hostCall) {
        ThreadUtils.runOnMain(new RemoteUtils$$ExternalSyntheticLambda4(lifecycle, iOnDoneCallback, str, hostCall));
    }

    static /* synthetic */ void lambda$dispatchCallFromHost$1(Lifecycle lifecycle, IOnDoneCallback iOnDoneCallback, String str, HostCall hostCall) {
        if (lifecycle == null || !lifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
            sendFailureResponseToHost(iOnDoneCallback, str, new IllegalStateException("Lifecycle is not at least created when dispatching " + hostCall));
            return;
        }
        dispatchCallFromHost(iOnDoneCallback, str, hostCall);
    }

    public static void dispatchCallFromHost(Lifecycle lifecycle, String str, HostCall hostCall) {
        ThreadUtils.runOnMain(new RemoteUtils$$ExternalSyntheticLambda0(lifecycle, hostCall, str));
    }

    static /* synthetic */ void lambda$dispatchCallFromHost$2(Lifecycle lifecycle, HostCall hostCall, String str) {
        if (lifecycle != null) {
            try {
                if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
                    hostCall.dispatch();
                    return;
                }
            } catch (BundlerException e) {
                Log.e(LogTags.TAG_DISPATCH, "Serialization failure in " + str, e);
                return;
            }
        }
        Log.w(LogTags.TAG_DISPATCH, "Lifecycle is not at least created when dispatching " + hostCall);
    }

    public static void sendSuccessResponseToHost(IOnDoneCallback iOnDoneCallback, String str, Object obj) {
        dispatchCallToHost(str + " onSuccess", new RemoteUtils$$ExternalSyntheticLambda1(iOnDoneCallback, obj, str));
    }

    static /* synthetic */ Object lambda$sendSuccessResponseToHost$3(IOnDoneCallback iOnDoneCallback, Object obj, String str) throws RemoteException {
        Bundleable bundleable;
        if (obj == null) {
            bundleable = null;
        } else {
            try {
                bundleable = Bundleable.create(obj);
            } catch (BundlerException e) {
                sendFailureResponseToHost(iOnDoneCallback, str, e);
            }
        }
        iOnDoneCallback.onSuccess(bundleable);
        return null;
    }

    public static void sendFailureResponseToHost(IOnDoneCallback iOnDoneCallback, String str, Throwable th) {
        dispatchCallToHost(str + " onFailure", new RemoteUtils$$ExternalSyntheticLambda2(iOnDoneCallback, th, str));
    }

    static /* synthetic */ Object lambda$sendFailureResponseToHost$4(IOnDoneCallback iOnDoneCallback, Throwable th, String str) throws RemoteException {
        try {
            iOnDoneCallback.onFailure(Bundleable.create(new FailureResponse(th)));
            return null;
        } catch (BundlerException e) {
            Log.e(LogTags.TAG_DISPATCH, "Serialization failure in " + str, e);
            return null;
        }
    }

    public static IOnDoneCallback createOnDoneCallbackStub(final OnDoneCallback onDoneCallback) {
        return new IOnDoneCallback.Stub() {
            public void onSuccess(Bundleable bundleable) {
                OnDoneCallback.this.onSuccess(bundleable);
            }

            public void onFailure(Bundleable bundleable) {
                OnDoneCallback.this.onFailure(bundleable);
            }
        };
    }

    private static class SurfaceCallbackStub extends ISurfaceCallback.Stub {
        private final Lifecycle mLifecycle;
        private final SurfaceCallback mSurfaceCallback;

        SurfaceCallbackStub(Lifecycle lifecycle, SurfaceCallback surfaceCallback) {
            this.mLifecycle = lifecycle;
            this.mSurfaceCallback = surfaceCallback;
        }

        public void onSurfaceAvailable(Bundleable bundleable, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, iOnDoneCallback, "onSurfaceAvailable", new RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda3(this, bundleable));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onSurfaceAvailable$0$androidx-car-app-utils-RemoteUtils$SurfaceCallbackStub  reason: not valid java name */
        public /* synthetic */ Object m56lambda$onSurfaceAvailable$0$androidxcarapputilsRemoteUtils$SurfaceCallbackStub(Bundleable bundleable) throws BundlerException {
            this.mSurfaceCallback.onSurfaceAvailable((SurfaceContainer) bundleable.get());
            return null;
        }

        public void onVisibleAreaChanged(Rect rect, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, iOnDoneCallback, "onVisibleAreaChanged", new RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda2(this, rect));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onVisibleAreaChanged$1$androidx-car-app-utils-RemoteUtils$SurfaceCallbackStub  reason: not valid java name */
        public /* synthetic */ Object m58lambda$onVisibleAreaChanged$1$androidxcarapputilsRemoteUtils$SurfaceCallbackStub(Rect rect) throws BundlerException {
            this.mSurfaceCallback.onVisibleAreaChanged(rect);
            return null;
        }

        public void onStableAreaChanged(Rect rect, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, iOnDoneCallback, "onStableAreaChanged", new RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda7(this, rect));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onStableAreaChanged$2$androidx-car-app-utils-RemoteUtils$SurfaceCallbackStub  reason: not valid java name */
        public /* synthetic */ Object m55lambda$onStableAreaChanged$2$androidxcarapputilsRemoteUtils$SurfaceCallbackStub(Rect rect) throws BundlerException {
            this.mSurfaceCallback.onStableAreaChanged(rect);
            return null;
        }

        public void onSurfaceDestroyed(Bundleable bundleable, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, iOnDoneCallback, "onSurfaceDestroyed", new RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda0(this, bundleable));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onSurfaceDestroyed$3$androidx-car-app-utils-RemoteUtils$SurfaceCallbackStub  reason: not valid java name */
        public /* synthetic */ Object m57lambda$onSurfaceDestroyed$3$androidxcarapputilsRemoteUtils$SurfaceCallbackStub(Bundleable bundleable) throws BundlerException {
            this.mSurfaceCallback.onSurfaceDestroyed((SurfaceContainer) bundleable.get());
            return null;
        }

        @RequiresCarApi(2)
        public void onScroll(float f, float f2) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, "onScroll", (HostCall) new RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda4(this, f, f2));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onScroll$4$androidx-car-app-utils-RemoteUtils$SurfaceCallbackStub  reason: not valid java name */
        public /* synthetic */ Object m54lambda$onScroll$4$androidxcarapputilsRemoteUtils$SurfaceCallbackStub(float f, float f2) throws BundlerException {
            this.mSurfaceCallback.onScroll(f, f2);
            return null;
        }

        @RequiresCarApi(2)
        public void onFling(float f, float f2) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, "onFling", (HostCall) new RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda5(this, f, f2));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onFling$5$androidx-car-app-utils-RemoteUtils$SurfaceCallbackStub  reason: not valid java name */
        public /* synthetic */ Object m52lambda$onFling$5$androidxcarapputilsRemoteUtils$SurfaceCallbackStub(float f, float f2) throws BundlerException {
            this.mSurfaceCallback.onFling(f, f2);
            return null;
        }

        @RequiresCarApi(2)
        public void onScale(float f, float f2, float f3) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, "onScale", (HostCall) new RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda6(this, f, f2, f3));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onScale$6$androidx-car-app-utils-RemoteUtils$SurfaceCallbackStub  reason: not valid java name */
        public /* synthetic */ Object m53lambda$onScale$6$androidxcarapputilsRemoteUtils$SurfaceCallbackStub(float f, float f2, float f3) throws BundlerException {
            this.mSurfaceCallback.onScale(f, f2, f3);
            return null;
        }

        @RequiresCarApi(5)
        public void onClick(float f, float f2) throws RemoteException {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, "onClick", (HostCall) new RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda1(this, f, f2));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$7$androidx-car-app-utils-RemoteUtils$SurfaceCallbackStub  reason: not valid java name */
        public /* synthetic */ Object m51lambda$onClick$7$androidxcarapputilsRemoteUtils$SurfaceCallbackStub(float f, float f2) throws BundlerException {
            this.mSurfaceCallback.onClick(f, f2);
            return null;
        }
    }

    private RemoteUtils() {
    }
}
