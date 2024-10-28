package io.ktor.server.engine;

import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.BaseApplicationEngineKt$installDefaultTransformationChecker$1", f = "BaseApplicationEngine.kt", i = {0}, l = {124, 145}, m = "invokeSuspend", n = {"$this$intercept"}, s = {"L$0"})
/* compiled from: BaseApplicationEngine.kt */
final class BaseApplicationEngineKt$installDefaultTransformationChecker$1 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;

    BaseApplicationEngineKt$installDefaultTransformationChecker$1(Continuation<? super BaseApplicationEngineKt$installDefaultTransformationChecker$1> continuation) {
        super(3, continuation);
    }

    public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
        BaseApplicationEngineKt$installDefaultTransformationChecker$1 baseApplicationEngineKt$installDefaultTransformationChecker$1 = new BaseApplicationEngineKt$installDefaultTransformationChecker$1(continuation);
        baseApplicationEngineKt$installDefaultTransformationChecker$1.L$0 = pipelineContext;
        return baseApplicationEngineKt$installDefaultTransformationChecker$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: io.ktor.util.pipeline.PipelineContext} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0022
            if (r1 == r3) goto L_0x001a
            if (r1 != r2) goto L_0x0012
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0084
        L_0x0012:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x001a:
            java.lang.Object r1 = r7.L$0
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ CannotTransformContentToTypeException -> 0x0038 }
            goto L_0x0084
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            r1 = r8
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            r8 = r7
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8     // Catch:{ CannotTransformContentToTypeException -> 0x0038 }
            r7.L$0 = r1     // Catch:{ CannotTransformContentToTypeException -> 0x0038 }
            r7.label = r3     // Catch:{ CannotTransformContentToTypeException -> 0x0038 }
            java.lang.Object r8 = r1.proceed(r8)     // Catch:{ CannotTransformContentToTypeException -> 0x0038 }
            if (r8 != r0) goto L_0x0084
            return r0
        L_0x0038:
            java.lang.Object r8 = r1.getContext()
            io.ktor.server.application.ApplicationCall r8 = (io.ktor.server.application.ApplicationCall) r8
            io.ktor.http.HttpStatusCode$Companion r1 = io.ktor.http.HttpStatusCode.Companion
            io.ktor.http.HttpStatusCode r1 = r1.getUnsupportedMediaType()
            boolean r3 = r1 instanceof io.ktor.http.content.OutgoingContent
            if (r3 != 0) goto L_0x0068
            boolean r3 = r1 instanceof byte[]
            if (r3 != 0) goto L_0x0068
            io.ktor.server.response.ApplicationResponse r3 = r8.getResponse()
            java.lang.Class<io.ktor.http.HttpStatusCode> r4 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KType r4 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r4)
            java.lang.reflect.Type r5 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r4)
            java.lang.Class<io.ktor.http.HttpStatusCode> r6 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            io.ktor.util.reflect.TypeInfo r4 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r5, r6, r4)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r3, r4)
        L_0x0068:
            io.ktor.server.response.ApplicationResponse r3 = r8.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r3 = r3.getPipeline()
            java.lang.String r4 = "null cannot be cast to non-null type kotlin.Any"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r4)
            r4 = r7
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r5 = 0
            r7.L$0 = r5
            r7.label = r2
            java.lang.Object r8 = r3.execute(r8, r1, r4)
            if (r8 != r0) goto L_0x0084
            return r0
        L_0x0084:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.BaseApplicationEngineKt$installDefaultTransformationChecker$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
