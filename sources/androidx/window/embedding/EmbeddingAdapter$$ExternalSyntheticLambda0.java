package androidx.window.embedding;

import android.app.Activity;
import androidx.window.extensions.core.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EmbeddingAdapter$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ SplitPlaceholderRule f$0;

    public /* synthetic */ EmbeddingAdapter$$ExternalSyntheticLambda0(SplitPlaceholderRule splitPlaceholderRule) {
        this.f$0 = splitPlaceholderRule;
    }

    public final boolean test(Object obj) {
        return EmbeddingAdapter.translateSplitPlaceholderRule$lambda$8(this.f$0, (Activity) obj);
    }
}