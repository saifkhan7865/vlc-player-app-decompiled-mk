package org.videolan.moviepedia.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.databinding.MoviepediaItemBinding;
import org.videolan.moviepedia.models.resolver.ResolverMedia;
import org.videolan.moviepedia.ui.MediaScrapingActivity;
import org.videolan.tools.LocaleUtilsKt;
import org.videolan.vlc.gui.helpers.SelectorViewHolder;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001cB\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u001c\u0010\u0012\u001a\u00020\u00132\n\u0010\u0014\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0011H\u0016J\u001c\u0010\u0016\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0011H\u0016J\u0014\u0010\u001a\u001a\u00020\u00132\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eR\u001e\u0010\u0006\u001a\u00060\u0007R\u00020\bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/videolan/moviepedia/ui/MediaScrapingResultAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/videolan/moviepedia/ui/MediaScrapingResultAdapter$ViewHolder;", "layoutInflater", "Landroid/view/LayoutInflater;", "(Landroid/view/LayoutInflater;)V", "clickHandler", "Lorg/videolan/moviepedia/ui/MediaScrapingActivity$ClickHandler;", "Lorg/videolan/moviepedia/ui/MediaScrapingActivity;", "getClickHandler$moviepedia_release", "()Lorg/videolan/moviepedia/ui/MediaScrapingActivity$ClickHandler;", "setClickHandler$moviepedia_release", "(Lorg/videolan/moviepedia/ui/MediaScrapingActivity$ClickHandler;)V", "dataList", "", "Lorg/videolan/moviepedia/models/resolver/ResolverMedia;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setItems", "newList", "ViewHolder", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingResultAdapter.kt */
public final class MediaScrapingResultAdapter extends RecyclerView.Adapter<ViewHolder> {
    public MediaScrapingActivity.ClickHandler clickHandler;
    private List<? extends ResolverMedia> dataList;
    private final LayoutInflater layoutInflater;

    public MediaScrapingResultAdapter(LayoutInflater layoutInflater2) {
        Intrinsics.checkNotNullParameter(layoutInflater2, "layoutInflater");
        this.layoutInflater = layoutInflater2;
    }

    public final MediaScrapingActivity.ClickHandler getClickHandler$moviepedia_release() {
        MediaScrapingActivity.ClickHandler clickHandler2 = this.clickHandler;
        if (clickHandler2 != null) {
            return clickHandler2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("clickHandler");
        return null;
    }

    public final void setClickHandler$moviepedia_release(MediaScrapingActivity.ClickHandler clickHandler2) {
        Intrinsics.checkNotNullParameter(clickHandler2, "<set-?>");
        this.clickHandler = clickHandler2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        MoviepediaItemBinding inflate = MoviepediaItemBinding.inflate(this.layoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        List<? extends ResolverMedia> list = this.dataList;
        Intrinsics.checkNotNull(list);
        ResolverMedia resolverMedia = (ResolverMedia) list.get(i);
        ((MoviepediaItemBinding) viewHolder.getBinding()).setItem(resolverMedia);
        Context context = this.layoutInflater.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        ((MoviepediaItemBinding) viewHolder.getBinding()).setImageUrl(resolverMedia.imageUri(LocaleUtilsKt.getLocaleLanguages(context)));
    }

    public final void setItems(List<? extends ResolverMedia> list) {
        Intrinsics.checkNotNullParameter(list, "newList");
        this.dataList = list;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        List<? extends ResolverMedia> list = this.dataList;
        if (list == null) {
            return 0;
        }
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/moviepedia/ui/MediaScrapingResultAdapter$ViewHolder;", "Lorg/videolan/vlc/gui/helpers/SelectorViewHolder;", "Lorg/videolan/moviepedia/databinding/MoviepediaItemBinding;", "binding", "(Lorg/videolan/moviepedia/ui/MediaScrapingResultAdapter;Lorg/videolan/moviepedia/databinding/MoviepediaItemBinding;)V", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaScrapingResultAdapter.kt */
    public final class ViewHolder extends SelectorViewHolder<MoviepediaItemBinding> {
        final /* synthetic */ MediaScrapingResultAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(MediaScrapingResultAdapter mediaScrapingResultAdapter, MoviepediaItemBinding moviepediaItemBinding) {
            super(moviepediaItemBinding);
            Intrinsics.checkNotNullParameter(moviepediaItemBinding, "binding");
            this.this$0 = mediaScrapingResultAdapter;
            moviepediaItemBinding.setHandler(mediaScrapingResultAdapter.getClickHandler$moviepedia_release());
        }
    }
}
