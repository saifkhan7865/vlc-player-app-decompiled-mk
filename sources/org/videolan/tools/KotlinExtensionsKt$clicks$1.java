package org.videolan.tools;

import android.view.View;
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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.tools.KotlinExtensionsKt$clicks$1", f = "KotlinExtensions.kt", i = {}, l = {141}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: KotlinExtensions.kt */
final class KotlinExtensionsKt$clicks$1 extends SuspendLambda implements Function2<ProducerScope<? super Unit>, Continuation<? super Unit>, Object> {
    final /* synthetic */ View $this_clicks;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KotlinExtensionsKt$clicks$1(View view, Continuation<? super KotlinExtensionsKt$clicks$1> continuation) {
        super(2, continuation);
        this.$this_clicks = view;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        KotlinExtensionsKt$clicks$1 kotlinExtensionsKt$clicks$1 = new KotlinExtensionsKt$clicks$1(this.$this_clicks, continuation);
        kotlinExtensionsKt$clicks$1.L$0 = obj;
        return kotlinExtensionsKt$clicks$1;
    }

    public final Object invoke(ProducerScope<? super Unit> producerScope, Continuation<? super Unit> continuation) {
        return ((KotlinExtensionsKt$clicks$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            this.$this_clicks.setOnClickListener(new KotlinExtensionsKt$clicks$1$$ExternalSyntheticLambda0(producerScope));
            final View view = this.$this_clicks;
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, new Function0<Unit>() {
                public final void invoke() {
                    view.setOnClickListener((View.OnClickListener) null);
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
    public static final void invokeSuspend$lambda$0(ProducerScope producerScope, View view) {
        producerScope.m1139trySendJP2dKIU(Unit.INSTANCE);
    }
}
