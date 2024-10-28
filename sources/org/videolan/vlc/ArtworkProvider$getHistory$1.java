package org.videolan.vlc;

import android.content.Context;
import java.util.Collection;
import java.util.List;
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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.ArtworkProvider$getHistory$1", f = "ArtworkProvider.kt", i = {}, l = {644, 351}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ArtworkProvider.kt */
final class ArtworkProvider$getHistory$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super byte[]>, Object> {
    final /* synthetic */ Context $ctx;
    int label;
    final /* synthetic */ ArtworkProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ArtworkProvider$getHistory$1(Context context, ArtworkProvider artworkProvider, Continuation<? super ArtworkProvider$getHistory$1> continuation) {
        super(2, continuation);
        this.$ctx = context;
        this.this$0 = artworkProvider;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ArtworkProvider$getHistory$1(this.$ctx, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super byte[]> continuation) {
        return ((ArtworkProvider$getHistory$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Context context = this.$ctx;
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new ArtworkProvider$getHistory$1$invokeSuspend$$inlined$getFromMl$1(context, (Continuation) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Collection collection = (List) obj;
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        this.label = 2;
        obj = this.this$0.getHomeImage(this.$ctx, "history", (MediaWrapper[]) collection.toArray(new MediaWrapper[0]), this);
        return obj == coroutine_suspended ? coroutine_suspended : obj;
    }
}
