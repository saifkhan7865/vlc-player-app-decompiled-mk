package org.videolan.vlc.providers.medialibrary;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.medialibrary.TracksProvider$getPage$1", f = "TracksProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: TracksProvider.kt */
final class TracksProvider$getPage$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper[] $list;
    final /* synthetic */ int $startposition;
    int label;
    final /* synthetic */ TracksProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TracksProvider$getPage$1(TracksProvider tracksProvider, MediaWrapper[] mediaWrapperArr, int i, Continuation<? super TracksProvider$getPage$1> continuation) {
        super(2, continuation);
        this.this$0 = tracksProvider;
        this.$list = mediaWrapperArr;
        this.$startposition = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TracksProvider$getPage$1(this.this$0, this.$list, this.$startposition, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TracksProvider$getPage$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            TracksProvider tracksProvider = this.this$0;
            MediaWrapper[] mediaWrapperArr = this.$list;
            Intrinsics.checkNotNullExpressionValue(mediaWrapperArr, "$list");
            tracksProvider.completeHeaders((MediaLibraryItem[]) mediaWrapperArr, this.$startposition);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
