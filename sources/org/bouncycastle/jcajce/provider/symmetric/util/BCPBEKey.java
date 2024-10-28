package org.bouncycastle.jcajce.provider.symmetric.util;

import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;
import javax.security.auth.Destroyable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

public class BCPBEKey implements PBEKey, Destroyable {
    String algorithm;
    int digest;
    private final AtomicBoolean hasBeenDestroyed = new AtomicBoolean(false);
    private final int iterationCount;
    int ivSize;
    int keySize;
    ASN1ObjectIdentifier oid;
    private final CipherParameters param;
    private final char[] password;
    private final byte[] salt;
    boolean tryWrong = false;
    int type;

    public BCPBEKey(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier, int i, int i2, int i3, int i4, PBEKeySpec pBEKeySpec, CipherParameters cipherParameters) {
        this.algorithm = str;
        this.oid = aSN1ObjectIdentifier;
        this.type = i;
        this.digest = i2;
        this.keySize = i3;
        this.ivSize = i4;
        this.password = pBEKeySpec.getPassword();
        this.iterationCount = pBEKeySpec.getIterationCount();
        this.salt = pBEKeySpec.getSalt();
        this.param = cipherParameters;
    }

    public BCPBEKey(String str, CipherParameters cipherParameters) {
        this.algorithm = str;
        this.param = cipherParameters;
        this.password = null;
        this.iterationCount = -1;
        this.salt = null;
    }

    static void checkDestroyed(Destroyable destroyable) {
        if (destroyable.isDestroyed()) {
            throw new IllegalStateException("key has been destroyed");
        }
    }

    public void destroy() {
        if (!this.hasBeenDestroyed.getAndSet(true)) {
            char[] cArr = this.password;
            if (cArr != null) {
                Arrays.fill(cArr, 0);
            }
            byte[] bArr = this.salt;
            if (bArr != null) {
                Arrays.fill(bArr, (byte) 0);
            }
        }
    }

    public String getAlgorithm() {
        String str = this.algorithm;
        checkDestroyed(this);
        return str;
    }

    /* access modifiers changed from: package-private */
    public int getDigest() {
        int i = this.digest;
        checkDestroyed(this);
        return i;
    }

    public byte[] getEncoded() {
        byte[] bArr;
        CipherParameters cipherParameters = this.param;
        if (cipherParameters != null) {
            if (cipherParameters instanceof ParametersWithIV) {
                cipherParameters = ((ParametersWithIV) cipherParameters).getParameters();
            }
            bArr = ((KeyParameter) cipherParameters).getKey();
        } else {
            int i = this.type;
            bArr = i == 2 ? PBEParametersGenerator.PKCS12PasswordToBytes(this.password) : i == 5 ? PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(this.password) : PBEParametersGenerator.PKCS5PasswordToBytes(this.password);
        }
        checkDestroyed(this);
        return bArr;
    }

    public String getFormat() {
        checkDestroyed(this);
        return "RAW";
    }

    public int getIterationCount() {
        int i = this.iterationCount;
        checkDestroyed(this);
        return i;
    }

    public int getIvSize() {
        int i = this.ivSize;
        checkDestroyed(this);
        return i;
    }

    /* access modifiers changed from: package-private */
    public int getKeySize() {
        int i = this.keySize;
        checkDestroyed(this);
        return i;
    }

    public ASN1ObjectIdentifier getOID() {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = this.oid;
        checkDestroyed(this);
        return aSN1ObjectIdentifier;
    }

    public CipherParameters getParam() {
        CipherParameters cipherParameters = this.param;
        checkDestroyed(this);
        return cipherParameters;
    }

    public char[] getPassword() {
        char[] clone = Arrays.clone(this.password);
        checkDestroyed(this);
        if (clone != null) {
            return clone;
        }
        throw new IllegalStateException("no password available");
    }

    public byte[] getSalt() {
        byte[] clone = Arrays.clone(this.salt);
        checkDestroyed(this);
        return clone;
    }

    /* access modifiers changed from: package-private */
    public int getType() {
        int i = this.type;
        checkDestroyed(this);
        return i;
    }

    public boolean isDestroyed() {
        return this.hasBeenDestroyed.get();
    }

    public void setTryWrongPKCS12Zero(boolean z) {
        this.tryWrong = z;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldTryWrongPKCS12() {
        return this.tryWrong;
    }
}
