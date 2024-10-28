package org.videolan.vlc.gui.helpers;

import android.os.Bundle;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.util.SchedulerCallback;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\f"}, d2 = {"org/videolan/vlc/gui/helpers/UiToolsKt$enableMarqueeEffect$1$scheduler$1", "Lorg/videolan/vlc/util/SchedulerCallback;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "onTaskTriggered", "", "id", "", "data", "Landroid/os/Bundle;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: UiTools.kt */
public final class UiToolsKt$enableMarqueeEffect$1$scheduler$1 implements SchedulerCallback {
    final /* synthetic */ LinearLayoutManager $layoutManager;
    final /* synthetic */ RecyclerView $recyclerView;

    UiToolsKt$enableMarqueeEffect$1$scheduler$1(LinearLayoutManager linearLayoutManager, RecyclerView recyclerView) {
        this.$layoutManager = linearLayoutManager;
        this.$recyclerView = recyclerView;
    }

    public void onTaskCancelled(String str) {
        SchedulerCallback.DefaultImpls.onTaskCancelled(this, str);
    }

    public void onTaskTriggered(String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(bundle, "data");
        int findFirstVisibleItemPosition = this.$layoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = this.$layoutManager.findLastVisibleItemPosition();
        if (findFirstVisibleItemPosition <= findLastVisibleItemPosition) {
            while (true) {
                RecyclerView.ViewHolder findViewHolderForLayoutPosition = this.$recyclerView.findViewHolderForLayoutPosition(findFirstVisibleItemPosition);
                TextView textView = null;
                MarqueeViewHolder marqueeViewHolder = findViewHolderForLayoutPosition instanceof MarqueeViewHolder ? (MarqueeViewHolder) findViewHolderForLayoutPosition : null;
                if (marqueeViewHolder != null) {
                    textView = marqueeViewHolder.getTitleView();
                }
                if (textView != null) {
                    textView.setSelected(true);
                }
                if (findFirstVisibleItemPosition != findLastVisibleItemPosition) {
                    findFirstVisibleItemPosition++;
                } else {
                    return;
                }
            }
        }
    }

    public Lifecycle getLifecycle() {
        LifecycleOwner lifecycleOwner = ViewTreeLifecycleOwner.get(this.$recyclerView);
        Intrinsics.checkNotNull(lifecycleOwner);
        return lifecycleOwner.getLifecycle();
    }
}
