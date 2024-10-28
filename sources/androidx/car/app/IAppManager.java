package androidx.car.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;

public interface IAppManager extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.IAppManager";

    public static class Default implements IAppManager {
        public IBinder asBinder() {
            return null;
        }

        public void getTemplate(IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void onBackPressed(IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void startLocationUpdates(IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void stopLocationUpdates(IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }
    }

    void getTemplate(IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void onBackPressed(IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void startLocationUpdates(IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void stopLocationUpdates(IOnDoneCallback iOnDoneCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IAppManager {
        static final int TRANSACTION_getTemplate = 2;
        static final int TRANSACTION_onBackPressed = 3;
        static final int TRANSACTION_startLocationUpdates = 4;
        static final int TRANSACTION_stopLocationUpdates = 5;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IAppManager.DESCRIPTOR);
        }

        public static IAppManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IAppManager.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAppManager)) {
                return new Proxy(iBinder);
            }
            return (IAppManager) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IAppManager.DESCRIPTOR);
            }
            if (i != 1598968902) {
                if (i == 2) {
                    getTemplate(IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                } else if (i == 3) {
                    onBackPressed(IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                } else if (i == 4) {
                    startLocationUpdates(IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                } else if (i != 5) {
                    return super.onTransact(i, parcel, parcel2, i2);
                } else {
                    stopLocationUpdates(IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                }
                return true;
            }
            parcel2.writeString(IAppManager.DESCRIPTOR);
            return true;
        }

        private static class Proxy implements IAppManager {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAppManager.DESCRIPTOR;
            }

            public void getTemplate(IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onBackPressed(IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void startLocationUpdates(IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(4, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void stopLocationUpdates(IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppManager.DESCRIPTOR);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(5, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
