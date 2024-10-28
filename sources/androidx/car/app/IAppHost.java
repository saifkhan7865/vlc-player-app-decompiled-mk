package androidx.car.app;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.car.app.ISurfaceCallback;
import androidx.car.app.serialization.Bundleable;

public interface IAppHost extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.IAppHost";

    public static class Default implements IAppHost {
        public IBinder asBinder() {
            return null;
        }

        public void dismissAlert(int i) throws RemoteException {
        }

        public void invalidate() throws RemoteException {
        }

        public Bundleable openMicrophone(Bundleable bundleable) throws RemoteException {
            return null;
        }

        public void sendLocation(Location location) throws RemoteException {
        }

        public void setSurfaceCallback(ISurfaceCallback iSurfaceCallback) throws RemoteException {
        }

        public void showAlert(Bundleable bundleable) throws RemoteException {
        }

        public void showToast(CharSequence charSequence, int i) throws RemoteException {
        }
    }

    void dismissAlert(int i) throws RemoteException;

    void invalidate() throws RemoteException;

    Bundleable openMicrophone(Bundleable bundleable) throws RemoteException;

    void sendLocation(Location location) throws RemoteException;

    void setSurfaceCallback(ISurfaceCallback iSurfaceCallback) throws RemoteException;

    void showAlert(Bundleable bundleable) throws RemoteException;

    void showToast(CharSequence charSequence, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IAppHost {
        static final int TRANSACTION_dismissAlert = 7;
        static final int TRANSACTION_invalidate = 2;
        static final int TRANSACTION_openMicrophone = 8;
        static final int TRANSACTION_sendLocation = 5;
        static final int TRANSACTION_setSurfaceCallback = 4;
        static final int TRANSACTION_showAlert = 6;
        static final int TRANSACTION_showToast = 3;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IAppHost.DESCRIPTOR);
        }

        public static IAppHost asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IAppHost.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAppHost)) {
                return new Proxy(iBinder);
            }
            return (IAppHost) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IAppHost.DESCRIPTOR);
            }
            if (i != 1598968902) {
                switch (i) {
                    case 2:
                        invalidate();
                        parcel2.writeNoException();
                        break;
                    case 3:
                        showToast((CharSequence) _Parcel.readTypedObject(parcel, TextUtils.CHAR_SEQUENCE_CREATOR), parcel.readInt());
                        parcel2.writeNoException();
                        break;
                    case 4:
                        setSurfaceCallback(ISurfaceCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        break;
                    case 5:
                        sendLocation((Location) _Parcel.readTypedObject(parcel, Location.CREATOR));
                        parcel2.writeNoException();
                        break;
                    case 6:
                        showAlert((Bundleable) _Parcel.readTypedObject(parcel, Bundleable.CREATOR));
                        parcel2.writeNoException();
                        break;
                    case 7:
                        dismissAlert(parcel.readInt());
                        parcel2.writeNoException();
                        break;
                    case 8:
                        Bundleable openMicrophone = openMicrophone((Bundleable) _Parcel.readTypedObject(parcel, Bundleable.CREATOR));
                        parcel2.writeNoException();
                        _Parcel.writeTypedObject(parcel2, openMicrophone, 1);
                        break;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
                return true;
            }
            parcel2.writeString(IAppHost.DESCRIPTOR);
            return true;
        }

        private static class Proxy implements IAppHost {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IAppHost.DESCRIPTOR;
            }

            public void invalidate() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppHost.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showToast(CharSequence charSequence, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppHost.DESCRIPTOR);
                    if (charSequence != null) {
                        obtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setSurfaceCallback(ISurfaceCallback iSurfaceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppHost.DESCRIPTOR);
                    obtain.writeStrongInterface(iSurfaceCallback);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void sendLocation(Location location) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppHost.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, location, 0);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showAlert(Bundleable bundleable) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppHost.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundleable, 0);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void dismissAlert(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppHost.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundleable openMicrophone(Bundleable bundleable) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAppHost.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundleable, 0);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return (Bundleable) _Parcel.readTypedObject(obtain2, Bundleable.CREATOR);
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
