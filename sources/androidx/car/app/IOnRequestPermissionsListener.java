package androidx.car.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IOnRequestPermissionsListener extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.IOnRequestPermissionsListener";

    public static class Default implements IOnRequestPermissionsListener {
        public IBinder asBinder() {
            return null;
        }

        public void onRequestPermissionsResult(String[] strArr, String[] strArr2) throws RemoteException {
        }
    }

    void onRequestPermissionsResult(String[] strArr, String[] strArr2) throws RemoteException;

    public static abstract class Stub extends Binder implements IOnRequestPermissionsListener {
        static final int TRANSACTION_onRequestPermissionsResult = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IOnRequestPermissionsListener.DESCRIPTOR);
        }

        public static IOnRequestPermissionsListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IOnRequestPermissionsListener.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOnRequestPermissionsListener)) {
                return new Proxy(iBinder);
            }
            return (IOnRequestPermissionsListener) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IOnRequestPermissionsListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IOnRequestPermissionsListener.DESCRIPTOR);
                return true;
            } else if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                onRequestPermissionsResult(parcel.createStringArray(), parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
        }

        private static class Proxy implements IOnRequestPermissionsListener {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOnRequestPermissionsListener.DESCRIPTOR;
            }

            public void onRequestPermissionsResult(String[] strArr, String[] strArr2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOnRequestPermissionsListener.DESCRIPTOR);
                    obtain.writeStringArray(strArr);
                    obtain.writeStringArray(strArr2);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
