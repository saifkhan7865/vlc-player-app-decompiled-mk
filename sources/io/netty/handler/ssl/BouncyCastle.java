package io.netty.handler.ssl;

import javax.net.ssl.SSLEngine;

final class BouncyCastle {
    private static final boolean BOUNCY_CASTLE_ON_CLASSPATH;

    static {
        boolean z;
        try {
            Class.forName("org.bouncycastle.jsse.provider.BouncyCastleJsseProvider");
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        BOUNCY_CASTLE_ON_CLASSPATH = z;
    }

    static boolean isAvailable() {
        return BOUNCY_CASTLE_ON_CLASSPATH;
    }

    static boolean isInUse(SSLEngine sSLEngine) {
        return sSLEngine.getClass().getPackage().getName().startsWith("org.bouncycastle.jsse.provider");
    }

    private BouncyCastle() {
    }
}
