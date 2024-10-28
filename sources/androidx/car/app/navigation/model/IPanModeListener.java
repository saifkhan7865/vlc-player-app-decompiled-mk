package androidx.car.app.navigation.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;

public interface IPanModeListener extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.navigation.model.IPanModeListener";

    public static class Default implements IPanModeListener {
        public IBinder asBinder() {
            return null;
        }

        public void onPanModeChanged(boolean z, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }
    }

    void onPanModeChanged(boolean z, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IPanModeListener {
        static final int TRANSACTION_onPanModeChanged = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IPanModeListener.DESCRIPTOR);
        }

        public static IPanModeListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IPanModeListener.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IPanModeListener)) {
                return new Proxy(iBinder);
            }
            return (IPanModeListener) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IPanModeListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IPanModeListener.DESCRIPTOR);
                return true;
            } else if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                onPanModeChanged(parcel.readInt() != 0, IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static class Proxy implements IPanModeListener {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPanModeListener.DESCRIPTOR;
            }

            public void onPanModeChanged(boolean z, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IPanModeListener.DESCRIPTOR);
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
