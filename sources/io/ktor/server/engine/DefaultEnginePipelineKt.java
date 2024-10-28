package io.ktor.server.engine;

import io.ktor.http.HttpStatusCode;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationEnvironment;
import io.ktor.server.engine.internal.ApplicationUtilsJvmKt;
import io.ktor.server.logging.LoggingKt;
import io.ktor.server.plugins.BadRequestException;
import io.ktor.server.plugins.NotFoundException;
import io.ktor.server.plugins.UnsupportedMediaTypeException;
import java.util.concurrent.TimeoutException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.TimeoutCancellationException;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a!\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a!\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a!\u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a\u001c\u0010\u0012\u001a\u00020\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"defaultEnginePipeline", "Lio/ktor/server/engine/EnginePipeline;", "environment", "Lio/ktor/server/application/ApplicationEnvironment;", "defaultExceptionStatusCode", "Lio/ktor/http/HttpStatusCode;", "cause", "", "handleFailure", "", "call", "Lio/ktor/server/application/ApplicationCall;", "error", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logError", "tryRespondError", "statusCode", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/HttpStatusCode;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logFailure", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultEnginePipeline.kt */
public final class DefaultEnginePipelineKt {
    public static final EnginePipeline defaultEnginePipeline(ApplicationEnvironment applicationEnvironment) {
        Intrinsics.checkNotNullParameter(applicationEnvironment, "environment");
        EnginePipeline enginePipeline = new EnginePipeline(applicationEnvironment.getDevelopmentMode());
        ApplicationUtilsJvmKt.configureShutdownUrl(applicationEnvironment, enginePipeline);
        enginePipeline.intercept(EnginePipeline.Companion.getCall(), new DefaultEnginePipelineKt$defaultEnginePipeline$1((Continuation<? super DefaultEnginePipelineKt$defaultEnginePipeline$1>) null));
        return enginePipeline;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: java.lang.Throwable} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object handleFailure(io.ktor.server.application.ApplicationCall r5, java.lang.Throwable r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.server.engine.DefaultEnginePipelineKt$handleFailure$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.server.engine.DefaultEnginePipelineKt$handleFailure$1 r0 = (io.ktor.server.engine.DefaultEnginePipelineKt$handleFailure$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.server.engine.DefaultEnginePipelineKt$handleFailure$1 r0 = new io.ktor.server.engine.DefaultEnginePipelineKt$handleFailure$1
            r0.<init>(r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0042
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x006c
        L_0x002d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0035:
            java.lang.Object r5 = r0.L$1
            r6 = r5
            java.lang.Throwable r6 = (java.lang.Throwable) r6
            java.lang.Object r5 = r0.L$0
            io.ktor.server.application.ApplicationCall r5 = (io.ktor.server.application.ApplicationCall) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0052
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = logError(r5, r6, r0)
            if (r7 != r1) goto L_0x0052
            return r1
        L_0x0052:
            io.ktor.http.HttpStatusCode r6 = defaultExceptionStatusCode(r6)
            if (r6 != 0) goto L_0x005e
            io.ktor.http.HttpStatusCode$Companion r6 = io.ktor.http.HttpStatusCode.Companion
            io.ktor.http.HttpStatusCode r6 = r6.getInternalServerError()
        L_0x005e:
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r5 = tryRespondError(r5, r6, r0)
            if (r5 != r1) goto L_0x006c
            return r1
        L_0x006c:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.DefaultEnginePipelineKt.handleFailure(io.ktor.server.application.ApplicationCall, java.lang.Throwable, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object logError(ApplicationCall applicationCall, Throwable th, Continuation<? super Unit> continuation) {
        Object withMDCBlock = LoggingKt.getMdcProvider(applicationCall.getApplication()).withMDCBlock(applicationCall, new DefaultEnginePipelineKt$logError$2(applicationCall, th, (Continuation<? super DefaultEnginePipelineKt$logError$2>) null), continuation);
        return withMDCBlock == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withMDCBlock : Unit.INSTANCE;
    }

    public static final HttpStatusCode defaultExceptionStatusCode(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "cause");
        if (th instanceof BadRequestException) {
            return HttpStatusCode.Companion.getBadRequest();
        }
        if (th instanceof NotFoundException) {
            return HttpStatusCode.Companion.getNotFound();
        }
        if (th instanceof UnsupportedMediaTypeException) {
            return HttpStatusCode.Companion.getUnsupportedMediaType();
        }
        if (!(th instanceof TimeoutException) && !(th instanceof TimeoutCancellationException)) {
            return null;
        }
        return HttpStatusCode.Companion.getGatewayTimeout();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object tryRespondError(io.ktor.server.application.ApplicationCall r6, io.ktor.http.HttpStatusCode r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.server.engine.DefaultEnginePipelineKt$tryRespondError$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.server.engine.DefaultEnginePipelineKt$tryRespondError$1 r0 = (io.ktor.server.engine.DefaultEnginePipelineKt$tryRespondError$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.server.engine.DefaultEnginePipelineKt$tryRespondError$1 r0 = new io.ktor.server.engine.DefaultEnginePipelineKt$tryRespondError$1
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ ResponseAlreadySentException -> 0x007a }
            goto L_0x007a
        L_0x002a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r8)
            io.ktor.server.response.ApplicationResponse r8 = r6.getResponse()     // Catch:{ ResponseAlreadySentException -> 0x007a }
            io.ktor.http.HttpStatusCode r8 = r8.status()     // Catch:{ ResponseAlreadySentException -> 0x007a }
            if (r8 != 0) goto L_0x007a
            boolean r8 = r7 instanceof io.ktor.http.content.OutgoingContent     // Catch:{ ResponseAlreadySentException -> 0x007a }
            if (r8 != 0) goto L_0x0062
            boolean r8 = r7 instanceof byte[]     // Catch:{ ResponseAlreadySentException -> 0x007a }
            if (r8 != 0) goto L_0x0062
            io.ktor.server.response.ApplicationResponse r8 = r6.getResponse()     // Catch:{ ResponseAlreadySentException -> 0x007a }
            java.lang.Class<io.ktor.http.HttpStatusCode> r2 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KType r2 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r2)     // Catch:{ ResponseAlreadySentException -> 0x007a }
            java.lang.reflect.Type r4 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r2)     // Catch:{ ResponseAlreadySentException -> 0x007a }
            java.lang.Class<io.ktor.http.HttpStatusCode> r5 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)     // Catch:{ ResponseAlreadySentException -> 0x007a }
            io.ktor.util.reflect.TypeInfo r2 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r4, r5, r2)     // Catch:{ ResponseAlreadySentException -> 0x007a }
            io.ktor.server.response.ResponseTypeKt.setResponseType(r8, r2)     // Catch:{ ResponseAlreadySentException -> 0x007a }
        L_0x0062:
            io.ktor.server.response.ApplicationResponse r8 = r6.getResponse()     // Catch:{ ResponseAlreadySentException -> 0x007a }
            io.ktor.server.response.ApplicationSendPipeline r8 = r8.getPipeline()     // Catch:{ ResponseAlreadySentException -> 0x007a }
            java.lang.String r2 = "null cannot be cast to non-null type kotlin.Any"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7, r2)     // Catch:{ ResponseAlreadySentException -> 0x007a }
            java.lang.Object r7 = (java.lang.Object) r7     // Catch:{ ResponseAlreadySentException -> 0x007a }
            r0.label = r3     // Catch:{ ResponseAlreadySentException -> 0x007a }
            java.lang.Object r6 = r8.execute(r6, r7, r0)     // Catch:{ ResponseAlreadySentException -> 0x007a }
            if (r6 != r1) goto L_0x007a
            return r1
        L_0x007a:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.DefaultEnginePipelineKt.tryRespondError(io.ktor.server.application.ApplicationCall, io.ktor.http.HttpStatusCode, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:33|34|40) */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        io.ktor.util.logging.LoggerKt.error(r4.getLog(), r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a7, code lost:
        io.ktor.server.engine.internal.ApplicationUtilsJvmKt.printError("OutOfMemoryError: ");
        io.ktor.server.engine.internal.ApplicationUtilsJvmKt.printError(r6.getMessage());
        io.ktor.server.engine.internal.ApplicationUtilsJvmKt.printError("\n");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x009f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void logFailure(io.ktor.server.application.ApplicationEnvironment r4, io.ktor.server.application.ApplicationCall r5, java.lang.Throwable r6) {
        /*
            java.lang.String r0 = ": "
            java.lang.String r1 = "(request error: "
            io.ktor.server.response.ApplicationResponse r2 = r5.getResponse()     // Catch:{ OutOfMemoryError -> 0x009f }
            io.ktor.http.HttpStatusCode r2 = r2.status()     // Catch:{ OutOfMemoryError -> 0x009f }
            if (r2 == 0) goto L_0x0011
        L_0x000e:
            java.lang.Comparable r2 = (java.lang.Comparable) r2     // Catch:{ OutOfMemoryError -> 0x009f }
            goto L_0x0014
        L_0x0011:
            java.lang.String r2 = "Unhandled"
            goto L_0x000e
        L_0x0014:
            io.ktor.server.request.ApplicationRequest r5 = r5.getRequest()     // Catch:{ all -> 0x001d }
            java.lang.String r5 = io.ktor.server.logging.LoggingKt.toLogString(r5)     // Catch:{ all -> 0x001d }
            goto L_0x002f
        L_0x001d:
            r5 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x009f }
            r3.<init>(r1)     // Catch:{ OutOfMemoryError -> 0x009f }
            r3.append(r5)     // Catch:{ OutOfMemoryError -> 0x009f }
            r5 = 41
            r3.append(r5)     // Catch:{ OutOfMemoryError -> 0x009f }
            java.lang.String r5 = r3.toString()     // Catch:{ OutOfMemoryError -> 0x009f }
        L_0x002f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x009f }
            r1.<init>()     // Catch:{ OutOfMemoryError -> 0x009f }
            r1.append(r2)     // Catch:{ OutOfMemoryError -> 0x009f }
            r1.append(r0)     // Catch:{ OutOfMemoryError -> 0x009f }
            r1.append(r5)     // Catch:{ OutOfMemoryError -> 0x009f }
            java.lang.String r3 = ". Exception "
            r1.append(r3)     // Catch:{ OutOfMemoryError -> 0x009f }
            java.lang.Class r3 = r6.getClass()     // Catch:{ OutOfMemoryError -> 0x009f }
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ OutOfMemoryError -> 0x009f }
            r1.append(r3)     // Catch:{ OutOfMemoryError -> 0x009f }
            r1.append(r0)     // Catch:{ OutOfMemoryError -> 0x009f }
            java.lang.String r3 = r6.getMessage()     // Catch:{ OutOfMemoryError -> 0x009f }
            r1.append(r3)     // Catch:{ OutOfMemoryError -> 0x009f }
            java.lang.String r1 = r1.toString()     // Catch:{ OutOfMemoryError -> 0x009f }
            boolean r3 = r6 instanceof java.util.concurrent.CancellationException     // Catch:{ OutOfMemoryError -> 0x009f }
            if (r3 == 0) goto L_0x0060
            goto L_0x007d
        L_0x0060:
            boolean r3 = r6 instanceof java.nio.channels.ClosedChannelException     // Catch:{ OutOfMemoryError -> 0x009f }
            if (r3 == 0) goto L_0x0065
            goto L_0x007d
        L_0x0065:
            boolean r3 = r6 instanceof io.ktor.util.cio.ChannelIOException     // Catch:{ OutOfMemoryError -> 0x009f }
            if (r3 == 0) goto L_0x006a
            goto L_0x007d
        L_0x006a:
            boolean r3 = r6 instanceof java.io.IOException     // Catch:{ OutOfMemoryError -> 0x009f }
            if (r3 == 0) goto L_0x006f
            goto L_0x007d
        L_0x006f:
            boolean r3 = r6 instanceof io.ktor.server.plugins.BadRequestException     // Catch:{ OutOfMemoryError -> 0x009f }
            if (r3 == 0) goto L_0x0074
            goto L_0x007d
        L_0x0074:
            boolean r3 = r6 instanceof io.ktor.server.plugins.NotFoundException     // Catch:{ OutOfMemoryError -> 0x009f }
            if (r3 == 0) goto L_0x0079
            goto L_0x007d
        L_0x0079:
            boolean r3 = r6 instanceof io.ktor.server.plugins.UnsupportedMediaTypeException     // Catch:{ OutOfMemoryError -> 0x009f }
            if (r3 == 0) goto L_0x0085
        L_0x007d:
            org.slf4j.Logger r5 = r4.getLog()     // Catch:{ OutOfMemoryError -> 0x009f }
            r5.debug((java.lang.String) r1, (java.lang.Throwable) r6)     // Catch:{ OutOfMemoryError -> 0x009f }
            goto L_0x00b8
        L_0x0085:
            org.slf4j.Logger r1 = r4.getLog()     // Catch:{ OutOfMemoryError -> 0x009f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x009f }
            r3.<init>()     // Catch:{ OutOfMemoryError -> 0x009f }
            r3.append(r2)     // Catch:{ OutOfMemoryError -> 0x009f }
            r3.append(r0)     // Catch:{ OutOfMemoryError -> 0x009f }
            r3.append(r5)     // Catch:{ OutOfMemoryError -> 0x009f }
            java.lang.String r5 = r3.toString()     // Catch:{ OutOfMemoryError -> 0x009f }
            r1.error((java.lang.String) r5, (java.lang.Throwable) r6)     // Catch:{ OutOfMemoryError -> 0x009f }
            goto L_0x00b8
        L_0x009f:
            org.slf4j.Logger r4 = r4.getLog()     // Catch:{ OutOfMemoryError -> 0x00a7 }
            io.ktor.util.logging.LoggerKt.error(r4, r6)     // Catch:{ OutOfMemoryError -> 0x00a7 }
            goto L_0x00b8
        L_0x00a7:
            java.lang.String r4 = "OutOfMemoryError: "
            io.ktor.server.engine.internal.ApplicationUtilsJvmKt.printError(r4)
            java.lang.String r4 = r6.getMessage()
            io.ktor.server.engine.internal.ApplicationUtilsJvmKt.printError(r4)
            java.lang.String r4 = "\n"
            io.ktor.server.engine.internal.ApplicationUtilsJvmKt.printError(r4)
        L_0x00b8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.DefaultEnginePipelineKt.logFailure(io.ktor.server.application.ApplicationEnvironment, io.ktor.server.application.ApplicationCall, java.lang.Throwable):void");
    }
}
