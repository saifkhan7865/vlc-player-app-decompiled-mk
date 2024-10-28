package org.videolan.vlc.gui.audio;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.FlowLiveDataConversions;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.window.layout.FoldingFeature;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.Calendar;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.tools.Strings;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.AudioPlayerBinding;
import org.videolan.vlc.gui.AudioPlayerContainerActivity;
import org.videolan.vlc.gui.InfoActivity;
import org.videolan.vlc.gui.audio.PlaylistAdapter;
import org.videolan.vlc.gui.dialogs.ContextSheetKt;
import org.videolan.vlc.gui.dialogs.CtxActionReceiver;
import org.videolan.vlc.gui.dialogs.PlaybackSpeedDialog;
import org.videolan.vlc.gui.dialogs.SleepTimerDialog;
import org.videolan.vlc.gui.helpers.BookmarkListDelegate;
import org.videolan.vlc.gui.helpers.PlayerOptionsDelegate;
import org.videolan.vlc.gui.helpers.SwipeDragItemTouchHelperCallback;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.video.VideoPlayerActivity;
import org.videolan.vlc.media.ABRepeat;
import org.videolan.vlc.media.PlayerController;
import org.videolan.vlc.media.PlaylistManager;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.util.FlagSet;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.viewmodels.BookmarkModel;
import org.videolan.vlc.viewmodels.PlaybackProgress;
import org.videolan.vlc.viewmodels.PlaylistModel;

