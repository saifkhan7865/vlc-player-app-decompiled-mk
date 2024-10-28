package org.bouncycastle.its.operator;

import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.its.ITSCertificate;

public interface ITSContentSigner {
    ITSCertificate getAssociatedCertificate();

    byte[] getAssociatedCertificateDigest();

    ASN1ObjectIdentifier getCurveID();

    AlgorithmIdentifier getDigestAlgorithm();

    OutputStream getOutputStream();

    byte[] getSignature();

    boolean isForSelfSigning();
}
