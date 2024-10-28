package org.videolan.vlc.util;

import android.os.Bundle;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\tJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J \u0010\u0016\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aJ \u0010\u001b\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aJ\u0018\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0019\u001a\u00020\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R*\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bj\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n`\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "callback", "Lorg/videolan/vlc/util/SchedulerCallback;", "(Lorg/videolan/vlc/util/SchedulerCallback;)V", "canceled", "", "timeTasks", "Ljava/util/HashMap;", "", "Ljava/util/TimerTask;", "Lkotlin/collections/HashMap;", "timer", "Ljava/util/Timer;", "cancelAction", "id", "discardTimer", "", "onDestroy", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onPause", "scheduleAction", "delay", "", "data", "Landroid/os/Bundle;", "scheduleAtFixedRate", "interval", "startAction", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LifecycleAwareScheduler.kt */
public final class LifecycleAwareScheduler implements DefaultLifecycleObserver {
    /* access modifiers changed from: private */
    public final SchedulerCallback callback;
    private boolean canceled;
    private final HashMap<String, TimerTask> timeTasks = new HashMap<>();
    private final Timer timer = new Timer();

    public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onCreate(this, lifecycleOwner);
    }

    public /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onResume(this, lifecycleOwner);
    }

    public /* synthetic */ void onStart(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onStart(this, lifecycleOwner);
    }

    public /* synthetic */ void onStop(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onStop(this, lifecycleOwner);
    }

    public LifecycleAwareScheduler(SchedulerCallback schedulerCallback) {
        Intrinsics.checkNotNullParameter(schedulerCallback, "callback");
        this.callback = schedulerCallback;
    }

    public static /* synthetic */ void startAction$default(LifecycleAwareScheduler lifecycleAwareScheduler, String str, Bundle bundle, int i, Object obj) {
        if ((i & 2) != 0) {
            bundle = new Bundle();
        }
        lifecycleAwareScheduler.startAction(str, bundle);
    }

    public final void startAction(String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(bundle, "data");
        scheduleAction(str, 0, bundle);
    }

    public static /* synthetic */ void scheduleAction$default(LifecycleAwareScheduler lifecycleAwareScheduler, String str, long j, Bundle bundle, int i, Object obj) {
        if ((i & 4) != 0) {
            bundle = new Bundle();
        }
        lifecycleAwareScheduler.scheduleAction(str, j, bundle);
    }

    public final void scheduleAction(String str, long j, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(bundle, "data");
        if (!this.canceled) {
            this.callback.getLifecycle().addObserver(this);
            if (this.timeTasks.keySet().contains(str)) {
                cancelAction(str);
            }
            this.timeTasks.put(str, new LifecycleAwareScheduler$scheduleAction$$inlined$timerTask$1(this, str, bundle));
            this.timer.schedule(this.timeTasks.get(str), j);
        }
    }

    public static /* synthetic */ void scheduleAtFixedRate$default(LifecycleAwareScheduler lifecycleAwareScheduler, String str, long j, Bundle bundle, int i, Object obj) {
        if ((i & 4) != 0) {
            bundle = new Bundle();
        }
        lifecycleAwareScheduler.scheduleAtFixedRate(str, j, bundle);
    }

    public final void scheduleAtFixedRate(String str, long j, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(bundle, "data");
        if (!this.canceled) {
            this.callback.getLifecycle().addObserver(this);
            if (this.timeTasks.keySet().contains(str)) {
                cancelAction(str);
            }
            this.timeTasks.put(str, new LifecycleAwareScheduler$scheduleAtFixedRate$$inlined$timerTask$1(this, str, bundle));
            this.timer.scheduleAtFixedRate(this.timeTasks.get(str), 0, j);
        }
    }

    public final boolean cancelAction(String str) {
        Intrinsics.checkNotNullParameter(str, "id");
        if (!this.timeTasks.keySet().contains(str)) {
            return false;
        }
        TimerTask timerTask = this.timeTasks.get(str);
        if (timerTask != null) {
            timerTask.cancel();
        }
        this.callback.onTaskCancelled(str);
        this.timeTasks.remove(str);
        return true;
    }

    private final void discardTimer() {
        for (Map.Entry key : this.timeTasks.entrySet()) {
            this.callback.onTaskCancelled((String) key.getKey());
        }
        this.canceled = true;
        this.timer.cancel();
        this.callback.getLifecycle().removeObserver(this);
    }

    public void onPause(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        DefaultLifecycleObserver.CC.$default$onPause(this, lifecycleOwner);
        for (Map.Entry entry : this.timeTasks.entrySet()) {
            ((TimerTask) entry.getValue()).cancel();
            this.callback.onTaskCancelled((String) entry.getKey());
        }
    }

    public void onDestroy(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        DefaultLifecycleObserver.CC.$default$onDestroy(this, lifecycleOwner);
        discardTimer();
    }
}
