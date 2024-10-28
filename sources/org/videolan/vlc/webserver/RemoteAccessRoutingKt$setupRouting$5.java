package org.videolan.vlc.webserver;

import android.content.SharedPreferences;
import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$5", f = "RemoteAccessRouting.kt", i = {0, 1, 1, 1, 2, 2, 2}, l = {217, 1497, 222, 236}, m = "invokeSuspend", n = {"$this$post", "$this$post", "fileDescription", "fileName", "$this$post", "fileDescription", "fileName"}, s = {"L$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$setupRouting$5 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
    final /* synthetic */ SharedPreferences $settings;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessRoutingKt$setupRouting$5(SharedPreferences sharedPreferences, Continuation<? super RemoteAccessRoutingKt$setupRouting$5> continuation) {
        super(3, continuation);
        this.$settings = sharedPreferences;
    }

    public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
        RemoteAccessRoutingKt$setupRouting$5 remoteAccessRoutingKt$setupRouting$5 = new RemoteAccessRoutingKt$setupRouting$5(this.$settings, continuation);
        remoteAccessRoutingKt$setupRouting$5.L$0 = pipelineContext;
        return remoteAccessRoutingKt$setupRouting$5.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0106 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x010a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) {
        /*
            r16 = this;
            r0 = r16
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r2 == 0) goto L_0x0051
            if (r2 == r6) goto L_0x0049
            if (r2 == r5) goto L_0x0035
            if (r2 == r4) goto L_0x0024
            if (r2 != r3) goto L_0x001c
            kotlin.ResultKt.throwOnFailure(r17)
            goto L_0x0107
        L_0x001c:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0024:
            java.lang.Object r2 = r0.L$2
            kotlin.jvm.internal.Ref$ObjectRef r2 = (kotlin.jvm.internal.Ref.ObjectRef) r2
            java.lang.Object r4 = r0.L$1
            kotlin.jvm.internal.Ref$ObjectRef r4 = (kotlin.jvm.internal.Ref.ObjectRef) r4
            java.lang.Object r5 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r5 = (io.ktor.util.pipeline.PipelineContext) r5
            kotlin.ResultKt.throwOnFailure(r17)
            goto L_0x00c7
        L_0x0035:
            java.lang.Object r2 = r0.L$2
            kotlin.jvm.internal.Ref$ObjectRef r2 = (kotlin.jvm.internal.Ref.ObjectRef) r2
            java.lang.Object r5 = r0.L$1
            kotlin.jvm.internal.Ref$ObjectRef r5 = (kotlin.jvm.internal.Ref.ObjectRef) r5
            java.lang.Object r6 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r6 = (io.ktor.util.pipeline.PipelineContext) r6
            kotlin.ResultKt.throwOnFailure(r17)
            r8 = r6
            r6 = r5
            r5 = r17
            goto L_0x00a8
        L_0x0049:
            java.lang.Object r2 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r2 = (io.ktor.util.pipeline.PipelineContext) r2
            kotlin.ResultKt.throwOnFailure(r17)
            goto L_0x006a
        L_0x0051:
            kotlin.ResultKt.throwOnFailure(r17)
            java.lang.Object r2 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r2 = (io.ktor.util.pipeline.PipelineContext) r2
            org.videolan.vlc.webserver.RemoteAccessSession r8 = org.videolan.vlc.webserver.RemoteAccessSession.INSTANCE
            android.content.SharedPreferences r9 = r0.$settings
            r10 = r0
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r0.L$0 = r2
            r0.label = r6
            java.lang.Object r6 = r8.verifyLogin(r2, r9, r10)
            if (r6 != r1) goto L_0x006a
            return r1
        L_0x006a:
            kotlin.jvm.internal.Ref$ObjectRef r6 = new kotlin.jvm.internal.Ref$ObjectRef
            r6.<init>()
            java.lang.String r8 = ""
            r6.element = r8
            kotlin.jvm.internal.Ref$ObjectRef r9 = new kotlin.jvm.internal.Ref$ObjectRef
            r9.<init>()
            r9.element = r8
            java.lang.Object r8 = r2.getContext()
            io.ktor.server.application.ApplicationCall r8 = (io.ktor.server.application.ApplicationCall) r8
            java.lang.Class<io.ktor.http.content.MultiPartData> r10 = io.ktor.http.content.MultiPartData.class
            kotlin.reflect.KType r10 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r10)
            java.lang.reflect.Type r11 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r10)
            java.lang.Class<io.ktor.http.content.MultiPartData> r12 = io.ktor.http.content.MultiPartData.class
            kotlin.reflect.KClass r12 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r12)
            io.ktor.util.reflect.TypeInfo r10 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r11, r12, r10)
            r11 = r0
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r0.L$0 = r2
            r0.L$1 = r6
            r0.L$2 = r9
            r0.label = r5
            java.lang.Object r5 = io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveNullable(r8, r10, r11)
            if (r5 != r1) goto L_0x00a6
            return r1
        L_0x00a6:
            r8 = r2
            r2 = r9
        L_0x00a8:
            if (r5 == 0) goto L_0x010a
            io.ktor.http.content.MultiPartData r5 = (io.ktor.http.content.MultiPartData) r5
            org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$5$1 r9 = new org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$5$1
            r9.<init>(r6, r2, r7)
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            r10 = r0
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r0.L$0 = r8
            r0.L$1 = r6
            r0.L$2 = r2
            r0.label = r4
            java.lang.Object r4 = io.ktor.http.content.MultipartKt.forEachPart(r5, r9, r10)
            if (r4 != r1) goto L_0x00c5
            return r1
        L_0x00c5:
            r4 = r6
            r5 = r8
        L_0x00c7:
            java.lang.Object r5 = r5.getContext()
            r8 = r5
            io.ktor.server.application.ApplicationCall r8 = (io.ktor.server.application.ApplicationCall) r8
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            T r4 = r4.element
            java.lang.String r4 = (java.lang.String) r4
            r5.append(r4)
            java.lang.String r4 = " is uploaded to 'uploads/"
            r5.append(r4)
            T r2 = r2.element
            java.lang.String r2 = (java.lang.String) r2
            r5.append(r2)
            r2 = 39
            r5.append(r2)
            java.lang.String r9 = r5.toString()
            r13 = r0
            kotlin.coroutines.Continuation r13 = (kotlin.coroutines.Continuation) r13
            r0.L$0 = r7
            r0.L$1 = r7
            r0.L$2 = r7
            r0.label = r3
            r10 = 0
            r11 = 0
            r12 = 0
            r14 = 14
            r15 = 0
            java.lang.Object r2 = io.ktor.server.response.ApplicationResponseFunctionsKt.respondText$default(r8, r9, r10, r11, r12, r13, r14, r15)
            if (r2 != r1) goto L_0x0107
            return r1
        L_0x0107:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x010a:
            io.ktor.server.plugins.CannotTransformContentToTypeException r1 = new io.ktor.server.plugins.CannotTransformContentToTypeException
            java.lang.Class<io.ktor.http.content.MultiPartData> r2 = io.ktor.http.content.MultiPartData.class
            kotlin.reflect.KType r2 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r2)
            java.lang.reflect.Type r3 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r2)
            java.lang.Class<io.ktor.http.content.MultiPartData> r4 = io.ktor.http.content.MultiPartData.class
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            io.ktor.util.reflect.TypeInfo r2 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r3, r4, r2)
            kotlin.reflect.KType r2 = r2.getKotlinType()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$5.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
