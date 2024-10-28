package org.videolan.television.ui;

import androidx.recyclerview.widget.DiffUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.television.ui.MediaScrapingTvItemAdapter;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u001a\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¨\u0006\f"}, d2 = {"org/videolan/television/ui/MediaScrapingTvItemAdapter$Companion$DIFF_CALLBACK$1", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "areContentsTheSame", "", "oldMedia", "newMedia", "areItemsTheSame", "getChangePayload", "", "oldItem", "newItem", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingTvItemAdapter.kt */
public final class MediaScrapingTvItemAdapter$Companion$DIFF_CALLBACK$1 extends DiffUtil.ItemCallback<MediaMetadataWithImages> {
    public boolean areContentsTheSame(MediaMetadataWithImages mediaMetadataWithImages, MediaMetadataWithImages mediaMetadataWithImages2) {
        Intrinsics.checkNotNullParameter(mediaMetadataWithImages, "oldMedia");
        Intrinsics.checkNotNullParameter(mediaMetadataWithImages2, "newMedia");
        return false;
    }

    MediaScrapingTvItemAdapter$Companion$DIFF_CALLBACK$1() {
    }

    public boolean areItemsTheSame(MediaMetadataWithImages mediaMetadataWithImages, MediaMetadataWithImages mediaMetadataWithImages2) {
        Intrinsics.checkNotNullParameter(mediaMetadataWithImages, "oldMedia");
        Intrinsics.checkNotNullParameter(mediaMetadataWithImages2, "newMedia");
        return MediaScrapingTvItemAdapter.preventNextAnim || mediaMetadataWithImages == mediaMetadataWithImages2;
    }

    public Object getChangePayload(MediaMetadataWithImages mediaMetadataWithImages, MediaMetadataWithImages mediaMetadataWithImages2) {
        Intrinsics.checkNotNullParameter(mediaMetadataWithImages, "oldItem");
        Intrinsics.checkNotNullParameter(mediaMetadataWithImages2, "newItem");
        MediaScrapingTvItemAdapter.Companion companion = MediaScrapingTvItemAdapter.Companion;
        MediaScrapingTvItemAdapter.preventNextAnim = false;
        return 5;
    }
}
