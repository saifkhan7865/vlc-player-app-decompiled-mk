package org.bouncycastle.cert.crmf;

import org.bouncycastle.asn1.cmp.CMPCertificate;
import org.bouncycastle.asn1.cmp.CertResponse;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.cms.CMSEnvelopedData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.Recipient;

public class CertificateResponse {
    private final CertResponse certResponse;

    public CertificateResponse(CertResponse certResponse2) {
        this.certResponse = certResponse2;
    }

    public CMPCertificate getCertificate() throws CMSException {
        if (!hasEncryptedCertificate()) {
            return this.certResponse.getCertifiedKeyPair().getCertOrEncCert().getCertificate();
        }
        throw new IllegalStateException("plaintext certificate asked for, none found");
    }

    public CMPCertificate getCertificate(Recipient recipient) throws CMSException {
        return CMPCertificate.getInstance(getEncryptedCertificate().getRecipientInfos().getRecipients().iterator().next().getContent(recipient));
    }

    public CMSEnvelopedData getEncryptedCertificate() throws CMSException {
        if (hasEncryptedCertificate()) {
            CMSEnvelopedData cMSEnvelopedData = new CMSEnvelopedData(new ContentInfo(PKCSObjectIdentifiers.envelopedData, this.certResponse.getCertifiedKeyPair().getCertOrEncCert().getEncryptedCert().getValue()));
            if (cMSEnvelopedData.getRecipientInfos().size() == 1) {
                return cMSEnvelopedData;
            }
            throw new IllegalStateException("data encrypted for more than one recipient");
        }
        throw new IllegalStateException("encrypted certificate asked for, none found");
    }

    public boolean hasEncryptedCertificate() {
        return this.certResponse.getCertifiedKeyPair().getCertOrEncCert().hasEncryptedCertificate();
    }

    public CertResponse toASN1Structure() {
        return this.certResponse;
    }
}
