package androidx.car.app.model;

import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.IOnSelectedListener;
import androidx.car.app.model.ItemList;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.RemoteUtils;
import j$.util.Objects;

public class OnSelectedDelegateImpl implements OnSelectedDelegate {
    private final IOnSelectedListener mStub;

    public void sendSelected(int i, OnDoneCallback onDoneCallback) {
        try {
            ((IOnSelectedListener) Objects.requireNonNull(this.mStub)).onSelected(i, RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private OnSelectedDelegateImpl(ItemList.OnSelectedListener onSelectedListener) {
        this.mStub = new OnSelectedListenerStub(onSelectedListener);
    }

    private OnSelectedDelegateImpl() {
        this.mStub = null;
    }

    static OnSelectedDelegate create(ItemList.OnSelectedListener onSelectedListener) {
        return new OnSelectedDelegateImpl(onSelectedListener);
    }

    private static class OnSelectedListenerStub extends IOnSelectedListener.Stub {
        private final ItemList.OnSelectedListener mListener;

        OnSelectedListenerStub(ItemList.OnSelectedListener onSelectedListener) {
            this.mListener = onSelectedListener;
        }

        public void onSelected(int i, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onSelectedListener", (RemoteUtils.HostCall) new OnSelectedDelegateImpl$OnSelectedListenerStub$$ExternalSyntheticLambda0(this, i));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onSelected$0$androidx-car-app-model-OnSelectedDelegateImpl$OnSelectedListenerStub  reason: not valid java name */
        public /* synthetic */ Object m44lambda$onSelected$0$androidxcarappmodelOnSelectedDelegateImpl$OnSelectedListenerStub(int i) throws BundlerException {
            this.mListener.onSelected(i);
            return null;
        }
    }
}
