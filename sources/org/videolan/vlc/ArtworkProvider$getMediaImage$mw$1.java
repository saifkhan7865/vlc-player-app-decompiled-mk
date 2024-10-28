package org.videolan.vlc;

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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.ArtworkProvider$getMediaImage$mw$1", f = "ArtworkProvider.kt", i = {}, l = {644}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ArtworkProvider.kt */
final class ArtworkProvider$getMediaImage$mw$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MediaWrapper>, Object> {
    final /* synthetic */ Context $ctx;
    final /* synthetic */ long $mediaId;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ArtworkProvider$getMediaImage$mw$1(Context context, long j, Continuation<? super ArtworkProvider$getMediaImage$mw$1> continuation) {
        super(2, continuation);
        this.$ctx = context;
        this.$mediaId = j;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ArtworkProvider$getMediaImage$mw$1(this.$ctx, this.$mediaId, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super MediaWrapper> continuation) {
        return ((ArtworkProvider$getMediaImage$mw$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Context context = this.$ctx;
            long j = this.$mediaId;
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new ArtworkProvider$getMediaImage$mw$1$invokeSuspend$$inlined$getFromMl$1(context, (Continuation) null, j), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
