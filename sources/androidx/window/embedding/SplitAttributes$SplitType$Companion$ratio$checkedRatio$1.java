package androidx.window.embedding;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "", "invoke", "(F)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: SplitAttributes.kt */
final class SplitAttributes$SplitType$Companion$ratio$checkedRatio$1 extends Lambda implements Function1<Float, Boolean> {
    final /* synthetic */ float $ratio;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SplitAttributes$SplitType$Companion$ratio$checkedRatio$1(float f) {
        super(1);
        this.$ratio = f;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).floatValue());
    }

    public final Boolean invoke(float f) {
        double d = (double) this.$ratio;
        boolean z = false;
        if (0.0d <= d && d <= 1.0d) {
            if (!ArraysKt.contains((T[]) new Float[]{Float.valueOf(0.0f), Float.valueOf(1.0f)}, Float.valueOf(this.$ratio))) {
                z = true;
            }
        }
        return Boolean.valueOf(z);
    }
}
