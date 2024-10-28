package androidx.car.app.model;

import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.ITabCallback;
import androidx.car.app.model.TabTemplate;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.RemoteUtils;
import j$.util.Objects;

@RequiresCarApi(6)
public class TabCallbackDelegateImpl implements TabCallbackDelegate {
    private final ITabCallback mStubCallback;

    public void sendTabSelected(String str, OnDoneCallback onDoneCallback) {
        try {
            ((ITabCallback) Objects.requireNonNull(this.mStubCallback)).onTabSelected(str, RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private TabCallbackDelegateImpl(TabTemplate.TabCallback tabCallback) {
        this.mStubCallback = new TabCallbackStub(tabCallback);
    }

    private TabCallbackDelegateImpl() {
        this.mStubCallback = null;
    }

    static TabCallbackDelegate create(TabTemplate.TabCallback tabCallback) {
        return new TabCallbackDelegateImpl(tabCallback);
    }

    private static class TabCallbackStub extends ITabCallback.Stub {
        private final TabTemplate.TabCallback mCallback;

        TabCallbackStub(TabTemplate.TabCallback tabCallback) {
            this.mCallback = tabCallback;
        }

        public void onTabSelected(String str, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onTabSelected", (RemoteUtils.HostCall) new TabCallbackDelegateImpl$TabCallbackStub$$ExternalSyntheticLambda0(this, str));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onTabSelected$0$androidx-car-app-model-TabCallbackDelegateImpl$TabCallbackStub  reason: not valid java name */
        public /* synthetic */ Object m47lambda$onTabSelected$0$androidxcarappmodelTabCallbackDelegateImpl$TabCallbackStub(String str) throws BundlerException {
            this.mCallback.onTabSelected(str);
            return null;
        }
    }
}
