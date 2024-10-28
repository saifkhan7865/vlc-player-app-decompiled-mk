package org.videolan.vlc.gui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.os.BundleKt;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigationrail.NavigationRailView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.CoroutineContextProvider;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.ExternalMonitor;
import org.videolan.vlc.MediaParsingService;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.audio.AudioPlayer;
import org.videolan.vlc.gui.audio.AudioPlaylistTipsDelegate;
import org.videolan.vlc.gui.audio.AudioTipsDelegate;
import org.videolan.vlc.gui.audio.EqualizerFragment;
import org.videolan.vlc.gui.helpers.BottomNavigationBehavior;
import org.videolan.vlc.gui.helpers.KeycodeListener;
import org.videolan.vlc.gui.helpers.PlayerBehavior;
import org.videolan.vlc.gui.helpers.PlayerKeyListenerDelegate;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.interfaces.IRefreshable;
import org.videolan.vlc.media.PlaylistManager;
import org.videolan.vlc.media.ResumeStatus;
import org.videolan.vlc.media.WaitConfirmation;
import org.videolan.vlc.util.LifecycleAwareScheduler;
import org.videolan.vlc.util.SchedulerCallback;
import org.videolan.vlc.viewmodels.PlaylistModel;

@Metadata(d1 = {"\u0000\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001e\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010u\u001a\u00020.2\u0006\u0010v\u001a\u00020\u0016H\u0002J\b\u0010w\u001a\u00020.H\u0016J\u0006\u0010x\u001a\u00020.J\b\u0010y\u001a\u00020.H\u0016J\u0006\u0010z\u001a\u00020.J\f\u0010{\u001a\b\u0012\u0002\b\u0003\u0018\u00010@J\u0012\u0010|\u001a\u0004\u0018\u00010&2\u0006\u0010}\u001a\u00020\u001cH\u0016J\u0006\u0010~\u001a\u00020.J\b\u0010\u001a\u00020.H\u0002J\u0012\u0010\u0001\u001a\u00020.2\u0007\u0010\u0001\u001a\u00020\u0016H\u0002J\t\u0010\u0001\u001a\u00020.H\u0016J\t\u0010\u0001\u001a\u00020.H\u0002J\t\u0010\u0001\u001a\u00020.H\u0014J\t\u0010\u0001\u001a\u00020\u001cH\u0016J\t\u0010\u0001\u001a\u00020\u001cH\u0016J\u0010\u0010\u0001\u001a\u00020.2\u0007\u0010\u0001\u001a\u00020\u001cJ\t\u0010\u0001\u001a\u00020.H\u0016J\u0012\u0010\u0001\u001a\u00020.2\t\u0010\u0001\u001a\u0004\u0018\u00010&J\u0012\u0010\u0001\u001a\u00020.2\t\u0010\u0001\u001a\u0004\u0018\u00010&J\u0012\u0010\u0001\u001a\u00020.2\t\u0010\u0001\u001a\u0004\u0018\u00010&J\u0012\u0010\u0001\u001a\u00020.2\t\u0010\u0001\u001a\u0004\u0018\u00010&J\u0015\u0010\u0001\u001a\u00020.2\n\u0010\u0001\u001a\u0005\u0018\u00010\u0001H\u0014J\t\u0010\u0001\u001a\u00020.H\u0014J\u001c\u0010\u0001\u001a\u00020\u001c2\u0007\u0010\u0001\u001a\u00020\u00162\b\u0010\u0001\u001a\u00030\u0001H\u0016J\u0013\u0010\u0001\u001a\u00020\u001c2\b\u0010\u0001\u001a\u00030\u0001H\u0016J\t\u0010\u0001\u001a\u00020.H\u0014J\u001b\u0010\u0001\u001a\u00020.2\u0007\u0010\u0001\u001a\u00020&2\u0007\u0010\u0001\u001a\u00020\u0016H\u0014J\t\u0010\u0001\u001a\u00020.H\u0014J\t\u0010\u0001\u001a\u00020.H\u0014J\u0013\u0010\u0001\u001a\u00020.2\b\u0010 \u0001\u001a\u00030\u0001H\u0014J\u0015\u0010¡\u0001\u001a\u00020.2\n\u0010¢\u0001\u001a\u0005\u0018\u00010£\u0001H\u0016J\t\u0010¤\u0001\u001a\u00020.H\u0014J\t\u0010¥\u0001\u001a\u00020.H\u0014J\u001d\u0010¦\u0001\u001a\u00020.2\b\u0010§\u0001\u001a\u00030¨\u00012\b\u0010©\u0001\u001a\u00030\u0001H\u0016J\t\u0010ª\u0001\u001a\u00020.H\u0016J\n\u0010«\u0001\u001a\u00030¬\u0001H\u0007J\t\u0010­\u0001\u001a\u00020.H\u0002J\u0013\u0010®\u0001\u001a\u00020.2\b\u0010¢\u0001\u001a\u00030£\u0001H\u0002J\u0007\u0010¯\u0001\u001a\u00020.J\t\u0010°\u0001\u001a\u00020.H\u0016J\u0012\u0010±\u0001\u001a\u00020.2\u0007\u0010²\u0001\u001a\u00020\u0016H\u0016J\t\u0010³\u0001\u001a\u00020.H\u0002J\u0010\u0010´\u0001\u001a\u00020.2\u0007\u0010µ\u0001\u001a\u00020\u001cJ\t\u0010¶\u0001\u001a\u00020.H\u0016J\t\u0010·\u0001\u001a\u00020.H\u0002J\t\u0010¸\u0001\u001a\u00020.H\u0002J\u0012\u0010¹\u0001\u001a\u00020.2\u0007\u0010º\u0001\u001a\u00020;H\u0002J\t\u0010»\u0001\u001a\u00020.H\u0016J\u0013\u0010¼\u0001\u001a\u00020.2\b\u0010½\u0001\u001a\u00030¨\u0001H\u0002J\u001c\u0010¾\u0001\u001a\u00020.2\u0007\u0010¿\u0001\u001a\u00020\u00162\b\u0010À\u0001\u001a\u00030¨\u0001H\u0007J\u0007\u0010Á\u0001\u001a\u00020\u001cJ\u0007\u0010Â\u0001\u001a\u00020.J\t\u0010Ã\u0001\u001a\u00020.H\u0016J\t\u0010Ä\u0001\u001a\u00020.H\u0016J\u0013\u0010Å\u0001\u001a\u00020.2\b\u0010¢\u0001\u001a\u00030£\u0001H\u0002J\u0012\u0010Æ\u0001\u001a\u00020.2\t\b\u0002\u0010Ç\u0001\u001a\u00020\u0016J\u0007\u0010È\u0001\u001a\u00020.J \u0010É\u0001\u001a\u00020.2\u0007\u0010µ\u0001\u001a\u00020\u001c2\f\b\u0002\u0010½\u0001\u001a\u0005\u0018\u00010¨\u0001H\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u0004\u0018\u00010\"8DX\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X.¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R \u0010+\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020.0,X\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u00101\u001a\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b1\u0010\u001eR\u0011\u00102\u001a\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b2\u0010\u001eR\u0011\u00103\u001a\u0002048F¢\u0006\u0006\u001a\u0004\b5\u00106R\u0010\u00107\u001a\u0004\u0018\u000108X\u000e¢\u0006\u0002\n\u0000R\u0018\u00109\u001a\f\u0012\b\b\u0000\u0012\u0004\u0018\u00010;0:X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010<\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0018\"\u0004\b>\u0010\u001aR\u001e\u0010?\u001a\u0006\u0012\u0002\b\u00030@X.¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001b\u0010E\u001a\u00020F8BX\u0002¢\u0006\f\n\u0004\bI\u0010J\u001a\u0004\bG\u0010HR\u000e\u0010K\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u001b\u0010L\u001a\u00020M8FX\u0002¢\u0006\f\n\u0004\bP\u0010J\u001a\u0004\bN\u0010OR\u000e\u0010Q\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010R\u001a\u00020\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\u001e\"\u0004\bT\u0010 R\u000e\u0010U\u001a\u00020VX.¢\u0006\u0002\n\u0000R\u0010\u0010W\u001a\u0004\u0018\u00010XX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010Y\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010Z\u001a\u0004\u0018\u00010[X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\\\u001a\u00020]X.¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR!\u0010b\u001a\u0012\u0012\u0004\u0012\u00020\u00160cj\b\u0012\u0004\u0012\u00020\u0016`d¢\u0006\b\n\u0000\u001a\u0004\be\u0010fR\u0010\u0010g\u001a\u0004\u0018\u00010hX\u000e¢\u0006\u0002\n\u0000R\u001b\u0010i\u001a\u00020j8FX\u0002¢\u0006\f\n\u0004\bm\u0010J\u001a\u0004\bk\u0010lR\u001a\u0010n\u001a\u00020oX.¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010q\"\u0004\br\u0010sR\u000e\u0010t\u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000¨\u0006Ê\u0001"}, d2 = {"Lorg/videolan/vlc/gui/AudioPlayerContainerActivity;", "Lorg/videolan/vlc/gui/BaseActivity;", "Lorg/videolan/vlc/gui/helpers/KeycodeListener;", "Lorg/videolan/vlc/util/SchedulerCallback;", "()V", "appBarLayout", "Lcom/google/android/material/appbar/AppBarLayout;", "getAppBarLayout", "()Lcom/google/android/material/appbar/AppBarLayout;", "setAppBarLayout", "(Lcom/google/android/material/appbar/AppBarLayout;)V", "audioPlayer", "Lorg/videolan/vlc/gui/audio/AudioPlayer;", "getAudioPlayer", "()Lorg/videolan/vlc/gui/audio/AudioPlayer;", "setAudioPlayer", "(Lorg/videolan/vlc/gui/audio/AudioPlayer;)V", "audioPlayerContainer", "Landroid/widget/FrameLayout;", "bottomBar", "Lcom/google/android/material/bottomnavigation/BottomNavigationView;", "bottomInset", "", "getBottomInset", "()I", "setBottomInset", "(I)V", "bottomIsHiddden", "", "getBottomIsHiddden", "()Z", "setBottomIsHiddden", "(Z)V", "currentFragment", "Landroidx/fragment/app/Fragment;", "getCurrentFragment", "()Landroidx/fragment/app/Fragment;", "fragmentContainer", "Landroid/view/View;", "getFragmentContainer", "()Landroid/view/View;", "setFragmentContainer", "(Landroid/view/View;)V", "insetListener", "Lkotlin/Function1;", "Landroidx/core/graphics/Insets;", "", "getInsetListener", "()Lkotlin/jvm/functions/Function1;", "isAudioPlayerExpanded", "isAudioPlayerReady", "menu", "Landroid/view/Menu;", "getMenu", "()Landroid/view/Menu;", "navigationRail", "Lcom/google/android/material/navigationrail/NavigationRailView;", "observer", "Landroidx/lifecycle/Observer;", "Lorg/videolan/vlc/media/WaitConfirmation;", "originalBottomPadding", "getOriginalBottomPadding", "setOriginalBottomPadding", "playerBehavior", "Lorg/videolan/vlc/gui/helpers/PlayerBehavior;", "getPlayerBehavior", "()Lorg/videolan/vlc/gui/helpers/PlayerBehavior;", "setPlayerBehavior", "(Lorg/videolan/vlc/gui/helpers/PlayerBehavior;)V", "playerKeyListenerDelegate", "Lorg/videolan/vlc/gui/helpers/PlayerKeyListenerDelegate;", "getPlayerKeyListenerDelegate", "()Lorg/videolan/vlc/gui/helpers/PlayerKeyListenerDelegate;", "playerKeyListenerDelegate$delegate", "Lkotlin/Lazy;", "playerShown", "playlistTipsDelegate", "Lorg/videolan/vlc/gui/audio/AudioPlaylistTipsDelegate;", "getPlaylistTipsDelegate", "()Lorg/videolan/vlc/gui/audio/AudioPlaylistTipsDelegate;", "playlistTipsDelegate$delegate", "preventRescan", "restoreBookmarks", "getRestoreBookmarks", "setRestoreBookmarks", "resumeCard", "Lcom/google/android/material/snackbar/Snackbar;", "scanProgressBar", "Landroid/widget/ProgressBar;", "scanProgressLayout", "scanProgressText", "Landroid/widget/TextView;", "scheduler", "Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "getScheduler", "()Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "setScheduler", "(Lorg/videolan/vlc/util/LifecycleAwareScheduler;)V", "shownTips", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getShownTips", "()Ljava/util/ArrayList;", "tabLayout", "Lcom/google/android/material/tabs/TabLayout;", "tipsDelegate", "Lorg/videolan/vlc/gui/audio/AudioTipsDelegate;", "getTipsDelegate", "()Lorg/videolan/vlc/gui/audio/AudioTipsDelegate;", "tipsDelegate$delegate", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "getToolbar", "()Landroidx/appcompat/widget/Toolbar;", "setToolbar", "(Landroidx/appcompat/widget/Toolbar;)V", "topInset", "applyMarginToProgressBar", "marginValue", "bookmark", "closeMiniPlayer", "decreaseRate", "expandAppBar", "getBehavior", "getSnackAnchorView", "overAudioPlayer", "hideAudioPlayer", "hideAudioPlayerImpl", "hideStatusIfNeeded", "newState", "increaseRate", "initAudioPlayer", "initAudioPlayerContainerActivity", "isReady", "isTransparent", "lockPlayer", "lock", "next", "onClickDismissPlaylistTips", "v", "onClickDismissTips", "onClickNextPlaylistTips", "onClickNextTips", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onKeyDown", "keyCode", "event", "Landroid/view/KeyEvent;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPause", "onPlayerStateChanged", "bottomSheet", "onRestart", "onResume", "onSaveInstanceState", "outState", "onServiceChanged", "service", "Lorg/videolan/vlc/PlaybackService;", "onStart", "onStop", "onTaskTriggered", "id", "", "data", "previous", "proposeCard", "Lkotlinx/coroutines/Job;", "registerLiveData", "registerObserver", "removeTipViewIfDisplayed", "resetRate", "seek", "delta", "setContentBottomPadding", "setTabLayoutVisibility", "show", "showAdvancedOptions", "showAudioPlayer", "showAudioPlayerImpl", "showConfirmResumeDialog", "confirmation", "showEqualizer", "showProgressBar", "discovery", "showTipViewIfNeeded", "stubId", "settingKey", "slideDownAudioPlayer", "slideUpOrDownAudioPlayer", "stop", "togglePlayPause", "unregisterObserver", "updateFragmentMargins", "state", "updateLib", "updateProgressVisibility", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayerContainerActivity.kt */
public class AudioPlayerContainerActivity extends BaseActivity implements KeycodeListener, SchedulerCallback {
    public AppBarLayout appBarLayout;
    public AudioPlayer audioPlayer;
    /* access modifiers changed from: private */
    public FrameLayout audioPlayerContainer;
    /* access modifiers changed from: private */
    public BottomNavigationView bottomBar;
    private int bottomInset;
    private boolean bottomIsHiddden;
    protected View fragmentContainer;
    private final Function1<Insets, Unit> insetListener = AudioPlayerContainerActivity$insetListener$1.INSTANCE;
    private NavigationRailView navigationRail;
    private Observer<? super WaitConfirmation> observer = new AudioPlayerContainerActivity$$ExternalSyntheticLambda7(this);
    private int originalBottomPadding;
    public PlayerBehavior<?> playerBehavior;
    private final Lazy playerKeyListenerDelegate$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new AudioPlayerContainerActivity$playerKeyListenerDelegate$2(this));
    private boolean playerShown;
    private final Lazy playlistTipsDelegate$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new AudioPlayerContainerActivity$playlistTipsDelegate$2(this));
    private boolean preventRescan;
    private boolean restoreBookmarks;
    /* access modifiers changed from: private */
    public Snackbar resumeCard;
    /* access modifiers changed from: private */
    public ProgressBar scanProgressBar;
    private View scanProgressLayout;
    /* access modifiers changed from: private */
    public TextView scanProgressText;
    public LifecycleAwareScheduler scheduler;
    private final ArrayList<Integer> shownTips = new ArrayList<>();
    private TabLayout tabLayout;
    private final Lazy tipsDelegate$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new AudioPlayerContainerActivity$tipsDelegate$2(this));
    protected Toolbar toolbar;
    /* access modifiers changed from: private */
    public int topInset;

    public boolean isTransparent() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onPlayerStateChanged(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "bottomSheet");
    }

    public void onTaskCancelled(String str) {
        SchedulerCallback.DefaultImpls.onTaskCancelled(this, str);
    }

    public final AppBarLayout getAppBarLayout() {
        AppBarLayout appBarLayout2 = this.appBarLayout;
        if (appBarLayout2 != null) {
            return appBarLayout2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("appBarLayout");
        return null;
    }

    public final void setAppBarLayout(AppBarLayout appBarLayout2) {
        Intrinsics.checkNotNullParameter(appBarLayout2, "<set-?>");
        this.appBarLayout = appBarLayout2;
    }

    /* access modifiers changed from: protected */
    public final Toolbar getToolbar() {
        Toolbar toolbar2 = this.toolbar;
        if (toolbar2 != null) {
            return toolbar2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("toolbar");
        return null;
    }

    /* access modifiers changed from: protected */
    public final void setToolbar(Toolbar toolbar2) {
        Intrinsics.checkNotNullParameter(toolbar2, "<set-?>");
        this.toolbar = toolbar2;
    }

    public final AudioPlayer getAudioPlayer() {
        AudioPlayer audioPlayer2 = this.audioPlayer;
        if (audioPlayer2 != null) {
            return audioPlayer2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("audioPlayer");
        return null;
    }

    public final void setAudioPlayer(AudioPlayer audioPlayer2) {
        Intrinsics.checkNotNullParameter(audioPlayer2, "<set-?>");
        this.audioPlayer = audioPlayer2;
    }

    public final PlayerBehavior<?> getPlayerBehavior() {
        PlayerBehavior<?> playerBehavior2 = this.playerBehavior;
        if (playerBehavior2 != null) {
            return playerBehavior2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playerBehavior");
        return null;
    }

    public final void setPlayerBehavior(PlayerBehavior<?> playerBehavior2) {
        Intrinsics.checkNotNullParameter(playerBehavior2, "<set-?>");
        this.playerBehavior = playerBehavior2;
    }

    /* access modifiers changed from: protected */
    public final View getFragmentContainer() {
        View view = this.fragmentContainer;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fragmentContainer");
        return null;
    }

    /* access modifiers changed from: protected */
    public final void setFragmentContainer(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.fragmentContainer = view;
    }

    /* access modifiers changed from: protected */
    public final int getOriginalBottomPadding() {
        return this.originalBottomPadding;
    }

    /* access modifiers changed from: protected */
    public final void setOriginalBottomPadding(int i) {
        this.originalBottomPadding = i;
    }

    public final AudioTipsDelegate getTipsDelegate() {
        return (AudioTipsDelegate) this.tipsDelegate$delegate.getValue();
    }

    public final AudioPlaylistTipsDelegate getPlaylistTipsDelegate() {
        return (AudioPlaylistTipsDelegate) this.playlistTipsDelegate$delegate.getValue();
    }

    private final PlayerKeyListenerDelegate getPlayerKeyListenerDelegate() {
        return (PlayerKeyListenerDelegate) this.playerKeyListenerDelegate$delegate.getValue();
    }

    public final ArrayList<Integer> getShownTips() {
        return this.shownTips;
    }

    /* access modifiers changed from: protected */
    public final Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.fragment_placeholder);
    }

    public final Menu getMenu() {
        Menu menu = getToolbar().getMenu();
        Intrinsics.checkNotNullExpressionValue(menu, "getMenu(...)");
        return menu;
    }

    /* access modifiers changed from: private */
    public static final void observer$lambda$1(AudioPlayerContainerActivity audioPlayerContainerActivity, WaitConfirmation waitConfirmation) {
        Intrinsics.checkNotNullParameter(audioPlayerContainerActivity, "this$0");
        if (waitConfirmation != null && !waitConfirmation.getUsed()) {
            waitConfirmation.setUsed(true);
            audioPlayerContainerActivity.showConfirmResumeDialog(waitConfirmation);
        }
    }

    public Function1<Insets, Unit> getInsetListener() {
        return this.insetListener;
    }

    public final int getBottomInset() {
        return this.bottomInset;
    }

    public final void setBottomInset(int i) {
        this.bottomInset = i;
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

    public final boolean isAudioPlayerReady() {
        return this.audioPlayer != null;
    }

    public final boolean isAudioPlayerExpanded() {
        return isAudioPlayerReady() && getPlayerBehavior().getState() == 3;
    }

    public final boolean getBottomIsHiddden() {
        return this.bottomIsHiddden;
    }

    public final void setBottomIsHiddden(boolean z) {
        this.bottomIsHiddden = z;
    }

    public final boolean getRestoreBookmarks() {
        return this.restoreBookmarks;
    }

    public final void setRestoreBookmarks(boolean z) {
        this.restoreBookmarks = z;
    }

    public View getSnackAnchorView(boolean z) {
        FrameLayout frameLayout = this.audioPlayerContainer;
        if (frameLayout != null) {
            FrameLayout frameLayout2 = null;
            if (frameLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioPlayerContainer");
                frameLayout = null;
            }
            if (!(frameLayout.getVisibility() == 8 || this.playerBehavior == null || getPlayerBehavior().getState() != 4)) {
                FrameLayout frameLayout3 = this.audioPlayerContainer;
                if (frameLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("audioPlayerContainer");
                } else {
                    frameLayout2 = frameLayout3;
                }
                return frameLayout2;
            }
        }
        return ((this.playerBehavior == null || getPlayerBehavior().getState() != 3) && this.playerBehavior != null) ? findViewById(R.id.coordinator) : findViewById(16908290);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        setScheduler(new LifecycleAwareScheduler(this));
        if (bundle != null) {
            ExtensionsKt.startMedialibrary$default(this, false, false, true, false, (CoroutineContextProvider) null, 24, (Object) null);
            this.bottomIsHiddden = bundle.getBoolean("bottom_is_hidden", false) && !bundle.getBoolean("player_opened", false);
            ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList("shown_tips");
            if (integerArrayList != null) {
                this.shownTips.addAll(integerArrayList);
            }
            this.restoreBookmarks = bundle.getBoolean("bookmark_visible", false);
        }
        super.onCreate(bundle);
        if (AndroidUtil.isLolliPopOrLater && (this instanceof MainActivity)) {
            WindowCompat.setDecorFitsSystemWindows(((MainActivity) this).getWindow(), false);
        }
        setVolumeControlStream(3);
        registerLiveData();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(16908290), new AudioPlayerContainerActivity$$ExternalSyntheticLambda1(this));
        getOnBackPressedDispatcher().addCallback(this, new AudioPlayerContainerActivity$onCreate$3(this));
    }

    /* access modifiers changed from: private */
    public static final WindowInsetsCompat onCreate$lambda$4(AudioPlayerContainerActivity audioPlayerContainerActivity, View view, WindowInsetsCompat windowInsetsCompat) {
        Intrinsics.checkNotNullParameter(audioPlayerContainerActivity, "this$0");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(windowInsetsCompat, "windowInsets");
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            ViewGroup.LayoutParams layoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams;
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams2;
            if (audioPlayerContainerActivity.isTransparent()) {
                ViewGroup.LayoutParams layoutParams3 = ((Toolbar) audioPlayerContainerActivity.findViewById(R.id.main_toolbar)).getLayoutParams();
                Intrinsics.checkNotNull(layoutParams3, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
                ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin = insets.top;
            }
            if (audioPlayerContainerActivity instanceof MainActivity) {
                marginLayoutParams.leftMargin = insets.left;
                marginLayoutParams.rightMargin = insets.right;
                if (UiTools.INSTANCE.isTablet(audioPlayerContainerActivity)) {
                    marginLayoutParams.bottomMargin = insets.bottom;
                }
                marginLayoutParams.topMargin = insets.top;
                audioPlayerContainerActivity.topInset = insets.top;
                BottomNavigationView bottomNavigationView = (BottomNavigationView) audioPlayerContainerActivity.findViewById(R.id.navigation);
                if (bottomNavigationView != null) {
                    bottomNavigationView.setPadding(bottomNavigationView.getPaddingLeft(), bottomNavigationView.getPaddingTop(), bottomNavigationView.getPaddingRight(), insets.bottom);
                }
                audioPlayerContainerActivity.bottomInset = insets.bottom;
                audioPlayerContainerActivity.getInsetListener().invoke(insets);
                if (audioPlayerContainerActivity.audioPlayer != null) {
                    audioPlayerContainerActivity.getAudioPlayer().setBottomMargin();
                }
            }
            audioPlayerContainerActivity.setContentBottomPadding();
            view.setLayoutParams(layoutParams2);
            return WindowInsetsCompat.CONSUMED;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
    }

    /* access modifiers changed from: private */
    public final void setContentBottomPadding() {
        boolean z = this instanceof MainActivity;
        int i = 0;
        int i2 = (!z || !UiTools.INSTANCE.isTablet(this)) ? this.bottomInset : 0;
        if (z && !UiTools.INSTANCE.isTablet(this)) {
            i = KotlinExtensionsKt.getDp(58);
        }
        getFragmentContainer().setPadding(getFragmentContainer().getPaddingLeft(), getFragmentContainer().getPaddingTop(), getFragmentContainer().getPaddingRight(), i2 + i + KotlinExtensionsKt.getDp((this.playerBehavior == null || getPlayerBehavior().getState() == 5) ? 4 : 72));
    }

    public final PlayerBehavior<?> getBehavior() {
        if (this.playerBehavior != null) {
            return getPlayerBehavior();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void initAudioPlayerContainerActivity() {
        ViewTreeObserver viewTreeObserver;
        View findViewById = findViewById(R.id.fragment_placeholder);
        if (findViewById != null) {
            setFragmentContainer(findViewById);
            this.originalBottomPadding = getFragmentContainer().getPaddingBottom();
        }
        View findViewById2 = findViewById(R.id.main_toolbar);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        setToolbar((Toolbar) findViewById2);
        setSupportActionBar(getToolbar());
        View findViewById3 = findViewById(R.id.appbar);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        setAppBarLayout((AppBarLayout) findViewById3);
        this.tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        this.navigationRail = (NavigationRailView) findViewById(R.id.navigation_rail);
        TabLayout tabLayout2 = this.tabLayout;
        if (tabLayout2 != null) {
            tabLayout2.setVisibility(0);
        }
        getAppBarLayout().setExpanded(true);
        this.bottomBar = (BottomNavigationView) findViewById(R.id.navigation);
        TabLayout tabLayout3 = this.tabLayout;
        if (!(tabLayout3 == null || (viewTreeObserver = tabLayout3.getViewTreeObserver()) == null)) {
            viewTreeObserver.addOnGlobalLayoutListener(new AudioPlayerContainerActivity$$ExternalSyntheticLambda6(this));
        }
        View findViewById4 = findViewById(R.id.audio_player_container);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        FrameLayout frameLayout = (FrameLayout) findViewById4;
        this.audioPlayerContainer = frameLayout;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioPlayerContainer");
            frameLayout = null;
        }
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
        ((CoordinatorLayout.LayoutParams) layoutParams).bottomMargin = this.bottomInset;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r0 = r0.getLayoutParams();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0016, code lost:
        r0 = r4.navigationRail;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void initAudioPlayerContainerActivity$lambda$6(org.videolan.vlc.gui.AudioPlayerContainerActivity r4) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            com.google.android.material.tabs.TabLayout r0 = r4.tabLayout
            r1 = 0
            r2 = 8
            if (r0 == 0) goto L_0x0023
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            if (r0 == 0) goto L_0x0023
            int r0 = r0.height
            if (r0 != 0) goto L_0x0023
            com.google.android.material.navigationrail.NavigationRailView r0 = r4.navigationRail
            if (r0 == 0) goto L_0x0021
            int r0 = r0.getVisibility()
            if (r0 == r2) goto L_0x0021
            goto L_0x0023
        L_0x0021:
            r0 = 0
            goto L_0x0024
        L_0x0023:
            r0 = 1
        L_0x0024:
            boolean r3 = org.videolan.libvlc.util.AndroidUtil.isLolliPopOrLater
            if (r3 == 0) goto L_0x003b
            com.google.android.material.appbar.AppBarLayout r4 = r4.getAppBarLayout()
            if (r0 == 0) goto L_0x0033
            int r0 = org.videolan.tools.KotlinExtensionsKt.getDp(r2)
            goto L_0x0037
        L_0x0033:
            int r0 = org.videolan.tools.KotlinExtensionsKt.getDp(r1)
        L_0x0037:
            float r0 = (float) r0
            r4.setElevation(r0)
        L_0x003b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.AudioPlayerContainerActivity.initAudioPlayerContainerActivity$lambda$6(org.videolan.vlc.gui.AudioPlayerContainerActivity):void");
    }

    public final void setTabLayoutVisibility(boolean z) {
        TabLayout tabLayout2 = this.tabLayout;
        ViewGroup.LayoutParams layoutParams = tabLayout2 != null ? tabLayout2.getLayoutParams() : null;
        if (layoutParams != null) {
            layoutParams.height = z ? -2 : 0;
        }
        TabLayout tabLayout3 = this.tabLayout;
        if (tabLayout3 != null) {
            tabLayout3.requestLayout();
        }
    }

    private final void showConfirmResumeDialog(WaitConfirmation waitConfirmation) {
        PlaybackService instance = PlaybackService.Companion.getInstance();
        if (instance != null) {
            instance.pause();
        }
        LayoutInflater layoutInflater = getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "getLayoutInflater(...)");
        View inflate = layoutInflater.inflate(R.layout.dialog_video_resume, (ViewGroup) null);
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.video_resume_checkbox);
        AlertDialog create = new AlertDialog.Builder(this).setTitle((CharSequence) waitConfirmation.getTitle()).setView(inflate).setCancelable(true).setPositiveButton(R.string.resume, (DialogInterface.OnClickListener) new AudioPlayerContainerActivity$$ExternalSyntheticLambda2(checkBox, this, waitConfirmation)).setNegativeButton(R.string.no, (DialogInterface.OnClickListener) new AudioPlayerContainerActivity$$ExternalSyntheticLambda3(checkBox, this, waitConfirmation)).create();
        create.setCancelable(true);
        create.setOnCancelListener(new AudioPlayerContainerActivity$$ExternalSyntheticLambda4());
        create.setOnKeyListener(new AudioPlayerContainerActivity$$ExternalSyntheticLambda5(this));
        create.show();
    }

    /* access modifiers changed from: private */
    public static final void showConfirmResumeDialog$lambda$7(CheckBox checkBox, AudioPlayerContainerActivity audioPlayerContainerActivity, WaitConfirmation waitConfirmation, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(audioPlayerContainerActivity, "this$0");
        Intrinsics.checkNotNullParameter(waitConfirmation, "$confirmation");
        if (checkBox.isChecked()) {
            PlaybackService instance = PlaybackService.Companion.getInstance();
            PlaylistManager playlistManager = instance != null ? instance.getPlaylistManager() : null;
            if (playlistManager != null) {
                playlistManager.setAudioResumeStatus(ResumeStatus.ALWAYS);
            }
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(audioPlayerContainerActivity), (CoroutineContext) null, (CoroutineStart) null, new AudioPlayerContainerActivity$showConfirmResumeDialog$1$1(waitConfirmation, (Continuation<? super AudioPlayerContainerActivity$showConfirmResumeDialog$1$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void showConfirmResumeDialog$lambda$8(CheckBox checkBox, AudioPlayerContainerActivity audioPlayerContainerActivity, WaitConfirmation waitConfirmation, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(audioPlayerContainerActivity, "this$0");
        Intrinsics.checkNotNullParameter(waitConfirmation, "$confirmation");
        if (checkBox.isChecked()) {
            PlaybackService instance = PlaybackService.Companion.getInstance();
            PlaylistManager playlistManager = instance != null ? instance.getPlaylistManager() : null;
            if (playlistManager != null) {
                playlistManager.setAudioResumeStatus(ResumeStatus.NEVER);
            }
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(audioPlayerContainerActivity), (CoroutineContext) null, (CoroutineStart) null, new AudioPlayerContainerActivity$showConfirmResumeDialog$2$1(waitConfirmation, (Continuation<? super AudioPlayerContainerActivity$showConfirmResumeDialog$2$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void showConfirmResumeDialog$lambda$11$lambda$9(DialogInterface dialogInterface) {
        PlaylistManager playlistManager;
        PlaybackService instance = PlaybackService.Companion.getInstance();
        if (instance != null && (playlistManager = instance.getPlaylistManager()) != null) {
            PlaylistManager.stop$default(playlistManager, false, false, 3, (Object) null);
        }
    }

    /* access modifiers changed from: private */
    public static final boolean showConfirmResumeDialog$lambda$11$lambda$10(AudioPlayerContainerActivity audioPlayerContainerActivity, DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(audioPlayerContainerActivity, "this$0");
        if (i != 4) {
            return false;
        }
        dialogInterface.dismiss();
        audioPlayerContainerActivity.finish();
        return true;
    }

    private final void initAudioPlayer() {
        BottomNavigationBehavior bottomNavigationBehavior;
        findViewById(R.id.audio_player_stub).setVisibility(0);
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.audio_player);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type org.videolan.vlc.gui.audio.AudioPlayer");
        setAudioPlayer((AudioPlayer) findFragmentById);
        FrameLayout frameLayout = this.audioPlayerContainer;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioPlayerContainer");
            frameLayout = null;
        }
        BottomSheetBehavior from = BottomSheetBehavior.from(frameLayout);
        Intrinsics.checkNotNull(from, "null cannot be cast to non-null type org.videolan.vlc.gui.helpers.PlayerBehavior<*>");
        setPlayerBehavior((PlayerBehavior) from);
        BottomNavigationView bottomNavigationView = this.bottomBar;
        if (bottomNavigationView != null) {
            bottomNavigationBehavior = BottomNavigationBehavior.Companion.from(bottomNavigationView);
            Intrinsics.checkNotNull(bottomNavigationBehavior, "null cannot be cast to non-null type org.videolan.vlc.gui.helpers.BottomNavigationBehavior<android.view.View>");
        } else {
            bottomNavigationBehavior = null;
        }
        if (!this.bottomIsHiddden) {
            hideStatusIfNeeded(getPlayerBehavior().getState());
        } else if (bottomNavigationBehavior != null) {
            bottomNavigationBehavior.setCollapsed();
        }
        getPlayerBehavior().setPeekHeight(getResources().getDimensionPixelSize(R.dimen.player_peek_height));
        updateFragmentMargins$default(this, 0, 1, (Object) null);
        getPlayerBehavior().setPeekHeightListener(new AudioPlayerContainerActivity$initAudioPlayer$1(this));
        getPlayerBehavior().setLayoutListener(new AudioPlayerContainerActivity$initAudioPlayer$2(this));
        getPlayerBehavior().addBottomSheetCallback(new AudioPlayerContainerActivity$initAudioPlayer$3(this, bottomNavigationBehavior));
        showTipViewIfNeeded(R.id.audio_player_tips, SettingsKt.PREF_AUDIOPLAYER_TIPS_SHOWN);
        if (getPlaylistTipsDelegate().getCurrentTip() != null) {
            lockPlayer(true);
        }
        if (this.restoreBookmarks) {
            getAppBarLayout().post(new AudioPlayerContainerActivity$$ExternalSyntheticLambda0(this));
        }
    }

    /* access modifiers changed from: private */
    public static final void initAudioPlayer$lambda$13(AudioPlayerContainerActivity audioPlayerContainerActivity) {
        Intrinsics.checkNotNullParameter(audioPlayerContainerActivity, "this$0");
        audioPlayerContainerActivity.getAudioPlayer().showBookmarks();
        audioPlayerContainerActivity.restoreBookmarks = false;
    }

    /* access modifiers changed from: private */
    public final void hideStatusIfNeeded(int i) {
        if (isTransparent()) {
            WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
            windowInsetsControllerCompat.setSystemBarsBehavior(i == 3 ? 2 : 0);
            if (i == 3) {
                windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.statusBars());
            } else {
                windowInsetsControllerCompat.show(WindowInsetsCompat.Type.statusBars());
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, NotificationCompat.CATEGORY_EVENT);
        if (getPlayerKeyListenerDelegate().onKeyDown(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean isReady() {
        return this.audioPlayer != null;
    }

    public void showAdvancedOptions() {
        getAudioPlayer().showAdvancedOptions((View) null);
    }

    public void togglePlayPause() {
        getAudioPlayer().onPlayPauseClick((View) null);
    }

    public void next() {
        getAudioPlayer().onNextClick((View) null);
    }

    public void previous() {
        getAudioPlayer().onPreviousClick((View) null);
    }

    public void stop() {
        getAudioPlayer().onStopClick((View) null);
    }

    public void seek(int i) {
        int time = ((int) getAudioPlayer().getPlaylistModel().getTime()) + i;
        if (time >= 0) {
            long j = (long) time;
            if (j <= getAudioPlayer().getPlaylistModel().getLength()) {
                PlaylistModel.setTime$default(getAudioPlayer().getPlaylistModel(), j, false, 2, (Object) null);
            }
        }
    }

    public void showEqualizer() {
        new EqualizerFragment().show(getSupportFragmentManager(), "equalizer");
    }

    public void increaseRate() {
        PlaybackService service = getAudioPlayer().getPlaylistModel().getService();
        if (service != null) {
            service.increaseRate();
        }
    }

    public void decreaseRate() {
        PlaybackService service = getAudioPlayer().getPlaylistModel().getService();
        if (service != null) {
            service.decreaseRate();
        }
    }

    public void resetRate() {
        PlaybackService service = getAudioPlayer().getPlaylistModel().getService();
        if (service != null) {
            service.resetRate();
        }
    }

    public void bookmark() {
        getAudioPlayer().getBookmarkModel().addBookmark(this);
        String string = getString(R.string.bookmark_added);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UiTools.INSTANCE.snackerConfirm(this, string, true, R.string.show, new AudioPlayerContainerActivity$bookmark$1(this));
    }

    public static /* synthetic */ void updateFragmentMargins$default(AudioPlayerContainerActivity audioPlayerContainerActivity, int i, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                i = 4;
            }
            audioPlayerContainerActivity.updateFragmentMargins(i);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateFragmentMargins");
    }

    public final void updateFragmentMargins(int i) {
        this.playerShown = i != 5;
        setContentBottomPadding();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        boolean z;
        Intrinsics.checkNotNullParameter(bundle, "outState");
        BottomNavigationView bottomNavigationView = this.bottomBar;
        boolean z2 = true;
        if (bottomNavigationView != null) {
            z = !(bottomNavigationView.getTranslationY() == 0.0f);
        } else {
            z = false;
        }
        bundle.putBoolean("bottom_is_hidden", z);
        if (this.playerBehavior == null || getPlayerBehavior().getState() != 3) {
            z2 = false;
        }
        bundle.putBoolean("player_opened", z2);
        bundle.putIntegerArrayList("shown_tips", this.shownTips);
        if (this.audioPlayer != null) {
            bundle.putBoolean("bookmark_visible", getAudioPlayer().areBookmarksVisible());
        }
        super.onSaveInstanceState(bundle);
    }

    public final void expandAppBar() {
        getAppBarLayout().setExpanded(true);
    }

    private final void unregisterObserver(PlaybackService playbackService) {
        if (playbackService.getPlaylistManager().getWaitForConfirmationAudio().hasActiveObservers()) {
            playbackService.getPlaylistManager().getWaitForConfirmationAudio().removeObservers(this);
        }
    }

    private final void registerObserver(PlaybackService playbackService) {
        if (!playbackService.getPlaylistManager().getWaitForConfirmationAudio().hasActiveObservers() && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            playbackService.getPlaylistManager().getWaitForConfirmationAudio().observe(this, this.observer);
        }
    }

    public void onServiceChanged(PlaybackService playbackService) {
        if (playbackService != null) {
            unregisterObserver(playbackService);
            registerObserver(playbackService);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        ExternalMonitor.INSTANCE.subscribeStorageCb(this);
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        this.preventRescan = true;
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        ExternalMonitor.INSTANCE.unsubscribeStorageCb(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        getScheduler().cancelAction("action_show_player");
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        FlowKt.launchIn(FlowKt.onEach(PlaybackService.Companion.getServiceFlow(), new AudioPlayerContainerActivity$onResume$1(this, (Continuation<? super AudioPlayerContainerActivity$onResume$1>) null)), CoroutineScopeKt.MainScope());
        if (this.playerShown) {
            applyMarginToProgressBar(getPlayerBehavior().getPeekHeight());
        } else {
            applyMarginToProgressBar(0);
        }
        setContentBottomPadding();
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        PlaybackService instance = PlaybackService.Companion.getInstance();
        if (instance != null) {
            unregisterObserver(instance);
        }
        super.onPause();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    public final void updateLib() {
        if (this.preventRescan) {
            this.preventRescan = false;
            return;
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        Fragment findFragmentById = supportFragmentManager.findFragmentById(R.id.fragment_placeholder);
        if (findFragmentById instanceof IRefreshable) {
            ((IRefreshable) findFragmentById).refresh();
        }
    }

    public final void showTipViewIfNeeded(int i, String str) {
        ViewStubCompat viewStubCompat;
        PlaybackService service;
        Intrinsics.checkNotNullParameter(str, "settingKey");
        if (!PlaybackService.Companion.hasRenderer() && (viewStubCompat = (ViewStubCompat) findViewById(i)) != null && !getSettings().getBoolean(str, false) && !Settings.INSTANCE.getShowTvUi()) {
            if (i == R.id.audio_player_tips) {
                if (getTipsDelegate().getCurrentTip() == null && !this.shownTips.contains(Integer.valueOf(i))) {
                    getTipsDelegate().init(viewStubCompat);
                }
            } else if (i == R.id.audio_playlist_tips && getPlaylistTipsDelegate().getCurrentTip() == null && !this.shownTips.contains(Integer.valueOf(i))) {
                getPlaylistTipsDelegate().init(viewStubCompat);
            }
            if (this.audioPlayer != null && (service = getAudioPlayer().getPlaylistModel().getService()) != null) {
                service.pause();
            }
        }
    }

    public final void onClickDismissTips(View view) {
        getTipsDelegate().close();
    }

    public final void onClickNextTips(View view) {
        getTipsDelegate().next();
    }

    public final void onClickDismissPlaylistTips(View view) {
        getPlaylistTipsDelegate().close();
    }

    public final void onClickNextPlaylistTips(View view) {
        getPlaylistTipsDelegate().next();
    }

    public final void removeTipViewIfDisplayed() {
        View findViewById = findViewById(R.id.audio_player_tips);
        if (findViewById != null) {
            ViewParent parent = findViewById.getParent();
            Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
            ((ViewGroup) parent).removeView(findViewById);
        }
    }

    /* access modifiers changed from: private */
    public final void showAudioPlayer() {
        if (!isFinishing()) {
            LifecycleAwareScheduler.scheduleAction$default(getScheduler(), "action_show_player", 100, (Bundle) null, 4, (Object) null);
        }
    }

    private final void showAudioPlayerImpl() {
        if (!isFinishing()) {
            if (!isAudioPlayerReady()) {
                initAudioPlayer();
            }
            FrameLayout frameLayout = this.audioPlayerContainer;
            FrameLayout frameLayout2 = null;
            if (frameLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioPlayerContainer");
                frameLayout = null;
            }
            if (frameLayout.getVisibility() != 0) {
                FrameLayout frameLayout3 = this.audioPlayerContainer;
                if (frameLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("audioPlayerContainer");
                } else {
                    frameLayout2 = frameLayout3;
                }
                frameLayout2.setVisibility(0);
            }
            PlayerBehavior<?> playerBehavior2 = getPlayerBehavior();
            if (playerBehavior2.getState() == 5) {
                playerBehavior2.setState(4);
            }
            playerBehavior2.setHideable(false);
            if (getTipsDelegate().getCurrentTip() == null && getPlaylistTipsDelegate().getCurrentTip() == null) {
                playerBehavior2.lock(false);
            }
        }
    }

    public final boolean slideDownAudioPlayer() {
        if (!isAudioPlayerReady() || getPlayerBehavior().getState() != 3) {
            return false;
        }
        getPlayerBehavior().setState(4);
        return true;
    }

    public final void slideUpOrDownAudioPlayer() {
        if (isAudioPlayerReady() && getPlayerBehavior().getState() != 5) {
            PlayerBehavior<?> playerBehavior2 = getPlayerBehavior();
            int i = 3;
            if (getPlayerBehavior().getState() == 3) {
                i = 4;
            }
            playerBehavior2.setState(i);
        }
    }

    public final void hideAudioPlayer() {
        if (!isFinishing()) {
            getScheduler().cancelAction("action_show_player");
            LifecycleAwareScheduler.startAction$default(getScheduler(), "action_hide_player", (Bundle) null, 2, (Object) null);
        }
    }

    private final void hideAudioPlayerImpl() {
        if (isAudioPlayerReady()) {
            getPlayerBehavior().setHideable(true);
            getPlayerBehavior().setState(5);
        }
    }

    static /* synthetic */ void updateProgressVisibility$default(AudioPlayerContainerActivity audioPlayerContainerActivity, boolean z, String str, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                str = null;
            }
            audioPlayerContainerActivity.updateProgressVisibility(z, str);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateProgressVisibility");
    }

    /* access modifiers changed from: private */
    public final void updateProgressVisibility(boolean z, String str) {
        int i = z ? 0 : 8;
        View view = this.scanProgressLayout;
        if (view != null && view.getVisibility() == i) {
            return;
        }
        if (z) {
            getScheduler().scheduleAction("action_display_progressbar", 100, BundleKt.bundleOf(TuplesKt.to("discovery", str)));
            return;
        }
        getScheduler().cancelAction("action_display_progressbar");
        KotlinExtensionsKt.setVisibility(this.scanProgressLayout, i);
    }

    private final void showProgressBar(String str) {
        if (Medialibrary.getInstance().isWorking()) {
            View findViewById = findViewById(R.id.scan_viewstub);
            int i = 0;
            if (findViewById != null) {
                findViewById.setVisibility(0);
                this.scanProgressLayout = findViewById(R.id.scan_progress_layout);
                this.scanProgressText = (TextView) findViewById(R.id.scan_progress_text);
                this.scanProgressBar = (ProgressBar) findViewById(R.id.scan_progress_bar);
            } else {
                View view = this.scanProgressLayout;
                if (view != null) {
                    view.setVisibility(0);
                }
            }
            if (findViewById != null) {
                ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
                Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
                CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
                if (this instanceof MainActivity) {
                    Context context = this;
                    layoutParams2.setAnchorId(UiTools.INSTANCE.isTablet(context) ? R.id.fragment_placeholder : R.id.navigation);
                    layoutParams2.anchorGravity = UiTools.INSTANCE.isTablet(context) ? 80 : 48;
                    if (UiTools.INSTANCE.isTablet(context)) {
                        i = 72;
                    }
                    layoutParams2.setMarginStart(KotlinExtensionsKt.getDp(i));
                }
                findViewById.setLayoutParams(layoutParams2);
            }
            TextView textView = this.scanProgressText;
            if (textView != null) {
                textView.setText(str);
            }
        }
    }

    public final void closeMiniPlayer() {
        hideAudioPlayerImpl();
    }

    /* access modifiers changed from: private */
    public final void applyMarginToProgressBar(int i) {
        View view = this.scanProgressLayout;
        if (view != null && view != null && view.getVisibility() == 0) {
            View view2 = this.scanProgressLayout;
            Intrinsics.checkNotNull(view2);
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams");
            CoordinatorLayout.LayoutParams layoutParams2 = (CoordinatorLayout.LayoutParams) layoutParams;
            if (!this.playerShown) {
                i = 0;
            }
            layoutParams2.bottomMargin = i;
            View view3 = this.scanProgressLayout;
            if (view3 != null) {
                view3.setLayoutParams(layoutParams2);
            }
        }
    }

    private final void registerLiveData() {
        LifecycleOwner lifecycleOwner = this;
        PlaylistManager.Companion.getShowAudioPlayer().observe(lifecycleOwner, new AudioPlayerContainerActivityKt$sam$androidx_lifecycle_Observer$0(new AudioPlayerContainerActivity$registerLiveData$1(this)));
        MediaParsingService.Companion.getProgress().observe(lifecycleOwner, new AudioPlayerContainerActivityKt$sam$androidx_lifecycle_Observer$0(new AudioPlayerContainerActivity$registerLiveData$2(this)));
        MediaParsingService.Companion.getDiscoveryError().observe(lifecycleOwner, new AudioPlayerContainerActivityKt$sam$androidx_lifecycle_Observer$0(new AudioPlayerContainerActivity$registerLiveData$3(this)));
        MediaParsingService.Companion.getNewStorages().observe(lifecycleOwner, new AudioPlayerContainerActivityKt$sam$androidx_lifecycle_Observer$0(new AudioPlayerContainerActivity$registerLiveData$4(this)));
    }

    public final Job proposeCard() {
        return LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new AudioPlayerContainerActivity$proposeCard$1(this, (Continuation<? super AudioPlayerContainerActivity$proposeCard$1>) null));
    }

    public final void lockPlayer(boolean z) {
        if (this.playerBehavior != null) {
            getPlayerBehavior().lock(z);
        }
    }

    public void onTaskTriggered(String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(bundle, "data");
        int hashCode = str.hashCode();
        if (hashCode != 12249077) {
            if (hashCode != 520819162) {
                if (hashCode == 1581328192 && str.equals("action_display_progressbar")) {
                    getScheduler().cancelAction("action_display_progressbar");
                    String string = bundle.getString("discovery", "");
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    showProgressBar(string);
                }
            } else if (str.equals("action_show_player")) {
                Snackbar snackbar = this.resumeCard;
                if (snackbar != null) {
                    Snackbar snackbar2 = null;
                    if (snackbar == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("resumeCard");
                        snackbar = null;
                    }
                    if (snackbar.isShown()) {
                        Snackbar snackbar3 = this.resumeCard;
                        if (snackbar3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("resumeCard");
                        } else {
                            snackbar2 = snackbar3;
                        }
                        snackbar2.dismiss();
                    }
                }
                showAudioPlayerImpl();
                if (this.playerBehavior != null) {
                    applyMarginToProgressBar(getPlayerBehavior().getPeekHeight());
                }
            }
        } else if (str.equals("action_hide_player")) {
            hideAudioPlayerImpl();
            applyMarginToProgressBar(0);
        }
    }
}
