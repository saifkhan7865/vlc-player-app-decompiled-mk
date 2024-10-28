package org.videolan.vlc.providers.medialibrary;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.Settings;
import org.videolan.vlc.viewmodels.SortableModel;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0013\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016¢\u0006\u0002\u0010\u000eJ#\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0016¢\u0006\u0002\u0010\u0012J\b\u0010\u0013\u001a\u00020\bH\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lorg/videolan/vlc/providers/medialibrary/FoldersProvider;", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "Lorg/videolan/medialibrary/interfaces/media/Folder;", "context", "Landroid/content/Context;", "model", "Lorg/videolan/vlc/viewmodels/SortableModel;", "type", "", "(Landroid/content/Context;Lorg/videolan/vlc/viewmodels/SortableModel;I)V", "getType", "()I", "getAll", "", "()[Lorg/videolan/medialibrary/interfaces/media/Folder;", "getPage", "loadSize", "startposition", "(II)[Lorg/videolan/medialibrary/interfaces/media/Folder;", "getTotalCount", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FoldersProvider.kt */
public final class FoldersProvider extends MedialibraryProvider<Folder> {
    private final int type;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FoldersProvider(Context context, SortableModel sortableModel, int i) {
        super(context, sortableModel);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sortableModel, "model");
        this.type = i;
    }

    public final int getType() {
        return this.type;
    }

    public Folder[] getAll() {
        Folder[] folders = getMedialibrary().getFolders(this.type, getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), getTotalCount(), 0);
        Intrinsics.checkNotNullExpressionValue(folders, "getFolders(...)");
        return folders;
    }

    public int getTotalCount() {
        CharSequence filterQuery = getModel().getFilterQuery();
        return (filterQuery == null || filterQuery.length() == 0) ? getMedialibrary().getFoldersCount(this.type) : getMedialibrary().getFoldersCount(getModel().getFilterQuery());
    }

    public Folder[] getPage(int i, int i2) {
        Folder[] folderArr;
        CharSequence filterQuery = getModel().getFilterQuery();
        if (filterQuery == null || filterQuery.length() == 0) {
            folderArr = getMedialibrary().getFolders(this.type, getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            Intrinsics.checkNotNull(folderArr);
            completeHeaders((MediaLibraryItem[]) folderArr, i2);
        } else {
            folderArr = getMedialibrary().searchFolders(getModel().getFilterQuery(), getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
        }
        if (Settings.INSTANCE.getShowTvUi()) {
            Intrinsics.checkNotNull(folderArr);
            completeHeaders((MediaLibraryItem[]) folderArr, i2);
        }
        Intrinsics.checkNotNullExpressionValue(folderArr, "also(...)");
        return folderArr;
    }
}
