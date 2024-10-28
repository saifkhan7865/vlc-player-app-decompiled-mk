package org.bouncycastle.crypto;

public interface CryptoServiceProperties {
    int bitsOfSecurity();

    Object getParams();

    CryptoServicePurpose getPurpose();

    String getServiceName();
}
