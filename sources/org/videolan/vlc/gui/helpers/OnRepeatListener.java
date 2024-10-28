package org.videolan.vlc.gui.helpers;

import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.util.LifecycleAwareScheduler;
import org.videolan.vlc.util.SchedulerCallback;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 +2\u00020\u0001:\u0001+B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016J\u000e\u0010(\u001a\u00020#2\u0006\u0010)\u001a\u00020\fJ\u000e\u0010*\u001a\u00020#2\u0006\u0010)\u001a\u00020\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R \u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\u0005\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lorg/videolan/vlc/gui/helpers/OnRepeatListener;", "Lorg/videolan/vlc/util/SchedulerCallback;", "initialInterval", "", "normalInterval", "speedUpDelay", "clickListener", "Landroid/view/View$OnClickListener;", "listenerLifecycle", "Landroidx/lifecycle/Lifecycle;", "(IIILandroid/view/View$OnClickListener;Landroidx/lifecycle/Lifecycle;)V", "downView", "Landroid/view/View;", "getDownView", "()Landroid/view/View;", "setDownView", "(Landroid/view/View;)V", "initialTime", "", "getInitialTime", "()J", "setInitialTime", "(J)V", "lifecycle", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "scheduler", "Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "getScheduler$annotations", "()V", "getScheduler", "()Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "setScheduler", "(Lorg/videolan/vlc/util/LifecycleAwareScheduler;)V", "onTaskTriggered", "", "id", "", "data", "Landroid/os/Bundle;", "startRepeating", "view", "stopRepeating", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OnRepeatListener.kt */
public class OnRepeatListener implements SchedulerCallback {
    private static final String ACTION_ONCLICK = "action_onclick";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int DEFAULT_INITIAL_DELAY = 500;
    public static final int DEFAULT_NORMAL_DELAY = 150;
    public static final int DEFAULT_SPEEDUP_DELAY = 2000;
    private final View.OnClickListener clickListener;
    private View downView;
    private final int initialInterval;
    private long initialTime = -1;
    private final Lifecycle listenerLifecycle;
    private final int normalInterval;
    private LifecycleAwareScheduler scheduler = new LifecycleAwareScheduler(this);
    private final int speedUpDelay;

    public static /* synthetic */ void getScheduler$annotations() {
    }

    public OnRepeatListener(int i, int i2, int i3, View.OnClickListener onClickListener, Lifecycle lifecycle) {
        Intrinsics.checkNotNullParameter(onClickListener, "clickListener");
        Intrinsics.checkNotNullParameter(lifecycle, "listenerLifecycle");
        this.initialInterval = i;
        this.normalInterval = i2;
        this.speedUpDelay = i3;
        this.clickListener = onClickListener;
        this.listenerLifecycle = lifecycle;
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("negative interval");
        }
    }

    public void onTaskCancelled(String str) {
        SchedulerCallback.DefaultImpls.onTaskCancelled(this, str);
    }

    public final View getDownView() {
        return this.downView;
    }

    public final void setDownView(View view) {
        this.downView = view;
    }

    public final long getInitialTime() {
        return this.initialTime;
    }

    public final void setInitialTime(long j) {
        this.initialTime = j;
    }

    public final LifecycleAwareScheduler getScheduler() {
        return this.scheduler;
    }

    public final void setScheduler(LifecycleAwareScheduler lifecycleAwareScheduler) {
        Intrinsics.checkNotNullParameter(lifecycleAwareScheduler, "<set-?>");
        this.scheduler = lifecycleAwareScheduler;
    }

    public final void startRepeating(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.scheduler.cancelAction(ACTION_ONCLICK);
        LifecycleAwareScheduler.scheduleAction$default(this.scheduler, ACTION_ONCLICK, (long) this.initialInterval, (Bundle) null, 4, (Object) null);
        this.downView = view;
        this.initialTime = System.currentTimeMillis();
        this.clickListener.onClick(view);
        view.setPressed(true);
    }

    public final void stopRepeating(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.scheduler.cancelAction(ACTION_ONCLICK);
        this.downView = null;
        this.initialTime = -1;
        view.setPressed(false);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/helpers/OnRepeatListener$Companion;", "", "()V", "ACTION_ONCLICK", "", "DEFAULT_INITIAL_DELAY", "", "DEFAULT_NORMAL_DELAY", "DEFAULT_SPEEDUP_DELAY", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: OnRepeatListener.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public void onTaskTriggered(String str, Bundle bundle) {
        long j;
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(bundle, "data");
        if (Intrinsics.areEqual((Object) str, (Object) ACTION_ONCLICK)) {
            if (this.initialTime <= -1 || System.currentTimeMillis() - this.initialTime <= ((long) this.speedUpDelay)) {
                j = (long) this.normalInterval;
            } else {
                int i = 2;
                if (System.currentTimeMillis() - this.initialTime < ((long) (this.speedUpDelay * 2))) {
                    i = 1;
                }
                j = ((long) this.normalInterval) / ((long) ((int) ((float) Math.pow((double) 3.0f, (double) i))));
            }
            LifecycleAwareScheduler.scheduleAction$default(this.scheduler, ACTION_ONCLICK, j, (Bundle) null, 4, (Object) null);
            this.clickListener.onClick(this.downView);
        }
    }

    public Lifecycle getLifecycle() {
        return this.listenerLifecycle;
    }
}
