package org.videolan.vlc.gui;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.gui.view.EmptyLoadingState;
import org.videolan.vlc.gui.view.TitleListView;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "list", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MoreFragment.kt */
final class MoreFragment$onViewCreated$1 extends Lambda implements Function1<List<MediaWrapper>, Unit> {
    final /* synthetic */ MoreFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MoreFragment$onViewCreated$1(MoreFragment moreFragment) {
        super(1);
        this.this$0 = moreFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<MediaWrapper>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<MediaWrapper> list) {
        if (list != null) {
            MoreFragment moreFragment = this.this$0;
            moreFragment.historyAdapter.update(list);
            TitleListView titleListView = null;
            if (list.isEmpty()) {
                TitleListView access$getHistoryEntry$p = moreFragment.historyEntry;
                if (access$getHistoryEntry$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("historyEntry");
                    access$getHistoryEntry$p = null;
                }
                KotlinExtensionsKt.setGone(access$getHistoryEntry$p);
            } else {
                TitleListView access$getHistoryEntry$p2 = moreFragment.historyEntry;
                if (access$getHistoryEntry$p2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("historyEntry");
                    access$getHistoryEntry$p2 = null;
                }
                KotlinExtensionsKt.setVisible(access$getHistoryEntry$p2);
                TitleListView access$getHistoryEntry$p3 = moreFragment.historyEntry;
                if (access$getHistoryEntry$p3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("historyEntry");
                    access$getHistoryEntry$p3 = null;
                }
                access$getHistoryEntry$p3.getLoading().setState(EmptyLoadingState.NONE);
            }
            if (!list.isEmpty()) {
                TitleListView access$getHistoryEntry$p4 = moreFragment.historyEntry;
                if (access$getHistoryEntry$p4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("historyEntry");
                } else {
                    titleListView = access$getHistoryEntry$p4;
                }
                KotlinExtensionsKt.setVisible(titleListView.getActionButton());
            } else {
                TitleListView access$getHistoryEntry$p5 = moreFragment.historyEntry;
                if (access$getHistoryEntry$p5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("historyEntry");
                } else {
                    titleListView = access$getHistoryEntry$p5;
                }
                KotlinExtensionsKt.setGone(titleListView.getActionButton());
            }
        }
        this.this$0.restoreMultiSelectHelper();
    }
}
