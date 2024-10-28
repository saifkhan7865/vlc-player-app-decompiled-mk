package org.videolan.television.ui;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.interfaces.FocusListener;
import org.videolan.television.databinding.MediaBrowserTvItemListBinding;
import org.videolan.television.ui.MediaTvItemAdapter;
import org.videolan.vlc.interfaces.IEventsHandler;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaTvItemAdapter.kt */
final class MediaTvItemAdapter$MediaItemTVListViewHolder$2$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ MediaBrowserTvItemListBinding $binding;
    final /* synthetic */ MediaTvItemAdapter.MediaItemTVListViewHolder this$0;
    final /* synthetic */ MediaTvItemAdapter this$1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaTvItemAdapter$MediaItemTVListViewHolder$2$1(MediaTvItemAdapter.MediaItemTVListViewHolder mediaItemTVListViewHolder, MediaBrowserTvItemListBinding mediaBrowserTvItemListBinding, MediaTvItemAdapter mediaTvItemAdapter) {
        super(0);
        this.this$0 = mediaItemTVListViewHolder;
        this.$binding = mediaBrowserTvItemListBinding;
        this.this$1 = mediaTvItemAdapter;
    }

    public final void invoke() {
        IEventsHandler<MediaLibraryItem> eventsHandler = this.this$0.getEventsHandler();
        View root = this.$binding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        MediaTvItemAdapter.MediaItemTVListViewHolder mediaItemTVListViewHolder = this.this$0;
        MediaLibraryItem item = mediaItemTVListViewHolder.getItem(mediaItemTVListViewHolder.getLayoutPosition());
        Intrinsics.checkNotNull(item);
        eventsHandler.onItemFocused(root, item);
        if (this.this$1.focusListener != null) {
            FocusListener access$getFocusListener$p = this.this$1.focusListener;
            Intrinsics.checkNotNull(access$getFocusListener$p);
            access$getFocusListener$p.onFocusChanged(this.this$0.getLayoutPosition());
        }
    }
}
