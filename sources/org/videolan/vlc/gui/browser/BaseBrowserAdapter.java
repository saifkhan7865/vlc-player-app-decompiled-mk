package org.videolan.vlc.gui.browser;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.medialibrary.media.Storage;
import org.videolan.resources.AndroidDevices;
import org.videolan.tools.MultiSelectAdapter;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.tools.Settings;
import org.videolan.vlc.databinding.BrowserItemBinding;
import org.videolan.vlc.databinding.BrowserItemSeparatorBinding;
import org.videolan.vlc.databinding.CardBrowserItemBinding;
import org.videolan.vlc.gui.DiffUtilAdapter;
import org.videolan.vlc.gui.helpers.MarqueeViewHolder;
import org.videolan.vlc.gui.helpers.SelectorViewHolder;
import org.videolan.vlc.gui.helpers.TalkbackUtil;
import org.videolan.vlc.gui.helpers.UiToolsKt;
import org.videolan.vlc.gui.helpers.hf.OtgAccessKt;
import org.videolan.vlc.gui.view.FastScroller;
import org.videolan.vlc.gui.view.MiniVisualizer;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.util.LifecycleAwareScheduler;
import org.videolan.vlc.viewmodels.PlaylistModel;

@Metadata(d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0016\u0018\u00002\u0018\u0012\u0004\u0012\u00020\u0002\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00040\u0003R\u00020\u00000\u00012\b\u0012\u0004\u0012\u00020\u00020\u00052\u00020\u0006:\b\u0001\u0001\u0001\u0001B1\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000eJ\u0016\u0010n\u001a\u00020o2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010p\u001a\u00020o2\u0006\u0010q\u001a\u00020r2\u0006\u0010s\u001a\u00020\u0010H\u0016J\u0006\u0010t\u001a\u00020oJ\b\u0010u\u001a\u00020,H\u0014J\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\u00020wJ\u0016\u0010x\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020#2\u0006\u0010y\u001a\u00020\fJ\u0010\u0010z\u001a\u00020\u00022\u0006\u0010{\u001a\u00020\nH\u0016J\b\u0010|\u001a\u00020\nH\u0016J\u0010\u0010}\u001a\u00020\n2\u0006\u0010{\u001a\u00020\nH\u0016J\u0012\u0010~\u001a\u0004\u0018\u00010\u00102\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010\u001a\u00020\fH\u0016J$\u0010\u0001\u001a\u00020o2\u0006\u0010{\u001a\u00020\n2\u0007\u0010\u0001\u001a\u00020\f2\b\u0010\u0001\u001a\u00030\u0001H\u0016J\u0013\u0010\u0001\u001a\u00020o2\b\u0010\u0001\u001a\u00030\u0001H\u0016J\u001f\u0010\u0001\u001a\u00020o2\f\u0010\u0001\u001a\u00070\u0001R\u00020\u00002\u0006\u0010{\u001a\u00020\nH\u0002J$\u0010\u0001\u001a\u00020o2\u0011\u0010\u0001\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003R\u00020\u00002\u0006\u0010{\u001a\u00020\nH\u0016J5\u0010\u0001\u001a\u00020o2\u0011\u0010\u0001\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003R\u00020\u00002\u0006\u0010{\u001a\u00020\n2\u000f\u0010\u0001\u001a\n\u0012\u0005\u0012\u00030\u00010\u0001H\u0016J&\u0010\u0001\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003R\u00020\u00002\b\u0010\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020\nH\u0016J\u0013\u0010\u0001\u001a\u00020o2\b\u0010\u0001\u001a\u00030\u0001H\u0016J\t\u0010\u0001\u001a\u00020oH\u0014J\u001c\u0010\u0001\u001a\u00020o2\u0011\u0010\u0001\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003R\u00020\u0000H\u0016J\u001e\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00020w2\r\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00020wH\u0014J\u0010\u0010\u0001\u001a\u00020o2\u0007\u0010\u0001\u001a\u00020\fJ\u000f\u0010\u0001\u001a\u00020o2\u0006\u0010;\u001a\u00020<R\u0014\u0010\u000f\u001a\u00020\u0010XD¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001d\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u001c\u001a\u0004\b\u001e\u0010\u001aR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R(\u0010$\u001a\u0004\u0018\u00010#2\b\u0010\"\u001a\u0004\u0018\u00010#@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010+\u001a\u00020,¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u001b\u0010/\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\b1\u0010\u001c\u001a\u0004\b0\u0010\u001aR\u001b\u00102\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\b4\u0010\u001c\u001a\u0004\b3\u0010\u001aR\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0014R\u001a\u00106\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u0010\u0010;\u001a\u0004\u0018\u00010<X\u000e¢\u0006\u0002\n\u0000R\u0017\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00020>¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@R\u000e\u0010A\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001b\u0010B\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\bD\u0010\u001c\u001a\u0004\bC\u0010\u001aR\u001b\u0010E\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\bG\u0010\u001c\u001a\u0004\bF\u0010\u001aR\u001b\u0010H\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\bJ\u0010\u001c\u001a\u0004\bI\u0010\u001aR\u001b\u0010K\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\bM\u0010\u001c\u001a\u0004\bL\u0010\u001aR\u001b\u0010N\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\bP\u0010\u001c\u001a\u0004\bO\u0010\u001aR\u001b\u0010Q\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\bS\u0010\u001c\u001a\u0004\bR\u0010\u001aR\u001b\u0010T\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\bV\u0010\u001c\u001a\u0004\bU\u0010\u001aR\u001b\u0010W\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\bY\u0010\u001c\u001a\u0004\bX\u0010\u001aR\u0010\u0010Z\u001a\u0004\u0018\u00010[X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u00108\"\u0004\b]\u0010:R\u000e\u0010^\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001b\u0010_\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\ba\u0010\u001c\u001a\u0004\b`\u0010\u001aR\u001b\u0010b\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\bd\u0010\u001c\u001a\u0004\bc\u0010\u001aR\u001b\u0010e\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\bg\u0010\u001c\u001a\u0004\bf\u0010\u001aR\u001b\u0010h\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\bj\u0010\u001c\u001a\u0004\bi\u0010\u001aR\u001b\u0010k\u001a\u00020\u00188BX\u0002¢\u0006\f\n\u0004\bm\u0010\u001c\u001a\u0004\bl\u0010\u001a¨\u0006\u0001"}, d2 = {"Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter;", "Lorg/videolan/vlc/gui/DiffUtilAdapter;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter$ViewHolder;", "Landroidx/databinding/ViewDataBinding;", "Lorg/videolan/tools/MultiSelectAdapter;", "Lorg/videolan/vlc/gui/view/FastScroller$SeparatedAdapter;", "browserContainer", "Lorg/videolan/vlc/gui/browser/BrowserContainer;", "sort", "", "asc", "", "forMain", "(Lorg/videolan/vlc/gui/browser/BrowserContainer;IZZ)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "getAsc", "()Z", "setAsc", "(Z)V", "audioDrawable", "Landroid/graphics/drawable/BitmapDrawable;", "getAudioDrawable", "()Landroid/graphics/drawable/BitmapDrawable;", "audioDrawable$delegate", "Lkotlin/Lazy;", "audioDrawableBig", "getAudioDrawableBig", "audioDrawableBig$delegate", "getBrowserContainer", "()Lorg/videolan/vlc/gui/browser/BrowserContainer;", "media", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "currentMedia", "getCurrentMedia", "()Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "setCurrentMedia", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)V", "currentPlayingVisu", "Lorg/videolan/vlc/gui/view/MiniVisualizer;", "diffCallback", "Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter$BrowserDiffCallback;", "getDiffCallback", "()Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter$BrowserDiffCallback;", "folderDrawable", "getFolderDrawable", "folderDrawable$delegate", "folderDrawableBig", "getFolderDrawableBig", "folderDrawableBig$delegate", "getForMain", "mediaCount", "getMediaCount$vlc_android_release", "()I", "setMediaCount$vlc_android_release", "(I)V", "model", "Lorg/videolan/vlc/viewmodels/PlaylistModel;", "multiSelectHelper", "Lorg/videolan/tools/MultiSelectHelper;", "getMultiSelectHelper", "()Lorg/videolan/tools/MultiSelectHelper;", "networkRoot", "qaDownloadDrawable", "getQaDownloadDrawable", "qaDownloadDrawable$delegate", "qaDownloadDrawableBig", "getQaDownloadDrawableBig", "qaDownloadDrawableBig$delegate", "qaMoviesDrawable", "getQaMoviesDrawable", "qaMoviesDrawable$delegate", "qaMoviesDrawableBig", "getQaMoviesDrawableBig", "qaMoviesDrawableBig$delegate", "qaMusicDrawable", "getQaMusicDrawable", "qaMusicDrawable$delegate", "qaMusicDrawableBig", "getQaMusicDrawableBig", "qaMusicDrawableBig$delegate", "qaPodcastsDrawable", "getQaPodcastsDrawable", "qaPodcastsDrawable$delegate", "qaPodcastsDrawableBig", "getQaPodcastsDrawableBig", "qaPodcastsDrawableBig$delegate", "scheduler", "Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "getSort", "setSort", "specialIcons", "subtitleDrawable", "getSubtitleDrawable", "subtitleDrawable$delegate", "subtitleDrawableBig", "getSubtitleDrawableBig", "subtitleDrawableBig$delegate", "unknownDrawable", "getUnknownDrawable", "unknownDrawable$delegate", "videoDrawable", "getVideoDrawable", "videoDrawable$delegate", "videoDrawableBig", "getVideoDrawableBig", "videoDrawableBig$delegate", "changeSort", "", "checkBoxAction", "v", "Landroid/view/View;", "mrl", "clear", "createCB", "getAll", "", "getIcon", "specialFolders", "getItem", "position", "getItemCount", "getItemViewType", "getProtocol", "hasSections", "itemFocusChanged", "hasFocus", "bindingContainer", "Lorg/videolan/vlc/gui/browser/BrowserItemBindingContainer;", "onAttachedToRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onBindMediaViewHolder", "vh", "Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter$MediaViewHolder;", "onBindViewHolder", "holder", "payloads", "", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onDetachedFromRecyclerView", "onUpdateFinished", "onViewRecycled", "prepareList", "list", "setCurrentlyPlaying", "playing", "setModel", "BrowserDiffCallback", "MediaViewHolder", "SeparatorViewHolder", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseBrowserAdapter.kt */
public class BaseBrowserAdapter extends DiffUtilAdapter<MediaLibraryItem, ViewHolder<ViewDataBinding>> implements MultiSelectAdapter<MediaLibraryItem>, FastScroller.SeparatedAdapter {
    private final String TAG;
    private boolean asc;
    private final Lazy audioDrawable$delegate;
    private final Lazy audioDrawableBig$delegate;
    private final BrowserContainer<MediaLibraryItem> browserContainer;
    private MediaWrapper currentMedia;
    private MiniVisualizer currentPlayingVisu;
    private final BrowserDiffCallback diffCallback;
    private final Lazy folderDrawable$delegate;
    private final Lazy folderDrawableBig$delegate;
    private final boolean forMain;
    private int mediaCount;
    private PlaylistModel model;
    private final MultiSelectHelper<MediaLibraryItem> multiSelectHelper;
    private boolean networkRoot;
    private final Lazy qaDownloadDrawable$delegate;
    private final Lazy qaDownloadDrawableBig$delegate;
    private final Lazy qaMoviesDrawable$delegate;
    private final Lazy qaMoviesDrawableBig$delegate;
    private final Lazy qaMusicDrawable$delegate;
    private final Lazy qaMusicDrawableBig$delegate;
    private final Lazy qaPodcastsDrawable$delegate;
    private final Lazy qaPodcastsDrawableBig$delegate;
    private LifecycleAwareScheduler scheduler;
    private int sort;
    private boolean specialIcons;
    private final Lazy subtitleDrawable$delegate;
    private final Lazy subtitleDrawableBig$delegate;
    private final Lazy unknownDrawable$delegate;
    private final Lazy videoDrawable$delegate;
    private final Lazy videoDrawableBig$delegate;

    public void checkBoxAction(View view, String str) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(str, "mrl");
    }

    public boolean hasSections() {
        return false;
    }

    public void itemFocusChanged(int i, boolean z, BrowserItemBindingContainer browserItemBindingContainer) {
        Intrinsics.checkNotNullParameter(browserItemBindingContainer, "bindingContainer");
    }

    public BaseBrowserAdapter(BrowserContainer<MediaLibraryItem> browserContainer2, int i, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(browserContainer2, "browserContainer");
        this.browserContainer = browserContainer2;
        this.sort = i;
        this.asc = z;
        this.forMain = z2;
        this.TAG = "VLC/BaseBrowserAdapter";
        boolean z3 = false;
        this.multiSelectHelper = new MultiSelectHelper<>(this, 0);
        this.folderDrawable$delegate = LazyKt.lazy(new BaseBrowserAdapter$folderDrawable$2(this));
        this.folderDrawableBig$delegate = LazyKt.lazy(new BaseBrowserAdapter$folderDrawableBig$2(this));
        this.audioDrawable$delegate = LazyKt.lazy(new BaseBrowserAdapter$audioDrawable$2(this));
        this.audioDrawableBig$delegate = LazyKt.lazy(new BaseBrowserAdapter$audioDrawableBig$2(this));
        this.videoDrawable$delegate = LazyKt.lazy(new BaseBrowserAdapter$videoDrawable$2(this));
        this.videoDrawableBig$delegate = LazyKt.lazy(new BaseBrowserAdapter$videoDrawableBig$2(this));
        this.subtitleDrawable$delegate = LazyKt.lazy(new BaseBrowserAdapter$subtitleDrawable$2(this));
        this.subtitleDrawableBig$delegate = LazyKt.lazy(new BaseBrowserAdapter$subtitleDrawableBig$2(this));
        this.unknownDrawable$delegate = LazyKt.lazy(new BaseBrowserAdapter$unknownDrawable$2(this));
        this.qaMoviesDrawable$delegate = LazyKt.lazy(new BaseBrowserAdapter$qaMoviesDrawable$2(this));
        this.qaMoviesDrawableBig$delegate = LazyKt.lazy(new BaseBrowserAdapter$qaMoviesDrawableBig$2(this));
        this.qaMusicDrawable$delegate = LazyKt.lazy(new BaseBrowserAdapter$qaMusicDrawable$2(this));
        this.qaMusicDrawableBig$delegate = LazyKt.lazy(new BaseBrowserAdapter$qaMusicDrawableBig$2(this));
        this.qaPodcastsDrawable$delegate = LazyKt.lazy(new BaseBrowserAdapter$qaPodcastsDrawable$2(this));
        this.qaPodcastsDrawableBig$delegate = LazyKt.lazy(new BaseBrowserAdapter$qaPodcastsDrawableBig$2(this));
        this.qaDownloadDrawable$delegate = LazyKt.lazy(new BaseBrowserAdapter$qaDownloadDrawable$2(this));
        this.qaDownloadDrawableBig$delegate = LazyKt.lazy(new BaseBrowserAdapter$qaDownloadDrawableBig$2(this));
        BrowserDiffCallback browserDiffCallback = new BrowserDiffCallback();
        this.diffCallback = browserDiffCallback;
        boolean isRootDirectory = browserContainer2.isRootDirectory();
        boolean isFile = browserContainer2.isFile();
        boolean z4 = isRootDirectory && isFile;
        this.networkRoot = isRootDirectory && browserContainer2.isNetwork();
        String mrl = browserContainer2.getMrl();
        if (z4 || (isFile && mrl != null && StringsKt.endsWith$default(mrl, AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY(), false, 2, (Object) null))) {
            z3 = true;
        }
        this.specialIcons = z3;
        browserContainer2.containerActivity().getResources();
        browserDiffCallback.setOldSort(this.sort);
        browserDiffCallback.setNewSort(this.sort);
        browserDiffCallback.setOldAsc(this.asc);
        browserDiffCallback.setNewAsc(this.asc);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BaseBrowserAdapter(BrowserContainer browserContainer2, int i, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(browserContainer2, (i2 & 2) != 0 ? 10 : i, (i2 & 4) != 0 ? true : z, (i2 & 8) != 0 ? true : z2);
    }

    public final boolean getAsc() {
        return this.asc;
    }

    public final BrowserContainer<MediaLibraryItem> getBrowserContainer() {
        return this.browserContainer;
    }

    public final boolean getForMain() {
        return this.forMain;
    }

    public final int getSort() {
        return this.sort;
    }

    public final void setAsc(boolean z) {
        this.asc = z;
    }

    public final void setSort(int i) {
        this.sort = i;
    }

    /* access modifiers changed from: protected */
    public final String getTAG() {
        return this.TAG;
    }

    public final MultiSelectHelper<MediaLibraryItem> getMultiSelectHelper() {
        return this.multiSelectHelper;
    }

    private final BitmapDrawable getFolderDrawable() {
        return (BitmapDrawable) this.folderDrawable$delegate.getValue();
    }

    private final BitmapDrawable getFolderDrawableBig() {
        return (BitmapDrawable) this.folderDrawableBig$delegate.getValue();
    }

    private final BitmapDrawable getAudioDrawable() {
        return (BitmapDrawable) this.audioDrawable$delegate.getValue();
    }

    private final BitmapDrawable getAudioDrawableBig() {
        return (BitmapDrawable) this.audioDrawableBig$delegate.getValue();
    }

    private final BitmapDrawable getVideoDrawable() {
        return (BitmapDrawable) this.videoDrawable$delegate.getValue();
    }

    private final BitmapDrawable getVideoDrawableBig() {
        return (BitmapDrawable) this.videoDrawableBig$delegate.getValue();
    }

    private final BitmapDrawable getSubtitleDrawable() {
        return (BitmapDrawable) this.subtitleDrawable$delegate.getValue();
    }

    private final BitmapDrawable getSubtitleDrawableBig() {
        return (BitmapDrawable) this.subtitleDrawableBig$delegate.getValue();
    }

    private final BitmapDrawable getUnknownDrawable() {
        return (BitmapDrawable) this.unknownDrawable$delegate.getValue();
    }

    private final BitmapDrawable getQaMoviesDrawable() {
        return (BitmapDrawable) this.qaMoviesDrawable$delegate.getValue();
    }

    private final BitmapDrawable getQaMoviesDrawableBig() {
        return (BitmapDrawable) this.qaMoviesDrawableBig$delegate.getValue();
    }

    private final BitmapDrawable getQaMusicDrawable() {
        return (BitmapDrawable) this.qaMusicDrawable$delegate.getValue();
    }

    private final BitmapDrawable getQaMusicDrawableBig() {
        return (BitmapDrawable) this.qaMusicDrawableBig$delegate.getValue();
    }

    private final BitmapDrawable getQaPodcastsDrawable() {
        return (BitmapDrawable) this.qaPodcastsDrawable$delegate.getValue();
    }

    private final BitmapDrawable getQaPodcastsDrawableBig() {
        return (BitmapDrawable) this.qaPodcastsDrawableBig$delegate.getValue();
    }

    private final BitmapDrawable getQaDownloadDrawable() {
        return (BitmapDrawable) this.qaDownloadDrawable$delegate.getValue();
    }

    private final BitmapDrawable getQaDownloadDrawableBig() {
        return (BitmapDrawable) this.qaDownloadDrawableBig$delegate.getValue();
    }

    public final int getMediaCount$vlc_android_release() {
        return this.mediaCount;
    }

    public final void setMediaCount$vlc_android_release(int i) {
        this.mediaCount = i;
    }

    public final BrowserDiffCallback getDiffCallback() {
        return this.diffCallback;
    }

    public final MediaWrapper getCurrentMedia() {
        return this.currentMedia;
    }

    public final void setCurrentMedia(MediaWrapper mediaWrapper) {
        if (!Intrinsics.areEqual((Object) mediaWrapper, (Object) this.currentMedia)) {
            MediaWrapper mediaWrapper2 = this.currentMedia;
            this.currentMedia = mediaWrapper;
            if (mediaWrapper2 != null) {
                notifyItemChanged(getDataset().indexOf(mediaWrapper2));
            }
            if (mediaWrapper != null) {
                notifyItemChanged(getDataset().indexOf(mediaWrapper));
            }
        }
    }

    public final void changeSort(int i, boolean z) {
        BrowserDiffCallback browserDiffCallback = this.diffCallback;
        browserDiffCallback.setOldSort(browserDiffCallback.getNewSort());
        BrowserDiffCallback browserDiffCallback2 = this.diffCallback;
        browserDiffCallback2.setOldAsc(browserDiffCallback2.getNewAsc());
        this.sort = i;
        this.asc = z;
        this.diffCallback.setNewAsc(z);
    }

    public ViewHolder<ViewDataBinding> onCreateViewHolder(ViewGroup viewGroup, int i) {
        BrowserItemBindingContainer browserItemBindingContainer;
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 32 || i == 128) {
            if (this.browserContainer.getInCards()) {
                CardBrowserItemBinding inflate = CardBrowserItemBinding.inflate(from, viewGroup, false);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
                browserItemBindingContainer = new BrowserItemBindingContainer(inflate);
            } else {
                BrowserItemBinding inflate2 = BrowserItemBinding.inflate(from, viewGroup, false);
                Intrinsics.checkNotNullExpressionValue(inflate2, "inflate(...)");
                browserItemBindingContainer = new BrowserItemBindingContainer(inflate2);
            }
            return new MediaViewHolder(this, browserItemBindingContainer);
        }
        BrowserItemSeparatorBinding inflate3 = BrowserItemSeparatorBinding.inflate(from, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate3, "inflate(...)");
        return new SeparatorViewHolder(this, inflate3);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        if (Settings.INSTANCE.getListTitleEllipsize() == 0 || Settings.INSTANCE.getListTitleEllipsize() == 4) {
            this.scheduler = UiToolsKt.enableMarqueeEffect(recyclerView);
        }
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        LifecycleAwareScheduler lifecycleAwareScheduler = this.scheduler;
        if (lifecycleAwareScheduler != null) {
            lifecycleAwareScheduler.cancelAction(UiToolsKt.MARQUEE_ACTION);
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

    public void onBindViewHolder(ViewHolder<ViewDataBinding> viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        if (getItemViewType(i) == 32) {
            onBindMediaViewHolder((MediaViewHolder) viewHolder, i);
        } else {
            ((BrowserItemSeparatorBinding) ((SeparatorViewHolder) viewHolder).getBinding()).setTitle(((MediaLibraryItem) getDataset().get(i)).getTitle());
        }
        MediaViewHolder mediaViewHolder = (MediaViewHolder) viewHolder;
        itemFocusChanged(i, false, mediaViewHolder.getBindingContainer());
        if (!this.forMain) {
            mediaViewHolder.getBindingContainer().setupGrid();
        }
    }

    public void onBindViewHolder(ViewHolder<ViewDataBinding> viewHolder, int i, List<Object> list) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        Intrinsics.checkNotNullParameter(list, "payloads");
        if (list.isEmpty()) {
            onBindViewHolder(viewHolder, i);
        } else if (Intrinsics.areEqual(list.get(0), (Object) BaseBrowserAdapterKt.UPDATE_PROGRESS)) {
            MediaLibraryItem item = getItem(i);
            Intrinsics.checkNotNull(item, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
            MediaWrapper mediaWrapper = (MediaWrapper) item;
            long j = (long) 1000;
            int length = (int) (mediaWrapper.getLength() / j);
            int displayTime = (int) (mediaWrapper.getDisplayTime() / j);
            MediaViewHolder mediaViewHolder = (MediaViewHolder) viewHolder;
            BrowserItemBindingContainer bindingContainer = mediaViewHolder.getBindingContainer();
            Context context = mediaViewHolder.getBindingContainer().getContainer().getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            bindingContainer.setProgress(context, displayTime, length);
            boolean z = true;
            if (mediaWrapper.getType() != 1) {
                BrowserItemBindingContainer bindingContainer2 = mediaViewHolder.getBindingContainer();
                Context context2 = mediaViewHolder.getBindingContainer().getContainer().getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                if (mediaWrapper.getPlayCount() <= 0) {
                    z = false;
                }
                bindingContainer2.setIsPlayed(context2, z);
            }
        } else if (list.get(0) instanceof CharSequence) {
            MediaViewHolder mediaViewHolder2 = (MediaViewHolder) viewHolder;
            mediaViewHolder2.getBindingContainer().getText().setVisibility(0);
            TextView text = mediaViewHolder2.getBindingContainer().getText();
            Object obj = list.get(0);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.CharSequence");
            Context context3 = mediaViewHolder2.getBindingContainer().getText().getContext();
            Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
            text.setText(KextensionsKt.getDescriptionSpan((CharSequence) obj, context3));
            MediaLibraryItem item2 = getItem(i);
            Intrinsics.checkNotNull(item2, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
            MediaWrapper mediaWrapper2 = (MediaWrapper) item2;
            View container = mediaViewHolder2.getBindingContainer().getContainer();
            TalkbackUtil talkbackUtil = TalkbackUtil.INSTANCE;
            Context context4 = viewHolder.getBinding().getRoot().getContext();
            Intrinsics.checkNotNullExpressionValue(context4, "getContext(...)");
            container.setContentDescription(talkbackUtil.getDir(context4, mediaWrapper2, mediaWrapper2.hasStateFlags(2)));
        } else if (list.get(0) instanceof Integer) {
            Object obj2 = list.get(0);
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Int");
            if (((Integer) obj2).intValue() == 0) {
                viewHolder.selectView(this.multiSelectHelper.isSelected(i));
            }
        }
        itemFocusChanged(i, false, ((MediaViewHolder) viewHolder).getBindingContainer());
    }

    private final void onBindMediaViewHolder(MediaViewHolder mediaViewHolder, int i) {
        String str;
        String protocol;
        MediaLibraryItem item = getItem(i);
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
        MediaWrapper mediaWrapper = (MediaWrapper) item;
        boolean hasStateFlags = mediaWrapper.hasStateFlags(2);
        long j = (long) 1000;
        int length = (int) (mediaWrapper.getLength() / j);
        BrowserItemBindingContainer bindingContainer = mediaViewHolder.getBindingContainer();
        Context context = mediaViewHolder.getBindingContainer().getContainer().getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        bindingContainer.setProgress(context, (int) (mediaWrapper.getDisplayTime() / j), length);
        boolean z = true;
        if (mediaWrapper.getType() != 1) {
            BrowserItemBindingContainer bindingContainer2 = mediaViewHolder.getBindingContainer();
            Context context2 = mediaViewHolder.getBindingContainer().getContainer().getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
            bindingContainer2.setIsPlayed(context2, mediaWrapper.getPlayCount() > 0);
        }
        mediaViewHolder.getBindingContainer().setItem(mediaWrapper);
        mediaViewHolder.getBindingContainer().setIsFavorite(hasStateFlags);
        Uri uri = mediaWrapper.getUri();
        if (uri == null || (str = uri.getScheme()) == null) {
            str = "";
        }
        BrowserItemBindingContainer bindingContainer3 = mediaViewHolder.getBindingContainer();
        if ((this.networkRoot && !hasStateFlags) || Intrinsics.areEqual((Object) "content", (Object) str) || Intrinsics.areEqual((Object) OtgAccessKt.OTG_SCHEME, (Object) str) || this.multiSelectHelper.getInActionMode()) {
            z = false;
        }
        bindingContainer3.setHasContextMenu(z);
        BrowserItemBindingContainer bindingContainer4 = mediaViewHolder.getBindingContainer();
        int i2 = this.sort;
        bindingContainer4.setFileName(((i2 == 10 || i2 == 0) && mediaWrapper.getType() != 3 && Intrinsics.areEqual((Object) "file", (Object) str)) ? mediaWrapper.getFileName() : null);
        if (this.networkRoot || (hasStateFlags && (protocol = getProtocol(mediaWrapper)) != null && !StringsKt.contains$default((CharSequence) protocol, (CharSequence) "file", false, 2, (Object) null))) {
            mediaViewHolder.getBindingContainer().setProtocol(getProtocol(mediaWrapper));
        }
        mediaViewHolder.getBindingContainer().setCover(getIcon(mediaWrapper, this.specialIcons));
        mediaViewHolder.selectView(this.multiSelectHelper.isSelected(i));
        itemFocusChanged(i, false, mediaViewHolder.getBindingContainer());
        if (Intrinsics.areEqual((Object) this.currentMedia, (Object) mediaWrapper)) {
            PlaylistModel playlistModel = this.model;
            if (playlistModel == null || playlistModel.getPlaying()) {
                mediaViewHolder.getBindingContainer().startVisu();
            } else {
                mediaViewHolder.getBindingContainer().stopVisu();
            }
            mediaViewHolder.getBindingContainer().setVisuVisibility(0);
            this.currentPlayingVisu = mediaViewHolder.getBindingContainer().getVisu();
            return;
        }
        mediaViewHolder.getBindingContainer().stopVisu();
        mediaViewHolder.getBindingContainer().setVisuVisibility(4);
    }

    public void onViewRecycled(ViewHolder<ViewDataBinding> viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        LifecycleAwareScheduler lifecycleAwareScheduler = this.scheduler;
        if (lifecycleAwareScheduler != null) {
            lifecycleAwareScheduler.cancelAction(UiToolsKt.MARQUEE_ACTION);
        }
        super.onViewRecycled(viewHolder);
        TextView titleView = viewHolder.getTitleView();
        if (titleView != null) {
            titleView.setSelected(false);
        }
    }

    public int getItemCount() {
        return getDataset().size();
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b¦\u0004\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\u0013"}, d2 = {"Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter$ViewHolder;", "T", "Landroidx/databinding/ViewDataBinding;", "Lorg/videolan/vlc/gui/helpers/SelectorViewHolder;", "Lorg/videolan/vlc/gui/helpers/MarqueeViewHolder;", "binding", "(Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter;Landroidx/databinding/ViewDataBinding;)V", "getType", "", "onBanClick", "", "v", "Landroid/view/View;", "onCheckBoxClick", "onClick", "onImageClick", "onLongClick", "", "onMoreClick", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BaseBrowserAdapter.kt */
    public abstract class ViewHolder<T extends ViewDataBinding> extends SelectorViewHolder<T> implements MarqueeViewHolder {
        final /* synthetic */ BaseBrowserAdapter this$0;

        public abstract int getType();

        public void onBanClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
        }

        public void onCheckBoxClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
        }

        public void onClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
        }

        public void onImageClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
        }

        public boolean onLongClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            return false;
        }

        public void onMoreClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(BaseBrowserAdapter baseBrowserAdapter, T t) {
            super(t);
            Intrinsics.checkNotNullParameter(t, "binding");
            this.this$0 = baseBrowserAdapter;
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0004\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001R\u00020\u00032\u00020\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0014J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010 \u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u0017H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006#"}, d2 = {"Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter$MediaViewHolder;", "Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter$ViewHolder;", "Landroidx/databinding/ViewDataBinding;", "Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter;", "Lorg/videolan/vlc/gui/helpers/MarqueeViewHolder;", "bindingContainer", "Lorg/videolan/vlc/gui/browser/BrowserItemBindingContainer;", "(Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter;Lorg/videolan/vlc/gui/browser/BrowserItemBindingContainer;)V", "getBindingContainer", "()Lorg/videolan/vlc/gui/browser/BrowserItemBindingContainer;", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "setJob", "(Lkotlinx/coroutines/Job;)V", "titleView", "Landroid/widget/TextView;", "getTitleView", "()Landroid/widget/TextView;", "getType", "", "isSelected", "", "onBanClick", "", "v", "Landroid/view/View;", "onCheckBoxClick", "onClick", "onImageClick", "onLongClick", "onMoreClick", "selectView", "selected", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BaseBrowserAdapter.kt */
    public final class MediaViewHolder extends ViewHolder<ViewDataBinding> implements MarqueeViewHolder {
        private final BrowserItemBindingContainer bindingContainer;
        private Job job;
        final /* synthetic */ BaseBrowserAdapter this$0;
        private final TextView titleView;

        public int getType() {
            return 32;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MediaViewHolder(BaseBrowserAdapter baseBrowserAdapter, BrowserItemBindingContainer browserItemBindingContainer) {
            super(baseBrowserAdapter, browserItemBindingContainer.getBinding());
            Intrinsics.checkNotNullParameter(browserItemBindingContainer, "bindingContainer");
            this.this$0 = baseBrowserAdapter;
            this.bindingContainer = browserItemBindingContainer;
            this.titleView = browserItemBindingContainer.getTitle();
            browserItemBindingContainer.setHolder(this);
            if (AndroidUtil.isMarshMallowOrLater) {
                this.itemView.setOnContextClickListener(new BaseBrowserAdapter$MediaViewHolder$$ExternalSyntheticLambda0(this));
            }
            if (baseBrowserAdapter instanceof FilePickerAdapter) {
                browserItemBindingContainer.getItemIcon().setFocusable(false);
            }
            BaseBrowserAdapter$MediaViewHolder$$ExternalSyntheticLambda1 baseBrowserAdapter$MediaViewHolder$$ExternalSyntheticLambda1 = new BaseBrowserAdapter$MediaViewHolder$$ExternalSyntheticLambda1(baseBrowserAdapter, this);
            browserItemBindingContainer.getBanIcon().setOnFocusChangeListener(baseBrowserAdapter$MediaViewHolder$$ExternalSyntheticLambda1);
            browserItemBindingContainer.getContainer().setOnFocusChangeListener(baseBrowserAdapter$MediaViewHolder$$ExternalSyntheticLambda1);
        }

        public final BrowserItemBindingContainer getBindingContainer() {
            return this.bindingContainer;
        }

        public TextView getTitleView() {
            return this.titleView;
        }

        public final Job getJob() {
            return this.job;
        }

        public final void setJob(Job job2) {
            this.job = job2;
        }

        /* access modifiers changed from: private */
        public static final boolean _init_$lambda$0(MediaViewHolder mediaViewHolder, View view) {
            Intrinsics.checkNotNullParameter(mediaViewHolder, "this$0");
            Intrinsics.checkNotNull(view);
            mediaViewHolder.onMoreClick(view);
            return true;
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$1(BaseBrowserAdapter baseBrowserAdapter, MediaViewHolder mediaViewHolder, View view, boolean z) {
            Intrinsics.checkNotNullParameter(baseBrowserAdapter, "this$0");
            Intrinsics.checkNotNullParameter(mediaViewHolder, "this$1");
            baseBrowserAdapter.itemFocusChanged(mediaViewHolder.getLayoutPosition(), z, mediaViewHolder.bindingContainer);
        }

        public void selectView(boolean z) {
            super.selectView(z);
            this.bindingContainer.getMoreIcon().setVisibility(this.this$0.getMultiSelectHelper().getInActionMode() ? 4 : 0);
        }

        public void onCheckBoxClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            MediaLibraryItem item = this.this$0.getItem(getLayoutPosition());
            if (item instanceof Storage) {
                BaseBrowserAdapter baseBrowserAdapter = this.this$0;
                MediaLibraryItem item2 = baseBrowserAdapter.getItem(getLayoutPosition());
                Intrinsics.checkNotNull(item2, "null cannot be cast to non-null type org.videolan.medialibrary.media.Storage");
                String uri = ((Storage) item2).getUri().toString();
                Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
                baseBrowserAdapter.checkBoxAction(view, uri);
            } else if (item instanceof MediaWrapper) {
                BaseBrowserAdapter baseBrowserAdapter2 = this.this$0;
                MediaLibraryItem item3 = baseBrowserAdapter2.getItem(getLayoutPosition());
                Intrinsics.checkNotNull(item3, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
                String uri2 = ((MediaWrapper) item3).getUri().toString();
                Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
                baseBrowserAdapter2.checkBoxAction(view, uri2);
            }
        }

        public void onClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            int layoutPosition = getLayoutPosition();
            if (layoutPosition < this.this$0.getDataset().size() && layoutPosition >= 0) {
                this.this$0.getBrowserContainer().onClick(view, layoutPosition, this.this$0.getDataset().get(layoutPosition));
            }
        }

        public void onImageClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            int layoutPosition = getLayoutPosition();
            if (layoutPosition < this.this$0.getDataset().size() && layoutPosition >= 0) {
                this.this$0.getBrowserContainer().onImageClick(view, layoutPosition, this.this$0.getDataset().get(layoutPosition));
            }
        }

        public void onMoreClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            int layoutPosition = getLayoutPosition();
            if (layoutPosition < this.this$0.getDataset().size() && layoutPosition >= 0) {
                this.this$0.getBrowserContainer().onCtxClick(view, layoutPosition, this.this$0.getDataset().get(layoutPosition));
            }
        }

        public void onBanClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            int layoutPosition = getLayoutPosition();
            this.this$0.getBrowserContainer().onLongClick(view, layoutPosition, this.this$0.getDataset().get(layoutPosition));
        }

        public boolean onLongClick(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            int layoutPosition = getLayoutPosition();
            if (this.this$0.getItem(layoutPosition).getItemType() == 128 && Settings.INSTANCE.getShowTvUi()) {
                this.bindingContainer.getBrowserCheckbox().toggle();
                onCheckBoxClick(this.bindingContainer.getBrowserCheckbox());
                return true;
            } else if (layoutPosition >= this.this$0.getDataset().size() || layoutPosition < 0 || !this.this$0.getBrowserContainer().onLongClick(view, layoutPosition, this.this$0.getDataset().get(layoutPosition))) {
                return false;
            } else {
                return true;
            }
        }

        /* access modifiers changed from: protected */
        public boolean isSelected() {
            return this.this$0.getMultiSelectHelper().isSelected(getLayoutPosition());
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0004\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001R\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter$SeparatorViewHolder;", "Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter$ViewHolder;", "Lorg/videolan/vlc/databinding/BrowserItemSeparatorBinding;", "Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter;", "binding", "(Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter;Lorg/videolan/vlc/databinding/BrowserItemSeparatorBinding;)V", "titleView", "Landroid/widget/TextView;", "getTitleView", "()Landroid/widget/TextView;", "getType", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BaseBrowserAdapter.kt */
    private final class SeparatorViewHolder extends ViewHolder<BrowserItemSeparatorBinding> {
        final /* synthetic */ BaseBrowserAdapter this$0;
        private final TextView titleView;

        public int getType() {
            return 64;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SeparatorViewHolder(BaseBrowserAdapter baseBrowserAdapter, BrowserItemSeparatorBinding browserItemSeparatorBinding) {
            super(baseBrowserAdapter, browserItemSeparatorBinding);
            Intrinsics.checkNotNullParameter(browserItemSeparatorBinding, "binding");
            this.this$0 = baseBrowserAdapter;
        }

        public TextView getTitleView() {
            return this.titleView;
        }
    }

    public final void clear() {
        if (!isEmpty()) {
            update(new ArrayList(0));
        }
    }

    public final List<MediaLibraryItem> getAll() {
        return getDataset();
    }

    public MediaLibraryItem getItem(int i) {
        return (MediaLibraryItem) getDataset().get(i);
    }

    public int getItemViewType(int i) {
        return getItem(i).getItemType();
    }

    public final BitmapDrawable getIcon(MediaWrapper mediaWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        int type = mediaWrapper.getType();
        if (type == 0) {
            return this.browserContainer.getInCards() ? getVideoDrawableBig() : getVideoDrawable();
        }
        if (type == 1) {
            return this.browserContainer.getInCards() ? getAudioDrawableBig() : getAudioDrawable();
        }
        if (type == 3) {
            if (z) {
                Uri uri = mediaWrapper.getUri();
                if (Intrinsics.areEqual((Object) AndroidDevices.MediaFolders.INSTANCE.getEXTERNAL_PUBLIC_MOVIES_DIRECTORY_URI(), (Object) uri) || Intrinsics.areEqual((Object) AndroidDevices.MediaFolders.INSTANCE.getWHATSAPP_VIDEOS_FILE_URI(), (Object) uri)) {
                    return this.browserContainer.getInCards() ? getQaMoviesDrawableBig() : getQaMoviesDrawable();
                }
                if (Intrinsics.areEqual((Object) AndroidDevices.MediaFolders.INSTANCE.getEXTERNAL_PUBLIC_MUSIC_DIRECTORY_URI(), (Object) uri)) {
                    return this.browserContainer.getInCards() ? getQaMusicDrawableBig() : getQaMusicDrawable();
                }
                if (Intrinsics.areEqual((Object) AndroidDevices.MediaFolders.INSTANCE.getEXTERNAL_PUBLIC_PODCAST_DIRECTORY_URI(), (Object) uri)) {
                    return this.browserContainer.getInCards() ? getQaPodcastsDrawableBig() : getQaPodcastsDrawable();
                }
                if (Intrinsics.areEqual((Object) AndroidDevices.MediaFolders.INSTANCE.getEXTERNAL_PUBLIC_DOWNLOAD_DIRECTORY_URI(), (Object) uri)) {
                    return this.browserContainer.getInCards() ? getQaDownloadDrawableBig() : getQaDownloadDrawable();
                }
            }
            return this.browserContainer.getInCards() ? getFolderDrawableBig() : getFolderDrawable();
        } else if (type != 4) {
            return getUnknownDrawable();
        } else {
            return this.browserContainer.getInCards() ? getSubtitleDrawableBig() : getSubtitleDrawable();
        }
    }

    private final String getProtocol(MediaWrapper mediaWrapper) {
        Uri uri;
        if (mediaWrapper.getType() == 3 && (uri = mediaWrapper.getUri()) != null) {
            return uri.getScheme();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public List<MediaLibraryItem> prepareList(List<? extends MediaLibraryItem> list) {
        boolean z;
        Intrinsics.checkNotNullParameter(list, "list");
        ArrayList arrayList = new ArrayList(list);
        Iterable<MediaLibraryItem> filterNotNull = CollectionsKt.filterNotNull(arrayList);
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(filterNotNull, 10));
        for (MediaLibraryItem mediaLibraryItem : filterNotNull) {
            if (mediaLibraryItem.getItemType() == 32) {
                Intrinsics.checkNotNull(mediaLibraryItem, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
                MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
                z = true;
                if (mediaWrapper.getType() != 1) {
                    if (mediaWrapper.getType() == 0) {
                    }
                }
                arrayList2.add(Boolean.valueOf(z));
            }
            z = false;
            arrayList2.add(Boolean.valueOf(z));
        }
        this.mediaCount = ((List) arrayList2).size();
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void onUpdateFinished() {
        this.browserContainer.onUpdateFinished(this);
        BrowserDiffCallback browserDiffCallback = this.diffCallback;
        browserDiffCallback.setOldSort(browserDiffCallback.getNewSort());
        BrowserDiffCallback browserDiffCallback2 = this.diffCallback;
        browserDiffCallback2.setOldAsc(browserDiffCallback2.getNewAsc());
    }

    /* access modifiers changed from: protected */
    public BrowserDiffCallback createCB() {
        return this.diffCallback;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000bH\u0016J\u0018\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000bH\u0016J\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR\u001a\u0010\u0013\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000f¨\u0006\u001c"}, d2 = {"Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter$BrowserDiffCallback;", "Lorg/videolan/vlc/gui/DiffUtilAdapter$DiffCallback;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "()V", "newAsc", "", "getNewAsc", "()Z", "setNewAsc", "(Z)V", "newSort", "", "getNewSort", "()I", "setNewSort", "(I)V", "oldAsc", "getOldAsc", "setOldAsc", "oldSort", "getOldSort", "setOldSort", "areContentsTheSame", "oldItemPosition", "newItemPosition", "areItemsTheSame", "getChangePayload", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BaseBrowserAdapter.kt */
    public static final class BrowserDiffCallback extends DiffUtilAdapter.DiffCallback<MediaLibraryItem> {
        private boolean newAsc = true;
        private int newSort = -1;
        private boolean oldAsc = true;
        private int oldSort = -1;

        public final int getOldSort() {
            return this.oldSort;
        }

        public final void setOldSort(int i) {
            this.oldSort = i;
        }

        public final int getNewSort() {
            return this.newSort;
        }

        public final void setNewSort(int i) {
            this.newSort = i;
        }

        public final boolean getOldAsc() {
            return this.oldAsc;
        }

        public final void setOldAsc(boolean z) {
            this.oldAsc = z;
        }

        public final boolean getNewAsc() {
            return this.newAsc;
        }

        public final void setNewAsc(boolean z) {
            this.newAsc = z;
        }

        public boolean areContentsTheSame(int i, int i2) {
            try {
                Object obj = getOldList().get(i);
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
                MediaWrapper mediaWrapper = (MediaWrapper) obj;
                Object obj2 = getNewList().get(i2);
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
                MediaWrapper mediaWrapper2 = (MediaWrapper) obj2;
                if (mediaWrapper.getDisplayTime() != mediaWrapper2.getDisplayTime() || mediaWrapper.getPlayCount() != mediaWrapper2.getPlayCount() || !Intrinsics.areEqual((Object) mediaWrapper.getFileName(), (Object) mediaWrapper2.getFileName()) || !Intrinsics.areEqual((Object) mediaWrapper2.getTitle(), (Object) mediaWrapper.getTitle())) {
                    return false;
                }
                return true;
            } catch (Exception unused) {
                return true;
            }
        }

        public Object getChangePayload(int i, int i2) {
            try {
                Object obj = getOldList().get(i);
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
                MediaWrapper mediaWrapper = (MediaWrapper) obj;
                Object obj2 = getNewList().get(i2);
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
                MediaWrapper mediaWrapper2 = (MediaWrapper) obj2;
                if (!(mediaWrapper.getDisplayTime() == mediaWrapper2.getDisplayTime() && mediaWrapper.getPlayCount() == mediaWrapper2.getPlayCount())) {
                    return BaseBrowserAdapterKt.UPDATE_PROGRESS;
                }
            } catch (Exception unused) {
            }
            return super.getChangePayload(i, i2);
        }

        public boolean areItemsTheSame(int i, int i2) {
            return Intrinsics.areEqual(getOldList().get(i), getNewList().get(i2));
        }
    }
}
