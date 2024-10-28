package org.videolan.moviepedia.repository;

import android.content.Context;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.moviepedia.database.PersonDao;
import org.videolan.moviepedia.database.models.Person;
import org.videolan.tools.IOScopedObject;
import org.videolan.tools.SingletonHolder;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bJ\u0014\u0010\u000b\u001a\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\rJ\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/videolan/moviepedia/repository/PersonRepository;", "Lorg/videolan/tools/IOScopedObject;", "personDao", "Lorg/videolan/moviepedia/database/PersonDao;", "(Lorg/videolan/moviepedia/database/PersonDao;)V", "addPerson", "Lkotlinx/coroutines/Job;", "person", "Lorg/videolan/moviepedia/database/models/Person;", "addPersonImmediate", "", "deleteAll", "personsToRemove", "", "getAll", "Companion", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PersonRepository.kt */
public final class PersonRepository extends IOScopedObject {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final PersonDao personDao;

    public PersonRepository(PersonDao personDao2) {
        Intrinsics.checkNotNullParameter(personDao2, "personDao");
        this.personDao = personDao2;
    }

    public final Job addPerson(Person person) {
        Intrinsics.checkNotNullParameter(person, "person");
        return BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PersonRepository$addPerson$1(this, person, (Continuation<? super PersonRepository$addPerson$1>) null), 3, (Object) null);
    }

    public final void addPersonImmediate(Person person) {
        Intrinsics.checkNotNullParameter(person, "person");
        this.personDao.insert(person);
    }

    public final List<Person> getAll() {
        return this.personDao.getAll();
    }

    public final void deleteAll(List<Person> list) {
        Intrinsics.checkNotNullParameter(list, "personsToRemove");
        this.personDao.deleteAll(list);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/moviepedia/repository/PersonRepository$Companion;", "Lorg/videolan/tools/SingletonHolder;", "Lorg/videolan/moviepedia/repository/PersonRepository;", "Landroid/content/Context;", "()V", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PersonRepository.kt */
    public static final class Companion extends SingletonHolder<PersonRepository, Context> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
            super(AnonymousClass1.INSTANCE);
        }
    }
}
