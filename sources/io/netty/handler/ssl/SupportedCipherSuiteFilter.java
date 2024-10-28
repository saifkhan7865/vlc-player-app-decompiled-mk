package io.netty.handler.ssl;

import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class SupportedCipherSuiteFilter implements CipherSuiteFilter {
    public static final SupportedCipherSuiteFilter INSTANCE = new SupportedCipherSuiteFilter();

    private SupportedCipherSuiteFilter() {
    }

    public String[] filterCipherSuites(Iterable<String> iterable, List<String> list, Set<String> set) {
        ArrayList arrayList;
        String next;
        ObjectUtil.checkNotNull(list, "defaultCiphers");
        ObjectUtil.checkNotNull(set, "supportedCiphers");
        if (iterable == null) {
            List<String> list2 = list;
            arrayList = new ArrayList(list.size());
            iterable = list2;
        } else {
            arrayList = new ArrayList(set.size());
        }
        Iterator<String> it = iterable.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            if (set.contains(next)) {
                arrayList.add(next);
            }
        }
        return (String[]) arrayList.toArray(EmptyArrays.EMPTY_STRINGS);
    }
}
