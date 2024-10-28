package androidx.window.layout;

import android.app.Activity;
import androidx.window.reflection.ReflectionUtils;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: SafeWindowLayoutComponentProvider.kt */
final class SafeWindowLayoutComponentProvider$isMethodWindowLayoutInfoListenerJavaConsumerValid$1 extends Lambda implements Function0<Boolean> {
    final /* synthetic */ SafeWindowLayoutComponentProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SafeWindowLayoutComponentProvider$isMethodWindowLayoutInfoListenerJavaConsumerValid$1(SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider) {
        super(0);
        this.this$0 = safeWindowLayoutComponentProvider;
    }

    public final Boolean invoke() {
        Class<?> consumerClassOrNull$window_release = this.this$0.consumerAdapter.consumerClassOrNull$window_release();
        boolean z = false;
        if (consumerClassOrNull$window_release == null) {
            return false;
        }
        Class access$getWindowLayoutComponentClass = this.this$0.getWindowLayoutComponentClass();
        Method method = access$getWindowLayoutComponentClass.getMethod("addWindowLayoutInfoListener", new Class[]{Activity.class, consumerClassOrNull$window_release});
        Method method2 = access$getWindowLayoutComponentClass.getMethod("removeWindowLayoutInfoListener", new Class[]{consumerClassOrNull$window_release});
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(method, "addListenerMethod");
        if (reflectionUtils.isPublic$window_release(method)) {
            ReflectionUtils reflectionUtils2 = ReflectionUtils.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(method2, "removeListenerMethod");
            if (reflectionUtils2.isPublic$window_release(method2)) {
                z = true;
            }
        }
        return Boolean.valueOf(z);
    }
}
