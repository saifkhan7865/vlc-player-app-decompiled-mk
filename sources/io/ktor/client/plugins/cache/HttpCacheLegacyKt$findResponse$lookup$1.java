package io.ktor.client.plugins.cache;

import io.ktor.http.HeadersBuilder;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpCacheLegacy.kt */
/* synthetic */ class HttpCacheLegacyKt$findResponse$lookup$1 extends FunctionReferenceImpl implements Function1<String, String> {
    HttpCacheLegacyKt$findResponse$lookup$1(Object obj) {
        super(1, obj, HeadersBuilder.class, "get", "get(Ljava/lang/String;)Ljava/lang/String;", 0);
    }

    public final String invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "p0");
        return ((HeadersBuilder) this.receiver).get(str);
    }
}
