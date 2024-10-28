package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "service", "Lorg/videolan/vlc/PlaybackService;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.MediaUtils$playAlbum$1", f = "MediaUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaUtils.kt */
final class MediaUtils$playAlbum$1 extends SuspendLambda implements Function2<PlaybackService, Continuation<? super Unit>, Object> {
    final /* synthetic */ Album $album;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaUtils$playAlbum$1(Album album, Continuation<? super MediaUtils$playAlbum$1> continuation) {
        super(2, continuation);
        this.$album = album;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MediaUtils$playAlbum$1 mediaUtils$playAlbum$1 = new MediaUtils$playAlbum$1(this.$album, continuation);
        mediaUtils$playAlbum$1.L$0 = obj;
        return mediaUtils$playAlbum$1;
    }

    public final Object invoke(PlaybackService playbackService, Continuation<? super Unit> continuation) {
        return ((MediaUtils$playAlbum$1) create(playbackService, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            PlaybackService playbackService = (PlaybackService) this.L$0;
            MediaWrapper[] tracks = this.$album.getTracks();
            if (tracks != null) {
                if (!(!(tracks.length == 0))) {
                    tracks = null;
                }
                if (tracks != null) {
                    playbackService.load(tracks, 0);
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
