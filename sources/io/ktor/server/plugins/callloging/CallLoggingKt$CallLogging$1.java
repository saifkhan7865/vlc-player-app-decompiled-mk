package io.ktor.server.plugins.callloging;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CallLogging.kt */
/* synthetic */ class CallLoggingKt$CallLogging$1 extends FunctionReferenceImpl implements Function0<CallLoggingConfig> {
    public static final CallLoggingKt$CallLogging$1 INSTANCE = new CallLoggingKt$CallLogging$1();

    CallLoggingKt$CallLogging$1() {
        super(0, CallLoggingConfig.class, "<init>", "<init>()V", 0);
    }

    public final CallLoggingConfig invoke() {
        return new CallLoggingConfig();
    }
}
