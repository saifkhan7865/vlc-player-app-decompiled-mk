package org.videolan.vlc.gui.browser;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.ActionMode;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import androidx.lifecycle.FlowLiveDataConversions;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.flow.FlowKt;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.KeyHelper;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.DirectoryBrowserBinding;
import org.videolan.vlc.gui.dialogs.ConfirmDeleteDialog;
import org.videolan.vlc.gui.dialogs.CtxActionReceiver;
import org.videolan.vlc.gui.dialogs.DisplaySettingsDialog;
import org.videolan.vlc.gui.dialogs.DisplaySettingsDialogKt;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;
import org.videolan.vlc.gui.dialogs.RenameDialog;
import org.videolan.vlc.gui.dialogs.SavePlaylistDialog;
import org.videolan.vlc.gui.helpers.MedialibraryUtils;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.view.EmptyLoadingState;
import org.videolan.vlc.gui.view.EmptyLoadingStateView;
import org.videolan.vlc.gui.view.VLCDividerItemDecoration;
import org.videolan.vlc.interfaces.IEventsHandler;
import org.videolan.vlc.interfaces.IRefreshable;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.media.PlaylistManager;
import org.videolan.vlc.repository.BrowserFavRepository;
import org.videolan.vlc.util.AccessibilityHelperKt;
import org.videolan.vlc.util.BrowserutilsKt;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.util.LifecycleAwareScheduler;
import org.videolan.vlc.util.Permissions;
import org.videolan.vlc.util.SchedulerCallback;
import org.videolan.vlc.viewmodels.DisplaySettingsViewModel;
import org.videolan.vlc.viewmodels.PlaylistModel;
import org.videolan.vlc.viewmodels.browser.BrowserModel;

