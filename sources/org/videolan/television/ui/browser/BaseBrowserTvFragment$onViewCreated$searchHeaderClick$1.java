package org.videolan.television.ui.browser;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "it", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseBrowserTvFragment.kt */
final class BaseBrowserTvFragment$onViewCreated$searchHeaderClick$1 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ BaseBrowserTvFragment<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseBrowserTvFragment$onViewCreated$searchHeaderClick$1(BaseBrowserTvFragment<T> baseBrowserTvFragment) {
        super(1);
        this.this$0 = baseBrowserTvFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        Intrinsics.checkNotNullParameter(view, "it");
        this.this$0.getAnimationDelegate$television_release().hideFAB$television_release();
    }
}