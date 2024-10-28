package org.videolan.vlc.gui.browser;

import android.view.MenuItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "mediaLibraryItems", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseBrowserFragment.kt */
final class BaseBrowserFragment$onViewCreated$2 extends Lambda implements Function1<List<MediaLibraryItem>, Unit> {
    final /* synthetic */ BaseBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseBrowserFragment$onViewCreated$2(BaseBrowserFragment baseBrowserFragment) {
        super(1);
        this.this$0 = baseBrowserFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<MediaLibraryItem>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<MediaLibraryItem> list) {
        BaseBrowserAdapter adapter = this.this$0.getAdapter();
        Intrinsics.checkNotNull(list);
        adapter.update(list);
        if (this.this$0.addPlaylistFolderOnly != null) {
            MenuItem access$getAddPlaylistFolderOnly$p = this.this$0.addPlaylistFolderOnly;
            if (access$getAddPlaylistFolderOnly$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("addPlaylistFolderOnly");
                access$getAddPlaylistFolderOnly$p = null;
            }
            access$getAddPlaylistFolderOnly$p.setVisible(this.this$0.getAdapter().getMediaCount$vlc_android_release() > 0);
        }
    }
}
