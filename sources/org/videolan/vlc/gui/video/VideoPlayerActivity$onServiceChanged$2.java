package org.videolan.vlc.gui.video;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.media.WaitConfirmation;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/vlc/media/WaitConfirmation;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerActivity.kt */
final class VideoPlayerActivity$onServiceChanged$2 extends Lambda implements Function1<WaitConfirmation, Unit> {
    final /* synthetic */ VideoPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerActivity$onServiceChanged$2(VideoPlayerActivity videoPlayerActivity) {
        super(1);
        this.this$0 = videoPlayerActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((WaitConfirmation) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(WaitConfirmation waitConfirmation) {
        if (waitConfirmation != null) {
            this.this$0.showConfirmResumeDialog(waitConfirmation);
        }
    }
}
