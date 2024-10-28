package io.ktor.server.engine;

import androidx.core.app.NotificationCompat;
import io.ktor.http.BadContentTypeFormatException;
import io.ktor.http.HttpHeaders;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.plugins.BadRequestException;
import io.ktor.server.request.ApplicationReceivePipeline;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import io.ktor.util.pipeline.PipelinePhase;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a-\u0010\u0005\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\nH\bø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a\n\u0010\f\u001a\u00020\r*\u00020\u000e\u001a\n\u0010\f\u001a\u00020\r*\u00020\u000f\u001a!\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015H@ø\u0001\u0001¢\u0006\u0002\u0010\u0016\"\u0018\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\u0002\u000b\n\u0005\b20\u0001\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"LOGGER", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "getLOGGER", "()Lorg/slf4j/Logger;", "withContentType", "R", "call", "Lio/ktor/server/application/ApplicationCall;", "block", "Lkotlin/Function0;", "(Lio/ktor/server/application/ApplicationCall;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "installDefaultTransformations", "", "Lio/ktor/server/request/ApplicationReceivePipeline;", "Lio/ktor/server/response/ApplicationSendPipeline;", "readText", "", "Lio/ktor/utils/io/ByteReadChannel;", "charset", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "(Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultTransform.kt */
public final class DefaultTransformKt {
    private static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.server.engine.DefaultTransform");

    public static final Logger getLOGGER() {
        return LOGGER;
    }

    public static final void installDefaultTransformations(ApplicationSendPipeline applicationSendPipeline) {
        Intrinsics.checkNotNullParameter(applicationSendPipeline, "<this>");
        applicationSendPipeline.intercept(ApplicationSendPipeline.Phases.getRender(), new DefaultTransformKt$installDefaultTransformations$1((Continuation<? super DefaultTransformKt$installDefaultTransformations$1>) null));
    }

    public static final void installDefaultTransformations(ApplicationReceivePipeline applicationReceivePipeline) {
        Intrinsics.checkNotNullParameter(applicationReceivePipeline, "<this>");
        applicationReceivePipeline.intercept(ApplicationReceivePipeline.Phases.getTransform(), new DefaultTransformKt$installDefaultTransformations$2((Continuation<? super DefaultTransformKt$installDefaultTransformations$2>) null));
        PipelinePhase pipelinePhase = new PipelinePhase("AfterTransform");
        applicationReceivePipeline.insertPhaseAfter(ApplicationReceivePipeline.Phases.getTransform(), pipelinePhase);
        applicationReceivePipeline.intercept(pipelinePhase, new DefaultTransformKt$installDefaultTransformations$3((Continuation<? super DefaultTransformKt$installDefaultTransformations$3>) null));
    }

    public static final <R> R withContentType(ApplicationCall applicationCall, Function0<? extends R> function0) {
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(function0, "block");
        try {
            return function0.invoke();
        } catch (BadContentTypeFormatException e) {
            throw new BadRequestException("Illegal Content-Type header format: " + applicationCall.getRequest().getHeaders().get(HttpHeaders.INSTANCE.getContentType()), e);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.nio.charset.Charset} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0052 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0055 A[SYNTHETIC, Splitter:B:18:0x0055] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object readText(io.ktor.utils.io.ByteReadChannel r4, java.nio.charset.Charset r5, kotlin.coroutines.Continuation<? super java.lang.String> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.server.engine.DefaultTransformKt$readText$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.server.engine.DefaultTransformKt$readText$1 r0 = (io.ktor.server.engine.DefaultTransformKt$readText$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.server.engine.DefaultTransformKt$readText$1 r0 = new io.ktor.server.engine.DefaultTransformKt$readText$1
            r0.<init>(r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            java.lang.Object r4 = r0.L$0
            r5 = r4
            java.nio.charset.Charset r5 = (java.nio.charset.Charset) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004a
        L_0x002f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r5
            r0.label = r3
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.lang.Object r6 = r4.readRemaining(r2, r0)
            if (r6 != r1) goto L_0x004a
            return r1
        L_0x004a:
            io.ktor.utils.io.core.ByteReadPacket r6 = (io.ktor.utils.io.core.ByteReadPacket) r6
            boolean r4 = r6.getEndOfInput()
            if (r4 == 0) goto L_0x0055
            java.lang.String r4 = ""
            return r4
        L_0x0055:
            java.nio.charset.Charset r4 = kotlin.text.Charsets.UTF_8     // Catch:{ all -> 0x0079 }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)     // Catch:{ all -> 0x0079 }
            if (r4 != 0) goto L_0x006b
            java.nio.charset.Charset r4 = kotlin.text.Charsets.ISO_8859_1     // Catch:{ all -> 0x0079 }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r4)     // Catch:{ all -> 0x0079 }
            if (r4 == 0) goto L_0x0066
            goto L_0x006b
        L_0x0066:
            java.lang.String r4 = io.ktor.server.engine.DefaultTransformJvmKt.readTextWithCustomCharset(r6, r5)     // Catch:{ all -> 0x0079 }
            goto L_0x0075
        L_0x006b:
            r4 = r6
            io.ktor.utils.io.core.Input r4 = (io.ktor.utils.io.core.Input) r4     // Catch:{ all -> 0x0079 }
            r5 = 3
            r0 = 0
            r1 = 0
            java.lang.String r4 = io.ktor.utils.io.core.Input.readText$default(r4, r1, r1, r5, r0)     // Catch:{ all -> 0x0079 }
        L_0x0075:
            r6.release()
            return r4
        L_0x0079:
            r4 = move-exception
            r6.release()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.DefaultTransformKt.readText(io.ktor.utils.io.ByteReadChannel, java.nio.charset.Charset, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
