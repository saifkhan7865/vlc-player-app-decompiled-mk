package org.videolan.vlc.gui.helpers;

import android.view.KeyEvent;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B3\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ$\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/videolan/vlc/gui/helpers/OnRepeatListenerKey;", "Landroid/view/View$OnKeyListener;", "Lorg/videolan/vlc/gui/helpers/OnRepeatListener;", "initialInterval", "", "normalInterval", "speedUpDelay", "clickListener", "Landroid/view/View$OnClickListener;", "listenerLifecycle", "Landroidx/lifecycle/Lifecycle;", "(IIILandroid/view/View$OnClickListener;Landroidx/lifecycle/Lifecycle;)V", "onKey", "", "view", "Landroid/view/View;", "keyCode", "event", "Landroid/view/KeyEvent;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OnRepeatListenerKey.kt */
public final class OnRepeatListenerKey extends OnRepeatListener implements View.OnKeyListener {
    private final View.OnClickListener clickListener;
    private final int initialInterval;
    private final int normalInterval;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnRepeatListenerKey(int i, int i2, int i3, View.OnClickListener onClickListener, Lifecycle lifecycle) {
        super(i, i2, i3, onClickListener, lifecycle);
        Intrinsics.checkNotNullParameter(onClickListener, "clickListener");
        Intrinsics.checkNotNullParameter(lifecycle, "listenerLifecycle");
        this.initialInterval = i;
        this.normalInterval = i2;
        this.clickListener = onClickListener;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ OnRepeatListenerKey(int i, int i2, int i3, View.OnClickListener onClickListener, Lifecycle lifecycle, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 500 : i, (i4 & 2) != 0 ? 150 : i2, (i4 & 4) != 0 ? OnRepeatListener.DEFAULT_SPEEDUP_DELAY : i3, onClickListener, lifecycle);
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (view == null || keyEvent == null || i != 23) {
            return false;
        }
        int action = keyEvent.getAction();
        if (action == 0) {
            if (!Intrinsics.areEqual((Object) getDownView(), (Object) view)) {
                startRepeating(view);
            }
            return true;
        } else if (action != 1 && action != 3) {
            return false;
        } else {
            stopRepeating(view);
            return true;
        }
    }
}
