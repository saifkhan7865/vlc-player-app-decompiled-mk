package org.bouncycastle.math.ec.custom.gm;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.ec.AbstractECLookupTable;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECLookupTable;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.encoders.Hex;

public class SM2P256V1Curve extends ECCurve.AbstractFp {
    /* access modifiers changed from: private */
    public static final ECFieldElement[] SM2P256V1_AFFINE_ZS = {new SM2P256V1FieldElement(ECConstants.ONE)};
    private static final int SM2P256V1_DEFAULT_COORDS = 2;
    public static final BigInteger q = SM2P256V1FieldElement.Q;
    protected SM2P256V1Point infinity = new SM2P256V1Point(this, (ECFieldElement) null, (ECFieldElement) null);

    public SM2P256V1Curve() {
        super(q);
        this.a = fromBigInteger(new BigInteger(1, Hex.decodeStrict("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFC")));
        this.b = fromBigInteger(new BigInteger(1, Hex.decodeStrict("28E9FA9E9D9F5E344D5A9E4BCF6509A7F39789F515AB8F92DDBCBD414D940E93")));
        this.order = new BigInteger(1, Hex.decodeStrict("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFF7203DF6B21C6052B53BBF40939D54123"));
        this.cofactor = BigInteger.valueOf(1);
        this.coord = 2;
    }

    /* access modifiers changed from: protected */
    public ECCurve cloneCurve() {
        return new SM2P256V1Curve();
    }

    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i, final int i2) {
        final int[] iArr = new int[(i2 * 16)];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            ECPoint eCPoint = eCPointArr[i + i4];
            Nat256.copy(((SM2P256V1FieldElement) eCPoint.getRawXCoord()).x, 0, iArr, i3);
            Nat256.copy(((SM2P256V1FieldElement) eCPoint.getRawYCoord()).x, 0, iArr, i3 + 8);
            i3 += 16;
        }
        return new AbstractECLookupTable() {
            private ECPoint createPoint(int[] iArr, int[] iArr2) {
                return SM2P256V1Curve.this.createRawPoint(new SM2P256V1FieldElement(iArr), new SM2P256V1FieldElement(iArr2), SM2P256V1Curve.SM2P256V1_AFFINE_ZS);
            }

            public int getSize() {
                return i2;
            }

            public ECPoint lookup(int i) {
                int[] create = Nat256.create();
                int[] create2 = Nat256.create();
                int i2 = 0;
                for (int i3 = 0; i3 < i2; i3++) {
                    int i4 = ((i3 ^ i) - 1) >> 31;
                    for (int i5 = 0; i5 < 8; i5++) {
                        int i6 = create[i5];
                        int[] iArr = iArr;
                        create[i5] = i6 ^ (iArr[i2 + i5] & i4);
                        create2[i5] = create2[i5] ^ (iArr[(i2 + 8) + i5] & i4);
                    }
                    i2 += 16;
                }
                return createPoint(create, create2);
            }

            public ECPoint lookupVar(int i) {
                int[] create = Nat256.create();
                int[] create2 = Nat256.create();
                int i2 = i * 16;
                for (int i3 = 0; i3 < 8; i3++) {
                    int[] iArr = iArr;
                    create[i3] = iArr[i2 + i3];
                    create2[i3] = iArr[8 + i2 + i3];
                }
                return createPoint(create, create2);
            }
        };
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SM2P256V1Point(this, eCFieldElement, eCFieldElement2);
    }

    /* access modifiers changed from: protected */
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SM2P256V1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SM2P256V1FieldElement(bigInteger);
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
        int[] create = Nat256.create();
        SM2P256V1Field.random(secureRandom, create);
        return new SM2P256V1FieldElement(create);
    }

    public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
        int[] create = Nat256.create();
        SM2P256V1Field.randomMult(secureRandom, create);
        return new SM2P256V1FieldElement(create);
    }

    public boolean supportsCoordinateSystem(int i) {
        return i == 2;
    }
}
