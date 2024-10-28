package androidx.fragment.app;

import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/lifecycle/viewmodel/CreationExtras;", "VM", "Landroidx/lifecycle/ViewModel;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
/* compiled from: FragmentViewModelLazy.kt */
public final class FragmentViewModelLazyKt$viewModels$7 extends Lambda implements Function0<CreationExtras> {
    final /* synthetic */ Function0<CreationExtras> $extrasProducer;
    final /* synthetic */ Lazy<ViewModelStoreOwner> $owner$delegate;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FragmentViewModelLazyKt$viewModels$7(Function0<? extends CreationExtras> function0, Lazy<? extends ViewModelStoreOwner> lazy) {
        super(0);
        this.$extrasProducer = function0;
        this.$owner$delegate = lazy;
    }

    public final CreationExtras invoke() {
        CreationExtras invoke;
        Function0<CreationExtras> function0 = this.$extrasProducer;
        if (function0 != null && (invoke = function0.invoke()) != null) {
            return invoke;
        }
        ViewModelStoreOwner r0 = FragmentViewModelLazyKt.m106viewModels$lambda1(this.$owner$delegate);
        HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = r0 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) r0 : null;
        if (hasDefaultViewModelProviderFactory != null) {
            return hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras();
        }
        return CreationExtras.Empty.INSTANCE;
    }
}
