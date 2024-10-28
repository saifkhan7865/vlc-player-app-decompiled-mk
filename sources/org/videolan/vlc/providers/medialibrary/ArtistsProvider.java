package org.videolan.vlc.providers.medialibrary;

import android.content.Context;
import androidx.lifecycle.ViewModelKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.tools.Settings;
import org.videolan.vlc.viewmodels.SortableModel;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0013\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000fH\u0016¢\u0006\u0002\u0010\u0010J#\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0013H\u0016R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lorg/videolan/vlc/providers/medialibrary/ArtistsProvider;", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "Lorg/videolan/medialibrary/interfaces/media/Artist;", "context", "Landroid/content/Context;", "model", "Lorg/videolan/vlc/viewmodels/SortableModel;", "showAll", "", "(Landroid/content/Context;Lorg/videolan/vlc/viewmodels/SortableModel;Z)V", "getShowAll", "()Z", "setShowAll", "(Z)V", "getAll", "", "()[Lorg/videolan/medialibrary/interfaces/media/Artist;", "getPage", "loadSize", "", "startposition", "(II)[Lorg/videolan/medialibrary/interfaces/media/Artist;", "getTotalCount", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ArtistsProvider.kt */
public final class ArtistsProvider extends MedialibraryProvider<Artist> {
    private boolean showAll;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ArtistsProvider(Context context, SortableModel sortableModel, boolean z) {
        super(context, sortableModel);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sortableModel, "model");
        this.showAll = z;
    }

    public final boolean getShowAll() {
        return this.showAll;
    }

    public final void setShowAll(boolean z) {
        this.showAll = z;
    }

    public Artist[] getAll() {
        Artist[] artists = getMedialibrary().getArtists(this.showAll, getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites());
        Intrinsics.checkNotNullExpressionValue(artists, "getArtists(...)");
        return artists;
    }

    public Artist[] getPage(int i, int i2) {
        Artist[] artistArr;
        if (getModel().getFilterQuery() == null) {
            artistArr = getMedialibrary().getPagedArtists(this.showAll, getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
        } else {
            artistArr = getMedialibrary().searchArtist(getModel().getFilterQuery(), getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(getModel()), (CoroutineContext) null, (CoroutineStart) null, new ArtistsProvider$getPage$1(this, artistArr, i2, (Continuation<? super ArtistsProvider$getPage$1>) null), 3, (Object) null);
        Intrinsics.checkNotNull(artistArr);
        return artistArr;
    }

    public int getTotalCount() {
        if (getModel().getFilterQuery() == null) {
            return getMedialibrary().getArtistsCount(this.showAll);
        }
        return getMedialibrary().getArtistsCount(getModel().getFilterQuery());
    }
}
