package org.bouncycastle.pqc.crypto.rainbow;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;

public class RainbowKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private RainbowKeyComputation rkc;
    private Version version;

    /* renamed from: org.bouncycastle.pqc.crypto.rainbow.RainbowKeyPairGenerator$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.bouncycastle.pqc.crypto.rainbow.Version[] r0 = org.bouncycastle.pqc.crypto.rainbow.Version.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version = r0
                org.bouncycastle.pqc.crypto.rainbow.Version r1 = org.bouncycastle.pqc.crypto.rainbow.Version.CLASSIC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version     // Catch:{ NoSuchFieldError -> 0x001d }
                org.bouncycastle.pqc.crypto.rainbow.Version r1 = org.bouncycastle.pqc.crypto.rainbow.Version.CIRCUMZENITHAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.bouncycastle.pqc.crypto.rainbow.Version r1 = org.bouncycastle.pqc.crypto.rainbow.Version.COMPRESSED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.rainbow.RainbowKeyPairGenerator.AnonymousClass1.<clinit>():void");
        }
    }

    private void initialize(KeyGenerationParameters keyGenerationParameters) {
        RainbowParameters parameters = ((RainbowKeyGenerationParameters) keyGenerationParameters).getParameters();
        this.rkc = new RainbowKeyComputation(parameters, keyGenerationParameters.getRandom());
        this.version = parameters.getVersion();
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version[this.version.ordinal()];
        if (i == 1) {
            return this.rkc.genKeyPairClassical();
        }
        if (i == 2) {
            return this.rkc.genKeyPairCircumzenithal();
        }
        if (i == 3) {
            return this.rkc.genKeyPairCompressed();
        }
        throw new IllegalArgumentException("No valid version. Please choose one of the following: classic, circumzenithal, compressed");
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }
}
