package io.ktor.network.tls;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001¨\u0006\u0003"}, d2 = {"keysGenerationAlgorithm", "", "algorithm", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: OID.kt */
public final class OIDKt {
    public static final String keysGenerationAlgorithm(String str) {
        Intrinsics.checkNotNullParameter(str, "algorithm");
        if (StringsKt.endsWith(str, "ecdsa", true)) {
            return "EC";
        }
        if (StringsKt.endsWith(str, "dsa", true)) {
            return "DSA";
        }
        if (StringsKt.endsWith(str, "rsa", true)) {
            return "RSA";
        }
        throw new IllegalStateException(("Couldn't find KeyPairGenerator algorithm for " + str).toString());
    }
}
