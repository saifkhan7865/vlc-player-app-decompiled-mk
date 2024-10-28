package org.bouncycastle.cert.cmp;

import java.math.BigInteger;
import org.bouncycastle.asn1.cmp.CMPCertificate;
import org.bouncycastle.asn1.cmp.CertStatus;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.util.Arrays;

public class CertificateStatus {
    private CertStatus certStatus;
    private DigestAlgorithmIdentifierFinder digestAlgFinder;

    CertificateStatus(DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder, CertStatus certStatus2) {
        this.digestAlgFinder = digestAlgorithmIdentifierFinder;
        this.certStatus = certStatus2;
    }

    public BigInteger getCertRequestID() {
        return this.certStatus.getCertReqId().getValue();
    }

    public PKIStatusInfo getStatusInfo() {
        return this.certStatus.getStatusInfo();
    }

    public boolean isVerified(CMPCertificate cMPCertificate, AlgorithmIdentifier algorithmIdentifier, DigestCalculatorProvider digestCalculatorProvider) throws CMPException {
        return Arrays.constantTimeAreEqual(this.certStatus.getCertHash().getOctets(), CMPUtil.calculateCertHash(cMPCertificate, algorithmIdentifier, digestCalculatorProvider, this.digestAlgFinder));
    }

    public boolean isVerified(X509CertificateHolder x509CertificateHolder, DigestCalculatorProvider digestCalculatorProvider) throws CMPException {
        return isVerified(new CMPCertificate(x509CertificateHolder.toASN1Structure()), x509CertificateHolder.getSignatureAlgorithm(), digestCalculatorProvider);
    }
}
