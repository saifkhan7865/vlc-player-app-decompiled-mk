package androidx.paging;

import androidx.paging.HintHandler;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u00042\n\u0010\u0005\u001a\u00060\u0003R\u00020\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "prependHint", "Landroidx/paging/HintHandler$HintFlow;", "Landroidx/paging/HintHandler;", "appendHint", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: HintHandler.kt */
final class HintHandler$processHint$1 extends Lambda implements Function2<HintHandler.HintFlow, HintHandler.HintFlow, Unit> {
    final /* synthetic */ ViewportHint $viewportHint;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HintHandler$processHint$1(ViewportHint viewportHint) {
        super(2);
        this.$viewportHint = viewportHint;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((HintHandler.HintFlow) obj, (HintHandler.HintFlow) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(HintHandler.HintFlow hintFlow, HintHandler.HintFlow hintFlow2) {
        Intrinsics.checkNotNullParameter(hintFlow, "prependHint");
        Intrinsics.checkNotNullParameter(hintFlow2, "appendHint");
        if (HintHandlerKt.shouldPrioritizeOver(this.$viewportHint, hintFlow.getValue(), LoadType.PREPEND)) {
            hintFlow.setValue(this.$viewportHint);
        }
        if (HintHandlerKt.shouldPrioritizeOver(this.$viewportHint, hintFlow2.getValue(), LoadType.APPEND)) {
            hintFlow2.setValue(this.$viewportHint);
        }
    }
}
