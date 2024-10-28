package org.bouncycastle.crypto.params;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import org.bouncycastle.math.ec.rfc8032.Ed448;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.io.Streams;

public final class Ed448PrivateKeyParameters extends AsymmetricKeyParameter {
    public static final int KEY_SIZE = 57;
    public static final int SIGNATURE_SIZE = 114;
    private Ed448PublicKeyParameters cachedPublicKey;
    private final byte[] data;

    public Ed448PrivateKeyParameters(InputStream inputStream) throws IOException {
        super(true);
        byte[] bArr = new byte[57];
        this.data = bArr;
        if (57 != Streams.readFully(inputStream, bArr)) {
            throw new EOFException("EOF encountered in middle of Ed448 private key");
        }
    }

    public Ed448PrivateKeyParameters(SecureRandom secureRandom) {
        super(true);
        byte[] bArr = new byte[57];
        this.data = bArr;
        Ed448.generatePrivateKey(secureRandom, bArr);
    }

    public Ed448PrivateKeyParameters(byte[] bArr) {
        this(validate(bArr), 0);
    }

    public Ed448PrivateKeyParameters(byte[] bArr, int i) {
        super(true);
        byte[] bArr2 = new byte[57];
        this.data = bArr2;
        System.arraycopy(bArr, i, bArr2, 0, 57);
    }

    private static byte[] validate(byte[] bArr) {
        if (bArr.length == 57) {
            return bArr;
        }
        throw new IllegalArgumentException("'buf' must have length 57");
    }

    public void encode(byte[] bArr, int i) {
        System.arraycopy(this.data, 0, bArr, i, 57);
    }

    public Ed448PublicKeyParameters generatePublicKey() {
        Ed448PublicKeyParameters ed448PublicKeyParameters;
        synchronized (this.data) {
            if (this.cachedPublicKey == null) {
                this.cachedPublicKey = new Ed448PublicKeyParameters(Ed448.generatePublicKey(this.data, 0));
            }
            ed448PublicKeyParameters = this.cachedPublicKey;
        }
        return ed448PublicKeyParameters;
    }

    public byte[] getEncoded() {
        return Arrays.clone(this.data);
    }

    public void sign(int i, Ed448PublicKeyParameters ed448PublicKeyParameters, byte[] bArr, byte[] bArr2, int i2, int i3, byte[] bArr3, int i4) {
        sign(i, bArr, bArr2, i2, i3, bArr3, i4);
    }

    public void sign(int i, byte[] bArr, byte[] bArr2, int i2, int i3, byte[] bArr3, int i4) {
        int i5 = i;
        byte[] bArr4 = bArr;
        byte[] bArr5 = new byte[57];
        generatePublicKey().encode(bArr5, 0);
        if (i5 == 0) {
            int i6 = i3;
            if (bArr4 == null) {
                throw new NullPointerException("'ctx' cannot be null");
            } else if (bArr4.length <= 255) {
                Ed448.sign(this.data, 0, bArr5, 0, bArr, bArr2, i2, i3, bArr3, i4);
            } else {
                throw new IllegalArgumentException("ctx");
            }
        } else if (i5 != 1) {
            throw new IllegalArgumentException("algorithm");
        } else if (bArr4 == null) {
            throw new NullPointerException("'ctx' cannot be null");
        } else if (bArr4.length > 255) {
            throw new IllegalArgumentException("ctx");
        } else if (64 == i3) {
            Ed448.signPrehash(this.data, 0, bArr5, 0, bArr, bArr2, i2, bArr3, i4);
        } else {
            throw new IllegalArgumentException("msgLen");
        }
    }
}
