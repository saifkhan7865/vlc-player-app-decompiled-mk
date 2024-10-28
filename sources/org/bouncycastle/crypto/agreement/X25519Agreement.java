package org.bouncycastle.crypto.agreement;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.RawAgreement;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.jcajce.spec.XDHParameterSpec;

public final class X25519Agreement implements RawAgreement {
    private X25519PrivateKeyParameters privateKey;

    public void calculateAgreement(CipherParameters cipherParameters, byte[] bArr, int i) {
        this.privateKey.generateSecret((X25519PublicKeyParameters) cipherParameters, bArr, i);
    }

    public int getAgreementSize() {
        return 32;
    }

    public void init(CipherParameters cipherParameters) {
        X25519PrivateKeyParameters x25519PrivateKeyParameters = (X25519PrivateKeyParameters) cipherParameters;
        this.privateKey = x25519PrivateKeyParameters;
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(XDHParameterSpec.X25519, x25519PrivateKeyParameters));
    }
}
