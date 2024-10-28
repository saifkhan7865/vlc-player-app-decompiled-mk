package org.bouncycastle.pqc.crypto.crystals.dilithium;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;

public class DilithiumSigner implements MessageSigner {
    private DilithiumPrivateKeyParameters privKey;
    private DilithiumPublicKeyParameters pubKey;
    private SecureRandom random;

    public byte[] generateSignature(byte[] bArr) {
        return this.privKey.getParameters().getEngine(this.random).sign(bArr, bArr.length, this.privKey.rho, this.privKey.k, this.privKey.tr, this.privKey.t0, this.privKey.s1, this.privKey.s2);
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        SecureRandom secureRandom;
        if (z) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.privKey = (DilithiumPrivateKeyParameters) parametersWithRandom.getParameters();
                secureRandom = parametersWithRandom.getRandom();
            } else {
                this.privKey = (DilithiumPrivateKeyParameters) cipherParameters;
                secureRandom = null;
            }
            this.random = secureRandom;
            return;
        }
        this.pubKey = (DilithiumPublicKeyParameters) cipherParameters;
    }

    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        return this.pubKey.getParameters().getEngine(this.random).signOpen(bArr, bArr2, bArr2.length, this.pubKey.rho, this.pubKey.t1);
    }
}
