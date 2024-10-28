package org.videolan.vlc.gui.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.view.ViewCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\f\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0011\u001a\u00020\u000fJ\b\u0010\u0012\u001a\u00020\u000fH\u0002R\u0010\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u0013"}, d2 = {"Lorg/videolan/vlc/gui/view/AccessibleSeekBar;", "Landroidx/appcompat/widget/AppCompatSeekBar;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "customAccessibilityDelegate", "org/videolan/vlc/gui/view/AccessibleSeekBar$customAccessibilityDelegate$1", "Lorg/videolan/vlc/gui/view/AccessibleSeekBar$customAccessibilityDelegate$1;", "disableAccessibilityEvents", "", "enableAccessibilityEvents", "forceAccessibilityUpdate", "initialize", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AccessibleSeekBar.kt */
public final class AccessibleSeekBar extends AppCompatSeekBar {
    private final AccessibleSeekBar$customAccessibilityDelegate$1 customAccessibilityDelegate = new AccessibleSeekBar$customAccessibilityDelegate$1(this);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AccessibleSeekBar(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        initialize();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AccessibleSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initialize();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AccessibleSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initialize();
    }

    private final void initialize() {
        ViewCompat.setAccessibilityDelegate(this, this.customAccessibilityDelegate);
    }

    public final void forceAccessibilityUpdate() {
        this.customAccessibilityDelegate.setForce(true);
    }

    public final void disableAccessibilityEvents() {
        this.customAccessibilityDelegate.setDisabled(true);
    }

    public final void enableAccessibilityEvents() {
        this.customAccessibilityDelegate.setDisabled(false);
    }
}
