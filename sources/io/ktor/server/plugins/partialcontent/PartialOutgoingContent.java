package io.ktor.server.plugins.partialcontent;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.RangeUnits;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.AttributeKey;
import io.ktor.utils.io.ByteReadChannel;
import io.netty.handler.codec.http.HttpHeaders;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0003\u001a\u001b\u001cB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J'\u0010\u000e\u001a\u0004\u0018\u0001H\u000f\"\b\b\u0000\u0010\u000f*\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0012H\u0016¢\u0006\u0002\u0010\u0013J/\u0010\u0014\u001a\u00020\u0015\"\b\b\u0000\u0010\u000f*\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00122\b\u0010\u0016\u001a\u0004\u0018\u0001H\u000fH\u0016¢\u0006\u0002\u0010\u0017J\f\u0010\u0018\u001a\u00020\u0015*\u00020\u0019H\u0004R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00058VX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u0004\u0018\u00010\u000b8VX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r\u0001\u0003\u001d\u001e\u001f¨\u0006 "}, d2 = {"Lio/ktor/server/plugins/partialcontent/PartialOutgoingContent;", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "original", "(Lio/ktor/http/content/OutgoingContent$ReadChannelContent;)V", "contentType", "Lio/ktor/http/ContentType;", "getContentType", "()Lio/ktor/http/ContentType;", "getOriginal", "()Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "status", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "getProperty", "T", "", "key", "Lio/ktor/util/AttributeKey;", "(Lio/ktor/util/AttributeKey;)Ljava/lang/Object;", "setProperty", "", "value", "(Lio/ktor/util/AttributeKey;Ljava/lang/Object;)V", "acceptRanges", "Lio/ktor/http/HeadersBuilder;", "Bypass", "Multiple", "Single", "Lio/ktor/server/plugins/partialcontent/PartialOutgoingContent$Bypass;", "Lio/ktor/server/plugins/partialcontent/PartialOutgoingContent$Multiple;", "Lio/ktor/server/plugins/partialcontent/PartialOutgoingContent$Single;", "ktor-server-partial-content"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PartialOutgoingContent.kt */
public abstract class PartialOutgoingContent extends OutgoingContent.ReadChannelContent {
    private final OutgoingContent.ReadChannelContent original;

    public /* synthetic */ PartialOutgoingContent(OutgoingContent.ReadChannelContent readChannelContent, DefaultConstructorMarker defaultConstructorMarker) {
        this(readChannelContent);
    }

    private PartialOutgoingContent(OutgoingContent.ReadChannelContent readChannelContent) {
        this.original = readChannelContent;
    }

    public final OutgoingContent.ReadChannelContent getOriginal() {
        return this.original;
    }

    public HttpStatusCode getStatus() {
        return this.original.getStatus();
    }

    public ContentType getContentType() {
        return this.original.getContentType();
    }

    public <T> T getProperty(AttributeKey<T> attributeKey) {
        Intrinsics.checkNotNullParameter(attributeKey, LeanbackPreferenceDialogFragment.ARG_KEY);
        return this.original.getProperty(attributeKey);
    }

