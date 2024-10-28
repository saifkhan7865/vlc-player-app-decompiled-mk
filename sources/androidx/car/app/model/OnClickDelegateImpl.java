package androidx.car.app.model;

import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.IOnClickListener;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.RemoteUtils;
import j$.util.Objects;

public class OnClickDelegateImpl implements OnClickDelegate {
    private final boolean mIsParkedOnly;
    private final IOnClickListener mListener;

    public boolean isParkedOnly() {
        return this.mIsParkedOnly;
    }

    public void sendClick(OnDoneCallback onDoneCallback) {
        try {
            ((IOnClickListener) Objects.requireNonNull(this.mListener)).onClick(RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    static OnClickDelegate create(OnClickListener onClickListener) {
        return new OnClickDelegateImpl(onClickListener, onClickListener instanceof ParkedOnlyOnClickListener);
    }

    private OnClickDelegateImpl(OnClickListener onClickListener, boolean z) {
        this.mListener = new OnClickListenerStub(onClickListener);
        this.mIsParkedOnly = z;
    }

    private OnClickDelegateImpl() {
        this.mListener = null;
        this.mIsParkedOnly = false;
    }

    private static class OnClickListenerStub extends IOnClickListener.Stub {
        private final OnClickListener mOnClickListener;

        OnClickListenerStub(OnClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
        }

        public void onClick(IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onClick", (RemoteUtils.HostCall) new OnClickDelegateImpl$OnClickListenerStub$$ExternalSyntheticLambda0(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onClick$0$androidx-car-app-model-OnClickDelegateImpl$OnClickListenerStub  reason: not valid java name */
        public /* synthetic */ Object m41lambda$onClick$0$androidxcarappmodelOnClickDelegateImpl$OnClickListenerStub() throws BundlerException {
            this.mOnClickListener.onClick();
            return null;
        }
    }
}
