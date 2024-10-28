package org.bouncycastle.asn1.tsp;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.Attributes;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.SignedData;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class ArchiveTimeStamp extends ASN1Object {
    private final Attributes attributes;
    private final AlgorithmIdentifier digestAlgorithm;
    private final ASN1Sequence reducedHashTree;
    private final ContentInfo timeStamp;

    private ArchiveTimeStamp(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 4) {
            throw new IllegalArgumentException("wrong sequence size in constructor: " + aSN1Sequence.size());
        }
        AlgorithmIdentifier algorithmIdentifier = null;
        Attributes attributes2 = null;
        ASN1Sequence aSN1Sequence2 = null;
        for (int i = 0; i < aSN1Sequence.size() - 1; i++) {
            ASN1Encodable objectAt = aSN1Sequence.getObjectAt(i);
            if (objectAt instanceof ASN1TaggedObject) {
                ASN1TaggedObject instance = ASN1TaggedObject.getInstance(objectAt);
                int tagNo = instance.getTagNo();
                if (tagNo == 0) {
                    algorithmIdentifier = AlgorithmIdentifier.getInstance(instance, false);
                } else if (tagNo == 1) {
                    attributes2 = Attributes.getInstance(instance, false);
                } else if (tagNo == 2) {
                    aSN1Sequence2 = ASN1Sequence.getInstance(instance, false);
                } else {
                    throw new IllegalArgumentException("invalid tag no in constructor: " + instance.getTagNo());
                }
            }
        }
        this.digestAlgorithm = algorithmIdentifier;
        this.attributes = attributes2;
        this.reducedHashTree = aSN1Sequence2;
        this.timeStamp = ContentInfo.getInstance(aSN1Sequence.getObjectAt(aSN1Sequence.size() - 1));
    }

    public ArchiveTimeStamp(ContentInfo contentInfo) {
        this((AlgorithmIdentifier) null, (Attributes) null, (PartialHashtree[]) null, contentInfo);
    }

    public ArchiveTimeStamp(AlgorithmIdentifier algorithmIdentifier, Attributes attributes2, PartialHashtree[] partialHashtreeArr, ContentInfo contentInfo) {
        this.digestAlgorithm = algorithmIdentifier;
        this.attributes = attributes2;
        this.reducedHashTree = partialHashtreeArr != null ? new DERSequence((ASN1Encodable[]) partialHashtreeArr) : null;
        this.timeStamp = contentInfo;
    }

    public ArchiveTimeStamp(AlgorithmIdentifier algorithmIdentifier, PartialHashtree[] partialHashtreeArr, ContentInfo contentInfo) {
        this(algorithmIdentifier, (Attributes) null, partialHashtreeArr, contentInfo);
    }

    public static ArchiveTimeStamp getInstance(Object obj) {
        if (obj instanceof ArchiveTimeStamp) {
            return (ArchiveTimeStamp) obj;
        }
        if (obj != null) {
            return new ArchiveTimeStamp(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private TSTInfo getTimeStampInfo() {
        if (this.timeStamp.getContentType().equals((ASN1Primitive) CMSObjectIdentifiers.signedData)) {
            SignedData instance = SignedData.getInstance(this.timeStamp.getContent());
            if (instance.getEncapContentInfo().getContentType().equals((ASN1Primitive) PKCSObjectIdentifiers.id_ct_TSTInfo)) {
                return TSTInfo.getInstance(ASN1OctetString.getInstance(instance.getEncapContentInfo().getContent()).getOctets());
            }
            throw new IllegalStateException("cannot parse time stamp");
        }
        throw new IllegalStateException("cannot identify algorithm identifier for digest");
    }

    public AlgorithmIdentifier getDigestAlgorithm() {
        return this.digestAlgorithm;
    }

    public AlgorithmIdentifier getDigestAlgorithmIdentifier() {
        AlgorithmIdentifier algorithmIdentifier = this.digestAlgorithm;
        return algorithmIdentifier != null ? algorithmIdentifier : getTimeStampInfo().getMessageImprint().getHashAlgorithm();
    }

    public PartialHashtree getHashTreeLeaf() {
        ASN1Sequence aSN1Sequence = this.reducedHashTree;
        if (aSN1Sequence == null) {
            return null;
        }
        return PartialHashtree.getInstance(aSN1Sequence.getObjectAt(0));
    }

    public PartialHashtree[] getReducedHashTree() {
        ASN1Sequence aSN1Sequence = this.reducedHashTree;
        if (aSN1Sequence == null) {
            return null;
        }
        int size = aSN1Sequence.size();
        PartialHashtree[] partialHashtreeArr = new PartialHashtree[size];
        for (int i = 0; i != size; i++) {
            partialHashtreeArr[i] = PartialHashtree.getInstance(this.reducedHashTree.getObjectAt(i));
        }
        return partialHashtreeArr;
    }

    public ContentInfo getTimeStamp() {
        return this.timeStamp;
    }

    public byte[] getTimeStampDigestValue() {
        return getTimeStampInfo().getMessageImprint().getHashedMessage();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(4);
        if (this.digestAlgorithm != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, (ASN1Encodable) this.digestAlgorithm));
        }
        if (this.attributes != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, (ASN1Encodable) this.attributes));
        }
        if (this.reducedHashTree != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, (ASN1Encodable) this.reducedHashTree));
        }
        aSN1EncodableVector.add(this.timeStamp);
        return new DERSequence(aSN1EncodableVector);
    }
}
