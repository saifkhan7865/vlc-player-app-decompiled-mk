package org.bouncycastle.cert.cmp;

import org.bouncycastle.asn1.cmp.CertConfirmContent;
import org.bouncycastle.asn1.cmp.CertStatus;
import org.bouncycastle.asn1.cmp.PKIBody;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;

public class CertificateConfirmationContent {
    private CertConfirmContent content;
    private DigestAlgorithmIdentifierFinder digestAlgFinder;

    public CertificateConfirmationContent(CertConfirmContent certConfirmContent) {
        this(certConfirmContent, new DefaultDigestAlgorithmIdentifierFinder());
    }

    public CertificateConfirmationContent(CertConfirmContent certConfirmContent, DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder) {
        this.digestAlgFinder = digestAlgorithmIdentifierFinder;
        this.content = certConfirmContent;
    }

    public static CertificateConfirmationContent fromPKIBody(PKIBody pKIBody) {
        return fromPKIBody(pKIBody, new DefaultDigestAlgorithmIdentifierFinder());
    }

    public static CertificateConfirmationContent fromPKIBody(PKIBody pKIBody, DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder) {
        if (isCertificateConfirmationContent(pKIBody.getType())) {
            return new CertificateConfirmationContent(CertConfirmContent.getInstance(pKIBody.getContent()), digestAlgorithmIdentifierFinder);
        }
        throw new IllegalArgumentException("content of PKIBody wrong type: " + pKIBody.getType());
    }

    public static boolean isCertificateConfirmationContent(int i) {
        return i == 24;
    }

    public CertificateStatus[] getStatusMessages() {
        CertStatus[] certStatusArray = this.content.toCertStatusArray();
        int length = certStatusArray.length;
        CertificateStatus[] certificateStatusArr = new CertificateStatus[length];
        for (int i = 0; i != length; i++) {
            certificateStatusArr[i] = new CertificateStatus(this.digestAlgFinder, certStatusArray[i]);
        }
        return certificateStatusArr;
    }

    public CertConfirmContent toASN1Structure() {
        return this.content;
    }
}
