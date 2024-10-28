package androidx.window.embedding;

import android.app.Activity;
import androidx.profileinstaller.ProfileInstallReceiver$$ExternalSyntheticLambda0;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "", "Landroidx/window/embedding/SplitInfo;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.window.embedding.SplitController$splitInfoList$1", f = "SplitController.kt", i = {}, l = {127}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SplitController.kt */
final class SplitController$splitInfoList$1 extends SuspendLambda implements Function2<ProducerScope<? super List<? extends SplitInfo>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SplitController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SplitController$splitInfoList$1(SplitController splitController, Activity activity, Continuation<? super SplitController$splitInfoList$1> continuation) {
        super(2, continuation);
        this.this$0 = splitController;
        this.$activity = activity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SplitController$splitInfoList$1 splitController$splitInfoList$1 = new SplitController$splitInfoList$1(this.this$0, this.$activity, continuation);
        splitController$splitInfoList$1.L$0 = obj;
        return splitController$splitInfoList$1;
    }

    public final Object invoke(ProducerScope<? super List<SplitInfo>> producerScope, Continuation<? super Unit> continuation) {
        return ((SplitController$splitInfoList$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            final SplitController$splitInfoList$1$$ExternalSyntheticLambda0 splitController$splitInfoList$1$$ExternalSyntheticLambda0 = new SplitController$splitInfoList$1$$ExternalSyntheticLambda0(producerScope);
            this.this$0.embeddingBackend.addSplitListenerForActivity(this.$activity, new ProfileInstallReceiver$$ExternalSyntheticLambda0(), splitController$splitInfoList$1$$ExternalSyntheticLambda0);
            final SplitController splitController = this.this$0;
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, new Function0<Unit>() {
                public final void invoke() {
                    splitController.embeddingBackend.removeSplitListenerForActivity(splitController$splitInfoList$1$$ExternalSyntheticLambda0);
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

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(ProducerScope producerScope, List list) {
        producerScope.m1139trySendJP2dKIU(list);
    }
}
