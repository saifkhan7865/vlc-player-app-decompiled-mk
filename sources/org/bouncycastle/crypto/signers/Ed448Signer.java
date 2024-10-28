package org.bouncycastle.crypto.signers;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.Ed448PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed448PublicKeyParameters;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.util.Arrays;

public class Ed448Signer implements Signer {
    private final Buffer buffer = new Buffer();
    private final byte[] context;
    private boolean forSigning;
    private Ed448PrivateKeyParameters privateKey;
    private Ed448PublicKeyParameters publicKey;

    private static final class Buffer extends ByteArrayOutputStream {
        private Buffer() {
        }

        /* access modifiers changed from: package-private */
        public synchronized byte[] generateSignature(Ed448PrivateKeyParameters ed448PrivateKeyParameters, byte[] bArr) {
            byte[] bArr2;
            bArr2 = new byte[114];
            ed448PrivateKeyParameters.sign(0, bArr, this.buf, 0, this.count, bArr2, 0);
            reset();
            return bArr2;
        }

        public synchronized void reset() {
            Arrays.fill(this.buf, 0, this.count, (byte) 0);
            this.count = 0;
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean verifySignature(Ed448PublicKeyParameters ed448PublicKeyParameters, byte[] bArr, byte[] bArr2) {
            if (114 != bArr2.length) {
                reset();
                return false;
            }
            boolean verify = ed448PublicKeyParameters.verify(0, bArr, this.buf, 0, this.count, bArr2, 0);
            reset();
            return verify;
        }
    }

    public Ed448Signer(byte[] bArr) {
        if (bArr != null) {
            this.context = Arrays.clone(bArr);
            return;
        }
        throw new NullPointerException("'context' cannot be null");
    }

    public byte[] generateSignature() {
        Ed448PrivateKeyParameters ed448PrivateKeyParameters;
        if (this.forSigning && (ed448PrivateKeyParameters = this.privateKey) != null) {
            return this.buffer.generateSignature(ed448PrivateKeyParameters, this.context);
        }
        throw new IllegalStateException("Ed448Signer not initialised for signature generation.");
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        this.forSigning = z;
        Ed448PublicKeyParameters ed448PublicKeyParameters = null;
        if (z) {
            this.privateKey = (Ed448PrivateKeyParameters) cipherParameters;
        } else {
            this.privateKey = null;
            ed448PublicKeyParameters = (Ed448PublicKeyParameters) cipherParameters;
        }
        this.publicKey = ed448PublicKeyParameters;
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(EdDSAParameterSpec.Ed448, BERTags.FLAGS, cipherParameters, z));
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
        Ed448PublicKeyParameters ed448PublicKeyParameters;
        if (!this.forSigning && (ed448PublicKeyParameters = this.publicKey) != null) {
            return this.buffer.verifySignature(ed448PublicKeyParameters, this.context, bArr);
        }
        throw new IllegalStateException("Ed448Signer not initialised for verification");
    }
}
