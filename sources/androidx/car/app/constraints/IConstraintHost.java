package androidx.car.app.constraints;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IConstraintHost extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.constraints.IConstraintHost";

    public static class Default implements IConstraintHost {
        public IBinder asBinder() {
            return null;
        }

        public int getContentLimit(int i) throws RemoteException {
            return 0;
        }

        public boolean isAppDrivenRefreshEnabled() throws RemoteException {
            return false;
        }
    }

    int getContentLimit(int i) throws RemoteException;

    boolean isAppDrivenRefreshEnabled() throws RemoteException;

    public static abstract class Stub extends Binder implements IConstraintHost {
        static final int TRANSACTION_getContentLimit = 2;
        static final int TRANSACTION_isAppDrivenRefreshEnabled = 3;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IConstraintHost.DESCRIPTOR);
        }

        public static IConstraintHost asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IConstraintHost.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IConstraintHost)) {
                return new Proxy(iBinder);
            }
            return (IConstraintHost) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IConstraintHost.DESCRIPTOR);
            }
            if (i != 1598968902) {
                if (i == 2) {
                    int contentLimit = getContentLimit(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(contentLimit);
                } else if (i != 3) {
                    return super.onTransact(i, parcel, parcel2, i2);
                } else {
                    boolean isAppDrivenRefreshEnabled = isAppDrivenRefreshEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(isAppDrivenRefreshEnabled ? 1 : 0);
                }
                return true;
            }
            parcel2.writeString(IConstraintHost.DESCRIPTOR);
            return true;
        }

        private static class Proxy implements IConstraintHost {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IConstraintHost.DESCRIPTOR;
            }

            public int getContentLimit(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IConstraintHost.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isAppDrivenRefreshEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IConstraintHost.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
