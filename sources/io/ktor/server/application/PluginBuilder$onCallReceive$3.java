package io.ktor.server.application;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "PluginConfig", "", "Lio/ktor/server/application/OnCallReceiveContext;", "call", "Lio/ktor/server/application/ApplicationCall;", "<anonymous parameter 1>"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.application.PluginBuilder$onCallReceive$3", f = "PluginBuilder.kt", i = {}, l = {163}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PluginBuilder.kt */
final class PluginBuilder$onCallReceive$3 extends SuspendLambda implements Function4<OnCallReceiveContext<PluginConfig>, ApplicationCall, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3<OnCallReceiveContext<PluginConfig>, ApplicationCall, Continuation<? super Unit>, Object> $block;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PluginBuilder$onCallReceive$3(Function3<? super OnCallReceiveContext<PluginConfig>, ? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super PluginBuilder$onCallReceive$3> continuation) {
        super(4, continuation);
        this.$block = function3;
    }

    public final Object invoke(OnCallReceiveContext<PluginConfig> onCallReceiveContext, ApplicationCall applicationCall, Object obj, Continuation<? super Unit> continuation) {
        PluginBuilder$onCallReceive$3 pluginBuilder$onCallReceive$3 = new PluginBuilder$onCallReceive$3(this.$block, continuation);
        pluginBuilder$onCallReceive$3.L$0 = onCallReceiveContext;
        pluginBuilder$onCallReceive$3.L$1 = applicationCall;
        return pluginBuilder$onCallReceive$3.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function3<OnCallReceiveContext<PluginConfig>, ApplicationCall, Continuation<? super Unit>, Object> function3 = this.$block;
            this.L$0 = null;
            this.label = 1;
            if (function3.invoke((OnCallReceiveContext) this.L$0, (ApplicationCall) this.L$1, this) == coroutine_suspended) {
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
