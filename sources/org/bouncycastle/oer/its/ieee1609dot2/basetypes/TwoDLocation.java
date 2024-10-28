package org.bouncycastle.oer.its.ieee1609dot2.basetypes;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

public class TwoDLocation extends ASN1Object {
    private final Latitude latitude;
    private final Longitude longitude;

    public static class Builder {
        private Latitude latitude;
        private Longitude longitude;

        public TwoDLocation createTwoDLocation() {
            return new TwoDLocation(this.latitude, this.longitude);
        }

        public Builder setLatitude(Latitude latitude2) {
            this.latitude = latitude2;
            return this;
        }

        public Builder setLongitude(Longitude longitude2) {
            this.longitude = longitude2;
            return this;
        }
    }

    private TwoDLocation(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.latitude = Latitude.getInstance(aSN1Sequence.getObjectAt(0));
            this.longitude = Longitude.getInstance(aSN1Sequence.getObjectAt(1));
            return;
        }
        throw new IllegalArgumentException("expected sequence size of 2");
    }

    public TwoDLocation(Latitude latitude2, Longitude longitude2) {
        this.latitude = latitude2;
        this.longitude = longitude2;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static TwoDLocation getInstance(Object obj) {
        if (obj instanceof TwoDLocation) {
            return (TwoDLocation) obj;
        }
        if (obj != null) {
            return new TwoDLocation(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public Latitude getLatitude() {
        return this.latitude;
    }

    public Longitude getLongitude() {
        return this.longitude;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(new ASN1Encodable[]{this.latitude, this.longitude});
    }
}
