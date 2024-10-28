package io.ktor.server.plugins.conditionalheaders;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ConditionalHeaders.kt */
/* synthetic */ class ConditionalHeadersKt$ConditionalHeaders$1 extends FunctionReferenceImpl implements Function0<ConditionalHeadersConfig> {
    public static final ConditionalHeadersKt$ConditionalHeaders$1 INSTANCE = new ConditionalHeadersKt$ConditionalHeaders$1();

    ConditionalHeadersKt$ConditionalHeaders$1() {
        super(0, ConditionalHeadersConfig.class, "<init>", "<init>()V", 0);
    }

    public final ConditionalHeadersConfig invoke() {
        return new ConditionalHeadersConfig();
    }
}
