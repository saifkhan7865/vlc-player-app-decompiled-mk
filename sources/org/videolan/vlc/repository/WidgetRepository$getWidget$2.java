package org.videolan.vlc.repository;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.mediadb.models.Widget;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Lorg/videolan/vlc/mediadb/models/Widget;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.repository.WidgetRepository$getWidget$2", f = "WidgetRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: WidgetRepository.kt */
final class WidgetRepository$getWidget$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Widget>, Object> {
    final /* synthetic */ int $id;
    int label;
    final /* synthetic */ WidgetRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WidgetRepository$getWidget$2(WidgetRepository widgetRepository, int i, Continuation<? super WidgetRepository$getWidget$2> continuation) {
        super(2, continuation);
        this.this$0 = widgetRepository;
        this.$id = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new WidgetRepository$getWidget$2(this.this$0, this.$id, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Widget> continuation) {
        return ((WidgetRepository$getWidget$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return this.this$0.widgetDao.get(this.$id);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
