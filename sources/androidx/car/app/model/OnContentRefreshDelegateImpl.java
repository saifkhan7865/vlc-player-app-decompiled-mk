package androidx.car.app.model;

import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.IOnContentRefreshListener;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.RemoteUtils;
import j$.util.Objects;

public class OnContentRefreshDelegateImpl implements OnContentRefreshDelegate {
    private final IOnContentRefreshListener mListener;

    public void sendContentRefreshRequested(OnDoneCallback onDoneCallback) {
        try {
            ((IOnContentRefreshListener) Objects.requireNonNull(this.mListener)).onContentRefreshRequested(RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public static OnContentRefreshDelegate create(OnContentRefreshListener onContentRefreshListener) {
        return new OnContentRefreshDelegateImpl(onContentRefreshListener);
    }

    private OnContentRefreshDelegateImpl(OnContentRefreshListener onContentRefreshListener) {
        this.mListener = new OnContentRefreshListenerStub(onContentRefreshListener);
    }

    private OnContentRefreshDelegateImpl() {
        this.mListener = null;
    }

    private static class OnContentRefreshListenerStub extends IOnContentRefreshListener.Stub {
        private final OnContentRefreshListener mOnContentRefreshListener;

        OnContentRefreshListenerStub(OnContentRefreshListener onContentRefreshListener) {
            this.mOnContentRefreshListener = onContentRefreshListener;
        }

        public void onContentRefreshRequested(IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onClick", (RemoteUtils.HostCall) new OnContentRefreshDelegateImpl$OnContentRefreshListenerStub$$ExternalSyntheticLambda0(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onContentRefreshRequested$0$androidx-car-app-model-OnContentRefreshDelegateImpl$OnContentRefreshListenerStub  reason: not valid java name */
        public /* synthetic */ Object m42lambda$onContentRefreshRequested$0$androidxcarappmodelOnContentRefreshDelegateImpl$OnContentRefreshListenerStub() throws BundlerException {
            this.mOnContentRefreshListener.onContentRefreshRequested();
            return null;
        }
    }
}
