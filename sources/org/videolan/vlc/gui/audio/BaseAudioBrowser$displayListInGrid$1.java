package org.videolan.vlc.gui.audio;

import androidx.recyclerview.widget.GridLayoutManager;
import kotlin.Metadata;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.Settings;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;

@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, d2 = {"org/videolan/vlc/gui/audio/BaseAudioBrowser$displayListInGrid$1", "Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;", "getSpanSize", "", "position", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseAudioBrowser.kt */
public final class BaseAudioBrowser$displayListInGrid$1 extends GridLayoutManager.SpanSizeLookup {
    final /* synthetic */ AudioBrowserAdapter $adapter;
    final /* synthetic */ MedialibraryProvider<MediaLibraryItem> $provider;
    final /* synthetic */ BaseAudioBrowser<T> this$0;

    BaseAudioBrowser$displayListInGrid$1(AudioBrowserAdapter audioBrowserAdapter, MedialibraryProvider<MediaLibraryItem> medialibraryProvider, BaseAudioBrowser<T> baseAudioBrowser) {
        this.$adapter = audioBrowserAdapter;
        this.$provider = medialibraryProvider;
        this.this$0 = baseAudioBrowser;
    }

    public int getSpanSize(int i) {
        if (i == this.$adapter.getItemCount() - 1 || !Settings.INSTANCE.getShowHeaders() || !this.$provider.isFirstInSection(i + 1)) {
            return 1;
        }
        return this.this$0.getNbColumns() - ((i - this.$provider.getPositionForSection(i)) % this.this$0.getNbColumns());
    }
}
