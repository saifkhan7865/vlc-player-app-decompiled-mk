package org.videolan.television.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.interfaces.FocusListener;
import org.videolan.television.databinding.MediaBrowserTvItemBinding;
import org.videolan.television.databinding.MediaBrowserTvItemListBinding;
import org.videolan.television.ui.browser.TvAdapterUtils;
import org.videolan.tools.Settings;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;
import org.videolan.vlc.gui.helpers.SelectorViewHolder;
import org.videolan.vlc.gui.view.FastScroller;
import org.videolan.vlc.interfaces.IEventsHandler;
import org.videolan.vlc.util.BrowserutilsKt;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 /2\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00012\u00020\u00052\u00020\u0006:\u0004./01B-\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\bH\u0016J\b\u0010\u001f\u001a\u00020\rH\u0016J\b\u0010 \u001a\u00020\rH\u0016J\u001e\u0010!\u001a\u00020\u001c2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u001e\u001a\u00020\bH\u0016J,\u0010!\u001a\u00020\u001c2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u001e\u001a\u00020\b2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$H\u0016J\u001e\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\bH\u0016J\u0016\u0010*\u001a\u00020\u001c2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\u0012\u0010+\u001a\u00020\u001c2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010,\u001a\u00020\u001c2\b\u0010-\u001a\u0004\u0018\u00010%H\u0016R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u000e\u0010\u001a\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lorg/videolan/television/ui/MediaTvItemAdapter;", "Landroidx/paging/PagedListAdapter;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lorg/videolan/television/ui/MediaTvItemAdapter$AbstractMediaItemViewHolder;", "Landroidx/databinding/ViewDataBinding;", "Lorg/videolan/vlc/gui/view/FastScroller$SeparatedAdapter;", "Lorg/videolan/television/ui/TvItemAdapter;", "type", "", "eventsHandler", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "itemSize", "inGrid", "", "(ILorg/videolan/vlc/interfaces/IEventsHandler;IZ)V", "defaultCover", "Landroid/graphics/drawable/BitmapDrawable;", "focusListener", "Lorg/videolan/resources/interfaces/FocusListener;", "focusNext", "getFocusNext", "()I", "setFocusNext", "(I)V", "getItemSize", "setItemSize", "seenMediaMarkerVisible", "displaySwitch", "", "getItemViewType", "position", "hasSections", "isEmpty", "onBindViewHolder", "holder", "payloads", "", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onViewRecycled", "setOnFocusChangeListener", "submitList", "pagedList", "AbstractMediaItemViewHolder", "Companion", "MediaItemTVListViewHolder", "MediaItemTVViewHolder", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaTvItemAdapter.kt */
public final class MediaTvItemAdapter extends PagedListAdapter<MediaLibraryItem, AbstractMediaItemViewHolder<ViewDataBinding>> implements FastScroller.SeparatedAdapter, TvItemAdapter {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final MediaTvItemAdapter$Companion$DIFF_CALLBACK$1 DIFF_CALLBACK = new MediaTvItemAdapter$Companion$DIFF_CALLBACK$1();
    private static final String TAG = "VLC/MediaTvItemAdapter";
    /* access modifiers changed from: private */
    public static boolean preventNextAnim;
    /* access modifiers changed from: private */
    public final BitmapDrawable defaultCover;
    private final IEventsHandler<MediaLibraryItem> eventsHandler;
    /* access modifiers changed from: private */
    public FocusListener focusListener;
    private int focusNext;
    private boolean inGrid;
    private int itemSize;
    /* access modifiers changed from: private */
    public boolean seenMediaMarkerVisible;

