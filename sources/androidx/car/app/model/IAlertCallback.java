package androidx.car.app.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;

public interface IAlertCallback extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.model.IAlertCallback";

    public static class Default implements IAlertCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onAlertCancelled(int i, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void onAlertDismissed(IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }
    }

    void onAlertCancelled(int i, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void onAlertDismissed(IOnDoneCallback iOnDoneCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IAlertCallback {
        static final int TRANSACTION_onAlertCancelled = 2;
        static final int TRANSACTION_onAlertDismissed = 3;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IAlertCallback.DESCRIPTOR);
        }

        public static IAlertCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IAlertCallback.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAlertCallback)) {
                return new Proxy(iBinder);
            }
            return (IAlertCallback) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IAlertCallback.DESCRIPTOR);
            }
            if (i != 1598968902) {
                if (i == 2) {
                    onAlertCancelled(parcel.readInt(), IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                } else if (i != 3) {
                    return super.onTransact(i, parcel, parcel2, i2);
                } else {
                    onAlertDismissed(IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                }
                return true;
            }
            parcel2.writeString(IAlertCallback.DESCRIPTOR);
            return true;
        }

        private static class Proxy implements IAlertCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAlertCallback.DESCRIPTOR;
            }

            public void onAlertCancelled(int i, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAlertCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onAlertDismissed(IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAlertCallback.DESCRIPTOR);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
