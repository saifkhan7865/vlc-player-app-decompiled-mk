package kotlin.reflect.jvm.internal.impl.types;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.videolan.resources.Constants;

/* compiled from: TypeSubstitution.kt */
public class DelegatedTypeSubstitution extends TypeSubstitution {
    private final TypeSubstitution substitution;

    public DelegatedTypeSubstitution(TypeSubstitution typeSubstitution) {
        Intrinsics.checkNotNullParameter(typeSubstitution, "substitution");
        this.substitution = typeSubstitution;
    }

    public TypeProjection get(KotlinType kotlinType) {
        Intrinsics.checkNotNullParameter(kotlinType, LeanbackPreferenceDialogFragment.ARG_KEY);
        return this.substitution.get(kotlinType);
    }

    public KotlinType prepareTopLevelType(KotlinType kotlinType, Variance variance) {
        Intrinsics.checkNotNullParameter(kotlinType, "topLevelType");
        Intrinsics.checkNotNullParameter(variance, Constants.PLAY_EXTRA_START_TIME);
        return this.substitution.prepareTopLevelType(kotlinType, variance);
    }

    public boolean isEmpty() {
        return this.substitution.isEmpty();
    }

    public boolean approximateCapturedTypes() {
        return this.substitution.approximateCapturedTypes();
    }

    public boolean approximateContravariantCapturedTypes() {
        return this.substitution.approximateContravariantCapturedTypes();
    }

    public Annotations filterAnnotations(Annotations annotations) {
        Intrinsics.checkNotNullParameter(annotations, "annotations");
        return this.substitution.filterAnnotations(annotations);
    }
}
