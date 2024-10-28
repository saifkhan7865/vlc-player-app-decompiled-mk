package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.jcajce.PKIXCertRevocationChecker;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.interfaces.BCX509Certificate;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.x509.ExtendedPKIXParameters;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;

public class PKIXCertPathValidatorSpi_8 extends CertPathValidatorSpi {
    private final JcaJceHelper helper;
    private final boolean isForCRLCheck;

    public PKIXCertPathValidatorSpi_8() {
        this(false);
    }

    public PKIXCertPathValidatorSpi_8(boolean z) {
        this.helper = new BCJcaJceHelper();
        this.isForCRLCheck = z;
    }

    static void checkCertificate(X509Certificate x509Certificate) throws AnnotatedException {
        if (x509Certificate instanceof BCX509Certificate) {
            try {
                if (((BCX509Certificate) x509Certificate).getTBSCertificateNative() == null) {
                    e = null;
                    throw new AnnotatedException("unable to process TBSCertificate", e);
                }
            } catch (RuntimeException e) {
                e = e;
            }
        } else {
            try {
                TBSCertificate.getInstance(x509Certificate.getTBSCertificate());
            } catch (CertificateEncodingException e2) {
                throw new AnnotatedException("unable to process TBSCertificate", e2);
            } catch (IllegalArgumentException e3) {
                throw new AnnotatedException(e3.getMessage());
            }
        }
    }

