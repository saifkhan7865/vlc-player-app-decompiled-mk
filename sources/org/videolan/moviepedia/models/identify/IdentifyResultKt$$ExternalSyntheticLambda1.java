package org.videolan.moviepedia.models.identify;

import java.util.Comparator;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class IdentifyResultKt$$ExternalSyntheticLambda1 implements Comparator {
    public final /* synthetic */ List f$0;

    public /* synthetic */ IdentifyResultKt$$ExternalSyntheticLambda1(List list) {
        this.f$0 = list;
    }

    public final int compare(Object obj, Object obj2) {
        return IdentifyResultKt.retrievePosters$lambda$0(this.f$0, (Poster) obj, (Poster) obj2);
    }
}
