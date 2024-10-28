package io.ktor.server.engine;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Comparisons.kt */
public final class ClassLoadersKt$urlClassPathByPackagesList$$inlined$sortedBy$1<T> implements Comparator {
    public final int compare(T t, T t2) {
        CharSequence charSequence = (String) t;
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            if (charSequence.charAt(i2) == '/') {
                i++;
            }
        }
        Comparable valueOf = Integer.valueOf(i);
        CharSequence charSequence2 = (String) t2;
        int i3 = 0;
        for (int i4 = 0; i4 < charSequence2.length(); i4++) {
            if (charSequence2.charAt(i4) == '/') {
                i3++;
            }
        }
        return ComparisonsKt.compareValues(valueOf, Integer.valueOf(i3));
    }
}
