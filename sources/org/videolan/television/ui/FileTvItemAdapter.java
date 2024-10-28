package org.videolan.television.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.interfaces.FocusListener;
import org.videolan.television.R;
import org.videolan.television.databinding.MediaBrowserTvItemBinding;
import org.videolan.television.databinding.MediaBrowserTvItemListBinding;
import org.videolan.television.ui.MediaTvItemAdapter;
import org.videolan.television.ui.browser.TvAdapterUtils;
import org.videolan.tools.Settings;
import org.videolan.vlc.gui.DiffUtilAdapter;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;
import org.videolan.vlc.gui.view.FastScroller;
import org.videolan.vlc.interfaces.IEventsHandler;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00012\u00020\u00052\u00020\u0006:\u000234B-\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001dH\u0014J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0010\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u000bH\u0016J\u0012\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020\u0002H\u0002J\b\u0010%\u001a\u00020\rH\u0016J\u001e\u0010&\u001a\u00020\u001f2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010!\u001a\u00020\u000bH\u0016J,\u0010&\u001a\u00020\u001f2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010!\u001a\u00020\u000b2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)H\u0016J\u001e\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u000bH\u0016J\u0016\u0010/\u001a\u00020\u001f2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\u0012\u00100\u001a\u00020\u001f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u00101\u001a\u00020\u001f2\b\u00102\u001a\u0004\u0018\u00010*H\u0016R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u000e\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u000e\u0010\u001b\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lorg/videolan/television/ui/FileTvItemAdapter;", "Lorg/videolan/vlc/gui/DiffUtilAdapter;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "Lorg/videolan/television/ui/MediaTvItemAdapter$AbstractMediaItemViewHolder;", "Landroidx/databinding/ViewDataBinding;", "Lorg/videolan/vlc/gui/view/FastScroller$SeparatedAdapter;", "Lorg/videolan/television/ui/TvItemAdapter;", "eventsHandler", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "itemSize", "", "showProtocol", "", "inGrid", "(Lorg/videolan/vlc/interfaces/IEventsHandler;IZZ)V", "defaultCover", "Landroid/graphics/drawable/BitmapDrawable;", "focusListener", "Lorg/videolan/resources/interfaces/FocusListener;", "focusNext", "getFocusNext", "()I", "setFocusNext", "(I)V", "getItemSize", "setItemSize", "seenMediaMarkerVisible", "createCB", "Lorg/videolan/vlc/gui/DiffUtilAdapter$DiffCallback;", "displaySwitch", "", "getItemViewType", "position", "getProtocol", "", "media", "hasSections", "onBindViewHolder", "holder", "payloads", "", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onViewRecycled", "setOnFocusChangeListener", "submitList", "pagedList", "MediaItemTVListViewHolder", "MediaItemTVViewHolder", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileTvItemAdapter.kt */
public final class FileTvItemAdapter extends DiffUtilAdapter<MediaWrapper, MediaTvItemAdapter.AbstractMediaItemViewHolder<ViewDataBinding>> implements FastScroller.SeparatedAdapter, TvItemAdapter {
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
    private final boolean showProtocol;

    public boolean hasSections() {
        return true;
    }

