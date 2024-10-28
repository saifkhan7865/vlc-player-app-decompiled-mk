package org.bouncycastle.oer.its.etsi102941;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.oer.its.etsi102941.basetypes.EcSignature;

public class AuthorizationValidationRequest extends ASN1Object {
    private final EcSignature ecSignature;
    private final SharedAtRequest sharedAtRequest;

    public static class Builder {
        private EcSignature ecSignature;
        private SharedAtRequest sharedAtRequest;

        public AuthorizationValidationRequest createAuthorizationValidationRequest() {
            return new AuthorizationValidationRequest(this.sharedAtRequest, this.ecSignature);
        }

        public Builder setEcSignature(EcSignature ecSignature2) {
            this.ecSignature = ecSignature2;
            return this;
        }

        public Builder setSharedAtRequest(SharedAtRequest sharedAtRequest2) {
            this.sharedAtRequest = sharedAtRequest2;
            return this;
        }
    }

    private AuthorizationValidationRequest(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.sharedAtRequest = SharedAtRequest.getInstance(aSN1Sequence.getObjectAt(0));
            this.ecSignature = EcSignature.getInstance(aSN1Sequence.getObjectAt(1));
            return;
        }
        throw new IllegalArgumentException("expected sequence size of 2");
    }

    public AuthorizationValidationRequest(SharedAtRequest sharedAtRequest2, EcSignature ecSignature2) {
        this.sharedAtRequest = sharedAtRequest2;
        this.ecSignature = ecSignature2;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static AuthorizationValidationRequest getInstance(Object obj) {
        if (obj instanceof AuthorizationValidationRequest) {
            return (AuthorizationValidationRequest) obj;
        }
        if (obj != null) {
            return new AuthorizationValidationRequest(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public EcSignature getEcSignature() {
        return this.ecSignature;
    }

    public SharedAtRequest getSharedAtRequest() {
        return this.sharedAtRequest;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(new ASN1Encodable[]{this.sharedAtRequest, this.ecSignature});
    }
}