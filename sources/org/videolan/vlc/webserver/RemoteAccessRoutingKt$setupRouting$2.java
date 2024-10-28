package org.videolan.vlc.webserver;

import android.content.Context;
import android.content.SharedPreferences;
import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$2", f = "RemoteAccessRouting.kt", i = {0}, l = {1497, 1508, 198, 201}, m = "invokeSuspend", n = {"$this$post"}, s = {"L$0"})
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$setupRouting$2 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $appContext;
    final /* synthetic */ CoroutineScope $scope;
    final /* synthetic */ SharedPreferences $settings;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessRoutingKt$setupRouting$2(Context context, SharedPreferences sharedPreferences, CoroutineScope coroutineScope, Continuation<? super RemoteAccessRoutingKt$setupRouting$2> continuation) {
        super(3, continuation);
        this.$appContext = context;
        this.$settings = sharedPreferences;
        this.$scope = coroutineScope;
    }

    public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
        RemoteAccessRoutingKt$setupRouting$2 remoteAccessRoutingKt$setupRouting$2 = new RemoteAccessRoutingKt$setupRouting$2(this.$appContext, this.$settings, this.$scope, continuation);
        remoteAccessRoutingKt$setupRouting$2.L$0 = pipelineContext;
        return remoteAccessRoutingKt$setupRouting$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v22, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: io.ktor.util.pipeline.PipelineContext} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r1 == 0) goto L_0x0034
            if (r1 == r5) goto L_0x002c
            if (r1 == r4) goto L_0x0027
            if (r1 == r3) goto L_0x0022
            if (r1 != r2) goto L_0x001a
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0145
        L_0x001a:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0128
        L_0x0027:
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00e4
        L_0x002c:
            java.lang.Object r1 = r13.L$0
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Exception -> 0x008a }
            goto L_0x0064
        L_0x0034:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.Object r14 = r13.L$0
            r1 = r14
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            java.lang.Object r14 = r1.getContext()     // Catch:{ Exception -> 0x008a }
            io.ktor.server.application.ApplicationCall r14 = (io.ktor.server.application.ApplicationCall) r14     // Catch:{ Exception -> 0x008a }
            java.lang.Class<io.ktor.http.Parameters> r7 = io.ktor.http.Parameters.class
            kotlin.reflect.KType r7 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r7)     // Catch:{ Exception -> 0x008a }
            java.lang.reflect.Type r8 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r7)     // Catch:{ Exception -> 0x008a }
            java.lang.Class<io.ktor.http.Parameters> r9 = io.ktor.http.Parameters.class
            kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)     // Catch:{ Exception -> 0x008a }
            io.ktor.util.reflect.TypeInfo r7 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r8, r9, r7)     // Catch:{ Exception -> 0x008a }
            r8 = r13
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8     // Catch:{ Exception -> 0x008a }
            r13.L$0 = r1     // Catch:{ Exception -> 0x008a }
            r13.label = r5     // Catch:{ Exception -> 0x008a }
            java.lang.Object r14 = io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveNullable(r14, r7, r8)     // Catch:{ Exception -> 0x008a }
            if (r14 != r0) goto L_0x0064
            return r0
        L_0x0064:
            if (r14 == 0) goto L_0x0069
            io.ktor.http.Parameters r14 = (io.ktor.http.Parameters) r14     // Catch:{ Exception -> 0x008a }
            goto L_0x008c
        L_0x0069:
            io.ktor.server.plugins.CannotTransformContentToTypeException r14 = new io.ktor.server.plugins.CannotTransformContentToTypeException     // Catch:{ Exception -> 0x008a }
            java.lang.Class<io.ktor.http.Parameters> r5 = io.ktor.http.Parameters.class
            kotlin.reflect.KType r5 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r5)     // Catch:{ Exception -> 0x008a }
            java.lang.reflect.Type r7 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r5)     // Catch:{ Exception -> 0x008a }
            java.lang.Class<io.ktor.http.Parameters> r8 = io.ktor.http.Parameters.class
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)     // Catch:{ Exception -> 0x008a }
            io.ktor.util.reflect.TypeInfo r5 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r7, r8, r5)     // Catch:{ Exception -> 0x008a }
            kotlin.reflect.KType r5 = r5.getKotlinType()     // Catch:{ Exception -> 0x008a }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch:{ Exception -> 0x008a }
            r14.<init>(r5)     // Catch:{ Exception -> 0x008a }
            throw r14     // Catch:{ Exception -> 0x008a }
        L_0x008a:
            r14 = r6
        L_0x008c:
            if (r14 == 0) goto L_0x0095
            java.lang.String r5 = "code"
            java.lang.String r14 = r14.get(r5)
            goto L_0x0096
        L_0x0095:
            r14 = r6
        L_0x0096:
            if (r14 != 0) goto L_0x00e7
            java.lang.Object r14 = r1.getContext()
            io.ktor.server.application.ApplicationCall r14 = (io.ktor.server.application.ApplicationCall) r14
            io.ktor.http.HttpStatusCode$Companion r1 = io.ktor.http.HttpStatusCode.Companion
            io.ktor.http.HttpStatusCode r1 = r1.getBadRequest()
            boolean r2 = r1 instanceof io.ktor.http.content.OutgoingContent
            if (r2 != 0) goto L_0x00c7
            boolean r2 = r1 instanceof byte[]
            if (r2 != 0) goto L_0x00c7
            io.ktor.server.response.ApplicationResponse r2 = r14.getResponse()
            java.lang.Class<io.ktor.http.HttpStatusCode> r3 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KType r3 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r3)
            java.lang.reflect.Type r5 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r3)
            java.lang.Class<io.ktor.http.HttpStatusCode> r7 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)
            io.ktor.util.reflect.TypeInfo r3 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r5, r7, r3)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r2, r3)
        L_0x00c7:
            io.ktor.server.response.ApplicationResponse r2 = r14.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r2 = r2.getPipeline()
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.Any"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r3)
            java.lang.Object r1 = (java.lang.Object) r1
            r3 = r13
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r13.L$0 = r6
            r13.label = r4
            java.lang.Object r14 = r2.execute(r14, r1, r3)
            if (r14 != r0) goto L_0x00e4
            return r0
        L_0x00e4:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x00e7:
            org.videolan.vlc.webserver.RemoteAccessOTP r4 = org.videolan.vlc.webserver.RemoteAccessOTP.INSTANCE
            android.content.Context r5 = r13.$appContext
            boolean r14 = r4.verifyCode(r5, r14)
            if (r14 == 0) goto L_0x012b
            org.videolan.vlc.webserver.RemoteAccessSession r14 = org.videolan.vlc.webserver.RemoteAccessSession.INSTANCE
            java.lang.Object r2 = r1.getContext()
            io.ktor.server.application.ApplicationCall r2 = (io.ktor.server.application.ApplicationCall) r2
            android.content.SharedPreferences r4 = r13.$settings
            r14.injectCookie(r2, r4)
            kotlinx.coroutines.CoroutineScope r7 = r13.$scope
            org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$2$1 r14 = new org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$2$1
            r14.<init>(r6)
            r10 = r14
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            r11 = 3
            r12 = 0
            r8 = 0
            r9 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r7, r8, r9, r10, r11, r12)
            java.lang.Object r14 = r1.getContext()
            r7 = r14
            io.ktor.server.application.ApplicationCall r7 = (io.ktor.server.application.ApplicationCall) r7
            r10 = r13
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r13.L$0 = r6
            r13.label = r3
            java.lang.String r8 = "/"
            r9 = 0
            r11 = 2
            java.lang.Object r14 = io.ktor.server.response.ApplicationResponseFunctionsKt.respondRedirect$default((io.ktor.server.application.ApplicationCall) r7, (java.lang.String) r8, (boolean) r9, (kotlin.coroutines.Continuation) r10, (int) r11, (java.lang.Object) r12)
            if (r14 != r0) goto L_0x0128
            return r0
        L_0x0128:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x012b:
            java.lang.Object r14 = r1.getContext()
            r7 = r14
            io.ktor.server.application.ApplicationCall r7 = (io.ktor.server.application.ApplicationCall) r7
            r10 = r13
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r13.L$0 = r6
            r13.label = r2
            java.lang.String r8 = "/index.html#/login/error"
            r9 = 0
            r11 = 2
            r12 = 0
            java.lang.Object r14 = io.ktor.server.response.ApplicationResponseFunctionsKt.respondRedirect$default((io.ktor.server.application.ApplicationCall) r7, (java.lang.String) r8, (boolean) r9, (kotlin.coroutines.Continuation) r10, (int) r11, (java.lang.Object) r12)
            if (r14 != r0) goto L_0x0145
            return r0
        L_0x0145:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
