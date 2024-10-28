package org.bouncycastle.pqc.jcajce.provider.util;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.spec.AlgorithmParameterSpec;

public class SpecUtil {
    /* access modifiers changed from: private */
    public static Object[] NO_ARGS = new Object[0];
    /* access modifiers changed from: private */
    public static Class[] NO_PARAMS = new Class[0];

    public static String getNameFrom(final AlgorithmParameterSpec algorithmParameterSpec) {
        return (String) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    return algorithmParameterSpec.getClass().getMethod("getName", SpecUtil.NO_PARAMS).invoke(algorithmParameterSpec, SpecUtil.NO_ARGS);
                } catch (Exception unused) {
                    return null;
                }
            }
        });
    }
}
