package org.bouncycastle.crypto.agreement;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.RawAgreement;
import org.bouncycastle.crypto.params.X448PrivateKeyParameters;
import org.bouncycastle.crypto.params.X448PublicKeyParameters;
import org.bouncycastle.jcajce.spec.XDHParameterSpec;

public final class X448Agreement implements RawAgreement {
    private X448PrivateKeyParameters privateKey;

    public void calculateAgreement(CipherParameters cipherParameters, byte[] bArr, int i) {
        this.privateKey.generateSecret((X448PublicKeyParameters) cipherParameters, bArr, i);
    }

    public int getAgreementSize() {
        return 56;
    }

    public void init(CipherParameters cipherParameters) {
        X448PrivateKeyParameters x448PrivateKeyParameters = (X448PrivateKeyParameters) cipherParameters;
        this.privateKey = x448PrivateKeyParameters;
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(XDHParameterSpec.X448, x448PrivateKeyParameters));
    }
}
