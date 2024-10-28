package org.videolan.moviepedia.models.resolver;

import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H&J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H&J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H&J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H&J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H&¨\u0006\n"}, d2 = {"Lorg/videolan/moviepedia/models/resolver/ResolverCasting;", "", "()V", "actors", "", "Lorg/videolan/moviepedia/models/resolver/ResolverPerson;", "directors", "musicians", "producers", "writers", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ResolverDataModel.kt */
public abstract class ResolverCasting {
    public abstract List<ResolverPerson> actors();

    public abstract List<ResolverPerson> directors();

    public abstract List<ResolverPerson> musicians();

    public abstract List<ResolverPerson> producers();

    public abstract List<ResolverPerson> writers();
}
