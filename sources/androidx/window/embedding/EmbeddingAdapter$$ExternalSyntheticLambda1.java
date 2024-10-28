package androidx.window.embedding;

import android.content.Intent;
import androidx.window.extensions.core.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EmbeddingAdapter$$ExternalSyntheticLambda1 implements Predicate {
    public final /* synthetic */ SplitPlaceholderRule f$0;

    public /* synthetic */ EmbeddingAdapter$$ExternalSyntheticLambda1(SplitPlaceholderRule splitPlaceholderRule) {
        this.f$0 = splitPlaceholderRule;
    }

    public final boolean test(Object obj) {
        return EmbeddingAdapter.translateSplitPlaceholderRule$lambda$10(this.f$0, (Intent) obj);
    }
}
