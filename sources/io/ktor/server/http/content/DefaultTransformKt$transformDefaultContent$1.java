package io.ktor.server.http.content;

import io.ktor.http.content.OutgoingContent;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"io/ktor/server/http/content/DefaultTransformKt$transformDefaultContent$1", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "readFrom", "Lio/ktor/utils/io/ByteReadChannel;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultTransform.kt */
public final class DefaultTransformKt$transformDefaultContent$1 extends OutgoingContent.ReadChannelContent {
    final /* synthetic */ Object $value;

    DefaultTransformKt$transformDefaultContent$1(Object obj) {
        this.$value = obj;
    }

    public ByteReadChannel readFrom() {
        return (ByteReadChannel) this.$value;
    }
}
