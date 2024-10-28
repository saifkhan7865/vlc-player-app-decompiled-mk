package io.ktor.server.http.content;

import io.ktor.http.CacheControl;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Lio/ktor/http/CacheControl;", "it", "Ljava/io/File;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PreCompressed.kt */
final class PreCompressedKt$respondStaticFile$3 extends Lambda implements Function1<File, List<? extends CacheControl>> {
    public static final PreCompressedKt$respondStaticFile$3 INSTANCE = new PreCompressedKt$respondStaticFile$3();

    PreCompressedKt$respondStaticFile$3() {
        super(1);
    }

    public final List<CacheControl> invoke(File file) {
        Intrinsics.checkNotNullParameter(file, "it");
        return CollectionsKt.emptyList();
    }
}
