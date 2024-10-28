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

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$4", f = "RemoteAccessRouting.kt", i = {0}, l = {210, 212}, m = "invokeSuspend", n = {"$this$get"}, s = {"L$0"})
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$setupRouting$4 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $appContext;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessRoutingKt$setupRouting$4(Context context, Continuation<? super RemoteAccessRoutingKt$setupRouting$4> continuation) {
        super(3, continuation);
        this.$appContext = context;
    }

    public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
        RemoteAccessRoutingKt$setupRouting$4 remoteAccessRoutingKt$setupRouting$4 = new RemoteAccessRoutingKt$setupRouting$4(this.$appContext, continuation);
        remoteAccessRoutingKt$setupRouting$4.L$0 = pipelineContext;
        return remoteAccessRoutingKt$setupRouting$4.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: io.ktor.util.pipeline.PipelineContext} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0022
            if (r1 == r3) goto L_0x001a
            if (r1 != r2) goto L_0x0012
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0088
        L_0x0012:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x001a:
            java.lang.Object r1 = r13.L$0
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ Exception -> 0x0069 }
            goto L_0x0088
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.Object r14 = r13.L$0
            r1 = r14
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            org.videolan.vlc.util.FileUtils r14 = org.videolan.vlc.util.FileUtils.INSTANCE     // Catch:{ Exception -> 0x0069 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0069 }
            r4.<init>()     // Catch:{ Exception -> 0x0069 }
            org.videolan.vlc.webserver.RemoteAccessServer$Companion r5 = org.videolan.vlc.webserver.RemoteAccessServer.Companion     // Catch:{ Exception -> 0x0069 }
            android.content.Context r6 = r13.$appContext     // Catch:{ Exception -> 0x0069 }
            java.lang.String r5 = r5.getServerFiles(r6)     // Catch:{ Exception -> 0x0069 }
            r4.append(r5)     // Catch:{ Exception -> 0x0069 }
            java.lang.String r5 = "index.html"
            r4.append(r5)     // Catch:{ Exception -> 0x0069 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0069 }
            java.lang.String r6 = r14.getStringFromFile(r4)     // Catch:{ Exception -> 0x0069 }
            java.lang.Object r14 = r1.getContext()     // Catch:{ Exception -> 0x0069 }
            r5 = r14
            io.ktor.server.application.ApplicationCall r5 = (io.ktor.server.application.ApplicationCall) r5     // Catch:{ Exception -> 0x0069 }
            io.ktor.http.ContentType$Text r14 = io.ktor.http.ContentType.Text.INSTANCE     // Catch:{ Exception -> 0x0069 }
            io.ktor.http.ContentType r7 = r14.getHtml()     // Catch:{ Exception -> 0x0069 }
            r10 = r13
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10     // Catch:{ Exception -> 0x0069 }
            r13.L$0 = r1     // Catch:{ Exception -> 0x0069 }
            r13.label = r3     // Catch:{ Exception -> 0x0069 }
            r8 = 0
            r9 = 0
            r11 = 12
            r12 = 0
            java.lang.Object r14 = io.ktor.server.response.ApplicationResponseFunctionsKt.respondText$default(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x0069 }
            if (r14 != r0) goto L_0x0088
            return r0
        L_0x0069:
            java.lang.Object r14 = r1.getContext()
            r3 = r14
            io.ktor.server.application.ApplicationCall r3 = (io.ktor.server.application.ApplicationCall) r3
            r8 = r13
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r14 = 0
            r13.L$0 = r14
            r13.label = r2
            java.lang.String r4 = "Failed to load index.html"
            r5 = 0
            r6 = 0
            r7 = 0
            r9 = 14
            r10 = 0
            java.lang.Object r14 = io.ktor.server.response.ApplicationResponseFunctionsKt.respondText$default(r3, r4, r5, r6, r7, r8, r9, r10)
            if (r14 != r0) goto L_0x0088
            return r0
        L_0x0088:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$4.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
