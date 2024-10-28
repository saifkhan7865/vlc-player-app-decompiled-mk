package io.ktor.server.engine;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/ApplicationCall;", "invoke", "(Lio/ktor/server/application/ApplicationCall;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ShutDownUrl.kt */
final class ShutDownUrl$Config$exitCodeSupplier$1 extends Lambda implements Function1<ApplicationCall, Integer> {
    public static final ShutDownUrl$Config$exitCodeSupplier$1 INSTANCE = new ShutDownUrl$Config$exitCodeSupplier$1();

    ShutDownUrl$Config$exitCodeSupplier$1() {
        super(1);
    }

    public final Integer invoke(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, "$this$null");
        return 0;
    }
}
