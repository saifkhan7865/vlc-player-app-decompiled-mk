package org.bouncycastle.cms;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.cms.KEMRecipientInfo;
import org.bouncycastle.asn1.cms.OtherRecipientInfo;
import org.bouncycastle.asn1.cms.RecipientIdentifier;
import org.bouncycastle.asn1.cms.RecipientInfo;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;

public abstract class KEMRecipientInfoGenerator implements RecipientInfoGenerator {
    private IssuerAndSerialNumber issuerAndSerial;
    private byte[] subjectKeyIdentifier;
    protected final KEMKeyWrapper wrapper;

    protected KEMRecipientInfoGenerator(IssuerAndSerialNumber issuerAndSerialNumber, KEMKeyWrapper kEMKeyWrapper) {
        this.issuerAndSerial = issuerAndSerialNumber;
        this.wrapper = kEMKeyWrapper;
    }

    protected KEMRecipientInfoGenerator(byte[] bArr, KEMKeyWrapper kEMKeyWrapper) {
        this.subjectKeyIdentifier = bArr;
        this.wrapper = kEMKeyWrapper;
    }

    public final RecipientInfo generate(GenericKey genericKey) throws CMSException {
        try {
            return new RecipientInfo(new OtherRecipientInfo(CMSObjectIdentifiers.id_ori_kem, new KEMRecipientInfo(this.issuerAndSerial != null ? new RecipientIdentifier(this.issuerAndSerial) : new RecipientIdentifier((ASN1OctetString) new DEROctetString(this.subjectKeyIdentifier)), this.wrapper.getAlgorithmIdentifier(), new DEROctetString(this.wrapper.getEncapsulation()), this.wrapper.getKdfAlgorithmIdentifier(), new ASN1Integer((long) this.wrapper.getKekLength()), (ASN1OctetString) null, this.wrapper.getWrapAlgorithmIdentifier(), new DEROctetString(this.wrapper.generateWrappedKey(genericKey)))));
        } catch (OperatorException e) {
            throw new CMSException("exception wrapping content key: " + e.getMessage(), e);
        }
    }
}
