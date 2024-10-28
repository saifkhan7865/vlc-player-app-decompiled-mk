package org.videolan.vlc.util;

import android.os.Bundle;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/util/SchedulerCallback;", "Landroidx/lifecycle/LifecycleOwner;", "onTaskCancelled", "", "id", "", "onTaskTriggered", "data", "Landroid/os/Bundle;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LifecycleAwareScheduler.kt */
public interface SchedulerCallback extends LifecycleOwner {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: LifecycleAwareScheduler.kt */
    public static final class DefaultImpls {
        public static void onTaskCancelled(SchedulerCallback schedulerCallback, String str) {
            Intrinsics.checkNotNullParameter(str, "id");
        }
    }

    void onTaskCancelled(String str);

    void onTaskTriggered(String str, Bundle bundle);
}
