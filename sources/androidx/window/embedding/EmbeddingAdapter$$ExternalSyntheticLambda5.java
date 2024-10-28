package androidx.window.embedding;

import android.util.Pair;
import androidx.window.extensions.core.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EmbeddingAdapter$$ExternalSyntheticLambda5 implements Predicate {
    public final /* synthetic */ SplitPairRule f$0;

    public /* synthetic */ EmbeddingAdapter$$ExternalSyntheticLambda5(SplitPairRule splitPairRule) {
        this.f$0 = splitPairRule;
    }

    public final boolean test(Object obj) {
        return EmbeddingAdapter.translateSplitPairRule$lambda$5(this.f$0, (Pair) obj);
    }
}
