package org.videolan.television.ui;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.resources.interfaces.FocusListener;
import org.videolan.television.databinding.MovieBrowserTvItemBinding;
import org.videolan.television.databinding.MovieBrowserTvItemListBinding;
import org.videolan.television.ui.browser.TvAdapterUtils;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;
import org.videolan.vlc.gui.helpers.SelectorViewHolder;
import org.videolan.vlc.gui.view.FastScroller;
import org.videolan.vlc.interfaces.IEventsHandler;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 /2\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00012\u00020\u00052\u00020\u0006:\u0004./01B-\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\fH\u0016J\b\u0010\u001f\u001a\u00020\u000eH\u0016J\b\u0010 \u001a\u00020\u000eH\u0016J\u001e\u0010!\u001a\u00020\u001c2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u001e\u001a\u00020\fH\u0016J,\u0010!\u001a\u00020\u001c2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u001e\u001a\u00020\f2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$H\u0016J\u001e\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\fH\u0016J\u0016\u0010*\u001a\u00020\u001c2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\u0012\u0010+\u001a\u00020\u001c2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010,\u001a\u00020\u001c2\b\u0010-\u001a\u0004\u0018\u00010%H\u0016R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018¨\u00062"}, d2 = {"Lorg/videolan/television/ui/MediaScrapingTvItemAdapter;", "Landroidx/paging/PagedListAdapter;", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "Lorg/videolan/television/ui/MediaScrapingTvItemAdapter$AbstractMediaScrapingItemViewHolder;", "Landroidx/databinding/ViewDataBinding;", "Lorg/videolan/vlc/gui/view/FastScroller$SeparatedAdapter;", "Lorg/videolan/television/ui/TvItemAdapter;", "type", "", "eventsHandler", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "itemSize", "", "inGrid", "", "(JLorg/videolan/vlc/interfaces/IEventsHandler;IZ)V", "defaultCover", "Landroid/graphics/drawable/BitmapDrawable;", "focusListener", "Lorg/videolan/resources/interfaces/FocusListener;", "focusNext", "getFocusNext", "()I", "setFocusNext", "(I)V", "getItemSize", "setItemSize", "displaySwitch", "", "getItemViewType", "position", "hasSections", "isEmpty", "onBindViewHolder", "holder", "payloads", "", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onViewRecycled", "setOnFocusChangeListener", "submitList", "pagedList", "AbstractMediaScrapingItemViewHolder", "Companion", "MovieItemTVListViewHolder", "MovieItemTVViewHolder", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingTvItemAdapter.kt */
public final class MediaScrapingTvItemAdapter extends PagedListAdapter<MediaMetadataWithImages, AbstractMediaScrapingItemViewHolder<ViewDataBinding>> implements FastScroller.SeparatedAdapter, TvItemAdapter {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final MediaScrapingTvItemAdapter$Companion$DIFF_CALLBACK$1 DIFF_CALLBACK = new MediaScrapingTvItemAdapter$Companion$DIFF_CALLBACK$1();
    private static final String TAG = "VLC/MediaTvItemAdapter";
    /* access modifiers changed from: private */
    public static boolean preventNextAnim;
    /* access modifiers changed from: private */
    public final BitmapDrawable defaultCover;
    private final IEventsHandler<MediaMetadataWithImages> eventsHandler;
    /* access modifiers changed from: private */
    public FocusListener focusListener;
    private int focusNext;
    private boolean inGrid;
    private int itemSize;

    public boolean hasSections() {
        return true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MediaScrapingTvItemAdapter(long j, IEventsHandler iEventsHandler, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, iEventsHandler, i, (i2 & 8) != 0 ? true : z);
    }

    public static final /* synthetic */ MediaMetadataWithImages access$getItem(MediaScrapingTvItemAdapter mediaScrapingTvItemAdapter, int i) {
        return (MediaMetadataWithImages) mediaScrapingTvItemAdapter.getItem(i);
    }

    public final int getItemSize() {
        return this.itemSize;
    }

