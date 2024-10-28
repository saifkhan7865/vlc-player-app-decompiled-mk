package org.bouncycastle.oer.its.ieee1609dot2.basetypes;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.BigIntegers;

public class Point384 extends ASN1Object {
    private final ASN1OctetString x;
    private final ASN1OctetString y;

    public static class Builder {
        private ASN1OctetString x;
        private ASN1OctetString y;

        public Point384 createPoint384() {
            return new Point384(this.x, this.y);
        }

        public Builder setX(BigInteger bigInteger) {
            this.x = new DEROctetString(BigIntegers.asUnsignedByteArray(48, bigInteger));
            return this;
        }

        public Builder setX(ASN1OctetString aSN1OctetString) {
            this.x = aSN1OctetString;
            return this;
        }

        public Builder setX(byte[] bArr) {
            this.x = new DEROctetString(bArr);
            return this;
        }

        public Builder setY(BigInteger bigInteger) {
            this.y = new DEROctetString(BigIntegers.asUnsignedByteArray(48, bigInteger));
            return this;
        }

        public Builder setY(ASN1OctetString aSN1OctetString) {
            this.y = aSN1OctetString;
            return this;
        }

        public Builder setY(byte[] bArr) {
            this.y = new DEROctetString(bArr);
            return this;
        }
    }

    public Point384(ASN1OctetString aSN1OctetString, ASN1OctetString aSN1OctetString2) {
        if (aSN1OctetString.getOctets().length != 48) {
            throw new IllegalArgumentException("x must be 48 bytes long");
        } else if (aSN1OctetString2.getOctets().length == 48) {
            this.x = aSN1OctetString;
            this.y = aSN1OctetString2;
        } else {
            throw new IllegalArgumentException("y must be 48 bytes long");
        }
    }

    private Point384(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            ASN1OctetString instance = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0));
            this.x = instance;
            ASN1OctetString instance2 = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1));
            this.y = instance2;
            if (instance.getOctets().length != 48) {
                throw new IllegalArgumentException("x must be 48 bytes long");
            } else if (instance2.getOctets().length != 48) {
                throw new IllegalArgumentException("y must be 48 bytes long");
            }
        } else {
            throw new IllegalArgumentException("expected sequence size of 2");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Point384 getInstance(Object obj) {
        if (obj instanceof Point384) {
            return (Point384) obj;
        }
        if (obj != null) {
            return new Point384(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1OctetString getX() {
        return this.x;
    }

    public ASN1OctetString getY() {
        return this.y;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(new ASN1Encodable[]{this.x, this.y});
    }
}
