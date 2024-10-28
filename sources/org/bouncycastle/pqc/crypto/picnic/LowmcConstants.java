package org.bouncycastle.pqc.crypto.picnic;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Properties;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.encoders.Hex;

abstract class LowmcConstants {
    protected KMatrices KMatrix;
    protected KMatrices KMatrix_full;
    protected KMatrices KMatrix_inv;
    protected KMatrices LMatrix;
    protected KMatrices LMatrix_full;
    protected KMatrices LMatrix_inv;
    protected KMatrices RConstants;
    protected KMatrices RConstants_full;
    protected int[] keyMatrices;
    protected int[] keyMatrices_full;
    protected int[] keyMatrices_inv;
    protected int[] linearMatrices;
    protected int[] linearMatrices_full;
    protected int[] linearMatrices_inv;
    protected int[] roundConstants;
    protected int[] roundConstants_full;

    LowmcConstants() {
    }

    private KMatricesWithPointer GET_MAT(KMatrices kMatrices, int i) {
        KMatricesWithPointer kMatricesWithPointer = new KMatricesWithPointer(kMatrices);
        kMatricesWithPointer.setMatrixPointer(i * kMatricesWithPointer.getSize());
        return kMatricesWithPointer;
    }

    static int[] ReadFromProperty(Properties properties, String str, int i) {
        byte[] decode = Hex.decode(removeCommas(properties.getProperty(str)));
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < decode.length / 4; i2++) {
            iArr[i2] = Pack.littleEndianToInt(decode, i2 * 4);
        }
        return iArr;
    }

    static int[] readArray(DataInputStream dataInputStream) throws IOException {
        int readInt = dataInputStream.readInt();
        int[] iArr = new int[readInt];
        for (int i = 0; i != readInt; i++) {
            iArr[i] = dataInputStream.readInt();
        }
        return iArr;
    }

    private static byte[] removeCommas(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i != str.length(); i++) {
            if (str.charAt(i) != ',') {
                byteArrayOutputStream.write(str.charAt(i));
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        if (r3.numRounds == 4) goto L_0x0013;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bouncycastle.pqc.crypto.picnic.KMatricesWithPointer KMatrix(org.bouncycastle.pqc.crypto.picnic.PicnicEngine r3, int r4) {
        /*
            r2 = this;
            int r0 = r3.stateSizeBits
            r1 = 128(0x80, float:1.794E-43)
            if (r0 != r1) goto L_0x000d
        L_0x0006:
            org.bouncycastle.pqc.crypto.picnic.KMatrices r3 = r2.KMatrix
        L_0x0008:
            org.bouncycastle.pqc.crypto.picnic.KMatricesWithPointer r3 = r2.GET_MAT(r3, r4)
            return r3
        L_0x000d:
            int r0 = r3.stateSizeBits
            r1 = 129(0x81, float:1.81E-43)
            if (r0 != r1) goto L_0x0016
        L_0x0013:
            org.bouncycastle.pqc.crypto.picnic.KMatrices r3 = r2.KMatrix_full
            goto L_0x0008
        L_0x0016:
            int r0 = r3.stateSizeBits
            r1 = 192(0xc0, float:2.69E-43)
            if (r0 != r1) goto L_0x0022
            int r3 = r3.numRounds
            r0 = 4
            if (r3 != r0) goto L_0x0006
            goto L_0x0013
        L_0x0022:
            int r0 = r3.stateSizeBits
            r1 = 255(0xff, float:3.57E-43)
            if (r0 != r1) goto L_0x0029
            goto L_0x0013
        L_0x0029:
            int r3 = r3.stateSizeBits
            r0 = 256(0x100, float:3.59E-43)
            if (r3 != r0) goto L_0x0030
            goto L_0x0006
        L_0x0030:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.picnic.LowmcConstants.KMatrix(org.bouncycastle.pqc.crypto.picnic.PicnicEngine, int):org.bouncycastle.pqc.crypto.picnic.KMatricesWithPointer");
    }

    /* access modifiers changed from: protected */
    public KMatricesWithPointer KMatrixInv(PicnicEngine picnicEngine) {
        if (picnicEngine.stateSizeBits == 129 || ((picnicEngine.stateSizeBits == 192 && picnicEngine.numRounds == 4) || picnicEngine.stateSizeBits == 255)) {
            return GET_MAT(this.KMatrix_inv, 0);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        if (r3.numRounds == 4) goto L_0x0013;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bouncycastle.pqc.crypto.picnic.KMatricesWithPointer LMatrix(org.bouncycastle.pqc.crypto.picnic.PicnicEngine r3, int r4) {
        /*
            r2 = this;
            int r0 = r3.stateSizeBits
            r1 = 128(0x80, float:1.794E-43)
            if (r0 != r1) goto L_0x000d
        L_0x0006:
            org.bouncycastle.pqc.crypto.picnic.KMatrices r3 = r2.LMatrix
        L_0x0008:
            org.bouncycastle.pqc.crypto.picnic.KMatricesWithPointer r3 = r2.GET_MAT(r3, r4)
            return r3
        L_0x000d:
            int r0 = r3.stateSizeBits
            r1 = 129(0x81, float:1.81E-43)
            if (r0 != r1) goto L_0x0016
        L_0x0013:
            org.bouncycastle.pqc.crypto.picnic.KMatrices r3 = r2.LMatrix_full
            goto L_0x0008
        L_0x0016:
            int r0 = r3.stateSizeBits
            r1 = 192(0xc0, float:2.69E-43)
            if (r0 != r1) goto L_0x0022
            int r3 = r3.numRounds
            r0 = 4
            if (r3 != r0) goto L_0x0006
            goto L_0x0013
        L_0x0022:
            int r0 = r3.stateSizeBits
            r1 = 255(0xff, float:3.57E-43)
            if (r0 != r1) goto L_0x0029
            goto L_0x0013
        L_0x0029:
            int r3 = r3.stateSizeBits
            r0 = 256(0x100, float:3.59E-43)
            if (r3 != r0) goto L_0x0030
            goto L_0x0006
        L_0x0030:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.picnic.LowmcConstants.LMatrix(org.bouncycastle.pqc.crypto.picnic.PicnicEngine, int):org.bouncycastle.pqc.crypto.picnic.KMatricesWithPointer");
    }

    /* access modifiers changed from: protected */
    public KMatricesWithPointer LMatrixInv(PicnicEngine picnicEngine, int i) {
        if (picnicEngine.stateSizeBits == 129 || ((picnicEngine.stateSizeBits == 192 && picnicEngine.numRounds == 4) || picnicEngine.stateSizeBits == 255)) {
            return GET_MAT(this.LMatrix_inv, i);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        if (r3.numRounds == 4) goto L_0x0013;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bouncycastle.pqc.crypto.picnic.KMatricesWithPointer RConstant(org.bouncycastle.pqc.crypto.picnic.PicnicEngine r3, int r4) {
        /*
            r2 = this;
            int r0 = r3.stateSizeBits
            r1 = 128(0x80, float:1.794E-43)
            if (r0 != r1) goto L_0x000d
        L_0x0006:
            org.bouncycastle.pqc.crypto.picnic.KMatrices r3 = r2.RConstants
        L_0x0008:
            org.bouncycastle.pqc.crypto.picnic.KMatricesWithPointer r3 = r2.GET_MAT(r3, r4)
            return r3
        L_0x000d:
            int r0 = r3.stateSizeBits
            r1 = 129(0x81, float:1.81E-43)
            if (r0 != r1) goto L_0x0016
        L_0x0013:
            org.bouncycastle.pqc.crypto.picnic.KMatrices r3 = r2.RConstants_full
            goto L_0x0008
        L_0x0016:
            int r0 = r3.stateSizeBits
            r1 = 192(0xc0, float:2.69E-43)
            if (r0 != r1) goto L_0x0022
            int r3 = r3.numRounds
            r0 = 4
            if (r3 != r0) goto L_0x0006
            goto L_0x0013
        L_0x0022:
            int r0 = r3.stateSizeBits
            r1 = 255(0xff, float:3.57E-43)
            if (r0 != r1) goto L_0x0029
            goto L_0x0013
        L_0x0029:
            int r3 = r3.stateSizeBits
            r0 = 256(0x100, float:3.59E-43)
            if (r3 != r0) goto L_0x0030
            goto L_0x0006
        L_0x0030:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.picnic.LowmcConstants.RConstant(org.bouncycastle.pqc.crypto.picnic.PicnicEngine, int):org.bouncycastle.pqc.crypto.picnic.KMatricesWithPointer");
    }
}
