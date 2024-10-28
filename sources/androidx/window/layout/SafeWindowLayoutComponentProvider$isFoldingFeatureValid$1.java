package androidx.window.layout;

import android.graphics.Rect;
import androidx.window.reflection.ReflectionUtils;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: SafeWindowLayoutComponentProvider.kt */
final class SafeWindowLayoutComponentProvider$isFoldingFeatureValid$1 extends Lambda implements Function0<Boolean> {
    final /* synthetic */ SafeWindowLayoutComponentProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SafeWindowLayoutComponentProvider$isFoldingFeatureValid$1(SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider) {
        super(0);
        this.this$0 = safeWindowLayoutComponentProvider;
    }

    public final Boolean invoke() {
        boolean z;
        Class access$getFoldingFeatureClass = this.this$0.getFoldingFeatureClass();
        Method method = access$getFoldingFeatureClass.getMethod("getBounds", (Class[]) null);
        Method method2 = access$getFoldingFeatureClass.getMethod("getType", (Class[]) null);
        Method method3 = access$getFoldingFeatureClass.getMethod("getState", (Class[]) null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(method, "getBoundsMethod");
        if (reflectionUtils.doesReturn$window_release(method, (KClass<?>) Reflection.getOrCreateKotlinClass(Rect.class)) && ReflectionUtils.INSTANCE.isPublic$window_release(method)) {
            ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(method2, "getTypeMethod");
            if (reflectionUtils2.doesReturn$window_release(method2, (KClass<?>) Reflection.getOrCreateKotlinClass(Integer.TYPE)) && ReflectionUtils.INSTANCE.isPublic$window_release(method2)) {
                ReflectionUtils reflectionUtils3 = ReflectionUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(method3, "getStateMethod");
                if (reflectionUtils3.doesReturn$window_release(method3, (KClass<?>) Reflection.getOrCreateKotlinClass(Integer.TYPE)) && ReflectionUtils.INSTANCE.isPublic$window_release(method3)) {
                    z = true;
                    return Boolean.valueOf(z);
                }
            }
        }
        z = false;
        return Boolean.valueOf(z);
    }
}
