package io.ktor.server.http.content;

import io.netty.handler.codec.rtsp.RtspHeaders;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "url", "Ljava/io/File;", "invoke", "(Ljava/io/File;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: SinglePageApplication.kt */
final class SinglePageApplicationKt$singlePageApplication$3$1$1 extends Lambda implements Function1<File, Boolean> {
    final /* synthetic */ Function1<String, Boolean> $ignoreConfig;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SinglePageApplicationKt$singlePageApplication$3$1$1(Function1<? super String, Boolean> function1) {
        super(1);
        this.$ignoreConfig = function1;
    }

    public final Boolean invoke(File file) {
        Intrinsics.checkNotNullParameter(file, RtspHeaders.Values.URL);
        Function1<String, Boolean> function1 = this.$ignoreConfig;
        String path = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "url.path");
        return function1.invoke(path);
    }
}
