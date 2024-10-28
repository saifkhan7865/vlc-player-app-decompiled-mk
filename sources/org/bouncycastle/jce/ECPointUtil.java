package org.bouncycastle.jce;

public class ECPointUtil {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v1, resolved type: org.bouncycastle.math.ec.ECCurve$F2m} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: org.bouncycastle.math.ec.ECCurve$F2m} */
    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.security.spec.ECPoint decodePoint(java.security.spec.EllipticCurve r20, byte[] r21) {
        /*
            java.security.spec.ECField r0 = r20.getField()
            boolean r0 = r0 instanceof java.security.spec.ECFieldFp
            if (r0 == 0) goto L_0x0025
            org.bouncycastle.math.ec.ECCurve$Fp r0 = new org.bouncycastle.math.ec.ECCurve$Fp
            java.security.spec.ECField r1 = r20.getField()
            java.security.spec.ECFieldFp r1 = (java.security.spec.ECFieldFp) r1
            java.math.BigInteger r2 = r1.getP()
            java.math.BigInteger r3 = r20.getA()
            java.math.BigInteger r4 = r20.getB()
            r5 = 0
            r6 = 0
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6)
        L_0x0022:
            r1 = r21
            goto L_0x0077
        L_0x0025:
            java.security.spec.ECField r0 = r20.getField()
            java.security.spec.ECFieldF2m r0 = (java.security.spec.ECFieldF2m) r0
            int[] r0 = r0.getMidTermsOfReductionPolynomial()
            int r1 = r0.length
            r2 = 3
            r3 = 0
            if (r1 != r2) goto L_0x0057
            org.bouncycastle.math.ec.ECCurve$F2m r1 = new org.bouncycastle.math.ec.ECCurve$F2m
            java.security.spec.ECField r2 = r20.getField()
            java.security.spec.ECFieldF2m r2 = (java.security.spec.ECFieldF2m) r2
            int r5 = r2.getM()
            r2 = 2
            r6 = r0[r2]
            r2 = 1
            r7 = r0[r2]
            r8 = r0[r3]
            java.math.BigInteger r9 = r20.getA()
            java.math.BigInteger r10 = r20.getB()
            r11 = 0
            r12 = 0
            r4 = r1
            r4.<init>((int) r5, (int) r6, (int) r7, (int) r8, (java.math.BigInteger) r9, (java.math.BigInteger) r10, (java.math.BigInteger) r11, (java.math.BigInteger) r12)
            goto L_0x0075
        L_0x0057:
            org.bouncycastle.math.ec.ECCurve$F2m r1 = new org.bouncycastle.math.ec.ECCurve$F2m
            java.security.spec.ECField r2 = r20.getField()
            java.security.spec.ECFieldF2m r2 = (java.security.spec.ECFieldF2m) r2
            int r14 = r2.getM()
            r15 = r0[r3]
            java.math.BigInteger r16 = r20.getA()
            java.math.BigInteger r17 = r20.getB()
            r18 = 0
            r19 = 0
            r13 = r1
            r13.<init>((int) r14, (int) r15, (java.math.BigInteger) r16, (java.math.BigInteger) r17, (java.math.BigInteger) r18, (java.math.BigInteger) r19)
        L_0x0075:
            r0 = r1
            goto L_0x0022
        L_0x0077:
            org.bouncycastle.math.ec.ECPoint r0 = r0.decodePoint(r1)
            java.security.spec.ECPoint r0 = org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util.convertPoint(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jce.ECPointUtil.decodePoint(java.security.spec.EllipticCurve, byte[]):java.security.spec.ECPoint");
    }
}
