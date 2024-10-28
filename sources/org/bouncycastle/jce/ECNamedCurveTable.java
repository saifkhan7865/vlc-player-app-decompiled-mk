package org.bouncycastle.jce;

import java.util.Enumeration;

public class ECNamedCurveTable {
    public static Enumeration getNames() {
        return org.bouncycastle.asn1.x9.ECNamedCurveTable.getNames();
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001c  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0029 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0011  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0016  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.bouncycastle.jce.spec.ECNamedCurveParameterSpec getParameterSpec(java.lang.String r10) {
        /*
            r0 = 0
            boolean r1 = possibleOID(r10)     // Catch:{ IllegalArgumentException -> 0x000d }
            if (r1 == 0) goto L_0x000e
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = new org.bouncycastle.asn1.ASN1ObjectIdentifier     // Catch:{ IllegalArgumentException -> 0x000d }
            r1.<init>(r10)     // Catch:{ IllegalArgumentException -> 0x000d }
            goto L_0x000f
        L_0x000d:
        L_0x000e:
            r1 = r0
        L_0x000f:
            if (r1 == 0) goto L_0x0016
            org.bouncycastle.asn1.x9.X9ECParameters r2 = org.bouncycastle.crypto.ec.CustomNamedCurves.getByOID(r1)
            goto L_0x001a
        L_0x0016:
            org.bouncycastle.asn1.x9.X9ECParameters r2 = org.bouncycastle.crypto.ec.CustomNamedCurves.getByName(r10)
        L_0x001a:
            if (r2 != 0) goto L_0x0027
            if (r1 == 0) goto L_0x0023
            org.bouncycastle.asn1.x9.X9ECParameters r2 = org.bouncycastle.asn1.x9.ECNamedCurveTable.getByOID(r1)
            goto L_0x0027
        L_0x0023:
            org.bouncycastle.asn1.x9.X9ECParameters r2 = org.bouncycastle.asn1.x9.ECNamedCurveTable.getByName(r10)
        L_0x0027:
            if (r2 != 0) goto L_0x002a
            return r0
        L_0x002a:
            org.bouncycastle.jce.spec.ECNamedCurveParameterSpec r0 = new org.bouncycastle.jce.spec.ECNamedCurveParameterSpec
            org.bouncycastle.math.ec.ECCurve r5 = r2.getCurve()
            org.bouncycastle.math.ec.ECPoint r6 = r2.getG()
            java.math.BigInteger r7 = r2.getN()
            java.math.BigInteger r8 = r2.getH()
            byte[] r9 = r2.getSeed()
            r3 = r0
            r4 = r10
            r3.<init>(r4, r5, r6, r7, r8, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jce.ECNamedCurveTable.getParameterSpec(java.lang.String):org.bouncycastle.jce.spec.ECNamedCurveParameterSpec");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0012, code lost:
        r4 = r4.charAt(0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean possibleOID(java.lang.String r4) {
        /*
            int r0 = r4.length()
            r1 = 3
            r2 = 0
            if (r0 < r1) goto L_0x0020
            r0 = 1
            char r1 = r4.charAt(r0)
            r3 = 46
            if (r1 == r3) goto L_0x0012
            goto L_0x0020
        L_0x0012:
            char r4 = r4.charAt(r2)
            r1 = 48
            if (r4 < r1) goto L_0x0020
            r1 = 50
            if (r4 <= r1) goto L_0x001f
            goto L_0x0020
        L_0x001f:
            return r0
        L_0x0020:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jce.ECNamedCurveTable.possibleOID(java.lang.String):boolean");
    }
}