@Metadata(d1 = {"\u0000\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0007\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0002\u001d3\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002³\u0001B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010Z\u001a\u00020[2\u0006\u0010\\\u001a\u00020]H\u0016J\u0006\u0010^\u001a\u00020\rJ\u0006\u0010_\u001a\u00020\rJ(\u0010`\u001a\u00020[2\u0006\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020H2\u0006\u0010d\u001a\u00020H2\u0006\u0010e\u001a\u00020HH\u0016J\u0006\u0010f\u001a\u00020\rJ\u000e\u0010g\u001a\u00020[H@¢\u0006\u0002\u0010hJ\b\u0010i\u001a\u00020jH\u0016J\b\u0010k\u001a\u00020\rH\u0002J\t\u0010l\u001a\u00020\rH\u0001J\u0006\u0010m\u001a\u00020\rJ\u0018\u0010n\u001a\u00020[2\u0006\u0010o\u001a\u00020\r2\u0006\u0010p\u001a\u00020\rH\u0002J\t\u0010q\u001a\u00020[H\u0001J\u0013\u0010r\u001a\u00020[2\b\b\u0002\u0010s\u001a\u00020\rH\u0001J\u000e\u0010t\u001a\u00020[2\u0006\u0010u\u001a\u00020vJ\u000e\u0010w\u001a\u00020[2\u0006\u0010u\u001a\u00020vJ\u0012\u0010x\u001a\u00020[2\b\u0010y\u001a\u0004\u0018\u00010zH\u0016J&\u0010{\u001a\u0004\u0018\u00010v2\u0006\u0010|\u001a\u00020}2\b\u0010~\u001a\u0004\u0018\u000102\b\u0010y\u001a\u0004\u0018\u00010zH\u0016J\t\u0010\u0001\u001a\u00020[H\u0016J\u0010\u0010\u0001\u001a\u00020[2\u0007\u0010\u0001\u001a\u00020vJ\u0010\u0010\u0001\u001a\u00020\r2\u0007\u0010\u0001\u001a\u00020vJ\u0010\u0010\u0001\u001a\u00020[2\u0007\u0010\u0001\u001a\u00020vJ\u0010\u0010\u0001\u001a\u00020\r2\u0007\u0010\u0001\u001a\u00020vJ\u0012\u0010\u0001\u001a\u00020[2\t\u0010\u0001\u001a\u0004\u0018\u00010vJ\u0012\u0010\u0001\u001a\u00020[2\t\u0010\u0001\u001a\u0004\u0018\u00010vJ\u0010\u0010\u0001\u001a\u00020[2\u0007\u0010\u0001\u001a\u00020vJ&\u0010\u0001\u001a\u00020[2\u0007\u0010\u0001\u001a\u00020v2\u0007\u0010\u0001\u001a\u00020H2\t\u0010\u0001\u001a\u0004\u0018\u00010#H\u0016J\u0012\u0010\u0001\u001a\u00020[2\t\u0010\u0001\u001a\u0004\u0018\u00010vJ\u0010\u0010\u0001\u001a\u00020[2\u0007\u0010\u0001\u001a\u00020vJ\t\u0010\u0001\u001a\u00020[H\u0016J\u0007\u0010\u0001\u001a\u00020[J\u0012\u0010\u0001\u001a\u00020[2\u0007\u0010\u0001\u001a\u00020zH\u0016J\u000f\u0010\u0001\u001a\u00020[2\u0006\u0010u\u001a\u00020vJ\u0012\u0010\u0001\u001a\u00020[2\u0007\u0010\u0001\u001a\u00020HH\u0016J\u0010\u0010\u0001\u001a\u00020[2\u0007\u0010\u0001\u001a\u00020vJ\u0014\u0010\u0001\u001a\u00020[2\b\u0010\u0001\u001a\u00030\u0001H\u0001J\u0010\u0010\u0001\u001a\u00020[2\u0007\u0010\u0001\u001a\u00020HJ\u0012\u0010\u0001\u001a\u00020\r2\t\u0010\u0001\u001a\u0004\u0018\u00010vJ)\u0010\u0001\u001a\u00020[2\u0006\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020H2\u0006\u0010d\u001a\u00020H2\u0006\u0010e\u001a\u00020HH\u0016J\u0010\u0010\u0001\u001a\u00020[2\u0007\u0010\u0001\u001a\u00020vJ\u001c\u0010\u0001\u001a\u00020[2\u0007\u0010\u0001\u001a\u00020v2\b\u0010y\u001a\u0004\u0018\u00010zH\u0016J\u001b\u0010\u0001\u001a\u00020[2\u0007\u0010\u0001\u001a\u00020H2\u0007\u0010\u0001\u001a\u00020#H\u0016J\t\u0010\u0001\u001a\u0004\u0018\u00010\u0007J\u0007\u0010 \u0001\u001a\u00020[J\t\u0010¡\u0001\u001a\u00020\rH\u0002J\u0011\u0010¢\u0001\u001a\u00020[2\b\u0010u\u001a\u0004\u0018\u00010vJ\u0007\u0010£\u0001\u001a\u00020[J\u0007\u0010¤\u0001\u001a\u00020[J\u0013\u0010¥\u0001\u001a\u00020[2\u0007\u0010¦\u0001\u001a\u00020\rH\u0001J\u0012\u0010§\u0001\u001a\u00020[2\u0007\u0010¨\u0001\u001a\u00020#H\u0002J\t\u0010©\u0001\u001a\u00020[H\u0002J\n\u0010ª\u0001\u001a\u00020[H\u0001J\u000f\u0010«\u0001\u001a\u00020[HA¢\u0006\u0002\u0010hJ\t\u0010¬\u0001\u001a\u00020[H\u0002J\u0013\u0010­\u0001\u001a\u00020[2\b\u0010®\u0001\u001a\u00030¯\u0001H\u0002J\t\u0010°\u0001\u001a\u00020[H\u0002J\t\u0010±\u0001\u001a\u00020[H\u0002J\u0016\u0010²\u0001\u001a\u00020[*\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fH\u0001R\u001a\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\u0004\n\u0002\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0004¢\u0006\u0002\n\u0000R$\u0010!\u001a\u0018\u0012\u0004\u0012\u00020#\u0012\f\u0012\n\u0012\u0004\u0012\u00020%\u0018\u00010$\u0018\u00010\"X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u0004\u0018\u00010'X\u000f¢\u0006\f\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001b\u0010,\u001a\u00020-8BX\u0002¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b.\u0010/R\u0010\u00102\u001a\u000203X\u0004¢\u0006\u0004\n\u0002\u00104R\u001f\u00105\u001a\u000606j\u0002`78BX\u0002¢\u0006\f\n\u0004\b:\u00101\u001a\u0004\b8\u00109R\u000e\u0010;\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X.¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020AX.¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020AX.¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020AX.¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020AX.¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020AX.¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020AX.¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020HX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020JX.¢\u0006\u0002\n\u0000R\u001a\u0010K\u001a\u00020LX.¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u000e\u0010Q\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020HX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020TX.¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020WX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000¨\u0006´\u0001"}, d2 = {"Lorg/videolan/vlc/gui/audio/AudioPlayer;", "Landroidx/fragment/app/Fragment;", "Lorg/videolan/vlc/gui/audio/PlaylistAdapter$IPlayer;", "Landroid/text/TextWatcher;", "Lorg/videolan/vlc/gui/audio/IAudioPlayerAnimator;", "()V", "abRepeatAddMarker", "Landroid/widget/Button;", "getAbRepeatAddMarker", "()Landroid/widget/Button;", "setAbRepeatAddMarker", "(Landroid/widget/Button;)V", "audioPlayProgressMode", "", "binding", "Lorg/videolan/vlc/databinding/AudioPlayerBinding;", "bookmarkListDelegate", "Lorg/videolan/vlc/gui/helpers/BookmarkListDelegate;", "getBookmarkListDelegate", "()Lorg/videolan/vlc/gui/helpers/BookmarkListDelegate;", "setBookmarkListDelegate", "(Lorg/videolan/vlc/gui/helpers/BookmarkListDelegate;)V", "bookmarkModel", "Lorg/videolan/vlc/viewmodels/BookmarkModel;", "getBookmarkModel", "()Lorg/videolan/vlc/viewmodels/BookmarkModel;", "setBookmarkModel", "(Lorg/videolan/vlc/viewmodels/BookmarkModel;)V", "coverMediaSwitcherListener", "org/videolan/vlc/gui/audio/AudioPlayer$coverMediaSwitcherListener$1", "Lorg/videolan/vlc/gui/audio/AudioPlayer$coverMediaSwitcherListener$1;", "ctxReceiver", "Lorg/videolan/vlc/gui/dialogs/CtxActionReceiver;", "currentChapters", "Lkotlin/Pair;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "", "Lorg/videolan/libvlc/MediaPlayer$Chapter;", "foldingFeature", "Landroidx/window/layout/FoldingFeature;", "getFoldingFeature", "()Landroidx/window/layout/FoldingFeature;", "setFoldingFeature", "(Landroidx/window/layout/FoldingFeature;)V", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "handler$delegate", "Lkotlin/Lazy;", "headerMediaSwitcherListener", "org/videolan/vlc/gui/audio/AudioPlayer$headerMediaSwitcherListener$1", "Lorg/videolan/vlc/gui/audio/AudioPlayer$headerMediaSwitcherListener$1;", "hideSearchRunnable", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "getHideSearchRunnable", "()Ljava/lang/Runnable;", "hideSearchRunnable$delegate", "isDragging", "lastEndsAt", "", "optionsDelegate", "Lorg/videolan/vlc/gui/helpers/PlayerOptionsDelegate;", "pauseToPlay", "Landroidx/vectordrawable/graphics/drawable/AnimatedVectorDrawableCompat;", "pauseToPlayHeader", "pauseToPlaySmall", "playToPause", "playToPauseHeader", "playToPauseSmall", "playerState", "", "playlistAdapter", "Lorg/videolan/vlc/gui/audio/PlaylistAdapter;", "playlistModel", "Lorg/videolan/vlc/viewmodels/PlaylistModel;", "getPlaylistModel", "()Lorg/videolan/vlc/viewmodels/PlaylistModel;", "setPlaylistModel", "(Lorg/videolan/vlc/viewmodels/PlaylistModel;)V", "previewingSeek", "previousRepeatType", "settings", "Landroid/content/SharedPreferences;", "showRemainingTime", "timelineListener", "Landroid/widget/SeekBar$OnSeekBarChangeListener;", "wasPlaying", "wasShuffling", "afterTextChanged", "", "editable", "Landroid/text/Editable;", "areBookmarksVisible", "backPressed", "beforeTextChanged", "charSequence", "", "start", "before", "count", "clearSearch", "doUpdate", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLifeCycle", "Landroidx/lifecycle/Lifecycle;", "hideSearchField", "isShowingCover", "isTablet", "jump", "forward", "long", "manageHinge", "manageSearchVisibilities", "filter", "onABRepeatResetClick", "v", "Landroid/view/View;", "onABRepeatStopClick", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onJumpBack", "view", "onJumpBackLong", "onJumpForward", "onJumpForwardLong", "onNextClick", "onPlayPauseClick", "onPlaylistSwitchClick", "onPopupMenu", "position", "item", "onPreviousClick", "onRepeatClick", "onResume", "onResumeToVideoClick", "onSaveInstanceState", "outState", "onSearchClick", "onSelectionSet", "onShuffleClick", "onSlide", "slideOffset", "", "onStateChanged", "newState", "onStopClick", "onTextChanged", "onTimeLabelClick", "onViewCreated", "playItem", "retrieveAbRepeatAddMarker", "setBottomMargin", "shouldHidePlayProgress", "showAdvancedOptions", "showBookmarks", "showChips", "showCover", "value", "showInfoDialog", "media", "showPlaylistTips", "switchShowCover", "updateBackground", "updatePlayPause", "updateProgress", "progress", "Lorg/videolan/vlc/viewmodels/PlaybackProgress;", "updateRepeatMode", "updateShuffleMode", "setupAnimator", "LongSeekListener", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayer.kt */
public final class AudioPlayer extends Fragment implements PlaylistAdapter.IPlayer, TextWatcher, IAudioPlayerAnimator {
    private final /* synthetic */ AudioPlayerAnimator $$delegate_0 = new AudioPlayerAnimator();
    public Button abRepeatAddMarker;
    /* access modifiers changed from: private */
    public boolean audioPlayProgressMode;
    /* access modifiers changed from: private */
    public AudioPlayerBinding binding;
    public BookmarkListDelegate bookmarkListDelegate;
    public BookmarkModel bookmarkModel;
    private final AudioPlayer$coverMediaSwitcherListener$1 coverMediaSwitcherListener = new AudioPlayer$coverMediaSwitcherListener$1(this);
    private final CtxActionReceiver ctxReceiver = new AudioPlayer$ctxReceiver$1(this);
    /* access modifiers changed from: private */
    public Pair<? extends MediaWrapper, ? extends List<? extends MediaPlayer.Chapter>> currentChapters;
    private final Lazy handler$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, AudioPlayer$handler$2.INSTANCE);
    private final AudioPlayer$headerMediaSwitcherListener$1 headerMediaSwitcherListener = new AudioPlayer$headerMediaSwitcherListener$1(this);
    private final Lazy hideSearchRunnable$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new AudioPlayer$hideSearchRunnable$2(this));
    /* access modifiers changed from: private */
    public boolean isDragging;
    /* access modifiers changed from: private */
    public long lastEndsAt = -1;
    private PlayerOptionsDelegate optionsDelegate;
    private AnimatedVectorDrawableCompat pauseToPlay;
    private AnimatedVectorDrawableCompat pauseToPlayHeader;
    private AnimatedVectorDrawableCompat pauseToPlaySmall;
    private AnimatedVectorDrawableCompat playToPause;
    private AnimatedVectorDrawableCompat playToPauseHeader;
    private AnimatedVectorDrawableCompat playToPauseSmall;
    private int playerState;
    /* access modifiers changed from: private */
    public PlaylistAdapter playlistAdapter;
    public PlaylistModel playlistModel;
    /* access modifiers changed from: private */
    public boolean previewingSeek;
    private int previousRepeatType = -1;
    private SharedPreferences settings;
    /* access modifiers changed from: private */
    public boolean showRemainingTime;
    private SeekBar.OnSeekBarChangeListener timelineListener = new AudioPlayer$timelineListener$1(this);
    private boolean wasPlaying = true;
    private boolean wasShuffling;

    public void afterTextChanged(Editable editable) {
        Intrinsics.checkNotNullParameter(editable, "editable");
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(charSequence, "charSequence");
    }

    public FoldingFeature getFoldingFeature() {
        return this.$$delegate_0.getFoldingFeature();
    }

    public boolean isShowingCover() {
        return this.$$delegate_0.isShowingCover();
    }

    public void manageHinge() {
        this.$$delegate_0.manageHinge();
    }

    public void manageSearchVisibilities(boolean z) {
        this.$$delegate_0.manageSearchVisibilities(z);
    }

    public void onSlide(float f) {
        this.$$delegate_0.onSlide(f);
    }

    public void setFoldingFeature(FoldingFeature foldingFeature) {
        this.$$delegate_0.setFoldingFeature(foldingFeature);
    }

    public void setupAnimator(AudioPlayer audioPlayer, AudioPlayerBinding audioPlayerBinding) {
        Intrinsics.checkNotNullParameter(audioPlayer, "<this>");
        Intrinsics.checkNotNullParameter(audioPlayerBinding, "binding");
        this.$$delegate_0.setupAnimator(audioPlayer, audioPlayerBinding);
    }

    public void showCover(boolean z) {
        this.$$delegate_0.showCover(z);
    }

    public void switchShowCover() {
        this.$$delegate_0.switchShowCover();
    }

    public Object updateBackground(Continuation<? super Unit> continuation) {
        return this.$$delegate_0.updateBackground(continuation);
    }

    /* access modifiers changed from: private */
    public final Handler getHandler() {
        return (Handler) this.handler$delegate.getValue();
    }

    public final PlaylistModel getPlaylistModel() {
        PlaylistModel playlistModel2 = this.playlistModel;
        if (playlistModel2 != null) {
            return playlistModel2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playlistModel");
        return null;
    }

    public final void setPlaylistModel(PlaylistModel playlistModel2) {
        Intrinsics.checkNotNullParameter(playlistModel2, "<set-?>");
        this.playlistModel = playlistModel2;
    }

    public final BookmarkModel getBookmarkModel() {
        BookmarkModel bookmarkModel2 = this.bookmarkModel;
        if (bookmarkModel2 != null) {
            return bookmarkModel2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bookmarkModel");
        return null;
    }

    public final void setBookmarkModel(BookmarkModel bookmarkModel2) {
        Intrinsics.checkNotNullParameter(bookmarkModel2, "<set-?>");
        this.bookmarkModel = bookmarkModel2;
    }

    public final BookmarkListDelegate getBookmarkListDelegate() {
        BookmarkListDelegate bookmarkListDelegate2 = this.bookmarkListDelegate;
        if (bookmarkListDelegate2 != null) {
            return bookmarkListDelegate2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bookmarkListDelegate");
        return null;
    }

    public final void setBookmarkListDelegate(BookmarkListDelegate bookmarkListDelegate2) {
        Intrinsics.checkNotNullParameter(bookmarkListDelegate2, "<set-?>");
        this.bookmarkListDelegate = bookmarkListDelegate2;
    }

    public final Button getAbRepeatAddMarker() {
        Button button = this.abRepeatAddMarker;
        if (button != null) {
            return button;
        }
        Intrinsics.throwUninitializedPropertyAccessException("abRepeatAddMarker");
        return null;
    }

    public final void setAbRepeatAddMarker(Button button) {
        Intrinsics.checkNotNullParameter(button, "<set-?>");
        this.abRepeatAddMarker = button;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.playerState = bundle.getInt("player_state");
            this.wasPlaying = bundle.getBoolean("was_playing");
            this.showRemainingTime = bundle.getBoolean(SettingsKt.SHOW_REMAINING_TIME);
        }
        this.playlistAdapter = new PlaylistAdapter(this);
        Settings settings2 = Settings.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        this.settings = (SharedPreferences) settings2.getInstance(requireContext);
        setPlaylistModel(PlaylistModel.Companion.get((Fragment) this));
        LifecycleOwner lifecycleOwner = this;
        getPlaylistModel().getProgress().observe(lifecycleOwner, new AudioPlayerKt$sam$androidx_lifecycle_Observer$0(new AudioPlayer$onCreate$2(this)));
        getPlaylistModel().getSpeed().observe(lifecycleOwner, new AudioPlayerKt$sam$androidx_lifecycle_Observer$0(new AudioPlayer$onCreate$3(this)));
        PlaylistAdapter playlistAdapter2 = this.playlistAdapter;
        if (playlistAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistAdapter");
            playlistAdapter2 = null;
        }
        playlistAdapter2.setModel(getPlaylistModel());
        KextensionsKt.launchWhenStarted(FlowKt.onEach(FlowKt.conflate(FlowLiveDataConversions.asFlow(getPlaylistModel().getDataset())), new AudioPlayer$onCreate$4(this, (Continuation<? super AudioPlayer$onCreate$4>) null)), LifecycleOwnerKt.getLifecycleScope(lifecycleOwner));
        BookmarkModel.Companion companion = BookmarkModel.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        setBookmarkModel(companion.get(requireActivity));
        PlaybackService.Companion.getPlayerSleepTime().observe(lifecycleOwner, new AudioPlayerKt$sam$androidx_lifecycle_Observer$0(new AudioPlayer$onCreate$5(this)));
        Settings.INSTANCE.setAudioControlsChangeListener(new AudioPlayer$onCreate$6(this));
        LifecycleOwnerKt.getLifecycleScope(lifecycleOwner).launchWhenStarted(new AudioPlayer$onCreate$7(this, (Continuation<? super AudioPlayer$onCreate$7>) null));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        AudioPlayerBinding inflate = AudioPlayerBinding.inflate(layoutInflater);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        AudioPlayerBinding audioPlayerBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setupAnimator(this, inflate);
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), (CoroutineStart) null, new AudioPlayer$onCreateView$1(this, (Continuation<? super AudioPlayer$onCreateView$1>) null), 2, (Object) null);
        AudioPlayerBinding audioPlayerBinding2 = this.binding;
        if (audioPlayerBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            audioPlayerBinding = audioPlayerBinding2;
        }
        return audioPlayerBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        PlaylistManager playlistManager;
        MutableLiveData<Boolean> abRepeatOn;
        PlaylistManager playlistManager2;
        MutableLiveData<ABRepeat> abRepeat;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        AudioPlayerBinding audioPlayerBinding = this.binding;
        AudioPlayerBinding audioPlayerBinding2 = null;
        if (audioPlayerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding = null;
        }
        audioPlayerBinding.songsList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        AudioPlayerBinding audioPlayerBinding3 = this.binding;
        if (audioPlayerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding3 = null;
        }
        RecyclerView recyclerView = audioPlayerBinding3.songsList;
        PlaylistAdapter playlistAdapter2 = this.playlistAdapter;
        if (playlistAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistAdapter");
            playlistAdapter2 = null;
        }
        recyclerView.setAdapter(playlistAdapter2);
        AudioPlayerBinding audioPlayerBinding4 = this.binding;
        if (audioPlayerBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding4 = null;
        }
        audioPlayerBinding4.audioMediaSwitcher.setAudioMediaSwitcherListener(this.headerMediaSwitcherListener);
        AudioPlayerBinding audioPlayerBinding5 = this.binding;
        if (audioPlayerBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding5 = null;
        }
        audioPlayerBinding5.coverMediaSwitcher.setAudioMediaSwitcherListener(this.coverMediaSwitcherListener);
        AudioPlayerBinding audioPlayerBinding6 = this.binding;
        if (audioPlayerBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding6 = null;
        }
        EditText editText = audioPlayerBinding6.playlistSearchText.getEditText();
        if (editText != null) {
            editText.addTextChangedListener(this);
        }
        AudioPlayerBinding audioPlayerBinding7 = this.binding;
        if (audioPlayerBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding7 = null;
        }
        audioPlayerBinding7.header.setOnClickListener(new AudioPlayer$$ExternalSyntheticLambda0(this));
        AudioPlayerBinding audioPlayerBinding8 = this.binding;
        if (audioPlayerBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding8 = null;
        }
        ImageView imageView = audioPlayerBinding8.nextChapter;
        if (imageView != null) {
            imageView.setOnClickListener(new AudioPlayer$$ExternalSyntheticLambda7(this));
        }
        AudioPlayerBinding audioPlayerBinding9 = this.binding;
        if (audioPlayerBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding9 = null;
        }
        ImageView imageView2 = audioPlayerBinding9.previousChapter;
        if (imageView2 != null) {
            imageView2.setOnClickListener(new AudioPlayer$$ExternalSyntheticLambda8(this));
        }
        PlaylistAdapter playlistAdapter3 = this.playlistAdapter;
        if (playlistAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playlistAdapter");
            playlistAdapter3 = null;
        }
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeDragItemTouchHelperCallback(playlistAdapter3, true, false, 4, (DefaultConstructorMarker) null));
        AudioPlayerBinding audioPlayerBinding10 = this.binding;
        if (audioPlayerBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding10 = null;
        }
        itemTouchHelper.attachToRecyclerView(audioPlayerBinding10.songsList);
        AudioPlayerBinding audioPlayerBinding11 = this.binding;
        if (audioPlayerBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding11 = null;
        }
        audioPlayerBinding11.setFragment(this);
        AudioPlayerBinding audioPlayerBinding12 = this.binding;
        if (audioPlayerBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding12 = null;
        }
        audioPlayerBinding12.next.setOnTouchListener(new LongSeekListener(true));
        AudioPlayerBinding audioPlayerBinding13 = this.binding;
        if (audioPlayerBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding13 = null;
        }
        audioPlayerBinding13.previous.setOnTouchListener(new LongSeekListener(false));
        AudioPlayerBinding audioPlayerBinding14 = this.binding;
        if (audioPlayerBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding14 = null;
        }
        registerForContextMenu(audioPlayerBinding14.songsList);
        setUserVisibleHint(true);
        AudioPlayerBinding audioPlayerBinding15 = this.binding;
        if (audioPlayerBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding15 = null;
        }
        audioPlayerBinding15.timeline.setOnSeekBarChangeListener(this.timelineListener);
        AnimatedVectorDrawableCompat create = AnimatedVectorDrawableCompat.create(requireActivity(), R.drawable.anim_play_pause_video);
        Intrinsics.checkNotNull(create);
        this.playToPause = create;
        AnimatedVectorDrawableCompat create2 = AnimatedVectorDrawableCompat.create(requireActivity(), R.drawable.anim_pause_play_video);
        Intrinsics.checkNotNull(create2);
        this.pauseToPlay = create2;
        AnimatedVectorDrawableCompat create3 = AnimatedVectorDrawableCompat.create(requireActivity(), R.drawable.anim_play_pause_video);
        Intrinsics.checkNotNull(create3);
        this.playToPauseHeader = create3;
        AnimatedVectorDrawableCompat create4 = AnimatedVectorDrawableCompat.create(requireActivity(), R.drawable.anim_pause_play_video);
        Intrinsics.checkNotNull(create4);
        this.pauseToPlayHeader = create4;
        AnimatedVectorDrawableCompat create5 = AnimatedVectorDrawableCompat.create(requireActivity(), R.drawable.anim_play_pause_video);
        Intrinsics.checkNotNull(create5);
        this.playToPauseSmall = create5;
        AnimatedVectorDrawableCompat create6 = AnimatedVectorDrawableCompat.create(requireActivity(), R.drawable.anim_pause_play_video);
        Intrinsics.checkNotNull(create6);
        this.pauseToPlaySmall = create6;
        onSlide(0.0f);
        AudioPlayerBinding audioPlayerBinding16 = this.binding;
        if (audioPlayerBinding16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding16 = null;
        }
        View findViewById = audioPlayerBinding16.abRepeatContainer.findViewById(R.id.ab_repeat_add_marker);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        setAbRepeatAddMarker((Button) findViewById);
        PlaybackService service = getPlaylistModel().getService();
        if (!(service == null || (playlistManager2 = service.getPlaylistManager()) == null || (abRepeat = playlistManager2.getAbRepeat()) == null)) {
            abRepeat.observe(getViewLifecycleOwner(), new AudioPlayerKt$sam$androidx_lifecycle_Observer$0(new AudioPlayer$onViewCreated$4(this)));
        }
        PlaybackService service2 = getPlaylistModel().getService();
        if (!(service2 == null || (playlistManager = service2.getPlaylistManager()) == null || (abRepeatOn = playlistManager.getAbRepeatOn()) == null)) {
            abRepeatOn.observe(getViewLifecycleOwner(), new AudioPlayerKt$sam$androidx_lifecycle_Observer$0(new AudioPlayer$onViewCreated$5(this)));
        }
        getAbRepeatAddMarker().setOnClickListener(new AudioPlayer$$ExternalSyntheticLambda9(this));
        Settings settings2 = Settings.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        this.audioPlayProgressMode = ((SharedPreferences) settings2.getInstance(requireActivity)).getBoolean(SettingsKt.AUDIO_PLAY_PROGRESS_MODE, false);
        AudioPlayerBinding audioPlayerBinding17 = this.binding;
        if (audioPlayerBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding17 = null;
        }
        audioPlayerBinding17.audioPlayProgress.setOnClickListener(new AudioPlayer$$ExternalSyntheticLambda10(this));
        AudioPlayerBinding audioPlayerBinding18 = this.binding;
        if (audioPlayerBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding18 = null;
        }
        audioPlayerBinding18.playbackSpeedQuickAction.setOnClickListener(new AudioPlayer$$ExternalSyntheticLambda11(this));
        AudioPlayerBinding audioPlayerBinding19 = this.binding;
        if (audioPlayerBinding19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding19 = null;
        }
        audioPlayerBinding19.playbackSpeedQuickAction.setOnLongClickListener(new AudioPlayer$$ExternalSyntheticLambda12(this));
        AudioPlayerBinding audioPlayerBinding20 = this.binding;
        if (audioPlayerBinding20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding20 = null;
        }
        audioPlayerBinding20.sleepQuickAction.setOnClickListener(new AudioPlayer$$ExternalSyntheticLambda1(this));
        AudioPlayerBinding audioPlayerBinding21 = this.binding;
        if (audioPlayerBinding21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding21 = null;
        }
        audioPlayerBinding21.sleepQuickAction.setOnLongClickListener(new AudioPlayer$$ExternalSyntheticLambda2(this));
        AudioPlayerBinding audioPlayerBinding22 = this.binding;
        if (audioPlayerBinding22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding22 = null;
        }
        TextView textView = audioPlayerBinding22.songTitle;
        if (textView != null) {
            textView.setOnClickListener(new AudioPlayer$$ExternalSyntheticLambda3(this));
        }
        AudioPlayerBinding audioPlayerBinding23 = this.binding;
        if (audioPlayerBinding23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding23 = null;
        }
        TextView textView2 = audioPlayerBinding23.songSubtitle;
        if (textView2 != null) {
            textView2.setOnClickListener(new AudioPlayer$$ExternalSyntheticLambda4(this));
        }
        AudioPlayerBinding audioPlayerBinding24 = this.binding;
        if (audioPlayerBinding24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding24 = null;
        }
        audioPlayerBinding24.hingeGoLeft.setOnClickListener(new AudioPlayer$$ExternalSyntheticLambda5(this));
        AudioPlayerBinding audioPlayerBinding25 = this.binding;
        if (audioPlayerBinding25 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            audioPlayerBinding2 = audioPlayerBinding25;
        }
        audioPlayerBinding2.hingeGoRight.setOnClickListener(new AudioPlayer$$ExternalSyntheticLambda6(this));
        setBottomMargin();
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$1(AudioPlayer audioPlayer, View view) {
        Intrinsics.checkNotNullParameter(audioPlayer, "this$0");
        FragmentActivity activity = audioPlayer.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type org.videolan.vlc.gui.AudioPlayerContainerActivity");
        ((AudioPlayerContainerActivity) activity).slideUpOrDownAudioPlayer();
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(AudioPlayer audioPlayer, View view) {
        Intrinsics.checkNotNullParameter(audioPlayer, "this$0");
        audioPlayer.coverMediaSwitcherListener.onChapterSwitching(true);
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$3(AudioPlayer audioPlayer, View view) {
        Intrinsics.checkNotNullParameter(audioPlayer, "this$0");
        audioPlayer.coverMediaSwitcherListener.onChapterSwitching(false);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0020, code lost:
        r0 = r0.getPlaylistManager();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void onViewCreated$lambda$4(org.videolan.vlc.gui.audio.AudioPlayer r3, android.view.View r4) {
        /*
            java.lang.String r4 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r4)
            org.videolan.vlc.viewmodels.PlaylistModel r4 = r3.getPlaylistModel()
            org.videolan.vlc.PlaybackService r4 = r4.getService()
            if (r4 == 0) goto L_0x0041
            org.videolan.vlc.media.PlaylistManager r4 = r4.getPlaylistManager()
            if (r4 == 0) goto L_0x0041
            org.videolan.vlc.viewmodels.PlaylistModel r0 = r3.getPlaylistModel()
            org.videolan.vlc.PlaybackService r0 = r0.getService()
            r1 = 0
            if (r0 == 0) goto L_0x002b
            org.videolan.vlc.media.PlaylistManager r0 = r0.getPlaylistManager()
            if (r0 == 0) goto L_0x002b
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = r0.getCurrentMedia()
            goto L_0x002c
        L_0x002b:
            r0 = r1
        L_0x002c:
            org.videolan.vlc.databinding.AudioPlayerBinding r3 = r3.binding
            if (r3 != 0) goto L_0x0036
            java.lang.String r3 = "binding"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L_0x0037
        L_0x0036:
            r1 = r3
        L_0x0037:
            org.videolan.vlc.gui.view.AccessibleSeekBar r3 = r1.timeline
            int r3 = r3.getProgress()
            long r1 = (long) r3
            r4.setABRepeatValue(r0, r1)
        L_0x0041:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioPlayer.onViewCreated$lambda$4(org.videolan.vlc.gui.audio.AudioPlayer, android.view.View):void");
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$6(AudioPlayer audioPlayer, View view) {
        Intrinsics.checkNotNullParameter(audioPlayer, "this$0");
        audioPlayer.audioPlayProgressMode = !audioPlayer.audioPlayProgressMode;
        Settings settings2 = Settings.INSTANCE;
        FragmentActivity requireActivity = audioPlayer.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        SettingsKt.putSingle((SharedPreferences) settings2.getInstance(requireActivity), SettingsKt.AUDIO_PLAY_PROGRESS_MODE, Boolean.valueOf(audioPlayer.audioPlayProgressMode));
        PlaybackProgress value = audioPlayer.getPlaylistModel().getProgress().getValue();
        if (value != null) {
            audioPlayer.updateProgress(value);
        }
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$7(AudioPlayer audioPlayer, View view) {
        Intrinsics.checkNotNullParameter(audioPlayer, "this$0");
        PlaybackSpeedDialog.Companion.newInstance().show(audioPlayer.requireActivity().getSupportFragmentManager(), SettingsKt.KEY_PLAYBACK_SPEED_PERSIST);
    }

    /* access modifiers changed from: private */
    public static final boolean onViewCreated$lambda$8(AudioPlayer audioPlayer, View view) {
        Intrinsics.checkNotNullParameter(audioPlayer, "this$0");
        PlaybackService service = audioPlayer.getPlaylistModel().getService();
        if (service != null) {
            service.setRate(1.0f, true);
        }
        audioPlayer.showChips();
        return true;
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$9(AudioPlayer audioPlayer, View view) {
        Intrinsics.checkNotNullParameter(audioPlayer, "this$0");
        SleepTimerDialog.Companion.newInstance$default(SleepTimerDialog.Companion, false, 1, (Object) null).show(audioPlayer.requireActivity().getSupportFragmentManager(), RtspHeaders.Values.TIME);
    }

    /* access modifiers changed from: private */
    public static final boolean onViewCreated$lambda$10(AudioPlayer audioPlayer, View view) {
        Intrinsics.checkNotNullParameter(audioPlayer, "this$0");
        PlaybackService service = audioPlayer.getPlaylistModel().getService();
        if (service != null) {
            service.setSleepTimer((Calendar) null);
        }
        audioPlayer.showChips();
        return true;
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$11(AudioPlayer audioPlayer, View view) {
        Intrinsics.checkNotNullParameter(audioPlayer, "this$0");
        audioPlayer.coverMediaSwitcherListener.onTextClicked();
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$12(AudioPlayer audioPlayer, View view) {
        Intrinsics.checkNotNullParameter(audioPlayer, "this$0");
        audioPlayer.coverMediaSwitcherListener.onTextClicked();
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$13(AudioPlayer audioPlayer, View view) {
        Intrinsics.checkNotNullParameter(audioPlayer, "this$0");
        Settings settings2 = Settings.INSTANCE;
        FragmentActivity requireActivity = audioPlayer.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        SettingsKt.putSingle((SharedPreferences) settings2.getInstance(requireActivity), SettingsKt.AUDIO_HINGE_ON_RIGHT, false);
        audioPlayer.manageHinge();
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$14(AudioPlayer audioPlayer, View view) {
        Intrinsics.checkNotNullParameter(audioPlayer, "this$0");
        Settings settings2 = Settings.INSTANCE;
        FragmentActivity requireActivity = audioPlayer.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        SettingsKt.putSingle((SharedPreferences) settings2.getInstance(requireActivity), SettingsKt.AUDIO_HINGE_ON_RIGHT, true);
        audioPlayer.manageHinge();
    }

    public void onDestroy() {
        Settings.INSTANCE.removeAudioControlsChangeListener();
        AudioPlayerBinding audioPlayerBinding = this.binding;
        if (audioPlayerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding = null;
        }
        audioPlayerBinding.songsList.setAdapter((RecyclerView.Adapter) null);
        this.currentChapters = null;
        super.onDestroy();
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [android.view.ViewGroup$LayoutParams] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setBottomMargin() {
        /*
            r4 = this;
            org.videolan.vlc.databinding.AudioPlayerBinding r0 = r4.binding
            r1 = 0
            if (r0 != 0) goto L_0x000b
            java.lang.String r0 = "binding"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = r1
        L_0x000b:
            androidx.appcompat.widget.AppCompatImageView r0 = r0.playPause
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            boolean r2 = r0 instanceof androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
            if (r2 == 0) goto L_0x0018
            r1 = r0
            androidx.constraintlayout.widget.ConstraintLayout$LayoutParams r1 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r1
        L_0x0018:
            if (r1 == 0) goto L_0x0041
            androidx.fragment.app.FragmentActivity r0 = r4.requireActivity()
            java.lang.String r2 = "null cannot be cast to non-null type org.videolan.vlc.gui.AudioPlayerContainerActivity"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r2)
            org.videolan.vlc.gui.AudioPlayerContainerActivity r0 = (org.videolan.vlc.gui.AudioPlayerContainerActivity) r0
            boolean r2 = r0 instanceof org.videolan.vlc.gui.MainActivity
            if (r2 == 0) goto L_0x0041
            org.videolan.vlc.gui.helpers.UiTools r2 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            r3 = r0
            android.content.Context r3 = (android.content.Context) r3
            boolean r2 = r2.isTablet(r3)
            if (r2 != 0) goto L_0x0041
            r2 = 8
            int r2 = org.videolan.tools.KotlinExtensionsKt.getDp(r2)
            int r0 = r0.getBottomInset()
            int r2 = r2 + r0
            r1.bottomMargin = r2
        L_0x0041:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioPlayer.setBottomMargin():void");
    }

    public final boolean isTablet() {
        UiTools uiTools = UiTools.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        return uiTools.isTablet(requireActivity);
    }

    public final void showChips() {
        AudioPlayerBinding audioPlayerBinding = null;
        if (!Intrinsics.areEqual(getPlaylistModel().getSpeed().getValue(), 1.0f) || PlaybackService.Companion.getPlayerSleepTime().getValue() != null) {
            AudioPlayerBinding audioPlayerBinding2 = this.binding;
            if (audioPlayerBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding2 = null;
            }
            KotlinExtensionsKt.setVisible(audioPlayerBinding2.playbackChips);
            AudioPlayerBinding audioPlayerBinding3 = this.binding;
            if (audioPlayerBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding3 = null;
            }
            KotlinExtensionsKt.setGone(audioPlayerBinding3.playbackSpeedQuickAction);
            AudioPlayerBinding audioPlayerBinding4 = this.binding;
            if (audioPlayerBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding4 = null;
            }
            KotlinExtensionsKt.setGone(audioPlayerBinding4.sleepQuickAction);
            Float value = getPlaylistModel().getSpeed().getValue();
            if (value != null) {
                if (value.floatValue() != 1.0f) {
                    AudioPlayerBinding audioPlayerBinding5 = this.binding;
                    if (audioPlayerBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        audioPlayerBinding5 = null;
                    }
                    KotlinExtensionsKt.setVisible(audioPlayerBinding5.playbackSpeedQuickAction);
                }
                AudioPlayerBinding audioPlayerBinding6 = this.binding;
                if (audioPlayerBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    audioPlayerBinding6 = null;
                }
                audioPlayerBinding6.playbackSpeedQuickAction.setText(Strings.formatRateString(value.floatValue()));
            }
            Calendar value2 = PlaybackService.Companion.getPlayerSleepTime().getValue();
            if (value2 != null) {
                AudioPlayerBinding audioPlayerBinding7 = this.binding;
                if (audioPlayerBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    audioPlayerBinding7 = null;
                }
                KotlinExtensionsKt.setVisible(audioPlayerBinding7.sleepQuickAction);
                AudioPlayerBinding audioPlayerBinding8 = this.binding;
                if (audioPlayerBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    audioPlayerBinding = audioPlayerBinding8;
                }
                audioPlayerBinding.sleepQuickAction.setText(DateFormat.getTimeFormat(requireContext()).format(value2.getTime()));
                return;
            }
            return;
        }
        AudioPlayerBinding audioPlayerBinding9 = this.binding;
        if (audioPlayerBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            audioPlayerBinding = audioPlayerBinding9;
        }
        KotlinExtensionsKt.setGone(audioPlayerBinding.playbackChips);
    }

    public void onResume() {
        onStateChanged(this.playerState);
        Settings settings2 = Settings.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        this.showRemainingTime = ((SharedPreferences) settings2.getInstance(requireContext)).getBoolean(SettingsKt.SHOW_REMAINING_TIME, false);
        SharedPreferences sharedPreferences = this.settings;
        SharedPreferences sharedPreferences2 = null;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences = null;
        }
        int i = sharedPreferences.getInt(SettingsKt.PREF_RESTORE_VIDEO_TIPS_SHOWN, 0);
        SharedPreferences sharedPreferences3 = this.settings;
        if (sharedPreferences3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences3 = null;
        }
        boolean z = sharedPreferences3.getBoolean(SettingsKt.RESTORE_BACKGROUND_VIDEO, false);
        PlaybackService service = getPlaylistModel().getService();
        if (service != null && !service.isVideoPlaying() && service.getVideoTracksCount() > 0) {
            if (!z && i < 4) {
                UiTools uiTools = UiTools.INSTANCE;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                UiTools.snacker$default(uiTools, requireActivity, R.string.return_to_video, false, 4, (Object) null);
                SharedPreferences sharedPreferences4 = this.settings;
                if (sharedPreferences4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("settings");
                } else {
                    sharedPreferences2 = sharedPreferences4;
                }
                SettingsKt.putSingle(sharedPreferences2, SettingsKt.PREF_RESTORE_VIDEO_TIPS_SHOWN, Integer.valueOf(i + 1));
            } else if (z && !PlaylistManager.Companion.getPlayingAsAudio()) {
                onResumeToVideoClick();
            }
        }
        super.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        bundle.putInt("player_state", this.playerState);
        bundle.putBoolean("was_playing", this.wasPlaying);
        bundle.putBoolean(SettingsKt.SHOW_REMAINING_TIME, this.showRemainingTime);
    }

    /* access modifiers changed from: private */
    public final void showInfoDialog(MediaWrapper mediaWrapper) {
        Intent intent = new Intent(requireActivity(), InfoActivity.class);
        intent.putExtra("ML_ITEM", mediaWrapper);
        startActivity(intent);
    }

    public void onPopupMenu(View view, int i, MediaWrapper mediaWrapper) {
        Uri uri;
        Intrinsics.checkNotNullParameter(view, "view");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            PlaylistAdapter playlistAdapter2 = this.playlistAdapter;
            String str = null;
            if (playlistAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playlistAdapter");
                playlistAdapter2 = null;
            }
            if (i < playlistAdapter2.getItemCount()) {
                FlagSet flagSet = new FlagSet(ContextOption.class);
                flagSet.addAll((Enum[]) new ContextOption[]{ContextOption.CTX_GO_TO_FOLDER, ContextOption.CTX_INFORMATION, ContextOption.CTX_REMOVE_FROM_PLAYLIST, ContextOption.CTX_STOP_AFTER_THIS});
                if (!(mediaWrapper == null || (uri = mediaWrapper.getUri()) == null)) {
                    str = uri.getScheme();
                }
                if (!Intrinsics.areEqual((Object) str, (Object) "content")) {
                    flagSet.addAll((Enum[]) new ContextOption[]{ContextOption.CTX_ADD_TO_PLAYLIST, ContextOption.CTX_SET_RINGTONE, ContextOption.CTX_SHARE});
                }
                ContextSheetKt.showContext(activity, this.ctxReceiver, i, mediaWrapper, flagSet);
            }
        }
    }

    public Lifecycle getLifeCycle() {
        Lifecycle lifecycle = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
        return lifecycle;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x01c0  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01c9  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0214  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x021d  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0224  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x022d  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0234  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x023d  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0267  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0270  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0280  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0289  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0290  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02a9  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x02c2  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x02e5  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0312 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d5 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0145  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x014a  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0177  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x019b  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01a4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object doUpdate(kotlin.coroutines.Continuation<? super kotlin.Unit> r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            boolean r2 = r1 instanceof org.videolan.vlc.gui.audio.AudioPlayer$doUpdate$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            org.videolan.vlc.gui.audio.AudioPlayer$doUpdate$1 r2 = (org.videolan.vlc.gui.audio.AudioPlayer$doUpdate$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            org.videolan.vlc.gui.audio.AudioPlayer$doUpdate$1 r2 = new org.videolan.vlc.gui.audio.AudioPlayer$doUpdate$1
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 5
            r6 = 4
            r7 = 3
            r8 = 8
            r9 = 2
            r10 = 0
            r11 = 1
            java.lang.String r12 = "binding"
            if (r4 == 0) goto L_0x006e
            if (r4 == r11) goto L_0x0066
            if (r4 == r9) goto L_0x005e
            if (r4 == r7) goto L_0x0055
            if (r4 == r6) goto L_0x0048
            if (r4 != r5) goto L_0x0040
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x0313
        L_0x0040:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0048:
            java.lang.Object r4 = r2.L$1
            android.widget.TextView r4 = (android.widget.TextView) r4
            java.lang.Object r6 = r2.L$0
            org.videolan.vlc.gui.audio.AudioPlayer r6 = (org.videolan.vlc.gui.audio.AudioPlayer) r6
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x0257
        L_0x0055:
            java.lang.Object r4 = r2.L$0
            org.videolan.vlc.gui.audio.AudioPlayer r4 = (org.videolan.vlc.gui.audio.AudioPlayer) r4
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00d6
        L_0x005e:
            java.lang.Object r4 = r2.L$0
            org.videolan.vlc.gui.audio.AudioPlayer r4 = (org.videolan.vlc.gui.audio.AudioPlayer) r4
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00b9
        L_0x0066:
            java.lang.Object r4 = r2.L$0
            org.videolan.vlc.gui.audio.AudioPlayer r4 = (org.videolan.vlc.gui.audio.AudioPlayer) r4
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x0087
        L_0x006e:
            kotlin.ResultKt.throwOnFailure(r1)
            boolean r1 = r16.isVisible()
            if (r1 == 0) goto L_0x0092
            org.videolan.vlc.viewmodels.PlaylistModel r1 = r16.getPlaylistModel()
            r2.L$0 = r0
            r2.label = r11
            java.lang.Object r1 = r1.switchToVideo(r2)
            if (r1 != r3) goto L_0x0086
            return r3
        L_0x0086:
            r4 = r0
        L_0x0087:
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0093
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x0092:
            r4 = r0
        L_0x0093:
            r4.updatePlayPause()
            r4.updateShuffleMode()
            r4.updateRepeatMode()
            org.videolan.vlc.databinding.AudioPlayerBinding r1 = r4.binding
            if (r1 != 0) goto L_0x00a4
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r1 = 0
        L_0x00a4:
            org.videolan.vlc.gui.view.HeaderMediaSwitcher r1 = r1.audioMediaSwitcher
            org.videolan.vlc.viewmodels.PlaylistModel r14 = r4.getPlaylistModel()
            org.videolan.vlc.PlaybackService r14 = r14.getService()
            r2.L$0 = r4
            r2.label = r9
            java.lang.Object r1 = r1.updateMedia(r14, r2)
            if (r1 != r3) goto L_0x00b9
            return r3
        L_0x00b9:
            org.videolan.vlc.databinding.AudioPlayerBinding r1 = r4.binding
            if (r1 != 0) goto L_0x00c1
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r1 = 0
        L_0x00c1:
            org.videolan.vlc.gui.view.CoverMediaSwitcher r1 = r1.coverMediaSwitcher
            org.videolan.vlc.viewmodels.PlaylistModel r14 = r4.getPlaylistModel()
            org.videolan.vlc.PlaybackService r14 = r14.getService()
            r2.L$0 = r4
            r2.label = r7
            java.lang.Object r1 = r1.updateMedia(r14, r2)
            if (r1 != r3) goto L_0x00d6
            return r3
        L_0x00d6:
            org.videolan.vlc.viewmodels.PlaylistModel r1 = r4.getPlaylistModel()
            org.videolan.vlc.PlaybackService r1 = r1.getService()
            if (r1 == 0) goto L_0x013b
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r1.getCurrentMediaWrapper()
            if (r1 == 0) goto L_0x013b
            org.videolan.vlc.databinding.AudioPlayerBinding r7 = r4.binding
            if (r7 != 0) goto L_0x00ee
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r7 = 0
        L_0x00ee:
            org.videolan.vlc.gui.view.HeaderMediaSwitcher r7 = r7.audioMediaSwitcher
            int r14 = org.videolan.vlc.R.string.talkback_audio_player
            org.videolan.vlc.gui.helpers.TalkbackUtil r15 = org.videolan.vlc.gui.helpers.TalkbackUtil.INSTANCE
            androidx.fragment.app.FragmentActivity r5 = r4.requireActivity()
            java.lang.String r13 = "requireActivity(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r13)
            android.content.Context r5 = (android.content.Context) r5
            java.lang.String r5 = r15.getAudioTrack(r5, r1)
            java.lang.Object[] r15 = new java.lang.Object[r11]
            r15[r10] = r5
            java.lang.String r5 = r4.getString(r14, r15)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r7.setContentDescription(r5)
            org.videolan.vlc.databinding.AudioPlayerBinding r5 = r4.binding
            if (r5 != 0) goto L_0x0118
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r5 = 0
        L_0x0118:
            androidx.constraintlayout.widget.ConstraintLayout r5 = r5.trackInfoContainer
            if (r5 != 0) goto L_0x011d
            goto L_0x013b
        L_0x011d:
            int r7 = org.videolan.vlc.R.string.talkback_audio_player
            org.videolan.vlc.gui.helpers.TalkbackUtil r14 = org.videolan.vlc.gui.helpers.TalkbackUtil.INSTANCE
            androidx.fragment.app.FragmentActivity r15 = r4.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r13)
            android.content.Context r15 = (android.content.Context) r15
            java.lang.String r1 = r14.getAudioTrack(r15, r1)
            java.lang.Object[] r13 = new java.lang.Object[r11]
            r13[r10] = r1
            java.lang.String r1 = r4.getString(r7, r13)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r5.setContentDescription(r1)
        L_0x013b:
            org.videolan.vlc.viewmodels.PlaylistModel r1 = r4.getPlaylistModel()
            org.videolan.vlc.PlaybackService r1 = r1.getService()
            if (r1 == 0) goto L_0x014a
            java.lang.String r1 = r1.getCurrentChapter()
            goto L_0x014b
        L_0x014a:
            r1 = 0
        L_0x014b:
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            if (r1 == 0) goto L_0x0177
            int r5 = r1.length()
            if (r5 != 0) goto L_0x0156
            goto L_0x0177
        L_0x0156:
            org.videolan.vlc.databinding.AudioPlayerBinding r5 = r4.binding
            if (r5 != 0) goto L_0x015e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r5 = 0
        L_0x015e:
            android.widget.ImageView r5 = r5.nextChapter
            if (r5 != 0) goto L_0x0163
            goto L_0x0166
        L_0x0163:
            r5.setVisibility(r10)
        L_0x0166:
            org.videolan.vlc.databinding.AudioPlayerBinding r5 = r4.binding
            if (r5 != 0) goto L_0x016e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r5 = 0
        L_0x016e:
            android.widget.ImageView r5 = r5.previousChapter
            if (r5 != 0) goto L_0x0173
            goto L_0x0197
        L_0x0173:
            r5.setVisibility(r10)
            goto L_0x0197
        L_0x0177:
            org.videolan.vlc.databinding.AudioPlayerBinding r5 = r4.binding
            if (r5 != 0) goto L_0x017f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r5 = 0
        L_0x017f:
            android.widget.ImageView r5 = r5.nextChapter
            if (r5 != 0) goto L_0x0184
            goto L_0x0187
        L_0x0184:
            r5.setVisibility(r8)
        L_0x0187:
            org.videolan.vlc.databinding.AudioPlayerBinding r5 = r4.binding
            if (r5 != 0) goto L_0x018f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r5 = 0
        L_0x018f:
            android.widget.ImageView r5 = r5.previousChapter
            if (r5 != 0) goto L_0x0194
            goto L_0x0197
        L_0x0194:
            r5.setVisibility(r8)
        L_0x0197:
            org.videolan.vlc.databinding.AudioPlayerBinding r5 = r4.binding
            if (r5 != 0) goto L_0x019f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r5 = 0
        L_0x019f:
            android.widget.TextView r5 = r5.songTitle
            if (r5 != 0) goto L_0x01a4
            goto L_0x01bc
        L_0x01a4:
            if (r1 == 0) goto L_0x01af
            int r7 = r1.length()
            if (r7 != 0) goto L_0x01ad
            goto L_0x01af
        L_0x01ad:
            r7 = r1
            goto L_0x01b9
        L_0x01af:
            org.videolan.vlc.viewmodels.PlaylistModel r7 = r4.getPlaylistModel()
            java.lang.String r7 = r7.getTitle()
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
        L_0x01b9:
            r5.setText(r7)
        L_0x01bc:
            org.videolan.vlc.databinding.AudioPlayerBinding r5 = r4.binding
            if (r5 != 0) goto L_0x01c4
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r5 = 0
        L_0x01c4:
            android.widget.TextView r5 = r5.songSubtitle
            if (r5 != 0) goto L_0x01c9
            goto L_0x0210
        L_0x01c9:
            if (r1 == 0) goto L_0x01ef
            int r1 = r1.length()
            if (r1 != 0) goto L_0x01d2
            goto L_0x01ef
        L_0x01d2:
            org.videolan.vlc.util.TextUtils r1 = org.videolan.vlc.util.TextUtils.INSTANCE
            org.videolan.vlc.viewmodels.PlaylistModel r7 = r4.getPlaylistModel()
            java.lang.String r7 = r7.getTitle()
            org.videolan.vlc.viewmodels.PlaylistModel r13 = r4.getPlaylistModel()
            java.lang.String r13 = r13.getArtist()
            java.lang.String[] r9 = new java.lang.String[r9]
            r9[r10] = r7
            r9[r11] = r13
            java.lang.String r1 = r1.separatedStringArgs(r9)
            goto L_0x020b
        L_0x01ef:
            org.videolan.vlc.util.TextUtils r1 = org.videolan.vlc.util.TextUtils.INSTANCE
            org.videolan.vlc.viewmodels.PlaylistModel r7 = r4.getPlaylistModel()
            java.lang.String r7 = r7.getArtist()
            org.videolan.vlc.viewmodels.PlaylistModel r13 = r4.getPlaylistModel()
            java.lang.String r13 = r13.getAlbum()
            java.lang.String[] r9 = new java.lang.String[r9]
            r9[r10] = r7
            r9[r11] = r13
            java.lang.String r1 = r1.separatedStringArgs(r9)
        L_0x020b:
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r5.setText(r1)
        L_0x0210:
            org.videolan.vlc.databinding.AudioPlayerBinding r1 = r4.binding
            if (r1 != 0) goto L_0x0218
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r1 = 0
        L_0x0218:
            android.widget.TextView r1 = r1.songTitle
            if (r1 != 0) goto L_0x021d
            goto L_0x0220
        L_0x021d:
            r1.setSelected(r11)
        L_0x0220:
            org.videolan.vlc.databinding.AudioPlayerBinding r1 = r4.binding
            if (r1 != 0) goto L_0x0228
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r1 = 0
        L_0x0228:
            android.widget.TextView r1 = r1.songSubtitle
            if (r1 != 0) goto L_0x022d
            goto L_0x0230
        L_0x022d:
            r1.setSelected(r11)
        L_0x0230:
            org.videolan.vlc.databinding.AudioPlayerBinding r1 = r4.binding
            if (r1 != 0) goto L_0x0238
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r1 = 0
        L_0x0238:
            android.widget.TextView r1 = r1.songTrackInfo
            if (r1 != 0) goto L_0x023d
            goto L_0x0263
        L_0x023d:
            org.videolan.vlc.viewmodels.PlaylistModel r5 = r4.getPlaylistModel()
            org.videolan.vlc.PlaybackService r5 = r5.getService()
            if (r5 == 0) goto L_0x025a
            r2.L$0 = r4
            r2.L$1 = r1
            r2.label = r6
            java.lang.Object r5 = r5.trackInfo(r2)
            if (r5 != r3) goto L_0x0254
            return r3
        L_0x0254:
            r6 = r4
            r4 = r1
            r1 = r5
        L_0x0257:
            java.lang.String r1 = (java.lang.String) r1
            goto L_0x025d
        L_0x025a:
            r6 = r4
            r4 = r1
            r1 = 0
        L_0x025d:
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r4.setText(r1)
            r4 = r6
        L_0x0263:
            org.videolan.vlc.databinding.AudioPlayerBinding r1 = r4.binding
            if (r1 != 0) goto L_0x026b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r1 = 0
        L_0x026b:
            android.widget.TextView r1 = r1.songTrackInfo
            if (r1 != 0) goto L_0x0270
            goto L_0x027c
        L_0x0270:
            org.videolan.tools.Settings r5 = org.videolan.tools.Settings.INSTANCE
            boolean r5 = r5.getShowAudioTrackInfo()
            if (r5 == 0) goto L_0x0279
            r8 = 0
        L_0x0279:
            r1.setVisibility(r8)
        L_0x027c:
            org.videolan.vlc.databinding.AudioPlayerBinding r1 = r4.binding
            if (r1 != 0) goto L_0x0284
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r1 = 0
        L_0x0284:
            android.widget.TextView r1 = r1.songTrackInfo
            if (r1 != 0) goto L_0x0289
            goto L_0x028c
        L_0x0289:
            r1.setSelected(r11)
        L_0x028c:
            org.videolan.vlc.databinding.AudioPlayerBinding r1 = r4.binding
            if (r1 != 0) goto L_0x0294
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r1 = 0
        L_0x0294:
            android.widget.TextView r1 = r1.audioRewindText
            org.videolan.tools.Settings r5 = org.videolan.tools.Settings.INSTANCE
            int r5 = r5.getAudioJumpDelay()
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r1.setText(r5)
            org.videolan.vlc.databinding.AudioPlayerBinding r1 = r4.binding
            if (r1 != 0) goto L_0x02ad
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r1 = 0
        L_0x02ad:
            android.widget.TextView r1 = r1.audioForwardText
            org.videolan.tools.Settings r5 = org.videolan.tools.Settings.INSTANCE
            int r5 = r5.getAudioJumpDelay()
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r1.setText(r5)
            org.videolan.vlc.databinding.AudioPlayerBinding r1 = r4.binding
            if (r1 != 0) goto L_0x02c6
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r1 = 0
        L_0x02c6:
            android.widget.ImageView r1 = r1.audioForward10
            int r5 = org.videolan.vlc.R.string.talkback_action_forward
            org.videolan.tools.Settings r6 = org.videolan.tools.Settings.INSTANCE
            int r6 = r6.getAudioJumpDelay()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.Object[] r7 = new java.lang.Object[r11]
            r7[r10] = r6
            java.lang.String r5 = r4.getString(r5, r7)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r1.setContentDescription(r5)
            org.videolan.vlc.databinding.AudioPlayerBinding r1 = r4.binding
            if (r1 != 0) goto L_0x02e9
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r12)
            r1 = 0
        L_0x02e9:
            android.widget.ImageView r1 = r1.audioRewind10
            int r5 = org.videolan.vlc.R.string.talkback_action_rewind
            org.videolan.tools.Settings r6 = org.videolan.tools.Settings.INSTANCE
            int r6 = r6.getAudioJumpDelay()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.Object[] r7 = new java.lang.Object[r11]
            r7[r10] = r6
            java.lang.String r5 = r4.getString(r5, r7)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r1.setContentDescription(r5)
            r1 = 0
            r2.L$0 = r1
            r2.L$1 = r1
            r1 = 5
            r2.label = r1
            java.lang.Object r1 = r4.updateBackground(r2)
            if (r1 != r3) goto L_0x0313
            return r3
        L_0x0313:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioPlayer.doUpdate(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void updatePlayPause() {
        /*
            r9 = this;
            android.content.Context r0 = r9.getContext()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            org.videolan.vlc.viewmodels.PlaylistModel r1 = r9.getPlaylistModel()
            boolean r1 = r1.getPlaying()
            if (r1 == 0) goto L_0x0014
            int r2 = org.videolan.vlc.R.string.pause
            goto L_0x0016
        L_0x0014:
            int r2 = org.videolan.vlc.R.string.play
        L_0x0016:
            java.lang.String r0 = r0.getString(r2)
            java.lang.String r2 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            r2 = 0
            if (r1 == 0) goto L_0x0029
            androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat r3 = r9.playToPause
            if (r3 != 0) goto L_0x0033
            java.lang.String r3 = "playToPause"
            goto L_0x002f
        L_0x0029:
            androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat r3 = r9.pauseToPlay
            if (r3 != 0) goto L_0x0033
            java.lang.String r3 = "pauseToPlay"
        L_0x002f:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r3 = r2
        L_0x0033:
            if (r1 == 0) goto L_0x003c
            androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat r4 = r9.playToPauseSmall
            if (r4 != 0) goto L_0x0046
            java.lang.String r4 = "playToPauseSmall"
            goto L_0x0042
        L_0x003c:
            androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat r4 = r9.pauseToPlaySmall
            if (r4 != 0) goto L_0x0046
            java.lang.String r4 = "pauseToPlaySmall"
        L_0x0042:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r4 = r2
        L_0x0046:
            if (r1 == 0) goto L_0x004f
            androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat r5 = r9.playToPauseHeader
            if (r5 != 0) goto L_0x0059
            java.lang.String r5 = "playToPauseHeader"
            goto L_0x0055
        L_0x004f:
            androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat r5 = r9.pauseToPlayHeader
            if (r5 != 0) goto L_0x0059
            java.lang.String r5 = "pauseToPlayHeader"
        L_0x0055:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r5 = r2
        L_0x0059:
            org.videolan.vlc.databinding.AudioPlayerBinding r6 = r9.binding
            java.lang.String r7 = "binding"
            if (r6 != 0) goto L_0x0063
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r6 = r2
        L_0x0063:
            androidx.appcompat.widget.AppCompatImageView r6 = r6.playPause
            r8 = r3
            android.graphics.drawable.Drawable r8 = (android.graphics.drawable.Drawable) r8
            r6.setImageDrawable(r8)
            org.videolan.vlc.databinding.AudioPlayerBinding r6 = r9.binding
            if (r6 != 0) goto L_0x0073
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r6 = r2
        L_0x0073:
            androidx.appcompat.widget.AppCompatImageView r6 = r6.headerLargePlayPause
            r8 = r5
            android.graphics.drawable.Drawable r8 = (android.graphics.drawable.Drawable) r8
            r6.setImageDrawable(r8)
            org.videolan.vlc.databinding.AudioPlayerBinding r6 = r9.binding
            if (r6 != 0) goto L_0x0083
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r6 = r2
        L_0x0083:
            android.widget.ImageView r6 = r6.headerPlayPause
            r8 = r4
            android.graphics.drawable.Drawable r8 = (android.graphics.drawable.Drawable) r8
            r6.setImageDrawable(r8)
            boolean r6 = r9.wasPlaying
            if (r1 == r6) goto L_0x0098
            r3.start()
            r4.start()
            r5.start()
        L_0x0098:
            org.videolan.vlc.gui.audio.PlaylistAdapter r3 = r9.playlistAdapter
            if (r3 != 0) goto L_0x00a2
            java.lang.String r3 = "playlistAdapter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r3 = r2
        L_0x00a2:
            r3.setCurrentlyPlaying(r1)
            org.videolan.vlc.databinding.AudioPlayerBinding r3 = r9.binding
            if (r3 != 0) goto L_0x00ad
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r3 = r2
        L_0x00ad:
            androidx.appcompat.widget.AppCompatImageView r3 = r3.playPause
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r3.setContentDescription(r0)
            org.videolan.vlc.databinding.AudioPlayerBinding r3 = r9.binding
            if (r3 != 0) goto L_0x00bc
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            goto L_0x00bd
        L_0x00bc:
            r2 = r3
        L_0x00bd:
            android.widget.ImageView r2 = r2.headerPlayPause
            r2.setContentDescription(r0)
            r9.wasPlaying = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioPlayer.updatePlayPause():void");
    }

    private final void updateShuffleMode() {
        Context context = getContext();
        if (context != null) {
            AudioPlayerBinding audioPlayerBinding = this.binding;
            AudioPlayerBinding audioPlayerBinding2 = null;
            if (audioPlayerBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding = null;
            }
            ImageView imageView = audioPlayerBinding.shuffle;
            AudioPlayerBinding audioPlayerBinding3 = this.binding;
            if (audioPlayerBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding3 = null;
            }
            ImageView[] imageViewArr = {imageView, audioPlayerBinding3.headerShuffle};
            AudioPlayerBinding audioPlayerBinding4 = this.binding;
            if (audioPlayerBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                audioPlayerBinding2 = audioPlayerBinding4;
            }
            audioPlayerBinding2.setCanShuffle(Boolean.valueOf(getPlaylistModel().getCanShuffle()));
            boolean shuffling = getPlaylistModel().getShuffling();
            if (this.wasShuffling != shuffling) {
                for (int i = 0; i < 2; i++) {
                    ImageView imageView2 = imageViewArr[i];
                    imageView2.setImageResource(shuffling ? R.drawable.ic_shuffle_on : R.drawable.ic_shuffle_audio);
                    imageView2.setContentDescription(context.getString(shuffling ? R.string.shuffle_on : R.string.shuffle));
                }
                this.wasShuffling = shuffling;
            }
        }
    }

    /* access modifiers changed from: private */
    public final void updateRepeatMode() {
        int repeatType;
        Context context = getContext();
        if (context != null && this.previousRepeatType != (repeatType = getPlaylistModel().getRepeatType())) {
            int i = 0;
            AudioPlayerBinding audioPlayerBinding = null;
            if (repeatType == 1) {
                AudioPlayerBinding audioPlayerBinding2 = this.binding;
                if (audioPlayerBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    audioPlayerBinding2 = null;
                }
                ImageView imageView = audioPlayerBinding2.repeat;
                AudioPlayerBinding audioPlayerBinding3 = this.binding;
                if (audioPlayerBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    audioPlayerBinding = audioPlayerBinding3;
                }
                ImageView[] imageViewArr = {imageView, audioPlayerBinding.headerRepeat};
                while (i < 2) {
                    ImageView imageView2 = imageViewArr[i];
                    imageView2.setImageResource(R.drawable.ic_repeat_one_audio);
                    imageView2.setContentDescription(context.getString(R.string.repeat_single));
                    i++;
                }
            } else if (repeatType != 2) {
                AudioPlayerBinding audioPlayerBinding4 = this.binding;
                if (audioPlayerBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    audioPlayerBinding4 = null;
                }
                ImageView imageView3 = audioPlayerBinding4.repeat;
                AudioPlayerBinding audioPlayerBinding5 = this.binding;
                if (audioPlayerBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    audioPlayerBinding = audioPlayerBinding5;
                }
                ImageView[] imageViewArr2 = {imageView3, audioPlayerBinding.headerRepeat};
                while (i < 2) {
                    ImageView imageView4 = imageViewArr2[i];
                    imageView4.setImageResource(R.drawable.ic_repeat_audio);
                    imageView4.setContentDescription(context.getString(R.string.repeat_none));
                    i++;
                }
            } else {
                AudioPlayerBinding audioPlayerBinding6 = this.binding;
                if (audioPlayerBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    audioPlayerBinding6 = null;
                }
                ImageView imageView5 = audioPlayerBinding6.repeat;
                AudioPlayerBinding audioPlayerBinding7 = this.binding;
                if (audioPlayerBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    audioPlayerBinding = audioPlayerBinding7;
                }
                ImageView[] imageViewArr3 = {imageView5, audioPlayerBinding.headerRepeat};
                while (i < 2) {
                    ImageView imageView6 = imageViewArr3[i];
                    imageView6.setImageResource(R.drawable.ic_repeat_all_audio);
                    imageView6.setContentDescription(context.getString(R.string.repeat_all));
                    i++;
                }
            }
            this.previousRepeatType = repeatType;
        }
    }

    /* access modifiers changed from: private */
    public final void updateProgress(PlaybackProgress playbackProgress) {
        if (getPlaylistModel().getCurrentMediaPosition() != -1) {
            AudioPlayerBinding audioPlayerBinding = this.binding;
            if (audioPlayerBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding = null;
            }
            audioPlayerBinding.length.setText(this.showRemainingTime ? Tools.millisToString(playbackProgress.getTime() - playbackProgress.getLength()) : playbackProgress.getLengthText());
            AudioPlayerBinding audioPlayerBinding2 = this.binding;
            if (audioPlayerBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding2 = null;
            }
            audioPlayerBinding2.timeline.setMax((int) playbackProgress.getLength());
            AudioPlayerBinding audioPlayerBinding3 = this.binding;
            if (audioPlayerBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding3 = null;
            }
            audioPlayerBinding3.progressBar.setMax((int) playbackProgress.getLength());
            if (!this.previewingSeek) {
                String timeText = playbackProgress.getTimeText();
                AudioPlayerBinding audioPlayerBinding4 = this.binding;
                if (audioPlayerBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    audioPlayerBinding4 = null;
                }
                audioPlayerBinding4.headerTime.setText(this.showRemainingTime ? Tools.millisToString(playbackProgress.getTime() - playbackProgress.getLength()) : timeText);
                AudioPlayerBinding audioPlayerBinding5 = this.binding;
                if (audioPlayerBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    audioPlayerBinding5 = null;
                }
                audioPlayerBinding5.time.setText(timeText);
                if (!this.isDragging) {
                    AudioPlayerBinding audioPlayerBinding6 = this.binding;
                    if (audioPlayerBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        audioPlayerBinding6 = null;
                    }
                    audioPlayerBinding6.timeline.setProgress((int) playbackProgress.getTime());
                }
                AudioPlayerBinding audioPlayerBinding7 = this.binding;
                if (audioPlayerBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    audioPlayerBinding7 = null;
                }
                audioPlayerBinding7.progressBar.setProgress((int) playbackProgress.getTime());
            }
            LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new AudioPlayer$updateProgress$1(this, playbackProgress, (Continuation<? super AudioPlayer$updateProgress$1>) null));
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0012, code lost:
        r0 = getPlaylistModel().getMedias();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean shouldHidePlayProgress() {
        /*
            r2 = this;
            android.widget.Button r0 = r2.getAbRepeatAddMarker()
            int r0 = r0.getVisibility()
            r1 = 8
            if (r0 != r1) goto L_0x0026
            boolean r0 = r2.areBookmarksVisible()
            if (r0 != 0) goto L_0x0026
            org.videolan.vlc.viewmodels.PlaylistModel r0 = r2.getPlaylistModel()
            java.util.List r0 = r0.getMedias()
            if (r0 == 0) goto L_0x0026
            int r0 = r0.size()
            r1 = 2
            if (r0 >= r1) goto L_0x0024
            goto L_0x0026
        L_0x0024:
            r0 = 0
            goto L_0x0027
        L_0x0026:
            r0 = 1
        L_0x0027:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioPlayer.shouldHidePlayProgress():boolean");
    }

    public void onSelectionSet(int i) {
        AudioPlayerBinding audioPlayerBinding = this.binding;
        if (audioPlayerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding = null;
        }
        audioPlayerBinding.songsList.scrollToPosition(i);
    }

    public void playItem(int i, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "item");
        clearSearch();
        getPlaylistModel().play(getPlaylistModel().getPlaylistPosition(i, mediaWrapper));
    }

    public final void onTimeLabelClick(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.showRemainingTime = !this.showRemainingTime;
        Settings settings2 = Settings.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        ((SharedPreferences) settings2.getInstance(requireContext)).edit().putBoolean(SettingsKt.SHOW_REMAINING_TIME, this.showRemainingTime).apply();
        PlaybackProgress value = getPlaylistModel().getProgress().getValue();
        if (value != null) {
            updateProgress(value);
        }
    }

    public final void onJumpBack(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        jump(false, false);
    }

    public final boolean onJumpBackLong(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        jump(false, true);
        return true;
    }

    public final void onJumpForward(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        jump(true, false);
    }

    public final boolean onJumpForwardLong(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        jump(true, true);
        return true;
    }

    private final void jump(boolean z, boolean z2) {
        PlaybackService service = getPlaylistModel().getService();
        if (service != null) {
            Settings settings2 = Settings.INSTANCE;
            int audioLongJumpDelay = (z2 ? settings2.getAudioLongJumpDelay() : settings2.getAudioJumpDelay()) * 1000;
            if (!z) {
                audioLongJumpDelay = -audioLongJumpDelay;
            }
            long time = service.getTime() + ((long) audioLongJumpDelay);
            if (time < 0) {
                time = 0;
            }
            if (time > service.getLength()) {
                time = service.getLength();
            }
            long j = time;
            PlaybackService.seek$default(service, j, (double) service.getLength(), true, false, 8, (Object) null);
            PlayerController.updateProgress$default(service.getPlaylistManager().getPlayer(), j, 0, 2, (Object) null);
            if (service.getPlaylistManager().getPlayer().getLastPosition() == 0.0f && (z || service.getTime() > 0)) {
                UiTools uiTools = UiTools.INSTANCE;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                String string = getString(R.string.unseekable_stream);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                uiTools.snacker(requireActivity, string);
                return;
            }
        }
    }

    public final void onPlayPauseClick(View view) {
        PlaybackService service = getPlaylistModel().getService();
        if (service == null || service.isPausable()) {
            getPlaylistModel().togglePlayPause();
            return;
        }
        UiTools uiTools = UiTools.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        String string = getString(R.string.stop_unpaubale);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UiTools.snackerConfirm$default(uiTools, requireActivity, string, true, 0, new AudioPlayer$onPlayPauseClick$1(this), 8, (Object) null);
    }

    public final boolean onStopClick(View view) {
        getPlaylistModel().stop();
        if (!(getActivity() instanceof AudioPlayerContainerActivity)) {
            return true;
        }
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type org.videolan.vlc.gui.AudioPlayerContainerActivity");
        ((AudioPlayerContainerActivity) activity).closeMiniPlayer();
        return true;
    }

    public final void onNextClick(View view) {
        if (!getPlaylistModel().next()) {
            UiTools uiTools = UiTools.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            uiTools.snacker(requireActivity, R.string.lastsong, true);
        }
    }

    public final void onPreviousClick(View view) {
        if (!PlaylistModel.previous$default(getPlaylistModel(), false, 1, (Object) null)) {
            UiTools uiTools = UiTools.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            UiTools.snacker$default(uiTools, requireActivity, R.string.firstsong, false, 4, (Object) null);
        }
    }

    public final void onRepeatClick(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        PlaylistModel playlistModel2 = getPlaylistModel();
        int repeatType = getPlaylistModel().getRepeatType();
        int i = 2;
        if (repeatType != 0) {
            i = repeatType != 2 ? 0 : 1;
        }
        playlistModel2.setRepeatType(i);
        updateRepeatMode();
    }

    public final void onPlaylistSwitchClick(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        switchShowCover();
        SharedPreferences sharedPreferences = this.settings;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences = null;
        }
        SettingsKt.putSingle(sharedPreferences, "audio_player_show_cover", Boolean.valueOf(isShowingCover()));
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new AudioPlayer$onPlaylistSwitchClick$1(this, (Continuation<? super AudioPlayer$onPlaylistSwitchClick$1>) null), 3, (Object) null);
    }

    public final void onShuffleClick(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        getPlaylistModel().shuffle();
        updateShuffleMode();
    }

    public final void onResumeToVideoClick() {
        MediaWrapper currentMediaWrapper = getPlaylistModel().getCurrentMediaWrapper();
        if (currentMediaWrapper == null) {
            return;
        }
        if (PlaybackService.Companion.hasRenderer()) {
            VideoPlayerActivity.Companion companion = VideoPlayerActivity.Companion;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            Uri uri = currentMediaWrapper.getUri();
            Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
            companion.startOpened(requireActivity, uri, getPlaylistModel().getCurrentMediaPosition());
        } else if (PlaylistManager.Companion.hasMedia()) {
            currentMediaWrapper.removeFlags(8);
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new AudioPlayer$onResumeToVideoClick$1$1(this, (Continuation<? super AudioPlayer$onResumeToVideoClick$1$1>) null), 1, (Object) null);
        }
    }

    public final void showAdvancedOptions(View view) {
        if (isVisible()) {
            PlayerOptionsDelegate playerOptionsDelegate = null;
            if (this.optionsDelegate == null) {
                PlaybackService service = getPlaylistModel().getService();
                if (service != null) {
                    FragmentActivity activity = getActivity();
                    AppCompatActivity appCompatActivity = activity instanceof AppCompatActivity ? (AppCompatActivity) activity : null;
                    if (appCompatActivity != null) {
                        PlayerOptionsDelegate playerOptionsDelegate2 = new PlayerOptionsDelegate(appCompatActivity, service, false, 4, (DefaultConstructorMarker) null);
                        this.optionsDelegate = playerOptionsDelegate2;
                        playerOptionsDelegate2.setBookmarkClickedListener(new AudioPlayer$showAdvancedOptions$1(this, appCompatActivity));
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            PlayerOptionsDelegate playerOptionsDelegate3 = this.optionsDelegate;
            if (playerOptionsDelegate3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("optionsDelegate");
            } else {
                playerOptionsDelegate = playerOptionsDelegate3;
            }
            playerOptionsDelegate.show();
        }
    }

    public final void showBookmarks() {
        PlaybackService service = getPlaylistModel().getService();
        if (service != null) {
            AudioPlayerBinding audioPlayerBinding = null;
            if (this.bookmarkListDelegate == null) {
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                setBookmarkListDelegate(new BookmarkListDelegate(requireActivity, service, getBookmarkModel()));
                getBookmarkListDelegate().setVisibilityListener(new AudioPlayer$showBookmarks$1(this));
                BookmarkListDelegate bookmarkListDelegate2 = getBookmarkListDelegate();
                AudioPlayerBinding audioPlayerBinding2 = this.binding;
                if (audioPlayerBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    audioPlayerBinding2 = null;
                }
                ConstraintLayout constraintLayout = audioPlayerBinding2.bookmarkMarkerContainer;
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "bookmarkMarkerContainer");
                bookmarkListDelegate2.setMarkerContainer(constraintLayout);
            }
            getBookmarkListDelegate().show();
            BookmarkListDelegate bookmarkListDelegate3 = getBookmarkListDelegate();
            AudioPlayerBinding audioPlayerBinding3 = this.binding;
            if (audioPlayerBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                audioPlayerBinding = audioPlayerBinding3;
            }
            bookmarkListDelegate3.setProgressHeight(audioPlayerBinding.time.getY());
        }
    }

    public final void onSearchClick(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        AudioPlayerBinding audioPlayerBinding = null;
        if (isShowingCover()) {
            AudioPlayerBinding audioPlayerBinding2 = this.binding;
            if (audioPlayerBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding2 = null;
            }
            ImageView imageView = audioPlayerBinding2.playlistSwitch;
            Intrinsics.checkNotNullExpressionValue(imageView, "playlistSwitch");
            onPlaylistSwitchClick(imageView);
        }
        manageSearchVisibilities(true);
        AudioPlayerBinding audioPlayerBinding3 = this.binding;
        if (audioPlayerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding3 = null;
        }
        EditText editText = audioPlayerBinding3.playlistSearchText.getEditText();
        if (editText != null) {
            editText.requestFocus();
        }
        Context applicationContext = view.getContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        Object systemService = ContextCompat.getSystemService(applicationContext, InputMethodManager.class);
        Intrinsics.checkNotNull(systemService);
        InputMethodManager inputMethodManager = (InputMethodManager) systemService;
        AudioPlayerBinding audioPlayerBinding4 = this.binding;
        if (audioPlayerBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            audioPlayerBinding = audioPlayerBinding4;
        }
        inputMethodManager.showSoftInput(audioPlayerBinding.playlistSearchText.getEditText(), 1);
        getHandler().postDelayed(getHideSearchRunnable(), 10000);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001f, code lost:
        r0 = r0.getPlaylistManager();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onABRepeatStopClick(android.view.View r2) {
        /*
            r1 = this;
            java.lang.String r0 = "v"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            org.videolan.vlc.viewmodels.PlaylistModel r2 = r1.getPlaylistModel()
            org.videolan.vlc.PlaybackService r2 = r2.getService()
            if (r2 == 0) goto L_0x002e
            org.videolan.vlc.media.PlaylistManager r2 = r2.getPlaylistManager()
            if (r2 == 0) goto L_0x002e
            org.videolan.vlc.viewmodels.PlaylistModel r0 = r1.getPlaylistModel()
            org.videolan.vlc.PlaybackService r0 = r0.getService()
            if (r0 == 0) goto L_0x002a
            org.videolan.vlc.media.PlaylistManager r0 = r0.getPlaylistManager()
            if (r0 == 0) goto L_0x002a
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = r0.getCurrentMedia()
            goto L_0x002b
        L_0x002a:
            r0 = 0
        L_0x002b:
            r2.resetABRepeatValues(r0)
        L_0x002e:
            org.videolan.vlc.viewmodels.PlaylistModel r2 = r1.getPlaylistModel()
            org.videolan.vlc.PlaybackService r2 = r2.getService()
            if (r2 == 0) goto L_0x0041
            org.videolan.vlc.media.PlaylistManager r2 = r2.getPlaylistManager()
            if (r2 == 0) goto L_0x0041
            r2.clearABRepeat()
        L_0x0041:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioPlayer.onABRepeatStopClick(android.view.View):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001f, code lost:
        r0 = r0.getPlaylistManager();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onABRepeatResetClick(android.view.View r2) {
        /*
            r1 = this;
            java.lang.String r0 = "v"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            org.videolan.vlc.viewmodels.PlaylistModel r2 = r1.getPlaylistModel()
            org.videolan.vlc.PlaybackService r2 = r2.getService()
            if (r2 == 0) goto L_0x002e
            org.videolan.vlc.media.PlaylistManager r2 = r2.getPlaylistManager()
            if (r2 == 0) goto L_0x002e
            org.videolan.vlc.viewmodels.PlaylistModel r0 = r1.getPlaylistModel()
            org.videolan.vlc.PlaybackService r0 = r0.getService()
            if (r0 == 0) goto L_0x002a
            org.videolan.vlc.media.PlaylistManager r0 = r0.getPlaylistManager()
            if (r0 == 0) goto L_0x002a
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = r0.getCurrentMedia()
            goto L_0x002b
        L_0x002a:
            r0 = 0
        L_0x002b:
            r2.resetABRepeatValues(r0)
        L_0x002e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioPlayer.onABRepeatResetClick(android.view.View):void");
    }

    public final boolean backPressed() {
        PlayerOptionsDelegate playerOptionsDelegate = this.optionsDelegate;
        if (playerOptionsDelegate != null) {
            PlayerOptionsDelegate playerOptionsDelegate2 = null;
            if (playerOptionsDelegate == null) {
                Intrinsics.throwUninitializedPropertyAccessException("optionsDelegate");
                playerOptionsDelegate = null;
            }
            if (playerOptionsDelegate.isShowing()) {
                PlayerOptionsDelegate playerOptionsDelegate3 = this.optionsDelegate;
                if (playerOptionsDelegate3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("optionsDelegate");
                } else {
                    playerOptionsDelegate2 = playerOptionsDelegate3;
                }
                playerOptionsDelegate2.hide();
                return true;
            }
        }
        if (!areBookmarksVisible()) {
            return clearSearch();
        }
        getBookmarkListDelegate().hide();
        return true;
    }

    public final boolean areBookmarksVisible() {
        return this.bookmarkListDelegate != null && getBookmarkListDelegate().getVisible();
    }

    public final boolean clearSearch() {
        if (this.playlistModel != null) {
            getPlaylistModel().filter((CharSequence) null);
        }
        return hideSearchField();
    }

    /* access modifiers changed from: private */
    public final boolean hideSearchField() {
        AudioPlayerBinding audioPlayerBinding = this.binding;
        AudioPlayerBinding audioPlayerBinding2 = null;
        if (audioPlayerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding = null;
        }
        if (audioPlayerBinding.playlistSearchText.getVisibility() != 0) {
            return false;
        }
        AudioPlayerBinding audioPlayerBinding3 = this.binding;
        if (audioPlayerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding3 = null;
        }
        EditText editText = audioPlayerBinding3.playlistSearchText.getEditText();
        if (editText != null) {
            TextWatcher textWatcher = this;
            editText.removeTextChangedListener(textWatcher);
            editText.setText("");
            editText.addTextChangedListener(textWatcher);
        }
        UiTools uiTools = UiTools.INSTANCE;
        AudioPlayerBinding audioPlayerBinding4 = this.binding;
        if (audioPlayerBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            audioPlayerBinding2 = audioPlayerBinding4;
        }
        uiTools.setKeyboardVisibility(audioPlayerBinding2.playlistSearchText, false);
        manageSearchVisibilities(false);
        return true;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(charSequence, "charSequence");
        if (charSequence.length() > 0) {
            getPlaylistModel().filter(charSequence);
            getHandler().removeCallbacks(getHideSearchRunnable());
            return;
        }
        getPlaylistModel().filter((CharSequence) null);
        hideSearchField();
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\u00060\u0016j\u0002`\u00178\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0006\"\u0004\b\u001e\u0010\b¨\u0006$"}, d2 = {"Lorg/videolan/vlc/gui/audio/AudioPlayer$LongSeekListener;", "Landroid/view/View$OnTouchListener;", "forward", "", "(Lorg/videolan/vlc/gui/audio/AudioPlayer;Z)V", "getForward", "()Z", "setForward", "(Z)V", "length", "", "getLength", "()J", "setLength", "(J)V", "possibleSeek", "", "getPossibleSeek", "()I", "setPossibleSeek", "(I)V", "seekRunnable", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "getSeekRunnable", "()Ljava/lang/Runnable;", "setSeekRunnable", "(Ljava/lang/Runnable;)V", "vibrated", "getVibrated", "setVibrated", "onTouch", "v", "Landroid/view/View;", "event", "Landroid/view/MotionEvent;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AudioPlayer.kt */
    private final class LongSeekListener implements View.OnTouchListener {
        private boolean forward;
        private long length = -1;
        private int possibleSeek;
        private Runnable seekRunnable;
        private boolean vibrated;

        public LongSeekListener(boolean z) {
            this.forward = z;
            this.seekRunnable = new AudioPlayer$LongSeekListener$seekRunnable$1(this, AudioPlayer.this);
        }

        public final boolean getForward() {
            return this.forward;
        }

        public final void setForward(boolean z) {
            this.forward = z;
        }

        public final long getLength() {
            return this.length;
        }

        public final void setLength(long j) {
            this.length = j;
        }

        public final int getPossibleSeek() {
            return this.possibleSeek;
        }

        public final void setPossibleSeek(int i) {
            this.possibleSeek = i;
        }

        public final boolean getVibrated() {
            return this.vibrated;
        }

        public final void setVibrated(boolean z) {
            this.vibrated = z;
        }

        public final Runnable getSeekRunnable() {
            return this.seekRunnable;
        }

        public final void setSeekRunnable(Runnable runnable) {
            Intrinsics.checkNotNullParameter(runnable, "<set-?>");
            this.seekRunnable = runnable;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(view, "v");
            Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
            int action = motionEvent.getAction();
            if (action == 0) {
                this.possibleSeek = (int) AudioPlayer.this.getPlaylistModel().getTime();
                AudioPlayer.this.previewingSeek = true;
                this.vibrated = false;
                this.length = AudioPlayer.this.getPlaylistModel().getLength();
                AudioPlayer.this.getHandler().postDelayed(this.seekRunnable, 1000);
                return false;
            } else if (action != 1 && action != 3) {
                return false;
            } else {
                AudioPlayer.this.getHandler().removeCallbacks(this.seekRunnable);
                AudioPlayer.this.previewingSeek = false;
                if (motionEvent.getEventTime() - motionEvent.getDownTime() < 1000) {
                    return false;
                }
                PlaylistModel.setTime$default(AudioPlayer.this.getPlaylistModel(), RangesKt.coerceAtMost(RangesKt.coerceAtLeast((long) this.possibleSeek, 0), AudioPlayer.this.getPlaylistModel().getLength()), false, 2, (Object) null);
                view.setPressed(false);
                return true;
            }
        }
    }

    private final void showPlaylistTips() {
        FragmentActivity activity = getActivity();
        AudioPlayerContainerActivity audioPlayerContainerActivity = activity instanceof AudioPlayerContainerActivity ? (AudioPlayerContainerActivity) activity : null;
        if (audioPlayerContainerActivity != null) {
            audioPlayerContainerActivity.showTipViewIfNeeded(R.id.audio_playlist_tips, SettingsKt.PREF_PLAYLIST_TIPS_SHOWN);
        }
    }

    public final void onStateChanged(int i) {
        this.playerState = i;
        if (i == 3) {
            onSlide(1.0f);
            showPlaylistTips();
            PlaylistAdapter playlistAdapter2 = this.playlistAdapter;
            if (playlistAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playlistAdapter");
                playlistAdapter2 = null;
            }
            playlistAdapter2.setCurrentIndex(getPlaylistModel().getCurrentMediaPosition());
        } else if (i == 4) {
            backPressed();
            onSlide(0.0f);
        }
    }

    public final Button retrieveAbRepeatAddMarker() {
        if (this.abRepeatAddMarker == null) {
            return null;
        }
        return getAbRepeatAddMarker();
    }

    private final Runnable getHideSearchRunnable() {
        return (Runnable) this.hideSearchRunnable$delegate.getValue();
    }
}
