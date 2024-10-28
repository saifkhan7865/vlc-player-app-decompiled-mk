package org.videolan.vlc.gui.dialogs;

import androidx.core.widget.NestedScrollView;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.databinding.SubtitleDownloaderDialogBinding;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lorg/videolan/vlc/gui/dialogs/SubtitleItem;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: SubtitleDownloaderDialogFragment.kt */
final class SubtitleDownloaderDialogFragment$onViewCreated$1 extends Lambda implements Function1<List<? extends SubtitleItem>, Unit> {
    final /* synthetic */ SubtitleDownloaderDialogFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SubtitleDownloaderDialogFragment$onViewCreated$1(SubtitleDownloaderDialogFragment subtitleDownloaderDialogFragment) {
        super(1);
        this.this$0 = subtitleDownloaderDialogFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<SubtitleItem>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<SubtitleItem> list) {
        SubtitlesAdapter access$getDownloadAdapter$p = this.this$0.downloadAdapter;
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding = null;
        if (access$getDownloadAdapter$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadAdapter");
            access$getDownloadAdapter$p = null;
        }
        access$getDownloadAdapter$p.setList(list);
        Intrinsics.checkNotNull(list);
        if (!list.isEmpty()) {
            SubtitleDownloaderDialogFragment subtitleDownloaderDialogFragment = this.this$0;
            SubtitleDownloaderDialogBinding access$getBinding$p = subtitleDownloaderDialogFragment.binding;
            if (access$getBinding$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                subtitleDownloaderDialogBinding = access$getBinding$p;
            }
            NestedScrollView nestedScrollView = subtitleDownloaderDialogBinding.scrollView;
            Intrinsics.checkNotNullExpressionValue(nestedScrollView, "scrollView");
            subtitleDownloaderDialogFragment.focusOnView(nestedScrollView);
        }
    }
}
