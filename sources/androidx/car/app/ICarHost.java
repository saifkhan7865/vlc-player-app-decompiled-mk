package androidx.car.app;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public interface ICarHost extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.ICarHost";

    public static class Default implements ICarHost {
        public IBinder asBinder() {
            return null;
        }

        public void finish() throws RemoteException {
        }

        public IBinder getHost(String str) throws RemoteException {
            return null;
        }

        public void startCarApp(Intent intent) throws RemoteException {
        }
    }

    void finish() throws RemoteException;

    IBinder getHost(String str) throws RemoteException;

    void startCarApp(Intent intent) throws RemoteException;

    public static abstract class Stub extends Binder implements ICarHost {
        static final int TRANSACTION_finish = 4;
        static final int TRANSACTION_getHost = 3;
        static final int TRANSACTION_startCarApp = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ICarHost.DESCRIPTOR);
        }

        public static ICarHost asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ICarHost.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ICarHost)) {
                return new Proxy(iBinder);
            }
            return (ICarHost) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ICarHost.DESCRIPTOR);
            }
            if (i != 1598968902) {
                if (i == 2) {
                    startCarApp((Intent) _Parcel.readTypedObject(parcel, Intent.CREATOR));
                    parcel2.writeNoException();
                } else if (i == 3) {
                    IBinder host = getHost(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(host);
                } else if (i != 4) {
                    return super.onTransact(i, parcel, parcel2, i2);
                } else {
                    finish();
                    parcel2.writeNoException();
                }
                return true;
            }
            parcel2.writeString(ICarHost.DESCRIPTOR);
            return true;
        }

        private static class Proxy implements ICarHost {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICarHost.DESCRIPTOR;
            }

            public void startCarApp(Intent intent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICarHost.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, intent, 0);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder getHost(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICarHost.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void finish() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICarHost.DESCRIPTOR);
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
