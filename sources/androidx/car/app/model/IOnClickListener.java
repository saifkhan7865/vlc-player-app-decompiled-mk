package androidx.car.app.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;

public interface IOnClickListener extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.model.IOnClickListener";

    public static class Default implements IOnClickListener {
        public IBinder asBinder() {
            return null;
        }

        public void onClick(IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }
    }

    void onClick(IOnDoneCallback iOnDoneCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IOnClickListener {
        static final int TRANSACTION_onClick = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IOnClickListener.DESCRIPTOR);
        }

        public static IOnClickListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IOnClickListener.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOnClickListener)) {
                return new Proxy(iBinder);
            }
            return (IOnClickListener) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IOnClickListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IOnClickListener.DESCRIPTOR);
                return true;
            } else if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                onClick(IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static class Proxy implements IOnClickListener {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOnClickListener.DESCRIPTOR;
            }

            public void onClick(IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOnClickListener.DESCRIPTOR);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
