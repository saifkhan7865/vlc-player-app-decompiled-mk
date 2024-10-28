package androidx.car.app;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import androidx.car.app.ICarHost;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.serialization.Bundleable;

public interface ICarApp extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.ICarApp";

    public static class Default implements ICarApp {
        public IBinder asBinder() {
            return null;
        }

        public void getAppInfo(IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void getManager(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void onAppCreate(ICarHost iCarHost, Intent intent, Configuration configuration, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void onAppPause(IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void onAppResume(IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void onAppStart(IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void onAppStop(IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void onConfigurationChanged(Configuration configuration, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void onHandshakeCompleted(Bundleable bundleable, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }

        public void onNewIntent(Intent intent, IOnDoneCallback iOnDoneCallback) throws RemoteException {
        }
    }

    void getAppInfo(IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void getManager(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void onAppCreate(ICarHost iCarHost, Intent intent, Configuration configuration, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void onAppPause(IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void onAppResume(IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void onAppStart(IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void onAppStop(IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void onConfigurationChanged(Configuration configuration, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void onHandshakeCompleted(Bundleable bundleable, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    void onNewIntent(Intent intent, IOnDoneCallback iOnDoneCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ICarApp {
        static final int TRANSACTION_getAppInfo = 10;
        static final int TRANSACTION_getManager = 9;
        static final int TRANSACTION_onAppCreate = 2;
        static final int TRANSACTION_onAppPause = 5;
        static final int TRANSACTION_onAppResume = 4;
        static final int TRANSACTION_onAppStart = 3;
        static final int TRANSACTION_onAppStop = 6;
        static final int TRANSACTION_onConfigurationChanged = 8;
        static final int TRANSACTION_onHandshakeCompleted = 11;
        static final int TRANSACTION_onNewIntent = 7;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ICarApp.DESCRIPTOR);
        }

        public static ICarApp asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ICarApp.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ICarApp)) {
                return new Proxy(iBinder);
            }
            return (ICarApp) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ICarApp.DESCRIPTOR);
            }
            if (i != 1598968902) {
                switch (i) {
                    case 2:
                        onAppCreate(ICarHost.Stub.asInterface(parcel.readStrongBinder()), (Intent) _Parcel.readTypedObject(parcel, Intent.CREATOR), (Configuration) _Parcel.readTypedObject(parcel, Configuration.CREATOR), IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                        break;
                    case 3:
                        onAppStart(IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                        break;
                    case 4:
                        onAppResume(IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                        break;
                    case 5:
                        onAppPause(IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                        break;
                    case 6:
                        onAppStop(IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                        break;
                    case 7:
                        onNewIntent((Intent) _Parcel.readTypedObject(parcel, Intent.CREATOR), IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                        break;
                    case 8:
                        onConfigurationChanged((Configuration) _Parcel.readTypedObject(parcel, Configuration.CREATOR), IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                        break;
                    case 9:
                        getManager(parcel.readString(), IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                        break;
                    case 10:
                        getAppInfo(IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                        break;
                    case 11:
                        onHandshakeCompleted((Bundleable) _Parcel.readTypedObject(parcel, Bundleable.CREATOR), IOnDoneCallback.Stub.asInterface(parcel.readStrongBinder()));
                        break;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
                return true;
            }
            parcel2.writeString(ICarApp.DESCRIPTOR);
            return true;
        }

        private static class Proxy implements ICarApp {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICarApp.DESCRIPTOR;
            }

            public void onAppCreate(ICarHost iCarHost, Intent intent, Configuration configuration, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICarApp.DESCRIPTOR);
                    obtain.writeStrongInterface(iCarHost);
                    _Parcel.writeTypedObject(obtain, intent, 0);
                    _Parcel.writeTypedObject(obtain, configuration, 0);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onAppStart(IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICarApp.DESCRIPTOR);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onAppResume(IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICarApp.DESCRIPTOR);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(4, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onAppPause(IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICarApp.DESCRIPTOR);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(5, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onAppStop(IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICarApp.DESCRIPTOR);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(6, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onNewIntent(Intent intent, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICarApp.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, intent, 0);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(7, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onConfigurationChanged(Configuration configuration, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICarApp.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, configuration, 0);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(8, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getManager(String str, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICarApp.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(9, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getAppInfo(IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICarApp.DESCRIPTOR);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(10, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onHandshakeCompleted(Bundleable bundleable, IOnDoneCallback iOnDoneCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICarApp.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundleable, 0);
                    obtain.writeStrongInterface(iOnDoneCallback);
                    this.mRemote.transact(11, obtain, (Parcel) null, 1);
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
