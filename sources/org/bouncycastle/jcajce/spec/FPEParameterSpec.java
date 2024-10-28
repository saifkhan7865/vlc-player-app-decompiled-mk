package org.bouncycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.util.RadixConverter;
import org.bouncycastle.util.Arrays;

public class FPEParameterSpec implements AlgorithmParameterSpec {
    private final RadixConverter radixConverter;
    private final byte[] tweak;
    private final boolean useInverse;

    public FPEParameterSpec(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    public FPEParameterSpec(int i, byte[] bArr, boolean z) {
        this(new RadixConverter(i), bArr, z);
    }

    public FPEParameterSpec(RadixConverter radixConverter2, byte[] bArr, boolean z) {
        this.radixConverter = radixConverter2;
        this.tweak = Arrays.clone(bArr);
        this.useInverse = z;
    }

    public int getRadix() {
        return this.radixConverter.getRadix();
    }

    public RadixConverter getRadixConverter() {
        return this.radixConverter;
    }

    public byte[] getTweak() {
        return Arrays.clone(this.tweak);
    }

    public boolean isUsingInverseFunction() {
        return this.useInverse;
    }
}
