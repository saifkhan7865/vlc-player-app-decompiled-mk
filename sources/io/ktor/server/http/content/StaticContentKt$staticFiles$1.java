package io.ktor.server.http.content;

import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lio/ktor/server/http/content/StaticContentConfig;", "Ljava/io/File;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: StaticContent.kt */
final class StaticContentKt$staticFiles$1 extends Lambda implements Function1<StaticContentConfig<File>, Unit> {
    public static final StaticContentKt$staticFiles$1 INSTANCE = new StaticContentKt$staticFiles$1();

    StaticContentKt$staticFiles$1() {
        super(1);
    }

    public final void invoke(StaticContentConfig<File> staticContentConfig) {
        Intrinsics.checkNotNullParameter(staticContentConfig, "$this$null");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((StaticContentConfig<File>) (StaticContentConfig) obj);
        return Unit.INSTANCE;
    }
}
