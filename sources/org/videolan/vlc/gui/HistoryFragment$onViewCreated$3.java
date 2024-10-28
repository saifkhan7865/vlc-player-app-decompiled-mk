package org.videolan.vlc.gui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0003*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "kotlin.jvm.PlatformType", "invoke", "(Lkotlin/Unit;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: HistoryFragment.kt */
final class HistoryFragment$onViewCreated$3 extends Lambda implements Function1<Unit, Unit> {
    final /* synthetic */ HistoryFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HistoryFragment$onViewCreated$3(HistoryFragment historyFragment) {
        super(1);
        this.this$0 = historyFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Unit) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Unit unit) {
        UiTools.INSTANCE.updateSortTitles(this.this$0);
        this.this$0.getSwipeRefreshLayout().setRefreshing(false);
        this.this$0.restoreMultiSelectHelper();
    }
}
