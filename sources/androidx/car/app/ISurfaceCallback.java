package androidx.car.app;

import android.graphics.Rect;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.serialization.Bundleable;

public interface ISurfaceCallback extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.ISurfaceCallback";

    public static class Default implements ISurfaceCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onClick(float f, float f2) throws RemoteException {
        }

        public void onFling(float f, float f2) throws RemoteException {
        }

        public void onScale(float f, float f2, float f3) throws RemoteException {
        }

        public void onScroll(float f, float f2) throws RemoteException {
        }

        public void onStableAreaChanged(Rect rect, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void onSurfaceAvailable(Bundleable bundleable, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void onSurfaceDestroyed(Bundleable bundleable, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void onVisibleAreaChanged(Rect rect, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }
    }

    void onClick(float f, float f2) throws RemoteException;

    void onFling(float f, float f2) throws RemoteException;

    void onScale(float f, float f2, float f3) throws RemoteException;

    void onScroll(float f, float f2) throws RemoteException;

    void onStableAreaChanged(Rect rect, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void onSurfaceAvailable(Bundleable bundleable, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void onSurfaceDestroyed(Bundleable bundleable, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void onVisibleAreaChanged(Rect rect, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ISurfaceCallback {
        static final int TRANSACTION_onClick = 9;
        static final int TRANSACTION_onFling = 7;
        static final int TRANSACTION_onScale = 8;
        static final int TRANSACTION_onScroll = 6;
        static final int TRANSACTION_onStableAreaChanged = 4;
        static final int TRANSACTION_onSurfaceAvailable = 2;
        static final int TRANSACTION_onSurfaceDestroyed = 5;
        static final int TRANSACTION_onVisibleAreaChanged = 3;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ISurfaceCallback.DESCRIPTOR);
        }

        public static ISurfaceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISurfaceCallback.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ISurfaceCallback)) {
                return new Proxy(iBinder);
            }
            return (ISurfaceCallback) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ISurfaceCallback.DESCRIPTOR);
            }
            if (i != 1598968902) {
                switch (i) {
                    case 2:
                        onSurfaceAvailable((Bundleable) _Parcel.readTypedObject(parcel, Bundleable.CREATOR), IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                        break;
                    case 3:
                        onVisibleAreaChanged((Rect) _Parcel.readTypedObject(parcel, Rect.CREATOR), IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                        break;
                    case 4:
                        onStableAreaChanged((Rect) _Parcel.readTypedObject(parcel, Rect.CREATOR), IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                        break;
                    case 5:
                        onSurfaceDestroyed((Bundleable) _Parcel.readTypedObject(parcel, Bundleable.CREATOR), IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                        break;
                    case 6:
                        onScroll(parcel.readFloat(), parcel.readFloat());
                        break;
                    case 7:
                        onFling(parcel.readFloat(), parcel.readFloat());
                        break;
                    case 8:
                        onScale(parcel.readFloat(), parcel.readFloat(), parcel.readFloat());
                        break;
                    case 9:
                        onClick(parcel.readFloat(), parcel.readFloat());
                        break;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
                return true;
            }
            parcel2.writeString(ISurfaceCallback.DESCRIPTOR);
            return true;
        }

        private static class Proxy implements ISurfaceCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISurfaceCallback.DESCRIPTOR;
            }

            public void onSurfaceAvailable(Bundleable bundleable, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISurfaceCallback.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundleable, 0);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onVisibleAreaChanged(Rect rect, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISurfaceCallback.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, rect, 0);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onStableAreaChanged(Rect rect, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISurfaceCallback.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, rect, 0);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(4, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onSurfaceDestroyed(Bundleable bundleable, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISurfaceCallback.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundleable, 0);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(5, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onScroll(float f, float f2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISurfaceCallback.DESCRIPTOR);
                    obtain.writeFloat(f);
                    obtain.writeFloat(f2);
                    this.mRemote.transact(6, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onFling(float f, float f2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISurfaceCallback.DESCRIPTOR);
                    obtain.writeFloat(f);
                    obtain.writeFloat(f2);
                    this.mRemote.transact(7, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onScale(float f, float f2, float f3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISurfaceCallback.DESCRIPTOR);
                    obtain.writeFloat(f);
                    obtain.writeFloat(f2);
                    obtain.writeFloat(f3);
                    this.mRemote.transact(8, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onClick(float f, float f2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISurfaceCallback.DESCRIPTOR);
                    obtain.writeFloat(f);
                    obtain.writeFloat(f2);
                    this.mRemote.transact(9, obtain, (Parcel) null, 1);
                } finally {
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
