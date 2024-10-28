package org.bouncycastle.math.ec.rfc8032;

import com.google.common.base.Ascii;
import java.security.SecureRandom;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.math.ec.rfc7748.X448;
import org.bouncycastle.math.ec.rfc7748.X448Field;
import org.bouncycastle.math.raw.Nat;

public abstract class Ed448 {
    private static final int[] B225_x = {110141154, 30892124, 160820362, 264558960, 217232225, 47722141, 19029845, 8326902, 183409749, 170134547, 90340180, 222600478, 61097333, 7431335, 198491505, 102372861};
    private static final int[] B225_y = {221945828, 50763449, 132637478, 109250759, 216053960, 61612587, 50649998, 138339097, 98949899, 248139835, 186410297, 126520782, 47339196, 78164062, 198835543, 169622712};
    private static final int[] B_x = {118276190, 40534716, 9670182, 135141552, 85017403, 259173222, 68333082, 171784774, 174973732, 15824510, 73756743, 57518561, 94773951, 248652241, 107736333, 82941708};
    private static final int[] B_y = {36764180, 8885695, 130592152, 20104429, 163904957, 30304195, 121295871, 5901357, 125344798, 171541512, 175338348, 209069246, 3626697, 38307682, 24032956, 110359655};
    private static final int COORD_INTS = 14;
    private static final int C_d = 39081;
    private static final byte[] DOM4_PREFIX = {83, 105, 103, 69, 100, 52, 52, 56};
    private static final int[] P = {-1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1};
    private static final int POINT_BYTES = 57;
    private static PointAffine[] PRECOMP_BASE225_WNAF = null;
    private static int[] PRECOMP_BASE_COMB = null;
    private static PointAffine[] PRECOMP_BASE_WNAF = null;
    private static final int PRECOMP_BLOCKS = 5;
    private static final Object PRECOMP_LOCK = new Object();
    private static final int PRECOMP_MASK = 15;
    private static final int PRECOMP_POINTS = 16;
    private static final int PRECOMP_RANGE = 450;
    private static final int PRECOMP_SPACING = 18;
    private static final int PRECOMP_TEETH = 5;
    public static final int PREHASH_SIZE = 64;
    public static final int PUBLIC_KEY_SIZE = 57;
    private static final int SCALAR_BYTES = 57;
    private static final int SCALAR_INTS = 14;
    public static final int SECRET_KEY_SIZE = 57;
    public static final int SIGNATURE_SIZE = 114;
    private static final int WNAF_WIDTH_225 = 5;
    private static final int WNAF_WIDTH_BASE = 7;

    public static final class Algorithm {
        public static final int Ed448 = 0;
        public static final int Ed448ph = 1;
    }

    private static class F extends X448Field {
        private F() {
        }
    }

    private static class PointAffine {
        int[] x;
        int[] y;

        private PointAffine() {
            this.x = F.create();
            this.y = F.create();
        }
    }

    private static class PointProjective {
        int[] x;
        int[] y;
        int[] z;

        private PointProjective() {
            this.x = F.create();
            this.y = F.create();
            this.z = F.create();
        }
    }

    private static class PointTemp {
        int[] r0;
        int[] r1;
        int[] r2;
        int[] r3;
        int[] r4;
        int[] r5;
        int[] r6;
        int[] r7;

        private PointTemp() {
            this.r0 = F.create();
            this.r1 = F.create();
            this.r2 = F.create();
            this.r3 = F.create();
            this.r4 = F.create();
            this.r5 = F.create();
            this.r6 = F.create();
            this.r7 = F.create();
        }
    }

    public static final class PublicPoint {
        final int[] data;

        PublicPoint(int[] iArr) {
            this.data = iArr;
        }
    }

