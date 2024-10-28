package io.ktor.server.application;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "PluginConfig", "", "Lio/ktor/server/application/OnCallReceiveContext;", "call", "Lio/ktor/server/application/ApplicationCall;", "body"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.application.PluginBuilder$onCallReceive$2", f = "PluginBuilder.kt", i = {}, l = {107}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PluginBuilder.kt */
final class PluginBuilder$onCallReceive$2 extends SuspendLambda implements Function4<OnCallReceiveContext<PluginConfig>, ApplicationCall, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function4<OnCallReceiveContext<PluginConfig>, ApplicationCall, Object, Continuation<? super Unit>, Object> $block;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PluginBuilder$onCallReceive$2(Function4<? super OnCallReceiveContext<PluginConfig>, ? super ApplicationCall, Object, ? super Continuation<? super Unit>, ? extends Object> function4, Continuation<? super PluginBuilder$onCallReceive$2> continuation) {
        super(4, continuation);
        this.$block = function4;
    }

    public final Object invoke(OnCallReceiveContext<PluginConfig> onCallReceiveContext, ApplicationCall applicationCall, Object obj, Continuation<? super Unit> continuation) {
        PluginBuilder$onCallReceive$2 pluginBuilder$onCallReceive$2 = new PluginBuilder$onCallReceive$2(this.$block, continuation);
        pluginBuilder$onCallReceive$2.L$0 = onCallReceiveContext;
        pluginBuilder$onCallReceive$2.L$1 = applicationCall;
        pluginBuilder$onCallReceive$2.L$2 = obj;
        return pluginBuilder$onCallReceive$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object obj2 = this.L$2;
            Function4<OnCallReceiveContext<PluginConfig>, ApplicationCall, Object, Continuation<? super Unit>, Object> function4 = this.$block;
            this.L$0 = null;
            this.L$1 = null;
            this.label = 1;
            if (function4.invoke((OnCallReceiveContext) this.L$0, (ApplicationCall) this.L$1, obj2, this) == coroutine_suspended) {
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
