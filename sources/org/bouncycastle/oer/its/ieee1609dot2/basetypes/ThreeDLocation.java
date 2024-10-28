package org.bouncycastle.oer.its.ieee1609dot2.basetypes;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

public class ThreeDLocation extends ASN1Object {
    private final Elevation elevation;
    private final Latitude latitude;
    private final Longitude longitude;

    public static class Builder {
        private Elevation elevation;
        private Latitude latitude;
        private Longitude longitude;

        public ThreeDLocation createThreeDLocation() {
            return new ThreeDLocation(this.latitude, this.longitude, this.elevation);
        }

        public Builder setElevation(Elevation elevation2) {
            this.elevation = elevation2;
            return this;
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

    private ThreeDLocation(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 3) {
            this.latitude = Latitude.getInstance(aSN1Sequence.getObjectAt(0));
            this.longitude = Longitude.getInstance(aSN1Sequence.getObjectAt(1));
            this.elevation = Elevation.getInstance(aSN1Sequence.getObjectAt(2));
            return;
        }
        throw new IllegalArgumentException("expected sequence size of 3");
    }

    public ThreeDLocation(Latitude latitude2, Longitude longitude2, Elevation elevation2) {
        this.latitude = latitude2;
        this.longitude = longitude2;
        this.elevation = elevation2;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static ThreeDLocation getInstance(Object obj) {
        if (obj instanceof ThreeDLocation) {
            return (ThreeDLocation) obj;
        }
        if (obj != null) {
            return new ThreeDLocation(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public Elevation getElevation() {
        return this.elevation;
    }

    public Latitude getLatitude() {
        return this.latitude;
    }

    public Longitude getLongitude() {
        return this.longitude;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(new ASN1Encodable[]{this.latitude, this.longitude, this.elevation});
    }
}
