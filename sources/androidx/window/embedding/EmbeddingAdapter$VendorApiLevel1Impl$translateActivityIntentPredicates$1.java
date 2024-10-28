package androidx.window.embedding;

import android.app.Activity;
import android.content.Intent;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "first", "Landroid/app/Activity;", "second", "Landroid/content/Intent;", "invoke", "(Landroid/app/Activity;Landroid/content/Intent;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: EmbeddingAdapter.kt */
final class EmbeddingAdapter$VendorApiLevel1Impl$translateActivityIntentPredicates$1 extends Lambda implements Function2<Activity, Intent, Boolean> {
    final /* synthetic */ Set<SplitPairFilter> $splitPairFilters;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EmbeddingAdapter$VendorApiLevel1Impl$translateActivityIntentPredicates$1(Set<SplitPairFilter> set) {
        super(2);
        this.$splitPairFilters = set;
    }

    public final Boolean invoke(Activity activity, Intent intent) {
        Intrinsics.checkNotNullParameter(activity, "first");
        Intrinsics.checkNotNullParameter(intent, "second");
        Iterable iterable = this.$splitPairFilters;
        boolean z = false;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((SplitPairFilter) it.next()).matchesActivityIntentPair(activity, intent)) {
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
