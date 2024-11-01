package org.bouncycastle.tsp.cms;

import java.net.URI;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1UTF8String;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.cms.Attributes;
import org.bouncycastle.asn1.cms.MetaData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.DigestCalculator;

public class CMSTimeStampedGenerator {
    protected URI dataUri;
    protected MetaData metaData;

    private void setMetaData(boolean z, ASN1UTF8String aSN1UTF8String, ASN1IA5String aSN1IA5String, Attributes attributes) {
        this.metaData = new MetaData(ASN1Boolean.getInstance(z), aSN1UTF8String, aSN1IA5String, attributes);
    }

    public void initialiseMessageImprintDigestCalculator(DigestCalculator digestCalculator) throws CMSException {
        new MetaDataUtil(this.metaData).initialiseMessageImprintDigestCalculator(digestCalculator);
    }

    public void setDataUri(URI uri) {
        this.dataUri = uri;
    }

    public void setMetaData(boolean z, String str, String str2) {
        setMetaData(z, str, str2, (Attributes) null);
    }

    public void setMetaData(boolean z, String str, String str2, Attributes attributes) {
        DERIA5String dERIA5String = null;
        DERUTF8String dERUTF8String = str != null ? new DERUTF8String(str) : null;
        if (str2 != null) {
            dERIA5String = new DERIA5String(str2);
        }
        setMetaData(z, (ASN1UTF8String) dERUTF8String, (ASN1IA5String) dERIA5String, attributes);
    }
}
