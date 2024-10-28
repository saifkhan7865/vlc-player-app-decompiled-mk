package io.ktor.server.engine;

import io.ktor.server.engine.ShutDownUrl;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ShutDownUrl.kt */
/* synthetic */ class ShutDownUrl$Companion$ApplicationCallPlugin$1 extends FunctionReferenceImpl implements Function0<ShutDownUrl.Config> {
    public static final ShutDownUrl$Companion$ApplicationCallPlugin$1 INSTANCE = new ShutDownUrl$Companion$ApplicationCallPlugin$1();

    ShutDownUrl$Companion$ApplicationCallPlugin$1() {
        super(0, ShutDownUrl.Config.class, "<init>", "<init>()V", 0);
    }

    public final ShutDownUrl.Config invoke() {
        return new ShutDownUrl.Config();
    }
}
