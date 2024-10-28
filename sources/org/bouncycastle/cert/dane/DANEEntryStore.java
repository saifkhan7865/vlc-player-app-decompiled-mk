package org.bouncycastle.cert.dane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bouncycastle.util.CollectionStore;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.StoreException;

public class DANEEntryStore implements Store {
    private final Map entries;

    DANEEntryStore(List list) {
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            DANEEntry dANEEntry = (DANEEntry) it.next();
            hashMap.put(dANEEntry.getDomainName(), dANEEntry);
        }
        this.entries = Collections.unmodifiableMap(hashMap);
    }

    public Collection getMatches(Selector selector) throws StoreException {
        if (selector == null) {
            return this.entries.values();
        }
        ArrayList arrayList = new ArrayList();
        for (Object next : this.entries.values()) {
            if (selector.match(next)) {
                arrayList.add(next);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Store toCertificateStore() {
        Collection<DANEEntry> matches = getMatches((Selector) null);
        ArrayList arrayList = new ArrayList(matches.size());
        for (DANEEntry certificate : matches) {
            arrayList.add(certificate.getCertificate());
        }
        return new CollectionStore(arrayList);
    }
}
