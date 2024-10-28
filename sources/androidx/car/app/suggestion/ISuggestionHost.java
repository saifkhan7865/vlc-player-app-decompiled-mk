package androidx.car.app.suggestion;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import androidx.car.app.serialization.Bundleable;

public interface ISuggestionHost extends IInterface {
    public static final String DESCRIPTOR = "androidx.car.app.suggestion.ISuggestionHost";

    public static class Default implements ISuggestionHost {
        public IBinder asBinder() {
            return null;
        }

        public void updateSuggestions(Bundleable bundleable) throws RemoteException {
        }
    }

    void updateSuggestions(Bundleable bundleable) throws RemoteException;

    public static abstract class Stub extends Binder implements ISuggestionHost {
        static final int TRANSACTION_updateSuggestions = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, ISuggestionHost.DESCRIPTOR);
        }

        public static ISuggestionHost asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ISuggestionHost.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ISuggestionHost)) {
                return new Proxy(iBinder);
            }
            return (ISuggestionHost) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(ISuggestionHost.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(ISuggestionHost.DESCRIPTOR);
                return true;
            } else if (i != 2) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                updateSuggestions((Bundleable) _Parcel.readTypedObject(parcel, Bundleable.CREATOR));
                parcel2.writeNoException();
                return true;
            }
        }

        private static class Proxy implements ISuggestionHost {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ISuggestionHost.DESCRIPTOR;
            }

            public void updateSuggestions(Bundleable bundleable) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ISuggestionHost.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, bundleable, 0);
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
