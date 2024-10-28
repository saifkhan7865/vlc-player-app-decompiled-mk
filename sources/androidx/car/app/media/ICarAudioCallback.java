package androidx.car.app.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICarAudioCallback extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.media.ICarAudioCallback";

    public static class Default implements ICarAudioCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onStopRecording() throws RemoteException {
        }
    }

    void onStopRecording() throws RemoteException;

    public static abstract class Stub extends Binder implements ICarAudioCallback {
        static final int TRANSACTION_onStopRecording = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ICarAudioCallback.DESCRIPTOR);
        }

        public static ICarAudioCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ICarAudioCallback.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ICarAudioCallback)) {
                return new Proxy(iBinder);
            }
            return (ICarAudioCallback) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ICarAudioCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ICarAudioCallback.DESCRIPTOR);
                return true;
            } else if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                onStopRecording();
                parcel2.writeNoException();
                return true;
            }
        }

        private static class Proxy implements ICarAudioCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICarAudioCallback.DESCRIPTOR;
            }

            public void onStopRecording() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ICarAudioCallback.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
