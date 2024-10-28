package org.videolan.vlc.providers.medialibrary;

import android.content.Context;
import androidx.lifecycle.ViewModelKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.Settings;
import org.videolan.vlc.viewmodels.SortableModel;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016J\b\u0010\f\u001a\u00020\tH\u0016J\u0013\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016¢\u0006\u0002\u0010\u0010J#\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0013H\u0016¨\u0006\u0017"}, d2 = {"Lorg/videolan/vlc/providers/medialibrary/VideoGroupsProvider;", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "context", "Landroid/content/Context;", "model", "Lorg/videolan/vlc/viewmodels/SortableModel;", "(Landroid/content/Context;Lorg/videolan/vlc/viewmodels/SortableModel;)V", "canSortByDuration", "", "canSortByInsertionDate", "canSortByLastModified", "canSortByMediaNumber", "getAll", "", "Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "()[Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "getPage", "loadSize", "", "startposition", "(II)[Lorg/videolan/medialibrary/media/MediaLibraryItem;", "getTotalCount", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoGroupsProvider.kt */
public final class VideoGroupsProvider extends MedialibraryProvider<MediaLibraryItem> {
    public boolean canSortByDuration() {
        return true;
    }

    public boolean canSortByInsertionDate() {
        return true;
    }

    public boolean canSortByLastModified() {
        return true;
    }

    public boolean canSortByMediaNumber() {
        return true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VideoGroupsProvider(Context context, SortableModel sortableModel) {
        super(context, sortableModel);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sortableModel, "model");
    }

    public VideoGroup[] getAll() {
        VideoGroup[] videoGroups = getMedialibrary().getVideoGroups(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), getTotalCount(), 0);
        Intrinsics.checkNotNullExpressionValue(videoGroups, "getVideoGroups(...)");
        return videoGroups;
    }

    public int getTotalCount() {
        return getMedialibrary().getVideoGroupsCount(getModel().getFilterQuery());
    }

    public MediaLibraryItem[] getPage(int i, int i2) {
        VideoGroup[] videoGroupArr;
        CharSequence filterQuery = getModel().getFilterQuery();
        if (filterQuery == null || filterQuery.length() == 0) {
            videoGroupArr = getMedialibrary().getVideoGroups(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
        } else {
            videoGroupArr = getMedialibrary().searchVideoGroups(getModel().getFilterQuery(), getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
        }
        Intrinsics.checkNotNull(videoGroupArr);
        MediaLibraryItem[] sanitizeGroups = VideoGroupsProviderKt.sanitizeGroups(videoGroupArr);
        if (Settings.INSTANCE.getShowTvUi()) {
            completeHeaders(sanitizeGroups, i2);
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(getModel()), (CoroutineContext) null, (CoroutineStart) null, new VideoGroupsProvider$getPage$1(this, sanitizeGroups, i2, (Continuation<? super VideoGroupsProvider$getPage$1>) null), 3, (Object) null);
        return sanitizeGroups;
    }
}
