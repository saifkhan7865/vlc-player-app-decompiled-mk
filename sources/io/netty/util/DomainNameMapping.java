package io.netty.util;

import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.net.IDN;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import okhttp3.CertificatePinner;

@Deprecated
public class DomainNameMapping<V> implements Mapping<String, V> {
    final V defaultValue;
    private final Map<String, V> map;
    private final Map<String, V> unmodifiableMap;

    @Deprecated
    public DomainNameMapping(V v) {
        this(4, v);
    }

    @Deprecated
    public DomainNameMapping(int i, V v) {
        this(new LinkedHashMap(i), v);
    }

    DomainNameMapping(Map<String, V> map2, V v) {
        this.defaultValue = ObjectUtil.checkNotNull(v, "defaultValue");
        this.map = map2;
        this.unmodifiableMap = map2 != null ? Collections.unmodifiableMap(map2) : null;
    }

    @Deprecated
    public DomainNameMapping<V> add(String str, V v) {
        this.map.put(normalizeHostname((String) ObjectUtil.checkNotNull(str, "hostname")), ObjectUtil.checkNotNull(v, "output"));
        return this;
    }

    static boolean matches(String str, String str2) {
        if (!str.startsWith(CertificatePinner.WILDCARD)) {
            return str.equals(str2);
        }
        if (str.regionMatches(2, str2, 0, str2.length()) || StringUtil.commonSuffixOfLength(str2, str, str.length() - 1)) {
            return true;
        }
        return false;
    }

    static String normalizeHostname(String str) {
        if (needsNormalization(str)) {
            str = IDN.toASCII(str, 1);
        }
        return str.toLowerCase(Locale.US);
    }

    private static boolean needsNormalization(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) > 127) {
                return true;
            }
        }
        return false;
    }

    public V map(String str) {
        if (str != null) {
            String normalizeHostname = normalizeHostname(str);
            for (Map.Entry next : this.map.entrySet()) {
                if (matches((String) next.getKey(), normalizeHostname)) {
                    return next.getValue();
                }
            }
        }
        return this.defaultValue;
    }

    public Map<String, V> asMap() {
        return this.unmodifiableMap;
    }

    public String toString() {
        return StringUtil.simpleClassName((Object) this) + "(default: " + this.defaultValue + ", map: " + this.map + ')';
    }
}