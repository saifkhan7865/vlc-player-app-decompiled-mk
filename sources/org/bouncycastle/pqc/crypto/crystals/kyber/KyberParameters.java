package org.bouncycastle.pqc.crypto.crystals.kyber;

import org.bouncycastle.pqc.crypto.KEMParameters;

public class KyberParameters implements KEMParameters {
    public static final KyberParameters kyber1024 = new KyberParameters("kyber1024", 4, 256, false);
    public static final KyberParameters kyber512 = new KyberParameters("kyber512", 2, 256, false);
    public static final KyberParameters kyber768 = new KyberParameters("kyber768", 3, 256, false);
    private final int k;
    private final String name;
    private final int sessionKeySize;
    private final boolean usingAes;

    private KyberParameters(String str, int i, int i2, boolean z) {
        this.name = str;
        this.k = i;
        this.sessionKeySize = i2;
        this.usingAes = z;
    }

    /* access modifiers changed from: package-private */
    public KyberEngine getEngine() {
        return new KyberEngine(this.k, this.usingAes);
    }

    public String getName() {
        return this.name;
    }

    public int getSessionKeySize() {
        return this.sessionKeySize;
    }
}
