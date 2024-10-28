package org.videolan.vlc.gui.video;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import org.videolan.medialibrary.EventTools;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.MediaParsingServiceKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.VideoGridBinding;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.browser.MediaBrowserFragment;
import org.videolan.vlc.gui.dialogs.ContextSheetKt;
import org.videolan.vlc.gui.dialogs.CtxActionReceiver;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;
import org.videolan.vlc.gui.dialogs.RenameDialog;
import org.videolan.vlc.gui.dialogs.SavePlaylistDialog;
import org.videolan.vlc.gui.helpers.AudioUtil;
import org.videolan.vlc.gui.helpers.ItemOffsetDecoration;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.view.AutoFitRecyclerView;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.media.MediaUtilsKt;
import org.videolan.vlc.media.PlaylistManager;
import org.videolan.vlc.providers.medialibrary.VideosProvider;
import org.videolan.vlc.util.AccessibilityHelperKt;
import org.videolan.vlc.util.BrowserutilsKt;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.util.FlagSet;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.viewmodels.mobile.VideoGroupingType;
import org.videolan.vlc.viewmodels.mobile.VideosViewModel;
import org.videolan.vlc.viewmodels.mobile.VideosViewModelKt;

@Metadata(d1 = {"\u0000Ö\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 c2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001cB\u0005¢\u0006\u0002\u0010\u0005J\u0016\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00130\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020$H\u0002J\u0010\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020'H\u0002J\u0010\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0012H\u0016J\b\u0010)\u001a\u00020*H\u0016J\u0018\u0010+\u001a\u00020\r2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0016J\u0018\u00100\u001a\u00020\u001c2\u0006\u00101\u001a\u00020'2\u0006\u0010.\u001a\u00020\u0013H\u0002J\u0012\u00102\u001a\u00020\u001c2\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0018\u00105\u001a\u00020\r2\u0006\u0010,\u001a\u00020-2\u0006\u00106\u001a\u000207H\u0016J$\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0018\u0010>\u001a\u00020\u001c2\u0006\u00101\u001a\u00020'2\u0006\u0010?\u001a\u00020@H\u0016J\b\u0010A\u001a\u00020\u001cH\u0016J\u0010\u0010B\u001a\u00020\u001c2\u0006\u0010,\u001a\u00020-H\u0016J\u0018\u0010C\u001a\u00020\u001c2\u0006\u0010D\u001a\u00020*2\u0006\u0010E\u001a\u00020FH\u0016J\u0010\u0010G\u001a\u00020\u001c2\u0006\u0010H\u001a\u000209H\u0016J\u0010\u0010I\u001a\u00020\u001c2\u0006\u00101\u001a\u00020'H\u0002J\u0010\u0010J\u001a\u00020\r2\u0006\u0010.\u001a\u00020/H\u0016J\u0018\u0010K\u001a\u00020\r2\u0006\u0010,\u001a\u00020-2\u0006\u00106\u001a\u000207H\u0016J\u0010\u0010L\u001a\u00020\u001c2\u0006\u00106\u001a\u000207H\u0016J\b\u0010M\u001a\u00020\u001cH\u0016J\b\u0010N\u001a\u00020\u001cH\u0016J\u0010\u0010O\u001a\u00020\u001c2\u0006\u0010P\u001a\u000204H\u0016J\b\u0010Q\u001a\u00020\u001cH\u0016J\b\u0010R\u001a\u00020\u001cH\u0016J\u001a\u0010S\u001a\u00020\u001c2\u0006\u0010H\u001a\u0002092\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0010\u0010T\u001a\u00020\u001c2\u0006\u0010U\u001a\u00020VH\u0002J\b\u0010W\u001a\u00020\u001cH\u0002J\u0010\u0010X\u001a\u00020\u001c2\u0006\u0010Y\u001a\u00020\rH\u0016J\u0010\u0010Z\u001a\u00020\u001c2\u0006\u0010[\u001a\u00020'H\u0016J\b\u0010\\\u001a\u00020\u001cH\u0002J\u0006\u0010]\u001a\u00020\u001cJ\b\u0010^\u001a\u00020\u001cH\u0002J\u0014\u0010_\u001a\u00020\u001c*\u00020`2\u0006\u0010.\u001a\u00020\u0013H\u0002J\f\u0010a\u001a\u00020\u001c*\u00020bH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\rXD¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX.¢\u0006\u0002\n\u0000¨\u0006d"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoGridFragment;", "Lorg/videolan/vlc/gui/browser/MediaBrowserFragment;", "Lorg/videolan/vlc/viewmodels/mobile/VideosViewModel;", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout$OnRefreshListener;", "Lorg/videolan/vlc/gui/dialogs/CtxActionReceiver;", "()V", "binding", "Lorg/videolan/vlc/databinding/VideoGridBinding;", "dataObserver", "Landroidx/recyclerview/widget/RecyclerView$AdapterDataObserver;", "gridItemDecoration", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "hasTabs", "", "getHasTabs", "()Z", "isMainNavigationPoint", "multiSelectHelper", "Lorg/videolan/tools/MultiSelectHelper;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "settings", "Landroid/content/SharedPreferences;", "thumbObs", "Landroidx/lifecycle/Observer;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "videoListAdapter", "Lorg/videolan/vlc/gui/video/VideoListAdapter;", "addToGroup", "", "selection", "", "banFolder", "folder", "Lorg/videolan/medialibrary/interfaces/media/Folder;", "changeGroupingType", "type", "Lorg/videolan/vlc/viewmodels/mobile/VideoGroupingType;", "checkFolderToParent", "count", "", "getMultiHelper", "getTitle", "", "onActionItemClicked", "mode", "Landroidx/appcompat/view/ActionMode;", "item", "Landroid/view/MenuItem;", "onClick", "position", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateActionMode", "menu", "Landroid/view/Menu;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onCtxAction", "option", "Lorg/videolan/vlc/util/ContextOption;", "onDestroy", "onDestroyActionMode", "onDisplaySettingChanged", "key", "value", "", "onFabPlayClick", "view", "onLongClick", "onOptionsItemSelected", "onPrepareActionMode", "onPrepareOptionsMenu", "onRefresh", "onResume", "onSaveInstanceState", "outState", "onStart", "onStop", "onViewCreated", "renameGroup", "media", "Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "setDataObservers", "setFabPlayVisibility", "enable", "sortBy", "sort", "updateEmptyView", "updateSeenMediaMarker", "updateViewMode", "open", "Landroidx/fragment/app/FragmentActivity;", "process", "Lorg/videolan/vlc/gui/video/VideoAction;", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoGridFragment.kt */
public final class VideoGridFragment extends MediaBrowserFragment<VideosViewModel> implements SwipeRefreshLayout.OnRefreshListener, CtxActionReceiver {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public VideoGridBinding binding;
    private RecyclerView.AdapterDataObserver dataObserver;
    private RecyclerView.ItemDecoration gridItemDecoration;
    private final boolean isMainNavigationPoint;
    /* access modifiers changed from: private */
    public MultiSelectHelper<MediaLibraryItem> multiSelectHelper;
    private SharedPreferences settings;
    private final Observer<MediaWrapper> thumbObs = new VideoGridFragment$$ExternalSyntheticLambda0(this);
    /* access modifiers changed from: private */
    public VideoListAdapter videoListAdapter;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoGridFragment.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        /* JADX WARNING: Can't wrap try/catch for region: R(63:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|69) */
        /* JADX WARNING: Can't wrap try/catch for region: R(64:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|69) */
        /* JADX WARNING: Can't wrap try/catch for region: R(65:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|69) */
        /* JADX WARNING: Can't wrap try/catch for region: R(66:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|60|61|62|63|64|65|66|67|69) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x003b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0055 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x005e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0067 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0071 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x007b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0085 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x008f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0099 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00a3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00ad */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00b7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00c1 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00cb */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00d5 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00df */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x00e9 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x00f3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x00fd */
        /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x0107 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x0111 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x011b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x0125 */
        static {
            /*
                org.videolan.vlc.viewmodels.mobile.VideoGroupingType[] r0 = org.videolan.vlc.viewmodels.mobile.VideoGroupingType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                r1 = 1
                org.videolan.vlc.viewmodels.mobile.VideoGroupingType r2 = org.videolan.vlc.viewmodels.mobile.VideoGroupingType.NONE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                r2 = 2
                org.videolan.vlc.viewmodels.mobile.VideoGroupingType r3 = org.videolan.vlc.viewmodels.mobile.VideoGroupingType.FOLDER     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                r3 = 3
                org.videolan.vlc.viewmodels.mobile.VideoGroupingType r4 = org.videolan.vlc.viewmodels.mobile.VideoGroupingType.NAME     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r0[r4] = r3     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                org.videolan.vlc.util.ContextOption[] r0 = org.videolan.vlc.util.ContextOption.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.util.ContextOption r4 = org.videolan.vlc.util.ContextOption.CTX_PLAY_FROM_START     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_PLAY_AS_AUDIO     // Catch:{ NoSuchFieldError -> 0x003b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003b }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003b }
            L_0x003b:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_PLAY_ALL     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_PLAY     // Catch:{ NoSuchFieldError -> 0x004c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004c }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004c }
            L_0x004c:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_INFORMATION     // Catch:{ NoSuchFieldError -> 0x0055 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0055 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0055 }
            L_0x0055:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_DELETE     // Catch:{ NoSuchFieldError -> 0x005e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005e }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005e }
            L_0x005e:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_APPEND     // Catch:{ NoSuchFieldError -> 0x0067 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0067 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0067 }
            L_0x0067:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_SET_RINGTONE     // Catch:{ NoSuchFieldError -> 0x0071 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0071 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0071 }
            L_0x0071:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_PLAY_NEXT     // Catch:{ NoSuchFieldError -> 0x007b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007b }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007b }
            L_0x007b:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_DOWNLOAD_SUBTITLES     // Catch:{ NoSuchFieldError -> 0x0085 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0085 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0085 }
            L_0x0085:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_ADD_TO_PLAYLIST     // Catch:{ NoSuchFieldError -> 0x008f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008f }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x008f }
            L_0x008f:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_FIND_METADATA     // Catch:{ NoSuchFieldError -> 0x0099 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0099 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0099 }
            L_0x0099:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_SHARE     // Catch:{ NoSuchFieldError -> 0x00a3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a3 }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a3 }
            L_0x00a3:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_REMOVE_GROUP     // Catch:{ NoSuchFieldError -> 0x00ad }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ad }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00ad }
            L_0x00ad:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_ADD_GROUP     // Catch:{ NoSuchFieldError -> 0x00b7 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b7 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b7 }
            L_0x00b7:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_GROUP_SIMILAR     // Catch:{ NoSuchFieldError -> 0x00c1 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c1 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c1 }
            L_0x00c1:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_MARK_AS_PLAYED     // Catch:{ NoSuchFieldError -> 0x00cb }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cb }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cb }
            L_0x00cb:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_MARK_AS_UNPLAYED     // Catch:{ NoSuchFieldError -> 0x00d5 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d5 }
                r2 = 18
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d5 }
            L_0x00d5:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_FAV_ADD     // Catch:{ NoSuchFieldError -> 0x00df }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00df }
                r2 = 19
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00df }
            L_0x00df:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_FAV_REMOVE     // Catch:{ NoSuchFieldError -> 0x00e9 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e9 }
                r2 = 20
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e9 }
            L_0x00e9:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_GO_TO_FOLDER     // Catch:{ NoSuchFieldError -> 0x00f3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f3 }
                r2 = 21
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00f3 }
            L_0x00f3:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_ADD_SHORTCUT     // Catch:{ NoSuchFieldError -> 0x00fd }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00fd }
                r2 = 22
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00fd }
            L_0x00fd:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_MARK_ALL_AS_PLAYED     // Catch:{ NoSuchFieldError -> 0x0107 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0107 }
                r2 = 23
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0107 }
            L_0x0107:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_MARK_ALL_AS_UNPLAYED     // Catch:{ NoSuchFieldError -> 0x0111 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0111 }
                r2 = 24
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0111 }
            L_0x0111:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_BAN_FOLDER     // Catch:{ NoSuchFieldError -> 0x011b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x011b }
                r2 = 25
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x011b }
            L_0x011b:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_RENAME_GROUP     // Catch:{ NoSuchFieldError -> 0x0125 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0125 }
                r2 = 26
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0125 }
            L_0x0125:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_UNGROUP     // Catch:{ NoSuchFieldError -> 0x012f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x012f }
                r2 = 27
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x012f }
            L_0x012f:
                $EnumSwitchMapping$1 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoGridFragment.WhenMappings.<clinit>():void");
        }
    }

    public boolean isMainNavigationPoint() {
        return this.isMainNavigationPoint;
    }

    public boolean getHasTabs() {
        return getParentFragment() != null;
    }

    /* access modifiers changed from: private */
    public final void open(FragmentActivity fragmentActivity, MediaLibraryItem mediaLibraryItem) {
        Intent intent = new Intent(getActivity(), SecondaryActivity.class);
        intent.putExtra(SecondaryActivity.KEY_FRAGMENT, SecondaryActivity.VIDEO_GROUP_LIST);
        if (mediaLibraryItem instanceof Folder) {
            intent.putExtra(Constants.KEY_FOLDER, mediaLibraryItem);
        } else if (mediaLibraryItem instanceof VideoGroup) {
            intent.putExtra(Constants.KEY_GROUP, mediaLibraryItem);
        }
        fragmentActivity.startActivityForResult(intent, 3);
    }

    public void onCreate(Bundle bundle) {
        Folder folder;
        VideoGroup videoGroup;
        VideoGroupingType videoGroupingType;
        Parcelable parcelable;
        Parcelable parcelable2;
        Parcelable parcelable3;
        Parcelable parcelable4;
        super.onCreate(bundle);
        if (this.settings == null) {
            Settings settings2 = Settings.INSTANCE;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
            this.settings = (SharedPreferences) settings2.getInstance(requireContext);
        }
        if (this.videoListAdapter == null) {
            SharedPreferences sharedPreferences = this.settings;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("settings");
                sharedPreferences = null;
            }
            boolean z = sharedPreferences.getBoolean("media_seen", true);
            Settings settings3 = Settings.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            VideoListAdapter videoListAdapter2 = new VideoListAdapter(z, true ^ ((SharedPreferences) settings3.getInstance(requireActivity)).getBoolean(SettingsKt.PLAYBACK_HISTORY, true));
            videoListAdapter2.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
            this.videoListAdapter = videoListAdapter2;
            this.dataObserver = KextensionsKt.onAnyChange(videoListAdapter2, new VideoGridFragment$onCreate$2(this));
            VideoListAdapter videoListAdapter3 = this.videoListAdapter;
            if (videoListAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
                videoListAdapter3 = null;
            }
            this.multiSelectHelper = videoListAdapter3.getMultiSelectHelper();
            if (bundle != null) {
                if (Build.VERSION.SDK_INT >= 33) {
                    parcelable4 = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(bundle, Constants.KEY_FOLDER, Folder.class);
                } else {
                    Parcelable parcelable5 = bundle.getParcelable(Constants.KEY_FOLDER);
                    if (!(parcelable5 instanceof Folder)) {
                        parcelable5 = null;
                    }
                    parcelable4 = (Folder) parcelable5;
                }
                folder = (Folder) parcelable4;
            } else {
                Bundle arguments = getArguments();
                if (arguments != null) {
                    if (Build.VERSION.SDK_INT >= 33) {
                        parcelable3 = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(arguments, Constants.KEY_FOLDER, Folder.class);
                    } else {
                        Parcelable parcelable6 = arguments.getParcelable(Constants.KEY_FOLDER);
                        if (!(parcelable6 instanceof Folder)) {
                            parcelable6 = null;
                        }
                        parcelable3 = (Folder) parcelable6;
                    }
                    folder = (Folder) parcelable3;
                } else {
                    folder = null;
                }
            }
            if (bundle != null) {
                if (Build.VERSION.SDK_INT >= 33) {
                    parcelable2 = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(bundle, Constants.KEY_GROUP, VideoGroup.class);
                } else {
                    Parcelable parcelable7 = bundle.getParcelable(Constants.KEY_GROUP);
                    if (!(parcelable7 instanceof VideoGroup)) {
                        parcelable7 = null;
                    }
                    parcelable2 = (VideoGroup) parcelable7;
                }
                videoGroup = (VideoGroup) parcelable2;
            } else {
                Bundle arguments2 = getArguments();
                if (arguments2 != null) {
                    if (Build.VERSION.SDK_INT >= 33) {
                        parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(arguments2, Constants.KEY_GROUP, VideoGroup.class);
                    } else {
                        Parcelable parcelable8 = arguments2.getParcelable(Constants.KEY_GROUP);
                        if (!(parcelable8 instanceof VideoGroup)) {
                            parcelable8 = null;
                        }
                        parcelable = (VideoGroup) parcelable8;
                    }
                    videoGroup = (VideoGroup) parcelable;
                } else {
                    videoGroup = null;
                }
            }
            if (videoGroup == null && folder == null) {
                Settings settings4 = Settings.INSTANCE;
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext(...)");
                String string = ((SharedPreferences) settings4.getInstance(requireContext2)).getString(Constants.KEY_GROUP_VIDEOS, (String) null);
                if (string == null) {
                    string = Constants.GROUP_VIDEOS_NAME;
                }
                int hashCode = string.hashCode();
                if (hashCode != 48) {
                    if (hashCode == 1444 && string.equals(Constants.GROUP_VIDEOS_NONE)) {
                        videoGroupingType = VideoGroupingType.NONE;
                    }
                } else if (string.equals(Constants.GROUP_VIDEOS_FOLDER)) {
                    videoGroupingType = VideoGroupingType.FOLDER;
                }
                videoGroupingType = VideoGroupingType.NAME;
            } else {
                videoGroupingType = VideoGroupingType.NONE;
            }
            setViewModel(VideosViewModelKt.getViewModel(this, videoGroupingType, folder, videoGroup));
            setDataObservers();
            LifecycleOwner lifecycleOwner = this;
            EventTools.getInstance().lastThumb.observe(lifecycleOwner, this.thumbObs);
            VideoListAdapter videoListAdapter4 = this.videoListAdapter;
            if (videoListAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
                videoListAdapter4 = null;
            }
            KextensionsKt.launchWhenStarted(FlowKt.onEach(videoListAdapter4.getEvents(), new VideoGridFragment$onCreate$3(this, (Continuation<? super VideoGridFragment$onCreate$3>) null)), LifecycleOwnerKt.getLifecycleScope(lifecycleOwner));
        }
    }

    private final void setDataObservers() {
        VideoListAdapter videoListAdapter2 = this.videoListAdapter;
        if (videoListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
            videoListAdapter2 = null;
        }
        videoListAdapter2.setDataType(((VideosViewModel) getViewModel()).getGroupingType());
        LifecycleOwner lifecycleOwner = this;
        ((VideosViewModel) getViewModel()).getProvider().getLoading().observe(lifecycleOwner, new VideoGridFragmentKt$sam$androidx_lifecycle_Observer$0(new VideoGridFragment$setDataObservers$1(this)));
        VideoListAdapter videoListAdapter3 = this.videoListAdapter;
        if (videoListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
            videoListAdapter3 = null;
        }
        videoListAdapter3.getShowFilename().set(((VideosViewModel) getViewModel()).getGroupingType() == VideoGroupingType.NONE && ((VideosViewModel) getViewModel()).getProvider().getSort() == 10);
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new VideoGridFragment$setDataObservers$2(this, (Continuation<? super VideoGridFragment$setDataObservers$2>) null), 3, (Object) null);
        EventTools.getInstance().lastThumb.observe(lifecycleOwner, new VideoGridFragmentKt$sam$androidx_lifecycle_Observer$0(new VideoGridFragment$setDataObservers$3(this)));
    }

    public void onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.onPrepareOptionsMenu(menu);
        MenuItem findItem = menu.findItem(R.id.ml_menu_last_playlist);
        SharedPreferences sharedPreferences = this.settings;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences = null;
        }
        findItem.setVisible(sharedPreferences.contains("media_list"));
        menu.findItem(R.id.rename_group).setVisible(((VideosViewModel) getViewModel()).getGroup() != null);
        menu.findItem(R.id.ungroup).setVisible(((VideosViewModel) getViewModel()).getGroup() != null);
        menu.findItem(R.id.ml_menu_sortby).setVisible(false);
        menu.findItem(R.id.ml_menu_display_options).setVisible(true);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        if (AccessibilityHelperKt.isTalkbackIsEnabled(requireActivity)) {
            menu.findItem(R.id.play_all).setVisible(true);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: android.content.SharedPreferences} */
    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r5v2 */
    /* JADX WARNING: type inference failed for: r5v3, types: [org.videolan.vlc.databinding.VideoGridBinding] */
    /* JADX WARNING: type inference failed for: r5v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onOptionsItemSelected(android.view.MenuItem r23) {
        /*
            r22 = this;
            r0 = r22
            java.lang.String r1 = "item"
            r2 = r23
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            int r1 = r23.getItemId()
            int r3 = org.videolan.vlc.R.id.ml_menu_last_playlist
            r4 = 1
            if (r1 != r3) goto L_0x001f
            org.videolan.vlc.media.MediaUtils r1 = org.videolan.vlc.media.MediaUtils.INSTANCE
            androidx.fragment.app.FragmentActivity r2 = r22.getActivity()
            android.content.Context r2 = (android.content.Context) r2
            r1.loadlastPlaylist(r2, r4)
            goto L_0x0174
        L_0x001f:
            int r3 = org.videolan.vlc.R.id.rename_group
            if (r1 != r3) goto L_0x0034
            org.videolan.vlc.viewmodels.SortableModel r1 = r22.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r1 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r1
            org.videolan.medialibrary.interfaces.media.VideoGroup r1 = r1.getGroup()
            if (r1 == 0) goto L_0x0174
            r0.renameGroup(r1)
            goto L_0x0174
        L_0x0034:
            int r3 = org.videolan.vlc.R.id.ungroup
            r5 = 0
            if (r1 != r3) goto L_0x0060
            org.videolan.vlc.viewmodels.SortableModel r1 = r22.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r1 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r1
            org.videolan.medialibrary.interfaces.media.VideoGroup r1 = r1.getGroup()
            if (r1 == 0) goto L_0x0174
            r2 = r0
            androidx.lifecycle.LifecycleOwner r2 = (androidx.lifecycle.LifecycleOwner) r2
            androidx.lifecycle.LifecycleCoroutineScope r2 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r2)
            r6 = r2
            kotlinx.coroutines.CoroutineScope r6 = (kotlinx.coroutines.CoroutineScope) r6
            org.videolan.vlc.gui.video.VideoGridFragment$onOptionsItemSelected$2$1 r2 = new org.videolan.vlc.gui.video.VideoGridFragment$onOptionsItemSelected$2$1
            r2.<init>(r0, r1, r5)
            r9 = r2
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            r10 = 3
            r11 = 0
            r7 = 0
            r8 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r6, r7, r8, r9, r10, r11)
            goto L_0x0174
        L_0x0060:
            int r3 = org.videolan.vlc.R.id.play_all
            if (r1 != r3) goto L_0x007d
            org.videolan.vlc.databinding.VideoGridBinding r1 = r0.binding
            if (r1 != 0) goto L_0x006e
            java.lang.String r1 = "binding"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L_0x006f
        L_0x006e:
            r5 = r1
        L_0x006f:
            android.view.View r1 = r5.getRoot()
            java.lang.String r2 = "getRoot(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r0.onFabPlayClick(r1)
            goto L_0x0174
        L_0x007d:
            int r3 = org.videolan.vlc.R.id.ml_menu_display_options
            if (r1 != r3) goto L_0x0175
            java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
            r2 = 10
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r6 = 7
            java.lang.Integer r7 = java.lang.Integer.valueOf(r6)
            r8 = 9
            java.lang.Integer r9 = java.lang.Integer.valueOf(r8)
            r10 = 2
            java.lang.Integer r11 = java.lang.Integer.valueOf(r10)
            r12 = 5
            java.lang.Integer r13 = java.lang.Integer.valueOf(r12)
            r14 = 4
            java.lang.Integer r15 = java.lang.Integer.valueOf(r14)
            r16 = 6
            java.lang.Integer r17 = java.lang.Integer.valueOf(r16)
            r18 = 15
            java.lang.Integer r18 = java.lang.Integer.valueOf(r18)
            r19 = 3
            java.lang.Integer r20 = java.lang.Integer.valueOf(r19)
            java.lang.Integer[] r2 = new java.lang.Integer[r2]
            r21 = 0
            r2[r21] = r1
            r2[r4] = r3
            r2[r10] = r7
            r2[r19] = r9
            r2[r14] = r11
            r2[r12] = r13
            r2[r16] = r15
            r2[r6] = r17
            r1 = 8
            r2[r1] = r18
            r2[r8] = r20
            java.util.ArrayList r1 = kotlin.collections.CollectionsKt.arrayListOf(r2)
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r1 = r1.iterator()
        L_0x00e2:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0107
            java.lang.Object r3 = r1.next()
            r6 = r3
            java.lang.Number r6 = (java.lang.Number) r6
            int r6 = r6.intValue()
            org.videolan.vlc.viewmodels.SortableModel r7 = r22.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r7 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r7
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider r7 = r7.getProvider()
            boolean r6 = r7.canSortBy(r6)
            if (r6 == 0) goto L_0x00e2
            r2.add(r3)
            goto L_0x00e2
        L_0x0107:
            r11 = r2
            java.util.List r11 = (java.util.List) r11
            org.videolan.vlc.gui.dialogs.DisplaySettingsDialog$Companion r7 = org.videolan.vlc.gui.dialogs.DisplaySettingsDialog.Companion
            android.content.SharedPreferences r1 = r0.settings
            java.lang.String r2 = "settings"
            if (r1 != 0) goto L_0x0116
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r1 = r5
        L_0x0116:
            java.lang.String r3 = "video_display_in_cards"
            boolean r8 = r1.getBoolean(r3, r4)
            org.videolan.vlc.viewmodels.SortableModel r1 = r22.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r1 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r1
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider r1 = r1.getProvider()
            boolean r1 = r1.getOnlyFavorites()
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r1)
            org.videolan.vlc.viewmodels.SortableModel r1 = r22.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r1 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r1
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider r1 = r1.getProvider()
            int r12 = r1.getSort()
            org.videolan.vlc.viewmodels.SortableModel r1 = r22.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r1 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r1
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider r1 = r1.getProvider()
            boolean r13 = r1.getDesc()
            android.content.SharedPreferences r1 = r0.settings
            if (r1 != 0) goto L_0x0152
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            goto L_0x0153
        L_0x0152:
            r5 = r1
        L_0x0153:
            java.lang.String r1 = "video_min_group_length"
            java.lang.String r2 = "6"
            java.lang.String r14 = r5.getString(r1, r2)
            r17 = 386(0x182, float:5.41E-43)
            r18 = 0
            r9 = 0
            r15 = 0
            r16 = 0
            org.videolan.vlc.gui.dialogs.DisplaySettingsDialog r1 = org.videolan.vlc.gui.dialogs.DisplaySettingsDialog.Companion.newInstance$default(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            androidx.fragment.app.FragmentActivity r2 = r22.requireActivity()
            androidx.fragment.app.FragmentManager r2 = r2.getSupportFragmentManager()
            java.lang.String r3 = "DisplaySettingsDialog"
            r1.show((androidx.fragment.app.FragmentManager) r2, (java.lang.String) r3)
        L_0x0174:
            return r4
        L_0x0175:
            boolean r1 = super.onOptionsItemSelected(r23)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoGridFragment.onOptionsItemSelected(android.view.MenuItem):boolean");
    }

    public void sortBy(int i) {
        VideoListAdapter videoListAdapter2 = this.videoListAdapter;
        if (videoListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
            videoListAdapter2 = null;
        }
        videoListAdapter2.getShowFilename().set(i == 10);
        super.sortBy(i);
    }

    private final void changeGroupingType(VideoGroupingType videoGroupingType) {
        LifecycleOwner lifecycleOwner = this;
        ((VideosViewModel) getViewModel()).getProvider().getPagedList().removeObservers(lifecycleOwner);
        ((VideosViewModel) getViewModel()).getProvider().getLoading().removeObservers(lifecycleOwner);
        ((VideosViewModel) getViewModel()).changeGroupingType$vlc_android_release(videoGroupingType);
        setDataObservers();
        FragmentActivity activity = getActivity();
        AppCompatActivity appCompatActivity = activity instanceof AppCompatActivity ? (AppCompatActivity) activity : null;
        if (appCompatActivity != null) {
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle(appCompatActivity.getTitle());
            }
            appCompatActivity.invalidateOptionsMenu();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        VideoGridBinding inflate = VideoGridBinding.inflate(layoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        View root = inflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    /* JADX WARNING: type inference failed for: r6v17, types: [androidx.fragment.app.Fragment] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onViewCreated(android.view.View r6, android.os.Bundle r7) {
        /*
            r5 = this;
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            super.onViewCreated(r6, r7)
            org.videolan.vlc.viewmodels.SortableModel r6 = r5.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r6 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r6
            boolean r6 = r6.isEmpty()
            org.videolan.vlc.databinding.VideoGridBinding r7 = r5.binding
            java.lang.String r0 = "binding"
            r1 = 0
            if (r7 != 0) goto L_0x001d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r7 = r1
        L_0x001d:
            org.videolan.vlc.gui.view.EmptyLoadingStateView r7 = r7.emptyLoading
            if (r6 == 0) goto L_0x0024
            org.videolan.vlc.gui.view.EmptyLoadingState r2 = org.videolan.vlc.gui.view.EmptyLoadingState.LOADING
            goto L_0x0026
        L_0x0024:
            org.videolan.vlc.gui.view.EmptyLoadingState r2 = org.videolan.vlc.gui.view.EmptyLoadingState.NONE
        L_0x0026:
            r7.setState(r2)
            org.videolan.vlc.databinding.VideoGridBinding r7 = r5.binding
            if (r7 != 0) goto L_0x0031
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r7 = r1
        L_0x0031:
            r7.setEmpty(r6)
            org.videolan.vlc.databinding.VideoGridBinding r6 = r5.binding
            if (r6 != 0) goto L_0x003c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r6 = r1
        L_0x003c:
            org.videolan.vlc.gui.view.EmptyLoadingStateView r6 = r6.emptyLoading
            org.videolan.vlc.gui.video.VideoGridFragment$onViewCreated$1 r7 = new org.videolan.vlc.gui.video.VideoGridFragment$onViewCreated$1
            r7.<init>(r5)
            kotlin.jvm.functions.Function0 r7 = (kotlin.jvm.functions.Function0) r7
            r6.setOnNoMediaClickListener(r7)
            org.videolan.vlc.gui.view.SwipeRefreshLayout r6 = r5.getSwipeRefreshLayout()
            r7 = r5
            androidx.swiperefreshlayout.widget.SwipeRefreshLayout$OnRefreshListener r7 = (androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener) r7
            r6.setOnRefreshListener(r7)
            org.videolan.vlc.databinding.VideoGridBinding r6 = r5.binding
            if (r6 != 0) goto L_0x005a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r6 = r1
        L_0x005a:
            org.videolan.vlc.gui.view.AutoFitRecyclerView r6 = r6.videoGrid
            org.videolan.vlc.gui.video.VideoListAdapter r7 = r5.videoListAdapter
            if (r7 != 0) goto L_0x0066
            java.lang.String r7 = "videoListAdapter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r7 = r1
        L_0x0066:
            androidx.recyclerview.widget.RecyclerView$Adapter r7 = (androidx.recyclerview.widget.RecyclerView.Adapter) r7
            r6.setAdapter(r7)
            org.videolan.vlc.databinding.VideoGridBinding r6 = r5.binding
            if (r6 != 0) goto L_0x0073
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r6 = r1
        L_0x0073:
            org.videolan.vlc.gui.view.FastScroller r6 = r6.fastScroller
            androidx.fragment.app.FragmentActivity r7 = r5.requireActivity()
            int r2 = org.videolan.vlc.R.id.appbar
            android.view.View r7 = r7.findViewById(r2)
            java.lang.String r2 = "null cannot be cast to non-null type com.google.android.material.appbar.AppBarLayout"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7, r2)
            com.google.android.material.appbar.AppBarLayout r7 = (com.google.android.material.appbar.AppBarLayout) r7
            androidx.fragment.app.FragmentActivity r2 = r5.requireActivity()
            int r3 = org.videolan.vlc.R.id.coordinator
            android.view.View r2 = r2.findViewById(r3)
            java.lang.String r3 = "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r3)
            androidx.coordinatorlayout.widget.CoordinatorLayout r2 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r2
            androidx.fragment.app.FragmentActivity r3 = r5.requireActivity()
            int r4 = org.videolan.vlc.R.id.fab
            android.view.View r3 = r3.findViewById(r4)
            java.lang.String r4 = "null cannot be cast to non-null type com.google.android.material.floatingactionbutton.FloatingActionButton"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r4)
            com.google.android.material.floatingactionbutton.FloatingActionButton r3 = (com.google.android.material.floatingactionbutton.FloatingActionButton) r3
            r6.attachToCoordinator(r7, r2, r3)
            org.videolan.vlc.databinding.VideoGridBinding r6 = r5.binding
            if (r6 != 0) goto L_0x00b3
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r6 = r1
        L_0x00b3:
            org.videolan.vlc.gui.view.FastScroller r6 = r6.fastScroller
            org.videolan.vlc.databinding.VideoGridBinding r7 = r5.binding
            if (r7 != 0) goto L_0x00bd
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r7 = r1
        L_0x00bd:
            org.videolan.vlc.gui.view.AutoFitRecyclerView r7 = r7.videoGrid
            java.lang.String r0 = "videoGrid"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)
            androidx.recyclerview.widget.RecyclerView r7 = (androidx.recyclerview.widget.RecyclerView) r7
            org.videolan.vlc.viewmodels.SortableModel r0 = r5.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r0 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r0
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider r0 = r0.getProvider()
            org.videolan.resources.util.HeaderProvider r0 = (org.videolan.resources.util.HeaderProvider) r0
            r6.setRecyclerView(r7, r0)
            androidx.fragment.app.Fragment r6 = r5.getParentFragment()
            boolean r7 = r6 instanceof org.videolan.vlc.gui.video.VideoBrowserFragment
            if (r7 == 0) goto L_0x00e0
            r1 = r6
            org.videolan.vlc.gui.video.VideoBrowserFragment r1 = (org.videolan.vlc.gui.video.VideoBrowserFragment) r1
        L_0x00e0:
            if (r1 != 0) goto L_0x00e3
            goto L_0x00f4
        L_0x00e3:
            org.videolan.vlc.viewmodels.SortableModel r6 = r5.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r6 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r6
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider r6 = r6.getProvider()
            boolean r6 = r6.getOnlyFavorites()
            r1.setVideoGridOnlyFavorites(r6)
        L_0x00f4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoGridFragment.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.content.SharedPreferences} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: org.videolan.vlc.gui.video.VideoBrowserFragment} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: android.content.SharedPreferences} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDisplaySettingChanged(java.lang.String r4, java.lang.Object r5) {
        /*
            r3 = this;
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "value"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            int r0 = r4.hashCode()
            java.lang.String r1 = "settings"
            r2 = 0
            switch(r0) {
                case -341247322: goto L_0x00c9;
                case 292773227: goto L_0x008e;
                case 1109124122: goto L_0x0068;
                case 1468888228: goto L_0x0016;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x00e4
        L_0x0016:
            java.lang.String r0 = "current_sort"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L_0x0020
            goto L_0x00e4
        L_0x0020:
            kotlin.Pair r5 = (kotlin.Pair) r5
            org.videolan.vlc.viewmodels.SortableModel r4 = r3.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r4 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r4
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider r4 = r4.getProvider()
            java.lang.Object r0 = r5.getFirst()
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            r4.setSort(r0)
            org.videolan.vlc.viewmodels.SortableModel r4 = r3.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r4 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r4
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider r4 = r4.getProvider()
            java.lang.Object r5 = r5.getSecond()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            r4.setDesc(r5)
            org.videolan.vlc.viewmodels.SortableModel r4 = r3.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r4 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r4
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider r4 = r4.getProvider()
            r4.saveSort()
            org.videolan.vlc.viewmodels.SortableModel r4 = r3.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r4 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r4
            r4.refresh()
            goto L_0x00e4
        L_0x0068:
            java.lang.String r0 = "show_video_groups"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L_0x0072
            goto L_0x00e4
        L_0x0072:
            org.videolan.vlc.gui.dialogs.DisplaySettingsDialog$VideoGroup r5 = (org.videolan.vlc.gui.dialogs.DisplaySettingsDialog.VideoGroup) r5
            android.content.SharedPreferences r4 = r3.settings
            if (r4 != 0) goto L_0x007c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L_0x007d
        L_0x007c:
            r2 = r4
        L_0x007d:
            java.lang.String r4 = "video_min_group_length"
            java.lang.String r0 = r5.getValue()
            org.videolan.tools.SettingsKt.putSingle(r2, r4, r0)
            org.videolan.vlc.viewmodels.mobile.VideoGroupingType r4 = r5.getType()
            r3.changeGroupingType(r4)
            goto L_0x00e4
        L_0x008e:
            java.lang.String r0 = "only_favs"
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L_0x0097
            goto L_0x00e4
        L_0x0097:
            org.videolan.vlc.viewmodels.SortableModel r4 = r3.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r4 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r4
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider r4 = r4.getProvider()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r0 = r5.booleanValue()
            r4.showOnlyFavs(r0)
            org.videolan.vlc.viewmodels.SortableModel r4 = r3.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r4 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r4
            r4.refresh()
            androidx.fragment.app.Fragment r4 = r3.getParentFragment()
            boolean r0 = r4 instanceof org.videolan.vlc.gui.video.VideoBrowserFragment
            if (r0 == 0) goto L_0x00be
            r2 = r4
            org.videolan.vlc.gui.video.VideoBrowserFragment r2 = (org.videolan.vlc.gui.video.VideoBrowserFragment) r2
        L_0x00be:
            if (r2 != 0) goto L_0x00c1
            goto L_0x00e4
        L_0x00c1:
            boolean r4 = r5.booleanValue()
            r2.setVideoGridOnlyFavorites(r4)
            goto L_0x00e4
        L_0x00c9:
            java.lang.String r0 = "display_in_cards"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00e4
            android.content.SharedPreferences r4 = r3.settings
            if (r4 != 0) goto L_0x00d9
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L_0x00da
        L_0x00d9:
            r2 = r4
        L_0x00da:
            java.lang.String r4 = "video_display_in_cards"
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            org.videolan.tools.SettingsKt.putSingle(r2, r4, r5)
            r3.updateViewMode()
        L_0x00e4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoGridFragment.onDisplaySettingChanged(java.lang.String, java.lang.Object):void");
    }

    public void onResume() {
        super.onResume();
        updateEmptyView();
    }

    public void onStart() {
        super.onStart();
        VideoGridBinding videoGridBinding = this.binding;
        if (videoGridBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            videoGridBinding = null;
        }
        registerForContextMenu(videoGridBinding.videoGrid);
        updateViewMode();
        setFabPlayVisibility(true);
        FloatingActionButton fabPlay = getFabPlay();
        if (fabPlay != null) {
            fabPlay.setImageResource(R.drawable.ic_fab_play);
        }
        FloatingActionButton fabPlay2 = getFabPlay();
        if (fabPlay2 != null) {
            fabPlay2.setContentDescription(getString(R.string.play));
        }
        if (!((VideosViewModel) getViewModel()).isEmpty() && getFilterQuery() == null) {
            ((VideosViewModel) getViewModel()).refresh();
        }
    }

    public void onStop() {
        super.onStop();
        VideoGridBinding videoGridBinding = this.binding;
        if (videoGridBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            videoGridBinding = null;
        }
        unregisterForContextMenu(videoGridBinding.videoGrid);
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(Constants.KEY_FOLDER, ((VideosViewModel) getViewModel()).getFolder());
        bundle.putParcelable(Constants.KEY_GROUP, ((VideosViewModel) getViewModel()).getGroup());
        bundle.putSerializable(Constants.KEY_GROUPING, ((VideosViewModel) getViewModel()).getGroupingType());
    }

    public void onDestroy() {
        super.onDestroy();
        RecyclerView.AdapterDataObserver adapterDataObserver = null;
        this.gridItemDecoration = null;
        getSwipeRefreshLayout().setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) null);
        if (this.dataObserver != null) {
            VideoListAdapter videoListAdapter2 = this.videoListAdapter;
            if (videoListAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
                videoListAdapter2 = null;
            }
            RecyclerView.AdapterDataObserver adapterDataObserver2 = this.dataObserver;
            if (adapterDataObserver2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dataObserver");
            } else {
                adapterDataObserver = adapterDataObserver2;
            }
            videoListAdapter2.unregisterAdapterDataObserver(adapterDataObserver);
        }
    }

    public String getTitle() {
        String displayTitle;
        int i = WhenMappings.$EnumSwitchMapping$0[((VideosViewModel) getViewModel()).getGroupingType().ordinal()];
        if (i == 1) {
            Folder folder = ((VideosViewModel) getViewModel()).getFolder();
            if (folder != null && (displayTitle = folder.getDisplayTitle()) != null) {
                return displayTitle;
            }
            VideoGroup group = ((VideosViewModel) getViewModel()).getGroup();
            String displayTitle2 = group != null ? group.getDisplayTitle() : null;
            if (displayTitle2 != null) {
                return displayTitle2;
            }
            String string = getString(R.string.videos);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        } else if (i == 2) {
            String string2 = getString(R.string.videos_folders_title);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            return string2;
        } else if (i == 3) {
            String string3 = getString(R.string.videos_groups_title);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            return string3;
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    public MultiSelectHelper<VideosViewModel> getMultiHelper() {
        VideoListAdapter videoListAdapter2 = this.videoListAdapter;
        if (videoListAdapter2 == null) {
            return null;
        }
        if (videoListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
            videoListAdapter2 = null;
        }
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper2 = videoListAdapter2.getMultiSelectHelper();
        if (multiSelectHelper2 instanceof MultiSelectHelper) {
            return multiSelectHelper2;
        }
        return null;
    }

    private final void updateViewMode() {
        if (getView() == null || getActivity() == null) {
            Log.w("VLC/VideoListFragment", "Unable to setup the view");
            return;
        }
        Resources resources = getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "getResources(...)");
        if (this.gridItemDecoration == null) {
            Resources resources2 = getResources();
            Intrinsics.checkNotNullExpressionValue(resources2, "getResources(...)");
            this.gridItemDecoration = new ItemOffsetDecoration(resources2, R.dimen.left_right_1610_margin, R.dimen.top_bottom_1610_margin);
        }
        SharedPreferences sharedPreferences = this.settings;
        VideoListAdapter videoListAdapter2 = null;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences = null;
        }
        int i = 1;
        boolean z = !sharedPreferences.getBoolean(Constants.KEY_VIDEOS_CARDS, true);
        VideoGridBinding videoGridBinding = this.binding;
        if (videoGridBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            videoGridBinding = null;
        }
        AutoFitRecyclerView autoFitRecyclerView = videoGridBinding.videoGrid;
        RecyclerView.ItemDecoration itemDecoration = this.gridItemDecoration;
        Intrinsics.checkNotNull(itemDecoration);
        autoFitRecyclerView.removeItemDecoration(itemDecoration);
        if (!z) {
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.grid_card_thumb_width);
            VideoGridBinding videoGridBinding2 = this.binding;
            if (videoGridBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                videoGridBinding2 = null;
            }
            int paddingStart = videoGridBinding2.videoGrid.getPaddingStart();
            VideoGridBinding videoGridBinding3 = this.binding;
            if (videoGridBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                videoGridBinding3 = null;
            }
            int paddingEnd = paddingStart + videoGridBinding3.videoGrid.getPaddingEnd();
            VideoGridBinding videoGridBinding4 = this.binding;
            if (videoGridBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                videoGridBinding4 = null;
            }
            int perfectColumnWidth = videoGridBinding4.videoGrid.getPerfectColumnWidth(dimensionPixelSize, paddingEnd) - (resources.getDimensionPixelSize(R.dimen.left_right_1610_margin) * 2);
            VideoGridBinding videoGridBinding5 = this.binding;
            if (videoGridBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                videoGridBinding5 = null;
            }
            videoGridBinding5.videoGrid.setColumnWidth(perfectColumnWidth);
            VideoGridBinding videoGridBinding6 = this.binding;
            if (videoGridBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                videoGridBinding6 = null;
            }
            AutoFitRecyclerView autoFitRecyclerView2 = videoGridBinding6.videoGrid;
            RecyclerView.ItemDecoration itemDecoration2 = this.gridItemDecoration;
            Intrinsics.checkNotNull(itemDecoration2);
            autoFitRecyclerView2.addItemDecoration(itemDecoration2);
            VideoGridBinding videoGridBinding7 = this.binding;
            if (videoGridBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                videoGridBinding7 = null;
            }
            videoGridBinding7.videoGrid.setPadding(KotlinExtensionsKt.getDp(4), KotlinExtensionsKt.getDp(4), KotlinExtensionsKt.getDp(4), KotlinExtensionsKt.getDp(4));
        } else {
            VideoGridBinding videoGridBinding8 = this.binding;
            if (videoGridBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                videoGridBinding8 = null;
            }
            videoGridBinding8.videoGrid.setPadding(0, 0, 0, 0);
        }
        VideoGridBinding videoGridBinding9 = this.binding;
        if (videoGridBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            videoGridBinding9 = null;
        }
        AutoFitRecyclerView autoFitRecyclerView3 = videoGridBinding9.videoGrid;
        if (!z) {
            i = -1;
        }
        autoFitRecyclerView3.setNumColumns(i);
        VideoListAdapter videoListAdapter3 = this.videoListAdapter;
        if (videoListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
        } else {
            videoListAdapter2 = videoListAdapter3;
        }
        videoListAdapter2.setListMode(z);
    }

    public void onFabPlayClick(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        VideosViewModel.playAll$vlc_android_release$default((VideosViewModel) getViewModel(), getActivity(), 0, 2, (Object) null);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0104  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void updateEmptyView() {
        /*
            r10 = this;
            org.videolan.vlc.databinding.VideoGridBinding r0 = r10.binding
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            boolean r0 = r10.isAdded()
            if (r0 != 0) goto L_0x000c
            return
        L_0x000c:
            org.videolan.vlc.viewmodels.SortableModel r0 = r10.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r0 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r0
            boolean r0 = r0.isEmpty()
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 == 0) goto L_0x0035
            org.videolan.vlc.gui.video.VideoListAdapter r0 = r10.videoListAdapter
            if (r0 != 0) goto L_0x0025
            java.lang.String r0 = "videoListAdapter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = r3
        L_0x0025:
            androidx.paging.PagedList r0 = r0.getCurrentList()
            java.util.Collection r0 = (java.util.Collection) r0
            if (r0 == 0) goto L_0x0033
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0035
        L_0x0033:
            r0 = 1
            goto L_0x0036
        L_0x0035:
            r0 = 0
        L_0x0036:
            org.videolan.vlc.viewmodels.SortableModel r4 = r10.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r4 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r4
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider r4 = r4.getProvider()
            androidx.lifecycle.MutableLiveData r4 = r4.getLoading()
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            r4 = r4 ^ r1
            org.videolan.vlc.databinding.VideoGridBinding r5 = r10.binding
            java.lang.String r6 = "binding"
            if (r5 != 0) goto L_0x005b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r5 = r3
        L_0x005b:
            org.videolan.vlc.gui.view.EmptyLoadingStateView r5 = r5.emptyLoading
            org.videolan.vlc.viewmodels.SortableModel r7 = r10.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r7 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r7
            java.lang.String r7 = r7.getFilterQuery()
            if (r7 == 0) goto L_0x0074
            int r8 = org.videolan.vlc.R.string.empty_search
            java.lang.Object[] r9 = new java.lang.Object[r1]
            r9[r2] = r7
            java.lang.String r7 = r10.getString(r8, r9)
            goto L_0x0075
        L_0x0074:
            r7 = r3
        L_0x0075:
            if (r7 != 0) goto L_0x0093
            org.videolan.vlc.viewmodels.SortableModel r7 = r10.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r7 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r7
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider r7 = r7.getProvider()
            boolean r7 = r7.getOnlyFavorites()
            if (r7 == 0) goto L_0x008a
            int r7 = org.videolan.vlc.R.string.nofav
            goto L_0x008c
        L_0x008a:
            int r7 = org.videolan.vlc.R.string.nomedia
        L_0x008c:
            java.lang.String r7 = r10.getString(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
        L_0x0093:
            r5.setEmptyText(r7)
            org.videolan.vlc.databinding.VideoGridBinding r5 = r10.binding
            if (r5 != 0) goto L_0x009e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r5 = r3
        L_0x009e:
            org.videolan.vlc.gui.view.EmptyLoadingStateView r5 = r5.emptyLoading
            org.videolan.vlc.util.Permissions r7 = org.videolan.vlc.util.Permissions.INSTANCE
            org.videolan.resources.AppContextProvider r8 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.Context r8 = r8.getAppContext()
            boolean r7 = r7.canReadStorage(r8)
            if (r7 != 0) goto L_0x00b3
            if (r0 == 0) goto L_0x00b3
            org.videolan.vlc.gui.view.EmptyLoadingState r7 = org.videolan.vlc.gui.view.EmptyLoadingState.MISSING_PERMISSION
            goto L_0x00f9
        L_0x00b3:
            if (r0 == 0) goto L_0x00ba
            if (r4 == 0) goto L_0x00ba
            org.videolan.vlc.gui.view.EmptyLoadingState r7 = org.videolan.vlc.gui.view.EmptyLoadingState.LOADING
            goto L_0x00f9
        L_0x00ba:
            if (r0 == 0) goto L_0x00d1
            if (r4 != 0) goto L_0x00d1
            org.videolan.vlc.viewmodels.SortableModel r7 = r10.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r7 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r7
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider r7 = r7.getProvider()
            boolean r7 = r7.getOnlyFavorites()
            if (r7 == 0) goto L_0x00d1
            org.videolan.vlc.gui.view.EmptyLoadingState r7 = org.videolan.vlc.gui.view.EmptyLoadingState.EMPTY_FAVORITES
            goto L_0x00f9
        L_0x00d1:
            if (r0 == 0) goto L_0x00e4
            if (r4 != 0) goto L_0x00e4
            org.videolan.vlc.viewmodels.SortableModel r7 = r10.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r7 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r7
            java.lang.String r7 = r7.getFilterQuery()
            if (r7 != 0) goto L_0x00e4
            org.videolan.vlc.gui.view.EmptyLoadingState r7 = org.videolan.vlc.gui.view.EmptyLoadingState.EMPTY
            goto L_0x00f9
        L_0x00e4:
            if (r0 == 0) goto L_0x00f7
            if (r4 != 0) goto L_0x00f7
            org.videolan.vlc.viewmodels.SortableModel r7 = r10.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r7 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r7
            java.lang.String r7 = r7.getFilterQuery()
            if (r7 == 0) goto L_0x00f7
            org.videolan.vlc.gui.view.EmptyLoadingState r7 = org.videolan.vlc.gui.view.EmptyLoadingState.EMPTY_SEARCH
            goto L_0x00f9
        L_0x00f7:
            org.videolan.vlc.gui.view.EmptyLoadingState r7 = org.videolan.vlc.gui.view.EmptyLoadingState.NONE
        L_0x00f9:
            r5.setState(r7)
            org.videolan.vlc.databinding.VideoGridBinding r5 = r10.binding
            if (r5 != 0) goto L_0x0104
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            goto L_0x0105
        L_0x0104:
            r3 = r5
        L_0x0105:
            if (r0 == 0) goto L_0x010a
            if (r4 != 0) goto L_0x010a
            goto L_0x010b
        L_0x010a:
            r1 = 0
        L_0x010b:
            r3.setEmpty(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoGridFragment.updateEmptyView():void");
    }

    public void onRefresh() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            MediaParsingServiceKt.reloadLibrary(activity);
        }
    }

    public void setFabPlayVisibility(boolean z) {
        super.setFabPlayVisibility(!((VideosViewModel) getViewModel()).isEmpty() && z);
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        Intrinsics.checkNotNullParameter(actionMode, RtspHeaders.Values.MODE);
        Intrinsics.checkNotNullParameter(menu, "menu");
        int i = WhenMappings.$EnumSwitchMapping$0[((VideosViewModel) getViewModel()).getGroupingType().ordinal()];
        if (i == 1) {
            actionMode.getMenuInflater().inflate(R.menu.action_mode_video, menu);
        } else if (i == 2) {
            actionMode.getMenuInflater().inflate(R.menu.action_mode_folder, menu);
        } else if (i == 3) {
            actionMode.getMenuInflater().inflate(R.menu.action_mode_video_group, menu);
        }
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper2 = this.multiSelectHelper;
        VideoListAdapter videoListAdapter2 = null;
        if (multiSelectHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
            multiSelectHelper2 = null;
        }
        VideoListAdapter videoListAdapter3 = this.videoListAdapter;
        if (videoListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
        } else {
            videoListAdapter2 = videoListAdapter3;
        }
        multiSelectHelper2.toggleActionMode(true, videoListAdapter2.getItemCount());
        return true;
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        Intrinsics.checkNotNullParameter(actionMode, RtspHeaders.Values.MODE);
        Intrinsics.checkNotNullParameter(menu, "menu");
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper2 = this.multiSelectHelper;
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper3 = null;
        if (multiSelectHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
            multiSelectHelper2 = null;
        }
        int selectionCount = multiSelectHelper2.getSelectionCount();
        boolean z5 = false;
        if (selectionCount == 0) {
            stopActionMode();
            return false;
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideoGridFragment$onPrepareActionMode$1(this, actionMode, (Continuation<? super VideoGridFragment$onPrepareActionMode$1>) null), 3, (Object) null);
        int i = WhenMappings.$EnumSwitchMapping$0[((VideosViewModel) getViewModel()).getGroupingType().ordinal()];
        if (i == 1) {
            menu.findItem(R.id.action_video_append).setVisible(PlaylistManager.Companion.hasMedia());
            menu.findItem(R.id.action_video_info).setVisible(selectionCount == 1);
            menu.findItem(R.id.action_remove_from_group).setVisible(((VideosViewModel) getViewModel()).getGroup() != null);
            menu.findItem(R.id.action_add_to_group).setVisible(((VideosViewModel) getViewModel()).getGroup() != null && selectionCount > 1);
            menu.findItem(R.id.action_mode_go_to_folder).setVisible(checkFolderToParent(selectionCount));
        } else if (i == 3) {
            MenuItem findItem = menu.findItem(R.id.action_ungroup);
            MultiSelectHelper<MediaLibraryItem> multiSelectHelper4 = this.multiSelectHelper;
            if (multiSelectHelper4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
                multiSelectHelper4 = null;
            }
            Iterable selection = multiSelectHelper4.getSelection();
            if (!(selection instanceof Collection) || !((Collection) selection).isEmpty()) {
                Iterator it = selection.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (!(((MediaLibraryItem) it.next()) instanceof VideoGroup)) {
                            z2 = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            z2 = false;
            findItem.setVisible(!z2);
            MenuItem findItem2 = menu.findItem(R.id.action_rename);
            if (selectionCount != 1) {
                z3 = false;
            } else {
                MultiSelectHelper<MediaLibraryItem> multiSelectHelper5 = this.multiSelectHelper;
                if (multiSelectHelper5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
                    multiSelectHelper5 = null;
                }
                Iterable selection2 = multiSelectHelper5.getSelection();
                if (!(selection2 instanceof Collection) || !((Collection) selection2).isEmpty()) {
                    Iterator it2 = selection2.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (!(((MediaLibraryItem) it2.next()) instanceof VideoGroup)) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                z3 = true;
            }
            findItem2.setVisible(z3);
            MenuItem findItem3 = menu.findItem(R.id.action_group_similar);
            if (selectionCount == 1) {
                MultiSelectHelper<MediaLibraryItem> multiSelectHelper6 = this.multiSelectHelper;
                if (multiSelectHelper6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
                    multiSelectHelper6 = null;
                }
                Collection arrayList = new ArrayList();
                for (Object next : multiSelectHelper6.getSelection()) {
                    if (next instanceof VideoGroup) {
                        arrayList.add(next);
                    }
                }
                if (((List) arrayList).isEmpty()) {
                    z4 = true;
                    findItem3.setVisible(z4);
                    menu.findItem(R.id.action_mode_go_to_folder).setVisible(checkFolderToParent(selectionCount));
                }
            }
            z4 = false;
            findItem3.setVisible(z4);
            menu.findItem(R.id.action_mode_go_to_folder).setVisible(checkFolderToParent(selectionCount));
        }
        MenuItem findItem4 = menu.findItem(R.id.action_mode_favorite_add);
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper7 = this.multiSelectHelper;
        if (multiSelectHelper7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
            multiSelectHelper7 = null;
        }
        Iterable selection3 = multiSelectHelper7.getSelection();
        if (!(selection3 instanceof Collection) || !((Collection) selection3).isEmpty()) {
            Iterator it3 = selection3.iterator();
            while (true) {
                if (it3.hasNext()) {
                    if (((MediaLibraryItem) it3.next()).isFavorite()) {
                        z = false;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        z = true;
        findItem4.setVisible(z);
        MenuItem findItem5 = menu.findItem(R.id.action_mode_favorite_remove);
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper8 = this.multiSelectHelper;
        if (multiSelectHelper8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
        } else {
            multiSelectHelper3 = multiSelectHelper8;
        }
        Iterable selection4 = multiSelectHelper3.getSelection();
        if (!(selection4 instanceof Collection) || !((Collection) selection4).isEmpty()) {
            Iterator it4 = selection4.iterator();
            while (true) {
                if (it4.hasNext()) {
                    if (!((MediaLibraryItem) it4.next()).isFavorite()) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        z5 = true;
        findItem5.setVisible(z5);
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: org.videolan.medialibrary.interfaces.media.MediaWrapper} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean checkFolderToParent(int r5) {
        /*
            r4 = this;
            r0 = 0
            r1 = 1
            if (r5 != r1) goto L_0x0032
            org.videolan.tools.MultiSelectHelper<org.videolan.medialibrary.media.MediaLibraryItem> r5 = r4.multiSelectHelper
            r2 = 0
            if (r5 != 0) goto L_0x000f
            java.lang.String r5 = "multiSelectHelper"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r5 = r2
        L_0x000f:
            java.util.List r5 = r5.getSelection()
            java.lang.Object r5 = kotlin.collections.CollectionsKt.firstOrNull(r5)
            boolean r3 = r5 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r3 == 0) goto L_0x001e
            r2 = r5
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r2
        L_0x001e:
            if (r2 == 0) goto L_0x0032
            int r5 = r2.getType()
            if (r5 == 0) goto L_0x0027
            goto L_0x0032
        L_0x0027:
            android.net.Uri r5 = r2.getUri()
            android.net.Uri r5 = org.videolan.tools.KotlinExtensionsKt.retrieveParent(r5)
            if (r5 == 0) goto L_0x0032
            r0 = 1
        L_0x0032:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoGridFragment.checkFolderToParent(int):boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: org.videolan.medialibrary.interfaces.media.MediaWrapper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v40, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: org.videolan.medialibrary.interfaces.media.MediaWrapper} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onActionItemClicked(androidx.appcompat.view.ActionMode r19, android.view.MenuItem r20) {
        /*
            r18 = this;
            r0 = r18
            java.lang.String r1 = "mode"
            r2 = r19
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            java.lang.String r1 = "item"
            r2 = r20
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            r1 = r0
            androidx.lifecycle.LifecycleOwner r1 = (androidx.lifecycle.LifecycleOwner) r1
            boolean r3 = org.videolan.tools.KotlinExtensionsKt.isStarted(r1)
            r4 = 0
            if (r3 != 0) goto L_0x001b
            return r4
        L_0x001b:
            org.videolan.vlc.viewmodels.SortableModel r3 = r18.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r3 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r3
            org.videolan.vlc.viewmodels.mobile.VideoGroupingType r3 = r3.getGroupingType()
            int[] r5 = org.videolan.vlc.gui.video.VideoGridFragment.WhenMappings.$EnumSwitchMapping$0
            int r3 = r3.ordinal()
            r3 = r5[r3]
            java.lang.String r5 = "multiSelectHelper"
            r6 = 1
            r7 = 0
            if (r3 == r6) goto L_0x0215
            r8 = 2
            if (r3 == r8) goto L_0x0157
            r8 = 3
            if (r3 == r8) goto L_0x003b
            goto L_0x037a
        L_0x003b:
            org.videolan.tools.MultiSelectHelper<org.videolan.medialibrary.media.MediaLibraryItem> r3 = r0.multiSelectHelper
            if (r3 != 0) goto L_0x0043
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r3 = r7
        L_0x0043:
            java.util.List r8 = r3.getSelection()
            int r2 = r20.getItemId()
            int r3 = org.videolan.vlc.R.id.action_videogroup_play
            if (r2 != r3) goto L_0x006c
            org.videolan.vlc.media.MediaUtils r1 = org.videolan.vlc.media.MediaUtils.INSTANCE
            androidx.fragment.app.FragmentActivity r2 = r18.getActivity()
            android.content.Context r2 = (android.content.Context) r2
            r12 = 7
            r13 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            java.util.List r11 = org.videolan.vlc.media.MediaUtilsKt.getAll$default(r8, r9, r10, r11, r12, r13)
            r14 = 8
            r15 = 0
            r12 = 0
            r13 = 0
            r9 = r1
            r10 = r2
            org.videolan.vlc.media.MediaUtils.openList$default(r9, r10, r11, r12, r13, r14, r15)
            goto L_0x037a
        L_0x006c:
            int r3 = org.videolan.vlc.R.id.action_videogroup_append
            if (r2 != r3) goto L_0x0086
            org.videolan.vlc.media.MediaUtils r1 = org.videolan.vlc.media.MediaUtils.INSTANCE
            androidx.fragment.app.FragmentActivity r2 = r18.getActivity()
            android.content.Context r2 = (android.content.Context) r2
            r12 = 7
            r13 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            java.util.List r3 = org.videolan.vlc.media.MediaUtilsKt.getAll$default(r8, r9, r10, r11, r12, r13)
            r1.appendMedia((android.content.Context) r2, (java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper>) r3)
            goto L_0x037a
        L_0x0086:
            int r3 = org.videolan.vlc.R.id.action_videogroup_add_playlist
            if (r2 != r3) goto L_0x00a2
            androidx.lifecycle.LifecycleCoroutineScope r1 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r1)
            r9 = r1
            kotlinx.coroutines.CoroutineScope r9 = (kotlinx.coroutines.CoroutineScope) r9
            org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$7 r1 = new org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$7
            r1.<init>(r0, r8, r7)
            r12 = r1
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            r13 = 3
            r14 = 0
            r10 = 0
            r11 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r9, r10, r11, r12, r13, r14)
            goto L_0x037a
        L_0x00a2:
            int r3 = org.videolan.vlc.R.id.action_group_similar
            if (r2 != r3) goto L_0x00be
            androidx.lifecycle.LifecycleCoroutineScope r1 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r1)
            r9 = r1
            kotlinx.coroutines.CoroutineScope r9 = (kotlinx.coroutines.CoroutineScope) r9
            org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$8 r1 = new org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$8
            r1.<init>(r0, r8, r7)
            r12 = r1
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            r13 = 3
            r14 = 0
            r10 = 0
            r11 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r9, r10, r11, r12, r13, r14)
            goto L_0x037a
        L_0x00be:
            int r3 = org.videolan.vlc.R.id.action_ungroup
            java.lang.String r5 = "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.VideoGroup"
            if (r2 != r3) goto L_0x00d8
            org.videolan.vlc.viewmodels.SortableModel r1 = r18.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r1 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r1
            java.lang.Object r2 = kotlin.collections.CollectionsKt.first(r8)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r5)
            org.videolan.medialibrary.interfaces.media.VideoGroup r2 = (org.videolan.medialibrary.interfaces.media.VideoGroup) r2
            r1.ungroup((org.videolan.medialibrary.interfaces.media.VideoGroup) r2)
            goto L_0x037a
        L_0x00d8:
            int r3 = org.videolan.vlc.R.id.action_rename
            if (r2 != r3) goto L_0x00ea
            java.lang.Object r1 = kotlin.collections.CollectionsKt.first(r8)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r5)
            org.videolan.medialibrary.interfaces.media.VideoGroup r1 = (org.videolan.medialibrary.interfaces.media.VideoGroup) r1
            r0.renameGroup(r1)
            goto L_0x037a
        L_0x00ea:
            int r3 = org.videolan.vlc.R.id.action_add_to_group
            if (r2 != r3) goto L_0x00f3
            r0.addToGroup(r8)
            goto L_0x037a
        L_0x00f3:
            int r3 = org.videolan.vlc.R.id.action_mode_go_to_folder
            if (r2 != r3) goto L_0x010c
            java.lang.Object r1 = kotlin.collections.CollectionsKt.first(r8)
            boolean r2 = r1 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r2 == 0) goto L_0x0102
            r7 = r1
            org.videolan.medialibrary.interfaces.media.MediaWrapper r7 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r7
        L_0x0102:
            if (r7 == 0) goto L_0x037a
            r1 = r0
            androidx.fragment.app.Fragment r1 = (androidx.fragment.app.Fragment) r1
            org.videolan.vlc.util.KextensionsKt.showParentFolder(r1, r7)
            goto L_0x037a
        L_0x010c:
            int r3 = org.videolan.vlc.R.id.action_video_delete
            if (r2 != r3) goto L_0x011e
            r12 = 7
            r13 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            java.util.List r1 = org.videolan.vlc.media.MediaUtilsKt.getAll$default(r8, r9, r10, r11, r12, r13)
            r0.removeItems(r1)
            goto L_0x037a
        L_0x011e:
            int r3 = org.videolan.vlc.R.id.action_mode_favorite_add
            if (r2 != r3) goto L_0x013a
            androidx.lifecycle.LifecycleCoroutineScope r1 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r1)
            r9 = r1
            kotlinx.coroutines.CoroutineScope r9 = (kotlinx.coroutines.CoroutineScope) r9
            org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$10 r1 = new org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$10
            r1.<init>(r0, r8, r7)
            r12 = r1
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            r13 = 3
            r14 = 0
            r10 = 0
            r11 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r9, r10, r11, r12, r13, r14)
            goto L_0x037a
        L_0x013a:
            int r3 = org.videolan.vlc.R.id.action_mode_favorite_remove
            if (r2 != r3) goto L_0x0156
            androidx.lifecycle.LifecycleCoroutineScope r1 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r1)
            r9 = r1
            kotlinx.coroutines.CoroutineScope r9 = (kotlinx.coroutines.CoroutineScope) r9
            org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$11 r1 = new org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$11
            r1.<init>(r0, r8, r7)
            r12 = r1
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            r13 = 3
            r14 = 0
            r10 = 0
            r11 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r9, r10, r11, r12, r13, r14)
            goto L_0x037a
        L_0x0156:
            return r4
        L_0x0157:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            org.videolan.tools.MultiSelectHelper<org.videolan.medialibrary.media.MediaLibraryItem> r8 = r0.multiSelectHelper
            if (r8 != 0) goto L_0x0164
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r8 = r7
        L_0x0164:
            java.util.List r5 = r8.getSelection()
            java.util.Iterator r5 = r5.iterator()
        L_0x016c:
            boolean r8 = r5.hasNext()
            if (r8 == 0) goto L_0x0183
            java.lang.Object r8 = r5.next()
            org.videolan.medialibrary.media.MediaLibraryItem r8 = (org.videolan.medialibrary.media.MediaLibraryItem) r8
            java.lang.String r9 = "null cannot be cast to non-null type org.videolan.medialibrary.media.FolderImpl"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8, r9)
            org.videolan.medialibrary.media.FolderImpl r8 = (org.videolan.medialibrary.media.FolderImpl) r8
            r3.add(r8)
            goto L_0x016c
        L_0x0183:
            int r2 = r20.getItemId()
            int r5 = org.videolan.vlc.R.id.action_folder_play
            if (r2 != r5) goto L_0x0198
            org.videolan.vlc.viewmodels.SortableModel r1 = r18.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r1 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r1
            java.util.List r3 = (java.util.List) r3
            r1.playFoldersSelection$vlc_android_release(r3)
            goto L_0x037a
        L_0x0198:
            int r5 = org.videolan.vlc.R.id.action_folder_append
            if (r2 != r5) goto L_0x01a9
            org.videolan.vlc.viewmodels.SortableModel r1 = r18.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r1 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r1
            java.util.List r3 = (java.util.List) r3
            r1.appendFoldersSelection$vlc_android_release(r3)
            goto L_0x037a
        L_0x01a9:
            int r5 = org.videolan.vlc.R.id.action_folder_add_playlist
            if (r2 != r5) goto L_0x01c5
            androidx.lifecycle.LifecycleCoroutineScope r1 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r1)
            r8 = r1
            kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8
            org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$4 r1 = new org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$4
            r1.<init>(r0, r3, r7)
            r11 = r1
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            r12 = 3
            r13 = 0
            r9 = 0
            r10 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r8, r9, r10, r11, r12, r13)
            goto L_0x037a
        L_0x01c5:
            int r5 = org.videolan.vlc.R.id.action_video_delete
            if (r2 != r5) goto L_0x01dc
            r8 = r3
            java.util.List r8 = (java.util.List) r8
            r13 = 15
            r14 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            java.util.List r1 = org.videolan.vlc.media.MediaUtilsKt.getAll$default((java.util.List) r8, (int) r9, (int) r10, (boolean) r11, (boolean) r12, (int) r13, (java.lang.Object) r14)
            r0.removeItems(r1)
            goto L_0x037a
        L_0x01dc:
            int r5 = org.videolan.vlc.R.id.action_mode_favorite_add
            if (r2 != r5) goto L_0x01f8
            androidx.lifecycle.LifecycleCoroutineScope r1 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r1)
            r8 = r1
            kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8
            org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$5 r1 = new org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$5
            r1.<init>(r0, r3, r7)
            r11 = r1
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            r12 = 3
            r13 = 0
            r9 = 0
            r10 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r8, r9, r10, r11, r12, r13)
            goto L_0x037a
        L_0x01f8:
            int r5 = org.videolan.vlc.R.id.action_mode_favorite_remove
            if (r2 != r5) goto L_0x0214
            androidx.lifecycle.LifecycleCoroutineScope r1 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r1)
            r8 = r1
            kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8
            org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$6 r1 = new org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$6
            r1.<init>(r0, r3, r7)
            r11 = r1
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            r12 = 3
            r13 = 0
            r9 = 0
            r10 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r8, r9, r10, r11, r12, r13)
            goto L_0x037a
        L_0x0214:
            return r4
        L_0x0215:
            org.videolan.tools.MultiSelectHelper<org.videolan.medialibrary.media.MediaLibraryItem> r3 = r0.multiSelectHelper
            if (r3 != 0) goto L_0x021d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r3 = r7
        L_0x021d:
            java.util.List r3 = r3.getSelection()
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.ArrayList r5 = new java.util.ArrayList
            r8 = 10
            int r8 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r3, r8)
            r5.<init>(r8)
            java.util.Collection r5 = (java.util.Collection) r5
            java.util.Iterator r3 = r3.iterator()
        L_0x0234:
            boolean r8 = r3.hasNext()
            if (r8 == 0) goto L_0x024b
            java.lang.Object r8 = r3.next()
            org.videolan.medialibrary.media.MediaLibraryItem r8 = (org.videolan.medialibrary.media.MediaLibraryItem) r8
            java.lang.String r9 = "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8, r9)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r8
            r5.add(r8)
            goto L_0x0234
        L_0x024b:
            r11 = r5
            java.util.List r11 = (java.util.List) r11
            r3 = r11
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            r3 = r3 ^ r6
            if (r3 == 0) goto L_0x037a
            int r2 = r20.getItemId()
            int r3 = org.videolan.vlc.R.id.action_video_play
            if (r2 != r3) goto L_0x0273
            org.videolan.vlc.media.MediaUtils r9 = org.videolan.vlc.media.MediaUtils.INSTANCE
            androidx.fragment.app.FragmentActivity r1 = r18.getActivity()
            r10 = r1
            android.content.Context r10 = (android.content.Context) r10
            r14 = 8
            r15 = 0
            r12 = 0
            r13 = 0
            org.videolan.vlc.media.MediaUtils.openList$default(r9, r10, r11, r12, r13, r14, r15)
            goto L_0x037a
        L_0x0273:
            int r3 = org.videolan.vlc.R.id.action_video_append
            if (r2 != r3) goto L_0x0284
            org.videolan.vlc.media.MediaUtils r1 = org.videolan.vlc.media.MediaUtils.INSTANCE
            androidx.fragment.app.FragmentActivity r2 = r18.getActivity()
            android.content.Context r2 = (android.content.Context) r2
            r1.appendMedia((android.content.Context) r2, (java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper>) r11)
            goto L_0x037a
        L_0x0284:
            int r3 = org.videolan.vlc.R.id.action_video_share
            java.lang.String r5 = "requireActivity(...)"
            if (r2 != r3) goto L_0x0296
            androidx.fragment.app.FragmentActivity r1 = r18.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r5)
            org.videolan.vlc.util.KextensionsKt.share((androidx.fragment.app.FragmentActivity) r1, (java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper>) r11)
            goto L_0x037a
        L_0x0296:
            int r3 = org.videolan.vlc.R.id.action_video_info
            if (r2 != r3) goto L_0x02a5
            java.lang.Object r1 = kotlin.collections.CollectionsKt.first(r11)
            org.videolan.medialibrary.media.MediaLibraryItem r1 = (org.videolan.medialibrary.media.MediaLibraryItem) r1
            r0.showInfoDialog(r1)
            goto L_0x037a
        L_0x02a5:
            int r3 = org.videolan.vlc.R.id.action_video_download_subtitles
            if (r2 != r3) goto L_0x02b7
            org.videolan.vlc.media.MediaUtils r1 = org.videolan.vlc.media.MediaUtils.INSTANCE
            androidx.fragment.app.FragmentActivity r2 = r18.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)
            r1.getSubs((androidx.fragment.app.FragmentActivity) r2, (java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper>) r11)
            goto L_0x037a
        L_0x02b7:
            int r3 = org.videolan.vlc.R.id.action_video_play_audio
            if (r2 != r3) goto L_0x02e4
            java.util.Iterator r1 = r11.iterator()
        L_0x02bf:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x02d1
            java.lang.Object r2 = r1.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r2
            r3 = 8
            r2.addFlags(r3)
            goto L_0x02bf
        L_0x02d1:
            org.videolan.vlc.media.MediaUtils r9 = org.videolan.vlc.media.MediaUtils.INSTANCE
            androidx.fragment.app.FragmentActivity r1 = r18.getActivity()
            r10 = r1
            android.content.Context r10 = (android.content.Context) r10
            r14 = 8
            r15 = 0
            r12 = 0
            r13 = 0
            org.videolan.vlc.media.MediaUtils.openList$default(r9, r10, r11, r12, r13, r14, r15)
            goto L_0x037a
        L_0x02e4:
            int r3 = org.videolan.vlc.R.id.action_mode_audio_add_playlist
            if (r2 != r3) goto L_0x02f6
            org.videolan.vlc.gui.helpers.UiTools r1 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            androidx.fragment.app.FragmentActivity r2 = r18.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)
            r1.addToPlaylist(r2, r11)
            goto L_0x037a
        L_0x02f6:
            int r3 = org.videolan.vlc.R.id.action_video_delete
            if (r2 != r3) goto L_0x02ff
            r0.removeItems(r11)
            goto L_0x037a
        L_0x02ff:
            int r3 = org.videolan.vlc.R.id.action_remove_from_group
            if (r2 != r3) goto L_0x030e
            org.videolan.vlc.viewmodels.SortableModel r1 = r18.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r1 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r1
            r1.removeFromGroup((java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper>) r11)
            goto L_0x037a
        L_0x030e:
            int r3 = org.videolan.vlc.R.id.action_ungroup
            if (r2 != r3) goto L_0x031c
            org.videolan.vlc.viewmodels.SortableModel r1 = r18.getViewModel()
            org.videolan.vlc.viewmodels.mobile.VideosViewModel r1 = (org.videolan.vlc.viewmodels.mobile.VideosViewModel) r1
            r1.ungroup((java.util.List<? extends org.videolan.medialibrary.media.MediaLibraryItem>) r11)
            goto L_0x037a
        L_0x031c:
            int r3 = org.videolan.vlc.R.id.action_add_to_group
            if (r2 != r3) goto L_0x0324
            r0.addToGroup(r11)
            goto L_0x037a
        L_0x0324:
            int r3 = org.videolan.vlc.R.id.action_mode_go_to_folder
            if (r2 != r3) goto L_0x033c
            java.lang.Object r1 = kotlin.collections.CollectionsKt.first(r11)
            boolean r2 = r1 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r2 == 0) goto L_0x0333
            r7 = r1
            org.videolan.medialibrary.interfaces.media.MediaWrapper r7 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r7
        L_0x0333:
            if (r7 == 0) goto L_0x037a
            r1 = r0
            androidx.fragment.app.Fragment r1 = (androidx.fragment.app.Fragment) r1
            org.videolan.vlc.util.KextensionsKt.showParentFolder(r1, r7)
            goto L_0x037a
        L_0x033c:
            int r3 = org.videolan.vlc.R.id.action_mode_favorite_add
            if (r2 != r3) goto L_0x0359
            androidx.lifecycle.LifecycleCoroutineScope r1 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r1)
            r12 = r1
            kotlinx.coroutines.CoroutineScope r12 = (kotlinx.coroutines.CoroutineScope) r12
            org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$2 r1 = new org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$2
            r1.<init>(r0, r11, r7)
            r15 = r1
            kotlin.jvm.functions.Function2 r15 = (kotlin.jvm.functions.Function2) r15
            r16 = 3
            r17 = 0
            r13 = 0
            r14 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r12, r13, r14, r15, r16, r17)
            goto L_0x037a
        L_0x0359:
            int r3 = org.videolan.vlc.R.id.action_mode_favorite_remove
            if (r2 != r3) goto L_0x0376
            androidx.lifecycle.LifecycleCoroutineScope r1 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r1)
            r12 = r1
            kotlinx.coroutines.CoroutineScope r12 = (kotlinx.coroutines.CoroutineScope) r12
            org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$3 r1 = new org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$3
            r1.<init>(r0, r11, r7)
            r15 = r1
            kotlin.jvm.functions.Function2 r15 = (kotlin.jvm.functions.Function2) r15
            r16 = 3
            r17 = 0
            r13 = 0
            r14 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r12, r13, r14, r15, r16, r17)
            goto L_0x037a
        L_0x0376:
            r18.stopActionMode()
            return r4
        L_0x037a:
            r18.stopActionMode()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoGridFragment.onActionItemClicked(androidx.appcompat.view.ActionMode, android.view.MenuItem):boolean");
    }

    private final void addToGroup(List<? extends MediaLibraryItem> list) {
        UiTools uiTools = UiTools.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        List all$default = MediaUtilsKt.getAll$default(list, 0, false, false, 7, (Object) null);
        boolean z = true;
        if (list.size() != 1) {
            z = false;
        }
        uiTools.addToGroup(requireActivity, all$default, z, new VideoGridFragment$addToGroup$1(this, list));
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        Intrinsics.checkNotNullParameter(actionMode, RtspHeaders.Values.MODE);
        VideoListAdapter videoListAdapter2 = null;
        setActionMode((ActionMode) null);
        setFabPlayVisibility(true);
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper2 = this.multiSelectHelper;
        if (multiSelectHelper2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
            multiSelectHelper2 = null;
        }
        multiSelectHelper2.clearSelection();
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper3 = this.multiSelectHelper;
        if (multiSelectHelper3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
            multiSelectHelper3 = null;
        }
        VideoListAdapter videoListAdapter3 = this.videoListAdapter;
        if (videoListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
        } else {
            videoListAdapter2 = videoListAdapter3;
        }
        multiSelectHelper3.toggleActionMode(false, videoListAdapter2.getItemCount());
    }

    public final void updateSeenMediaMarker() {
        VideoListAdapter videoListAdapter2 = this.videoListAdapter;
        VideoListAdapter videoListAdapter3 = null;
        if (videoListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
            videoListAdapter2 = null;
        }
        SharedPreferences sharedPreferences = this.settings;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences = null;
        }
        videoListAdapter2.setSeenMediaMarkerVisible(sharedPreferences.getBoolean("media_seen", true));
        VideoListAdapter videoListAdapter4 = this.videoListAdapter;
        if (videoListAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
            videoListAdapter4 = null;
        }
        VideoListAdapter videoListAdapter5 = this.videoListAdapter;
        if (videoListAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
        } else {
            videoListAdapter3 = videoListAdapter5;
        }
        videoListAdapter4.notifyItemRangeChanged(0, videoListAdapter3.getItemCount() - 1, 3);
    }

    public void onCtxAction(int i, ContextOption contextOption) {
        FragmentActivity activity;
        Intrinsics.checkNotNullParameter(contextOption, DuplicationWarningDialog.OPTION_KEY);
        VideoListAdapter videoListAdapter2 = this.videoListAdapter;
        if (videoListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
            videoListAdapter2 = null;
        }
        if (i < videoListAdapter2.getItemCount() && (activity = getActivity()) != null) {
            VideoListAdapter videoListAdapter3 = this.videoListAdapter;
            if (videoListAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
                videoListAdapter3 = null;
            }
            MediaLibraryItem item = videoListAdapter3.getItem(i);
            if (item instanceof MediaWrapper) {
                switch (WhenMappings.$EnumSwitchMapping$1[contextOption.ordinal()]) {
                    case 1:
                        VideosViewModel.playVideo$vlc_android_release$default((VideosViewModel) getViewModel(), activity, (MediaWrapper) item, i, true, false, 16, (Object) null);
                        return;
                    case 2:
                        ((VideosViewModel) getViewModel()).playAudio$vlc_android_release(activity, (MediaWrapper) item);
                        return;
                    case 3:
                        VideosViewModel.playVideo$vlc_android_release$default((VideosViewModel) getViewModel(), activity, (MediaWrapper) item, i, false, true, 8, (Object) null);
                        return;
                    case 4:
                        ((VideosViewModel) getViewModel()).play$vlc_android_release(i);
                        return;
                    case 5:
                        showInfoDialog(item);
                        return;
                    case 6:
                        removeItem(item);
                        return;
                    case 7:
                        MediaUtils.INSTANCE.appendMedia((Context) activity, (MediaWrapper) item);
                        return;
                    case 8:
                        AudioUtil audioUtil = AudioUtil.INSTANCE;
                        FragmentActivity requireActivity = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                        audioUtil.setRingtone(requireActivity, (MediaWrapper) item);
                        return;
                    case 9:
                        MediaUtils.INSTANCE.insertNext((Context) requireActivity(), ((MediaWrapper) item).getTracks());
                        return;
                    case 10:
                        MediaUtils mediaUtils = MediaUtils.INSTANCE;
                        FragmentActivity requireActivity2 = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                        mediaUtils.getSubs(requireActivity2, (MediaWrapper) item);
                        return;
                    case 11:
                        UiTools uiTools = UiTools.INSTANCE;
                        FragmentActivity requireActivity3 = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity(...)");
                        MediaWrapper[] tracks = ((MediaWrapper) item).getTracks();
                        Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
                        uiTools.addToPlaylist(requireActivity3, tracks, SavePlaylistDialog.KEY_NEW_TRACKS);
                        return;
                    case 12:
                        Intent intent = new Intent();
                        intent.setClassName(requireContext().getApplicationContext(), Constants.MOVIEPEDIA_ACTIVITY);
                        intent.putExtra(Constants.MOVIEPEDIA_MEDIA, item);
                        startActivity(intent);
                        return;
                    case 13:
                        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideoGridFragment$onCtxAction$1(this, item, (Continuation<? super VideoGridFragment$onCtxAction$1>) null), 3, (Object) null);
                        return;
                    case 14:
                        ((VideosViewModel) getViewModel()).removeFromGroup((MediaWrapper) item);
                        return;
                    case 15:
                        UiTools uiTools2 = UiTools.INSTANCE;
                        FragmentActivity requireActivity4 = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity(...)");
                        uiTools2.addToGroup(requireActivity4, CollectionsKt.listOf(item), true, VideoGridFragment$onCtxAction$2.INSTANCE);
                        return;
                    case 16:
                        Job unused2 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideoGridFragment$onCtxAction$3(this, item, (Continuation<? super VideoGridFragment$onCtxAction$3>) null), 3, (Object) null);
                        return;
                    case 17:
                        Job unused3 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideoGridFragment$onCtxAction$4(this, item, (Continuation<? super VideoGridFragment$onCtxAction$4>) null), 3, (Object) null);
                        return;
                    case 18:
                        Job unused4 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideoGridFragment$onCtxAction$5(this, item, (Continuation<? super VideoGridFragment$onCtxAction$5>) null), 3, (Object) null);
                        return;
                    case 19:
                    case 20:
                        Job unused5 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), (CoroutineStart) null, new VideoGridFragment$onCtxAction$6(item, contextOption, (Continuation<? super VideoGridFragment$onCtxAction$6>) null), 2, (Object) null);
                        return;
                    case 21:
                        KextensionsKt.showParentFolder(this, (MediaWrapper) item);
                        return;
                    case 22:
                        Job unused6 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideoGridFragment$onCtxAction$7(this, item, (Continuation<? super VideoGridFragment$onCtxAction$7>) null), 3, (Object) null);
                        return;
                    default:
                        return;
                }
            } else if (item instanceof Folder) {
                int i2 = WhenMappings.$EnumSwitchMapping$1[contextOption.ordinal()];
                if (i2 == 4) {
                    ((VideosViewModel) getViewModel()).play$vlc_android_release(i);
                } else if (i2 == 7) {
                    ((VideosViewModel) getViewModel()).append$vlc_android_release(i);
                } else if (i2 == 11) {
                    FragmentActivity requireActivity5 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity5, "requireActivity(...)");
                    ((VideosViewModel) getViewModel()).addItemToPlaylist$vlc_android_release(requireActivity5, i);
                } else if (i2 == 19 || i2 == 20) {
                    Job unused7 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), (CoroutineStart) null, new VideoGridFragment$onCtxAction$10(item, contextOption, (Continuation<? super VideoGridFragment$onCtxAction$10>) null), 2, (Object) null);
                } else {
                    switch (i2) {
                        case 23:
                            Job unused8 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideoGridFragment$onCtxAction$8(this, item, (Continuation<? super VideoGridFragment$onCtxAction$8>) null), 3, (Object) null);
                            return;
                        case 24:
                            Job unused9 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideoGridFragment$onCtxAction$9(this, item, (Continuation<? super VideoGridFragment$onCtxAction$9>) null), 3, (Object) null);
                            return;
                        case 25:
                            banFolder((Folder) item);
                            return;
                        default:
                            return;
                    }
                }
            } else if (item instanceof VideoGroup) {
                int i3 = WhenMappings.$EnumSwitchMapping$1[contextOption.ordinal()];
                if (i3 == 3) {
                    ((VideosViewModel) getViewModel()).play$vlc_android_release(i);
                } else if (i3 == 4) {
                    ((VideosViewModel) getViewModel()).play$vlc_android_release(i);
                } else if (i3 == 7) {
                    ((VideosViewModel) getViewModel()).append$vlc_android_release(i);
                } else if (i3 == 11) {
                    FragmentActivity requireActivity6 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity6, "requireActivity(...)");
                    ((VideosViewModel) getViewModel()).addItemToPlaylist$vlc_android_release(requireActivity6, i);
                } else if (i3 == 15) {
                    UiTools uiTools3 = UiTools.INSTANCE;
                    FragmentActivity requireActivity7 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity7, "requireActivity(...)");
                    uiTools3.addToGroup(requireActivity7, MediaUtilsKt.getAll$default(CollectionsKt.listOf(item), 0, false, false, 7, (Object) null), true, VideoGridFragment$onCtxAction$14.INSTANCE);
                } else if (i3 == 19 || i3 == 20) {
                    Job unused10 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), (CoroutineStart) null, new VideoGridFragment$onCtxAction$15(item, contextOption, (Continuation<? super VideoGridFragment$onCtxAction$15>) null), 2, (Object) null);
                } else if (i3 == 23) {
                    Job unused11 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideoGridFragment$onCtxAction$12(this, item, (Continuation<? super VideoGridFragment$onCtxAction$12>) null), 3, (Object) null);
                } else if (i3 == 24) {
                    Job unused12 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideoGridFragment$onCtxAction$13(this, item, (Continuation<? super VideoGridFragment$onCtxAction$13>) null), 3, (Object) null);
                } else if (i3 == 26) {
                    renameGroup((VideoGroup) item);
                } else if (i3 == 27) {
                    Job unused13 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideoGridFragment$onCtxAction$11(this, item, (Continuation<? super VideoGridFragment$onCtxAction$11>) null), 3, (Object) null);
                }
            }
        }
    }

    private final void banFolder(Folder folder) {
        String str = folder.mMrl;
        Intrinsics.checkNotNullExpressionValue(str, "mMrl");
        String path = Uri.parse(str).getPath();
        if (path == null || BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), (CoroutineStart) null, new VideoGridFragment$banFolder$1$1(path, this, (Continuation<? super VideoGridFragment$banFolder$1$1>) null), 2, (Object) null) == null) {
            Integer.valueOf(Log.e("VLC/VideoListFragment", "banFolder: path is null"));
        }
    }

    private final void renameGroup(VideoGroup videoGroup) {
        RenameDialog newInstance$default = RenameDialog.Companion.newInstance$default(RenameDialog.Companion, videoGroup, false, 2, (Object) null);
        newInstance$default.setListener(new VideoGridFragment$renameGroup$1(this));
        newInstance$default.show(requireActivity().getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(RenameDialog.class).getSimpleName());
    }

    /* access modifiers changed from: private */
    public static final void thumbObs$lambda$18(VideoGridFragment videoGridFragment, MediaWrapper mediaWrapper) {
        PagedList value;
        Intrinsics.checkNotNullParameter(videoGridFragment, "this$0");
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        if (videoGridFragment.videoListAdapter != null && (((VideosViewModel) videoGridFragment.getViewModel()).getProvider() instanceof VideosProvider) && (value = ((VideosViewModel) videoGridFragment.getViewModel()).getProvider().getPagedList().getValue()) != null) {
            int indexOf = CollectionsKt.indexOf(value, mediaWrapper);
            VideoListAdapter videoListAdapter2 = videoGridFragment.videoListAdapter;
            VideoListAdapter videoListAdapter3 = null;
            if (videoListAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
                videoListAdapter2 = null;
            }
            MediaLibraryItem item = videoListAdapter2.getItem(indexOf);
            MediaWrapper mediaWrapper2 = item instanceof MediaWrapper ? (MediaWrapper) item : null;
            if (mediaWrapper2 != null) {
                mediaWrapper2.setArtworkURL(mediaWrapper.getArtworkURL());
                VideoListAdapter videoListAdapter4 = videoGridFragment.videoListAdapter;
                if (videoListAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
                } else {
                    videoListAdapter3 = videoListAdapter4;
                }
                videoListAdapter3.notifyItemChanged(indexOf);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void process(VideoAction videoAction) {
        if (videoAction instanceof VideoClick) {
            VideoClick videoClick = (VideoClick) videoAction;
            onClick(videoClick.getPosition(), videoClick.getItem());
        } else if (videoAction instanceof VideoLongClick) {
            VideoLongClick videoLongClick = (VideoLongClick) videoAction;
            if (!(videoLongClick.getItem() instanceof VideoGroup) || ((VideoGroup) videoLongClick.getItem()).getPresentCount() != 0) {
                onLongClick(videoLongClick.getPosition());
                return;
            }
            UiTools uiTools = UiTools.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            uiTools.snackerMissing(requireActivity);
        } else if (videoAction instanceof VideoCtxClick) {
            VideoCtxClick videoCtxClick = (VideoCtxClick) videoAction;
            MediaLibraryItem item = videoCtxClick.getItem();
            if (item instanceof Folder) {
                FlagSet<ContextOption> createCtxFolderFlags = ContextOption.Companion.createCtxFolderFlags();
                createCtxFolderFlags.add(((Folder) videoCtxClick.getItem()).isFavorite() ? ContextOption.CTX_FAV_REMOVE : ContextOption.CTX_FAV_ADD);
                FragmentActivity requireActivity2 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                ContextSheetKt.showContext(requireActivity2, this, videoCtxClick.getPosition(), videoCtxClick.getItem(), createCtxFolderFlags);
            } else if (item instanceof VideoGroup) {
                if (((VideoGroup) videoCtxClick.getItem()).getPresentCount() == 0) {
                    UiTools uiTools2 = UiTools.INSTANCE;
                    FragmentActivity requireActivity3 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity(...)");
                    uiTools2.snackerMissing(requireActivity3);
                    return;
                }
                FlagSet<ContextOption> createCtxVideoGroupFlags = ContextOption.Companion.createCtxVideoGroupFlags();
                createCtxVideoGroupFlags.add(((VideoGroup) videoCtxClick.getItem()).isFavorite() ? ContextOption.CTX_FAV_REMOVE : ContextOption.CTX_FAV_ADD);
                FragmentActivity requireActivity4 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity(...)");
                ContextSheetKt.showContext(requireActivity4, this, videoCtxClick.getPosition(), videoCtxClick.getItem(), createCtxVideoGroupFlags);
            } else if (item instanceof MediaWrapper) {
                FlagSet<ContextOption> createCtxVideoFlags = ContextOption.Companion.createCtxVideoFlags();
                createCtxVideoFlags.add(((MediaWrapper) videoCtxClick.getItem()).isFavorite() ? ContextOption.CTX_FAV_REMOVE : ContextOption.CTX_FAV_ADD);
                createCtxVideoFlags.add(((MediaWrapper) videoCtxClick.getItem()).getSeen() > 0 ? ContextOption.CTX_MARK_AS_UNPLAYED : ContextOption.CTX_MARK_AS_PLAYED);
                if (((MediaWrapper) videoCtxClick.getItem()).getTime() != 0) {
                    createCtxVideoFlags.add(ContextOption.CTX_PLAY_FROM_START);
                }
                if (((VideosViewModel) getViewModel()).getGroupingType() == VideoGroupingType.NAME || ((VideosViewModel) getViewModel()).getGroup() != null) {
                    if (((VideosViewModel) getViewModel()).getGroup() != null) {
                        createCtxVideoFlags.add(ContextOption.CTX_REMOVE_GROUP);
                    } else {
                        createCtxVideoFlags.addAll((Enum[]) new ContextOption[]{ContextOption.CTX_ADD_GROUP, ContextOption.CTX_GROUP_SIMILAR});
                    }
                }
                if (KotlinExtensionsKt.retrieveParent(((MediaWrapper) videoCtxClick.getItem()).getUri()) != null) {
                    createCtxVideoFlags.add(ContextOption.CTX_GO_TO_FOLDER);
                }
                FragmentActivity requireActivity5 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity5, "requireActivity(...)");
                ContextSheetKt.showContext(requireActivity5, this, videoCtxClick.getPosition(), videoCtxClick.getItem(), createCtxVideoFlags);
            }
        } else if (!(videoAction instanceof VideoImageClick)) {
        } else {
            if (getActionMode() != null) {
                VideoImageClick videoImageClick = (VideoImageClick) videoAction;
                onClick(videoImageClick.getPosition(), videoImageClick.getItem());
                return;
            }
            onLongClick(((VideoImageClick) videoAction).getPosition());
        }
    }

    private final void onLongClick(int i) {
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper2 = null;
        if (getActionMode() == null && inSearchMode()) {
            UiTools uiTools = UiTools.INSTANCE;
            VideoGridBinding videoGridBinding = this.binding;
            if (videoGridBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                videoGridBinding = null;
            }
            uiTools.setKeyboardVisibility(videoGridBinding.getRoot(), false);
        }
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper3 = this.multiSelectHelper;
        if (multiSelectHelper3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
        } else {
            multiSelectHelper2 = multiSelectHelper3;
        }
        multiSelectHelper2.toggleSelection(i, true);
        if (getActionMode() == null) {
            startActionMode();
        } else {
            invalidateActionMode();
        }
    }

    private final void onClick(int i, MediaLibraryItem mediaLibraryItem) {
        if (mediaLibraryItem instanceof MediaWrapper) {
            if (getActionMode() != null) {
                MultiSelectHelper<MediaLibraryItem> multiSelectHelper2 = this.multiSelectHelper;
                if (multiSelectHelper2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
                    multiSelectHelper2 = null;
                }
                MultiSelectHelper.toggleSelection$default(multiSelectHelper2, i, false, 2, (Object) null);
                invalidateActionMode();
                return;
            }
            VideosViewModel.playVideo$vlc_android_release$default((VideosViewModel) getViewModel(), getActivity(), (MediaWrapper) mediaLibraryItem, i, false, false, 24, (Object) null);
        } else if (mediaLibraryItem instanceof Folder) {
            String str = ((Folder) mediaLibraryItem).mMrl;
            Intrinsics.checkNotNullExpressionValue(str, "mMrl");
            if (!BrowserutilsKt.isMissing(str)) {
                if (getActionMode() != null) {
                    MultiSelectHelper<MediaLibraryItem> multiSelectHelper3 = this.multiSelectHelper;
                    if (multiSelectHelper3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
                        multiSelectHelper3 = null;
                    }
                    MultiSelectHelper.toggleSelection$default(multiSelectHelper3, i, false, 2, (Object) null);
                    invalidateActionMode();
                    return;
                }
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    open(activity, mediaLibraryItem);
                }
            }
        } else if (!(mediaLibraryItem instanceof VideoGroup)) {
        } else {
            if (getActionMode() != null) {
                MultiSelectHelper<MediaLibraryItem> multiSelectHelper4 = this.multiSelectHelper;
                if (multiSelectHelper4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("multiSelectHelper");
                    multiSelectHelper4 = null;
                }
                MultiSelectHelper.toggleSelection$default(multiSelectHelper4, i, false, 2, (Object) null);
                invalidateActionMode();
                return;
            }
            VideoGroup videoGroup = (VideoGroup) mediaLibraryItem;
            if (videoGroup.getPresentCount() == 0) {
                UiTools uiTools = UiTools.INSTANCE;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                uiTools.snackerMissing(requireActivity);
            } else if (videoGroup.getPresentCount() == 1) {
                ((VideosViewModel) getViewModel()).play$vlc_android_release(i);
            } else {
                FragmentActivity activity2 = getActivity();
                if (activity2 != null) {
                    open(activity2, mediaLibraryItem);
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoGridFragment$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/video/VideoGridFragment;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoGridFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VideoGridFragment newInstance() {
            return new VideoGridFragment();
        }
    }
}
