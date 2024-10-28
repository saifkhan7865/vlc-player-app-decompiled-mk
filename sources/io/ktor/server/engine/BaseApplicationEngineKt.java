package io.ktor.server.engine;

import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationCallPipeline;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a!\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, d2 = {"installDefaultInterceptors", "", "Lio/ktor/server/application/Application;", "installDefaultTransformationChecker", "verifyHostHeader", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/util/pipeline/PipelineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: BaseApplicationEngine.kt */
public final class BaseApplicationEngineKt {
    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object verifyHostHeader(io.ktor.util.pipeline.PipelineContext<kotlin.Unit, io.ktor.server.application.ApplicationCall> r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.server.engine.BaseApplicationEngineKt$verifyHostHeader$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.server.engine.BaseApplicationEngineKt$verifyHostHeader$1 r0 = (io.ktor.server.engine.BaseApplicationEngineKt$verifyHostHeader$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.server.engine.BaseApplicationEngineKt$verifyHostHeader$1 r0 = new io.ktor.server.engine.BaseApplicationEngineKt$verifyHostHeader$1
            r0.<init>(r9)
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
            goto L_0x00a5
        L_0x002e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Object r9 = r8.getContext()
            io.ktor.server.application.ApplicationCall r9 = (io.ktor.server.application.ApplicationCall) r9
            io.ktor.server.request.ApplicationRequest r9 = r9.getRequest()
            io.ktor.http.Headers r9 = r9.getHeaders()
            io.ktor.http.HttpHeaders r2 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r2 = r2.getHost()
            java.util.List r9 = r9.getAll(r2)
            if (r9 != 0) goto L_0x0056
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0056:
            int r9 = r9.size()
            if (r9 <= r3) goto L_0x00a8
            java.lang.Object r9 = r8.getContext()
            io.ktor.server.application.ApplicationCall r9 = (io.ktor.server.application.ApplicationCall) r9
            io.ktor.http.HttpStatusCode$Companion r2 = io.ktor.http.HttpStatusCode.Companion
            io.ktor.http.HttpStatusCode r2 = r2.getBadRequest()
            boolean r4 = r2 instanceof io.ktor.http.content.OutgoingContent
            if (r4 != 0) goto L_0x008b
            boolean r4 = r2 instanceof byte[]
            if (r4 != 0) goto L_0x008b
            io.ktor.server.response.ApplicationResponse r4 = r9.getResponse()
            java.lang.Class<io.ktor.http.HttpStatusCode> r5 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KType r5 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r5)
            java.lang.reflect.Type r6 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r5)
            java.lang.Class<io.ktor.http.HttpStatusCode> r7 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)
            io.ktor.util.reflect.TypeInfo r5 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r6, r7, r5)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r4, r5)
        L_0x008b:
            io.ktor.server.response.ApplicationResponse r4 = r9.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r4 = r4.getPipeline()
            java.lang.String r5 = "null cannot be cast to non-null type kotlin.Any"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r5)
            java.lang.Object r2 = (java.lang.Object) r2
            r0.L$0 = r8
            r0.label = r3
            java.lang.Object r9 = r4.execute(r9, r2, r0)
            if (r9 != r1) goto L_0x00a5
            return r1
        L_0x00a5:
            r8.finish()
        L_0x00a8:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.BaseApplicationEngineKt.verifyHostHeader(io.ktor.util.pipeline.PipelineContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final void installDefaultInterceptors(Application application) {
        application.intercept(ApplicationCallPipeline.ApplicationPhase.getFallback(), new BaseApplicationEngineKt$installDefaultInterceptors$1((Continuation<? super BaseApplicationEngineKt$installDefaultInterceptors$1>) null));
        application.intercept(ApplicationCallPipeline.ApplicationPhase.getCall(), new BaseApplicationEngineKt$installDefaultInterceptors$2((Continuation<? super BaseApplicationEngineKt$installDefaultInterceptors$2>) null));
    }

    /* access modifiers changed from: private */
    public static final void installDefaultTransformationChecker(Application application) {
        application.intercept(ApplicationCallPipeline.ApplicationPhase.getPlugins(), new BaseApplicationEngineKt$installDefaultTransformationChecker$1((Continuation<? super BaseApplicationEngineKt$installDefaultTransformationChecker$1>) null));
        PipelinePhase pipelinePhase = new PipelinePhase("BodyTransformationCheckPostRender");
        application.getSendPipeline().insertPhaseAfter(ApplicationSendPipeline.Phases.getRender(), pipelinePhase);
        application.getSendPipeline().intercept(pipelinePhase, new BaseApplicationEngineKt$installDefaultTransformationChecker$2((Continuation<? super BaseApplicationEngineKt$installDefaultTransformationChecker$2>) null));
    }
}
