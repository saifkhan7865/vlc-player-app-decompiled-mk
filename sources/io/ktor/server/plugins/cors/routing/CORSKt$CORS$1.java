package io.ktor.server.plugins.cors.routing;

import io.ktor.server.plugins.cors.CORSConfig;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CORS.kt */
/* synthetic */ class CORSKt$CORS$1 extends FunctionReferenceImpl implements Function0<CORSConfig> {
    public static final CORSKt$CORS$1 INSTANCE = new CORSKt$CORS$1();

    CORSKt$CORS$1() {
        super(0, CORSConfig.class, "<init>", "<init>()V", 0);
    }

    public final CORSConfig invoke() {
        return new CORSConfig();
    }
}
