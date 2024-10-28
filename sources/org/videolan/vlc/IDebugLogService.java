package org.videolan.vlc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import org.videolan.vlc.IDebugLogServiceCallback;

public interface IDebugLogService extends IInterface {
    public static final String DESCRIPTOR = "org.videolan.vlc.IDebugLogService";

    public static class Default implements IDebugLogService {
        public IBinder asBinder() {
            return null;
        }

        public void clear() throws RemoteException {
        }

        public void registerCallback(IDebugLogServiceCallback iDebugLogServiceCallback) throws RemoteException {
        }

        public void save() throws RemoteException {
        }

        public void start() throws RemoteException {
        }

        public void stop() throws RemoteException {
        }

        public void unregisterCallback(IDebugLogServiceCallback iDebugLogServiceCallback) throws RemoteException {
        }
    }

    void clear() throws RemoteException;

    void registerCallback(IDebugLogServiceCallback iDebugLogServiceCallback) throws RemoteException;

    void save() throws RemoteException;

    void start() throws RemoteException;

    void stop() throws RemoteException;

    void unregisterCallback(IDebugLogServiceCallback iDebugLogServiceCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IDebugLogService {
        static final int TRANSACTION_clear = 3;
        static final int TRANSACTION_registerCallback = 5;
        static final int TRANSACTION_save = 4;
        static final int TRANSACTION_start = 1;
        static final int TRANSACTION_stop = 2;
        static final int TRANSACTION_unregisterCallback = 6;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IDebugLogService.DESCRIPTOR);
        }

        public static IDebugLogService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IDebugLogService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IDebugLogService)) {
                return new Proxy(iBinder);
            }
            return (IDebugLogService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDebugLogService.DESCRIPTOR);
            }
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        start();
                        parcel2.writeNoException();
                        break;
                    case 2:
                        stop();
                        parcel2.writeNoException();
                        break;
                    case 3:
                        clear();
                        parcel2.writeNoException();
                        break;
                    case 4:
                        save();
                        parcel2.writeNoException();
                        break;
                    case 5:
                        registerCallback(IDebugLogServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        break;
                    case 6:
                        unregisterCallback(IDebugLogServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        break;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
                return true;
            }
            parcel2.writeString(IDebugLogService.DESCRIPTOR);
            return true;
        }

        private static class Proxy implements IDebugLogService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IDebugLogService.DESCRIPTOR;
            }

            public void start() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDebugLogService.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void stop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDebugLogService.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void clear() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDebugLogService.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void save() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDebugLogService.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void registerCallback(IDebugLogServiceCallback iDebugLogServiceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDebugLogService.DESCRIPTOR);
                    obtain.writeStrongInterface(iDebugLogServiceCallback);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unregisterCallback(IDebugLogServiceCallback iDebugLogServiceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDebugLogService.DESCRIPTOR);
                    obtain.writeStrongInterface(iDebugLogServiceCallback);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
