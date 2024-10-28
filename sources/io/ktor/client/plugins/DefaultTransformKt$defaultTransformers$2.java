package io.ktor.client.plugins;

import io.ktor.client.call.HttpClientCall;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", "<name for destructuring parameter 0>"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.DefaultTransformKt$defaultTransformers$2", f = "DefaultTransform.kt", i = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5, 6, 6, 7, 7, 8, 8}, l = {68, 72, 72, 78, 78, 82, 90, 116, 121}, m = "invokeSuspend", n = {"$this$intercept", "info", "$this$intercept", "info", "$this$intercept", "info", "$this$intercept", "info", "$this$intercept", "info", "$this$intercept", "info", "response", "$this$intercept", "info", "$this$intercept", "info", "$this$intercept", "info"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
/* compiled from: DefaultTransform.kt */
final class DefaultTransformKt$defaultTransformers$2 extends SuspendLambda implements Function3<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    int label;

    DefaultTransformKt$defaultTransformers$2(Continuation<? super DefaultTransformKt$defaultTransformers$2> continuation) {
        super(3, continuation);
    }

    public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, Continuation<? super Unit> continuation) {
        DefaultTransformKt$defaultTransformers$2 defaultTransformKt$defaultTransformers$2 = new DefaultTransformKt$defaultTransformers$2(continuation);
        defaultTransformKt$defaultTransformers$2.L$0 = pipelineContext;
        defaultTransformKt$defaultTransformers$2.L$1 = httpResponseContainer;
        return defaultTransformKt$defaultTransformers$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0108, code lost:
        r5 = (io.ktor.client.statement.HttpResponseContainer) r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x010b, code lost:
        r7 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x013b, code lost:
        r0.L$0 = r7;
        r0.L$1 = r6;
        r0.L$2 = null;
        r0.L$3 = null;
        r0.label = 3;
        r2 = r8.proceedWith(new io.ktor.client.statement.HttpResponseContainer(r2, kotlin.coroutines.jvm.internal.Boxing.boxInt(java.lang.Integer.parseInt(io.ktor.utils.io.core.Input.readText$default((io.ktor.utils.io.core.Input) r3, 0, 0, 3, (java.lang.Object) null)))), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0160, code lost:
        if (r2 != r1) goto L_0x0163;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0162, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0163, code lost:
        r1 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0164, code lost:
        r5 = (io.ktor.client.statement.HttpResponseContainer) r2;
        r2 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x01a3, code lost:
        r0.L$0 = r6;
        r0.L$1 = r4;
        r0.L$2 = null;
        r0.L$3 = null;
        r0.label = 5;
        r2 = r7.proceedWith(new io.ktor.client.statement.HttpResponseContainer(r2, r3), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x01ba, code lost:
        if (r2 != r1) goto L_0x01bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x01bc, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x01bd, code lost:
        r1 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x01be, code lost:
        r5 = (io.ktor.client.statement.HttpResponseContainer) r2;
        r7 = r1;
        r2 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x01eb, code lost:
        r6 = (byte[]) r6;
        r9 = io.ktor.http.HttpMessagePropertiesKt.contentLength((io.ktor.http.HttpMessage) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x01fa, code lost:
        if (io.ktor.util.PlatformUtils.INSTANCE.getIS_BROWSER() != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x020a, code lost:
        if (r2.getHeaders().get(io.ktor.http.HttpHeaders.INSTANCE.getContentEncoding()) != null) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x020c, code lost:
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x020d, code lost:
        r2 = !kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) ((io.ktor.client.call.HttpClientCall) r8.getContext()).getRequest().getMethod(), (java.lang.Object) io.ktor.http.HttpMethod.Companion.getHead());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0226, code lost:
        if (r4 == false) goto L_0x0260;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0228, code lost:
        if (r2 == false) goto L_0x0260;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x022a, code lost:
        if (r9 == null) goto L_0x0260;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0234, code lost:
        if (r9.longValue() <= 0) goto L_0x0260;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x023c, code lost:
        if (r6.length != ((int) r9.longValue())) goto L_0x023f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x025f, code lost:
        throw new java.lang.IllegalStateException(("Expected " + r9 + ", actual " + r6.length).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0260, code lost:
        r0.L$0 = r8;
        r0.L$1 = r7;
        r0.L$2 = null;
        r0.label = 7;
        r2 = r8.proceedWith(new io.ktor.client.statement.HttpResponseContainer(r7, r6), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0275, code lost:
        if (r2 != r1) goto L_0x0278;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0277, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0278, code lost:
        r1 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0279, code lost:
        r5 = (io.ktor.client.statement.HttpResponseContainer) r2;
        r7 = r1;
        r2 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x02da, code lost:
        r5 = (io.ktor.client.statement.HttpResponseContainer) r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x030c, code lost:
        r5 = (io.ktor.client.statement.HttpResponseContainer) r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0311, code lost:
        if (r5 == null) goto L_0x0342;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0313, code lost:
        io.ktor.client.plugins.DefaultTransformKt.LOGGER.trace("Transformed with default transformers response body for " + ((io.ktor.client.call.HttpClientCall) r2.getContext()).getRequest().getUrl() + " to " + r7.getType());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0344, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            r18 = this;
            r0 = r18
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            r5 = 0
            switch(r2) {
                case 0: goto L_0x00b7;
                case 1: goto L_0x00a9;
                case 2: goto L_0x0091;
                case 3: goto L_0x0081;
                case 4: goto L_0x0069;
                case 5: goto L_0x0059;
                case 6: goto L_0x0044;
                case 7: goto L_0x0034;
                case 8: goto L_0x0025;
                case 9: goto L_0x0016;
                default: goto L_0x000e;
            }
        L_0x000e:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0016:
            java.lang.Object r1 = r0.L$1
            io.ktor.util.reflect.TypeInfo r1 = (io.ktor.util.reflect.TypeInfo) r1
            java.lang.Object r2 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r2 = (io.ktor.util.pipeline.PipelineContext) r2
            kotlin.ResultKt.throwOnFailure(r19)
            r3 = r19
            goto L_0x030c
        L_0x0025:
            java.lang.Object r1 = r0.L$1
            io.ktor.util.reflect.TypeInfo r1 = (io.ktor.util.reflect.TypeInfo) r1
            java.lang.Object r2 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r2 = (io.ktor.util.pipeline.PipelineContext) r2
            kotlin.ResultKt.throwOnFailure(r19)
            r3 = r19
            goto L_0x02da
        L_0x0034:
            java.lang.Object r1 = r0.L$1
            io.ktor.util.reflect.TypeInfo r1 = (io.ktor.util.reflect.TypeInfo) r1
            java.lang.Object r2 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r2 = (io.ktor.util.pipeline.PipelineContext) r2
            kotlin.ResultKt.throwOnFailure(r19)
            r8 = r2
            r2 = r19
            goto L_0x0279
        L_0x0044:
            java.lang.Object r2 = r0.L$2
            io.ktor.client.statement.HttpResponse r2 = (io.ktor.client.statement.HttpResponse) r2
            java.lang.Object r6 = r0.L$1
            io.ktor.util.reflect.TypeInfo r6 = (io.ktor.util.reflect.TypeInfo) r6
            java.lang.Object r7 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r7 = (io.ktor.util.pipeline.PipelineContext) r7
            kotlin.ResultKt.throwOnFailure(r19)
            r8 = r7
            r7 = r6
            r6 = r19
            goto L_0x01eb
        L_0x0059:
            java.lang.Object r1 = r0.L$1
            io.ktor.util.reflect.TypeInfo r1 = (io.ktor.util.reflect.TypeInfo) r1
            java.lang.Object r2 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r2 = (io.ktor.util.pipeline.PipelineContext) r2
            kotlin.ResultKt.throwOnFailure(r19)
            r6 = r2
            r2 = r19
            goto L_0x01be
        L_0x0069:
            java.lang.Object r2 = r0.L$3
            io.ktor.util.reflect.TypeInfo r2 = (io.ktor.util.reflect.TypeInfo) r2
            java.lang.Object r3 = r0.L$2
            io.ktor.util.pipeline.PipelineContext r3 = (io.ktor.util.pipeline.PipelineContext) r3
            java.lang.Object r4 = r0.L$1
            io.ktor.util.reflect.TypeInfo r4 = (io.ktor.util.reflect.TypeInfo) r4
            java.lang.Object r6 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r6 = (io.ktor.util.pipeline.PipelineContext) r6
            kotlin.ResultKt.throwOnFailure(r19)
            r7 = r3
            r3 = r19
            goto L_0x01a3
        L_0x0081:
            java.lang.Object r1 = r0.L$1
            io.ktor.util.reflect.TypeInfo r1 = (io.ktor.util.reflect.TypeInfo) r1
            java.lang.Object r2 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r2 = (io.ktor.util.pipeline.PipelineContext) r2
            kotlin.ResultKt.throwOnFailure(r19)
            r7 = r2
            r2 = r19
            goto L_0x0164
        L_0x0091:
            java.lang.Object r2 = r0.L$3
            io.ktor.util.reflect.TypeInfo r2 = (io.ktor.util.reflect.TypeInfo) r2
            java.lang.Object r3 = r0.L$2
            io.ktor.util.pipeline.PipelineContext r3 = (io.ktor.util.pipeline.PipelineContext) r3
            java.lang.Object r6 = r0.L$1
            io.ktor.util.reflect.TypeInfo r6 = (io.ktor.util.reflect.TypeInfo) r6
            java.lang.Object r7 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r7 = (io.ktor.util.pipeline.PipelineContext) r7
            kotlin.ResultKt.throwOnFailure(r19)
            r8 = r3
            r3 = r19
            goto L_0x013b
        L_0x00a9:
            java.lang.Object r1 = r0.L$1
            io.ktor.util.reflect.TypeInfo r1 = (io.ktor.util.reflect.TypeInfo) r1
            java.lang.Object r2 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r2 = (io.ktor.util.pipeline.PipelineContext) r2
            kotlin.ResultKt.throwOnFailure(r19)
            r3 = r19
            goto L_0x0108
        L_0x00b7:
            kotlin.ResultKt.throwOnFailure(r19)
            java.lang.Object r2 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r2 = (io.ktor.util.pipeline.PipelineContext) r2
            java.lang.Object r6 = r0.L$1
            io.ktor.client.statement.HttpResponseContainer r6 = (io.ktor.client.statement.HttpResponseContainer) r6
            io.ktor.util.reflect.TypeInfo r7 = r6.component1()
            java.lang.Object r6 = r6.component2()
            boolean r8 = r6 instanceof io.ktor.utils.io.ByteReadChannel
            if (r8 != 0) goto L_0x00d1
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x00d1:
            java.lang.Object r8 = r2.getContext()
            io.ktor.client.call.HttpClientCall r8 = (io.ktor.client.call.HttpClientCall) r8
            io.ktor.client.statement.HttpResponse r8 = r8.getResponse()
            kotlin.reflect.KClass r9 = r7.getType()
            java.lang.Class<kotlin.Unit> r10 = kotlin.Unit.class
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r10)
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)
            if (r10 == 0) goto L_0x010e
            io.ktor.utils.io.ByteReadChannel r6 = (io.ktor.utils.io.ByteReadChannel) r6
            io.ktor.utils.io.ByteReadChannelKt.cancel(r6)
            io.ktor.client.statement.HttpResponseContainer r4 = new io.ktor.client.statement.HttpResponseContainer
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            r4.<init>(r7, r5)
            r5 = r0
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r0.L$0 = r2
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r3 = r2.proceedWith(r4, r5)
            if (r3 != r1) goto L_0x0107
            return r1
        L_0x0107:
            r1 = r7
        L_0x0108:
            r5 = r3
            io.ktor.client.statement.HttpResponseContainer r5 = (io.ktor.client.statement.HttpResponseContainer) r5
        L_0x010b:
            r7 = r1
            goto L_0x0311
        L_0x010e:
            java.lang.Class r10 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r10)
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)
            if (r10 == 0) goto L_0x0169
            r11 = r6
            io.ktor.utils.io.ByteReadChannel r11 = (io.ktor.utils.io.ByteReadChannel) r11
            r14 = r0
            kotlin.coroutines.Continuation r14 = (kotlin.coroutines.Continuation) r14
            r0.L$0 = r2
            r0.L$1 = r7
            r0.L$2 = r2
            r0.L$3 = r7
            r3 = 2
            r0.label = r3
            r12 = 0
            r15 = 1
            r16 = 0
            java.lang.Object r3 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r11, r12, r14, r15, r16)
            if (r3 != r1) goto L_0x0137
            return r1
        L_0x0137:
            r8 = r2
            r6 = r7
            r2 = r6
            r7 = r8
        L_0x013b:
            io.ktor.utils.io.core.Input r3 = (io.ktor.utils.io.core.Input) r3
            r9 = 3
            java.lang.String r3 = io.ktor.utils.io.core.Input.readText$default(r3, r4, r4, r9, r5)
            int r3 = java.lang.Integer.parseInt(r3)
            java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
            io.ktor.client.statement.HttpResponseContainer r4 = new io.ktor.client.statement.HttpResponseContainer
            r4.<init>(r2, r3)
            r2 = r0
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r0.L$0 = r7
            r0.L$1 = r6
            r0.L$2 = r5
            r0.L$3 = r5
            r0.label = r9
            java.lang.Object r2 = r8.proceedWith(r4, r2)
            if (r2 != r1) goto L_0x0163
            return r1
        L_0x0163:
            r1 = r6
        L_0x0164:
            r5 = r2
            io.ktor.client.statement.HttpResponseContainer r5 = (io.ktor.client.statement.HttpResponseContainer) r5
            r2 = r7
            goto L_0x010b
        L_0x0169:
            java.lang.Class<io.ktor.utils.io.core.ByteReadPacket> r10 = io.ktor.utils.io.core.ByteReadPacket.class
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r10)
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)
            if (r10 == 0) goto L_0x0176
            goto L_0x0182
        L_0x0176:
            java.lang.Class<io.ktor.utils.io.core.Input> r10 = io.ktor.utils.io.core.Input.class
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r10)
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)
            if (r10 == 0) goto L_0x01c5
        L_0x0182:
            r11 = r6
            io.ktor.utils.io.ByteReadChannel r11 = (io.ktor.utils.io.ByteReadChannel) r11
            r14 = r0
            kotlin.coroutines.Continuation r14 = (kotlin.coroutines.Continuation) r14
            r0.L$0 = r2
            r0.L$1 = r7
            r0.L$2 = r2
            r0.L$3 = r7
            r3 = 4
            r0.label = r3
            r12 = 0
            r15 = 1
            r16 = 0
            java.lang.Object r3 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r11, r12, r14, r15, r16)
            if (r3 != r1) goto L_0x019f
            return r1
        L_0x019f:
            r6 = r2
            r4 = r7
            r2 = r4
            r7 = r6
        L_0x01a3:
            io.ktor.client.statement.HttpResponseContainer r8 = new io.ktor.client.statement.HttpResponseContainer
            r8.<init>(r2, r3)
            r2 = r0
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r0.L$0 = r6
            r0.L$1 = r4
            r0.L$2 = r5
            r0.L$3 = r5
            r3 = 5
            r0.label = r3
            java.lang.Object r2 = r7.proceedWith(r8, r2)
            if (r2 != r1) goto L_0x01bd
            return r1
        L_0x01bd:
            r1 = r4
        L_0x01be:
            r5 = r2
            io.ktor.client.statement.HttpResponseContainer r5 = (io.ktor.client.statement.HttpResponseContainer) r5
            r7 = r1
            r2 = r6
            goto L_0x0311
        L_0x01c5:
            java.lang.Class<byte[]> r10 = byte[].class
            kotlin.reflect.KClass r10 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r10)
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)
            if (r10 == 0) goto L_0x0280
            io.ktor.utils.io.ByteReadChannel r6 = (io.ktor.utils.io.ByteReadChannel) r6
            r9 = r0
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r0.L$0 = r2
            r0.L$1 = r7
            r0.L$2 = r8
            r10 = 6
            r0.label = r10
            java.lang.Object r6 = io.ktor.util.ByteChannelsKt.toByteArray(r6, r9)
            if (r6 != r1) goto L_0x01e6
            return r1
        L_0x01e6:
            r17 = r8
            r8 = r2
            r2 = r17
        L_0x01eb:
            byte[] r6 = (byte[]) r6
            r9 = r2
            io.ktor.http.HttpMessage r9 = (io.ktor.http.HttpMessage) r9
            java.lang.Long r9 = io.ktor.http.HttpMessagePropertiesKt.contentLength((io.ktor.http.HttpMessage) r9)
            io.ktor.util.PlatformUtils r10 = io.ktor.util.PlatformUtils.INSTANCE
            boolean r10 = r10.getIS_BROWSER()
            if (r10 != 0) goto L_0x020d
            io.ktor.http.Headers r2 = r2.getHeaders()
            io.ktor.http.HttpHeaders r10 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r10 = r10.getContentEncoding()
            java.lang.String r2 = r2.get(r10)
            if (r2 != 0) goto L_0x020d
            r4 = 1
        L_0x020d:
            java.lang.Object r2 = r8.getContext()
            io.ktor.client.call.HttpClientCall r2 = (io.ktor.client.call.HttpClientCall) r2
            io.ktor.client.request.HttpRequest r2 = r2.getRequest()
            io.ktor.http.HttpMethod r2 = r2.getMethod()
            io.ktor.http.HttpMethod$Companion r10 = io.ktor.http.HttpMethod.Companion
            io.ktor.http.HttpMethod r10 = r10.getHead()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r10)
            r2 = r2 ^ r3
            if (r4 == 0) goto L_0x0260
            if (r2 == 0) goto L_0x0260
            if (r9 == 0) goto L_0x0260
            long r2 = r9.longValue()
            r10 = 0
            int r4 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r4 <= 0) goto L_0x0260
            int r2 = r6.length
            long r3 = r9.longValue()
            int r4 = (int) r3
            if (r2 != r4) goto L_0x023f
            goto L_0x0260
        L_0x023f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Expected "
            r1.<init>(r2)
            r1.append(r9)
            java.lang.String r2 = ", actual "
            r1.append(r2)
            int r2 = r6.length
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            throw r2
        L_0x0260:
            io.ktor.client.statement.HttpResponseContainer r2 = new io.ktor.client.statement.HttpResponseContainer
            r2.<init>(r7, r6)
            r3 = r0
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r0.L$0 = r8
            r0.L$1 = r7
            r0.L$2 = r5
            r4 = 7
            r0.label = r4
            java.lang.Object r2 = r8.proceedWith(r2, r3)
            if (r2 != r1) goto L_0x0278
            return r1
        L_0x0278:
            r1 = r7
        L_0x0279:
            r5 = r2
            io.ktor.client.statement.HttpResponseContainer r5 = (io.ktor.client.statement.HttpResponseContainer) r5
            r7 = r1
            r2 = r8
            goto L_0x0311
        L_0x0280:
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r3 = io.ktor.utils.io.ByteReadChannel.class
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x02df
            kotlin.coroutines.CoroutineContext r3 = r8.getCoroutineContext()
            kotlinx.coroutines.Job$Key r4 = kotlinx.coroutines.Job.Key
            kotlin.coroutines.CoroutineContext$Key r4 = (kotlin.coroutines.CoroutineContext.Key) r4
            kotlin.coroutines.CoroutineContext$Element r3 = r3.get(r4)
            kotlinx.coroutines.Job r3 = (kotlinx.coroutines.Job) r3
            kotlinx.coroutines.CompletableJob r3 = kotlinx.coroutines.JobKt.Job((kotlinx.coroutines.Job) r3)
            r9 = r2
            kotlinx.coroutines.CoroutineScope r9 = (kotlinx.coroutines.CoroutineScope) r9
            kotlin.coroutines.CoroutineContext r10 = r8.getCoroutineContext()
            io.ktor.client.plugins.DefaultTransformKt$defaultTransformers$2$result$channel$1 r4 = new io.ktor.client.plugins.DefaultTransformKt$defaultTransformers$2$result$channel$1
            r4.<init>(r6, r8, r5)
            r12 = r4
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            r13 = 2
            r14 = 0
            r11 = 0
            io.ktor.utils.io.WriterJob r4 = io.ktor.utils.io.CoroutinesKt.writer$default((kotlinx.coroutines.CoroutineScope) r9, (kotlin.coroutines.CoroutineContext) r10, (boolean) r11, (kotlin.jvm.functions.Function2) r12, (int) r13, (java.lang.Object) r14)
            io.ktor.client.plugins.DefaultTransformKt$defaultTransformers$2$result$channel$2$1 r5 = new io.ktor.client.plugins.DefaultTransformKt$defaultTransformers$2$result$channel$2$1
            r5.<init>(r3)
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            r4.invokeOnCompletion(r5)
            io.ktor.utils.io.ByteReadChannel r3 = r4.getChannel()
            io.ktor.client.statement.HttpResponseContainer r4 = new io.ktor.client.statement.HttpResponseContainer
            r4.<init>(r7, r3)
            r3 = r0
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r0.L$0 = r2
            r0.L$1 = r7
            r5 = 8
            r0.label = r5
            java.lang.Object r3 = r2.proceedWith(r4, r3)
            if (r3 != r1) goto L_0x02d9
            return r1
        L_0x02d9:
            r1 = r7
        L_0x02da:
            r5 = r3
            io.ktor.client.statement.HttpResponseContainer r5 = (io.ktor.client.statement.HttpResponseContainer) r5
            goto L_0x010b
        L_0x02df:
            java.lang.Class<io.ktor.http.HttpStatusCode> r3 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x0311
            io.ktor.utils.io.ByteReadChannel r6 = (io.ktor.utils.io.ByteReadChannel) r6
            io.ktor.utils.io.ByteReadChannelKt.cancel(r6)
            io.ktor.client.statement.HttpResponseContainer r3 = new io.ktor.client.statement.HttpResponseContainer
            io.ktor.http.HttpStatusCode r4 = r8.getStatus()
            r3.<init>(r7, r4)
            r4 = r0
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r0.L$0 = r2
            r0.L$1 = r7
            r5 = 9
            r0.label = r5
            java.lang.Object r3 = r2.proceedWith(r3, r4)
            if (r3 != r1) goto L_0x030b
            return r1
        L_0x030b:
            r1 = r7
        L_0x030c:
            r5 = r3
            io.ktor.client.statement.HttpResponseContainer r5 = (io.ktor.client.statement.HttpResponseContainer) r5
            goto L_0x010b
        L_0x0311:
            if (r5 == 0) goto L_0x0342
            org.slf4j.Logger r1 = io.ktor.client.plugins.DefaultTransformKt.LOGGER
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Transformed with default transformers response body for "
            r3.<init>(r4)
            java.lang.Object r2 = r2.getContext()
            io.ktor.client.call.HttpClientCall r2 = (io.ktor.client.call.HttpClientCall) r2
            io.ktor.client.request.HttpRequest r2 = r2.getRequest()
            io.ktor.http.Url r2 = r2.getUrl()
            r3.append(r2)
            java.lang.String r2 = " to "
            r3.append(r2)
            kotlin.reflect.KClass r2 = r7.getType()
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.trace(r2)
        L_0x0342:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.DefaultTransformKt$defaultTransformers$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
