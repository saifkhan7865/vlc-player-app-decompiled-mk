package androidx.window.embedding;

import android.content.Intent;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "intent", "Landroid/content/Intent;", "invoke", "(Landroid/content/Intent;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: EmbeddingAdapter.kt */
final class EmbeddingAdapter$VendorApiLevel1Impl$translateIntentPredicates$1 extends Lambda implements Function1<Intent, Boolean> {
    final /* synthetic */ Set<ActivityFilter> $activityFilters;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EmbeddingAdapter$VendorApiLevel1Impl$translateIntentPredicates$1(Set<ActivityFilter> set) {
        super(1);
        this.$activityFilters = set;
    }

    public final Boolean invoke(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        Iterable iterable = this.$activityFilters;
        boolean z = false;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((ActivityFilter) it.next()).matchesIntent(intent)) {
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
