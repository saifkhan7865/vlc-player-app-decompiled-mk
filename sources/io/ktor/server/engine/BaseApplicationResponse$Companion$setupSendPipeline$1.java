package io.ktor.server.engine;

import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "body"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.BaseApplicationResponse$Companion$setupSendPipeline$1", f = "BaseApplicationResponse.kt", i = {}, l = {317}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BaseApplicationResponse.kt */
final class BaseApplicationResponse$Companion$setupSendPipeline$1 extends SuspendLambda implements Function3<PipelineContext<Object, ApplicationCall>, Object, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    BaseApplicationResponse$Companion$setupSendPipeline$1(Continuation<? super BaseApplicationResponse$Companion$setupSendPipeline$1> continuation) {
        super(3, continuation);
    }

    public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        BaseApplicationResponse$Companion$setupSendPipeline$1 baseApplicationResponse$Companion$setupSendPipeline$1 = new BaseApplicationResponse$Companion$setupSendPipeline$1(continuation);
        baseApplicationResponse$Companion$setupSendPipeline$1.L$0 = pipelineContext;
        baseApplicationResponse$Companion$setupSendPipeline$1.L$1 = obj;
        return baseApplicationResponse$Companion$setupSendPipeline$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: io.ktor.server.engine.BaseApplicationResponse} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L_0x0017
            if (r1 != r2) goto L_0x000f
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x006f
        L_0x000f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x0017:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r6.L$0
            io.ktor.util.pipeline.PipelineContext r7 = (io.ktor.util.pipeline.PipelineContext) r7
            java.lang.Object r1 = r6.L$1
            java.lang.Object r3 = r7.getContext()
            io.ktor.server.application.ApplicationCall r3 = (io.ktor.server.application.ApplicationCall) r3
            boolean r3 = io.ktor.server.application.ApplicationCallKt.isHandled(r3)
            if (r3 == 0) goto L_0x002f
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x002f:
            boolean r3 = r1 instanceof io.ktor.http.content.OutgoingContent
            if (r3 == 0) goto L_0x0072
            java.lang.Object r3 = r7.getContext()
            io.ktor.server.application.ApplicationCall r3 = (io.ktor.server.application.ApplicationCall) r3
            io.ktor.server.response.ApplicationResponse r3 = r3.getResponse()
            boolean r4 = r3 instanceof io.ktor.server.engine.BaseApplicationResponse
            r5 = 0
            if (r4 == 0) goto L_0x0045
            io.ktor.server.engine.BaseApplicationResponse r3 = (io.ktor.server.engine.BaseApplicationResponse) r3
            goto L_0x0046
        L_0x0045:
            r3 = r5
        L_0x0046:
            if (r3 != 0) goto L_0x005f
            java.lang.Object r7 = r7.getContext()
            io.ktor.server.application.ApplicationCall r7 = (io.ktor.server.application.ApplicationCall) r7
            io.ktor.util.Attributes r7 = r7.getAttributes()
            io.ktor.server.engine.BaseApplicationResponse$Companion r3 = io.ktor.server.engine.BaseApplicationResponse.Companion
            io.ktor.util.AttributeKey r3 = r3.getEngineResponseAttributeKey()
            java.lang.Object r7 = r7.get(r3)
            r3 = r7
            io.ktor.server.engine.BaseApplicationResponse r3 = (io.ktor.server.engine.BaseApplicationResponse) r3
        L_0x005f:
            io.ktor.http.content.OutgoingContent r1 = (io.ktor.http.content.OutgoingContent) r1
            r7 = r6
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r6.L$0 = r5
            r6.label = r2
            java.lang.Object r7 = r3.respondOutgoingContent(r1, r7)
            if (r7 != r0) goto L_0x006f
            return r0
        L_0x006f:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x0072:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Response pipeline couldn't transform '"
            r0.<init>(r2)
            java.lang.Class r1 = r1.getClass()
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)
            r0.append(r1)
            java.lang.String r1 = "' to the OutgoingContent"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.BaseApplicationResponse$Companion$setupSendPipeline$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