    private static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[28];
        Scalar448.decode(bArr, iArr);
        int[] iArr2 = new int[14];
        Scalar448.decode(bArr2, iArr2);
        int[] iArr3 = new int[14];
        Scalar448.decode(bArr3, iArr3);
        Nat.mulAddTo(14, iArr2, iArr3, iArr);
        byte[] bArr4 = new byte[114];
        Codec.encode32(iArr, 0, 28, bArr4, 0);
        return Scalar448.reduce912(bArr4);
    }

    private static boolean checkContextVar(byte[] bArr) {
        return bArr != null && bArr.length < 256;
    }

    private static int checkPoint(PointAffine pointAffine) {
        int[] create = F.create();
        int[] create2 = F.create();
        int[] create3 = F.create();
        F.sqr(pointAffine.x, create2);
        F.sqr(pointAffine.y, create3);
        F.mul(create2, create3, create);
        F.add(create2, create3, create2);
        F.mul(create, (int) C_d, create);
        F.subOne(create);
        F.add(create, create2, create);
        F.normalize(create);
        F.normalize(create3);
        return F.isZero(create) & (F.isZero(create3) ^ -1);
    }

    private static int checkPoint(PointProjective pointProjective) {
        int[] create = F.create();
        int[] create2 = F.create();
        int[] create3 = F.create();
        int[] create4 = F.create();
        F.sqr(pointProjective.x, create2);
        F.sqr(pointProjective.y, create3);
        F.sqr(pointProjective.z, create4);
        F.mul(create2, create3, create);
        F.add(create2, create3, create2);
        F.mul(create2, create4, create2);
        F.sqr(create4, create4);
        F.mul(create, (int) C_d, create);
        F.sub(create, create4, create);
        F.add(create, create2, create);
        F.normalize(create);
        F.normalize(create3);
        F.normalize(create4);
        return F.isZero(create) & (F.isZero(create3) ^ -1) & (F.isZero(create4) ^ -1);
    }

    private static boolean checkPointFullVar(byte[] bArr) {
        if ((bArr[56] & Byte.MAX_VALUE) != 0) {
            return false;
        }
        int decode32 = Codec.decode32(bArr, 52);
        int i = P[13] ^ decode32;
        for (int i2 = 12; i2 > 0; i2--) {
            int decode322 = Codec.decode32(bArr, i2 * 4);
            if (i == 0 && decode322 - 2147483648 > P[i2] - 2147483648) {
                return false;
            }
            decode32 |= decode322;
            i |= P[i2] ^ decode322;
        }
        int decode323 = Codec.decode32(bArr, 0);
        if (decode32 != 0 || decode323 - 2147483648 > -2147483647) {
            return i != 0 || decode323 + Integer.MIN_VALUE < P[0] - -2147483647;
        }
        return false;
    }

    private static boolean checkPointOrderVar(PointAffine pointAffine) {
        PointProjective pointProjective = new PointProjective();
        scalarMultOrderVar(pointAffine, pointProjective);
        return normalizeToNeutralElementVar(pointProjective);
    }

    private static boolean checkPointVar(byte[] bArr) {
        if ((bArr[56] & Byte.MAX_VALUE) != 0) {
            return false;
        }
        int decode32 = Codec.decode32(bArr, 52);
        int[] iArr = P;
        if (decode32 != iArr[13]) {
            return true;
        }
        int[] iArr2 = new int[14];
        Codec.decode32(bArr, 0, iArr2, 0, 14);
        return !Nat.gte(14, iArr2, iArr);
    }

    private static byte[] copy(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public static Xof createPrehash() {
        return createXof();
    }

    private static Xof createXof() {
        return new SHAKEDigest(256);
    }

    private static boolean decodePointVar(byte[] bArr, boolean z, PointAffine pointAffine) {
        int i = (bArr[56] & 128) >>> 7;
        F.decode(bArr, pointAffine.y);
        int[] create = F.create();
        int[] create2 = F.create();
        F.sqr(pointAffine.y, create);
        F.mul(create, (int) C_d, create2);
        F.negate(create, create);
        F.addOne(create);
        F.addOne(create2);
        boolean sqrtRatioVar = F.sqrtRatioVar(create, create2, pointAffine.x);
        boolean z2 = false;
        if (!sqrtRatioVar) {
            return false;
        }
        F.normalize(pointAffine.x);
        if (i == 1 && F.isZeroVar(pointAffine.x)) {
            return false;
        }
        if (i != (pointAffine.x[0] & 1)) {
            z2 = true;
        }
        if (z ^ z2) {
            F.negate(pointAffine.x, pointAffine.x);
            F.normalize(pointAffine.x);
        }
        return true;
    }

    private static void dom4(Xof xof, byte b, byte[] bArr) {
        byte[] bArr2 = DOM4_PREFIX;
        int length = bArr2.length;
        int i = length + 2;
        int length2 = bArr.length + i;
        byte[] bArr3 = new byte[length2];
        System.arraycopy(bArr2, 0, bArr3, 0, length);
        bArr3[length] = b;
        bArr3[length + 1] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr3, i, bArr.length);
        xof.update(bArr3, 0, length2);
    }

    private static void encodePoint(PointAffine pointAffine, byte[] bArr, int i) {
        F.encode(pointAffine.y, bArr, i);
        bArr[i + 56] = (byte) ((pointAffine.x[0] & 1) << 7);
    }

    public static void encodePublicPoint(PublicPoint publicPoint, byte[] bArr, int i) {
        F.encode(publicPoint.data, 16, bArr, i);
        bArr[i + 56] = (byte) ((publicPoint.data[0] & 1) << 7);
    }

    private static int encodeResult(PointProjective pointProjective, byte[] bArr, int i) {
        PointAffine pointAffine = new PointAffine();
        normalizeToAffine(pointProjective, pointAffine);
        int checkPoint = checkPoint(pointAffine);
        encodePoint(pointAffine, bArr, i);
        return checkPoint;
    }

    private static PublicPoint exportPoint(PointAffine pointAffine) {
        int[] iArr = new int[32];
        F.copy(pointAffine.x, 0, iArr, 0);
        F.copy(pointAffine.y, 0, iArr, 16);
        return new PublicPoint(iArr);
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        if (bArr.length == 57) {
            secureRandom.nextBytes(bArr);
            return;
        }
        throw new IllegalArgumentException("k");
    }

    public static PublicPoint generatePublicKey(byte[] bArr, int i) {
        Xof createXof = createXof();
        byte[] bArr2 = new byte[114];
        createXof.update(bArr, i, 57);
        createXof.doFinal(bArr2, 0, 114);
        byte[] bArr3 = new byte[57];
        pruneScalar(bArr2, 0, bArr3);
        PointProjective pointProjective = new PointProjective();
        scalarMultBase(bArr3, pointProjective);
        PointAffine pointAffine = new PointAffine();
        normalizeToAffine(pointProjective, pointAffine);
        if (checkPoint(pointAffine) != 0) {
            return exportPoint(pointAffine);
        }
        throw new IllegalStateException();
    }

    public static void generatePublicKey(byte[] bArr, int i, byte[] bArr2, int i2) {
        Xof createXof = createXof();
        byte[] bArr3 = new byte[114];
        createXof.update(bArr, i, 57);
        createXof.doFinal(bArr3, 0, 114);
        byte[] bArr4 = new byte[57];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncoded(bArr4, bArr2, i2);
    }

    private static int getWindow4(int[] iArr, int i) {
        return (iArr[i >>> 3] >>> ((i & 7) << 2)) & 15;
    }

    private static void implSign(Xof xof, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte b, byte[] bArr5, int i2, int i3, byte[] bArr6, int i4) {
        dom4(xof, b, bArr4);
        xof.update(bArr, 57, 57);
        xof.update(bArr5, i2, i3);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] reduce912 = Scalar448.reduce912(bArr);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncoded(reduce912, bArr7, 0);
        dom4(xof, b, bArr4);
        xof.update(bArr7, 0, 57);
        xof.update(bArr3, i, 57);
        xof.update(bArr5, i2, i3);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] calculateS = calculateS(reduce912, Scalar448.reduce912(bArr), bArr2);
        System.arraycopy(bArr7, 0, bArr6, i4, 57);
        System.arraycopy(calculateS, 0, bArr6, i4 + 57, 57);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, byte b, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        if (checkContextVar(bArr2)) {
            Xof createXof = createXof();
            byte[] bArr5 = new byte[114];
            byte[] bArr6 = bArr;
            int i5 = i;
            createXof.update(bArr, i, 57);
            createXof.doFinal(bArr5, 0, 114);
            byte[] bArr7 = new byte[57];
            pruneScalar(bArr5, 0, bArr7);
            byte[] bArr8 = new byte[57];
            scalarMultBaseEncoded(bArr7, bArr8, 0);
            implSign(createXof, bArr5, bArr7, bArr8, 0, bArr2, b, bArr3, i2, i3, bArr4, i4);
            return;
        }
        throw new IllegalArgumentException("ctx");
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        if (checkContextVar(bArr3)) {
            Xof createXof = createXof();
            byte[] bArr6 = new byte[114];
            byte[] bArr7 = bArr;
            int i6 = i;
            createXof.update(bArr, i, 57);
            createXof.doFinal(bArr6, 0, 114);
            byte[] bArr8 = new byte[57];
            pruneScalar(bArr6, 0, bArr8);
            implSign(createXof, bArr6, bArr8, bArr2, i2, bArr3, b, bArr4, i3, i4, bArr5, i5);
            return;
        }
        throw new IllegalArgumentException("ctx");
    }

    private static boolean implVerify(byte[] bArr, int i, PublicPoint publicPoint, byte[] bArr2, byte b, byte[] bArr3, int i2, int i3) {
        byte[] bArr4 = bArr;
        int i4 = i;
        PublicPoint publicPoint2 = publicPoint;
        if (checkContextVar(bArr2)) {
            byte[] copy = copy(bArr, i, 57);
            byte[] copy2 = copy(bArr, i4 + 57, 57);
            if (!checkPointVar(copy)) {
                return false;
            }
            int[] iArr = new int[14];
            if (!Scalar448.checkVar(copy2, iArr)) {
                return false;
            }
            PointAffine pointAffine = new PointAffine();
            if (!decodePointVar(copy, true, pointAffine)) {
                return false;
            }
            PointAffine pointAffine2 = new PointAffine();
            F.negate(publicPoint2.data, pointAffine2.x);
            F.copy(publicPoint2.data, 16, pointAffine2.y, 0);
            byte[] bArr5 = new byte[57];
            encodePublicPoint(publicPoint2, bArr5, 0);
            Xof createXof = createXof();
            byte[] bArr6 = new byte[114];
            dom4(createXof, b, bArr2);
            createXof.update(copy, 0, 57);
            createXof.update(bArr5, 0, 57);
            createXof.update(bArr3, i2, i3);
            createXof.doFinal(bArr6, 0, 114);
            int[] iArr2 = new int[14];
            Scalar448.decode(Scalar448.reduce912(bArr6), iArr2);
            int[] iArr3 = new int[8];
            int[] iArr4 = new int[8];
            Scalar448.reduceBasisVar(iArr2, iArr3, iArr4);
            Scalar448.multiply225Var(iArr, iArr4, iArr);
            PointProjective pointProjective = new PointProjective();
            scalarMultStraus225Var(iArr, iArr3, pointAffine2, iArr4, pointAffine, pointProjective);
            return normalizeToNeutralElementVar(pointProjective);
        }
        throw new IllegalArgumentException("ctx");
    }

    private static boolean implVerify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4) {
        byte[] bArr5 = bArr;
        int i5 = i;
        if (checkContextVar(bArr3)) {
            byte[] copy = copy(bArr, i, 57);
            byte[] copy2 = copy(bArr, i5 + 57, 57);
            byte[] copy3 = copy(bArr2, i2, 57);
            if (!checkPointVar(copy)) {
                return false;
            }
            int[] iArr = new int[14];
            if (!Scalar448.checkVar(copy2, iArr) || !checkPointFullVar(copy3)) {
                return false;
            }
            PointAffine pointAffine = new PointAffine();
            if (!decodePointVar(copy, true, pointAffine)) {
                return false;
            }
            PointAffine pointAffine2 = new PointAffine();
            if (!decodePointVar(copy3, true, pointAffine2)) {
                return false;
            }
            Xof createXof = createXof();
            byte[] bArr6 = new byte[114];
            dom4(createXof, b, bArr3);
            createXof.update(copy, 0, 57);
            createXof.update(copy3, 0, 57);
            createXof.update(bArr4, i3, i4);
            createXof.doFinal(bArr6, 0, 114);
            int[] iArr2 = new int[14];
            Scalar448.decode(Scalar448.reduce912(bArr6), iArr2);
            int[] iArr3 = new int[8];
            int[] iArr4 = new int[8];
            Scalar448.reduceBasisVar(iArr2, iArr3, iArr4);
            Scalar448.multiply225Var(iArr, iArr4, iArr);
            PointProjective pointProjective = new PointProjective();
            scalarMultStraus225Var(iArr, iArr3, pointAffine2, iArr4, pointAffine, pointProjective);
            return normalizeToNeutralElementVar(pointProjective);
        }
        throw new IllegalArgumentException("ctx");
    }

    private static void invertZs(PointProjective[] pointProjectiveArr) {
        int length = pointProjectiveArr.length;
        int[] createTable = F.createTable(length);
        int[] create = F.create();
        F.copy(pointProjectiveArr[0].z, 0, create, 0);
        F.copy(create, 0, createTable, 0);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i2 >= length) {
                break;
            }
            F.mul(create, pointProjectiveArr[i2].z, create);
            F.copy(create, 0, createTable, i2 * 16);
            i = i2;
        }
        F.invVar(create, create);
        int[] create2 = F.create();
        while (i > 0) {
            int i3 = i - 1;
            F.copy(createTable, i3 * 16, create2, 0);
            F.mul(create2, create, create2);
            F.mul(create, pointProjectiveArr[i].z, create);
            F.copy(create2, 0, pointProjectiveArr[i].z, 0);
            i = i3;
        }
        F.copy(create, 0, pointProjectiveArr[0].z, 0);
    }

    private static void normalizeToAffine(PointProjective pointProjective, PointAffine pointAffine) {
        F.inv(pointProjective.z, pointAffine.y);
        F.mul(pointAffine.y, pointProjective.x, pointAffine.x);
        F.mul(pointAffine.y, pointProjective.y, pointAffine.y);
        F.normalize(pointAffine.x);
        F.normalize(pointAffine.y);
    }

    private static boolean normalizeToNeutralElementVar(PointProjective pointProjective) {
        F.normalize(pointProjective.x);
        F.normalize(pointProjective.y);
        F.normalize(pointProjective.z);
        return F.isZeroVar(pointProjective.x) && !F.isZeroVar(pointProjective.y) && F.areEqualVar(pointProjective.y, pointProjective.z);
    }

    private static void pointAdd(PointAffine pointAffine, PointProjective pointProjective, PointTemp pointTemp) {
        int[] iArr = pointTemp.r1;
        int[] iArr2 = pointTemp.r2;
        int[] iArr3 = pointTemp.r3;
        int[] iArr4 = pointTemp.r4;
        int[] iArr5 = pointTemp.r5;
        int[] iArr6 = pointTemp.r6;
        int[] iArr7 = pointTemp.r7;
        F.sqr(pointProjective.z, iArr);
        F.mul(pointAffine.x, pointProjective.x, iArr2);
        F.mul(pointAffine.y, pointProjective.y, iArr3);
        F.mul(iArr2, iArr3, iArr4);
        F.mul(iArr4, (int) C_d, iArr4);
        F.add(iArr, iArr4, iArr5);
        F.sub(iArr, iArr4, iArr6);
        F.add(pointAffine.y, pointAffine.x, iArr7);
        F.add(pointProjective.y, pointProjective.x, iArr4);
        F.mul(iArr7, iArr4, iArr7);
        F.add(iArr3, iArr2, iArr);
        F.sub(iArr3, iArr2, iArr4);
        F.carry(iArr);
        F.sub(iArr7, iArr, iArr7);
        F.mul(iArr7, pointProjective.z, iArr7);
        F.mul(iArr4, pointProjective.z, iArr4);
        F.mul(iArr5, iArr7, pointProjective.x);
        F.mul(iArr4, iArr6, pointProjective.y);
        F.mul(iArr5, iArr6, pointProjective.z);
    }

    private static void pointAdd(PointProjective pointProjective, PointProjective pointProjective2, PointTemp pointTemp) {
        int[] iArr = pointTemp.r0;
        int[] iArr2 = pointTemp.r1;
        int[] iArr3 = pointTemp.r2;
        int[] iArr4 = pointTemp.r3;
        int[] iArr5 = pointTemp.r4;
        int[] iArr6 = pointTemp.r5;
        int[] iArr7 = pointTemp.r6;
        int[] iArr8 = pointTemp.r7;
        F.mul(pointProjective.z, pointProjective2.z, iArr);
        F.sqr(iArr, iArr2);
        F.mul(pointProjective.x, pointProjective2.x, iArr3);
        F.mul(pointProjective.y, pointProjective2.y, iArr4);
        F.mul(iArr3, iArr4, iArr5);
        F.mul(iArr5, (int) C_d, iArr5);
        F.add(iArr2, iArr5, iArr6);
        F.sub(iArr2, iArr5, iArr7);
        F.add(pointProjective.y, pointProjective.x, iArr8);
        F.add(pointProjective2.y, pointProjective2.x, iArr5);
        F.mul(iArr8, iArr5, iArr8);
        F.add(iArr4, iArr3, iArr2);
        F.sub(iArr4, iArr3, iArr5);
        F.carry(iArr2);
        F.sub(iArr8, iArr2, iArr8);
        F.mul(iArr8, iArr, iArr8);
        F.mul(iArr5, iArr, iArr5);
        F.mul(iArr6, iArr8, pointProjective2.x);
        F.mul(iArr5, iArr7, pointProjective2.y);
        F.mul(iArr6, iArr7, pointProjective2.z);
    }

    private static void pointAddVar(boolean z, PointAffine pointAffine, PointProjective pointProjective, PointTemp pointTemp) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] iArr5 = pointTemp.r1;
        int[] iArr6 = pointTemp.r2;
        int[] iArr7 = pointTemp.r3;
        int[] iArr8 = pointTemp.r4;
        int[] iArr9 = pointTemp.r5;
        int[] iArr10 = pointTemp.r6;
        int[] iArr11 = pointTemp.r7;
        if (z) {
            F.sub(pointAffine.y, pointAffine.x, iArr11);
            iArr2 = iArr5;
            iArr3 = iArr8;
            iArr4 = iArr9;
            iArr = iArr10;
        } else {
            F.add(pointAffine.y, pointAffine.x, iArr11);
            iArr3 = iArr5;
            iArr2 = iArr8;
            iArr = iArr9;
            iArr4 = iArr10;
        }
        F.sqr(pointProjective.z, iArr5);
        F.mul(pointAffine.x, pointProjective.x, iArr6);
        F.mul(pointAffine.y, pointProjective.y, iArr7);
        F.mul(iArr6, iArr7, iArr8);
        F.mul(iArr8, (int) C_d, iArr8);
        F.add(iArr5, iArr8, iArr);
        F.sub(iArr5, iArr8, iArr4);
        F.add(pointProjective.y, pointProjective.x, iArr8);
        F.mul(iArr11, iArr8, iArr11);
        F.add(iArr7, iArr6, iArr3);
        F.sub(iArr7, iArr6, iArr2);
        F.carry(iArr3);
        F.sub(iArr11, iArr5, iArr11);
        F.mul(iArr11, pointProjective.z, iArr11);
        F.mul(iArr8, pointProjective.z, iArr8);
        F.mul(iArr9, iArr11, pointProjective.x);
        F.mul(iArr8, iArr10, pointProjective.y);
        F.mul(iArr9, iArr10, pointProjective.z);
    }

    private static void pointAddVar(boolean z, PointProjective pointProjective, PointProjective pointProjective2, PointTemp pointTemp) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] iArr5 = pointTemp.r0;
        int[] iArr6 = pointTemp.r1;
        int[] iArr7 = pointTemp.r2;
        int[] iArr8 = pointTemp.r3;
        int[] iArr9 = pointTemp.r4;
        int[] iArr10 = pointTemp.r5;
        int[] iArr11 = pointTemp.r6;
        int[] iArr12 = pointTemp.r7;
        if (z) {
            F.sub(pointProjective.y, pointProjective.x, iArr12);
            iArr2 = iArr6;
            iArr3 = iArr9;
            iArr4 = iArr10;
            iArr = iArr11;
        } else {
            F.add(pointProjective.y, pointProjective.x, iArr12);
            iArr3 = iArr6;
            iArr2 = iArr9;
            iArr = iArr10;
            iArr4 = iArr11;
        }
        F.mul(pointProjective.z, pointProjective2.z, iArr5);
        F.sqr(iArr5, iArr6);
        F.mul(pointProjective.x, pointProjective2.x, iArr7);
        F.mul(pointProjective.y, pointProjective2.y, iArr8);
        F.mul(iArr7, iArr8, iArr9);
        F.mul(iArr9, (int) C_d, iArr9);
        F.add(iArr6, iArr9, iArr);
        F.sub(iArr6, iArr9, iArr4);
        F.add(pointProjective2.y, pointProjective2.x, iArr9);
        F.mul(iArr12, iArr9, iArr12);
        F.add(iArr8, iArr7, iArr3);
        F.sub(iArr8, iArr7, iArr2);
        F.carry(iArr3);
        F.sub(iArr12, iArr6, iArr12);
        F.mul(iArr12, iArr5, iArr12);
        F.mul(iArr9, iArr5, iArr9);
        F.mul(iArr10, iArr12, pointProjective2.x);
        F.mul(iArr9, iArr11, pointProjective2.y);
        F.mul(iArr10, iArr11, pointProjective2.z);
    }

    private static void pointCopy(PointAffine pointAffine, PointProjective pointProjective) {
        F.copy(pointAffine.x, 0, pointProjective.x, 0);
        F.copy(pointAffine.y, 0, pointProjective.y, 0);
        F.one(pointProjective.z);
    }

    private static void pointCopy(PointProjective pointProjective, PointProjective pointProjective2) {
        F.copy(pointProjective.x, 0, pointProjective2.x, 0);
        F.copy(pointProjective.y, 0, pointProjective2.y, 0);
        F.copy(pointProjective.z, 0, pointProjective2.z, 0);
    }

    private static void pointDouble(PointProjective pointProjective, PointTemp pointTemp) {
        int[] iArr = pointTemp.r1;
        int[] iArr2 = pointTemp.r2;
        int[] iArr3 = pointTemp.r3;
        int[] iArr4 = pointTemp.r4;
        int[] iArr5 = pointTemp.r7;
        int[] iArr6 = pointTemp.r0;
        F.add(pointProjective.x, pointProjective.y, iArr);
        F.sqr(iArr, iArr);
        F.sqr(pointProjective.x, iArr2);
        F.sqr(pointProjective.y, iArr3);
        F.add(iArr2, iArr3, iArr4);
        F.carry(iArr4);
        F.sqr(pointProjective.z, iArr5);
        F.add(iArr5, iArr5, iArr5);
        F.carry(iArr5);
        F.sub(iArr4, iArr5, iArr6);
        F.sub(iArr, iArr4, iArr);
        F.sub(iArr2, iArr3, iArr2);
        F.mul(iArr, iArr6, pointProjective.x);
        F.mul(iArr4, iArr2, pointProjective.y);
        F.mul(iArr4, iArr6, pointProjective.z);
    }

    private static void pointLookup(int i, int i2, PointAffine pointAffine) {
        int i3 = i * 512;
        for (int i4 = 0; i4 < 16; i4++) {
            int i5 = ((i4 ^ i2) - 1) >> 31;
            F.cmov(i5, PRECOMP_BASE_COMB, i3, pointAffine.x, 0);
            F.cmov(i5, PRECOMP_BASE_COMB, i3 + 16, pointAffine.y, 0);
            i3 += 32;
        }
    }

    private static void pointLookup(int[] iArr, int i, int[] iArr2, PointProjective pointProjective) {
        int window4 = getWindow4(iArr, i);
        int i2 = (window4 >>> 3) ^ 1;
        int i3 = (window4 ^ (-i2)) & 7;
        int i4 = 0;
        for (int i5 = 0; i5 < 8; i5++) {
            int i6 = ((i5 ^ i3) - 1) >> 31;
            F.cmov(i6, iArr2, i4, pointProjective.x, 0);
            F.cmov(i6, iArr2, i4 + 16, pointProjective.y, 0);
            F.cmov(i6, iArr2, i4 + 32, pointProjective.z, 0);
            i4 += 48;
        }
        F.cnegate(i2, pointProjective.x);
    }

    private static void pointLookup15(int[] iArr, PointProjective pointProjective) {
        F.copy(iArr, 336, pointProjective.x, 0);
        F.copy(iArr, 352, pointProjective.y, 0);
        F.copy(iArr, 368, pointProjective.z, 0);
    }

    private static void pointPrecompute(PointAffine pointAffine, PointProjective[] pointProjectiveArr, int i, int i2, PointTemp pointTemp) {
        PointProjective pointProjective = new PointProjective();
        pointCopy(pointAffine, pointProjective);
        pointDouble(pointProjective, pointTemp);
        PointProjective pointProjective2 = new PointProjective();
        pointProjectiveArr[i] = pointProjective2;
        pointCopy(pointAffine, pointProjective2);
        for (int i3 = 1; i3 < i2; i3++) {
            int i4 = i + i3;
            PointProjective pointProjective3 = new PointProjective();
            pointProjectiveArr[i4] = pointProjective3;
            pointCopy(pointProjectiveArr[i4 - 1], pointProjective3);
            pointAdd(pointProjective, pointProjectiveArr[i4], pointTemp);
        }
    }

    private static int[] pointPrecompute(PointProjective pointProjective, int i, PointTemp pointTemp) {
        PointProjective pointProjective2 = new PointProjective();
        pointCopy(pointProjective, pointProjective2);
        PointProjective pointProjective3 = new PointProjective();
        pointCopy(pointProjective, pointProjective3);
        pointDouble(pointProjective3, pointTemp);
        int[] createTable = F.createTable(i * 3);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            F.copy(pointProjective2.x, 0, createTable, i2);
            F.copy(pointProjective2.y, 0, createTable, i2 + 16);
            F.copy(pointProjective2.z, 0, createTable, i2 + 32);
            i2 += 48;
            i3++;
            if (i3 == i) {
                return createTable;
            }
            pointAdd(pointProjective3, pointProjective2, pointTemp);
        }
    }

    private static void pointSetNeutral(PointProjective pointProjective) {
        F.zero(pointProjective.x);
        F.one(pointProjective.y);
        F.one(pointProjective.z);
    }

    public static void precompute() {
        int i;
        synchronized (PRECOMP_LOCK) {
            if (PRECOMP_BASE_COMB == null) {
                PointProjective[] pointProjectiveArr = new PointProjective[144];
                PointTemp pointTemp = new PointTemp();
                PointAffine pointAffine = new PointAffine();
                F.copy(B_x, 0, pointAffine.x, 0);
                F.copy(B_y, 0, pointAffine.y, 0);
                pointPrecompute(pointAffine, pointProjectiveArr, 0, 32, pointTemp);
                PointAffine pointAffine2 = new PointAffine();
                F.copy(B225_x, 0, pointAffine2.x, 0);
                F.copy(B225_y, 0, pointAffine2.y, 0);
                pointPrecompute(pointAffine2, pointProjectiveArr, 32, 32, pointTemp);
                PointProjective pointProjective = new PointProjective();
                pointCopy(pointAffine, pointProjective);
                int i2 = 5;
                PointProjective[] pointProjectiveArr2 = new PointProjective[5];
                for (int i3 = 0; i3 < 5; i3++) {
                    pointProjectiveArr2[i3] = new PointProjective();
                }
                int i4 = 0;
                int i5 = 64;
                while (i4 < i2) {
                    int i6 = i5 + 1;
                    PointProjective pointProjective2 = new PointProjective();
                    pointProjectiveArr[i5] = pointProjective2;
                    int i7 = 0;
                    while (true) {
                        i = 1;
                        if (i7 >= i2) {
                            break;
                        }
                        if (i7 == 0) {
                            pointCopy(pointProjective, pointProjective2);
                        } else {
                            pointAdd(pointProjective, pointProjective2, pointTemp);
                        }
                        pointDouble(pointProjective, pointTemp);
                        pointCopy(pointProjective, pointProjectiveArr2[i7]);
                        if (i4 + i7 != 8) {
                            while (i < 18) {
                                pointDouble(pointProjective, pointTemp);
                                i++;
                            }
                        }
                        i7++;
                        i2 = 5;
                    }
                    F.negate(pointProjective2.x, pointProjective2.x);
                    i5 = i6;
                    int i8 = 0;
                    while (i8 < 4) {
                        int i9 = i << i8;
                        int i10 = 0;
                        while (i10 < i9) {
                            PointProjective pointProjective3 = new PointProjective();
                            pointProjectiveArr[i5] = pointProjective3;
                            pointCopy(pointProjectiveArr[i5 - i9], pointProjective3);
                            pointAdd(pointProjectiveArr2[i8], pointProjectiveArr[i5], pointTemp);
                            i10++;
                            i5++;
                        }
                        i8++;
                        i = 1;
                    }
                    i4++;
                    i2 = 5;
                }
                invertZs(pointProjectiveArr);
                PRECOMP_BASE_WNAF = new PointAffine[32];
                for (int i11 = 0; i11 < 32; i11++) {
                    PointProjective pointProjective4 = pointProjectiveArr[i11];
                    PointAffine[] pointAffineArr = PRECOMP_BASE_WNAF;
                    PointAffine pointAffine3 = new PointAffine();
                    pointAffineArr[i11] = pointAffine3;
                    F.mul(pointProjective4.x, pointProjective4.z, pointAffine3.x);
                    F.normalize(pointAffine3.x);
                    F.mul(pointProjective4.y, pointProjective4.z, pointAffine3.y);
                    F.normalize(pointAffine3.y);
                }
                PRECOMP_BASE225_WNAF = new PointAffine[32];
                for (int i12 = 0; i12 < 32; i12++) {
                    PointProjective pointProjective5 = pointProjectiveArr[32 + i12];
                    PointAffine[] pointAffineArr2 = PRECOMP_BASE225_WNAF;
                    PointAffine pointAffine4 = new PointAffine();
                    pointAffineArr2[i12] = pointAffine4;
                    F.mul(pointProjective5.x, pointProjective5.z, pointAffine4.x);
                    F.normalize(pointAffine4.x);
                    F.mul(pointProjective5.y, pointProjective5.z, pointAffine4.y);
                    F.normalize(pointAffine4.y);
                }
                PRECOMP_BASE_COMB = F.createTable(160);
                int i13 = 0;
                for (int i14 = 64; i14 < 144; i14++) {
                    PointProjective pointProjective6 = pointProjectiveArr[i14];
                    F.mul(pointProjective6.x, pointProjective6.z, pointProjective6.x);
                    F.normalize(pointProjective6.x);
                    F.mul(pointProjective6.y, pointProjective6.z, pointProjective6.y);
                    F.normalize(pointProjective6.y);
                    F.copy(pointProjective6.x, 0, PRECOMP_BASE_COMB, i13);
                    F.copy(pointProjective6.y, 0, PRECOMP_BASE_COMB, i13 + 16);
                    i13 += 32;
                }
            }
        }
    }

    private static void pruneScalar(byte[] bArr, int i, byte[] bArr2) {
        System.arraycopy(bArr, i, bArr2, 0, 56);
        bArr2[0] = (byte) (bArr2[0] & 252);
        bArr2[55] = (byte) (bArr2[55] | 128);
        bArr2[56] = 0;
    }

    private static void scalarMult(byte[] bArr, PointProjective pointProjective, PointProjective pointProjective2) {
        int[] iArr = new int[15];
        Scalar448.decode(bArr, iArr);
        Scalar448.toSignedDigits(449, iArr, iArr);
        PointProjective pointProjective3 = new PointProjective();
        PointTemp pointTemp = new PointTemp();
        int[] pointPrecompute = pointPrecompute(pointProjective, 8, pointTemp);
        pointLookup15(pointPrecompute, pointProjective2);
        pointAdd(pointProjective, pointProjective2, pointTemp);
        int i = 111;
        while (true) {
            pointLookup(iArr, i, pointPrecompute, pointProjective3);
            pointAdd(pointProjective3, pointProjective2, pointTemp);
            i--;
            if (i >= 0) {
                for (int i2 = 0; i2 < 4; i2++) {
                    pointDouble(pointProjective2, pointTemp);
                }
            } else {
                return;
            }
        }
    }

    private static void scalarMultBase(byte[] bArr, PointProjective pointProjective) {
        precompute();
        int[] iArr = new int[15];
        Scalar448.decode(bArr, iArr);
        Scalar448.toSignedDigits(PRECOMP_RANGE, iArr, iArr);
        PointAffine pointAffine = new PointAffine();
        PointTemp pointTemp = new PointTemp();
        pointSetNeutral(pointProjective);
        int i = 17;
        while (true) {
            int i2 = i;
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = 0;
                for (int i5 = 0; i5 < 5; i5++) {
                    i4 = (i4 & ((1 << i5) ^ -1)) ^ ((iArr[i2 >>> 5] >>> (i2 & 31)) << i5);
                    i2 += 18;
                }
                int i6 = (i4 >>> 4) & 1;
                pointLookup(i3, ((-i6) ^ i4) & 15, pointAffine);
                F.cnegate(i6, pointAffine.x);
                pointAdd(pointAffine, pointProjective, pointTemp);
            }
            i--;
            if (i >= 0) {
                pointDouble(pointProjective, pointTemp);
            } else {
                return;
            }
        }
    }

    private static void scalarMultBaseEncoded(byte[] bArr, byte[] bArr2, int i) {
        PointProjective pointProjective = new PointProjective();
        scalarMultBase(bArr, pointProjective);
        if (encodeResult(pointProjective, bArr2, i) == 0) {
            throw new IllegalStateException();
        }
    }

    public static void scalarMultBaseXY(X448.Friend friend, byte[] bArr, int i, int[] iArr, int[] iArr2) {
        if (friend != null) {
            byte[] bArr2 = new byte[57];
            pruneScalar(bArr, i, bArr2);
            PointProjective pointProjective = new PointProjective();
            scalarMultBase(bArr2, pointProjective);
            if (checkPoint(pointProjective) != 0) {
                F.copy(pointProjective.x, 0, iArr, 0);
                F.copy(pointProjective.y, 0, iArr2, 0);
                return;
            }
            throw new IllegalStateException();
        }
        throw new NullPointerException("This method is only for use by X448");
    }

    private static void scalarMultOrderVar(PointAffine pointAffine, PointProjective pointProjective) {
        byte[] bArr = new byte[447];
        Scalar448.getOrderWnafVar(5, bArr);
        PointProjective[] pointProjectiveArr = new PointProjective[8];
        PointTemp pointTemp = new PointTemp();
        pointPrecompute(pointAffine, pointProjectiveArr, 0, 8, pointTemp);
        pointSetNeutral(pointProjective);
        int i = 446;
        while (true) {
            byte b = bArr[i];
            if (b != 0) {
                pointAddVar(b < 0, pointProjectiveArr[(b >> 1) ^ (b >> Ascii.US)], pointProjective, pointTemp);
            }
            i--;
            if (i >= 0) {
                pointDouble(pointProjective, pointTemp);
            } else {
                return;
            }
        }
    }

    private static void scalarMultStraus225Var(int[] iArr, int[] iArr2, PointAffine pointAffine, int[] iArr3, PointAffine pointAffine2, PointProjective pointProjective) {
        int i;
        precompute();
        byte[] bArr = new byte[PRECOMP_RANGE];
        int i2 = 225;
        byte[] bArr2 = new byte[225];
        byte[] bArr3 = new byte[225];
        Wnaf.getSignedVar(iArr, 7, bArr);
        Wnaf.getSignedVar(iArr2, 5, bArr2);
        Wnaf.getSignedVar(iArr3, 5, bArr3);
        PointProjective[] pointProjectiveArr = new PointProjective[8];
        PointProjective[] pointProjectiveArr2 = new PointProjective[8];
        PointTemp pointTemp = new PointTemp();
        pointPrecompute(pointAffine, pointProjectiveArr, 0, 8, pointTemp);
        pointPrecompute(pointAffine2, pointProjectiveArr2, 0, 8, pointTemp);
        pointSetNeutral(pointProjective);
        while (true) {
            i = i2 - 1;
            if (i >= 0 && (bArr[i] | bArr[i2 + BERTags.FLAGS] | bArr2[i] | bArr3[i]) == 0) {
                i2 = i;
            }
        }
        while (i >= 0) {
            byte b = bArr[i];
            boolean z = true;
            if (b != 0) {
                pointAddVar(b < 0, PRECOMP_BASE_WNAF[(b >> 1) ^ (b >> Ascii.US)], pointProjective, pointTemp);
            }
            byte b2 = bArr[i + 225];
            if (b2 != 0) {
                pointAddVar(b2 < 0, PRECOMP_BASE225_WNAF[(b2 >> 1) ^ (b2 >> Ascii.US)], pointProjective, pointTemp);
            }
            byte b3 = bArr2[i];
            if (b3 != 0) {
                pointAddVar(b3 < 0, pointProjectiveArr[(b3 >> 1) ^ (b3 >> Ascii.US)], pointProjective, pointTemp);
            }
            byte b4 = bArr3[i];
            if (b4 != 0) {
                int i3 = (b4 >> 1) ^ (b4 >> Ascii.US);
                if (b4 >= 0) {
                    z = false;
                }
                pointAddVar(z, pointProjectiveArr2[i3], pointProjective, pointTemp);
            }
            pointDouble(pointProjective, pointTemp);
            i--;
        }
        pointDouble(pointProjective, pointTemp);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4, bArr5, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        implSign(bArr, i, bArr2, (byte) 0, bArr3, i2, i3, bArr4, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Xof xof, byte[] bArr4, int i3) {
        byte[] bArr5 = new byte[64];
        if (64 == xof.doFinal(bArr5, 0, 64)) {
            implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr5, 0, 64, bArr4, i3);
            return;
        }
        throw new IllegalArgumentException("ph");
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, byte[] bArr5, int i4) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64, bArr5, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, Xof xof, byte[] bArr3, int i2) {
        byte[] bArr4 = new byte[64];
        if (64 == xof.doFinal(bArr4, 0, 64)) {
            implSign(bArr, i, bArr2, (byte) 1, bArr4, 0, 64, bArr3, i2);
            return;
        }
        throw new IllegalArgumentException("ph");
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, byte[] bArr4, int i3) {
        implSign(bArr, i, bArr2, (byte) 1, bArr3, i2, 64, bArr4, i3);
    }

    public static boolean validatePublicKeyFull(byte[] bArr, int i) {
        byte[] copy = copy(bArr, i, 57);
        if (!checkPointFullVar(copy)) {
            return false;
        }
        PointAffine pointAffine = new PointAffine();
        if (!decodePointVar(copy, false, pointAffine)) {
            return false;
        }
        return checkPointOrderVar(pointAffine);
    }

    public static PublicPoint validatePublicKeyFullExport(byte[] bArr, int i) {
        byte[] copy = copy(bArr, i, 57);
        if (!checkPointFullVar(copy)) {
            return null;
        }
        PointAffine pointAffine = new PointAffine();
        if (decodePointVar(copy, false, pointAffine) && checkPointOrderVar(pointAffine)) {
            return exportPoint(pointAffine);
        }
        return null;
    }

    public static boolean validatePublicKeyPartial(byte[] bArr, int i) {
        byte[] copy = copy(bArr, i, 57);
        if (!checkPointFullVar(copy)) {
            return false;
        }
        return decodePointVar(copy, false, new PointAffine());
    }

    public static PublicPoint validatePublicKeyPartialExport(byte[] bArr, int i) {
        byte[] copy = copy(bArr, i, 57);
        if (!checkPointFullVar(copy)) {
            return null;
        }
        PointAffine pointAffine = new PointAffine();
        if (!decodePointVar(copy, false, pointAffine)) {
            return null;
        }
        return exportPoint(pointAffine);
    }

    public static boolean verify(byte[] bArr, int i, PublicPoint publicPoint, byte[] bArr2, byte[] bArr3, int i2, int i3) {
        return implVerify(bArr, i, publicPoint, bArr2, (byte) 0, bArr3, i2, i3);
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4);
    }

    public static boolean verifyPrehash(byte[] bArr, int i, PublicPoint publicPoint, byte[] bArr2, Xof xof) {
        byte[] bArr3 = new byte[64];
        if (64 == xof.doFinal(bArr3, 0, 64)) {
            return implVerify(bArr, i, publicPoint, bArr2, (byte) 1, bArr3, 0, 64);
        }
        throw new IllegalArgumentException("ph");
    }

    public static boolean verifyPrehash(byte[] bArr, int i, PublicPoint publicPoint, byte[] bArr2, byte[] bArr3, int i2) {
        return implVerify(bArr, i, publicPoint, bArr2, (byte) 1, bArr3, i2, 64);
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Xof xof) {
        byte[] bArr4 = new byte[64];
        if (64 == xof.doFinal(bArr4, 0, 64)) {
            return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, 0, 64);
        }
        throw new IllegalArgumentException("ph");
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64);
    }
}
