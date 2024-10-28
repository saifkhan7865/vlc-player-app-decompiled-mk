package org.videolan.moviepedia.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.database.MediaPersonJoinDao;
import org.videolan.moviepedia.database.models.MediaPersonJoin;
import org.videolan.moviepedia.database.models.Person;
import org.videolan.moviepedia.database.models.PersonType;
import org.videolan.tools.IOScopedObject;
import org.videolan.tools.SingletonHolder;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\"\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\b0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/videolan/moviepedia/repository/MediaPersonRepository;", "Lorg/videolan/tools/IOScopedObject;", "mediaPersonActorJoinDao", "Lorg/videolan/moviepedia/database/MediaPersonJoinDao;", "(Lorg/videolan/moviepedia/database/MediaPersonJoinDao;)V", "addPersons", "", "mediaPersons", "", "Lorg/videolan/moviepedia/database/models/MediaPersonJoin;", "getAll", "getPersonsByType", "Landroidx/lifecycle/LiveData;", "Lorg/videolan/moviepedia/database/models/Person;", "moviepediaId", "", "personType", "Lorg/videolan/moviepedia/database/models/PersonType;", "removeAllFor", "Companion", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaPersonRepository.kt */
public final class MediaPersonRepository extends IOScopedObject {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final MediaPersonJoinDao mediaPersonActorJoinDao;

    public MediaPersonRepository(MediaPersonJoinDao mediaPersonJoinDao) {
        Intrinsics.checkNotNullParameter(mediaPersonJoinDao, "mediaPersonActorJoinDao");
        this.mediaPersonActorJoinDao = mediaPersonJoinDao;
    }

    public final void addPersons(List<MediaPersonJoin> list) {
        Intrinsics.checkNotNullParameter(list, "mediaPersons");
        this.mediaPersonActorJoinDao.insertPersons(list);
    }

    public final void removeAllFor(String str) {
        Intrinsics.checkNotNullParameter(str, "moviepediaId");
        this.mediaPersonActorJoinDao.removeAllFor(str);
    }

    public final List<MediaPersonJoin> getAll() {
        return this.mediaPersonActorJoinDao.getAll();
    }

    public final LiveData<List<Person>> getPersonsByType(String str, PersonType personType) {
        Intrinsics.checkNotNullParameter(str, "moviepediaId");
        Intrinsics.checkNotNullParameter(personType, "personType");
        return this.mediaPersonActorJoinDao.getActorsForMediaLive(str, personType);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/moviepedia/repository/MediaPersonRepository$Companion;", "Lorg/videolan/tools/SingletonHolder;", "Lorg/videolan/moviepedia/repository/MediaPersonRepository;", "Landroid/content/Context;", "()V", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaPersonRepository.kt */
    public static final class Companion extends SingletonHolder<MediaPersonRepository, Context> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
            super(AnonymousClass1.INSTANCE);
        }
    }
}
