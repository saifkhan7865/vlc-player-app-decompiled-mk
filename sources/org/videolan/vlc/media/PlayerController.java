package org.videolan.vlc.media;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import androidx.lifecycle.MutableLiveData;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.RendererItem;
import org.videolan.libvlc.interfaces.ILibVLC;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.interfaces.IMediaList;
import org.videolan.libvlc.interfaces.IVLCVout;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.Constants;
import org.videolan.resources.VLCInstance;
import org.videolan.resources.VLCOptions;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.VersionDependantKt;
import org.videolan.vlc.gui.dialogs.VideoTracksDialog;
import org.videolan.vlc.gui.dialogs.adapters.VlcTrack;
import org.videolan.vlc.repository.SlaveRepository;

@Metadata(d1 = {"\u0000ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 Ê\u00012\u00020\u00012\u00020\u00022\u00020\u0003:\u0002Ê\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010L\u001a\u00020\u00132\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020\u0013J\u0016\u0010L\u001a\u00020\u00132\u0006\u0010P\u001a\u00020Q2\u0006\u0010O\u001a\u00020\u0013J\u0006\u0010R\u001a\u00020\u0013J\u0006\u0010S\u001a\u00020\u0013J\u0010\u0010T\u001a\u0004\u0018\u00010UH@¢\u0006\u0002\u0010VJ\u0006\u0010W\u001a\u00020\u001eJ\u0006\u0010X\u001a\u00020QJ\u0015\u0010Y\u001a\f\u0012\u0006\b\u0001\u0012\u00020[\u0018\u00010Z¢\u0006\u0002\u0010\\J\u0006\u0010]\u001a\u00020^J\u0006\u0010_\u001a\u00020^J\u001d\u0010`\u001a\f\u0012\u0006\b\u0001\u0012\u00020a\u0018\u00010Z2\u0006\u0010b\u001a\u00020^¢\u0006\u0002\u0010cJ\u0006\u0010d\u001a\u00020\u001eJ\b\u0010e\u001a\u0004\u0018\u00010[J\u0006\u0010f\u001a\u00020\u001eJ\b\u0010g\u001a\u0004\u0018\u00010hJ\u0006\u0010i\u001a\u00020\u0018J\u0006\u0010j\u001a\u00020\u001eJ\u0006\u0010k\u001a\u00020QJ\u0015\u0010l\u001a\f\u0012\u0006\b\u0001\u0012\u00020[\u0018\u00010Z¢\u0006\u0002\u0010\\J\u0006\u0010m\u001a\u00020^J\u0006\u0010n\u001a\u00020^J\u0015\u0010o\u001a\f\u0012\u0006\b\u0001\u0012\u00020p\u0018\u00010Z¢\u0006\u0002\u0010qJ\u0006\u0010r\u001a\u00020QJ\u0013\u0010s\u001a\n\u0012\u0006\b\u0001\u0012\u00020[0Z¢\u0006\u0002\u0010\\J\u0006\u0010t\u001a\u00020^J\u0006\u0010u\u001a\u00020^J\b\u0010v\u001a\u0004\u0018\u00010wJ\u0006\u0010x\u001a\u00020\u0013J\u0006\u0010y\u001a\u00020\u0013J\u0006\u0010z\u001a\u00020\u0013J\u000e\u0010{\u001a\u00020|2\u0006\u0010}\u001a\u00020^J\b\u0010~\u001a\u00020\u001fH\u0002J\u0013\u0010\u001a\u00020|2\t\u0010\u0001\u001a\u0004\u0018\u00010\u000fH\u0016J\u0014\u0010\u0001\u001a\u00020|2\t\u0010\u0001\u001a\u0004\u0018\u00010wH\u0016J\u0014\u0010\u0001\u001a\u00020|2\t\u0010\u0001\u001a\u0004\u0018\u00010wH\u0016J\u0007\u0010\u0001\u001a\u00020\u0013J\u0007\u0010\u0001\u001a\u00020|J\u0012\u0010\u0001\u001a\u00020|2\t\b\u0002\u0010\u0001\u001a\u00020\u001fJ\u0011\u0010\u0001\u001a\u0004\u0018\u00010|H\u0002¢\u0006\u0003\u0010\u0001J\u001b\u0010\u0001\u001a\u00020|2\u0007\u0010\u0001\u001a\u00020\u001e2\u0007\u0010\u0001\u001a\u00020\u001eH\u0002J\t\u0010\u0001\u001a\u00020|H\u0007J\u0010\u0010\u0001\u001a\u00020\u00132\u0007\u0010\u0001\u001a\u00020\u001eJ\u0010\u0010\u0001\u001a\u00020\u00132\u0007\u0010\u0001\u001a\u00020\u0013J\u0010\u0010\u0001\u001a\u00020\u00132\u0007\u0010\u0001\u001a\u00020QJ\u0010\u0010\u0001\u001a\u00020|2\u0007\u0010\u0001\u001a\u00020^J\u0007\u0010\u0001\u001a\u00020|J\u0013\u0010\u0001\u001a\u00020\u00132\n\u0010\u0001\u001a\u0005\u0018\u00010\u0001J\t\u0010\u0001\u001a\u00020|H\u0002J\u0010\u0010\u0001\u001a\u00020|2\u0007\u0010\u0001\u001a\u00020\u0018J\u0007\u0010\u0001\u001a\u00020|J\u0019\u0010\u0001\u001a\u00020|2\u0007\u0010\u0001\u001a\u00020\u00182\u0007\u0010 \u0001\u001a\u00020\u0013J\u0013\u0010¡\u0001\u001a\u00020|2\n\u0010¢\u0001\u001a\u0005\u0018\u00010£\u0001J\u001b\u0010¤\u0001\u001a\u00030¥\u00012\u0007\u0010¦\u0001\u001a\u00020h2\b\u0010§\u0001\u001a\u00030¨\u0001J\u0010\u0010©\u0001\u001a\u00020\u00132\u0007\u0010\u0001\u001a\u00020\u001eJ\u0010\u0010ª\u0001\u001a\u00020\u00132\u0007\u0010\u0001\u001a\u00020QJ\u001b\u0010«\u0001\u001a\u00020|2\u0007\u0010\u0001\u001a\u00020\u001e2\t\b\u0002\u0010¬\u0001\u001a\u00020\u0013J\u000f\u0010­\u0001\u001a\u00020|2\u0006\u0010b\u001a\u00020^J\u0012\u0010®\u0001\u001a\u00020|2\t\u0010¯\u0001\u001a\u0004\u0018\u00010QJ\u0012\u0010°\u0001\u001a\u00020|2\u0007\u0010±\u0001\u001a\u00020\u0018H\u0007J\u0010\u0010²\u0001\u001a\u00020\u00132\u0007\u0010\u0001\u001a\u00020QJ\u0010\u0010³\u0001\u001a\u00020|2\u0007\u0010\u0001\u001a\u00020\u0013J\u0010\u0010´\u0001\u001a\u00020^2\u0007\u0010µ\u0001\u001a\u00020^J.\u0010¶\u0001\u001a\u00020|2\u0007\u0010¦\u0001\u001a\u00020h2\u0007\u0010·\u0001\u001a\u00020$2\u0007\u0010\u0001\u001a\u00020\u001eH@¢\u0006\u0006\b¸\u0001\u0010¹\u0001J\u0007\u0010º\u0001\u001a\u00020|J\u0011\u0010»\u0001\u001a\u00020|2\b\u0010¼\u0001\u001a\u00030½\u0001J$\u0010¾\u0001\u001a\u00020\u00132\u0007\u0010¿\u0001\u001a\u00020^2\n\u0010§\u0001\u001a\u0005\u0018\u00010¨\u0001H\u0000¢\u0006\u0003\bÀ\u0001J\u001f\u0010Á\u0001\u001a\u00020|2\t\b\u0002\u0010Â\u0001\u001a\u00020\u001e2\t\b\u0002\u0010Ã\u0001\u001a\u00020\u001eH\u0007J4\u0010Ä\u0001\u001a\u00020\u00132\u0007\u0010Å\u0001\u001a\u00020\u00182\u0007\u0010Æ\u0001\u001a\u00020\u00182\u0007\u0010Ç\u0001\u001a\u00020\u00182\u0007\u0010È\u0001\u001a\u00020\u00182\u0007\u0010É\u0001\u001a\u00020\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010 \u001a\u00020\u001f2\u0006\u0010\u0012\u001a\u00020\u001f@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0016\"\u0004\b'\u0010(R\u001b\u0010)\u001a\u00020*8BX\u0002¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b+\u0010,R\"\u00100\u001a\u0004\u0018\u00010/2\b\u0010\u0012\u001a\u0004\u0018\u00010/@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R!\u00103\u001a\b\u0012\u0004\u0012\u000205048FX\u0002¢\u0006\f\n\u0004\b8\u0010.\u001a\u0004\b6\u00107R\u001a\u00109\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0016\"\u0004\b;\u0010(R\u001b\u0010<\u001a\u00020=8BX\u0002¢\u0006\f\n\u0004\b@\u0010.\u001a\u0004\b>\u0010?R\u001b\u0010A\u001a\u00020B8BX\u0002¢\u0006\f\n\u0004\bE\u0010.\u001a\u0004\bC\u0010DR!\u0010F\u001a\b\u0012\u0004\u0012\u00020\u0018048FX\u0002¢\u0006\f\n\u0004\bH\u0010.\u001a\u0004\bG\u00107R\u001a\u0010I\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u0016\"\u0004\bK\u0010(¨\u0006Ë\u0001"}, d2 = {"Lorg/videolan/vlc/media/PlayerController;", "Lorg/videolan/libvlc/interfaces/IVLCVout$Callback;", "Lorg/videolan/libvlc/MediaPlayer$EventListener;", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "eventActor", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/libvlc/MediaPlayer$Event;", "getEventActor$annotations", "()V", "<set-?>", "", "hasRenderer", "getHasRenderer", "()Z", "lastPosition", "", "getLastPosition", "()F", "setLastPosition", "(F)V", "lastTime", "", "Lorg/videolan/libvlc/MediaPlayer;", "mediaplayer", "getMediaplayer", "()Lorg/videolan/libvlc/MediaPlayer;", "mediaplayerEventListener", "Lorg/videolan/vlc/media/MediaPlayerEventListener;", "pausable", "getPausable", "setPausable", "(Z)V", "playerContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "getPlayerContext", "()Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "playerContext$delegate", "Lkotlin/Lazy;", "Lorg/videolan/libvlc/interfaces/IMedia$Stats;", "previousMediaStats", "getPreviousMediaStats", "()Lorg/videolan/libvlc/interfaces/IMedia$Stats;", "progress", "Landroidx/lifecycle/MutableLiveData;", "Lorg/videolan/vlc/media/Progress;", "getProgress", "()Landroidx/lifecycle/MutableLiveData;", "progress$delegate", "seekable", "getSeekable", "setSeekable", "settings", "Landroid/content/SharedPreferences;", "getSettings", "()Landroid/content/SharedPreferences;", "settings$delegate", "slaveRepository", "Lorg/videolan/vlc/repository/SlaveRepository;", "getSlaveRepository", "()Lorg/videolan/vlc/repository/SlaveRepository;", "slaveRepository$delegate", "speed", "getSpeed", "speed$delegate", "switchToVideo", "getSwitchToVideo", "setSwitchToVideo", "addSubtitleTrack", "uri", "Landroid/net/Uri;", "select", "path", "", "canDoPassthrough", "canSwitchToVideo", "expand", "Lorg/videolan/libvlc/interfaces/IMediaList;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAudioDelay", "getAudioTrack", "getAudioTracks", "", "Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;", "()[Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;", "getAudioTracksCount", "", "getChapterIdx", "getChapters", "Lorg/videolan/libvlc/MediaPlayer$Chapter;", "title", "(I)[Lorg/videolan/libvlc/MediaPlayer$Chapter;", "getCurrentTime", "getCurrentVideoTrack", "getLength", "getMedia", "Lorg/videolan/libvlc/interfaces/IMedia;", "getRate", "getSpuDelay", "getSpuTrack", "getSpuTracks", "getSpuTracksCount", "getTitleIdx", "getTitles", "Lorg/videolan/libvlc/MediaPlayer$Title;", "()[Lorg/videolan/libvlc/MediaPlayer$Title;", "getVideoTrack", "getVideoTracks", "getVideoTracksCount", "getVolume", "getVout", "Lorg/videolan/libvlc/interfaces/IVLCVout;", "isPaused", "isPlaying", "isVideoPlaying", "navigate", "", "where", "newMediaPlayer", "onEvent", "event", "onSurfacesCreated", "vlcVout", "onSurfacesDestroyed", "pause", "play", "release", "player", "releaseMedia", "()Lkotlin/Unit;", "resetPlaybackState", "time", "duration", "restart", "setAudioDelay", "delay", "setAudioDigitalOutputEnabled", "enabled", "setAudioTrack", "index", "setChapterIdx", "chapter", "setCurrentStats", "setEqualizer", "equalizer", "Lorg/videolan/libvlc/MediaPlayer$Equalizer;", "setPlaybackStopped", "setPosition", "position", "setPreviousStats", "setRate", "rate", "save", "setRenderer", "renderer", "Lorg/videolan/libvlc/RendererItem;", "setSlaves", "Lkotlinx/coroutines/Job;", "media", "mw", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "setSpuDelay", "setSpuTrack", "setTime", "fast", "setTitleIdx", "setVideoAspectRatio", "aspect", "setVideoScale", "scale", "setVideoTrack", "setVideoTrackEnabled", "setVolume", "volume", "startPlayback", "listener", "startPlayback$vlc_android_release", "(Lorg/videolan/libvlc/interfaces/IMedia;Lorg/videolan/vlc/media/MediaPlayerEventListener;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stop", "unselectTrackType", "trackType", "Lorg/videolan/vlc/gui/dialogs/VideoTracksDialog$TrackType;", "updateCurrentMeta", "id", "updateCurrentMeta$vlc_android_release", "updateProgress", "newTime", "newLength", "updateViewpoint", "yaw", "pitch", "roll", "fov", "absolute", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlayerController.kt */
public final class PlayerController implements IVLCVout.Callback, MediaPlayer.EventListener, CoroutineScope {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static volatile int playbackState;
    private final Context context;
    private final CoroutineContext coroutineContext = Dispatchers.getMain().getImmediate().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null));
    private final SendChannel<MediaPlayer.Event> eventActor = ActorKt.actor$default(this, (CoroutineContext) null, Integer.MAX_VALUE, CoroutineStart.UNDISPATCHED, (Function1) null, new PlayerController$eventActor$1(this, (Continuation<? super PlayerController$eventActor$1>) null), 9, (Object) null);
    private volatile boolean hasRenderer;
    private float lastPosition;
    /* access modifiers changed from: private */
    public long lastTime;
    private MediaPlayer mediaplayer = newMediaPlayer();
    /* access modifiers changed from: private */
    public MediaPlayerEventListener mediaplayerEventListener;
    private boolean pausable;
    private final Lazy playerContext$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, PlayerController$playerContext$2.INSTANCE);
    private IMedia.Stats previousMediaStats;
    private final Lazy progress$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, PlayerController$progress$2.INSTANCE);
    private boolean seekable;
    private final Lazy settings$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new PlayerController$settings$2(this));
    private final Lazy slaveRepository$delegate = LazyKt.lazy(new PlayerController$slaveRepository$2(this));
    private final Lazy speed$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, PlayerController$speed$2.INSTANCE);
    private boolean switchToVideo;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PlayerController.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                org.videolan.vlc.gui.dialogs.VideoTracksDialog$TrackType[] r0 = org.videolan.vlc.gui.dialogs.VideoTracksDialog.TrackType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.gui.dialogs.VideoTracksDialog$TrackType r1 = org.videolan.vlc.gui.dialogs.VideoTracksDialog.TrackType.VIDEO     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.gui.dialogs.VideoTracksDialog$TrackType r1 = org.videolan.vlc.gui.dialogs.VideoTracksDialog.TrackType.AUDIO     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.gui.dialogs.VideoTracksDialog$TrackType r1 = org.videolan.vlc.gui.dialogs.VideoTracksDialog.TrackType.SPU     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlayerController.WhenMappings.<clinit>():void");
        }
    }

    private static /* synthetic */ void getEventActor$annotations() {
    }

    public void onSurfacesCreated(IVLCVout iVLCVout) {
    }

    public final void updateProgress() {
        updateProgress$default(this, 0, 0, 3, (Object) null);
    }

    public final void updateProgress(long j) {
        updateProgress$default(this, j, 0, 2, (Object) null);
    }

    public PlayerController(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
    }

    public final Context getContext() {
        return this.context;
    }

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    private final ExecutorCoroutineDispatcher getPlayerContext() {
        return (ExecutorCoroutineDispatcher) this.playerContext$delegate.getValue();
    }

    private final SharedPreferences getSettings() {
        return (SharedPreferences) this.settings$delegate.getValue();
    }

    public final MutableLiveData<Progress> getProgress() {
        return (MutableLiveData) this.progress$delegate.getValue();
    }

    public final MutableLiveData<Float> getSpeed() {
        return (MutableLiveData) this.speed$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final SlaveRepository getSlaveRepository() {
        return (SlaveRepository) this.slaveRepository$delegate.getValue();
    }

    public final MediaPlayer getMediaplayer() {
        return this.mediaplayer;
    }

    public final boolean getSwitchToVideo() {
        return this.switchToVideo;
    }

    public final void setSwitchToVideo(boolean z) {
        this.switchToVideo = z;
    }

    public final boolean getSeekable() {
        return this.seekable;
    }

    public final void setSeekable(boolean z) {
        this.seekable = z;
    }

    public final boolean getPausable() {
        return this.pausable;
    }

    public final void setPausable(boolean z) {
        this.pausable = z;
    }

    public final IMedia.Stats getPreviousMediaStats() {
        return this.previousMediaStats;
    }

    public final boolean getHasRenderer() {
        return this.hasRenderer;
    }

    public final IVLCVout getVout() {
        return this.mediaplayer.getVLCVout();
    }

    public final boolean canDoPassthrough() {
        return this.mediaplayer.hasMedia() && !this.mediaplayer.isReleased() && this.mediaplayer.canDoPassthrough();
    }

    public final IMedia getMedia() {
        return this.mediaplayer.getMedia();
    }

    public final void play() {
        if (this.mediaplayer.hasMedia() && !this.mediaplayer.isReleased()) {
            this.mediaplayer.play();
        }
    }

    public final boolean pause() {
        if (!isPlaying() || !this.mediaplayer.hasMedia() || !this.pausable) {
            return false;
        }
        this.mediaplayer.pause();
        return true;
    }

    public final void stop() {
        if (this.mediaplayer.hasMedia() && !this.mediaplayer.isReleased()) {
            this.mediaplayer.stop();
        }
        setPlaybackStopped();
    }

    private final Unit releaseMedia() {
        IMedia media = this.mediaplayer.getMedia();
        if (media == null) {
            return null;
        }
        media.setEventListener((IMedia.EventListener) null);
        media.release();
        return Unit.INSTANCE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object startPlayback$vlc_android_release(org.videolan.libvlc.interfaces.IMedia r8, org.videolan.vlc.media.MediaPlayerEventListener r9, long r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r7 = this;
            boolean r0 = r12 instanceof org.videolan.vlc.media.PlayerController$startPlayback$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            org.videolan.vlc.media.PlayerController$startPlayback$1 r0 = (org.videolan.vlc.media.PlayerController$startPlayback$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.media.PlayerController$startPlayback$1 r0 = new org.videolan.vlc.media.PlayerController$startPlayback$1
            r0.<init>(r7, r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            java.lang.Object r8 = r0.L$0
            org.videolan.vlc.media.PlayerController r8 = (org.videolan.vlc.media.PlayerController) r8
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0061
        L_0x002f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r12)
            r7.mediaplayerEventListener = r9
            long r5 = r8.getDuration()
            r7.resetPlaybackState(r10, r5)
            org.videolan.libvlc.MediaPlayer r9 = r7.mediaplayer
            r9.setEventListener(r4)
            kotlinx.coroutines.CoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            org.videolan.vlc.media.PlayerController$startPlayback$2 r10 = new org.videolan.vlc.media.PlayerController$startPlayback$2
            r10.<init>(r7, r8, r4)
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r9, r10, r0)
            if (r8 != r1) goto L_0x0060
            return r1
        L_0x0060:
            r8 = r7
        L_0x0061:
            org.videolan.libvlc.MediaPlayer r9 = r8.mediaplayer
            r10 = r8
            org.videolan.libvlc.MediaPlayer$EventListener r10 = (org.videolan.libvlc.MediaPlayer.EventListener) r10
            r9.setEventListener(r10)
            org.videolan.libvlc.MediaPlayer r9 = r8.mediaplayer
            boolean r9 = r9.isReleased()
            if (r9 != 0) goto L_0x008b
            org.videolan.libvlc.MediaPlayer r9 = r8.mediaplayer
            org.videolan.resources.VLCOptions r10 = org.videolan.resources.VLCOptions.INSTANCE
            android.content.Context r11 = r8.context
            r12 = 2
            r0 = 0
            org.videolan.libvlc.MediaPlayer$Equalizer r10 = org.videolan.resources.VLCOptions.getEqualizerSetFromSettings$default(r10, r11, r0, r12, r4)
            r9.setEqualizer(r10)
            org.videolan.libvlc.MediaPlayer r9 = r8.mediaplayer
            r10 = -1
            r9.setVideoTitleDisplay(r10, r0)
            org.videolan.libvlc.MediaPlayer r8 = r8.mediaplayer
            r8.play()
        L_0x008b:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlayerController.startPlayback$vlc_android_release(org.videolan.libvlc.interfaces.IMedia, org.videolan.vlc.media.MediaPlayerEventListener, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void resetPlaybackState(long j, long j2) {
        this.seekable = true;
        this.pausable = true;
        this.lastTime = j;
        updateProgress(j, j2);
    }

    public final void restart() {
        int intValue;
        MediaPlayer mediaPlayer = this.mediaplayer;
        Integer valueOf = !mediaPlayer.isReleased() ? Integer.valueOf(mediaPlayer.getVolume()) : null;
        this.mediaplayer = newMediaPlayer();
        if (valueOf != null && (intValue = valueOf.intValue()) > 100) {
            this.mediaplayer.setVolume(intValue);
        }
        release(mediaPlayer);
    }

    public final void setPosition(float f) {
        if (this.seekable && this.mediaplayer.hasMedia() && !this.mediaplayer.isReleased()) {
            this.mediaplayer.setPosition(f);
        }
    }

    public static /* synthetic */ void setTime$default(PlayerController playerController, long j, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        playerController.setTime(j, z);
    }

    public final void setTime(long j, boolean z) {
        if (this.seekable && this.mediaplayer.hasMedia() && !this.mediaplayer.isReleased()) {
            this.mediaplayer.setTime(j, z);
        }
    }

    public final boolean isPlaying() {
        return playbackState == 3;
    }

    public final boolean isPaused() {
        return playbackState == 2;
    }

    public final boolean isVideoPlaying() {
        return !this.mediaplayer.isReleased() && this.mediaplayer.getVLCVout().areViewsAttached();
    }

    public final boolean canSwitchToVideo() {
        return getVideoTracksCount() > 0;
    }

    public final int getVideoTracksCount() {
        if (this.mediaplayer.isReleased() || !this.mediaplayer.hasMedia()) {
            return 0;
        }
        return this.mediaplayer.getVideoTracksCount();
    }

    public final VlcTrack[] getVideoTracks() {
        return (this.mediaplayer.isReleased() || !this.mediaplayer.hasMedia()) ? new VlcTrack[0] : VersionDependantKt.getAllVideoTracks(this.mediaplayer);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        r0 = (r0 = org.videolan.vlc.VersionDependantKt.getSelectedVideoTrack(r2.mediaplayer)).getId();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getVideoTrack() {
        /*
            r2 = this;
            org.videolan.libvlc.MediaPlayer r0 = r2.mediaplayer
            boolean r0 = r0.isReleased()
            java.lang.String r1 = "-1"
            if (r0 != 0) goto L_0x0022
            org.videolan.libvlc.MediaPlayer r0 = r2.mediaplayer
            boolean r0 = r0.hasMedia()
            if (r0 == 0) goto L_0x0022
            org.videolan.libvlc.MediaPlayer r0 = r2.mediaplayer
            org.videolan.vlc.gui.dialogs.adapters.VlcTrack r0 = org.videolan.vlc.VersionDependantKt.getSelectedVideoTrack(r0)
            if (r0 == 0) goto L_0x0022
            java.lang.String r0 = r0.getId()
            if (r0 != 0) goto L_0x0021
            goto L_0x0022
        L_0x0021:
            r1 = r0
        L_0x0022:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlayerController.getVideoTrack():java.lang.String");
    }

    public final VlcTrack getCurrentVideoTrack() {
        if (this.mediaplayer.isReleased() || !this.mediaplayer.hasMedia()) {
            return null;
        }
        return VersionDependantKt.getSelectedVideoTrack(this.mediaplayer);
    }

    public final int getAudioTracksCount() {
        if (this.mediaplayer.isReleased() || !this.mediaplayer.hasMedia()) {
            return 0;
        }
        return this.mediaplayer.getAudioTracksCount();
    }

    public final VlcTrack[] getAudioTracks() {
        return (this.mediaplayer.isReleased() || !this.mediaplayer.hasMedia()) ? new VlcTrack[0] : VersionDependantKt.getAllAudioTracks(this.mediaplayer);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        r0 = (r0 = org.videolan.vlc.VersionDependantKt.getSelectedAudioTrack(r2.mediaplayer)).getId();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getAudioTrack() {
        /*
            r2 = this;
            org.videolan.libvlc.MediaPlayer r0 = r2.mediaplayer
            boolean r0 = r0.isReleased()
            java.lang.String r1 = "-1"
            if (r0 != 0) goto L_0x0022
            org.videolan.libvlc.MediaPlayer r0 = r2.mediaplayer
            boolean r0 = r0.hasMedia()
            if (r0 == 0) goto L_0x0022
            org.videolan.libvlc.MediaPlayer r0 = r2.mediaplayer
            org.videolan.vlc.gui.dialogs.adapters.VlcTrack r0 = org.videolan.vlc.VersionDependantKt.getSelectedAudioTrack(r0)
            if (r0 == 0) goto L_0x0022
            java.lang.String r0 = r0.getId()
            if (r0 != 0) goto L_0x0021
            goto L_0x0022
        L_0x0021:
            r1 = r0
        L_0x0022:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlayerController.getAudioTrack():java.lang.String");
    }

    public final boolean setVideoTrack(String str) {
        Intrinsics.checkNotNullParameter(str, "index");
        return !this.mediaplayer.isReleased() && this.mediaplayer.hasMedia() && VersionDependantKt.setVideoTrack(this.mediaplayer, str);
    }

    public final boolean setAudioTrack(String str) {
        Intrinsics.checkNotNullParameter(str, "index");
        return !this.mediaplayer.isReleased() && this.mediaplayer.hasMedia() && VersionDependantKt.setAudioTrack(this.mediaplayer, str);
    }

    public final void unselectTrackType(VideoTracksDialog.TrackType trackType) {
        Intrinsics.checkNotNullParameter(trackType, "trackType");
        int i = WhenMappings.$EnumSwitchMapping$0[trackType.ordinal()];
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i == 2) {
                i2 = 0;
            } else if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
        }
        if (!this.mediaplayer.isReleased() && this.mediaplayer.hasMedia()) {
            VersionDependantKt.unselectTrackType(this.mediaplayer, i2);
        }
    }

    public final boolean setAudioDigitalOutputEnabled(boolean z) {
        return !this.mediaplayer.isReleased() && this.mediaplayer.setAudioDigitalOutputEnabled(z);
    }

    public final long getAudioDelay() {
        if (!this.mediaplayer.hasMedia() || this.mediaplayer.isReleased()) {
            return 0;
        }
        return this.mediaplayer.getAudioDelay();
    }

    public final long getSpuDelay() {
        if (!this.mediaplayer.hasMedia() || this.mediaplayer.isReleased()) {
            return 0;
        }
        return this.mediaplayer.getSpuDelay();
    }

    public final float getRate() {
        if (!this.mediaplayer.hasMedia() || this.mediaplayer.isReleased() || playbackState == 1) {
            return 1.0f;
        }
        return this.mediaplayer.getRate();
    }

    public final boolean setSpuDelay(long j) {
        return this.mediaplayer.setSpuDelay(j);
    }

    public final void setVideoTrackEnabled(boolean z) {
        this.mediaplayer.setVideoTrackEnabled(z);
    }

    public final boolean addSubtitleTrack(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        return this.mediaplayer.addSlave(0, str, z);
    }

    public final boolean addSubtitleTrack(Uri uri, boolean z) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        return this.mediaplayer.addSlave(0, uri, z);
    }

    public final VlcTrack[] getSpuTracks() {
        return VersionDependantKt.getAllSpuTracks(this.mediaplayer);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r0.getId();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getSpuTrack() {
        /*
            r1 = this;
            org.videolan.libvlc.MediaPlayer r0 = r1.mediaplayer
            org.videolan.vlc.gui.dialogs.adapters.VlcTrack r0 = org.videolan.vlc.VersionDependantKt.getSelectedSpuTrack(r0)
            if (r0 == 0) goto L_0x000e
            java.lang.String r0 = r0.getId()
            if (r0 != 0) goto L_0x0010
        L_0x000e:
            java.lang.String r0 = "-1"
        L_0x0010:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlayerController.getSpuTrack():java.lang.String");
    }

    public final boolean setSpuTrack(String str) {
        Intrinsics.checkNotNullParameter(str, "index");
        return VersionDependantKt.setSpuTrack(this.mediaplayer, str);
    }

    public final int getSpuTracksCount() {
        return this.mediaplayer.getSpuTracksCount();
    }

    public final boolean setAudioDelay(long j) {
        return this.mediaplayer.setAudioDelay(j);
    }

    public final boolean setEqualizer(MediaPlayer.Equalizer equalizer) {
        return this.mediaplayer.setEqualizer(equalizer);
    }

    public final void setVideoScale(float f) {
        this.mediaplayer.setScale(f);
    }

    public final void setVideoAspectRatio(String str) {
        this.mediaplayer.setAspectRatio(str);
    }

    public final void setRenderer(RendererItem rendererItem) {
        if (!this.mediaplayer.isReleased()) {
            this.mediaplayer.setRenderer(rendererItem);
        }
        this.hasRenderer = rendererItem != null;
    }

    public static /* synthetic */ void release$default(PlayerController playerController, MediaPlayer mediaPlayer, int i, Object obj) {
        if ((i & 1) != 0) {
            mediaPlayer = playerController.mediaplayer;
        }
        playerController.release(mediaPlayer);
    }

    public final void release(MediaPlayer mediaPlayer) {
        Intrinsics.checkNotNullParameter(mediaPlayer, "player");
        mediaPlayer.setEventListener((MediaPlayer.EventListener) null);
        if (isVideoPlaying()) {
            mediaPlayer.getVLCVout().detachViews();
        }
        releaseMedia();
        Job unused = BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getIO(), (CoroutineStart) null, new PlayerController$release$1(mediaPlayer, this, (Continuation<? super PlayerController$release$1>) null), 2, (Object) null);
        setPlaybackStopped();
    }

    public final Job setSlaves(IMedia iMedia, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(iMedia, "media");
        Intrinsics.checkNotNullParameter(mediaWrapper, "mw");
        return BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PlayerController$setSlaves$1(this, mediaWrapper, iMedia, (Continuation<? super PlayerController$setSlaves$1>) null), 3, (Object) null);
    }

    private final MediaPlayer newMediaPlayer() {
        MediaPlayer mediaPlayer = new MediaPlayer((ILibVLC) VLCInstance.INSTANCE.getInstance(this.context));
        mediaPlayer.setAudioDigitalOutputEnabled(VLCOptions.INSTANCE.isAudioDigitalOutputEnabled(getSettings()));
        String aout = VLCOptions.INSTANCE.getAout(getSettings());
        if (aout != null) {
            mediaPlayer.setAudioOutput(aout);
        }
        mediaPlayer.setRenderer((RendererItem) PlaybackService.Companion.getRenderer().getValue());
        mediaPlayer.getVLCVout().addCallback(this);
        return mediaPlayer;
    }

    public void onSurfacesDestroyed(IVLCVout iVLCVout) {
        this.switchToVideo = false;
    }

    public final long getCurrentTime() {
        Progress value = getProgress().getValue();
        if (value != null) {
            return value.getTime();
        }
        return 0;
    }

    public final long getLength() {
        Progress value = getProgress().getValue();
        if (value != null) {
            return value.getLength();
        }
        return 0;
    }

    public final void setRate(float f, boolean z) {
        if (!this.mediaplayer.isReleased()) {
            this.mediaplayer.setRate(f);
            getSpeed().postValue(Float.valueOf(f));
            if (z) {
                if (getSettings().getBoolean(isVideoPlaying() ? SettingsKt.KEY_PLAYBACK_SPEED_PERSIST_VIDEO : SettingsKt.KEY_PLAYBACK_SPEED_PERSIST, false)) {
                    SettingsKt.putSingle(getSettings(), isVideoPlaying() ? SettingsKt.KEY_PLAYBACK_RATE_VIDEO : SettingsKt.KEY_PLAYBACK_RATE, Float.valueOf(f));
                }
            }
        }
    }

    public final boolean updateCurrentMeta$vlc_android_release(int i, MediaWrapper mediaWrapper) {
        if (i == 13) {
            return false;
        }
        if (mediaWrapper != null) {
            mediaWrapper.updateMeta(this.mediaplayer);
        }
        if (i == 12) {
            if ((mediaWrapper != null ? mediaWrapper.getNowPlaying() : null) != null) {
                return true;
            }
            return false;
        }
        return true;
    }

    public final void setCurrentStats() {
        IMedia media = this.mediaplayer.getMedia();
        if (media != null) {
            this.previousMediaStats = media.getStats();
        }
    }

    public final void setPreviousStats() {
        IMedia media = this.mediaplayer.getMedia();
        if (media != null) {
            this.previousMediaStats = media.getStats();
            media.release();
        }
    }

    public final boolean updateViewpoint(float f, float f2, float f3, float f4, boolean z) {
        return this.mediaplayer.updateViewpoint(f, f2, f3, f4, z);
    }

    public final void navigate(int i) {
        this.mediaplayer.navigate(i);
    }

    public final MediaPlayer.Chapter[] getChapters(int i) {
        return !this.mediaplayer.isReleased() ? this.mediaplayer.getChapters(i) : new MediaPlayer.Chapter[0];
    }

    public final MediaPlayer.Title[] getTitles() {
        return !this.mediaplayer.isReleased() ? this.mediaplayer.getTitles() : new MediaPlayer.Title[0];
    }

    public final int getChapterIdx() {
        if (!this.mediaplayer.isReleased()) {
            return this.mediaplayer.getChapter();
        }
        return -1;
    }

    public final void setChapterIdx(int i) {
        if (!this.mediaplayer.isReleased()) {
            this.mediaplayer.setChapter(i);
        }
    }

    public final int getTitleIdx() {
        if (!this.mediaplayer.isReleased()) {
            return this.mediaplayer.getTitle();
        }
        return -1;
    }

    public final void setTitleIdx(int i) {
        if (!this.mediaplayer.isReleased()) {
            this.mediaplayer.setTitle(i);
        }
    }

    public final int getVolume() {
        if (!this.mediaplayer.isReleased()) {
            return this.mediaplayer.getVolume();
        }
        return 100;
    }

    public final int setVolume(int i) {
        if (!this.mediaplayer.isReleased()) {
            return this.mediaplayer.setVolume(i);
        }
        return -1;
    }

    public final Object expand(Continuation<? super IMediaList> continuation) {
        IMedia media = this.mediaplayer.getMedia();
        if (media != null) {
            return BuildersKt.withContext(getPlayerContext(), new PlayerController$expand$2$1(this, media, (Continuation<? super PlayerController$expand$2$1>) null), continuation);
        }
        return null;
    }

    public final float getLastPosition() {
        return this.lastPosition;
    }

    public final void setLastPosition(float f) {
        this.lastPosition = f;
    }

    public static /* synthetic */ void updateProgress$default(PlayerController playerController, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            Progress value = playerController.getProgress().getValue();
            j = value != null ? value.getTime() : 0;
        }
        if ((i & 2) != 0) {
            Progress value2 = playerController.getProgress().getValue();
            j2 = value2 != null ? value2.getLength() : 0;
        }
        playerController.updateProgress(j, j2);
    }

    public final void updateProgress(long j, long j2) {
        MutableLiveData<Progress> progress = getProgress();
        Progress value = getProgress().getValue();
        if (value != null) {
            value.setTime(j);
            value.setLength(j2);
        } else {
            value = null;
        }
        progress.setValue(value);
    }

    public void onEvent(MediaPlayer.Event event) {
        if (event != null) {
            this.eventActor.m1139trySendJP2dKIU(event);
        }
    }

    /* access modifiers changed from: private */
    public final void setPlaybackStopped() {
        playbackState = 1;
        updateProgress(0, 0);
        this.lastTime = 0;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/videolan/vlc/media/PlayerController$Companion;", "", "()V", "<set-?>", "", "playbackState", "getPlaybackState", "()I", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PlayerController.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getPlaybackState() {
            return PlayerController.playbackState;
        }
    }
}
