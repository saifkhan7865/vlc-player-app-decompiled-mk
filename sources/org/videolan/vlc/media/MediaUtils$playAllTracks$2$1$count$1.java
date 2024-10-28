package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.providers.medialibrary.FoldersProvider;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.MediaUtils$playAllTracks$2$1$count$1", f = "MediaUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaUtils.kt */
final class MediaUtils$playAllTracks$2$1$count$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final /* synthetic */ FoldersProvider $provider;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaUtils$playAllTracks$2$1$count$1(FoldersProvider foldersProvider, Continuation<? super MediaUtils$playAllTracks$2$1$count$1> continuation) {
        super(2, continuation);
        this.$provider = foldersProvider;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaUtils$playAllTracks$2$1$count$1(this.$provider, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return ((MediaUtils$playAllTracks$2$1$count$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxInt(this.$provider.getTotalCount());
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
