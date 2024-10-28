package androidx.car.app.serialization;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import j$.util.Objects;

public final class Bundleable implements Parcelable {
    public static final Parcelable.Creator<Bundleable> CREATOR = new Parcelable.Creator<Bundleable>() {
        public Bundleable createFromParcel(Parcel parcel) {
            return new Bundleable((Bundle) Objects.requireNonNull(parcel.readBundle(getClass().getClassLoader())));
        }

        public Bundleable[] newArray(int i) {
            return new Bundleable[i];
        }
    };
    private final Bundle mBundle;

    public int describeContents() {
        return 0;
    }

    public static Bundleable create(Object obj) throws BundlerException {
        return new Bundleable(obj);
    }

    public Object get() throws BundlerException {
        return Bundler.fromBundle(this.mBundle);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.mBundle);
    }

    private Bundleable(Object obj) throws BundlerException {
        this.mBundle = Bundler.toBundle(obj);
    }

    Bundleable(Bundle bundle) {
        this.mBundle = bundle;
    }
}
