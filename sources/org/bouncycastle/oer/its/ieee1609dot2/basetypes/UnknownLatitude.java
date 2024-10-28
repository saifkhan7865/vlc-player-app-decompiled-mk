package org.bouncycastle.oer.its.ieee1609dot2.basetypes;

import org.bouncycastle.asn1.ASN1Integer;

public class UnknownLatitude extends Latitude {
    public static UnknownLatitude INSTANCE = new UnknownLatitude();

    private UnknownLatitude() {
        super(900000001);
    }

    public static UnknownLatitude getInstance(Object obj) {
        if (obj instanceof UnknownLatitude) {
            return (UnknownLatitude) obj;
        }
        if (obj == null) {
            return null;
        }
        ASN1Integer instance = ASN1Integer.getInstance(obj);
        if (instance.getValue().intValue() == 900000001) {
            return INSTANCE;
        }
        throw new IllegalArgumentException("value " + instance.getValue() + " is not unknown value of 900000001");
    }
}
