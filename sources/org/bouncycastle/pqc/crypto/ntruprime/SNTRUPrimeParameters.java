package org.bouncycastle.pqc.crypto.ntruprime;

import androidx.core.view.PointerIconCompat;
import org.bouncycastle.pqc.crypto.KEMParameters;
import org.videolan.libvlc.MediaPlayer;

public class SNTRUPrimeParameters implements KEMParameters {
    public static final SNTRUPrimeParameters sntrup1013 = new SNTRUPrimeParameters("sntrup1013", PointerIconCompat.TYPE_ALL_SCROLL, 7177, 448, 1623, 1423, 1623, 2417, 24);
    public static final SNTRUPrimeParameters sntrup1277 = new SNTRUPrimeParameters("sntrup1277", 1277, 7879, 492, 2067, 1815, 2067, 3059, 32);
    public static final SNTRUPrimeParameters sntrup653 = new SNTRUPrimeParameters("sntrup653", 653, 4621, 288, 994, 865, 994, 1518, 16);
    public static final SNTRUPrimeParameters sntrup761 = new SNTRUPrimeParameters("sntrup761", 761, 4591, MediaPlayer.Event.RecordChanged, 1158, PointerIconCompat.TYPE_CROSSHAIR, 1158, 1763, 16);
    public static final SNTRUPrimeParameters sntrup857 = new SNTRUPrimeParameters("sntrup857", 857, 5167, 322, 1322, 1152, 1322, 1999, 16);
    public static final SNTRUPrimeParameters sntrup953 = new SNTRUPrimeParameters("sntrup953", 953, 6343, 396, 1505, 1317, 1505, 2254, 24);
    private final String name;
    private final int p;
    private final int privateKeyBytes;
    private final int publicKeyBytes;
    private final int q;
    private final int roundedPolynomialBytes;
    private final int rqPolynomialBytes;
    private final int sharedKeyBytes;
    private final int w;

    private SNTRUPrimeParameters(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.name = str;
        this.p = i;
        this.q = i2;
        this.w = i3;
        this.rqPolynomialBytes = i4;
        this.roundedPolynomialBytes = i5;
        this.publicKeyBytes = i6;
        this.privateKeyBytes = i7;
        this.sharedKeyBytes = i8;
    }

    public String getName() {
        return this.name;
    }

    public int getP() {
        return this.p;
    }

    public int getPrivateKeyBytes() {
        return this.privateKeyBytes;
    }

    public int getPublicKeyBytes() {
        return this.publicKeyBytes;
    }

    public int getQ() {
        return this.q;
    }

    public int getRoundedPolynomialBytes() {
        return this.roundedPolynomialBytes;
    }

    public int getRqPolynomialBytes() {
        return this.rqPolynomialBytes;
    }

    public int getSessionKeySize() {
        return this.sharedKeyBytes * 8;
    }

    public int getW() {
        return this.w;
    }
}
