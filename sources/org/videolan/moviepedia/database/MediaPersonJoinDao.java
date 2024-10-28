package org.videolan.moviepedia.database;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.videolan.moviepedia.database.models.MediaPersonJoin;
import org.videolan.moviepedia.database.models.Person;
import org.videolan.moviepedia.database.models.PersonType;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH'J$\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH'J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003H'J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fH'J\u0016\u0010\u0010\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0003H'J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006H'¨\u0006\u0013"}, d2 = {"Lorg/videolan/moviepedia/database/MediaPersonJoinDao;", "", "getActorsForMedia", "", "Lorg/videolan/moviepedia/database/models/Person;", "moviepediaId", "", "type", "Lorg/videolan/moviepedia/database/models/PersonType;", "getActorsForMediaLive", "Landroidx/lifecycle/LiveData;", "getAll", "Lorg/videolan/moviepedia/database/models/MediaPersonJoin;", "insertPerson", "", "person", "insertPersons", "persons", "removeAllFor", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaPersonJoinDao.kt */
public interface MediaPersonJoinDao {
    List<Person> getActorsForMedia(String str, PersonType personType);

    LiveData<List<Person>> getActorsForMediaLive(String str, PersonType personType);

    List<MediaPersonJoin> getAll();

    void insertPerson(MediaPersonJoin mediaPersonJoin);

    void insertPersons(List<MediaPersonJoin> list);

    void removeAllFor(String str);
}
