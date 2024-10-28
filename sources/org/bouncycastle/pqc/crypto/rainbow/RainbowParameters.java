package org.bouncycastle.pqc.crypto.rainbow;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;

public class RainbowParameters implements CipherParameters {
    private static final int len_pkseed = 32;
    private static final int len_salt = 16;
    private static final int len_skseed = 32;
    public static final RainbowParameters rainbowIIIcircumzenithal = new RainbowParameters("rainbow-III-circumzenithal", 3, Version.CIRCUMZENITHAL);
    public static final RainbowParameters rainbowIIIclassic = new RainbowParameters("rainbow-III-classic", 3, Version.CLASSIC);
    public static final RainbowParameters rainbowIIIcompressed = new RainbowParameters("rainbow-III-compressed", 3, Version.COMPRESSED);
    public static final RainbowParameters rainbowVcircumzenithal = new RainbowParameters("rainbow-V-circumzenithal", 5, Version.CIRCUMZENITHAL);
    public static final RainbowParameters rainbowVclassic = new RainbowParameters("rainbow-V-classic", 5, Version.CLASSIC);
    public static final RainbowParameters rainbowVcompressed = new RainbowParameters("rainbow-V-compressed", 5, Version.COMPRESSED);
    private final Digest hash_algo;
    private final int m;
    private final int n;
    private final String name;
    private final int o1;
    private final int o2;
    private final int v1;
    private final int v2;
    private final Version version;

    private RainbowParameters(String str, int i, Version version2) {
        Digest digest;
        this.name = str;
        if (i == 3) {
            this.v1 = 68;
            this.o1 = 32;
            this.o2 = 48;
            digest = new SHA384Digest();
        } else if (i == 5) {
            this.v1 = 96;
            this.o1 = 36;
            this.o2 = 64;
            digest = new SHA512Digest();
        } else {
            throw new IllegalArgumentException("No valid version. Please choose one of the following: 3, 5");
        }
        this.hash_algo = digest;
        int i2 = this.v1;
        int i3 = this.o1;
        this.v2 = i2 + i3;
        int i4 = this.o2;
        this.n = i2 + i3 + i4;
        this.m = i3 + i4;
        this.version = version2;
    }

    /* access modifiers changed from: package-private */
    public Digest getHash_algo() {
        return this.hash_algo;
    }

    /* access modifiers changed from: package-private */
    public int getLen_pkseed() {
        return 32;
    }

    /* access modifiers changed from: package-private */
    public int getLen_salt() {
        return 16;
    }

    /* access modifiers changed from: package-private */
    public int getLen_skseed() {
        return 32;
    }

    /* access modifiers changed from: package-private */
    public int getM() {
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public int getN() {
        return this.n;
    }

    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: package-private */
    public int getO1() {
        return this.o1;
    }

    /* access modifiers changed from: package-private */
    public int getO2() {
        return this.o2;
    }

    /* access modifiers changed from: package-private */
    public int getV1() {
        return this.v1;
    }

    /* access modifiers changed from: package-private */
    public int getV2() {
        return this.v2;
    }

    /* access modifiers changed from: package-private */
    public Version getVersion() {
        return this.version;
    }
}
