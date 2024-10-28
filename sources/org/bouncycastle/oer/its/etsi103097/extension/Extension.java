package org.bouncycastle.oer.its.etsi103097.extension;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

public class Extension extends ASN1Object {
    public static final ExtId etsiTs102941CrlRequestId = new ExtId(1);
    public static final ExtId etsiTs102941DeltaCtlRequestId = new ExtId(2);
    private final ASN1Encodable content;
    private final ExtId id;

    protected Extension(ASN1Sequence aSN1Sequence) {
        ASN1Encodable instance;
        if (aSN1Sequence.size() == 2) {
            ExtId instance2 = ExtId.getInstance(aSN1Sequence.getObjectAt(0));
            this.id = instance2;
            if (instance2.equals(etsiTs102941CrlRequestId)) {
                instance = EtsiTs102941CrlRequest.getInstance(aSN1Sequence.getObjectAt(1));
            } else if (instance2.equals(etsiTs102941DeltaCtlRequestId)) {
                instance = EtsiTs102941DeltaCtlRequest.getInstance(aSN1Sequence.getObjectAt(1));
            } else {
                throw new IllegalArgumentException("id not 1 (EtsiTs102941CrlRequest) or 2 (EtsiTs102941DeltaCtlRequest)");
            }
            this.content = instance;
            return;
        }
        throw new IllegalArgumentException("expected sequence size of 2");
    }

    public Extension(ExtId extId, ASN1Encodable aSN1Encodable) {
        this.id = extId;
        if (extId.getExtId().intValue() == 1 || extId.getExtId().intValue() == 2) {
            this.content = aSN1Encodable;
            return;
        }
        throw new IllegalArgumentException("id not 1 (EtsiTs102941CrlRequest) or 2 (EtsiTs102941DeltaCtlRequest)");
    }

    public static Extension etsiTs102941CrlRequest(EtsiTs102941CrlRequest etsiTs102941CrlRequest) {
        return new Extension(etsiTs102941CrlRequestId, etsiTs102941CrlRequest);
    }

    public static Extension etsiTs102941DeltaCtlRequest(EtsiTs102941DeltaCtlRequest etsiTs102941DeltaCtlRequest) {
        return new Extension(etsiTs102941DeltaCtlRequestId, etsiTs102941DeltaCtlRequest);
    }

    public static Extension getInstance(Object obj) {
        if (obj instanceof Extension) {
            return (Extension) obj;
        }
        if (obj != null) {
            return new Extension(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Encodable getContent() {
        return this.content;
    }

    public ExtId getId() {
        return this.id;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(new ASN1Encodable[]{this.id, this.content});
    }
}
