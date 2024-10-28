package org.bouncycastle.math.ec.custom.sec;

import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat;

public class SecP521R1Point extends ECPoint.AbstractFp {
    SecP521R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
    }

    SecP521R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    /* JADX WARNING: type inference failed for: r17v0, types: [org.bouncycastle.math.ec.ECPoint] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bouncycastle.math.ec.ECPoint add(org.bouncycastle.math.ec.ECPoint r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            boolean r2 = r16.isInfinity()
            if (r2 == 0) goto L_0x000b
            return r1
        L_0x000b:
            boolean r2 = r17.isInfinity()
            if (r2 == 0) goto L_0x0012
            return r0
        L_0x0012:
            if (r0 != r1) goto L_0x0019
            org.bouncycastle.math.ec.ECPoint r1 = r16.twice()
            return r1
        L_0x0019:
            org.bouncycastle.math.ec.ECCurve r2 = r16.getCurve()
            org.bouncycastle.math.ec.ECFieldElement r3 = r0.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement r3 = (org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement) r3
            org.bouncycastle.math.ec.ECFieldElement r4 = r0.y
            org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement r4 = (org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement) r4
            org.bouncycastle.math.ec.ECFieldElement r5 = r17.getXCoord()
            org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement r5 = (org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement) r5
            org.bouncycastle.math.ec.ECFieldElement r6 = r17.getYCoord()
            org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement r6 = (org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement) r6
            org.bouncycastle.math.ec.ECFieldElement[] r7 = r0.zs
            r8 = 0
            r7 = r7[r8]
            org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement r7 = (org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement) r7
            org.bouncycastle.math.ec.ECFieldElement r1 = r1.getZCoord(r8)
            org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement r1 = (org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement) r1
            r9 = 33
            int[] r9 = org.bouncycastle.math.raw.Nat.create(r9)
            r10 = 17
            int[] r11 = org.bouncycastle.math.raw.Nat.create(r10)
            int[] r12 = org.bouncycastle.math.raw.Nat.create(r10)
            int[] r13 = org.bouncycastle.math.raw.Nat.create(r10)
            int[] r14 = org.bouncycastle.math.raw.Nat.create(r10)
            boolean r15 = r7.isOne()
            if (r15 == 0) goto L_0x0061
            int[] r5 = r5.x
            int[] r6 = r6.x
            goto L_0x0077
        L_0x0061:
            int[] r8 = r7.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.square(r8, r13, r9)
            int[] r5 = r5.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.multiply(r13, r5, r12, r9)
            int[] r5 = r7.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.multiply(r13, r5, r13, r9)
            int[] r5 = r6.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.multiply(r13, r5, r13, r9)
            r5 = r12
            r6 = r13
        L_0x0077:
            boolean r8 = r1.isOne()
            if (r8 == 0) goto L_0x0082
            int[] r3 = r3.x
            int[] r4 = r4.x
            goto L_0x009a
        L_0x0082:
            int[] r10 = r1.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.square(r10, r14, r9)
            int[] r3 = r3.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.multiply(r14, r3, r11, r9)
            int[] r3 = r1.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.multiply(r14, r3, r14, r9)
            int[] r3 = r4.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.multiply(r14, r3, r14, r9)
            r3 = r11
            r4 = r14
            r10 = 17
        L_0x009a:
            int[] r0 = org.bouncycastle.math.raw.Nat.create(r10)
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.subtract(r3, r5, r0)
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.subtract(r4, r6, r12)
            boolean r5 = org.bouncycastle.math.raw.Nat.isZero(r10, r0)
            if (r5 == 0) goto L_0x00ba
            boolean r0 = org.bouncycastle.math.raw.Nat.isZero(r10, r12)
            if (r0 == 0) goto L_0x00b5
            org.bouncycastle.math.ec.ECPoint r0 = r16.twice()
            return r0
        L_0x00b5:
            org.bouncycastle.math.ec.ECPoint r0 = r2.getInfinity()
            return r0
        L_0x00ba:
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.square(r0, r13, r9)
            int[] r5 = org.bouncycastle.math.raw.Nat.create(r10)
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.multiply(r13, r0, r5, r9)
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.multiply(r13, r3, r13, r9)
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.multiply(r4, r5, r11, r9)
            org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement r3 = new org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement
            r3.<init>((int[]) r14)
            int[] r4 = r3.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.square(r12, r4, r9)
            int[] r4 = r3.x
            int[] r6 = r3.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.add(r4, r5, r6)
            int[] r4 = r3.x
            int[] r6 = r3.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.subtract(r4, r13, r6)
            int[] r4 = r3.x
            int[] r6 = r3.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.subtract(r4, r13, r6)
            org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement r4 = new org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement
            r4.<init>((int[]) r5)
            int[] r5 = r3.x
            int[] r6 = r4.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.subtract(r13, r5, r6)
            int[] r5 = r4.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.multiply(r5, r12, r12, r9)
            int[] r5 = r4.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.subtract(r12, r11, r5)
            org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement r5 = new org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement
            r5.<init>((int[]) r0)
            if (r15 != 0) goto L_0x010f
            int[] r0 = r5.x
            int[] r6 = r7.x
            int[] r7 = r5.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.multiply(r0, r6, r7, r9)
        L_0x010f:
            if (r8 != 0) goto L_0x011a
            int[] r0 = r5.x
            int[] r1 = r1.x
            int[] r6 = r5.x
            org.bouncycastle.math.ec.custom.sec.SecP521R1Field.multiply(r0, r1, r6, r9)
        L_0x011a:
            r0 = 1
            org.bouncycastle.math.ec.ECFieldElement[] r0 = new org.bouncycastle.math.ec.ECFieldElement[r0]
            r1 = 0
            r0[r1] = r5
            org.bouncycastle.math.ec.custom.sec.SecP521R1Point r1 = new org.bouncycastle.math.ec.custom.sec.SecP521R1Point
            r1.<init>(r2, r3, r4, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.math.ec.custom.sec.SecP521R1Point.add(org.bouncycastle.math.ec.ECPoint):org.bouncycastle.math.ec.ECPoint");
    }

    /* access modifiers changed from: protected */
    public ECPoint detach() {
        return new SecP521R1Point((ECCurve) null, getAffineXCoord(), getAffineYCoord());
    }

    /* access modifiers changed from: protected */
    public ECFieldElement doubleProductFromSquares(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3, ECFieldElement eCFieldElement4) {
        return eCFieldElement.add(eCFieldElement2).square().subtract(eCFieldElement3).subtract(eCFieldElement4);
    }

    /* access modifiers changed from: protected */
    public ECFieldElement eight(ECFieldElement eCFieldElement) {
        return four(two(eCFieldElement));
    }

    /* access modifiers changed from: protected */
    public ECFieldElement four(ECFieldElement eCFieldElement) {
        return two(two(eCFieldElement));
    }

    public ECPoint negate() {
        return isInfinity() ? this : new SecP521R1Point(this.curve, this.x, this.y.negate(), this.zs);
    }

    /* access modifiers changed from: protected */
    public ECFieldElement three(ECFieldElement eCFieldElement) {
        return two(eCFieldElement).add(eCFieldElement);
    }

    public ECPoint threeTimes() {
        return (isInfinity() || this.y.isZero()) ? this : twice().add(this);
    }

    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        SecP521R1FieldElement secP521R1FieldElement = (SecP521R1FieldElement) this.y;
        if (secP521R1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP521R1FieldElement secP521R1FieldElement2 = (SecP521R1FieldElement) this.x;
        SecP521R1FieldElement secP521R1FieldElement3 = (SecP521R1FieldElement) this.zs[0];
        int[] create = Nat.create(33);
        int[] create2 = Nat.create(17);
        int[] create3 = Nat.create(17);
        int[] create4 = Nat.create(17);
        SecP521R1Field.square(secP521R1FieldElement.x, create4, create);
        int[] create5 = Nat.create(17);
        SecP521R1Field.square(create4, create5, create);
        boolean isOne = secP521R1FieldElement3.isOne();
        int[] iArr = secP521R1FieldElement3.x;
        if (!isOne) {
            SecP521R1Field.square(secP521R1FieldElement3.x, create3, create);
            iArr = create3;
        }
        SecP521R1Field.subtract(secP521R1FieldElement2.x, iArr, create2);
        SecP521R1Field.add(secP521R1FieldElement2.x, iArr, create3);
        SecP521R1Field.multiply(create3, create2, create3, create);
        Nat.addBothTo(17, create3, create3, create3);
        SecP521R1Field.reduce23(create3);
        SecP521R1Field.multiply(create4, secP521R1FieldElement2.x, create4, create);
        Nat.shiftUpBits(17, create4, 2, 0);
        SecP521R1Field.reduce23(create4);
        Nat.shiftUpBits(17, create5, 3, 0, create2);
        SecP521R1Field.reduce23(create2);
        SecP521R1FieldElement secP521R1FieldElement4 = new SecP521R1FieldElement(create5);
        SecP521R1Field.square(create3, secP521R1FieldElement4.x, create);
        SecP521R1Field.subtract(secP521R1FieldElement4.x, create4, secP521R1FieldElement4.x);
        SecP521R1Field.subtract(secP521R1FieldElement4.x, create4, secP521R1FieldElement4.x);
        SecP521R1FieldElement secP521R1FieldElement5 = new SecP521R1FieldElement(create4);
        SecP521R1Field.subtract(create4, secP521R1FieldElement4.x, secP521R1FieldElement5.x);
        SecP521R1Field.multiply(secP521R1FieldElement5.x, create3, secP521R1FieldElement5.x, create);
        SecP521R1Field.subtract(secP521R1FieldElement5.x, create2, secP521R1FieldElement5.x);
        SecP521R1FieldElement secP521R1FieldElement6 = new SecP521R1FieldElement(create3);
        SecP521R1Field.twice(secP521R1FieldElement.x, secP521R1FieldElement6.x);
        if (!isOne) {
            SecP521R1Field.multiply(secP521R1FieldElement6.x, secP521R1FieldElement3.x, secP521R1FieldElement6.x, create);
        }
        return new SecP521R1Point(curve, secP521R1FieldElement4, secP521R1FieldElement5, new ECFieldElement[]{secP521R1FieldElement6});
    }

    public ECPoint twicePlus(ECPoint eCPoint) {
        return this == eCPoint ? threeTimes() : isInfinity() ? eCPoint : eCPoint.isInfinity() ? twice() : this.y.isZero() ? eCPoint : twice().add(eCPoint);
    }

    /* access modifiers changed from: protected */
    public ECFieldElement two(ECFieldElement eCFieldElement) {
        return eCFieldElement.add(eCFieldElement);
    }
}