@Metadata(d1 = {"\u0000\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000 ®\u00012\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\b\u0012\u0004\u0012\u00020\u00060\u00052\u00020\u00072\u00020\b2\b\u0012\u0004\u0012\u00020\u00060\t2\u00020\n2\u00020\u000b:\u0002®\u0001B\u0005¢\u0006\u0002\u0010\fJ\u0010\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020&H\u0002J\u0010\u0010R\u001a\u00020P2\u0006\u0010S\u001a\u00020\"H\u0016J\u0016\u0010T\u001a\u00020P2\u0006\u0010U\u001a\u00020&2\u0006\u0010V\u001a\u000204J\b\u0010W\u001a\u00020PH$J\b\u0010X\u001a\u00020PH\u0016J\b\u0010Y\u001a\u00020ZH$J\b\u0010[\u001a\u00020\\H\u0016J\b\u0010]\u001a\u000204H\u0014J\b\u0010^\u001a\u000204H\u0016J\u0016\u0010_\u001a\u00020&2\u0006\u0010Q\u001a\u00020&H@¢\u0006\u0002\u0010`J\u0010\u0010a\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010bH\u0016J\b\u0010c\u001a\u00020\u0002H\u0016J\b\u0010d\u001a\u00020\"H\u0016J\u0006\u0010e\u001a\u000204J\b\u0010f\u001a\u00020PH\u0002J\u0018\u0010g\u001a\u0002042\u0006\u0010h\u001a\u00020i2\u0006\u0010j\u001a\u00020\u0014H\u0016J \u0010k\u001a\u00020P2\u0006\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020o2\u0006\u0010j\u001a\u00020\u0006H\u0016J\u0012\u0010p\u001a\u00020P2\b\u0010q\u001a\u0004\u0018\u00010rH\u0016J\u0018\u0010s\u001a\u0002042\u0006\u0010h\u001a\u00020i2\u0006\u0010t\u001a\u00020uH\u0016J&\u0010v\u001a\u0004\u0018\u00010m2\u0006\u0010w\u001a\u00020x2\b\u0010y\u001a\u0004\u0018\u00010z2\b\u0010q\u001a\u0004\u0018\u00010rH\u0016J\u0018\u0010{\u001a\u00020P2\u0006\u0010n\u001a\u00020o2\u0006\u0010|\u001a\u00020}H\u0016J \u0010~\u001a\u00020P2\u0006\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020o2\u0006\u0010j\u001a\u00020\u0006H\u0016J\b\u0010\u001a\u00020PH\u0016J\u0013\u0010\u0001\u001a\u00020P2\b\u0010h\u001a\u0004\u0018\u00010iH\u0016J\u001b\u0010\u0001\u001a\u00020P2\u0007\u0010\u0001\u001a\u00020\"2\u0007\u00103\u001a\u00030\u0001H\u0016J\u0012\u0010\u0001\u001a\u00020P2\u0007\u0010\u0001\u001a\u00020mH\u0016J!\u0010\u0001\u001a\u00020P2\u0006\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020o2\u0006\u0010j\u001a\u00020\u0006H\u0016J\u0019\u0010\u0001\u001a\u00020P2\u0006\u0010l\u001a\u00020m2\u0006\u0010j\u001a\u00020\u0006H\u0016J!\u0010\u0001\u001a\u0002042\u0006\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020o2\u0006\u0010j\u001a\u00020\u0006H\u0016J!\u0010\u0001\u001a\u00020P2\u0006\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020o2\u0006\u0010j\u001a\u00020\u0006H\u0016J\u0013\u0010\u0001\u001a\u00020P2\b\u0010\u0001\u001a\u00030\u0001H\u0016J\u0013\u0010\u0001\u001a\u00020P2\b\u0010\u0001\u001a\u00030\u0001H\u0016J\u0011\u0010\u0001\u001a\u0002042\u0006\u0010j\u001a\u00020\u0014H\u0016J\t\u0010\u0001\u001a\u00020PH\u0016J\u0019\u0010\u0001\u001a\u0002042\u0006\u0010h\u001a\u00020i2\u0006\u0010t\u001a\u00020uH\u0016J\u0011\u0010\u0001\u001a\u00020P2\u0006\u0010t\u001a\u00020uH\u0016J\t\u0010\u0001\u001a\u00020PH\u0016J\t\u0010\u0001\u001a\u00020PH\u0016J\u0012\u0010\u0001\u001a\u00020P2\u0007\u0010\u0001\u001a\u00020rH\u0016J\t\u0010\u0001\u001a\u00020PH\u0016J\t\u0010\u0001\u001a\u00020PH\u0016J\u001b\u0010\u0001\u001a\u00020P2\u0007\u0010\u0001\u001a\u00020\"2\u0007\u0010\u0001\u001a\u00020rH\u0016J\u0016\u0010\u0001\u001a\u00020P2\u000b\u0010\r\u001a\u0007\u0012\u0002\b\u00030\u0001H\u0016J\u001c\u0010\u0001\u001a\u00020P2\u0007\u0010\u0001\u001a\u00020m2\b\u0010q\u001a\u0004\u0018\u00010rH\u0016J\u0013\u0010\u0001\u001a\u00020P2\b\u0010Q\u001a\u0004\u0018\u00010&H\u0002J\t\u0010 \u0001\u001a\u00020PH\u0016J\t\u0010¡\u0001\u001a\u00020PH\u0016J\u0011\u0010¢\u0001\u001a\u0002042\u0006\u0010j\u001a\u00020\u0006H\u0014J\t\u0010£\u0001\u001a\u00020PH\u0014J\u0012\u0010¤\u0001\u001a\u00020P2\u0007\u0010¥\u0001\u001a\u000204H\u0016J\t\u0010¦\u0001\u001a\u000204H\u0016J\u0012\u0010§\u0001\u001a\u00020P2\u0007\u0010¨\u0001\u001a\u00020oH\u0016J\n\u0010©\u0001\u001a\u00030ª\u0001H\u0002J\t\u0010«\u0001\u001a\u00020PH\u0016J\t\u0010¬\u0001\u001a\u00020PH\u0014J\t\u0010­\u0001\u001a\u00020PH\u0002R\u001a\u0010\r\u001a\u00020\u000eX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0016X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0012\u0010!\u001a\u00020\"X¤\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001b\u0010+\u001a\u00020,8BX\u0002¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b-\u0010.R\u0010\u00101\u001a\u0004\u0018\u000102X\u000e¢\u0006\u0002\n\u0000R$\u00105\u001a\u0002042\u0006\u00103\u001a\u000204@VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010:\u001a\u000204X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u00107\"\u0004\b;\u00109R\u000e\u0010<\u001a\u00020=X.¢\u0006\u0002\n\u0000R\u001c\u0010>\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010$\"\u0004\b@\u0010AR\u000e\u0010B\u001a\u000204X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010C\u001a\u000204XD¢\u0006\b\n\u0000\u001a\u0004\bD\u00107R\u001a\u0010E\u001a\u00020FX.¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u000e\u0010K\u001a\u00020LX.¢\u0006\u0002\n\u0000R\u0016\u0010M\u001a\u0004\u0018\u00010\"X\u0004¢\u0006\b\n\u0000\u001a\u0004\bN\u0010$¨\u0006¯\u0001"}, d2 = {"Lorg/videolan/vlc/gui/browser/BaseBrowserFragment;", "Lorg/videolan/vlc/gui/browser/MediaBrowserFragment;", "Lorg/videolan/vlc/viewmodels/browser/BrowserModel;", "Lorg/videolan/vlc/interfaces/IRefreshable;", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout$OnRefreshListener;", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lorg/videolan/vlc/gui/dialogs/CtxActionReceiver;", "Lorg/videolan/vlc/gui/browser/PathAdapterListener;", "Lorg/videolan/vlc/gui/browser/BrowserContainer;", "Lorg/videolan/vlc/util/SchedulerCallback;", "Lorg/videolan/vlc/PlaybackService$Callback;", "()V", "adapter", "Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter;", "getAdapter", "()Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter;", "setAdapter", "(Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter;)V", "addPlaylistFolderOnly", "Landroid/view/MenuItem;", "binding", "Lorg/videolan/vlc/databinding/DirectoryBrowserBinding;", "getBinding", "()Lorg/videolan/vlc/databinding/DirectoryBrowserBinding;", "setBinding", "(Lorg/videolan/vlc/databinding/DirectoryBrowserBinding;)V", "browserFavRepository", "Lorg/videolan/vlc/repository/BrowserFavRepository;", "getBrowserFavRepository", "()Lorg/videolan/vlc/repository/BrowserFavRepository;", "setBrowserFavRepository", "(Lorg/videolan/vlc/repository/BrowserFavRepository;)V", "categoryTitle", "", "getCategoryTitle", "()Ljava/lang/String;", "currentMedia", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getCurrentMedia", "()Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "setCurrentMedia", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)V", "displaySettingsViewModel", "Lorg/videolan/vlc/viewmodels/DisplaySettingsViewModel;", "getDisplaySettingsViewModel", "()Lorg/videolan/vlc/viewmodels/DisplaySettingsViewModel;", "displaySettingsViewModel$delegate", "Lkotlin/Lazy;", "enqueuingSnackbar", "Lcom/google/android/material/snackbar/Snackbar;", "value", "", "inCards", "getInCards", "()Z", "setInCards", "(Z)V", "isRootDirectory", "setRootDirectory", "layoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "mrl", "getMrl", "setMrl", "(Ljava/lang/String;)V", "needToRefreshMeta", "scannedDirectory", "getScannedDirectory", "scheduler", "Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "getScheduler", "()Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "setScheduler", "(Lorg/videolan/vlc/util/LifecycleAwareScheduler;)V", "startedScope", "Lkotlinx/coroutines/CoroutineScope;", "subTitle", "getSubTitle", "addToScannedFolders", "", "mw", "backTo", "tag", "browse", "media", "save", "browseRoot", "clear", "createFragment", "Landroidx/fragment/app/Fragment;", "currentContext", "Landroid/content/Context;", "defineIsRoot", "enableSearchOption", "getMediaWithMeta", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMultiHelper", "Lorg/videolan/tools/MultiSelectHelper;", "getPathOperationDelegate", "getTitle", "goBack", "manageDisplay", "onActionItemClicked", "mode", "Landroidx/appcompat/view/ActionMode;", "item", "onClick", "v", "Landroid/view/View;", "position", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateActionMode", "menu", "Landroid/view/Menu;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onCtxAction", "option", "Lorg/videolan/vlc/util/ContextOption;", "onCtxClick", "onDestroy", "onDestroyActionMode", "onDisplaySettingChanged", "key", "", "onFabPlayClick", "view", "onImageClick", "onItemFocused", "onLongClick", "onMainActionClick", "onMediaEvent", "event", "Lorg/videolan/libvlc/interfaces/IMedia$Event;", "onMediaPlayerEvent", "Lorg/videolan/libvlc/MediaPlayer$Event;", "onOptionsItemSelected", "onPause", "onPrepareActionMode", "onPrepareOptionsMenu", "onRefresh", "onResume", "onSaveInstanceState", "outState", "onStart", "onStop", "onTaskTriggered", "id", "data", "onUpdateFinished", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "onViewCreated", "playAll", "refresh", "registerSwiperRefreshlayout", "removeItem", "setBreadcrumb", "setSearchVisibility", "visible", "showRoot", "sortBy", "sort", "toggleFavorite", "Lkotlinx/coroutines/Job;", "update", "updateEmptyView", "updateFab", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseBrowserFragment.kt */
public abstract class BaseBrowserFragment extends MediaBrowserFragment<BrowserModel> implements IRefreshable, SwipeRefreshLayout.OnRefreshListener, IEventsHandler<MediaLibraryItem>, CtxActionReceiver, PathAdapterListener, BrowserContainer<MediaLibraryItem>, SchedulerCallback, PlaybackService.Callback {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final MutableLiveData<Boolean> needRefresh = new MutableLiveData<>(false);
    protected BaseBrowserAdapter adapter;
    /* access modifiers changed from: private */
    public MenuItem addPlaylistFolderOnly;
    protected DirectoryBrowserBinding binding;
    protected BrowserFavRepository browserFavRepository;
    private MediaWrapper currentMedia;
    private final Lazy displaySettingsViewModel$delegate;
    private Snackbar enqueuingSnackbar;
    private boolean inCards = true;
    private boolean isRootDirectory;
    private LinearLayoutManager layoutManager;
    private String mrl;
    private boolean needToRefreshMeta;
    private final boolean scannedDirectory;
    public LifecycleAwareScheduler scheduler;
    private CoroutineScope startedScope;
    private final String subTitle;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BaseBrowserFragment.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(31:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|31) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0046 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0050 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0082 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                org.videolan.vlc.util.ContextOption[] r0 = org.videolan.vlc.util.ContextOption.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_PLAY     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_PLAY_ALL     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_APPEND     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_DELETE     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_RENAME     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_INFORMATION     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_PLAY_AS_AUDIO     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_ADD_TO_PLAYLIST     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_DOWNLOAD_SUBTITLES     // Catch:{ NoSuchFieldError -> 0x005a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_FAV_REMOVE     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_ADD_SCANNED     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_FIND_METADATA     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_ADD_FOLDER_PLAYLIST     // Catch:{ NoSuchFieldError -> 0x0082 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0082 }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0082 }
            L_0x0082:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_ADD_FOLDER_AND_SUB_PLAYLIST     // Catch:{ NoSuchFieldError -> 0x008c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008c }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x008c }
            L_0x008c:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.BaseBrowserFragment.WhenMappings.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public abstract void browseRoot();

    /* access modifiers changed from: protected */
    public abstract Fragment createFragment();

    /* access modifiers changed from: protected */
    public abstract String getCategoryTitle();

    public void onItemFocused(View view, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
    }

    public void onMainActionClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
    }

    public void onMediaEvent(IMedia.Event event) {
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
    }

    public void setSearchVisibility(boolean z) {
    }

    public boolean showRoot() {
        return true;
    }

    public void update() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0039, code lost:
        r0 = org.videolan.tools.Strings.removeFileScheme(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BaseBrowserFragment() {
        /*
            r6 = this;
            r6.<init>()
            r0 = 1
            r6.inCards = r0
            r0 = r6
            androidx.fragment.app.Fragment r0 = (androidx.fragment.app.Fragment) r0
            java.lang.Class<org.videolan.vlc.viewmodels.DisplaySettingsViewModel> r1 = org.videolan.vlc.viewmodels.DisplaySettingsViewModel.class
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)
            org.videolan.vlc.gui.browser.BaseBrowserFragment$special$$inlined$activityViewModels$default$1 r2 = new org.videolan.vlc.gui.browser.BaseBrowserFragment$special$$inlined$activityViewModels$default$1
            r2.<init>(r0)
            kotlin.jvm.functions.Function0 r2 = (kotlin.jvm.functions.Function0) r2
            org.videolan.vlc.gui.browser.BaseBrowserFragment$special$$inlined$activityViewModels$default$2 r3 = new org.videolan.vlc.gui.browser.BaseBrowserFragment$special$$inlined$activityViewModels$default$2
            r4 = 0
            r3.<init>(r4, r0)
            kotlin.jvm.functions.Function0 r3 = (kotlin.jvm.functions.Function0) r3
            org.videolan.vlc.gui.browser.BaseBrowserFragment$special$$inlined$activityViewModels$default$3 r5 = new org.videolan.vlc.gui.browser.BaseBrowserFragment$special$$inlined$activityViewModels$default$3
            r5.<init>(r0)
            kotlin.jvm.functions.Function0 r5 = (kotlin.jvm.functions.Function0) r5
            kotlin.Lazy r0 = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(r0, r1, r2, r3, r5)
            r6.displaySettingsViewModel$delegate = r0
            boolean r0 = r6.isRootDirectory()
            if (r0 == 0) goto L_0x0033
            goto L_0x00b0
        L_0x0033:
            java.lang.String r0 = r6.getMrl()
            if (r0 == 0) goto L_0x003f
            java.lang.String r0 = org.videolan.tools.Strings.removeFileScheme(r0)
            if (r0 != 0) goto L_0x0041
        L_0x003f:
            java.lang.String r0 = ""
        L_0x0041:
            r1 = r0
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            int r1 = r1.length()
            if (r1 <= 0) goto L_0x00ab
            boolean r1 = r6 instanceof org.videolan.vlc.gui.browser.FileBrowserFragment
            if (r1 == 0) goto L_0x0084
            org.videolan.resources.AndroidDevices r1 = org.videolan.resources.AndroidDevices.INSTANCE
            java.lang.String r1 = r1.getEXTERNAL_PUBLIC_DIRECTORY()
            r2 = 0
            r3 = 2
            boolean r1 = kotlin.text.StringsKt.startsWith$default(r0, r1, r2, r3, r4)
            if (r1 == 0) goto L_0x0084
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            int r2 = org.videolan.vlc.R.string.internal_memory
            java.lang.String r2 = r6.getString(r2)
            r1.append(r2)
            org.videolan.resources.AndroidDevices r2 = org.videolan.resources.AndroidDevices.INSTANCE
            java.lang.String r2 = r2.getEXTERNAL_PUBLIC_DIRECTORY()
            int r2 = r2.length()
            java.lang.String r0 = r0.substring(r2)
            java.lang.String r2 = "substring(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
        L_0x0084:
            java.lang.String r0 = android.net.Uri.decode(r0)
            java.lang.String r1 = "decode(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            kotlin.text.Regex r1 = new kotlin.text.Regex
            java.lang.String r2 = "://"
            r1.<init>((java.lang.String) r2)
            java.lang.String r2 = " "
            java.lang.String r0 = r1.replace((java.lang.CharSequence) r0, (java.lang.String) r2)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            kotlin.text.Regex r1 = new kotlin.text.Regex
            java.lang.String r2 = "/"
            r1.<init>((java.lang.String) r2)
            java.lang.String r2 = " > "
            java.lang.String r0 = r1.replace((java.lang.CharSequence) r0, (java.lang.String) r2)
        L_0x00ab:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r6.currentMedia
            if (r1 == 0) goto L_0x00b0
            r4 = r0
        L_0x00b0:
            r6.subTitle = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.BaseBrowserFragment.<init>():void");
    }

    public void onTaskCancelled(String str) {
        SchedulerCallback.DefaultImpls.onTaskCancelled(this, str);
    }

    public final LifecycleAwareScheduler getScheduler() {
        LifecycleAwareScheduler lifecycleAwareScheduler = this.scheduler;
        if (lifecycleAwareScheduler != null) {
            return lifecycleAwareScheduler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("scheduler");
        return null;
    }

    public final void setScheduler(LifecycleAwareScheduler lifecycleAwareScheduler) {
        Intrinsics.checkNotNullParameter(lifecycleAwareScheduler, "<set-?>");
        this.scheduler = lifecycleAwareScheduler;
    }

    public String getMrl() {
        return this.mrl;
    }

    public void setMrl(String str) {
        this.mrl = str;
    }

    /* access modifiers changed from: protected */
    public final MediaWrapper getCurrentMedia() {
        return this.currentMedia;
    }

    /* access modifiers changed from: protected */
    public final void setCurrentMedia(MediaWrapper mediaWrapper) {
        this.currentMedia = mediaWrapper;
    }

    public boolean isRootDirectory() {
        return this.isRootDirectory;
    }

    public void setRootDirectory(boolean z) {
        this.isRootDirectory = z;
    }

    public boolean getScannedDirectory() {
        return this.scannedDirectory;
    }

    public boolean getInCards() {
        return this.inCards;
    }

    public void setInCards(boolean z) {
        this.inCards = z;
        manageDisplay();
    }

    /* access modifiers changed from: protected */
    public final BaseBrowserAdapter getAdapter() {
        BaseBrowserAdapter baseBrowserAdapter = this.adapter;
        if (baseBrowserAdapter != null) {
            return baseBrowserAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        return null;
    }

    /* access modifiers changed from: protected */
    public final void setAdapter(BaseBrowserAdapter baseBrowserAdapter) {
        Intrinsics.checkNotNullParameter(baseBrowserAdapter, "<set-?>");
        this.adapter = baseBrowserAdapter;
    }

    /* access modifiers changed from: protected */
    public final DirectoryBrowserBinding getBinding() {
        DirectoryBrowserBinding directoryBrowserBinding = this.binding;
        if (directoryBrowserBinding != null) {
            return directoryBrowserBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    /* access modifiers changed from: protected */
    public final void setBinding(DirectoryBrowserBinding directoryBrowserBinding) {
        Intrinsics.checkNotNullParameter(directoryBrowserBinding, "<set-?>");
        this.binding = directoryBrowserBinding;
    }

    /* access modifiers changed from: protected */
    public final BrowserFavRepository getBrowserFavRepository() {
        BrowserFavRepository browserFavRepository2 = this.browserFavRepository;
        if (browserFavRepository2 != null) {
            return browserFavRepository2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("browserFavRepository");
        return null;
    }

    /* access modifiers changed from: protected */
    public final void setBrowserFavRepository(BrowserFavRepository browserFavRepository2) {
        Intrinsics.checkNotNullParameter(browserFavRepository2, "<set-?>");
        this.browserFavRepository = browserFavRepository2;
    }

    private final DisplaySettingsViewModel getDisplaySettingsViewModel() {
        return (DisplaySettingsViewModel) this.displaySettingsViewModel$delegate.getValue();
    }

    public void onCreate(Bundle bundle) {
        Parcelable parcelable;
        String str;
        super.onCreate(bundle);
        setScheduler(new LifecycleAwareScheduler(this));
        if (bundle == null) {
            bundle = getArguments();
        }
        if (bundle != null) {
            if (Build.VERSION.SDK_INT >= 33) {
                parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(bundle, BaseBrowserFragmentKt.KEY_MEDIA, MediaWrapper.class);
            } else {
                Parcelable parcelable2 = bundle.getParcelable(BaseBrowserFragmentKt.KEY_MEDIA);
                if (!(parcelable2 instanceof MediaWrapper)) {
                    parcelable2 = null;
                }
                parcelable = (MediaWrapper) parcelable2;
            }
            MediaWrapper mediaWrapper = (MediaWrapper) parcelable;
            this.currentMedia = mediaWrapper;
            if (mediaWrapper == null || (str = mediaWrapper.getLocation()) == null) {
                str = bundle.getString("mrl");
            }
            setMrl(str);
        } else if (requireActivity().getIntent() != null) {
            setMrl(requireActivity().getIntent().getDataString());
            requireActivity().setIntent((Intent) null);
        }
        setRootDirectory(defineIsRoot());
        BrowserFavRepository.Companion companion = BrowserFavRepository.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        setBrowserFavRepository((BrowserFavRepository) companion.getInstance(requireContext));
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), (CoroutineStart) null, new BaseBrowserFragment$onCreate$1(this, (Continuation<? super BaseBrowserFragment$onCreate$1>) null), 2, (Object) null);
    }

    private final void manageDisplay() {
        if (getBinding().networkList.getItemDecorationCount() > 0) {
            getBinding().networkList.removeItemDecorationAt(0);
        }
        if (getInCards()) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(), getResources().getInteger(R.integer.mobile_card_columns));
            gridLayoutManager.setSpanSizeLookup(new BaseBrowserFragment$manageDisplay$1(this));
            getBinding().networkList.setLayoutManager(gridLayoutManager);
            getBinding().networkList.addItemDecoration(new BaseBrowserFragment$manageDisplay$2());
        } else {
            getBinding().networkList.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        }
        RecyclerView.Adapter adapter2 = getBinding().networkList.getAdapter();
        if (adapter2 != null) {
            getBinding().networkList.setAdapter(adapter2);
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.onPrepareOptionsMenu(menu);
        MenuItem findItem = menu.findItem(R.id.ml_menu_display_options);
        if (findItem != null) {
            findItem.setVisible(true);
        }
        MenuItem findItem2 = menu.findItem(R.id.ml_menu_filter);
        if (findItem2 != null) {
            findItem2.setVisible(enableSearchOption());
        }
        MenuItem findItem3 = menu.findItem(R.id.ml_menu_sortby);
        boolean z = false;
        if (findItem3 != null) {
            findItem3.setVisible(false);
        }
        MenuItem findItem4 = menu.findItem(R.id.ml_menu_sortby_media_number);
        if (findItem4 != null) {
            findItem4.setVisible(false);
        }
        MenuItem findItem5 = menu.findItem(R.id.ml_menu_add_playlist);
        if (findItem5 != null) {
            findItem5.setVisible(!isRootDirectory());
        }
        MenuItem findItem6 = menu.findItem(R.id.folder_add_playlist);
        Intrinsics.checkNotNullExpressionValue(findItem6, "findItem(...)");
        this.addPlaylistFolderOnly = findItem6;
        if (findItem6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("addPlaylistFolderOnly");
            findItem6 = null;
        }
        if (getAdapter().getMediaCount$vlc_android_release() > 0) {
            z = true;
        }
        findItem6.setVisible(z);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        if (AccessibilityHelperKt.isTalkbackIsEnabled(requireActivity)) {
            menu.findItem(R.id.play_all).setVisible(true);
        }
        UiTools.INSTANCE.updateSortTitles(this);
    }

    /* access modifiers changed from: protected */
    public boolean defineIsRoot() {
        return getMrl() == null;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        DirectoryBrowserBinding inflate = DirectoryBrowserBinding.inflate(layoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        setBinding(inflate);
        return getBinding().getRoot();
    }

    /* JADX WARNING: type inference failed for: r6v17, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onViewCreated(android.view.View r5, android.os.Bundle r6) {
        /*
            r4 = this;
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            super.onViewCreated(r5, r6)
            org.videolan.vlc.gui.browser.BaseBrowserAdapter r6 = r4.adapter
            if (r6 != 0) goto L_0x0038
            org.videolan.vlc.gui.browser.BaseBrowserAdapter r6 = new org.videolan.vlc.gui.browser.BaseBrowserAdapter
            r0 = r4
            org.videolan.vlc.gui.browser.BrowserContainer r0 = (org.videolan.vlc.gui.browser.BrowserContainer) r0
            org.videolan.vlc.viewmodels.SortableModel r1 = r4.getViewModel()
            org.videolan.vlc.viewmodels.browser.BrowserModel r1 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r1
            int r1 = r1.getSort()
            org.videolan.vlc.viewmodels.SortableModel r2 = r4.getViewModel()
            org.videolan.vlc.viewmodels.browser.BrowserModel r2 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r2
            boolean r2 = r2.getDesc()
            r2 = r2 ^ 1
            androidx.fragment.app.FragmentActivity r3 = r4.requireActivity()
            boolean r3 = r3 instanceof org.videolan.vlc.gui.MainActivity
            r6.<init>(r0, r1, r2, r3)
            androidx.recyclerview.widget.RecyclerView$Adapter$StateRestorationPolicy r0 = androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            r6.setStateRestorationPolicy(r0)
            r4.setAdapter(r6)
        L_0x0038:
            androidx.recyclerview.widget.LinearLayoutManager r6 = new androidx.recyclerview.widget.LinearLayoutManager
            androidx.fragment.app.FragmentActivity r0 = r4.getActivity()
            android.content.Context r0 = (android.content.Context) r0
            r6.<init>(r0)
            r4.layoutManager = r6
            org.videolan.vlc.databinding.DirectoryBrowserBinding r6 = r4.getBinding()
            androidx.recyclerview.widget.RecyclerView r6 = r6.networkList
            androidx.recyclerview.widget.LinearLayoutManager r0 = r4.layoutManager
            r1 = 0
            if (r0 != 0) goto L_0x0056
            java.lang.String r0 = "layoutManager"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = r1
        L_0x0056:
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = (androidx.recyclerview.widget.RecyclerView.LayoutManager) r0
            r6.setLayoutManager(r0)
            org.videolan.vlc.databinding.DirectoryBrowserBinding r6 = r4.getBinding()
            androidx.recyclerview.widget.RecyclerView r6 = r6.networkList
            org.videolan.vlc.gui.browser.BaseBrowserAdapter r0 = r4.getAdapter()
            androidx.recyclerview.widget.RecyclerView$Adapter r0 = (androidx.recyclerview.widget.RecyclerView.Adapter) r0
            r6.setAdapter(r0)
            r4.registerSwiperRefreshlayout()
            org.videolan.vlc.viewmodels.SortableModel r6 = r4.getViewModel()
            org.videolan.vlc.viewmodels.browser.BrowserModel r6 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r6
            org.videolan.tools.livedata.LiveDataset r6 = r6.getDataset()
            androidx.lifecycle.LifecycleOwner r0 = r4.getViewLifecycleOwner()
            org.videolan.vlc.gui.browser.BaseBrowserFragment$onViewCreated$2 r2 = new org.videolan.vlc.gui.browser.BaseBrowserFragment$onViewCreated$2
            r2.<init>(r4)
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            org.videolan.vlc.gui.browser.BaseBrowserFragmentKt$sam$androidx_lifecycle_Observer$0 r3 = new org.videolan.vlc.gui.browser.BaseBrowserFragmentKt$sam$androidx_lifecycle_Observer$0
            r3.<init>(r2)
            androidx.lifecycle.Observer r3 = (androidx.lifecycle.Observer) r3
            r6.observe(r0, r3)
            org.videolan.vlc.viewmodels.SortableModel r6 = r4.getViewModel()
            org.videolan.vlc.viewmodels.browser.BrowserModel r6 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r6
            androidx.lifecycle.MutableLiveData r6 = r6.getDescriptionUpdate()
            androidx.lifecycle.LifecycleOwner r0 = r4.getViewLifecycleOwner()
            org.videolan.vlc.gui.browser.BaseBrowserFragment$onViewCreated$3 r2 = new org.videolan.vlc.gui.browser.BaseBrowserFragment$onViewCreated$3
            r2.<init>(r4)
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            org.videolan.vlc.gui.browser.BaseBrowserFragmentKt$sam$androidx_lifecycle_Observer$0 r3 = new org.videolan.vlc.gui.browser.BaseBrowserFragmentKt$sam$androidx_lifecycle_Observer$0
            r3.<init>(r2)
            androidx.lifecycle.Observer r3 = (androidx.lifecycle.Observer) r3
            r6.observe(r0, r3)
            org.videolan.vlc.viewmodels.SortableModel r6 = r4.getViewModel()
            org.videolan.vlc.viewmodels.browser.BrowserModel r6 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r6
            androidx.lifecycle.MutableLiveData r6 = r6.getLoading()
            androidx.lifecycle.LifecycleOwner r0 = r4.getViewLifecycleOwner()
            org.videolan.vlc.gui.browser.BaseBrowserFragment$onViewCreated$4 r2 = new org.videolan.vlc.gui.browser.BaseBrowserFragment$onViewCreated$4
            r2.<init>(r4)
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            org.videolan.vlc.gui.browser.BaseBrowserFragmentKt$sam$androidx_lifecycle_Observer$0 r3 = new org.videolan.vlc.gui.browser.BaseBrowserFragmentKt$sam$androidx_lifecycle_Observer$0
            r3.<init>(r2)
            androidx.lifecycle.Observer r3 = (androidx.lifecycle.Observer) r3
            r6.observe(r0, r3)
            android.view.View r6 = r5.getRootView()
            int r0 = org.videolan.vlc.R.id.appbar
            android.view.View r6 = r6.findViewById(r0)
            boolean r0 = r6 instanceof com.google.android.material.appbar.AppBarLayout
            if (r0 == 0) goto L_0x00db
            r1 = r6
            com.google.android.material.appbar.AppBarLayout r1 = (com.google.android.material.appbar.AppBarLayout) r1
        L_0x00db:
            if (r1 == 0) goto L_0x0128
            org.videolan.vlc.databinding.DirectoryBrowserBinding r6 = r4.getBinding()
            org.videolan.vlc.gui.view.FastScroller r6 = r6.browserFastScroller
            android.view.View r0 = r5.getRootView()
            int r2 = org.videolan.vlc.R.id.coordinator
            android.view.View r0 = r0.findViewById(r2)
            java.lang.String r2 = "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r2)
            androidx.coordinatorlayout.widget.CoordinatorLayout r0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r0
            android.view.View r5 = r5.getRootView()
            int r2 = org.videolan.vlc.R.id.fab
            android.view.View r5 = r5.findViewById(r2)
            java.lang.String r2 = "null cannot be cast to non-null type com.google.android.material.floatingactionbutton.FloatingActionButton"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r2)
            com.google.android.material.floatingactionbutton.FloatingActionButton r5 = (com.google.android.material.floatingactionbutton.FloatingActionButton) r5
            r6.attachToCoordinator(r1, r0, r5)
            org.videolan.vlc.databinding.DirectoryBrowserBinding r5 = r4.getBinding()
            org.videolan.vlc.gui.view.FastScroller r5 = r5.browserFastScroller
            org.videolan.vlc.databinding.DirectoryBrowserBinding r6 = r4.getBinding()
            androidx.recyclerview.widget.RecyclerView r6 = r6.networkList
            java.lang.String r0 = "networkList"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
            org.videolan.vlc.viewmodels.SortableModel r0 = r4.getViewModel()
            org.videolan.vlc.viewmodels.browser.BrowserModel r0 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r0
            org.videolan.vlc.providers.BrowserProvider r0 = r0.getProvider()
            org.videolan.resources.util.HeaderProvider r0 = (org.videolan.resources.util.HeaderProvider) r0
            r5.setRecyclerView(r6, r0)
        L_0x0128:
            org.videolan.vlc.media.PlaylistManager$Companion r5 = org.videolan.vlc.media.PlaylistManager.Companion
            androidx.lifecycle.MutableLiveData r5 = r5.getCurrentPlayedMedia()
            r6 = r4
            androidx.lifecycle.LifecycleOwner r6 = (androidx.lifecycle.LifecycleOwner) r6
            org.videolan.vlc.gui.browser.BaseBrowserFragment$onViewCreated$6 r0 = new org.videolan.vlc.gui.browser.BaseBrowserFragment$onViewCreated$6
            r0.<init>(r4)
            kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
            org.videolan.vlc.gui.browser.BaseBrowserFragmentKt$sam$androidx_lifecycle_Observer$0 r1 = new org.videolan.vlc.gui.browser.BaseBrowserFragmentKt$sam$androidx_lifecycle_Observer$0
            r1.<init>(r0)
            androidx.lifecycle.Observer r1 = (androidx.lifecycle.Observer) r1
            r5.observe(r6, r1)
            org.videolan.tools.Settings r5 = org.videolan.tools.Settings.INSTANCE
            androidx.fragment.app.FragmentActivity r6 = r4.requireActivity()
            java.lang.String r0 = "requireActivity(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
            java.lang.Object r5 = r5.getInstance(r6)
            android.content.SharedPreferences r5 = (android.content.SharedPreferences) r5
            java.lang.String r6 = "browser_display_in_cards"
            r0 = 0
            boolean r5 = r5.getBoolean(r6, r0)
            r4.setInCards(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.BaseBrowserFragment.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    public void onDisplaySettingChanged(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(obj, "value");
        switch (str.hashCode()) {
            case -341247322:
                if (str.equals(DisplaySettingsDialogKt.DISPLAY_IN_CARDS)) {
                    Settings settings = Settings.INSTANCE;
                    FragmentActivity requireActivity = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                    Boolean bool = (Boolean) obj;
                    SettingsKt.putSingle((SharedPreferences) settings.getInstance(requireActivity), SettingsKt.BROWSER_DISPLAY_IN_CARDS, bool);
                    setInCards(bool.booleanValue());
                    return;
                }
                return;
            case 1048536484:
                if (str.equals(DisplaySettingsDialogKt.SHOW_HIDDEN_FILES)) {
                    Settings settings2 = Settings.INSTANCE;
                    FragmentActivity requireActivity2 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                    Boolean bool2 = (Boolean) obj;
                    SettingsKt.putSingle((SharedPreferences) settings2.getInstance(requireActivity2), SettingsKt.BROWSER_SHOW_HIDDEN_FILES, bool2);
                    Settings.INSTANCE.setShowHiddenFiles(bool2.booleanValue());
                    ((BrowserModel) getViewModel()).refresh();
                    return;
                }
                return;
            case 1468888228:
                if (str.equals(DisplaySettingsDialogKt.CURRENT_SORT)) {
                    Pair pair = (Pair) obj;
                    ((BrowserModel) getViewModel()).setDesc(((Boolean) pair.getSecond()).booleanValue());
                    ((BrowserModel) getViewModel()).setSort(((Number) pair.getFirst()).intValue());
                    ((BrowserModel) getViewModel()).getProvider().setDesc(((Boolean) pair.getSecond()).booleanValue());
                    ((BrowserModel) getViewModel()).getProvider().setSort(((Number) pair.getFirst()).intValue());
                    refresh();
                    ((BrowserModel) getViewModel()).saveSort();
                    return;
                }
                return;
            case 1881018356:
                if (str.equals(DisplaySettingsDialogKt.SHOW_ONLY_MULTIMEDIA_FILES)) {
                    Settings settings3 = Settings.INSTANCE;
                    FragmentActivity requireActivity3 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity(...)");
                    Boolean bool3 = (Boolean) obj;
                    SettingsKt.putSingle((SharedPreferences) settings3.getInstance(requireActivity3), SettingsKt.BROWSER_SHOW_ONLY_MULTIMEDIA, bool3);
                    ((BrowserModel) getViewModel()).updateShowAllFiles(bool3.booleanValue());
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void sortBy(int i) {
        super.sortBy(i);
        getAdapter().setSort(i);
        getAdapter().changeSort(i, !((BrowserModel) getViewModel()).getDesc());
        UiTools.INSTANCE.updateSortTitles(this);
    }

    public void registerSwiperRefreshlayout() {
        getSwipeRefreshLayout().setOnRefreshListener(this);
    }

    /* access modifiers changed from: protected */
    public void setBreadcrumb() {
        RecyclerView recyclerView = getBinding().ariane;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "ariane");
        MediaWrapper mediaWrapper = this.currentMedia;
        if (mediaWrapper != null) {
            Uri uri = mediaWrapper.getUri();
            if (BrowserutilsKt.isSchemeSupported(uri != null ? uri.getScheme() : null)) {
                recyclerView.setVisibility(0);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
                recyclerView.setAdapter(new PathAdapter(this, mediaWrapper));
                if (recyclerView.getItemDecorationCount() == 0) {
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
                    Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_divider);
                    Intrinsics.checkNotNull(drawable);
                    recyclerView.addItemDecoration(new VLCDividerItemDecoration(requireContext, 0, drawable));
                }
                RecyclerView.Adapter adapter2 = recyclerView.getAdapter();
                Intrinsics.checkNotNull(adapter2);
                recyclerView.scrollToPosition(adapter2.getItemCount() - 1);
                return;
            }
        }
        recyclerView.setVisibility(8);
    }

    public void backTo(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        if (Intrinsics.areEqual((Object) str, (Object) "root")) {
            requireActivity().finish();
            return;
        }
        FragmentManager supportFragmentManager = requireActivity().getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        int backStackEntryCount = supportFragmentManager.getBackStackEntryCount();
        for (int i = 0; i < backStackEntryCount; i++) {
            if (Intrinsics.areEqual((Object) str, (Object) supportFragmentManager.getBackStackEntryAt(i).getName())) {
                supportFragmentManager.popBackStack(str, 1);
                return;
            }
        }
        MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(Uri.parse(str));
        Intrinsics.checkNotNullExpressionValue(abstractMediaWrapper, "getAbstractMediaWrapper(...)");
        browse(abstractMediaWrapper, false);
    }

    public Context currentContext() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        return requireContext;
    }

    public BrowserModel getPathOperationDelegate() {
        return (BrowserModel) getViewModel();
    }

    /* JADX WARNING: type inference failed for: r0v6, types: [androidx.fragment.app.FragmentActivity] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onStart() {
        /*
            r3 = this;
            super.onStart()
            kotlinx.coroutines.CoroutineScope r0 = kotlinx.coroutines.CoroutineScopeKt.MainScope()
            r3.startedScope = r0
            org.videolan.vlc.PlaybackService$Companion r0 = org.videolan.vlc.PlaybackService.Companion
            kotlinx.coroutines.flow.MutableStateFlow r0 = r0.getServiceFlow()
            kotlinx.coroutines.flow.Flow r0 = (kotlinx.coroutines.flow.Flow) r0
            org.videolan.vlc.gui.browser.BaseBrowserFragment$onStart$1 r1 = new org.videolan.vlc.gui.browser.BaseBrowserFragment$onStart$1
            r2 = 0
            r1.<init>(r3, r2)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            kotlinx.coroutines.flow.Flow r0 = kotlinx.coroutines.flow.FlowKt.onEach(r0, r1)
            kotlinx.coroutines.CoroutineScope r1 = r3.startedScope
            if (r1 != 0) goto L_0x0027
            java.lang.String r1 = "startedScope"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r1 = r2
        L_0x0027:
            kotlinx.coroutines.flow.FlowKt.launchIn(r0, r1)
            com.google.android.material.floatingactionbutton.FloatingActionButton r0 = r3.getFabPlay()
            if (r0 == 0) goto L_0x004a
            int r1 = org.videolan.vlc.R.drawable.ic_fab_play
            r0.setImageResource(r1)
            r3.updateFab()
            com.google.android.material.floatingactionbutton.FloatingActionButton r0 = r3.getFabPlay()
            if (r0 != 0) goto L_0x003f
            goto L_0x004a
        L_0x003f:
            int r1 = org.videolan.vlc.R.string.play
            java.lang.String r1 = r3.getString(r1)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setContentDescription(r1)
        L_0x004a:
            androidx.fragment.app.FragmentActivity r0 = r3.getActivity()
            boolean r1 = r0 instanceof org.videolan.vlc.gui.AudioPlayerContainerActivity
            if (r1 == 0) goto L_0x0055
            r2 = r0
            org.videolan.vlc.gui.AudioPlayerContainerActivity r2 = (org.videolan.vlc.gui.AudioPlayerContainerActivity) r2
        L_0x0055:
            if (r2 == 0) goto L_0x005a
            r2.expandAppBar()
        L_0x005a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.BaseBrowserFragment.onStart():void");
    }

    public void onStop() {
        super.onStop();
        CoroutineScope coroutineScope = this.startedScope;
        if (coroutineScope == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startedScope");
            coroutineScope = null;
        }
        CoroutineScopeKt.cancel$default(coroutineScope, (CancellationException) null, 1, (Object) null);
        PlaybackService value = PlaybackService.Companion.getServiceFlow().getValue();
        if (value != null) {
            ChannelResult.m2336boximpl(value.m2457removeCallbackJP2dKIU(this));
        }
        ((BrowserModel) getViewModel()).stop();
        needRefresh.postValue(false);
    }

    public void onPause() {
        super.onPause();
        getAdapter().setCurrentlyPlaying(false);
    }

    public void onResume() {
        super.onResume();
        PlaylistModel playlistModel = PlaylistModel.Companion.get((Fragment) this);
        getAdapter().setModel(playlistModel);
        KextensionsKt.launchWhenStarted(FlowKt.onEach(FlowKt.conflate(FlowLiveDataConversions.asFlow(playlistModel.getDataset())), new BaseBrowserFragment$onResume$1(this, playlistModel, (Continuation<? super BaseBrowserFragment$onResume$1>) null)), LifecycleOwnerKt.getLifecycleScope(this));
    }

    public void onDestroy() {
        if (this.adapter != null) {
            CoroutineScopeKt.cancel$default(getAdapter(), (CancellationException) null, 1, (Object) null);
        }
        super.onDestroy();
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        bundle.putString("mrl", getMrl());
        bundle.putParcelable(BaseBrowserFragmentKt.KEY_MEDIA, this.currentMedia);
        super.onSaveInstanceState(bundle);
    }

    public String getTitle() {
        if (isRootDirectory()) {
            return getCategoryTitle();
        }
        MediaWrapper mediaWrapper = this.currentMedia;
        if (mediaWrapper != null) {
            Intrinsics.checkNotNull(mediaWrapper);
            String title = mediaWrapper.getTitle();
            Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
            return title;
        }
        String mrl2 = getMrl();
        return mrl2 == null ? "" : mrl2;
    }

    public MultiSelectHelper<BrowserModel> getMultiHelper() {
        if (this.adapter == null) {
            return null;
        }
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper = getAdapter().getMultiSelectHelper();
        if (multiSelectHelper instanceof MultiSelectHelper) {
            return multiSelectHelper;
        }
        return null;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public final boolean goBack() {
        FragmentActivity activity = getActivity();
        if (activity == null || !KotlinExtensionsKt.isStarted(activity)) {
            return false;
        }
        if (!isRootDirectory() && !activity.isFinishing() && !activity.isDestroyed()) {
            activity.getSupportFragmentManager().popBackStack();
        }
        return !isRootDirectory();
    }

    public final void browse(MediaWrapper mediaWrapper, boolean z) {
        String str;
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        FragmentActivity activity = getActivity();
        if (activity != null && isResumed() && !isRemoving()) {
            FragmentTransaction beginTransaction = activity.getSupportFragmentManager().beginTransaction();
            Intrinsics.checkNotNullExpressionValue(beginTransaction, "beginTransaction(...)");
            Fragment createFragment = createFragment();
            ((BrowserModel) getViewModel()).saveList(mediaWrapper);
            createFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(BaseBrowserFragmentKt.KEY_MEDIA, mediaWrapper)));
            if (z) {
                if (isRootDirectory()) {
                    str = "root";
                } else {
                    MediaWrapper mediaWrapper2 = this.currentMedia;
                    if (mediaWrapper2 != null) {
                        str = String.valueOf(mediaWrapper2 != null ? mediaWrapper2.getUri() : null);
                    } else {
                        str = getMrl();
                        Intrinsics.checkNotNull(str);
                    }
                }
                beginTransaction.addToBackStack(str);
            }
            beginTransaction.replace(R.id.fragment_placeholder, createFragment, mediaWrapper.getTitle());
            beginTransaction.commit();
        }
    }

    public void onRefresh() {
        ((BrowserModel) getViewModel()).refresh();
    }

    /* access modifiers changed from: protected */
    public void updateEmptyView() {
        String str;
        EmptyLoadingStateView emptyLoadingStateView = getBinding().emptyLoading;
        String filterQuery = ((BrowserModel) getViewModel()).getFilterQuery();
        if (filterQuery != null) {
            str = getString(R.string.empty_search, filterQuery);
        } else {
            str = null;
        }
        if (str == null) {
            str = getString(R.string.nomedia);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        }
        emptyLoadingStateView.setEmptyText(str);
        org.videolan.vlc.gui.view.SwipeRefreshLayout swipeRefreshLayout = getSwipeRefreshLayout();
        Permissions permissions = Permissions.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        if (!permissions.canReadStorage(requireActivity)) {
            getBinding().emptyLoading.setState(EmptyLoadingState.MISSING_PERMISSION);
        } else if (swipeRefreshLayout.isRefreshing()) {
            getBinding().emptyLoading.setState(EmptyLoadingState.LOADING);
            getBinding().networkList.setVisibility(8);
        } else if (((BrowserModel) getViewModel()).isEmpty() && ((BrowserModel) getViewModel()).getFilterQuery() != null) {
            getBinding().emptyLoading.setState(EmptyLoadingState.EMPTY_SEARCH);
            getBinding().networkList.setVisibility(8);
        } else if (((BrowserModel) getViewModel()).isEmpty()) {
            getBinding().emptyLoading.setState(EmptyLoadingState.EMPTY);
            getBinding().networkList.setVisibility(8);
        } else {
            getBinding().emptyLoading.setState(EmptyLoadingState.NONE);
            getBinding().networkList.setVisibility(0);
        }
    }

    public void refresh() {
        ((BrowserModel) getViewModel()).refresh();
    }

    public void onFabPlayClick(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        playAll((MediaWrapper) null);
    }

    public void onTaskTriggered(String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(bundle, "data");
        switch (str.hashCode()) {
            case -1982808259:
                if (str.equals(BaseBrowserFragmentKt.MSG_HIDE_LOADING)) {
                    getScheduler().cancelAction("msg_show_loading");
                    getSwipeRefreshLayout().setRefreshing(false);
                    return;
                }
                return;
            case -1758932963:
                if (str.equals("msg_refresh")) {
                    getScheduler().cancelAction("msg_refresh");
                    if (!isDetached()) {
                        refresh();
                        return;
                    }
                    return;
                }
                return;
            case -523634047:
                if (str.equals("msg_show_enqueuing")) {
                    FragmentActivity activity = getActivity();
                    if (activity != null) {
                        String string = activity.getString(R.string.enqueuing);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        this.enqueuingSnackbar = UiTools.INSTANCE.snackerMessageInfinite(activity, string);
                    }
                    Snackbar snackbar = this.enqueuingSnackbar;
                    if (snackbar != null) {
                        snackbar.show();
                        return;
                    }
                    return;
                }
                return;
            case 897962488:
                if (str.equals("msg_show_loading")) {
                    getSwipeRefreshLayout().setRefreshing(true);
                    return;
                }
                return;
            case 1309584006:
                if (str.equals("msg_hide_enqueuing")) {
                    Snackbar snackbar2 = this.enqueuingSnackbar;
                    if (snackbar2 != null) {
                        snackbar2.dismiss();
                    }
                    getScheduler().cancelAction("msg_show_enqueuing");
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void clear() {
        getAdapter().clear();
    }

    /* access modifiers changed from: protected */
    public boolean removeItem(MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        MediaWrapper mediaWrapper = mediaLibraryItem instanceof MediaWrapper ? (MediaWrapper) mediaLibraryItem : null;
        if (mediaWrapper == null) {
            return false;
        }
        ConfirmDeleteDialog newInstance$default = ConfirmDeleteDialog.Companion.newInstance$default(ConfirmDeleteDialog.Companion, CollectionsKt.arrayListOf(mediaWrapper), (String) null, (String) null, (String) null, 14, (Object) null);
        newInstance$default.show(requireActivity().getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(ConfirmDeleteDialog.class).getSimpleName());
        newInstance$default.setListener(new BaseBrowserFragment$removeItem$1(this, mediaWrapper, new BaseBrowserFragment$removeItem$$inlined$Runnable$1(this, mediaWrapper)));
        return true;
    }

    private final void playAll(MediaWrapper mediaWrapper) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new BaseBrowserFragment$playAll$1(this, mediaWrapper, (Continuation<? super BaseBrowserFragment$playAll$1>) null), 3, (Object) null);
    }

    public boolean enableSearchOption() {
        return !isRootDirectory();
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        Intrinsics.checkNotNullParameter(actionMode, RtspHeaders.Values.MODE);
        Intrinsics.checkNotNullParameter(menu, "menu");
        MultiSelectHelper<BrowserModel> multiHelper = getMultiHelper();
        if (multiHelper != null) {
            multiHelper.toggleActionMode(true, getAdapter().getItemCount());
        }
        actionMode.getMenuInflater().inflate(R.menu.action_mode_browser_file, menu);
        return true;
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        int i;
        Intrinsics.checkNotNullParameter(actionMode, RtspHeaders.Values.MODE);
        Intrinsics.checkNotNullParameter(menu, "menu");
        int selectionCount = getAdapter().getMultiSelectHelper().getSelectionCount();
        boolean z = false;
        if (selectionCount == 0) {
            stopActionMode();
            return false;
        }
        actionMode.setTitle((CharSequence) requireActivity().getString(R.string.selection_count, new Object[]{Integer.valueOf(selectionCount)}));
        boolean z2 = this instanceof FileBrowserFragment;
        boolean z3 = z2 && selectionCount == 1;
        List<MediaLibraryItem> selection = z3 ? getAdapter().getMultiSelectHelper().getSelection() : null;
        Collection collection = selection;
        if (collection == null || collection.isEmpty()) {
            i = -1;
        } else {
            MediaLibraryItem mediaLibraryItem = selection.get(0);
            Intrinsics.checkNotNull(mediaLibraryItem, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
            i = ((MediaWrapper) mediaLibraryItem).getType();
        }
        MenuItem findItem = menu.findItem(R.id.action_mode_file_info);
        if (z3 && (i == 1 || i == 0)) {
            z = true;
        }
        findItem.setVisible(z);
        menu.findItem(R.id.action_mode_file_append).setVisible(PlaylistManager.Companion.hasMedia());
        menu.findItem(R.id.action_mode_file_delete).setVisible(z2);
        return true;
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(actionMode, RtspHeaders.Values.MODE);
        Intrinsics.checkNotNullParameter(menuItem, "item");
        LifecycleOwner lifecycleOwner = this;
        if (!KotlinExtensionsKt.isStarted(lifecycleOwner)) {
            return false;
        }
        List<MediaLibraryItem> selection = getAdapter().getMultiSelectHelper().getSelection();
        if (!(selection instanceof List)) {
            selection = null;
        }
        if (selection == null) {
            return false;
        }
        if (!selection.isEmpty()) {
            int itemId = menuItem.getItemId();
            if (itemId == R.id.action_mode_file_play) {
                Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new BaseBrowserFragment$onActionItemClicked$1(this, selection, (Continuation<? super BaseBrowserFragment$onActionItemClicked$1>) null), 3, (Object) null);
            } else if (itemId == R.id.action_mode_file_append) {
                Job unused2 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new BaseBrowserFragment$onActionItemClicked$2(this, selection, (Continuation<? super BaseBrowserFragment$onActionItemClicked$2>) null), 3, (Object) null);
            } else if (itemId == R.id.action_mode_file_add_playlist) {
                UiTools uiTools = UiTools.INSTANCE;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                uiTools.addToPlaylist(requireActivity, selection);
            } else if (itemId == R.id.action_mode_file_info) {
                UiTools uiTools2 = UiTools.INSTANCE;
                FragmentActivity requireActivity2 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                uiTools2.showMediaInfo(requireActivity2, (MediaWrapper) selection.get(0));
            } else if (itemId == R.id.action_mode_file_delete) {
                removeItems(selection);
            } else {
                stopActionMode();
                return false;
            }
        }
        stopActionMode();
        return true;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        MultiSelectHelper<BrowserModel> multiHelper = getMultiHelper();
        if (multiHelper != null) {
            multiHelper.toggleActionMode(false, getAdapter().getItemCount());
        }
        setActionMode((ActionMode) null);
        getAdapter().getMultiSelectHelper().clearSelection();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        MenuItem menuItem2 = menuItem;
        Intrinsics.checkNotNullParameter(menuItem2, "item");
        int itemId = menuItem.getItemId();
        if (itemId == R.id.ml_menu_save) {
            toggleFavorite();
            Menu menu = getMenu();
            if (menu == null) {
                return true;
            }
            onPrepareOptionsMenu(menu);
            return true;
        } else if (itemId == R.id.ml_menu_display_options) {
            ArrayList arrayListOf = CollectionsKt.arrayListOf(1, 10);
            Settings settings = Settings.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            SharedPreferences sharedPreferences = (SharedPreferences) settings.getInstance(requireActivity);
            DisplaySettingsDialog.Companion companion = DisplaySettingsDialog.Companion;
            boolean inCards2 = getInCards();
            List list = arrayListOf;
            Integer valueOf = Integer.valueOf(((BrowserModel) getViewModel()).getProvider().getSort());
            if (valueOf.intValue() == 0) {
                valueOf = null;
            }
            DisplaySettingsDialog.Companion.newInstance$default(companion, inCards2, (Boolean) null, (Boolean) null, list, valueOf != null ? valueOf.intValue() : 10, ((BrowserModel) getViewModel()).getProvider().getDesc(), (String) null, Boolean.valueOf(sharedPreferences.getBoolean(SettingsKt.BROWSER_SHOW_ONLY_MULTIMEDIA, false)), Boolean.valueOf(sharedPreferences.getBoolean(SettingsKt.BROWSER_SHOW_HIDDEN_FILES, true)), 66, (Object) null).show(requireActivity().getSupportFragmentManager(), "DisplaySettingsDialog");
            return true;
        } else if (itemId == R.id.ml_menu_scan) {
            MediaWrapper mediaWrapper = this.currentMedia;
            if (mediaWrapper == null) {
                return true;
            }
            addToScannedFolders(mediaWrapper);
            menuItem2.setVisible(false);
            return true;
        } else if (itemId == R.id.folder_add_playlist) {
            MediaWrapper mediaWrapper2 = this.currentMedia;
            if (mediaWrapper2 == null) {
                return true;
            }
            UiTools uiTools = UiTools.INSTANCE;
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
            String uri = mediaWrapper2.getUri().toString();
            Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
            String title = mediaWrapper2.getTitle();
            Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
            UiTools.addToPlaylistAsync$default(uiTools, requireActivity2, uri, false, title, 2, (Object) null);
            return true;
        } else if (itemId == R.id.subfolders_add_playlist) {
            MediaWrapper mediaWrapper3 = this.currentMedia;
            if (mediaWrapper3 == null) {
                return true;
            }
            UiTools uiTools2 = UiTools.INSTANCE;
            FragmentActivity requireActivity3 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity(...)");
            String uri2 = mediaWrapper3.getUri().toString();
            Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
            String title2 = mediaWrapper3.getTitle();
            Intrinsics.checkNotNullExpressionValue(title2, "getTitle(...)");
            uiTools2.addToPlaylistAsync(requireActivity3, uri2, true, title2);
            return true;
        } else if (itemId != R.id.play_all) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            View root = getBinding().getRoot();
            Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
            onFabPlayClick(root);
            return true;
        }
    }

    private final void addToScannedFolders(MediaWrapper mediaWrapper) {
        MedialibraryUtils medialibraryUtils = MedialibraryUtils.INSTANCE;
        String uri = mediaWrapper.getUri().toString();
        Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
        Context applicationContext = requireActivity().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        medialibraryUtils.addDir(uri, applicationContext);
        View root = getBinding().getRoot();
        int i = R.string.scanned_directory_added;
        String uri2 = mediaWrapper.getUri().toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        Snackbar.make(root, (CharSequence) getString(i, Uri.parse(uri2).getLastPathSegment()), 0).show();
    }

    private final Job toggleFavorite() {
        return BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new BaseBrowserFragment$toggleFavorite$1(this, (Continuation<? super BaseBrowserFragment$toggleFavorite$1>) null), 3, (Object) null);
    }

    public void onClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        if (KeyHelper.INSTANCE.isShiftPressed() || KeyHelper.INSTANCE.isCtrlPressed()) {
            onLongClick(view, i, mediaLibraryItem);
            return;
        }
        MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
        boolean z = false;
        if (getActionMode() == null) {
            mediaWrapper.removeFlags(8);
            if (mediaWrapper.getType() == 3) {
                browse(mediaWrapper, true);
                return;
            }
            String str = mediaWrapper.getType() == 0 ? SettingsKt.FORCE_PLAY_ALL_VIDEO : SettingsKt.FORCE_PLAY_ALL_AUDIO;
            Settings settings = Settings.INSTANCE;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
            SharedPreferences sharedPreferences = (SharedPreferences) settings.getInstance(requireContext);
            if (Intrinsics.areEqual((Object) str, (Object) SettingsKt.FORCE_PLAY_ALL_VIDEO) && Settings.INSTANCE.getTvUI()) {
                z = true;
            }
            if (!sharedPreferences.getBoolean(str, z)) {
                Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new BaseBrowserFragment$onClick$1(this, mediaLibraryItem, (Continuation<? super BaseBrowserFragment$onClick$1>) null), 3, (Object) null);
            } else {
                Job unused2 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new BaseBrowserFragment$onClick$2(this, view, i, (Continuation<? super BaseBrowserFragment$onClick$2>) null), 3, (Object) null);
            }
        } else if (mediaWrapper.getType() == 1 || mediaWrapper.getType() == 0 || mediaWrapper.getType() == 3) {
            MultiSelectHelper.toggleSelection$default(getAdapter().getMultiSelectHelper(), i, false, 2, (Object) null);
            invalidateActionMode();
        }
    }

    public boolean onLongClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        if (mediaLibraryItem.getItemType() != 32) {
            return false;
        }
        MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
        if (mediaWrapper.getType() == 1 || mediaWrapper.getType() == 0 || mediaWrapper.getType() == 3) {
            MultiSelectHelper.toggleSelection$default(getAdapter().getMultiSelectHelper(), i, false, 2, (Object) null);
            if (getActionMode() == null) {
                startActionMode();
            } else {
                invalidateActionMode();
            }
        } else {
            onCtxClick(view, i, mediaLibraryItem);
        }
        return true;
    }

    public void onCtxClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        if (getActionMode() == null && mediaLibraryItem.getItemType() == 32) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new BaseBrowserFragment$onCtxClick$1(mediaLibraryItem, this, i, (Continuation<? super BaseBrowserFragment$onCtxClick$1>) null), 3, (Object) null);
        }
    }

    /* access modifiers changed from: private */
    public final Object getMediaWithMeta(MediaWrapper mediaWrapper, Continuation<? super MediaWrapper> continuation) {
        if (!this.needToRefreshMeta) {
            return mediaWrapper;
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        return BuildersKt.withContext(Dispatchers.getIO(), new BaseBrowserFragment$getMediaWithMeta$$inlined$getFromMl$1(requireActivity, (Continuation) null, mediaWrapper), continuation);
    }

    public void onCtxAction(int i, ContextOption contextOption) {
        Intrinsics.checkNotNullParameter(contextOption, DuplicationWarningDialog.OPTION_KEY);
        MediaLibraryItem item = getAdapter().getItem(i);
        MediaWrapper mediaWrapper = item instanceof MediaWrapper ? (MediaWrapper) item : null;
        if (mediaWrapper != null) {
            switch (WhenMappings.$EnumSwitchMapping$0[contextOption.ordinal()]) {
                case 1:
                    Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new BaseBrowserFragment$onCtxAction$1(this, mediaWrapper, (Continuation<? super BaseBrowserFragment$onCtxAction$1>) null), 3, (Object) null);
                    return;
                case 2:
                    mediaWrapper.removeFlags(8);
                    playAll(mediaWrapper);
                    return;
                case 3:
                    Job unused2 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new BaseBrowserFragment$onCtxAction$2(this, mediaWrapper, (Continuation<? super BaseBrowserFragment$onCtxAction$2>) null), 3, (Object) null);
                    return;
                case 4:
                    removeItem(mediaWrapper);
                    return;
                case 5:
                    RenameDialog newInstance = RenameDialog.Companion.newInstance(mediaWrapper, true);
                    newInstance.show(requireActivity().getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(RenameDialog.class).getSimpleName());
                    newInstance.setListener(new BaseBrowserFragment$onCtxAction$3(this));
                    return;
                case 6:
                    UiTools uiTools = UiTools.INSTANCE;
                    FragmentActivity requireActivity = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                    uiTools.showMediaInfo(requireActivity, mediaWrapper);
                    return;
                case 7:
                    Job unused3 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new BaseBrowserFragment$onCtxAction$4(mediaWrapper, this, (Continuation<? super BaseBrowserFragment$onCtxAction$4>) null), 3, (Object) null);
                    return;
                case 8:
                    UiTools uiTools2 = UiTools.INSTANCE;
                    FragmentActivity requireActivity2 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                    MediaWrapper[] tracks = mediaWrapper.getTracks();
                    Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
                    uiTools2.addToPlaylist(requireActivity2, tracks, SavePlaylistDialog.KEY_NEW_TRACKS);
                    return;
                case 9:
                    MediaUtils mediaUtils = MediaUtils.INSTANCE;
                    FragmentActivity requireActivity3 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity(...)");
                    mediaUtils.getSubs(requireActivity3, mediaWrapper);
                    return;
                case 10:
                    Job unused4 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), (CoroutineStart) null, new BaseBrowserFragment$onCtxAction$5(this, mediaWrapper, (Continuation<? super BaseBrowserFragment$onCtxAction$5>) null), 2, (Object) null);
                    return;
                case 11:
                    addToScannedFolders(mediaWrapper);
                    return;
                case 12:
                    Intent intent = new Intent();
                    intent.setClassName(requireContext().getApplicationContext(), Constants.MOVIEPEDIA_ACTIVITY);
                    intent.putExtra(Constants.MOVIEPEDIA_MEDIA, mediaWrapper);
                    startActivity(intent);
                    return;
                case 13:
                    UiTools uiTools3 = UiTools.INSTANCE;
                    FragmentActivity requireActivity4 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity(...)");
                    String uri = mediaWrapper.getUri().toString();
                    Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
                    String title = mediaWrapper.getTitle();
                    Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
                    uiTools3.addToPlaylistAsync(requireActivity4, uri, false, title);
                    return;
                case 14:
                    UiTools uiTools4 = UiTools.INSTANCE;
                    FragmentActivity requireActivity5 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity5, "requireActivity(...)");
                    String uri2 = mediaWrapper.getUri().toString();
                    Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
                    String title2 = mediaWrapper.getTitle();
                    Intrinsics.checkNotNullExpressionValue(title2, "getTitle(...)");
                    uiTools4.addToPlaylistAsync(requireActivity5, uri2, true, title2);
                    return;
                default:
                    return;
            }
        }
    }

    public void onImageClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        if (getActionMode() != null) {
            onClick(view, i, mediaLibraryItem);
        } else {
            onLongClick(view, i, mediaLibraryItem);
        }
    }

    public void onUpdateFinished(RecyclerView.Adapter<?> adapter2) {
        Intrinsics.checkNotNullParameter(adapter2, "adapter");
        if (KotlinExtensionsKt.isStarted(this)) {
            restoreMultiSelectHelper();
            getSwipeRefreshLayout().setRefreshing(false);
            LifecycleAwareScheduler.startAction$default(getScheduler(), BaseBrowserFragmentKt.MSG_HIDE_LOADING, (Bundle) null, 2, (Object) null);
            updateEmptyView();
            if (!isRootDirectory()) {
                updateFab();
            }
        }
    }

    private final void updateFab() {
        String url;
        FloatingActionButton fabPlay = getFabPlay();
        if (fabPlay == null) {
            return;
        }
        if ((this instanceof StorageBrowserFragment) || (getAdapter().getMediaCount$vlc_android_release() <= 0 && ((url = ((BrowserModel) getViewModel()).getUrl()) == null || !StringsKt.startsWith$default(url, "file", false, 2, (Object) null)))) {
            setFabPlayVisibility(false);
            fabPlay.setOnClickListener((View.OnClickListener) null);
            return;
        }
        setFabPlayVisibility(true);
        fabPlay.setOnClickListener(new BaseBrowserFragment$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public static final void updateFab$lambda$16$lambda$15(BaseBrowserFragment baseBrowserFragment, View view) {
        Intrinsics.checkNotNullParameter(baseBrowserFragment, "this$0");
        Intrinsics.checkNotNull(view);
        baseBrowserFragment.onFabPlayClick(view);
    }

    public void onMediaPlayerEvent(MediaPlayer.Event event) {
        MediaWrapper currentMediaWrapper;
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
        if (event.type == 265 || event.type == 268) {
            PlaybackService value = PlaybackService.Companion.getServiceFlow().getValue();
            if (!(value == null || (currentMediaWrapper = value.getCurrentMediaWrapper()) == null)) {
                if (event.type == 268) {
                    BrowserModel browserModel = (BrowserModel) getViewModel();
                    PlaybackService value2 = PlaybackService.Companion.getServiceFlow().getValue();
                    browserModel.refreshMedia(currentMediaWrapper, value2 != null ? value2.getTime() : 0);
                }
                if (event.type == 265) {
                    Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new BaseBrowserFragment$onMediaPlayerEvent$1$1(this, currentMediaWrapper, (Continuation<? super BaseBrowserFragment$onMediaPlayerEvent$1$1>) null), 3, (Object) null);
                }
                getAdapter().notifyItemChanged(((BrowserModel) getViewModel()).getDataset().getList().indexOf(currentMediaWrapper), BaseBrowserAdapterKt.UPDATE_PROGRESS);
            }
            this.needToRefreshMeta = true;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001f\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/browser/BaseBrowserFragment$Companion;", "", "()V", "needRefresh", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getNeedRefresh", "()Landroidx/lifecycle/MutableLiveData;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BaseBrowserFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MutableLiveData<Boolean> getNeedRefresh() {
            return BaseBrowserFragment.needRefresh;
        }
    }
}
