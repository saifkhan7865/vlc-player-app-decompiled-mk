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
@DebugMetadata(c = "io.ktor.server.engine.DefaultEnginePipelineKt$defaultEnginePipeline$1", f = "DefaultEnginePipeline.kt", i = {0, 2, 4}, l = {123, 43, 36, 43, 40, 43, 43}, m = "invokeSuspend", n = {"$this$intercept", "$this$intercept", "$this$intercept"}, s = {"L$0", "L$0", "L$0"})
/* compiled from: DefaultEnginePipeline.kt */
final class DefaultEnginePipelineKt$defaultEnginePipeline$1 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;

    DefaultEnginePipelineKt$defaultEnginePipeline$1(Continuation<? super DefaultEnginePipelineKt$defaultEnginePipeline$1> continuation) {
        super(3, continuation);
    }

    public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
        DefaultEnginePipelineKt$defaultEnginePipeline$1 defaultEnginePipelineKt$defaultEnginePipeline$1 = new DefaultEnginePipelineKt$defaultEnginePipeline$1(continuation);
        defaultEnginePipelineKt$defaultEnginePipeline$1.L$0 = pipelineContext;
        return defaultEnginePipelineKt$defaultEnginePipeline$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v23, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: io.ktor.util.pipeline.PipelineContext} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r7.L$0 = null;
        r7.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0087, code lost:
        if (io.ktor.utils.io.ByteReadChannelKt.discard(((io.ktor.server.application.ApplicationCall) r1.getContext()).getRequest().receiveChannel(), r7) != r0) goto L_0x0105;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0089, code lost:
        return r0;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:33:0x00a0=Splitter:B:33:0x00a0, B:42:0x00e8=Splitter:B:42:0x00e8, B:24:0x006d=Splitter:B:24:0x006d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 0
            switch(r1) {
                case 0: goto L_0x003d;
                case 1: goto L_0x0035;
                case 2: goto L_0x001b;
                case 3: goto L_0x0029;
                case 4: goto L_0x001b;
                case 5: goto L_0x0020;
                case 6: goto L_0x001b;
                case 7: goto L_0x0012;
                default: goto L_0x000a;
            }
        L_0x000a:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0012:
            java.lang.Object r0 = r7.L$0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0126 }
            goto L_0x0126
        L_0x001b:
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0105 }
            goto L_0x0105
        L_0x0020:
            java.lang.Object r1 = r7.L$0
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0032 }
            goto L_0x00a0
        L_0x0029:
            java.lang.Object r1 = r7.L$0
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0032 }
            goto L_0x00e8
        L_0x0032:
            r8 = move-exception
            goto L_0x0108
        L_0x0035:
            java.lang.Object r1 = r7.L$0
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ ChannelIOException -> 0x00bd, all -> 0x008a }
            goto L_0x006d
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            r1 = r8
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            java.lang.Object r8 = r1.getContext()     // Catch:{ ChannelIOException -> 0x00bd, all -> 0x008a }
            io.ktor.server.application.ApplicationCall r8 = (io.ktor.server.application.ApplicationCall) r8     // Catch:{ ChannelIOException -> 0x00bd, all -> 0x008a }
            io.ktor.server.application.Application r8 = r8.getApplication()     // Catch:{ ChannelIOException -> 0x00bd, all -> 0x008a }
            io.ktor.util.pipeline.Pipeline r8 = (io.ktor.util.pipeline.Pipeline) r8     // Catch:{ ChannelIOException -> 0x00bd, all -> 0x008a }
            java.lang.Object r3 = r1.getContext()     // Catch:{ ChannelIOException -> 0x00bd, all -> 0x008a }
            io.ktor.server.application.ApplicationCall r3 = (io.ktor.server.application.ApplicationCall) r3     // Catch:{ ChannelIOException -> 0x00bd, all -> 0x008a }
            io.ktor.server.engine.DefaultEnginePipelineKt$defaultEnginePipeline$1$invokeSuspend$$inlined$execute$1 r4 = new io.ktor.server.engine.DefaultEnginePipelineKt$defaultEnginePipeline$1$invokeSuspend$$inlined$execute$1     // Catch:{ ChannelIOException -> 0x00bd, all -> 0x008a }
            r4.<init>(r8, r3, r2)     // Catch:{ ChannelIOException -> 0x00bd, all -> 0x008a }
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4     // Catch:{ ChannelIOException -> 0x00bd, all -> 0x008a }
            r8 = r7
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8     // Catch:{ ChannelIOException -> 0x00bd, all -> 0x008a }
            r7.L$0 = r1     // Catch:{ ChannelIOException -> 0x00bd, all -> 0x008a }
            r3 = 1
            r7.label = r3     // Catch:{ ChannelIOException -> 0x00bd, all -> 0x008a }
            java.lang.Object r8 = io.ktor.util.debug.ContextUtilsKt.initContextInDebugMode(r4, r8)     // Catch:{ ChannelIOException -> 0x00bd, all -> 0x008a }
            if (r8 != r0) goto L_0x006d
            return r0
        L_0x006d:
            java.lang.Object r8 = r1.getContext()     // Catch:{ all -> 0x0105 }
            io.ktor.server.application.ApplicationCall r8 = (io.ktor.server.application.ApplicationCall) r8     // Catch:{ all -> 0x0105 }
            io.ktor.server.request.ApplicationRequest r8 = r8.getRequest()     // Catch:{ all -> 0x0105 }
            io.ktor.utils.io.ByteReadChannel r8 = r8.receiveChannel()     // Catch:{ all -> 0x0105 }
            r1 = r7
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch:{ all -> 0x0105 }
            r7.L$0 = r2     // Catch:{ all -> 0x0105 }
            r2 = 2
            r7.label = r2     // Catch:{ all -> 0x0105 }
            java.lang.Object r8 = io.ktor.utils.io.ByteReadChannelKt.discard(r8, r1)     // Catch:{ all -> 0x0105 }
            if (r8 != r0) goto L_0x0105
            return r0
        L_0x008a:
            r8 = move-exception
            java.lang.Object r3 = r1.getContext()     // Catch:{ all -> 0x0032 }
            io.ktor.server.application.ApplicationCall r3 = (io.ktor.server.application.ApplicationCall) r3     // Catch:{ all -> 0x0032 }
            r4 = r7
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch:{ all -> 0x0032 }
            r7.L$0 = r1     // Catch:{ all -> 0x0032 }
            r5 = 5
            r7.label = r5     // Catch:{ all -> 0x0032 }
            java.lang.Object r8 = io.ktor.server.engine.DefaultEnginePipelineKt.handleFailure(r3, r8, r4)     // Catch:{ all -> 0x0032 }
            if (r8 != r0) goto L_0x00a0
            return r0
        L_0x00a0:
            java.lang.Object r8 = r1.getContext()     // Catch:{ all -> 0x0105 }
            io.ktor.server.application.ApplicationCall r8 = (io.ktor.server.application.ApplicationCall) r8     // Catch:{ all -> 0x0105 }
            io.ktor.server.request.ApplicationRequest r8 = r8.getRequest()     // Catch:{ all -> 0x0105 }
            io.ktor.utils.io.ByteReadChannel r8 = r8.receiveChannel()     // Catch:{ all -> 0x0105 }
            r1 = r7
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch:{ all -> 0x0105 }
            r7.L$0 = r2     // Catch:{ all -> 0x0105 }
            r2 = 6
            r7.label = r2     // Catch:{ all -> 0x0105 }
            java.lang.Object r8 = io.ktor.utils.io.ByteReadChannelKt.discard(r8, r1)     // Catch:{ all -> 0x0105 }
            if (r8 != r0) goto L_0x0105
            return r0
        L_0x00bd:
            r8 = move-exception
            java.lang.Object r3 = r1.getContext()     // Catch:{ all -> 0x0032 }
            io.ktor.server.application.ApplicationCall r3 = (io.ktor.server.application.ApplicationCall) r3     // Catch:{ all -> 0x0032 }
            io.ktor.server.application.Application r3 = r3.getApplication()     // Catch:{ all -> 0x0032 }
            io.ktor.server.logging.MDCProvider r3 = io.ktor.server.logging.LoggingKt.getMdcProvider(r3)     // Catch:{ all -> 0x0032 }
            java.lang.Object r4 = r1.getContext()     // Catch:{ all -> 0x0032 }
            io.ktor.server.application.ApplicationCall r4 = (io.ktor.server.application.ApplicationCall) r4     // Catch:{ all -> 0x0032 }
            io.ktor.server.engine.DefaultEnginePipelineKt$defaultEnginePipeline$1$1 r5 = new io.ktor.server.engine.DefaultEnginePipelineKt$defaultEnginePipeline$1$1     // Catch:{ all -> 0x0032 }
            r5.<init>(r1, r8, r2)     // Catch:{ all -> 0x0032 }
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5     // Catch:{ all -> 0x0032 }
            r8 = r7
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8     // Catch:{ all -> 0x0032 }
            r7.L$0 = r1     // Catch:{ all -> 0x0032 }
            r6 = 3
            r7.label = r6     // Catch:{ all -> 0x0032 }
            java.lang.Object r8 = r3.withMDCBlock(r4, r5, r8)     // Catch:{ all -> 0x0032 }
            if (r8 != r0) goto L_0x00e8
            return r0
        L_0x00e8:
            java.lang.Object r8 = r1.getContext()     // Catch:{ all -> 0x0105 }
            io.ktor.server.application.ApplicationCall r8 = (io.ktor.server.application.ApplicationCall) r8     // Catch:{ all -> 0x0105 }
            io.ktor.server.request.ApplicationRequest r8 = r8.getRequest()     // Catch:{ all -> 0x0105 }
            io.ktor.utils.io.ByteReadChannel r8 = r8.receiveChannel()     // Catch:{ all -> 0x0105 }
            r1 = r7
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch:{ all -> 0x0105 }
            r7.L$0 = r2     // Catch:{ all -> 0x0105 }
            r2 = 4
            r7.label = r2     // Catch:{ all -> 0x0105 }
            java.lang.Object r8 = io.ktor.utils.io.ByteReadChannelKt.discard(r8, r1)     // Catch:{ all -> 0x0105 }
            if (r8 != r0) goto L_0x0105
            return r0
        L_0x0105:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0108:
            java.lang.Object r1 = r1.getContext()     // Catch:{ all -> 0x0125 }
            io.ktor.server.application.ApplicationCall r1 = (io.ktor.server.application.ApplicationCall) r1     // Catch:{ all -> 0x0125 }
            io.ktor.server.request.ApplicationRequest r1 = r1.getRequest()     // Catch:{ all -> 0x0125 }
            io.ktor.utils.io.ByteReadChannel r1 = r1.receiveChannel()     // Catch:{ all -> 0x0125 }
            r2 = r7
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch:{ all -> 0x0125 }
            r7.L$0 = r8     // Catch:{ all -> 0x0125 }
            r3 = 7
            r7.label = r3     // Catch:{ all -> 0x0125 }
            java.lang.Object r1 = io.ktor.utils.io.ByteReadChannelKt.discard(r1, r2)     // Catch:{ all -> 0x0125 }
            if (r1 != r0) goto L_0x0125
            return r0
        L_0x0125:
            r0 = r8
        L_0x0126:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.DefaultEnginePipelineKt$defaultEnginePipeline$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
