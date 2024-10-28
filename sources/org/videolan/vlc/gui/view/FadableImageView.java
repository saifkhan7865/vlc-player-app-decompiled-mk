package org.videolan.vlc.gui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\r\u001a\u00020\u000eH\u0002J\u0006\u0010\u000f\u001a\u00020\u000eJ\u0012\u0010\u0010\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\tH\u0016J\u0012\u0010\u0015\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0012\u0010\u0018\u001a\u00020\u000e2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u001a\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\tH\u0016J\u0012\u0010\u001f\u001a\u00020\u000e2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lorg/videolan/vlc/gui/view/FadableImageView;", "Landroidx/appcompat/widget/AppCompatImageView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "animationRunning", "Ljava/util/concurrent/atomic/AtomicBoolean;", "fade", "", "resetFade", "setBackground", "background", "Landroid/graphics/drawable/Drawable;", "setBackgroundResource", "resid", "setImageBitmap", "bm", "Landroid/graphics/Bitmap;", "setImageDrawable", "drawable", "setImageIcon", "icon", "Landroid/graphics/drawable/Icon;", "setImageResource", "resId", "setImageURI", "uri", "Landroid/net/Uri;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FadableImageView.kt */
public final class FadableImageView extends AppCompatImageView {
    /* access modifiers changed from: private */
    public AtomicBoolean animationRunning = new AtomicBoolean(false);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FadableImageView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FadableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FadableImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
    }

    private final void fade() {
        AtomicBoolean atomicBoolean = this.animationRunning;
        if (atomicBoolean == null || !atomicBoolean.get()) {
            setAlpha(0.0f);
            AtomicBoolean atomicBoolean2 = this.animationRunning;
            if (atomicBoolean2 != null) {
                atomicBoolean2.set(true);
            }
            animate().setListener(new FadableImageView$fade$1(this)).alpha(1.0f);
        }
    }

    public final void resetFade() {
        post(new FadableImageView$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public static final void resetFade$lambda$0(FadableImageView fadableImageView) {
        Intrinsics.checkNotNullParameter(fadableImageView, "this$0");
        fadableImageView.animate().cancel();
        fadableImageView.setAlpha(1.0f);
    }

    public void setBackground(Drawable drawable) {
        super.setBackground(drawable);
        if (drawable == null || (drawable instanceof ColorDrawable)) {
            resetFade();
        } else {
            fade();
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (i != 0) {
            fade();
        } else {
            resetFade();
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            fade();
        } else {
            resetFade();
        }
        super.setImageBitmap(bitmap);
    }

    public void setImageDrawable(Drawable drawable) {
        if (drawable != null) {
            fade();
        } else {
            resetFade();
        }
        super.setImageDrawable(drawable);
    }

    public void setImageIcon(Icon icon) {
        if (icon != null) {
            fade();
        } else {
            resetFade();
        }
        super.setImageIcon(icon);
    }

    public void setImageResource(int i) {
        if (i != 0) {
            fade();
        } else {
            resetFade();
        }
        super.setImageResource(i);
    }

    public void setImageURI(Uri uri) {
        if (uri != null) {
            fade();
        } else {
            resetFade();
        }
        super.setImageURI(uri);
    }
}
