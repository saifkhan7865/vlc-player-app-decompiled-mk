package org.bouncycastle.cms;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.cms.KEMRecipientInfo;
import org.bouncycastle.asn1.cms.RecipientIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class KEMRecipientInformation extends RecipientInformation {
    private KEMRecipientInfo info;

    KEMRecipientInformation(KEMRecipientInfo kEMRecipientInfo, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        super(kEMRecipientInfo.getKem(), algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        KeyTransRecipientId keyTransRecipientId;
        this.info = kEMRecipientInfo;
        RecipientIdentifier recipientIdentifier = kEMRecipientInfo.getRecipientIdentifier();
        boolean isTagged = recipientIdentifier.isTagged();
        ASN1Encodable id = recipientIdentifier.getId();
        if (isTagged) {
            keyTransRecipientId = new KeyTransRecipientId(ASN1OctetString.getInstance(id).getOctets());
        } else {
            IssuerAndSerialNumber instance = IssuerAndSerialNumber.getInstance(id);
            keyTransRecipientId = new KeyTransRecipientId(instance.getName(), instance.getSerialNumber().getValue());
        }
        this.rid = keyTransRecipientId;
    }

    /* access modifiers changed from: protected */
    public RecipientOperator getRecipientOperator(Recipient recipient) throws CMSException {
        return ((KEMRecipient) recipient).getRecipientOperator(new AlgorithmIdentifier(this.keyEncAlg.getAlgorithm(), this.info), this.messageAlgorithm, this.info.getEncryptedKey().getOctets());
    }
}
