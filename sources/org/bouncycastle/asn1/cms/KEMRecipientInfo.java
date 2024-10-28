package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class KEMRecipientInfo extends ASN1Object {
    private final ASN1Integer cmsVersion;
    private final ASN1OctetString encryptedKey;
    private final AlgorithmIdentifier kdf;
    private final ASN1Integer kekLength;
    private final AlgorithmIdentifier kem;
    private final ASN1OctetString kemct;
    private final RecipientIdentifier rid;
    private final ASN1OctetString ukm;
    private final AlgorithmIdentifier wrap;

    private KEMRecipientInfo(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 3) {
            this.cmsVersion = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
            this.rid = RecipientIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
            this.kem = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(2));
            this.kemct = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(3));
            this.kdf = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(4));
            this.kekLength = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(5));
            int i = 6;
            if (aSN1Sequence.getObjectAt(6) instanceof ASN1TaggedObject) {
                this.ukm = ASN1OctetString.getInstance(ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(6)), true);
                i = 7;
            } else {
                this.ukm = null;
            }
            this.wrap = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(i));
            this.encryptedKey = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(i + 1));
            return;
        }
        throw new IllegalArgumentException("sequence must consist of 3 elements");
    }

    public KEMRecipientInfo(RecipientIdentifier recipientIdentifier, AlgorithmIdentifier algorithmIdentifier, ASN1OctetString aSN1OctetString, AlgorithmIdentifier algorithmIdentifier2, ASN1Integer aSN1Integer, ASN1OctetString aSN1OctetString2, AlgorithmIdentifier algorithmIdentifier3, ASN1OctetString aSN1OctetString3) {
        if (algorithmIdentifier == null) {
            throw new NullPointerException("kem cannot be null");
        } else if (algorithmIdentifier3 != null) {
            this.cmsVersion = new ASN1Integer(0);
            this.rid = recipientIdentifier;
            this.kem = algorithmIdentifier;
            this.kemct = aSN1OctetString;
            this.kdf = algorithmIdentifier2;
            this.kekLength = aSN1Integer;
            this.ukm = aSN1OctetString2;
            this.wrap = algorithmIdentifier3;
            this.encryptedKey = aSN1OctetString3;
        } else {
            throw new NullPointerException("wrap cannot be null");
        }
    }

    public static KEMRecipientInfo getInstance(Object obj) {
        if (obj instanceof KEMRecipientInfo) {
            return (KEMRecipientInfo) obj;
        }
        if (obj != null) {
            return new KEMRecipientInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1OctetString getEncryptedKey() {
        return this.encryptedKey;
    }

    public AlgorithmIdentifier getKdf() {
        return this.kdf;
    }

    public AlgorithmIdentifier getKem() {
        return this.kem;
    }

    public ASN1OctetString getKemct() {
        return this.kemct;
    }

    public RecipientIdentifier getRecipientIdentifier() {
        return this.rid;
    }

    public byte[] getUkm() {
        ASN1OctetString aSN1OctetString = this.ukm;
        if (aSN1OctetString == null) {
            return null;
        }
        return aSN1OctetString.getOctets();
    }

    public AlgorithmIdentifier getWrap() {
        return this.wrap;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.cmsVersion);
        aSN1EncodableVector.add(this.rid);
        aSN1EncodableVector.add(this.kem);
        aSN1EncodableVector.add(this.kemct);
        aSN1EncodableVector.add(this.kdf);
        aSN1EncodableVector.add(this.kekLength);
        if (this.ukm != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, (ASN1Encodable) this.ukm));
        }
        aSN1EncodableVector.add(this.wrap);
        aSN1EncodableVector.add(this.encryptedKey);
        return new DERSequence(aSN1EncodableVector);
    }
}
