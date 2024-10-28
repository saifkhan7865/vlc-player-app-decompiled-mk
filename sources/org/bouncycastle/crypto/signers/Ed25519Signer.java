package org.bouncycastle.crypto.signers;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.util.Arrays;

public class Ed25519Signer implements Signer {
    private final Buffer buffer = new Buffer();
    private boolean forSigning;
    private Ed25519PrivateKeyParameters privateKey;
    private Ed25519PublicKeyParameters publicKey;

    private static final class Buffer extends ByteArrayOutputStream {
        private Buffer() {
        }

        /* access modifiers changed from: package-private */
        public synchronized byte[] generateSignature(Ed25519PrivateKeyParameters ed25519PrivateKeyParameters) {
            byte[] bArr;
            bArr = new byte[64];
            ed25519PrivateKeyParameters.sign(0, (byte[]) null, this.buf, 0, this.count, bArr, 0);
            reset();
            return bArr;
        }

        public synchronized void reset() {
            Arrays.fill(this.buf, 0, this.count, (byte) 0);
            this.count = 0;
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean verifySignature(Ed25519PublicKeyParameters ed25519PublicKeyParameters, byte[] bArr) {
            if (64 != bArr.length) {
                reset();
                return false;
            }
            boolean verify = ed25519PublicKeyParameters.verify(0, (byte[]) null, this.buf, 0, this.count, bArr, 0);
            reset();
            return verify;
        }
    }

    public byte[] generateSignature() {
        Ed25519PrivateKeyParameters ed25519PrivateKeyParameters;
        if (this.forSigning && (ed25519PrivateKeyParameters = this.privateKey) != null) {
            return this.buffer.generateSignature(ed25519PrivateKeyParameters);
        }
        throw new IllegalStateException("Ed25519Signer not initialised for signature generation.");
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        this.forSigning = z;
        Ed25519PublicKeyParameters ed25519PublicKeyParameters = null;
        if (z) {
            this.privateKey = (Ed25519PrivateKeyParameters) cipherParameters;
        } else {
            this.privateKey = null;
            ed25519PublicKeyParameters = (Ed25519PublicKeyParameters) cipherParameters;
        }
        this.publicKey = ed25519PublicKeyParameters;
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(EdDSAParameterSpec.Ed25519, 128, cipherParameters, z));
        reset();
    }

    public void reset() {
        this.buffer.reset();
    }

    public void update(byte b) {
        this.buffer.write(b);
    }

    public void update(byte[] bArr, int i, int i2) {
        this.buffer.write(bArr, i, i2);
    }

    public boolean verifySignature(byte[] bArr) {
        Ed25519PublicKeyParameters ed25519PublicKeyParameters;
        if (!this.forSigning && (ed25519PublicKeyParameters = this.publicKey) != null) {
            return this.buffer.verifySignature(ed25519PublicKeyParameters, bArr);
        }
        throw new IllegalStateException("Ed25519Signer not initialised for verification");
    }
}
