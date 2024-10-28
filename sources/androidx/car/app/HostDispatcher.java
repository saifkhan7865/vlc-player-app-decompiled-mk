package androidx.car.app;

import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import androidx.car.app.IAppHost;
import androidx.car.app.constraints.IConstraintHost;
import androidx.car.app.navigation.INavigationHost;
import androidx.car.app.suggestion.ISuggestionHost;
import androidx.car.app.utils.LogTags;
import androidx.car.app.utils.RemoteUtils;
import androidx.car.app.utils.ThreadUtils;
import j$.util.Objects;
import java.security.InvalidParameterException;

public final class HostDispatcher {
    private IAppHost mAppHost;
    private ICarHost mCarHost;
    private IConstraintHost mConstraintHost;
    private INavigationHost mNavigationHost;
    private ISuggestionHost mSuggestionHost;

    public <ServiceT, ReturnT> ReturnT dispatchForResult(String str, String str2, HostCall<ServiceT, ReturnT> hostCall) throws RemoteException {
        return RemoteUtils.dispatchCallToHostForResult(str2, new HostDispatcher$$ExternalSyntheticLambda1(this, str, str2, hostCall));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$dispatchForResult$0$androidx-car-app-HostDispatcher  reason: not valid java name */
    public /* synthetic */ Object m29lambda$dispatchForResult$0$androidxcarappHostDispatcher(String str, String str2, HostCall hostCall) throws RemoteException {
        IInterface host = getHost(str);
        if (host != null) {
            return hostCall.dispatch(host);
        }
        Log.e(LogTags.TAG_DISPATCH, "Could not retrieve host while dispatching call " + str2);
        return null;
    }

    public <ServiceT, ReturnT> void dispatch(String str, String str2, HostCall<ServiceT, ReturnT> hostCall) {
        RemoteUtils.dispatchCallToHost(str2, new HostDispatcher$$ExternalSyntheticLambda0(this, str, str2, hostCall));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$dispatch$1$androidx-car-app-HostDispatcher  reason: not valid java name */
    public /* synthetic */ Object m28lambda$dispatch$1$androidxcarappHostDispatcher(String str, String str2, HostCall hostCall) throws RemoteException {
        IInterface host = getHost(str);
        if (host == null) {
            Log.e(LogTags.TAG_DISPATCH, "Could not retrieve host while dispatching call " + str2);
            return null;
        }
        hostCall.dispatch(host);
        return null;
    }

    public void setCarHost(ICarHost iCarHost) {
        ThreadUtils.checkMainThread();
        resetHosts();
        this.mCarHost = iCarHost;
    }

    /* access modifiers changed from: package-private */
    public void resetHosts() {
        ThreadUtils.checkMainThread();
        this.mCarHost = null;
        this.mAppHost = null;
        this.mNavigationHost = null;
    }

    /* access modifiers changed from: package-private */
    public IInterface getHost(String str) throws RemoteException {
        if (this.mCarHost == null) {
            Log.e(LogTags.TAG_DISPATCH, "Host is not bound when attempting to retrieve host service");
            return null;
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1606703562:
                if (str.equals("constraints")) {
                    c = 0;
                    break;
                }
                break;
            case 96801:
                if (str.equals(CarContext.APP_SERVICE)) {
                    c = 1;
                    break;
                }
                break;
            case 98260:
                if (str.equals(CarContext.CAR_SERVICE)) {
                    c = 2;
                    break;
                }
                break;
            case 1197722116:
                if (str.equals(CarContext.SUGGESTION_SERVICE)) {
                    c = 3;
                    break;
                }
                break;
            case 1862666772:
                if (str.equals("navigation")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                if (this.mConstraintHost == null) {
                    this.mConstraintHost = (IConstraintHost) RemoteUtils.dispatchCallToHostForResult("getHost(Constraints)", new HostDispatcher$$ExternalSyntheticLambda3(this));
                }
                return this.mConstraintHost;
            case 1:
                if (this.mAppHost == null) {
                    this.mAppHost = (IAppHost) RemoteUtils.dispatchCallToHostForResult("getHost(App)", new HostDispatcher$$ExternalSyntheticLambda2(this));
                }
                return this.mAppHost;
            case 2:
                return this.mCarHost;
            case 3:
                if (this.mSuggestionHost == null) {
                    this.mSuggestionHost = (ISuggestionHost) RemoteUtils.dispatchCallToHostForResult("getHost(Suggestion)", new HostDispatcher$$ExternalSyntheticLambda4(this));
                }
                return this.mSuggestionHost;
            case 4:
                if (this.mNavigationHost == null) {
                    this.mNavigationHost = (INavigationHost) RemoteUtils.dispatchCallToHostForResult("getHost(Navigation)", new HostDispatcher$$ExternalSyntheticLambda5(this));
                }
                return this.mNavigationHost;
            default:
                throw new InvalidParameterException("Invalid host type: " + str);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getHost$2$androidx-car-app-HostDispatcher  reason: not valid java name */
    public /* synthetic */ IAppHost m30lambda$getHost$2$androidxcarappHostDispatcher() throws RemoteException {
        return IAppHost.Stub.asInterface(((ICarHost) Objects.requireNonNull(this.mCarHost)).getHost(CarContext.APP_SERVICE));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getHost$3$androidx-car-app-HostDispatcher  reason: not valid java name */
    public /* synthetic */ IConstraintHost m31lambda$getHost$3$androidxcarappHostDispatcher() throws RemoteException {
        return IConstraintHost.Stub.asInterface(((ICarHost) Objects.requireNonNull(this.mCarHost)).getHost("constraints"));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getHost$4$androidx-car-app-HostDispatcher  reason: not valid java name */
    public /* synthetic */ ISuggestionHost m32lambda$getHost$4$androidxcarappHostDispatcher() throws RemoteException {
        return ISuggestionHost.Stub.asInterface(((ICarHost) Objects.requireNonNull(this.mCarHost)).getHost(CarContext.SUGGESTION_SERVICE));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getHost$5$androidx-car-app-HostDispatcher  reason: not valid java name */
    public /* synthetic */ INavigationHost m33lambda$getHost$5$androidxcarappHostDispatcher() throws RemoteException {
        return INavigationHost.Stub.asInterface(((ICarHost) Objects.requireNonNull(this.mCarHost)).getHost("navigation"));
    }
}
