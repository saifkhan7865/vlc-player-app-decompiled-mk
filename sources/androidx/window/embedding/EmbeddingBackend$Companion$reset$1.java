package androidx.window.embedding;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/window/embedding/EmbeddingBackend;", "it", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: EmbeddingBackend.kt */
final class EmbeddingBackend$Companion$reset$1 extends Lambda implements Function1<EmbeddingBackend, EmbeddingBackend> {
    public static final EmbeddingBackend$Companion$reset$1 INSTANCE = new EmbeddingBackend$Companion$reset$1();

    EmbeddingBackend$Companion$reset$1() {
        super(1);
    }

    public final EmbeddingBackend invoke(EmbeddingBackend embeddingBackend) {
        Intrinsics.checkNotNullParameter(embeddingBackend, "it");
        return embeddingBackend;
    }
}
