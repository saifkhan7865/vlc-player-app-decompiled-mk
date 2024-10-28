package org.bouncycastle.oer.its.ieee1609dot2.basetypes;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.util.Arrays;

public class HashedId extends ASN1Object {
    private final byte[] id;

    protected HashedId(byte[] bArr) {
        this.id = Arrays.clone(bArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        return java.util.Arrays.equals(this.id, ((HashedId) obj).id);
    }

    public byte[] getHashBytes() {
        return Arrays.clone(this.id);
    }

    public int hashCode() {
        return (super.hashCode() * 31) + java.util.Arrays.hashCode(this.id);
    }

    public ASN1Primitive toASN1Primitive() {
        return new DEROctetString(this.id);
    }
}
