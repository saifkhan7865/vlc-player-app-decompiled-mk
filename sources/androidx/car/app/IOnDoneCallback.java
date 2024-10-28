package androidx.car.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import androidx.car.app.serialization.Bundleable;

public interface IOnDoneCallback extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.IOnDoneCallback";

    public static class Default implements IOnDoneCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onFailure(Bundleable bundleable) throws RemoteException {
        }

        public void onSuccess(Bundleable bundleable) throws RemoteException {
        }
    }

    void onFailure(Bundleable bundleable) throws RemoteException;

    void onSuccess(Bundleable bundleable) throws RemoteException;

    public static abstract class Stub extends Binder implements IOnDoneCallback {
        static final int TRANSACTION_onFailure = 3;
        static final int TRANSACTION_onSuccess = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IOnDoneCallback.DESCRIPTOR);
        }

        public static IOnDoneCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IOnDoneCallback.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOnDoneCallback)) {
                return new Proxy(iBinder);
            }
            return (IOnDoneCallback) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IOnDoneCallback.DESCRIPTOR);
            }
            if (i != 1598968902) {
                if (i == 2) {
                    onSuccess((Bundleable) _Parcel.readTypedObject(parcel, Bundleable.CREATOR));
                    parcel2.writeNoException();
                } else if (i != 3) {
                    return super.onTransact(i, parcel, parcel2, i2);
                } else {
                    onFailure((Bundleable) _Parcel.readTypedObject(parcel, Bundleable.CREATOR));
                    parcel2.writeNoException();
                }
                return true;
            }
            parcel2.writeString(IOnDoneCallback.DESCRIPTOR);
            return true;
        }

        private static class Proxy implements IOnDoneCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IOnDoneCallback.DESCRIPTOR;
            }

            public void onSuccess(Bundleable bundleable) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOnDoneCallback.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundleable, 0);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onFailure(Bundleable bundleable) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IOnDoneCallback.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundleable, 0);
                    this.mRemote.transact(3, obtain, obtain2, 0);
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
