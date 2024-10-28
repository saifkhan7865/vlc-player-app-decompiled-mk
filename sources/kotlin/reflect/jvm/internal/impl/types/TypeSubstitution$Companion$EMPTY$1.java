package kotlin.reflect.jvm.internal.impl.types;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TypeSubstitution.kt */
public final class TypeSubstitution$Companion$EMPTY$1 extends TypeSubstitution {
    public Void get(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, LeanbackPreferenceDialogFragment.ARG_KEY);
        return null;
    }

    public boolean isEmpty() {
        return true;
    }

    TypeSubstitution$Companion$EMPTY$1() {
    }

    public String toString() {
        return "Empty TypeSubstitution";
    }
}
