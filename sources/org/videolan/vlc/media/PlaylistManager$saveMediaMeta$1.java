package org.videolan.vlc.media;

import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.util.VLCCrashHandler;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.gui.browser.BaseBrowserFragment;
import org.videolan.vlc.util.BrowserutilsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$saveMediaMeta$1", f = "PlaylistManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$saveMediaMeta$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $end;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$saveMediaMeta$1(PlaylistManager playlistManager, boolean z, Continuation<? super PlaylistManager$saveMediaMeta$1> continuation) {
        super(2, continuation);
        this.this$0 = playlistManager;
        this.$end = z;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlaylistManager$saveMediaMeta$1 playlistManager$saveMediaMeta$1 = new PlaylistManager$saveMediaMeta$1(this.this$0, this.$end, continuation);
        playlistManager$saveMediaMeta$1.L$0 = obj;
        return playlistManager$saveMediaMeta$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaylistManager$saveMediaMeta$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            if (!((SharedPreferences) Settings.INSTANCE.getInstance(AppContextProvider.INSTANCE.getAppContext())).getBoolean(SettingsKt.PLAYBACK_HISTORY, true)) {
                return Unit.INSTANCE;
            }
            if (this.this$0.endReachedFor != null) {
                String access$getEndReachedFor$p = this.this$0.endReachedFor;
                MediaWrapper currentMedia = this.this$0.getCurrentMedia();
                if (Intrinsics.areEqual((Object) access$getEndReachedFor$p, (Object) String.valueOf(currentMedia != null ? currentMedia.getUri() : null)) && !this.$end) {
                    return Unit.INSTANCE;
                }
            }
            final int titleIdx = this.this$0.getPlayer().getTitleIdx();
            final MediaWrapper currentMedia2 = this.this$0.getCurrentMedia();
            if (currentMedia2 == null) {
                return Unit.INSTANCE;
            }
            if (BrowserutilsKt.isSchemeFD(currentMedia2.getUri().getScheme())) {
                return Unit.INSTANCE;
            }
            if (((SharedPreferences) Settings.INSTANCE.getInstance(AppContextProvider.INSTANCE.getAppContext())).getBoolean(SettingsKt.KEY_INCOGNITO, false)) {
                return Unit.INSTANCE;
            }
            final long time = this.this$0.getPlayer().getMediaplayer().getTime();
            final long length = this.this$0.getPlayer().getLength();
            final boolean canSwitchToVideo = this.this$0.getPlayer().canSwitchToVideo();
            final float rate = this.this$0.getPlayer().getRate();
            final PlaylistManager playlistManager = this.this$0;
            final boolean z = this.$end;
            Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 2, (Object) null);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$saveMediaMeta$1$1", f = "PlaylistManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.media.PlaylistManager$saveMediaMeta$1$1  reason: invalid class name */
    /* compiled from: PlaylistManager.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(playlistManager, currentMedia2, titleIdx, time, canSwitchToVideo, length, z, rate, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            MediaWrapper mediaWrapper;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (playlistManager.entryUrl != null) {
                    mediaWrapper = playlistManager.getMedialibrary().getMedia(playlistManager.entryUrl);
                } else {
                    mediaWrapper = playlistManager.getMedialibrary().findMedia(currentMedia2);
                    if (mediaWrapper == null) {
                        return Unit.INSTANCE;
                    }
                }
                if (!Intrinsics.areEqual((Object) PlaylistManager.Companion.getShowAudioPlayer().getValue(), (Object) Boxing.boxBoolean(true))) {
                    BaseBrowserFragment.Companion.getNeedRefresh().postValue(Boxing.boxBoolean(true));
                }
                if (mediaWrapper.getId() == 0) {
                    return Unit.INSTANCE;
                }
                int i = titleIdx;
                if (i > 0) {
                    mediaWrapper.setLongMeta(52, (long) i);
                }
                if ((time != 0 || playlistManager.getPlayer().isPlaying()) && (mediaWrapper.getType() == 0 || canSwitchToVideo || mediaWrapper.isPodcast())) {
                    if (length == 0) {
                        mediaWrapper.setTime(-1);
                        mediaWrapper.setPosition(playlistManager.getPlayer().getLastPosition());
                        playlistManager.getMedialibrary().setLastPosition(mediaWrapper.getId(), z ? 1.0f : mediaWrapper.getPosition());
                    } else {
                        if (mediaWrapper.getLength() <= 0) {
                            long j = length;
                            if (j > 0) {
                                mediaWrapper.setLength(j);
                            }
                        }
                        try {
                            int lastTime = playlistManager.getMedialibrary().setLastTime(mediaWrapper.getId(), z ? length : time);
                            if (lastTime != 0) {
                                if (lastTime != 1) {
                                    if (lastTime == 2) {
                                        mediaWrapper.setTime(time);
                                    } else if (lastTime != 3) {
                                    }
                                }
                                mediaWrapper.setTime(0);
                            }
                        } catch (NullPointerException e) {
                            VLCCrashHandler.Companion.saveLog(e, "NullPointerException in PlaylistManager saveMediaMeta");
                        }
                    }
                }
                if (mediaWrapper.getType() != 0 && !canSwitchToVideo && !mediaWrapper.isPodcast()) {
                    PlaylistManager.Companion.setSkipMediaUpdateRefresh(true);
                }
                mediaWrapper.setStringMeta(51, String.valueOf(rate));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
