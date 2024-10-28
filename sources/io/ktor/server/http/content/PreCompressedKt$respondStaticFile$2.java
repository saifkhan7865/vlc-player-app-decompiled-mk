package io.ktor.server.http.content;

import io.ktor.http.ContentType;
import io.ktor.http.FileContentTypeJvmKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/http/ContentType;", "it", "Ljava/io/File;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PreCompressed.kt */
final class PreCompressedKt$respondStaticFile$2 extends Lambda implements Function1<File, ContentType> {
    public static final PreCompressedKt$respondStaticFile$2 INSTANCE = new PreCompressedKt$respondStaticFile$2();

    PreCompressedKt$respondStaticFile$2() {
        super(1);
    }

    public final ContentType invoke(File file) {
        Intrinsics.checkNotNullParameter(file, "it");
        return FileContentTypeJvmKt.defaultForFile(ContentType.Companion, file);
    }
}
