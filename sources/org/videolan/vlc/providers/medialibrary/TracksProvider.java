package org.videolan.vlc.providers.medialibrary;

import androidx.lifecycle.ViewModelKt;
import androidx.paging.PagedList;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.Settings;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0016J\b\u0010\u0015\u001a\u00020\u0011H\u0016J\b\u0010\u0016\u001a\u00020\u0011H\u0016J\b\u0010\u0017\u001a\u00020\u0011H\u0016J\b\u0010\u0018\u001a\u00020\u0011H\u0016J\u0013\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u001aH\u0016¢\u0006\u0002\u0010\u001bJ#\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0002\u0010 J\b\u0010!\u001a\u00020\u001eH\u0016R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lorg/videolan/vlc/providers/medialibrary/TracksProvider;", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "parent", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "context", "Landroid/content/Context;", "model", "Lorg/videolan/vlc/viewmodels/SortableModel;", "(Lorg/videolan/medialibrary/media/MediaLibraryItem;Landroid/content/Context;Lorg/videolan/vlc/viewmodels/SortableModel;)V", "getParent", "()Lorg/videolan/medialibrary/media/MediaLibraryItem;", "sortKey", "", "getSortKey", "()Ljava/lang/String;", "canSortByAlbum", "", "canSortByDuration", "canSortByFileNameName", "canSortByInsertionDate", "canSortByLastModified", "canSortByName", "canSortByReleaseDate", "canSortByTrackId", "getAll", "", "()[Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getPage", "loadSize", "", "startposition", "(II)[Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getTotalCount", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TracksProvider.kt */
public final class TracksProvider extends MedialibraryProvider<MediaWrapper> {
    private final MediaLibraryItem parent;
    private final String sortKey;

