package androidx.core.content;

import android.net.Uri;
import androidx.core.util.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class IntentSanitizer$Builder$$ExternalSyntheticLambda7 implements Predicate {
    public final /* synthetic */ String f$0;

    public /* synthetic */ IntentSanitizer$Builder$$ExternalSyntheticLambda7(String str) {
        this.f$0 = str;
    }

    public /* synthetic */ Predicate and(Predicate predicate) {
        return Predicate.CC.$default$and(this, predicate);
    }

    public /* synthetic */ Predicate negate() {
        return Predicate.CC.$default$negate(this);
    }

    public /* synthetic */ Predicate or(Predicate predicate) {
        return Predicate.CC.$default$or(this, predicate);
    }

    public final boolean test(Object obj) {
        return this.f$0.equals(((Uri) obj).getAuthority());
    }
}
