package org.bouncycastle.its.jcajce;

import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.GCMParameterSpec;

class ClassUtil {
    ClassUtil() {
    }

    public static AlgorithmParameterSpec getGCMSpec(byte[] bArr, int i) {
        return new GCMParameterSpec(i, bArr);
    }
}
