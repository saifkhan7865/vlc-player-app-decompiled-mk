package org.videolan.television.ui;

import androidx.leanback.widget.ArrayObjectAdapter;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MainTvFragment.kt */
final class MainTvFragment$registerDatasets$8 extends Lambda implements Function1<List<? extends MediaWrapper>, Unit> {
    final /* synthetic */ MainTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainTvFragment$registerDatasets$8(MainTvFragment mainTvFragment) {
        super(1);
        this.this$0 = mainTvFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<? extends MediaWrapper>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<? extends MediaWrapper> list) {
        MainTvFragment mainTvFragment = this.this$0;
        Intrinsics.checkNotNull(list);
        Collection collection = list;
        mainTvFragment.displayHistory = !collection.isEmpty();
        if (!collection.isEmpty()) {
            ArrayObjectAdapter access$getHistoryAdapter$p = this.this$0.historyAdapter;
            if (access$getHistoryAdapter$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
                access$getHistoryAdapter$p = null;
            }
            access$getHistoryAdapter$p.setItems(list, TvUtil.INSTANCE.getDiffCallback());
        }
        this.this$0.resetLines();
        this.this$0.addAndCheckLoadedLines(2);
    }
}
