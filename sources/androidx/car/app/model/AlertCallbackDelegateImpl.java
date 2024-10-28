package androidx.car.app.model;

import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.IAlertCallback;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.RemoteUtils;
import j$.util.Objects;

public class AlertCallbackDelegateImpl implements AlertCallbackDelegate {
    private final IAlertCallback mCallback;

    static AlertCallbackDelegate create(AlertCallback alertCallback) {
        return new AlertCallbackDelegateImpl(alertCallback);
    }

    private AlertCallbackDelegateImpl(AlertCallback alertCallback) {
        this.mCallback = new AlertCallbackStub(alertCallback);
    }

    private AlertCallbackDelegateImpl() {
        this.mCallback = null;
    }

    public void sendCancel(int i, OnDoneCallback onDoneCallback) {
        try {
            ((IAlertCallback) Objects.requireNonNull(this.mCallback)).onAlertCancelled(i, RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendDismiss(OnDoneCallback onDoneCallback) {
        try {
            ((IAlertCallback) Objects.requireNonNull(this.mCallback)).onAlertDismissed(RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private static class AlertCallbackStub extends IAlertCallback.Stub {
        private final AlertCallback mCallback;

        AlertCallbackStub(AlertCallback alertCallback) {
            this.mCallback = alertCallback;
        }

        public void onAlertCancelled(int i, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onCancel", (RemoteUtils.HostCall) new AlertCallbackDelegateImpl$AlertCallbackStub$$ExternalSyntheticLambda1(this, i));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onAlertCancelled$0$androidx-car-app-model-AlertCallbackDelegateImpl$AlertCallbackStub  reason: not valid java name */
        public /* synthetic */ Object m36lambda$onAlertCancelled$0$androidxcarappmodelAlertCallbackDelegateImpl$AlertCallbackStub(int i) throws BundlerException {
            this.mCallback.onCancel(i);
            return null;
        }

        public void onAlertDismissed(IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onDismiss", (RemoteUtils.HostCall) new AlertCallbackDelegateImpl$AlertCallbackStub$$ExternalSyntheticLambda0(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onAlertDismissed$1$androidx-car-app-model-AlertCallbackDelegateImpl$AlertCallbackStub  reason: not valid java name */
        public /* synthetic */ Object m37lambda$onAlertDismissed$1$androidxcarappmodelAlertCallbackDelegateImpl$AlertCallbackStub() throws BundlerException {
            this.mCallback.onDismiss();
            return null;
        }
    }
}
