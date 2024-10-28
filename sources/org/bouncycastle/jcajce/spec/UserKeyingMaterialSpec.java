package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.util.Arrays;

public class UserKeyingMaterialSpec implements AlgorithmParameterSpec {
    private final byte[] salt;
    private final byte[] userKeyingMaterial;

    public UserKeyingMaterialSpec(byte[] bArr) {
        this(bArr, (byte[]) null);
    }

    public UserKeyingMaterialSpec(byte[] bArr, byte[] bArr2) {
        this.userKeyingMaterial = Arrays.clone(bArr);
        this.salt = Arrays.clone(bArr2);
    }

    public byte[] getSalt() {
        return Arrays.clone(this.salt);
    }

    public byte[] getUserKeyingMaterial() {
        return Arrays.clone(this.userKeyingMaterial);
    }
}
