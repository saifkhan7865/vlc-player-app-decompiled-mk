package org.videolan.vlc.gui.video;

import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.BindingAdapter;
import io.netty.handler.codec.rtsp.RtspHeaders;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"TAG", "", "setLayoutHeight", "", "view", "Landroid/view/View;", "time", "resolution", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoListAdapter.kt */
public final class VideoListAdapterKt {
    private static final String TAG = "VLC/VideoListAdapter";

    @BindingAdapter({"time", "resolution"})
    public static final void setLayoutHeight(View view, String str, String str2) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(str, RtspHeaders.Values.TIME);
        Intrinsics.checkNotNullParameter(str2, "resolution");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (str.length() == 0 && str2.length() == 0) ? -1 : -2;
        view.setLayoutParams(layoutParams);
    }
}
