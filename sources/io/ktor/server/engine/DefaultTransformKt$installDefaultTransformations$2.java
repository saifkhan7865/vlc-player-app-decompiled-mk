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
@DebugMetadata(c = "io.ktor.server.engine.DefaultTransformKt$installDefaultTransformations$2", f = "DefaultTransform.kt", i = {0, 0, 1, 1, 2, 2, 3, 3}, l = {42, 47, 53, 69, 73}, m = "invokeSuspend", n = {"$this$intercept", "body", "$this$intercept", "body", "$this$intercept", "body", "$this$intercept", "body"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
/* compiled from: DefaultTransform.kt */
final class DefaultTransformKt$installDefaultTransformations$2 extends SuspendLambda implements Function3<PipelineContext<Object, ApplicationCall>, Object, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    int label;

    DefaultTransformKt$installDefaultTransformations$2(Continuation<? super DefaultTransformKt$installDefaultTransformations$2> continuation) {
        super(3, continuation);
    }

    public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        DefaultTransformKt$installDefaultTransformations$2 defaultTransformKt$installDefaultTransformations$2 = new DefaultTransformKt$installDefaultTransformations$2(continuation);
        defaultTransformKt$installDefaultTransformations$2.L$0 = pipelineContext;
        defaultTransformKt$installDefaultTransformations$2.L$1 = obj;
        return defaultTransformKt$installDefaultTransformations$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x01ce  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) {
        /*
            r16 = this;
            r1 = r16
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            r8 = 0
            if (r2 == 0) goto L_0x0052
            if (r2 == r7) goto L_0x0027
            if (r2 == r6) goto L_0x0044
            if (r2 == r5) goto L_0x0035
            if (r2 == r4) goto L_0x0027
            if (r2 != r3) goto L_0x001f
            kotlin.ResultKt.throwOnFailure(r17)
            goto L_0x0213
        L_0x001f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0027:
            java.lang.Object r2 = r1.L$1
            java.lang.Object r4 = r1.L$0
            io.ktor.util.pipeline.PipelineContext r4 = (io.ktor.util.pipeline.PipelineContext) r4
            kotlin.ResultKt.throwOnFailure(r17)
            r5 = r4
            r4 = r17
            goto L_0x0177
        L_0x0035:
            java.lang.Object r2 = r1.L$2
            io.ktor.http.ParametersBuilder r2 = (io.ktor.http.ParametersBuilder) r2
            java.lang.Object r4 = r1.L$1
            java.lang.Object r5 = r1.L$0
            io.ktor.util.pipeline.PipelineContext r5 = (io.ktor.util.pipeline.PipelineContext) r5
            kotlin.ResultKt.throwOnFailure(r17)
            goto L_0x0133
        L_0x0044:
            java.lang.Object r2 = r1.L$1
            java.lang.Object r4 = r1.L$0
            io.ktor.util.pipeline.PipelineContext r4 = (io.ktor.util.pipeline.PipelineContext) r4
            kotlin.ResultKt.throwOnFailure(r17)
            r5 = r4
            r4 = r17
            goto L_0x00f5
        L_0x0052:
            kotlin.ResultKt.throwOnFailure(r17)
            java.lang.Object r2 = r1.L$0
            io.ktor.util.pipeline.PipelineContext r2 = (io.ktor.util.pipeline.PipelineContext) r2
            java.lang.Object r9 = r1.L$1
            boolean r10 = r9 instanceof io.ktor.utils.io.ByteReadChannel
            if (r10 == 0) goto L_0x0063
            r10 = r9
            io.ktor.utils.io.ByteReadChannel r10 = (io.ktor.utils.io.ByteReadChannel) r10
            goto L_0x0064
        L_0x0063:
            r10 = r8
        L_0x0064:
            if (r10 != 0) goto L_0x0069
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0069:
            java.lang.Object r11 = r2.getContext()
            io.ktor.server.application.ApplicationCall r11 = (io.ktor.server.application.ApplicationCall) r11
            io.ktor.util.reflect.TypeInfo r11 = io.ktor.server.application.ApplicationCallKt.getReceiveType(r11)
            kotlin.reflect.KClass r11 = r11.getType()
            java.lang.Class<io.ktor.utils.io.ByteReadChannel> r12 = io.ktor.utils.io.ByteReadChannel.class
            kotlin.reflect.KClass r12 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r12)
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r12)
            if (r12 == 0) goto L_0x0088
        L_0x0083:
            r5 = r2
            r4 = r8
        L_0x0085:
            r2 = r9
            goto L_0x0177
        L_0x0088:
            java.lang.Class<byte[]> r12 = byte[].class
            kotlin.reflect.KClass r12 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r12)
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r12)
            r13 = 0
            if (r12 == 0) goto L_0x00a5
            r4 = r1
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r1.L$0 = r2
            r1.L$1 = r9
            r1.label = r7
            java.lang.Object r4 = io.ktor.util.cio.ReadersKt.toByteArray$default(r10, r13, r4, r7, r8)
            if (r4 != r0) goto L_0x0174
            return r0
        L_0x00a5:
            java.lang.Class<io.ktor.http.Parameters> r12 = io.ktor.http.Parameters.class
            kotlin.reflect.KClass r12 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r12)
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r12)
            if (r11 == 0) goto L_0x0164
            java.lang.Object r4 = r2.getContext()
            io.ktor.server.application.ApplicationCall r4 = (io.ktor.server.application.ApplicationCall) r4
            java.lang.Object r11 = r2.getContext()     // Catch:{ BadContentTypeFormatException -> 0x013b }
            io.ktor.server.application.ApplicationCall r11 = (io.ktor.server.application.ApplicationCall) r11     // Catch:{ BadContentTypeFormatException -> 0x013b }
            io.ktor.server.request.ApplicationRequest r11 = r11.getRequest()     // Catch:{ BadContentTypeFormatException -> 0x013b }
            io.ktor.http.ContentType r4 = io.ktor.server.request.ApplicationRequestPropertiesKt.contentType(r11)     // Catch:{ BadContentTypeFormatException -> 0x013b }
            io.ktor.http.ContentType$Application r11 = io.ktor.http.ContentType.Application.INSTANCE
            io.ktor.http.ContentType r11 = r11.getFormUrlEncoded()
            boolean r11 = r4.match((io.ktor.http.ContentType) r11)
            if (r11 == 0) goto L_0x0104
            java.lang.Object r4 = r2.getContext()
            io.ktor.server.application.ApplicationCall r4 = (io.ktor.server.application.ApplicationCall) r4
            io.ktor.server.request.ApplicationRequest r4 = r4.getRequest()
            java.nio.charset.Charset r4 = io.ktor.server.request.ApplicationRequestPropertiesKt.contentCharset(r4)
            if (r4 != 0) goto L_0x00e3
            java.nio.charset.Charset r4 = kotlin.text.Charsets.UTF_8
        L_0x00e3:
            r5 = r1
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r1.L$0 = r2
            r1.L$1 = r9
            r1.label = r6
            java.lang.Object r4 = io.ktor.server.engine.DefaultTransformKt.readText(r10, r4, r5)
            if (r4 != r0) goto L_0x00f3
            return r0
        L_0x00f3:
            r5 = r2
            r2 = r9
        L_0x00f5:
            r9 = r4
            java.lang.String r9 = (java.lang.String) r9
            r13 = 14
            r14 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            io.ktor.http.Parameters r4 = io.ktor.http.QueryKt.parseQueryString$default(r9, r10, r11, r12, r13, r14)
            goto L_0x0177
        L_0x0104:
            io.ktor.http.ContentType$MultiPart r6 = io.ktor.http.ContentType.MultiPart.INSTANCE
            io.ktor.http.ContentType r6 = r6.getFormData()
            boolean r4 = r4.match((io.ktor.http.ContentType) r6)
            if (r4 == 0) goto L_0x0083
            io.ktor.http.Parameters$Companion r4 = io.ktor.http.Parameters.Companion
            io.ktor.http.ParametersBuilder r4 = io.ktor.http.ParametersKt.ParametersBuilder$default(r13, r7, r8)
            io.ktor.http.content.MultiPartData r6 = io.ktor.server.engine.DefaultTransformJvmKt.multiPartData(r2, r10)
            io.ktor.server.engine.DefaultTransformKt$installDefaultTransformations$2$transformed$1$1 r7 = new io.ktor.server.engine.DefaultTransformKt$installDefaultTransformations$2$transformed$1$1
            r7.<init>(r4, r8)
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r1.L$0 = r2
            r1.L$1 = r9
            r1.L$2 = r4
            r1.label = r5
            java.lang.Object r5 = io.ktor.http.content.MultipartKt.forEachPart(r6, r7, r1)
            if (r5 != r0) goto L_0x0130
            return r0
        L_0x0130:
            r5 = r2
            r2 = r4
            r4 = r9
        L_0x0133:
            io.ktor.http.Parameters r2 = r2.build()
            r15 = r4
            r4 = r2
            r2 = r15
            goto L_0x0177
        L_0x013b:
            r0 = move-exception
            io.ktor.server.plugins.BadRequestException r2 = new io.ktor.server.plugins.BadRequestException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "Illegal Content-Type header format: "
            r3.<init>(r5)
            io.ktor.server.request.ApplicationRequest r4 = r4.getRequest()
            io.ktor.http.Headers r4 = r4.getHeaders()
            io.ktor.http.HttpHeaders r5 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r5 = r5.getContentType()
            java.lang.String r4 = r4.get(r5)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r2.<init>(r3, r0)
            throw r2
        L_0x0164:
            r5 = r1
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r1.L$0 = r2
            r1.L$1 = r9
            r1.label = r4
            java.lang.Object r4 = io.ktor.server.engine.DefaultTransformJvmKt.defaultPlatformTransformations(r2, r9, r5)
            if (r4 != r0) goto L_0x0174
            return r0
        L_0x0174:
            r5 = r2
            goto L_0x0085
        L_0x0177:
            if (r4 == 0) goto L_0x01ce
            org.slf4j.Logger r6 = io.ktor.server.engine.DefaultTransformKt.getLOGGER()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r9 = "Transformed "
            r7.<init>(r9)
            java.lang.Class r2 = r2.getClass()
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            r7.append(r2)
            java.lang.String r2 = " to "
            r7.append(r2)
            java.lang.Class r2 = r4.getClass()
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            r7.append(r2)
            java.lang.String r2 = " for "
            r7.append(r2)
            java.lang.Object r2 = r5.getContext()
            io.ktor.server.application.ApplicationCall r2 = (io.ktor.server.application.ApplicationCall) r2
            io.ktor.server.request.ApplicationRequest r2 = r2.getRequest()
            java.lang.String r2 = io.ktor.server.request.ApplicationRequestPropertiesKt.getUri(r2)
            r7.append(r2)
            java.lang.String r2 = r7.toString()
            r6.trace(r2)
            r2 = r1
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r1.L$0 = r8
            r1.L$1 = r8
            r1.L$2 = r8
            r1.label = r3
            java.lang.Object r2 = r5.proceedWith(r4, r2)
            if (r2 != r0) goto L_0x0213
            return r0
        L_0x01ce:
            org.slf4j.Logger r0 = io.ktor.server.engine.DefaultTransformKt.getLOGGER()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "No Default Transformations found for "
            r3.<init>(r4)
            java.lang.Class r2 = r2.getClass()
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            r3.append(r2)
            java.lang.String r2 = " and expected type "
            r3.append(r2)
            java.lang.Object r2 = r5.getContext()
            io.ktor.server.application.ApplicationCall r2 = (io.ktor.server.application.ApplicationCall) r2
            io.ktor.util.reflect.TypeInfo r2 = io.ktor.server.application.ApplicationCallKt.getReceiveType(r2)
            r3.append(r2)
            java.lang.String r2 = " for call "
            r3.append(r2)
            java.lang.Object r2 = r5.getContext()
            io.ktor.server.application.ApplicationCall r2 = (io.ktor.server.application.ApplicationCall) r2
            io.ktor.server.request.ApplicationRequest r2 = r2.getRequest()
            java.lang.String r2 = io.ktor.server.request.ApplicationRequestPropertiesKt.getUri(r2)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r0.trace(r2)
        L_0x0213:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.DefaultTransformKt$installDefaultTransformations$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
