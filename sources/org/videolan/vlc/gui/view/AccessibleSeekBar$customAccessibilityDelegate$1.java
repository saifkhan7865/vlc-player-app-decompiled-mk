package org.videolan.vlc.gui.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.app.NotificationCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.TalkbackUtil;
import org.videolan.vlc.util.AccessibilityHelperKt;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R$\u0010\t\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0005\"\u0004\b\u000b\u0010\u0007¨\u0006\u0012"}, d2 = {"org/videolan/vlc/gui/view/AccessibleSeekBar$customAccessibilityDelegate$1", "Landroidx/core/view/AccessibilityDelegateCompat;", "disabled", "", "getDisabled", "()Z", "setDisabled", "(Z)V", "value", "force", "getForce", "setForce", "sendAccessibilityEventUnchecked", "", "host", "Landroid/view/View;", "event", "Landroid/view/accessibility/AccessibilityEvent;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AccessibleSeekBar.kt */
public final class AccessibleSeekBar$customAccessibilityDelegate$1 extends AccessibilityDelegateCompat {
    private boolean disabled = true;
    private boolean force;
    final /* synthetic */ AccessibleSeekBar this$0;

    AccessibleSeekBar$customAccessibilityDelegate$1(AccessibleSeekBar accessibleSeekBar) {
        this.this$0 = accessibleSeekBar;
    }

    public final boolean getForce() {
        return this.force;
    }

    public final void setForce(boolean z) {
        this.force = z;
        Context context = this.this$0.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
        if (AccessibilityHelperKt.isTalkbackIsEnabled((Activity) context) && z) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(4);
            Unit unit = Unit.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(obtain, "apply(...)");
            sendAccessibilityEventUnchecked(this.this$0, obtain);
        }
    }

    public final boolean getDisabled() {
        return this.disabled;
    }

    public final void setDisabled(boolean z) {
        this.disabled = z;
    }

    public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        Intrinsics.checkNotNullParameter(view, "host");
        Intrinsics.checkNotNullParameter(accessibilityEvent, NotificationCompat.CATEGORY_EVENT);
        if (this.disabled) {
            super.sendAccessibilityEventUnchecked(view, accessibilityEvent);
            return;
        }
        AccessibleSeekBar accessibleSeekBar = this.this$0;
        Context context = accessibleSeekBar.getContext();
        int i = R.string.talkback_out_of;
        TalkbackUtil talkbackUtil = TalkbackUtil.INSTANCE;
        Context context2 = this.this$0.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        String millisToString = talkbackUtil.millisToString(context2, (long) this.this$0.getProgress());
        TalkbackUtil talkbackUtil2 = TalkbackUtil.INSTANCE;
        Context context3 = this.this$0.getContext();
        Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
        accessibleSeekBar.setContentDescription(context.getString(i, new Object[]{millisToString, talkbackUtil2.millisToString(context3, (long) this.this$0.getMax())}));
        if (accessibilityEvent.getEventType() != 2048 && accessibilityEvent.getEventType() != 4) {
            super.sendAccessibilityEventUnchecked(view, accessibilityEvent);
        } else if (this.force) {
            super.sendAccessibilityEventUnchecked(view, accessibilityEvent);
            setForce(false);
        }
    }
}
