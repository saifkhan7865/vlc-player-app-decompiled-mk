package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: AbstractBinaryClassAnnotationAndConstantLoader.kt */
final class AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationDefaultValue$1 extends Lambda implements Function2<AnnotationsContainerWithConstants<? extends A, ? extends C>, MemberSignature, C> {
    public static final AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationDefaultValue$1 INSTANCE = new AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationDefaultValue$1();

    AbstractBinaryClassAnnotationAndConstantLoader$loadAnnotationDefaultValue$1() {
        super(2);
    }

    public final C invoke(AnnotationsContainerWithConstants<? extends A, ? extends C> annotationsContainerWithConstants, MemberSignature memberSignature) {
        Intrinsics.checkNotNullParameter(annotationsContainerWithConstants, "$this$loadConstantFromProperty");
        Intrinsics.checkNotNullParameter(memberSignature, "it");
        return annotationsContainerWithConstants.getAnnotationParametersDefaultValues().get(memberSignature);
    }
}
