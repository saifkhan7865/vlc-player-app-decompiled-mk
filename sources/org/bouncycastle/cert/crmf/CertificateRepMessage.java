package org.bouncycastle.cert.crmf;

import java.util.ArrayList;
import org.bouncycastle.asn1.cmp.CMPCertificate;
import org.bouncycastle.asn1.cmp.CertRepMessage;
import org.bouncycastle.asn1.cmp.CertResponse;
import org.bouncycastle.asn1.cmp.PKIBody;
import org.bouncycastle.cert.X509CertificateHolder;

public class CertificateRepMessage {
    private final CMPCertificate[] caCerts;
    private final CertResponse[] resps;

    public CertificateRepMessage(CertRepMessage certRepMessage) {
        this.resps = certRepMessage.getResponse();
        this.caCerts = certRepMessage.getCaPubs();
    }

    public static CertificateRepMessage fromPKIBody(PKIBody pKIBody) {
        if (isCertificateRepMessage(pKIBody.getType())) {
            return new CertificateRepMessage(CertRepMessage.getInstance(pKIBody.getContent()));
        }
        throw new IllegalArgumentException("content of PKIBody wrong type: " + pKIBody.getType());
    }

    public static boolean isCertificateRepMessage(int i) {
        return i == 1 || i == 3 || i == 8 || i == 14;
    }

    public CMPCertificate[] getCMPCertificates() {
        CMPCertificate[] cMPCertificateArr = this.caCerts;
        int length = cMPCertificateArr.length;
        CMPCertificate[] cMPCertificateArr2 = new CMPCertificate[length];
        System.arraycopy(cMPCertificateArr, 0, cMPCertificateArr2, 0, length);
        return cMPCertificateArr2;
    }

    public CertificateResponse[] getResponses() {
        int length = this.resps.length;
        CertificateResponse[] certificateResponseArr = new CertificateResponse[length];
        for (int i = 0; i != length; i++) {
            certificateResponseArr[i] = new CertificateResponse(this.resps[i]);
        }
        return certificateResponseArr;
    }

    public X509CertificateHolder[] getX509Certificates() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            CMPCertificate[] cMPCertificateArr = this.caCerts;
            if (i == cMPCertificateArr.length) {
                return (X509CertificateHolder[]) arrayList.toArray(new X509CertificateHolder[0]);
            }
            if (cMPCertificateArr[i].isX509v3PKCert()) {
                arrayList.add(new X509CertificateHolder(this.caCerts[i].getX509v3PKCert()));
            }
            i++;
        }
    }

    public boolean isOnlyX509PKCertificates() {
        boolean z = true;
        int i = 0;
        while (true) {
            CMPCertificate[] cMPCertificateArr = this.caCerts;
            if (i == cMPCertificateArr.length) {
                return z;
            }
            z &= cMPCertificateArr[i].isX509v3PKCert();
            i++;
        }
    }

    public CertRepMessage toASN1Structure() {
        return new CertRepMessage(this.caCerts, this.resps);
    }
}
