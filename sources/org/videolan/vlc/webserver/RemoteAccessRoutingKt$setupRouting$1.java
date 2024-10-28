package org.videolan.vlc.webserver;

import android.content.Context;
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
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$1", f = "RemoteAccessRouting.kt", i = {0}, l = {1497, 178}, m = "invokeSuspend", n = {"$this$post"}, s = {"L$0"})
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$setupRouting$1 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $appContext;
    final /* synthetic */ CoroutineScope $scope;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessRoutingKt$setupRouting$1(Context context, CoroutineScope coroutineScope, Continuation<? super RemoteAccessRoutingKt$setupRouting$1> continuation) {
        super(3, continuation);
        this.$appContext = context;
        this.$scope = coroutineScope;
    }

    public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
        RemoteAccessRoutingKt$setupRouting$1 remoteAccessRoutingKt$setupRouting$1 = new RemoteAccessRoutingKt$setupRouting$1(this.$appContext, this.$scope, continuation);
        remoteAccessRoutingKt$setupRouting$1.L$0 = pipelineContext;
        return remoteAccessRoutingKt$setupRouting$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: io.ktor.util.pipeline.PipelineContext} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x0024
            if (r1 == r3) goto L_0x001c
            if (r1 != r2) goto L_0x0014
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00d2
        L_0x0014:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x001c:
            java.lang.Object r1 = r13.L$0
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Exception -> 0x007a }
            goto L_0x0054
        L_0x0024:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.Object r14 = r13.L$0
            r1 = r14
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            java.lang.Object r14 = r1.getContext()     // Catch:{ Exception -> 0x007a }
            io.ktor.server.application.ApplicationCall r14 = (io.ktor.server.application.ApplicationCall) r14     // Catch:{ Exception -> 0x007a }
            java.lang.Class<io.ktor.http.Parameters> r5 = io.ktor.http.Parameters.class
            kotlin.reflect.KType r5 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r5)     // Catch:{ Exception -> 0x007a }
            java.lang.reflect.Type r6 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r5)     // Catch:{ Exception -> 0x007a }
            java.lang.Class<io.ktor.http.Parameters> r7 = io.ktor.http.Parameters.class
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch:{ Exception -> 0x007a }
            io.ktor.util.reflect.TypeInfo r5 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r6, r7, r5)     // Catch:{ Exception -> 0x007a }
            r6 = r13
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch:{ Exception -> 0x007a }
            r13.L$0 = r1     // Catch:{ Exception -> 0x007a }
            r13.label = r3     // Catch:{ Exception -> 0x007a }
            java.lang.Object r14 = io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveNullable(r14, r5, r6)     // Catch:{ Exception -> 0x007a }
            if (r14 != r0) goto L_0x0054
            return r0
        L_0x0054:
            if (r14 == 0) goto L_0x0059
            io.ktor.http.Parameters r14 = (io.ktor.http.Parameters) r14     // Catch:{ Exception -> 0x007a }
            goto L_0x007c
        L_0x0059:
            io.ktor.server.plugins.CannotTransformContentToTypeException r14 = new io.ktor.server.plugins.CannotTransformContentToTypeException     // Catch:{ Exception -> 0x007a }
            java.lang.Class<io.ktor.http.Parameters> r3 = io.ktor.http.Parameters.class
            kotlin.reflect.KType r3 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r3)     // Catch:{ Exception -> 0x007a }
            java.lang.reflect.Type r5 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r3)     // Catch:{ Exception -> 0x007a }
            java.lang.Class<io.ktor.http.Parameters> r6 = io.ktor.http.Parameters.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)     // Catch:{ Exception -> 0x007a }
            io.ktor.util.reflect.TypeInfo r3 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r5, r6, r3)     // Catch:{ Exception -> 0x007a }
            kotlin.reflect.KType r3 = r3.getKotlinType()     // Catch:{ Exception -> 0x007a }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch:{ Exception -> 0x007a }
            r14.<init>(r3)     // Catch:{ Exception -> 0x007a }
            throw r14     // Catch:{ Exception -> 0x007a }
        L_0x007a:
            r14 = r4
        L_0x007c:
            if (r14 != 0) goto L_0x0080
            r14 = r4
            goto L_0x008a
        L_0x0080:
            java.lang.String r3 = "challenge"
            java.lang.String r14 = r14.get(r3)
            java.lang.String r14 = java.lang.String.valueOf(r14)
        L_0x008a:
            r3 = r14
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            if (r3 == 0) goto L_0x009b
            boolean r3 = kotlin.text.StringsKt.isBlank(r3)
            if (r3 == 0) goto L_0x0096
            goto L_0x009b
        L_0x0096:
            org.videolan.vlc.webserver.RemoteAccessOTP r3 = org.videolan.vlc.webserver.RemoteAccessOTP.INSTANCE
            r3.removeCodeWithChallenge(r14)
        L_0x009b:
            org.videolan.vlc.webserver.RemoteAccessOTP r14 = org.videolan.vlc.webserver.RemoteAccessOTP.INSTANCE
            android.content.Context r3 = r13.$appContext
            org.videolan.vlc.webserver.OTPCode r14 = r14.getFirstValidCode(r3)
            kotlinx.coroutines.CoroutineScope r5 = r13.$scope
            org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$1$1 r3 = new org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$1$1
            r3.<init>(r14, r4)
            r8 = r3
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9 = 3
            r10 = 0
            r6 = 0
            r7 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r5, r6, r7, r8, r9, r10)
            java.lang.Object r1 = r1.getContext()
            r5 = r1
            io.ktor.server.application.ApplicationCall r5 = (io.ktor.server.application.ApplicationCall) r5
            java.lang.String r6 = r14.getChallenge()
            r10 = r13
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r13.L$0 = r4
            r13.label = r2
            r8 = 0
            r9 = 0
            r11 = 14
            r12 = 0
            java.lang.Object r14 = io.ktor.server.response.ApplicationResponseFunctionsKt.respondText$default(r5, r6, r7, r8, r9, r10, r11, r12)
            if (r14 != r0) goto L_0x00d2
            return r0
        L_0x00d2:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
