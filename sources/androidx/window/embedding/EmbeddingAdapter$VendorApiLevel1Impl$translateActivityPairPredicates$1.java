package androidx.window.embedding;

import android.app.Activity;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "first", "Landroid/app/Activity;", "second", "invoke", "(Landroid/app/Activity;Landroid/app/Activity;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: EmbeddingAdapter.kt */
final class EmbeddingAdapter$VendorApiLevel1Impl$translateActivityPairPredicates$1 extends Lambda implements Function2<Activity, Activity, Boolean> {
    final /* synthetic */ Set<SplitPairFilter> $splitPairFilters;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EmbeddingAdapter$VendorApiLevel1Impl$translateActivityPairPredicates$1(Set<SplitPairFilter> set) {
        super(2);
        this.$splitPairFilters = set;
    }

    public final Boolean invoke(Activity activity, Activity activity2) {
        Intrinsics.checkNotNullParameter(activity, "first");
        Intrinsics.checkNotNullParameter(activity2, "second");
        Iterable iterable = this.$splitPairFilters;
        boolean z = false;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((SplitPairFilter) it.next()).matchesActivityPair(activity, activity2)) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return Boolean.valueOf(z);
    }
}
