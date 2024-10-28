package org.videolan.vlc.gui.audio;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.view.ActionMode;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.MultiSelectHelper;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.browser.MediaBrowserFragment;
import org.videolan.vlc.gui.dialogs.CtxActionReceiver;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;
import org.videolan.vlc.gui.dialogs.SavePlaylistDialog;
import org.videolan.vlc.gui.helpers.AudioUtil;
import org.videolan.vlc.gui.helpers.INavigator;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.view.RecyclerSectionItemDecoration;
import org.videolan.vlc.gui.view.RecyclerSectionItemGridDecoration;
import org.videolan.vlc.interfaces.IEventsHandler;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.viewmodels.MedialibraryViewModel;

@Metadata(d1 = {"\u0000è\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u00020\u00050\u00042\u00020\u00062\u00020\u00072\u00020\bB\u0005¢\u0006\u0002\u0010\tJ,\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020K2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00050M2\u0006\u0010N\u001a\u00020\u0018J\u000e\u0010O\u001a\u00020\"2\u0006\u0010P\u001a\u00020\u0018J\n\u0010Q\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010R\u001a\u00020KH$J\u0010\u0010S\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010TH\u0016J\u0018\u0010U\u001a\u00020\"2\u0006\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020YH\u0016J \u0010Z\u001a\u00020I2\u0006\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020\u00182\u0006\u0010X\u001a\u00020\u0005H\u0016J\u0010\u0010^\u001a\u00020I2\u0006\u0010_\u001a\u00020`H\u0016J\u0012\u0010a\u001a\u00020I2\b\u0010b\u001a\u0004\u0018\u00010cH\u0016J\u0018\u0010d\u001a\u00020\"2\u0006\u0010V\u001a\u00020W2\u0006\u0010e\u001a\u00020fH\u0016J\u0018\u0010g\u001a\u00020I2\u0006\u0010]\u001a\u00020\u00182\u0006\u0010h\u001a\u00020iH\u0016J \u0010j\u001a\u00020I2\u0006\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020\u00182\u0006\u0010X\u001a\u00020\u0005H\u0016J\u0010\u0010k\u001a\u00020I2\u0006\u0010l\u001a\u00020WH\u0016J\u0017\u0010k\u001a\u00020I2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0000¢\u0006\u0002\bmJ \u0010n\u001a\u00020I2\u0006\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020\u00182\u0006\u0010X\u001a\u00020\u0005H\u0016J\u0018\u0010o\u001a\u00020I2\u0006\u0010[\u001a\u00020\\2\u0006\u0010X\u001a\u00020\u0005H\u0016J \u0010p\u001a\u00020\"2\u0006\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020\u00182\u0006\u0010X\u001a\u00020\u0005H\u0016J \u0010q\u001a\u00020I2\u0006\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020\u00182\u0006\u0010X\u001a\u00020\u0005H\u0016J\u0010\u0010r\u001a\u00020\"2\u0006\u0010X\u001a\u00020YH\u0016J\u0010\u0010s\u001a\u00020I2\u0006\u0010t\u001a\u00020\u0018H\u0016J \u0010u\u001a\u00020I2\u0006\u0010]\u001a\u00020\u00182\u0006\u0010v\u001a\u00020w2\u0006\u0010x\u001a\u00020\u0018H\u0016J\u0010\u0010y\u001a\u00020I2\u0006\u0010]\u001a\u00020\u0018H\u0016J\u0018\u0010z\u001a\u00020\"2\u0006\u0010V\u001a\u00020W2\u0006\u0010e\u001a\u00020fH\u0016J\b\u0010{\u001a\u00020IH\u0016J\b\u0010|\u001a\u00020IH\u0016J\b\u0010}\u001a\u00020IH\u0016J\u0011\u0010~\u001a\u00020I2\u0007\u0010\u001a\u00030\u0001H\u0016J\u0012\u0010\u0001\u001a\u00020I2\u0007\u0010\u001a\u00030\u0001H\u0016J\u0012\u0010\u0001\u001a\u00020I2\u0007\u0010\u001a\u00030\u0001H\u0016J\u0016\u0010\u0001\u001a\u00020I2\u000b\u0010\n\u001a\u0007\u0012\u0002\b\u00030\u0001H\u0016J\u001c\u0010\u0001\u001a\u00020I2\u0007\u0010\u0001\u001a\u00020\\2\b\u0010b\u001a\u0004\u0018\u00010cH\u0016J\u0007\u0010\u0001\u001a\u00020IJ6\u0010\u0001\u001a\u00020I2\u0007\u0010\u0001\u001a\u00020\"2\u0006\u0010J\u001a\u00020K2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00050M2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010N\u001a\u00020\u0018J\t\u0010\u0001\u001a\u00020IH\u0016J\t\u0010\u0001\u001a\u00020IH\u0002J#\u0010\u0001\u001a\n\u0012\u0005\u0012\u00030\u00010\u0001*\t\u0012\u0004\u0012\u00020\u00050\u0001H@¢\u0006\u0003\u0010\u0001R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011X.¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u00188D@DX\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001cR\u0014\u0010!\u001a\u00020\"8DX\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X.¢\u0006\u0002\n\u0000R\u001a\u0010-\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001a\"\u0004\b/\u0010\u001cR\u001a\u00100\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u001a\"\u0004\b2\u0010\u001cR\u001a\u00103\u001a\u00020\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010$\"\u0004\b5\u00106R\u0014\u00107\u001a\u000208X\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u001c\u0010;\u001a\u0004\u0018\u00010<X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u000e\u0010A\u001a\u00020,X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010B\u001a\u00020CX.¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010E\"\u0004\bF\u0010G¨\u0006\u0001"}, d2 = {"Lorg/videolan/vlc/gui/audio/BaseAudioBrowser;", "T", "Lorg/videolan/vlc/viewmodels/MedialibraryViewModel;", "Lorg/videolan/vlc/gui/browser/MediaBrowserFragment;", "Lorg/videolan/vlc/interfaces/IEventsHandler;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lorg/videolan/vlc/gui/dialogs/CtxActionReceiver;", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "Lcom/google/android/material/tabs/TabLayout$OnTabSelectedListener;", "()V", "adapter", "Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;", "getAdapter", "()Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;", "setAdapter", "(Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;)V", "adapters", "", "getAdapters$vlc_android_release", "()[Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;", "setAdapters$vlc_android_release", "([Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;)V", "[Lorg/videolan/vlc/gui/audio/AudioBrowserAdapter;", "backgroundColor", "", "getBackgroundColor", "()I", "setBackgroundColor", "(I)V", "value", "currentTab", "getCurrentTab", "setCurrentTab", "empty", "", "getEmpty", "()Z", "lastQuery", "", "getLastQuery", "()Ljava/lang/String;", "setLastQuery", "(Ljava/lang/String;)V", "layoutOnPageChangeListener", "Lcom/google/android/material/tabs/TabLayout$TabLayoutOnPageChangeListener;", "listColor", "getListColor", "setListColor", "nbColumns", "getNbColumns", "setNbColumns", "needToReopenSearch", "getNeedToReopenSearch", "setNeedToReopenSearch", "(Z)V", "scrollListener", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "getScrollListener$vlc_android_release", "()Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "tabLayout", "Lcom/google/android/material/tabs/TabLayout;", "getTabLayout", "()Lcom/google/android/material/tabs/TabLayout;", "setTabLayout", "(Lcom/google/android/material/tabs/TabLayout;)V", "tcl", "viewPager", "Landroidx/viewpager/widget/ViewPager;", "getViewPager", "()Landroidx/viewpager/widget/ViewPager;", "setViewPager", "(Landroidx/viewpager/widget/ViewPager;)V", "displayListInGrid", "", "list", "Landroidx/recyclerview/widget/RecyclerView;", "provider", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "spacing", "emptyAt", "index", "getCurrentAdapter", "getCurrentRV", "getMultiHelper", "Lorg/videolan/tools/MultiSelectHelper;", "onActionItemClicked", "mode", "Landroidx/appcompat/view/ActionMode;", "item", "Landroid/view/MenuItem;", "onClick", "v", "Landroid/view/View;", "position", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateActionMode", "menu", "Landroid/view/Menu;", "onCtxAction", "option", "Lorg/videolan/vlc/util/ContextOption;", "onCtxClick", "onDestroyActionMode", "actionMode", "onDestroyActionMode$vlc_android_release", "onImageClick", "onItemFocused", "onLongClick", "onMainActionClick", "onOptionsItemSelected", "onPageScrollStateChanged", "state", "onPageScrolled", "positionOffset", "", "positionOffsetPixels", "onPageSelected", "onPrepareActionMode", "onRefresh", "onStart", "onStop", "onTabReselected", "tab", "Lcom/google/android/material/tabs/TabLayout$Tab;", "onTabSelected", "onTabUnselected", "onUpdateFinished", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "onViewCreated", "view", "reopenSearchIfNeeded", "setupLayoutManager", "providerInCard", "setupTabLayout", "unSetTabLayout", "getTracks", "Ljava/util/ArrayList;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseAudioBrowser.kt */
public abstract class BaseAudioBrowser<T extends MedialibraryViewModel> extends MediaBrowserFragment<T> implements IEventsHandler<MediaLibraryItem>, CtxActionReceiver, ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {
    private AudioBrowserAdapter adapter;
    public AudioBrowserAdapter[] adapters;
    private int backgroundColor = -1;
    private String lastQuery = "";
    private TabLayout.TabLayoutOnPageChangeListener layoutOnPageChangeListener;
    private int listColor = -1;
    private int nbColumns = 2;
    private boolean needToReopenSearch;
    private final RecyclerView.OnScrollListener scrollListener = new BaseAudioBrowser$scrollListener$1(this);
    private TabLayout tabLayout;
    private final TabLayout.TabLayoutOnPageChangeListener tcl = new TabLayout.TabLayoutOnPageChangeListener(this.tabLayout);
    public ViewPager viewPager;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BaseAudioBrowser.kt */
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
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_PLAY_SHUFFLE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_PLAY_AS_AUDIO     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_INFORMATION     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_DELETE     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_APPEND     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_PLAY_NEXT     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_ADD_TO_PLAYLIST     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_SET_RINGTONE     // Catch:{ NoSuchFieldError -> 0x005a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_SHARE     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_GO_TO_FOLDER     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_ADD_SHORTCUT     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_FAV_ADD     // Catch:{ NoSuchFieldError -> 0x0082 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0082 }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0082 }
            L_0x0082:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_FAV_REMOVE     // Catch:{ NoSuchFieldError -> 0x008c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008c }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x008c }
            L_0x008c:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.BaseAudioBrowser.WhenMappings.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public abstract RecyclerView getCurrentRV();

    public void onItemFocused(View view, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
    }

    public void onPageSelected(int i) {
    }

    public void onRefresh() {
    }

    public void onTabReselected(TabLayout.Tab tab) {
        Intrinsics.checkNotNullParameter(tab, "tab");
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    public final void setBackgroundColor(int i) {
        this.backgroundColor = i;
    }

    public final int getListColor() {
        return this.listColor;
    }

    public final void setListColor(int i) {
        this.listColor = i;
    }

    public final AudioBrowserAdapter[] getAdapters$vlc_android_release() {
        AudioBrowserAdapter[] audioBrowserAdapterArr = this.adapters;
        if (audioBrowserAdapterArr != null) {
            return audioBrowserAdapterArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapters");
        return null;
    }

    public final void setAdapters$vlc_android_release(AudioBrowserAdapter[] audioBrowserAdapterArr) {
        Intrinsics.checkNotNullParameter(audioBrowserAdapterArr, "<set-?>");
        this.adapters = audioBrowserAdapterArr;
    }

    public final TabLayout getTabLayout() {
        return this.tabLayout;
    }

    public final void setTabLayout(TabLayout tabLayout2) {
        this.tabLayout = tabLayout2;
    }

    public final ViewPager getViewPager() {
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 != null) {
            return viewPager2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        return null;
    }

    public final void setViewPager(ViewPager viewPager2) {
        Intrinsics.checkNotNullParameter(viewPager2, "<set-?>");
        this.viewPager = viewPager2;
    }

    public final int getNbColumns() {
        return this.nbColumns;
    }

    public final void setNbColumns(int i) {
        this.nbColumns = i;
    }

    /* access modifiers changed from: protected */
    public final AudioBrowserAdapter getAdapter() {
        return this.adapter;
    }

    /* access modifiers changed from: protected */
    public final void setAdapter(AudioBrowserAdapter audioBrowserAdapter) {
        this.adapter = audioBrowserAdapter;
    }

    public AudioBrowserAdapter getCurrentAdapter() {
        return this.adapter;
    }

    public final boolean getNeedToReopenSearch() {
        return this.needToReopenSearch;
    }

    public final void setNeedToReopenSearch(boolean z) {
        this.needToReopenSearch = z;
    }

    public final String getLastQuery() {
        return this.lastQuery;
    }

    public final void setLastQuery(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lastQuery = str;
    }

    /* access modifiers changed from: protected */
    public final int getCurrentTab() {
        if (this.viewPager != null) {
            return getViewPager().getCurrentItem();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public final void setCurrentTab(int i) {
        if (this.viewPager != null) {
            getViewPager().setCurrentItem(i);
        }
    }

    public final RecyclerView.OnScrollListener getScrollListener$vlc_android_release() {
        return this.scrollListener;
    }

    public final void displayListInGrid(RecyclerView recyclerView, AudioBrowserAdapter audioBrowserAdapter, MedialibraryProvider<MediaLibraryItem> medialibraryProvider, int i) {
        Intrinsics.checkNotNullParameter(recyclerView, "list");
        Intrinsics.checkNotNullParameter(audioBrowserAdapter, "adapter");
        Intrinsics.checkNotNullParameter(medialibraryProvider, "provider");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireActivity(), this.nbColumns);
        gridLayoutManager.setSpanSizeLookup(new BaseAudioBrowser$displayListInGrid$1(audioBrowserAdapter, medialibraryProvider, this));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new RecyclerSectionItemGridDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_section_header_height), i, KotlinExtensionsKt.getDp(16), true, this.nbColumns, medialibraryProvider));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.nbColumns = getResources().getInteger(R.integer.mobile_card_columns);
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = requireActivity().getTheme();
        Intrinsics.checkNotNullExpressionValue(theme, "getTheme(...)");
        theme.resolveAttribute(R.attr.background_default_darker, typedValue, true);
        this.backgroundColor = typedValue.data;
        theme.resolveAttribute(R.attr.background_default, typedValue, true);
        this.listColor = typedValue.data;
    }

    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        this.nbColumns = getResources().getInteger(R.integer.mobile_card_columns);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ViewPager viewPager2 = (ViewPager) view.findViewById(R.id.pager);
        if (viewPager2 != null) {
            setViewPager(viewPager2);
        }
        this.tabLayout = (TabLayout) requireActivity().findViewById(R.id.sliding_tabs);
    }

    public final void setupLayoutManager(boolean z, RecyclerView recyclerView, MedialibraryProvider<MediaLibraryItem> medialibraryProvider, AudioBrowserAdapter audioBrowserAdapter, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(recyclerView, "list");
        Intrinsics.checkNotNullParameter(medialibraryProvider, "provider");
        Intrinsics.checkNotNullParameter(audioBrowserAdapter, "adapter");
        if (recyclerView.getItemDecorationCount() > 0) {
            recyclerView.removeItemDecorationAt(0);
        }
        int i3 = -1;
        if (z) {
            FragmentActivity requireActivity = requireActivity();
            INavigator iNavigator = requireActivity instanceof INavigator ? (INavigator) requireActivity : null;
            if (iNavigator != null) {
                FragmentActivity requireActivity2 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                i2 = iNavigator.getFragmentWidth(requireActivity2);
            } else {
                FragmentActivity requireActivity3 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity(...)");
                i2 = KextensionsKt.getScreenWidth(requireActivity3);
            }
            audioBrowserAdapter.setCardSize$vlc_android_release(RecyclerSectionItemGridDecoration.Companion.getItemSize(i2, this.nbColumns, i, KotlinExtensionsKt.getDp(16)));
            displayListInGrid(recyclerView, audioBrowserAdapter, medialibraryProvider, i);
        } else {
            audioBrowserAdapter.setCardSize$vlc_android_release(-1);
            recyclerView.addItemDecoration(new RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_section_header_height), true, medialibraryProvider));
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
        float dimension = requireActivity().getResources().getDimension(R.dimen.default_content_width);
        if (!z) {
            i3 = (int) dimension;
        }
        layoutParams.width = i3;
        ViewParent parent = recyclerView.getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
        ((View) parent).setBackgroundColor((z || dimension == -1.0f) ? ContextCompat.getColor(requireContext(), R.color.transparent) : this.backgroundColor);
        recyclerView.setBackgroundColor((z || dimension == -1.0f) ? ContextCompat.getColor(requireContext(), R.color.transparent) : this.listColor);
        recyclerView.setLayoutParams(layoutParams);
    }

    public void setupTabLayout() {
        TabLayout tabLayout2 = this.tabLayout;
        if (tabLayout2 != null && this.viewPager != null) {
            if (tabLayout2 != null) {
                tabLayout2.setupWithViewPager(getViewPager());
            }
            if (this.layoutOnPageChangeListener == null) {
                this.layoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(this.tabLayout);
            }
            ViewPager viewPager2 = getViewPager();
            TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener = this.layoutOnPageChangeListener;
            if (tabLayoutOnPageChangeListener == null) {
                Intrinsics.throwUninitializedPropertyAccessException("layoutOnPageChangeListener");
                tabLayoutOnPageChangeListener = null;
            }
            viewPager2.addOnPageChangeListener(tabLayoutOnPageChangeListener);
            TabLayout tabLayout3 = this.tabLayout;
            if (tabLayout3 != null) {
                tabLayout3.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) this);
            }
            getViewPager().addOnPageChangeListener(this);
        }
    }

    private final void unSetTabLayout() {
        if (this.viewPager != null) {
            ViewPager viewPager2 = getViewPager();
            TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener = this.layoutOnPageChangeListener;
            if (tabLayoutOnPageChangeListener == null) {
                Intrinsics.throwUninitializedPropertyAccessException("layoutOnPageChangeListener");
                tabLayoutOnPageChangeListener = null;
            }
            viewPager2.removeOnPageChangeListener(tabLayoutOnPageChangeListener);
        }
        TabLayout tabLayout2 = this.tabLayout;
        if (tabLayout2 != null) {
            tabLayout2.removeOnTabSelectedListener((TabLayout.OnTabSelectedListener) this);
        }
        if (this.viewPager != null) {
            getViewPager().removeOnPageChangeListener(this);
        }
        TabLayout tabLayout3 = this.tabLayout;
        if (tabLayout3 != null) {
            tabLayout3.setupWithViewPager((ViewPager) null);
        }
    }

    public void onStart() {
        super.onStart();
        setupTabLayout();
    }

    public void onStop() {
        super.onStop();
        unSetTabLayout();
    }

    public void onTabSelected(TabLayout.Tab tab) {
        Intrinsics.checkNotNullParameter(tab, "tab");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
    }

    /* JADX WARNING: type inference failed for: r3v4, types: [androidx.fragment.app.FragmentActivity] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onTabUnselected(com.google.android.material.tabs.TabLayout.Tab r3) {
        /*
            r2 = this;
            java.lang.String r0 = "tab"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            r2.stopActionMode()
            androidx.fragment.app.FragmentActivity r3 = r2.getActivity()
            boolean r0 = r3 instanceof org.videolan.vlc.gui.ContentActivity
            r1 = 0
            if (r0 == 0) goto L_0x0014
            org.videolan.vlc.gui.ContentActivity r3 = (org.videolan.vlc.gui.ContentActivity) r3
            goto L_0x0015
        L_0x0014:
            r3 = r1
        L_0x0015:
            if (r3 == 0) goto L_0x001c
            boolean r3 = r3.isSearchViewVisible()
            goto L_0x001d
        L_0x001c:
            r3 = 0
        L_0x001d:
            r2.needToReopenSearch = r3
            androidx.fragment.app.FragmentActivity r3 = r2.getActivity()
            boolean r0 = r3 instanceof org.videolan.vlc.gui.ContentActivity
            if (r0 == 0) goto L_0x002a
            r1 = r3
            org.videolan.vlc.gui.ContentActivity r1 = (org.videolan.vlc.gui.ContentActivity) r1
        L_0x002a:
            if (r1 == 0) goto L_0x0032
            java.lang.String r3 = r1.getCurrentQuery()
            if (r3 != 0) goto L_0x0034
        L_0x0032:
            java.lang.String r3 = ""
        L_0x0034:
            r2.lastQuery = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.BaseAudioBrowser.onTabUnselected(com.google.android.material.tabs.TabLayout$Tab):void");
    }

    public void onPageScrolled(int i, float f, int i2) {
        this.tcl.onPageScrolled(i, f, i2);
    }

    public void onPageScrollStateChanged(int i) {
        this.tcl.onPageScrollStateChanged(i);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() != R.id.ml_menu_last_playlist) {
            return super.onOptionsItemSelected(menuItem);
        }
        MediaUtils.INSTANCE.loadlastPlaylist(getActivity(), 0);
        return true;
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        Intrinsics.checkNotNullParameter(actionMode, RtspHeaders.Values.MODE);
        Intrinsics.checkNotNullParameter(menu, "menu");
        AudioBrowserAdapter currentAdapter = getCurrentAdapter();
        if (currentAdapter != null) {
            int itemCount = currentAdapter.getItemCount();
            MultiSelectHelper multiHelper = getMultiHelper();
            if (multiHelper != null) {
                multiHelper.toggleActionMode(true, itemCount);
            }
        }
        actionMode.getMenuInflater().inflate(R.menu.action_mode_audio_browser, menu);
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: org.videolan.medialibrary.interfaces.media.MediaWrapper} */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0011, code lost:
        r0 = r0.getMultiSelectHelper();
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPrepareActionMode(androidx.appcompat.view.ActionMode r13, android.view.Menu r14) {
        /*
            r12 = this;
            java.lang.String r0 = "mode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "menu"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            org.videolan.vlc.gui.audio.AudioBrowserAdapter r0 = r12.getCurrentAdapter()
            r1 = 0
            if (r0 == 0) goto L_0x001c
            org.videolan.tools.MultiSelectHelper r0 = r0.getMultiSelectHelper()
            if (r0 == 0) goto L_0x001c
            java.util.List r0 = r0.getSelection()
            goto L_0x001d
        L_0x001c:
            r0 = r1
        L_0x001d:
            r2 = 0
            if (r0 == 0) goto L_0x0171
            int r3 = r0.size()
            if (r3 != 0) goto L_0x002a
            r12.stopActionMode()
            return r2
        L_0x002a:
            org.videolan.vlc.gui.audio.AudioBrowserAdapter r4 = r12.getCurrentAdapter()
            if (r4 == 0) goto L_0x004f
            org.videolan.tools.MultiSelectHelper r4 = r4.getMultiSelectHelper()
            if (r4 == 0) goto L_0x004f
            r5 = r12
            androidx.lifecycle.LifecycleOwner r5 = (androidx.lifecycle.LifecycleOwner) r5
            androidx.lifecycle.LifecycleCoroutineScope r5 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r5)
            r6 = r5
            kotlinx.coroutines.CoroutineScope r6 = (kotlinx.coroutines.CoroutineScope) r6
            org.videolan.vlc.gui.audio.BaseAudioBrowser$onPrepareActionMode$1$1 r5 = new org.videolan.vlc.gui.audio.BaseAudioBrowser$onPrepareActionMode$1$1
            r5.<init>(r12, r13, r4, r1)
            r9 = r5
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            r10 = 3
            r11 = 0
            r7 = 0
            r8 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r6, r7, r8, r9, r10, r11)
        L_0x004f:
            java.lang.Object r13 = kotlin.collections.CollectionsKt.first(r0)
            org.videolan.medialibrary.media.MediaLibraryItem r13 = (org.videolan.medialibrary.media.MediaLibraryItem) r13
            int r13 = r13.getItemType()
            r0 = 32
            r4 = 1
            if (r13 != r0) goto L_0x0060
            r13 = 1
            goto L_0x0061
        L_0x0060:
            r13 = 0
        L_0x0061:
            if (r3 != r4) goto L_0x0067
            if (r13 == 0) goto L_0x0067
            r0 = 1
            goto L_0x0068
        L_0x0067:
            r0 = 0
        L_0x0068:
            int r5 = org.videolan.vlc.R.id.action_mode_audio_set_song
            android.view.MenuItem r5 = r14.findItem(r5)
            if (r0 == 0) goto L_0x007a
            org.videolan.resources.AndroidDevices r0 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r0 = r0.isPhone()
            if (r0 == 0) goto L_0x007a
            r0 = 1
            goto L_0x007b
        L_0x007a:
            r0 = 0
        L_0x007b:
            r5.setVisible(r0)
            int r0 = org.videolan.vlc.R.id.action_mode_audio_info
            android.view.MenuItem r0 = r14.findItem(r0)
            if (r3 != r4) goto L_0x0088
            r5 = 1
            goto L_0x0089
        L_0x0088:
            r5 = 0
        L_0x0089:
            r0.setVisible(r5)
            int r0 = org.videolan.vlc.R.id.action_mode_audio_append
            android.view.MenuItem r0 = r14.findItem(r0)
            org.videolan.vlc.media.PlaylistManager$Companion r5 = org.videolan.vlc.media.PlaylistManager.Companion
            boolean r5 = r5.hasMedia()
            r0.setVisible(r5)
            int r0 = org.videolan.vlc.R.id.action_mode_audio_delete
            android.view.MenuItem r0 = r14.findItem(r0)
            r0.setVisible(r13)
            int r0 = org.videolan.vlc.R.id.action_mode_audio_share
            android.view.MenuItem r0 = r14.findItem(r0)
            r0.setVisible(r13)
            int r0 = org.videolan.vlc.R.id.action_mode_audio_share
            android.view.MenuItem r0 = r14.findItem(r0)
            r0.setVisible(r13)
            int r13 = org.videolan.vlc.R.id.action_mode_favorite_add
            android.view.MenuItem r13 = r14.findItem(r13)
            org.videolan.vlc.gui.audio.AudioBrowserAdapter r0 = r12.getCurrentAdapter()
            if (r0 == 0) goto L_0x00f5
            org.videolan.tools.MultiSelectHelper r0 = r0.getMultiSelectHelper()
            if (r0 == 0) goto L_0x00f5
            java.util.List r0 = r0.getSelection()
            if (r0 == 0) goto L_0x00f5
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            boolean r5 = r0 instanceof java.util.Collection
            if (r5 == 0) goto L_0x00df
            r5 = r0
            java.util.Collection r5 = (java.util.Collection) r5
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x00df
        L_0x00dd:
            r0 = 1
            goto L_0x00f6
        L_0x00df:
            java.util.Iterator r0 = r0.iterator()
        L_0x00e3:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x00dd
            java.lang.Object r5 = r0.next()
            org.videolan.medialibrary.media.MediaLibraryItem r5 = (org.videolan.medialibrary.media.MediaLibraryItem) r5
            boolean r5 = r5.isFavorite()
            if (r5 == 0) goto L_0x00e3
        L_0x00f5:
            r0 = 0
        L_0x00f6:
            r13.setVisible(r0)
            int r13 = org.videolan.vlc.R.id.action_mode_favorite_remove
            android.view.MenuItem r13 = r14.findItem(r13)
            org.videolan.vlc.gui.audio.AudioBrowserAdapter r0 = r12.getCurrentAdapter()
            if (r0 == 0) goto L_0x0139
            org.videolan.tools.MultiSelectHelper r0 = r0.getMultiSelectHelper()
            if (r0 == 0) goto L_0x0139
            java.util.List r0 = r0.getSelection()
            if (r0 == 0) goto L_0x0139
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            boolean r5 = r0 instanceof java.util.Collection
            if (r5 == 0) goto L_0x0122
            r5 = r0
            java.util.Collection r5 = (java.util.Collection) r5
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x0122
        L_0x0120:
            r0 = 1
            goto L_0x013a
        L_0x0122:
            java.util.Iterator r0 = r0.iterator()
        L_0x0126:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x0120
            java.lang.Object r5 = r0.next()
            org.videolan.medialibrary.media.MediaLibraryItem r5 = (org.videolan.medialibrary.media.MediaLibraryItem) r5
            boolean r5 = r5.isFavorite()
            r5 = r5 ^ r4
            if (r5 == 0) goto L_0x0126
        L_0x0139:
            r0 = 0
        L_0x013a:
            r13.setVisible(r0)
            int r13 = org.videolan.vlc.R.id.action_mode_go_to_folder
            android.view.MenuItem r13 = r14.findItem(r13)
            if (r3 != r4) goto L_0x016d
            org.videolan.vlc.gui.audio.AudioBrowserAdapter r14 = r12.getCurrentAdapter()
            if (r14 == 0) goto L_0x016d
            org.videolan.tools.MultiSelectHelper r14 = r14.getMultiSelectHelper()
            if (r14 == 0) goto L_0x016d
            java.util.List r14 = r14.getSelection()
            java.lang.Object r14 = kotlin.collections.CollectionsKt.first(r14)
            boolean r0 = r14 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r0 == 0) goto L_0x0160
            r1 = r14
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
        L_0x0160:
            if (r1 == 0) goto L_0x016d
            android.net.Uri r14 = r1.getUri()
            android.net.Uri r14 = org.videolan.tools.KotlinExtensionsKt.retrieveParent(r14)
            if (r14 == 0) goto L_0x016d
            r2 = 1
        L_0x016d:
            r13.setVisible(r2)
            return r4
        L_0x0171:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.BaseAudioBrowser.onPrepareActionMode(androidx.appcompat.view.ActionMode, android.view.Menu):boolean");
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [androidx.fragment.app.FragmentActivity] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void reopenSearchIfNeeded() {
        /*
            r3 = this;
            boolean r0 = r3.needToReopenSearch
            if (r0 == 0) goto L_0x002f
            androidx.fragment.app.FragmentActivity r0 = r3.getActivity()
            boolean r1 = r0 instanceof org.videolan.vlc.gui.ContentActivity
            r2 = 0
            if (r1 == 0) goto L_0x0010
            org.videolan.vlc.gui.ContentActivity r0 = (org.videolan.vlc.gui.ContentActivity) r0
            goto L_0x0011
        L_0x0010:
            r0 = r2
        L_0x0011:
            if (r0 == 0) goto L_0x0016
            r0.openSearchView()
        L_0x0016:
            androidx.fragment.app.FragmentActivity r0 = r3.getActivity()
            boolean r1 = r0 instanceof org.videolan.vlc.gui.ContentActivity
            if (r1 == 0) goto L_0x0021
            r2 = r0
            org.videolan.vlc.gui.ContentActivity r2 = (org.videolan.vlc.gui.ContentActivity) r2
        L_0x0021:
            if (r2 == 0) goto L_0x0028
            java.lang.String r0 = r3.lastQuery
            r2.setCurrentQuery(r0)
        L_0x0028:
            java.lang.String r0 = ""
            r3.lastQuery = r0
            r0 = 0
            r3.needToReopenSearch = r0
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.BaseAudioBrowser.reopenSearchIfNeeded():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001c, code lost:
        r0 = r0.getMultiSelectHelper();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onActionItemClicked(androidx.appcompat.view.ActionMode r9, android.view.MenuItem r10) {
        /*
            r8 = this;
            java.lang.String r0 = "mode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r9 = "item"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r9)
            r9 = r8
            androidx.lifecycle.LifecycleOwner r9 = (androidx.lifecycle.LifecycleOwner) r9
            boolean r0 = org.videolan.tools.KotlinExtensionsKt.isStarted(r9)
            if (r0 != 0) goto L_0x0015
            r9 = 0
            return r9
        L_0x0015:
            org.videolan.vlc.gui.audio.AudioBrowserAdapter r0 = r8.getCurrentAdapter()
            r1 = 0
            if (r0 == 0) goto L_0x0027
            org.videolan.tools.MultiSelectHelper r0 = r0.getMultiSelectHelper()
            if (r0 == 0) goto L_0x0027
            java.util.List r0 = r0.getSelection()
            goto L_0x0028
        L_0x0027:
            r0 = r1
        L_0x0028:
            r8.stopActionMode()
            r2 = r0
            java.util.Collection r2 = (java.util.Collection) r2
            if (r2 == 0) goto L_0x004d
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0037
            goto L_0x004d
        L_0x0037:
            androidx.lifecycle.LifecycleCoroutineScope r9 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r9)
            r2 = r9
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            org.videolan.vlc.gui.audio.BaseAudioBrowser$onActionItemClicked$1 r9 = new org.videolan.vlc.gui.audio.BaseAudioBrowser$onActionItemClicked$1
            r9.<init>(r8, r10, r0, r1)
            r5 = r9
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = 3
            r7 = 0
            r3 = 0
            r4 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r2, r3, r4, r5, r6, r7)
        L_0x004d:
            r9 = 1
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.BaseAudioBrowser.onActionItemClicked(androidx.appcompat.view.ActionMode, android.view.MenuItem):boolean");
    }

    /* access modifiers changed from: private */
    public final Object getTracks(List<? extends MediaLibraryItem> list, Continuation<? super ArrayList<MediaWrapper>> continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new BaseAudioBrowser$getTracks$2(list, (Continuation<? super BaseAudioBrowser$getTracks$2>) null), continuation);
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        Intrinsics.checkNotNullParameter(actionMode, "actionMode");
        onDestroyActionMode$vlc_android_release(getCurrentAdapter());
    }

    public final void onDestroyActionMode$vlc_android_release(AudioBrowserAdapter audioBrowserAdapter) {
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper;
        if (audioBrowserAdapter != null) {
            int itemCount = audioBrowserAdapter.getItemCount();
            MultiSelectHelper multiHelper = getMultiHelper();
            if (multiHelper != null) {
                multiHelper.toggleActionMode(false, itemCount);
            }
        }
        setFabPlayVisibility(true);
        setActionMode((ActionMode) null);
        if (audioBrowserAdapter != null && (multiSelectHelper = audioBrowserAdapter.getMultiSelectHelper()) != null) {
            multiSelectHelper.clearSelection();
        }
    }

    public void onClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper;
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        if (getActionMode() != null) {
            AudioBrowserAdapter currentAdapter = getCurrentAdapter();
            if (!(currentAdapter == null || (multiSelectHelper = currentAdapter.getMultiSelectHelper()) == null)) {
                MultiSelectHelper.toggleSelection$default(multiSelectHelper, i, false, 2, (Object) null);
            }
            invalidateActionMode();
        }
    }

    public boolean onLongClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper;
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        AudioBrowserAdapter currentAdapter = getCurrentAdapter();
        if (!(currentAdapter == null || (multiSelectHelper = currentAdapter.getMultiSelectHelper()) == null)) {
            multiSelectHelper.toggleSelection(i, true);
        }
        if (getActionMode() == null && inSearchMode()) {
            UiTools.INSTANCE.setKeyboardVisibility(view, false);
        }
        if (getActionMode() == null) {
            startActionMode();
        } else {
            invalidateActionMode();
        }
        return true;
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

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v4, types: [org.videolan.medialibrary.interfaces.media.Album] */
    /* JADX WARNING: type inference failed for: r1v8, types: [org.videolan.medialibrary.interfaces.media.Artist] */
    /* JADX WARNING: type inference failed for: r1v12, types: [org.videolan.medialibrary.interfaces.media.Genre] */
    /* JADX WARNING: type inference failed for: r1v16, types: [org.videolan.medialibrary.interfaces.media.MediaWrapper] */
    /* JADX WARNING: type inference failed for: r1v20 */
    /* JADX WARNING: type inference failed for: r1v21 */
    /* JADX WARNING: type inference failed for: r1v22 */
    /* JADX WARNING: type inference failed for: r1v23 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCtxClick(android.view.View r5, int r6, org.videolan.medialibrary.media.MediaLibraryItem r7) {
        /*
            r4 = this;
            java.lang.String r0 = "v"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r5 = "item"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r5)
            int r5 = r7.getItemType()
            r0 = 1
            r1 = 0
            r2 = 2
            if (r5 == r2) goto L_0x00d1
            r3 = 4
            if (r5 == r3) goto L_0x00a4
            r3 = 8
            if (r5 == r3) goto L_0x0077
            r3 = 16
            if (r5 == r3) goto L_0x004b
            r2 = 32
            if (r5 == r2) goto L_0x002a
            org.videolan.vlc.util.ContextOption$Companion r5 = org.videolan.vlc.util.ContextOption.Companion
            org.videolan.vlc.util.FlagSet r5 = r5.createCtxAudioFlags()
            goto L_0x00fd
        L_0x002a:
            org.videolan.vlc.util.ContextOption$Companion r5 = org.videolan.vlc.util.ContextOption.Companion
            org.videolan.vlc.util.FlagSet r5 = r5.createCtxTrackFlags()
            boolean r2 = r7 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r2 == 0) goto L_0x0037
            r1 = r7
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
        L_0x0037:
            if (r1 == 0) goto L_0x0042
            boolean r1 = r1.isFavorite()
            if (r1 != r0) goto L_0x0042
            org.videolan.vlc.util.ContextOption r0 = org.videolan.vlc.util.ContextOption.CTX_FAV_REMOVE
            goto L_0x0044
        L_0x0042:
            org.videolan.vlc.util.ContextOption r0 = org.videolan.vlc.util.ContextOption.CTX_FAV_ADD
        L_0x0044:
            java.lang.Enum r0 = (java.lang.Enum) r0
            r5.add(r0)
            goto L_0x00fd
        L_0x004b:
            org.videolan.vlc.util.ContextOption$Companion r5 = org.videolan.vlc.util.ContextOption.Companion
            org.videolan.vlc.util.FlagSet r5 = r5.createCtxPlaylistAlbumFlags()
            org.videolan.vlc.util.ContextOption r0 = org.videolan.vlc.util.ContextOption.CTX_PLAY_AS_AUDIO
            java.lang.Enum r0 = (java.lang.Enum) r0
            r5.add(r0)
            int r0 = r7.getTracksCount()
            if (r0 <= r2) goto L_0x0065
            org.videolan.vlc.util.ContextOption r0 = org.videolan.vlc.util.ContextOption.CTX_PLAY_SHUFFLE
            java.lang.Enum r0 = (java.lang.Enum) r0
            r5.add(r0)
        L_0x0065:
            boolean r0 = r7.isFavorite()
            if (r0 == 0) goto L_0x006e
            org.videolan.vlc.util.ContextOption r0 = org.videolan.vlc.util.ContextOption.CTX_FAV_REMOVE
            goto L_0x0070
        L_0x006e:
            org.videolan.vlc.util.ContextOption r0 = org.videolan.vlc.util.ContextOption.CTX_FAV_ADD
        L_0x0070:
            java.lang.Enum r0 = (java.lang.Enum) r0
            r5.add(r0)
            goto L_0x00fd
        L_0x0077:
            org.videolan.vlc.util.ContextOption$Companion r5 = org.videolan.vlc.util.ContextOption.Companion
            org.videolan.vlc.util.FlagSet r5 = r5.createCtxAudioFlags()
            int r3 = r7.getTracksCount()
            if (r3 <= r2) goto L_0x008a
            org.videolan.vlc.util.ContextOption r2 = org.videolan.vlc.util.ContextOption.CTX_PLAY_SHUFFLE
            java.lang.Enum r2 = (java.lang.Enum) r2
            r5.add(r2)
        L_0x008a:
            boolean r2 = r7 instanceof org.videolan.medialibrary.interfaces.media.Genre
            if (r2 == 0) goto L_0x0091
            r1 = r7
            org.videolan.medialibrary.interfaces.media.Genre r1 = (org.videolan.medialibrary.interfaces.media.Genre) r1
        L_0x0091:
            if (r1 == 0) goto L_0x009c
            boolean r1 = r1.isFavorite()
            if (r1 != r0) goto L_0x009c
            org.videolan.vlc.util.ContextOption r0 = org.videolan.vlc.util.ContextOption.CTX_FAV_REMOVE
            goto L_0x009e
        L_0x009c:
            org.videolan.vlc.util.ContextOption r0 = org.videolan.vlc.util.ContextOption.CTX_FAV_ADD
        L_0x009e:
            java.lang.Enum r0 = (java.lang.Enum) r0
            r5.add(r0)
            goto L_0x00fd
        L_0x00a4:
            org.videolan.vlc.util.ContextOption$Companion r5 = org.videolan.vlc.util.ContextOption.Companion
            org.videolan.vlc.util.FlagSet r5 = r5.createCtxAudioFlags()
            int r3 = r7.getTracksCount()
            if (r3 <= r2) goto L_0x00b7
            org.videolan.vlc.util.ContextOption r2 = org.videolan.vlc.util.ContextOption.CTX_PLAY_SHUFFLE
            java.lang.Enum r2 = (java.lang.Enum) r2
            r5.add(r2)
        L_0x00b7:
            boolean r2 = r7 instanceof org.videolan.medialibrary.interfaces.media.Artist
            if (r2 == 0) goto L_0x00be
            r1 = r7
            org.videolan.medialibrary.interfaces.media.Artist r1 = (org.videolan.medialibrary.interfaces.media.Artist) r1
        L_0x00be:
            if (r1 == 0) goto L_0x00c9
            boolean r1 = r1.isFavorite()
            if (r1 != r0) goto L_0x00c9
            org.videolan.vlc.util.ContextOption r0 = org.videolan.vlc.util.ContextOption.CTX_FAV_REMOVE
            goto L_0x00cb
        L_0x00c9:
            org.videolan.vlc.util.ContextOption r0 = org.videolan.vlc.util.ContextOption.CTX_FAV_ADD
        L_0x00cb:
            java.lang.Enum r0 = (java.lang.Enum) r0
            r5.add(r0)
            goto L_0x00fd
        L_0x00d1:
            org.videolan.vlc.util.ContextOption$Companion r5 = org.videolan.vlc.util.ContextOption.Companion
            org.videolan.vlc.util.FlagSet r5 = r5.createCtxPlaylistAlbumFlags()
            int r3 = r7.getTracksCount()
            if (r3 <= r2) goto L_0x00e4
            org.videolan.vlc.util.ContextOption r2 = org.videolan.vlc.util.ContextOption.CTX_PLAY_SHUFFLE
            java.lang.Enum r2 = (java.lang.Enum) r2
            r5.add(r2)
        L_0x00e4:
            boolean r2 = r7 instanceof org.videolan.medialibrary.interfaces.media.Album
            if (r2 == 0) goto L_0x00eb
            r1 = r7
            org.videolan.medialibrary.interfaces.media.Album r1 = (org.videolan.medialibrary.interfaces.media.Album) r1
        L_0x00eb:
            if (r1 == 0) goto L_0x00f6
            boolean r1 = r1.isFavorite()
            if (r1 != r0) goto L_0x00f6
            org.videolan.vlc.util.ContextOption r0 = org.videolan.vlc.util.ContextOption.CTX_FAV_REMOVE
            goto L_0x00f8
        L_0x00f6:
            org.videolan.vlc.util.ContextOption r0 = org.videolan.vlc.util.ContextOption.CTX_FAV_ADD
        L_0x00f8:
            java.lang.Enum r0 = (java.lang.Enum) r0
            r5.add(r0)
        L_0x00fd:
            androidx.appcompat.view.ActionMode r0 = r4.getActionMode()
            if (r0 != 0) goto L_0x0112
            androidx.fragment.app.FragmentActivity r0 = r4.requireActivity()
            java.lang.String r1 = "requireActivity(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r1 = r4
            org.videolan.vlc.gui.dialogs.CtxActionReceiver r1 = (org.videolan.vlc.gui.dialogs.CtxActionReceiver) r1
            org.videolan.vlc.gui.dialogs.ContextSheetKt.showContext(r0, r1, r6, r7, r5)
        L_0x0112:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.BaseAudioBrowser.onCtxClick(android.view.View, int, org.videolan.medialibrary.media.MediaLibraryItem):void");
    }

    public void onMainActionClick(View view, int i, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        MediaWrapper[] tracks = mediaLibraryItem.getTracks();
        Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
        MediaUtils.openList$default(MediaUtils.INSTANCE, getActivity(), CollectionsKt.listOf(Arrays.copyOf(tracks, tracks.length)), 0, false, 8, (Object) null);
    }

    public void onUpdateFinished(RecyclerView.Adapter<?> adapter2) {
        Intrinsics.checkNotNullParameter(adapter2, "adapter");
        sortMenuTitles(getCurrentTab());
        if (Intrinsics.areEqual((Object) adapter2, (Object) getCurrentAdapter())) {
            restoreMultiSelectHelper();
        }
    }

    public void onCtxAction(int i, ContextOption contextOption) {
        AudioBrowserAdapter currentAdapter;
        MediaLibraryItem item;
        Intrinsics.checkNotNullParameter(contextOption, DuplicationWarningDialog.OPTION_KEY);
        AudioBrowserAdapter currentAdapter2 = getCurrentAdapter();
        if (i < (currentAdapter2 != null ? currentAdapter2.getItemCount() : 0) && (currentAdapter = getCurrentAdapter()) != null && (item = currentAdapter.getItem(i)) != null) {
            switch (WhenMappings.$EnumSwitchMapping$0[contextOption.ordinal()]) {
                case 1:
                    MediaUtils mediaUtils = MediaUtils.INSTANCE;
                    FragmentActivity requireActivity = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                    MediaUtils.playTracks$default(mediaUtils, (Context) requireActivity, item, 0, false, 8, (Object) null);
                    return;
                case 2:
                    MediaUtils mediaUtils2 = MediaUtils.INSTANCE;
                    FragmentActivity requireActivity2 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                    mediaUtils2.playTracks((Context) requireActivity2, item, new SecureRandom().nextInt(Math.min(item.getTracksCount(), 500)), true);
                    return;
                case 3:
                    Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), (CoroutineStart) null, new BaseAudioBrowser$onCtxAction$1(item, this, (Continuation<? super BaseAudioBrowser$onCtxAction$1>) null), 2, (Object) null);
                    return;
                case 4:
                    showInfoDialog(item);
                    return;
                case 5:
                    removeItem(item);
                    return;
                case 6:
                    MediaUtils mediaUtils3 = MediaUtils.INSTANCE;
                    FragmentActivity requireActivity3 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity(...)");
                    MediaWrapper[] tracks = item.getTracks();
                    Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
                    mediaUtils3.appendMedia((Context) requireActivity3, tracks);
                    return;
                case 7:
                    MediaUtils.INSTANCE.insertNext((Context) requireActivity(), item.getTracks());
                    return;
                case 8:
                    UiTools uiTools = UiTools.INSTANCE;
                    FragmentActivity requireActivity4 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity(...)");
                    MediaWrapper[] tracks2 = item.getTracks();
                    Intrinsics.checkNotNullExpressionValue(tracks2, "getTracks(...)");
                    uiTools.addToPlaylist(requireActivity4, tracks2, SavePlaylistDialog.KEY_NEW_TRACKS);
                    return;
                case 9:
                    FragmentActivity activity = getActivity();
                    if (activity != null) {
                        AudioUtil.INSTANCE.setRingtone(activity, (MediaWrapper) item);
                        return;
                    }
                    return;
                case 10:
                    Job unused2 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new BaseAudioBrowser$onCtxAction$2(this, item, (Continuation<? super BaseAudioBrowser$onCtxAction$2>) null), 3, (Object) null);
                    return;
                case 11:
                    KextensionsKt.showParentFolder(this, (MediaWrapper) item);
                    return;
                case 12:
                    Job unused3 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new BaseAudioBrowser$onCtxAction$3(this, item, (Continuation<? super BaseAudioBrowser$onCtxAction$3>) null), 3, (Object) null);
                    return;
                case 13:
                case 14:
                    Job unused4 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new BaseAudioBrowser$onCtxAction$4(item, contextOption, (Continuation<? super BaseAudioBrowser$onCtxAction$4>) null), 3, (Object) null);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r0 = getCurrentAdapter();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean getEmpty() {
        /*
            r1 = this;
            org.videolan.vlc.viewmodels.SortableModel r0 = r1.getViewModel()
            org.videolan.vlc.viewmodels.MedialibraryViewModel r0 = (org.videolan.vlc.viewmodels.MedialibraryViewModel) r0
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x001b
            org.videolan.vlc.gui.audio.AudioBrowserAdapter r0 = r1.getCurrentAdapter()
            if (r0 == 0) goto L_0x0019
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0019
            goto L_0x001b
        L_0x0019:
            r0 = 1
            goto L_0x001c
        L_0x001b:
            r0 = 0
        L_0x001c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.BaseAudioBrowser.getEmpty():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r2 = getCurrentAdapter();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean emptyAt(int r2) {
        /*
            r1 = this;
            org.videolan.vlc.viewmodels.SortableModel r0 = r1.getViewModel()
            org.videolan.vlc.viewmodels.MedialibraryViewModel r0 = (org.videolan.vlc.viewmodels.MedialibraryViewModel) r0
            boolean r2 = r0.isEmptyAt(r2)
            if (r2 == 0) goto L_0x001b
            org.videolan.vlc.gui.audio.AudioBrowserAdapter r2 = r1.getCurrentAdapter()
            if (r2 == 0) goto L_0x0019
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x0019
            goto L_0x001b
        L_0x0019:
            r2 = 1
            goto L_0x001c
        L_0x001b:
            r2 = 0
        L_0x001c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.BaseAudioBrowser.emptyAt(int):boolean");
    }

    public MultiSelectHelper<T> getMultiHelper() {
        AudioBrowserAdapter currentAdapter = getCurrentAdapter();
        MultiSelectHelper<MediaLibraryItem> multiSelectHelper = currentAdapter != null ? currentAdapter.getMultiSelectHelper() : null;
        if (multiSelectHelper instanceof MultiSelectHelper) {
            return multiSelectHelper;
        }
        return null;
    }
}
