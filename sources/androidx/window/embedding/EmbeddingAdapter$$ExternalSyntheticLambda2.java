package androidx.window.embedding;

import android.content.Context;
import android.view.WindowMetrics;
import androidx.window.extensions.core.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EmbeddingAdapter$$ExternalSyntheticLambda2 implements Predicate {
    public final /* synthetic */ SplitPlaceholderRule f$0;
    public final /* synthetic */ Context f$1;

    public /* synthetic */ EmbeddingAdapter$$ExternalSyntheticLambda2(SplitPlaceholderRule splitPlaceholderRule, Context context) {
        this.f$0 = splitPlaceholderRule;
        this.f$1 = context;
    }

    public final boolean test(Object obj) {
        return EmbeddingAdapter.translateSplitPlaceholderRule$lambda$11(this.f$0, this.f$1, (WindowMetrics) obj);
    }
}
