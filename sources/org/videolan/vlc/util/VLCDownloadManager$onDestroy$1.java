package org.videolan.vlc.util;

import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.dialogs.SubtitleItem;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005 \u0006*\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "map", "", "", "Lorg/videolan/vlc/gui/dialogs/SubtitleItem;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VLCDownloadManager.kt */
final class VLCDownloadManager$onDestroy$1 extends Lambda implements Function1<Map<Long, ? extends SubtitleItem>, Unit> {
    public static final VLCDownloadManager$onDestroy$1 INSTANCE = new VLCDownloadManager$onDestroy$1();

    VLCDownloadManager$onDestroy$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Map<Long, SubtitleItem>) (Map) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Map<Long, SubtitleItem> map) {
        Set<Long> keySet;
        if (map != null && (keySet = map.keySet()) != null) {
            for (Number longValue : keySet) {
                long longValue2 = longValue.longValue();
                VLCDownloadManager.downloadManager.remove(new long[]{longValue2});
            }
        }
    }
}
