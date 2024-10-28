package org.bouncycastle.cert.path.validations;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.path.CertPathValidation;
import org.bouncycastle.cert.path.CertPathValidationContext;
import org.bouncycastle.cert.path.CertPathValidationException;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Memoable;

public class BasicConstraintsValidation implements CertPathValidation {
    private boolean isMandatory;
    private Integer maxPathLength;
    private boolean previousCertWasCA;

    public BasicConstraintsValidation() {
        this(true);
    }

    public BasicConstraintsValidation(boolean z) {
        this.previousCertWasCA = true;
        this.maxPathLength = null;
        this.isMandatory = z;
    }

    public Memoable copy() {
        BasicConstraintsValidation basicConstraintsValidation = new BasicConstraintsValidation();
        basicConstraintsValidation.isMandatory = this.isMandatory;
        basicConstraintsValidation.previousCertWasCA = this.previousCertWasCA;
        basicConstraintsValidation.maxPathLength = this.maxPathLength;
        return basicConstraintsValidation;
    }

    public void reset(Memoable memoable) {
        BasicConstraintsValidation basicConstraintsValidation = (BasicConstraintsValidation) memoable;
        this.isMandatory = basicConstraintsValidation.isMandatory;
        this.previousCertWasCA = basicConstraintsValidation.previousCertWasCA;
        this.maxPathLength = basicConstraintsValidation.maxPathLength;
    }

    public void validate(CertPathValidationContext certPathValidationContext, X509CertificateHolder x509CertificateHolder) throws CertPathValidationException {
        ASN1Integer pathLenConstraintInteger;
        certPathValidationContext.addHandledExtension(Extension.basicConstraints);
        if (this.previousCertWasCA) {
            BasicConstraints fromExtensions = BasicConstraints.fromExtensions(x509CertificateHolder.getExtensions());
            this.previousCertWasCA = (fromExtensions != null && fromExtensions.isCA()) || (fromExtensions == null && !this.isMandatory);
            if (this.maxPathLength != null && !x509CertificateHolder.getSubject().equals(x509CertificateHolder.getIssuer())) {
                if (this.maxPathLength.intValue() >= 0) {
                    this.maxPathLength = Integers.valueOf(this.maxPathLength.intValue() - 1);
                } else {
                    throw new CertPathValidationException("Basic constraints violated: path length exceeded");
                }
            }
            if (fromExtensions != null && fromExtensions.isCA() && (pathLenConstraintInteger = fromExtensions.getPathLenConstraintInteger()) != null) {
                int intPositiveValueExact = pathLenConstraintInteger.intPositiveValueExact();
                Integer num = this.maxPathLength;
                if (num == null || intPositiveValueExact < num.intValue()) {
                    this.maxPathLength = Integers.valueOf(intPositiveValueExact);
                    return;
                }
                return;
            }
            return;
        }
        throw new CertPathValidationException("Basic constraints violated: issuer is not a CA");
    }
}
