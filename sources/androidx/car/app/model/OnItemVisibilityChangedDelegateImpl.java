package androidx.car.app.model;

import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.IOnItemVisibilityChangedListener;
import androidx.car.app.model.ItemList;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.RemoteUtils;
import j$.util.Objects;

public class OnItemVisibilityChangedDelegateImpl implements OnItemVisibilityChangedDelegate {
    private final IOnItemVisibilityChangedListener mStub;

    public void sendItemVisibilityChanged(int i, int i2, OnDoneCallback onDoneCallback) {
        try {
            ((IOnItemVisibilityChangedListener) Objects.requireNonNull(this.mStub)).onItemVisibilityChanged(i, i2, RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private OnItemVisibilityChangedDelegateImpl(ItemList.OnItemVisibilityChangedListener onItemVisibilityChangedListener) {
        this.mStub = new OnItemVisibilityChangedListenerStub(onItemVisibilityChangedListener);
    }

    private OnItemVisibilityChangedDelegateImpl() {
        this.mStub = null;
    }

    static OnItemVisibilityChangedDelegate create(ItemList.OnItemVisibilityChangedListener onItemVisibilityChangedListener) {
        return new OnItemVisibilityChangedDelegateImpl(onItemVisibilityChangedListener);
    }

    private static class OnItemVisibilityChangedListenerStub extends IOnItemVisibilityChangedListener.Stub {
        private final ItemList.OnItemVisibilityChangedListener mListener;

        OnItemVisibilityChangedListenerStub(ItemList.OnItemVisibilityChangedListener onItemVisibilityChangedListener) {
            this.mListener = onItemVisibilityChangedListener;
        }

        public void onItemVisibilityChanged(int i, int i2, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onItemVisibilityChanged", (RemoteUtils.HostCall) new OnItemVisibilityChangedDelegateImpl$OnItemVisibilityChangedListenerStub$$ExternalSyntheticLambda0(this, i, i2));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onItemVisibilityChanged$0$androidx-car-app-model-OnItemVisibilityChangedDelegateImpl$OnItemVisibilityChangedListenerStub  reason: not valid java name */
        public /* synthetic */ Object m43lambda$onItemVisibilityChanged$0$androidxcarappmodelOnItemVisibilityChangedDelegateImpl$OnItemVisibilityChangedListenerStub(int i, int i2) throws BundlerException {
            this.mListener.onItemVisibilityChanged(i, i2);
            return null;
        }
    }
}
