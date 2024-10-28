package io.ktor.server.http.content;

import io.ktor.http.content.OutgoingContent;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/server/http/content/CompressedResource;", "", "url", "Ljava/net/URL;", "content", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "compression", "Lio/ktor/server/http/content/CompressedFileType;", "(Ljava/net/URL;Lio/ktor/http/content/OutgoingContent$ReadChannelContent;Lio/ktor/server/http/content/CompressedFileType;)V", "getCompression", "()Lio/ktor/server/http/content/CompressedFileType;", "getContent", "()Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "getUrl", "()Ljava/net/URL;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PreCompressed.kt */
public final class CompressedResource {
    private final CompressedFileType compression;
    private final OutgoingContent.ReadChannelContent content;
    private final URL url;

    public CompressedResource(URL url2, OutgoingContent.ReadChannelContent readChannelContent, CompressedFileType compressedFileType) {
        Intrinsics.checkNotNullParameter(url2, RtspHeaders.Values.URL);
        Intrinsics.checkNotNullParameter(readChannelContent, "content");
        Intrinsics.checkNotNullParameter(compressedFileType, "compression");
        this.url = url2;
        this.content = readChannelContent;
        this.compression = compressedFileType;
    }

    public final URL getUrl() {
        return this.url;
    }

    public final OutgoingContent.ReadChannelContent getContent() {
        return this.content;
    }

    public final CompressedFileType getCompression() {
        return this.compression;
    }
}
