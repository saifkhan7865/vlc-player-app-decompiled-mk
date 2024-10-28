package androidx.window.layout;

import androidx.window.reflection.WindowExtensionsConstants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/lang/Class;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: SafeWindowLayoutComponentProvider.kt */
final class SafeWindowLayoutComponentProvider$isWindowExtensionsPresent$1 extends Lambda implements Function0<Class<?>> {
    final /* synthetic */ SafeWindowLayoutComponentProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SafeWindowLayoutComponentProvider$isWindowExtensionsPresent$1(SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider) {
        super(0);
        this.this$0 = safeWindowLayoutComponentProvider;
    }

    public final Class<?> invoke() {
        Class<?> loadClass = this.this$0.loader.loadClass(WindowExtensionsConstants.WINDOW_EXTENSIONS_PROVIDER_CLASS);
        Intrinsics.checkNotNullExpressionValue(loadClass, "loader.loadClass(WINDOW_EXTENSIONS_PROVIDER_CLASS)");
        return loadClass;
    }
}
