package org.videolan.television.ui;

import androidx.leanback.widget.DetailsOverviewRow;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.database.models.MediaMetadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/television/ui/VideoDetailsOverviewRow;", "Landroidx/leanback/widget/DetailsOverviewRow;", "item", "Lorg/videolan/moviepedia/database/models/MediaMetadata;", "(Lorg/videolan/moviepedia/database/models/MediaMetadata;)V", "getItem", "()Lorg/videolan/moviepedia/database/models/MediaMetadata;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaItemDetailsFragment.kt */
public final class VideoDetailsOverviewRow extends DetailsOverviewRow {
    private final MediaMetadata item;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VideoDetailsOverviewRow(MediaMetadata mediaMetadata) {
        super(mediaMetadata);
        Intrinsics.checkNotNullParameter(mediaMetadata, "item");
        this.item = mediaMetadata;
    }

    public final MediaMetadata getItem() {
        return this.item;
    }
}
