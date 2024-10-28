package org.bouncycastle.tsp.ers;

import java.util.Date;
import org.bouncycastle.util.Selector;

public class ERSEvidenceRecordSelector implements Selector<ERSEvidenceRecord> {
    private final ERSData data;
    private final Date date;

    public ERSEvidenceRecordSelector(ERSData eRSData) {
        this(eRSData, new Date());
    }

    public ERSEvidenceRecordSelector(ERSData eRSData, Date date2) {
        this.data = eRSData;
        this.date = new Date(date2.getTime());
    }

    public Object clone() {
        return this;
    }

    public ERSData getData() {
        return this.data;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean match(org.bouncycastle.tsp.ers.ERSEvidenceRecord r4) {
        /*
            r3 = this;
            r0 = 0
            org.bouncycastle.tsp.ers.ERSData r1 = r3.data     // Catch:{ Exception -> 0x0014 }
            java.util.Date r2 = r3.date     // Catch:{ Exception -> 0x0014 }
            boolean r1 = r4.isContaining(r1, r2)     // Catch:{ Exception -> 0x0014 }
            if (r1 == 0) goto L_0x0014
            org.bouncycastle.tsp.ers.ERSData r1 = r3.data     // Catch:{  }
            java.util.Date r2 = r3.date     // Catch:{  }
            r4.validatePresent(r1, r2)     // Catch:{  }
            r4 = 1
            return r4
        L_0x0014:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.tsp.ers.ERSEvidenceRecordSelector.match(org.bouncycastle.tsp.ers.ERSEvidenceRecord):boolean");
    }
}
