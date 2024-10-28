package org.videolan.vlc.gui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.SearchView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.FlowLiveDataConversions;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.HeaderMediaListActivityBinding;
import org.videolan.vlc.gui.audio.AudioAlbumTracksAdapter;
import org.videolan.vlc.gui.audio.AudioBrowserAdapter;
import org.videolan.vlc.gui.dialogs.ConfirmDeleteDialog;
import org.videolan.vlc.gui.dialogs.ContextSheetKt;
import org.videolan.vlc.gui.dialogs.CtxActionReceiver;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;
import org.videolan.vlc.gui.dialogs.RenameDialog;
import org.videolan.vlc.gui.dialogs.SavePlaylistDialog;
import org.videolan.vlc.gui.helpers.AudioUtil;
import org.videolan.vlc.gui.helpers.ExpandStateAppBarLayoutBehavior;
import org.videolan.vlc.gui.helpers.SwipeDragItemTouchHelperCallback;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.view.FastScroller;
import org.videolan.vlc.gui.view.RecyclerSectionItemDecoration;
import org.videolan.vlc.interfaces.Filterable;
import org.videolan.vlc.interfaces.IEventsHandler;
import org.videolan.vlc.interfaces.IListEventsHandler;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.media.PlaylistManager;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;
import org.videolan.vlc.util.BrowserutilsKt;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.util.FlagSet;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.viewmodels.PlaylistModel;
import org.videolan.vlc.viewmodels.mobile.PlaylistViewModel;
import org.videolan.vlc.viewmodels.mobile.PlaylistViewModelKt;

