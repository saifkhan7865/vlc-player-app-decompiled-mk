package org.bouncycastle.oer.its.etsi102941;

import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERIA5String;

public class DcDelete extends ASN1Object {
    private final String url;

    public DcDelete(String str) {
        this.url = str;
    }

    private DcDelete(ASN1IA5String aSN1IA5String) {
        this.url = aSN1IA5String.getString();
    }

    public static DcDelete getInstance(Object obj) {
        if (obj instanceof DcDelete) {
            return (DcDelete) obj;
        }
        if (obj != null) {
            return new DcDelete(ASN1IA5String.getInstance(obj));
        }
        return null;
    }

    public String getUrl() {
        return this.url;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERIA5String(this.url);
    }
}