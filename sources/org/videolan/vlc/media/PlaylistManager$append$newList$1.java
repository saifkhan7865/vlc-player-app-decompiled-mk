package org.videolan.vlc.media;

import java.util.List;
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
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$append$newList$1", f = "PlaylistManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$append$newList$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<MediaWrapper>>, Object> {
    final /* synthetic */ List<MediaWrapper> $list;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$append$newList$1(List<? extends MediaWrapper> list, Continuation<? super PlaylistManager$append$newList$1> continuation) {
        super(2, continuation);
        this.$list = list;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistManager$append$newList$1(this.$list, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<MediaWrapper>> continuation) {
        return ((PlaylistManager$append$newList$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return KextensionsKt.updateWithMLMeta(this.$list);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