@Metadata(d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0016\u0018\u0000 l2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\n:\u0001lB\u0005¢\u0006\u0002\u0010\u000bJ\b\u0010 \u001a\u00020\u0013H\u0016J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0003H\u0002J\b\u0010$\u001a\u00020\u0013H\u0016J\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0016J\n\u0010)\u001a\u0004\u0018\u00010(H\u0016J\b\u0010*\u001a\u00020&H\u0002J\b\u0010+\u001a\u00020\u0013H\u0016J\u0018\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020/H\u0016J\u0010\u00100\u001a\u00020&2\u0006\u00101\u001a\u000202H\u0016J \u00100\u001a\u00020&2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u0003H\u0016J\u0012\u00104\u001a\u00020&2\b\u00105\u001a\u0004\u0018\u000106H\u0016J\u0018\u00107\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\r2\u0006\u00108\u001a\u000209H\u0016J\u0010\u0010:\u001a\u00020\u00132\u0006\u00108\u001a\u000209H\u0016J\u0018\u0010;\u001a\u00020&2\u0006\u00103\u001a\u00020\u00192\u0006\u0010<\u001a\u00020=H\u0016J \u0010>\u001a\u00020&2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u0003H\u0016J\u0010\u0010?\u001a\u00020&2\u0006\u0010-\u001a\u00020\rH\u0016J \u0010@\u001a\u00020&2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u0003H\u0016J\u0018\u0010A\u001a\u00020&2\u0006\u00101\u001a\u0002022\u0006\u0010.\u001a\u00020\u0003H\u0016J \u0010B\u001a\u00020\u00132\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u0003H\u0016J \u0010C\u001a\u00020&2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u0003H\u0016J\u0010\u0010D\u001a\u00020\u00132\u0006\u0010.\u001a\u00020/H\u0016J\u0010\u0010E\u001a\u00020\u00132\u0006\u0010.\u001a\u00020/H\u0016J\u0018\u0010F\u001a\u00020&2\u0006\u0010G\u001a\u00020\u00192\u0006\u0010H\u001a\u00020\u0019H\u0016J\u0010\u0010I\u001a\u00020\u00132\u0006\u0010.\u001a\u00020/H\u0016J\b\u0010J\u001a\u00020&H\u0014J\u0018\u0010K\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\r2\u0006\u00108\u001a\u000209H\u0016J\u0012\u0010L\u001a\u00020\u00132\b\u0010M\u001a\u0004\u0018\u00010(H\u0016J\u0012\u0010N\u001a\u00020\u00132\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0018\u0010O\u001a\u00020&2\u0006\u00103\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u0003H\u0016J\b\u0010P\u001a\u00020&H\u0014J\u0010\u0010Q\u001a\u00020&2\u0006\u0010R\u001a\u000206H\u0016J\u0010\u0010S\u001a\u00020&2\u0006\u0010T\u001a\u00020UH\u0016J\b\u0010V\u001a\u00020&H\u0014J\u0014\u0010W\u001a\u00020&2\n\u0010X\u001a\u0006\u0012\u0002\b\u00030YH\u0016J*\u0010Z\u001a\u00020&2\f\u0010[\u001a\b\u0012\u0004\u0012\u00020]0\\2\f\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00190\\H@¢\u0006\u0002\u0010_J\u001e\u0010`\u001a\u00020&2\u0006\u00103\u001a\u00020\u00192\u0006\u0010a\u001a\u00020]H@¢\u0006\u0002\u0010bJ\u0016\u0010c\u001a\u00020&2\f\u0010d\u001a\b\u0012\u0004\u0012\u00020]0\\H\u0002J\b\u0010e\u001a\u00020&H\u0016J\u0010\u0010f\u001a\u00020&2\u0006\u0010g\u001a\u00020\u0013H\u0016J\u0010\u0010h\u001a\u00020&2\u0006\u0010a\u001a\u00020]H\u0002J\b\u0010i\u001a\u00020&H\u0002J\u000f\u0010j\u001a\u0004\u0018\u00010&H\u0002¢\u0006\u0002\u0010kR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX.¢\u0006\u0002\n\u0000¨\u0006m"}, d2 = {"Lorg/videolan/vlc/gui/HeaderMediaListActivity;", "Lorg/videolan/vlc/gui/AudioPlayerContainerActivity;", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lorg/videolan/vlc/interfaces/IListEventsHandler;", "Landroidx/appcompat/view/ActionMode$Callback;", "Landroid/view/View$OnClickListener;", "Lorg/videolan/vlc/gui/dialogs/CtxActionReceiver;", "Lorg/videolan/vlc/interfaces/Filterable;", "Landroidx/appcompat/widget/SearchView$OnQueryTextListener;", "Landroid/view/MenuItem$OnActionExpandListener;", "()V", "actionMode", "Landroidx/appcompat/view/ActionMode;", "audioBrowserAdapter", "Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;", "binding", "Lorg/videolan/vlc/databinding/HeaderMediaListActivityBinding;", "isPlaylist", "", "itemTouchHelper", "Landroidx/recyclerview/widget/ItemTouchHelper;", "itemTouchHelperCallback", "Lorg/videolan/vlc/gui/helpers/SwipeDragItemTouchHelperCallback;", "lastDismissedPosition", "", "mediaLibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "searchView", "Landroidx/appcompat/widget/SearchView;", "viewModel", "Lorg/videolan/vlc/viewmodels/mobile/PlaylistViewModel;", "allowedToExpand", "deleteMedia", "Lkotlinx/coroutines/Job;", "mw", "enableSearchOption", "filter", "", "query", "", "getFilterQuery", "invalidateActionMode", "isTransparent", "onActionItemClicked", "mode", "item", "Landroid/view/MenuItem;", "onClick", "v", "Landroid/view/View;", "position", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateActionMode", "menu", "Landroid/view/Menu;", "onCreateOptionsMenu", "onCtxAction", "option", "Lorg/videolan/vlc/util/ContextOption;", "onCtxClick", "onDestroyActionMode", "onImageClick", "onItemFocused", "onLongClick", "onMainActionClick", "onMenuItemActionCollapse", "onMenuItemActionExpand", "onMove", "oldPosition", "newPosition", "onOptionsItemSelected", "onPause", "onPrepareActionMode", "onQueryTextChange", "newText", "onQueryTextSubmit", "onRemove", "onResume", "onSaveInstanceState", "outState", "onStartDrag", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onStop", "onUpdateFinished", "adapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "removeFromPlaylist", "list", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "indexes", "(Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeItem", "media", "(ILorg/videolan/medialibrary/interfaces/media/MediaWrapper;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeItems", "items", "restoreList", "setSearchVisibility", "visible", "showInfoDialog", "startActionMode", "stopActionMode", "()Lkotlin/Unit;", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: HeaderMediaListActivity.kt */
public class HeaderMediaListActivity extends AudioPlayerContainerActivity implements IEventsHandler<MediaLibraryItem>, IListEventsHandler, ActionMode.Callback, View.OnClickListener, CtxActionReceiver, Filterable, SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {
    public static final String ARTIST_FROM_ALBUM = "ARTIST_FROM_ALBUM";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VLC/PlaylistActivity";
    private ActionMode actionMode;
    /* access modifiers changed from: private */
    public AudioBrowserAdapter audioBrowserAdapter;
    /* access modifiers changed from: private */
    public HeaderMediaListActivityBinding binding;
    /* access modifiers changed from: private */
    public boolean isPlaylist;
    private ItemTouchHelper itemTouchHelper;
    /* access modifiers changed from: private */
    public SwipeDragItemTouchHelperCallback itemTouchHelperCallback;
    /* access modifiers changed from: private */
    public int lastDismissedPosition = -1;
    /* access modifiers changed from: private */
    public final Medialibrary mediaLibrary;
    private SearchView searchView;
    /* access modifiers changed from: private */
    public PlaylistViewModel viewModel;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: HeaderMediaListActivity.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|27) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0046 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0050 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                org.videolan.vlc.util.ContextOption[] r0 = org.videolan.vlc.util.ContextOption.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_INFORMATION     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_DELETE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_APPEND     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_PLAY_NEXT     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_ADD_TO_PLAYLIST     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_SET_RINGTONE     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_SHARE     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_RENAME     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_COPY     // Catch:{ NoSuchFieldError -> 0x005a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_FAV_ADD     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_FAV_REMOVE     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_ADD_SHORTCUT     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.HeaderMediaListActivity.WhenMappings.<clinit>():void");
        }
    }

    public boolean allowedToExpand() {
        return true;
    }

    public boolean enableSearchOption() {
        return true;
    }

    public boolean isTransparent() {
        return true;
    }

    public void onItemFocused(View view, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
    }

    public boolean onQueryTextSubmit(String str) {
        return false;
    }

    public void onUpdateFinished(RecyclerView.Adapter<?> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
    }

    public void setSearchVisibility(boolean z) {
    }

    public HeaderMediaListActivity() {
        Medialibrary instance = Medialibrary.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        this.mediaLibrary = instance;
    }

    public void onCreate(Bundle bundle) {
        MediaLibraryItem mediaLibraryItem;
        Parcelable parcelable;
        Parcelable parcelable2;
        Bundle bundle2 = bundle;
        super.onCreate(bundle);
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.header_media_list_activity);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(...)");
        this.binding = (HeaderMediaListActivityBinding) contentView;
        initAudioPlayerContainerActivity();
        HeaderMediaListActivityBinding headerMediaListActivityBinding = this.binding;
        PlaylistViewModel playlistViewModel = null;
        if (headerMediaListActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding = null;
        }
        RecyclerView recyclerView = headerMediaListActivityBinding.songs;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "songs");
        setFragmentContainer(recyclerView);
        setOriginalBottomPadding(getFragmentContainer().getPaddingBottom());
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setTitle((CharSequence) "");
        }
        HeaderMediaListActivityBinding headerMediaListActivityBinding2 = this.binding;
        if (headerMediaListActivityBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding2 = null;
        }
        headerMediaListActivityBinding2.setTopmargin(Integer.valueOf(KotlinExtensionsKt.getDp(86)));
        getToolbar().addOnLayoutChangeListener(new HeaderMediaListActivity$$ExternalSyntheticLambda1(this));
        if (bundle2 != null) {
            if (Build.VERSION.SDK_INT >= 33) {
                parcelable2 = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(bundle2, "ML_ITEM", Parcelable.class);
            } else {
                parcelable2 = bundle2.getParcelable("ML_ITEM");
                if (!(parcelable2 instanceof Parcelable)) {
                    parcelable2 = null;
                }
            }
            mediaLibraryItem = (MediaLibraryItem) parcelable2;
        } else {
            Intent intent = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
            if (Build.VERSION.SDK_INT >= 33) {
                parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, "ML_ITEM", Parcelable.class);
            } else {
                parcelable = intent.getParcelableExtra("ML_ITEM");
                if (!(parcelable instanceof Parcelable)) {
                    parcelable = null;
                }
            }
            mediaLibraryItem = (MediaLibraryItem) parcelable;
        }
        if (mediaLibraryItem == null) {
            finish();
            return;
        }
        this.isPlaylist = mediaLibraryItem.getItemType() == 16;
        HeaderMediaListActivityBinding headerMediaListActivityBinding3 = this.binding;
        if (headerMediaListActivityBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding3 = null;
        }
        headerMediaListActivityBinding3.setPlaylist(mediaLibraryItem);
        PlaylistViewModel viewModel2 = PlaylistViewModelKt.getViewModel(this, mediaLibraryItem);
        this.viewModel = viewModel2;
        if (viewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            viewModel2 = null;
        }
        LifecycleOwner lifecycleOwner = this;
        viewModel2.getTracksProvider().getPagedList().observe(lifecycleOwner, new HeaderMediaListActivity$sam$androidx_lifecycle_Observer$0(new HeaderMediaListActivity$onCreate$2(this)));
        PlaylistViewModel playlistViewModel2 = this.viewModel;
        if (playlistViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playlistViewModel2 = null;
        }
        playlistViewModel2.getPlaylistLiveData().observe(lifecycleOwner, new HeaderMediaListActivity$sam$androidx_lifecycle_Observer$0(new HeaderMediaListActivity$onCreate$3(this)));
        PlaylistViewModel playlistViewModel3 = this.viewModel;
        if (playlistViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playlistViewModel3 = null;
        }
        playlistViewModel3.getTracksProvider().getLiveHeaders().observe(lifecycleOwner, new HeaderMediaListActivity$sam$androidx_lifecycle_Observer$0(new HeaderMediaListActivity$onCreate$4(this)));
        if (this.isPlaylist) {
            this.audioBrowserAdapter = new AudioBrowserAdapter(32, this, this, this.isPlaylist, 0, 16, (DefaultConstructorMarker) null);
            AudioBrowserAdapter audioBrowserAdapter2 = this.audioBrowserAdapter;
            if (audioBrowserAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
                audioBrowserAdapter2 = null;
            }
            SwipeDragItemTouchHelperCallback swipeDragItemTouchHelperCallback = new SwipeDragItemTouchHelperCallback(audioBrowserAdapter2, false, Settings.INSTANCE.getSafeMode(), 2, (DefaultConstructorMarker) null);
            this.itemTouchHelperCallback = swipeDragItemTouchHelperCallback;
            swipeDragItemTouchHelperCallback.setSwipeAttemptListener(new HeaderMediaListActivity$onCreate$5(this));
            SwipeDragItemTouchHelperCallback swipeDragItemTouchHelperCallback2 = this.itemTouchHelperCallback;
            if (swipeDragItemTouchHelperCallback2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("itemTouchHelperCallback");
                swipeDragItemTouchHelperCallback2 = null;
            }
            ItemTouchHelper itemTouchHelper2 = new ItemTouchHelper(swipeDragItemTouchHelperCallback2);
            this.itemTouchHelper = itemTouchHelper2;
            Intrinsics.checkNotNull(itemTouchHelper2);
            HeaderMediaListActivityBinding headerMediaListActivityBinding4 = this.binding;
            if (headerMediaListActivityBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                headerMediaListActivityBinding4 = null;
            }
            itemTouchHelper2.attachToRecyclerView(headerMediaListActivityBinding4.songs);
            HeaderMediaListActivityBinding headerMediaListActivityBinding5 = this.binding;
            if (headerMediaListActivityBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                headerMediaListActivityBinding5 = null;
            }
            headerMediaListActivityBinding5.releaseDate.setVisibility(8);
        } else {
            this.audioBrowserAdapter = new AudioAlbumTracksAdapter(32, this, this);
            HeaderMediaListActivityBinding headerMediaListActivityBinding6 = this.binding;
            if (headerMediaListActivityBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                headerMediaListActivityBinding6 = null;
            }
            RecyclerView recyclerView2 = headerMediaListActivityBinding6.songs;
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.recycler_section_header_height);
            PlaylistViewModel playlistViewModel4 = this.viewModel;
            if (playlistViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                playlistViewModel4 = null;
            }
            recyclerView2.addItemDecoration(new RecyclerSectionItemDecoration(dimensionPixelSize, true, playlistViewModel4.getTracksProvider()));
        }
        HeaderMediaListActivityBinding headerMediaListActivityBinding7 = this.binding;
        if (headerMediaListActivityBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding7 = null;
        }
        headerMediaListActivityBinding7.btnShuffle.setOnClickListener(new HeaderMediaListActivity$$ExternalSyntheticLambda2(this, mediaLibraryItem));
        HeaderMediaListActivityBinding headerMediaListActivityBinding8 = this.binding;
        if (headerMediaListActivityBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding8 = null;
        }
        headerMediaListActivityBinding8.btnAddPlaylist.setOnClickListener(new HeaderMediaListActivity$$ExternalSyntheticLambda3(this));
        HeaderMediaListActivityBinding headerMediaListActivityBinding9 = this.binding;
        if (headerMediaListActivityBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding9 = null;
        }
        headerMediaListActivityBinding9.btnFavorite.setOnClickListener(new HeaderMediaListActivity$$ExternalSyntheticLambda4(this));
        HeaderMediaListActivityBinding headerMediaListActivityBinding10 = this.binding;
        if (headerMediaListActivityBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding10 = null;
        }
        headerMediaListActivityBinding10.headerListArtist.setOnClickListener(new HeaderMediaListActivity$$ExternalSyntheticLambda5(this));
        HeaderMediaListActivityBinding headerMediaListActivityBinding11 = this.binding;
        if (headerMediaListActivityBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding11 = null;
        }
        headerMediaListActivityBinding11.songs.setLayoutManager(new LinearLayoutManager(this));
        HeaderMediaListActivityBinding headerMediaListActivityBinding12 = this.binding;
        if (headerMediaListActivityBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding12 = null;
        }
        RecyclerView recyclerView3 = headerMediaListActivityBinding12.songs;
        AudioBrowserAdapter audioBrowserAdapter3 = this.audioBrowserAdapter;
        if (audioBrowserAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
            audioBrowserAdapter3 = null;
        }
        recyclerView3.setAdapter(audioBrowserAdapter3);
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new HeaderMediaListActivity$onCreate$10(this, this, mediaLibraryItem, (Continuation<? super HeaderMediaListActivity$onCreate$10>) null), 3, (Object) null);
        HeaderMediaListActivityBinding headerMediaListActivityBinding13 = this.binding;
        if (headerMediaListActivityBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding13 = null;
        }
        headerMediaListActivityBinding13.playBtn.setOnClickListener(this);
        HeaderMediaListActivityBinding headerMediaListActivityBinding14 = this.binding;
        if (headerMediaListActivityBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding14 = null;
        }
        headerMediaListActivityBinding14.swipeLayout.setEnabled(false);
        AudioBrowserAdapter audioBrowserAdapter4 = this.audioBrowserAdapter;
        if (audioBrowserAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
            audioBrowserAdapter4 = null;
        }
        audioBrowserAdapter4.setAreSectionsEnabled(false);
        HeaderMediaListActivityBinding headerMediaListActivityBinding15 = this.binding;
        if (headerMediaListActivityBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding15 = null;
        }
        FastScroller fastScroller = headerMediaListActivityBinding15.browserFastScroller;
        HeaderMediaListActivityBinding headerMediaListActivityBinding16 = this.binding;
        if (headerMediaListActivityBinding16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding16 = null;
        }
        AppBarLayout appBarLayout = headerMediaListActivityBinding16.appbar;
        Intrinsics.checkNotNullExpressionValue(appBarLayout, "appbar");
        HeaderMediaListActivityBinding headerMediaListActivityBinding17 = this.binding;
        if (headerMediaListActivityBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding17 = null;
        }
        CoordinatorLayout coordinatorLayout = headerMediaListActivityBinding17.coordinator;
        Intrinsics.checkNotNullExpressionValue(coordinatorLayout, "coordinator");
        fastScroller.attachToCoordinator(appBarLayout, coordinatorLayout, (FloatingActionButton) null);
        HeaderMediaListActivityBinding headerMediaListActivityBinding18 = this.binding;
        if (headerMediaListActivityBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding18 = null;
        }
        FastScroller fastScroller2 = headerMediaListActivityBinding18.browserFastScroller;
        HeaderMediaListActivityBinding headerMediaListActivityBinding19 = this.binding;
        if (headerMediaListActivityBinding19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding19 = null;
        }
        RecyclerView recyclerView4 = headerMediaListActivityBinding19.songs;
        Intrinsics.checkNotNullExpressionValue(recyclerView4, "songs");
        PlaylistViewModel playlistViewModel5 = this.viewModel;
        if (playlistViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            playlistViewModel = playlistViewModel5;
        }
        fastScroller2.setRecyclerView(recyclerView4, playlistViewModel.getTracksProvider());
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$0(HeaderMediaListActivity headerMediaListActivity, View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Intrinsics.checkNotNullParameter(headerMediaListActivity, "this$0");
        HeaderMediaListActivityBinding headerMediaListActivityBinding = headerMediaListActivity.binding;
        if (headerMediaListActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding = null;
        }
        headerMediaListActivityBinding.setTopmargin(Integer.valueOf(i4 + KotlinExtensionsKt.getDp(8)));
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$2(HeaderMediaListActivity headerMediaListActivity, MediaLibraryItem mediaLibraryItem, View view) {
        Intrinsics.checkNotNullParameter(headerMediaListActivity, "this$0");
        PlaylistViewModel playlistViewModel = headerMediaListActivity.viewModel;
        if (playlistViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playlistViewModel = null;
        }
        MediaLibraryItem playlist = playlistViewModel.getPlaylist();
        if (playlist != null) {
            MediaUtils.INSTANCE.playTracks((Context) headerMediaListActivity, playlist, new SecureRandom().nextInt(Math.min(mediaLibraryItem.getTracksCount(), 500)), true);
        }
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$4(HeaderMediaListActivity headerMediaListActivity, View view) {
        Intrinsics.checkNotNullParameter(headerMediaListActivity, "this$0");
        PlaylistViewModel playlistViewModel = headerMediaListActivity.viewModel;
        if (playlistViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playlistViewModel = null;
        }
        MediaLibraryItem playlist = playlistViewModel.getPlaylist();
        if (playlist != null) {
            MediaWrapper[] tracks = playlist.getTracks();
            Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
            UiTools.INSTANCE.addToPlaylist(headerMediaListActivity, ArraysKt.toList((T[]) (Object[]) tracks));
        }
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$5(HeaderMediaListActivity headerMediaListActivity, View view) {
        Intrinsics.checkNotNullParameter(headerMediaListActivity, "this$0");
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(headerMediaListActivity), (CoroutineContext) null, (CoroutineStart) null, new HeaderMediaListActivity$onCreate$8$1(headerMediaListActivity, (Continuation<? super HeaderMediaListActivity$onCreate$8$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$6(HeaderMediaListActivity headerMediaListActivity, View view) {
        Intrinsics.checkNotNullParameter(headerMediaListActivity, "this$0");
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(headerMediaListActivity), (CoroutineContext) null, (CoroutineStart) null, new HeaderMediaListActivity$onCreate$9$1(headerMediaListActivity, (Continuation<? super HeaderMediaListActivity$onCreate$9$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        PlaylistModel playlistModel = PlaylistModel.Companion.get((FragmentActivity) this);
        LifecycleOwner lifecycleOwner = this;
        PlaylistManager.Companion.getCurrentPlayedMedia().observe(lifecycleOwner, new HeaderMediaListActivity$sam$androidx_lifecycle_Observer$0(new HeaderMediaListActivity$onResume$1(this)));
        AudioBrowserAdapter audioBrowserAdapter2 = null;
        KextensionsKt.launchWhenStarted(FlowKt.onEach(FlowKt.conflate(FlowLiveDataConversions.asFlow(playlistModel.getDataset())), new HeaderMediaListActivity$onResume$2(this, playlistModel, (Continuation<? super HeaderMediaListActivity$onResume$2>) null)), LifecycleOwnerKt.getLifecycleScope(lifecycleOwner));
        AudioBrowserAdapter audioBrowserAdapter3 = this.audioBrowserAdapter;
        if (audioBrowserAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
        } else {
            audioBrowserAdapter2 = audioBrowserAdapter3;
        }
        audioBrowserAdapter2.setModel(playlistModel);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        AudioBrowserAdapter audioBrowserAdapter2 = this.audioBrowserAdapter;
        if (audioBrowserAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
            audioBrowserAdapter2 = null;
        }
        audioBrowserAdapter2.setCurrentlyPlaying(false);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        stopActionMode();
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        PlaylistViewModel playlistViewModel = this.viewModel;
        if (playlistViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playlistViewModel = null;
        }
        MediaLibraryItem playlist = playlistViewModel.getPlaylist();
        if (playlist != null) {
            bundle.putParcelable("ML_ITEM", playlist);
        }
        super.onSaveInstanceState(bundle);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.playlist_option, menu);
        if (!this.isPlaylist) {
            menu.findItem(R.id.ml_menu_sortby).setVisible(true);
            MenuItem findItem = menu.findItem(R.id.ml_menu_albums_show_track_numbers);
            findItem.setVisible(true);
            findItem.setChecked(Settings.INSTANCE.getShowTrackNumber());
        }
        MenuItem findItem2 = menu.findItem(R.id.ml_menu_sortby);
        PlaylistViewModel playlistViewModel = this.viewModel;
        SearchView searchView2 = null;
        if (playlistViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playlistViewModel = null;
        }
        findItem2.setVisible(playlistViewModel.canSortByName());
        MenuItem findItem3 = menu.findItem(R.id.ml_menu_sortby_filename);
        PlaylistViewModel playlistViewModel2 = this.viewModel;
        if (playlistViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playlistViewModel2 = null;
        }
        findItem3.setVisible(playlistViewModel2.canSortByFileNameName());
        MenuItem findItem4 = menu.findItem(R.id.ml_menu_sortby_artist_name);
        PlaylistViewModel playlistViewModel3 = this.viewModel;
        if (playlistViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playlistViewModel3 = null;
        }
        findItem4.setVisible(playlistViewModel3.canSortByArtist());
        MenuItem findItem5 = menu.findItem(R.id.ml_menu_sortby_length);
        PlaylistViewModel playlistViewModel4 = this.viewModel;
        if (playlistViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playlistViewModel4 = null;
        }
        findItem5.setVisible(playlistViewModel4.canSortByDuration());
        MenuItem findItem6 = menu.findItem(R.id.ml_menu_sortby_date);
        PlaylistViewModel playlistViewModel5 = this.viewModel;
        if (playlistViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playlistViewModel5 = null;
        }
        findItem6.setVisible(playlistViewModel5.canSortByReleaseDate());
        MenuItem findItem7 = menu.findItem(R.id.ml_menu_sortby_last_modified);
        PlaylistViewModel playlistViewModel6 = this.viewModel;
        if (playlistViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playlistViewModel6 = null;
        }
        findItem7.setVisible(playlistViewModel6.canSortByLastModified());
        MenuItem findItem8 = menu.findItem(R.id.ml_menu_filter);
        View actionView = findItem8.getActionView();
        Intrinsics.checkNotNull(actionView, "null cannot be cast to non-null type androidx.appcompat.widget.SearchView");
        SearchView searchView3 = (SearchView) actionView;
        this.searchView = searchView3;
        if (searchView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView3 = null;
        }
        searchView3.setQueryHint(getString(R.string.search_in_list_hint));
        SearchView searchView4 = this.searchView;
        if (searchView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView4 = null;
        }
        searchView4.setOnQueryTextListener(this);
        String filterQuery = getFilterQuery();
        CharSequence charSequence = filterQuery;
        if (!(charSequence == null || charSequence.length() == 0)) {
            SearchView searchView5 = this.searchView;
            if (searchView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("searchView");
            } else {
                searchView2 = searchView5;
            }
            searchView2.post(new HeaderMediaListActivity$$ExternalSyntheticLambda0(findItem8, this, filterQuery));
        }
        findItem8.setOnActionExpandListener(this);
        return true;
    }

    /* access modifiers changed from: private */
    public static final void onCreateOptionsMenu$lambda$8(MenuItem menuItem, HeaderMediaListActivity headerMediaListActivity, String str) {
        Intrinsics.checkNotNullParameter(headerMediaListActivity, "this$0");
        menuItem.expandActionView();
        SearchView searchView2 = headerMediaListActivity.searchView;
        SearchView searchView3 = null;
        if (searchView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView2 = null;
        }
        searchView2.clearFocus();
        UiTools uiTools = UiTools.INSTANCE;
        SearchView searchView4 = headerMediaListActivity.searchView;
        if (searchView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView4 = null;
        }
        uiTools.setKeyboardVisibility(searchView4, false);
        SearchView searchView5 = headerMediaListActivity.searchView;
        if (searchView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
        } else {
            searchView3 = searchView5;
        }
        searchView3.setQuery(str, false);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        int itemId = menuItem.getItemId();
        PlaylistViewModel playlistViewModel = null;
        if (itemId == R.id.ml_menu_sortby_track) {
            PlaylistViewModel playlistViewModel2 = this.viewModel;
            if (playlistViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                playlistViewModel = playlistViewModel2;
            }
            playlistViewModel.sort(12);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_filename) {
            PlaylistViewModel playlistViewModel3 = this.viewModel;
            if (playlistViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                playlistViewModel = playlistViewModel3;
            }
            playlistViewModel.sort(10);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_length) {
            PlaylistViewModel playlistViewModel4 = this.viewModel;
            if (playlistViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                playlistViewModel = playlistViewModel4;
            }
            playlistViewModel.sort(2);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_date) {
            PlaylistViewModel playlistViewModel5 = this.viewModel;
            if (playlistViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                playlistViewModel = playlistViewModel5;
            }
            playlistViewModel.sort(5);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_last_modified) {
            PlaylistViewModel playlistViewModel6 = this.viewModel;
            if (playlistViewModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                playlistViewModel = playlistViewModel6;
            }
            playlistViewModel.sort(4);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_artist_name) {
            PlaylistViewModel playlistViewModel7 = this.viewModel;
            if (playlistViewModel7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                playlistViewModel = playlistViewModel7;
            }
            playlistViewModel.sort(7);
            return true;
        } else if (itemId == R.id.ml_menu_sortby_album_name) {
            PlaylistViewModel playlistViewModel8 = this.viewModel;
            if (playlistViewModel8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                playlistViewModel = playlistViewModel8;
            }
            playlistViewModel.sort(9);
            return true;
        } else if (itemId != R.id.ml_menu_albums_show_track_numbers) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            menuItem.setChecked(!((SharedPreferences) Settings.INSTANCE.getInstance(this)).getBoolean(SettingsKt.ALBUMS_SHOW_TRACK_NUMBER, true));
            SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(this), SettingsKt.ALBUMS_SHOW_TRACK_NUMBER, Boolean.valueOf(menuItem.isChecked()));
            Settings.INSTANCE.setShowTrackNumber(menuItem.isChecked());
            AudioBrowserAdapter audioBrowserAdapter2 = this.audioBrowserAdapter;
            if (audioBrowserAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
                audioBrowserAdapter2 = null;
            }
            audioBrowserAdapter2.notifyDataSetChanged();
            PlaylistViewModel playlistViewModel9 = this.viewModel;
            if (playlistViewModel9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                playlistViewModel = playlistViewModel9;
            }
            playlistViewModel.refresh();
            return true;
        }
    }

    public void onClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        PlaylistViewModel playlistViewModel = null;
        if (this.actionMode != null) {
            AudioBrowserAdapter audioBrowserAdapter2 = this.audioBrowserAdapter;
            if (audioBrowserAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
                audioBrowserAdapter2 = null;
            }
            MultiSelectHelper.toggleSelection$default(audioBrowserAdapter2.getMultiSelectHelper(), i, false, 2, (Object) null);
            invalidateActionMode();
            return;
        }
        SearchView searchView2 = this.searchView;
        if (searchView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchView");
            searchView2 = null;
        }
        if (searchView2.getVisibility() == 0) {
            UiTools.INSTANCE.setKeyboardVisibility(view, false);
        }
        MediaUtils mediaUtils = MediaUtils.INSTANCE;
        Context context = this;
        PlaylistViewModel playlistViewModel2 = this.viewModel;
        if (playlistViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            playlistViewModel = playlistViewModel2;
        }
        MediaUtils.playTracks$default(mediaUtils, context, (MedialibraryProvider) playlistViewModel.getTracksProvider(), i, false, 8, (Object) null);
    }

    public boolean onLongClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        AudioBrowserAdapter audioBrowserAdapter2 = this.audioBrowserAdapter;
        if (audioBrowserAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
            audioBrowserAdapter2 = null;
        }
        audioBrowserAdapter2.getMultiSelectHelper().toggleSelection(i, true);
        if (this.actionMode == null) {
            startActionMode();
        } else {
            invalidateActionMode();
        }
        return true;
    }

    public void onImageClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        if (this.actionMode != null) {
            onClick(view, i, mediaLibraryItem);
        } else {
            onLongClick(view, i, mediaLibraryItem);
        }
    }

    public void onCtxClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        if (this.actionMode == null) {
            MediaWrapper mediaWrapper = mediaLibraryItem instanceof MediaWrapper ? (MediaWrapper) mediaLibraryItem : null;
            if (mediaWrapper != null) {
                FlagSet<ContextOption> createCtxPlaylistItemFlags = ContextOption.Companion.createCtxPlaylistItemFlags();
                createCtxPlaylistItemFlags.add(((MediaWrapper) mediaLibraryItem).isFavorite() ? ContextOption.CTX_FAV_REMOVE : ContextOption.CTX_FAV_ADD);
                if (mediaWrapper.getType() == 6 || (mediaWrapper.getType() == -1 && BrowserutilsKt.isSchemeHttpOrHttps(mediaWrapper.getUri().getScheme()))) {
                    createCtxPlaylistItemFlags.addAll((Enum[]) new ContextOption[]{ContextOption.CTX_COPY, ContextOption.CTX_RENAME});
                } else {
                    createCtxPlaylistItemFlags.add(ContextOption.CTX_SHARE);
                }
                ContextSheetKt.showContext(this, this, i, mediaWrapper, createCtxPlaylistItemFlags);
            }
        }
    }

    public void onRemove(int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        this.lastDismissedPosition = i;
        MediaWrapper[] tracks = mediaLibraryItem.getTracks();
        Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new HeaderMediaListActivity$onRemove$1(this, new ArrayList(CollectionsKt.listOf(Arrays.copyOf(tracks, tracks.length))), i, (Continuation<? super HeaderMediaListActivity$onRemove$1>) null), 3, (Object) null);
    }

    public void onMove(int i, int i2) {
        PlaylistViewModel playlistViewModel = this.viewModel;
        if (playlistViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playlistViewModel = null;
        }
        MediaLibraryItem playlist = playlistViewModel.getPlaylist();
        Intrinsics.checkNotNull(playlist, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.Playlist");
        ((Playlist) playlist).move(i, i2);
    }

    public void onMainActionClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        MediaWrapper[] tracks = mediaLibraryItem.getTracks();
        Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
        MediaUtils.openList$default(MediaUtils.INSTANCE, this, CollectionsKt.listOf(Arrays.copyOf(tracks, tracks.length)), 0, false, 8, (Object) null);
    }

    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        ItemTouchHelper itemTouchHelper2 = this.itemTouchHelper;
        Intrinsics.checkNotNull(itemTouchHelper2);
        itemTouchHelper2.startDrag(viewHolder);
    }

    private final void startActionMode() {
        this.actionMode = startSupportActionMode(this);
    }

    private final Unit stopActionMode() {
        ActionMode actionMode2 = this.actionMode;
        if (actionMode2 == null) {
            return null;
        }
        actionMode2.finish();
        onDestroyActionMode(actionMode2);
        return Unit.INSTANCE;
    }

    private final void invalidateActionMode() {
        ActionMode actionMode2 = this.actionMode;
        if (actionMode2 != null) {
            Intrinsics.checkNotNull(actionMode2);
            actionMode2.invalidate();
        }
    }

    public boolean onCreateActionMode(ActionMode actionMode2, Menu menu) {
        Intrinsics.checkNotNullParameter(actionMode2, RtspHeaders.Values.MODE);
        Intrinsics.checkNotNullParameter(menu, "menu");
        AudioBrowserAdapter audioBrowserAdapter2 = this.audioBrowserAdapter;
        AudioBrowserAdapter audioBrowserAdapter3 = null;
        if (audioBrowserAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
            audioBrowserAdapter2 = null;
        }
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper = audioBrowserAdapter2.getMultiSelectHelper();
        AudioBrowserAdapter audioBrowserAdapter4 = this.audioBrowserAdapter;
        if (audioBrowserAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
        } else {
            audioBrowserAdapter3 = audioBrowserAdapter4;
        }
        multiSelectHelper.toggleActionMode(true, audioBrowserAdapter3.getItemCount());
        actionMode2.getMenuInflater().inflate(R.menu.action_mode_audio_browser, menu);
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00f8 A[EDGE_INSN: B:58:0x00f8->B:48:0x00f8 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPrepareActionMode(androidx.appcompat.view.ActionMode r7, android.view.Menu r8) {
        /*
            r6 = this;
            java.lang.String r0 = "mode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r7 = "menu"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r7)
            org.videolan.vlc.gui.audio.AudioBrowserAdapter r7 = r6.audioBrowserAdapter
            r0 = 0
            java.lang.String r1 = "audioBrowserAdapter"
            if (r7 != 0) goto L_0x0015
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r7 = r0
        L_0x0015:
            org.videolan.tools.MultiSelectHelper r7 = r7.getMultiSelectHelper()
            int r7 = r7.getSelectionCount()
            r2 = 0
            if (r7 != 0) goto L_0x0024
            r6.stopActionMode()
            return r2
        L_0x0024:
            org.videolan.vlc.gui.audio.AudioBrowserAdapter r3 = r6.audioBrowserAdapter
            if (r3 != 0) goto L_0x002c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r3 = r0
        L_0x002c:
            org.videolan.tools.MultiSelectHelper r3 = r3.getMultiSelectHelper()
            java.util.List r3 = r3.getSelection()
            java.lang.Object r3 = r3.get(r2)
            org.videolan.medialibrary.media.MediaLibraryItem r3 = (org.videolan.medialibrary.media.MediaLibraryItem) r3
            int r3 = r3.getItemType()
            r4 = 32
            r5 = 1
            if (r3 != r4) goto L_0x0045
            r3 = 1
            goto L_0x0046
        L_0x0045:
            r3 = 0
        L_0x0046:
            if (r7 != r5) goto L_0x004c
            if (r3 == 0) goto L_0x004c
            r7 = 1
            goto L_0x004d
        L_0x004c:
            r7 = 0
        L_0x004d:
            int r3 = org.videolan.vlc.R.id.action_mode_audio_set_song
            android.view.MenuItem r3 = r8.findItem(r3)
            if (r7 == 0) goto L_0x0063
            org.videolan.resources.AndroidDevices r4 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r4 = r4.isPhone()
            if (r4 == 0) goto L_0x0063
            boolean r4 = r6.isPlaylist
            if (r4 != 0) goto L_0x0063
            r4 = 1
            goto L_0x0064
        L_0x0063:
            r4 = 0
        L_0x0064:
            r3.setVisible(r4)
            int r3 = org.videolan.vlc.R.id.action_mode_audio_info
            android.view.MenuItem r3 = r8.findItem(r3)
            r3.setVisible(r7)
            int r3 = org.videolan.vlc.R.id.action_mode_audio_append
            android.view.MenuItem r3 = r8.findItem(r3)
            org.videolan.vlc.media.PlaylistManager$Companion r4 = org.videolan.vlc.media.PlaylistManager.Companion
            boolean r4 = r4.hasMedia()
            r3.setVisible(r4)
            int r3 = org.videolan.vlc.R.id.action_mode_audio_delete
            android.view.MenuItem r3 = r8.findItem(r3)
            r3.setVisible(r5)
            int r3 = org.videolan.vlc.R.id.action_mode_audio_share
            android.view.MenuItem r3 = r8.findItem(r3)
            r3.setVisible(r7)
            int r7 = org.videolan.vlc.R.id.action_mode_favorite_add
            android.view.MenuItem r7 = r8.findItem(r7)
            org.videolan.vlc.gui.audio.AudioBrowserAdapter r3 = r6.audioBrowserAdapter
            if (r3 != 0) goto L_0x009f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r3 = r0
        L_0x009f:
            org.videolan.tools.MultiSelectHelper r3 = r3.getMultiSelectHelper()
            java.util.List r3 = r3.getSelection()
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            boolean r4 = r3 instanceof java.util.Collection
            if (r4 == 0) goto L_0x00b8
            r4 = r3
            java.util.Collection r4 = (java.util.Collection) r4
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L_0x00b8
        L_0x00b6:
            r3 = 1
            goto L_0x00cf
        L_0x00b8:
            java.util.Iterator r3 = r3.iterator()
        L_0x00bc:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x00b6
            java.lang.Object r4 = r3.next()
            org.videolan.medialibrary.media.MediaLibraryItem r4 = (org.videolan.medialibrary.media.MediaLibraryItem) r4
            boolean r4 = r4.isFavorite()
            if (r4 == 0) goto L_0x00bc
            r3 = 0
        L_0x00cf:
            r7.setVisible(r3)
            int r7 = org.videolan.vlc.R.id.action_mode_favorite_remove
            android.view.MenuItem r7 = r8.findItem(r7)
            org.videolan.vlc.gui.audio.AudioBrowserAdapter r8 = r6.audioBrowserAdapter
            if (r8 != 0) goto L_0x00e0
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L_0x00e1
        L_0x00e0:
            r0 = r8
        L_0x00e1:
            org.videolan.tools.MultiSelectHelper r8 = r0.getMultiSelectHelper()
            java.util.List r8 = r8.getSelection()
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            boolean r0 = r8 instanceof java.util.Collection
            if (r0 == 0) goto L_0x00fa
            r0 = r8
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x00fa
        L_0x00f8:
            r2 = 1
            goto L_0x0111
        L_0x00fa:
            java.util.Iterator r8 = r8.iterator()
        L_0x00fe:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x00f8
            java.lang.Object r0 = r8.next()
            org.videolan.medialibrary.media.MediaLibraryItem r0 = (org.videolan.medialibrary.media.MediaLibraryItem) r0
            boolean r0 = r0.isFavorite()
            r0 = r0 ^ r5
            if (r0 == 0) goto L_0x00fe
        L_0x0111:
            r7.setVisible(r2)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.HeaderMediaListActivity.onPrepareActionMode(androidx.appcompat.view.ActionMode, android.view.Menu):boolean");
    }

    public boolean onActionItemClicked(ActionMode actionMode2, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(actionMode2, RtspHeaders.Values.MODE);
        Intrinsics.checkNotNullParameter(menuItem, "item");
        LifecycleOwner lifecycleOwner = this;
        if (!KotlinExtensionsKt.isStarted(lifecycleOwner)) {
            return false;
        }
        AudioBrowserAdapter audioBrowserAdapter2 = this.audioBrowserAdapter;
        if (audioBrowserAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
            audioBrowserAdapter2 = null;
        }
        List<MediaLibraryItem> selection = audioBrowserAdapter2.getMultiSelectHelper().getSelection();
        ArrayList arrayList = new ArrayList();
        for (MediaLibraryItem tracks : selection) {
            MediaWrapper[] tracks2 = tracks.getTracks();
            Intrinsics.checkNotNullExpressionValue(tracks2, "getTracks(...)");
            arrayList.addAll(CollectionsKt.listOf(Arrays.copyOf(tracks2, tracks2.length)));
        }
        AudioBrowserAdapter audioBrowserAdapter3 = this.audioBrowserAdapter;
        if (audioBrowserAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
            audioBrowserAdapter3 = null;
        }
        ArrayList<Integer> selectionMap = audioBrowserAdapter3.getMultiSelectHelper().getSelectionMap();
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_mode_audio_play) {
            MediaUtils.openList$default(MediaUtils.INSTANCE, this, arrayList, 0, false, 8, (Object) null);
        } else if (itemId == R.id.action_mode_audio_append) {
            MediaUtils.INSTANCE.appendMedia((Context) this, (List<? extends MediaWrapper>) arrayList);
        } else if (itemId == R.id.action_mode_audio_add_playlist) {
            UiTools.INSTANCE.addToPlaylist(this, arrayList);
        } else if (itemId == R.id.action_mode_audio_info) {
            MediaLibraryItem mediaLibraryItem = selection.get(0);
            Intrinsics.checkNotNull(mediaLibraryItem, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
            showInfoDialog((MediaWrapper) mediaLibraryItem);
        } else if (itemId == R.id.action_mode_audio_share) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new HeaderMediaListActivity$onActionItemClicked$2(this, selection, (Continuation<? super HeaderMediaListActivity$onActionItemClicked$2>) null), 3, (Object) null);
        } else if (itemId == R.id.action_mode_audio_set_song) {
            MediaLibraryItem mediaLibraryItem2 = selection.get(0);
            Intrinsics.checkNotNull(mediaLibraryItem2, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
            AudioUtil.INSTANCE.setRingtone(this, (MediaWrapper) mediaLibraryItem2);
        } else if (itemId == R.id.action_mode_audio_delete) {
            Job unused2 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new HeaderMediaListActivity$onActionItemClicked$3(this, arrayList, selectionMap, (Continuation<? super HeaderMediaListActivity$onActionItemClicked$3>) null), 3, (Object) null);
        } else if (itemId == R.id.action_mode_favorite_add) {
            Job unused3 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new HeaderMediaListActivity$onActionItemClicked$4(this, arrayList, (Continuation<? super HeaderMediaListActivity$onActionItemClicked$4>) null), 3, (Object) null);
        } else if (itemId != R.id.action_mode_favorite_remove) {
            return false;
        } else {
            Job unused4 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), (CoroutineContext) null, (CoroutineStart) null, new HeaderMediaListActivity$onActionItemClicked$5(this, arrayList, (Continuation<? super HeaderMediaListActivity$onActionItemClicked$5>) null), 3, (Object) null);
        }
        stopActionMode();
        return true;
    }

    public void onDestroyActionMode(ActionMode actionMode2) {
        Intrinsics.checkNotNullParameter(actionMode2, RtspHeaders.Values.MODE);
        AudioBrowserAdapter audioBrowserAdapter2 = this.audioBrowserAdapter;
        AudioBrowserAdapter audioBrowserAdapter3 = null;
        if (audioBrowserAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
            audioBrowserAdapter2 = null;
        }
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper = audioBrowserAdapter2.getMultiSelectHelper();
        AudioBrowserAdapter audioBrowserAdapter4 = this.audioBrowserAdapter;
        if (audioBrowserAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
            audioBrowserAdapter4 = null;
        }
        multiSelectHelper.toggleActionMode(false, audioBrowserAdapter4.getItemCount());
        this.actionMode = null;
        AudioBrowserAdapter audioBrowserAdapter5 = this.audioBrowserAdapter;
        if (audioBrowserAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
        } else {
            audioBrowserAdapter3 = audioBrowserAdapter5;
        }
        audioBrowserAdapter3.getMultiSelectHelper().clearSelection();
    }

    private final void showInfoDialog(MediaWrapper mediaWrapper) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("ML_ITEM", mediaWrapper);
        startActivity(intent);
    }

    public void onCtxAction(int i, ContextOption contextOption) {
        Intrinsics.checkNotNullParameter(contextOption, DuplicationWarningDialog.OPTION_KEY);
        AudioBrowserAdapter audioBrowserAdapter2 = this.audioBrowserAdapter;
        if (audioBrowserAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
            audioBrowserAdapter2 = null;
        }
        if (i < audioBrowserAdapter2.getItemCount()) {
            AudioBrowserAdapter audioBrowserAdapter3 = this.audioBrowserAdapter;
            if (audioBrowserAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
                audioBrowserAdapter3 = null;
            }
            MediaWrapper mediaWrapper = (MediaWrapper) audioBrowserAdapter3.getItem(i);
            if (mediaWrapper != null) {
                switch (WhenMappings.$EnumSwitchMapping$0[contextOption.ordinal()]) {
                    case 1:
                        showInfoDialog(mediaWrapper);
                        return;
                    case 2:
                        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new HeaderMediaListActivity$onCtxAction$1(this, i, mediaWrapper, (Continuation<? super HeaderMediaListActivity$onCtxAction$1>) null), 3, (Object) null);
                        return;
                    case 3:
                        MediaWrapper[] tracks = mediaWrapper.getTracks();
                        Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
                        MediaUtils.INSTANCE.appendMedia((Context) this, tracks);
                        return;
                    case 4:
                        MediaUtils.INSTANCE.insertNext((Context) this, mediaWrapper.getTracks());
                        return;
                    case 5:
                        MediaWrapper[] tracks2 = mediaWrapper.getTracks();
                        Intrinsics.checkNotNullExpressionValue(tracks2, "getTracks(...)");
                        UiTools.INSTANCE.addToPlaylist(this, tracks2, SavePlaylistDialog.KEY_NEW_TRACKS);
                        return;
                    case 6:
                        AudioUtil.INSTANCE.setRingtone(this, mediaWrapper);
                        return;
                    case 7:
                        Job unused2 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new HeaderMediaListActivity$onCtxAction$2(this, mediaWrapper, (Continuation<? super HeaderMediaListActivity$onCtxAction$2>) null), 3, (Object) null);
                        return;
                    case 8:
                        RenameDialog newInstance$default = RenameDialog.Companion.newInstance$default(RenameDialog.Companion, mediaWrapper, false, 2, (Object) null);
                        newInstance$default.show(getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(RenameDialog.class).getSimpleName());
                        newInstance$default.setListener(new HeaderMediaListActivity$onCtxAction$3(this));
                        return;
                    case 9:
                        String title = mediaWrapper.getTitle();
                        Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
                        String location = mediaWrapper.getLocation();
                        Intrinsics.checkNotNullExpressionValue(location, "getLocation(...)");
                        KotlinExtensionsKt.copy(this, title, location);
                        Snackbar.make(getWindow().getDecorView().findViewById(16908290), R.string.url_copied_to_clipboard, 0).show();
                        return;
                    case 10:
                    case 11:
                        Job unused3 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new HeaderMediaListActivity$onCtxAction$4(mediaWrapper, contextOption, (Continuation<? super HeaderMediaListActivity$onCtxAction$4>) null), 3, (Object) null);
                        return;
                    case 12:
                        Job unused4 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new HeaderMediaListActivity$onCtxAction$5(this, mediaWrapper, (Continuation<? super HeaderMediaListActivity$onCtxAction$5>) null), 3, (Object) null);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public final Object removeItem(int i, MediaWrapper mediaWrapper, Continuation<? super Unit> continuation) {
        if (this.isPlaylist) {
            Object removeFromPlaylist = removeFromPlaylist(CollectionsKt.listOf(mediaWrapper), CollectionsKt.listOf(Boxing.boxInt(i)), continuation);
            return removeFromPlaylist == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? removeFromPlaylist : Unit.INSTANCE;
        }
        removeItems(CollectionsKt.listOf(mediaWrapper));
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final void removeItems(List<? extends MediaWrapper> list) {
        ConfirmDeleteDialog newInstance$default = ConfirmDeleteDialog.Companion.newInstance$default(ConfirmDeleteDialog.Companion, new ArrayList(list), (String) null, (String) null, (String) null, 14, (Object) null);
        newInstance$default.show(getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(ConfirmDeleteDialog.class).getSimpleName());
        newInstance$default.setListener(new HeaderMediaListActivity$removeItems$1(this, list));
    }

    private final Job deleteMedia(MediaLibraryItem mediaLibraryItem) {
        return BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), (CoroutineStart) null, new HeaderMediaListActivity$deleteMedia$1(mediaLibraryItem, this, (Continuation<? super HeaderMediaListActivity$deleteMedia$1>) null), 2, (Object) null);
    }

    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        MediaUtils mediaUtils = MediaUtils.INSTANCE;
        Context context = this;
        PlaylistViewModel playlistViewModel = this.viewModel;
        if (playlistViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playlistViewModel = null;
        }
        MediaUtils.playTracks$default(mediaUtils, context, (MedialibraryProvider) playlistViewModel.getTracksProvider(), 0, false, 8, (Object) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: org.videolan.vlc.gui.helpers.SwipeDragItemTouchHelperCallback} */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v3, types: [org.videolan.vlc.gui.audio.AudioBrowserAdapter] */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object removeFromPlaylist(java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper> r9, java.util.List<java.lang.Integer> r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r8 = this;
            org.videolan.vlc.gui.helpers.UiTools r11 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            r0 = r8
            androidx.fragment.app.FragmentActivity r0 = (androidx.fragment.app.FragmentActivity) r0
            boolean r11 = r11.showPinIfNeeded(r0)
            r0 = 0
            if (r11 != 0) goto L_0x0053
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            org.videolan.vlc.viewmodels.mobile.PlaylistViewModel r11 = r8.viewModel
            if (r11 != 0) goto L_0x001b
            java.lang.String r11 = "viewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            r11 = r0
        L_0x001b:
            org.videolan.medialibrary.media.MediaLibraryItem r11 = r11.getPlaylist()
            boolean r1 = r11 instanceof org.videolan.medialibrary.interfaces.media.Playlist
            if (r1 == 0) goto L_0x0027
            org.videolan.medialibrary.interfaces.media.Playlist r11 = (org.videolan.medialibrary.interfaces.media.Playlist) r11
            r5 = r11
            goto L_0x0028
        L_0x0027:
            r5 = r0
        L_0x0028:
            if (r5 != 0) goto L_0x002d
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x002d:
            org.videolan.vlc.gui.helpers.SwipeDragItemTouchHelperCallback r11 = r8.itemTouchHelperCallback
            if (r11 != 0) goto L_0x0037
            java.lang.String r11 = "itemTouchHelperCallback"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            goto L_0x0038
        L_0x0037:
            r0 = r11
        L_0x0038:
            r11 = 0
            r0.setSwipeEnabled(r11)
            r11 = r8
            androidx.lifecycle.LifecycleOwner r11 = (androidx.lifecycle.LifecycleOwner) r11
            androidx.lifecycle.LifecycleCoroutineScope r11 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r11)
            org.videolan.vlc.gui.HeaderMediaListActivity$removeFromPlaylist$2 r0 = new org.videolan.vlc.gui.HeaderMediaListActivity$removeFromPlaylist$2
            r7 = 0
            r1 = r0
            r2 = r8
            r3 = r10
            r4 = r9
            r1.<init>(r2, r3, r4, r5, r6, r7)
            kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0
            r11.launchWhenStarted(r0)
            goto L_0x006a
        L_0x0053:
            int r9 = r8.lastDismissedPosition
            r10 = -1
            if (r9 == r10) goto L_0x006a
            org.videolan.vlc.gui.audio.AudioBrowserAdapter r9 = r8.audioBrowserAdapter
            if (r9 != 0) goto L_0x0062
            java.lang.String r9 = "audioBrowserAdapter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            goto L_0x0063
        L_0x0062:
            r0 = r9
        L_0x0063:
            int r9 = r8.lastDismissedPosition
            r0.notifyItemChanged(r9)
            r8.lastDismissedPosition = r10
        L_0x006a:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.HeaderMediaListActivity.removeFromPlaylist(java.util.List, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lorg/videolan/vlc/gui/HeaderMediaListActivity$Companion;", "", "()V", "ARTIST_FROM_ALBUM", "", "TAG", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: HeaderMediaListActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public String getFilterQuery() {
        PlaylistViewModel playlistViewModel = this.viewModel;
        if (playlistViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playlistViewModel = null;
        }
        return playlistViewModel.getFilterQuery();
    }

    public void filter(String str) {
        Intrinsics.checkNotNullParameter(str, "query");
        PlaylistViewModel playlistViewModel = this.viewModel;
        if (playlistViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playlistViewModel = null;
        }
        playlistViewModel.filter(str);
    }

    public void restoreList() {
        PlaylistViewModel playlistViewModel = this.viewModel;
        if (playlistViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playlistViewModel = null;
        }
        playlistViewModel.restore();
    }

    public boolean onQueryTextChange(String str) {
        if (str == null || str.length() != 0) {
            if (str == null) {
                str = "";
            }
            filter(str);
            return true;
        }
        restoreList();
        return true;
    }

    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        HeaderMediaListActivityBinding headerMediaListActivityBinding = this.binding;
        HeaderMediaListActivityBinding headerMediaListActivityBinding2 = null;
        if (headerMediaListActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            headerMediaListActivityBinding = null;
        }
        headerMediaListActivityBinding.appbar.setExpanded(false, true);
        AudioBrowserAdapter audioBrowserAdapter2 = this.audioBrowserAdapter;
        if (audioBrowserAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
            audioBrowserAdapter2 = null;
        }
        audioBrowserAdapter2.setStopReorder(true);
        AudioBrowserAdapter audioBrowserAdapter3 = this.audioBrowserAdapter;
        if (audioBrowserAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
            audioBrowserAdapter3 = null;
        }
        AudioBrowserAdapter audioBrowserAdapter4 = this.audioBrowserAdapter;
        if (audioBrowserAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
            audioBrowserAdapter4 = null;
        }
        audioBrowserAdapter3.notifyItemRangeChanged(0, audioBrowserAdapter4.getItemCount(), 7);
        HeaderMediaListActivityBinding headerMediaListActivityBinding3 = this.binding;
        if (headerMediaListActivityBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            headerMediaListActivityBinding2 = headerMediaListActivityBinding3;
        }
        ViewGroup.LayoutParams layoutParams = headerMediaListActivityBinding2.appbar.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
        Intrinsics.checkNotNull(behavior, "null cannot be cast to non-null type org.videolan.vlc.gui.helpers.ExpandStateAppBarLayoutBehavior");
        ((ExpandStateAppBarLayoutBehavior) behavior).setScrollEnabled(false);
        return true;
    }

    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        AudioBrowserAdapter audioBrowserAdapter2 = this.audioBrowserAdapter;
        HeaderMediaListActivityBinding headerMediaListActivityBinding = null;
        if (audioBrowserAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
            audioBrowserAdapter2 = null;
        }
        audioBrowserAdapter2.setStopReorder(false);
        AudioBrowserAdapter audioBrowserAdapter3 = this.audioBrowserAdapter;
        if (audioBrowserAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
            audioBrowserAdapter3 = null;
        }
        AudioBrowserAdapter audioBrowserAdapter4 = this.audioBrowserAdapter;
        if (audioBrowserAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
            audioBrowserAdapter4 = null;
        }
        audioBrowserAdapter3.notifyItemRangeChanged(0, audioBrowserAdapter4.getItemCount(), 7);
        HeaderMediaListActivityBinding headerMediaListActivityBinding2 = this.binding;
        if (headerMediaListActivityBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            headerMediaListActivityBinding = headerMediaListActivityBinding2;
        }
        ViewGroup.LayoutParams layoutParams = headerMediaListActivityBinding.appbar.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
        Intrinsics.checkNotNull(behavior, "null cannot be cast to non-null type org.videolan.vlc.gui.helpers.ExpandStateAppBarLayoutBehavior");
        ((ExpandStateAppBarLayoutBehavior) behavior).setScrollEnabled(true);
        return true;
    }
}
