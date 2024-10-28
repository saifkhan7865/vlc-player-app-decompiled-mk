package org.videolan.moviepedia.database;

import java.util.List;
import kotlin.Metadata;
import org.videolan.moviepedia.database.models.Person;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H'J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H'J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH'J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0006H'¨\u0006\f"}, d2 = {"Lorg/videolan/moviepedia/database/PersonDao;", "", "deleteAll", "", "person", "", "Lorg/videolan/moviepedia/database/models/Person;", "getAll", "getPerson", "id", "", "insert", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PersonDao.kt */
public interface PersonDao {
    void deleteAll(List<Person> list);

    List<Person> getAll();

    Person getPerson(String str);

    void insert(Person person);
}
