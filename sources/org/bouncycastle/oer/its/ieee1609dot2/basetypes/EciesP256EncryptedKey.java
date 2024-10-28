package org.bouncycastle.oer.its.ieee1609dot2.basetypes;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;

public class EciesP256EncryptedKey extends ASN1Object {
    private final ASN1OctetString c;
    private final ASN1OctetString t;
    private final EccP256CurvePoint v;

    public static class Builder {
        private ASN1OctetString c;
        private ASN1OctetString t;
        private EccP256CurvePoint v;

        public EciesP256EncryptedKey createEciesP256EncryptedKey() {
            return new EciesP256EncryptedKey(this.v, this.c, this.t);
        }

        public Builder setC(ASN1OctetString aSN1OctetString) {
            this.c = aSN1OctetString;
            return this;
        }

        public Builder setC(byte[] bArr) {
            this.c = new DEROctetString(Arrays.clone(bArr));
            return this;
        }

        public Builder setT(ASN1OctetString aSN1OctetString) {
            this.t = aSN1OctetString;
            return this;
        }

        public Builder setT(byte[] bArr) {
            this.t = new DEROctetString(Arrays.clone(bArr));
            return this;
        }

        public Builder setV(EccP256CurvePoint eccP256CurvePoint) {
            this.v = eccP256CurvePoint;
            return this;
        }
    }

    private EciesP256EncryptedKey(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 3) {
            this.v = EccP256CurvePoint.getInstance(aSN1Sequence.getObjectAt(0));
            this.c = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1));
            this.t = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(2));
            return;
        }
        throw new IllegalArgumentException("expected sequence size of 3");
    }

    public EciesP256EncryptedKey(EccP256CurvePoint eccP256CurvePoint, ASN1OctetString aSN1OctetString, ASN1OctetString aSN1OctetString2) {
        this.v = eccP256CurvePoint;
        this.c = aSN1OctetString;
        this.t = aSN1OctetString2;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static EciesP256EncryptedKey getInstance(Object obj) {
        if (obj instanceof EciesP256EncryptedKey) {
            return (EciesP256EncryptedKey) obj;
        }
        if (obj != null) {
            return new EciesP256EncryptedKey(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1OctetString getC() {
        return this.c;
    }

    public ASN1OctetString getT() {
        return this.t;
    }

    public EccP256CurvePoint getV() {
        return this.v;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(new ASN1Encodable[]{this.v, this.c, this.t});
    }
}
