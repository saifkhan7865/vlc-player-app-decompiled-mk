package org.videolan.vlc.gui.helpers;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.util.LifecycleAwareScheduler;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"org/videolan/vlc/gui/helpers/UiToolsKt$enableMarqueeEffect$1$1", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "onScrollStateChanged", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "newState", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: UiTools.kt */
public final class UiToolsKt$enableMarqueeEffect$1$1 extends RecyclerView.OnScrollListener {
    final /* synthetic */ LifecycleAwareScheduler $scheduler;

    UiToolsKt$enableMarqueeEffect$1$1(LifecycleAwareScheduler lifecycleAwareScheduler) {
        this.$scheduler = lifecycleAwareScheduler;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onScrollStateChanged(recyclerView, i);
        this.$scheduler.cancelAction(UiToolsKt.MARQUEE_ACTION);
        if (i == 0) {
            LifecycleAwareScheduler.scheduleAction$default(this.$scheduler, UiToolsKt.MARQUEE_ACTION, 1500, (Bundle) null, 4, (Object) null);
        }
    }
}
