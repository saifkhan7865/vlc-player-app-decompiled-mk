package androidx.window.embedding;

import androidx.window.extensions.core.util.function.Function;
import androidx.window.extensions.embedding.SplitAttributesCalculatorParams;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EmbeddingAdapter$$ExternalSyntheticLambda3 implements Function {
    public final /* synthetic */ EmbeddingAdapter f$0;
    public final /* synthetic */ Function1 f$1;

    public /* synthetic */ EmbeddingAdapter$$ExternalSyntheticLambda3(EmbeddingAdapter embeddingAdapter, Function1 function1) {
        this.f$0 = embeddingAdapter;
        this.f$1 = function1;
    }

    public final Object apply(Object obj) {
        return EmbeddingAdapter.translateSplitAttributesCalculator$lambda$0(this.f$0, this.f$1, (SplitAttributesCalculatorParams) obj);
    }
}
