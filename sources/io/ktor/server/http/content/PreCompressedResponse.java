package io.ktor.server.http.content;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.AttributeKey;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J'\u0010\u0018\u001a\u0004\u0018\u0001H\u0019\"\b\b\u0000\u0010\u0019*\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00190\u001cH\u0016¢\u0006\u0002\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J/\u0010\"\u001a\u00020#\"\b\b\u0000\u0010\u0019*\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00190\u001c2\b\u0010$\u001a\u0004\u0018\u0001H\u0019H\u0016¢\u0006\u0002\u0010%R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u0004\u0018\u00010\u000b8VX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8VX\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u00158VX\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006&"}, d2 = {"Lio/ktor/server/http/content/PreCompressedResponse;", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "original", "encoding", "", "(Lio/ktor/http/content/OutgoingContent$ReadChannelContent;Ljava/lang/String;)V", "contentLength", "", "getContentLength", "()Ljava/lang/Long;", "contentType", "Lio/ktor/http/ContentType;", "getContentType", "()Lio/ktor/http/ContentType;", "headers", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "headers$delegate", "Lkotlin/Lazy;", "status", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "getProperty", "T", "", "key", "Lio/ktor/util/AttributeKey;", "(Lio/ktor/util/AttributeKey;)Ljava/lang/Object;", "readFrom", "Lio/ktor/utils/io/ByteReadChannel;", "range", "Lkotlin/ranges/LongRange;", "setProperty", "", "value", "(Lio/ktor/util/AttributeKey;Ljava/lang/Object;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PreCompressed.kt */
public final class PreCompressedResponse extends OutgoingContent.ReadChannelContent {
    /* access modifiers changed from: private */
    public final String encoding;
    private final Lazy headers$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new PreCompressedResponse$headers$2(this));
    /* access modifiers changed from: private */
    public final OutgoingContent.ReadChannelContent original;

    public PreCompressedResponse(OutgoingContent.ReadChannelContent readChannelContent, String str) {
        Intrinsics.checkNotNullParameter(readChannelContent, "original");
        this.original = readChannelContent;
        this.encoding = str;
    }

    public Long getContentLength() {
        return this.original.getContentLength();
    }

    public ContentType getContentType() {
        return this.original.getContentType();
    }

    public HttpStatusCode getStatus() {
        return this.original.getStatus();
    }

    public ByteReadChannel readFrom() {
        return this.original.readFrom();
    }

    public ByteReadChannel readFrom(LongRange longRange) {
        Intrinsics.checkNotNullParameter(longRange, "range");
        return this.original.readFrom(longRange);
    }

    public Headers getHeaders() {
        return (Headers) this.headers$delegate.getValue();
    }

    public <T> T getProperty(AttributeKey<T> attributeKey) {
        Intrinsics.checkNotNullParameter(attributeKey, LeanbackPreferenceDialogFragment.ARG_KEY);
        return this.original.getProperty(attributeKey);
    }

    public <T> void setProperty(AttributeKey<T> attributeKey, T t) {
        Intrinsics.checkNotNullParameter(attributeKey, LeanbackPreferenceDialogFragment.ARG_KEY);
        this.original.setProperty(attributeKey, t);
    }
}
