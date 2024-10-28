package androidx.window.embedding;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: EmbeddingBackend.kt */
/* synthetic */ class EmbeddingBackend$Companion$overrideDecorator$1 extends FunctionReferenceImpl implements Function1<EmbeddingBackend, EmbeddingBackend> {
    EmbeddingBackend$Companion$overrideDecorator$1(Object obj) {
        super(1, obj, EmbeddingBackendDecorator.class, "decorate", "decorate(Landroidx/window/embedding/EmbeddingBackend;)Landroidx/window/embedding/EmbeddingBackend;", 0);
    }

    public final EmbeddingBackend invoke(EmbeddingBackend embeddingBackend) {
        Intrinsics.checkNotNullParameter(embeddingBackend, "p0");
        return ((EmbeddingBackendDecorator) this.receiver).decorate(embeddingBackend);
    }
}
