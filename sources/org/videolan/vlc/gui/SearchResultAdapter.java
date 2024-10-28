package org.videolan.vlc.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.SearchItemBinding;
import org.videolan.vlc.gui.SearchActivity;
import org.videolan.vlc.gui.helpers.SelectorViewHolder;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001!B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0019\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u001c\u0010\u0017\u001a\u00020\u00122\n\u0010\u0018\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0016H\u0016J\u001c\u0010\u001a\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0016H\u0016J\u0019\u0010\u001e\u001a\u00020\u00122\n\u0010\u001f\u001a\u00060\u0007R\u00020\bH\u0000¢\u0006\u0002\b R\u001e\u0010\u0006\u001a\u00060\u0007R\u00020\bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lorg/videolan/vlc/gui/SearchResultAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lorg/videolan/vlc/gui/SearchResultAdapter$ViewHolder;", "mLayoutInflater", "Landroid/view/LayoutInflater;", "(Landroid/view/LayoutInflater;)V", "mClickHandler", "Lorg/videolan/vlc/gui/SearchActivity$ClickHandler;", "Lorg/videolan/vlc/gui/SearchActivity;", "getMClickHandler$vlc_android_release", "()Lorg/videolan/vlc/gui/SearchActivity$ClickHandler;", "setMClickHandler$vlc_android_release", "(Lorg/videolan/vlc/gui/SearchActivity$ClickHandler;)V", "mDataList", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "[Lorg/videolan/medialibrary/media/MediaLibraryItem;", "add", "", "newList", "([Lorg/videolan/medialibrary/media/MediaLibraryItem;)V", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setClickHandler", "clickHandler", "setClickHandler$vlc_android_release", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SearchResultAdapter.kt */
public final class SearchResultAdapter extends RecyclerView.Adapter<ViewHolder> {
    public SearchActivity.ClickHandler mClickHandler;
    private MediaLibraryItem[] mDataList;
    private final LayoutInflater mLayoutInflater;

    public SearchResultAdapter(LayoutInflater layoutInflater) {
        Intrinsics.checkNotNullParameter(layoutInflater, "mLayoutInflater");
        this.mLayoutInflater = layoutInflater;
    }

    public final SearchActivity.ClickHandler getMClickHandler$vlc_android_release() {
        SearchActivity.ClickHandler clickHandler = this.mClickHandler;
        if (clickHandler != null) {
            return clickHandler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mClickHandler");
        return null;
    }

    public final void setMClickHandler$vlc_android_release(SearchActivity.ClickHandler clickHandler) {
        Intrinsics.checkNotNullParameter(clickHandler, "<set-?>");
        this.mClickHandler = clickHandler;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        SearchItemBinding inflate = SearchItemBinding.inflate(this.mLayoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return new ViewHolder(this, inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        MediaLibraryItem[] mediaLibraryItemArr = this.mDataList;
        Intrinsics.checkNotNull(mediaLibraryItemArr);
        MediaLibraryItem mediaLibraryItem = mediaLibraryItemArr[i];
        CharSequence artworkMrl = mediaLibraryItem.getArtworkMrl();
        if (artworkMrl == null || artworkMrl.length() == 0) {
            UiTools uiTools = UiTools.INSTANCE;
            Context context = viewHolder.itemView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            ((SearchItemBinding) viewHolder.getBinding()).setCover(uiTools.getDefaultCover(context, mediaLibraryItem));
        }
        ((SearchItemBinding) viewHolder.getBinding()).setItem(mediaLibraryItem);
        boolean z = mediaLibraryItem instanceof MediaWrapper;
        boolean z2 = !z || ((MediaWrapper) mediaLibraryItem).getType() != 0;
        ((SearchItemBinding) viewHolder.getBinding()).setIsSquare(Boolean.valueOf(z2));
        ((SearchItemBinding) viewHolder.getBinding()).setCoverWidth(KotlinExtensionsKt.getDp(z2 ? 48 : 100));
        SearchItemBinding searchItemBinding = (SearchItemBinding) viewHolder.getBinding();
        String str = null;
        MediaWrapper mediaWrapper = z ? (MediaWrapper) mediaLibraryItem : null;
        if (mediaWrapper == null || mediaWrapper.getType() != 0) {
            str = ((mediaLibraryItem instanceof Playlist) || (mediaLibraryItem instanceof Genre)) ? viewHolder.itemView.getContext().getString(R.string.track_number, new Object[]{Integer.valueOf(mediaLibraryItem.getTracksCount())}) : mediaLibraryItem.getDescription();
        } else {
            MediaWrapper mediaWrapper2 = (MediaWrapper) mediaLibraryItem;
            if (mediaWrapper2.getLength() > 0) {
                String generateResolutionClass = KextensionsKt.generateResolutionClass(mediaWrapper2.getWidth(), mediaWrapper2.getHeight());
                if (generateResolutionClass != null) {
                    str = Tools.millisToString(mediaWrapper2.getLength()) + "  •  " + generateResolutionClass;
                } else {
                    str = Tools.millisToString(mediaWrapper2.getLength());
                }
            }
        }
        searchItemBinding.setDescription(str);
    }

    public final void add(MediaLibraryItem[] mediaLibraryItemArr) {
        Intrinsics.checkNotNullParameter(mediaLibraryItemArr, "newList");
        this.mDataList = mediaLibraryItemArr;
        notifyDataSetChanged();
    }

    public final void setClickHandler$vlc_android_release(SearchActivity.ClickHandler clickHandler) {
        Intrinsics.checkNotNullParameter(clickHandler, "clickHandler");
        setMClickHandler$vlc_android_release(clickHandler);
    }

    public int getItemCount() {
        MediaLibraryItem[] mediaLibraryItemArr = this.mDataList;
        if (mediaLibraryItemArr == null) {
            return 0;
        }
        Intrinsics.checkNotNull(mediaLibraryItemArr);
        return mediaLibraryItemArr.length;
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/SearchResultAdapter$ViewHolder;", "Lorg/videolan/vlc/gui/helpers/SelectorViewHolder;", "Lorg/videolan/vlc/databinding/SearchItemBinding;", "binding", "(Lorg/videolan/vlc/gui/SearchResultAdapter;Lorg/videolan/vlc/databinding/SearchItemBinding;)V", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SearchResultAdapter.kt */
    public final class ViewHolder extends SelectorViewHolder<SearchItemBinding> {
        final /* synthetic */ SearchResultAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(SearchResultAdapter searchResultAdapter, SearchItemBinding searchItemBinding) {
            super(searchItemBinding);
            Intrinsics.checkNotNullParameter(searchItemBinding, "binding");
            this.this$0 = searchResultAdapter;
            searchItemBinding.setHolder(this);
            searchItemBinding.setHandler(searchResultAdapter.getMClickHandler$vlc_android_release());
        }
    }
}
