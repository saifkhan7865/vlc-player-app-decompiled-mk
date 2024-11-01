package com.google.zxing.pdf417.decoder;

class DetectionResultColumn {
    private static final int MAX_NEARBY_DISTANCE = 5;
    private final BoundingBox boundingBox;
    private final Codeword[] codewords;

    DetectionResultColumn(BoundingBox boundingBox2) {
        this.boundingBox = new BoundingBox(boundingBox2);
        this.codewords = new Codeword[((boundingBox2.getMaxY() - boundingBox2.getMinY()) + 1)];
    }

    /* access modifiers changed from: package-private */
    public final Codeword getCodewordNearby(int i) {
        Codeword codeword;
        Codeword codeword2;
        Codeword codeword3 = getCodeword(i);
        if (codeword3 != null) {
            return codeword3;
        }
        for (int i2 = 1; i2 < 5; i2++) {
            int imageRowToCodewordIndex = imageRowToCodewordIndex(i) - i2;
            if (imageRowToCodewordIndex >= 0 && (codeword2 = this.codewords[imageRowToCodewordIndex]) != null) {
                return codeword2;
            }
            int imageRowToCodewordIndex2 = imageRowToCodewordIndex(i) + i2;
            Codeword[] codewordArr = this.codewords;
            if (imageRowToCodewordIndex2 < codewordArr.length && (codeword = codewordArr[imageRowToCodewordIndex2]) != null) {
                return codeword;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final int imageRowToCodewordIndex(int i) {
        return i - this.boundingBox.getMinY();
    }

    /* access modifiers changed from: package-private */
    public final void setCodeword(int i, Codeword codeword) {
        this.codewords[imageRowToCodewordIndex(i)] = codeword;
    }

    /* access modifiers changed from: package-private */
    public final Codeword getCodeword(int i) {
        return this.codewords[imageRowToCodewordIndex(i)];
    }

    /* access modifiers changed from: package-private */
    public final BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    /* access modifiers changed from: package-private */
    public final Codeword[] getCodewords() {
        return this.codewords;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0056, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005c, code lost:
        kotlin.UInt$$ExternalSyntheticBackport0.m(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0060, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
            r12 = this;
            java.util.Formatter r0 = new java.util.Formatter
            r0.<init>()
            com.google.zxing.pdf417.decoder.Codeword[] r1 = r12.codewords     // Catch:{ all -> 0x0054 }
            int r2 = r1.length     // Catch:{ all -> 0x0054 }
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x000b:
            if (r4 >= r2) goto L_0x004c
            r6 = r1[r4]     // Catch:{ all -> 0x0054 }
            r7 = 1
            if (r6 != 0) goto L_0x0023
            java.lang.String r6 = "%3d:    |   %n"
            int r8 = r5 + 1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0054 }
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x0054 }
            r7[r3] = r5     // Catch:{ all -> 0x0054 }
            r0.format(r6, r7)     // Catch:{ all -> 0x0054 }
            r5 = r8
            goto L_0x0049
        L_0x0023:
            java.lang.String r8 = "%3d: %3d|%3d%n"
            int r9 = r5 + 1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0054 }
            int r10 = r6.getRowNumber()     // Catch:{ all -> 0x0054 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x0054 }
            int r6 = r6.getValue()     // Catch:{ all -> 0x0054 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0054 }
            r11 = 3
            java.lang.Object[] r11 = new java.lang.Object[r11]     // Catch:{ all -> 0x0054 }
            r11[r3] = r5     // Catch:{ all -> 0x0054 }
            r11[r7] = r10     // Catch:{ all -> 0x0054 }
            r5 = 2
            r11[r5] = r6     // Catch:{ all -> 0x0054 }
            r0.format(r8, r11)     // Catch:{ all -> 0x0054 }
            r5 = r9
        L_0x0049:
            int r4 = r4 + 1
            goto L_0x000b
        L_0x004c:
            java.lang.String r1 = r0.toString()     // Catch:{ all -> 0x0054 }
            r0.close()
            return r1
        L_0x0054:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0056 }
        L_0x0056:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r0 = move-exception
            kotlin.UInt$$ExternalSyntheticBackport0.m((java.lang.Throwable) r1, (java.lang.Throwable) r0)
        L_0x005f:
            goto L_0x0061
        L_0x0060:
            throw r2
        L_0x0061:
            goto L_0x0060
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DetectionResultColumn.toString():java.lang.String");
    }
}
