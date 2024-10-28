package io.ktor.server.engine;

import java.lang.reflect.Method;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u001a\b\u0010\u0005\u001a\u00020\u0006H\u0000\"\u001d\u0010\u0000\u001a\u0004\u0018\u00010\u00018BX\u0002¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0000\u0010\u0002¨\u0006\u0007"}, d2 = {"isParkingAllowedFunction", "Ljava/lang/reflect/Method;", "()Ljava/lang/reflect/Method;", "isParkingAllowedFunction$delegate", "Lkotlin/Lazy;", "safeToRunInPlace", "", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: BlockingBridge.kt */
public final class BlockingBridgeKt {
    private static final Lazy isParkingAllowedFunction$delegate = LazyKt.lazy(BlockingBridgeKt$isParkingAllowedFunction$2.INSTANCE);

    private static final Method isParkingAllowedFunction() {
        return (Method) isParkingAllowedFunction$delegate.getValue();
    }

    public static final boolean safeToRunInPlace() {
        Method isParkingAllowedFunction = isParkingAllowedFunction();
        if (isParkingAllowedFunction != null) {
            try {
                if (Intrinsics.areEqual(isParkingAllowedFunction.invoke((Object) null, (Object[]) null), (Object) true)) {
                    return true;
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }
}
