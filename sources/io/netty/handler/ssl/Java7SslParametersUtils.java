package io.netty.handler.ssl;

import io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0;
import javax.net.ssl.SSLParameters;

final class Java7SslParametersUtils {
    private Java7SslParametersUtils() {
    }

    static void setAlgorithmConstraints(SSLParameters sSLParameters, Object obj) {
        sSLParameters.setAlgorithmConstraints(NioPathKt$$ExternalSyntheticApiModelOutline0.m(obj));
    }
}
