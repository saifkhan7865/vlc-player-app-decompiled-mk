package io.ktor.client.plugins.cache;

import io.ktor.http.URLProtocol;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001aN\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u001a\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\f0\u0006H\u0000\u001a\f\u0010\r\u001a\u00020\u000e*\u00020\u000fH\u0002\"\u0018\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0010"}, d2 = {"LOGGER", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "getLOGGER", "()Lorg/slf4j/Logger;", "mergedHeadersLookup", "Lkotlin/Function1;", "", "content", "Lio/ktor/http/content/OutgoingContent;", "headerExtractor", "allHeadersExtractor", "", "canStore", "", "Lio/ktor/http/URLProtocol;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpCache.kt */
public final class HttpCacheKt {
    private static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpCache");

    public static final Logger getLOGGER() {
        return LOGGER;
    }

    public static final Function1<String, String> mergedHeadersLookup(OutgoingContent outgoingContent, Function1<? super String, String> function1, Function1<? super String, ? extends List<String>> function12) {
        Intrinsics.checkNotNullParameter(outgoingContent, "content");
        Intrinsics.checkNotNullParameter(function1, "headerExtractor");
        Intrinsics.checkNotNullParameter(function12, "allHeadersExtractor");
        return new HttpCacheKt$mergedHeadersLookup$1(outgoingContent, function1, function12);
    }

    /* access modifiers changed from: private */
    public static final boolean canStore(URLProtocol uRLProtocol) {
        return Intrinsics.areEqual((Object) uRLProtocol.getName(), (Object) "http") || Intrinsics.areEqual((Object) uRLProtocol.getName(), (Object) "https");
    }
}
