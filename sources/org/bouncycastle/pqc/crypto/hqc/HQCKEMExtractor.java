package org.bouncycastle.pqc.crypto.hqc;

import org.bouncycastle.crypto.EncapsulatedSecretExtractor;
import org.bouncycastle.util.Arrays;

public class HQCKEMExtractor implements EncapsulatedSecretExtractor {
    private HQCEngine engine;
    private HQCKeyParameters key;

    public HQCKEMExtractor(HQCPrivateKeyParameters hQCPrivateKeyParameters) {
        this.key = hQCPrivateKeyParameters;
        initCipher(hQCPrivateKeyParameters.getParameters());
    }

    private void initCipher(HQCParameters hQCParameters) {
        this.engine = hQCParameters.getEngine();
    }

    public byte[] extractSecret(byte[] bArr) {
        byte[] bArr2 = new byte[this.engine.getSessionKeySize()];
        this.engine.decaps(bArr2, bArr, ((HQCPrivateKeyParameters) this.key).getPrivateKey());
        return Arrays.copyOfRange(bArr2, 0, this.key.getParameters().getK());
    }

    public int getEncapsulationLength() {
        return this.key.getParameters().getN_BYTES() + this.key.getParameters().getN1N2_BYTES() + 80;
    }
}
