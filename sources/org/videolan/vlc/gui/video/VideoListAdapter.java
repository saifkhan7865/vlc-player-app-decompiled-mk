package org.videolan.vlc.gui.video;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.flow.Flow;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.MultiSelectAdapter;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.EventsSource;
import org.videolan.vlc.gui.helpers.IEventsSource;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;
import org.videolan.vlc.gui.helpers.SelectorViewHolder;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.view.FastScroller;
import org.videolan.vlc.util.BrowserutilsKt;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.viewmodels.mobile.VideoGroupingType;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u00012\u00020\u00042\b\u0012\u0004\u0012\u00020\u00020\u00052\b\u0012\u0004\u0012\u00020\u00070\u0006:\u0002CDB\u0015\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u001c\u0010*\u001a\u00020+2\n\u0010,\u001a\u00060\u0003R\u00020\u00002\u0006\u0010-\u001a\u00020\u0002H\u0002J\u0012\u0010.\u001a\u0004\u0018\u00010\u00022\u0006\u0010/\u001a\u000200H\u0016J\u0010\u00101\u001a\u0002022\u0006\u0010/\u001a\u000200H\u0016J\u0010\u00103\u001a\u0002002\u0006\u0010/\u001a\u000200H\u0016J\b\u00104\u001a\u00020\tH\u0016J\u0010\u00105\u001a\u00020\t2\u0006\u0010/\u001a\u000200H\u0002J\u001c\u00106\u001a\u00020+2\n\u0010,\u001a\u00060\u0003R\u00020\u00002\u0006\u0010/\u001a\u000200H\u0016J*\u00106\u001a\u00020+2\n\u0010,\u001a\u00060\u0003R\u00020\u00002\u0006\u0010/\u001a\u0002002\f\u00107\u001a\b\u0012\u0004\u0012\u0002080\rH\u0016J\u001c\u00109\u001a\u00060\u0003R\u00020\u00002\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u000200H\u0016J\u0014\u0010=\u001a\u00020+2\n\u0010,\u001a\u00060\u0003R\u00020\u0000H\u0016J\u000e\u0010>\u001a\u00020+2\u0006\u0010?\u001a\u00020\tJ\u000e\u0010@\u001a\u00020+2\u0006\u0010A\u001a\u00020BR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0017X\u0005¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u001bX\u0005¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\n\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00020#¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010&\u001a\u00020'¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)¨\u0006E"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoListAdapter;", "Landroidx/paging/PagedListAdapter;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lorg/videolan/vlc/gui/video/VideoListAdapter$ViewHolder;", "Lorg/videolan/vlc/gui/view/FastScroller$SeparatedAdapter;", "Lorg/videolan/tools/MultiSelectAdapter;", "Lorg/videolan/vlc/gui/helpers/IEventsSource;", "Lorg/videolan/vlc/gui/video/VideoAction;", "isSeenMediaMarkerVisible", "", "hideProgress", "(ZZ)V", "all", "", "getAll", "()Ljava/util/List;", "dataType", "Lorg/videolan/vlc/viewmodels/mobile/VideoGroupingType;", "getDataType", "()Lorg/videolan/vlc/viewmodels/mobile/VideoGroupingType;", "setDataType", "(Lorg/videolan/vlc/viewmodels/mobile/VideoGroupingType;)V", "events", "Lkotlinx/coroutines/flow/Flow;", "getEvents", "()Lkotlinx/coroutines/flow/Flow;", "eventsChannel", "Lkotlinx/coroutines/channels/Channel;", "getEventsChannel", "()Lkotlinx/coroutines/channels/Channel;", "isListMode", "()Z", "setListMode", "(Z)V", "multiSelectHelper", "Lorg/videolan/tools/MultiSelectHelper;", "getMultiSelectHelper", "()Lorg/videolan/tools/MultiSelectHelper;", "showFilename", "Landroidx/databinding/ObservableBoolean;", "getShowFilename", "()Landroidx/databinding/ObservableBoolean;", "fillView", "", "holder", "item", "getItem", "position", "", "getItemId", "", "getItemViewType", "hasSections", "isPositionValid", "onBindViewHolder", "payloads", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onViewRecycled", "setSeenMediaMarkerVisible", "seenMediaMarkerVisible", "updateThumb", "media", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "VideoItemDiffCallback", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoListAdapter.kt */
public final class VideoListAdapter extends PagedListAdapter<MediaLibraryItem, ViewHolder> implements FastScroller.SeparatedAdapter, MultiSelectAdapter<MediaLibraryItem>, IEventsSource<VideoAction> {
    private final /* synthetic */ EventsSource<VideoAction> $$delegate_0 = new EventsSource<>();
    private VideoGroupingType dataType = VideoGroupingType.NONE;
    private boolean hideProgress;
    private boolean isListMode;
    private boolean isSeenMediaMarkerVisible;
    private final MultiSelectHelper<MediaLibraryItem> multiSelectHelper = new MultiSelectHelper<>(this, 0);
    private final ObservableBoolean showFilename = new ObservableBoolean(false);

