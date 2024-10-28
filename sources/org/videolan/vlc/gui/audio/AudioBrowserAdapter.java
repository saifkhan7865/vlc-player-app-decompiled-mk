package org.videolan.vlc.gui.audio;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.view.MotionEventCompat;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.interfaces.FocusListener;
import org.videolan.tools.MultiSelectAdapter;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.tools.Settings;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.AudioBrowserCardItemBinding;
import org.videolan.vlc.databinding.AudioBrowserItemBinding;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;
import org.videolan.vlc.gui.helpers.MarqueeViewHolder;
import org.videolan.vlc.gui.helpers.SelectorViewHolder;
import org.videolan.vlc.gui.helpers.UiToolsKt;
import org.videolan.vlc.gui.view.FadableImageView;
import org.videolan.vlc.gui.view.FastScroller;
import org.videolan.vlc.gui.view.MiniVisualizer;
import org.videolan.vlc.interfaces.IEventsHandler;
import org.videolan.vlc.interfaces.IListEventsHandler;
import org.videolan.vlc.interfaces.SwipeDragHelperAdapter;
import org.videolan.vlc.util.BrowserutilsKt;
import org.videolan.vlc.util.LifecycleAwareScheduler;
import org.videolan.vlc.viewmodels.PlaylistModel;

