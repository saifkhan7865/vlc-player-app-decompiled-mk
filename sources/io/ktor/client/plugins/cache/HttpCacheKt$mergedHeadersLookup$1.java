package io.ktor.client.plugins.cache;

import io.ktor.client.engine.UtilsKt;
import io.ktor.http.ContentType;
import io.ktor.http.HttpHeaders;
import io.ktor.http.content.OutgoingContent;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "header", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpCache.kt */
final class HttpCacheKt$mergedHeadersLookup$1 extends Lambda implements Function1<String, String> {
    final /* synthetic */ Function1<String, List<String>> $allHeadersExtractor;
    final /* synthetic */ OutgoingContent $content;
    final /* synthetic */ Function1<String, String> $headerExtractor;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HttpCacheKt$mergedHeadersLookup$1(OutgoingContent outgoingContent, Function1<? super String, String> function1, Function1<? super String, ? extends List<String>> function12) {
        super(1);
        this.$content = outgoingContent;
        this.$headerExtractor = function1;
        this.$allHeadersExtractor = function12;
    }

    public final String invoke(String str) {
        String contentType;
        Intrinsics.checkNotNullParameter(str, "header");
        if (Intrinsics.areEqual((Object) str, (Object) HttpHeaders.INSTANCE.getContentLength())) {
            Long contentLength = this.$content.getContentLength();
            if (contentLength == null || (contentType = contentLength.toString()) == null) {
                return "";
            }
        } else if (Intrinsics.areEqual((Object) str, (Object) HttpHeaders.INSTANCE.getContentType())) {
            ContentType contentType2 = this.$content.getContentType();
            if (contentType2 == null || (contentType = contentType2.toString()) == null) {
                return "";
            }
        } else if (Intrinsics.areEqual((Object) str, (Object) HttpHeaders.INSTANCE.getUserAgent())) {
            String str2 = this.$content.getHeaders().get(HttpHeaders.INSTANCE.getUserAgent());
            if (str2 != null) {
                return str2;
            }
            String invoke = this.$headerExtractor.invoke(HttpHeaders.INSTANCE.getUserAgent());
            if (invoke == null) {
                return UtilsKt.getKTOR_DEFAULT_USER_AGENT();
            }
            return invoke;
        } else {
            List<String> all = this.$content.getHeaders().getAll(str);
            if (all == null && (all = this.$allHeadersExtractor.invoke(str)) == null) {
                all = CollectionsKt.emptyList();
            }
            return CollectionsKt.joinToString$default(all, ";", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        }
        return contentType;
    }
}
