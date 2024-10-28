package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.ExtensionsUtil;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.reflection.ReflectionUtils;
import androidx.window.reflection.WindowExtensionsConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0016H\u0002J\b\u0010\u0019\u001a\u00020\u0016H\u0002J\b\u0010\u001a\u001a\u00020\u0016H\u0002J\b\u0010\u001b\u001a\u00020\u0016H\u0002J\b\u0010\u001c\u001a\u00020\u0016H\u0002J\b\u0010\u001d\u001a\u00020\u0016H\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0018\u0010\r\u001a\u0006\u0012\u0002\b\u00030\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\nR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\n¨\u0006\u001f"}, d2 = {"Landroidx/window/layout/SafeWindowLayoutComponentProvider;", "", "loader", "Ljava/lang/ClassLoader;", "consumerAdapter", "Landroidx/window/core/ConsumerAdapter;", "(Ljava/lang/ClassLoader;Landroidx/window/core/ConsumerAdapter;)V", "foldingFeatureClass", "Ljava/lang/Class;", "getFoldingFeatureClass", "()Ljava/lang/Class;", "windowExtensionsClass", "getWindowExtensionsClass", "windowExtensionsProviderClass", "getWindowExtensionsProviderClass", "windowLayoutComponent", "Landroidx/window/extensions/layout/WindowLayoutComponent;", "getWindowLayoutComponent", "()Landroidx/window/extensions/layout/WindowLayoutComponent;", "windowLayoutComponentClass", "getWindowLayoutComponentClass", "canUseWindowLayoutComponent", "", "hasValidVendorApiLevel1", "hasValidVendorApiLevel2", "isFoldingFeatureValid", "isMethodWindowLayoutInfoListenerJavaConsumerValid", "isMethodWindowLayoutInfoListenerWindowConsumerValid", "isWindowExtensionsPresent", "isWindowExtensionsValid", "isWindowLayoutProviderValid", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SafeWindowLayoutComponentProvider.kt */
public final class SafeWindowLayoutComponentProvider {
    /* access modifiers changed from: private */
    public final ConsumerAdapter consumerAdapter;
    /* access modifiers changed from: private */
    public final ClassLoader loader;

    public SafeWindowLayoutComponentProvider(ClassLoader classLoader, ConsumerAdapter consumerAdapter2) {
        Intrinsics.checkNotNullParameter(classLoader, "loader");
        Intrinsics.checkNotNullParameter(consumerAdapter2, "consumerAdapter");
        this.loader = classLoader;
        this.consumerAdapter = consumerAdapter2;
    }

    public final WindowLayoutComponent getWindowLayoutComponent() {
        if (!canUseWindowLayoutComponent()) {
            return null;
        }
        try {
            return WindowExtensionsProvider.getWindowExtensions().getWindowLayoutComponent();
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }

    private final boolean canUseWindowLayoutComponent() {
        if (!isWindowExtensionsPresent() || !isWindowExtensionsValid() || !isWindowLayoutProviderValid() || !isFoldingFeatureValid()) {
            return false;
        }
        int safeVendorApiLevel = ExtensionsUtil.INSTANCE.getSafeVendorApiLevel();
        if (safeVendorApiLevel == 1) {
            return hasValidVendorApiLevel1();
        }
        if (2 > safeVendorApiLevel || safeVendorApiLevel > Integer.MAX_VALUE) {
            return false;
        }
        return hasValidVendorApiLevel2();
    }

    private final boolean isWindowExtensionsPresent() {
        return ReflectionUtils.INSTANCE.checkIsPresent$window_release(new SafeWindowLayoutComponentProvider$isWindowExtensionsPresent$1(this));
    }

    private final boolean hasValidVendorApiLevel1() {
        return isMethodWindowLayoutInfoListenerJavaConsumerValid();
    }

    private final boolean hasValidVendorApiLevel2() {
        return hasValidVendorApiLevel1() && isMethodWindowLayoutInfoListenerWindowConsumerValid();
    }

    private final boolean isWindowExtensionsValid() {
        return ReflectionUtils.validateReflection$window_release("WindowExtensionsProvider#getWindowExtensions is not valid", new SafeWindowLayoutComponentProvider$isWindowExtensionsValid$1(this));
    }

    private final boolean isWindowLayoutProviderValid() {
        return ReflectionUtils.validateReflection$window_release("WindowExtensions#getWindowLayoutComponent is not valid", new SafeWindowLayoutComponentProvider$isWindowLayoutProviderValid$1(this));
    }

    private final boolean isFoldingFeatureValid() {
        return ReflectionUtils.validateReflection$window_release("FoldingFeature class is not valid", new SafeWindowLayoutComponentProvider$isFoldingFeatureValid$1(this));
    }

    private final boolean isMethodWindowLayoutInfoListenerJavaConsumerValid() {
        return ReflectionUtils.validateReflection$window_release("WindowLayoutComponent#addWindowLayoutInfoListener(" + Activity.class.getName() + ", java.util.function.Consumer) is not valid", new SafeWindowLayoutComponentProvider$isMethodWindowLayoutInfoListenerJavaConsumerValid$1(this));
    }

    private final boolean isMethodWindowLayoutInfoListenerWindowConsumerValid() {
        return ReflectionUtils.validateReflection$window_release("WindowLayoutComponent#addWindowLayoutInfoListener(" + Context.class.getName() + ", androidx.window.extensions.core.util.function.Consumer) is not valid", new SafeWindowLayoutComponentProvider$isMethodWindowLayoutInfoListenerWindowConsumerValid$1(this));
    }

    /* access modifiers changed from: private */
    public final Class<?> getWindowExtensionsProviderClass() {
        Class<?> loadClass = this.loader.loadClass(WindowExtensionsConstants.WINDOW_EXTENSIONS_PROVIDER_CLASS);
        Intrinsics.checkNotNullExpressionValue(loadClass, "loader.loadClass(WINDOW_EXTENSIONS_PROVIDER_CLASS)");
        return loadClass;
    }

    /* access modifiers changed from: private */
    public final Class<?> getWindowExtensionsClass() {
        Class<?> loadClass = this.loader.loadClass(WindowExtensionsConstants.WINDOW_EXTENSIONS_CLASS);
        Intrinsics.checkNotNullExpressionValue(loadClass, "loader.loadClass(WINDOW_EXTENSIONS_CLASS)");
        return loadClass;
    }

    /* access modifiers changed from: private */
    public final Class<?> getFoldingFeatureClass() {
        Class<?> loadClass = this.loader.loadClass(WindowExtensionsConstants.FOLDING_FEATURE_CLASS);
        Intrinsics.checkNotNullExpressionValue(loadClass, "loader.loadClass(FOLDING_FEATURE_CLASS)");
        return loadClass;
    }

    /* access modifiers changed from: private */
    public final Class<?> getWindowLayoutComponentClass() {
        Class<?> loadClass = this.loader.loadClass(WindowExtensionsConstants.WINDOW_LAYOUT_COMPONENT_CLASS);
        Intrinsics.checkNotNullExpressionValue(loadClass, "loader.loadClass(WINDOW_LAYOUT_COMPONENT_CLASS)");
        return loadClass;
    }
}
