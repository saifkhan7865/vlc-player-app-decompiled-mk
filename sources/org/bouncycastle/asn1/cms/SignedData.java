package org.bouncycastle.asn1.cms;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DLSequence;

public class SignedData extends ASN1Object {
    private static final ASN1Integer VERSION_1 = new ASN1Integer(1);
    private static final ASN1Integer VERSION_3 = new ASN1Integer(3);
    private static final ASN1Integer VERSION_4 = new ASN1Integer(4);
    private static final ASN1Integer VERSION_5 = new ASN1Integer(5);
    private ASN1Set certificates;
    private boolean certsBer;
    private final ContentInfo contentInfo;
    private ASN1Set crls;
    private boolean crlsBer;
    private final ASN1Set digestAlgorithms;
    private final boolean digsBer;
    private final ASN1Set signerInfos;
    private final boolean sigsBer;
    private final ASN1Integer version;

    private SignedData(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.version = ASN1Integer.getInstance(objects.nextElement());
        this.digestAlgorithms = (ASN1Set) objects.nextElement();
        this.contentInfo = ContentInfo.getInstance(objects.nextElement());
        ASN1Set aSN1Set = null;
        while (objects.hasMoreElements()) {
            ASN1Primitive aSN1Primitive = (ASN1Primitive) objects.nextElement();
            if (aSN1Primitive instanceof ASN1TaggedObject) {
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
                int tagNo = aSN1TaggedObject.getTagNo();
                if (tagNo == 0) {
                    this.certsBer = aSN1TaggedObject instanceof BERTaggedObject;
                    this.certificates = ASN1Set.getInstance(aSN1TaggedObject, false);
                } else if (tagNo == 1) {
                    this.crlsBer = aSN1TaggedObject instanceof BERTaggedObject;
                    this.crls = ASN1Set.getInstance(aSN1TaggedObject, false);
                } else {
                    throw new IllegalArgumentException("unknown tag value " + aSN1TaggedObject.getTagNo());
                }
            } else if (aSN1Primitive instanceof ASN1Set) {
                aSN1Set = (ASN1Set) aSN1Primitive;
            } else {
                throw new IllegalArgumentException("SET expected, not encountered");
            }
        }
        if (aSN1Set != null) {
            this.signerInfos = aSN1Set;
            this.digsBer = this.digestAlgorithms instanceof BERSet;
            this.sigsBer = aSN1Set instanceof BERSet;
            return;
        }
        throw new IllegalArgumentException("signerInfos not set");
    }

    public SignedData(ASN1Set aSN1Set, ContentInfo contentInfo2, ASN1Set aSN1Set2, ASN1Set aSN1Set3, ASN1Set aSN1Set4) {
        this.version = calculateVersion(contentInfo2.getContentType(), aSN1Set2, aSN1Set3, aSN1Set4);
        this.digestAlgorithms = aSN1Set;
        this.contentInfo = contentInfo2;
        this.certificates = aSN1Set2;
        this.crls = aSN1Set3;
        this.signerInfos = aSN1Set4;
        this.digsBer = aSN1Set instanceof BERSet;
        this.crlsBer = aSN1Set3 instanceof BERSet;
        this.certsBer = aSN1Set2 instanceof BERSet;
        this.sigsBer = aSN1Set4 instanceof BERSet;
    }

    private ASN1Integer calculateVersion(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Set aSN1Set, ASN1Set aSN1Set2, ASN1Set aSN1Set3) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = false;
        if (aSN1Set != null) {
            Enumeration objects = aSN1Set.getObjects();
            z3 = false;
            z2 = false;
            z = false;
            while (objects.hasMoreElements()) {
                Object nextElement = objects.nextElement();
                if (nextElement instanceof ASN1TaggedObject) {
                    ASN1TaggedObject instance = ASN1TaggedObject.getInstance(nextElement);
                    if (instance.getTagNo() == 1) {
                        z2 = true;
                    } else if (instance.getTagNo() == 2) {
                        z = true;
                    } else if (instance.getTagNo() == 3) {
                        z3 = true;
                    }
                }
            }
        } else {
            z3 = false;
            z2 = false;
            z = false;
        }
        if (z3) {
            return new ASN1Integer(5);
        }
        if (aSN1Set2 != null) {
            Enumeration objects2 = aSN1Set2.getObjects();
            while (objects2.hasMoreElements()) {
                if (objects2.nextElement() instanceof ASN1TaggedObject) {
                    z4 = true;
                }
            }
            if (z4) {
                return VERSION_5;
            }
        }
        return z ? VERSION_4 : z2 ? VERSION_3 : checkForVersion3(aSN1Set3) ? VERSION_3 : !CMSObjectIdentifiers.data.equals((ASN1Primitive) aSN1ObjectIdentifier) ? VERSION_3 : VERSION_1;
    }

    private boolean checkForVersion3(ASN1Set aSN1Set) {
        Enumeration objects = aSN1Set.getObjects();
        while (objects.hasMoreElements()) {
            if (SignerInfo.getInstance(objects.nextElement()).getVersion().hasValue(3)) {
                return true;
            }
        }
        return false;
    }

    public static SignedData getInstance(Object obj) {
        if (obj instanceof SignedData) {
            return (SignedData) obj;
        }
        if (obj != null) {
            return new SignedData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Set getCRLs() {
        return this.crls;
    }

    public ASN1Set getCertificates() {
        return this.certificates;
    }

    public ASN1Set getDigestAlgorithms() {
        return this.digestAlgorithms;
    }

    public ContentInfo getEncapContentInfo() {
        return this.contentInfo;
    }

    public ASN1Set getSignerInfos() {
        return this.signerInfos;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(6);
        aSN1EncodableVector.add(this.version);
        aSN1EncodableVector.add(this.digestAlgorithms);
        aSN1EncodableVector.add(this.contentInfo);
        if (this.certificates != null) {
            aSN1EncodableVector.add(this.certsBer ? new BERTaggedObject(false, 0, (ASN1Encodable) this.certificates) : new DERTaggedObject(false, 0, (ASN1Encodable) this.certificates));
        }
        if (this.crls != null) {
            aSN1EncodableVector.add(this.crlsBer ? new BERTaggedObject(false, 1, (ASN1Encodable) this.crls) : new DERTaggedObject(false, 1, (ASN1Encodable) this.crls));
        }
        aSN1EncodableVector.add(this.signerInfos);
        return (!this.contentInfo.isDefiniteLength() || this.digsBer || this.sigsBer || this.crlsBer || this.certsBer) ? new BERSequence(aSN1EncodableVector) : new DLSequence(aSN1EncodableVector);
    }
}