    public Flow<VideoAction> getEvents() {
        return this.$$delegate_0.getEvents();
    }

    public Channel<VideoAction> getEventsChannel() {
        return this.$$delegate_0.getEventsChannel();
    }

    public long getItemId(int i) {
        return 0;
    }

    public boolean hasSections() {
        return true;
    }

    public VideoListAdapter(boolean z, boolean z2) {
        super(VideoItemDiffCallback.INSTANCE);
        this.isSeenMediaMarkerVisible = z;
        this.hideProgress = z2;
    }

    public final boolean isListMode() {
        return this.isListMode;
    }

    public final void setListMode(boolean z) {
        this.isListMode = z;
    }

    public final VideoGroupingType getDataType() {
        return this.dataType;
    }

    public final void setDataType(VideoGroupingType videoGroupingType) {
        Intrinsics.checkNotNullParameter(videoGroupingType, "<set-?>");
        this.dataType = videoGroupingType;
    }

    public final ObservableBoolean getShowFilename() {
        return this.showFilename;
    }

    public final MultiSelectHelper<MediaLibraryItem> getMultiSelectHelper() {
        return this.multiSelectHelper;
    }

    public final void updateThumb(MediaWrapper mediaWrapper) {
        List snapshot;
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        PagedList currentList = getCurrentList();
        if (currentList != null && (snapshot = currentList.snapshot()) != null) {
            int indexOf = snapshot.indexOf(mediaWrapper);
            MediaLibraryItem item = getItem(indexOf);
            MediaWrapper mediaWrapper2 = item instanceof MediaWrapper ? (MediaWrapper) item : null;
            if (mediaWrapper2 != null) {
                mediaWrapper2.setArtworkURL(mediaWrapper.getArtworkURL());
                notifyItemChanged(indexOf);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r0.snapshot();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<org.videolan.medialibrary.media.MediaLibraryItem> getAll() {
        /*
            r1 = this;
            androidx.paging.PagedList r0 = r1.getCurrentList()
            if (r0 == 0) goto L_0x000c
            java.util.List r0 = r0.snapshot()
            if (r0 != 0) goto L_0x0010
        L_0x000c:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0010:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoListAdapter.getAll():java.util.List");
    }

    public int getItemViewType(int i) {
        return this.isListMode ^ true ? 1 : 0;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), i == 0 ? R.layout.video_list_card : R.layout.video_grid_card, viewGroup, false);
        Intrinsics.checkNotNull(inflate);
        return new ViewHolder(this, inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        MediaLibraryItem item = getItem(i);
        if (item != null) {
            viewHolder.getBinding().setVariable(BR.scaleType, ImageView.ScaleType.CENTER_CROP);
            fillView(viewHolder, item);
            viewHolder.getBinding().setVariable(BR.media, item);
            viewHolder.selectView(this.multiSelectHelper.isSelected(i));
            viewHolder.getBinding().setVariable(BR.isFavorite, Boolean.valueOf(item.isFavorite()));
            ViewDataBinding binding = viewHolder.getBinding();
            int i2 = BR.showProgress;
            CharSequence artworkMrl = item.getArtworkMrl();
            binding.setVariable(i2, Boolean.valueOf(artworkMrl == null || StringsKt.isBlank(artworkMrl)));
            viewHolder.getBinding().setVariable(BR.showItemProgress, Boolean.valueOf(!this.hideProgress));
        }
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i, List<? extends Object> list) {
        MediaLibraryItem item;
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        Intrinsics.checkNotNullParameter(list, "payloads");
        if (list.isEmpty()) {
            onBindViewHolder(viewHolder, i);
            return;
        }
        MediaLibraryItem item2 = getItem(i);
        for (Object next : list) {
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlin.Int");
            int intValue = ((Integer) next).intValue();
            if (intValue == 0) {
                viewHolder.selectView(this.multiSelectHelper.isSelected(i));
            } else if (intValue == 1) {
                ImageLoaderKt.loadImage$default(viewHolder.getOverlay(), item2, 0, false, false, 28, (Object) null);
            } else if (intValue == 2 || intValue == 3) {
                Intrinsics.checkNotNull(item2, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
                fillView(viewHolder, (MediaWrapper) item2);
            } else if (intValue == 6) {
                Intrinsics.checkNotNull(item2);
                fillView(viewHolder, item2);
            } else if (intValue == 8 && (item = getItem(i)) != null) {
                viewHolder.getBinding().setVariable(BR.isFavorite, Boolean.valueOf(item.isFavorite()));
            }
        }
    }

    public void onViewRecycled(ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        ViewDataBinding binding = viewHolder.getBinding();
        int i = BR.cover;
        UiTools uiTools = UiTools.INSTANCE;
        Context context = viewHolder.itemView.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        binding.setVariable(i, uiTools.getDefaultVideoDrawable(context));
    }

    public MediaLibraryItem getItem(int i) {
        if (isPositionValid(i)) {
            return (MediaLibraryItem) super.getItem(i);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public final boolean isPositionValid(int i) {
        return i >= 0 && i < getItemCount();
    }

    private final void fillView(ViewHolder viewHolder, MediaLibraryItem mediaLibraryItem) {
        int i;
        int i2;
        String str;
        String str2 = null;
        int i3 = 0;
        if (mediaLibraryItem instanceof Folder) {
            Folder folder = (Folder) mediaLibraryItem;
            viewHolder.getTitle().setText(folder.getTitle());
            if (!this.isListMode) {
                viewHolder.getBinding().setVariable(BR.resolution, (Object) null);
            }
            viewHolder.getBinding().setVariable(BR.seen, 0L);
            viewHolder.getBinding().setVariable(BR.max, 0);
            int mediaCount = folder.mediaCount(Folder.TYPE_FOLDER_VIDEO);
            viewHolder.getBinding().setVariable(BR.time, viewHolder.itemView.getContext().getResources().getQuantityString(R.plurals.videos_quantity, mediaCount, new Object[]{Integer.valueOf(mediaCount)}));
            viewHolder.getBinding().setVariable(BR.isNetwork, false);
            viewHolder.getBinding().setVariable(BR.isPresent, true);
            viewHolder.getBinding().setVariable(BR.isFavorite, Boolean.valueOf(folder.isFavorite()));
            viewHolder.getBinding().setVariable(BR.media, mediaLibraryItem);
        } else if (mediaLibraryItem instanceof VideoGroup) {
            View view = viewHolder.itemView;
            Intrinsics.checkNotNullExpressionValue(view, "itemView");
            Job unused = BuildersKt__Builders_commonKt.launch$default(KextensionsKt.getScope(view), (CoroutineContext) null, (CoroutineStart) null, new VideoListAdapter$fillView$1(mediaLibraryItem, viewHolder, this, (Continuation<? super VideoListAdapter$fillView$1>) null), 3, (Object) null);
        } else if (mediaLibraryItem instanceof MediaWrapper) {
            viewHolder.getTitle().setText(this.showFilename.get() ? ((MediaWrapper) mediaLibraryItem).getFileName() : ((MediaWrapper) mediaLibraryItem).getTitle());
            MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
            String generateResolutionClass = KextensionsKt.generateResolutionClass(mediaWrapper.getWidth(), mediaWrapper.getHeight());
            viewHolder.getBinding().setVariable(BR.isNetwork, Boolean.valueOf(BrowserutilsKt.isSchemeSMB(mediaWrapper.getUri().getScheme())));
            ViewDataBinding binding = viewHolder.getBinding();
            int i4 = BR.isOTG;
            Uri uri = mediaWrapper.getUri();
            Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
            binding.setVariable(i4, Boolean.valueOf(BrowserutilsKt.isOTG(uri)));
            ViewDataBinding binding2 = viewHolder.getBinding();
            int i5 = BR.isSD;
            Uri uri2 = mediaWrapper.getUri();
            Intrinsics.checkNotNullExpressionValue(uri2, "getUri(...)");
            binding2.setVariable(i5, Boolean.valueOf(BrowserutilsKt.isSD(uri2)));
            viewHolder.getBinding().setVariable(BR.isPresent, Boolean.valueOf(mediaWrapper.isPresent()));
            long seen = this.isSeenMediaMarkerVisible ? mediaWrapper.getSeen() : 0;
            if (mediaWrapper.getLength() > 0) {
                long displayTime = mediaWrapper.getDisplayTime();
                if (displayTime > 0) {
                    long j = (long) 1000;
                    i2 = (int) (displayTime / j);
                    i3 = (int) (mediaWrapper.getLength() / j);
                } else {
                    i2 = 0;
                }
                if (!this.isListMode || generateResolutionClass == null) {
                    str = Tools.millisToString(mediaWrapper.getLength());
                } else {
                    str = Tools.millisToString(mediaWrapper.getLength()) + "  •  " + generateResolutionClass;
                }
                String str3 = str;
                i = i2;
                str2 = str3;
            } else {
                i = 0;
            }
            viewHolder.getBinding().setVariable(BR.time, str2);
            viewHolder.getBinding().setVariable(BR.max, Integer.valueOf(i3));
            viewHolder.getBinding().setVariable(BR.progress, Integer.valueOf(i));
            viewHolder.getBinding().setVariable(BR.seen, Long.valueOf(seen));
            viewHolder.getBinding().setVariable(BR.isFavorite, Boolean.valueOf(mediaWrapper.isFavorite()));
            if (!this.isListMode) {
                viewHolder.getBinding().setVariable(BR.resolution, generateResolutionClass);
            }
        }
        viewHolder.getBinding().setVariable(BR.inSelection, Boolean.valueOf(this.multiSelectHelper.getInActionMode()));
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u0010H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoListAdapter$ViewHolder;", "Lorg/videolan/vlc/gui/helpers/SelectorViewHolder;", "Landroidx/databinding/ViewDataBinding;", "binding", "(Lorg/videolan/vlc/gui/video/VideoListAdapter;Landroidx/databinding/ViewDataBinding;)V", "more", "Landroid/widget/ImageView;", "getMore", "()Landroid/widget/ImageView;", "overlay", "getOverlay", "title", "Landroid/widget/TextView;", "getTitle", "()Landroid/widget/TextView;", "isSelected", "", "onClick", "", "v", "Landroid/view/View;", "onImageClick", "onLongClick", "onMoreClick", "selectView", "selected", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoListAdapter.kt */
    public final class ViewHolder extends SelectorViewHolder<ViewDataBinding> {
        private final ImageView more;
        private final ImageView overlay;
        final /* synthetic */ VideoListAdapter this$0;
        private final TextView title;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(VideoListAdapter videoListAdapter, ViewDataBinding viewDataBinding) {
            super(viewDataBinding);
            Intrinsics.checkNotNullParameter(viewDataBinding, "binding");
            this.this$0 = videoListAdapter;
            View findViewById = this.itemView.findViewById(R.id.ml_item_overlay);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.overlay = (ImageView) findViewById;
            View findViewById2 = this.itemView.findViewById(R.id.ml_item_title);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.title = (TextView) findViewById2;
            View findViewById3 = this.itemView.findViewById(R.id.item_more);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            this.more = (ImageView) findViewById3;
            viewDataBinding.setVariable(BR.holder, this);
            int i = BR.cover;
            UiTools uiTools = UiTools.INSTANCE;
            Context context = this.itemView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            viewDataBinding.setVariable(i, uiTools.getDefaultVideoDrawable(context));
            if (AndroidUtil.isMarshMallowOrLater) {
                this.itemView.setOnContextClickListener(new VideoListAdapter$ViewHolder$$ExternalSyntheticLambda0(this));
            }
        }

        public final ImageView getOverlay() {
            return this.overlay;
        }

        public final TextView getTitle() {
            return this.title;
        }

        public final ImageView getMore() {
            return this.more;
        }

        /* access modifiers changed from: private */
        public static final boolean _init_$lambda$0(ViewHolder viewHolder, View view) {
            Intrinsics.checkNotNullParameter(viewHolder, "this$0");
            Intrinsics.checkNotNull(view);
            viewHolder.onMoreClick(view);
            return true;
        }

        public final void onImageClick(View view) {
            MediaLibraryItem item;
            Intrinsics.checkNotNullParameter(view, "v");
            int layoutPosition = getLayoutPosition();
            if (this.this$0.isPositionValid(layoutPosition) && (item = this.this$0.getItem(layoutPosition)) != null) {
                ChannelResult.m2336boximpl(this.this$0.getEventsChannel().m1139trySendJP2dKIU(new VideoImageClick(getLayoutPosition(), item)));
            }
        }

        public final void onClick(View view) {
            MediaLibraryItem item;
            Intrinsics.checkNotNullParameter(view, "v");
            int layoutPosition = getLayoutPosition();
            if (this.this$0.isPositionValid(layoutPosition) && (item = this.this$0.getItem(layoutPosition)) != null) {
                ChannelResult.m2336boximpl(this.this$0.getEventsChannel().m1139trySendJP2dKIU(new VideoClick(getLayoutPosition(), item)));
            }
        }

        public final void onMoreClick(View view) {
            MediaLibraryItem item;
            Intrinsics.checkNotNullParameter(view, "v");
            int layoutPosition = getLayoutPosition();
            if (this.this$0.isPositionValid(layoutPosition) && (item = this.this$0.getItem(layoutPosition)) != null) {
                ChannelResult.m2336boximpl(this.this$0.getEventsChannel().m1139trySendJP2dKIU(new VideoCtxClick(getLayoutPosition(), item)));
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0011, code lost:
            r4 = r3.this$0.getItem(r4);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean onLongClick(android.view.View r4) {
            /*
                r3 = this;
                java.lang.String r0 = "v"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                int r4 = r3.getLayoutPosition()
                org.videolan.vlc.gui.video.VideoListAdapter r0 = r3.this$0
                boolean r0 = r0.isPositionValid(r4)
                if (r0 == 0) goto L_0x0034
                org.videolan.vlc.gui.video.VideoListAdapter r0 = r3.this$0
                org.videolan.medialibrary.media.MediaLibraryItem r4 = r0.getItem((int) r4)
                if (r4 == 0) goto L_0x0034
                org.videolan.vlc.gui.video.VideoListAdapter r0 = r3.this$0
                kotlinx.coroutines.channels.Channel r0 = r0.getEventsChannel()
                org.videolan.vlc.gui.video.VideoLongClick r1 = new org.videolan.vlc.gui.video.VideoLongClick
                int r2 = r3.getLayoutPosition()
                r1.<init>(r2, r4)
                java.lang.Object r4 = r0.m1139trySendJP2dKIU(r1)
                boolean r4 = kotlinx.coroutines.channels.ChannelResult.m2346isSuccessimpl(r4)
                r0 = 1
                if (r4 != r0) goto L_0x0034
                goto L_0x0035
            L_0x0034:
                r0 = 0
            L_0x0035:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoListAdapter.ViewHolder.onLongClick(android.view.View):boolean");
        }

        public void selectView(boolean z) {
            getBinding().setVariable(BR.selected, Boolean.valueOf(z));
            int i = 0;
            this.overlay.setImageResource(z ? R.drawable.video_overlay_selected : this.this$0.isListMode() ? 0 : R.drawable.video_overlay_gradient);
            if (this.this$0.isListMode()) {
                this.overlay.setVisibility(z ? 0 : 8);
            }
            ImageView imageView = this.more;
            if (this.this$0.getMultiSelectHelper().getInActionMode()) {
                i = 4;
            }
            imageView.setVisibility(i);
        }

        /* access modifiers changed from: protected */
        public boolean isSelected() {
            return this.this$0.getMultiSelectHelper().isSelected(getLayoutPosition());
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0017J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u001d\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoListAdapter$VideoItemDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "getChangePayload", "", "(Lorg/videolan/medialibrary/media/MediaLibraryItem;Lorg/videolan/medialibrary/media/MediaLibraryItem;)Ljava/lang/Integer;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoListAdapter.kt */
    private static final class VideoItemDiffCallback extends DiffUtil.ItemCallback<MediaLibraryItem> {
        public static final VideoItemDiffCallback INSTANCE = new VideoItemDiffCallback();

        private VideoItemDiffCallback() {
        }

        public boolean areItemsTheSame(MediaLibraryItem mediaLibraryItem, MediaLibraryItem mediaLibraryItem2) {
            Intrinsics.checkNotNullParameter(mediaLibraryItem, "oldItem");
            Intrinsics.checkNotNullParameter(mediaLibraryItem2, "newItem");
            if (!(mediaLibraryItem instanceof MediaWrapper) || !(mediaLibraryItem2 instanceof MediaWrapper)) {
                if (mediaLibraryItem == mediaLibraryItem2 || (mediaLibraryItem.getItemType() == mediaLibraryItem2.getItemType() && mediaLibraryItem.equals(mediaLibraryItem2))) {
                    return true;
                }
                return false;
            } else if (mediaLibraryItem != mediaLibraryItem2) {
                MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
                MediaWrapper mediaWrapper2 = (MediaWrapper) mediaLibraryItem2;
                if (mediaWrapper.getType() != mediaWrapper2.getType() || !mediaWrapper.equals(mediaWrapper2)) {
                    return false;
                }
            }
            return true;
        }

        public boolean areContentsTheSame(MediaLibraryItem mediaLibraryItem, MediaLibraryItem mediaLibraryItem2) {
            Intrinsics.checkNotNullParameter(mediaLibraryItem, "oldItem");
            Intrinsics.checkNotNullParameter(mediaLibraryItem2, "newItem");
            if (!(mediaLibraryItem instanceof MediaWrapper) || !(mediaLibraryItem2 instanceof MediaWrapper)) {
                if (!(mediaLibraryItem instanceof VideoGroup) || !(mediaLibraryItem2 instanceof VideoGroup)) {
                    if (!(mediaLibraryItem instanceof Folder) || !(mediaLibraryItem2 instanceof Folder)) {
                        if (mediaLibraryItem.getItemType() == 1024 || (mediaLibraryItem.getItemType() == 2048 && mediaLibraryItem.isFavorite() == mediaLibraryItem2.isFavorite())) {
                            return true;
                        }
                        return false;
                    } else if (mediaLibraryItem != mediaLibraryItem2) {
                        Folder folder = (Folder) mediaLibraryItem;
                        Folder folder2 = (Folder) mediaLibraryItem2;
                        if (!Intrinsics.areEqual((Object) folder.getTitle(), (Object) folder2.getTitle()) || folder.getTracksCount() != folder2.getTracksCount() || !Intrinsics.areEqual((Object) folder.mMrl, (Object) folder2.mMrl) || folder.isFavorite() != folder2.isFavorite()) {
                            return false;
                        }
                    }
                } else if (mediaLibraryItem != mediaLibraryItem2) {
                    VideoGroup videoGroup = (VideoGroup) mediaLibraryItem;
                    VideoGroup videoGroup2 = (VideoGroup) mediaLibraryItem2;
                    if (!Intrinsics.areEqual((Object) videoGroup.getTitle(), (Object) videoGroup2.getTitle()) || videoGroup.getTracksCount() != videoGroup2.getTracksCount() || videoGroup.getPresentCount() == videoGroup2.getPresentCount() || videoGroup.isFavorite() != videoGroup2.isFavorite()) {
                        return false;
                    }
                }
            } else if (mediaLibraryItem != mediaLibraryItem2) {
                MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
                MediaWrapper mediaWrapper2 = (MediaWrapper) mediaLibraryItem2;
                if (!(mediaWrapper.getDisplayTime() == mediaWrapper2.getDisplayTime() && Intrinsics.areEqual((Object) mediaWrapper.getArtworkMrl(), (Object) mediaWrapper2.getArtworkMrl()) && mediaWrapper.getSeen() == mediaWrapper2.getSeen() && mediaWrapper.isPresent() == mediaWrapper2.isPresent() && mediaWrapper.isFavorite() == mediaWrapper2.isFavorite())) {
                    return false;
                }
            }
            return true;
        }

        public Integer getChangePayload(MediaLibraryItem mediaLibraryItem, MediaLibraryItem mediaLibraryItem2) {
            int i;
            Intrinsics.checkNotNullParameter(mediaLibraryItem, "oldItem");
            Intrinsics.checkNotNullParameter(mediaLibraryItem2, "newItem");
            if ((mediaLibraryItem instanceof MediaWrapper) && (mediaLibraryItem2 instanceof MediaWrapper) && ((MediaWrapper) mediaLibraryItem).getDisplayTime() != ((MediaWrapper) mediaLibraryItem2).getDisplayTime()) {
                i = 2;
            } else if (((mediaLibraryItem instanceof VideoGroup) && (mediaLibraryItem2 instanceof VideoGroup)) || ((mediaLibraryItem instanceof Folder) && (mediaLibraryItem2 instanceof Folder))) {
                i = 6;
            } else if (!Intrinsics.areEqual((Object) mediaLibraryItem.getArtworkMrl(), (Object) mediaLibraryItem2.getArtworkMrl())) {
                i = 1;
            } else {
                i = mediaLibraryItem.isFavorite() != mediaLibraryItem2.isFavorite() ? 8 : 3;
            }
            return Integer.valueOf(i);
        }
    }

    public final void setSeenMediaMarkerVisible(boolean z) {
        this.isSeenMediaMarkerVisible = z;
    }
}
