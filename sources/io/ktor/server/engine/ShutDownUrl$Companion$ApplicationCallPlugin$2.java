package io.ktor.server.engine;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.OnCallContext;
import io.ktor.server.application.PluginBuilder;
import io.ktor.server.engine.ShutDownUrl;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/PluginBuilder;", "Lio/ktor/server/engine/ShutDownUrl$Config;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ShutDownUrl.kt */
final class ShutDownUrl$Companion$ApplicationCallPlugin$2 extends Lambda implements Function1<PluginBuilder<ShutDownUrl.Config>, Unit> {
    public static final ShutDownUrl$Companion$ApplicationCallPlugin$2 INSTANCE = new ShutDownUrl$Companion$ApplicationCallPlugin$2();

    ShutDownUrl$Companion$ApplicationCallPlugin$2() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PluginBuilder<ShutDownUrl.Config>) (PluginBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(PluginBuilder<ShutDownUrl.Config> pluginBuilder) {
        Intrinsics.checkNotNullParameter(pluginBuilder, "$this$createApplicationPlugin");
        final ShutDownUrl shutDownUrl = new ShutDownUrl(pluginBuilder.getPluginConfig().getShutDownUrl(), pluginBuilder.getPluginConfig().getExitCodeSupplier());
        pluginBuilder.onCall(new AnonymousClass1((Continuation<? super AnonymousClass1>) null));
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H@"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/OnCallContext;", "Lio/ktor/server/engine/ShutDownUrl$Config;", "call", "Lio/ktor/server/application/ApplicationCall;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.server.engine.ShutDownUrl$Companion$ApplicationCallPlugin$2$1", f = "ShutDownUrl.kt", i = {}, l = {103}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.ktor.server.engine.ShutDownUrl$Companion$ApplicationCallPlugin$2$1  reason: invalid class name */
    /* compiled from: ShutDownUrl.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function3<OnCallContext<ShutDownUrl.Config>, ApplicationCall, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public final Object invoke(OnCallContext<ShutDownUrl.Config> onCallContext, ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
            AnonymousClass1 r2 = new AnonymousClass1(shutDownUrl, continuation);
            r2.L$0 = applicationCall;
            return r2.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ApplicationCall applicationCall = (ApplicationCall) this.L$0;
                if (Intrinsics.areEqual((Object) ApplicationRequestPropertiesKt.getUri(applicationCall.getRequest()), (Object) shutDownUrl.getUrl())) {
                    this.label = 1;
                    if (shutDownUrl.doShutdown(applicationCall, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }
}
