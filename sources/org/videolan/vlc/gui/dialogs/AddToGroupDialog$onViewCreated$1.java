package org.videolan.vlc.gui.dialogs;

import androidx.paging.PagedList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.DummyItem;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.SimpleAdapter;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001e\u0010\u0002\u001a\u001a\u0012\u0006\b\u0001\u0012\u00020\u0004 \u0005*\f\u0012\u0006\b\u0001\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Landroidx/paging/PagedList;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AddToGroupDialog.kt */
final class AddToGroupDialog$onViewCreated$1 extends Lambda implements Function1<PagedList<? extends MediaLibraryItem>, Unit> {
    final /* synthetic */ AddToGroupDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AddToGroupDialog$onViewCreated$1(AddToGroupDialog addToGroupDialog) {
        super(1);
        this.this$0 = addToGroupDialog;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PagedList<? extends MediaLibraryItem>) (PagedList) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(PagedList<? extends MediaLibraryItem> pagedList) {
        Intrinsics.checkNotNull(pagedList);
        Collection arrayList = new ArrayList();
        for (Object next : pagedList) {
            MediaLibraryItem mediaLibraryItem = (MediaLibraryItem) next;
            if ((mediaLibraryItem instanceof VideoGroup) && ((VideoGroup) mediaLibraryItem).mediaCount() > 1) {
                arrayList.add(next);
            }
        }
        List<MediaLibraryItem> list = (List) arrayList;
        AddToGroupDialog addToGroupDialog = this.this$0;
        for (MediaLibraryItem mediaLibraryItem2 : list) {
            mediaLibraryItem2.setDescription(addToGroupDialog.getResources().getQuantityString(R.plurals.media_quantity, mediaLibraryItem2.getTracksCount(), new Object[]{Integer.valueOf(mediaLibraryItem2.getTracksCount())}));
        }
        List mutableList = CollectionsKt.toMutableList(list);
        AddToGroupDialog addToGroupDialog2 = this.this$0;
        MediaWrapper[] access$getNewTrack$p = addToGroupDialog2.newTrack;
        SimpleAdapter simpleAdapter = null;
        if (access$getNewTrack$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("newTrack");
            access$getNewTrack$p = null;
        }
        if (access$getNewTrack$p.length > 1 && !addToGroupDialog2.forbidNewGroup) {
            mutableList.add(0, new DummyItem(0, addToGroupDialog2.getString(R.string.new_group), addToGroupDialog2.getString(R.string.new_group_desc)));
        }
        SimpleAdapter access$getAdapter$p = this.this$0.adapter;
        if (access$getAdapter$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            simpleAdapter = access$getAdapter$p;
        }
        simpleAdapter.submitList(mutableList);
        this.this$0.updateEmptyView(mutableList.isEmpty());
    }
}
