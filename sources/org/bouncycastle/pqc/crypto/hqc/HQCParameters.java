package org.bouncycastle.pqc.crypto.hqc;

import nl.dionsegijn.konfetti.core.Angle;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.pqc.crypto.KEMParameters;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.util.Permissions;

public class HQCParameters implements KEMParameters {
    static final int GF_MUL_ORDER = 255;
    static final int PARAM_M = 8;
    public static final HQCParameters hqc128 = new HQCParameters("hqc-128", 17669, 46, KyberEngine.KyberPolyBytes, 16, 31, 15, 66, 75, 75, 16767881, 4, new int[]{89, 69, 153, 116, 176, 117, 111, 75, 73, 233, 242, 233, 65, 210, 21, 139, 103, 173, 67, 118, 105, 210, 174, 110, 74, 69, 228, 82, 255, 181, 1});
    public static final HQCParameters hqc192 = new HQCParameters("hqc-192", 35851, 56, 640, 24, 33, 16, 100, 114, 114, 16742417, 5, new int[]{45, 216, 239, 24, Permissions.PERMISSION_WRITE_STORAGE_TAG, 104, 27, 40, 107, 50, 163, 210, 227, 134, BERTags.FLAGS, 158, 119, 13, 158, 1, 238, 164, 82, 43, 15, 232, 246, 142, 50, 189, 29, 232, 1});
    public static final HQCParameters hqc256 = new HQCParameters("hqc-256", 57637, 90, 640, 32, 59, 29, 131, 149, 149, 16772367, 5, new int[]{49, 167, 49, 39, 200, 121, 124, 91, 240, 63, 148, 71, 150, 123, 87, 101, 32, 215, 159, 71, 201, 115, 97, 210, 186, 183, 141, 217, 123, 12, 31, 243, Angle.LEFT, 219, MediaWrapper.META_AUDIODELAY, 239, 99, 141, 4, 246, 191, 144, 8, 232, 47, 27, 141, 178, 130, 64, 124, 47, 39, 188, 216, 48, 199, 187, 1});
    private int delta;
    private int fft;
    private int g;
    private int[] generatorPoly;
    private HQCEngine hqcEngine;
    private int k;
    private int n;
    private int n1;
    private int n2;
    private final String name;
    private int utilRejectionThreshold;
    private int w;
    private int we;
    private int wr;

    private HQCParameters(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
        this.name = str;
        int i12 = i;
        this.n = i12;
        int i13 = i2;
        this.n1 = i13;
        int i14 = i3;
        this.n2 = i14;
        int i15 = i4;
        this.k = i15;
        int i16 = i6;
        this.delta = i16;
        int i17 = i7;
        this.w = i17;
        int i18 = i8;
        this.wr = i18;
        int i19 = i9;
        this.we = i19;
        int[] iArr2 = iArr;
        this.generatorPoly = iArr2;
        int i20 = i5;
        this.g = i20;
        int i21 = i10;
        this.utilRejectionThreshold = i21;
        int i22 = i11;
        this.fft = i22;
        this.hqcEngine = new HQCEngine(i12, i13, i14, i15, i20, i16, i17, i18, i19, i21, i22, iArr2);
    }

    /* access modifiers changed from: package-private */
    public int getDelta() {
        return this.delta;
    }

    /* access modifiers changed from: package-private */
    public HQCEngine getEngine() {
        return this.hqcEngine;
    }

    /* access modifiers changed from: package-private */
    public int getK() {
        return this.k;
    }

    /* access modifiers changed from: package-private */
    public int getN() {
        return this.n;
    }

    /* access modifiers changed from: package-private */
    public int getN1() {
        return this.n1;
    }

    /* access modifiers changed from: package-private */
    public int getN1N2_BYTES() {
        return ((this.n1 * this.n2) + 7) / 8;
    }

    /* access modifiers changed from: package-private */
    public int getN2() {
        return this.n2;
    }

    /* access modifiers changed from: package-private */
    public int getN_BYTES() {
        return (this.n + 7) / 8;
    }

    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: package-private */
    public int getSALT_SIZE_BYTES() {
        return 16;
    }

    /* access modifiers changed from: package-private */
    public int getSHA512_BYTES() {
        return 64;
    }

    public int getSessionKeySize() {
        return this.k * 8;
    }

    /* access modifiers changed from: package-private */
    public int getW() {
        return this.w;
    }

    /* access modifiers changed from: package-private */
    public int getWe() {
        return this.we;
    }

    /* access modifiers changed from: package-private */
    public int getWr() {
        return this.wr;
    }
}
