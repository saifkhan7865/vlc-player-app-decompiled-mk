package org.bouncycastle.pqc.legacy.crypto.mceliece;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageEncryptor;
import org.bouncycastle.pqc.legacy.math.linearalgebra.GF2Matrix;
import org.bouncycastle.pqc.legacy.math.linearalgebra.GF2Vector;
import org.bouncycastle.pqc.legacy.math.linearalgebra.GF2mField;
import org.bouncycastle.pqc.legacy.math.linearalgebra.GoppaCode;
import org.bouncycastle.pqc.legacy.math.linearalgebra.Permutation;
import org.bouncycastle.pqc.legacy.math.linearalgebra.PolynomialGF2mSmallM;
import org.bouncycastle.pqc.legacy.math.linearalgebra.Vector;

public class McElieceCipher implements MessageEncryptor {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.1";
    public int cipherTextSize;
    private boolean forEncryption;
    private int k;
    private McElieceKeyParameters key;
    public int maxPlainTextSize;
    private int n;
    private SecureRandom sr;
    private int t;

    private byte[] computeMessage(GF2Vector gF2Vector) throws InvalidCipherTextException {
        byte[] encoded = gF2Vector.getEncoded();
        int length = encoded.length - 1;
        while (length >= 0 && encoded[length] == 0) {
            length--;
        }
        if (length < 0 || encoded[length] != 1) {
            throw new InvalidCipherTextException("Bad Padding: invalid ciphertext");
        }
        byte[] bArr = new byte[length];
        System.arraycopy(encoded, 0, bArr, 0, length);
        return bArr;
    }

    private GF2Vector computeMessageRepresentative(byte[] bArr) {
        byte[] bArr2 = new byte[(this.maxPlainTextSize + ((this.k & 7) != 0 ? 1 : 0))];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        bArr2[bArr.length] = 1;
        return GF2Vector.OS2VP(this.k, bArr2);
    }

    private void initCipherDecrypt(McEliecePrivateKeyParameters mcEliecePrivateKeyParameters) {
        this.n = mcEliecePrivateKeyParameters.getN();
        int k2 = mcEliecePrivateKeyParameters.getK();
        this.k = k2;
        this.maxPlainTextSize = k2 >> 3;
        this.cipherTextSize = this.n >> 3;
    }

    private void initCipherEncrypt(McEliecePublicKeyParameters mcEliecePublicKeyParameters) {
        this.n = mcEliecePublicKeyParameters.getN();
        this.k = mcEliecePublicKeyParameters.getK();
        this.t = mcEliecePublicKeyParameters.getT();
        this.cipherTextSize = this.n >> 3;
        this.maxPlainTextSize = this.k >> 3;
    }

    public int getKeySize(McElieceKeyParameters mcElieceKeyParameters) {
        if (mcElieceKeyParameters instanceof McEliecePublicKeyParameters) {
            return ((McEliecePublicKeyParameters) mcElieceKeyParameters).getN();
        }
        if (mcElieceKeyParameters instanceof McEliecePrivateKeyParameters) {
            return ((McEliecePrivateKeyParameters) mcElieceKeyParameters).getN();
        }
        throw new IllegalArgumentException("unsupported type");
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        this.forEncryption = z;
        if (!z) {
            McEliecePrivateKeyParameters mcEliecePrivateKeyParameters = (McEliecePrivateKeyParameters) cipherParameters;
            this.key = mcEliecePrivateKeyParameters;
            McEliecePrivateKeyParameters mcEliecePrivateKeyParameters2 = mcEliecePrivateKeyParameters;
            initCipherDecrypt(mcEliecePrivateKeyParameters);
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.sr = parametersWithRandom.getRandom();
            McEliecePublicKeyParameters mcEliecePublicKeyParameters = (McEliecePublicKeyParameters) parametersWithRandom.getParameters();
            this.key = mcEliecePublicKeyParameters;
            McEliecePublicKeyParameters mcEliecePublicKeyParameters2 = mcEliecePublicKeyParameters;
            initCipherEncrypt(mcEliecePublicKeyParameters);
        } else {
            this.sr = CryptoServicesRegistrar.getSecureRandom();
            McEliecePublicKeyParameters mcEliecePublicKeyParameters3 = (McEliecePublicKeyParameters) cipherParameters;
            this.key = mcEliecePublicKeyParameters3;
            McEliecePublicKeyParameters mcEliecePublicKeyParameters4 = mcEliecePublicKeyParameters3;
            initCipherEncrypt(mcEliecePublicKeyParameters3);
        }
    }

    public byte[] messageDecrypt(byte[] bArr) throws InvalidCipherTextException {
        if (!this.forEncryption) {
            GF2Vector OS2VP = GF2Vector.OS2VP(this.n, bArr);
            McEliecePrivateKeyParameters mcEliecePrivateKeyParameters = (McEliecePrivateKeyParameters) this.key;
            GF2mField field = mcEliecePrivateKeyParameters.getField();
            PolynomialGF2mSmallM goppaPoly = mcEliecePrivateKeyParameters.getGoppaPoly();
            GF2Matrix sInv = mcEliecePrivateKeyParameters.getSInv();
            Permutation p1 = mcEliecePrivateKeyParameters.getP1();
            Permutation p2 = mcEliecePrivateKeyParameters.getP2();
            GF2Matrix h = mcEliecePrivateKeyParameters.getH();
            PolynomialGF2mSmallM[] qInv = mcEliecePrivateKeyParameters.getQInv();
            Permutation rightMultiply = p1.rightMultiply(p2);
            GF2Vector gF2Vector = (GF2Vector) OS2VP.multiply(rightMultiply.computeInverse());
            GF2Vector syndromeDecode = GoppaCode.syndromeDecode((GF2Vector) h.rightMultiply((Vector) gF2Vector), field, goppaPoly, qInv);
            GF2Vector gF2Vector2 = (GF2Vector) syndromeDecode.multiply(rightMultiply);
            return computeMessage((GF2Vector) sInv.leftMultiply((Vector) ((GF2Vector) ((GF2Vector) gF2Vector.add(syndromeDecode)).multiply(p1)).extractRightVector(this.k)));
        }
        throw new IllegalStateException("cipher initialised for decryption");
    }

    public byte[] messageEncrypt(byte[] bArr) {
        if (this.forEncryption) {
            GF2Vector computeMessageRepresentative = computeMessageRepresentative(bArr);
            return ((GF2Vector) ((McEliecePublicKeyParameters) this.key).getG().leftMultiply((Vector) computeMessageRepresentative).add(new GF2Vector(this.n, this.t, this.sr))).getEncoded();
        }
        throw new IllegalStateException("cipher initialised for decryption");
    }
}
