package org.bouncycastle.crypto.params;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.math.ec.rfc8032.Ed448;
import org.bouncycastle.util.io.Streams;

public final class Ed448PublicKeyParameters extends AsymmetricKeyParameter {
    public static final int KEY_SIZE = 57;
    private final Ed448.PublicPoint publicPoint;

    public Ed448PublicKeyParameters(InputStream inputStream) throws IOException {
        super(false);
        byte[] bArr = new byte[57];
        if (57 == Streams.readFully(inputStream, bArr)) {
            this.publicPoint = parse(bArr, 0);
            return;
        }
        throw new EOFException("EOF encountered in middle of Ed448 public key");
    }

    public Ed448PublicKeyParameters(Ed448.PublicPoint publicPoint2) {
        super(false);
        if (publicPoint2 != null) {
            this.publicPoint = publicPoint2;
            return;
        }
        throw new NullPointerException("'publicPoint' cannot be null");
    }

    public Ed448PublicKeyParameters(byte[] bArr) {
        this(validate(bArr), 0);
    }

    public Ed448PublicKeyParameters(byte[] bArr, int i) {
        super(false);
        this.publicPoint = parse(bArr, i);
    }

    private static Ed448.PublicPoint parse(byte[] bArr, int i) {
        Ed448.PublicPoint validatePublicKeyPartialExport = Ed448.validatePublicKeyPartialExport(bArr, i);
        if (validatePublicKeyPartialExport != null) {
            return validatePublicKeyPartialExport;
        }
        throw new IllegalArgumentException("invalid public key");
    }

    private static byte[] validate(byte[] bArr) {
        if (bArr.length == 57) {
            return bArr;
        }
        throw new IllegalArgumentException("'buf' must have length 57");
    }

    public void encode(byte[] bArr, int i) {
        Ed448.encodePublicPoint(this.publicPoint, bArr, i);
    }

    public byte[] getEncoded() {
        byte[] bArr = new byte[57];
        encode(bArr, 0);
        return bArr;
    }

    public boolean verify(int i, byte[] bArr, byte[] bArr2, int i2, int i3, byte[] bArr3, int i4) {
        if (i != 0) {
            if (i != 1) {
                throw new IllegalArgumentException("algorithm");
            } else if (bArr == null) {
                throw new NullPointerException("'ctx' cannot be null");
            } else if (bArr.length > 255) {
                throw new IllegalArgumentException("ctx");
            } else if (64 == i3) {
                return Ed448.verifyPrehash(bArr3, i4, this.publicPoint, bArr, bArr2, i2);
            } else {
                throw new IllegalArgumentException("msgLen");
            }
        } else if (bArr == null) {
            throw new NullPointerException("'ctx' cannot be null");
        } else if (bArr.length <= 255) {
            return Ed448.verify(bArr3, i4, this.publicPoint, bArr, bArr2, i2, i3);
        } else {
            throw new IllegalArgumentException("ctx");
        }
    }
}
