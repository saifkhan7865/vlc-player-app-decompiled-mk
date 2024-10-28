package androidx.car.app.model;

import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.ISearchCallback;
import androidx.car.app.model.SearchTemplate;
import androidx.car.app.serialization.BundlerException;
import androidx.car.app.utils.RemoteUtils;
import j$.util.Objects;

public class SearchCallbackDelegateImpl implements SearchCallbackDelegate {
    private final ISearchCallback mStubCallback;

    public void sendSearchTextChanged(String str, OnDoneCallback onDoneCallback) {
        try {
            ((ISearchCallback) Objects.requireNonNull(this.mStubCallback)).onSearchTextChanged(str, RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendSearchSubmitted(String str, OnDoneCallback onDoneCallback) {
        try {
            ((ISearchCallback) Objects.requireNonNull(this.mStubCallback)).onSearchSubmitted(str, RemoteUtils.createOnDoneCallbackStub(onDoneCallback));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private SearchCallbackDelegateImpl(SearchTemplate.SearchCallback searchCallback) {
        this.mStubCallback = new SearchCallbackStub(searchCallback);
    }

    private SearchCallbackDelegateImpl() {
        this.mStubCallback = null;
    }

    static SearchCallbackDelegate create(SearchTemplate.SearchCallback searchCallback) {
        return new SearchCallbackDelegateImpl(searchCallback);
    }

    private static class SearchCallbackStub extends ISearchCallback.Stub {
        private final SearchTemplate.SearchCallback mCallback;

        SearchCallbackStub(SearchTemplate.SearchCallback searchCallback) {
            this.mCallback = searchCallback;
        }

        public void onSearchTextChanged(String str, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onSearchTextChanged", (RemoteUtils.HostCall) new SearchCallbackDelegateImpl$SearchCallbackStub$$ExternalSyntheticLambda1(this, str));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onSearchTextChanged$0$androidx-car-app-model-SearchCallbackDelegateImpl$SearchCallbackStub  reason: not valid java name */
        public /* synthetic */ Object m46lambda$onSearchTextChanged$0$androidxcarappmodelSearchCallbackDelegateImpl$SearchCallbackStub(String str) throws BundlerException {
            this.mCallback.onSearchTextChanged(str);
            return null;
        }

        public void onSearchSubmitted(String str, IOnDoneCallback iOnDoneCallback) {
            RemoteUtils.dispatchCallFromHost(iOnDoneCallback, "onSearchSubmitted", (RemoteUtils.HostCall) new SearchCallbackDelegateImpl$SearchCallbackStub$$ExternalSyntheticLambda0(this, str));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onSearchSubmitted$1$androidx-car-app-model-SearchCallbackDelegateImpl$SearchCallbackStub  reason: not valid java name */
        public /* synthetic */ Object m45lambda$onSearchSubmitted$1$androidxcarappmodelSearchCallbackDelegateImpl$SearchCallbackStub(String str) throws BundlerException {
            this.mCallback.onSearchSubmitted(str);
            return null;
        }
    }
}
