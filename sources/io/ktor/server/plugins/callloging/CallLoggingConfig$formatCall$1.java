package io.ktor.server.plugins.callloging;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CallLoggingConfig.kt */
/* synthetic */ class CallLoggingConfig$formatCall$1 extends FunctionReferenceImpl implements Function1<ApplicationCall, String> {
    CallLoggingConfig$formatCall$1(Object obj) {
        super(1, obj, CallLoggingConfig.class, "defaultFormat", "defaultFormat(Lio/ktor/server/application/ApplicationCall;)Ljava/lang/String;", 0);
    }

    public final String invoke(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, "p0");
        return ((CallLoggingConfig) this.receiver).defaultFormat(applicationCall);
    }
}
