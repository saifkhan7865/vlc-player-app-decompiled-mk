package org.videolan.vlc.util;

import android.hardware.display.DisplayManager;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\b"}, d2 = {"org/videolan/vlc/util/FrameRateManager$displayListener$1", "Landroid/hardware/display/DisplayManager$DisplayListener;", "onDisplayAdded", "", "displayId", "", "onDisplayChanged", "onDisplayRemoved", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FrameRateManager.kt */
public final class FrameRateManager$displayListener$1 implements DisplayManager.DisplayListener {
    final /* synthetic */ FrameRateManager this$0;

    public void onDisplayAdded(int i) {
    }

    public void onDisplayRemoved(int i) {
    }

    FrameRateManager$displayListener$1(FrameRateManager frameRateManager) {
        this.this$0 = frameRateManager;
    }

    public void onDisplayChanged(int i) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0.getActivity()), Dispatchers.getIO(), (CoroutineStart) null, new FrameRateManager$displayListener$1$onDisplayChanged$1(this.this$0, (Continuation<? super FrameRateManager$displayListener$1$onDisplayChanged$1>) null), 2, (Object) null);
        this.this$0.getDisplayManager().unregisterDisplayListener(this);
    }
}
