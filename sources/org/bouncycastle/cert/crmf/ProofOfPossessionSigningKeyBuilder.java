package org.bouncycastle.cert.crmf;

import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.crmf.CertRequest;
import org.bouncycastle.asn1.crmf.PKMACValue;
import org.bouncycastle.asn1.crmf.POPOSigningKey;
import org.bouncycastle.asn1.crmf.POPOSigningKeyInput;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.operator.ContentSigner;

public class ProofOfPossessionSigningKeyBuilder {
    private CertRequest certRequest;
    private GeneralName name;
    private SubjectPublicKeyInfo pubKeyInfo;
    private PKMACValue publicKeyMAC;

    public ProofOfPossessionSigningKeyBuilder(CertRequest certRequest2) {
        this.certRequest = certRequest2;
    }

    public ProofOfPossessionSigningKeyBuilder(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.pubKeyInfo = subjectPublicKeyInfo;
    }

    public POPOSigningKey build(ContentSigner contentSigner) {
        POPOSigningKeyInput pOPOSigningKeyInput;
        GeneralName generalName = this.name;
        if (generalName == null || this.publicKeyMAC == null) {
            CertRequest certRequest2 = this.certRequest;
            if (certRequest2 != null) {
                CRMFUtil.derEncodeToStream(certRequest2, contentSigner.getOutputStream());
                pOPOSigningKeyInput = null;
            } else {
                pOPOSigningKeyInput = generalName != null ? new POPOSigningKeyInput(this.name, this.pubKeyInfo) : new POPOSigningKeyInput(this.publicKeyMAC, this.pubKeyInfo);
                CRMFUtil.derEncodeToStream(pOPOSigningKeyInput, contentSigner.getOutputStream());
            }
            return new POPOSigningKey(pOPOSigningKeyInput, contentSigner.getAlgorithmIdentifier(), new DERBitString(contentSigner.getSignature()));
        }
        throw new IllegalStateException("name and publicKeyMAC cannot both be set.");
    }

    public ProofOfPossessionSigningKeyBuilder setPublicKeyMac(PKMACBuilder pKMACBuilder, char[] cArr) throws CRMFException {
        this.publicKeyMAC = PKMACValueGenerator.generate(pKMACBuilder, cArr, this.pubKeyInfo);
        return this;
    }

    public ProofOfPossessionSigningKeyBuilder setSender(GeneralName generalName) {
        this.name = generalName;
        return this;
    }
}
