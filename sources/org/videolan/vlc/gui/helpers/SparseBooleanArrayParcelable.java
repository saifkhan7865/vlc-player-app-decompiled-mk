package org.videolan.vlc.gui.helpers;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lorg/videolan/vlc/gui/helpers/SparseBooleanArrayParcelable;", "Landroid/os/Parcelable;", "data", "Landroid/util/SparseBooleanArray;", "(Landroid/util/SparseBooleanArray;)V", "getData", "()Landroid/util/SparseBooleanArray;", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SparseBooleanArrayParcelable.kt */
public final class SparseBooleanArrayParcelable implements Parcelable {
    public static final Parcelable.Creator<SparseBooleanArrayParcelable> CREATOR = new Creator();
    private final SparseBooleanArray data;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SparseBooleanArrayParcelable.kt */
    public static final class Creator implements Parcelable.Creator<SparseBooleanArrayParcelable> {
        public final SparseBooleanArrayParcelable createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new SparseBooleanArrayParcelable(parcel.readSparseBooleanArray());
        }

        public final SparseBooleanArrayParcelable[] newArray(int i) {
            return new SparseBooleanArrayParcelable[i];
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeSparseBooleanArray(this.data);
    }

    public SparseBooleanArrayParcelable(SparseBooleanArray sparseBooleanArray) {
        Intrinsics.checkNotNullParameter(sparseBooleanArray, "data");
        this.data = sparseBooleanArray;
    }

    public final SparseBooleanArray getData() {
        return this.data;
    }
}
