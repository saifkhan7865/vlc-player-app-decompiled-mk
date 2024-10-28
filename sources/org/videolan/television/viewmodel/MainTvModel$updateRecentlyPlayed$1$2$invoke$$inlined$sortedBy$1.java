package org.videolan.television.viewmodel;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: Comparisons.kt */
public final class MainTvModel$updateRecentlyPlayed$1$2$invoke$$inlined$sortedBy$1<T> implements Comparator {
    final /* synthetic */ List $history$inlined;

    public MainTvModel$updateRecentlyPlayed$1$2$invoke$$inlined$sortedBy$1(List list) {
        this.$history$inlined = list;
    }

    public final int compare(T t, T t2) {
        Object obj;
        Object obj2;
        MediaMetadataWithImages mediaMetadataWithImages = (MediaMetadataWithImages) t;
        List list = this.$history$inlined;
        Iterator it = list.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it.next();
            long id = ((MediaWrapper) obj2).getId();
            Long mlId = mediaMetadataWithImages.getMetadata().getMlId();
            if (mlId != null && id == mlId.longValue()) {
                break;
            }
        }
        Comparable valueOf = Integer.valueOf(list.indexOf(obj2));
        MediaMetadataWithImages mediaMetadataWithImages2 = (MediaMetadataWithImages) t2;
        List list2 = this.$history$inlined;
        Iterator it2 = list2.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Object next = it2.next();
            long id2 = ((MediaWrapper) next).getId();
            Long mlId2 = mediaMetadataWithImages2.getMetadata().getMlId();
            if (mlId2 != null && id2 == mlId2.longValue()) {
                obj = next;
                break;
            }
        }
        return ComparisonsKt.compareValues(valueOf, Integer.valueOf(list2.indexOf(obj)));
    }
}