    public FileTvItemAdapter(IEventsHandler<MediaLibraryItem> iEventsHandler, int i, boolean z, boolean z2) {
        Context context;
        Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
        this.eventsHandler = iEventsHandler;
        this.itemSize = i;
        this.showProtocol = z;
        this.inGrid = z2;
        this.focusNext = -1;
        boolean z3 = true;
        this.seenMediaMarkerVisible = true;
        BitmapDrawable bitmapDrawable = null;
        if (iEventsHandler instanceof Context) {
            context = (Context) iEventsHandler;
        } else {
            context = iEventsHandler instanceof Fragment ? ((Fragment) iEventsHandler).getContext() : null;
        }
        this.defaultCover = context != null ? new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_unknown_big)) : bitmapDrawable;
        this.seenMediaMarkerVisible = context != null ? ((SharedPreferences) Settings.INSTANCE.getInstance(context)).getBoolean("media_seen", true) : z3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FileTvItemAdapter(IEventsHandler iEventsHandler, int i, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(iEventsHandler, i, z, (i2 & 8) != 0 ? true : z2);
    }

    public final int getItemSize() {
        return this.itemSize;
    }

    public final void setItemSize(int i) {
        this.itemSize = i;
    }

    public void submitList(Object obj) {
        if (obj instanceof List) {
            update((List) obj);
        }
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

    public MediaTvItemAdapter.AbstractMediaItemViewHolder<ViewDataBinding> onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (this.inGrid) {
            MediaBrowserTvItemBinding inflate = MediaBrowserTvItemBinding.inflate(from, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new MediaItemTVViewHolder(this, inflate, this.eventsHandler, this.showProtocol);
        }
        MediaBrowserTvItemListBinding inflate2 = MediaBrowserTvItemListBinding.inflate(from, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate2, "inflate(...)");
        return new MediaItemTVListViewHolder(this, inflate2, this.eventsHandler, this.showProtocol);
    }

    public int getItemViewType(int i) {
        return this.inGrid ^ true ? 1 : 0;
    }

    public void onBindViewHolder(MediaTvItemAdapter.AbstractMediaItemViewHolder<ViewDataBinding> abstractMediaItemViewHolder, int i) {
        Intrinsics.checkNotNullParameter(abstractMediaItemViewHolder, "holder");
        if (i < getItemCount()) {
            abstractMediaItemViewHolder.setItem((MediaWrapper) getItem(i));
            abstractMediaItemViewHolder.getBinding().executePendingBindings();
            if (i == getFocusNext()) {
                abstractMediaItemViewHolder.getBinding().getRoot().requestFocus();
                setFocusNext(-1);
            }
        }
    }

    public void onBindViewHolder(MediaTvItemAdapter.AbstractMediaItemViewHolder<ViewDataBinding> abstractMediaItemViewHolder, int i, List<Object> list) {
        Intrinsics.checkNotNullParameter(abstractMediaItemViewHolder, "holder");
        Intrinsics.checkNotNullParameter(list, "payloads");
        if (list.isEmpty()) {
            onBindViewHolder(abstractMediaItemViewHolder, i);
            return;
        }
        for (Object next : list) {
            ViewDataBinding binding = abstractMediaItemViewHolder.getBinding();
            if (binding instanceof MediaBrowserTvItemBinding) {
                if (next instanceof String) {
                    ViewDataBinding binding2 = abstractMediaItemViewHolder.getBinding();
                    Intrinsics.checkNotNull(binding2, "null cannot be cast to non-null type org.videolan.television.databinding.MediaBrowserTvItemBinding");
                    ((MediaBrowserTvItemBinding) binding2).setDescription((String) next);
                } else {
                    onBindViewHolder(abstractMediaItemViewHolder, i);
                }
            } else if (binding instanceof MediaBrowserTvItemListBinding) {
                if (next instanceof String) {
                    ViewDataBinding binding3 = abstractMediaItemViewHolder.getBinding();
                    Intrinsics.checkNotNull(binding3, "null cannot be cast to non-null type org.videolan.television.databinding.MediaBrowserTvItemListBinding");
                    ((MediaBrowserTvItemListBinding) binding3).setDescription((String) next);
                } else {
                    onBindViewHolder(abstractMediaItemViewHolder, i);
                }
            }
        }
    }

    public void onViewRecycled(MediaTvItemAdapter.AbstractMediaItemViewHolder<ViewDataBinding> abstractMediaItemViewHolder) {
        Intrinsics.checkNotNullParameter(abstractMediaItemViewHolder, "holder");
        super.onViewRecycled(abstractMediaItemViewHolder);
        abstractMediaItemViewHolder.recycle();
    }

    public void setOnFocusChangeListener(FocusListener focusListener2) {
        this.focusListener = focusListener2;
    }

    /* access modifiers changed from: protected */
    public DiffUtilAdapter.DiffCallback<MediaWrapper> createCB() {
        return new FileTvItemAdapter$createCB$1();
    }

    /* access modifiers changed from: private */
    public final String getProtocol(MediaWrapper mediaWrapper) {
        if (mediaWrapper.getType() != 3) {
            return null;
        }
        return mediaWrapper.getUri().getScheme();
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\bH\u0016J\u0012\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u0006H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/videolan/television/ui/FileTvItemAdapter$MediaItemTVViewHolder;", "Lorg/videolan/television/ui/MediaTvItemAdapter$AbstractMediaItemViewHolder;", "Lorg/videolan/television/databinding/MediaBrowserTvItemBinding;", "binding", "eventsHandler", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "showProtocol", "", "(Lorg/videolan/television/ui/FileTvItemAdapter;Lorg/videolan/television/databinding/MediaBrowserTvItemBinding;Lorg/videolan/vlc/interfaces/IEventsHandler;Z)V", "getEventsHandler", "()Lorg/videolan/vlc/interfaces/IEventsHandler;", "getItem", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "layoutPosition", "", "getView", "Lorg/videolan/television/ui/FocusableConstraintLayout;", "recycle", "", "setCoverlay", "selected", "setItem", "item", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FileTvItemAdapter.kt */
    public final class MediaItemTVViewHolder extends MediaTvItemAdapter.AbstractMediaItemViewHolder<MediaBrowserTvItemBinding> {
        private final IEventsHandler<MediaLibraryItem> eventsHandler;
        private final boolean showProtocol;
        final /* synthetic */ FileTvItemAdapter this$0;

        public void setCoverlay(boolean z) {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MediaItemTVViewHolder(FileTvItemAdapter fileTvItemAdapter, MediaBrowserTvItemBinding mediaBrowserTvItemBinding, IEventsHandler<MediaLibraryItem> iEventsHandler, boolean z) {
            super(mediaBrowserTvItemBinding);
            Intrinsics.checkNotNullParameter(mediaBrowserTvItemBinding, "binding");
            Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
            this.this$0 = fileTvItemAdapter;
            this.eventsHandler = iEventsHandler;
            this.showProtocol = z;
            mediaBrowserTvItemBinding.setHolder(this);
            mediaBrowserTvItemBinding.setIsPresent(true);
            mediaBrowserTvItemBinding.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            BitmapDrawable access$getDefaultCover$p = fileTvItemAdapter.defaultCover;
            if (access$getDefaultCover$p != null) {
                mediaBrowserTvItemBinding.setCover(access$getDefaultCover$p);
            }
            if (AndroidUtil.isMarshMallowOrLater) {
                this.itemView.setOnContextClickListener(new FileTvItemAdapter$MediaItemTVViewHolder$$ExternalSyntheticLambda1(this));
            }
            mediaBrowserTvItemBinding.container.getLayoutParams().width = fileTvItemAdapter.getItemSize();
            mediaBrowserTvItemBinding.container.setOnFocusChangeListener(new FileTvItemAdapter$MediaItemTVViewHolder$$ExternalSyntheticLambda2(mediaBrowserTvItemBinding, fileTvItemAdapter, this));
            if (AndroidUtil.isLolliPopOrLater) {
                mediaBrowserTvItemBinding.container.setClipToOutline(true);
            }
            mediaBrowserTvItemBinding.setShowSeen(fileTvItemAdapter.seenMediaMarkerVisible);
        }

        public IEventsHandler<MediaLibraryItem> getEventsHandler() {
            return this.eventsHandler;
        }

        public MediaWrapper getItem(int i) {
            return (MediaWrapper) this.this$0.getItem(i);
        }

        public FocusableConstraintLayout getView() {
            FocusableConstraintLayout focusableConstraintLayout = ((MediaBrowserTvItemBinding) getBinding()).container;
            Intrinsics.checkNotNullExpressionValue(focusableConstraintLayout, "container");
            return focusableConstraintLayout;
        }

        /* access modifiers changed from: private */
        public static final boolean _init_$lambda$1(MediaItemTVViewHolder mediaItemTVViewHolder, View view) {
            Intrinsics.checkNotNullParameter(mediaItemTVViewHolder, "this$0");
            Intrinsics.checkNotNull(view);
            mediaItemTVViewHolder.onMoreClick(view);
            return true;
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$3(MediaBrowserTvItemBinding mediaBrowserTvItemBinding, FileTvItemAdapter fileTvItemAdapter, MediaItemTVViewHolder mediaItemTVViewHolder, View view, boolean z) {
            Intrinsics.checkNotNullParameter(mediaBrowserTvItemBinding, "$binding");
            Intrinsics.checkNotNullParameter(fileTvItemAdapter, "this$0");
            Intrinsics.checkNotNullParameter(mediaItemTVViewHolder, "this$1");
            mediaBrowserTvItemBinding.container.post(new FileTvItemAdapter$MediaItemTVViewHolder$$ExternalSyntheticLambda0(z, fileTvItemAdapter, mediaBrowserTvItemBinding, mediaItemTVViewHolder));
        }

        static final void lambda$3$lambda$2(boolean z, FileTvItemAdapter fileTvItemAdapter, MediaBrowserTvItemBinding mediaBrowserTvItemBinding, MediaItemTVViewHolder mediaItemTVViewHolder) {
            Intrinsics.checkNotNullParameter(fileTvItemAdapter, "this$0");
            Intrinsics.checkNotNullParameter(mediaBrowserTvItemBinding, "$binding");
            Intrinsics.checkNotNullParameter(mediaItemTVViewHolder, "this$1");
            TvAdapterUtils tvAdapterUtils = TvAdapterUtils.INSTANCE;
            int itemSize = fileTvItemAdapter.getItemSize();
            FocusableConstraintLayout focusableConstraintLayout = mediaBrowserTvItemBinding.container;
            Intrinsics.checkNotNullExpressionValue(focusableConstraintLayout, "container");
            tvAdapterUtils.itemFocusChange(z, itemSize, focusableConstraintLayout, false, new FileTvItemAdapter$MediaItemTVViewHolder$3$1$1(fileTvItemAdapter, mediaItemTVViewHolder, mediaBrowserTvItemBinding));
        }

        public void recycle() {
            BitmapDrawable access$getDefaultCover$p = this.this$0.defaultCover;
            if (access$getDefaultCover$p != null) {
                ((MediaBrowserTvItemBinding) getBinding()).setCover(access$getDefaultCover$p);
            }
            ((MediaBrowserTvItemBinding) getBinding()).title.setText("");
            ((MediaBrowserTvItemBinding) getBinding()).subtitle.setText("");
            ((MediaBrowserTvItemBinding) getBinding()).mediaCover.resetFade();
        }

        /* JADX WARNING: Removed duplicated region for block: B:37:0x00eb  */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x0104  */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x010c  */
        /* JADX WARNING: Removed duplicated region for block: B:43:0x0125  */
        /* JADX WARNING: Removed duplicated region for block: B:44:0x0128  */
        /* JADX WARNING: Removed duplicated region for block: B:47:0x0139  */
        /* JADX WARNING: Removed duplicated region for block: B:48:0x013c  */
        /* JADX WARNING: Removed duplicated region for block: B:51:0x0150  */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x0153  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setItem(org.videolan.medialibrary.media.MediaLibraryItem r17) {
            /*
                r16 = this;
                r0 = r16
                r1 = r17
                androidx.databinding.ViewDataBinding r2 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r2 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r2
                r2.setItem(r1)
                if (r1 == 0) goto L_0x0014
                java.lang.String r2 = r17.getDescription()
                goto L_0x0015
            L_0x0014:
                r2 = 0
            L_0x0015:
                boolean r3 = r1 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
                java.lang.String r4 = ""
                r6 = 0
                if (r3 == 0) goto L_0x00a5
                r8 = r1
                org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r8
                int r9 = r8.getType()
                if (r9 != 0) goto L_0x00a5
                int r2 = r8.getWidth()
                int r9 = r8.getHeight()
                java.lang.String r2 = org.videolan.vlc.util.KextensionsKt.generateResolutionClass(r2, r9)
                if (r2 != 0) goto L_0x0035
                r2 = r4
            L_0x0035:
                java.lang.String r9 = r8.getDescription()
                if (r9 == 0) goto L_0x0048
                java.lang.CharSequence r9 = (java.lang.CharSequence) r9
                int r9 = r9.length()
                if (r9 <= 0) goto L_0x0048
                java.lang.String r4 = r8.getDescription()
                goto L_0x006d
            L_0x0048:
                long r9 = r8.getTime()
                int r11 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
                if (r11 == 0) goto L_0x0055
                java.lang.String r4 = org.videolan.medialibrary.Tools.getProgressText(r8)
                goto L_0x006d
            L_0x0055:
                long r9 = r8.getTime()
                int r11 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
                if (r11 != 0) goto L_0x006d
                long r9 = r8.getLength()
                int r11 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
                if (r11 == 0) goto L_0x006d
                long r9 = r8.getLength()
                java.lang.String r4 = org.videolan.medialibrary.Tools.millisToString(r9)
            L_0x006d:
                androidx.databinding.ViewDataBinding r9 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r9 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r9
                r9.setBadge(r2)
                long r9 = r8.getSeen()
                long r11 = r8.getLength()
                int r13 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
                if (r13 <= 0) goto L_0x0096
                long r11 = r8.getDisplayTime()
                int r13 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
                if (r13 <= 0) goto L_0x0096
                long r13 = r8.getLength()
                r8 = 1000(0x3e8, float:1.401E-42)
                long r5 = (long) r8
                long r13 = r13 / r5
                int r7 = (int) r13
                long r11 = r11 / r5
                int r5 = (int) r11
                goto L_0x0098
            L_0x0096:
                r5 = 0
                r7 = 0
            L_0x0098:
                androidx.databinding.ViewDataBinding r6 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r6 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r6
                r6.setMax(r7)
                r15 = r4
                r4 = r2
                r2 = r15
                goto L_0x00a8
            L_0x00a5:
                r5 = 0
                r9 = 0
            L_0x00a8:
                androidx.databinding.ViewDataBinding r6 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r6 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r6
                r6.setProgress(r5)
                androidx.databinding.ViewDataBinding r6 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r6 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r6
                r7 = 1
                java.lang.Boolean r8 = java.lang.Boolean.valueOf(r7)
                r6.setIsSquare(r8)
                androidx.databinding.ViewDataBinding r6 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r6 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r6
                r6.setSeen(r9)
                androidx.databinding.ViewDataBinding r6 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r6 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r6
                r6.setDescription(r2)
                boolean r2 = r0.showProtocol
                if (r2 == 0) goto L_0x00e9
                if (r3 == 0) goto L_0x00e9
                androidx.databinding.ViewDataBinding r2 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r2 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r2
                org.videolan.television.ui.FileTvItemAdapter r6 = r0.this$0
                r8 = r1
                org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r8
                java.lang.String r6 = r6.getProtocol(r8)
                r2.setProtocol(r6)
            L_0x00e9:
                if (r3 == 0) goto L_0x0104
                androidx.databinding.ViewDataBinding r2 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r2 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r2
                android.view.View r2 = r2.getRoot()
                android.content.Context r2 = r2.getContext()
                org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
                int r1 = r1.getType()
                android.graphics.drawable.BitmapDrawable r1 = org.videolan.vlc.gui.helpers.ImageLoaderKt.getMediaIconDrawable(r2, r1, r7)
                goto L_0x010a
            L_0x0104:
                org.videolan.television.ui.FileTvItemAdapter r1 = r0.this$0
                android.graphics.drawable.BitmapDrawable r1 = r1.defaultCover
            L_0x010a:
                if (r1 == 0) goto L_0x0115
                androidx.databinding.ViewDataBinding r2 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r2 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r2
                r2.setCover(r1)
            L_0x0115:
                androidx.databinding.ViewDataBinding r1 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r1 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r1
                androidx.appcompat.widget.AppCompatImageView r1 = r1.mlItemSeen
                r2 = 8
                r6 = 0
                int r3 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
                if (r3 != 0) goto L_0x0128
                r3 = 8
                goto L_0x0129
            L_0x0128:
                r3 = 0
            L_0x0129:
                r1.setVisibility(r3)
                androidx.databinding.ViewDataBinding r1 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r1 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r1
                android.widget.ProgressBar r1 = r1.progressBar
                long r8 = (long) r5
                int r3 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
                if (r3 > 0) goto L_0x013c
                r3 = 8
                goto L_0x013d
            L_0x013c:
                r3 = 0
            L_0x013d:
                r1.setVisibility(r3)
                androidx.databinding.ViewDataBinding r1 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemBinding r1 = (org.videolan.television.databinding.MediaBrowserTvItemBinding) r1
                android.widget.TextView r1 = r1.badgeTV
                java.lang.CharSequence r4 = (java.lang.CharSequence) r4
                boolean r3 = kotlin.text.StringsKt.isBlank(r4)
                if (r3 == 0) goto L_0x0153
                r5 = 8
                goto L_0x0154
            L_0x0153:
                r5 = 0
            L_0x0154:
                r1.setVisibility(r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.FileTvItemAdapter.MediaItemTVViewHolder.setItem(org.videolan.medialibrary.media.MediaLibraryItem):void");
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\bH\u0016J\u0012\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u0006H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/videolan/television/ui/FileTvItemAdapter$MediaItemTVListViewHolder;", "Lorg/videolan/television/ui/MediaTvItemAdapter$AbstractMediaItemViewHolder;", "Lorg/videolan/television/databinding/MediaBrowserTvItemListBinding;", "binding", "eventsHandler", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "showProtocol", "", "(Lorg/videolan/television/ui/FileTvItemAdapter;Lorg/videolan/television/databinding/MediaBrowserTvItemListBinding;Lorg/videolan/vlc/interfaces/IEventsHandler;Z)V", "getEventsHandler", "()Lorg/videolan/vlc/interfaces/IEventsHandler;", "getItem", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "layoutPosition", "", "getView", "Lorg/videolan/television/ui/FocusableConstraintLayout;", "recycle", "", "setCoverlay", "selected", "setItem", "item", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FileTvItemAdapter.kt */
    public final class MediaItemTVListViewHolder extends MediaTvItemAdapter.AbstractMediaItemViewHolder<MediaBrowserTvItemListBinding> {
        private final IEventsHandler<MediaLibraryItem> eventsHandler;
        private final boolean showProtocol;
        final /* synthetic */ FileTvItemAdapter this$0;

        public void setCoverlay(boolean z) {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MediaItemTVListViewHolder(FileTvItemAdapter fileTvItemAdapter, MediaBrowserTvItemListBinding mediaBrowserTvItemListBinding, IEventsHandler<MediaLibraryItem> iEventsHandler, boolean z) {
            super(mediaBrowserTvItemListBinding);
            Intrinsics.checkNotNullParameter(mediaBrowserTvItemListBinding, "binding");
            Intrinsics.checkNotNullParameter(iEventsHandler, "eventsHandler");
            this.this$0 = fileTvItemAdapter;
            this.eventsHandler = iEventsHandler;
            this.showProtocol = z;
            mediaBrowserTvItemListBinding.setHolder(this);
            mediaBrowserTvItemListBinding.setIsPresent(true);
            mediaBrowserTvItemListBinding.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            BitmapDrawable access$getDefaultCover$p = fileTvItemAdapter.defaultCover;
            if (access$getDefaultCover$p != null) {
                mediaBrowserTvItemListBinding.setCover(access$getDefaultCover$p);
            }
            if (AndroidUtil.isMarshMallowOrLater) {
                this.itemView.setOnContextClickListener(new FileTvItemAdapter$MediaItemTVListViewHolder$$ExternalSyntheticLambda2(this));
            }
            mediaBrowserTvItemListBinding.container.setOnFocusChangeListener(new FileTvItemAdapter$MediaItemTVListViewHolder$$ExternalSyntheticLambda3(fileTvItemAdapter, mediaBrowserTvItemListBinding, this));
            mediaBrowserTvItemListBinding.container.setClipToOutline(true);
            mediaBrowserTvItemListBinding.setShowSeen(fileTvItemAdapter.seenMediaMarkerVisible);
        }

        public IEventsHandler<MediaLibraryItem> getEventsHandler() {
            return this.eventsHandler;
        }

        public MediaWrapper getItem(int i) {
            return (MediaWrapper) this.this$0.getItem(i);
        }

        public FocusableConstraintLayout getView() {
            FocusableConstraintLayout focusableConstraintLayout = ((MediaBrowserTvItemListBinding) getBinding()).container;
            Intrinsics.checkNotNullExpressionValue(focusableConstraintLayout, "container");
            return focusableConstraintLayout;
        }

        /* access modifiers changed from: private */
        public static final boolean _init_$lambda$1(MediaItemTVListViewHolder mediaItemTVListViewHolder, View view) {
            Intrinsics.checkNotNullParameter(mediaItemTVListViewHolder, "this$0");
            Intrinsics.checkNotNull(view);
            mediaItemTVListViewHolder.onMoreClick(view);
            return true;
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$2(FileTvItemAdapter fileTvItemAdapter, MediaBrowserTvItemListBinding mediaBrowserTvItemListBinding, MediaItemTVListViewHolder mediaItemTVListViewHolder, View view, boolean z) {
            Intrinsics.checkNotNullParameter(fileTvItemAdapter, "this$0");
            Intrinsics.checkNotNullParameter(mediaBrowserTvItemListBinding, "$binding");
            Intrinsics.checkNotNullParameter(mediaItemTVListViewHolder, "this$1");
            TvAdapterUtils tvAdapterUtils = TvAdapterUtils.INSTANCE;
            int itemSize = fileTvItemAdapter.getItemSize();
            FocusableConstraintLayout focusableConstraintLayout = mediaBrowserTvItemListBinding.container;
            Intrinsics.checkNotNullExpressionValue(focusableConstraintLayout, "container");
            tvAdapterUtils.itemFocusChange(z, itemSize, focusableConstraintLayout, true, new FileTvItemAdapter$MediaItemTVListViewHolder$3$1(fileTvItemAdapter, mediaItemTVListViewHolder, mediaBrowserTvItemListBinding));
        }

        public void recycle() {
            BitmapDrawable access$getDefaultCover$p = this.this$0.defaultCover;
            if (access$getDefaultCover$p != null) {
                ((MediaBrowserTvItemListBinding) getBinding()).setCover(access$getDefaultCover$p);
            }
            ((MediaBrowserTvItemListBinding) getBinding()).title.setText("");
            ((MediaBrowserTvItemListBinding) getBinding()).subtitle.setText("");
            ((MediaBrowserTvItemListBinding) getBinding()).mediaCover.resetFade();
        }

        /* JADX WARNING: Removed duplicated region for block: B:28:0x00c8  */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x00e1  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x00e9  */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x0102  */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x0105  */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x0116  */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x0119  */
        /* JADX WARNING: Removed duplicated region for block: B:42:0x012d  */
        /* JADX WARNING: Removed duplicated region for block: B:43:0x0130  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setItem(org.videolan.medialibrary.media.MediaLibraryItem r17) {
            /*
                r16 = this;
                r0 = r16
                r1 = r17
                androidx.databinding.ViewDataBinding r2 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r2 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r2
                r2.setItem(r1)
                if (r1 == 0) goto L_0x0014
                java.lang.String r2 = r17.getDescription()
                goto L_0x0015
            L_0x0014:
                r2 = 0
            L_0x0015:
                boolean r3 = r1 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
                java.lang.String r4 = ""
                r5 = 1
                r7 = 0
                if (r3 == 0) goto L_0x0082
                r9 = r1
                org.videolan.medialibrary.interfaces.media.MediaWrapper r9 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r9
                int r10 = r9.getType()
                if (r10 != 0) goto L_0x0082
                int r2 = r9.getWidth()
                int r10 = r9.getHeight()
                java.lang.String r2 = org.videolan.vlc.util.KextensionsKt.generateResolutionClass(r2, r10)
                if (r2 != 0) goto L_0x0036
                goto L_0x0037
            L_0x0036:
                r4 = r2
            L_0x0037:
                long r10 = r9.getTime()
                int r2 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
                if (r2 != 0) goto L_0x0048
                long r10 = r9.getLength()
                java.lang.String r2 = org.videolan.medialibrary.Tools.millisToString(r10)
                goto L_0x004c
            L_0x0048:
                java.lang.String r2 = org.videolan.medialibrary.Tools.getProgressText(r9)
            L_0x004c:
                androidx.databinding.ViewDataBinding r10 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r10 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r10
                r10.setBadge(r4)
                long r10 = r9.getSeen()
                long r12 = r9.getLength()
                int r14 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
                if (r14 <= 0) goto L_0x0075
                long r12 = r9.getDisplayTime()
                int r14 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
                if (r14 <= 0) goto L_0x0075
                long r14 = r9.getLength()
                r9 = 1000(0x3e8, float:1.401E-42)
                long r6 = (long) r9
                long r14 = r14 / r6
                int r8 = (int) r14
                long r12 = r12 / r6
                int r6 = (int) r12
                goto L_0x0077
            L_0x0075:
                r6 = 0
                r8 = 0
            L_0x0077:
                androidx.databinding.ViewDataBinding r7 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r7 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r7
                r7.setMax(r8)
                r7 = 0
                goto L_0x0086
            L_0x0082:
                r6 = 0
                r7 = 1
                r10 = 0
            L_0x0086:
                androidx.databinding.ViewDataBinding r8 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r8 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r8
                r8.setProgress(r6)
                androidx.databinding.ViewDataBinding r8 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r8 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r8
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
                r8.setIsSquare(r7)
                androidx.databinding.ViewDataBinding r7 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r7 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r7
                r7.setSeen(r10)
                androidx.databinding.ViewDataBinding r7 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r7 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r7
                r7.setDescription(r2)
                boolean r2 = r0.showProtocol
                if (r2 == 0) goto L_0x00c6
                if (r3 == 0) goto L_0x00c6
                androidx.databinding.ViewDataBinding r2 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r2 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r2
                org.videolan.television.ui.FileTvItemAdapter r7 = r0.this$0
                r8 = r1
                org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r8
                java.lang.String r7 = r7.getProtocol(r8)
                r2.setProtocol(r7)
            L_0x00c6:
                if (r3 == 0) goto L_0x00e1
                androidx.databinding.ViewDataBinding r2 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r2 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r2
                android.view.View r2 = r2.getRoot()
                android.content.Context r2 = r2.getContext()
                org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
                int r1 = r1.getType()
                android.graphics.drawable.BitmapDrawable r1 = org.videolan.vlc.gui.helpers.ImageLoaderKt.getMediaIconDrawable(r2, r1, r5)
                goto L_0x00e7
            L_0x00e1:
                org.videolan.television.ui.FileTvItemAdapter r1 = r0.this$0
                android.graphics.drawable.BitmapDrawable r1 = r1.defaultCover
            L_0x00e7:
                if (r1 == 0) goto L_0x00f2
                androidx.databinding.ViewDataBinding r2 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r2 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r2
                r2.setCover(r1)
            L_0x00f2:
                androidx.databinding.ViewDataBinding r1 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r1 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r1
                android.widget.ImageView r1 = r1.mlItemSeen
                r2 = 8
                r7 = 0
                int r3 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
                if (r3 != 0) goto L_0x0105
                r3 = 8
                goto L_0x0106
            L_0x0105:
                r3 = 0
            L_0x0106:
                r1.setVisibility(r3)
                androidx.databinding.ViewDataBinding r1 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r1 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r1
                android.widget.ProgressBar r1 = r1.progressBar
                long r5 = (long) r6
                int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r3 > 0) goto L_0x0119
                r3 = 8
                goto L_0x011a
            L_0x0119:
                r3 = 0
            L_0x011a:
                r1.setVisibility(r3)
                androidx.databinding.ViewDataBinding r1 = r16.getBinding()
                org.videolan.television.databinding.MediaBrowserTvItemListBinding r1 = (org.videolan.television.databinding.MediaBrowserTvItemListBinding) r1
                android.widget.TextView r1 = r1.badgeTV
                java.lang.CharSequence r4 = (java.lang.CharSequence) r4
                boolean r3 = kotlin.text.StringsKt.isBlank(r4)
                if (r3 == 0) goto L_0x0130
                r6 = 8
                goto L_0x0131
            L_0x0130:
                r6 = 0
            L_0x0131:
                r1.setVisibility(r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.FileTvItemAdapter.MediaItemTVListViewHolder.setItem(org.videolan.medialibrary.media.MediaLibraryItem):void");
        }
    }
}
