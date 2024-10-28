package io.ktor.server.http.content;

import io.ktor.http.ContentType;
import io.ktor.http.FileContentTypeKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/http/ContentType;", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: StaticContentResolution.kt */
final class StaticContentResolutionKt$resolveResource$1 extends Lambda implements Function1<String, ContentType> {
    public static final StaticContentResolutionKt$resolveResource$1 INSTANCE = new StaticContentResolutionKt$resolveResource$1();

    StaticContentResolutionKt$resolveResource$1() {
        super(1);
    }

    public final ContentType invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        return FileContentTypeKt.defaultForFileExtension(ContentType.Companion, str);
    }
}
