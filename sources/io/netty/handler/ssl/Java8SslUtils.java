package io.netty.handler.ssl;

import io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0;
import io.netty.util.CharsetUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SNIMatcher;
import javax.net.ssl.SNIServerName;
import javax.net.ssl.SSLParameters;

final class Java8SslUtils {
    private Java8SslUtils() {
    }

    static List<String> getSniHostNames(SSLParameters sSLParameters) {
        List<Object> m = NioPathKt$$ExternalSyntheticApiModelOutline0.m(sSLParameters);
        if (m == null || m.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(m.size());
        for (Object m2 : m) {
            SNIServerName m3 = NioPathKt$$ExternalSyntheticApiModelOutline0.m(m2);
            if (NioPathKt$$ExternalSyntheticApiModelOutline0.m$1((Object) m3)) {
                arrayList.add(NioPathKt$$ExternalSyntheticApiModelOutline0.m((Object) m3).getAsciiName());
            } else {
                throw new IllegalArgumentException("Only " + NioPathKt$$ExternalSyntheticApiModelOutline0.m().getName() + " instances are supported, but found: " + m3);
            }
        }
        return arrayList;
    }

    static void setSniHostNames(SSLParameters sSLParameters, List<String> list) {
        sSLParameters.setServerNames(getSniHostNames(list));
    }

    static boolean isValidHostNameForSNI(String str) {
        try {
            new SNIHostName(str);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    static List getSniHostNames(List<String> list) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (String bytes : list) {
            arrayList.add(new SNIHostName(bytes.getBytes(CharsetUtil.UTF_8)));
        }
        return arrayList;
    }

    static List getSniHostName(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return Collections.emptyList();
        }
        return Collections.singletonList(new SNIHostName(bArr));
    }

    static boolean getUseCipherSuitesOrder(SSLParameters sSLParameters) {
        return NioPathKt$$ExternalSyntheticApiModelOutline0.m(sSLParameters);
    }

    static void setUseCipherSuitesOrder(SSLParameters sSLParameters, boolean z) {
        sSLParameters.setUseCipherSuitesOrder(z);
    }

    static void setSNIMatchers(SSLParameters sSLParameters, Collection<?> collection) {
        sSLParameters.setSNIMatchers(collection);
    }

    static boolean checkSniHostnameMatch(Collection<?> collection, byte[] bArr) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        SNIHostName sNIHostName = new SNIHostName(bArr);
        for (Object m : collection) {
            SNIMatcher m2 = NioPathKt$$ExternalSyntheticApiModelOutline0.m((Object) m);
            if (m2.getType() == 0 && m2.matches(sNIHostName)) {
                return true;
            }
        }
        return false;
    }
}
