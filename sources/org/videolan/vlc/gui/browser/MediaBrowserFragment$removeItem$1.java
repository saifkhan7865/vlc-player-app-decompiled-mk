package org.videolan.vlc.gui.browser;

import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.media.MediaUtils;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "T", "Lorg/videolan/vlc/viewmodels/SortableModel;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaBrowserFragment.kt */
final class MediaBrowserFragment$removeItem$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ MediaLibraryItem $item;
    final /* synthetic */ MediaBrowserFragment<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaBrowserFragment$removeItem$1(MediaBrowserFragment<T> mediaBrowserFragment, MediaLibraryItem mediaLibraryItem) {
        super(0);
        this.this$0 = mediaBrowserFragment;
        this.$item = mediaLibraryItem;
    }

    public final void invoke() {
        MediaUtils mediaUtils = MediaUtils.INSTANCE;
        FragmentActivity requireActivity = this.this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        MediaLibraryItem mediaLibraryItem = this.$item;
        final MediaBrowserFragment<T> mediaBrowserFragment = this.this$0;
        mediaUtils.deleteItem(requireActivity, mediaLibraryItem, new Function1<MediaLibraryItem, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((MediaLibraryItem) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(MediaLibraryItem mediaLibraryItem) {
                Intrinsics.checkNotNullParameter(mediaLibraryItem, "it");
                mediaBrowserFragment.onDeleteFailed(mediaLibraryItem);
            }
        });
    }
}
