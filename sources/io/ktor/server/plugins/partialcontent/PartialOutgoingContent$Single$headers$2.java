package io.ktor.server.plugins.partialcontent;

import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.plugins.partialcontent.PartialOutgoingContent;
import io.ktor.server.response.ApplicationResponsePropertiesKt;
import io.ktor.util.StringValuesKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/http/Headers;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PartialOutgoingContent.kt */
final class PartialOutgoingContent$Single$headers$2 extends Lambda implements Function0<Headers> {
    final /* synthetic */ OutgoingContent.ReadChannelContent $original;
    final /* synthetic */ PartialOutgoingContent.Single this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PartialOutgoingContent$Single$headers$2(OutgoingContent.ReadChannelContent readChannelContent, PartialOutgoingContent.Single single) {
        super(0);
        this.$original = readChannelContent;
        this.this$0 = single;
    }

    public final Headers invoke() {
        Headers.Companion companion = Headers.Companion;
        OutgoingContent.ReadChannelContent readChannelContent = this.$original;
        PartialOutgoingContent.Single single = this.this$0;
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, (DefaultConstructorMarker) null);
        StringValuesKt.appendFiltered$default(headersBuilder, readChannelContent.getHeaders(), false, PartialOutgoingContent$Single$headers$2$1$1.INSTANCE, 2, (Object) null);
        single.acceptRanges(headersBuilder);
        ApplicationResponsePropertiesKt.contentRange$default(headersBuilder, single.getRange(), Long.valueOf(single.getFullLength()), (String) null, 4, (Object) null);
        return headersBuilder.build();
    }
}
