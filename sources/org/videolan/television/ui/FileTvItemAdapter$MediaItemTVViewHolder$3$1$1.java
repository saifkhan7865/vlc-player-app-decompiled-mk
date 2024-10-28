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
import org.videolan.television.ui.FileTvItemAdapter;
import org.videolan.vlc.interfaces.IEventsHandler;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileTvItemAdapter.kt */
final class FileTvItemAdapter$MediaItemTVViewHolder$3$1$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ MediaBrowserTvItemBinding $binding;
    final /* synthetic */ FileTvItemAdapter this$0;
    final /* synthetic */ FileTvItemAdapter.MediaItemTVViewHolder this$1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileTvItemAdapter$MediaItemTVViewHolder$3$1$1(FileTvItemAdapter fileTvItemAdapter, FileTvItemAdapter.MediaItemTVViewHolder mediaItemTVViewHolder, MediaBrowserTvItemBinding mediaBrowserTvItemBinding) {
        super(0);
        this.this$0 = fileTvItemAdapter;
        this.this$1 = mediaItemTVViewHolder;
        this.$binding = mediaBrowserTvItemBinding;
    }

    public final void invoke() {
        int size = this.this$0.getDataset().size();
        int layoutPosition = this.this$1.getLayoutPosition();
        if (layoutPosition >= 0 && layoutPosition < size) {
            IEventsHandler<MediaLibraryItem> eventsHandler = this.this$1.getEventsHandler();
            View root = this.$binding.getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
            FileTvItemAdapter.MediaItemTVViewHolder mediaItemTVViewHolder = this.this$1;
            eventsHandler.onItemFocused(root, mediaItemTVViewHolder.getItem(mediaItemTVViewHolder.getLayoutPosition()));
            FocusListener access$getFocusListener$p = this.this$0.focusListener;
            if (access$getFocusListener$p != null) {
                access$getFocusListener$p.onFocusChanged(this.this$1.getLayoutPosition());
            }
        }
    }
}