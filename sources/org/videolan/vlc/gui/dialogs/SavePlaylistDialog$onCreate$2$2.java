package org.videolan.vlc.gui.dialogs;

import android.content.res.Resources;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.SequencesKt;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "mediaLibraryItems", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: SavePlaylistDialog.kt */
final class SavePlaylistDialog$onCreate$2$2 extends Lambda implements Function1<List<MediaLibraryItem>, Unit> {
    final /* synthetic */ SavePlaylistDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SavePlaylistDialog$onCreate$2$2(SavePlaylistDialog savePlaylistDialog) {
        super(1);
        this.this$0 = savePlaylistDialog;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<MediaLibraryItem>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<MediaLibraryItem> list) {
        SavePlaylistDialog savePlaylistDialog = this.this$0;
        Intrinsics.checkNotNull(list);
        savePlaylistDialog.newTracks = (MediaWrapper[]) SequencesKt.toList(SequencesKt.filter(SequencesKt.map(CollectionsKt.asSequence(list), AnonymousClass1.INSTANCE), AnonymousClass2.INSTANCE)).toArray(new MediaWrapper[0]);
        this.this$0.setLoading(false);
        SavePlaylistDialog savePlaylistDialog2 = this.this$0;
        Resources resources = savePlaylistDialog2.getResources();
        int i = R.plurals.media_quantity;
        MediaWrapper[] access$getNewTracks$p = this.this$0.newTracks;
        MediaWrapper[] mediaWrapperArr = null;
        if (access$getNewTracks$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("newTracks");
            access$getNewTracks$p = null;
        }
        int length = access$getNewTracks$p.length;
        MediaWrapper[] access$getNewTracks$p2 = this.this$0.newTracks;
        if (access$getNewTracks$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("newTracks");
        } else {
            mediaWrapperArr = access$getNewTracks$p2;
        }
        String quantityString = resources.getQuantityString(i, length, new Object[]{Integer.valueOf(mediaWrapperArr.length)});
        Intrinsics.checkNotNullExpressionValue(quantityString, "getQuantityString(...)");
        savePlaylistDialog2.setFilesText(quantityString);
    }
}
