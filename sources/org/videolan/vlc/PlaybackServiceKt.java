package org.videolan.vlc;

import android.view.View;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.media.ABRepeat;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"TAG", "", "manageAbRepeatStep", "", "Lorg/videolan/vlc/PlaybackService;", "abRepeatReset", "Landroid/view/View;", "abRepeatStop", "abRepeatContainer", "abRepeatAddMarker", "Landroid/widget/TextView;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaybackService.kt */
public final class PlaybackServiceKt {
    private static final String TAG = "VLC/PlaybackService";

    public static final void manageAbRepeatStep(PlaybackService playbackService, View view, View view2, View view3, TextView textView) {
        ABRepeat value;
        ABRepeat value2;
        ABRepeat value3;
        Intrinsics.checkNotNullParameter(playbackService, "<this>");
        Intrinsics.checkNotNullParameter(view, "abRepeatReset");
        Intrinsics.checkNotNullParameter(view2, "abRepeatStop");
        Intrinsics.checkNotNullParameter(view3, "abRepeatContainer");
        Intrinsics.checkNotNullParameter(textView, "abRepeatAddMarker");
        if (!Intrinsics.areEqual((Object) playbackService.getPlaylistManager().getAbRepeatOn().getValue(), (Object) true)) {
            view.setVisibility(8);
            view2.setVisibility(8);
            view3.setVisibility(8);
            return;
        }
        ABRepeat value4 = playbackService.getPlaylistManager().getAbRepeat().getValue();
        if ((value4 == null || value4.getStart() != -1) && ((value3 = playbackService.getPlaylistManager().getAbRepeat().getValue()) == null || value3.getStop() != -1)) {
            view.setVisibility(0);
            view2.setVisibility(0);
            view3.setVisibility(8);
            return;
        }
        ABRepeat value5 = playbackService.getPlaylistManager().getAbRepeat().getValue();
        if (value5 == null || value5.getStart() != -1 || (value2 = playbackService.getPlaylistManager().getAbRepeat().getValue()) == null || value2.getStop() != -1) {
            ABRepeat value6 = playbackService.getPlaylistManager().getAbRepeat().getValue();
            if ((value6 != null && value6.getStart() == -1) || ((value = playbackService.getPlaylistManager().getAbRepeat().getValue()) != null && value.getStop() == -1)) {
                textView.setText(playbackService.getString(R.string.abrepeat_add_second_marker));
                view3.setVisibility(0);
                view.setVisibility(8);
                view2.setVisibility(8);
                return;
            }
            return;
        }
        view3.setVisibility(0);
        textView.setText(playbackService.getString(R.string.abrepeat_add_first_marker));
        view.setVisibility(8);
        view2.setVisibility(8);
    }
}
