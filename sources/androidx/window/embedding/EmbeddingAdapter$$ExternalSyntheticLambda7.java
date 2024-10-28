package androidx.window.embedding;

import android.app.Activity;
import androidx.window.extensions.core.util.function.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EmbeddingAdapter$$ExternalSyntheticLambda7 implements Predicate {
    public final /* synthetic */ ActivityRule f$0;

    public /* synthetic */ EmbeddingAdapter$$ExternalSyntheticLambda7(ActivityRule activityRule) {
        this.f$0 = activityRule;
    }

    public final boolean test(Object obj) {
        return EmbeddingAdapter.translateActivityRule$lambda$13(this.f$0, (Activity) obj);
    }
}
