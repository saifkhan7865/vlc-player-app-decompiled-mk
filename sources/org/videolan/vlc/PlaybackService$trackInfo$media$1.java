package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.libvlc.interfaces.ILibVLC;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.VLCInstance;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "Lorg/videolan/libvlc/interfaces/IMedia;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.PlaybackService$trackInfo$media$1", f = "PlaybackService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaybackService.kt */
final class PlaybackService$trackInfo$media$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super IMedia>, Object> {
    final /* synthetic */ MediaWrapper $mediaWrapper;
    int label;
    final /* synthetic */ PlaybackService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaybackService$trackInfo$media$1(PlaybackService playbackService, MediaWrapper mediaWrapper, Continuation<? super PlaybackService$trackInfo$media$1> continuation) {
        super(2, continuation);
        this.this$0 = playbackService;
        this.$mediaWrapper = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaybackService$trackInfo$media$1(this.this$0, this.$mediaWrapper, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super IMedia> continuation) {
        return ((PlaybackService$trackInfo$media$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            IMedia fromUri = this.this$0.mediaFactory.getFromUri((ILibVLC) VLCInstance.INSTANCE.getInstance(this.this$0), this.$mediaWrapper.getUri());
            fromUri.parse();
            return fromUri;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
