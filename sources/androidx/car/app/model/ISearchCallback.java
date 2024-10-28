package androidx.car.app.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;

public interface ISearchCallback extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.model.ISearchCallback";

    public static class Default implements ISearchCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onSearchSubmitted(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void onSearchTextChanged(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }
    }

    void onSearchSubmitted(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void onSearchTextChanged(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ISearchCallback {
        static final int TRANSACTION_onSearchSubmitted = 3;
        static final int TRANSACTION_onSearchTextChanged = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ISearchCallback.DESCRIPTOR);
        }

        public static ISearchCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISearchCallback.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ISearchCallback)) {
                return new Proxy(iBinder);
            }
            return (ISearchCallback) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ISearchCallback.DESCRIPTOR);
            }
            if (i != 1598968902) {
                if (i == 2) {
                    onSearchTextChanged(parcel.readString(), IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                } else if (i != 3) {
                    return super.onTransact(i, parcel, parcel2, i2);
                } else {
                    onSearchSubmitted(parcel.readString(), IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                }
                return true;
            }
            parcel2.writeString(ISearchCallback.DESCRIPTOR);
            return true;
        }

        private static class Proxy implements ISearchCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISearchCallback.DESCRIPTOR;
            }

            public void onSearchTextChanged(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISearchCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onSearchSubmitted(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISearchCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
