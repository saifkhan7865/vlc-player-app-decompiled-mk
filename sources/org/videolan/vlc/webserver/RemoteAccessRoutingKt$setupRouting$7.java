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
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$7", f = "RemoteAccessRouting.kt", i = {1}, l = {1501, 255, 265}, m = "invokeSuspend", n = {"$this$get"}, s = {"L$0"})
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$setupRouting$7 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
    final /* synthetic */ SharedPreferences $settings;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessRoutingKt$setupRouting$7(SharedPreferences sharedPreferences, Continuation<? super RemoteAccessRoutingKt$setupRouting$7> continuation) {
        super(3, continuation);
        this.$settings = sharedPreferences;
    }

    public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
        RemoteAccessRoutingKt$setupRouting$7 remoteAccessRoutingKt$setupRouting$7 = new RemoteAccessRoutingKt$setupRouting$7(this.$settings, continuation);
        remoteAccessRoutingKt$setupRouting$7.L$0 = pipelineContext;
        return remoteAccessRoutingKt$setupRouting$7.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: io.ktor.util.pipeline.PipelineContext} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0125 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0102 A[EDGE_INSN: B:37:0x0102->B:32:0x0102 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x002a
            if (r1 == r4) goto L_0x0026
            if (r1 == r3) goto L_0x001e
            if (r1 != r2) goto L_0x0016
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x0126
        L_0x0016:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L_0x001e:
            java.lang.Object r1 = r14.L$0
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x0098
        L_0x0026:
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x0087
        L_0x002a:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.Object r15 = r14.L$0
            r1 = r15
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            android.content.SharedPreferences r15 = r14.$settings
            java.lang.String r5 = "remote_access_logs"
            r6 = 0
            boolean r15 = r15.getBoolean(r5, r6)
            if (r15 != 0) goto L_0x008a
            java.lang.Object r15 = r1.getContext()
            io.ktor.server.application.ApplicationCall r15 = (io.ktor.server.application.ApplicationCall) r15
            io.ktor.http.HttpStatusCode$Companion r1 = io.ktor.http.HttpStatusCode.Companion
            io.ktor.http.HttpStatusCode r1 = r1.getForbidden()
            boolean r2 = r1 instanceof io.ktor.http.content.OutgoingContent
            if (r2 != 0) goto L_0x006c
            boolean r2 = r1 instanceof byte[]
            if (r2 != 0) goto L_0x006c
            io.ktor.server.response.ApplicationResponse r2 = r15.getResponse()
            java.lang.Class<io.ktor.http.HttpStatusCode> r3 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KType r3 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r3)
            java.lang.reflect.Type r5 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r3)
            java.lang.Class<io.ktor.http.HttpStatusCode> r6 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            io.ktor.util.reflect.TypeInfo r3 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r5, r6, r3)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r2, r3)
        L_0x006c:
            io.ktor.server.response.ApplicationResponse r2 = r15.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r2 = r2.getPipeline()
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.Any"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r3)
            java.lang.Object r1 = (java.lang.Object) r1
            r3 = r14
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r14.label = r4
            java.lang.Object r15 = r2.execute(r15, r1, r3)
            if (r15 != r0) goto L_0x0087
            return r0
        L_0x0087:
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            return r15
        L_0x008a:
            r15 = r14
            kotlin.coroutines.Continuation r15 = (kotlin.coroutines.Continuation) r15
            r14.L$0 = r1
            r14.label = r3
            java.lang.Object r15 = org.videolan.vlc.webserver.RemoteAccessRoutingKt.getLogsFiles(r15)
            if (r15 != r0) goto L_0x0098
            return r0
        L_0x0098:
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$7$invokeSuspend$$inlined$sortedBy$1 r3 = new org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$7$invokeSuspend$$inlined$sortedBy$1
            r3.<init>()
            java.util.Comparator r3 = (java.util.Comparator) r3
            java.util.List r15 = kotlin.collections.CollectionsKt.sortedWith(r15, r3)
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            java.util.List r15 = kotlin.collections.CollectionsKt.reversed(r15)
            org.json.JSONArray r3 = new org.json.JSONArray
            r3.<init>()
            java.util.Iterator r15 = r15.iterator()
        L_0x00b4:
            boolean r4 = r15.hasNext()
            r5 = 0
            if (r4 == 0) goto L_0x0102
            java.lang.Object r4 = r15.next()
            org.videolan.vlc.webserver.LogFile r4 = (org.videolan.vlc.webserver.LogFile) r4
            org.json.JSONObject r6 = new org.json.JSONObject
            r6.<init>()
            java.lang.String r7 = "path"
            java.lang.String r8 = r4.getPath()
            r6.put(r7, r8)
            org.videolan.vlc.webserver.RemoteAccessRoutingKt$format$2$1 r7 = org.videolan.vlc.webserver.RemoteAccessRoutingKt.getFormat()
            java.lang.Object r7 = r7.get()
            java.text.DateFormat r7 = (java.text.DateFormat) r7
            if (r7 == 0) goto L_0x00f0
            java.io.File r5 = new java.io.File
            java.lang.String r8 = r4.getPath()
            r5.<init>(r8)
            long r8 = r5.lastModified()
            java.lang.Long r5 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r8)
            java.lang.String r5 = r7.format(r5)
        L_0x00f0:
            java.lang.String r7 = "date"
            r6.put(r7, r5)
            java.lang.String r5 = "type"
            java.lang.String r4 = r4.getType()
            r6.put(r5, r4)
            r3.put(r6)
            goto L_0x00b4
        L_0x0102:
            java.lang.Object r15 = r1.getContext()
            r6 = r15
            io.ktor.server.application.ApplicationCall r6 = (io.ktor.server.application.ApplicationCall) r6
            java.lang.String r7 = r3.toString()
            java.lang.String r15 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r15)
            r11 = r14
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r14.L$0 = r5
            r14.label = r2
            r8 = 0
            r9 = 0
            r10 = 0
            r12 = 14
            r13 = 0
            java.lang.Object r15 = io.ktor.server.response.ApplicationResponseFunctionsKt.respondText$default(r6, r7, r8, r9, r10, r11, r12, r13)
            if (r15 != r0) goto L_0x0126
            return r0
        L_0x0126:
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$7.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
