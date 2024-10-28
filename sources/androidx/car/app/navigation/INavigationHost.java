package androidx.car.app.navigation;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import androidx.car.app.serialization.Bundleable;

public interface INavigationHost extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.navigation.INavigationHost";

    public static class Default implements INavigationHost {
        public IBinder asBinder() {
            return null;
        }

        public void navigationEnded() throws RemoteException {
        }

        public void navigationStarted() throws RemoteException {
        }

        public void updateTrip(Bundleable bundleable) throws RemoteException {
        }
    }

    void navigationEnded() throws RemoteException;

    void navigationStarted() throws RemoteException;

    void updateTrip(Bundleable bundleable) throws RemoteException;

    public static abstract class Stub extends Binder implements INavigationHost {
        static final int TRANSACTION_navigationEnded = 3;
        static final int TRANSACTION_navigationStarted = 2;
        static final int TRANSACTION_updateTrip = 4;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, INavigationHost.DESCRIPTOR);
        }

        public static INavigationHost asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(INavigationHost.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof INavigationHost)) {
                return new Proxy(iBinder);
            }
            return (INavigationHost) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(INavigationHost.DESCRIPTOR);
            }
            if (i != 1598968902) {
                if (i == 2) {
                    navigationStarted();
                    parcel2.writeNoException();
                } else if (i == 3) {
                    navigationEnded();
                    parcel2.writeNoException();
                } else if (i != 4) {
                    return super.onTransact(i, parcel, parcel2, i2);
                } else {
                    updateTrip((Bundleable) _Parcel.readTypedObject(parcel, Bundleable.CREATOR));
                    parcel2.writeNoException();
                }
                return true;
            }
            parcel2.writeString(INavigationHost.DESCRIPTOR);
            return true;
        }

        private static class Proxy implements INavigationHost {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return INavigationHost.DESCRIPTOR;
            }

            public void navigationStarted() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(INavigationHost.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void navigationEnded() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(INavigationHost.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateTrip(Bundleable bundleable) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(INavigationHost.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundleable, 0);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    public static class _Parcel {
        /* access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
                return;
            }
            parcel.writeInt(0);
        }
    }
}
