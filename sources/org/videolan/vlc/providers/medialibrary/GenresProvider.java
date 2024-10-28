package org.videolan.vlc.providers.medialibrary;

import android.content.Context;
import androidx.lifecycle.ViewModelKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.tools.Settings;
import org.videolan.vlc.viewmodels.SortableModel;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0016¢\u0006\u0002\u0010\nJ#\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\rH\u0016¨\u0006\u0011"}, d2 = {"Lorg/videolan/vlc/providers/medialibrary/GenresProvider;", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "Lorg/videolan/medialibrary/interfaces/media/Genre;", "context", "Landroid/content/Context;", "model", "Lorg/videolan/vlc/viewmodels/SortableModel;", "(Landroid/content/Context;Lorg/videolan/vlc/viewmodels/SortableModel;)V", "getAll", "", "()[Lorg/videolan/medialibrary/interfaces/media/Genre;", "getPage", "loadSize", "", "startposition", "(II)[Lorg/videolan/medialibrary/interfaces/media/Genre;", "getTotalCount", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: GenresProvider.kt */
public final class GenresProvider extends MedialibraryProvider<Genre> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GenresProvider(Context context, SortableModel sortableModel) {
        super(context, sortableModel);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sortableModel, "model");
    }

    public Genre[] getAll() {
        Genre[] genres = getMedialibrary().getGenres(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites());
        Intrinsics.checkNotNullExpressionValue(genres, "getGenres(...)");
        return genres;
    }

    public Genre[] getPage(int i, int i2) {
        Genre[] genreArr;
        if (getModel().getFilterQuery() == null) {
            genreArr = getMedialibrary().getPagedGenres(getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
        } else {
            genreArr = getMedialibrary().searchGenre(getModel().getFilterQuery(), getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(getModel()), (CoroutineContext) null, (CoroutineStart) null, new GenresProvider$getPage$1(this, genreArr, i2, (Continuation<? super GenresProvider$getPage$1>) null), 3, (Object) null);
        Intrinsics.checkNotNull(genreArr);
        return genreArr;
    }

    public int getTotalCount() {
        return getModel().getFilterQuery() == null ? getMedialibrary().getGenresCount() : getMedialibrary().getGenresCount(getModel().getFilterQuery());
    }
}
