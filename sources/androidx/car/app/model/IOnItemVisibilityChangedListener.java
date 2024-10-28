package androidx.car.app.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;

public interface IOnItemVisibilityChangedListener extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.model.IOnItemVisibilityChangedListener";

    public static class Default implements IOnItemVisibilityChangedListener {
        public IBinder asBinder() {
            return null;
        }

        public void onItemVisibilityChanged(int i, int i2, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }
    }

    void onItemVisibilityChanged(int i, int i2, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IOnItemVisibilityChangedListener {
        static final int TRANSACTION_onItemVisibilityChanged = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IOnItemVisibilityChangedListener.DESCRIPTOR);
        }

        public static IOnItemVisibilityChangedListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IOnItemVisibilityChangedListener.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOnItemVisibilityChangedListener)) {
                return new Proxy(iBinder);
            }
            return (IOnItemVisibilityChangedListener) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IOnItemVisibilityChangedListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IOnItemVisibilityChangedListener.DESCRIPTOR);
                return true;
            } else if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                onItemVisibilityChanged(parcel.readInt(), parcel.readInt(), IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static class Proxy implements IOnItemVisibilityChangedListener {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOnItemVisibilityChangedListener.DESCRIPTOR;
            }

            public void onItemVisibilityChanged(int i, int i2, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOnItemVisibilityChangedListener.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
