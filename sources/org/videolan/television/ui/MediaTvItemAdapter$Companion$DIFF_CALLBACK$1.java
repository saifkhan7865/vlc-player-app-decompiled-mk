package org.videolan.television.ui;

import androidx.recyclerview.widget.DiffUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.television.ui.MediaTvItemAdapter;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u001a\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¨\u0006\f"}, d2 = {"org/videolan/television/ui/MediaTvItemAdapter$Companion$DIFF_CALLBACK$1", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "areContentsTheSame", "", "oldMedia", "newMedia", "areItemsTheSame", "getChangePayload", "", "oldItem", "newItem", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaTvItemAdapter.kt */
public final class MediaTvItemAdapter$Companion$DIFF_CALLBACK$1 extends DiffUtil.ItemCallback<MediaLibraryItem> {
    public boolean areContentsTheSame(MediaLibraryItem mediaLibraryItem, MediaLibraryItem mediaLibraryItem2) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "oldMedia");
        Intrinsics.checkNotNullParameter(mediaLibraryItem2, "newMedia");
        return false;
    }

    MediaTvItemAdapter$Companion$DIFF_CALLBACK$1() {
    }

    public boolean areItemsTheSame(MediaLibraryItem mediaLibraryItem, MediaLibraryItem mediaLibraryItem2) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "oldMedia");
        Intrinsics.checkNotNullParameter(mediaLibraryItem2, "newMedia");
        if (MediaTvItemAdapter.preventNextAnim || mediaLibraryItem == mediaLibraryItem2) {
            return true;
        }
        if (mediaLibraryItem.getItemType() != mediaLibraryItem2.getItemType() || !mediaLibraryItem.equals(mediaLibraryItem2)) {
            return false;
        }
        return true;
    }

    public Object getChangePayload(MediaLibraryItem mediaLibraryItem, MediaLibraryItem mediaLibraryItem2) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "oldItem");
        Intrinsics.checkNotNullParameter(mediaLibraryItem2, "newItem");
        MediaTvItemAdapter.Companion companion = MediaTvItemAdapter.Companion;
        MediaTvItemAdapter.preventNextAnim = false;
        return 5;
    }
}
