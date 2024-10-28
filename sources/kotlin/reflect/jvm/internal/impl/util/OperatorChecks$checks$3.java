package kotlin.reflect.jvm.internal.impl.util;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;

/* compiled from: modifierChecks.kt */
final class OperatorChecks$checks$3 extends Lambda implements Function1<FunctionDescriptor, String> {
    public static final OperatorChecks$checks$3 INSTANCE = new OperatorChecks$checks$3();

    OperatorChecks$checks$3() {
        super(1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0030, code lost:
        if (kotlin.reflect.jvm.internal.impl.util.OperatorChecks.INSTANCE.incDecCheckForExpectClass(r5, r0) != false) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0032, code lost:
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0028, code lost:
        if (kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.isSubtypeOf(r1, r2) == false) goto L_0x002a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String invoke(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor r5) {
        /*
            r4 = this;
            java.lang.String r0 = "$this$$receiver"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor r0 = r5.getDispatchReceiverParameter()
            if (r0 != 0) goto L_0x000f
            kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor r0 = r5.getExtensionReceiverParameter()
        L_0x000f:
            kotlin.reflect.jvm.internal.impl.util.OperatorChecks r1 = kotlin.reflect.jvm.internal.impl.util.OperatorChecks.INSTANCE
            kotlin.reflect.jvm.internal.impl.util.AbstractModifierChecks r1 = (kotlin.reflect.jvm.internal.impl.util.AbstractModifierChecks) r1
            if (r0 == 0) goto L_0x0034
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = r5.getReturnType()
            if (r1 == 0) goto L_0x002a
            kotlin.reflect.jvm.internal.impl.types.KotlinType r2 = r0.getType()
            java.lang.String r3 = "getType(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            boolean r1 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.isSubtypeOf(r1, r2)
            if (r1 != 0) goto L_0x0032
        L_0x002a:
            kotlin.reflect.jvm.internal.impl.util.OperatorChecks r1 = kotlin.reflect.jvm.internal.impl.util.OperatorChecks.INSTANCE
            boolean r5 = r1.incDecCheckForExpectClass(r5, r0)
            if (r5 == 0) goto L_0x0034
        L_0x0032:
            r5 = 1
            goto L_0x0035
        L_0x0034:
            r5 = 0
        L_0x0035:
            if (r5 != 0) goto L_0x003a
            java.lang.String r5 = "receiver must be a supertype of the return type"
            goto L_0x003b
        L_0x003a:
            r5 = 0
        L_0x003b:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.util.OperatorChecks$checks$3.invoke(kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor):java.lang.String");
    }
}
