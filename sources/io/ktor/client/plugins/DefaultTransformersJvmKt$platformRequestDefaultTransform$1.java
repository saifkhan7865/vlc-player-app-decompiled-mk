package io.ktor.client.plugins;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.ContentType;
import io.ktor.http.HttpHeaders;
import io.ktor.http.content.OutgoingContent;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.jvm.javaio.ReadingKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;

@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"io/ktor/client/plugins/DefaultTransformersJvmKt$platformRequestDefaultTransform$1", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "contentLength", "", "getContentLength", "()Ljava/lang/Long;", "Ljava/lang/Long;", "contentType", "Lio/ktor/http/ContentType;", "getContentType", "()Lio/ktor/http/ContentType;", "readFrom", "Lio/ktor/utils/io/ByteReadChannel;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultTransformersJvm.kt */
public final class DefaultTransformersJvmKt$platformRequestDefaultTransform$1 extends OutgoingContent.ReadChannelContent {
    final /* synthetic */ Object $body;
    private final Long contentLength;
    private final ContentType contentType;

    DefaultTransformersJvmKt$platformRequestDefaultTransform$1(HttpRequestBuilder httpRequestBuilder, ContentType contentType2, Object obj) {
        this.$body = obj;
        String str = httpRequestBuilder.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
        this.contentLength = str != null ? Long.valueOf(Long.parseLong(str)) : null;
        this.contentType = contentType2 == null ? ContentType.Application.INSTANCE.getOctetStream() : contentType2;
    }

    public Long getContentLength() {
        return this.contentLength;
    }

    public ContentType getContentType() {
        return this.contentType;
    }

    public ByteReadChannel readFrom() {
        return ReadingKt.toByteReadChannelWithArrayPool$default((InputStream) this.$body, (CoroutineContext) null, (ObjectPool) null, 3, (Object) null);
    }
}
