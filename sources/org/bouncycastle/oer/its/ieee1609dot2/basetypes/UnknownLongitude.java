package org.bouncycastle.oer.its.ieee1609dot2.basetypes;

import org.bouncycastle.asn1.ASN1Integer;

public class UnknownLongitude extends Longitude {
    public static final UnknownLongitude INSTANCE = new UnknownLongitude();

    public UnknownLongitude() {
        super(1800000001);
    }

    public static UnknownLongitude getInstance(Object obj) {
        if (obj instanceof UnknownLongitude) {
            return (UnknownLongitude) obj;
        }
        if (obj == null) {
            return null;
        }
        ASN1Integer instance = ASN1Integer.getInstance(obj);
        if (instance.getValue().intValue() == 1800000001) {
            return INSTANCE;
        }
        throw new IllegalArgumentException("value " + instance.getValue() + " is not 1800000001");
    }
}
