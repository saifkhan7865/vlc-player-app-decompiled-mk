package io.ktor.server.application;

import io.ktor.util.KtorDsl;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@KtorDsl
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\b\u0000\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJO\u0010\u000b\u001a\u00020\f2<\u0010\r\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000e¢\u0006\u0002\b\u0014H@ø\u0001\u0000¢\u0006\u0002\u0010\u0015R \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lio/ktor/server/application/OnCallRespondContext;", "PluginConfig", "", "Lio/ktor/server/application/CallContext;", "pluginConfig", "context", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "(Ljava/lang/Object;Lio/ktor/util/pipeline/PipelineContext;)V", "getContext", "()Lio/ktor/util/pipeline/PipelineContext;", "transformBody", "", "transform", "Lkotlin/Function3;", "Lio/ktor/server/application/TransformBodyContext;", "Lkotlin/ParameterName;", "name", "body", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: KtorCallContexts.kt */
public final class OnCallRespondContext<PluginConfig> extends CallContext<PluginConfig> {
    private final PipelineContext<Object, ApplicationCall> context;

    /* access modifiers changed from: protected */
    public PipelineContext<Object, ApplicationCall> getContext() {
        return this.context;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnCallRespondContext(PluginConfig pluginconfig, PipelineContext<Object, ApplicationCall> pipelineContext) {
        super(pluginconfig, pipelineContext);
        Intrinsics.checkNotNullParameter(pluginconfig, "pluginConfig");
        Intrinsics.checkNotNullParameter(pipelineContext, "context");
        this.context = pipelineContext;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object transformBody(kotlin.jvm.functions.Function3<? super io.ktor.server.application.TransformBodyContext, java.lang.Object, ? super kotlin.coroutines.Continuation<java.lang.Object>, ? extends java.lang.Object> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.server.application.OnCallRespondContext$transformBody$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.server.application.OnCallRespondContext$transformBody$1 r0 = (io.ktor.server.application.OnCallRespondContext$transformBody$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.server.application.OnCallRespondContext$transformBody$1 r0 = new io.ktor.server.application.OnCallRespondContext$transformBody$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r6 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r6 = (io.ktor.util.pipeline.PipelineContext) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0068
        L_0x002e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r7)
            io.ktor.server.application.TransformBodyContext r7 = new io.ktor.server.application.TransformBodyContext
            io.ktor.util.pipeline.PipelineContext r2 = r5.getContext()
            java.lang.Object r2 = r2.getContext()
            io.ktor.server.application.ApplicationCall r2 = (io.ktor.server.application.ApplicationCall) r2
            io.ktor.server.response.ApplicationResponse r2 = r2.getResponse()
            io.ktor.util.reflect.TypeInfo r2 = io.ktor.server.response.ResponseTypeKt.getResponseType(r2)
            r7.<init>(r2)
            io.ktor.util.pipeline.PipelineContext r2 = r5.getContext()
            io.ktor.util.pipeline.PipelineContext r4 = r5.getContext()
            java.lang.Object r4 = r4.getSubject()
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r7 = r6.invoke(r7, r4, r0)
            if (r7 != r1) goto L_0x0067
            return r1
        L_0x0067:
            r6 = r2
        L_0x0068:
            r6.setSubject(r7)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.application.OnCallRespondContext.transformBody(kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
