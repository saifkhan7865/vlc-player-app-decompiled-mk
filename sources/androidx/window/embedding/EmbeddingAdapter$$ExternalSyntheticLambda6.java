package androidx.window.embedding;

import android.content.Context;
import android.view.WindowMetrics;
import androidx.window.extensions.core.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EmbeddingAdapter$$ExternalSyntheticLambda6 implements Predicate {
    public final /* synthetic */ SplitPairRule f$0;
    public final /* synthetic */ Context f$1;

    public /* synthetic */ EmbeddingAdapter$$ExternalSyntheticLambda6(SplitPairRule splitPairRule, Context context) {
        this.f$0 = splitPairRule;
        this.f$1 = context;
    }

    public final boolean test(Object obj) {
        return EmbeddingAdapter.translateSplitPairRule$lambda$6(this.f$0, this.f$1, (WindowMetrics) obj);
    }
}
