package org.videolan.television.ui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.moviepedia.viewmodel.MediaMetadataFull;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/moviepedia/viewmodel/MediaMetadataFull;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingTvshowDetailsFragment.kt */
final class MediaScrapingTvshowDetailsFragment$onCreate$1 extends Lambda implements Function1<MediaMetadataFull, Unit> {
    final /* synthetic */ MediaScrapingTvshowDetailsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingTvshowDetailsFragment$onCreate$1(MediaScrapingTvshowDetailsFragment mediaScrapingTvshowDetailsFragment) {
        super(1);
        this.this$0 = mediaScrapingTvshowDetailsFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((MediaMetadataFull) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(MediaMetadataFull mediaMetadataFull) {
        MediaScrapingTvshowDetailsFragment mediaScrapingTvshowDetailsFragment = this.this$0;
        Intrinsics.checkNotNull(mediaMetadataFull);
        mediaScrapingTvshowDetailsFragment.buildDetails(mediaMetadataFull);
        this.this$0.updateMetadata(mediaMetadataFull);
    }
}
