package androidx.window.embedding;

import androidx.window.reflection.ReflectionUtils;
import java.lang.reflect.Method;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: SafeActivityEmbeddingComponentProvider.kt */
final class SafeActivityEmbeddingComponentProvider$isMethodSetEmbeddingRulesValid$1 extends Lambda implements Function0<Boolean> {
    final /* synthetic */ SafeActivityEmbeddingComponentProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SafeActivityEmbeddingComponentProvider$isMethodSetEmbeddingRulesValid$1(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) {
        super(0);
        this.this$0 = safeActivityEmbeddingComponentProvider;
    }

    public final Boolean invoke() {
        Method method = this.this$0.getActivityEmbeddingComponentClass().getMethod("setEmbeddingRules", new Class[]{Set.class});
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(method, "setEmbeddingRulesMethod");
        return Boolean.valueOf(reflectionUtils.isPublic$window_release(method));
    }
}