@Metadata(d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0016\u0018\u0000 p2\u0018\u0012\u0004\u0012\u00020\u0002\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00040\u0003R\u00020\u00000\u00012\u00020\u00052\b\u0012\u0004\u0012\u00020\u00020\u00062\u00020\u0007:\u0004opqrB=\b\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\t¢\u0006\u0002\u0010\u0011J\u0006\u0010F\u001a\u00020GJ\b\u0010H\u001a\u00020\u000fH\u0002J\u0012\u0010I\u001a\u0004\u0018\u00010\u00022\u0006\u0010J\u001a\u00020\tH\u0016J\u0010\u0010K\u001a\u00020L2\u0006\u0010J\u001a\u00020\tH\u0016J\u0010\u0010M\u001a\u00020\t2\u0006\u0010J\u001a\u00020\tH\u0016J\b\u0010N\u001a\u00020\u000fH\u0016J\b\u0010O\u001a\u00020\u000fH\u0004J\u0010\u0010P\u001a\u00020\u000f2\u0006\u0010J\u001a\u00020\tH\u0002J\u0010\u0010Q\u001a\u00020G2\u0006\u0010R\u001a\u00020SH\u0016J\"\u0010T\u001a\u00020G2\u0010\u0010U\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003R\u00020\u00002\u0006\u0010J\u001a\u00020\tH\u0016J0\u0010T\u001a\u00020G2\u0010\u0010U\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003R\u00020\u00002\u0006\u0010J\u001a\u00020\t2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020X0WH\u0016J\"\u0010Y\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003R\u00020\u00002\u0006\u0010Z\u001a\u00020[2\u0006\u0010\\\u001a\u00020\tH\u0016J(\u0010]\u001a\u00020G2\u000e\u0010^\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010_2\u000e\u0010`\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010_H\u0016J\u0010\u0010a\u001a\u00020G2\u0006\u0010R\u001a\u00020SH\u0016J\u0010\u0010b\u001a\u00020G2\u0006\u0010J\u001a\u00020\tH\u0016J\u0018\u0010c\u001a\u00020G2\u0006\u0010d\u001a\u00020\t2\u0006\u0010e\u001a\u00020\tH\u0016J\u0018\u0010f\u001a\u00020G2\u0006\u0010g\u001a\u00020\t2\u0006\u0010h\u001a\u00020\tH\u0016J\u001a\u0010i\u001a\u00020G2\u0010\u0010j\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003R\u00020\u0000H\u0016J\u000e\u0010k\u001a\u00020G2\u0006\u0010l\u001a\u00020\u000fJ\u000e\u0010m\u001a\u00020G2\u0006\u0010:\u001a\u00020;J\u0010\u0010n\u001a\u00020G2\b\u0010+\u001a\u0004\u0018\u00010,R\u001a\u0010\u0012\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0010\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR(\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010$\u001a\u0004\u0018\u00010%X\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0010\u0010(\u001a\u0004\u0018\u00010%X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010.\u001a\u00020/X.¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0011\u00104\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b4\u0010\u0014R\u0016\u0010\f\u001a\u0004\u0018\u00010\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u001a\u00107\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u0018\"\u0004\b9\u0010\u001aR\u0010\u0010:\u001a\u0004\u0018\u00010;X\u000e¢\u0006\u0002\n\u0000R\u0017\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00020=¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u0014\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u0014R\u0010\u0010A\u001a\u0004\u0018\u00010BX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010C\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u0014\"\u0004\bE\u0010\u0016¨\u0006s"}, d2 = {"Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;", "Landroidx/paging/PagedListAdapter;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter$AbstractMediaItemViewHolder;", "Landroidx/databinding/ViewDataBinding;", "Lorg/videolan/vlc/gui/view/FastScroller$SeparatedAdapter;", "Lorg/videolan/tools/MultiSelectAdapter;", "Lorg/videolan/vlc/interfaces/SwipeDragHelperAdapter;", "type", "", "eventsHandler", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "listEventsHandler", "Lorg/videolan/vlc/interfaces/IListEventsHandler;", "reorderable", "", "cardSize", "(ILorg/videolan/vlc/interfaces/IEventsHandler;Lorg/videolan/vlc/interfaces/IListEventsHandler;ZI)V", "areSectionsEnabled", "getAreSectionsEnabled", "()Z", "setAreSectionsEnabled", "(Z)V", "getCardSize$vlc_android_release", "()I", "setCardSize$vlc_android_release", "(I)V", "media", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "currentMedia", "getCurrentMedia", "()Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "setCurrentMedia", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)V", "currentPlayingVisu", "Lorg/videolan/vlc/gui/view/MiniVisualizer;", "defaultCover", "Landroid/graphics/drawable/BitmapDrawable;", "getDefaultCover", "()Landroid/graphics/drawable/BitmapDrawable;", "defaultCoverCard", "getEventsHandler", "()Lorg/videolan/vlc/interfaces/IEventsHandler;", "focusListener", "Lorg/videolan/resources/interfaces/FocusListener;", "focusNext", "inflater", "Landroid/view/LayoutInflater;", "getInflater", "()Landroid/view/LayoutInflater;", "setInflater", "(Landroid/view/LayoutInflater;)V", "isEmpty", "getListEventsHandler", "()Lorg/videolan/vlc/interfaces/IListEventsHandler;", "listImageWidth", "getListImageWidth", "setListImageWidth", "model", "Lorg/videolan/vlc/viewmodels/PlaylistModel;", "multiSelectHelper", "Lorg/videolan/tools/MultiSelectHelper;", "getMultiSelectHelper", "()Lorg/videolan/tools/MultiSelectHelper;", "getReorderable", "scheduler", "Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "stopReorder", "getStopReorder", "setStopReorder", "clear", "", "displayInCard", "getItem", "position", "getItemId", "", "getItemViewType", "hasSections", "inflaterInitialized", "isPositionValid", "onAttachedToRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onBindViewHolder", "holder", "payloads", "", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onCurrentListChanged", "previousList", "Landroidx/paging/PagedList;", "currentList", "onDetachedFromRecyclerView", "onItemDismiss", "onItemMove", "fromPosition", "toPosition", "onItemMoved", "dragFrom", "dragTo", "onViewRecycled", "h", "setCurrentlyPlaying", "playing", "setModel", "setOnFocusChangeListener", "AbstractMediaItemViewHolder", "Companion", "MediaItemCardViewHolder", "MediaItemViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioBrowserAdapter.kt */
public class AudioBrowserAdapter extends PagedListAdapter<MediaLibraryItem, AbstractMediaItemViewHolder<ViewDataBinding>> implements FastScroller.SeparatedAdapter, MultiSelectAdapter<MediaLibraryItem>, SwipeDragHelperAdapter {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final AudioBrowserAdapter$Companion$DIFF_CALLBACK$1 DIFF_CALLBACK = new AudioBrowserAdapter$Companion$DIFF_CALLBACK$1();
    private static final String TAG = "VLC/AudioBrowserAdapter";
    private static final int UPDATE_PAYLOAD = 1;
    /* access modifiers changed from: private */
    public static boolean preventNextAnim;
    private boolean areSectionsEnabled;
    private int cardSize;
    private MediaWrapper currentMedia;
    private MiniVisualizer currentPlayingVisu;
    private final BitmapDrawable defaultCover;
    /* access modifiers changed from: private */
    public final BitmapDrawable defaultCoverCard;
    private final IEventsHandler<MediaLibraryItem> eventsHandler;
    private FocusListener focusListener;
    private int focusNext;
    public LayoutInflater inflater;
    private final IListEventsHandler listEventsHandler;
    private int listImageWidth;
    private PlaylistModel model;
    private final MultiSelectHelper<MediaLibraryItem> multiSelectHelper;
    private final boolean reorderable;
    private LifecycleAwareScheduler scheduler;
    private boolean stopReorder;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AudioBrowserAdapter(int i, IEventsHandler<MediaLibraryItem> iEventsHandler) {
        this(i, iEventsHandler, (IListEventsHandler) null, false, 0, 28, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AudioBrowserAdapter(int i, IEventsHandler<MediaLibraryItem> iEventsHandler, IListEventsHandler iListEventsHandler) {
        this(i, iEventsHandler, iListEventsHandler, false, 0, 24, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AudioBrowserAdapter(int i, IEventsHandler<MediaLibraryItem> iEventsHandler, IListEventsHandler iListEventsHandler, boolean z) {
        this(i, iEventsHandler, iListEventsHandler, z, 0, 16, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
    }

    public final void clear() {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AudioBrowserAdapter(int i, IEventsHandler iEventsHandler, IListEventsHandler iListEventsHandler, boolean z, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, iEventsHandler, (i3 & 4) != 0 ? null : iListEventsHandler, (i3 & 8) != 0 ? false : z, (i3 & 16) != 0 ? -1 : i2);
    }

    /* access modifiers changed from: protected */
    public final IEventsHandler<MediaLibraryItem> getEventsHandler() {
        return this.eventsHandler;
    }

    /* access modifiers changed from: protected */
    public final IListEventsHandler getListEventsHandler() {
        return this.listEventsHandler;
    }

    /* access modifiers changed from: protected */
    public final boolean getReorderable() {
        return this.reorderable;
    }

    public final int getCardSize$vlc_android_release() {
        return this.cardSize;
    }

    public final void setCardSize$vlc_android_release(int i) {
        this.cardSize = i;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AudioBrowserAdapter(int i, IEventsHandler<MediaLibraryItem> iEventsHandler, IListEventsHandler iListEventsHandler, boolean z, int i2) {
        super(DIFF_CALLBACK);
        Context context;
        Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
        this.eventsHandler = iEventsHandler;
        this.listEventsHandler = iListEventsHandler;
        this.reorderable = z;
        this.cardSize = i2;
        this.multiSelectHelper = new MultiSelectHelper<>(this, 0);
        this.focusNext = -1;
        this.areSectionsEnabled = true;
        if (iEventsHandler instanceof Context) {
            context = (Context) iEventsHandler;
        } else if (iEventsHandler instanceof Fragment) {
            context = ((Fragment) iEventsHandler).requireContext();
        } else {
            context = AppContextProvider.INSTANCE.getAppContext();
        }
        Intrinsics.checkNotNull(context);
        this.listImageWidth = (int) context.getResources().getDimension(R.dimen.audio_browser_item_size);
        this.defaultCover = ImageLoaderKt.getAudioIconDrawable(context, i, false);
        this.defaultCoverCard = ImageLoaderKt.getAudioIconDrawable(context, i, true);
    }

    /* access modifiers changed from: protected */
    public final int getListImageWidth() {
        return this.listImageWidth;
    }

    /* access modifiers changed from: protected */
    public final void setListImageWidth(int i) {
        this.listImageWidth = i;
    }

    public final MultiSelectHelper<MediaLibraryItem> getMultiSelectHelper() {
        return this.multiSelectHelper;
    }

    /* access modifiers changed from: protected */
    public final BitmapDrawable getDefaultCover() {
        return this.defaultCover;
    }

    public final LayoutInflater getInflater() {
        LayoutInflater layoutInflater = this.inflater;
        if (layoutInflater != null) {
            return layoutInflater;
        }
        Intrinsics.throwUninitializedPropertyAccessException("inflater");
        return null;
    }

    public final void setInflater(LayoutInflater layoutInflater) {
        Intrinsics.checkNotNullParameter(layoutInflater, "<set-?>");
        this.inflater = layoutInflater;
    }

    public final boolean getStopReorder() {
        return this.stopReorder;
    }

    public final void setStopReorder(boolean z) {
        this.stopReorder = z;
    }

    public final boolean getAreSectionsEnabled() {
        return this.areSectionsEnabled;
    }

    public final void setAreSectionsEnabled(boolean z) {
        this.areSectionsEnabled = z;
    }

    public final MediaWrapper getCurrentMedia() {
        return this.currentMedia;
    }

    public final void setCurrentMedia(MediaWrapper mediaWrapper) {
        PagedList currentList;
        PagedList currentList2;
        if (!Intrinsics.areEqual((Object) mediaWrapper, (Object) this.currentMedia)) {
            MediaWrapper mediaWrapper2 = this.currentMedia;
            this.currentMedia = mediaWrapper;
            if (!(mediaWrapper2 == null || (currentList2 = getCurrentList()) == null)) {
                notifyItemChanged(currentList2.indexOf(mediaWrapper2));
            }
            if (mediaWrapper != null && (currentList = getCurrentList()) != null) {
                notifyItemChanged(currentList.indexOf(mediaWrapper));
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean inflaterInitialized() {
        return this.inflater != null;
    }

    public final boolean isEmpty() {
        Collection currentList = getCurrentList();
        return currentList == null || currentList.isEmpty();
    }

    public AbstractMediaItemViewHolder<ViewDataBinding> onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        if (this.inflater == null) {
            LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
            Intrinsics.checkNotNullExpressionValue(from, "from(...)");
            setInflater(from);
        }
        if (displayInCard()) {
            AudioBrowserCardItemBinding inflate = AudioBrowserCardItemBinding.inflate(getInflater(), viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new MediaItemCardViewHolder(this, inflate);
        }
        AudioBrowserItemBinding inflate2 = AudioBrowserItemBinding.inflate(getInflater(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate2, "inflate(...)");
        return new MediaItemViewHolder(this, inflate2);
    }

    private final boolean displayInCard() {
        return this.cardSize != -1;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        if (Settings.INSTANCE.getListTitleEllipsize() == 4) {
            this.scheduler = UiToolsKt.enableMarqueeEffect(recyclerView);
        }
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        LifecycleAwareScheduler lifecycleAwareScheduler = this.scheduler;
        if (lifecycleAwareScheduler != null) {
            lifecycleAwareScheduler.cancelAction("");
        }
        setCurrentMedia((MediaWrapper) null);
        this.currentPlayingVisu = null;
        super.onDetachedFromRecyclerView(recyclerView);
    }

    public final void setCurrentlyPlaying(boolean z) {
        if (z) {
            MiniVisualizer miniVisualizer = this.currentPlayingVisu;
            if (miniVisualizer != null) {
                miniVisualizer.start();
                return;
            }
            return;
        }
        MiniVisualizer miniVisualizer2 = this.currentPlayingVisu;
        if (miniVisualizer2 != null) {
            miniVisualizer2.stop();
        }
    }

    public final void setModel(PlaylistModel playlistModel) {
        Intrinsics.checkNotNullParameter(playlistModel, "model");
        this.model = playlistModel;
    }

    public void onBindViewHolder(AbstractMediaItemViewHolder<ViewDataBinding> abstractMediaItemViewHolder, int i) {
        Intrinsics.checkNotNullParameter(abstractMediaItemViewHolder, "holder");
        if (i < getItemCount()) {
            MediaLibraryItem item = getItem(i);
            abstractMediaItemViewHolder.setItem(item);
            if (item instanceof Artist) {
                Artist artist = (Artist) item;
                artist.setDescription(abstractMediaItemViewHolder.getBinding().getRoot().getContext().getResources().getQuantityString(R.plurals.albums_quantity, artist.getAlbumsCount(), new Object[]{Integer.valueOf(artist.getAlbumsCount())}));
            }
            if (item instanceof Genre) {
                Genre genre = (Genre) item;
                genre.setDescription(abstractMediaItemViewHolder.getBinding().getRoot().getContext().getResources().getQuantityString(R.plurals.track_quantity, genre.getTracksCount(), new Object[]{Integer.valueOf(genre.getTracksCount())}));
            }
            abstractMediaItemViewHolder.selectView(this.multiSelectHelper.isSelected(i));
            if (item instanceof MediaWrapper) {
                MediaWrapper mediaWrapper = (MediaWrapper) item;
                abstractMediaItemViewHolder.getBinding().setVariable(BR.isNetwork, Boolean.valueOf(BrowserutilsKt.isSchemeSMB(mediaWrapper.getUri().getScheme())));
                ViewDataBinding binding = abstractMediaItemViewHolder.getBinding();
                int i2 = BR.isOTG;
                Uri uri = mediaWrapper.getUri();
                Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
                binding.setVariable(i2, Boolean.valueOf(BrowserutilsKt.isOTG(uri)));
                ViewDataBinding binding2 = abstractMediaItemViewHolder.getBinding();
                int i3 = BR.isSD;
                Uri uri2 = mediaWrapper.getUri();
                Intrinsics.checkNotNullExpressionValue(uri2, "getUri(...)");
                binding2.setVariable(i3, Boolean.valueOf(BrowserutilsKt.isSD(uri2)));
                abstractMediaItemViewHolder.getBinding().setVariable(BR.isPresent, Boolean.valueOf(mediaWrapper.isPresent()));
            } else {
                abstractMediaItemViewHolder.getBinding().setVariable(BR.isPresent, true);
            }
            MiniVisualizer miniVisu = abstractMediaItemViewHolder.getMiniVisu();
            if (Intrinsics.areEqual((Object) this.currentMedia, (Object) item)) {
                PlaylistModel playlistModel = this.model;
                if (playlistModel == null || playlistModel.getPlaying()) {
                    miniVisu.start();
                } else {
                    miniVisu.stop();
                }
                miniVisu.setVisibility(0);
                abstractMediaItemViewHolder.changePlayingVisibility(true);
                this.currentPlayingVisu = miniVisu;
            } else {
                miniVisu.stop();
                abstractMediaItemViewHolder.changePlayingVisibility(false);
                miniVisu.setVisibility(4);
            }
            if (item != null) {
                abstractMediaItemViewHolder.getBinding().setVariable(BR.isFavorite, Boolean.valueOf(item.isFavorite()));
            }
            abstractMediaItemViewHolder.getBinding().setVariable(BR.inSelection, Boolean.valueOf(this.multiSelectHelper.getInActionMode()));
            abstractMediaItemViewHolder.getBinding().invalidateAll();
            abstractMediaItemViewHolder.getBinding().executePendingBindings();
            if (i == this.focusNext) {
                abstractMediaItemViewHolder.getBinding().getRoot().requestFocus();
                this.focusNext = -1;
            }
        }
    }

    public void onBindViewHolder(AbstractMediaItemViewHolder<ViewDataBinding> abstractMediaItemViewHolder, int i, List<? extends Object> list) {
        MediaLibraryItem item;
        Intrinsics.checkNotNullParameter(abstractMediaItemViewHolder, "holder");
        Intrinsics.checkNotNullParameter(list, "payloads");
        if (list.isEmpty()) {
            onBindViewHolder(abstractMediaItemViewHolder, i);
            return;
        }
        Object obj = list.get(0);
        if (obj instanceof MediaLibraryItem) {
            abstractMediaItemViewHolder.selectView(((MediaLibraryItem) obj).hasStateFlags(1));
        } else if (!(obj instanceof Integer)) {
        } else {
            if (Intrinsics.areEqual((Object) obj, (Object) 0)) {
                abstractMediaItemViewHolder.selectView(this.multiSelectHelper.isSelected(i));
            } else if (Intrinsics.areEqual((Object) obj, (Object) 7)) {
                abstractMediaItemViewHolder.getBinding().invalidateAll();
            } else if (Intrinsics.areEqual((Object) obj, (Object) 8) && (item = getItem(i)) != null) {
                abstractMediaItemViewHolder.getBinding().setVariable(BR.isFavorite, Boolean.valueOf(item.isFavorite()));
            }
        }
    }

    public void onViewRecycled(AbstractMediaItemViewHolder<ViewDataBinding> abstractMediaItemViewHolder) {
        Intrinsics.checkNotNullParameter(abstractMediaItemViewHolder, "h");
        LifecycleAwareScheduler lifecycleAwareScheduler = this.scheduler;
        if (lifecycleAwareScheduler != null) {
            lifecycleAwareScheduler.cancelAction(UiToolsKt.MARQUEE_ACTION);
        }
        abstractMediaItemViewHolder.recycle();
        super.onViewRecycled(abstractMediaItemViewHolder);
    }

    private final boolean isPositionValid(int i) {
        return i >= 0 && i < getItemCount();
    }

    public long getItemId(int i) {
        MediaLibraryItem item;
        if (isPositionValid(i) && (item = getItem(i)) != null) {
            return item.getId();
        }
        return -1;
    }

    public MediaLibraryItem getItem(int i) {
        if (i < 0 || i >= getItemCount()) {
            return null;
        }
        return (MediaLibraryItem) super.getItem(i);
    }

    public int getItemViewType(int i) {
        MediaLibraryItem item = getItem(i);
        if (item != null) {
            return item.getItemType();
        }
        return 32;
    }

    public void onCurrentListChanged(PagedList<MediaLibraryItem> pagedList, PagedList<MediaLibraryItem> pagedList2) {
        this.eventsHandler.onUpdateFinished(this);
    }

    public boolean hasSections() {
        return this.areSectionsEnabled;
    }

    public void onItemMove(int i, int i2) {
        notifyItemMoved(i, i2);
    }

    public void onItemMoved(int i, int i2) {
        IListEventsHandler iListEventsHandler = this.listEventsHandler;
        Intrinsics.checkNotNull(iListEventsHandler);
        iListEventsHandler.onMove(i, i2);
        preventNextAnim = true;
    }

    public void onItemDismiss(int i) {
        MediaLibraryItem item = getItem(i);
        IListEventsHandler iListEventsHandler = this.listEventsHandler;
        Intrinsics.checkNotNull(iListEventsHandler);
        Intrinsics.checkNotNull(item);
        iListEventsHandler.onRemove(i, item);
    }

    public final void setOnFocusChangeListener(FocusListener focusListener2) {
        this.focusListener = focusListener2;
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001R\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0011H\u0016J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0013H\u0016J\u0012\u0010\u0019\u001a\u00020\u00112\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u0004\u0018\u00010\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter$MediaItemViewHolder;", "Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter$AbstractMediaItemViewHolder;", "Lorg/videolan/vlc/databinding/AudioBrowserItemBinding;", "Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;", "binding", "(Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;Lorg/videolan/vlc/databinding/AudioBrowserItemBinding;)V", "onTouchListener", "Landroid/view/View$OnTouchListener;", "getOnTouchListener", "()Landroid/view/View$OnTouchListener;", "setOnTouchListener", "(Landroid/view/View$OnTouchListener;)V", "titleView", "Landroid/widget/TextView;", "getTitleView", "()Landroid/widget/TextView;", "changePlayingVisibility", "", "isCurrent", "", "getMiniVisu", "Lorg/videolan/vlc/gui/view/MiniVisualizer;", "recycle", "selectView", "selected", "setItem", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AudioBrowserAdapter.kt */
    public final class MediaItemViewHolder extends AbstractMediaItemViewHolder<AudioBrowserItemBinding> {
        private View.OnTouchListener onTouchListener;
        final /* synthetic */ AudioBrowserAdapter this$0;
        private final TextView titleView;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MediaItemViewHolder(final AudioBrowserAdapter audioBrowserAdapter, AudioBrowserItemBinding audioBrowserItemBinding) {
            super(audioBrowserAdapter, audioBrowserItemBinding);
            Intrinsics.checkNotNullParameter(audioBrowserItemBinding, "binding");
            this.this$0 = audioBrowserAdapter;
            this.titleView = audioBrowserItemBinding.title;
            audioBrowserItemBinding.setHolder(this);
            BitmapDrawable defaultCover = audioBrowserAdapter.getDefaultCover();
            if (defaultCover != null) {
                audioBrowserItemBinding.setCover(defaultCover);
            }
            if (AndroidUtil.isMarshMallowOrLater) {
                this.itemView.setOnContextClickListener(new AudioBrowserAdapter$MediaItemViewHolder$$ExternalSyntheticLambda0(this));
            }
            this.onTouchListener = new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    Intrinsics.checkNotNullParameter(view, "v");
                    Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
                    if (audioBrowserAdapter.getListEventsHandler() == null || audioBrowserAdapter.getMultiSelectHelper().getSelectionCount() != 0 || MotionEventCompat.getActionMasked(motionEvent) != 0) {
                        return false;
                    }
                    audioBrowserAdapter.getListEventsHandler().onStartDrag(this);
                    return true;
                }
            };
            audioBrowserItemBinding.setImageWidth(audioBrowserAdapter.getListImageWidth());
        }

        public final View.OnTouchListener getOnTouchListener() {
            return this.onTouchListener;
        }

        public final void setOnTouchListener(View.OnTouchListener onTouchListener2) {
            Intrinsics.checkNotNullParameter(onTouchListener2, "<set-?>");
            this.onTouchListener = onTouchListener2;
        }

        public TextView getTitleView() {
            return this.titleView;
        }

        /* access modifiers changed from: private */
        public static final boolean _init_$lambda$1(MediaItemViewHolder mediaItemViewHolder, View view) {
            Intrinsics.checkNotNullParameter(mediaItemViewHolder, "this$0");
            Intrinsics.checkNotNull(view);
            mediaItemViewHolder.onMoreClick(view);
            return true;
        }

        public void selectView(boolean z) {
            ((AudioBrowserItemBinding) getBinding()).setVariable(BR.selected, Boolean.valueOf(z));
            ((AudioBrowserItemBinding) getBinding()).itemMore.setVisibility(this.this$0.getMultiSelectHelper().getInActionMode() ? 4 : 0);
        }

        public void setItem(MediaLibraryItem mediaLibraryItem) {
            ((AudioBrowserItemBinding) getBinding()).setItem(mediaLibraryItem);
        }

        public void recycle() {
            ((AudioBrowserItemBinding) getBinding()).setCover((this.this$0.getCardSize$vlc_android_release() != -1 || this.this$0.getDefaultCover() == null) ? null : this.this$0.getDefaultCover());
            ((AudioBrowserItemBinding) getBinding()).mediaCover.resetFade();
            ((AudioBrowserItemBinding) getBinding()).title.setSelected(false);
        }

        public MiniVisualizer getMiniVisu() {
            MiniVisualizer miniVisualizer = ((AudioBrowserItemBinding) getBinding()).playing;
            Intrinsics.checkNotNullExpressionValue(miniVisualizer, "playing");
            return miniVisualizer;
        }

        public void changePlayingVisibility(boolean z) {
            ((AudioBrowserItemBinding) getBinding()).mediaCover.setVisibility(z ? 4 : 0);
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001R\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000bH\u0016J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\rH\u0016J\u0012\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0016"}, d2 = {"Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter$MediaItemCardViewHolder;", "Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter$AbstractMediaItemViewHolder;", "Lorg/videolan/vlc/databinding/AudioBrowserCardItemBinding;", "Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;", "binding", "(Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;Lorg/videolan/vlc/databinding/AudioBrowserCardItemBinding;)V", "titleView", "Landroid/widget/TextView;", "getTitleView", "()Landroid/widget/TextView;", "changePlayingVisibility", "", "isCurrent", "", "getMiniVisu", "Lorg/videolan/vlc/gui/view/MiniVisualizer;", "recycle", "selectView", "selected", "setItem", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AudioBrowserAdapter.kt */
    public final class MediaItemCardViewHolder extends AbstractMediaItemViewHolder<AudioBrowserCardItemBinding> {
        final /* synthetic */ AudioBrowserAdapter this$0;
        private final TextView titleView;

        public void changePlayingVisibility(boolean z) {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MediaItemCardViewHolder(AudioBrowserAdapter audioBrowserAdapter, AudioBrowserCardItemBinding audioBrowserCardItemBinding) {
            super(audioBrowserAdapter, audioBrowserCardItemBinding);
            Intrinsics.checkNotNullParameter(audioBrowserCardItemBinding, "binding");
            this.this$0 = audioBrowserAdapter;
            TextView textView = audioBrowserCardItemBinding.title;
            Intrinsics.checkNotNullExpressionValue(textView, "title");
            this.titleView = textView;
            audioBrowserCardItemBinding.setHolder(this);
            audioBrowserCardItemBinding.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            BitmapDrawable access$getDefaultCoverCard$p = audioBrowserAdapter.defaultCoverCard;
            if (access$getDefaultCoverCard$p != null) {
                audioBrowserCardItemBinding.setCover(access$getDefaultCoverCard$p);
            }
            if (AndroidUtil.isMarshMallowOrLater) {
                this.itemView.setOnContextClickListener(new AudioBrowserAdapter$MediaItemCardViewHolder$$ExternalSyntheticLambda0(this));
            }
            audioBrowserCardItemBinding.setImageWidth(audioBrowserAdapter.getCardSize$vlc_android_release());
            audioBrowserCardItemBinding.container.getLayoutParams().width = audioBrowserAdapter.getCardSize$vlc_android_release();
        }

        public TextView getTitleView() {
            return this.titleView;
        }

        /* access modifiers changed from: private */
        public static final boolean _init_$lambda$1(MediaItemCardViewHolder mediaItemCardViewHolder, View view) {
            Intrinsics.checkNotNullParameter(mediaItemCardViewHolder, "this$0");
            Intrinsics.checkNotNull(view);
            mediaItemCardViewHolder.onMoreClick(view);
            return true;
        }

        public void selectView(boolean z) {
            super.selectView(z);
            ((AudioBrowserCardItemBinding) getBinding()).setVariable(BR.selected, Boolean.valueOf(z));
            int i = 4;
            ((AudioBrowserCardItemBinding) getBinding()).itemMore.setVisibility(this.this$0.getMultiSelectHelper().getInActionMode() ? 4 : 0);
            FadableImageView fadableImageView = ((AudioBrowserCardItemBinding) getBinding()).mainActionButton;
            if (!this.this$0.getMultiSelectHelper().getInActionMode()) {
                i = 0;
            }
            fadableImageView.setVisibility(i);
        }

        public void setItem(MediaLibraryItem mediaLibraryItem) {
            ((AudioBrowserCardItemBinding) getBinding()).setItem(mediaLibraryItem);
        }

        public void recycle() {
            BitmapDrawable access$getDefaultCoverCard$p = this.this$0.defaultCoverCard;
            if (access$getDefaultCoverCard$p != null) {
                ((AudioBrowserCardItemBinding) getBinding()).setCover(access$getDefaultCoverCard$p);
            }
            ((AudioBrowserCardItemBinding) getBinding()).mediaCover.resetFade();
            ((AudioBrowserCardItemBinding) getBinding()).title.setSelected(false);
        }

        public MiniVisualizer getMiniVisu() {
            MiniVisualizer miniVisualizer = ((AudioBrowserCardItemBinding) getBinding()).playing;
            Intrinsics.checkNotNullExpressionValue(miniVisualizer, "playing");
            return miniVisualizer;
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b§\u0004\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH&J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\bH\u0014J\u000e\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0018\u001a\u00020\fH&J\u0012\u0010\u0019\u001a\u00020\f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH&R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter$AbstractMediaItemViewHolder;", "T", "Landroidx/databinding/ViewDataBinding;", "Lorg/videolan/vlc/gui/helpers/SelectorViewHolder;", "Lorg/videolan/vlc/gui/helpers/MarqueeViewHolder;", "binding", "(Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;Landroidx/databinding/ViewDataBinding;)V", "canBeReordered", "", "getCanBeReordered", "()Z", "changePlayingVisibility", "", "isCurrent", "getMiniVisu", "Lorg/videolan/vlc/gui/view/MiniVisualizer;", "isSelected", "onClick", "v", "Landroid/view/View;", "onImageClick", "onLongClick", "onMainActionClick", "onMoreClick", "recycle", "setItem", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AudioBrowserAdapter.kt */
    public abstract class AbstractMediaItemViewHolder<T extends ViewDataBinding> extends SelectorViewHolder<T> implements MarqueeViewHolder {
        final /* synthetic */ AudioBrowserAdapter this$0;

        public abstract void changePlayingVisibility(boolean z);

        public abstract MiniVisualizer getMiniVisu();

        public abstract void recycle();

        public abstract void setItem(MediaLibraryItem mediaLibraryItem);

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AbstractMediaItemViewHolder(AudioBrowserAdapter audioBrowserAdapter, T t) {
            super(t);
            Intrinsics.checkNotNullParameter(t, "binding");
            this.this$0 = audioBrowserAdapter;
        }

        public final boolean getCanBeReordered() {
            return this.this$0.getReorderable() && !this.this$0.getStopReorder();
        }

        public final void onClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            MediaLibraryItem item = this.this$0.getItem(getLayoutPosition());
            if (item != null) {
                this.this$0.getEventsHandler().onClick(view, getLayoutPosition(), item);
            }
        }

        public final void onMoreClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            MediaLibraryItem item = this.this$0.getItem(getLayoutPosition());
            if (item != null) {
                this.this$0.getEventsHandler().onCtxClick(view, getLayoutPosition(), item);
            }
        }

        public final boolean onLongClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            MediaLibraryItem item = this.this$0.getItem(getLayoutPosition());
            return item != null && this.this$0.getEventsHandler().onLongClick(view, getLayoutPosition(), item);
        }

        public final void onImageClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            MediaLibraryItem item = this.this$0.getItem(getLayoutPosition());
            if (item != null) {
                this.this$0.getEventsHandler().onImageClick(view, getLayoutPosition(), item);
            }
        }

        public final void onMainActionClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            MediaLibraryItem item = this.this$0.getItem(getLayoutPosition());
            if (item != null) {
                this.this$0.getEventsHandler().onMainActionClick(view, getLayoutPosition(), item);
            }
        }

        /* access modifiers changed from: protected */
        public boolean isSelected() {
            return this.this$0.getMultiSelectHelper().isSelected(getLayoutPosition());
        }
    }

    @Metadata(d1 = {"\u0000%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "org/videolan/vlc/gui/audio/AudioBrowserAdapter$Companion$DIFF_CALLBACK$1", "Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter$Companion$DIFF_CALLBACK$1;", "TAG", "", "UPDATE_PAYLOAD", "", "preventNextAnim", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AudioBrowserAdapter.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
