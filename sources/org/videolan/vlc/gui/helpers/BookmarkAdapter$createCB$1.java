package org.videolan.vlc.gui.helpers;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.Bookmark;
import org.videolan.vlc.gui.DiffUtilAdapter;

@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J(\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00060\nj\b\u0012\u0004\u0012\u00020\u0006`\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\f"}, d2 = {"org/videolan/vlc/gui/helpers/BookmarkAdapter$createCB$1", "Lorg/videolan/vlc/gui/DiffUtilAdapter$DiffCallback;", "Lorg/videolan/medialibrary/interfaces/media/Bookmark;", "areContentsTheSame", "", "oldItemPosition", "", "newItemPosition", "areItemsTheSame", "getChangePayload", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BookmarkAdapter.kt */
public final class BookmarkAdapter$createCB$1 extends DiffUtilAdapter.DiffCallback<Bookmark> {
    BookmarkAdapter$createCB$1() {
    }

    public boolean areContentsTheSame(int i, int i2) {
        return Intrinsics.areEqual((Object) ((Bookmark) getOldList().get(i)).getTitle(), (Object) ((Bookmark) getNewList().get(i2)).getTitle()) && ((Bookmark) getOldList().get(i)).getTime() == ((Bookmark) getNewList().get(i2)).getTime();
    }

    public boolean areItemsTheSame(int i, int i2) {
        try {
            return Intrinsics.areEqual(getOldList().get(i), getNewList().get(i2));
        } catch (Exception unused) {
            return false;
        }
    }

    public ArrayList<Integer> getChangePayload(int i, int i2) {
        return CollectionsKt.arrayListOf(5);
    }
}
