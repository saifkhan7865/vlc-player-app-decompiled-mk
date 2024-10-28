package org.videolan.vlc.widget;

import kotlin.Metadata;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0014¨\u0006\b"}, d2 = {"Lorg/videolan/vlc/widget/VLCAppWidgetProviderWhite;", "Lorg/videolan/vlc/widget/VLCAppWidgetProvider;", "()V", "getPlayPauseImage", "", "isPlaying", "", "getlayout", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VLCAppWidgetProviderWhite.kt */
public final class VLCAppWidgetProviderWhite extends VLCAppWidgetProvider {
    /* access modifiers changed from: protected */
    public int getlayout() {
        return R.layout.widget_old;
    }

    /* access modifiers changed from: protected */
    public int getPlayPauseImage(boolean z) {
        return z ? R.drawable.ic_widget_pause : R.drawable.ic_widget_play;
    }
}
