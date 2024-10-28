package org.videolan.vlc.gui;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.gui.network.MRLAdapter;
import org.videolan.vlc.gui.view.EmptyLoadingState;
import org.videolan.vlc.gui.view.TitleListView;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MoreFragment.kt */
final class MoreFragment$onViewCreated$6 extends Lambda implements Function1<List<MediaWrapper>, Unit> {
    final /* synthetic */ MoreFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MoreFragment$onViewCreated$6(MoreFragment moreFragment) {
        super(1);
        this.this$0 = moreFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<MediaWrapper>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<MediaWrapper> list) {
        MRLAdapter access$getStreamsAdapter$p = this.this$0.streamsAdapter;
        TitleListView titleListView = null;
        if (access$getStreamsAdapter$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("streamsAdapter");
            access$getStreamsAdapter$p = null;
        }
        Intrinsics.checkNotNull(list);
        access$getStreamsAdapter$p.update(list);
        TitleListView access$getStreamsEntry$p = this.this$0.streamsEntry;
        if (access$getStreamsEntry$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("streamsEntry");
        } else {
            titleListView = access$getStreamsEntry$p;
        }
        titleListView.getLoading().setState(EmptyLoadingState.NONE);
    }
}
