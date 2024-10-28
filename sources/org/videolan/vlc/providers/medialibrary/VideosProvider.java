package org.videolan.vlc.providers.medialibrary;

import android.content.Context;
import androidx.lifecycle.ViewModelKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.tools.Settings;
import org.videolan.vlc.media.MediaUtilsKt;
import org.videolan.vlc.viewmodels.SortableModel;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B)\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0016J\u0013\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0016H\u0016¢\u0006\u0002\u0010\u0017J#\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0002\u0010\u001cJ\b\u0010\u001d\u001a\u00020\u001aH\u0016R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001e"}, d2 = {"Lorg/videolan/vlc/providers/medialibrary/VideosProvider;", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "folder", "Lorg/videolan/medialibrary/interfaces/media/Folder;", "group", "Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "context", "Landroid/content/Context;", "model", "Lorg/videolan/vlc/viewmodels/SortableModel;", "(Lorg/videolan/medialibrary/interfaces/media/Folder;Lorg/videolan/medialibrary/interfaces/media/VideoGroup;Landroid/content/Context;Lorg/videolan/vlc/viewmodels/SortableModel;)V", "getFolder", "()Lorg/videolan/medialibrary/interfaces/media/Folder;", "getGroup", "()Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "canSortByDuration", "", "canSortByFileNameName", "canSortByInsertionDate", "canSortByLastModified", "getAll", "", "()[Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getPage", "loadSize", "", "startposition", "(II)[Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getTotalCount", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideosProvider.kt */
public final class VideosProvider extends MedialibraryProvider<MediaWrapper> {
    private final Folder folder;
    private final VideoGroup group;

    public boolean canSortByDuration() {
        return true;
    }

    public boolean canSortByFileNameName() {
        return true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VideosProvider(Folder folder2, VideoGroup videoGroup, Context context, SortableModel sortableModel) {
        super(context, sortableModel);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sortableModel, "model");
        this.folder = folder2;
        this.group = videoGroup;
    }

    public final Folder getFolder() {
        return this.folder;
    }

    public final VideoGroup getGroup() {
        return this.group;
    }

    public boolean canSortByLastModified() {
        return this.folder == null;
    }

    public boolean canSortByInsertionDate() {
        return this.group == null;
    }

    public int getTotalCount() {
        if (getModel().getFilterQuery() == null) {
            Folder folder2 = this.folder;
            if (folder2 != null) {
                return folder2.mediaCount(Folder.TYPE_FOLDER_VIDEO);
            }
            VideoGroup videoGroup = this.group;
            if (videoGroup != null) {
                return videoGroup.mediaCount();
            }
            return getMedialibrary().getVideoCount();
        }
        Folder folder3 = this.folder;
        if (folder3 != null) {
            return folder3.searchTracksCount(getModel().getFilterQuery(), Folder.TYPE_FOLDER_VIDEO);
        }
        VideoGroup videoGroup2 = this.group;
        if (videoGroup2 != null) {
            return videoGroup2.searchTracksCount(getModel().getFilterQuery());
        }
        return getMedialibrary().getVideoCount(getModel().getFilterQuery());
    }

    public MediaWrapper[] getPage(int i, int i2) {
        MediaWrapper[] mediaWrapperArr;
        if (getModel().getFilterQuery() == null) {
            Folder folder2 = this.folder;
            if (folder2 != null) {
                mediaWrapperArr = folder2.media(Folder.TYPE_FOLDER_VIDEO, getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            } else {
                VideoGroup videoGroup = this.group;
                if (videoGroup != null) {
                    mediaWrapperArr = videoGroup.media(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
                } else {
                    mediaWrapperArr = getMedialibrary().getPagedVideos(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
                }
            }
        } else {
            Folder folder3 = this.folder;
            if (folder3 != null) {
                mediaWrapperArr = folder3.searchTracks(getModel().getFilterQuery(), Folder.TYPE_FOLDER_VIDEO, getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            } else {
                VideoGroup videoGroup2 = this.group;
                if (videoGroup2 != null) {
                    mediaWrapperArr = videoGroup2.searchTracks(getModel().getFilterQuery(), getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
                } else {
                    mediaWrapperArr = getMedialibrary().searchVideo(getModel().getFilterQuery(), getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
                }
            }
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(getModel()), (CoroutineContext) null, (CoroutineStart) null, new VideosProvider$getPage$1(this, mediaWrapperArr, i2, (Continuation<? super VideosProvider$getPage$1>) null), 3, (Object) null);
        Intrinsics.checkNotNull(mediaWrapperArr);
        return mediaWrapperArr;
    }

    public MediaWrapper[] getAll() {
        Folder folder2 = this.folder;
        if (folder2 != null) {
            return (MediaWrapper[]) MediaUtilsKt.getAll$default(folder2, Folder.TYPE_FOLDER_VIDEO, getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), false, 16, (Object) null).toArray(new MediaWrapper[0]);
        }
        VideoGroup videoGroup = this.group;
        if (videoGroup != null) {
            return (MediaWrapper[]) MediaUtilsKt.getAll(videoGroup, getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites()).toArray(new MediaWrapper[0]);
        }
        MediaWrapper[] videos = getMedialibrary().getVideos(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites());
        Intrinsics.checkNotNullExpressionValue(videos, "getVideos(...)");
        return videos;
    }
}
