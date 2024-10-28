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
@DebugMetadata(c = "io.ktor.server.engine.DefaultTransformKt$installDefaultTransformations$3", f = "DefaultTransform.kt", i = {0}, l = {87, 88}, m = "invokeSuspend", n = {"$this$intercept"}, s = {"L$0"})
/* compiled from: DefaultTransform.kt */
final class DefaultTransformKt$installDefaultTransformations$3 extends SuspendLambda implements Function3<PipelineContext<Object, ApplicationCall>, Object, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    DefaultTransformKt$installDefaultTransformations$3(Continuation<? super DefaultTransformKt$installDefaultTransformations$3> continuation) {
        super(3, continuation);
    }

    public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        DefaultTransformKt$installDefaultTransformations$3 defaultTransformKt$installDefaultTransformations$3 = new DefaultTransformKt$installDefaultTransformations$3(continuation);
        defaultTransformKt$installDefaultTransformations$3.L$0 = pipelineContext;
        defaultTransformKt$installDefaultTransformations$3.L$1 = obj;
        return defaultTransformKt$installDefaultTransformations$3.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: io.ktor.util.pipeline.PipelineContext} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 0
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0024
            if (r1 == r4) goto L_0x001c
            if (r1 != r3) goto L_0x0014
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x008e
        L_0x0014:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x001c:
            java.lang.Object r1 = r7.L$0
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x007e
        L_0x0024:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            r1 = r8
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            java.lang.Object r8 = r7.L$1
            boolean r5 = r8 instanceof io.ktor.utils.io.ByteReadChannel
            if (r5 == 0) goto L_0x0035
            io.ktor.utils.io.ByteReadChannel r8 = (io.ktor.utils.io.ByteReadChannel) r8
            goto L_0x0036
        L_0x0035:
            r8 = r2
        L_0x0036:
            if (r8 != 0) goto L_0x003b
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x003b:
            java.lang.Object r5 = r1.getContext()
            io.ktor.server.application.ApplicationCall r5 = (io.ktor.server.application.ApplicationCall) r5
            io.ktor.util.reflect.TypeInfo r5 = io.ktor.server.application.ApplicationCallKt.getReceiveType(r5)
            kotlin.reflect.KClass r5 = r5.getType()
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r5 != 0) goto L_0x0058
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0058:
            java.lang.Object r5 = r1.getContext()
            io.ktor.server.application.ApplicationCall r5 = (io.ktor.server.application.ApplicationCall) r5
            java.lang.Object r6 = r1.getContext()     // Catch:{ BadContentTypeFormatException -> 0x0091 }
            io.ktor.server.application.ApplicationCall r6 = (io.ktor.server.application.ApplicationCall) r6     // Catch:{ BadContentTypeFormatException -> 0x0091 }
            io.ktor.server.request.ApplicationRequest r6 = r6.getRequest()     // Catch:{ BadContentTypeFormatException -> 0x0091 }
            java.nio.charset.Charset r5 = io.ktor.server.request.ApplicationRequestPropertiesKt.contentCharset(r6)     // Catch:{ BadContentTypeFormatException -> 0x0091 }
            if (r5 != 0) goto L_0x0070
            java.nio.charset.Charset r5 = kotlin.text.Charsets.UTF_8
        L_0x0070:
            r6 = r7
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r7.L$0 = r1
            r7.label = r4
            java.lang.Object r8 = io.ktor.server.engine.DefaultTransformKt.readText(r8, r5, r6)
            if (r8 != r0) goto L_0x007e
            return r0
        L_0x007e:
            java.lang.String r8 = (java.lang.String) r8
            r4 = r7
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r7.L$0 = r2
            r7.label = r3
            java.lang.Object r8 = r1.proceedWith(r8, r4)
            if (r8 != r0) goto L_0x008e
            return r0
        L_0x008e:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0091:
            r8 = move-exception
            io.ktor.server.plugins.BadRequestException r0 = new io.ktor.server.plugins.BadRequestException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Illegal Content-Type header format: "
            r1.<init>(r2)
            io.ktor.server.request.ApplicationRequest r2 = r5.getRequest()
            io.ktor.http.Headers r2 = r2.getHeaders()
            io.ktor.http.HttpHeaders r3 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r3 = r3.getContentType()
            java.lang.String r2 = r2.get(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            r0.<init>(r1, r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.DefaultTransformKt$installDefaultTransformations$3.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
