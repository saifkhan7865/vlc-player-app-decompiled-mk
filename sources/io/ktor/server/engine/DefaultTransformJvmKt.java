package io.ktor.server.engine;

import io.ktor.http.HttpHeaders;
import io.ktor.http.cio.CIOMultipartDataBase;
import io.ktor.http.content.MultiPartData;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationCallKt;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.jvm.javaio.BlockingKt;
import io.ktor.utils.io.streams.StreamsKt;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a+\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a\u001e\u0010\n\u001a\u00020\u000b*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\f\u001a\u00020\u0003H\u0000\u001a\u0018\u0010\r\u001a\u00020\u000e*\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012H\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"receiveGuardedInputStream", "Ljava/io/InputStream;", "channel", "Lio/ktor/utils/io/ByteReadChannel;", "defaultPlatformTransformations", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "query", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "multiPartData", "Lio/ktor/http/content/MultiPartData;", "rc", "readTextWithCustomCharset", "", "Lio/ktor/utils/io/core/ByteReadPacket;", "charset", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultTransformJvm.kt */
public final class DefaultTransformJvmKt {
    public static final Object defaultPlatformTransformations(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, Continuation<Object> continuation) {
        ByteReadChannel byteReadChannel = obj instanceof ByteReadChannel ? (ByteReadChannel) obj : null;
        if (byteReadChannel == null) {
            return null;
        }
        KClass<?> type = ApplicationCallKt.getReceiveType(pipelineContext.getContext()).getType();
        if (Intrinsics.areEqual((Object) type, (Object) Reflection.getOrCreateKotlinClass(InputStream.class))) {
            return receiveGuardedInputStream(byteReadChannel);
        }
        if (Intrinsics.areEqual((Object) type, (Object) Reflection.getOrCreateKotlinClass(MultiPartData.class))) {
            return multiPartData(pipelineContext, byteReadChannel);
        }
        return null;
    }

    public static final String readTextWithCustomCharset(ByteReadPacket byteReadPacket, Charset charset) {
        Intrinsics.checkNotNullParameter(byteReadPacket, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return TextStreamsKt.readText(new InputStreamReader(StreamsKt.inputStream(byteReadPacket), charset));
    }

    private static final InputStream receiveGuardedInputStream(ByteReadChannel byteReadChannel) {
        return BlockingKt.toInputStream$default(byteReadChannel, (Job) null, 1, (Object) null);
    }

    public static final MultiPartData multiPartData(PipelineContext<?, ApplicationCall> pipelineContext, ByteReadChannel byteReadChannel) {
        Intrinsics.checkNotNullParameter(pipelineContext, "<this>");
        Intrinsics.checkNotNullParameter(byteReadChannel, "rc");
        String header = ApplicationRequestPropertiesKt.header(pipelineContext.getContext().getRequest(), HttpHeaders.INSTANCE.getContentType());
        if (header != null) {
            String header2 = ApplicationRequestPropertiesKt.header(pipelineContext.getContext().getRequest(), HttpHeaders.INSTANCE.getContentLength());
            return new CIOMultipartDataBase(pipelineContext.getCoroutineContext().plus(Dispatchers.getUnconfined()), byteReadChannel, header, header2 != null ? Long.valueOf(Long.parseLong(header2)) : null, 0, 0, 48, (DefaultConstructorMarker) null);
        }
        throw new IllegalStateException("Content-Type header is required for multipart processing");
    }
}
