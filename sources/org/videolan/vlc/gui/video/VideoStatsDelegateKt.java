package org.videolan.vlc.gui.video;

import androidx.core.widget.NestedScrollView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a0\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u000e\b\u0004\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u00042\u000e\b\u0004\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0006"}, d2 = {"scrollState", "", "Landroidx/core/widget/NestedScrollView;", "idle", "Lkotlin/Function0;", "scrolling", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoStatsDelegate.kt */
public final class VideoStatsDelegateKt {
    public static final void scrollState(NestedScrollView nestedScrollView, Function0<Unit> function0, Function0<Unit> function02) {
        Intrinsics.checkNotNullParameter(nestedScrollView, "<this>");
        Intrinsics.checkNotNullParameter(function0, "idle");
        Intrinsics.checkNotNullParameter(function02, "scrolling");
        nestedScrollView.setOnTouchListener(new VideoStatsDelegateKt$scrollState$1(function02, function0));
    }
}
