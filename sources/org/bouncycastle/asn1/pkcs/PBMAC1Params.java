package org.bouncycastle.asn1.pkcs;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class PBMAC1Params extends ASN1Object implements PKCSObjectIdentifiers {
    private AlgorithmIdentifier func;
    private AlgorithmIdentifier scheme;

    private PBMAC1Params(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        ASN1Sequence instance = ASN1Sequence.getInstance(((ASN1Encodable) objects.nextElement()).toASN1Primitive());
        if (instance.getObjectAt(0).equals(id_PBKDF2)) {
            this.func = new AlgorithmIdentifier(id_PBKDF2, PBKDF2Params.getInstance(instance.getObjectAt(1)));
        } else {
            this.func = AlgorithmIdentifier.getInstance(instance);
        }
        this.scheme = AlgorithmIdentifier.getInstance(objects.nextElement());
    }

    public PBMAC1Params(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) {
        this.func = algorithmIdentifier;
        this.scheme = algorithmIdentifier2;
    }

    public static PBMAC1Params getInstance(Object obj) {
        if (obj instanceof PBMAC1Params) {
            return (PBMAC1Params) obj;
        }
        if (obj != null) {
            return new PBMAC1Params(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public AlgorithmIdentifier getKeyDerivationFunc() {
        return this.func;
    }

    public AlgorithmIdentifier getMessageAuthScheme() {
        return this.scheme;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.func);
        aSN1EncodableVector.add(this.scheme);
        return new DERSequence(aSN1EncodableVector);
    }
}
