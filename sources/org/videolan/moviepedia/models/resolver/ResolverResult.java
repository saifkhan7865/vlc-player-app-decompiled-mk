package org.videolan.moviepedia.models.resolver;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H&¨\u0006\b"}, d2 = {"Lorg/videolan/moviepedia/models/resolver/ResolverResult;", "", "()V", "getAllResults", "", "Lorg/videolan/moviepedia/models/resolver/ResolverMedia;", "lucky", "results", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ResolverDataModel.kt */
public abstract class ResolverResult {
    public abstract ResolverMedia lucky();

    public abstract List<ResolverMedia> results();

    public final List<ResolverMedia> getAllResults() {
        ArrayList arrayList = new ArrayList(results());
        arrayList.add(lucky());
        return CollectionsKt.toList(arrayList);
    }
}
