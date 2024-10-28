package org.bouncycastle.oer.its.ieee1609dot2.basetypes;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;

public abstract class UintBase extends ASN1Object {
    protected final BigInteger value;

    public UintBase(int i) {
        this(BigInteger.valueOf((long) i));
    }

    public UintBase(long j) {
        this(BigInteger.valueOf(j));
    }

    public UintBase(BigInteger bigInteger) {
        this.value = bigInteger;
        assertLimit();
    }

    protected UintBase(ASN1Integer aSN1Integer) {
        this(aSN1Integer.getValue());
    }

    /* access modifiers changed from: protected */
    public abstract void assertLimit();

    public BigInteger getValue() {
        return this.value;
    }

    public ASN1Primitive toASN1Primitive() {
        return new ASN1Integer(this.value);
    }
}
