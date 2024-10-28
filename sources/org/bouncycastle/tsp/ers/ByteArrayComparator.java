package org.bouncycastle.tsp.ers;

import java.util.Comparator;

class ByteArrayComparator implements Comparator {
    ByteArrayComparator() {
    }

    public int compare(Object obj, Object obj2) {
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = (byte[]) obj2;
        int i = 0;
        while (i < bArr.length && i < bArr2.length) {
            byte b = bArr[i] & 255;
            byte b2 = bArr2[i] & 255;
            if (b != b2) {
                return b - b2;
            }
            i++;
        }
        return bArr.length - bArr2.length;
    }
}
