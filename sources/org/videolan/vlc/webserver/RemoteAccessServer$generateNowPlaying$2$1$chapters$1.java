package org.videolan.vlc.webserver;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/libvlc/MediaPlayer$Chapter;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessServer$generateNowPlaying$2$1$chapters$1", f = "RemoteAccessServer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessServer.kt */
final class RemoteAccessServer$generateNowPlaying$2$1$chapters$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MediaPlayer.Chapter[]>, Object> {
    final /* synthetic */ PlaybackService $service;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessServer$generateNowPlaying$2$1$chapters$1(PlaybackService playbackService, Continuation<? super RemoteAccessServer$generateNowPlaying$2$1$chapters$1> continuation) {
        super(2, continuation);
        this.$service = playbackService;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessServer$generateNowPlaying$2$1$chapters$1(this.$service, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super MediaPlayer.Chapter[]> continuation) {
        return ((RemoteAccessServer$generateNowPlaying$2$1$chapters$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaPlayer.Chapter[] chapters = this.$service.getChapters(-1);
            return chapters == null ? new MediaPlayer.Chapter[0] : chapters;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
