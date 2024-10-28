package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class CMSORIforKEMOtherInfo extends ASN1Object {
    private final int kekLength;
    private final byte[] ukm;
    private final AlgorithmIdentifier wrap;

    public CMSORIforKEMOtherInfo(AlgorithmIdentifier algorithmIdentifier, int i) {
        this(algorithmIdentifier, i, (byte[]) null);
    }

    public CMSORIforKEMOtherInfo(AlgorithmIdentifier algorithmIdentifier, int i, byte[] bArr) {
        this.wrap = algorithmIdentifier;
        this.kekLength = i;
        this.ukm = bArr;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.wrap);
        aSN1EncodableVector.add(new ASN1Integer((long) this.kekLength));
        if (this.ukm != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, (ASN1Encodable) new DEROctetString(this.ukm)));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