    public boolean hasSections() {
        return true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MediaTvItemAdapter(int i, IEventsHandler<MediaLibraryItem> iEventsHandler, int i2, boolean z) {
        super(DIFF_CALLBACK);
        Context context;
        Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
        this.eventsHandler = iEventsHandler;
        this.itemSize = i2;
        this.inGrid = z;
        this.focusNext = -1;
        boolean z2 = true;
        this.seenMediaMarkerVisible = true;
        BitmapDrawable bitmapDrawable = null;
        if (iEventsHandler instanceof Context) {
            context = (Context) iEventsHandler;
        } else if (iEventsHandler instanceof Fragment) {
            Intrinsics.checkNotNull(iEventsHandler, "null cannot be cast to non-null type androidx.fragment.app.Fragment");
            context = ((Fragment) iEventsHandler).getContext();
        } else {
            context = null;
        }
        this.defaultCover = context != null ? ImageLoaderKt.getMediaIconDrawable(context, i, true) : bitmapDrawable;
        this.seenMediaMarkerVisible = context != null ? ((SharedPreferences) Settings.INSTANCE.getInstance(context)).getBoolean("media_seen", true) : z2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MediaTvItemAdapter(int i, IEventsHandler iEventsHandler, int i2, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, iEventsHandler, i2, (i3 & 8) != 0 ? true : z);
    }

    public static final /* synthetic */ MediaLibraryItem access$getItem(MediaTvItemAdapter mediaTvItemAdapter, int i) {
        return (MediaLibraryItem) mediaTvItemAdapter.getItem(i);
    }

    public final int getItemSize() {
        return this.itemSize;
    }

    public final void setItemSize(int i) {
        this.itemSize = i;
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

    public AbstractMediaItemViewHolder<ViewDataBinding> onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (this.inGrid) {
            MediaBrowserTvItemBinding inflate = MediaBrowserTvItemBinding.inflate(from, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new MediaItemTVViewHolder(this, inflate, this.eventsHandler);
        }
        MediaBrowserTvItemListBinding inflate2 = MediaBrowserTvItemListBinding.inflate(from, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate2, "inflate(...)");
        return new MediaItemTVListViewHolder(this, inflate2, this.eventsHandler);
    }

    public int getItemViewType(int i) {
        return this.inGrid ^ true ? 1 : 0;
    }

    public void onBindViewHolder(AbstractMediaItemViewHolder<ViewDataBinding> abstractMediaItemViewHolder, int i) {
        Intrinsics.checkNotNullParameter(abstractMediaItemViewHolder, "holder");
        if (i < getItemCount()) {
            abstractMediaItemViewHolder.setItem((MediaLibraryItem) getItem(i));
            abstractMediaItemViewHolder.getBinding().executePendingBindings();
            if (i == getFocusNext()) {
                abstractMediaItemViewHolder.getBinding().getRoot().requestFocus();
                setFocusNext(-1);
            }
        }
    }

    public void onBindViewHolder(AbstractMediaItemViewHolder<ViewDataBinding> abstractMediaItemViewHolder, int i, List<? extends Object> list) {
        Intrinsics.checkNotNullParameter(abstractMediaItemViewHolder, "holder");
        Intrinsics.checkNotNullParameter(list, "payloads");
        if (list.isEmpty() || !(list.get(0) instanceof MediaLibraryItem)) {
            onBindViewHolder(abstractMediaItemViewHolder, i);
            return;
        }
        Object obj = list.get(0);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.videolan.medialibrary.media.MediaLibraryItem");
        boolean hasStateFlags = ((MediaLibraryItem) obj).hasStateFlags(1);
        abstractMediaItemViewHolder.setCoverlay(hasStateFlags);
        abstractMediaItemViewHolder.selectView(hasStateFlags);
    }

    public void onViewRecycled(AbstractMediaItemViewHolder<ViewDataBinding> abstractMediaItemViewHolder) {
        Intrinsics.checkNotNullParameter(abstractMediaItemViewHolder, "holder");
        super.onViewRecycled(abstractMediaItemViewHolder);
        abstractMediaItemViewHolder.recycle();
    }

    public boolean isEmpty() {
        Collection currentList = getCurrentList();
        return currentList == null || currentList.isEmpty();
    }

    public void submitList(Object obj) {
        if (obj == null) {
            submitList((PagedList) null);
        }
        if (obj instanceof PagedList) {
            submitList((PagedList) obj);
        }
    }

    public void setOnFocusChangeListener(FocusListener focusListener2) {
        this.focusListener = focusListener2;
    }

    @Metadata(d1 = {"\u0000\u001f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/videolan/television/ui/MediaTvItemAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "org/videolan/television/ui/MediaTvItemAdapter$Companion$DIFF_CALLBACK$1", "Lorg/videolan/television/ui/MediaTvItemAdapter$Companion$DIFF_CALLBACK$1;", "TAG", "", "preventNextAnim", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaTvItemAdapter.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\f\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&J\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0011J\u0006\u0010\u0014\u001a\u00020\u0011J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000fJ\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000fJ\u000e\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u000fJ\u000e\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000fJ\u000e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000fJ\b\u0010\u001d\u001a\u00020\u0016H&J\u0010\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u0011H&J\u0012\u0010 \u001a\u00020\u00162\b\u0010!\u001a\u0004\u0018\u00010\bH&R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\""}, d2 = {"Lorg/videolan/television/ui/MediaTvItemAdapter$AbstractMediaItemViewHolder;", "T", "Landroidx/databinding/ViewDataBinding;", "Lorg/videolan/vlc/gui/helpers/SelectorViewHolder;", "binding", "(Landroidx/databinding/ViewDataBinding;)V", "eventsHandler", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "getEventsHandler", "()Lorg/videolan/vlc/interfaces/IEventsHandler;", "getItem", "layoutPosition", "", "getView", "Landroid/view/View;", "isNetwork", "", "isOTG", "isPresent", "isSD", "onClick", "", "v", "onImageClick", "onLongClick", "view", "onMainActionClick", "onMoreClick", "recycle", "setCoverlay", "selected", "setItem", "item", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaTvItemAdapter.kt */
    public static abstract class AbstractMediaItemViewHolder<T extends ViewDataBinding> extends SelectorViewHolder<T> {
        public abstract IEventsHandler<MediaLibraryItem> getEventsHandler();

        public abstract MediaLibraryItem getItem(int i);

        public abstract View getView();

        public abstract void recycle();

        public abstract void setCoverlay(boolean z);

        public abstract void setItem(MediaLibraryItem mediaLibraryItem);

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AbstractMediaItemViewHolder(T t) {
            super(t);
            Intrinsics.checkNotNullParameter(t, "binding");
        }

        public final void onClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            MediaLibraryItem item = getItem(getLayoutPosition());
            if (item != null) {
                getEventsHandler().onClick(view, getLayoutPosition(), item);
            }
        }

        public final void onMoreClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            MediaLibraryItem item = getItem(getLayoutPosition());
            if (item != null) {
                getEventsHandler().onCtxClick(view, getLayoutPosition(), item);
            }
        }

        public final boolean onLongClick(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            MediaLibraryItem item = getItem(getLayoutPosition());
            if (item != null) {
                return getEventsHandler().onLongClick(view, getLayoutPosition(), item);
            }
            return false;
        }

        public final void onImageClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            MediaLibraryItem item = getItem(getLayoutPosition());
            if (item != null) {
                getEventsHandler().onImageClick(view, getLayoutPosition(), item);
            }
        }

        public final void onMainActionClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            MediaLibraryItem item = getItem(getLayoutPosition());
            if (item != null) {
                getEventsHandler().onMainActionClick(view, getLayoutPosition(), item);
            }
        }

        public final boolean isPresent() {
            MediaLibraryItem item = getItem(getLayoutPosition());
            MediaWrapper mediaWrapper = item instanceof MediaWrapper ? (MediaWrapper) item : null;
            if (mediaWrapper != null) {
                return mediaWrapper.isPresent();
            }
            return true;
        }

        public final boolean isNetwork() {
            Uri uri;
            MediaLibraryItem item = getItem(getLayoutPosition());
            String str = null;
            MediaWrapper mediaWrapper = item instanceof MediaWrapper ? (MediaWrapper) item : null;
            if (!(mediaWrapper == null || (uri = mediaWrapper.getUri()) == null)) {
                str = uri.getScheme();
            }
            return !BrowserutilsKt.isSchemeFile(str);
        }

        public final boolean isSD() {
            Uri uri;
            MediaLibraryItem item = getItem(getLayoutPosition());
            MediaWrapper mediaWrapper = item instanceof MediaWrapper ? (MediaWrapper) item : null;
            return (mediaWrapper == null || (uri = mediaWrapper.getUri()) == null || !BrowserutilsKt.isSD(uri)) ? false : true;
        }

        public final boolean isOTG() {
            Uri uri;
            MediaLibraryItem item = getItem(getLayoutPosition());
            MediaWrapper mediaWrapper = item instanceof MediaWrapper ? (MediaWrapper) item : null;
            return (mediaWrapper == null || (uri = mediaWrapper.getUri()) == null || !BrowserutilsKt.isOTG(uri)) ? false : true;
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0006H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0016"}, d2 = {"Lorg/videolan/television/ui/MediaTvItemAdapter$MediaItemTVViewHolder;", "Lorg/videolan/television/ui/MediaTvItemAdapter$AbstractMediaItemViewHolder;", "Lorg/videolan/television/databinding/MediaBrowserTvItemBinding;", "binding", "eventsHandler", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "(Lorg/videolan/television/ui/MediaTvItemAdapter;Lorg/videolan/television/databinding/MediaBrowserTvItemBinding;Lorg/videolan/vlc/interfaces/IEventsHandler;)V", "getEventsHandler", "()Lorg/videolan/vlc/interfaces/IEventsHandler;", "getItem", "layoutPosition", "", "getView", "Lorg/videolan/television/ui/FocusableConstraintLayout;", "recycle", "", "setCoverlay", "selected", "", "setItem", "item", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaTvItemAdapter.kt */
    public final class MediaItemTVViewHolder extends AbstractMediaItemViewHolder<MediaBrowserTvItemBinding> {
        private final IEventsHandler<MediaLibraryItem> eventsHandler;
        final /* synthetic */ MediaTvItemAdapter this$0;

        public void setCoverlay(boolean z) {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MediaItemTVViewHolder(MediaTvItemAdapter mediaTvItemAdapter, MediaBrowserTvItemBinding mediaBrowserTvItemBinding, IEventsHandler<MediaLibraryItem> iEventsHandler) {
            super(mediaBrowserTvItemBinding);
            Intrinsics.checkNotNullParameter(mediaBrowserTvItemBinding, "binding");
            Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
            this.this$0 = mediaTvItemAdapter;
            this.eventsHandler = iEventsHandler;
            mediaBrowserTvItemBinding.setHolder(this);
            if (mediaTvItemAdapter.defaultCover != null) {
                mediaBrowserTvItemBinding.setCover(mediaTvItemAdapter.defaultCover);
            }
            if (AndroidUtil.isMarshMallowOrLater) {
                this.itemView.setOnContextClickListener(new MediaTvItemAdapter$MediaItemTVViewHolder$$ExternalSyntheticLambda0(this));
            }
            mediaBrowserTvItemBinding.container.getLayoutParams().width = mediaTvItemAdapter.getItemSize();
            mediaBrowserTvItemBinding.container.setOnFocusChangeListener(new MediaTvItemAdapter$MediaItemTVViewHolder$$ExternalSyntheticLambda1(mediaTvItemAdapter, mediaBrowserTvItemBinding, this));
            mediaBrowserTvItemBinding.setShowSeen(mediaTvItemAdapter.seenMediaMarkerVisible);
            if (AndroidUtil.isLolliPopOrLater) {
                mediaBrowserTvItemBinding.container.setClipToOutline(true);
            }
        }

        public IEventsHandler<MediaLibraryItem> getEventsHandler() {
            return this.eventsHandler;
        }

        public MediaLibraryItem getItem(int i) {
            return MediaTvItemAdapter.access$getItem(this.this$0, i);
        }

        public FocusableConstraintLayout getView() {
            FocusableConstraintLayout focusableConstraintLayout = ((MediaBrowserTvItemBinding) getBinding()).container;
            Intrinsics.checkNotNullExpressionValue(focusableConstraintLayout, "container");
            return focusableConstraintLayout;
        }

        /* access modifiers changed from: private */
        public static final boolean _init_$lambda$0(MediaItemTVViewHolder mediaItemTVViewHolder, View view) {
            Intrinsics.checkNotNullParameter(mediaItemTVViewHolder, "this$0");
            Intrinsics.checkNotNull(view);
            mediaItemTVViewHolder.onMoreClick(view);
            return true;
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$1(MediaTvItemAdapter mediaTvItemAdapter, MediaBrowserTvItemBinding mediaBrowserTvItemBinding, MediaItemTVViewHolder mediaItemTVViewHolder, View view, boolean z) {
            Intrinsics.checkNotNullParameter(mediaTvItemAdapter, "this$0");
            Intrinsics.checkNotNullParameter(mediaBrowserTvItemBinding, "$binding");
            Intrinsics.checkNotNullParameter(mediaItemTVViewHolder, "this$1");
            TvAdapterUtils tvAdapterUtils = TvAdapterUtils.INSTANCE;
            int itemSize = mediaTvItemAdapter.getItemSize();
            FocusableConstraintLayout focusableConstraintLayout = mediaBrowserTvItemBinding.container;
            Intrinsics.checkNotNullExpressionValue(focusableConstraintLayout, "container");
            tvAdapterUtils.itemFocusChange(z, itemSize, focusableConstraintLayout, false, new MediaTvItemAdapter$MediaItemTVViewHolder$2$1(mediaItemTVViewHolder, mediaBrowserTvItemBinding, mediaTvItemAdapter));
        }

        public void recycle() {
            if (this.this$0.defaultCover != null) {
                ((MediaBrowserTvItemBinding) getBinding()).setCover(this.this$0.defaultCover);
            }
            ((MediaBrowserTvItemBinding) getBinding()).title.setText("");
            ((MediaBrowserTvItemBinding) getBinding()).subtitle.setText("");
            ((MediaBrowserTvItemBinding) getBinding()).mediaCover.resetFade();
        }

        /* JADX WARNING: Removed duplicated region for block: B:25:0x0088  */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x0134  */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x0137  */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x0148  */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x014b  */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x015f  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setItem(org.videolan.medialibrary.media.MediaLibraryItem r17) {
            /*
                r16 = this;
                r0 = r17
                androidx.databinding.ViewDataBinding r1 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r1 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r1
                r1.setItem(r0)
                if (r0 == 0) goto L_0x0012
                java.lang.String r1 = r17.getDescription()
                goto L_0x0013
            L_0x0012:
                r1 = 0
            L_0x0013:
                boolean r2 = r0 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
                java.lang.String r3 = ""
                r4 = 1
                r5 = 0
                r6 = 0
                if (r2 == 0) goto L_0x0081
                r2 = r0
                org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r2
                int r8 = r2.getType()
                if (r8 != 0) goto L_0x0081
                int r1 = r2.getWidth()
                int r8 = r2.getHeight()
                java.lang.String r1 = org.videolan.vlc.util.KextensionsKt.generateResolutionClass(r1, r8)
                if (r1 != 0) goto L_0x0035
                goto L_0x0036
            L_0x0035:
                r3 = r1
            L_0x0036:
                long r8 = r2.getTime()
                int r1 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
                if (r1 > 0) goto L_0x0047
                long r8 = r2.getLength()
                java.lang.String r1 = org.videolan.medialibrary.Tools.millisToString(r8)
                goto L_0x004b
            L_0x0047:
                java.lang.String r1 = org.videolan.medialibrary.Tools.getProgressText(r2)
            L_0x004b:
                androidx.databinding.ViewDataBinding r8 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r8 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r8
                r8.setBadge(r3)
                long r8 = r2.getSeen()
                long r10 = r2.getLength()
                int r12 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
                if (r12 <= 0) goto L_0x0074
                long r10 = r2.getDisplayTime()
                int r12 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
                if (r12 <= 0) goto L_0x0074
                long r12 = r2.getLength()
                r2 = 1000(0x3e8, float:1.401E-42)
                long r14 = (long) r2
                long r12 = r12 / r14
                int r2 = (int) r12
                long r10 = r10 / r14
                int r11 = (int) r10
                goto L_0x0076
            L_0x0074:
                r2 = 0
                r11 = 0
            L_0x0076:
                androidx.databinding.ViewDataBinding r10 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r10 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r10
                r10.setMax(r2)
                r2 = 0
                goto L_0x0084
            L_0x0081:
                r8 = r6
                r2 = 1
                r11 = 0
            L_0x0084:
                boolean r10 = r0 instanceof org.videolan.medialibrary.interfaces.media.Genre
                if (r10 == 0) goto L_0x00b0
                androidx.databinding.ViewDataBinding r1 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r1 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r1
                org.videolan.television.ui.FocusableConstraintLayout r1 = r1.container
                android.content.Context r1 = r1.getContext()
                android.content.res.Resources r1 = r1.getResources()
                int r10 = org.videolan.resources.R.plurals.track_quantity
                org.videolan.medialibrary.interfaces.media.Genre r0 = (org.videolan.medialibrary.interfaces.media.Genre) r0
                int r12 = r0.getTracksCount()
                int r0 = r0.getTracksCount()
                java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                java.lang.Object[] r4 = new java.lang.Object[r4]
                r4[r5] = r0
                java.lang.String r1 = r1.getQuantityString(r10, r12, r4)
            L_0x00b0:
                androidx.databinding.ViewDataBinding r0 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r0
                r0.setProgress(r11)
                androidx.databinding.ViewDataBinding r0 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r0
                java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
                r0.setIsSquare(r2)
                androidx.databinding.ViewDataBinding r0 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r0
                r2 = 120(0x78, float:1.68E-43)
                int r2 = org.videolan.tools.KotlinExtensionsKt.getDp(r2)
                r0.setImageWidth(r2)
                androidx.databinding.ViewDataBinding r0 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r0
                r0.setSeen(r8)
                androidx.databinding.ViewDataBinding r0 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r0
                r0.setDescription(r1)
                androidx.databinding.ViewDataBinding r0 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r0
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_INSIDE
                r0.setScaleType(r1)
                androidx.databinding.ViewDataBinding r0 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r0
                boolean r1 = r16.isNetwork()
                r0.setIsNetwork(r1)
                androidx.databinding.ViewDataBinding r0 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r0
                boolean r1 = r16.isSD()
                r0.setIsSD(r1)
                androidx.databinding.ViewDataBinding r0 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r0
                boolean r1 = r16.isOTG()
                r0.setIsOTG(r1)
                androidx.databinding.ViewDataBinding r0 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r0
                boolean r1 = r16.isPresent()
                r0.setIsPresent(r1)
                androidx.databinding.ViewDataBinding r0 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r0
                androidx.appcompat.widget.AppCompatImageView r0 = r0.mlItemSeen
                r1 = 8
                int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
                if (r2 != 0) goto L_0x0137
                r2 = 8
                goto L_0x0138
            L_0x0137:
                r2 = 0
            L_0x0138:
                r0.setVisibility(r2)
                androidx.databinding.ViewDataBinding r0 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r0
                android.widget.ProgressBar r0 = r0.progressBar
                long r8 = (long) r11
                int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
                if (r2 > 0) goto L_0x014b
                r2 = 8
                goto L_0x014c
            L_0x014b:
                r2 = 0
            L_0x014c:
                r0.setVisibility(r2)
                androidx.databinding.ViewDataBinding r0 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r0
                android.widget.TextView r0 = r0.badgeTV
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                boolean r2 = kotlin.text.StringsKt.isBlank(r3)
                if (r2 == 0) goto L_0x0161
                r5 = 8
            L_0x0161:
                r0.setVisibility(r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.MediaTvItemAdapter.MediaItemTVViewHolder.setItem(org.videolan.medialibrary.media.MediaLibraryItem):void");
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0006H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0016"}, d2 = {"Lorg/videolan/television/ui/MediaTvItemAdapter$MediaItemTVListViewHolder;", "Lorg/videolan/television/ui/MediaTvItemAdapter$AbstractMediaItemViewHolder;", "Lorg/videolan/television/databinding/MediaBrowserTvItemListBinding;", "binding", "eventsHandler", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "(Lorg/videolan/television/ui/MediaTvItemAdapter;Lorg/videolan/television/databinding/MediaBrowserTvItemListBinding;Lorg/videolan/vlc/interfaces/IEventsHandler;)V", "getEventsHandler", "()Lorg/videolan/vlc/interfaces/IEventsHandler;", "getItem", "layoutPosition", "", "getView", "Lorg/videolan/television/ui/FocusableConstraintLayout;", "recycle", "", "setCoverlay", "selected", "", "setItem", "item", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaTvItemAdapter.kt */
    public final class MediaItemTVListViewHolder extends AbstractMediaItemViewHolder<MediaBrowserTvItemListBinding> {
        private final IEventsHandler<MediaLibraryItem> eventsHandler;
        final /* synthetic */ MediaTvItemAdapter this$0;

        public void setCoverlay(boolean z) {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MediaItemTVListViewHolder(MediaTvItemAdapter mediaTvItemAdapter, MediaBrowserTvItemListBinding mediaBrowserTvItemListBinding, IEventsHandler<MediaLibraryItem> iEventsHandler) {
            super(mediaBrowserTvItemListBinding);
            Intrinsics.checkNotNullParameter(mediaBrowserTvItemListBinding, "binding");
            Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
            this.this$0 = mediaTvItemAdapter;
            this.eventsHandler = iEventsHandler;
            mediaBrowserTvItemListBinding.setHolder(this);
            if (mediaTvItemAdapter.defaultCover != null) {
                mediaBrowserTvItemListBinding.setCover(mediaTvItemAdapter.defaultCover);
            }
            if (AndroidUtil.isMarshMallowOrLater) {
                this.itemView.setOnContextClickListener(new MediaTvItemAdapter$MediaItemTVListViewHolder$$ExternalSyntheticLambda0(this));
            }
            mediaBrowserTvItemListBinding.container.setOnFocusChangeListener(new MediaTvItemAdapter$MediaItemTVListViewHolder$$ExternalSyntheticLambda1(mediaTvItemAdapter, mediaBrowserTvItemListBinding, this));
            mediaBrowserTvItemListBinding.container.setClipToOutline(true);
            mediaBrowserTvItemListBinding.setShowSeen(mediaTvItemAdapter.seenMediaMarkerVisible);
        }

        public IEventsHandler<MediaLibraryItem> getEventsHandler() {
            return this.eventsHandler;
        }

        public MediaLibraryItem getItem(int i) {
            return MediaTvItemAdapter.access$getItem(this.this$0, i);
        }

        public FocusableConstraintLayout getView() {
            FocusableConstraintLayout focusableConstraintLayout = ((MediaBrowserTvItemListBinding) getBinding()).container;
            Intrinsics.checkNotNullExpressionValue(focusableConstraintLayout, "container");
            return focusableConstraintLayout;
        }

        /* access modifiers changed from: private */
        public static final boolean _init_$lambda$0(MediaItemTVListViewHolder mediaItemTVListViewHolder, View view) {
            Intrinsics.checkNotNullParameter(mediaItemTVListViewHolder, "this$0");
            Intrinsics.checkNotNull(view);
            mediaItemTVListViewHolder.onMoreClick(view);
            return true;
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$1(MediaTvItemAdapter mediaTvItemAdapter, MediaBrowserTvItemListBinding mediaBrowserTvItemListBinding, MediaItemTVListViewHolder mediaItemTVListViewHolder, View view, boolean z) {
            Intrinsics.checkNotNullParameter(mediaTvItemAdapter, "this$0");
            Intrinsics.checkNotNullParameter(mediaBrowserTvItemListBinding, "$binding");
            Intrinsics.checkNotNullParameter(mediaItemTVListViewHolder, "this$1");
            TvAdapterUtils tvAdapterUtils = TvAdapterUtils.INSTANCE;
            int itemSize = mediaTvItemAdapter.getItemSize();
            FocusableConstraintLayout focusableConstraintLayout = mediaBrowserTvItemListBinding.container;
            Intrinsics.checkNotNullExpressionValue(focusableConstraintLayout, "container");
            tvAdapterUtils.itemFocusChange(z, itemSize, focusableConstraintLayout, true, new MediaTvItemAdapter$MediaItemTVListViewHolder$2$1(mediaItemTVListViewHolder, mediaBrowserTvItemListBinding, mediaTvItemAdapter));
        }

        public void recycle() {
            if (this.this$0.defaultCover != null) {
                ((MediaBrowserTvItemListBinding) getBinding()).setCover(this.this$0.defaultCover);
            }
            ((MediaBrowserTvItemListBinding) getBinding()).title.setText("");
            ((MediaBrowserTvItemListBinding) getBinding()).subtitle.setText("");
            ((MediaBrowserTvItemListBinding) getBinding()).mediaCover.resetFade();
        }

        /* JADX WARNING: Removed duplicated region for block: B:25:0x0099  */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x0138  */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x013b  */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x014c  */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x014f  */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x0163  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setItem(org.videolan.medialibrary.media.MediaLibraryItem r16) {
            /*
                r15 = this;
                r0 = r16
                androidx.databinding.ViewDataBinding r1 = r15.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r1 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r1
                r1.setItem(r0)
                androidx.databinding.ViewDataBinding r1 = r15.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r1 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r1
                r2 = r15
                org.videolan.television.ui.MediaTvItemAdapter r3 = r2.this$0
                int r3 = r3.getItemSize()
                r1.setImageWidth(r3)
                if (r0 == 0) goto L_0x0022
                java.lang.String r1 = r16.getDescription()
                goto L_0x0023
            L_0x0022:
                r1 = 0
            L_0x0023:
                boolean r3 = r0 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
                java.lang.String r4 = ""
                r5 = 1
                r6 = 0
                r7 = 0
                if (r3 == 0) goto L_0x0091
                r3 = r0
                org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r3
                int r9 = r3.getType()
                if (r9 != 0) goto L_0x0091
                int r1 = r3.getWidth()
                int r9 = r3.getHeight()
                java.lang.String r1 = org.videolan.vlc.util.KextensionsKt.generateResolutionClass(r1, r9)
                if (r1 != 0) goto L_0x0045
                goto L_0x0046
            L_0x0045:
                r4 = r1
            L_0x0046:
                long r9 = r3.getTime()
                int r1 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
                if (r1 != 0) goto L_0x0057
                long r9 = r3.getLength()
                java.lang.String r1 = org.videolan.medialibrary.Tools.millisToString(r9)
                goto L_0x005b
            L_0x0057:
                java.lang.String r1 = org.videolan.medialibrary.Tools.getProgressText(r3)
            L_0x005b:
                androidx.databinding.ViewDataBinding r9 = r15.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r9 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r9
                r9.setBadge(r4)
                long r9 = r3.getSeen()
                long r11 = r3.getLength()
                int r13 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
                if (r13 <= 0) goto L_0x0084
                long r11 = r3.getDisplayTime()
                int r13 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
                if (r13 <= 0) goto L_0x0084
                long r13 = r3.getLength()
                r3 = 1000(0x3e8, float:1.401E-42)
                long r7 = (long) r3
                long r13 = r13 / r7
                int r3 = (int) r13
                long r11 = r11 / r7
                int r7 = (int) r11
                goto L_0x0086
            L_0x0084:
                r3 = 0
                r7 = 0
            L_0x0086:
                androidx.databinding.ViewDataBinding r8 = r15.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r8 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r8
                r8.setMax(r3)
                r3 = 0
                goto L_0x0095
            L_0x0091:
                r3 = 1
                r7 = 0
                r9 = 0
            L_0x0095:
                boolean r8 = r0 instanceof org.videolan.medialibrary.interfaces.media.Genre
                if (r8 == 0) goto L_0x00c1
                androidx.databinding.ViewDataBinding r1 = r15.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r1 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r1
                org.videolan.television.ui.FocusableConstraintLayout r1 = r1.container
                android.content.Context r1 = r1.getContext()
                android.content.res.Resources r1 = r1.getResources()
                int r8 = org.videolan.resources.R.plurals.track_quantity
                org.videolan.medialibrary.interfaces.media.Genre r0 = (org.videolan.medialibrary.interfaces.media.Genre) r0
                int r11 = r0.getTracksCount()
                int r0 = r0.getTracksCount()
                java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                java.lang.Object[] r5 = new java.lang.Object[r5]
                r5[r6] = r0
                java.lang.String r1 = r1.getQuantityString(r8, r11, r5)
            L_0x00c1:
                androidx.databinding.ViewDataBinding r0 = r15.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r0
                r0.setProgress(r7)
                androidx.databinding.ViewDataBinding r0 = r15.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r0
                java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
                r0.setIsSquare(r3)
                androidx.databinding.ViewDataBinding r0 = r15.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r0
                r0.setSeen(r9)
                androidx.databinding.ViewDataBinding r0 = r15.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r0
                r0.setDescription(r1)
                androidx.databinding.ViewDataBinding r0 = r15.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r0
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_INSIDE
                r0.setScaleType(r1)
                androidx.databinding.ViewDataBinding r0 = r15.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r0
                boolean r1 = r15.isNetwork()
                r0.setIsNetwork(r1)
                androidx.databinding.ViewDataBinding r0 = r15.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r0
                boolean r1 = r15.isSD()
                r0.setIsSD(r1)
                androidx.databinding.ViewDataBinding r0 = r15.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r0
                boolean r1 = r15.isOTG()
                r0.setIsOTG(r1)
                androidx.databinding.ViewDataBinding r0 = r15.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r0
                boolean r1 = r15.isPresent()
                r0.setIsPresent(r1)
                androidx.databinding.ViewDataBinding r0 = r15.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r0
                android.widget.ImageView r0 = r0.mlItemSeen
                r1 = 8
                r11 = 0
                int r3 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
                if (r3 != 0) goto L_0x013b
                r3 = 8
                goto L_0x013c
            L_0x013b:
                r3 = 0
            L_0x013c:
                r0.setVisibility(r3)
                androidx.databinding.ViewDataBinding r0 = r15.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r0
                android.widget.ProgressBar r0 = r0.progressBar
                long r7 = (long) r7
                int r3 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
                if (r3 > 0) goto L_0x014f
                r3 = 8
                goto L_0x0150
            L_0x014f:
                r3 = 0
            L_0x0150:
                r0.setVisibility(r3)
                androidx.databinding.ViewDataBinding r0 = r15.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r0 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r0
                android.widget.TextView r0 = r0.badgeTV
                java.lang.CharSequence r4 = (java.lang.CharSequence) r4
                boolean r3 = kotlin.text.StringsKt.isBlank(r4)
                if (r3 == 0) goto L_0x0165
                r6 = 8
            L_0x0165:
                r0.setVisibility(r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.MediaTvItemAdapter.MediaItemTVListViewHolder.setItem(org.videolan.medialibrary.media.MediaLibraryItem):void");
        }
    }
}
