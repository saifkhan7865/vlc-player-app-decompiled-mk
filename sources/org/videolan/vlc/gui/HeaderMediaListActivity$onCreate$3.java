package org.videolan.vlc.gui;

import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.HeaderMediaListActivityBinding;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "playlist", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: HeaderMediaListActivity.kt */
final class HeaderMediaListActivity$onCreate$3 extends Lambda implements Function1<MediaLibraryItem, Unit> {
    final /* synthetic */ HeaderMediaListActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HeaderMediaListActivity$onCreate$3(HeaderMediaListActivity headerMediaListActivity) {
        super(1);
        this.this$0 = headerMediaListActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((MediaLibraryItem) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(MediaLibraryItem mediaLibraryItem) {
        MediaWrapper[] tracks;
        HeaderMediaListActivityBinding access$getBinding$p = this.this$0.binding;
        HeaderMediaListActivityBinding headerMediaListActivityBinding = null;
        if (access$getBinding$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p = null;
        }
        access$getBinding$p.btnFavorite.setImageDrawable(ContextCompat.getDrawable(this.this$0, (mediaLibraryItem == null || !mediaLibraryItem.isFavorite()) ? R.drawable.ic_header_media_favorite_outline : R.drawable.ic_header_media_favorite));
        HeaderMediaListActivityBinding access$getBinding$p2 = this.this$0.binding;
        if (access$getBinding$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p2 = null;
        }
        long j = 0;
        if (!(mediaLibraryItem == null || (tracks = mediaLibraryItem.getTracks()) == null)) {
            for (MediaWrapper length : tracks) {
                j += length.getLength();
            }
        }
        access$getBinding$p2.setTotalDuration(Long.valueOf(j));
        if (mediaLibraryItem instanceof Album) {
            int releaseYear = ((Album) mediaLibraryItem).getReleaseYear();
            HeaderMediaListActivityBinding access$getBinding$p3 = this.this$0.binding;
            if (access$getBinding$p3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                access$getBinding$p3 = null;
            }
            access$getBinding$p3.setReleaseYear(releaseYear > 0 ? String.valueOf(releaseYear) : "");
            if (releaseYear <= 0) {
                HeaderMediaListActivityBinding access$getBinding$p4 = this.this$0.binding;
                if (access$getBinding$p4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    headerMediaListActivityBinding = access$getBinding$p4;
                }
                headerMediaListActivityBinding.releaseDate.setVisibility(8);
            }
        }
    }
}
