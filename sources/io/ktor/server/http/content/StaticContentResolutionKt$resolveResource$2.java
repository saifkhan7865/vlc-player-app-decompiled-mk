package io.ktor.server.http.content;

import io.ktor.http.ContentType;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/http/ContentType;", "it", "Ljava/net/URL;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: StaticContentResolution.kt */
final class StaticContentResolutionKt$resolveResource$2 extends Lambda implements Function1<URL, ContentType> {
    final /* synthetic */ Function1<String, ContentType> $mimeResolve;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StaticContentResolutionKt$resolveResource$2(Function1<? super String, ContentType> function1) {
        super(1);
        this.$mimeResolve = function1;
    }

    public final ContentType invoke(URL url) {
        Intrinsics.checkNotNullParameter(url, "it");
        Function1<String, ContentType> function1 = this.$mimeResolve;
        String path = url.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "it.path");
        return function1.invoke(StaticContentResolutionKt.extension(path));
    }
}
