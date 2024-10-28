package androidx.car.app.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;

public interface ITabCallback extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.model.ITabCallback";

    public static class Default implements ITabCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onTabSelected(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }
    }

    void onTabSelected(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ITabCallback {
        static final int TRANSACTION_onTabSelected = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ITabCallback.DESCRIPTOR);
        }

        public static ITabCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ITabCallback.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ITabCallback)) {
                return new Proxy(iBinder);
            }
            return (ITabCallback) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ITabCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ITabCallback.DESCRIPTOR);
                return true;
            } else if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                onTabSelected(parcel.readString(), IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static class Proxy implements ITabCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ITabCallback.DESCRIPTOR;
            }

            public void onTabSelected(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ITabCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
