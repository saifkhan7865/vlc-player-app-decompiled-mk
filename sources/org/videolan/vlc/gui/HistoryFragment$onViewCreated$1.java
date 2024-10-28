package org.videolan.vlc.gui;

import android.view.MenuItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "list", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: HistoryFragment.kt */
final class HistoryFragment$onViewCreated$1 extends Lambda implements Function1<List<MediaWrapper>, Unit> {
    final /* synthetic */ HistoryFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HistoryFragment$onViewCreated$1(HistoryFragment historyFragment) {
        super(1);
        this.this$0 = historyFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<MediaWrapper>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<MediaWrapper> list) {
        if (list != null) {
            HistoryFragment historyFragment = this.this$0;
            historyFragment.historyAdapter.update(list);
            historyFragment.updateEmptyView();
            if (historyFragment.cleanMenuItem != null) {
                MenuItem access$getCleanMenuItem$p = historyFragment.cleanMenuItem;
                if (access$getCleanMenuItem$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cleanMenuItem");
                    access$getCleanMenuItem$p = null;
                }
                access$getCleanMenuItem$p.setVisible(!list.isEmpty());
            }
        }
    }
}
