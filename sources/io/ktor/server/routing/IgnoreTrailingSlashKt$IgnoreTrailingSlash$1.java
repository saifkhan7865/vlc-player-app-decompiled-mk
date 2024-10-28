package io.ktor.server.routing;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.OnCallContext;
import io.ktor.server.application.PluginBuilder;
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

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/PluginBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: IgnoreTrailingSlash.kt */
final class IgnoreTrailingSlashKt$IgnoreTrailingSlash$1 extends Lambda implements Function1<PluginBuilder<Unit>, Unit> {
    public static final IgnoreTrailingSlashKt$IgnoreTrailingSlash$1 INSTANCE = new IgnoreTrailingSlashKt$IgnoreTrailingSlash$1();

    IgnoreTrailingSlashKt$IgnoreTrailingSlash$1() {
        super(1);
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H@"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/OnCallContext;", "call", "Lio/ktor/server/application/ApplicationCall;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.server.routing.IgnoreTrailingSlashKt$IgnoreTrailingSlash$1$1", f = "IgnoreTrailingSlash.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.ktor.server.routing.IgnoreTrailingSlashKt$IgnoreTrailingSlash$1$1  reason: invalid class name */
    /* compiled from: IgnoreTrailingSlash.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function3<OnCallContext<Unit>, ApplicationCall, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public final Object invoke(OnCallContext<Unit> onCallContext, ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
            AnonymousClass1 r1 = new AnonymousClass1(continuation);
            r1.L$0 = applicationCall;
            return r1.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                IgnoreTrailingSlashKt.setIgnoreTrailingSlash((ApplicationCall) this.L$0, true);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PluginBuilder<Unit>) (PluginBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(PluginBuilder<Unit> pluginBuilder) {
        Intrinsics.checkNotNullParameter(pluginBuilder, "$this$createApplicationPlugin");
        pluginBuilder.onCall(new AnonymousClass1((Continuation<? super AnonymousClass1>) null));
    }
}
