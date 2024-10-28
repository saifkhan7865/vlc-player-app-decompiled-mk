package org.videolan.vlc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IDebugLogServiceCallback extends IInterface {
    public static final String DESCRIPTOR = "org.videolan.vlc.IDebugLogServiceCallback";

    public static class Default implements IDebugLogServiceCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onLog(String str) throws RemoteException {
        }

        public void onSaved(boolean z, String str) throws RemoteException {
        }

        public void onStarted(List<String> list) throws RemoteException {
        }

        public void onStopped() throws RemoteException {
        }
    }

    void onLog(String str) throws RemoteException;

    void onSaved(boolean z, String str) throws RemoteException;

    void onStarted(List<String> list) throws RemoteException;

    void onStopped() throws RemoteException;

    public static abstract class Stub extends Binder implements IDebugLogServiceCallback {
        static final int TRANSACTION_onLog = 3;
        static final int TRANSACTION_onSaved = 4;
        static final int TRANSACTION_onStarted = 1;
        static final int TRANSACTION_onStopped = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IDebugLogServiceCallback.DESCRIPTOR);
        }

        public static IDebugLogServiceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IDebugLogServiceCallback.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IDebugLogServiceCallback)) {
                return new Proxy(iBinder);
            }
            return (IDebugLogServiceCallback) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDebugLogServiceCallback.DESCRIPTOR);
            }
            if (i != 1598968902) {
                if (i == 1) {
                    onStarted(parcel.createStringArrayList());
                    parcel2.writeNoException();
                } else if (i == 2) {
                    onStopped();
                    parcel2.writeNoException();
                } else if (i == 3) {
                    onLog(parcel.readString());
                    parcel2.writeNoException();
                } else if (i != 4) {
                    return super.onTransact(i, parcel, parcel2, i2);
                } else {
                    onSaved(parcel.readInt() != 0, parcel.readString());
                    parcel2.writeNoException();
                }
                return true;
            }
            parcel2.writeString(IDebugLogServiceCallback.DESCRIPTOR);
            return true;
        }

        private static class Proxy implements IDebugLogServiceCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDebugLogServiceCallback.DESCRIPTOR;
            }

            public void onStarted(List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDebugLogServiceCallback.DESCRIPTOR);
                    obtain.writeStringList(list);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onStopped() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDebugLogServiceCallback.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onLog(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDebugLogServiceCallback.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onSaved(boolean z, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDebugLogServiceCallback.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
