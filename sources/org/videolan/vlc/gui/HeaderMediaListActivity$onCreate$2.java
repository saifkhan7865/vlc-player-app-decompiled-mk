package org.videolan.vlc.gui;

import android.view.Menu;
import androidx.paging.PagedList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.gui.audio.AudioBrowserAdapter;
import org.videolan.vlc.gui.helpers.SwipeDragItemTouchHelperCallback;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.viewmodels.mobile.PlaylistViewModel;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "tracks", "Landroidx/paging/PagedList;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: HeaderMediaListActivity.kt */
final class HeaderMediaListActivity$onCreate$2 extends Lambda implements Function1<PagedList<MediaWrapper>, Unit> {
    final /* synthetic */ HeaderMediaListActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HeaderMediaListActivity$onCreate$2(HeaderMediaListActivity headerMediaListActivity) {
        super(1);
        this.this$0 = headerMediaListActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PagedList<MediaWrapper>) (PagedList) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(PagedList<MediaWrapper> pagedList) {
        SwipeDragItemTouchHelperCallback swipeDragItemTouchHelperCallback = null;
        if (pagedList == null) {
            pagedList = null;
        }
        if (pagedList != null) {
            AudioBrowserAdapter access$getAudioBrowserAdapter$p = this.this$0.audioBrowserAdapter;
            if (access$getAudioBrowserAdapter$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioBrowserAdapter");
                access$getAudioBrowserAdapter$p = null;
            }
            access$getAudioBrowserAdapter$p.submitList(pagedList);
        }
        Menu menu = this.this$0.getMenu();
        HeaderMediaListActivity headerMediaListActivity = this.this$0;
        UiTools uiTools = UiTools.INSTANCE;
        PlaylistViewModel access$getViewModel$p = headerMediaListActivity.viewModel;
        if (access$getViewModel$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            access$getViewModel$p = null;
        }
        uiTools.updateSortTitles(menu, access$getViewModel$p.getTracksProvider());
        if (this.this$0.itemTouchHelperCallback != null) {
            SwipeDragItemTouchHelperCallback access$getItemTouchHelperCallback$p = this.this$0.itemTouchHelperCallback;
            if (access$getItemTouchHelperCallback$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("itemTouchHelperCallback");
            } else {
                swipeDragItemTouchHelperCallback = access$getItemTouchHelperCallback$p;
            }
            swipeDragItemTouchHelperCallback.setSwipeEnabled(true);
        }
    }
}