    public <T> void setProperty(AttributeKey<T> attributeKey, T t) {
        Intrinsics.checkNotNullParameter(attributeKey, LeanbackPreferenceDialogFragment.ARG_KEY);
        this.original.setProperty(attributeKey, t);
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8VX\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0011"}, d2 = {"Lio/ktor/server/plugins/partialcontent/PartialOutgoingContent$Bypass;", "Lio/ktor/server/plugins/partialcontent/PartialOutgoingContent;", "original", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "(Lio/ktor/http/content/OutgoingContent$ReadChannelContent;)V", "contentLength", "", "getContentLength", "()Ljava/lang/Long;", "headers", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "headers$delegate", "Lkotlin/Lazy;", "readFrom", "Lio/ktor/utils/io/ByteReadChannel;", "ktor-server-partial-content"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PartialOutgoingContent.kt */
    public static final class Bypass extends PartialOutgoingContent {
        private final Lazy headers$delegate;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Bypass(OutgoingContent.ReadChannelContent readChannelContent) {
            super(readChannelContent, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(readChannelContent, "original");
            this.headers$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new PartialOutgoingContent$Bypass$headers$2(readChannelContent, this));
        }

        public Long getContentLength() {
            return getOriginal().getContentLength();
        }

        public ByteReadChannel readFrom() {
            return getOriginal().readFrom();
        }

        public Headers getHeaders() {
            return (Headers) this.headers$delegate.getValue();
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u0014\u0010\u000b\u001a\u00020\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0012\u001a\u00020\u00138VX\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u001b8VX\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006 "}, d2 = {"Lio/ktor/server/plugins/partialcontent/PartialOutgoingContent$Single;", "Lio/ktor/server/plugins/partialcontent/PartialOutgoingContent;", "get", "", "original", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "range", "Lkotlin/ranges/LongRange;", "fullLength", "", "(ZLio/ktor/http/content/OutgoingContent$ReadChannelContent;Lkotlin/ranges/LongRange;J)V", "contentLength", "getContentLength", "()Ljava/lang/Long;", "getFullLength", "()J", "getGet", "()Z", "headers", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "headers$delegate", "Lkotlin/Lazy;", "getRange", "()Lkotlin/ranges/LongRange;", "status", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "readFrom", "Lio/ktor/utils/io/ByteReadChannel;", "ktor-server-partial-content"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PartialOutgoingContent.kt */
    public static final class Single extends PartialOutgoingContent {
        private final long fullLength;
        private final boolean get;
        private final Lazy headers$delegate;
        private final LongRange range;

        public final boolean getGet() {
            return this.get;
        }

        public final LongRange getRange() {
            return this.range;
        }

        public final long getFullLength() {
            return this.fullLength;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Single(boolean z, OutgoingContent.ReadChannelContent readChannelContent, LongRange longRange, long j) {
            super(readChannelContent, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(readChannelContent, "original");
            Intrinsics.checkNotNullParameter(longRange, "range");
            this.get = z;
            this.range = longRange;
            this.fullLength = j;
            this.headers$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new PartialOutgoingContent$Single$headers$2(readChannelContent, this));
        }

        public HttpStatusCode getStatus() {
            return this.get ? HttpStatusCode.Companion.getPartialContent() : getOriginal().getStatus();
        }

        public Long getContentLength() {
            return Long.valueOf((this.range.getLast() - this.range.getFirst()) + 1);
        }

        public ByteReadChannel readFrom() {
            return getOriginal().readFrom(this.range);
        }

        public Headers getHeaders() {
            return (Headers) this.headers$delegate.getValue();
        }
    }

    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B;\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\b\u0010,\u001a\u00020-H\u0016R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178VX\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010\u001e\u001a\u00020\u001f8VX\u0002¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b \u0010!R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0016\u0010(\u001a\u0004\u0018\u00010)8VX\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+¨\u0006."}, d2 = {"Lio/ktor/server/plugins/partialcontent/PartialOutgoingContent$Multiple;", "Lio/ktor/server/plugins/partialcontent/PartialOutgoingContent;", "Lkotlinx/coroutines/CoroutineScope;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "get", "", "original", "Lio/ktor/http/content/OutgoingContent$ReadChannelContent;", "ranges", "", "Lkotlin/ranges/LongRange;", "length", "", "boundary", "", "(Lkotlin/coroutines/CoroutineContext;ZLio/ktor/http/content/OutgoingContent$ReadChannelContent;Ljava/util/List;JLjava/lang/String;)V", "getBoundary", "()Ljava/lang/String;", "contentLength", "getContentLength", "()Ljava/lang/Long;", "contentType", "Lio/ktor/http/ContentType;", "getContentType", "()Lio/ktor/http/ContentType;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "getGet", "()Z", "headers", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "headers$delegate", "Lkotlin/Lazy;", "getLength", "()J", "getRanges", "()Ljava/util/List;", "status", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "readFrom", "Lio/ktor/utils/io/ByteReadChannel;", "ktor-server-partial-content"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PartialOutgoingContent.kt */
    public static final class Multiple extends PartialOutgoingContent implements CoroutineScope {
        private final String boundary;
        private final long contentLength;
        private final CoroutineContext coroutineContext;
        private final boolean get;
        private final Lazy headers$delegate;
        private final long length;
        private final List<LongRange> ranges;

        public CoroutineContext getCoroutineContext() {
            return this.coroutineContext;
        }

        public final boolean getGet() {
            return this.get;
        }

        public final List<LongRange> getRanges() {
            return this.ranges;
        }

        public final long getLength() {
            return this.length;
        }

        public final String getBoundary() {
            return this.boundary;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Multiple(CoroutineContext coroutineContext2, boolean z, OutgoingContent.ReadChannelContent readChannelContent, List<LongRange> list, long j, String str) {
            super(readChannelContent, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(coroutineContext2, "coroutineContext");
            Intrinsics.checkNotNullParameter(readChannelContent, "original");
            Intrinsics.checkNotNullParameter(list, "ranges");
            Intrinsics.checkNotNullParameter(str, HttpHeaders.Values.BOUNDARY);
            this.coroutineContext = coroutineContext2;
            this.get = z;
            this.ranges = list;
            this.length = j;
            this.boundary = str;
            this.contentLength = MultipleRangeWriterKt.calculateMultipleRangesBodyLength(list, Long.valueOf(j), str, String.valueOf(readChannelContent.getContentType()));
            this.headers$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new PartialOutgoingContent$Multiple$headers$2(readChannelContent, this));
        }

        public HttpStatusCode getStatus() {
            return this.get ? HttpStatusCode.Companion.getPartialContent() : getOriginal().getStatus();
        }

        public Long getContentLength() {
            return Long.valueOf(this.contentLength);
        }

        public ContentType getContentType() {
            return ContentType.MultiPart.INSTANCE.getByteRanges().withParameter(HttpHeaders.Values.BOUNDARY, this.boundary);
        }

        public ByteReadChannel readFrom() {
            return MultipleRangeWriterKt.writeMultipleRangesImpl(this, new PartialOutgoingContent$Multiple$readFrom$1(this), this.ranges, Long.valueOf(this.length), this.boundary, String.valueOf(getOriginal().getContentType()));
        }

        public Headers getHeaders() {
            return (Headers) this.headers$delegate.getValue();
        }
    }

    /* access modifiers changed from: protected */
    public final void acceptRanges(HeadersBuilder headersBuilder) {
        Intrinsics.checkNotNullParameter(headersBuilder, "<this>");
        if (!headersBuilder.contains(io.ktor.http.HttpHeaders.INSTANCE.getAcceptRanges(), RangeUnits.Bytes.getUnitToken())) {
            headersBuilder.append(io.ktor.http.HttpHeaders.INSTANCE.getAcceptRanges(), RangeUnits.Bytes.getUnitToken());
        }
    }
}
