package org.videolan.television.ui;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.resources.interfaces.FocusListener;
import org.videolan.television.databinding.MovieBrowserTvItemBinding;
import org.videolan.television.ui.MediaScrapingTvItemAdapter;
import org.videolan.vlc.interfaces.IEventsHandler;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingTvItemAdapter.kt */
final class MediaScrapingTvItemAdapter$MovieItemTVViewHolder$2$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ MovieBrowserTvItemBinding $binding;
    final /* synthetic */ MediaScrapingTvItemAdapter.MovieItemTVViewHolder this$0;
    final /* synthetic */ MediaScrapingTvItemAdapter this$1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingTvItemAdapter$MovieItemTVViewHolder$2$1(MediaScrapingTvItemAdapter.MovieItemTVViewHolder movieItemTVViewHolder, MovieBrowserTvItemBinding movieBrowserTvItemBinding, MediaScrapingTvItemAdapter mediaScrapingTvItemAdapter) {
        super(0);
        this.this$0 = movieItemTVViewHolder;
        this.$binding = movieBrowserTvItemBinding;
        this.this$1 = mediaScrapingTvItemAdapter;
    }

    public final void invoke() {
        IEventsHandler<MediaMetadataWithImages> eventsHandler = this.this$0.getEventsHandler();
        View root = this.$binding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        MediaScrapingTvItemAdapter.MovieItemTVViewHolder movieItemTVViewHolder = this.this$0;
        MediaMetadataWithImages item = movieItemTVViewHolder.getItem(movieItemTVViewHolder.getLayoutPosition());
        Intrinsics.checkNotNull(item);
        eventsHandler.onItemFocused(root, item);
        if (this.this$1.focusListener != null) {
            FocusListener access$getFocusListener$p = this.this$1.focusListener;
            Intrinsics.checkNotNull(access$getFocusListener$p);
            access$getFocusListener$p.onFocusChanged(this.this$0.getLayoutPosition());
        }
    }
}
