package org.videolan.television.ui;

import androidx.leanback.widget.DiffCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.database.models.MediaImage;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"org/videolan/television/ui/MediaScrapingTvshowDetailsFragment$imageDiffCallback$1", "Landroidx/leanback/widget/DiffCallback;", "Lorg/videolan/moviepedia/database/models/MediaImage;", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingTvshowDetailsFragment.kt */
public final class MediaScrapingTvshowDetailsFragment$imageDiffCallback$1 extends DiffCallback<MediaImage> {
    MediaScrapingTvshowDetailsFragment$imageDiffCallback$1() {
    }

    public boolean areItemsTheSame(MediaImage mediaImage, MediaImage mediaImage2) {
        Intrinsics.checkNotNullParameter(mediaImage, "oldItem");
        Intrinsics.checkNotNullParameter(mediaImage2, "newItem");
        return Intrinsics.areEqual((Object) mediaImage.getUrl(), (Object) mediaImage2.getUrl());
    }

    public boolean areContentsTheSame(MediaImage mediaImage, MediaImage mediaImage2) {
        Intrinsics.checkNotNullParameter(mediaImage, "oldItem");
        Intrinsics.checkNotNullParameter(mediaImage2, "newItem");
        return Intrinsics.areEqual((Object) mediaImage.getUrl(), (Object) mediaImage2.getUrl());
    }
}
