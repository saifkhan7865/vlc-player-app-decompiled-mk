package androidx.window.embedding;

import android.content.Intent;
import androidx.window.extensions.core.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EmbeddingAdapter$$ExternalSyntheticLambda8 implements Predicate {
    public final /* synthetic */ ActivityRule f$0;

    public /* synthetic */ EmbeddingAdapter$$ExternalSyntheticLambda8(ActivityRule activityRule) {
        this.f$0 = activityRule;
    }

    public final boolean test(Object obj) {
        return EmbeddingAdapter.translateActivityRule$lambda$15(this.f$0, (Intent) obj);
    }
}
