package io.ktor.server.http.content;

import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.util.StringValuesKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/http/Headers;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PreCompressed.kt */
final class PreCompressedResponse$headers$2 extends Lambda implements Function0<Headers> {
    final /* synthetic */ PreCompressedResponse this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreCompressedResponse$headers$2(PreCompressedResponse preCompressedResponse) {
        super(0);
        this.this$0 = preCompressedResponse;
    }

    public final Headers invoke() {
        if (this.this$0.encoding == null) {
            return this.this$0.original.getHeaders();
        }
        Headers.Companion companion = Headers.Companion;
        PreCompressedResponse preCompressedResponse = this.this$0;
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, (DefaultConstructorMarker) null);
        StringValuesKt.appendFiltered$default(headersBuilder, preCompressedResponse.original.getHeaders(), false, PreCompressedResponse$headers$2$1$1.INSTANCE, 2, (Object) null);
        headersBuilder.append(HttpHeaders.INSTANCE.getContentEncoding(), preCompressedResponse.encoding);
        return headersBuilder.build();
    }
}
