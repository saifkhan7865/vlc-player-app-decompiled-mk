package androidx.car.app;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public interface IStartCarApp extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.IStartCarApp";

    public static class Default implements IStartCarApp {
        public IBinder asBinder() {
            return null;
        }

        public void startCarApp(Intent intent) throws RemoteException {
        }
    }

    void startCarApp(Intent intent) throws RemoteException;

    public static abstract class Stub extends Binder implements IStartCarApp {
        static final int TRANSACTION_startCarApp = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IStartCarApp.DESCRIPTOR);
        }

        public static IStartCarApp asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IStartCarApp.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IStartCarApp)) {
                return new Proxy(iBinder);
            }
            return (IStartCarApp) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IStartCarApp.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IStartCarApp.DESCRIPTOR);
                return true;
            } else if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                startCarApp((Intent) _Parcel.readTypedObject(parcel, Intent.CREATOR));
                parcel2.writeNoException();
                return true;
            }
        }

        private static class Proxy implements IStartCarApp {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IStartCarApp.DESCRIPTOR;
            }

            public void startCarApp(Intent intent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IStartCarApp.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, intent, 0);
                    this.mRemote.transact(2, obtain, obtain2, 0);
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
