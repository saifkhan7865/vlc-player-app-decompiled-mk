package androidx.car.app;

import android.os.RemoteException;

public interface HostCall<ServiceT, ReturnT> {
    ReturnT dispatch(ServiceT servicet) throws RemoteException;
}
