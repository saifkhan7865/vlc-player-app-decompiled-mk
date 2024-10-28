package io.ktor.server.application;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.application.OnCallRespondContext", f = "KtorCallContexts.kt", i = {}, l = {86}, m = "transformBody", n = {}, s = {})
/* compiled from: KtorCallContexts.kt */
final class OnCallRespondContext$transformBody$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OnCallRespondContext<PluginConfig> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OnCallRespondContext$transformBody$1(OnCallRespondContext<PluginConfig> onCallRespondContext, Continuation<? super OnCallRespondContext$transformBody$1> continuation) {
        super(continuation);
        this.this$0 = onCallRespondContext;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.transformBody((Function3<? super TransformBodyContext, Object, ? super Continuation<Object>, ? extends Object>) null, this);
    }
}
