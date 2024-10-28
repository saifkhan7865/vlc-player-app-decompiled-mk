package io.ktor.server.application;

import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.application.OnCallReceiveContext", f = "KtorCallContexts.kt", i = {}, l = {65}, m = "transformBody", n = {}, s = {})
/* compiled from: KtorCallContexts.kt */
final class OnCallReceiveContext$transformBody$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OnCallReceiveContext<PluginConfig> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OnCallReceiveContext$transformBody$1(OnCallReceiveContext<PluginConfig> onCallReceiveContext, Continuation<? super OnCallReceiveContext$transformBody$1> continuation) {
        super(continuation);
        this.this$0 = onCallReceiveContext;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.transformBody((Function3<? super TransformBodyContext, ? super ByteReadChannel, ? super Continuation<Object>, ? extends Object>) null, this);
    }
}
