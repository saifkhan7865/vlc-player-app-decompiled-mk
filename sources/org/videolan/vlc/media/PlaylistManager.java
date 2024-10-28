package org.videolan.vlc.media;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.videolan.libvlc.FactoryManager;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.RendererItem;
import org.videolan.libvlc.interfaces.IComponentFactory;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.interfaces.IMediaFactory;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.Constants;
import org.videolan.tools.AppScope;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.gui.video.VideoPlayerActivity;
import org.videolan.vlc.media.MediaWrapperList;
import org.videolan.vlc.util.BrowserutilsKt;

@Metadata(d1 = {"\u0000Ñ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0010*\u0001F\u0018\u0000 æ\u00012\u00020\u00012\u00020\u00022\u00020\u0003:\u0002æ\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J'\u0010{\u001a\u00020\u00142\f\u0010|\u001a\b\u0012\u0004\u0012\u00020~0}2\b\b\u0002\u0010\u001a\u00020'H@¢\u0006\u0003\u0010\u0001J\u0007\u0010\u0001\u001a\u00020\u000fJ\u0007\u0010\u0001\u001a\u00020\u000fJ\u0007\u0010\u0001\u001a\u00020\u0014J\u001b\u0010\u0001\u001a\u00020\u00142\t\b\u0002\u0010\u0001\u001a\u00020\u000fH@¢\u0006\u0003\u0010\u0001J\t\u0010\u0001\u001a\u00020\u0014H\u0002J\u0019\u0010\u0001\u001a\u00020'2\u0007\u0010\u0001\u001a\u00020\u000fH@¢\u0006\u0003\u0010\u0001J\t\u0010\u0001\u001a\u0004\u0018\u00010~J\u0012\u0010\u0001\u001a\u0004\u0018\u00010~2\u0007\u0010\u0001\u001a\u00020'J\r\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020~0}J\u0007\u0010\u0001\u001a\u00020'J\t\u0010\u0001\u001a\u0004\u0018\u00010~J\t\u0010\u0001\u001a\u0004\u0018\u00010~J\u0012\u0010\u0001\u001a\u00020<2\u0007\u0010\u0001\u001a\u00020~H\u0002J\u0007\u0010\u0001\u001a\u00020\u000fJ\u0007\u0010\u0001\u001a\u00020\u000fJ\u0007\u0010\u0001\u001a\u00020\u000fJ\u0007\u0010\u0001\u001a\u00020\u000fJ\u001b\u0010\u0001\u001a\u00020\u00142\u0007\u0010\u0001\u001a\u00020'2\u0007\u0010\u0001\u001a\u00020~H\u0007J\u0017\u0010\u0001\u001a\u00020\u00142\f\u0010|\u001a\b\u0012\u0004\u0012\u00020~0}H\u0007J\u000f\u0010\u0001\u001a\u00020\u000fH\u0000¢\u0006\u0003\b\u0001J\u0010\u0010\u0001\u001a\u00020\u000f2\u0007\u0010\u0001\u001a\u00020'J=\u0010\u0001\u001a\u00020\u00142\f\u0010|\u001a\b\u0012\u0004\u0012\u00020~0}2\u0007\u0010\u0001\u001a\u00020'2\t\b\u0002\u0010\u0001\u001a\u00020\u000f2\t\b\u0002\u0010\u0001\u001a\u00020\u000fH@¢\u0006\u0003\u0010\u0001J\u0012\u0010\u0001\u001a\u00020\u000f2\t\b\u0002\u0010 \u0001\u001a\u00020'J!\u0010¡\u0001\u001a\u00020\u00142\r\u0010¢\u0001\u001a\b\u0012\u0004\u0012\u0002020}2\u0007\u0010\u0001\u001a\u00020'H\u0007J\u0012\u0010£\u0001\u001a\u00020\u00142\u0007\u0010¤\u0001\u001a\u00020~H\u0002J\u001b\u0010¥\u0001\u001a\u00020\u00142\u0007\u0010¦\u0001\u001a\u00020'2\u0007\u0010§\u0001\u001a\u00020'H\u0007J\u0014\u0010¨\u0001\u001a\u00020\u00142\t\b\u0002\u0010©\u0001\u001a\u00020\u000fH\u0007J\u0013\u0010ª\u0001\u001a\u00020\u00142\b\u0010«\u0001\u001a\u00030¬\u0001H\u0016J\u001a\u0010­\u0001\u001a\u00020\u00142\u0006\u0010\u001a\u00020'2\u0007\u0010®\u0001\u001a\u000202H\u0017J$\u0010¯\u0001\u001a\u00020\u00142\u0007\u0010°\u0001\u001a\u00020'2\u0007\u0010±\u0001\u001a\u00020'2\u0007\u0010®\u0001\u001a\u000202H\u0016J\u001a\u0010²\u0001\u001a\u00020\u00142\u0006\u0010\u001a\u00020'2\u0007\u0010®\u0001\u001a\u000202H\u0017J\u0007\u0010³\u0001\u001a\u00020\u0014J\u0007\u0010´\u0001\u001a\u00020\u0014J\u0007\u0010µ\u0001\u001a\u00020\u0014J9\u0010¶\u0001\u001a\u00020\u00142\u0006\u0010\u001a\u00020'2\t\b\u0002\u0010·\u0001\u001a\u00020'2\t\b\u0002\u0010¸\u0001\u001a\u00020\u000f2\t\b\u0002\u0010¹\u0001\u001a\u00020\u000fH@¢\u0006\u0003\u0010º\u0001J\u0011\u0010Q\u001a\u00020\u00142\u0007\u0010©\u0001\u001a\u00020\u000fH\u0007J\u0007\u0010»\u0001\u001a\u00020<J\u0012\u0010¼\u0001\u001a\u00020\u00142\u0007\u0010\u0001\u001a\u00020~H\u0002J\u0012\u0010½\u0001\u001a\u00020\u00142\u0007\u0010\u0001\u001a\u00020'H\u0007J\u0012\u0010¾\u0001\u001a\u00020\u00142\u0007\u0010¿\u0001\u001a\u000202H\u0007J\u0012\u0010À\u0001\u001a\u00020\u00142\t\u0010¤\u0001\u001a\u0004\u0018\u00010~J\u0007\u0010Á\u0001\u001a\u00020\u0014J\u0007\u0010Â\u0001\u001a\u00020\u0014J\u0007\u0010Ã\u0001\u001a\u00020\u0014J\u0012\u0010Ä\u0001\u001a\u00020\u00142\t\b\u0002\u0010Å\u0001\u001a\u00020\u000fJ\u001b\u0010Æ\u0001\u001a\u00020\u00142\t\b\u0002\u0010Å\u0001\u001a\u00020\u000fH@¢\u0006\u0003\u0010\u0001J\u0013\u0010Ç\u0001\u001a\u00030È\u00012\t\b\u0002\u0010É\u0001\u001a\u00020\u000fJ\u0019\u0010Ê\u0001\u001a\u00020\u00142\u0007\u0010\u0001\u001a\u00020~H@¢\u0006\u0003\u0010Ë\u0001J\u001f\u0010Ì\u0001\u001a\u00020\u00142\t\b\u0002\u0010Í\u0001\u001a\u00020\u000f2\t\b\u0002\u0010Î\u0001\u001a\u00020\u000fH\u0002J\u001b\u0010Ï\u0001\u001a\u00020\u00142\t\u0010¤\u0001\u001a\u0004\u0018\u00010~2\u0007\u0010Ð\u0001\u001a\u00020<J\u0010\u0010Ñ\u0001\u001a\u00020\u00142\u0007\u0010Ò\u0001\u001a\u00020<J\u0019\u0010Ó\u0001\u001a\u00020\u00142\u0007\u0010Ð\u0001\u001a\u00020<2\u0007\u0010Ô\u0001\u001a\u00020\u000fJ\u0013\u0010Õ\u0001\u001a\u00020\u00142\n\u0010Ö\u0001\u001a\u0005\u0018\u00010×\u0001J\u0012\u0010Ø\u0001\u001a\u00020\u00142\u0007\u0010Ù\u0001\u001a\u00020'H\u0007J\t\u0010Ú\u0001\u001a\u00020\u0014H\u0002J\u0010\u0010Û\u0001\u001a\u00020\u00142\u0007\u0010Ò\u0001\u001a\u00020<J\u000f\u0010Ü\u0001\u001a\u00020\u00142\u0006\u0010\u001a\u000202J\u0010\u0010Ý\u0001\u001a\u00020\u00142\u0007\u0010Þ\u0001\u001a\u00020\u000fJ\t\u0010ß\u0001\u001a\u00020\u0014H\u0007J\t\u0010à\u0001\u001a\u00020\u0014H\u0002J\u001d\u0010á\u0001\u001a\u00020\u00142\t\b\u0002\u0010â\u0001\u001a\u00020\u000f2\t\b\u0002\u0010Î\u0001\u001a\u00020\u000fJ\t\u0010ã\u0001\u001a\u00020\u000fH\u0007J\u0007\u0010ä\u0001\u001a\u00020\u0014J\u0007\u0010å\u0001\u001a\u00020\u0014R!\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8FX\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR!\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\b8FX\u0002¢\u0006\f\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u0010\u0010\u000bR\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001b\u0010!\u001a\u00020\"8BX\u0002¢\u0006\f\n\u0004\b%\u0010\r\u001a\u0004\b#\u0010$R$\u0010(\u001a\u00020'2\u0006\u0010&\u001a\u00020'@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R!\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\b8FX\u0002¢\u0006\f\n\u0004\b0\u0010\r\u001a\u0004\b/\u0010\u000bR\u0010\u00101\u001a\u0004\u0018\u000102X\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000102X\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u001a\u00105\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u00109\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u00106\"\u0004\b:\u00108R\u000e\u0010;\u001a\u00020<X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010@\u001a\u00020A8BX\u0002¢\u0006\f\n\u0004\bD\u0010\r\u001a\u0004\bB\u0010CR\u0010\u0010E\u001a\u00020FX\u0004¢\u0006\u0004\n\u0002\u0010GR\u000e\u0010H\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020'X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u001b\u0010K\u001a\u00020L8FX\u0002¢\u0006\f\n\u0004\bO\u0010\r\u001a\u0004\bM\u0010NR\u000e\u0010P\u001a\u00020'X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010Q\u001a\b\u0012\u0004\u0012\u00020'0RX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020TX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010U\u001a\u00020<X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010[R\u001b\u0010\\\u001a\u00020]8BX\u0002¢\u0006\f\n\u0004\b`\u0010\r\u001a\u0004\b^\u0010_R\u000e\u0010a\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010b\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u00106\"\u0004\bd\u00108R\u001a\u0010e\u001a\u00020'X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010*\"\u0004\bg\u0010,R\u001a\u0010h\u001a\u00020'X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010*\"\u0004\bj\u0010,R\u001e\u0010l\u001a\u00020\u000f2\u0006\u0010k\u001a\u00020\u000f@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\bm\u00106R\u001a\u0010n\u001a\u00020\u0018X.¢\u0006\u000e\n\u0000\u001a\u0004\bo\u0010\u001a\"\u0004\bp\u0010\u001cR!\u0010q\u001a\b\u0012\u0004\u0012\u00020\u000f0\b8FX\u0002¢\u0006\f\n\u0004\bs\u0010\r\u001a\u0004\br\u0010\u000bR#\u0010t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010u0\b8FX\u0002¢\u0006\f\n\u0004\bw\u0010\r\u001a\u0004\bv\u0010\u000bR#\u0010x\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010u0\b8FX\u0002¢\u0006\f\n\u0004\bz\u0010\r\u001a\u0004\by\u0010\u000b¨\u0006ç\u0001"}, d2 = {"Lorg/videolan/vlc/media/PlaylistManager;", "Lorg/videolan/vlc/media/MediaWrapperList$EventListener;", "Lorg/videolan/libvlc/interfaces/IMedia$EventListener;", "Lkotlinx/coroutines/CoroutineScope;", "service", "Lorg/videolan/vlc/PlaybackService;", "(Lorg/videolan/vlc/PlaybackService;)V", "abRepeat", "Landroidx/lifecycle/MutableLiveData;", "Lorg/videolan/vlc/media/ABRepeat;", "getAbRepeat", "()Landroidx/lifecycle/MutableLiveData;", "abRepeat$delegate", "Lkotlin/Lazy;", "abRepeatOn", "", "getAbRepeatOn", "abRepeatOn$delegate", "addUpdateActor", "Lkotlinx/coroutines/channels/SendChannel;", "", "getAddUpdateActor$annotations", "()V", "audioResumeStatus", "Lorg/videolan/vlc/media/ResumeStatus;", "getAudioResumeStatus", "()Lorg/videolan/vlc/media/ResumeStatus;", "setAudioResumeStatus", "(Lorg/videolan/vlc/media/ResumeStatus;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "ctx", "Landroid/content/Context;", "getCtx", "()Landroid/content/Context;", "ctx$delegate", "value", "", "currentIndex", "getCurrentIndex", "()I", "setCurrentIndex", "(I)V", "delayValue", "Lorg/videolan/vlc/media/DelayValues;", "getDelayValue", "delayValue$delegate", "endReachedFor", "", "entryUrl", "expanding", "isBenchmark", "()Z", "setBenchmark", "(Z)V", "isHardware", "setHardware", "lastPrevious", "", "loadingLastPlaylist", "mediaFactory", "Lorg/videolan/libvlc/interfaces/IMediaFactory;", "medialibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "getMedialibrary", "()Lorg/videolan/medialibrary/interfaces/Medialibrary;", "medialibrary$delegate", "mediaplayerEventListener", "org/videolan/vlc/media/PlaylistManager$mediaplayerEventListener$1", "Lorg/videolan/vlc/media/PlaylistManager$mediaplayerEventListener$1;", "newMedia", "nextIndex", "parsed", "player", "Lorg/videolan/vlc/media/PlayerController;", "getPlayer", "()Lorg/videolan/vlc/media/PlayerController;", "player$delegate", "prevIndex", "previous", "Ljava/util/Stack;", "random", "Ljava/security/SecureRandom;", "savedTime", "getSavedTime", "()J", "setSavedTime", "(J)V", "getService", "()Lorg/videolan/vlc/PlaybackService;", "settings", "Landroid/content/SharedPreferences;", "getSettings", "()Landroid/content/SharedPreferences;", "settings$delegate", "shouldDisableCookieForwarding", "shuffling", "getShuffling", "setShuffling", "startupIndex", "getStartupIndex", "setStartupIndex", "stopAfter", "getStopAfter", "setStopAfter", "<set-?>", "videoBackground", "getVideoBackground", "videoResumeStatus", "getVideoResumeStatus", "setVideoResumeStatus", "videoStatsOn", "getVideoStatsOn", "videoStatsOn$delegate", "waitForConfirmation", "Lorg/videolan/vlc/media/WaitConfirmation;", "getWaitForConfirmation", "waitForConfirmation$delegate", "waitForConfirmationAudio", "getWaitForConfirmationAudio", "waitForConfirmationAudio$delegate", "append", "list", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "index", "(Ljava/util/List;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "canRepeat", "canShuffle", "clearABRepeat", "determinePrevAndNextIndices", "expand", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeUpdate", "updateHistory", "getCurrentMedia", "getMedia", "position", "getMediaList", "getMediaListSize", "getNextMedia", "getPrevMedia", "getStartTime", "mw", "hasCurrentMedia", "hasNext", "hasPlaylist", "hasPrevious", "insertItem", "insertNext", "isAudioList", "isAudioList$vlc_android_release", "isValidPosition", "load", "mlUpdate", "avoidErasingStop", "(Ljava/util/List;IZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadLastPlaylist", "type", "loadLocations", "mediaPathList", "loadMediaMeta", "media", "moveItem", "positionStart", "positionEnd", "next", "force", "onEvent", "event", "Lorg/videolan/libvlc/interfaces/IMedia$Event;", "onItemAdded", "mrl", "onItemMoved", "indexBefore", "indexAfter", "onItemRemoved", "onServiceDestroyed", "pause", "play", "playIndex", "flags", "forceResume", "forceRestart", "(IIZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "previousTotalTime", "refreshTrackMeta", "remove", "removeLocation", "location", "resetABRepeatValues", "resetDelayValues", "resetResumeStatus", "restart", "saveCurrentMedia", "forceVideo", "saveMediaList", "saveMediaMeta", "Lkotlinx/coroutines/Job;", "end", "savePlaycount", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "savePosition", "reset", "video", "setABRepeatValue", "time", "setAudioDelay", "delay", "setDelayValue", "start", "setRenderer", "item", "Lorg/videolan/libvlc/RendererItem;", "setRepeatType", "repeatType", "setRepeatTypeFromSettings", "setSpuDelay", "setSpuTrack", "setVideoTrackEnabled", "enabled", "shuffle", "skipMedia", "stop", "systemExit", "switchToVideo", "toggleABRepeat", "toggleStats", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistManager.kt */
public final class PlaylistManager implements MediaWrapperList.EventListener, IMedia.EventListener, CoroutineScope {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final MutableLiveData<MediaWrapper> currentPlayedMedia;
    /* access modifiers changed from: private */
    public static final MediaWrapperList mediaList = new MediaWrapperList();
    /* access modifiers changed from: private */
    public static boolean playingAsAudio;
    /* access modifiers changed from: private */
    public static final MutableLiveData<Boolean> playingState;
    /* access modifiers changed from: private */
    public static final MutableStateFlow<Integer> repeating = StateFlowKt.MutableStateFlow(0);
    /* access modifiers changed from: private */
    public static final MutableLiveData<Boolean> showAudioPlayer;
    /* access modifiers changed from: private */
    public static boolean skipMediaUpdateRefresh;
    private final Lazy abRepeat$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, PlaylistManager$abRepeat$2.INSTANCE);
    private final Lazy abRepeatOn$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, PlaylistManager$abRepeatOn$2.INSTANCE);
    private final SendChannel<Unit> addUpdateActor;
    public ResumeStatus audioResumeStatus;
    private final CoroutineContext coroutineContext = Dispatchers.getMain().getImmediate().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null));
    private final Lazy ctx$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new PlaylistManager$ctx$2(this));
    private int currentIndex = -1;
    private final Lazy delayValue$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, PlaylistManager$delayValue$2.INSTANCE);
    /* access modifiers changed from: private */
    public String endReachedFor;
    /* access modifiers changed from: private */
    public String entryUrl;
    /* access modifiers changed from: private */
    public volatile boolean expanding;
    private boolean isBenchmark;
    private boolean isHardware;
    private long lastPrevious = -1;
    /* access modifiers changed from: private */
    public volatile boolean loadingLastPlaylist;
    private final IMediaFactory mediaFactory;
    private final Lazy medialibrary$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, PlaylistManager$medialibrary$2.INSTANCE);
    private final PlaylistManager$mediaplayerEventListener$1 mediaplayerEventListener;
    /* access modifiers changed from: private */
    public boolean newMedia;
    /* access modifiers changed from: private */
    public int nextIndex = -1;
    private boolean parsed;
    private final Lazy player$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new PlaylistManager$player$2(this));
    private int prevIndex = -1;
    /* access modifiers changed from: private */
    public Stack<Integer> previous = new Stack<>();
    private SecureRandom random = new SecureRandom();
    private long savedTime;
    private final PlaybackService service;
    private final Lazy settings$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new PlaylistManager$settings$2(this));
    private boolean shouldDisableCookieForwarding;
    private boolean shuffling;
    private int startupIndex = -1;
    private int stopAfter = -1;
    private boolean videoBackground;
    public ResumeStatus videoResumeStatus;
    private final Lazy videoStatsOn$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, PlaylistManager$videoStatsOn$2.INSTANCE);
    private final Lazy waitForConfirmation$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, PlaylistManager$waitForConfirmation$2.INSTANCE);
    private final Lazy waitForConfirmationAudio$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, PlaylistManager$waitForConfirmationAudio$2.INSTANCE);

    private static /* synthetic */ void getAddUpdateActor$annotations() {
    }

    public PlaylistManager(PlaybackService playbackService) {
        PlaybackService playbackService2 = playbackService;
        Intrinsics.checkNotNullParameter(playbackService2, NotificationCompat.CATEGORY_SERVICE);
        this.service = playbackService2;
        IComponentFactory factory = FactoryManager.getFactory(IMediaFactory.factoryId);
        Intrinsics.checkNotNull(factory, "null cannot be cast to non-null type org.videolan.libvlc.interfaces.IMediaFactory");
        this.mediaFactory = (IMediaFactory) factory;
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(this, (Continuation<? super AnonymousClass1>) null), 3, (Object) null);
        resetResumeStatus();
        this.addUpdateActor = ActorKt.actor$default(this, (CoroutineContext) null, -1, (CoroutineStart) null, (Function1) null, new PlaylistManager$addUpdateActor$1(this, (Continuation<? super PlaylistManager$addUpdateActor$1>) null), 13, (Object) null);
        this.mediaplayerEventListener = new PlaylistManager$mediaplayerEventListener$1(this);
    }

    public final PlaybackService getService() {
        return this.service;
    }

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\t\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001c\u001a\u00020\u000bR\u0019\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0007R\u001a\u0010\u0019\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000f¨\u0006\u001d"}, d2 = {"Lorg/videolan/vlc/media/PlaylistManager$Companion;", "", "()V", "currentPlayedMedia", "Landroidx/lifecycle/MutableLiveData;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getCurrentPlayedMedia", "()Landroidx/lifecycle/MutableLiveData;", "mediaList", "Lorg/videolan/vlc/media/MediaWrapperList;", "playingAsAudio", "", "getPlayingAsAudio", "()Z", "setPlayingAsAudio", "(Z)V", "playingState", "getPlayingState", "repeating", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "getRepeating", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "showAudioPlayer", "getShowAudioPlayer", "skipMediaUpdateRefresh", "getSkipMediaUpdateRefresh", "setSkipMediaUpdateRefresh", "hasMedia", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PlaylistManager.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MutableLiveData<Boolean> getShowAudioPlayer() {
            return PlaylistManager.showAudioPlayer;
        }

        public final MutableLiveData<MediaWrapper> getCurrentPlayedMedia() {
            return PlaylistManager.currentPlayedMedia;
        }

        public final MutableLiveData<Boolean> getPlayingState() {
            return PlaylistManager.playingState;
        }

        public final boolean getSkipMediaUpdateRefresh() {
            return PlaylistManager.skipMediaUpdateRefresh;
        }

        public final void setSkipMediaUpdateRefresh(boolean z) {
            PlaylistManager.skipMediaUpdateRefresh = z;
        }

        public final boolean hasMedia() {
            return PlaylistManager.mediaList.size() != 0;
        }

        public final MutableStateFlow<Integer> getRepeating() {
            return PlaylistManager.repeating;
        }

        public final boolean getPlayingAsAudio() {
            return PlaylistManager.playingAsAudio;
        }

        public final void setPlayingAsAudio(boolean z) {
            PlaylistManager.playingAsAudio = z;
        }
    }

    static {
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(false);
        showAudioPlayer = mutableLiveData;
        MutableLiveData<MediaWrapper> mutableLiveData2 = new MutableLiveData<>();
        mutableLiveData2.setValue(null);
        currentPlayedMedia = mutableLiveData2;
        MutableLiveData<Boolean> mutableLiveData3 = new MutableLiveData<>();
        mutableLiveData3.setValue(false);
        playingState = mutableLiveData3;
    }

    /* access modifiers changed from: private */
    public final Medialibrary getMedialibrary() {
        return (Medialibrary) this.medialibrary$delegate.getValue();
    }

    public final PlayerController getPlayer() {
        return (PlayerController) this.player$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final SharedPreferences getSettings() {
        return (SharedPreferences) this.settings$delegate.getValue();
    }

    private final Context getCtx() {
        return (Context) this.ctx$delegate.getValue();
    }

    public final int getCurrentIndex() {
        return this.currentIndex;
    }

    public final void setCurrentIndex(int i) {
        this.currentIndex = i;
        currentPlayedMedia.postValue(mediaList.getMedia(i));
    }

    public final int getStartupIndex() {
        return this.startupIndex;
    }

    public final void setStartupIndex(int i) {
        this.startupIndex = i;
    }

    public final int getStopAfter() {
        return this.stopAfter;
    }

    public final void setStopAfter(int i) {
        this.stopAfter = i;
    }

    public final boolean getShuffling() {
        return this.shuffling;
    }

    public final void setShuffling(boolean z) {
        this.shuffling = z;
    }

    public final boolean getVideoBackground() {
        return this.videoBackground;
    }

    public final boolean isBenchmark() {
        return this.isBenchmark;
    }

    public final void setBenchmark(boolean z) {
        this.isBenchmark = z;
    }

    public final boolean isHardware() {
        return this.isHardware;
    }

    public final void setHardware(boolean z) {
        this.isHardware = z;
    }

    public final long getSavedTime() {
        return this.savedTime;
    }

    public final void setSavedTime(long j) {
        this.savedTime = j;
    }

    public final MutableLiveData<ABRepeat> getAbRepeat() {
        return (MutableLiveData) this.abRepeat$delegate.getValue();
    }

    public final MutableLiveData<Boolean> getAbRepeatOn() {
        return (MutableLiveData) this.abRepeatOn$delegate.getValue();
    }

    public final MutableLiveData<Boolean> getVideoStatsOn() {
        return (MutableLiveData) this.videoStatsOn$delegate.getValue();
    }

    public final MutableLiveData<DelayValues> getDelayValue() {
        return (MutableLiveData) this.delayValue$delegate.getValue();
    }

    public final MutableLiveData<WaitConfirmation> getWaitForConfirmation() {
        return (MutableLiveData) this.waitForConfirmation$delegate.getValue();
    }

    public final MutableLiveData<WaitConfirmation> getWaitForConfirmationAudio() {
        return (MutableLiveData) this.waitForConfirmationAudio$delegate.getValue();
    }

    public final ResumeStatus getVideoResumeStatus() {
        ResumeStatus resumeStatus = this.videoResumeStatus;
        if (resumeStatus != null) {
            return resumeStatus;
        }
        Intrinsics.throwUninitializedPropertyAccessException("videoResumeStatus");
        return null;
    }

    public final void setVideoResumeStatus(ResumeStatus resumeStatus) {
        Intrinsics.checkNotNullParameter(resumeStatus, "<set-?>");
        this.videoResumeStatus = resumeStatus;
    }

    public final ResumeStatus getAudioResumeStatus() {
        ResumeStatus resumeStatus = this.audioResumeStatus;
        if (resumeStatus != null) {
            return resumeStatus;
        }
        Intrinsics.throwUninitializedPropertyAccessException("audioResumeStatus");
        return null;
    }

    public final void setAudioResumeStatus(ResumeStatus resumeStatus) {
        Intrinsics.checkNotNullParameter(resumeStatus, "<set-?>");
        this.audioResumeStatus = resumeStatus;
    }

    public final boolean hasCurrentMedia() {
        return isValidPosition(this.currentIndex);
    }

    public final boolean canRepeat() {
        return mediaList.size() > 0;
    }

    public final boolean hasPlaylist() {
        return mediaList.size() > 1;
    }

    public final boolean canShuffle() {
        return mediaList.size() > 2;
    }

    public final boolean isValidPosition(int i) {
        return i >= 0 && i < mediaList.size();
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$1", f = "PlaylistManager.kt", i = {}, l = {173}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.media.PlaylistManager$1  reason: invalid class name */
    /* compiled from: PlaylistManager.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ PlaylistManager this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (PlaylistManager.Companion.getRepeating().emit(Boxing.boxInt(this.this$0.getSettings().getInt("audio_repeat_mode", 0)), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public final void resetResumeStatus() {
        String string = getSettings().getString(SettingsKt.KEY_VIDEO_CONFIRM_RESUME, Constants.GROUP_VIDEOS_FOLDER);
        setVideoResumeStatus(Intrinsics.areEqual((Object) string, (Object) "2") ? ResumeStatus.ASK : Intrinsics.areEqual((Object) string, (Object) Constants.GROUP_VIDEOS_FOLDER) ? ResumeStatus.ALWAYS : ResumeStatus.NEVER);
        String string2 = getSettings().getString(SettingsKt.KEY_AUDIO_CONFIRM_RESUME, Constants.GROUP_VIDEOS_FOLDER);
        setAudioResumeStatus(Intrinsics.areEqual((Object) string2, (Object) "2") ? ResumeStatus.ASK : Intrinsics.areEqual((Object) string2, (Object) Constants.GROUP_VIDEOS_FOLDER) ? ResumeStatus.ALWAYS : ResumeStatus.NEVER);
        getWaitForConfirmation().postValue(null);
        getWaitForConfirmationAudio().postValue(null);
    }

    public final void loadLocations(List<String> list, int i) {
        Intrinsics.checkNotNullParameter(list, "mediaPathList");
        Log.d("VLC/PlaylistManager", "loadLocations with values: ", new Exception("Call stack"));
        for (String str : list) {
            try {
                Log.d("VLC/PlaylistManager", "Media location: " + str);
            } catch (NullPointerException e) {
                Log.d("VLC/PlaylistManager", "Media crash", e);
            }
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PlaylistManager$loadLocations$2(this, i, list, (Continuation<? super PlaylistManager$loadLocations$2>) null), 3, (Object) null);
    }

    public static /* synthetic */ Object load$default(PlaylistManager playlistManager, List list, int i, boolean z, boolean z2, Continuation continuation, int i2, Object obj) {
        return playlistManager.load(list, i, (i2 & 4) != 0 ? false : z, (i2 & 8) != 0 ? false : z2, continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01ad A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x01ae  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01bf  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01db A[LOOP:1: B:67:0x01d5->B:69:0x01db, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0237  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0031  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object load(java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper> r21, int r22, boolean r23, boolean r24, kotlin.coroutines.Continuation<? super kotlin.Unit> r25) {
        /*
            r20 = this;
            r1 = r20
            r0 = r25
            boolean r2 = r0 instanceof org.videolan.vlc.media.PlaylistManager$load$1
            if (r2 == 0) goto L_0x0018
            r2 = r0
            org.videolan.vlc.media.PlaylistManager$load$1 r2 = (org.videolan.vlc.media.PlaylistManager$load$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r0 = r2.label
            int r0 = r0 - r4
            r2.label = r0
            goto L_0x001d
        L_0x0018:
            org.videolan.vlc.media.PlaylistManager$load$1 r2 = new org.videolan.vlc.media.PlaylistManager$load$1
            r2.<init>(r1, r0)
        L_0x001d:
            java.lang.Object r0 = r2.result
            java.lang.Object r11 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r2.label
            java.lang.String r12 = "Media location: "
            r13 = 4
            r4 = 2
            r14 = 3
            r15 = 0
            r10 = 1
            java.lang.String r9 = "VLC/PlaylistManager"
            r8 = 0
            if (r3 == 0) goto L_0x0084
            if (r3 == r10) goto L_0x0068
            if (r3 == r4) goto L_0x005b
            if (r3 == r14) goto L_0x0050
            if (r3 != r13) goto L_0x0048
            java.lang.Object r3 = r2.L$1
            org.videolan.vlc.media.MediaWrapperList r3 = (org.videolan.vlc.media.MediaWrapperList) r3
            java.lang.Object r2 = r2.L$0
            org.videolan.vlc.media.PlaylistManager r2 = (org.videolan.vlc.media.PlaylistManager) r2
            kotlin.ResultKt.throwOnFailure(r0)
            r17 = r9
            goto L_0x01b4
        L_0x0048:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0050:
            java.lang.Object r3 = r2.L$0
            org.videolan.vlc.media.PlaylistManager r3 = (org.videolan.vlc.media.PlaylistManager) r3
            kotlin.ResultKt.throwOnFailure(r0)
            r17 = r9
            goto L_0x0191
        L_0x005b:
            boolean r3 = r2.Z$0
            java.lang.Object r4 = r2.L$0
            org.videolan.vlc.media.PlaylistManager r4 = (org.videolan.vlc.media.PlaylistManager) r4
            kotlin.ResultKt.throwOnFailure(r0)
            r17 = r9
            goto L_0x0179
        L_0x0068:
            boolean r3 = r2.Z$1
            boolean r5 = r2.Z$0
            int r6 = r2.I$0
            java.lang.Object r7 = r2.L$1
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r13 = r2.L$0
            org.videolan.vlc.media.PlaylistManager r13 = (org.videolan.vlc.media.PlaylistManager) r13
            kotlin.ResultKt.throwOnFailure(r0)
            r18 = r6
            r6 = r3
            r3 = r18
            r19 = r13
            r13 = r5
            r5 = r19
            goto L_0x00a5
        L_0x0084:
            kotlin.ResultKt.throwOnFailure(r0)
            r2.L$0 = r1
            r0 = r21
            r2.L$1 = r0
            r3 = r22
            r2.I$0 = r3
            r5 = r23
            r2.Z$0 = r5
            r6 = r24
            r2.Z$1 = r6
            r2.label = r10
            java.lang.Object r7 = saveMediaList$default(r1, r8, r2, r10, r15)
            if (r7 != r11) goto L_0x00a2
            return r11
        L_0x00a2:
            r7 = r0
            r13 = r5
            r5 = r1
        L_0x00a5:
            savePosition$default(r5, r8, r8, r14, r15)
            org.videolan.vlc.media.MediaWrapperList r0 = mediaList
            r10 = r5
            org.videolan.vlc.media.MediaWrapperList$EventListener r10 = (org.videolan.vlc.media.MediaWrapperList.EventListener) r10
            r0.removeEventListener(r10)
            java.util.Stack<java.lang.Integer> r0 = r5.previous
            r0.clear()
            r5.videoBackground = r8
            java.lang.Exception r0 = new java.lang.Exception
            java.lang.String r14 = "Call stack"
            r0.<init>(r14)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            java.lang.String r14 = "load with values: "
            android.util.Log.d(r9, r14, r0)
            r0 = r7
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r14 = r0.iterator()
        L_0x00cc:
            boolean r0 = r14.hasNext()
            if (r0 == 0) goto L_0x00f9
            java.lang.Object r0 = r14.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ NullPointerException -> 0x00ef }
            r4.<init>()     // Catch:{ NullPointerException -> 0x00ef }
            r4.append(r12)     // Catch:{ NullPointerException -> 0x00ef }
            android.net.Uri r0 = r0.getUri()     // Catch:{ NullPointerException -> 0x00ef }
            r4.append(r0)     // Catch:{ NullPointerException -> 0x00ef }
            java.lang.String r0 = r4.toString()     // Catch:{ NullPointerException -> 0x00ef }
            android.util.Log.d(r9, r0)     // Catch:{ NullPointerException -> 0x00ef }
            goto L_0x00f7
        L_0x00ef:
            r0 = move-exception
            java.lang.String r4 = "Media crash"
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            android.util.Log.d(r9, r4, r0)
        L_0x00f7:
            r4 = 2
            goto L_0x00cc
        L_0x00f9:
            org.videolan.vlc.media.MediaWrapperList r0 = mediaList
            r0.replaceWith(r7)
            org.videolan.vlc.media.PlaylistManager$Companion r4 = Companion
            boolean r4 = r4.hasMedia()
            if (r4 != 0) goto L_0x010e
            java.lang.String r0 = "Warning: empty media list, nothing to play !"
            android.util.Log.w(r9, r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x010e:
            boolean r4 = r5.isValidPosition(r3)
            if (r4 == 0) goto L_0x0118
            r5.setCurrentIndex(r3)
            goto L_0x0122
        L_0x0118:
            r5.setCurrentIndex(r8)
            if (r3 < 0) goto L_0x011f
            r4 = r3
            goto L_0x0120
        L_0x011f:
            r4 = 0
        L_0x0120:
            r5.startupIndex = r4
        L_0x0122:
            r0.addEventListener(r10)
            org.videolan.tools.Settings r0 = org.videolan.tools.Settings.INSTANCE
            org.videolan.resources.AppContextProvider r4 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.Context r4 = r4.getAppContext()
            java.lang.Object r0 = r0.getInstance(r4)
            android.content.SharedPreferences r0 = (android.content.SharedPreferences) r0
            java.lang.String r4 = "audio_stop_after"
            r7 = -1
            if (r6 != 0) goto L_0x013f
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            org.videolan.tools.SettingsKt.putSingle(r0, r4, r6)
        L_0x013f:
            int r0 = r0.getInt(r4, r7)
            r5.stopAfter = r0
            if (r0 >= r3) goto L_0x0149
            r5.stopAfter = r7
        L_0x0149:
            r5.clearABRepeat()
            org.videolan.vlc.media.PlayerController r0 = r5.getPlayer()
            r3 = 1065353216(0x3f800000, float:1.0)
            r0.setRate(r3, r8)
            int r4 = r5.currentIndex
            r2.L$0 = r5
            r2.L$1 = r15
            r2.Z$0 = r13
            r3 = 2
            r2.label = r3
            r0 = 0
            r6 = 0
            r7 = 0
            r10 = 14
            r14 = 0
            r3 = r5
            r16 = r5
            r5 = r0
            r8 = r2
            r17 = r9
            r9 = r10
            r10 = r14
            java.lang.Object r0 = playIndex$default(r3, r4, r5, r6, r7, r8, r9, r10)
            if (r0 != r11) goto L_0x0176
            return r11
        L_0x0176:
            r3 = r13
            r4 = r16
        L_0x0179:
            org.videolan.vlc.PlaybackService r0 = r4.service
            r0.onPlaylistLoaded()
            if (r3 == 0) goto L_0x0200
            org.videolan.vlc.PlaybackService r0 = r4.service
            android.content.Context r0 = (android.content.Context) r0
            r2.L$0 = r4
            r3 = 3
            r2.label = r3
            java.lang.Object r0 = org.videolan.vlc.util.KextensionsKt.awaitMedialibraryStarted(r0, r2)
            if (r0 != r11) goto L_0x0190
            return r11
        L_0x0190:
            r3 = r4
        L_0x0191:
            org.videolan.vlc.media.MediaWrapperList r0 = mediaList
            kotlinx.coroutines.CoroutineDispatcher r4 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4
            org.videolan.vlc.media.PlaylistManager$load$3 r5 = new org.videolan.vlc.media.PlaylistManager$load$3
            r5.<init>(r15)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r2.L$0 = r3
            r2.L$1 = r0
            r6 = 4
            r2.label = r6
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r4, r5, r2)
            if (r2 != r11) goto L_0x01ae
            return r11
        L_0x01ae:
            r18 = r3
            r3 = r0
            r0 = r2
            r2 = r18
        L_0x01b4:
            java.util.List r0 = (java.util.List) r0
            r3.replaceWith(r0)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = r2.getCurrentMedia()
            if (r0 == 0) goto L_0x01c2
            r2.refreshTrackMeta(r0)
        L_0x01c2:
            java.lang.String r0 = "load after ml update with values: "
            r3 = r17
            android.util.Log.d(r3, r0)
            org.videolan.vlc.media.MediaWrapperList r0 = mediaList
            java.util.List r0 = r0.getCopy()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x01d5:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x01f5
            java.lang.Object r4 = r0.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r4
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r12)
            android.net.Uri r4 = r4.getUri()
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            android.util.Log.d(r3, r4)
            goto L_0x01d5
        L_0x01f5:
            org.videolan.vlc.PlaybackService r0 = r2.service
            r0.onMediaListChanged()
            org.videolan.vlc.PlaybackService r0 = r2.service
            r0.showNotification()
            r4 = r2
        L_0x0200:
            android.content.SharedPreferences r0 = r4.getSettings()
            java.lang.String r2 = "audio_force_shuffle"
            r3 = 0
            boolean r0 = r0.getBoolean(r2, r3)
            if (r0 == 0) goto L_0x0227
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = r4.getCurrentMedia()
            if (r0 == 0) goto L_0x0227
            int r0 = r0.getType()
            r2 = 1
            if (r0 != r2) goto L_0x0227
            boolean r0 = r4.shuffling
            if (r0 != 0) goto L_0x0227
            boolean r0 = r4.canShuffle()
            if (r0 == 0) goto L_0x0227
            r4.shuffle()
        L_0x0227:
            android.content.SharedPreferences r0 = r4.getSettings()
            java.lang.String r2 = "sleep_timer_default_interval"
            r5 = -1
            long r7 = r0.getLong(r2, r5)
            int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r0 == 0) goto L_0x0273
            org.videolan.vlc.PlaybackService r0 = r4.service
            android.content.SharedPreferences r7 = r4.getSettings()
            java.lang.String r8 = "sleep_timer_default_wait"
            boolean r7 = r7.getBoolean(r8, r3)
            r0.setWaitForMediaEnd(r7)
            org.videolan.vlc.PlaybackService r0 = r4.service
            android.content.SharedPreferences r7 = r4.getSettings()
            java.lang.String r8 = "sleep_timer_default_reset_interaction"
            boolean r7 = r7.getBoolean(r8, r3)
            r0.setResetOnInteraction(r7)
            java.util.Calendar r0 = java.util.Calendar.getInstance()
            long r7 = r0.getTimeInMillis()
            android.content.SharedPreferences r9 = r4.getSettings()
            long r5 = r9.getLong(r2, r5)
            long r7 = r7 + r5
            r0.setTimeInMillis(r7)
            r2 = 13
            r0.set(r2, r3)
            org.videolan.vlc.PlaybackService r2 = r4.service
            r2.setSleepTimer(r0)
        L_0x0273:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlaylistManager.load(java.util.List, int, boolean, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ boolean loadLastPlaylist$default(PlaylistManager playlistManager, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return playlistManager.loadLastPlaylist(i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean loadLastPlaylist(int r12) {
        /*
            r11 = this;
            boolean r0 = r11.loadingLastPlaylist
            r1 = 1
            if (r0 == 0) goto L_0x0006
            return r1
        L_0x0006:
            r11.loadingLastPlaylist = r1
            if (r12 == 0) goto L_0x0012
            if (r12 == r1) goto L_0x000f
            java.lang.String r0 = "current_media_resume"
            goto L_0x0014
        L_0x000f:
            java.lang.String r0 = "current_media"
            goto L_0x0014
        L_0x0012:
            java.lang.String r0 = "current_song"
        L_0x0014:
            if (r12 == 0) goto L_0x001e
            if (r12 == r1) goto L_0x001b
            java.lang.String r2 = "media_list_resume"
            goto L_0x0020
        L_0x001b:
            java.lang.String r2 = "media_list"
            goto L_0x0020
        L_0x001e:
            java.lang.String r2 = "audio_list"
        L_0x0020:
            r3 = 0
            if (r12 != 0) goto L_0x0025
            r12 = 1
            goto L_0x0026
        L_0x0025:
            r12 = 0
        L_0x0026:
            android.content.SharedPreferences r4 = r11.getSettings()
            java.lang.String r5 = ""
            java.lang.String r0 = r4.getString(r0, r5)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            if (r0 == 0) goto L_0x00b3
            int r0 = r0.length()
            if (r0 != 0) goto L_0x003c
            goto L_0x00b3
        L_0x003c:
            android.content.SharedPreferences r0 = r11.getSettings()
            r4 = 0
            java.lang.String r0 = r0.getString(r2, r4)
            if (r0 == 0) goto L_0x0096
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            kotlin.text.Regex r2 = new kotlin.text.Regex
            java.lang.String r5 = " "
            r2.<init>((java.lang.String) r5)
            java.util.List r0 = r2.split(r0, r3)
            if (r0 == 0) goto L_0x0096
            boolean r2 = r0.isEmpty()
            if (r2 != 0) goto L_0x0085
            int r2 = r0.size()
            java.util.ListIterator r2 = r0.listIterator(r2)
        L_0x0064:
            boolean r5 = r2.hasPrevious()
            if (r5 == 0) goto L_0x0085
            java.lang.Object r5 = r2.previous()
            java.lang.String r5 = (java.lang.String) r5
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            int r5 = r5.length()
            if (r5 != 0) goto L_0x0079
            goto L_0x0064
        L_0x0079:
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            int r2 = r2.nextIndex()
            int r2 = r2 + r1
            java.util.List r0 = kotlin.collections.CollectionsKt.take(r0, r2)
            goto L_0x0089
        L_0x0085:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0089:
            if (r0 == 0) goto L_0x0096
            java.util.Collection r0 = (java.util.Collection) r0
            java.lang.String[] r2 = new java.lang.String[r3]
            java.lang.Object[] r0 = r0.toArray(r2)
            java.lang.String[] r0 = (java.lang.String[]) r0
            goto L_0x0097
        L_0x0096:
            r0 = r4
        L_0x0097:
            if (r0 == 0) goto L_0x00b0
            int r2 = r0.length
            if (r2 != 0) goto L_0x009d
            goto L_0x00b0
        L_0x009d:
            r5 = r11
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            org.videolan.vlc.media.PlaylistManager$loadLastPlaylist$1 r2 = new org.videolan.vlc.media.PlaylistManager$loadLastPlaylist$1
            r2.<init>(r11, r12, r0, r4)
            r8 = r2
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9 = 3
            r10 = 0
            r6 = 0
            r7 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r5, r6, r7, r8, r9, r10)
            return r1
        L_0x00b0:
            r11.loadingLastPlaylist = r3
            return r3
        L_0x00b3:
            r11.loadingLastPlaylist = r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlaylistManager.loadLastPlaylist(int):boolean");
    }

    public final void play() {
        if (Companion.hasMedia()) {
            getPlayer().play();
        }
    }

    public final void pause() {
        if (getPlayer().pause()) {
            savePosition$default(this, false, false, 3, (Object) null);
            Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PlaylistManager$pause$1(this, (Continuation<? super PlaylistManager$pause$1>) null), 3, (Object) null);
            MediaWrapper currentMedia = getCurrentMedia();
            if (currentMedia != null && currentMedia.isPodcast()) {
                saveMediaMeta$default(this, false, 1, (Object) null);
            }
        }
    }

    public static /* synthetic */ void next$default(PlaylistManager playlistManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        playlistManager.next(z);
    }

    public final void next(boolean z) {
        int i;
        MediaWrapperList mediaWrapperList = mediaList;
        MediaWrapper media = mediaWrapperList.getMedia(this.currentIndex);
        if (media != null && media.getType() == 0) {
            saveMediaMeta$default(this, false, 1, (Object) null);
        }
        int size = mediaWrapperList.size();
        if (z || repeating.getValue().intValue() != 1) {
            this.previous.push(Integer.valueOf(this.currentIndex));
            int i2 = this.startupIndex;
            if (i2 != -1) {
                setCurrentIndex(i2);
                this.startupIndex = -1;
            } else {
                setCurrentIndex(this.nextIndex);
            }
            if (size == 0 || (i = this.currentIndex) < 0 || i >= size) {
                Log.w("VLC/PlaylistManager", "Warning: invalid next index, aborted !");
                stop$default(this, false, false, 3, (Object) null);
                return;
            }
            this.videoBackground = this.videoBackground || (!getPlayer().isVideoPlaying() && getPlayer().canSwitchToVideo());
            if (repeating.getValue().intValue() == 1) {
                setRepeatType(0);
            }
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PlaylistManager$next$2(this, (Continuation<? super PlaylistManager$next$2>) null), 3, (Object) null);
    }

    public final void restart() {
        boolean z = getPlayer().isPlaying() && isAudioList$vlc_android_release();
        stop$default(this, false, false, 3, (Object) null);
        if (z) {
            PlaybackService.Companion.loadLastAudio(this.service);
        }
    }

    public static /* synthetic */ void stop$default(PlaylistManager playlistManager, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        playlistManager.stop(z, z2);
    }

    public final void stop(boolean z, boolean z2) {
        Job job;
        clearABRepeat();
        if (this.stopAfter != -1) {
            SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(AppContextProvider.INSTANCE.getAppContext()), SettingsKt.AUDIO_STOP_AFTER, Integer.valueOf(this.stopAfter));
        }
        this.stopAfter = -1;
        this.videoBackground = false;
        MediaWrapper currentMedia = getCurrentMedia();
        if (currentMedia != null) {
            savePosition$default(this, false, z2, 1, (Object) null);
            job = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new PlaylistManager$stop$job$1$1(this, z2, currentMedia, (Continuation<? super PlaylistManager$stop$job$1$1>) null), 1, (Object) null);
        } else {
            job = null;
        }
        this.service.setSleepTimer((Calendar) null);
        MediaWrapperList mediaWrapperList = mediaList;
        mediaWrapperList.removeEventListener(this);
        this.previous.clear();
        setCurrentIndex(-1);
        if (z) {
            PlayerController.release$default(getPlayer(), (MediaPlayer) null, 1, (Object) null);
        } else {
            getPlayer().restart();
        }
        mediaWrapperList.clear();
        showAudioPlayer.setValue(false);
        this.service.onPlaybackStopped(z);
        LocalBroadcastManager.getInstance(getCtx()).sendBroadcast(new Intent(Constants.EXIT_PLAYER));
        if (z) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new PlaylistManager$stop$1(job, this, (Continuation<? super PlaylistManager$stop$1>) null), 1, (Object) null);
        }
        playingState.setValue(false);
    }

    public final void previous(boolean z) {
        MediaWrapperList mediaWrapperList = mediaList;
        MediaWrapper media = mediaWrapperList.getMedia(this.currentIndex);
        if (media != null && media.getType() == 0) {
            saveMediaMeta$default(this, false, 1, (Object) null);
        }
        if (!hasPrevious() || (!z && getPlayer().getSeekable() && getPlayer().getCurrentTime() >= CoroutineLiveDataKt.DEFAULT_TIMEOUT && (this.lastPrevious == -1 || System.currentTimeMillis() - this.lastPrevious >= CoroutineLiveDataKt.DEFAULT_TIMEOUT))) {
            getPlayer().setPosition(0.0f);
            this.lastPrevious = System.currentTimeMillis();
            return;
        }
        int size = mediaWrapperList.size();
        setCurrentIndex(this.prevIndex);
        if (this.previous.size() > 0) {
            this.previous.pop();
        }
        if (size == 0 || this.prevIndex < 0 || this.currentIndex >= size) {
            Log.w("VLC/PlaylistManager", "Warning: invalid previous index, aborted !");
            getPlayer().stop();
            return;
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PlaylistManager$previous$2(this, (Continuation<? super PlaylistManager$previous$2>) null), 3, (Object) null);
        this.lastPrevious = -1;
    }

    public final void shuffle() {
        if (this.shuffling) {
            this.previous.clear();
        }
        this.shuffling = !this.shuffling;
        savePosition$default(this, false, false, 3, (Object) null);
        Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PlaylistManager$shuffle$1(this, (Continuation<? super PlaylistManager$shuffle$1>) null), 3, (Object) null);
    }

    private final void setRepeatTypeFromSettings() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new PlaylistManager$setRepeatTypeFromSettings$1(this, (Continuation<? super PlaylistManager$setRepeatTypeFromSettings$1>) null), 3, (Object) null);
    }

    public final void setRepeatType(int i) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new PlaylistManager$setRepeatType$1(i, (Continuation<? super PlaylistManager$setRepeatType$1>) null), 3, (Object) null);
        MediaWrapper currentMedia = getCurrentMedia();
        if (currentMedia == null || currentMedia.getType() != 0) {
            SettingsKt.putSingle(getSettings(), "audio_repeat_mode", repeating.getValue());
        } else {
            SettingsKt.putSingle(getSettings(), "video_repeat_mode", repeating.getValue());
        }
        savePosition$default(this, false, false, 3, (Object) null);
        Job unused2 = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PlaylistManager$setRepeatType$2(this, (Continuation<? super PlaylistManager$setRepeatType$2>) null), 3, (Object) null);
    }

    public final void setRenderer(RendererItem rendererItem) {
        getPlayer().setRenderer(rendererItem);
        MutableLiveData<Boolean> mutableLiveData = showAudioPlayer;
        boolean z = true;
        if (PlayerController.Companion.getPlaybackState() == 1 || (rendererItem == null && getPlayer().isVideoPlaying())) {
            z = false;
        }
        mutableLiveData.setValue(Boolean.valueOf(z));
    }

    public static /* synthetic */ Object playIndex$default(PlaylistManager playlistManager, int i, int i2, boolean z, boolean z2, Continuation continuation, int i3, Object obj) {
        return playlistManager.playIndex(i, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? false : z, (i3 & 8) != 0 ? false : z2, continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:153:0x03df A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01e0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01e1  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01ec  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01f2  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0033  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object playIndex(int r25, int r26, boolean r27, boolean r28, kotlin.coroutines.Continuation<? super kotlin.Unit> r29) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            r2 = r29
            boolean r3 = r2 instanceof org.videolan.vlc.media.PlaylistManager$playIndex$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            org.videolan.vlc.media.PlaylistManager$playIndex$1 r3 = (org.videolan.vlc.media.PlaylistManager$playIndex$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            org.videolan.vlc.media.PlaylistManager$playIndex$1 r3 = new org.videolan.vlc.media.PlaylistManager$playIndex$1
            r3.<init>(r0, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r10 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r3.label
            java.lang.String r5 = "getUri(...)"
            r6 = 8
            r11 = 4
            r7 = 3
            r8 = 2
            r12 = 0
            r13 = 0
            r14 = 1
            if (r4 == 0) goto L_0x0091
            if (r4 == r14) goto L_0x007a
            if (r4 == r8) goto L_0x005f
            if (r4 == r7) goto L_0x004c
            if (r4 != r11) goto L_0x0044
            java.lang.Object r1 = r3.L$0
            org.videolan.vlc.media.PlaylistManager r1 = (org.videolan.vlc.media.PlaylistManager) r1
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x03e0
        L_0x0044:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x004c:
            java.lang.Object r1 = r3.L$2
            org.videolan.libvlc.interfaces.IMedia r1 = (org.videolan.libvlc.interfaces.IMedia) r1
            java.lang.Object r4 = r3.L$1
            org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r4
            java.lang.Object r5 = r3.L$0
            org.videolan.vlc.media.PlaylistManager r5 = (org.videolan.vlc.media.PlaylistManager) r5
            kotlin.ResultKt.throwOnFailure(r2)
            r2 = r1
            r1 = r5
            goto L_0x03c3
        L_0x005f:
            int r1 = r3.I$2
            boolean r4 = r3.Z$1
            boolean r6 = r3.Z$0
            int r8 = r3.I$1
            int r9 = r3.I$0
            java.lang.Object r15 = r3.L$1
            org.videolan.medialibrary.interfaces.media.MediaWrapper r15 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r15
            java.lang.Object r11 = r3.L$0
            org.videolan.vlc.media.PlaylistManager r11 = (org.videolan.vlc.media.PlaylistManager) r11
            kotlin.ResultKt.throwOnFailure(r2)
            r19 = r8
            r18 = r9
            goto L_0x01e8
        L_0x007a:
            int r1 = r3.I$2
            boolean r4 = r3.Z$1
            boolean r9 = r3.Z$0
            int r11 = r3.I$1
            int r15 = r3.I$0
            java.lang.Object r7 = r3.L$1
            org.videolan.medialibrary.interfaces.media.MediaWrapper r7 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r7
            java.lang.Object r8 = r3.L$0
            org.videolan.vlc.media.PlaylistManager r8 = (org.videolan.vlc.media.PlaylistManager) r8
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x017e
        L_0x0091:
            kotlin.ResultKt.throwOnFailure(r2)
            boolean r2 = r0.videoBackground
            if (r2 != 0) goto L_0x00af
            org.videolan.vlc.media.PlayerController r2 = r24.getPlayer()
            boolean r2 = r2.isVideoPlaying()
            if (r2 != 0) goto L_0x00ad
            org.videolan.vlc.media.PlayerController r2 = r24.getPlayer()
            boolean r2 = r2.canSwitchToVideo()
            if (r2 == 0) goto L_0x00ad
            goto L_0x00af
        L_0x00ad:
            r2 = 0
            goto L_0x00b0
        L_0x00af:
            r2 = 1
        L_0x00b0:
            r0.videoBackground = r2
            org.videolan.vlc.media.MediaWrapperList r2 = mediaList
            int r4 = r2.size()
            java.lang.String r7 = "VLC/PlaylistManager"
            if (r4 != 0) goto L_0x00c4
            java.lang.String r1 = "Warning: empty media list, nothing to play !"
            android.util.Log.w(r7, r1)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x00c4:
            boolean r4 = r24.isValidPosition(r25)
            if (r4 == 0) goto L_0x00cc
            r4 = r1
            goto L_0x00e3
        L_0x00cc:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r8 = "Warning: index "
            r4.<init>(r8)
            r4.append(r1)
            java.lang.String r8 = " out of bounds"
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            android.util.Log.w(r7, r4)
            r4 = 0
        L_0x00e3:
            r0.setCurrentIndex(r4)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r7 = r2.getMedia(r1)
            if (r7 != 0) goto L_0x00ef
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x00ef:
            int r2 = r7.getType()
            if (r2 != 0) goto L_0x00fd
            boolean r2 = org.videolan.tools.KotlinExtensionsKt.isAppStarted()
            if (r2 != 0) goto L_0x00fd
            r0.videoBackground = r14
        L_0x00fd:
            int r2 = r7.getType()
            if (r2 != 0) goto L_0x010f
            org.videolan.vlc.media.PlayerController r2 = r24.getPlayer()
            boolean r2 = r2.isVideoPlaying()
            if (r2 == 0) goto L_0x010f
            r2 = 1
            goto L_0x0110
        L_0x010f:
            r2 = 0
        L_0x0110:
            r24.setRepeatTypeFromSettings()
            boolean r4 = r0.videoBackground
            if (r4 != 0) goto L_0x011c
            if (r2 == 0) goto L_0x011c
            r7.addFlags(r14)
        L_0x011c:
            boolean r4 = r0.videoBackground
            if (r4 == 0) goto L_0x0123
            r7.addFlags(r6)
        L_0x0123:
            boolean r4 = r0.isBenchmark
            if (r4 == 0) goto L_0x012c
            r4 = 16
            r7.addFlags(r4)
        L_0x012c:
            r0.parsed = r13
            org.videolan.vlc.media.PlayerController r4 = r24.getPlayer()
            r4.setSwitchToVideo(r13)
            android.net.Uri r4 = r7.getUri()
            java.lang.String r4 = r4.getScheme()
            java.lang.String r8 = "content"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r8)
            if (r4 == 0) goto L_0x016f
            kotlinx.coroutines.CoroutineDispatcher r4 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4
            org.videolan.vlc.media.PlaylistManager$playIndex$2 r8 = new org.videolan.vlc.media.PlaylistManager$playIndex$2
            r8.<init>(r7, r12)
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r3.L$0 = r0
            r3.L$1 = r7
            r3.I$0 = r1
            r9 = r26
            r3.I$1 = r9
            r11 = r27
            r3.Z$0 = r11
            r15 = r28
            r3.Z$1 = r15
            r3.I$2 = r2
            r3.label = r14
            java.lang.Object r4 = kotlinx.coroutines.BuildersKt.withContext(r4, r8, r3)
            if (r4 != r10) goto L_0x0175
            return r10
        L_0x016f:
            r9 = r26
            r11 = r27
            r15 = r28
        L_0x0175:
            r8 = r0
            r4 = r15
            r15 = r1
            r1 = r2
            r23 = r11
            r11 = r9
            r9 = r23
        L_0x017e:
            int r2 = r7.getType()
            if (r2 != 0) goto L_0x01bc
            if (r1 != 0) goto L_0x01bc
            org.videolan.vlc.media.PlayerController r2 = r8.getPlayer()
            boolean r2 = r2.getHasRenderer()
            if (r2 != 0) goto L_0x01bc
            boolean r2 = r7.hasFlag(r6)
            if (r2 == 0) goto L_0x0197
            goto L_0x01bc
        L_0x0197:
            org.videolan.vlc.media.PlayerController r1 = r8.getPlayer()
            boolean r1 = r1.isPlaying()
            if (r1 == 0) goto L_0x01a8
            org.videolan.vlc.media.PlayerController r1 = r8.getPlayer()
            r1.stop()
        L_0x01a8:
            org.videolan.vlc.gui.video.VideoPlayerActivity$Companion r1 = org.videolan.vlc.gui.video.VideoPlayerActivity.Companion
            android.content.Context r2 = r8.getCtx()
            android.net.Uri r3 = r7.getUri()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)
            int r4 = r8.currentIndex
            r1.startOpened(r2, r3, r4)
            goto L_0x03e5
        L_0x01bc:
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            org.videolan.vlc.media.PlaylistManager$playIndex$uri$1 r6 = new org.videolan.vlc.media.PlaylistManager$playIndex$uri$1
            r6.<init>(r7, r12)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r3.L$0 = r8
            r3.L$1 = r7
            r3.I$0 = r15
            r3.I$1 = r11
            r3.Z$0 = r9
            r3.Z$1 = r4
            r3.I$2 = r1
            r12 = 2
            r3.label = r12
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r2, r6, r3)
            if (r2 != r10) goto L_0x01e1
            return r10
        L_0x01e1:
            r6 = r9
            r19 = r11
            r18 = r15
            r15 = r7
            r11 = r8
        L_0x01e8:
            android.net.Uri r2 = (android.net.Uri) r2
            if (r2 != 0) goto L_0x01f2
            r11.skipMedia()
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x01f2:
            boolean r7 = r11.isBenchmark
            if (r7 != 0) goto L_0x0212
            org.videolan.resources.AndroidDevices r7 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r7 = r7.isTv()
            if (r7 == 0) goto L_0x0212
            if (r1 == 0) goto L_0x0212
            org.videolan.vlc.gui.video.VideoPlayerActivity$Companion r7 = org.videolan.vlc.gui.video.VideoPlayerActivity.Companion
            android.content.Context r8 = r11.getCtx()
            android.net.Uri r9 = r15.getUri()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r5)
            int r5 = r11.currentIndex
            r7.startOpened(r8, r9, r5)
        L_0x0212:
            r5 = 52
            long r7 = r15.getMetaLong(r5)
            r16 = 0
            int r5 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r5 <= 0) goto L_0x0236
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r2)
            r2 = 35
            r5.append(r2)
            r5.append(r7)
            java.lang.String r2 = r5.toString()
            android.net.Uri r2 = android.net.Uri.parse(r2)
        L_0x0236:
            java.lang.String r5 = "getTitle(...)"
            java.lang.String r7 = "playback_history"
            if (r1 == 0) goto L_0x0296
            if (r4 != 0) goto L_0x0260
            org.videolan.vlc.media.ResumeStatus r1 = r11.getVideoResumeStatus()
            org.videolan.vlc.media.ResumeStatus r4 = org.videolan.vlc.media.ResumeStatus.NEVER
            if (r1 == r4) goto L_0x0260
            org.videolan.tools.Settings r1 = org.videolan.tools.Settings.INSTANCE
            org.videolan.resources.AppContextProvider r4 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.Context r4 = r4.getAppContext()
            java.lang.Object r1 = r1.getInstance(r4)
            android.content.SharedPreferences r1 = (android.content.SharedPreferences) r1
            boolean r1 = r1.getBoolean(r7, r14)
            if (r1 != 0) goto L_0x025b
            goto L_0x0260
        L_0x025b:
            long r7 = r11.getStartTime(r15)
            goto L_0x0262
        L_0x0260:
            r7 = r16
        L_0x0262:
            if (r6 != 0) goto L_0x02f0
            org.videolan.vlc.media.ResumeStatus r1 = r11.getVideoResumeStatus()
            org.videolan.vlc.media.ResumeStatus r4 = org.videolan.vlc.media.ResumeStatus.ASK
            if (r1 != r4) goto L_0x02f0
            int r1 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r1 <= 0) goto L_0x02f0
            boolean r1 = org.videolan.tools.KotlinExtensionsKt.isAppStarted()
            if (r1 == 0) goto L_0x02f0
            androidx.lifecycle.MutableLiveData r1 = r11.getWaitForConfirmation()
            org.videolan.vlc.media.WaitConfirmation r2 = new org.videolan.vlc.media.WaitConfirmation
            java.lang.String r3 = r15.getTitle()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)
            r21 = 8
            r22 = 0
            r20 = 0
            r16 = r2
            r17 = r3
            r16.<init>(r17, r18, r19, r20, r21, r22)
            r1.postValue(r2)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x0296:
            if (r4 != 0) goto L_0x02ba
            org.videolan.vlc.media.ResumeStatus r1 = r11.getAudioResumeStatus()
            org.videolan.vlc.media.ResumeStatus r4 = org.videolan.vlc.media.ResumeStatus.NEVER
            if (r1 == r4) goto L_0x02ba
            org.videolan.tools.Settings r1 = org.videolan.tools.Settings.INSTANCE
            org.videolan.resources.AppContextProvider r4 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.Context r4 = r4.getAppContext()
            java.lang.Object r1 = r1.getInstance(r4)
            android.content.SharedPreferences r1 = (android.content.SharedPreferences) r1
            boolean r1 = r1.getBoolean(r7, r14)
            if (r1 != 0) goto L_0x02b5
            goto L_0x02ba
        L_0x02b5:
            long r7 = r11.getStartTime(r15)
            goto L_0x02bc
        L_0x02ba:
            r7 = r16
        L_0x02bc:
            if (r6 != 0) goto L_0x02f0
            org.videolan.vlc.media.ResumeStatus r1 = r11.getAudioResumeStatus()
            org.videolan.vlc.media.ResumeStatus r4 = org.videolan.vlc.media.ResumeStatus.ASK
            if (r1 != r4) goto L_0x02f0
            int r1 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r1 <= 0) goto L_0x02f0
            boolean r1 = org.videolan.tools.KotlinExtensionsKt.isAppStarted()
            if (r1 == 0) goto L_0x02f0
            org.videolan.vlc.media.WaitConfirmation r1 = new org.videolan.vlc.media.WaitConfirmation
            java.lang.String r2 = r15.getTitle()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)
            r21 = 8
            r22 = 0
            r20 = 0
            r16 = r1
            r17 = r2
            r16.<init>(r17, r18, r19, r20, r21, r22)
            androidx.lifecycle.MutableLiveData r2 = r11.getWaitForConfirmationAudio()
            r2.postValue(r1)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x02f0:
            org.videolan.libvlc.interfaces.IMediaFactory r1 = r11.mediaFactory
            org.videolan.resources.VLCInstance r4 = org.videolan.resources.VLCInstance.INSTANCE
            org.videolan.vlc.PlaybackService r5 = r11.service
            java.lang.Object r4 = r4.getInstance(r5)
            org.videolan.libvlc.interfaces.ILibVLC r4 = (org.videolan.libvlc.interfaces.ILibVLC) r4
            org.videolan.libvlc.interfaces.IMedia r1 = r1.getFromUri(r4, r2)
            boolean r2 = r11.shouldDisableCookieForwarding
            if (r2 == 0) goto L_0x030b
            r11.shouldDisableCookieForwarding = r13
            java.lang.String r2 = ":no-http-forward-cookies"
            r1.addOption(r2)
        L_0x030b:
            android.content.SharedPreferences r2 = r11.getSettings()
            java.lang.String r4 = "http_user_agent"
            r5 = 0
            java.lang.String r2 = r2.getString(r4, r5)
            if (r2 == 0) goto L_0x0329
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = ":http-user-agent="
            r4.<init>(r5)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r1.addOption(r2)
        L_0x0329:
            android.content.SharedPreferences r2 = r11.getSettings()
            java.lang.String r4 = "dav1d_thread_number"
            java.lang.String r5 = ""
            java.lang.String r2 = r2.getString(r4, r5)
            if (r2 != 0) goto L_0x0338
            goto L_0x0339
        L_0x0338:
            r5 = r2
        L_0x0339:
            r2 = r5
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            int r2 = r2.length()
            if (r2 <= 0) goto L_0x0359
            int r2 = java.lang.Integer.parseInt(r5)
            if (r2 < r14) goto L_0x0359
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = ":dav1d-thread-frames="
            r2.<init>(r4)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            r1.addOption(r2)
        L_0x0359:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = ":start-time="
            r2.<init>(r4)
            r4 = 1000(0x3e8, double:4.94E-321)
            long r4 = r7 / r4
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r1.addOption(r2)
            org.videolan.resources.VLCOptions r2 = org.videolan.resources.VLCOptions.INSTANCE
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            android.content.Context r4 = r11.getCtx()
            int r5 = r15.getFlags()
            r5 = r19 | r5
            org.videolan.vlc.PlaybackService$Companion r6 = org.videolan.vlc.PlaybackService.Companion
            boolean r6 = r6.hasRenderer()
            r2.setMediaOptions(r1, r4, r5, r6)
            boolean r2 = r11.isBenchmark
            if (r2 == 0) goto L_0x039f
            java.lang.String r2 = ":no-audio"
            r1.addOption(r2)
            java.lang.String r2 = ":no-spu"
            r1.addOption(r2)
            boolean r2 = r11.isHardware
            if (r2 == 0) goto L_0x039f
            java.lang.String r2 = ":codec=mediacodec_ndk,mediacodec_jni,none"
            r1.addOption(r2)
            r11.isHardware = r13
        L_0x039f:
            r2 = r11
            org.videolan.libvlc.interfaces.IMedia$EventListener r2 = (org.videolan.libvlc.interfaces.IMedia.EventListener) r2
            r1.setEventListener(r2)
            org.videolan.vlc.media.PlayerController r4 = r11.getPlayer()
            org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1 r2 = r11.mediaplayerEventListener
            r6 = r2
            org.videolan.vlc.media.MediaPlayerEventListener r6 = (org.videolan.vlc.media.MediaPlayerEventListener) r6
            r3.L$0 = r11
            r3.L$1 = r15
            r3.L$2 = r1
            r2 = 3
            r3.label = r2
            r5 = r1
            r9 = r3
            java.lang.Object r2 = r4.startPlayback$vlc_android_release(r5, r6, r7, r9)
            if (r2 != r10) goto L_0x03c0
            return r10
        L_0x03c0:
            r2 = r1
            r1 = r11
            r4 = r15
        L_0x03c3:
            org.videolan.vlc.media.PlayerController r5 = r1.getPlayer()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            r5.setSlaves(r2, r4)
            r1.newMedia = r14
            r3.L$0 = r1
            r2 = 0
            r3.L$1 = r2
            r3.L$2 = r2
            r4 = 4
            r3.label = r4
            java.lang.Object r2 = determinePrevAndNextIndices$default(r1, r13, r3, r14, r2)
            if (r2 != r10) goto L_0x03e0
            return r10
        L_0x03e0:
            org.videolan.vlc.PlaybackService r1 = r1.service
            r1.onNewPlayback()
        L_0x03e5:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlaylistManager.playIndex(int, int, boolean, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void skipMedia() {
        if (this.currentIndex != this.nextIndex) {
            next$default(this, false, 1, (Object) null);
        } else {
            stop$default(this, false, false, 3, (Object) null);
        }
    }

    public final void onServiceDestroyed() {
        PlayerController.release$default(getPlayer(), (MediaPlayer) null, 1, (Object) null);
    }

    public final boolean switchToVideo() {
        MediaWrapper currentMedia = getCurrentMedia();
        if (currentMedia == null || currentMedia.hasFlag(8) || !getPlayer().canSwitchToVideo()) {
            return false;
        }
        boolean hasRenderer = getPlayer().getHasRenderer();
        this.videoBackground = false;
        showAudioPlayer.postValue(false);
        if (getPlayer().isVideoPlaying() && !hasRenderer) {
            getPlayer().setVideoTrackEnabled(true);
            LocalBroadcastManager.getInstance(this.service).sendBroadcast(VideoPlayerActivity.Companion.getIntent(Constants.PLAY_FROM_SERVICE, currentMedia, false, this.currentIndex));
        } else if (!getPlayer().getSwitchToVideo()) {
            VideoPlayerActivity.Companion companion = VideoPlayerActivity.Companion;
            Context appContext = AppContextProvider.INSTANCE.getAppContext();
            Uri uri = currentMedia.getUri();
            Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
            companion.startOpened(appContext, uri, this.currentIndex);
            if (!hasRenderer) {
                getPlayer().setSwitchToVideo(true);
            }
        }
        return true;
    }

    public final void setVideoTrackEnabled(boolean z) {
        if (Companion.hasMedia() && getPlayer().isPlaying()) {
            if (z) {
                MediaWrapper currentMedia = getCurrentMedia();
                if (currentMedia != null) {
                    currentMedia.addFlags(1);
                }
            } else {
                MediaWrapper currentMedia2 = getCurrentMedia();
                if (currentMedia2 != null) {
                    currentMedia2.removeFlags(1);
                }
            }
            getPlayer().setVideoTrackEnabled(z);
        }
    }

    public final boolean hasPrevious() {
        return this.prevIndex != -1;
    }

    public final boolean hasNext() {
        return this.nextIndex != -1;
    }

    public void onItemAdded(int i, String str) {
        Intrinsics.checkNotNullParameter(str, "mrl");
        if (this.currentIndex >= i && !this.expanding) {
            setCurrentIndex(this.currentIndex + 1);
        }
        this.addUpdateActor.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onItemRemoved(int i, String str) {
        Intrinsics.checkNotNullParameter(str, "mrl");
        int i2 = this.currentIndex;
        boolean z = i2 == i;
        if (i2 >= i && !this.expanding) {
            setCurrentIndex(this.currentIndex - 1);
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PlaylistManager$onItemRemoved$1(this, z, (Continuation<? super PlaylistManager$onItemRemoved$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void executeUpdate() {
        this.service.executeUpdate(true);
    }

    public static /* synthetic */ Job saveMediaMeta$default(PlaylistManager playlistManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return playlistManager.saveMediaMeta(z);
    }

    public final Job saveMediaMeta(boolean z) {
        return BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new PlaylistManager$saveMediaMeta$1(this, z, (Continuation<? super PlaylistManager$saveMediaMeta$1>) null), 1, (Object) null);
    }

    public final void setSpuTrack(String str) {
        MediaWrapper currentMedia;
        Intrinsics.checkNotNullParameter(str, "index");
        if (getPlayer().setSpuTrack(str) && (currentMedia = getCurrentMedia()) != null && currentMedia.getId() != 0) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getIO(), (CoroutineStart) null, new PlaylistManager$setSpuTrack$1(currentMedia, str, (Continuation<? super PlaylistManager$setSpuTrack$1>) null), 2, (Object) null);
        }
    }

    public final void setAudioDelay(long j) {
        MediaWrapper currentMedia;
        if (getPlayer().setAudioDelay(j) && (currentMedia = getCurrentMedia()) != null && currentMedia.getId() != 0 && getSettings().getBoolean("save_individual_audio_delay", true)) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getIO(), (CoroutineStart) null, new PlaylistManager$setAudioDelay$1(currentMedia, this, (Continuation<? super PlaylistManager$setAudioDelay$1>) null), 2, (Object) null);
        }
    }

    public final void setSpuDelay(long j) {
        MediaWrapper currentMedia;
        if (getPlayer().setSpuDelay(j) && (currentMedia = getCurrentMedia()) != null && currentMedia.getId() != 0) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getIO(), (CoroutineStart) null, new PlaylistManager$setSpuDelay$1(currentMedia, this, (Continuation<? super PlaylistManager$setSpuDelay$1>) null), 2, (Object) null);
        }
    }

    /* access modifiers changed from: private */
    public final void loadMediaMeta(MediaWrapper mediaWrapper) {
        if (getPlayer().canSwitchToVideo()) {
            long metaLong = mediaWrapper.getMetaLong(MediaWrapper.META_AUDIODELAY);
            long j = ((SharedPreferences) Settings.INSTANCE.getInstance(AppContextProvider.INSTANCE.getAppContext())).getLong(SettingsKt.AUDIO_DELAY_GLOBAL, 0);
            if (metaLong == 0 && j != 0) {
                getPlayer().setAudioDelay(j);
            } else if (getSettings().getBoolean("save_individual_audio_delay", true)) {
                getPlayer().setAudioDelay(metaLong);
            }
            long metaLong2 = getSettings().getBoolean(SettingsKt.PLAYBACK_HISTORY, true) ? mediaWrapper.getMetaLong(106) : 0;
            if (metaLong2 != 0) {
                getAbRepeatOn().setValue(true);
                long metaLong3 = mediaWrapper.getMetaLong(107);
                MutableLiveData<ABRepeat> abRepeat = getAbRepeat();
                if (metaLong3 == 0) {
                    metaLong3 = -1;
                }
                abRepeat.postValue(new ABRepeat(metaLong2, metaLong3));
            }
            getPlayer().setSpuTrack(String.valueOf(mediaWrapper.getMetaLong(200)));
            getPlayer().setSpuDelay(mediaWrapper.getMetaLong(201));
            String metaString = getSettings().getBoolean(SettingsKt.PLAYBACK_HISTORY, true) ? mediaWrapper.getMetaString(51) : null;
            CharSequence charSequence = metaString;
            if (charSequence != null && charSequence.length() != 0) {
                getPlayer().setRate(Float.parseFloat(metaString), false);
            }
        }
    }

    public static /* synthetic */ void saveCurrentMedia$default(PlaylistManager playlistManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        playlistManager.saveCurrentMedia(z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0048, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0114, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0024  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0049 A[SYNTHETIC, Splitter:B:19:0x0049] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void saveCurrentMedia(boolean r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = r6.getCurrentMedia()     // Catch:{ all -> 0x0115 }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r6)
            return
        L_0x0009:
            boolean r1 = r6.isAudioList$vlc_android_release()     // Catch:{ all -> 0x0115 }
            r2 = 1
            if (r1 != 0) goto L_0x0015
            if (r7 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r7 = 0
            goto L_0x0016
        L_0x0015:
            r7 = 1
        L_0x0016:
            android.net.Uri r1 = r0.getUri()     // Catch:{ all -> 0x0115 }
            java.lang.String r1 = r1.getScheme()     // Catch:{ all -> 0x0115 }
            boolean r1 = org.videolan.vlc.util.BrowserutilsKt.isSchemeFD(r1)     // Catch:{ all -> 0x0115 }
            if (r1 == 0) goto L_0x0049
            if (r7 == 0) goto L_0x0047
            android.content.SharedPreferences r7 = r6.getSettings()     // Catch:{ all -> 0x0115 }
            java.lang.String r0 = "key_current_audio_resume_title"
            java.lang.String r1 = ""
            org.videolan.tools.SettingsKt.putSingle(r7, r0, r1)     // Catch:{ all -> 0x0115 }
            android.content.SharedPreferences r7 = r6.getSettings()     // Catch:{ all -> 0x0115 }
            java.lang.String r0 = "key_current_audio_resume_artist"
            java.lang.String r1 = ""
            org.videolan.tools.SettingsKt.putSingle(r7, r0, r1)     // Catch:{ all -> 0x0115 }
            android.content.SharedPreferences r7 = r6.getSettings()     // Catch:{ all -> 0x0115 }
            java.lang.String r0 = "key_current_audio_resume_thumb"
            java.lang.String r1 = ""
            org.videolan.tools.SettingsKt.putSingle(r7, r0, r1)     // Catch:{ all -> 0x0115 }
        L_0x0047:
            monitor-exit(r6)
            return
        L_0x0049:
            android.content.SharedPreferences r1 = r6.getSettings()     // Catch:{ all -> 0x0115 }
            java.lang.String r3 = "audio_resume_playback"
            boolean r1 = r1.getBoolean(r3, r2)     // Catch:{ all -> 0x0115 }
            android.content.SharedPreferences r3 = r6.getSettings()     // Catch:{ all -> 0x0115 }
            java.lang.String r4 = "video_resume_playback"
            boolean r2 = r3.getBoolean(r4, r2)     // Catch:{ all -> 0x0115 }
            if (r7 != 0) goto L_0x00b8
            if (r2 == 0) goto L_0x00b8
            android.content.SharedPreferences r2 = r6.getSettings()     // Catch:{ all -> 0x0115 }
            java.lang.String r3 = "current_media_resume"
            java.lang.String r4 = r0.getLocation()     // Catch:{ all -> 0x0115 }
            java.lang.String r5 = "getLocation(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch:{ all -> 0x0115 }
            org.videolan.tools.SettingsKt.putSingle(r2, r3, r4)     // Catch:{ all -> 0x0115 }
            android.content.SharedPreferences r2 = r6.getSettings()     // Catch:{ all -> 0x0115 }
            java.lang.String r3 = "key_current_audio_resume_title"
            java.lang.String r4 = r0.getTitle()     // Catch:{ all -> 0x0115 }
            if (r4 != 0) goto L_0x0081
            java.lang.String r4 = ""
        L_0x0081:
            org.videolan.tools.SettingsKt.putSingle(r2, r3, r4)     // Catch:{ all -> 0x0115 }
            android.content.SharedPreferences r2 = r6.getSettings()     // Catch:{ all -> 0x0115 }
            java.lang.String r3 = "key_current_audio_resume_artist"
            java.lang.String r4 = r0.getArtist()     // Catch:{ all -> 0x0115 }
            if (r4 != 0) goto L_0x0092
            java.lang.String r4 = ""
        L_0x0092:
            org.videolan.tools.SettingsKt.putSingle(r2, r3, r4)     // Catch:{ all -> 0x0115 }
            android.content.SharedPreferences r2 = r6.getSettings()     // Catch:{ all -> 0x0115 }
            java.lang.String r3 = "key_current_audio_resume_thumb"
            java.lang.String r4 = r0.getArtworkURL()     // Catch:{ all -> 0x0115 }
            if (r4 != 0) goto L_0x00a3
            java.lang.String r4 = ""
        L_0x00a3:
            org.videolan.tools.SettingsKt.putSingle(r2, r3, r4)     // Catch:{ all -> 0x0115 }
            android.content.SharedPreferences r2 = r6.getSettings()     // Catch:{ all -> 0x0115 }
            java.lang.String r3 = "current_media"
            java.lang.String r4 = r0.getLocation()     // Catch:{ all -> 0x0115 }
            java.lang.String r5 = "getLocation(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch:{ all -> 0x0115 }
            org.videolan.tools.SettingsKt.putSingle(r2, r3, r4)     // Catch:{ all -> 0x0115 }
        L_0x00b8:
            if (r7 == 0) goto L_0x0113
            if (r1 == 0) goto L_0x0113
            android.content.SharedPreferences r7 = r6.getSettings()     // Catch:{ all -> 0x0115 }
            java.lang.String r1 = "current_media_resume"
            java.lang.String r2 = r0.getLocation()     // Catch:{ all -> 0x0115 }
            java.lang.String r3 = "getLocation(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ all -> 0x0115 }
            org.videolan.tools.SettingsKt.putSingle(r7, r1, r2)     // Catch:{ all -> 0x0115 }
            android.content.SharedPreferences r7 = r6.getSettings()     // Catch:{ all -> 0x0115 }
            java.lang.String r1 = "current_song"
            java.lang.String r2 = r0.getLocation()     // Catch:{ all -> 0x0115 }
            java.lang.String r3 = "getLocation(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ all -> 0x0115 }
            org.videolan.tools.SettingsKt.putSingle(r7, r1, r2)     // Catch:{ all -> 0x0115 }
            android.content.SharedPreferences r7 = r6.getSettings()     // Catch:{ all -> 0x0115 }
            java.lang.String r1 = "key_current_audio_resume_title"
            java.lang.String r2 = r0.getTitle()     // Catch:{ all -> 0x0115 }
            if (r2 != 0) goto L_0x00ee
            java.lang.String r2 = ""
        L_0x00ee:
            org.videolan.tools.SettingsKt.putSingle(r7, r1, r2)     // Catch:{ all -> 0x0115 }
            android.content.SharedPreferences r7 = r6.getSettings()     // Catch:{ all -> 0x0115 }
            java.lang.String r1 = "key_current_audio_resume_artist"
            java.lang.String r2 = r0.getArtist()     // Catch:{ all -> 0x0115 }
            if (r2 != 0) goto L_0x00ff
            java.lang.String r2 = ""
        L_0x00ff:
            org.videolan.tools.SettingsKt.putSingle(r7, r1, r2)     // Catch:{ all -> 0x0115 }
            android.content.SharedPreferences r7 = r6.getSettings()     // Catch:{ all -> 0x0115 }
            java.lang.String r1 = "key_current_audio_resume_thumb"
            java.lang.String r0 = r0.getArtworkURL()     // Catch:{ all -> 0x0115 }
            if (r0 != 0) goto L_0x0110
            java.lang.String r0 = ""
        L_0x0110:
            org.videolan.tools.SettingsKt.putSingle(r7, r1, r0)     // Catch:{ all -> 0x0115 }
        L_0x0113:
            monitor-exit(r6)
            return
        L_0x0115:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlaylistManager.saveCurrentMedia(boolean):void");
    }

    public static /* synthetic */ Object saveMediaList$default(PlaylistManager playlistManager, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return playlistManager.saveMediaList(z, continuation);
    }

    public final Object saveMediaList(boolean z, Continuation<? super Unit> continuation) {
        MediaWrapper currentMedia = getCurrentMedia();
        if (currentMedia == null) {
            return Unit.INSTANCE;
        }
        if (BrowserutilsKt.isSchemeFD(currentMedia.getUri().getScheme())) {
            return Unit.INSTANCE;
        }
        Object withContext = BuildersKt.withContext(Dispatchers.getDefault(), new PlaylistManager$saveMediaList$2(new StringBuilder(), isAudioList$vlc_android_release() || z, this, (Continuation<? super PlaylistManager$saveMediaList$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public void onItemMoved(int i, int i2, String str) {
        Intrinsics.checkNotNullParameter(str, "mrl");
        int i3 = this.currentIndex;
        if (i3 == i) {
            setCurrentIndex(i2);
            if (i2 > i) {
                setCurrentIndex(this.currentIndex - 1);
            }
        } else if (i2 <= i3 && i3 < i) {
            setCurrentIndex(i3 + 1);
        } else if (i + 1 <= i3 && i3 < i2) {
            setCurrentIndex(i3 - 1);
        }
        this.previous.clear();
        this.addUpdateActor.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object determinePrevAndNextIndices(boolean r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof org.videolan.vlc.media.PlaylistManager$determinePrevAndNextIndices$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            org.videolan.vlc.media.PlaylistManager$determinePrevAndNextIndices$1 r0 = (org.videolan.vlc.media.PlaylistManager$determinePrevAndNextIndices$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.media.PlaylistManager$determinePrevAndNextIndices$1 r0 = new org.videolan.vlc.media.PlaylistManager$determinePrevAndNextIndices$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = -1
            r5 = 1
            if (r2 == 0) goto L_0x003c
            if (r2 != r5) goto L_0x0034
            java.lang.Object r7 = r0.L$1
            org.videolan.vlc.media.PlaylistManager r7 = (org.videolan.vlc.media.PlaylistManager) r7
            java.lang.Object r0 = r0.L$0
            org.videolan.vlc.media.PlaylistManager r0 = (org.videolan.vlc.media.PlaylistManager) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0062
        L_0x0034:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r8)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = r6.getCurrentMedia()
            if (r7 == 0) goto L_0x006d
            if (r8 == 0) goto L_0x006d
            r6.expanding = r5
            int r7 = r8.getType()
            r8 = 6
            if (r7 != r8) goto L_0x0052
            r7 = 1
            goto L_0x0053
        L_0x0052:
            r7 = 0
        L_0x0053:
            r0.L$0 = r6
            r0.L$1 = r6
            r0.label = r5
            java.lang.Object r8 = r6.expand(r7, r0)
            if (r8 != r1) goto L_0x0060
            return r1
        L_0x0060:
            r7 = r6
            r0 = r7
        L_0x0062:
            java.lang.Number r8 = (java.lang.Number) r8
            int r8 = r8.intValue()
            r7.nextIndex = r8
            r0.expanding = r3
            goto L_0x0070
        L_0x006d:
            r6.nextIndex = r4
            r0 = r6
        L_0x0070:
            r0.prevIndex = r4
            int r7 = r0.nextIndex
            if (r7 != r4) goto L_0x012d
            org.videolan.vlc.media.MediaWrapperList r7 = mediaList
            int r7 = r7.size()
            boolean r8 = r0.shuffling
            r1 = 2
            if (r7 <= r1) goto L_0x0083
            r1 = 1
            goto L_0x0084
        L_0x0083:
            r1 = 0
        L_0x0084:
            r8 = r8 & r1
            r0.shuffling = r8
            if (r8 == 0) goto L_0x010d
            java.util.Stack<java.lang.Integer> r8 = r0.previous
            boolean r8 = r8.isEmpty()
            if (r8 != 0) goto L_0x00d3
            java.util.Stack<java.lang.Integer> r8 = r0.previous
            java.lang.Object r8 = r8.peek()
            java.lang.String r1 = "peek(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r1)
            java.lang.Number r8 = (java.lang.Number) r8
            int r8 = r8.intValue()
            r0.prevIndex = r8
        L_0x00a4:
            int r8 = r0.prevIndex
            boolean r8 = r0.isValidPosition(r8)
            if (r8 != 0) goto L_0x00d3
            java.util.Stack<java.lang.Integer> r8 = r0.previous
            int r2 = r8.size()
            int r2 = r2 - r5
            r8.remove(r2)
            java.util.Stack<java.lang.Integer> r8 = r0.previous
            boolean r8 = r8.isEmpty()
            if (r8 == 0) goto L_0x00c1
            r0.prevIndex = r4
            goto L_0x00d3
        L_0x00c1:
            java.util.Stack<java.lang.Integer> r8 = r0.previous
            java.lang.Object r8 = r8.peek()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r1)
            java.lang.Number r8 = (java.lang.Number) r8
            int r8 = r8.intValue()
            r0.prevIndex = r8
            goto L_0x00a4
        L_0x00d3:
            java.util.Stack<java.lang.Integer> r8 = r0.previous
            int r8 = r8.size()
            int r8 = r8 + r5
            if (r8 != r7) goto L_0x00f4
            kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> r8 = repeating
            java.lang.Object r8 = r8.getValue()
            java.lang.Number r8 = (java.lang.Number) r8
            int r8 = r8.intValue()
            if (r8 != 0) goto L_0x00ef
            r0.nextIndex = r4
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x00ef:
            java.util.Stack<java.lang.Integer> r8 = r0.previous
            r8.clear()
        L_0x00f4:
            java.security.SecureRandom r8 = r0.random
            int r8 = r8.nextInt(r7)
            r0.nextIndex = r8
            int r1 = r0.currentIndex
            if (r8 == r1) goto L_0x00f4
            java.util.Stack<java.lang.Integer> r1 = r0.previous
            java.lang.Integer r8 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r8)
            boolean r8 = r1.contains(r8)
            if (r8 != 0) goto L_0x00f4
            goto L_0x012d
        L_0x010d:
            int r8 = r0.currentIndex
            if (r8 <= 0) goto L_0x0115
            int r1 = r8 + -1
            r0.prevIndex = r1
        L_0x0115:
            int r1 = r8 + 1
            if (r1 >= r7) goto L_0x011c
            int r3 = r8 + 1
            goto L_0x012b
        L_0x011c:
            kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> r7 = repeating
            java.lang.Object r7 = r7.getValue()
            java.lang.Number r7 = (java.lang.Number) r7
            int r7 = r7.intValue()
            if (r7 != 0) goto L_0x012b
            r3 = -1
        L_0x012b:
            r0.nextIndex = r3
        L_0x012d:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlaylistManager.determinePrevAndNextIndices(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object determinePrevAndNextIndices$default(PlaylistManager playlistManager, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return playlistManager.determinePrevAndNextIndices(z, continuation);
    }

    public final long previousTotalTime() {
        int i = this.currentIndex;
        List<MediaWrapper> copy = mediaList.getCopy();
        if (copy.size() == 0 || i < 0) {
            return 0;
        }
        if (this.shuffling) {
            return SequencesKt.sumOfLong(SequencesKt.map(SequencesKt.filterIndexed(CollectionsKt.asSequence(copy), new PlaylistManager$previousTotalTime$1(this)), PlaylistManager$previousTotalTime$2.INSTANCE));
        }
        return SequencesKt.sumOfLong(SequencesKt.map(SequencesKt.take(CollectionsKt.asSequence(copy), i), PlaylistManager$previousTotalTime$3.INSTANCE));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x012c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0196  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01ab  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x01ae  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object expand(boolean r23, kotlin.coroutines.Continuation<? super java.lang.Integer> r24) {
        /*
            r22 = this;
            r0 = r22
            r1 = r24
            boolean r2 = r1 instanceof org.videolan.vlc.media.PlaylistManager$expand$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            org.videolan.vlc.media.PlaylistManager$expand$1 r2 = (org.videolan.vlc.media.PlaylistManager$expand$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            org.videolan.vlc.media.PlaylistManager$expand$1 r2 = new org.videolan.vlc.media.PlaylistManager$expand$1
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            java.lang.String r5 = "VLC/PlaylistManager"
            r6 = 2
            r7 = 0
            r8 = 0
            r9 = 1
            if (r4 == 0) goto L_0x006e
            if (r4 == r9) goto L_0x005c
            if (r4 != r6) goto L_0x0054
            int r4 = r2.I$3
            int r10 = r2.I$2
            int r11 = r2.I$1
            int r12 = r2.I$0
            java.lang.Object r13 = r2.L$3
            org.videolan.libvlc.interfaces.IMedia r13 = (org.videolan.libvlc.interfaces.IMedia) r13
            java.lang.Object r14 = r2.L$2
            java.lang.String r14 = (java.lang.String) r14
            java.lang.Object r15 = r2.L$1
            org.videolan.libvlc.interfaces.IMediaList r15 = (org.videolan.libvlc.interfaces.IMediaList) r15
            java.lang.Object r6 = r2.L$0
            org.videolan.vlc.media.PlaylistManager r6 = (org.videolan.vlc.media.PlaylistManager) r6
            kotlin.ResultKt.throwOnFailure(r1)
            r16 = r5
            r0 = r6
            r6 = r12
            r1 = r15
            r15 = 2
            goto L_0x012f
        L_0x0054:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x005c:
            int r4 = r2.I$1
            int r6 = r2.I$0
            boolean r10 = r2.Z$0
            java.lang.Object r11 = r2.L$1
            org.videolan.medialibrary.interfaces.media.MediaWrapper r11 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r11
            java.lang.Object r12 = r2.L$0
            org.videolan.vlc.media.PlaylistManager r12 = (org.videolan.vlc.media.PlaylistManager) r12
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00ad
        L_0x006e:
            kotlin.ResultKt.throwOnFailure(r1)
            r0.entryUrl = r8
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.String r4 = "Call stack"
            r1.<init>(r4)
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            java.lang.String r4 = "expand with values: "
            android.util.Log.d(r5, r4, r1)
            int r6 = r0.currentIndex
            org.videolan.medialibrary.interfaces.media.MediaWrapper r11 = r22.getCurrentMedia()
            if (r11 == 0) goto L_0x0092
            int r1 = r11.getType()
            r4 = 6
            if (r1 != r4) goto L_0x0092
            r4 = 1
            goto L_0x0093
        L_0x0092:
            r4 = 0
        L_0x0093:
            org.videolan.vlc.media.PlayerController r1 = r22.getPlayer()
            r2.L$0 = r0
            r2.L$1 = r11
            r10 = r23
            r2.Z$0 = r10
            r2.I$0 = r6
            r2.I$1 = r4
            r2.label = r9
            java.lang.Object r1 = r1.expand(r2)
            if (r1 != r3) goto L_0x00ac
            return r3
        L_0x00ac:
            r12 = r0
        L_0x00ad:
            org.videolan.libvlc.interfaces.IMediaList r1 = (org.videolan.libvlc.interfaces.IMediaList) r1
            if (r1 == 0) goto L_0x01ab
            int r13 = r1.getCount()
            if (r13 <= 0) goto L_0x01ab
            if (r10 == 0) goto L_0x00c0
            if (r11 == 0) goto L_0x00c0
            java.lang.String r10 = r11.getLocation()
            goto L_0x00c1
        L_0x00c0:
            r10 = r8
        L_0x00c1:
            org.videolan.vlc.media.MediaWrapperList r11 = mediaList
            r13 = r12
            org.videolan.vlc.media.MediaWrapperList$EventListener r13 = (org.videolan.vlc.media.MediaWrapperList.EventListener) r13
            r11.removeEventListener(r13)
            r11.remove((int) r6)
            int r11 = r1.getCount()
            r14 = r10
            r13 = r12
            r10 = 0
            r21 = r11
            r11 = r4
            r4 = r21
        L_0x00d8:
            if (r10 >= r4) goto L_0x0162
            org.videolan.libvlc.interfaces.IMedia r12 = r1.getMediaAt(r10)
            android.net.Uri r15 = r12.getUri()
            java.lang.String r15 = r15.getScheme()
            boolean r15 = org.videolan.vlc.util.BrowserutilsKt.isSchemeHttpOrHttps(r15)
            if (r15 == 0) goto L_0x0104
            android.net.Uri r15 = r12.getUri()
            java.lang.String r15 = r15.getAuthority()
            if (r15 == 0) goto L_0x0104
            java.lang.String r0 = ".youtube.com"
            r16 = r5
            r5 = 2
            boolean r0 = kotlin.text.StringsKt.endsWith$default(r15, r0, r7, r5, r8)
            if (r0 != r9) goto L_0x0106
            r13.shouldDisableCookieForwarding = r9
            goto L_0x0106
        L_0x0104:
            r16 = r5
        L_0x0106:
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0
            org.videolan.vlc.media.PlaylistManager$expand$2 r5 = new org.videolan.vlc.media.PlaylistManager$expand$2
            r5.<init>(r12, r8)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r2.L$0 = r13
            r2.L$1 = r1
            r2.L$2 = r14
            r2.L$3 = r12
            r2.I$0 = r6
            r2.I$1 = r11
            r2.I$2 = r10
            r2.I$3 = r4
            r15 = 2
            r2.label = r15
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r5, r2)
            if (r0 != r3) goto L_0x012d
            return r3
        L_0x012d:
            r0 = r13
            r13 = r12
        L_0x012f:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r12 = "inserting: "
            r5.<init>(r12)
            android.net.Uri r12 = r13.getUri()
            r5.append(r12)
            java.lang.String r5 = r5.toString()
            r12 = r16
            android.util.Log.d(r12, r5)
            org.videolan.vlc.media.MediaWrapperList r5 = mediaList
            int r7 = r6 + r10
            org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = org.videolan.medialibrary.MLServiceLocator.getAbstractMediaWrapper((org.videolan.libvlc.interfaces.IMedia) r13)
            java.lang.String r15 = "getAbstractMediaWrapper(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r15)
            r5.insert(r7, r8)
            r13.release()
            int r10 = r10 + r9
            r13 = r0
            r5 = r12
            r7 = 0
            r8 = 0
            r0 = r22
            goto L_0x00d8
        L_0x0162:
            org.videolan.vlc.media.MediaWrapperList r0 = mediaList
            r2 = r13
            org.videolan.vlc.media.MediaWrapperList$EventListener r2 = (org.videolan.vlc.media.MediaWrapperList.EventListener) r2
            r0.addEventListener(r2)
            kotlinx.coroutines.channels.SendChannel<kotlin.Unit> r0 = r13.addUpdateActor
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            r0.m1139trySendJP2dKIU(r2)
            org.videolan.vlc.PlaybackService r0 = r13.service
            r0.onMediaListChanged()
            if (r14 == 0) goto L_0x01ac
            int r0 = r1.getCount()
            if (r0 != r9) goto L_0x01ac
            org.videolan.medialibrary.interfaces.media.MediaWrapper r12 = r13.getCurrentMedia()
            if (r12 == 0) goto L_0x01ac
            org.videolan.tools.AppScope r0 = org.videolan.tools.AppScope.INSTANCE
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getIO()
            r16 = r2
            kotlin.coroutines.CoroutineContext r16 = (kotlin.coroutines.CoroutineContext) r16
            org.videolan.vlc.media.PlaylistManager$expand$3$1 r2 = new org.videolan.vlc.media.PlaylistManager$expand$3$1
            if (r11 == 0) goto L_0x0196
            r11 = 1
            goto L_0x0197
        L_0x0196:
            r11 = 0
        L_0x0197:
            r15 = 0
            r10 = r2
            r10.<init>(r11, r12, r13, r14, r15)
            r18 = r2
            kotlin.jvm.functions.Function2 r18 = (kotlin.jvm.functions.Function2) r18
            r19 = 2
            r20 = 0
            r17 = 0
            r15 = r0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r15, r16, r17, r18, r19, r20)
            goto L_0x01ac
        L_0x01ab:
            r6 = -1
        L_0x01ac:
            if (r1 == 0) goto L_0x01b1
            r1.release()
        L_0x01b1:
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlaylistManager.expand(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final MediaWrapper getCurrentMedia() {
        return mediaList.getMedia(this.currentIndex);
    }

    public final MediaWrapper getPrevMedia() {
        if (isValidPosition(this.prevIndex)) {
            return mediaList.getMedia(this.prevIndex);
        }
        return null;
    }

    public final MediaWrapper getNextMedia() {
        if (isValidPosition(this.nextIndex)) {
            return mediaList.getMedia(this.nextIndex);
        }
        return null;
    }

    public final MediaWrapper getMedia(int i) {
        return mediaList.getMedia(i);
    }

    private final long getStartTime(MediaWrapper mediaWrapper) {
        long j;
        if (mediaWrapper.hasFlag(32)) {
            mediaWrapper.removeFlags(32);
        } else {
            j = this.savedTime;
            if (j <= 0) {
                if (mediaWrapper.getTime() > 0) {
                    j = mediaWrapper.getTime();
                }
            }
            this.savedTime = 0;
            return j;
        }
        j = 0;
        this.savedTime = 0;
        return j;
    }

    static /* synthetic */ void savePosition$default(PlaylistManager playlistManager, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        playlistManager.savePosition(z, z2);
    }

    private final synchronized void savePosition(boolean z, boolean z2) {
        if (Companion.hasMedia()) {
            SharedPreferences.Editor edit = getSettings().edit();
            boolean z3 = !z2 && isAudioList$vlc_android_release();
            edit.putBoolean(z3 ? SettingsKt.AUDIO_SHUFFLING : SettingsKt.MEDIA_SHUFFLING, this.shuffling);
            edit.putInt(z3 ? SettingsKt.POSITION_IN_AUDIO_LIST : SettingsKt.POSITION_IN_MEDIA_LIST, z ? 0 : this.currentIndex);
            edit.putLong(z3 ? SettingsKt.POSITION_IN_SONG : SettingsKt.POSITION_IN_MEDIA, z ? 0 : getPlayer().getCurrentTime());
            if (!z3) {
                edit.putFloat(SettingsKt.VIDEO_SPEED, getPlayer().getRate());
            }
            if (z) {
                MediaWrapper mediaWrapper = getMediaList().get(0);
                if (z3 && getSettings().getBoolean(SettingsKt.AUDIO_RESUME_PLAYBACK, true)) {
                    edit.putString(Constants.KEY_CURRENT_AUDIO, mediaWrapper.getLocation());
                }
            }
            edit.apply();
        }
    }

    public static /* synthetic */ Object append$default(PlaylistManager playlistManager, List list, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return playlistManager.append(list, i, continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0092 A[LOOP:0: B:21:0x008c->B:23:0x0092, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object append(java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper> r12, int r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof org.videolan.vlc.media.PlaylistManager$append$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            org.videolan.vlc.media.PlaylistManager$append$1 r0 = (org.videolan.vlc.media.PlaylistManager$append$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.media.PlaylistManager$append$1 r0 = new org.videolan.vlc.media.PlaylistManager$append$1
            r0.<init>(r11, r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "VLC/PlaylistManager"
            r4 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r4) goto L_0x0030
            java.lang.Object r12 = r0.L$0
            org.videolan.vlc.media.PlaylistManager r12 = (org.videolan.vlc.media.PlaylistManager) r12
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x007e
        L_0x0030:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.Exception r14 = new java.lang.Exception
            java.lang.String r2 = "Call stack"
            r14.<init>(r2)
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            java.lang.String r2 = "append with values: "
            android.util.Log.d(r3, r2, r14)
            boolean r14 = r11.hasCurrentMedia()
            r2 = 0
            if (r14 != 0) goto L_0x0065
            r5 = r11
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            org.videolan.vlc.media.PlaylistManager$append$2 r14 = new org.videolan.vlc.media.PlaylistManager$append$2
            r14.<init>(r11, r12, r13, r2)
            r8 = r14
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9 = 3
            r10 = 0
            r6 = 0
            r7 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r5, r6, r7, r8, r9, r10)
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x0065:
            kotlinx.coroutines.CoroutineDispatcher r13 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r13 = (kotlin.coroutines.CoroutineContext) r13
            org.videolan.vlc.media.PlaylistManager$append$newList$1 r14 = new org.videolan.vlc.media.PlaylistManager$append$newList$1
            r14.<init>(r12, r2)
            kotlin.jvm.functions.Function2 r14 = (kotlin.jvm.functions.Function2) r14
            r0.L$0 = r11
            r0.label = r4
            java.lang.Object r14 = kotlinx.coroutines.BuildersKt.withContext(r13, r14, r0)
            if (r14 != r1) goto L_0x007d
            return r1
        L_0x007d:
            r12 = r11
        L_0x007e:
            java.util.List r14 = (java.util.List) r14
            org.videolan.vlc.media.MediaWrapperList r13 = mediaList
            r0 = r12
            org.videolan.vlc.media.MediaWrapperList$EventListener r0 = (org.videolan.vlc.media.MediaWrapperList.EventListener) r0
            r13.removeEventListener(r0)
            java.util.Iterator r13 = r14.iterator()
        L_0x008c:
            boolean r1 = r13.hasNext()
            if (r1 == 0) goto L_0x009e
            java.lang.Object r1 = r13.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
            org.videolan.vlc.media.MediaWrapperList r2 = mediaList
            r2.add(r1)
            goto L_0x008c
        L_0x009e:
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            java.util.Iterator r13 = r14.iterator()
        L_0x00a4:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x00d2
            java.lang.Object r14 = r13.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r14 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r14
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NullPointerException -> 0x00c9 }
            r1.<init>()     // Catch:{ NullPointerException -> 0x00c9 }
            java.lang.String r2 = "Media location: "
            r1.append(r2)     // Catch:{ NullPointerException -> 0x00c9 }
            android.net.Uri r14 = r14.getUri()     // Catch:{ NullPointerException -> 0x00c9 }
            r1.append(r14)     // Catch:{ NullPointerException -> 0x00c9 }
            java.lang.String r14 = r1.toString()     // Catch:{ NullPointerException -> 0x00c9 }
            android.util.Log.d(r3, r14)     // Catch:{ NullPointerException -> 0x00c9 }
            goto L_0x00a4
        L_0x00c9:
            r14 = move-exception
            java.lang.String r1 = "Media crash"
            java.lang.Throwable r14 = (java.lang.Throwable) r14
            android.util.Log.d(r3, r1, r14)
            goto L_0x00a4
        L_0x00d2:
            org.videolan.vlc.media.MediaWrapperList r13 = mediaList
            r13.addEventListener(r0)
            kotlinx.coroutines.channels.SendChannel<kotlin.Unit> r13 = r12.addUpdateActor
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            r13.m1139trySendJP2dKIU(r14)
            android.content.SharedPreferences r13 = r12.getSettings()
            java.lang.String r14 = "audio_force_shuffle"
            r0 = 0
            boolean r13 = r13.getBoolean(r14, r0)
            if (r13 == 0) goto L_0x0104
            org.videolan.medialibrary.interfaces.media.MediaWrapper r13 = r12.getCurrentMedia()
            if (r13 == 0) goto L_0x0104
            int r13 = r13.getType()
            if (r13 != r4) goto L_0x0104
            boolean r13 = r12.shuffling
            if (r13 != 0) goto L_0x0104
            boolean r13 = r12.canShuffle()
            if (r13 == 0) goto L_0x0104
            r12.shuffle()
        L_0x0104:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlaylistManager.append(java.util.List, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void insertNext(List<? extends MediaWrapper> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        Log.d("VLC/PlaylistManager", "insertNext with values: ", new Exception("Call stack"));
        for (MediaWrapper uri : list) {
            Log.d("VLC/PlaylistManager", "Media location: " + uri.getUri());
        }
        if (!hasCurrentMedia()) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PlaylistManager$insertNext$2(this, list, (Continuation<? super PlaylistManager$insertNext$2>) null), 3, (Object) null);
            return;
        }
        int i = this.currentIndex + 1;
        int i2 = 0;
        for (MediaWrapper insert : list) {
            int i3 = i2 + 1;
            mediaList.insert(i2 + i, insert);
            i2 = i3;
        }
    }

    public final void moveItem(int i, int i2) {
        mediaList.move(i, i2);
    }

    public final void insertItem(int i, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "mw");
        Log.d("VLC/PlaylistManager", "insertItem with values: ", new Exception("Call stack"));
        Log.d("VLC/PlaylistManager", "Media location: " + mediaWrapper.getUri());
        mediaList.insert(i, mediaWrapper);
    }

    public final void remove(int i) {
        mediaList.remove(i);
    }

    public final void removeLocation(String str) {
        Intrinsics.checkNotNullParameter(str, "location");
        mediaList.remove(str);
    }

    public final int getMediaListSize() {
        return mediaList.size();
    }

    public final List<MediaWrapper> getMediaList() {
        return mediaList.getCopy();
    }

    public final void setABRepeatValue(MediaWrapper mediaWrapper, long j) {
        ABRepeat value = getAbRepeat().getValue();
        if (value == null) {
            value = new ABRepeat(0, 0, 3, (DefaultConstructorMarker) null);
        }
        if (value.getStart() == -1) {
            value.setStart(j);
        } else if (value.getStart() <= j || j <= -1) {
            value.setStop(j);
        } else {
            value.setStop(value.getStart());
            value.setStart(j);
        }
        if (getSettings().getBoolean(SettingsKt.PLAYBACK_HISTORY, true)) {
            if (mediaWrapper != null) {
                mediaWrapper.setLongMeta(106, value.getStart());
            }
            if (mediaWrapper != null) {
                mediaWrapper.setLongMeta(107, value.getStop());
            }
        }
        getAbRepeat().setValue(value);
    }

    public final void setDelayValue(long j, boolean z) {
        DelayValues value = getDelayValue().getValue();
        if (value == null) {
            value = new DelayValues(0, 0, 3, (DefaultConstructorMarker) null);
        }
        if (z) {
            value.setStart(j);
        } else {
            value.setStop(j);
        }
        getDelayValue().setValue(value);
    }

    public final void resetDelayValues() {
        getDelayValue().postValue(new DelayValues(0, 0, 3, (DefaultConstructorMarker) null));
    }

    public final void resetABRepeatValues(MediaWrapper mediaWrapper) {
        getAbRepeat().setValue(new ABRepeat(0, 0, 3, (DefaultConstructorMarker) null));
        if (mediaWrapper != null) {
            mediaWrapper.setLongMeta(106, 0);
        }
        if (mediaWrapper != null) {
            mediaWrapper.setLongMeta(107, 0);
        }
    }

    public final void toggleABRepeat() {
        MutableLiveData<Boolean> abRepeatOn = getAbRepeatOn();
        Boolean value = getAbRepeatOn().getValue();
        Intrinsics.checkNotNull(value);
        abRepeatOn.setValue(Boolean.valueOf(!value.booleanValue()));
        if (Intrinsics.areEqual((Object) getAbRepeatOn().getValue(), (Object) false)) {
            getAbRepeat().setValue(new ABRepeat(0, 0, 3, (DefaultConstructorMarker) null));
        }
    }

    public final void toggleStats() {
        MutableLiveData<Boolean> videoStatsOn = getVideoStatsOn();
        Boolean value = getVideoStatsOn().getValue();
        Intrinsics.checkNotNull(value);
        videoStatsOn.setValue(Boolean.valueOf(!value.booleanValue()));
    }

    public final void clearABRepeat() {
        MutableLiveData<ABRepeat> abRepeat = getAbRepeat();
        ABRepeat value = getAbRepeat().getValue();
        if (value != null) {
            value.setStart(-1);
            value.setStop(-1);
        } else {
            value = null;
        }
        abRepeat.setValue(value);
        getAbRepeatOn().setValue(false);
    }

    public void onEvent(IMedia.Event event) {
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
        int i = event.type;
        if (i != 0) {
            if (i == 3) {
                getPlayer().updateCurrentMeta$vlc_android_release(-1, getCurrentMedia());
                this.parsed = true;
            } else {
                return;
            }
        } else if (this.parsed && getPlayer().updateCurrentMeta$vlc_android_release(event.getMetaId(), getCurrentMedia())) {
            this.service.onMediaListChanged();
        }
        this.service.m2456onMediaEventJP2dKIU(event);
        if (this.parsed) {
            this.service.notifyTrackChanged();
            this.service.showNotification();
        }
    }

    /* access modifiers changed from: private */
    public final void refreshTrackMeta(MediaWrapper mediaWrapper) {
        mediaWrapper.updateMeta(getPlayer().getMediaplayer());
        this.service.onMediaListChanged();
        this.service.showNotification();
    }

    /* access modifiers changed from: private */
    public final Object savePlaycount(MediaWrapper mediaWrapper, Continuation<? super Unit> continuation) {
        if (((SharedPreferences) Settings.INSTANCE.getInstance(AppContextProvider.INSTANCE.getAppContext())).getBoolean(SettingsKt.KEY_INCOGNITO, false)) {
            return Unit.INSTANCE;
        }
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = mediaWrapper;
        if (!getSettings().getBoolean(SettingsKt.PLAYBACK_HISTORY, true) || BrowserutilsKt.isSchemeFD(mediaWrapper.getUri().getScheme())) {
            return Unit.INSTANCE;
        }
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new PlaylistManager$savePlaycount$2(mediaWrapper, this, objectRef, (Continuation<? super PlaylistManager$savePlaycount$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final boolean isAudioList$vlc_android_release() {
        return !getPlayer().isVideoPlaying() && mediaList.isAudioList();
    }
}
