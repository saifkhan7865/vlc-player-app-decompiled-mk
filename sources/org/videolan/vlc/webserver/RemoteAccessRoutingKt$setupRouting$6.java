package org.videolan.vlc.webserver;

import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$6", f = "RemoteAccessRouting.kt", i = {0}, l = {244, 1506}, m = "invokeSuspend", n = {"$this$get"}, s = {"L$0"})
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$setupRouting$6 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;

    RemoteAccessRoutingKt$setupRouting$6(Continuation<? super RemoteAccessRoutingKt$setupRouting$6> continuation) {
        super(3, continuation);
    }

    public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
        RemoteAccessRoutingKt$setupRouting$6 remoteAccessRoutingKt$setupRouting$6 = new RemoteAccessRoutingKt$setupRouting$6(continuation);
        remoteAccessRoutingKt$setupRouting$6.L$0 = pipelineContext;
        return remoteAccessRoutingKt$setupRouting$6.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: io.ktor.util.pipeline.PipelineContext} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0023
            if (r1 == r3) goto L_0x001b
            if (r1 != r2) goto L_0x0013
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x00e7
        L_0x0013:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L_0x001b:
            java.lang.Object r1 = r14.L$0
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x0096
        L_0x0023:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.Object r15 = r14.L$0
            r1 = r15
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            java.lang.Object r15 = r1.getContext()
            io.ktor.server.application.ApplicationCall r15 = (io.ktor.server.application.ApplicationCall) r15
            io.ktor.server.request.ApplicationRequest r15 = r15.getRequest()
            io.ktor.http.Parameters r15 = r15.getQueryParameters()
            java.lang.String r4 = "file"
            java.lang.String r15 = r15.get(r4)
            if (r15 == 0) goto L_0x0096
            java.io.File r4 = new java.io.File
            r4.<init>(r15)
            boolean r5 = r4.exists()
            if (r5 == 0) goto L_0x0096
            java.lang.Object r5 = r1.getContext()
            io.ktor.server.application.ApplicationCall r5 = (io.ktor.server.application.ApplicationCall) r5
            io.ktor.server.response.ApplicationResponse r5 = r5.getResponse()
            io.ktor.http.HttpHeaders r6 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r6 = r6.getContentDisposition()
            io.ktor.http.ContentDisposition$Companion r7 = io.ktor.http.ContentDisposition.Companion
            io.ktor.http.ContentDisposition r8 = r7.getAttachment()
            java.lang.String r10 = r4.getName()
            java.lang.String r4 = "getName(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r4)
            r12 = 4
            r13 = 0
            java.lang.String r9 = "filename"
            r11 = 0
            io.ktor.http.ContentDisposition r4 = io.ktor.http.ContentDisposition.withParameter$default(r8, r9, r10, r11, r12, r13)
            java.lang.String r4 = r4.toString()
            io.ktor.server.response.ApplicationResponsePropertiesKt.header((io.ktor.server.response.ApplicationResponse) r5, (java.lang.String) r6, (java.lang.String) r4)
            java.lang.Object r4 = r1.getContext()
            r5 = r4
            io.ktor.server.application.ApplicationCall r5 = (io.ktor.server.application.ApplicationCall) r5
            java.io.File r6 = new java.io.File
            r6.<init>(r15)
            r14.L$0 = r1
            r14.label = r3
            r7 = 0
            r9 = 2
            r10 = 0
            r8 = r14
            java.lang.Object r15 = io.ktor.server.response.ApplicationResponseFunctionsJvmKt.respondFile$default(r5, r6, r7, r8, r9, r10)
            if (r15 != r0) goto L_0x0096
            return r0
        L_0x0096:
            java.lang.Object r15 = r1.getContext()
            io.ktor.server.application.ApplicationCall r15 = (io.ktor.server.application.ApplicationCall) r15
            io.ktor.http.HttpStatusCode$Companion r1 = io.ktor.http.HttpStatusCode.Companion
            io.ktor.http.HttpStatusCode r1 = r1.getNotFound()
            io.ktor.server.response.ApplicationResponse r3 = r15.getResponse()
            r3.status(r1)
            java.lang.String r1 = ""
            boolean r3 = r1 instanceof io.ktor.http.content.OutgoingContent
            if (r3 != 0) goto L_0x00ce
            boolean r3 = r1 instanceof byte[]
            if (r3 != 0) goto L_0x00ce
            io.ktor.server.response.ApplicationResponse r3 = r15.getResponse()
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            kotlin.reflect.KType r4 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r4)
            java.lang.reflect.Type r5 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r4)
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            io.ktor.util.reflect.TypeInfo r4 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r5, r6, r4)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r3, r4)
        L_0x00ce:
            io.ktor.server.response.ApplicationResponse r3 = r15.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r3 = r3.getPipeline()
            java.lang.Object r1 = (java.lang.Object) r1
            r4 = r14
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r5 = 0
            r14.L$0 = r5
            r14.label = r2
            java.lang.Object r15 = r3.execute(r15, r1, r4)
            if (r15 != r0) goto L_0x00e7
            return r0
        L_0x00e7:
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$6.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
