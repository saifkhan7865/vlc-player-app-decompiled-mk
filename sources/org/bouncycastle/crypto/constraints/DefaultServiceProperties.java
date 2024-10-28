package org.bouncycastle.crypto.constraints;

import org.bouncycastle.crypto.CryptoServiceProperties;
import org.bouncycastle.crypto.CryptoServicePurpose;

public class DefaultServiceProperties implements CryptoServiceProperties {
    private final String algorithm;
    private final int bitsOfSecurity;
    private final Object params;
    private final CryptoServicePurpose purpose;

    public DefaultServiceProperties(String str, int i) {
        this(str, i, (Object) null, CryptoServicePurpose.ANY);
    }

    public DefaultServiceProperties(String str, int i, Object obj) {
        this(str, i, obj, CryptoServicePurpose.ANY);
    }

    public DefaultServiceProperties(String str, int i, Object obj, CryptoServicePurpose cryptoServicePurpose) {
        this.algorithm = str;
        this.bitsOfSecurity = i;
        this.params = obj;
        if (!(obj instanceof CryptoServicePurpose)) {
            this.purpose = cryptoServicePurpose;
            return;
        }
        throw new IllegalArgumentException("params should not be CryptoServicePurpose");
    }

    public int bitsOfSecurity() {
        return this.bitsOfSecurity;
    }

    public Object getParams() {
        return this.params;
    }

    public CryptoServicePurpose getPurpose() {
        return this.purpose;
    }

    public String getServiceName() {
        return this.algorithm;
    }
}
