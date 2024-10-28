package androidx.car.app.navigation;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;

public interface INavigationManager extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.navigation.INavigationManager";

    public static class Default implements INavigationManager {
        public IBinder asBinder() {
            return null;
        }

        public void onStopNavigation(IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }
    }

    void onStopNavigation(IOnDoneCallback iOnDoneCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements INavigationManager {
        static final int TRANSACTION_onStopNavigation = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, INavigationManager.DESCRIPTOR);
        }

        public static INavigationManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(INavigationManager.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof INavigationManager)) {
                return new Proxy(iBinder);
            }
            return (INavigationManager) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(INavigationManager.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(INavigationManager.DESCRIPTOR);
                return true;
            } else if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                onStopNavigation(IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static class Proxy implements INavigationManager {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return INavigationManager.DESCRIPTOR;
            }

            public void onStopNavigation(IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(INavigationManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
