package androidx.car.app.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;

public interface IOnSelectedListener extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.model.IOnSelectedListener";

    public static class Default implements IOnSelectedListener {
        public IBinder asBinder() {
            return null;
        }

        public void onSelected(int i, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }
    }

    void onSelected(int i, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IOnSelectedListener {
        static final int TRANSACTION_onSelected = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IOnSelectedListener.DESCRIPTOR);
        }

        public static IOnSelectedListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IOnSelectedListener.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOnSelectedListener)) {
                return new Proxy(iBinder);
            }
            return (IOnSelectedListener) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IOnSelectedListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IOnSelectedListener.DESCRIPTOR);
                return true;
            } else if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                onSelected(parcel.readInt(), IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static class Proxy implements IOnSelectedListener {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOnSelectedListener.DESCRIPTOR;
            }

            public void onSelected(int i, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOnSelectedListener.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
