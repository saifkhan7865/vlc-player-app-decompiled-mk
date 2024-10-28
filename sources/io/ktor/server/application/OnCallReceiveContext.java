package io.ktor.server.application;

import io.ktor.util.KtorDsl;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@KtorDsl
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\b\u0000\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJO\u0010\u000b\u001a\u00020\f2<\u0010\r\u001a8\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000e¢\u0006\u0002\b\u0015H@ø\u0001\u0000¢\u0006\u0002\u0010\u0016R \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lio/ktor/server/application/OnCallReceiveContext;", "PluginConfig", "", "Lio/ktor/server/application/CallContext;", "pluginConfig", "context", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "(Ljava/lang/Object;Lio/ktor/util/pipeline/PipelineContext;)V", "getContext", "()Lio/ktor/util/pipeline/PipelineContext;", "transformBody", "", "transform", "Lkotlin/Function3;", "Lio/ktor/server/application/TransformBodyContext;", "Lio/ktor/utils/io/ByteReadChannel;", "Lkotlin/ParameterName;", "name", "body", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: KtorCallContexts.kt */
public final class OnCallReceiveContext<PluginConfig> extends CallContext<PluginConfig> {
    private final PipelineContext<Object, ApplicationCall> context;

    /* access modifiers changed from: protected */
    public PipelineContext<Object, ApplicationCall> getContext() {
        return this.context;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnCallReceiveContext(PluginConfig pluginconfig, PipelineContext<Object, ApplicationCall> pipelineContext) {
        super(pluginconfig, pipelineContext);
        Intrinsics.checkNotNullParameter(pluginconfig, "pluginConfig");
        Intrinsics.checkNotNullParameter(pipelineContext, "context");
        this.context = pipelineContext;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object transformBody(kotlin.jvm.functions.Function3<? super io.ktor.server.application.TransformBodyContext, ? super io.ktor.utils.io.ByteReadChannel, ? super kotlin.coroutines.Continuation<java.lang.Object>, ? extends java.lang.Object> r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof io.ktor.server.application.OnCallReceiveContext$transformBody$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.server.application.OnCallReceiveContext$transformBody$1 r0 = (io.ktor.server.application.OnCallReceiveContext$transformBody$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.server.application.OnCallReceiveContext$transformBody$1 r0 = new io.ktor.server.application.OnCallReceiveContext$transformBody$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r8 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r8 = (io.ktor.util.pipeline.PipelineContext) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x008e
        L_0x002e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r9)
            io.ktor.util.pipeline.PipelineContext r9 = r7.getContext()
            java.lang.Object r9 = r9.getSubject()
            boolean r2 = r9 instanceof io.ktor.utils.io.ByteReadChannel
            if (r2 == 0) goto L_0x0048
            io.ktor.utils.io.ByteReadChannel r9 = (io.ktor.utils.io.ByteReadChannel) r9
            goto L_0x0049
        L_0x0048:
            r9 = 0
        L_0x0049:
            if (r9 != 0) goto L_0x004e
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x004e:
            io.ktor.util.pipeline.PipelineContext r2 = r7.getContext()
            java.lang.Object r2 = r2.getContext()
            io.ktor.server.application.ApplicationCall r2 = (io.ktor.server.application.ApplicationCall) r2
            io.ktor.util.reflect.TypeInfo r2 = io.ktor.server.application.ApplicationCallKt.getReceiveType(r2)
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r4 = io.ktor.utils.io.ByteReadChannel.class
            kotlin.reflect.KType r4 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r4)
            java.lang.reflect.Type r5 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r4)
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r6 = io.ktor.utils.io.ByteReadChannel.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            io.ktor.util.reflect.TypeInfo r4 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r5, r6, r4)
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r4)
            if (r4 == 0) goto L_0x0079
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0079:
            io.ktor.server.application.TransformBodyContext r4 = new io.ktor.server.application.TransformBodyContext
            r4.<init>(r2)
            io.ktor.util.pipeline.PipelineContext r2 = r7.getContext()
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r9 = r8.invoke(r4, r9, r0)
            if (r9 != r1) goto L_0x008d
            return r1
        L_0x008d:
            r8 = r2
        L_0x008e:
            r8.setSubject(r9)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.application.OnCallReceiveContext.transformBody(kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
