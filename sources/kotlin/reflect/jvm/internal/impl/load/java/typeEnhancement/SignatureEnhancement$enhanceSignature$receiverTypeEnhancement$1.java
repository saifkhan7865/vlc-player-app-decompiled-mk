package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* compiled from: signatureEnhancement.kt */
final class SignatureEnhancement$enhanceSignature$receiverTypeEnhancement$1 extends Lambda implements Function1<CallableMemberDescriptor, KotlinType> {
    public static final SignatureEnhancement$enhanceSignature$receiverTypeEnhancement$1 INSTANCE = new SignatureEnhancement$enhanceSignature$receiverTypeEnhancement$1();

    SignatureEnhancement$enhanceSignature$receiverTypeEnhancement$1() {
        super(1);
    }

    public final KotlinType invoke(CallableMemberDescriptor callableMemberDescriptor) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor, "it");
        ReceiverParameterDescriptor extensionReceiverParameter = callableMemberDescriptor.getExtensionReceiverParameter();
        Intrinsics.checkNotNull(extensionReceiverParameter);
        KotlinType type = extensionReceiverParameter.getType();
        Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
        return type;
    }
}
