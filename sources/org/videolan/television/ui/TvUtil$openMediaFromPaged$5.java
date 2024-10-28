package org.videolan.television.ui;

import androidx.fragment.app.FragmentActivity;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.media.MediaUtils;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "list", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "<anonymous parameter 1>", "Lorg/videolan/vlc/PlaybackService;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: TvUtil.kt */
final class TvUtil$openMediaFromPaged$5 extends Lambda implements Function2<List<? extends MediaWrapper>, PlaybackService, Unit> {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ Object $item;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TvUtil$openMediaFromPaged$5(FragmentActivity fragmentActivity, Object obj) {
        super(2);
        this.$activity = fragmentActivity;
        this.$item = obj;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((List<? extends MediaWrapper>) (List) obj, (PlaybackService) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(List<? extends MediaWrapper> list, PlaybackService playbackService) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(playbackService, "<anonymous parameter 1>");
        MediaUtils.openList$default(MediaUtils.INSTANCE, this.$activity, list, KotlinExtensionsKt.getposition(list, this.$item), false, 8, (Object) null);
    }
}
