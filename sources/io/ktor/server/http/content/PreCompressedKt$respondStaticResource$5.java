package io.ktor.server.http.content;

import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Ljava/net/URL;", "invoke", "(Ljava/net/URL;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PreCompressed.kt */
final class PreCompressedKt$respondStaticResource$5 extends Lambda implements Function1<URL, Boolean> {
    public static final PreCompressedKt$respondStaticResource$5 INSTANCE = new PreCompressedKt$respondStaticResource$5();

    PreCompressedKt$respondStaticResource$5() {
        super(1);
    }

    public final Boolean invoke(URL url) {
        Intrinsics.checkNotNullParameter(url, "it");
        return false;
    }
}
