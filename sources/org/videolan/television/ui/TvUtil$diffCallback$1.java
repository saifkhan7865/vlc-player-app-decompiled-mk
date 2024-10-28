package org.videolan.television.ui;

import androidx.leanback.widget.DiffCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0017J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"org/videolan/television/ui/TvUtil$diffCallback$1", "Landroidx/leanback/widget/DiffCallback;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "getChangePayload", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TvUtil.kt */
public final class TvUtil$diffCallback$1 extends DiffCallback<MediaLibraryItem> {
    TvUtil$diffCallback$1() {
    }

    public boolean areItemsTheSame(MediaLibraryItem mediaLibraryItem, MediaLibraryItem mediaLibraryItem2) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "oldItem");
        Intrinsics.checkNotNullParameter(mediaLibraryItem2, "newItem");
        return mediaLibraryItem.equals(mediaLibraryItem2) && Intrinsics.areEqual((Object) mediaLibraryItem.getTitle(), (Object) mediaLibraryItem2.getTitle());
    }

    public boolean areContentsTheSame(MediaLibraryItem mediaLibraryItem, MediaLibraryItem mediaLibraryItem2) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "oldItem");
        Intrinsics.checkNotNullParameter(mediaLibraryItem2, "newItem");
        if (mediaLibraryItem.getItemType() == 64) {
            return Intrinsics.areEqual((Object) mediaLibraryItem.getDescription(), (Object) mediaLibraryItem2.getDescription());
        }
        MediaWrapper mediaWrapper = null;
        MediaWrapper mediaWrapper2 = mediaLibraryItem instanceof MediaWrapper ? (MediaWrapper) mediaLibraryItem : null;
        if (mediaWrapper2 == null) {
            return true;
        }
        if (mediaLibraryItem2 instanceof MediaWrapper) {
            mediaWrapper = (MediaWrapper) mediaLibraryItem2;
        }
        if (mediaWrapper == null || mediaWrapper2 == mediaWrapper) {
            return true;
        }
        if (mediaWrapper2.getTime() == mediaWrapper.getTime() && Intrinsics.areEqual((Object) mediaWrapper2.getArtworkMrl(), (Object) mediaWrapper.getArtworkMrl()) && mediaWrapper2.getSeen() == mediaWrapper.getSeen()) {
            return true;
        }
        return false;
    }

    public Object getChangePayload(MediaLibraryItem mediaLibraryItem, MediaLibraryItem mediaLibraryItem2) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "oldItem");
        Intrinsics.checkNotNullParameter(mediaLibraryItem2, "newItem");
        if (mediaLibraryItem.getItemType() == 64) {
            return 4;
        }
        MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
        MediaWrapper mediaWrapper2 = (MediaWrapper) mediaLibraryItem2;
        if (mediaWrapper.getTime() != mediaWrapper2.getTime()) {
            return 2;
        }
        return Integer.valueOf(!Intrinsics.areEqual((Object) mediaWrapper.getArtworkMrl(), (Object) mediaWrapper2.getArtworkMrl()) ? 1 : 3);
    }
}
