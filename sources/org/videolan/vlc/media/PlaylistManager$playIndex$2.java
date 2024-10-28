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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$playIndex$2", f = "PlaylistManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$playIndex$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper $mw;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$playIndex$2(MediaWrapper mediaWrapper, Continuation<? super PlaylistManager$playIndex$2> continuation) {
        super(2, continuation);
        this.$mw = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistManager$playIndex$2(this.$mw, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaylistManager$playIndex$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return MediaUtils.INSTANCE.retrieveMediaTitle(this.$mw);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}