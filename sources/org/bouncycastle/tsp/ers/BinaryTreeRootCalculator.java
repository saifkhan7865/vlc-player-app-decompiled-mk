package org.bouncycastle.tsp.ers;

import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.tsp.PartialHashtree;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.util.Arrays;

public class BinaryTreeRootCalculator implements ERSRootNodeCalculator {
    private List<List<byte[]>> tree;

    public PartialHashtree[] computePathToRoot(DigestCalculator digestCalculator, PartialHashtree partialHashtree, int i) {
        int i2;
        List list;
        ArrayList arrayList = new ArrayList();
        byte[] computeNodeHash = ERSUtil.computeNodeHash(digestCalculator, partialHashtree);
        arrayList.add(partialHashtree);
        int i3 = 0;
        while (i3 < this.tree.size() - 1) {
            if (i == this.tree.get(i3).size() - 1) {
                while (true) {
                    int i4 = i3 + 1;
                    List list2 = this.tree.get(i4);
                    if (!Arrays.areEqual(computeNodeHash, (byte[]) list2.get(list2.size() - 1))) {
                        break;
                    }
                    i = this.tree.get(i4).size() - 1;
                    i3 = i4;
                }
            }
            if ((i & 1) == 0) {
                list = this.tree.get(i3);
                i2 = i + 1;
            } else {
                list = this.tree.get(i3);
                i2 = i - 1;
            }
            byte[] bArr = (byte[]) list.get(i2);
            arrayList.add(new PartialHashtree(bArr));
            computeNodeHash = ERSUtil.calculateBranchHash(digestCalculator, computeNodeHash, bArr);
            i /= 2;
            i3++;
        }
        return (PartialHashtree[]) arrayList.toArray(new PartialHashtree[0]);
    }

    public byte[] computeRootHash(DigestCalculator digestCalculator, PartialHashtree[] partialHashtreeArr) {
        ArrayList arrayList;
        SortedHashList sortedHashList = new SortedHashList();
        for (PartialHashtree computeNodeHash : partialHashtreeArr) {
            sortedHashList.add(ERSUtil.computeNodeHash(digestCalculator, computeNodeHash));
        }
        List<byte[]> list = sortedHashList.toList();
        ArrayList arrayList2 = new ArrayList();
        this.tree = arrayList2;
        arrayList2.add(list);
        if (list.size() > 1) {
            while (true) {
                arrayList = new ArrayList((list.size() / 2) + 1);
                for (int i = 0; i <= list.size() - 2; i += 2) {
                    arrayList.add(ERSUtil.calculateBranchHash(digestCalculator, list.get(i), list.get(i + 1)));
                }
                if (list.size() % 2 == 1) {
                    arrayList.add(list.get(list.size() - 1));
                }
                this.tree.add(arrayList);
                if (arrayList.size() <= 1) {
                    break;
                }
                list = arrayList;
            }
            list = arrayList;
        }
        return list.get(0);
    }

    public byte[] recoverRootHash(DigestCalculator digestCalculator, PartialHashtree[] partialHashtreeArr) {
        byte[] computeNodeHash = ERSUtil.computeNodeHash(digestCalculator, partialHashtreeArr[0]);
        for (int i = 1; i < partialHashtreeArr.length; i++) {
            computeNodeHash = ERSUtil.calculateBranchHash(digestCalculator, computeNodeHash, ERSUtil.computeNodeHash(digestCalculator, partialHashtreeArr[i]));
        }
        return computeNodeHash;
    }
}
