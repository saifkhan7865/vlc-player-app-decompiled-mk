package io.ktor.server.plugins.partialcontent;

import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.plugins.partialcontent.PartialOutgoingContent;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/http/Headers;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PartialOutgoingContent.kt */
final class PartialOutgoingContent$Bypass$headers$2 extends Lambda implements Function0<Headers> {
    final /* synthetic */ OutgoingContent.ReadChannelContent $original;
    final /* synthetic */ PartialOutgoingContent.Bypass this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PartialOutgoingContent$Bypass$headers$2(OutgoingContent.ReadChannelContent readChannelContent, PartialOutgoingContent.Bypass bypass) {
        super(0);
        this.$original = readChannelContent;
        this.this$0 = bypass;
    }

    public final Headers invoke() {
        Headers.Companion companion = Headers.Companion;
        OutgoingContent.ReadChannelContent readChannelContent = this.$original;
        PartialOutgoingContent.Bypass bypass = this.this$0;
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, (DefaultConstructorMarker) null);
        headersBuilder.appendAll(readChannelContent.getHeaders());
        bypass.acceptRanges(headersBuilder);
        return headersBuilder.build();
    }
}
