package io.ktor.server.http.content;

import io.ktor.http.ContentType;
import io.ktor.http.FileContentTypeJvmKt;
import io.ktor.http.FileContentTypeKt;
import java.io.File;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Lio/ktor/http/ContentType;", "Resource", "", "it", "invoke", "(Ljava/lang/Object;)Lio/ktor/http/ContentType;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: StaticContent.kt */
final class StaticContentConfig$defaultContentType$1 extends Lambda implements Function1<Resource, ContentType> {
    public static final StaticContentConfig$defaultContentType$1 INSTANCE = new StaticContentConfig$defaultContentType$1();

    StaticContentConfig$defaultContentType$1() {
        super(1);
    }

    public final ContentType invoke(Resource resource) {
        Intrinsics.checkNotNullParameter(resource, "it");
        if (resource instanceof File) {
            return FileContentTypeJvmKt.defaultForFile(ContentType.Companion, (File) resource);
        }
        if (resource instanceof URL) {
            ContentType.Companion companion = ContentType.Companion;
            String path = ((URL) resource).getPath();
            Intrinsics.checkNotNullExpressionValue(path, "it.path");
            return FileContentTypeKt.defaultForFilePath(companion, path);
        }
        throw new IllegalArgumentException("Argument can be only of type File or URL, but was " + Reflection.getOrCreateKotlinClass(resource.getClass()));
    }
}
