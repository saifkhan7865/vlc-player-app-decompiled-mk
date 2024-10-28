package org.videolan.vlc.gui.helpers;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u001b\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006B\u0019\b\u0016\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"Lorg/videolan/vlc/gui/helpers/BottomNavigationBehaviorState;", "Landroidx/customview/view/AbsSavedState;", "source", "Landroid/os/Parcel;", "loader", "Ljava/lang/ClassLoader;", "(Landroid/os/Parcel;Ljava/lang/ClassLoader;)V", "superState", "Landroid/os/Parcelable;", "translation", "", "(Landroid/os/Parcelable;F)V", "getTranslation", "()F", "setTranslation", "(F)V", "writeToParcel", "", "out", "flags", "", "CREATOR", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BottomNavigationBehavior.kt */
public final class BottomNavigationBehaviorState extends AbsSavedState {
    public static final CREATOR CREATOR = new CREATOR((DefaultConstructorMarker) null);
    private float translation;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BottomNavigationBehaviorState(Parcel parcel) {
        this(parcel, (ClassLoader) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(parcel, "source");
    }

    public final float getTranslation() {
        return this.translation;
    }

    public final void setTranslation(float f) {
        this.translation = f;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BottomNavigationBehaviorState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        Intrinsics.checkNotNullParameter(parcel, "source");
        this.translation = parcel.readFloat();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BottomNavigationBehaviorState(Parcel parcel, ClassLoader classLoader, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel, (i & 2) != 0 ? null : classLoader);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BottomNavigationBehaviorState(Parcelable parcelable, float f) {
        super(parcelable);
        Intrinsics.checkNotNull(parcelable);
        this.translation = f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.translation);
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/gui/helpers/BottomNavigationBehaviorState$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lorg/videolan/vlc/gui/helpers/BottomNavigationBehaviorState;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lorg/videolan/vlc/gui/helpers/BottomNavigationBehaviorState;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BottomNavigationBehavior.kt */
    public static final class CREATOR implements Parcelable.Creator<BottomNavigationBehaviorState> {
        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }

        public BottomNavigationBehaviorState createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new BottomNavigationBehaviorState(parcel, (ClassLoader) null, 2, (DefaultConstructorMarker) null);
        }

        public BottomNavigationBehaviorState[] newArray(int i) {
            return new BottomNavigationBehaviorState[i];
        }
    }
}
