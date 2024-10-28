package org.bouncycastle.cms.bc;

import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.cms.KEKIdentifier;
import org.bouncycastle.asn1.cms.OtherKeyAttribute;
import org.bouncycastle.cms.KEKRecipientInfoGenerator;
import org.bouncycastle.operator.bc.BcSymmetricKeyWrapper;

public class BcKEKRecipientInfoGenerator extends KEKRecipientInfoGenerator {
    public BcKEKRecipientInfoGenerator(KEKIdentifier kEKIdentifier, BcSymmetricKeyWrapper bcSymmetricKeyWrapper) {
        super(kEKIdentifier, bcSymmetricKeyWrapper);
    }

    public BcKEKRecipientInfoGenerator(byte[] bArr, BcSymmetricKeyWrapper bcSymmetricKeyWrapper) {
        this(new KEKIdentifier(bArr, (ASN1GeneralizedTime) null, (OtherKeyAttribute) null), bcSymmetricKeyWrapper);
    }
}
