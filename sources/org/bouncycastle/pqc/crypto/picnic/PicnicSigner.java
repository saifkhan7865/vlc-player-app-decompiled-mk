package org.bouncycastle.pqc.crypto.picnic;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

public class PicnicSigner implements MessageSigner {
    private PicnicPrivateKeyParameters privKey;
    private PicnicPublicKeyParameters pubKey;

    public byte[] generateSignature(byte[] bArr) {
        PicnicEngine engine = this.privKey.getParameters().getEngine();
        byte[] bArr2 = new byte[engine.getSignatureSize(bArr.length)];
        engine.crypto_sign(bArr2, bArr, this.privKey.getEncoded());
        byte[] bArr3 = new byte[engine.getTrueSignatureSize()];
        System.arraycopy(bArr2, bArr.length + 4, bArr3, 0, engine.getTrueSignatureSize());
        return bArr3;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        if (z) {
            this.privKey = (PicnicPrivateKeyParameters) cipherParameters;
        } else {
            this.pubKey = (PicnicPublicKeyParameters) cipherParameters;
        }
    }

    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        PicnicEngine engine = this.pubKey.getParameters().getEngine();
        byte[] bArr3 = new byte[bArr.length];
        boolean crypto_sign_open = engine.crypto_sign_open(bArr3, Arrays.concatenate(Pack.intToLittleEndian(bArr2.length), bArr, bArr2), this.pubKey.getEncoded());
        if (!Arrays.areEqual(bArr, bArr3)) {
            return false;
        }
        return crypto_sign_open;
    }
}
