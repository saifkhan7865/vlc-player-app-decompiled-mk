package io.ktor.client.plugins.api;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.reflect.TypeInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function5;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.api.TransformRequestBodyHook$install$1", f = "KtorCallContexts.kt", i = {0}, l = {82, 83}, m = "invokeSuspend", n = {"$this$intercept"}, s = {"L$0"})
/* compiled from: KtorCallContexts.kt */
final class TransformRequestBodyHook$install$1 extends SuspendLambda implements Function3<PipelineContext<Object, HttpRequestBuilder>, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function5<TransformRequestBodyContext, HttpRequestBuilder, Object, TypeInfo, Continuation<? super OutgoingContent>, Object> $handler;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TransformRequestBodyHook$install$1(Function5<? super TransformRequestBodyContext, ? super HttpRequestBuilder, Object, ? super TypeInfo, ? super Continuation<? super OutgoingContent>, ? extends Object> function5, Continuation<? super TransformRequestBodyHook$install$1> continuation) {
        super(3, continuation);
        this.$handler = function5;
    }

    public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        TransformRequestBodyHook$install$1 transformRequestBodyHook$install$1 = new TransformRequestBodyHook$install$1(this.$handler, continuation);
        transformRequestBodyHook$install$1.L$0 = pipelineContext;
        return transformRequestBodyHook$install$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: io.ktor.util.pipeline.PipelineContext} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0022
            if (r1 == r3) goto L_0x001a
            if (r1 != r2) goto L_0x0012
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0062
        L_0x0012:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x001a:
            java.lang.Object r1 = r10.L$0
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x004f
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.Object r11 = r10.L$0
            r1 = r11
            io.ktor.util.pipeline.PipelineContext r1 = (io.ktor.util.pipeline.PipelineContext) r1
            kotlin.jvm.functions.Function5<io.ktor.client.plugins.api.TransformRequestBodyContext, io.ktor.client.request.HttpRequestBuilder, java.lang.Object, io.ktor.util.reflect.TypeInfo, kotlin.coroutines.Continuation<? super io.ktor.http.content.OutgoingContent>, java.lang.Object> r4 = r10.$handler
            io.ktor.client.plugins.api.TransformRequestBodyContext r5 = new io.ktor.client.plugins.api.TransformRequestBodyContext
            r5.<init>()
            java.lang.Object r6 = r1.getContext()
            java.lang.Object r7 = r1.getSubject()
            java.lang.Object r11 = r1.getContext()
            io.ktor.client.request.HttpRequestBuilder r11 = (io.ktor.client.request.HttpRequestBuilder) r11
            io.ktor.util.reflect.TypeInfo r8 = r11.getBodyType()
            r10.L$0 = r1
            r10.label = r3
            r9 = r10
            java.lang.Object r11 = r4.invoke(r5, r6, r7, r8, r9)
            if (r11 != r0) goto L_0x004f
            return r0
        L_0x004f:
            io.ktor.http.content.OutgoingContent r11 = (io.ktor.http.content.OutgoingContent) r11
            if (r11 == 0) goto L_0x0062
            r3 = r10
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r4 = 0
            r10.L$0 = r4
            r10.label = r2
            java.lang.Object r11 = r1.proceedWith(r11, r3)
            if (r11 != r0) goto L_0x0062
            return r0
        L_0x0062:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.api.TransformRequestBodyHook$install$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
