package androidx.window.embedding;

import android.content.Context;
import android.view.WindowMetrics;
import androidx.transition.WindowIdApi18$$ExternalSyntheticApiModelOutline0;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "windowMetrics", "Landroid/view/WindowMetrics;", "invoke", "(Landroid/view/WindowMetrics;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: EmbeddingAdapter.kt */
final class EmbeddingAdapter$VendorApiLevel1Impl$translateParentMetricsPredicate$1 extends Lambda implements Function1<WindowMetrics, Boolean> {
    final /* synthetic */ Context $context;
    final /* synthetic */ SplitRule $splitRule;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EmbeddingAdapter$VendorApiLevel1Impl$translateParentMetricsPredicate$1(SplitRule splitRule, Context context) {
        super(1);
        this.$splitRule = splitRule;
        this.$context = context;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(WindowIdApi18$$ExternalSyntheticApiModelOutline0.m(obj));
    }

    public final Boolean invoke(WindowMetrics windowMetrics) {
        Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
        return Boolean.valueOf(this.$splitRule.checkParentMetrics$window_release(this.$context, windowMetrics));
    }
}
