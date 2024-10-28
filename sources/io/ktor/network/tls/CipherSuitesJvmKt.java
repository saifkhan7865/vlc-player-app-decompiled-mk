package io.ktor.network.tls;

import io.ktor.network.tls.platform.PlatformVersionKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"isSupported", "", "Lio/ktor/network/tls/CipherSuite;", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CipherSuitesJvm.kt */
public final class CipherSuitesJvmKt {
    public static final boolean isSupported(CipherSuite cipherSuite) {
        Intrinsics.checkNotNullParameter(cipherSuite, "<this>");
        String major = PlatformVersionKt.getPlatformVersion().getMajor();
        int hashCode = major.hashCode();
        if (hashCode != 46676283) {
            if (hashCode != 46677244) {
                if (hashCode == 46678205 && major.equals("1.8.0") && PlatformVersionKt.getPlatformVersion().getMinor() < 161 && cipherSuite.getKeyStrength() > 128) {
                    return false;
                }
            } else if (major.equals("1.7.0") && PlatformVersionKt.getPlatformVersion().getMinor() < 171 && cipherSuite.getKeyStrength() > 128) {
                return false;
            }
        } else if (major.equals("1.6.0") && PlatformVersionKt.getPlatformVersion().getMinor() < 181 && cipherSuite.getKeyStrength() > 128) {
            return false;
        }
        return true;
    }
}
