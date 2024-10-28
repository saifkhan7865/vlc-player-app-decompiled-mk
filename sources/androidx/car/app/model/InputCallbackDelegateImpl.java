package androidx.car.app.model;

import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.IInputCallback;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.RemoteUtils;
import j$.util.Objects;

public class InputCallbackDelegateImpl implements InputCallbackDelegate {
    private final IInputCallback mCallback;

    public void sendInputSubmitted(String str, OnDoneCallback onDoneCallback) {
        try {
            ((IInputCallback) Objects.requireNonNull(this.mCallback)).onInputSubmitted(str, RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendInputTextChanged(String str, OnDoneCallback onDoneCallback) {
        try {
            ((IInputCallback) Objects.requireNonNull(this.mCallback)).onInputTextChanged(str, RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public static InputCallbackDelegate create(InputCallback inputCallback) {
        return new InputCallbackDelegateImpl((InputCallback) Objects.requireNonNull(inputCallback));
    }

    private InputCallbackDelegateImpl(InputCallback inputCallback) {
        this.mCallback = new OnInputCallbackStub(inputCallback);
    }

    private InputCallbackDelegateImpl() {
        this.mCallback = null;
    }

    private static class OnInputCallbackStub extends IInputCallback.Stub {
        private final InputCallback mCallback;

        OnInputCallbackStub(InputCallback inputCallback) {
            this.mCallback = inputCallback;
        }

        public void onInputSubmitted(String str, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onInputSubmitted", (RemoteUtils.HostCall) new InputCallbackDelegateImpl$OnInputCallbackStub$$ExternalSyntheticLambda1(this, str));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onInputSubmitted$0$androidx-car-app-model-InputCallbackDelegateImpl$OnInputCallbackStub  reason: not valid java name */
        public /* synthetic */ Object m38lambda$onInputSubmitted$0$androidxcarappmodelInputCallbackDelegateImpl$OnInputCallbackStub(String str) throws BundlerException {
            this.mCallback.onInputSubmitted(str);
            return null;
        }

        public void onInputTextChanged(String str, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onInputTextChanged", (RemoteUtils.HostCall) new InputCallbackDelegateImpl$OnInputCallbackStub$$ExternalSyntheticLambda0(this, str));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onInputTextChanged$1$androidx-car-app-model-InputCallbackDelegateImpl$OnInputCallbackStub  reason: not valid java name */
        public /* synthetic */ Object m39lambda$onInputTextChanged$1$androidxcarappmodelInputCallbackDelegateImpl$OnInputCallbackStub(String str) throws BundlerException {
            this.mCallback.onInputTextChanged(str);
            return null;
        }
    }
}
