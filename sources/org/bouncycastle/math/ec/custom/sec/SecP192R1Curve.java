package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.ec.AbstractECLookupTable;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECLookupTable;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat192;
import org.bouncycastle.util.encoders.Hex;

public class SecP192R1Curve extends ECCurve.AbstractFp {
    /* access modifiers changed from: private */
    public static final ECFieldElement[] SECP192R1_AFFINE_ZS = {new SecP192R1FieldElement(ECConstants.ONE)};
    private static final int SECP192R1_DEFAULT_COORDS = 2;
    public static final BigInteger q = SecP192R1FieldElement.Q;
    protected SecP192R1Point infinity = new SecP192R1Point(this, (ECFieldElement) null, (ECFieldElement) null);

    public SecP192R1Curve() {
        super(q);
        this.a = fromBigInteger(new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFC")));
        this.b = fromBigInteger(new BigInteger(1, Hex.decodeStrict("64210519E59C80E70FA7E9AB72243049FEB8DEECC146B9B1")));
        this.order = new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFF99DEF836146BC9B1B4D22831"));
        this.cofactor = BigInteger.valueOf(1);
        this.coord = 2;
    }

    /* access modifiers changed from: protected */
    public ECCurve cloneCurve() {
        return new SecP192R1Curve();
    }

    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i, final int i2) {
        final int[] iArr = new int[(i2 * 12)];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            ECPoint eCPoint = eCPointArr[i + i4];
            Nat192.copy(((SecP192R1FieldElement) eCPoint.getRawXCoord()).x, 0, iArr, i3);
            Nat192.copy(((SecP192R1FieldElement) eCPoint.getRawYCoord()).x, 0, iArr, i3 + 6);
            i3 += 12;
        }
        return new AbstractECLookupTable() {
            private ECPoint createPoint(int[] iArr, int[] iArr2) {
                return SecP192R1Curve.this.createRawPoint(new SecP192R1FieldElement(iArr), new SecP192R1FieldElement(iArr2), SecP192R1Curve.SECP192R1_AFFINE_ZS);
            }

            public int getSize() {
                return i2;
            }

            public ECPoint lookup(int i) {
                int[] create = Nat192.create();
                int[] create2 = Nat192.create();
                int i2 = 0;
                for (int i3 = 0; i3 < i2; i3++) {
                    int i4 = ((i3 ^ i) - 1) >> 31;
                    for (int i5 = 0; i5 < 6; i5++) {
                        int i6 = create[i5];
                        int[] iArr = iArr;
                        create[i5] = i6 ^ (iArr[i2 + i5] & i4);
                        create2[i5] = create2[i5] ^ (iArr[(i2 + 6) + i5] & i4);
                    }
                    i2 += 12;
                }
                return createPoint(create, create2);
            }

            public ECPoint lookupVar(int i) {
                int[] create = Nat192.create();
                int[] create2 = Nat192.create();
                int i2 = i * 12;
                for (int i3 = 0; i3 < 6; i3++) {
                    int[] iArr = iArr;
                    create[i3] = iArr[i2 + i3];
                    create2[i3] = iArr[6 + i2 + i3];
                }
                return createPoint(create, create2);
            }
        };
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecP192R1Point(this, eCFieldElement, eCFieldElement2);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecP192R1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP192R1FieldElement(bigInteger);
    }

    public int getFieldSize() {
        return q.bitLength();
    }

    public ECPoint getInfinity() {
        return this.infinity;
    }

    public BigInteger getQ() {
        return q;
    }

    public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
        int[] create = Nat192.create();
        SecP192R1Field.random(secureRandom, create);
        return new SecP192R1FieldElement(create);
    }

    public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
        int[] create = Nat192.create();
        SecP192R1Field.randomMult(secureRandom, create);
        return new SecP192R1FieldElement(create);
    }

    public boolean supportsCoordinateSystem(int i) {
        return i == 2;
    }
}
