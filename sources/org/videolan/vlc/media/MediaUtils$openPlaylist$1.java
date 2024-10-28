package org.videolan.vlc.media;

import android.content.Context;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.tools.Settings;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "service", "Lorg/videolan/vlc/PlaybackService;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.MediaUtils$openPlaylist$1", f = "MediaUtils.kt", i = {0}, l = {594}, m = "invokeSuspend", n = {"service"}, s = {"L$0"})
/* compiled from: MediaUtils.kt */
final class MediaUtils$openPlaylist$1 extends SuspendLambda implements Function2<PlaybackService, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ long $playlistId;
    final /* synthetic */ int $position;
    final /* synthetic */ boolean $shuffle;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaUtils$openPlaylist$1(Context context, int i, boolean z, long j, Continuation<? super MediaUtils$openPlaylist$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$position = i;
        this.$shuffle = z;
        this.$playlistId = j;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MediaUtils$openPlaylist$1 mediaUtils$openPlaylist$1 = new MediaUtils$openPlaylist$1(this.$context, this.$position, this.$shuffle, this.$playlistId, continuation);
        mediaUtils$openPlaylist$1.L$0 = obj;
        return mediaUtils$openPlaylist$1;
    }

    public final Object invoke(PlaybackService playbackService, Continuation<? super Unit> continuation) {
        return ((MediaUtils$openPlaylist$1) create(playbackService, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        PlaybackService playbackService;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PlaybackService playbackService2 = (PlaybackService) this.L$0;
            Context context = this.$context;
            long j = this.$playlistId;
            this.L$0 = playbackService2;
            this.label = 1;
            Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new MediaUtils$openPlaylist$1$invokeSuspend$$inlined$getFromMl$1(context, (Continuation) null, j), this);
            if (withContext == coroutine_suspended) {
                return coroutine_suspended;
            }
            playbackService = playbackService2;
            obj = withContext;
        } else if (i == 1) {
            playbackService = (PlaybackService) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Playlist playlist = (Playlist) obj;
        playbackService.load(playlist.getPagedTracks(playlist.getRealTracksCount(Settings.INSTANCE.getIncludeMissing(), false), 0, Settings.INSTANCE.getIncludeMissing(), false), this.$position);
        if (this.$shuffle && !playbackService.isShuffling()) {
            playbackService.shuffle();
        }
        return Unit.INSTANCE;
    }
}
