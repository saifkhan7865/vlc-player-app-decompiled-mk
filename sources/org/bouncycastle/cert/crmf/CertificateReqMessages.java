package org.bouncycastle.cert.crmf;

import org.bouncycastle.asn1.cmp.PKIBody;
import org.bouncycastle.asn1.crmf.CertReqMessages;
import org.bouncycastle.asn1.crmf.CertReqMsg;

public class CertificateReqMessages {
    private final CertReqMsg[] reqs;

    public CertificateReqMessages(CertReqMessages certReqMessages) {
        this.reqs = certReqMessages.toCertReqMsgArray();
    }

    public static CertificateReqMessages fromPKIBody(PKIBody pKIBody) {
        if (isCertificateRequestMessages(pKIBody.getType())) {
            return new CertificateReqMessages(CertReqMessages.getInstance(pKIBody.getContent()));
        }
        throw new IllegalArgumentException("content of PKIBody wrong type: " + pKIBody.getType());
    }

    public static boolean isCertificateRequestMessages(int i) {
        return i == 0 || i == 2 || i == 7 || i == 9 || i == 13;
    }

    public CertificateRequestMessage[] getRequests() {
        int length = this.reqs.length;
        CertificateRequestMessage[] certificateRequestMessageArr = new CertificateRequestMessage[length];
        for (int i = 0; i != length; i++) {
            certificateRequestMessageArr[i] = new CertificateRequestMessage(this.reqs[i]);
        }
        return certificateRequestMessageArr;
    }

    public CertReqMessages toASN1Structure() {
        return new CertReqMessages(this.reqs);
    }
}
