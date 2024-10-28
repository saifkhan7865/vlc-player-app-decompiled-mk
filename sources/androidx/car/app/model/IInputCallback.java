package androidx.car.app.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;

public interface IInputCallback extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.model.IInputCallback";

    public static class Default implements IInputCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onInputSubmitted(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void onInputTextChanged(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }
    }

    void onInputSubmitted(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void onInputTextChanged(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IInputCallback {
        static final int TRANSACTION_onInputSubmitted = 3;
        static final int TRANSACTION_onInputTextChanged = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IInputCallback.DESCRIPTOR);
        }

        public static IInputCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IInputCallback.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IInputCallback)) {
                return new Proxy(iBinder);
            }
            return (IInputCallback) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IInputCallback.DESCRIPTOR);
            }
            if (i != 1598968902) {
                if (i == 2) {
                    onInputTextChanged(parcel.readString(), IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                } else if (i != 3) {
                    return super.onTransact(i, parcel, parcel2, i2);
                } else {
                    onInputSubmitted(parcel.readString(), IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                }
                return true;
            }
            parcel2.writeString(IInputCallback.DESCRIPTOR);
            return true;
        }

        private static class Proxy implements IInputCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IInputCallback.DESCRIPTOR;
            }

            public void onInputTextChanged(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IInputCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onInputSubmitted(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IInputCallback.DESCRIPTOR);
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
