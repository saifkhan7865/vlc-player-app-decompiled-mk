package org.bouncycastle.pqc.crypto.saber;

import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.SICBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

abstract class Symmetric {

    static class AesSymmetric extends Symmetric {
        private final StreamCipher cipher = SICBlockCipher.newInstance(AESEngine.newInstance());
        private final SHA256Digest sha256Digest = new SHA256Digest();
        private final SHA512Digest sha512Digest = new SHA512Digest();

        AesSymmetric() {
        }

        /* access modifiers changed from: package-private */
        public void hash_g(byte[] bArr, byte[] bArr2) {
            this.sha512Digest.update(bArr2, 0, bArr2.length);
            this.sha512Digest.doFinal(bArr, 0);
        }

        /* access modifiers changed from: package-private */
        public void hash_h(byte[] bArr, byte[] bArr2, int i) {
            this.sha256Digest.update(bArr2, 0, bArr2.length);
            this.sha256Digest.doFinal(bArr, i);
        }

        /* access modifiers changed from: package-private */
        public void prf(byte[] bArr, byte[] bArr2, int i, int i2) {
            this.cipher.init(true, new ParametersWithIV(new KeyParameter(bArr2, 0, i), new byte[16]));
            this.cipher.processBytes(new byte[i2], 0, i2, bArr, 0);
        }
    }

    static class ShakeSymmetric extends Symmetric {
        private final SHA3Digest sha3Digest256 = new SHA3Digest(256);
        private final SHA3Digest sha3Digest512 = new SHA3Digest(512);
        private final Xof shakeDigest = new SHAKEDigest(128);

        ShakeSymmetric() {
        }

        /* access modifiers changed from: package-private */
        public void hash_g(byte[] bArr, byte[] bArr2) {
            this.sha3Digest512.update(bArr2, 0, bArr2.length);
            this.sha3Digest512.doFinal(bArr, 0);
        }

        /* access modifiers changed from: package-private */
        public void hash_h(byte[] bArr, byte[] bArr2, int i) {
            this.sha3Digest256.update(bArr2, 0, bArr2.length);
            this.sha3Digest256.doFinal(bArr, i);
        }

        /* access modifiers changed from: package-private */
        public void prf(byte[] bArr, byte[] bArr2, int i, int i2) {
            this.shakeDigest.reset();
            this.shakeDigest.update(bArr2, 0, i);
            this.shakeDigest.doFinal(bArr, 0, i2);
        }
    }

    Symmetric() {
    }

    /* access modifiers changed from: package-private */
    public abstract void hash_g(byte[] bArr, byte[] bArr2);

    /* access modifiers changed from: package-private */
    public abstract void hash_h(byte[] bArr, byte[] bArr2, int i);

    /* access modifiers changed from: package-private */
    public abstract void prf(byte[] bArr, byte[] bArr2, int i, int i2);
}
