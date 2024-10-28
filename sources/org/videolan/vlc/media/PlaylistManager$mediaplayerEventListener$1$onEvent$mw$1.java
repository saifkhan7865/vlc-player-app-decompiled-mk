package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1$onEvent$mw$1", f = "PlaylistManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$mediaplayerEventListener$1$onEvent$mw$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MediaWrapper>, Object> {
    int label;
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$mediaplayerEventListener$1$onEvent$mw$1(PlaylistManager playlistManager, Continuation<? super PlaylistManager$mediaplayerEventListener$1$onEvent$mw$1> continuation) {
        super(2, continuation);
        this.this$0 = playlistManager;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistManager$mediaplayerEventListener$1$onEvent$mw$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super MediaWrapper> continuation) {
        return ((PlaylistManager$mediaplayerEventListener$1$onEvent$mw$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaWrapper currentMedia = this.this$0.getCurrentMedia();
            if (currentMedia == null) {
                return null;
            }
            MediaWrapper findMedia = this.this$0.getMedialibrary().findMedia(currentMedia);
            if (findMedia.getType() != -1) {
                return findMedia;
            }
            findMedia.setType(currentMedia.getType());
            return findMedia;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
