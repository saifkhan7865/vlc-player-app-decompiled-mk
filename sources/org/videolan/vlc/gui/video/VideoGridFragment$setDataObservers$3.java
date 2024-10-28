package org.videolan.vlc.gui.video;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoGridFragment.kt */
final class VideoGridFragment$setDataObservers$3 extends Lambda implements Function1<MediaWrapper, Unit> {
    final /* synthetic */ VideoGridFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoGridFragment$setDataObservers$3(VideoGridFragment videoGridFragment) {
        super(1);
        this.this$0 = videoGridFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((MediaWrapper) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(MediaWrapper mediaWrapper) {
        VideoListAdapter access$getVideoListAdapter$p = this.this$0.videoListAdapter;
        if (access$getVideoListAdapter$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
            access$getVideoListAdapter$p = null;
        }
        Intrinsics.checkNotNull(mediaWrapper);
        access$getVideoListAdapter$p.updateThumb(mediaWrapper);
    }
}
