package org.videolan.vlc.gui.view;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H\u0014¨\u0006\u000f"}, d2 = {"Lorg/videolan/vlc/gui/view/TopCropImageView;", "Landroidx/appcompat/widget/AppCompatImageView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "setFrame", "", "l", "t", "r", "b", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TopCropImageView.kt */
public final class TopCropImageView extends AppCompatImageView {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TopCropImageView(Context context) {
        this(context, (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TopCropImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TopCropImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TopCropImageView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* access modifiers changed from: protected */
    public boolean setFrame(int i, int i2, int i3, int i4) {
        Matrix imageMatrix = getImageMatrix();
        float intrinsicWidth = ((float) (i3 - i)) / ((float) getDrawable().getIntrinsicWidth());
        float intrinsicHeight = ((float) (i4 - i2)) / ((float) getDrawable().getIntrinsicHeight());
        if (intrinsicHeight > intrinsicWidth) {
            intrinsicWidth = intrinsicHeight;
        }
        imageMatrix.setScale(intrinsicWidth, intrinsicWidth, 0.0f, 0.0f);
        setImageMatrix(imageMatrix);
        return super.setFrame(i, i2, i3, i4);
    }
}