    public boolean canSortByInsertionDate() {
        return true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0022, code lost:
        r0 = r4.getClass();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TracksProvider(org.videolan.medialibrary.media.MediaLibraryItem r4, android.content.Context r5, org.videolan.vlc.viewmodels.SortableModel r6) {
        /*
            r3 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "model"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            r3.<init>(r5, r6)
            r3.parent = r4
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = super.getSortKey()
            r6.append(r0)
            r0 = 95
            r6.append(r0)
            if (r4 == 0) goto L_0x002d
            java.lang.Class r0 = r4.getClass()
            if (r0 == 0) goto L_0x002d
            java.lang.String r0 = r0.getSimpleName()
            goto L_0x002e
        L_0x002d:
            r0 = 0
        L_0x002e:
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            r3.sortKey = r6
            org.videolan.tools.Settings r6 = org.videolan.tools.Settings.INSTANCE
            java.lang.Object r6 = r6.getInstance(r5)
            android.content.SharedPreferences r6 = (android.content.SharedPreferences) r6
            java.lang.String r0 = r3.getSortKey()
            r1 = 0
            int r6 = r6.getInt(r0, r1)
            r3.setSort(r6)
            org.videolan.tools.Settings r6 = org.videolan.tools.Settings.INSTANCE
            java.lang.Object r6 = r6.getInstance(r5)
            android.content.SharedPreferences r6 = (android.content.SharedPreferences) r6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r3.getSortKey()
            r0.append(r2)
            java.lang.String r2 = "_desc"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            boolean r2 = r4 instanceof org.videolan.medialibrary.interfaces.media.Artist
            boolean r6 = r6.getBoolean(r0, r2)
            r3.setDesc(r6)
            org.videolan.tools.Settings r6 = org.videolan.tools.Settings.INSTANCE
            java.lang.Object r5 = r6.getInstance(r5)
            android.content.SharedPreferences r5 = (android.content.SharedPreferences) r5
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = r3.getSortKey()
            r6.append(r0)
            java.lang.String r0 = "_only_favs"
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            boolean r5 = r5.getBoolean(r6, r1)
            r3.setOnlyFavorites(r5)
            int r5 = r3.getSort()
            if (r5 != 0) goto L_0x00ad
            boolean r5 = r4 instanceof org.videolan.medialibrary.interfaces.media.Artist
            if (r5 == 0) goto L_0x00a2
            r4 = 9
            goto L_0x00aa
        L_0x00a2:
            boolean r4 = r4 instanceof org.videolan.medialibrary.interfaces.media.Album
            if (r4 == 0) goto L_0x00a9
            r4 = 12
            goto L_0x00aa
        L_0x00a9:
            r4 = 1
        L_0x00aa:
            r3.setSort(r4)
        L_0x00ad:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.providers.medialibrary.TracksProvider.<init>(org.videolan.medialibrary.media.MediaLibraryItem, android.content.Context, org.videolan.vlc.viewmodels.SortableModel):void");
    }

    public final MediaLibraryItem getParent() {
        return this.parent;
    }

    /* access modifiers changed from: protected */
    public String getSortKey() {
        return this.sortKey;
    }

    public boolean canSortByDuration() {
        return !(this.parent instanceof Playlist);
    }

    public boolean canSortByAlbum() {
        MediaLibraryItem mediaLibraryItem = this.parent;
        return mediaLibraryItem != null && !(mediaLibraryItem instanceof Album) && !(mediaLibraryItem instanceof Playlist);
    }

    public boolean canSortByLastModified() {
        return !(this.parent instanceof Playlist);
    }

    public boolean canSortByReleaseDate() {
        return !(this.parent instanceof Playlist);
    }

    public boolean canSortByName() {
        return !(this.parent instanceof Playlist);
    }

    public boolean canSortByFileNameName() {
        return !(this.parent instanceof Playlist);
    }

    public boolean canSortByTrackId() {
        return this.parent instanceof Album;
    }

    public MediaWrapper[] getAll() {
        MediaWrapper[] mediaWrapperArr;
        PagedList pagedList = (PagedList) getPagedList().getValue();
        if (pagedList != null && (mediaWrapperArr = (MediaWrapper[]) pagedList.toArray(new MediaWrapper[0])) != null) {
            return mediaWrapperArr;
        }
        MediaWrapper[] audio = getMedialibrary().getAudio(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites());
        Intrinsics.checkNotNullExpressionValue(audio, "getAudio(...)");
        return audio;
    }

    public MediaWrapper[] getPage(int i, int i2) {
        MediaWrapper[] mediaWrapperArr;
        if (getModel().getFilterQuery() == null) {
            MediaLibraryItem mediaLibraryItem = this.parent;
            if (mediaLibraryItem instanceof Artist) {
                mediaWrapperArr = ((Artist) mediaLibraryItem).getPagedTracks(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            } else if (mediaLibraryItem instanceof Album) {
                mediaWrapperArr = ((Album) mediaLibraryItem).getPagedTracks(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            } else if (mediaLibraryItem instanceof Genre) {
                mediaWrapperArr = ((Genre) mediaLibraryItem).getPagedTracks(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            } else if (mediaLibraryItem instanceof Playlist) {
                mediaWrapperArr = ((Playlist) mediaLibraryItem).getPagedTracks(i, i2, Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites());
            } else {
                mediaWrapperArr = getMedialibrary().getPagedAudio(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            }
        } else {
            MediaLibraryItem mediaLibraryItem2 = this.parent;
            if (mediaLibraryItem2 instanceof Artist) {
                mediaWrapperArr = ((Artist) mediaLibraryItem2).searchTracks(getModel().getFilterQuery(), getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            } else if (mediaLibraryItem2 instanceof Album) {
                mediaWrapperArr = ((Album) mediaLibraryItem2).searchTracks(getModel().getFilterQuery(), getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            } else if (mediaLibraryItem2 instanceof Genre) {
                mediaWrapperArr = ((Genre) mediaLibraryItem2).searchTracks(getModel().getFilterQuery(), getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            } else if (mediaLibraryItem2 instanceof Playlist) {
                mediaWrapperArr = ((Playlist) mediaLibraryItem2).searchTracks(getModel().getFilterQuery(), getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            } else {
                mediaWrapperArr = getMedialibrary().searchAudio(getModel().getFilterQuery(), getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            }
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(getModel()), (CoroutineContext) null, (CoroutineStart) null, new TracksProvider$getPage$1(this, mediaWrapperArr, i2, (Continuation<? super TracksProvider$getPage$1>) null), 3, (Object) null);
        Intrinsics.checkNotNull(mediaWrapperArr);
        return mediaWrapperArr;
    }

    public int getTotalCount() {
        if (getModel().getFilterQuery() == null) {
            MediaLibraryItem mediaLibraryItem = this.parent;
            if (mediaLibraryItem instanceof Album) {
                return ((Album) mediaLibraryItem).getRealTracksCount();
            }
            if (mediaLibraryItem instanceof Playlist) {
                return ((Playlist) mediaLibraryItem).getRealTracksCount(Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites());
            }
            if (!(mediaLibraryItem instanceof Artist) && !(mediaLibraryItem instanceof Genre)) {
                return getMedialibrary().getAudioCount();
            }
            return mediaLibraryItem.getTracksCount();
        }
        MediaLibraryItem mediaLibraryItem2 = this.parent;
        if (mediaLibraryItem2 instanceof Artist) {
            return ((Artist) mediaLibraryItem2).searchTracksCount(getModel().getFilterQuery());
        }
        if (mediaLibraryItem2 instanceof Album) {
            return ((Album) mediaLibraryItem2).searchTracksCount(getModel().getFilterQuery());
        }
        if (mediaLibraryItem2 instanceof Genre) {
            return ((Genre) mediaLibraryItem2).searchTracksCount(getModel().getFilterQuery());
        }
        if (mediaLibraryItem2 instanceof Playlist) {
            return ((Playlist) mediaLibraryItem2).searchTracksCount(getModel().getFilterQuery());
        }
        return getMedialibrary().getAudioCount(getModel().getFilterQuery());
    }
}
