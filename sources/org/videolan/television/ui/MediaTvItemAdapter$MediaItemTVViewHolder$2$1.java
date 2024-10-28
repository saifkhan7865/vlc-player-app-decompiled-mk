package org.videolan.television.ui;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.interfaces.FocusListener;
import org.videolan.television.databinding.MediaBrowserTvItemBinding;
import org.videolan.television.ui.MediaTvItemAdapter;
import org.videolan.vlc.interfaces.IEventsHandler;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaTvItemAdapter.kt */
final class MediaTvItemAdapter$MediaItemTVViewHolder$2$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ MediaBrowserTvItemBinding $binding;
    final /* synthetic */ MediaTvItemAdapter.MediaItemTVViewHolder this$0;
    final /* synthetic */ MediaTvItemAdapter this$1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaTvItemAdapter$MediaItemTVViewHolder$2$1(MediaTvItemAdapter.MediaItemTVViewHolder mediaItemTVViewHolder, MediaBrowserTvItemBinding mediaBrowserTvItemBinding, MediaTvItemAdapter mediaTvItemAdapter) {
        super(0);
        this.this$0 = mediaItemTVViewHolder;
        this.$binding = mediaBrowserTvItemBinding;
        this.this$1 = mediaTvItemAdapter;
    }

    public final void invoke() {
        IEventsHandler<MediaLibraryItem> eventsHandler = this.this$0.getEventsHandler();
        View root = this.$binding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        MediaTvItemAdapter.MediaItemTVViewHolder mediaItemTVViewHolder = this.this$0;
        MediaLibraryItem item = mediaItemTVViewHolder.getItem(mediaItemTVViewHolder.getLayoutPosition());
        Intrinsics.checkNotNull(item);
        eventsHandler.onItemFocused(root, item);
        if (this.this$1.focusListener != null) {
            FocusListener access$getFocusListener$p = this.this$1.focusListener;
            Intrinsics.checkNotNull(access$getFocusListener$p);
            access$getFocusListener$p.onFocusChanged(this.this$0.getLayoutPosition());
        }
    }
}
