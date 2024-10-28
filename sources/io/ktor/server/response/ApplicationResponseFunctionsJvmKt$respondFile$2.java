package io.ktor.server.response;

import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/http/content/OutgoingContent;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationResponseFunctionsJvm.kt */
final class ApplicationResponseFunctionsJvmKt$respondFile$2 extends Lambda implements Function1<OutgoingContent, Unit> {
    public static final ApplicationResponseFunctionsJvmKt$respondFile$2 INSTANCE = new ApplicationResponseFunctionsJvmKt$respondFile$2();

    ApplicationResponseFunctionsJvmKt$respondFile$2() {
        super(1);
    }

    public final void invoke(OutgoingContent outgoingContent) {
        Intrinsics.checkNotNullParameter(outgoingContent, "$this$null");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((OutgoingContent) obj);
        return Unit.INSTANCE;
    }
}
