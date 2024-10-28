package org.videolan.vlc.providers.medialibrary;

import androidx.lifecycle.ViewModelKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.Settings;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0016J\u0013\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0016H\u0016¢\u0006\u0002\u0010\u0017J#\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0002\u0010\u001cJ\b\u0010\u001d\u001a\u00020\u001aH\u0016R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001e"}, d2 = {"Lorg/videolan/vlc/providers/medialibrary/AlbumsProvider;", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "Lorg/videolan/medialibrary/interfaces/media/Album;", "parent", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "context", "Landroid/content/Context;", "model", "Lorg/videolan/vlc/viewmodels/SortableModel;", "(Lorg/videolan/medialibrary/media/MediaLibraryItem;Landroid/content/Context;Lorg/videolan/vlc/viewmodels/SortableModel;)V", "getParent", "()Lorg/videolan/medialibrary/media/MediaLibraryItem;", "sortKey", "", "getSortKey", "()Ljava/lang/String;", "canSortByArtist", "", "canSortByDuration", "canSortByInsertionDate", "canSortByReleaseDate", "getAll", "", "()[Lorg/videolan/medialibrary/interfaces/media/Album;", "getPage", "loadSize", "", "startposition", "(II)[Lorg/videolan/medialibrary/interfaces/media/Album;", "getTotalCount", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AlbumsProvider.kt */
public final class AlbumsProvider extends MedialibraryProvider<Album> {
    private final MediaLibraryItem parent;
    private final String sortKey;

    public boolean canSortByArtist() {
        return true;
    }

    public boolean canSortByDuration() {
        return true;
    }

    public boolean canSortByInsertionDate() {
        return true;
    }

    public boolean canSortByReleaseDate() {
        return true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0022, code lost:
        r0 = r3.getClass();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AlbumsProvider(org.videolan.medialibrary.media.MediaLibraryItem r3, android.content.Context r4, org.videolan.vlc.viewmodels.SortableModel r5) {
        /*
            r2 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "model"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            r2.<init>(r4, r5)
            r2.parent = r3
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = super.getSortKey()
            r5.append(r0)
            r0 = 95
            r5.append(r0)
            if (r3 == 0) goto L_0x002d
            java.lang.Class r0 = r3.getClass()
            if (r0 == 0) goto L_0x002d
            java.lang.String r0 = r0.getSimpleName()
            goto L_0x002e
        L_0x002d:
            r0 = 0
        L_0x002e:
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            r2.sortKey = r5
            org.videolan.tools.Settings r5 = org.videolan.tools.Settings.INSTANCE
            java.lang.Object r5 = r5.getInstance(r4)
            android.content.SharedPreferences r5 = (android.content.SharedPreferences) r5
            java.lang.String r0 = r2.getSortKey()
            boolean r3 = r3 instanceof org.videolan.medialibrary.interfaces.media.Artist
            r1 = 0
            if (r3 == 0) goto L_0x004a
            r3 = 5
            goto L_0x004b
        L_0x004a:
            r3 = 0
        L_0x004b:
            int r3 = r5.getInt(r0, r3)
            r2.setSort(r3)
            org.videolan.tools.Settings r3 = org.videolan.tools.Settings.INSTANCE
            java.lang.Object r3 = r3.getInstance(r4)
            android.content.SharedPreferences r3 = (android.content.SharedPreferences) r3
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = r2.getSortKey()
            r5.append(r0)
            java.lang.String r0 = "_desc"
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            boolean r3 = r3.getBoolean(r5, r1)
            r2.setDesc(r3)
            org.videolan.tools.Settings r3 = org.videolan.tools.Settings.INSTANCE
            java.lang.Object r3 = r3.getInstance(r4)
            android.content.SharedPreferences r3 = (android.content.SharedPreferences) r3
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r2.getSortKey()
            r4.append(r5)
            java.lang.String r5 = "_only_favs"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            boolean r3 = r3.getBoolean(r4, r1)
            r2.setOnlyFavorites(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.providers.medialibrary.AlbumsProvider.<init>(org.videolan.medialibrary.media.MediaLibraryItem, android.content.Context, org.videolan.vlc.viewmodels.SortableModel):void");
    }

    public final MediaLibraryItem getParent() {
        return this.parent;
    }

    /* access modifiers changed from: protected */
    public String getSortKey() {
        return this.sortKey;
    }

    public Album[] getAll() {
        MediaLibraryItem mediaLibraryItem = this.parent;
        if (mediaLibraryItem instanceof Artist) {
            Album[] albums = ((Artist) mediaLibraryItem).getAlbums(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites());
            Intrinsics.checkNotNullExpressionValue(albums, "getAlbums(...)");
            return albums;
        } else if (mediaLibraryItem instanceof Genre) {
            Album[] albums2 = ((Genre) mediaLibraryItem).getAlbums(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites());
            Intrinsics.checkNotNullExpressionValue(albums2, "getAlbums(...)");
            return albums2;
        } else {
            Album[] albums3 = getMedialibrary().getAlbums(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites());
            Intrinsics.checkNotNullExpressionValue(albums3, "getAlbums(...)");
            return albums3;
        }
    }

    public Album[] getPage(int i, int i2) {
        Album[] albumArr;
        if (getModel().getFilterQuery() == null) {
            MediaLibraryItem mediaLibraryItem = this.parent;
            if (mediaLibraryItem instanceof Artist) {
                albumArr = ((Artist) mediaLibraryItem).getPagedAlbums(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            } else if (mediaLibraryItem instanceof Genre) {
                albumArr = ((Genre) mediaLibraryItem).getPagedAlbums(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            } else {
                albumArr = getMedialibrary().getPagedAlbums(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            }
        } else {
            MediaLibraryItem mediaLibraryItem2 = this.parent;
            if (mediaLibraryItem2 instanceof Artist) {
                albumArr = ((Artist) mediaLibraryItem2).searchAlbums(getModel().getFilterQuery(), getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            } else if (mediaLibraryItem2 instanceof Genre) {
                albumArr = ((Genre) mediaLibraryItem2).searchAlbums(getModel().getFilterQuery(), getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            } else {
                albumArr = getMedialibrary().searchAlbum(getModel().getFilterQuery(), getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
            }
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(getModel()), (CoroutineContext) null, (CoroutineStart) null, new AlbumsProvider$getPage$1(this, albumArr, i2, (Continuation<? super AlbumsProvider$getPage$1>) null), 3, (Object) null);
        Intrinsics.checkNotNull(albumArr);
        return albumArr;
    }

    public int getTotalCount() {
        if (getModel().getFilterQuery() == null) {
            MediaLibraryItem mediaLibraryItem = this.parent;
            if (mediaLibraryItem instanceof Artist) {
                return ((Artist) mediaLibraryItem).getAlbumsCount();
            }
            if (mediaLibraryItem instanceof Genre) {
                return ((Genre) mediaLibraryItem).getAlbumsCount();
            }
            return getMedialibrary().getAlbumsCount();
        }
        MediaLibraryItem mediaLibraryItem2 = this.parent;
        if (mediaLibraryItem2 instanceof Artist) {
            return ((Artist) mediaLibraryItem2).searchAlbumsCount(getModel().getFilterQuery());
        }
        if (mediaLibraryItem2 instanceof Genre) {
            return ((Genre) mediaLibraryItem2).searchAlbumsCount(getModel().getFilterQuery());
        }
        return getMedialibrary().getAlbumsCount(getModel().getFilterQuery());
    }
}
