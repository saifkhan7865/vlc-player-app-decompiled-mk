package org.bouncycastle.tsp.ers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.bouncycastle.asn1.tsp.ArchiveTimeStamp;
import org.bouncycastle.asn1.tsp.PartialHashtree;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.StoreException;

public class ERSEvidenceRecordStore implements Store<ERSEvidenceRecord> {
    private DigestCalculator digCalc = null;
    private Map<HashNode, List<ERSEvidenceRecord>> recordMap = new HashMap();

    private static class HashNode {
        private final byte[] dataHash;
        private final int hashCode;

        public HashNode(byte[] bArr) {
            this.dataHash = bArr;
            this.hashCode = Arrays.hashCode(bArr);
        }

        public boolean equals(Object obj) {
            if (obj instanceof HashNode) {
                return Arrays.areEqual(this.dataHash, ((HashNode) obj).dataHash);
            }
            return false;
        }

        public int hashCode() {
            return this.hashCode;
        }
    }

    public ERSEvidenceRecordStore(Collection<ERSEvidenceRecord> collection) throws OperatorCreationException {
        for (ERSEvidenceRecord next : collection) {
            ArchiveTimeStamp archiveTimeStamp = next.getArchiveTimeStamps()[0];
            if (this.digCalc == null) {
                this.digCalc = next.getDigestAlgorithmProvider().get(archiveTimeStamp.getDigestAlgorithmIdentifier());
            }
            PartialHashtree hashTreeLeaf = archiveTimeStamp.getHashTreeLeaf();
            if (hashTreeLeaf != null) {
                byte[][] values = hashTreeLeaf.getValues();
                if (values.length > 1) {
                    for (int i = 0; i != values.length; i++) {
                        addRecord(new HashNode(values[i]), next);
                    }
                    addRecord(new HashNode(ERSUtil.computeNodeHash(this.digCalc, hashTreeLeaf)), next);
                } else {
                    addRecord(new HashNode(values[0]), next);
                }
            } else {
                addRecord(new HashNode(archiveTimeStamp.getTimeStampDigestValue()), next);
            }
        }
    }

    private void addRecord(HashNode hashNode, ERSEvidenceRecord eRSEvidenceRecord) {
        List list = this.recordMap.get(hashNode);
        if (list != null) {
            ArrayList arrayList = new ArrayList(list.size() + 1);
            arrayList.addAll(list);
            arrayList.add(eRSEvidenceRecord);
            this.recordMap.put(hashNode, arrayList);
            return;
        }
        this.recordMap.put(hashNode, Collections.singletonList(eRSEvidenceRecord));
    }

    public Collection<ERSEvidenceRecord> getMatches(Selector<ERSEvidenceRecord> selector) throws StoreException {
        if (selector instanceof ERSEvidenceRecordSelector) {
            List list = this.recordMap.get(new HashNode(((ERSEvidenceRecordSelector) selector).getData().getHash(this.digCalc, (byte[]) null)));
            if (list == null) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList(list.size());
            for (int i = 0; i != list.size(); i++) {
                ERSEvidenceRecord eRSEvidenceRecord = (ERSEvidenceRecord) list.get(i);
                if (selector.match(eRSEvidenceRecord)) {
                    arrayList.add(eRSEvidenceRecord);
                }
            }
            return Collections.unmodifiableList(arrayList);
        } else if (selector == null) {
            HashSet hashSet = new HashSet(this.recordMap.size());
            for (List<ERSEvidenceRecord> addAll : this.recordMap.values()) {
                hashSet.addAll(addAll);
            }
            return Collections.unmodifiableList(new ArrayList(hashSet));
        } else {
            HashSet hashSet2 = new HashSet();
            for (List next : this.recordMap.values()) {
                for (int i2 = 0; i2 != next.size(); i2++) {
                    if (selector.match(next.get(i2))) {
                        hashSet2.add(next.get(i2));
                    }
                }
            }
            return Collections.unmodifiableList(new ArrayList(hashSet2));
        }
    }
}
