package io.netty.handler.ssl;

import io.netty.util.internal.EmptyArrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class IdentityCipherSuiteFilter implements CipherSuiteFilter {
    public static final IdentityCipherSuiteFilter INSTANCE = new IdentityCipherSuiteFilter(true);
    public static final IdentityCipherSuiteFilter INSTANCE_DEFAULTING_TO_SUPPORTED_CIPHERS = new IdentityCipherSuiteFilter(false);
    private final boolean defaultToDefaultCiphers;

    private IdentityCipherSuiteFilter(boolean z) {
        this.defaultToDefaultCiphers = z;
    }

    public String[] filterCipherSuites(Iterable<String> iterable, List<String> list, Set<String> set) {
        String next;
        if (iterable != null) {
            ArrayList arrayList = new ArrayList(set.size());
            Iterator<String> it = iterable.iterator();
            while (it.hasNext() && (next = it.next()) != null) {
                arrayList.add(next);
            }
            return (String[]) arrayList.toArray(EmptyArrays.EMPTY_STRINGS);
        } else if (this.defaultToDefaultCiphers) {
            return (String[]) list.toArray(EmptyArrays.EMPTY_STRINGS);
        } else {
            return (String[]) set.toArray(EmptyArrays.EMPTY_STRINGS);
        }
    }
}