    public PKIXCertPathChecker engineGetRevocationChecker() {
        return new ProvRevocationChecker(this.helper);
    }

    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters pKIXExtendedParameters;
        List<? extends Certificate> list;
        int i;
        PublicKey publicKey;
        X500Name x500Name;
        HashSet hashSet;
        ArrayList arrayList;
        int i2;
        int i3;
        HashSet hashSet2;
        CertPath certPath2 = certPath;
        CertPathParameters certPathParameters2 = certPathParameters;
        if (certPathParameters2 instanceof PKIXParameters) {
            PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder((PKIXParameters) certPathParameters2);
            if (certPathParameters2 instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters) certPathParameters2;
                builder.setUseDeltasEnabled(extendedPKIXParameters.isUseDeltasEnabled());
                builder.setValidityModel(extendedPKIXParameters.getValidityModel());
            }
            pKIXExtendedParameters = builder.build();
        } else if (certPathParameters2 instanceof PKIXExtendedBuilderParameters) {
            pKIXExtendedParameters = ((PKIXExtendedBuilderParameters) certPathParameters2).getBaseParameters();
        } else if (certPathParameters2 instanceof PKIXExtendedParameters) {
            pKIXExtendedParameters = (PKIXExtendedParameters) certPathParameters2;
        } else {
            throw new InvalidAlgorithmParameterException("Parameters must be a " + PKIXParameters.class.getName() + " instance.");
        }
        if (pKIXExtendedParameters.getTrustAnchors() != null) {
            List<? extends Certificate> certificates = certPath.getCertificates();
            int size = certificates.size();
            if (!certificates.isEmpty()) {
                Date validityDate = CertPathValidatorUtilities.getValidityDate(pKIXExtendedParameters, new Date());
                Set initialPolicies = pKIXExtendedParameters.getInitialPolicies();
                try {
                    TrustAnchor findTrustAnchor = CertPathValidatorUtilities.findTrustAnchor((X509Certificate) certificates.get(certificates.size() - 1), pKIXExtendedParameters.getTrustAnchors(), pKIXExtendedParameters.getSigProvider());
                    if (findTrustAnchor != null) {
                        checkCertificate(findTrustAnchor.getTrustedCert());
                        PKIXExtendedParameters build = new PKIXExtendedParameters.Builder(pKIXExtendedParameters).setTrustAnchor(findTrustAnchor).build();
                        ArrayList arrayList2 = new ArrayList();
                        PKIXCertRevocationChecker pKIXCertRevocationChecker = null;
                        for (PKIXCertPathChecker pKIXCertPathChecker : build.getCertPathCheckers()) {
                            pKIXCertPathChecker.init(false);
                            if (!AppUtils$$ExternalSyntheticApiModelOutline0.m((Object) pKIXCertPathChecker)) {
                                arrayList2.add(pKIXCertPathChecker);
                            } else if (pKIXCertRevocationChecker == null) {
                                pKIXCertRevocationChecker = pKIXCertPathChecker instanceof PKIXCertRevocationChecker ? (PKIXCertRevocationChecker) pKIXCertPathChecker : new WrappedRevocationChecker(pKIXCertPathChecker);
                            } else {
                                throw new CertPathValidatorException("only one PKIXRevocationChecker allowed");
                            }
                        }
                        if (build.isRevocationEnabled() && pKIXCertRevocationChecker == null) {
                            pKIXCertRevocationChecker = new ProvRevocationChecker(this.helper);
                        }
                        PKIXCertRevocationChecker pKIXCertRevocationChecker2 = pKIXCertRevocationChecker;
                        int i4 = size + 1;
                        ArrayList[] arrayListArr = new ArrayList[i4];
                        for (int i5 = 0; i5 < i4; i5++) {
                            arrayListArr[i5] = new ArrayList();
                        }
                        HashSet hashSet3 = new HashSet();
                        hashSet3.add(RFC3280CertPathUtilities.ANY_POLICY);
                        PKIXPolicyNode pKIXPolicyNode = new PKIXPolicyNode(new ArrayList(), 0, hashSet3, (PolicyNode) null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false);
                        arrayListArr[0].add(pKIXPolicyNode);
                        PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
                        HashSet hashSet4 = new HashSet();
                        int i6 = build.isExplicitPolicyRequired() ? 0 : i4;
                        int i7 = build.isAnyPolicyInhibited() ? 0 : i4;
                        if (build.isPolicyMappingInhibited()) {
                            i4 = 0;
                        }
                        X509Certificate trustedCert = findTrustAnchor.getTrustedCert();
                        if (trustedCert != null) {
                            try {
                                x500Name = PrincipalUtils.getSubjectPrincipal(trustedCert);
                                publicKey = trustedCert.getPublicKey();
                            } catch (RuntimeException e) {
                                throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", e, certPath2, -1);
                            }
                        } else {
                            x500Name = PrincipalUtils.getCA(findTrustAnchor);
                            publicKey = findTrustAnchor.getCAPublicKey();
                        }
                        try {
                            AlgorithmIdentifier algorithmIdentifier = CertPathValidatorUtilities.getAlgorithmIdentifier(publicKey);
                            algorithmIdentifier.getAlgorithm();
                            algorithmIdentifier.getParameters();
                            if (build.getTargetConstraints() == null || build.getTargetConstraints().match((Certificate) (X509Certificate) certificates.get(0))) {
                                int size2 = certificates.size() - 1;
                                int i8 = size;
                                X509Certificate x509Certificate = null;
                                int i9 = i7;
                                int i10 = i4;
                                int i11 = i6;
                                PKIXPolicyNode pKIXPolicyNode2 = pKIXPolicyNode;
                                while (size2 >= 0) {
                                    int i12 = size - size2;
                                    int i13 = size;
                                    X509Certificate x509Certificate2 = (X509Certificate) certificates.get(size2);
                                    boolean z = size2 == certificates.size() + -1;
                                    try {
                                        checkCertificate(x509Certificate2);
                                        int i14 = size2;
                                        List<? extends Certificate> list2 = certificates;
                                        PKIXNameConstraintValidator pKIXNameConstraintValidator2 = pKIXNameConstraintValidator;
                                        Date date = validityDate;
                                        ArrayList[] arrayListArr2 = arrayListArr;
                                        PKIXExtendedParameters pKIXExtendedParameters2 = build;
                                        int i15 = i11;
                                        ArrayList arrayList3 = arrayList2;
                                        boolean z2 = z;
                                        TrustAnchor trustAnchor = findTrustAnchor;
                                        int i16 = i10;
                                        RFC3280CertPathUtilities.processCertA(certPath, build, validityDate, pKIXCertRevocationChecker2, i14, publicKey, z2, x500Name, trustedCert);
                                        int i17 = i14;
                                        RFC3280CertPathUtilities.processCertBC(certPath2, i17, pKIXNameConstraintValidator2, this.isForCRLCheck);
                                        PKIXPolicyNode processCertE = RFC3280CertPathUtilities.processCertE(certPath2, i17, RFC3280CertPathUtilities.processCertD(certPath, i17, hashSet4, pKIXPolicyNode2, arrayListArr2, i9, this.isForCRLCheck));
                                        RFC3280CertPathUtilities.processCertF(certPath2, i17, processCertE, i15);
                                        int i18 = i13;
                                        if (i12 != i18) {
                                            if (x509Certificate2 == null || x509Certificate2.getVersion() != 1) {
                                                RFC3280CertPathUtilities.prepareNextCertA(certPath2, i17);
                                                arrayListArr = arrayListArr2;
                                                int i19 = i16;
                                                PKIXPolicyNode prepareCertB = RFC3280CertPathUtilities.prepareCertB(certPath2, i17, arrayListArr, processCertE, i19);
                                                RFC3280CertPathUtilities.prepareNextCertG(certPath2, i17, pKIXNameConstraintValidator2);
                                                int prepareNextCertH1 = RFC3280CertPathUtilities.prepareNextCertH1(certPath2, i17, i15);
                                                int prepareNextCertH2 = RFC3280CertPathUtilities.prepareNextCertH2(certPath2, i17, i19);
                                                int prepareNextCertH3 = RFC3280CertPathUtilities.prepareNextCertH3(certPath2, i17, i9);
                                                i15 = RFC3280CertPathUtilities.prepareNextCertI1(certPath2, i17, prepareNextCertH1);
                                                i3 = RFC3280CertPathUtilities.prepareNextCertI2(certPath2, i17, prepareNextCertH2);
                                                i2 = RFC3280CertPathUtilities.prepareNextCertJ(certPath2, i17, prepareNextCertH3);
                                                RFC3280CertPathUtilities.prepareNextCertK(certPath2, i17);
                                                i8 = RFC3280CertPathUtilities.prepareNextCertM(certPath2, i17, RFC3280CertPathUtilities.prepareNextCertL(certPath2, i17, i8));
                                                RFC3280CertPathUtilities.prepareNextCertN(certPath2, i17);
                                                Set criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                                                if (criticalExtensionOIDs != null) {
                                                    hashSet2.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                                    hashSet2.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                                    hashSet2.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                                    hashSet2.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                                    hashSet2.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                                    hashSet2.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                                    hashSet2.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                                    hashSet2.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                                    hashSet2.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                                    hashSet2.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                                } else {
                                                    hashSet2 = new HashSet();
                                                }
                                                arrayList = arrayList3;
                                                RFC3280CertPathUtilities.prepareNextCertO(certPath2, i17, hashSet2, arrayList);
                                                X500Name subjectPrincipal = PrincipalUtils.getSubjectPrincipal(x509Certificate2);
                                                try {
                                                    PublicKey nextWorkingKey = CertPathValidatorUtilities.getNextWorkingKey(certPath.getCertificates(), i17, this.helper);
                                                    AlgorithmIdentifier algorithmIdentifier2 = CertPathValidatorUtilities.getAlgorithmIdentifier(nextWorkingKey);
                                                    algorithmIdentifier2.getAlgorithm();
                                                    algorithmIdentifier2.getParameters();
                                                    pKIXPolicyNode2 = prepareCertB;
                                                    x500Name = subjectPrincipal;
                                                    publicKey = nextWorkingKey;
                                                    trustedCert = x509Certificate2;
                                                    i11 = i15;
                                                    int i20 = i17 - 1;
                                                    i9 = i2;
                                                    arrayList2 = arrayList;
                                                    x509Certificate = x509Certificate2;
                                                    findTrustAnchor = trustAnchor;
                                                    validityDate = date;
                                                    size = i18;
                                                    i10 = i3;
                                                    pKIXNameConstraintValidator = pKIXNameConstraintValidator2;
                                                    certificates = list2;
                                                    size2 = i20;
                                                    build = pKIXExtendedParameters2;
                                                } catch (CertPathValidatorException e2) {
                                                    throw new CertPathValidatorException("Next working key could not be retrieved.", e2, certPath2, i17);
                                                }
                                            } else if (i12 != 1 || !x509Certificate2.equals(trustAnchor.getTrustedCert())) {
                                                throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", (Throwable) null, certPath2, i17);
                                            }
                                        }
                                        i2 = i9;
                                        arrayListArr = arrayListArr2;
                                        arrayList = arrayList3;
                                        i3 = i16;
                                        pKIXPolicyNode2 = processCertE;
                                        i8 = i8;
                                        i11 = i15;
                                        int i202 = i17 - 1;
                                        i9 = i2;
                                        arrayList2 = arrayList;
                                        x509Certificate = x509Certificate2;
                                        findTrustAnchor = trustAnchor;
                                        validityDate = date;
                                        size = i18;
                                        i10 = i3;
                                        pKIXNameConstraintValidator = pKIXNameConstraintValidator2;
                                        certificates = list2;
                                        size2 = i202;
                                        build = pKIXExtendedParameters2;
                                    } catch (AnnotatedException e3) {
                                        AnnotatedException annotatedException = e3;
                                        throw new CertPathValidatorException(annotatedException.getMessage(), annotatedException.getUnderlyingException(), certPath2, size2);
                                    }
                                }
                                PKIXExtendedParameters pKIXExtendedParameters3 = build;
                                ArrayList arrayList4 = arrayList2;
                                TrustAnchor trustAnchor2 = findTrustAnchor;
                                X509Certificate x509Certificate3 = x509Certificate;
                                int i21 = size2;
                                int i22 = i21 + 1;
                                int wrapupCertB = RFC3280CertPathUtilities.wrapupCertB(certPath2, i22, RFC3280CertPathUtilities.wrapupCertA(i11, x509Certificate3));
                                Set criticalExtensionOIDs2 = x509Certificate3.getCriticalExtensionOIDs();
                                if (criticalExtensionOIDs2 != null) {
                                    hashSet.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                    hashSet.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                    hashSet.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                    hashSet.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                    hashSet.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                    hashSet.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                    hashSet.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                    hashSet.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                    hashSet.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                    hashSet.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                    hashSet.remove(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS);
                                    hashSet.remove(Extension.extendedKeyUsage.getId());
                                } else {
                                    hashSet = new HashSet();
                                }
                                RFC3280CertPathUtilities.wrapupCertF(certPath2, i22, arrayList4, hashSet);
                                PKIXPolicyNode wrapupCertG = RFC3280CertPathUtilities.wrapupCertG(certPath, pKIXExtendedParameters3, initialPolicies, i22, arrayListArr, pKIXPolicyNode2, hashSet4);
                                if (wrapupCertB > 0 || wrapupCertG != null) {
                                    return new PKIXCertPathValidatorResult(trustAnchor2, wrapupCertG, x509Certificate3.getPublicKey());
                                }
                                throw new CertPathValidatorException("Path processing failed on policy.", (Throwable) null, certPath2, i21);
                            }
                            throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", (Throwable) null, certPath2, 0);
                        } catch (CertPathValidatorException e4) {
                            throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", e4, certPath2, -1);
                        }
                    } else {
                        list = certificates;
                        i = 1;
                        try {
                            throw new CertPathValidatorException("Trust anchor for certification path not found.", (Throwable) null, certPath2, -1);
                        } catch (AnnotatedException e5) {
                            e = e5;
                            throw new CertPathValidatorException(e.getMessage(), e.getUnderlyingException(), certPath2, list.size() - i);
                        }
                    }
                } catch (AnnotatedException e6) {
                    e = e6;
                    list = certificates;
                    i = 1;
                    throw new CertPathValidatorException(e.getMessage(), e.getUnderlyingException(), certPath2, list.size() - i);
                }
            } else {
                throw new CertPathValidatorException("Certification path is empty.", (Throwable) null, certPath2, -1);
            }
        } else {
            throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
        }
    }
}
