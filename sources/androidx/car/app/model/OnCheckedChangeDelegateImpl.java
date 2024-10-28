package androidx.car.app.model;

import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.IOnCheckedChangeListener;
import androidx.car.app.model.Toggle;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.RemoteUtils;
import j$.util.Objects;

public class OnCheckedChangeDelegateImpl implements OnCheckedChangeDelegate {
    private final IOnCheckedChangeListener mStub;

    public void sendCheckedChange(boolean z, OnDoneCallback onDoneCallback) {
        try {
            ((IOnCheckedChangeListener) Objects.requireNonNull(this.mStub)).onCheckedChange(z, RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private OnCheckedChangeDelegateImpl(Toggle.OnCheckedChangeListener onCheckedChangeListener) {
        this.mStub = new OnCheckedChangeListenerStub(onCheckedChangeListener);
    }

    private OnCheckedChangeDelegateImpl() {
        this.mStub = null;
    }

    static OnCheckedChangeDelegate create(Toggle.OnCheckedChangeListener onCheckedChangeListener) {
        return new OnCheckedChangeDelegateImpl(onCheckedChangeListener);
    }

    private static class OnCheckedChangeListenerStub extends IOnCheckedChangeListener.Stub {
        private final Toggle.OnCheckedChangeListener mListener;

        OnCheckedChangeListenerStub(Toggle.OnCheckedChangeListener onCheckedChangeListener) {
            this.mListener = onCheckedChangeListener;
        }

        public void onCheckedChange(boolean z, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onCheckedChange", (RemoteUtils.HostCall) new OnCheckedChangeDelegateImpl$OnCheckedChangeListenerStub$$ExternalSyntheticLambda0(this, z));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onCheckedChange$0$androidx-car-app-model-OnCheckedChangeDelegateImpl$OnCheckedChangeListenerStub  reason: not valid java name */
        public /* synthetic */ Object m40lambda$onCheckedChange$0$androidxcarappmodelOnCheckedChangeDelegateImpl$OnCheckedChangeListenerStub(boolean z) throws BundlerException {
            this.mListener.onCheckedChange(z);
            return null;
        }
    }
}
