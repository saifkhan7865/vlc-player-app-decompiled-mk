package androidx.window.embedding;

import android.app.Activity;
import androidx.core.util.Consumer;
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
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.window.embedding.SplitController$addSplitListener$1$1", f = "SplitController.kt", i = {}, l = {80}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SplitController.kt */
final class SplitController$addSplitListener$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ Consumer<List<SplitInfo>> $consumer;
    int label;
    final /* synthetic */ SplitController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SplitController$addSplitListener$1$1(SplitController splitController, Activity activity, Consumer<List<SplitInfo>> consumer, Continuation<? super SplitController$addSplitListener$1$1> continuation) {
        super(2, continuation);
        this.this$0 = splitController;
        this.$activity = activity;
        this.$consumer = consumer;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SplitController$addSplitListener$1$1(this.this$0, this.$activity, this.$consumer, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SplitController$addSplitListener$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow<List<SplitInfo>> splitInfoList = this.this$0.splitInfoList(this.$activity);
            final Consumer<List<SplitInfo>> consumer = this.$consumer;
            this.label = 1;
            if (splitInfoList.collect(new FlowCollector() {
                public final Object emit(List<SplitInfo> list, Continuation<? super Unit> continuation) {
                    consumer.accept(list);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
