package org.videolan.television.ui.browser;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.ScanProgress;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "scanProgress", "Lorg/videolan/vlc/ScanProgress;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseTvActivity.kt */
final class BaseTvActivity$registerLiveData$1 extends Lambda implements Function1<ScanProgress, Unit> {
    final /* synthetic */ BaseTvActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseTvActivity$registerLiveData$1(BaseTvActivity baseTvActivity) {
        super(1);
        this.this$0 = baseTvActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ScanProgress) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ScanProgress scanProgress) {
        if (scanProgress != null) {
            this.this$0.onParsingServiceProgress(scanProgress);
        }
    }
}
