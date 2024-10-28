package org.videolan.vlc.gui.browser;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005 \u0006*\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "pair", "Lkotlin/Pair;", "", "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseBrowserFragment.kt */
final class BaseBrowserFragment$onViewCreated$3 extends Lambda implements Function1<Pair<? extends Integer, ? extends String>, Unit> {
    final /* synthetic */ BaseBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseBrowserFragment$onViewCreated$3(BaseBrowserFragment baseBrowserFragment) {
        super(1);
        this.this$0 = baseBrowserFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Pair<Integer, String>) (Pair) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Pair<Integer, String> pair) {
        if (pair != null) {
            this.this$0.getAdapter().notifyItemChanged(pair.getFirst().intValue(), pair.getSecond());
        }
    }
}
