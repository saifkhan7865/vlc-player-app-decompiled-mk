package org.videolan.television.ui.browser;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.MediaParsingService;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "devices", "", "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseTvActivity.kt */
final class BaseTvActivity$registerLiveData$3 extends Lambda implements Function1<List<String>, Unit> {
    final /* synthetic */ BaseTvActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseTvActivity$registerLiveData$3(BaseTvActivity baseTvActivity) {
        super(1);
        this.this$0 = baseTvActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<String>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<String> list) {
        if (list != null) {
            for (String newStorageDetected : list) {
                UiTools.INSTANCE.newStorageDetected(this.this$0, newStorageDetected);
            }
            MediaParsingService.Companion.getNewStorages().setValue(null);
        }
    }
}