    public final void setItemSize(int i) {
        this.itemSize = i;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MediaScrapingTvItemAdapter(long j, IEventsHandler<MediaMetadataWithImages> iEventsHandler, int i, boolean z) {
        super(DIFF_CALLBACK);
        Context context;
        Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
        this.eventsHandler = iEventsHandler;
        this.itemSize = i;
        this.inGrid = z;
        this.focusNext = -1;
        BitmapDrawable bitmapDrawable = null;
        if (iEventsHandler instanceof Context) {
            context = (Context) iEventsHandler;
        } else if (iEventsHandler instanceof Fragment) {
            Intrinsics.checkNotNull(iEventsHandler, "null cannot be cast to non-null type androidx.fragment.app.Fragment");
            context = ((Fragment) iEventsHandler).getContext();
        } else {
            context = null;
        }
        this.defaultCover = context != null ? ImageLoaderKt.getMoviepediaIconDrawable(context, j, true) : bitmapDrawable;
    }

    public int getFocusNext() {
        return this.focusNext;
    }

    public void setFocusNext(int i) {
        this.focusNext = i;
    }

    public void displaySwitch(boolean z) {
        this.inGrid = z;
    }

    public AbstractMediaScrapingItemViewHolder<ViewDataBinding> onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (this.inGrid) {
            MovieBrowserTvItemBinding inflate = MovieBrowserTvItemBinding.inflate(from, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new MovieItemTVViewHolder(this, inflate, this.eventsHandler);
        }
        MovieBrowserTvItemListBinding inflate2 = MovieBrowserTvItemListBinding.inflate(from, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate2, "inflate(...)");
        return new MovieItemTVListViewHolder(this, inflate2, this.eventsHandler);
    }

    public int getItemViewType(int i) {
        return this.inGrid ^ true ? 1 : 0;
    }

    public void onBindViewHolder(AbstractMediaScrapingItemViewHolder<ViewDataBinding> abstractMediaScrapingItemViewHolder, int i) {
        Intrinsics.checkNotNullParameter(abstractMediaScrapingItemViewHolder, "holder");
        if (i < getItemCount()) {
            abstractMediaScrapingItemViewHolder.setItem((MediaMetadataWithImages) getItem(i));
            abstractMediaScrapingItemViewHolder.getBinding().executePendingBindings();
            if (i == getFocusNext()) {
                abstractMediaScrapingItemViewHolder.getBinding().getRoot().requestFocus();
                setFocusNext(-1);
            }
        }
    }

    public void onBindViewHolder(AbstractMediaScrapingItemViewHolder<ViewDataBinding> abstractMediaScrapingItemViewHolder, int i, List<? extends Object> list) {
        Intrinsics.checkNotNullParameter(abstractMediaScrapingItemViewHolder, "holder");
        Intrinsics.checkNotNullParameter(list, "payloads");
        if (list.isEmpty()) {
            onBindViewHolder(abstractMediaScrapingItemViewHolder, i);
            return;
        }
        Object obj = list.get(0);
        if (obj instanceof MediaLibraryItem) {
            boolean hasStateFlags = ((MediaLibraryItem) obj).hasStateFlags(1);
            abstractMediaScrapingItemViewHolder.setCoverlay(hasStateFlags);
            abstractMediaScrapingItemViewHolder.selectView(hasStateFlags);
        }
    }

    public void onViewRecycled(AbstractMediaScrapingItemViewHolder<ViewDataBinding> abstractMediaScrapingItemViewHolder) {
        Intrinsics.checkNotNullParameter(abstractMediaScrapingItemViewHolder, "holder");
        super.onViewRecycled(abstractMediaScrapingItemViewHolder);
        abstractMediaScrapingItemViewHolder.recycle();
    }

    public void submitList(Object obj) {
        if (obj == null) {
            submitList((PagedList) null);
        }
        if (obj instanceof PagedList) {
            submitList((PagedList) obj);
        }
    }

    public boolean isEmpty() {
        PagedList currentList = getCurrentList();
        boolean z = false;
        if (currentList != null && !currentList.isEmpty()) {
            z = true;
        }
        return !z;
    }

    public void setOnFocusChangeListener(FocusListener focusListener2) {
        this.focusListener = focusListener2;
    }

    @Metadata(d1 = {"\u0000\u001f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/videolan/television/ui/MediaScrapingTvItemAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "org/videolan/television/ui/MediaScrapingTvItemAdapter$Companion$DIFF_CALLBACK$1", "Lorg/videolan/television/ui/MediaScrapingTvItemAdapter$Companion$DIFF_CALLBACK$1;", "TAG", "", "preventNextAnim", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaScrapingTvItemAdapter.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\rH&J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0011J\u000e\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0018\u001a\u00020\u000fH&J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u0014H&J\u0012\u0010\u001b\u001a\u00020\u000f2\b\u0010\u001c\u001a\u0004\u0018\u00010\bH&R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u001d"}, d2 = {"Lorg/videolan/television/ui/MediaScrapingTvItemAdapter$AbstractMediaScrapingItemViewHolder;", "T", "Landroidx/databinding/ViewDataBinding;", "Lorg/videolan/vlc/gui/helpers/SelectorViewHolder;", "binding", "(Landroidx/databinding/ViewDataBinding;)V", "eventsHandler", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "getEventsHandler", "()Lorg/videolan/vlc/interfaces/IEventsHandler;", "getItem", "layoutPosition", "", "onClick", "", "v", "Landroid/view/View;", "onImageClick", "onLongClick", "", "view", "onMainActionClick", "onMoreClick", "recycle", "setCoverlay", "selected", "setItem", "item", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaScrapingTvItemAdapter.kt */
    public static abstract class AbstractMediaScrapingItemViewHolder<T extends ViewDataBinding> extends SelectorViewHolder<T> {
        public abstract IEventsHandler<MediaMetadataWithImages> getEventsHandler();

        public abstract MediaMetadataWithImages getItem(int i);

        public abstract void recycle();

        public abstract void setCoverlay(boolean z);

        public abstract void setItem(MediaMetadataWithImages mediaMetadataWithImages);

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AbstractMediaScrapingItemViewHolder(T t) {
            super(t);
            Intrinsics.checkNotNullParameter(t, "binding");
        }

        public final void onClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            MediaMetadataWithImages item = getItem(getLayoutPosition());
            if (item != null) {
                getEventsHandler().onClick(view, getLayoutPosition(), item);
            }
        }

        public final void onMoreClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            MediaMetadataWithImages item = getItem(getLayoutPosition());
            if (item != null) {
                getEventsHandler().onCtxClick(view, getLayoutPosition(), item);
            }
        }

        public final boolean onLongClick(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            MediaMetadataWithImages item = getItem(getLayoutPosition());
            if (item != null) {
                return getEventsHandler().onLongClick(view, getLayoutPosition(), item);
            }
            return false;
        }

        public final void onImageClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            MediaMetadataWithImages item = getItem(getLayoutPosition());
            if (item != null) {
                getEventsHandler().onImageClick(view, getLayoutPosition(), item);
            }
        }

        public final void onMainActionClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            MediaMetadataWithImages item = getItem(getLayoutPosition());
            if (item != null) {
                getEventsHandler().onMainActionClick(view, getLayoutPosition(), item);
            }
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0006H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lorg/videolan/television/ui/MediaScrapingTvItemAdapter$MovieItemTVViewHolder;", "Lorg/videolan/television/ui/MediaScrapingTvItemAdapter$AbstractMediaScrapingItemViewHolder;", "Lorg/videolan/television/databinding/MovieBrowserTvItemBinding;", "binding", "eventsHandler", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "(Lorg/videolan/television/ui/MediaScrapingTvItemAdapter;Lorg/videolan/television/databinding/MovieBrowserTvItemBinding;Lorg/videolan/vlc/interfaces/IEventsHandler;)V", "getEventsHandler", "()Lorg/videolan/vlc/interfaces/IEventsHandler;", "getItem", "layoutPosition", "", "recycle", "", "setCoverlay", "selected", "", "setItem", "item", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaScrapingTvItemAdapter.kt */
    public final class MovieItemTVViewHolder extends AbstractMediaScrapingItemViewHolder<MovieBrowserTvItemBinding> {
        private final IEventsHandler<MediaMetadataWithImages> eventsHandler;
        final /* synthetic */ MediaScrapingTvItemAdapter this$0;

        public void setCoverlay(boolean z) {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MovieItemTVViewHolder(MediaScrapingTvItemAdapter mediaScrapingTvItemAdapter, MovieBrowserTvItemBinding movieBrowserTvItemBinding, IEventsHandler<MediaMetadataWithImages> iEventsHandler) {
            super(movieBrowserTvItemBinding);
            Intrinsics.checkNotNullParameter(movieBrowserTvItemBinding, "binding");
            Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
            this.this$0 = mediaScrapingTvItemAdapter;
            this.eventsHandler = iEventsHandler;
            movieBrowserTvItemBinding.setHolder(this);
            if (mediaScrapingTvItemAdapter.defaultCover != null) {
                movieBrowserTvItemBinding.setCover(mediaScrapingTvItemAdapter.defaultCover);
            }
            movieBrowserTvItemBinding.setScaleType(ImageView.ScaleType.FIT_CENTER);
            if (AndroidUtil.isMarshMallowOrLater) {
                this.itemView.setOnContextClickListener(new MediaScrapingTvItemAdapter$MovieItemTVViewHolder$$ExternalSyntheticLambda0(this));
            }
            movieBrowserTvItemBinding.container.getLayoutParams().width = mediaScrapingTvItemAdapter.getItemSize();
            movieBrowserTvItemBinding.container.setOnFocusChangeListener(new MediaScrapingTvItemAdapter$MovieItemTVViewHolder$$ExternalSyntheticLambda1(mediaScrapingTvItemAdapter, movieBrowserTvItemBinding, this));
            movieBrowserTvItemBinding.container.setClipToOutline(true);
        }

        public IEventsHandler<MediaMetadataWithImages> getEventsHandler() {
            return this.eventsHandler;
        }

        public MediaMetadataWithImages getItem(int i) {
            return MediaScrapingTvItemAdapter.access$getItem(this.this$0, i);
        }

        /* access modifiers changed from: private */
        public static final boolean _init_$lambda$0(MovieItemTVViewHolder movieItemTVViewHolder, View view) {
            Intrinsics.checkNotNullParameter(movieItemTVViewHolder, "this$0");
            Intrinsics.checkNotNull(view);
            movieItemTVViewHolder.onMoreClick(view);
            return true;
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$1(MediaScrapingTvItemAdapter mediaScrapingTvItemAdapter, MovieBrowserTvItemBinding movieBrowserTvItemBinding, MovieItemTVViewHolder movieItemTVViewHolder, View view, boolean z) {
            Intrinsics.checkNotNullParameter(mediaScrapingTvItemAdapter, "this$0");
            Intrinsics.checkNotNullParameter(movieBrowserTvItemBinding, "$binding");
            Intrinsics.checkNotNullParameter(movieItemTVViewHolder, "this$1");
            TvAdapterUtils tvAdapterUtils = TvAdapterUtils.INSTANCE;
            int itemSize = mediaScrapingTvItemAdapter.getItemSize();
            FocusableConstraintLayout focusableConstraintLayout = movieBrowserTvItemBinding.container;
            Intrinsics.checkNotNullExpressionValue(focusableConstraintLayout, "container");
            tvAdapterUtils.itemFocusChange(z, itemSize, focusableConstraintLayout, false, new MediaScrapingTvItemAdapter$MovieItemTVViewHolder$2$1(movieItemTVViewHolder, movieBrowserTvItemBinding, mediaScrapingTvItemAdapter));
        }

        public void recycle() {
            if (this.this$0.defaultCover != null) {
                ((MovieBrowserTvItemBinding) getBinding()).setCover(this.this$0.defaultCover);
            }
            ((MovieBrowserTvItemBinding) getBinding()).setScaleType(ImageView.ScaleType.FIT_CENTER);
            ((MovieBrowserTvItemBinding) getBinding()).title.setText("");
            ((MovieBrowserTvItemBinding) getBinding()).subtitle.setText("");
            ((MovieBrowserTvItemBinding) getBinding()).mediaCover.resetFade();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x000b, code lost:
            r0 = r14.getMetadata();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setItem(org.videolan.moviepedia.database.models.MediaMetadataWithImages r14) {
            /*
                r13 = this;
                androidx.databinding.ViewDataBinding r0 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemBinding r0 = (org.videolan.television.databinding.MovieBrowserTvItemBinding) r0
                r0.setItem(r14)
                if (r14 == 0) goto L_0x0016
                org.videolan.moviepedia.database.models.MediaMetadata r0 = r14.getMetadata()
                if (r0 == 0) goto L_0x0016
                java.lang.String r0 = r0.getSummary()
                goto L_0x0017
            L_0x0016:
                r0 = 0
            L_0x0017:
                java.lang.String r1 = ""
                r2 = 0
                r3 = 0
                if (r14 == 0) goto L_0x0084
                org.videolan.medialibrary.interfaces.media.MediaWrapper r14 = r14.getMedia()
                if (r14 == 0) goto L_0x0084
                int r5 = r14.getType()
                if (r5 != 0) goto L_0x0084
                int r0 = r14.getWidth()
                int r5 = r14.getHeight()
                java.lang.String r0 = org.videolan.vlc.util.KextensionsKt.generateResolutionClass(r0, r5)
                if (r0 != 0) goto L_0x0039
                goto L_0x003a
            L_0x0039:
                r1 = r0
            L_0x003a:
                long r5 = r14.getTime()
                int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
                if (r0 != 0) goto L_0x004b
                long r5 = r14.getLength()
                java.lang.String r0 = org.videolan.medialibrary.Tools.millisToString(r5)
                goto L_0x004f
            L_0x004b:
                java.lang.String r0 = org.videolan.medialibrary.Tools.getProgressText(r14)
            L_0x004f:
                androidx.databinding.ViewDataBinding r5 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemBinding r5 = (org.videolan.television.databinding.MovieBrowserTvItemBinding) r5
                r5.setBadge(r1)
                long r5 = r14.getSeen()
                long r7 = r14.getLength()
                int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
                if (r9 <= 0) goto L_0x0078
                long r7 = r14.getDisplayTime()
                int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
                if (r9 <= 0) goto L_0x0078
                long r9 = r14.getLength()
                r14 = 1000(0x3e8, float:1.401E-42)
                long r11 = (long) r14
                long r9 = r9 / r11
                int r14 = (int) r9
                long r7 = r7 / r11
                int r8 = (int) r7
                goto L_0x007a
            L_0x0078:
                r14 = 0
                r8 = 0
            L_0x007a:
                androidx.databinding.ViewDataBinding r7 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemBinding r7 = (org.videolan.television.databinding.MovieBrowserTvItemBinding) r7
                r7.setMax(r14)
                goto L_0x0086
            L_0x0084:
                r5 = r3
                r8 = 0
            L_0x0086:
                androidx.databinding.ViewDataBinding r14 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemBinding r14 = (org.videolan.television.databinding.MovieBrowserTvItemBinding) r14
                r14.setProgress(r8)
                androidx.databinding.ViewDataBinding r14 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemBinding r14 = (org.videolan.television.databinding.MovieBrowserTvItemBinding) r14
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r2)
                r14.setIsSquare(r7)
                androidx.databinding.ViewDataBinding r14 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemBinding r14 = (org.videolan.television.databinding.MovieBrowserTvItemBinding) r14
                r14.setSeen(r5)
                androidx.databinding.ViewDataBinding r14 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemBinding r14 = (org.videolan.television.databinding.MovieBrowserTvItemBinding) r14
                r14.setDescription(r0)
                androidx.databinding.ViewDataBinding r14 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemBinding r14 = (org.videolan.television.databinding.MovieBrowserTvItemBinding) r14
                android.widget.ImageView$ScaleType r0 = android.widget.ImageView.ScaleType.CENTER_INSIDE
                r14.setScaleType(r0)
                androidx.databinding.ViewDataBinding r14 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemBinding r14 = (org.videolan.television.databinding.MovieBrowserTvItemBinding) r14
                android.widget.ImageView r14 = r14.mlItemSeen
                r0 = 8
                int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
                if (r7 != 0) goto L_0x00ca
                r5 = 8
                goto L_0x00cb
            L_0x00ca:
                r5 = 0
            L_0x00cb:
                r14.setVisibility(r5)
                androidx.databinding.ViewDataBinding r14 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemBinding r14 = (org.videolan.television.databinding.MovieBrowserTvItemBinding) r14
                android.widget.ProgressBar r14 = r14.progressBar
                long r5 = (long) r8
                int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
                if (r7 > 0) goto L_0x00de
                r3 = 8
                goto L_0x00df
            L_0x00de:
                r3 = 0
            L_0x00df:
                r14.setVisibility(r3)
                androidx.databinding.ViewDataBinding r14 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemBinding r14 = (org.videolan.television.databinding.MovieBrowserTvItemBinding) r14
                android.widget.TextView r14 = r14.badgeTV
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                boolean r1 = kotlin.text.StringsKt.isBlank(r1)
                if (r1 == 0) goto L_0x00f4
                r2 = 8
            L_0x00f4:
                r14.setVisibility(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.MediaScrapingTvItemAdapter.MovieItemTVViewHolder.setItem(org.videolan.moviepedia.database.models.MediaMetadataWithImages):void");
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0006H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Lorg/videolan/television/ui/MediaScrapingTvItemAdapter$MovieItemTVListViewHolder;", "Lorg/videolan/television/ui/MediaScrapingTvItemAdapter$AbstractMediaScrapingItemViewHolder;", "Lorg/videolan/television/databinding/MovieBrowserTvItemListBinding;", "binding", "eventsHandler", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "(Lorg/videolan/television/ui/MediaScrapingTvItemAdapter;Lorg/videolan/television/databinding/MovieBrowserTvItemListBinding;Lorg/videolan/vlc/interfaces/IEventsHandler;)V", "getEventsHandler", "()Lorg/videolan/vlc/interfaces/IEventsHandler;", "getItem", "layoutPosition", "", "recycle", "", "setCoverlay", "selected", "", "setItem", "item", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaScrapingTvItemAdapter.kt */
    public final class MovieItemTVListViewHolder extends AbstractMediaScrapingItemViewHolder<MovieBrowserTvItemListBinding> {
        private final IEventsHandler<MediaMetadataWithImages> eventsHandler;
        final /* synthetic */ MediaScrapingTvItemAdapter this$0;

        public void setCoverlay(boolean z) {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MovieItemTVListViewHolder(MediaScrapingTvItemAdapter mediaScrapingTvItemAdapter, MovieBrowserTvItemListBinding movieBrowserTvItemListBinding, IEventsHandler<MediaMetadataWithImages> iEventsHandler) {
            super(movieBrowserTvItemListBinding);
            Intrinsics.checkNotNullParameter(movieBrowserTvItemListBinding, "binding");
            Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
            this.this$0 = mediaScrapingTvItemAdapter;
            this.eventsHandler = iEventsHandler;
            movieBrowserTvItemListBinding.setHolder(this);
            if (mediaScrapingTvItemAdapter.defaultCover != null) {
                movieBrowserTvItemListBinding.setCover(mediaScrapingTvItemAdapter.defaultCover);
            }
            movieBrowserTvItemListBinding.setScaleType(ImageView.ScaleType.FIT_CENTER);
            if (AndroidUtil.isMarshMallowOrLater) {
                this.itemView.setOnContextClickListener(new MediaScrapingTvItemAdapter$MovieItemTVListViewHolder$$ExternalSyntheticLambda0(this));
            }
            movieBrowserTvItemListBinding.container.setOnFocusChangeListener(new MediaScrapingTvItemAdapter$MovieItemTVListViewHolder$$ExternalSyntheticLambda1(mediaScrapingTvItemAdapter, movieBrowserTvItemListBinding, this));
            movieBrowserTvItemListBinding.container.setClipToOutline(true);
        }

        public IEventsHandler<MediaMetadataWithImages> getEventsHandler() {
            return this.eventsHandler;
        }

        public MediaMetadataWithImages getItem(int i) {
            return MediaScrapingTvItemAdapter.access$getItem(this.this$0, i);
        }

        /* access modifiers changed from: private */
        public static final boolean _init_$lambda$0(MovieItemTVListViewHolder movieItemTVListViewHolder, View view) {
            Intrinsics.checkNotNullParameter(movieItemTVListViewHolder, "this$0");
            Intrinsics.checkNotNull(view);
            movieItemTVListViewHolder.onMoreClick(view);
            return true;
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$1(MediaScrapingTvItemAdapter mediaScrapingTvItemAdapter, MovieBrowserTvItemListBinding movieBrowserTvItemListBinding, MovieItemTVListViewHolder movieItemTVListViewHolder, View view, boolean z) {
            Intrinsics.checkNotNullParameter(mediaScrapingTvItemAdapter, "this$0");
            Intrinsics.checkNotNullParameter(movieBrowserTvItemListBinding, "$binding");
            Intrinsics.checkNotNullParameter(movieItemTVListViewHolder, "this$1");
            TvAdapterUtils tvAdapterUtils = TvAdapterUtils.INSTANCE;
            int itemSize = mediaScrapingTvItemAdapter.getItemSize();
            FocusableConstraintLayout focusableConstraintLayout = movieBrowserTvItemListBinding.container;
            Intrinsics.checkNotNullExpressionValue(focusableConstraintLayout, "container");
            tvAdapterUtils.itemFocusChange(z, itemSize, focusableConstraintLayout, true, new MediaScrapingTvItemAdapter$MovieItemTVListViewHolder$2$1(movieItemTVListViewHolder, movieBrowserTvItemListBinding, mediaScrapingTvItemAdapter));
        }

        public void recycle() {
            if (this.this$0.defaultCover != null) {
                ((MovieBrowserTvItemListBinding) getBinding()).setCover(this.this$0.defaultCover);
            }
            ((MovieBrowserTvItemListBinding) getBinding()).setScaleType(ImageView.ScaleType.FIT_CENTER);
            ((MovieBrowserTvItemListBinding) getBinding()).title.setText("");
            ((MovieBrowserTvItemListBinding) getBinding()).subtitle.setText("");
            ((MovieBrowserTvItemListBinding) getBinding()).mediaCover.resetFade();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x000b, code lost:
            r0 = r14.getMetadata();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setItem(org.videolan.moviepedia.database.models.MediaMetadataWithImages r14) {
            /*
                r13 = this;
                androidx.databinding.ViewDataBinding r0 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemListBinding r0 = (org.videolan.television.databinding.MovieBrowserTvItemListBinding) r0
                r0.setItem(r14)
                if (r14 == 0) goto L_0x0016
                org.videolan.moviepedia.database.models.MediaMetadata r0 = r14.getMetadata()
                if (r0 == 0) goto L_0x0016
                java.lang.String r0 = r0.getSummary()
                goto L_0x0017
            L_0x0016:
                r0 = 0
            L_0x0017:
                java.lang.String r1 = ""
                r2 = 0
                r3 = 0
                if (r14 == 0) goto L_0x0084
                org.videolan.medialibrary.interfaces.media.MediaWrapper r14 = r14.getMedia()
                if (r14 == 0) goto L_0x0084
                int r5 = r14.getType()
                if (r5 != 0) goto L_0x0084
                int r0 = r14.getWidth()
                int r5 = r14.getHeight()
                java.lang.String r0 = org.videolan.vlc.util.KextensionsKt.generateResolutionClass(r0, r5)
                if (r0 != 0) goto L_0x0039
                goto L_0x003a
            L_0x0039:
                r1 = r0
            L_0x003a:
                long r5 = r14.getTime()
                int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
                if (r0 != 0) goto L_0x004b
                long r5 = r14.getLength()
                java.lang.String r0 = org.videolan.medialibrary.Tools.millisToString(r5)
                goto L_0x004f
            L_0x004b:
                java.lang.String r0 = org.videolan.medialibrary.Tools.getProgressText(r14)
            L_0x004f:
                androidx.databinding.ViewDataBinding r5 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemListBinding r5 = (org.videolan.television.databinding.MovieBrowserTvItemListBinding) r5
                r5.setBadge(r1)
                long r5 = r14.getSeen()
                long r7 = r14.getLength()
                int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
                if (r9 <= 0) goto L_0x0078
                long r7 = r14.getDisplayTime()
                int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
                if (r9 <= 0) goto L_0x0078
                long r9 = r14.getLength()
                r14 = 1000(0x3e8, float:1.401E-42)
                long r11 = (long) r14
                long r9 = r9 / r11
                int r14 = (int) r9
                long r7 = r7 / r11
                int r8 = (int) r7
                goto L_0x007a
            L_0x0078:
                r14 = 0
                r8 = 0
            L_0x007a:
                androidx.databinding.ViewDataBinding r7 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemListBinding r7 = (org.videolan.television.databinding.MovieBrowserTvItemListBinding) r7
                r7.setMax(r14)
                goto L_0x0086
            L_0x0084:
                r5 = r3
                r8 = 0
            L_0x0086:
                androidx.databinding.ViewDataBinding r14 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemListBinding r14 = (org.videolan.television.databinding.MovieBrowserTvItemListBinding) r14
                r14.setProgress(r8)
                androidx.databinding.ViewDataBinding r14 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemListBinding r14 = (org.videolan.television.databinding.MovieBrowserTvItemListBinding) r14
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r2)
                r14.setIsSquare(r7)
                androidx.databinding.ViewDataBinding r14 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemListBinding r14 = (org.videolan.television.databinding.MovieBrowserTvItemListBinding) r14
                r14.setSeen(r5)
                androidx.databinding.ViewDataBinding r14 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemListBinding r14 = (org.videolan.television.databinding.MovieBrowserTvItemListBinding) r14
                r14.setDescription(r0)
                androidx.databinding.ViewDataBinding r14 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemListBinding r14 = (org.videolan.television.databinding.MovieBrowserTvItemListBinding) r14
                android.widget.ImageView$ScaleType r0 = android.widget.ImageView.ScaleType.CENTER_INSIDE
                r14.setScaleType(r0)
                androidx.databinding.ViewDataBinding r14 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemListBinding r14 = (org.videolan.television.databinding.MovieBrowserTvItemListBinding) r14
                android.widget.ImageView r14 = r14.mlItemSeen
                r0 = 8
                int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
                if (r7 != 0) goto L_0x00ca
                r5 = 8
                goto L_0x00cb
            L_0x00ca:
                r5 = 0
            L_0x00cb:
                r14.setVisibility(r5)
                androidx.databinding.ViewDataBinding r14 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemListBinding r14 = (org.videolan.television.databinding.MovieBrowserTvItemListBinding) r14
                android.widget.ProgressBar r14 = r14.progressBar
                long r5 = (long) r8
                int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
                if (r7 > 0) goto L_0x00de
                r3 = 8
                goto L_0x00df
            L_0x00de:
                r3 = 0
            L_0x00df:
                r14.setVisibility(r3)
                androidx.databinding.ViewDataBinding r14 = r13.getBinding()
                org.videolan.television.databinding.MovieBrowserTvItemListBinding r14 = (org.videolan.television.databinding.MovieBrowserTvItemListBinding) r14
                android.widget.TextView r14 = r14.badgeTV
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                boolean r1 = kotlin.text.StringsKt.isBlank(r1)
                if (r1 == 0) goto L_0x00f4
                r2 = 8
            L_0x00f4:
                r14.setVisibility(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.MediaScrapingTvItemAdapter.MovieItemTVListViewHolder.setItem(org.videolan.moviepedia.database.models.MediaMetadataWithImages):void");
        }
    }
}
