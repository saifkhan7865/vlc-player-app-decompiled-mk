package io.ktor.server.plugins.partialcontent;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PartialContent.kt */
/* synthetic */ class PartialContentKt$PartialContent$1 extends FunctionReferenceImpl implements Function0<PartialContentConfig> {
    public static final PartialContentKt$PartialContent$1 INSTANCE = new PartialContentKt$PartialContent$1();

    PartialContentKt$PartialContent$1() {
        super(0, PartialContentConfig.class, "<init>", "<init>()V", 0);
    }

    public final PartialContentConfig invoke() {
        return new PartialContentConfig();
    }
}
