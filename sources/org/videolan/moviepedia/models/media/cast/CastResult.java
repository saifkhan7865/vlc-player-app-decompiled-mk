package org.videolan.moviepedia.models.media.cast;

import com.squareup.moshi.Json;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.models.resolver.ResolverCasting;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BU\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0003\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0003¢\u0006\u0002\u0010\rJ\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003H\u0016J\u0011\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0003HÆ\u0003Jc\u0010\u001b\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00032\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00032\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0003HÆ\u0001J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003H\u0016J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003H\u0016J\u000e\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003H\u0016J\t\u0010%\u001a\u00020&HÖ\u0001J\u000e\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003H\u0016R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u001e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u001e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f¨\u0006("}, d2 = {"Lorg/videolan/moviepedia/models/media/cast/CastResult;", "Lorg/videolan/moviepedia/models/resolver/ResolverCasting;", "actor", "", "Lorg/videolan/moviepedia/models/media/cast/Actor;", "director", "Lorg/videolan/moviepedia/models/media/cast/Director;", "musician", "Lorg/videolan/moviepedia/models/media/cast/Musician;", "producer", "Lorg/videolan/moviepedia/models/media/cast/Producer;", "writer", "Lorg/videolan/moviepedia/models/media/cast/Writer;", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getActor", "()Ljava/util/List;", "getDirector", "getMusician", "getProducer", "getWriter", "actors", "Lorg/videolan/moviepedia/models/media/cast/Person;", "component1", "component2", "component3", "component4", "component5", "copy", "directors", "equals", "", "other", "", "hashCode", "", "musicians", "producers", "toString", "", "writers", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CastResult.kt */
public final class CastResult extends ResolverCasting {
    @Json(name = "actor")
    private final List<Actor> actor;
    @Json(name = "director")
    private final List<Director> director;
    @Json(name = "musician")
    private final List<Musician> musician;
    @Json(name = "producer")
    private final List<Producer> producer;
    @Json(name = "writer")
    private final List<Writer> writer;

    public static /* synthetic */ CastResult copy$default(CastResult castResult, List<Actor> list, List<Director> list2, List<Musician> list3, List<Producer> list4, List<Writer> list5, int i, Object obj) {
        if ((i & 1) != 0) {
            list = castResult.actor;
        }
        if ((i & 2) != 0) {
            list2 = castResult.director;
        }
        List<Director> list6 = list2;
        if ((i & 4) != 0) {
            list3 = castResult.musician;
        }
        List<Musician> list7 = list3;
        if ((i & 8) != 0) {
            list4 = castResult.producer;
        }
        List<Producer> list8 = list4;
        if ((i & 16) != 0) {
            list5 = castResult.writer;
        }
        return castResult.copy(list, list6, list7, list8, list5);
    }

    public final List<Actor> component1() {
        return this.actor;
    }

    public final List<Director> component2() {
        return this.director;
    }

    public final List<Musician> component3() {
        return this.musician;
    }

    public final List<Producer> component4() {
        return this.producer;
    }

    public final List<Writer> component5() {
        return this.writer;
    }

    public final CastResult copy(List<Actor> list, List<Director> list2, List<Musician> list3, List<Producer> list4, List<Writer> list5) {
        return new CastResult(list, list2, list3, list4, list5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CastResult)) {
            return false;
        }
        CastResult castResult = (CastResult) obj;
        return Intrinsics.areEqual((Object) this.actor, (Object) castResult.actor) && Intrinsics.areEqual((Object) this.director, (Object) castResult.director) && Intrinsics.areEqual((Object) this.musician, (Object) castResult.musician) && Intrinsics.areEqual((Object) this.producer, (Object) castResult.producer) && Intrinsics.areEqual((Object) this.writer, (Object) castResult.writer);
    }

    public int hashCode() {
        List<Actor> list = this.actor;
        int i = 0;
        int hashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<Director> list2 = this.director;
        int hashCode2 = (hashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<Musician> list3 = this.musician;
        int hashCode3 = (hashCode2 + (list3 == null ? 0 : list3.hashCode())) * 31;
        List<Producer> list4 = this.producer;
        int hashCode4 = (hashCode3 + (list4 == null ? 0 : list4.hashCode())) * 31;
        List<Writer> list5 = this.writer;
        if (list5 != null) {
            i = list5.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        return "CastResult(actor=" + this.actor + ", director=" + this.director + ", musician=" + this.musician + ", producer=" + this.producer + ", writer=" + this.writer + ')';
    }

    public final List<Actor> getActor() {
        return this.actor;
    }

    public final List<Director> getDirector() {
        return this.director;
    }

    public final List<Musician> getMusician() {
        return this.musician;
    }

    public final List<Producer> getProducer() {
        return this.producer;
    }

    public final List<Writer> getWriter() {
        return this.writer;
    }

    public CastResult(List<Actor> list, List<Director> list2, List<Musician> list3, List<Producer> list4, List<Writer> list5) {
        this.actor = list;
        this.director = list2;
        this.musician = list3;
        this.producer = list4;
        this.writer = list5;
    }

    public List<Person> actors() {
        List<Actor> list = this.actor;
        if (list == null) {
            return CollectionsKt.emptyList();
        }
        Iterable<Actor> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Actor person : iterable) {
            arrayList.add(person.getPerson());
        }
        return (List) arrayList;
    }

    public List<Person> directors() {
        List<Director> list = this.director;
        if (list == null) {
            return CollectionsKt.emptyList();
        }
        Iterable<Director> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Director person : iterable) {
            arrayList.add(person.getPerson());
        }
        return (List) arrayList;
    }

    public List<Person> writers() {
        List<Writer> list = this.writer;
        if (list == null) {
            return CollectionsKt.emptyList();
        }
        Iterable<Writer> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Writer person : iterable) {
            arrayList.add(person.getPerson());
        }
        return (List) arrayList;
    }

    public List<Person> musicians() {
        List<Musician> list = this.musician;
        if (list == null) {
            return CollectionsKt.emptyList();
        }
        Iterable<Musician> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Musician person : iterable) {
            arrayList.add(person.getPerson());
        }
        return (List) arrayList;
    }

    public List<Person> producers() {
        List<Producer> list = this.producer;
        if (list == null) {
            return CollectionsKt.emptyList();
        }
        Iterable<Producer> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Producer person : iterable) {
            arrayList.add(person.getPerson());
        }
        return (List) arrayList;
    }
}
