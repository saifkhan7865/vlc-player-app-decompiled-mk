package androidx.car.app.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;

public interface IOnContentRefreshListener extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.model.IOnContentRefreshListener";

    public static class Default implements IOnContentRefreshListener {
        public IBinder asBinder() {
            return null;
        }

        public void onContentRefreshRequested(IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }
    }

    void onContentRefreshRequested(IOnDoneCallback iOnDoneCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IOnContentRefreshListener {
        static final int TRANSACTION_onContentRefreshRequested = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IOnContentRefreshListener.DESCRIPTOR);
        }

        public static IOnContentRefreshListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IOnContentRefreshListener.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOnContentRefreshListener)) {
                return new Proxy(iBinder);
            }
            return (IOnContentRefreshListener) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IOnContentRefreshListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IOnContentRefreshListener.DESCRIPTOR);
                return true;
            } else if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                onContentRefreshRequested(IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static class Proxy implements IOnContentRefreshListener {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOnContentRefreshListener.DESCRIPTOR;
            }

            public void onContentRefreshRequested(IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOnContentRefreshListener.DESCRIPTOR);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
