package androidx.window.embedding;

import androidx.window.embedding.EmbeddingInterfaceCompat;
import androidx.window.extensions.core.util.function.Consumer;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EmbeddingCompat$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ EmbeddingInterfaceCompat.EmbeddingCallbackInterface f$0;
    public final /* synthetic */ EmbeddingCompat f$1;

    public /* synthetic */ EmbeddingCompat$$ExternalSyntheticLambda0(EmbeddingInterfaceCompat.EmbeddingCallbackInterface embeddingCallbackInterface, EmbeddingCompat embeddingCompat) {
        this.f$0 = embeddingCallbackInterface;
        this.f$1 = embeddingCompat;
    }

    public final void accept(Object obj) {
        EmbeddingCompat.setEmbeddingCallback$lambda$0(this.f$0, this.f$1, (List) obj);
    }
}
