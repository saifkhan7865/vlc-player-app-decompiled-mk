package org.videolan.moviepedia.viewmodel;

import java.util.List;
import kotlin.Metadata;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.moviepedia.database.models.Person;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\"\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0007\"\u0004\b\u0015\u0010\tR\"\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0007\"\u0004\b\u0018\u0010\tR\"\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0007\"\u0004\b\u001c\u0010\tR\"\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0007\"\u0004\b\u001f\u0010\t¨\u0006 "}, d2 = {"Lorg/videolan/moviepedia/viewmodel/MediaMetadataFull;", "", "()V", "actors", "", "Lorg/videolan/moviepedia/database/models/Person;", "getActors", "()Ljava/util/List;", "setActors", "(Ljava/util/List;)V", "directors", "getDirectors", "setDirectors", "metadata", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "getMetadata", "()Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "setMetadata", "(Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;)V", "musicians", "getMusicians", "setMusicians", "producers", "getProducers", "setProducers", "seasons", "Lorg/videolan/moviepedia/viewmodel/Season;", "getSeasons", "setSeasons", "writers", "getWriters", "setWriters", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaMetadataModel.kt */
public final class MediaMetadataFull {
    private List<Person> actors;
    private List<Person> directors;
    private MediaMetadataWithImages metadata;
    private List<Person> musicians;
    private List<Person> producers;
    private List<Season> seasons;
    private List<Person> writers;

    public final MediaMetadataWithImages getMetadata() {
        return this.metadata;
    }

    public final void setMetadata(MediaMetadataWithImages mediaMetadataWithImages) {
        this.metadata = mediaMetadataWithImages;
    }

    public final List<Season> getSeasons() {
        return this.seasons;
    }

    public final void setSeasons(List<Season> list) {
        this.seasons = list;
    }

    public final List<Person> getActors() {
        return this.actors;
    }

    public final void setActors(List<Person> list) {
        this.actors = list;
    }

    public final List<Person> getWriters() {
        return this.writers;
    }

    public final void setWriters(List<Person> list) {
        this.writers = list;
    }

    public final List<Person> getProducers() {
        return this.producers;
    }

    public final void setProducers(List<Person> list) {
        this.producers = list;
    }

    public final List<Person> getMusicians() {
        return this.musicians;
    }

    public final void setMusicians(List<Person> list) {
        this.musicians = list;
    }

    public final List<Person> getDirectors() {
        return this.directors;
    }

    public final void setDirectors(List<Person> list) {
        this.directors = list;
    }
}
