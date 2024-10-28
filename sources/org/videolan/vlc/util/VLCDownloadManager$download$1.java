package org.videolan.vlc.util;

import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.vlc.gui.dialogs.SubtitleItem;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.VLCDownloadManager", f = "VLCDownloadManager.kt", i = {0, 0}, l = {73, 75}, m = "download", n = {"this", "context"}, s = {"L$0", "L$1"})
/* compiled from: VLCDownloadManager.kt */
final class VLCDownloadManager$download$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VLCDownloadManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VLCDownloadManager$download$1(VLCDownloadManager vLCDownloadManager, Continuation<? super VLCDownloadManager$download$1> continuation) {
        super(continuation);
        this.this$0 = vLCDownloadManager;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.download((FragmentActivity) null, (SubtitleItem) null, this);
    }
}
