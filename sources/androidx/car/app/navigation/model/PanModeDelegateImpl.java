package androidx.car.app.navigation.model;

import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.navigation.model.IPanModeListener;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.RemoteUtils;
import j$.util.Objects;

public class PanModeDelegateImpl implements PanModeDelegate {
    private final IPanModeListener mStub;

    public void sendPanModeChanged(boolean z, OnDoneCallback onDoneCallback) {
        try {
            ((IPanModeListener) Objects.requireNonNull(this.mStub)).onPanModeChanged(z, RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private PanModeDelegateImpl(PanModeListener panModeListener) {
        this.mStub = new PanModeListenerStub(panModeListener);
    }

    private PanModeDelegateImpl() {
        this.mStub = null;
    }

    static PanModeDelegate create(PanModeListener panModeListener) {
        return new PanModeDelegateImpl(panModeListener);
    }

    private static class PanModeListenerStub extends IPanModeListener.Stub {
        private final PanModeListener mListener;

        PanModeListenerStub(PanModeListener panModeListener) {
            this.mListener = panModeListener;
        }

        public void onPanModeChanged(boolean z, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onPanModeChanged", (RemoteUtils.HostCall) new PanModeDelegateImpl$PanModeListenerStub$$ExternalSyntheticLambda0(this, z));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onPanModeChanged$0$androidx-car-app-navigation-model-PanModeDelegateImpl$PanModeListenerStub  reason: not valid java name */
        public /* synthetic */ Object m50lambda$onPanModeChanged$0$androidxcarappnavigationmodelPanModeDelegateImpl$PanModeListenerStub(boolean z) throws BundlerException {
            this.mListener.onPanModeChanged(z);
            return null;
        }
    }
}
