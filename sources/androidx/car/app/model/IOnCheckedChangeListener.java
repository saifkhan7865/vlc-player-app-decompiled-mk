package androidx.car.app.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;

public interface IOnCheckedChangeListener extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.model.IOnCheckedChangeListener";

    public static class Default implements IOnCheckedChangeListener {
        public IBinder asBinder() {
            return null;
        }

        public void onCheckedChange(boolean z, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }
    }

    void onCheckedChange(boolean z, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IOnCheckedChangeListener {
        static final int TRANSACTION_onCheckedChange = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IOnCheckedChangeListener.DESCRIPTOR);
        }

        public static IOnCheckedChangeListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IOnCheckedChangeListener.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOnCheckedChangeListener)) {
                return new Proxy(iBinder);
            }
            return (IOnCheckedChangeListener) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IOnCheckedChangeListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IOnCheckedChangeListener.DESCRIPTOR);
                return true;
            } else if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                onCheckedChange(parcel.readInt() != 0, IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static class Proxy implements IOnCheckedChangeListener {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOnCheckedChangeListener.DESCRIPTOR;
            }

            public void onCheckedChange(boolean z, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOnCheckedChangeListener.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
