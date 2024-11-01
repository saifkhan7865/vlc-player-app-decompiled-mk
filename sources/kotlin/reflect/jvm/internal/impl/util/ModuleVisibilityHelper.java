package kotlin.reflect.jvm.internal.impl.util;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;

/* compiled from: ModuleVisibilityHelper.kt */
public interface ModuleVisibilityHelper {
    boolean isInFriendModule(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2);

    /* compiled from: ModuleVisibilityHelper.kt */
    public static final class EMPTY implements ModuleVisibilityHelper {
        public static final EMPTY INSTANCE = new EMPTY();

        public boolean isInFriendModule(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
            Intrinsics.checkNotNullParameter(declarationDescriptor, "what");
            Intrinsics.checkNotNullParameter(declarationDescriptor2, TypedValues.TransitionType.S_FROM);
            return true;
        }

        private EMPTY() {
        }
    }
}
