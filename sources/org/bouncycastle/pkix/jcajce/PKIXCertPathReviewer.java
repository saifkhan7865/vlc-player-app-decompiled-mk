package org.bouncycastle.pkix.jcajce;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.GeneralSubtree;
import org.bouncycastle.asn1.x509.NameConstraints;
import org.bouncycastle.asn1.x509.qualified.MonetaryValue;
import org.bouncycastle.asn1.x509.qualified.QCStatement;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pkix.PKIXNameConstraintValidator;
import org.bouncycastle.pkix.PKIXNameConstraintValidatorException;
import org.bouncycastle.pkix.util.ErrorBundle;
import org.bouncycastle.pkix.util.filter.TrustedInput;
import org.bouncycastle.pkix.util.filter.UntrustedInput;
import org.bouncycastle.util.Integers;

public class PKIXCertPathReviewer extends CertPathValidatorUtilities {
    private static final String AUTH_INFO_ACCESS = Extension.authorityInfoAccess.getId();
    private static final String CRL_DIST_POINTS = Extension.cRLDistributionPoints.getId();
    private static final String QC_STATEMENT = Extension.qCStatements.getId();
    private static final String RESOURCE_NAME = "org.bouncycastle.pkix.CertPathReviewerMessages";
    protected CertPath certPath;
    protected List certs;
    protected Date currentDate;
    protected List[] errors;
    private boolean initialized;
    protected int n;
    protected List[] notifications;
    protected PKIXParameters pkixParams;
    protected PolicyNode policyTree;
    protected PublicKey subjectPublicKey;
    protected TrustAnchor trustAnchor;
    protected Date validDate;

    public PKIXCertPathReviewer() {
    }

    public PKIXCertPathReviewer(CertPath certPath2, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        init(certPath2, pKIXParameters);
    }

    private String IPtoString(byte[] bArr) {
        try {
            return InetAddress.getByAddress(bArr).getHostAddress();
        } catch (Exception unused) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i != bArr.length; i++) {
                stringBuffer.append(Integer.toHexString(bArr[i] & 255));
                stringBuffer.append(' ');
            }
            return stringBuffer.toString();
        }
    }

    private void checkCriticalExtensions() {
        int size;
        List<PKIXCertPathChecker> certPathCheckers = this.pkixParams.getCertPathCheckers();
        for (PKIXCertPathChecker init : certPathCheckers) {
            try {
                init.init(false);
            } catch (CertPathValidatorException e) {
                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certPathCheckerError", new Object[]{e.getMessage(), e, e.getClass().getName()}), e);
            } catch (CertPathValidatorException e2) {
                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.criticalExtensionError", new Object[]{e2.getMessage(), e2, e2.getClass().getName()}), e2.getCause(), this.certPath, size);
            } catch (CertPathReviewerException e3) {
                addError(e3.getErrorMessage(), e3.getIndex());
                return;
            }
        }
        size = this.certs.size() - 1;
        while (size >= 0) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            Set<String> criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
            if (criticalExtensionOIDs != null) {
                if (!criticalExtensionOIDs.isEmpty()) {
                    criticalExtensionOIDs.remove(KEY_USAGE);
                    criticalExtensionOIDs.remove(CERTIFICATE_POLICIES);
                    criticalExtensionOIDs.remove(POLICY_MAPPINGS);
                    criticalExtensionOIDs.remove(INHIBIT_ANY_POLICY);
                    criticalExtensionOIDs.remove(ISSUING_DISTRIBUTION_POINT);
                    criticalExtensionOIDs.remove(DELTA_CRL_INDICATOR);
                    criticalExtensionOIDs.remove(POLICY_CONSTRAINTS);
                    criticalExtensionOIDs.remove(BASIC_CONSTRAINTS);
                    criticalExtensionOIDs.remove(SUBJECT_ALTERNATIVE_NAME);
                    criticalExtensionOIDs.remove(NAME_CONSTRAINTS);
                    if (size == 0) {
                        criticalExtensionOIDs.remove(Extension.extendedKeyUsage.getId());
                    }
                    String str = QC_STATEMENT;
                    if (criticalExtensionOIDs.contains(str) && processQcStatements(x509Certificate, size)) {
                        criticalExtensionOIDs.remove(str);
                    }
                    for (PKIXCertPathChecker check : certPathCheckers) {
                        check.check(x509Certificate, criticalExtensionOIDs);
                    }
                    if (!criticalExtensionOIDs.isEmpty()) {
                        for (String aSN1ObjectIdentifier : criticalExtensionOIDs) {
                            addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.unknownCriticalExt", new Object[]{new ASN1ObjectIdentifier(aSN1ObjectIdentifier)}), size);
                        }
                    }
                }
            }
            size--;
        }
    }

    private void checkNameConstraints() {
        GeneralName instance;
        PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
        for (int size = this.certs.size() - 1; size > 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            if (!isSelfIssued(x509Certificate)) {
                X500Principal subjectPrincipal = getSubjectPrincipal(x509Certificate);
                try {
                    ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream((InputStream) new ByteArrayInputStream(subjectPrincipal.getEncoded())).readObject();
                    pKIXNameConstraintValidator.checkPermittedDN(aSN1Sequence);
                    pKIXNameConstraintValidator.checkExcludedDN(aSN1Sequence);
                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence) getExtensionValue(x509Certificate, SUBJECT_ALTERNATIVE_NAME);
                    if (aSN1Sequence2 != null) {
                        for (int i = 0; i < aSN1Sequence2.size(); i++) {
                            instance = GeneralName.getInstance(aSN1Sequence2.getObjectAt(i));
                            pKIXNameConstraintValidator.checkPermitted(instance);
                            pKIXNameConstraintValidator.checkExcluded(instance);
                        }
                    }
                } catch (AnnotatedException e) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncExtError"), e, this.certPath, size);
                } catch (IOException e2) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncSubjectNameError", new Object[]{new UntrustedInput(subjectPrincipal)}), e2, this.certPath, size);
                } catch (PKIXNameConstraintValidatorException e3) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e3, this.certPath, size);
                } catch (PKIXNameConstraintValidatorException e4) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.excludedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e4, this.certPath, size);
                } catch (AnnotatedException e5) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.subjAltNameExtError"), e5, this.certPath, size);
                } catch (PKIXNameConstraintValidatorException e6) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedEmail", new Object[]{new UntrustedInput(instance)}), e6, this.certPath, size);
                } catch (CertPathReviewerException e7) {
                    addError(e7.getErrorMessage(), e7.getIndex());
                    return;
                }
            }
            ASN1Sequence aSN1Sequence3 = (ASN1Sequence) getExtensionValue(x509Certificate, NAME_CONSTRAINTS);
            if (aSN1Sequence3 != null) {
                NameConstraints instance2 = NameConstraints.getInstance(aSN1Sequence3);
                GeneralSubtree[] permittedSubtrees = instance2.getPermittedSubtrees();
                if (permittedSubtrees != null) {
                    pKIXNameConstraintValidator.intersectPermittedSubtree(permittedSubtrees);
                }
                GeneralSubtree[] excludedSubtrees = instance2.getExcludedSubtrees();
                if (excludedSubtrees != null) {
                    for (int i2 = 0; i2 != excludedSubtrees.length; i2++) {
                        pKIXNameConstraintValidator.addExcludedSubtree(excludedSubtrees[i2]);
                    }
                }
            }
        }
    }

    private void checkPathLength() {
        BasicConstraints basicConstraints;
        ASN1Integer pathLenConstraintInteger;
        int i = this.n;
        int i2 = 0;
        for (int size = this.certs.size() - 1; size > 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            if (!isSelfIssued(x509Certificate)) {
                if (i <= 0) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.pathLengthExtended"));
                }
                i--;
                i2++;
            }
            try {
                basicConstraints = BasicConstraints.getInstance(getExtensionValue(x509Certificate, BASIC_CONSTRAINTS));
            } catch (AnnotatedException unused) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.processLengthConstError"), size);
                basicConstraints = null;
            }
            if (!(basicConstraints == null || !basicConstraints.isCA() || (pathLenConstraintInteger = basicConstraints.getPathLenConstraintInteger()) == null)) {
                i = Math.min(i, pathLenConstraintInteger.intPositiveValueExact());
            }
        }
        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.totalPathLength", new Object[]{Integers.valueOf(i2)}));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:215:0x03fd, code lost:
        throw new org.bouncycastle.pkix.jcajce.CertPathReviewerException(new org.bouncycastle.pkix.util.ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyInhibitExtError"), r1.certPath, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        r0 = getQualifierSet(r2.getPolicyQualifiers());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        r2 = r5[r4 - 1];
        r30 = r13;
        r15 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0166, code lost:
        if (r15 >= r2.size()) goto L_0x01fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0168, code lost:
        r13 = (org.bouncycastle.pkix.jcajce.PKIXPolicyNode) r2.get(r15);
        r31 = r13.getExpectedPolicies().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x017a, code lost:
        if (r31.hasNext() == false) goto L_0x01f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x017c, code lost:
        r32 = r2;
        r2 = r31.next();
        r33 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0186, code lost:
        if ((r2 instanceof java.lang.String) == false) goto L_0x018b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0188, code lost:
        r2 = (java.lang.String) r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x018d, code lost:
        if ((r2 instanceof org.bouncycastle.asn1.ASN1ObjectIdentifier) == false) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x018f, code lost:
        r2 = ((org.bouncycastle.asn1.ASN1ObjectIdentifier) r2).getId();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0195, code lost:
        r10 = r13.getChildren();
        r17 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x019f, code lost:
        if (r10.hasNext() == false) goto L_0x01b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01a1, code lost:
        r19 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01b1, code lost:
        if (r2.equals(((org.bouncycastle.pkix.jcajce.PKIXPolicyNode) r10.next()).getValidPolicy()) == false) goto L_0x01b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01b3, code lost:
        r17 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01b5, code lost:
        r10 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01b8, code lost:
        if (r17 != false) goto L_0x01e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01ba, code lost:
        r10 = new java.util.HashSet();
        r10.add(r2);
        r34 = r11;
        r17 = new org.bouncycastle.pkix.jcajce.PKIXPolicyNode(new java.util.ArrayList(), r4, r10, r13, r0, r2, false);
        r13.addChild(r17);
        r5[r4].add(r17);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x01e5, code lost:
        r34 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01e7, code lost:
        r2 = r32;
        r10 = r33;
        r11 = r34;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01ee, code lost:
        r2 = r32;
        r10 = r33;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01f3, code lost:
        r32 = r2;
        r33 = r10;
        r34 = r11;
        r15 = r15 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01fd, code lost:
        r33 = r10;
        r34 = r11;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:213:0x03ef */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0216 A[Catch:{ AnnotatedException -> 0x05ea, AnnotatedException -> 0x0433, AnnotatedException -> 0x040d, AnnotatedException -> 0x03fe, AnnotatedException -> 0x0362, CertPathValidatorException -> 0x0353, CertPathValidatorException -> 0x0202, CertPathValidatorException -> 0x00e0, CertPathReviewerException -> 0x05f7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x023f A[Catch:{ AnnotatedException -> 0x05ea, AnnotatedException -> 0x0433, AnnotatedException -> 0x040d, AnnotatedException -> 0x03fe, AnnotatedException -> 0x0362, CertPathValidatorException -> 0x0353, CertPathValidatorException -> 0x0202, CertPathValidatorException -> 0x00e0, CertPathReviewerException -> 0x05f7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:350:0x012b A[EDGE_INSN: B:350:0x012b->B:62:0x012b ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0120 A[Catch:{ AnnotatedException -> 0x05ea, AnnotatedException -> 0x0433, AnnotatedException -> 0x040d, AnnotatedException -> 0x03fe, AnnotatedException -> 0x0362, CertPathValidatorException -> 0x0353, CertPathValidatorException -> 0x0202, CertPathValidatorException -> 0x00e0, CertPathReviewerException -> 0x05f7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x013d A[Catch:{ AnnotatedException -> 0x05ea, AnnotatedException -> 0x0433, AnnotatedException -> 0x040d, AnnotatedException -> 0x03fe, AnnotatedException -> 0x0362, CertPathValidatorException -> 0x0353, CertPathValidatorException -> 0x0202, CertPathValidatorException -> 0x00e0, CertPathReviewerException -> 0x05f7 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkPolicy() {
        /*
            r35 = this;
            r1 = r35
            java.lang.String r2 = "CertPathReviewer.policyExtError"
            java.security.cert.PKIXParameters r0 = r1.pkixParams
            java.util.Set r0 = r0.getInitialPolicies()
            int r3 = r1.n
            r4 = 1
            int r3 = r3 + r4
            java.util.ArrayList[] r5 = new java.util.ArrayList[r3]
            r6 = 0
            r7 = 0
        L_0x0012:
            if (r7 >= r3) goto L_0x001e
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r5[r7] = r8
            int r7 = r7 + 1
            goto L_0x0012
        L_0x001e:
            java.util.HashSet r11 = new java.util.HashSet
            r11.<init>()
            java.lang.String r7 = "2.5.29.32.0"
            r11.add(r7)
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r15 = new org.bouncycastle.pkix.jcajce.PKIXPolicyNode
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.HashSet r13 = new java.util.HashSet
            r13.<init>()
            java.lang.String r14 = "2.5.29.32.0"
            r16 = 0
            r10 = 0
            r12 = 0
            r8 = r15
            r4 = r15
            r15 = r16
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)
            r8 = r5[r6]
            r8.add(r4)
            java.security.cert.PKIXParameters r8 = r1.pkixParams
            boolean r8 = r8.isExplicitPolicyRequired()
            if (r8 == 0) goto L_0x0051
            r8 = 0
            r9 = 1
            goto L_0x0055
        L_0x0051:
            int r8 = r1.n
            r9 = 1
            int r8 = r8 + r9
        L_0x0055:
            java.security.cert.PKIXParameters r10 = r1.pkixParams
            boolean r10 = r10.isAnyPolicyInhibited()
            if (r10 == 0) goto L_0x005f
            r10 = 0
            goto L_0x0062
        L_0x005f:
            int r10 = r1.n
            int r10 = r10 + r9
        L_0x0062:
            java.security.cert.PKIXParameters r11 = r1.pkixParams
            boolean r11 = r11.isPolicyMappingInhibited()
            if (r11 == 0) goto L_0x006c
            r11 = 0
            goto L_0x006f
        L_0x006c:
            int r11 = r1.n
            int r11 = r11 + r9
        L_0x006f:
            java.util.List r12 = r1.certs     // Catch:{ CertPathReviewerException -> 0x05f7 }
            int r12 = r12.size()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            int r12 = r12 - r9
            r15 = r4
            r4 = 0
            r13 = 0
        L_0x0079:
            java.lang.String r14 = "CertPathReviewer.policyConstExtError"
            java.lang.String r9 = "org.bouncycastle.pkix.CertPathReviewerMessages"
            if (r12 < 0) goto L_0x0442
            int r4 = r1.n     // Catch:{ CertPathReviewerException -> 0x05f7 }
            int r4 = r4 - r12
            java.util.List r6 = r1.certs     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.lang.Object r6 = r6.get(r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.security.cert.X509Certificate r6 = (java.security.cert.X509Certificate) r6     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r25 = r3
            java.lang.String r3 = CERTIFICATE_POLICIES     // Catch:{ AnnotatedException -> 0x0433 }
            org.bouncycastle.asn1.ASN1Primitive r3 = getExtensionValue(r6, r3)     // Catch:{ AnnotatedException -> 0x0433 }
            org.bouncycastle.asn1.ASN1Sequence r3 = (org.bouncycastle.asn1.ASN1Sequence) r3     // Catch:{ AnnotatedException -> 0x0433 }
            r26 = r14
            java.lang.String r14 = "CertPathReviewer.policyQualifierError"
            if (r3 == 0) goto L_0x025d
            if (r15 == 0) goto L_0x025d
            java.util.Enumeration r17 = r3.getObjects()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r27 = r0
            java.util.HashSet r0 = new java.util.HashSet     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r0.<init>()     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x00a7:
            boolean r18 = r17.hasMoreElements()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r18 == 0) goto L_0x00f3
            java.lang.Object r18 = r17.nextElement()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.asn1.x509.PolicyInformation r18 = org.bouncycastle.asn1.x509.PolicyInformation.getInstance(r18)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r28 = r15
            org.bouncycastle.asn1.ASN1ObjectIdentifier r15 = r18.getPolicyIdentifier()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r29 = r2
            java.lang.String r2 = r15.getId()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r0.add(r2)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.lang.String r2 = r15.getId()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            boolean r2 = r7.equals(r2)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r2 != 0) goto L_0x00ee
            org.bouncycastle.asn1.ASN1Sequence r2 = r18.getPolicyQualifiers()     // Catch:{ CertPathValidatorException -> 0x00e0 }
            java.util.Set r2 = getQualifierSet(r2)     // Catch:{ CertPathValidatorException -> 0x00e0 }
            boolean r18 = processCertD1i(r4, r5, r15, r2)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r18 != 0) goto L_0x00ee
            processCertD1ii(r4, r5, r15, r2)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            goto L_0x00ee
        L_0x00e0:
            r0 = move-exception
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2.<init>(r9, r14)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r3 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.security.cert.CertPath r4 = r1.certPath     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r3.<init>(r2, r0, r4, r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            throw r3     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x00ee:
            r15 = r28
            r2 = r29
            goto L_0x00a7
        L_0x00f3:
            r29 = r2
            r28 = r15
            if (r13 == 0) goto L_0x011d
            boolean r2 = r13.contains(r7)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r2 == 0) goto L_0x0100
            goto L_0x011d
        L_0x0100:
            java.util.Iterator r2 = r13.iterator()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.util.HashSet r13 = new java.util.HashSet     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r13.<init>()     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x0109:
            boolean r15 = r2.hasNext()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r15 == 0) goto L_0x011e
            java.lang.Object r15 = r2.next()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            boolean r17 = r0.contains(r15)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r17 == 0) goto L_0x0109
            r13.add(r15)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            goto L_0x0109
        L_0x011d:
            r13 = r0
        L_0x011e:
            if (r10 > 0) goto L_0x0133
            int r0 = r1.n     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r4 >= r0) goto L_0x012b
            boolean r0 = isSelfIssued(r6)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r0 == 0) goto L_0x012b
            goto L_0x0133
        L_0x012b:
            r33 = r10
            r34 = r11
            r30 = r13
            goto L_0x0210
        L_0x0133:
            java.util.Enumeration r0 = r3.getObjects()     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x0137:
            boolean r2 = r0.hasMoreElements()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r2 == 0) goto L_0x012b
            java.lang.Object r2 = r0.nextElement()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.asn1.x509.PolicyInformation r2 = org.bouncycastle.asn1.x509.PolicyInformation.getInstance(r2)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r15 = r2.getPolicyIdentifier()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.lang.String r15 = r15.getId()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            boolean r15 = r7.equals(r15)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r15 == 0) goto L_0x0137
            org.bouncycastle.asn1.ASN1Sequence r0 = r2.getPolicyQualifiers()     // Catch:{ CertPathValidatorException -> 0x0202 }
            java.util.Set r0 = getQualifierSet(r0)     // Catch:{ CertPathValidatorException -> 0x0202 }
            int r2 = r4 + -1
            r2 = r5[r2]     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r30 = r13
            r15 = 0
        L_0x0162:
            int r13 = r2.size()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r15 >= r13) goto L_0x01fd
            java.lang.Object r13 = r2.get(r15)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r13 = (org.bouncycastle.pkix.jcajce.PKIXPolicyNode) r13     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.util.Set r17 = r13.getExpectedPolicies()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.util.Iterator r31 = r17.iterator()     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x0176:
            boolean r17 = r31.hasNext()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r17 == 0) goto L_0x01f3
            r32 = r2
            java.lang.Object r2 = r31.next()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r33 = r10
            boolean r10 = r2 instanceof java.lang.String     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r10 == 0) goto L_0x018b
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ CertPathReviewerException -> 0x05f7 }
            goto L_0x0195
        L_0x018b:
            boolean r10 = r2 instanceof org.bouncycastle.asn1.ASN1ObjectIdentifier     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r10 == 0) goto L_0x01ee
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = (org.bouncycastle.asn1.ASN1ObjectIdentifier) r2     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.lang.String r2 = r2.getId()     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x0195:
            java.util.Iterator r10 = r13.getChildren()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r17 = 0
        L_0x019b:
            boolean r18 = r10.hasNext()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r18 == 0) goto L_0x01b8
            java.lang.Object r18 = r10.next()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r18 = (org.bouncycastle.pkix.jcajce.PKIXPolicyNode) r18     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r19 = r10
            java.lang.String r10 = r18.getValidPolicy()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            boolean r10 = r2.equals(r10)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r10 == 0) goto L_0x01b5
            r17 = 1
        L_0x01b5:
            r10 = r19
            goto L_0x019b
        L_0x01b8:
            if (r17 != 0) goto L_0x01e5
            java.util.HashSet r10 = new java.util.HashSet     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r10.<init>()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r10.add(r2)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r34 = r11
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r11 = new org.bouncycastle.pkix.jcajce.PKIXPolicyNode     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.util.ArrayList r18 = new java.util.ArrayList     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r18.<init>()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r24 = 0
            r17 = r11
            r19 = r4
            r20 = r10
            r21 = r13
            r22 = r0
            r23 = r2
            r17.<init>(r18, r19, r20, r21, r22, r23, r24)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r13.addChild(r11)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2 = r5[r4]     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2.add(r11)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            goto L_0x01e7
        L_0x01e5:
            r34 = r11
        L_0x01e7:
            r2 = r32
            r10 = r33
            r11 = r34
            goto L_0x0176
        L_0x01ee:
            r2 = r32
            r10 = r33
            goto L_0x0176
        L_0x01f3:
            r32 = r2
            r33 = r10
            r34 = r11
            int r15 = r15 + 1
            goto L_0x0162
        L_0x01fd:
            r33 = r10
            r34 = r11
            goto L_0x0210
        L_0x0202:
            r0 = move-exception
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2.<init>(r9, r14)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r3 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.security.cert.CertPath r4 = r1.certPath     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r3.<init>(r2, r0, r4, r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            throw r3     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x0210:
            int r0 = r4 + -1
            r15 = r28
        L_0x0214:
            if (r0 < 0) goto L_0x0239
            r2 = r5[r0]     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r10 = 0
        L_0x0219:
            int r11 = r2.size()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r10 >= r11) goto L_0x0236
            java.lang.Object r11 = r2.get(r10)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r11 = (org.bouncycastle.pkix.jcajce.PKIXPolicyNode) r11     // Catch:{ CertPathReviewerException -> 0x05f7 }
            boolean r13 = r11.hasChildren()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r13 != 0) goto L_0x0233
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r11 = removePolicyNode(r15, r5, r11)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r15 = r11
            if (r11 != 0) goto L_0x0233
            goto L_0x0236
        L_0x0233:
            int r10 = r10 + 1
            goto L_0x0219
        L_0x0236:
            int r0 = r0 + -1
            goto L_0x0214
        L_0x0239:
            java.util.Set r0 = r6.getCriticalExtensionOIDs()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r0 == 0) goto L_0x025a
            java.lang.String r2 = CERTIFICATE_POLICIES     // Catch:{ CertPathReviewerException -> 0x05f7 }
            boolean r0 = r0.contains(r2)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2 = r5[r4]     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r10 = 0
        L_0x0248:
            int r11 = r2.size()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r10 >= r11) goto L_0x025a
            java.lang.Object r11 = r2.get(r10)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r11 = (org.bouncycastle.pkix.jcajce.PKIXPolicyNode) r11     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r11.setCritical(r0)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            int r10 = r10 + 1
            goto L_0x0248
        L_0x025a:
            r13 = r30
            goto L_0x0269
        L_0x025d:
            r27 = r0
            r29 = r2
            r33 = r10
            r34 = r11
            r28 = r15
            r15 = r28
        L_0x0269:
            if (r3 != 0) goto L_0x026c
            r15 = 0
        L_0x026c:
            if (r8 > 0) goto L_0x027e
            if (r15 == 0) goto L_0x0271
            goto L_0x027e
        L_0x0271:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.lang.String r2 = "CertPathReviewer.noValidPolicyTree"
            r0.<init>(r9, r2)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r2 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2.<init>(r0)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            throw r2     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x027e:
            int r0 = r1.n     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r4 == r0) goto L_0x041d
            java.lang.String r0 = POLICY_MAPPINGS     // Catch:{ AnnotatedException -> 0x040d }
            org.bouncycastle.asn1.ASN1Primitive r0 = getExtensionValue(r6, r0)     // Catch:{ AnnotatedException -> 0x040d }
            if (r0 == 0) goto L_0x02df
            r2 = r0
            org.bouncycastle.asn1.ASN1Sequence r2 = (org.bouncycastle.asn1.ASN1Sequence) r2     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r3 = 0
        L_0x028e:
            int r10 = r2.size()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r3 >= r10) goto L_0x02df
            org.bouncycastle.asn1.ASN1Encodable r10 = r2.getObjectAt(r3)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.asn1.ASN1Sequence r10 = (org.bouncycastle.asn1.ASN1Sequence) r10     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r11 = 0
            org.bouncycastle.asn1.ASN1Encodable r17 = r10.getObjectAt(r11)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r17 = (org.bouncycastle.asn1.ASN1ObjectIdentifier) r17     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r11 = 1
            org.bouncycastle.asn1.ASN1Encodable r10 = r10.getObjectAt(r11)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r10 = (org.bouncycastle.asn1.ASN1ObjectIdentifier) r10     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.lang.String r11 = r17.getId()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            boolean r11 = r7.equals(r11)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r17 = r2
            java.lang.String r2 = "CertPathReviewer.invalidPolicyMapping"
            if (r11 != 0) goto L_0x02d2
            java.lang.String r10 = r10.getId()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            boolean r10 = r7.equals(r10)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r10 != 0) goto L_0x02c5
            int r3 = r3 + 1
            r2 = r17
            goto L_0x028e
        L_0x02c5:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r0.<init>(r9, r2)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r2 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.security.cert.CertPath r3 = r1.certPath     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2.<init>(r0, r3, r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            throw r2     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x02d2:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r0.<init>(r9, r2)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r2 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.security.cert.CertPath r3 = r1.certPath     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2.<init>(r0, r3, r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            throw r2     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x02df:
            if (r0 == 0) goto L_0x037e
            org.bouncycastle.asn1.ASN1Sequence r0 = (org.bouncycastle.asn1.ASN1Sequence) r0     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2.<init>()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r3.<init>()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r10 = 0
        L_0x02ee:
            int r11 = r0.size()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r10 >= r11) goto L_0x0339
            org.bouncycastle.asn1.ASN1Encodable r11 = r0.getObjectAt(r10)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.asn1.ASN1Sequence r11 = (org.bouncycastle.asn1.ASN1Sequence) r11     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r17 = r0
            r0 = 0
            org.bouncycastle.asn1.ASN1Encodable r18 = r11.getObjectAt(r0)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r18 = (org.bouncycastle.asn1.ASN1ObjectIdentifier) r18     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.lang.String r0 = r18.getId()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r18 = r13
            r13 = 1
            org.bouncycastle.asn1.ASN1Encodable r11 = r11.getObjectAt(r13)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.asn1.ASN1ObjectIdentifier r11 = (org.bouncycastle.asn1.ASN1ObjectIdentifier) r11     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.lang.String r11 = r11.getId()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            boolean r13 = r2.containsKey(r0)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r13 != 0) goto L_0x0329
            java.util.HashSet r13 = new java.util.HashSet     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r13.<init>()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r13.add(r11)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2.put(r0, r13)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r3.add(r0)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            goto L_0x0332
        L_0x0329:
            java.lang.Object r0 = r2.get(r0)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.util.Set r0 = (java.util.Set) r0     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r0.add(r11)     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x0332:
            int r10 = r10 + 1
            r0 = r17
            r13 = r18
            goto L_0x02ee
        L_0x0339:
            r18 = r13
            java.util.Iterator r0 = r3.iterator()     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x033f:
            boolean r3 = r0.hasNext()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r3 == 0) goto L_0x0380
            java.lang.Object r3 = r0.next()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r34 <= 0) goto L_0x0373
            prepareNextCertB1(r4, r5, r3, r2, r6)     // Catch:{ AnnotatedException -> 0x0362, CertPathValidatorException -> 0x0353 }
            r10 = r29
            goto L_0x037b
        L_0x0353:
            r0 = move-exception
            r2 = r0
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r0.<init>(r9, r14)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r3 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.security.cert.CertPath r4 = r1.certPath     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r3.<init>(r0, r2, r4, r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            throw r3     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x0362:
            r0 = move-exception
            r2 = r0
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r10 = r29
            r0.<init>(r9, r10)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r3 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.security.cert.CertPath r4 = r1.certPath     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r3.<init>(r0, r2, r4, r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            throw r3     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x0373:
            r10 = r29
            if (r34 > 0) goto L_0x037b
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r15 = prepareNextCertB2(r4, r5, r3, r15)     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x037b:
            r29 = r10
            goto L_0x033f
        L_0x037e:
            r18 = r13
        L_0x0380:
            r10 = r29
            boolean r0 = isSelfIssued(r6)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r0 != 0) goto L_0x039b
            if (r8 == 0) goto L_0x038c
            int r8 = r8 + -1
        L_0x038c:
            if (r34 == 0) goto L_0x0391
            int r11 = r34 + -1
            goto L_0x0393
        L_0x0391:
            r11 = r34
        L_0x0393:
            if (r33 == 0) goto L_0x0398
            int r0 = r33 + -1
            goto L_0x039f
        L_0x0398:
            r0 = r33
            goto L_0x039f
        L_0x039b:
            r0 = r33
            r11 = r34
        L_0x039f:
            java.lang.String r2 = POLICY_CONSTRAINTS     // Catch:{ AnnotatedException -> 0x03fe }
            org.bouncycastle.asn1.ASN1Primitive r2 = getExtensionValue(r6, r2)     // Catch:{ AnnotatedException -> 0x03fe }
            org.bouncycastle.asn1.ASN1Sequence r2 = (org.bouncycastle.asn1.ASN1Sequence) r2     // Catch:{ AnnotatedException -> 0x03fe }
            if (r2 == 0) goto L_0x03dd
            java.util.Enumeration r2 = r2.getObjects()     // Catch:{ AnnotatedException -> 0x03fe }
        L_0x03ad:
            boolean r3 = r2.hasMoreElements()     // Catch:{ AnnotatedException -> 0x03fe }
            if (r3 == 0) goto L_0x03dd
            java.lang.Object r3 = r2.nextElement()     // Catch:{ AnnotatedException -> 0x03fe }
            org.bouncycastle.asn1.ASN1TaggedObject r3 = (org.bouncycastle.asn1.ASN1TaggedObject) r3     // Catch:{ AnnotatedException -> 0x03fe }
            int r4 = r3.getTagNo()     // Catch:{ AnnotatedException -> 0x03fe }
            if (r4 == 0) goto L_0x03d0
            r13 = 1
            if (r4 == r13) goto L_0x03c3
            goto L_0x03ad
        L_0x03c3:
            r4 = 0
            org.bouncycastle.asn1.ASN1Integer r3 = org.bouncycastle.asn1.ASN1Integer.getInstance(r3, r4)     // Catch:{ AnnotatedException -> 0x03fe }
            int r3 = r3.intValueExact()     // Catch:{ AnnotatedException -> 0x03fe }
            if (r3 >= r11) goto L_0x03ad
            r11 = r3
            goto L_0x03ad
        L_0x03d0:
            r4 = 0
            org.bouncycastle.asn1.ASN1Integer r3 = org.bouncycastle.asn1.ASN1Integer.getInstance(r3, r4)     // Catch:{ AnnotatedException -> 0x03fe }
            int r3 = r3.intValueExact()     // Catch:{ AnnotatedException -> 0x03fe }
            if (r3 >= r8) goto L_0x03ad
            r8 = r3
            goto L_0x03ad
        L_0x03dd:
            java.lang.String r2 = INHIBIT_ANY_POLICY     // Catch:{ AnnotatedException -> 0x03ef }
            org.bouncycastle.asn1.ASN1Primitive r2 = getExtensionValue(r6, r2)     // Catch:{ AnnotatedException -> 0x03ef }
            org.bouncycastle.asn1.ASN1Integer r2 = (org.bouncycastle.asn1.ASN1Integer) r2     // Catch:{ AnnotatedException -> 0x03ef }
            if (r2 == 0) goto L_0x0425
            int r2 = r2.intValueExact()     // Catch:{ AnnotatedException -> 0x03ef }
            if (r2 >= r0) goto L_0x0425
            r0 = r2
            goto L_0x0425
        L_0x03ef:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.lang.String r2 = "CertPathReviewer.policyInhibitExtError"
            r0.<init>(r9, r2)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r2 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.security.cert.CertPath r3 = r1.certPath     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2.<init>(r0, r3, r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            throw r2     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x03fe:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2 = r26
            r0.<init>(r9, r2)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r2 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.security.cert.CertPath r3 = r1.certPath     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2.<init>(r0, r3, r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            throw r2     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x040d:
            r0 = move-exception
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.lang.String r3 = "CertPathReviewer.policyMapExtError"
            r2.<init>(r9, r3)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r3 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.security.cert.CertPath r4 = r1.certPath     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r3.<init>(r2, r0, r4, r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            throw r3     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x041d:
            r18 = r13
            r10 = r29
            r0 = r33
            r11 = r34
        L_0x0425:
            int r12 = r12 + -1
            r4 = r6
            r2 = r10
            r13 = r18
            r3 = r25
            r6 = 0
            r10 = r0
            r0 = r27
            goto L_0x0079
        L_0x0433:
            r0 = move-exception
            r10 = r2
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2.<init>(r9, r10)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r3 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.security.cert.CertPath r4 = r1.certPath     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r3.<init>(r2, r0, r4, r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            throw r3     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x0442:
            r27 = r0
            r25 = r3
            r2 = r14
            r28 = r15
            boolean r0 = isSelfIssued(r4)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r0 != 0) goto L_0x0453
            if (r8 <= 0) goto L_0x0453
            int r8 = r8 + -1
        L_0x0453:
            java.lang.String r0 = POLICY_CONSTRAINTS     // Catch:{ AnnotatedException -> 0x05ea }
            org.bouncycastle.asn1.ASN1Primitive r0 = getExtensionValue(r4, r0)     // Catch:{ AnnotatedException -> 0x05ea }
            org.bouncycastle.asn1.ASN1Sequence r0 = (org.bouncycastle.asn1.ASN1Sequence) r0     // Catch:{ AnnotatedException -> 0x05ea }
            if (r0 == 0) goto L_0x0486
            java.util.Enumeration r0 = r0.getObjects()     // Catch:{ AnnotatedException -> 0x05ea }
            r11 = r8
        L_0x0462:
            boolean r3 = r0.hasMoreElements()     // Catch:{ AnnotatedException -> 0x05ea }
            if (r3 == 0) goto L_0x0483
            java.lang.Object r3 = r0.nextElement()     // Catch:{ AnnotatedException -> 0x05ea }
            org.bouncycastle.asn1.ASN1TaggedObject r3 = (org.bouncycastle.asn1.ASN1TaggedObject) r3     // Catch:{ AnnotatedException -> 0x05ea }
            int r4 = r3.getTagNo()     // Catch:{ AnnotatedException -> 0x05ea }
            if (r4 == 0) goto L_0x0476
            r4 = 0
            goto L_0x0462
        L_0x0476:
            r4 = 0
            org.bouncycastle.asn1.ASN1Integer r3 = org.bouncycastle.asn1.ASN1Integer.getInstance(r3, r4)     // Catch:{ AnnotatedException -> 0x05ea }
            int r3 = r3.intValueExact()     // Catch:{ AnnotatedException -> 0x05ea }
            if (r3 != 0) goto L_0x0462
            r11 = 0
            goto L_0x0462
        L_0x0483:
            r4 = 0
            r8 = r11
            goto L_0x0487
        L_0x0486:
            r4 = 0
        L_0x0487:
            java.lang.String r0 = "CertPathReviewer.explicitPolicy"
            if (r28 != 0) goto L_0x04a3
            java.security.cert.PKIXParameters r2 = r1.pkixParams     // Catch:{ CertPathReviewerException -> 0x05f7 }
            boolean r2 = r2.isExplicitPolicyRequired()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r2 != 0) goto L_0x0496
            r15 = 0
            goto L_0x05d8
        L_0x0496:
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2.<init>(r9, r0)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r0 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.security.cert.CertPath r3 = r1.certPath     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r0.<init>(r2, r3, r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            throw r0     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x04a3:
            boolean r2 = isAnyPolicy(r27)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r2 == 0) goto L_0x0544
            java.security.cert.PKIXParameters r2 = r1.pkixParams     // Catch:{ CertPathReviewerException -> 0x05f7 }
            boolean r2 = r2.isExplicitPolicyRequired()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r2 == 0) goto L_0x0540
            boolean r2 = r13.isEmpty()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r2 != 0) goto L_0x0533
            java.util.HashSet r0 = new java.util.HashSet     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r0.<init>()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r3 = r25
            r11 = 0
        L_0x04bf:
            if (r11 >= r3) goto L_0x04f2
            r2 = r5[r11]     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r6 = 0
        L_0x04c4:
            int r10 = r2.size()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r6 >= r10) goto L_0x04ef
            java.lang.Object r10 = r2.get(r6)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r10 = (org.bouncycastle.pkix.jcajce.PKIXPolicyNode) r10     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.lang.String r12 = r10.getValidPolicy()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            boolean r12 = r7.equals(r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r12 == 0) goto L_0x04ec
            java.util.Iterator r10 = r10.getChildren()     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x04de:
            boolean r12 = r10.hasNext()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r12 == 0) goto L_0x04ec
            java.lang.Object r12 = r10.next()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r0.add(r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            goto L_0x04de
        L_0x04ec:
            int r6 = r6 + 1
            goto L_0x04c4
        L_0x04ef:
            int r11 = r11 + 1
            goto L_0x04bf
        L_0x04f2:
            java.util.Iterator r0 = r0.iterator()     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x04f6:
            boolean r2 = r0.hasNext()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r2 == 0) goto L_0x050a
            java.lang.Object r2 = r0.next()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r2 = (org.bouncycastle.pkix.jcajce.PKIXPolicyNode) r2     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.lang.String r2 = r2.getValidPolicy()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r13.contains(r2)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            goto L_0x04f6
        L_0x050a:
            if (r28 == 0) goto L_0x0540
            int r0 = r1.n     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2 = 1
            int r0 = r0 - r2
            r15 = r28
        L_0x0512:
            if (r0 < 0) goto L_0x05d8
            r2 = r5[r0]     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r11 = 0
        L_0x0517:
            int r3 = r2.size()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r11 >= r3) goto L_0x0530
            java.lang.Object r3 = r2.get(r11)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r3 = (org.bouncycastle.pkix.jcajce.PKIXPolicyNode) r3     // Catch:{ CertPathReviewerException -> 0x05f7 }
            boolean r6 = r3.hasChildren()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r6 != 0) goto L_0x052d
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r15 = removePolicyNode(r15, r5, r3)     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x052d:
            int r11 = r11 + 1
            goto L_0x0517
        L_0x0530:
            int r0 = r0 + -1
            goto L_0x0512
        L_0x0533:
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2.<init>(r9, r0)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r0 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.security.cert.CertPath r3 = r1.certPath     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r0.<init>(r2, r3, r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            throw r0     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x0540:
            r15 = r28
            goto L_0x05d8
        L_0x0544:
            r3 = r25
            java.util.HashSet r0 = new java.util.HashSet     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r0.<init>()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r11 = 0
        L_0x054c:
            if (r11 >= r3) goto L_0x058b
            r2 = r5[r11]     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r6 = 0
        L_0x0551:
            int r10 = r2.size()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r6 >= r10) goto L_0x0588
            java.lang.Object r10 = r2.get(r6)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r10 = (org.bouncycastle.pkix.jcajce.PKIXPolicyNode) r10     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.lang.String r12 = r10.getValidPolicy()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            boolean r12 = r7.equals(r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r12 == 0) goto L_0x0585
            java.util.Iterator r10 = r10.getChildren()     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x056b:
            boolean r12 = r10.hasNext()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r12 == 0) goto L_0x0585
            java.lang.Object r12 = r10.next()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r12 = (org.bouncycastle.pkix.jcajce.PKIXPolicyNode) r12     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.lang.String r13 = r12.getValidPolicy()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            boolean r13 = r7.equals(r13)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r13 != 0) goto L_0x056b
            r0.add(r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            goto L_0x056b
        L_0x0585:
            int r6 = r6 + 1
            goto L_0x0551
        L_0x0588:
            int r11 = r11 + 1
            goto L_0x054c
        L_0x058b:
            java.util.Iterator r0 = r0.iterator()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r15 = r28
        L_0x0591:
            boolean r2 = r0.hasNext()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r2 == 0) goto L_0x05b1
            java.lang.Object r2 = r0.next()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r2 = (org.bouncycastle.pkix.jcajce.PKIXPolicyNode) r2     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.lang.String r3 = r2.getValidPolicy()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r6 = r27
            boolean r3 = r6.contains(r3)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r3 != 0) goto L_0x05ae
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r2 = removePolicyNode(r15, r5, r2)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r15 = r2
        L_0x05ae:
            r27 = r6
            goto L_0x0591
        L_0x05b1:
            if (r15 == 0) goto L_0x05d8
            int r0 = r1.n     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2 = 1
            int r0 = r0 - r2
        L_0x05b7:
            if (r0 < 0) goto L_0x05d8
            r2 = r5[r0]     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r11 = 0
        L_0x05bc:
            int r3 = r2.size()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r11 >= r3) goto L_0x05d5
            java.lang.Object r3 = r2.get(r11)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r3 = (org.bouncycastle.pkix.jcajce.PKIXPolicyNode) r3     // Catch:{ CertPathReviewerException -> 0x05f7 }
            boolean r6 = r3.hasChildren()     // Catch:{ CertPathReviewerException -> 0x05f7 }
            if (r6 != 0) goto L_0x05d2
            org.bouncycastle.pkix.jcajce.PKIXPolicyNode r15 = removePolicyNode(r15, r5, r3)     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x05d2:
            int r11 = r11 + 1
            goto L_0x05bc
        L_0x05d5:
            int r0 = r0 + -1
            goto L_0x05b7
        L_0x05d8:
            if (r8 > 0) goto L_0x0603
            if (r15 == 0) goto L_0x05dd
            goto L_0x0603
        L_0x05dd:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.lang.String r2 = "CertPathReviewer.invalidPolicy"
            r0.<init>(r9, r2)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r2 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2.<init>(r0)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            throw r2     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x05ea:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r0.<init>(r9, r2)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r2 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x05f7 }
            java.security.cert.CertPath r3 = r1.certPath     // Catch:{ CertPathReviewerException -> 0x05f7 }
            r2.<init>(r0, r3, r12)     // Catch:{ CertPathReviewerException -> 0x05f7 }
            throw r2     // Catch:{ CertPathReviewerException -> 0x05f7 }
        L_0x05f7:
            r0 = move-exception
            org.bouncycastle.pkix.util.ErrorBundle r2 = r0.getErrorMessage()
            int r0 = r0.getIndex()
            r1.addError(r2, r0)
        L_0x0603:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pkix.jcajce.PKIXCertPathReviewer.checkPolicy():void");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x00bc */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x02cb A[LOOP:1: B:103:0x02c5->B:105:0x02cb, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x02ee A[LOOP:2: B:107:0x02e8->B:109:0x02ee, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0339  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x034d  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x036c  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0373  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x03d1  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0289 A[SYNTHETIC, Splitter:B:88:0x0289] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x02a9 A[Catch:{ AnnotatedException -> 0x02ae }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkSignatures() {
        /*
            r21 = this;
            r10 = r21
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            org.bouncycastle.pkix.util.filter.TrustedInput r1 = new org.bouncycastle.pkix.util.filter.TrustedInput
            java.util.Date r2 = r10.validDate
            r1.<init>(r2)
            org.bouncycastle.pkix.util.filter.TrustedInput r2 = new org.bouncycastle.pkix.util.filter.TrustedInput
            java.util.Date r3 = r10.currentDate
            r2.<init>(r3)
            r11 = 2
            java.lang.Object[] r3 = new java.lang.Object[r11]
            r12 = 0
            r3[r12] = r1
            r13 = 1
            r3[r13] = r2
            java.lang.String r14 = "org.bouncycastle.pkix.CertPathReviewerMessages"
            java.lang.String r1 = "CertPathReviewer.certPathValidDate"
            r0.<init>((java.lang.String) r14, (java.lang.String) r1, (java.lang.Object[]) r3)
            r10.addNotification(r0)
            java.util.List r0 = r10.certs     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            int r1 = r0.size()     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            int r1 = r1 - r13
            java.lang.Object r0 = r0.get(r1)     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            java.security.cert.PKIXParameters r1 = r10.pkixParams     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            java.util.Set r1 = r1.getTrustAnchors()     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            java.util.Collection r1 = r10.getTrustAnchors(r0, r1)     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            int r2 = r1.size()     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            if (r2 <= r13) goto L_0x0064
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            java.lang.String r3 = "CertPathReviewer.conflictingTrustAnchors"
            int r1 = r1.size()     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            java.lang.Integer r1 = org.bouncycastle.util.Integers.valueOf(r1)     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            org.bouncycastle.pkix.util.filter.UntrustedInput r4 = new org.bouncycastle.pkix.util.filter.UntrustedInput     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            javax.security.auth.x500.X500Principal r0 = r0.getIssuerX500Principal()     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            r4.<init>(r0)     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            java.lang.Object[] r0 = new java.lang.Object[r11]     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            r0[r12] = r1     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            r0[r13] = r4     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            r2.<init>((java.lang.String) r14, (java.lang.String) r3, (java.lang.Object[]) r0)     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            r10.addError(r2)     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            goto L_0x0091
        L_0x0064:
            boolean r2 = r1.isEmpty()     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            if (r2 == 0) goto L_0x0093
            org.bouncycastle.pkix.util.ErrorBundle r1 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            java.lang.String r2 = "CertPathReviewer.noTrustAnchorFound"
            org.bouncycastle.pkix.util.filter.UntrustedInput r3 = new org.bouncycastle.pkix.util.filter.UntrustedInput     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            javax.security.auth.x500.X500Principal r0 = r0.getIssuerX500Principal()     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            r3.<init>(r0)     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            java.security.cert.PKIXParameters r0 = r10.pkixParams     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            java.util.Set r0 = r0.getTrustAnchors()     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            int r0 = r0.size()     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            java.lang.Integer r0 = org.bouncycastle.util.Integers.valueOf(r0)     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            java.lang.Object[] r4 = new java.lang.Object[r11]     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            r4[r12] = r3     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            r4[r13] = r0     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            r1.<init>((java.lang.String) r14, (java.lang.String) r2, (java.lang.Object[]) r4)     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            r10.addError(r1)     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
        L_0x0091:
            r1 = 0
            goto L_0x00f5
        L_0x0093:
            java.util.Iterator r1 = r1.iterator()     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            java.lang.Object r1 = r1.next()     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            java.security.cert.TrustAnchor r1 = (java.security.cert.TrustAnchor) r1     // Catch:{ CertPathReviewerException -> 0x00ec, all -> 0x00cb }
            java.security.cert.X509Certificate r2 = r1.getTrustedCert()     // Catch:{ CertPathReviewerException -> 0x00c9, all -> 0x00c7 }
            if (r2 == 0) goto L_0x00ac
            java.security.cert.X509Certificate r2 = r1.getTrustedCert()     // Catch:{ CertPathReviewerException -> 0x00c9, all -> 0x00c7 }
            java.security.PublicKey r2 = r2.getPublicKey()     // Catch:{ CertPathReviewerException -> 0x00c9, all -> 0x00c7 }
            goto L_0x00b0
        L_0x00ac:
            java.security.PublicKey r2 = r1.getCAPublicKey()     // Catch:{ CertPathReviewerException -> 0x00c9, all -> 0x00c7 }
        L_0x00b0:
            java.security.cert.PKIXParameters r3 = r10.pkixParams     // Catch:{ SignatureException -> 0x00bc, Exception -> 0x00ba }
            java.lang.String r3 = r3.getSigProvider()     // Catch:{ SignatureException -> 0x00bc, Exception -> 0x00ba }
            org.bouncycastle.pkix.jcajce.CertPathValidatorUtilities.verifyX509Certificate(r0, r2, r3)     // Catch:{ SignatureException -> 0x00bc, Exception -> 0x00ba }
            goto L_0x00f5
        L_0x00ba:
            goto L_0x00f5
        L_0x00bc:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00c9, all -> 0x00c7 }
            java.lang.String r2 = "CertPathReviewer.trustButInvalidCert"
            r0.<init>(r14, r2)     // Catch:{ CertPathReviewerException -> 0x00c9, all -> 0x00c7 }
            r10.addError(r0)     // Catch:{ CertPathReviewerException -> 0x00c9, all -> 0x00c7 }
            goto L_0x00f5
        L_0x00c7:
            r0 = move-exception
            goto L_0x00cd
        L_0x00c9:
            r0 = move-exception
            goto L_0x00ee
        L_0x00cb:
            r0 = move-exception
            r1 = 0
        L_0x00cd:
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle
            org.bouncycastle.pkix.util.filter.UntrustedInput r3 = new org.bouncycastle.pkix.util.filter.UntrustedInput
            java.lang.String r4 = r0.getMessage()
            r3.<init>(r4)
            org.bouncycastle.pkix.util.filter.UntrustedInput r4 = new org.bouncycastle.pkix.util.filter.UntrustedInput
            r4.<init>(r0)
            java.lang.Object[] r0 = new java.lang.Object[r11]
            r0[r12] = r3
            r0[r13] = r4
            java.lang.String r3 = "CertPathReviewer.unknown"
            r2.<init>((java.lang.String) r14, (java.lang.String) r3, (java.lang.Object[]) r0)
            r10.addError(r2)
            goto L_0x00f5
        L_0x00ec:
            r0 = move-exception
            r1 = 0
        L_0x00ee:
            org.bouncycastle.pkix.util.ErrorBundle r0 = r0.getErrorMessage()
            r10.addError(r0)
        L_0x00f5:
            r9 = r1
            r8 = 5
            if (r9 == 0) goto L_0x0140
            java.security.cert.X509Certificate r0 = r9.getTrustedCert()
            if (r0 == 0) goto L_0x0104
            javax.security.auth.x500.X500Principal r1 = getSubjectPrincipal(r0)     // Catch:{ IllegalArgumentException -> 0x010e }
            goto L_0x0126
        L_0x0104:
            javax.security.auth.x500.X500Principal r1 = new javax.security.auth.x500.X500Principal     // Catch:{ IllegalArgumentException -> 0x010e }
            java.lang.String r2 = r9.getCAName()     // Catch:{ IllegalArgumentException -> 0x010e }
            r1.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x010e }
            goto L_0x0126
        L_0x010e:
            org.bouncycastle.pkix.util.ErrorBundle r1 = new org.bouncycastle.pkix.util.ErrorBundle
            org.bouncycastle.pkix.util.filter.UntrustedInput r2 = new org.bouncycastle.pkix.util.filter.UntrustedInput
            java.lang.String r3 = r9.getCAName()
            r2.<init>(r3)
            java.lang.Object[] r3 = new java.lang.Object[r13]
            r3[r12] = r2
            java.lang.String r2 = "CertPathReviewer.trustDNInvalid"
            r1.<init>((java.lang.String) r14, (java.lang.String) r2, (java.lang.Object[]) r3)
            r10.addError(r1)
            r1 = 0
        L_0x0126:
            if (r0 == 0) goto L_0x0141
            boolean[] r0 = r0.getKeyUsage()
            if (r0 == 0) goto L_0x0141
            int r2 = r0.length
            if (r2 <= r8) goto L_0x0135
            boolean r0 = r0[r8]
            if (r0 != 0) goto L_0x0141
        L_0x0135:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r2 = "CertPathReviewer.trustKeyUsage"
            r0.<init>(r14, r2)
            r10.addNotification(r0)
            goto L_0x0141
        L_0x0140:
            r1 = 0
        L_0x0141:
            if (r9 == 0) goto L_0x0168
            java.security.cert.X509Certificate r0 = r9.getTrustedCert()
            if (r0 == 0) goto L_0x014e
            java.security.PublicKey r2 = r0.getPublicKey()
            goto L_0x0152
        L_0x014e:
            java.security.PublicKey r2 = r9.getCAPublicKey()
        L_0x0152:
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r3 = getAlgorithmIdentifier(r2)     // Catch:{ CertPathValidatorException -> 0x015d }
            r3.getAlgorithm()     // Catch:{ CertPathValidatorException -> 0x015d }
            r3.getParameters()     // Catch:{ CertPathValidatorException -> 0x015d }
            goto L_0x016a
        L_0x015d:
            org.bouncycastle.pkix.util.ErrorBundle r3 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r4 = "CertPathReviewer.trustPubKeyError"
            r3.<init>(r14, r4)
            r10.addError(r3)
            goto L_0x016a
        L_0x0168:
            r0 = 0
            r2 = 0
        L_0x016a:
            java.util.List r3 = r10.certs
            int r3 = r3.size()
            int r3 = r3 - r13
            r16 = r0
            r7 = r1
            r6 = r2
            r5 = r3
        L_0x0176:
            if (r5 < 0) goto L_0x03ff
            int r0 = r10.n
            int r4 = r0 - r5
            java.util.List r0 = r10.certs
            java.lang.Object r0 = r0.get(r5)
            r3 = r0
            java.security.cert.X509Certificate r3 = (java.security.cert.X509Certificate) r3
            java.lang.String r1 = "CertPathReviewer.signatureNotVerified"
            r2 = 3
            if (r6 == 0) goto L_0x01b4
            java.security.cert.PKIXParameters r0 = r10.pkixParams     // Catch:{ GeneralSecurityException -> 0x0195 }
            java.lang.String r0 = r0.getSigProvider()     // Catch:{ GeneralSecurityException -> 0x0195 }
            org.bouncycastle.pkix.jcajce.CertPathValidatorUtilities.verifyX509Certificate(r3, r6, r0)     // Catch:{ GeneralSecurityException -> 0x0195 }
            goto L_0x024f
        L_0x0195:
            r0 = move-exception
            org.bouncycastle.pkix.util.ErrorBundle r15 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r17 = r0.getMessage()
            java.lang.Class r18 = r0.getClass()
            java.lang.String r18 = r18.getName()
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r12] = r17
            r2[r13] = r0
            r2[r11] = r18
            r15.<init>((java.lang.String) r14, (java.lang.String) r1, (java.lang.Object[]) r2)
        L_0x01af:
            r10.addError(r15, r5)
            goto L_0x024f
        L_0x01b4:
            boolean r0 = isSelfIssued(r3)
            if (r0 == 0) goto L_0x01ee
            java.security.PublicKey r0 = r3.getPublicKey()     // Catch:{ GeneralSecurityException -> 0x01d3 }
            java.security.cert.PKIXParameters r15 = r10.pkixParams     // Catch:{ GeneralSecurityException -> 0x01d3 }
            java.lang.String r15 = r15.getSigProvider()     // Catch:{ GeneralSecurityException -> 0x01d3 }
            org.bouncycastle.pkix.jcajce.CertPathValidatorUtilities.verifyX509Certificate(r3, r0, r15)     // Catch:{ GeneralSecurityException -> 0x01d3 }
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ GeneralSecurityException -> 0x01d3 }
            java.lang.String r15 = "CertPathReviewer.rootKeyIsValidButNotATrustAnchor"
            r0.<init>(r14, r15)     // Catch:{ GeneralSecurityException -> 0x01d3 }
            r10.addError(r0, r5)     // Catch:{ GeneralSecurityException -> 0x01d3 }
            goto L_0x024f
        L_0x01d3:
            r0 = move-exception
            org.bouncycastle.pkix.util.ErrorBundle r15 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r17 = r0.getMessage()
            java.lang.Class r18 = r0.getClass()
            java.lang.String r18 = r18.getName()
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r12] = r17
            r2[r13] = r0
            r2[r11] = r18
            r15.<init>((java.lang.String) r14, (java.lang.String) r1, (java.lang.Object[]) r2)
            goto L_0x01af
        L_0x01ee:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r1 = "CertPathReviewer.NoIssuerPublicKey"
            r0.<init>(r14, r1)
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = org.bouncycastle.asn1.x509.Extension.authorityKeyIdentifier
            java.lang.String r1 = r1.getId()
            byte[] r1 = r3.getExtensionValue(r1)
            if (r1 == 0) goto L_0x024c
            org.bouncycastle.asn1.ASN1OctetString r1 = org.bouncycastle.asn1.DEROctetString.getInstance(r1)
            byte[] r1 = r1.getOctets()
            org.bouncycastle.asn1.x509.AuthorityKeyIdentifier r1 = org.bouncycastle.asn1.x509.AuthorityKeyIdentifier.getInstance(r1)
            org.bouncycastle.asn1.x509.GeneralNames r15 = r1.getAuthorityCertIssuer()
            if (r15 == 0) goto L_0x024c
            org.bouncycastle.asn1.x509.GeneralName[] r15 = r15.getNames()
            r15 = r15[r12]
            java.math.BigInteger r1 = r1.getAuthorityCertSerialNumber()
            if (r1 == 0) goto L_0x024c
            org.bouncycastle.pkix.util.LocaleString r8 = new org.bouncycastle.pkix.util.LocaleString
            java.lang.String r2 = "missingIssuer"
            r8.<init>(r14, r2)
            org.bouncycastle.pkix.util.LocaleString r2 = new org.bouncycastle.pkix.util.LocaleString
            java.lang.String r11 = "missingSerial"
            r2.<init>(r14, r11)
            r11 = 7
            java.lang.Object[] r11 = new java.lang.Object[r11]
            r11[r12] = r8
            java.lang.String r8 = " \""
            r11[r13] = r8
            r8 = 2
            r11[r8] = r15
            java.lang.String r8 = "\" "
            r15 = 3
            r11[r15] = r8
            r8 = 4
            r11[r8] = r2
            java.lang.String r2 = " "
            r8 = 5
            r11[r8] = r2
            r2 = 6
            r11[r2] = r1
            r0.setExtraArguments(r11)
        L_0x024c:
            r10.addError(r0, r5)
        L_0x024f:
            java.util.Date r0 = r10.validDate     // Catch:{ CertificateNotYetValidException -> 0x026a, CertificateExpiredException -> 0x0255 }
            r3.checkValidity(r0)     // Catch:{ CertificateNotYetValidException -> 0x026a, CertificateExpiredException -> 0x0255 }
            goto L_0x0281
        L_0x0255:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            org.bouncycastle.pkix.util.filter.TrustedInput r1 = new org.bouncycastle.pkix.util.filter.TrustedInput
            java.util.Date r2 = r3.getNotAfter()
            r1.<init>(r2)
            java.lang.Object[] r2 = new java.lang.Object[r13]
            r2[r12] = r1
            java.lang.String r1 = "CertPathReviewer.certificateExpired"
            r0.<init>((java.lang.String) r14, (java.lang.String) r1, (java.lang.Object[]) r2)
            goto L_0x027e
        L_0x026a:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            org.bouncycastle.pkix.util.filter.TrustedInput r1 = new org.bouncycastle.pkix.util.filter.TrustedInput
            java.util.Date r2 = r3.getNotBefore()
            r1.<init>(r2)
            java.lang.Object[] r2 = new java.lang.Object[r13]
            r2[r12] = r1
            java.lang.String r1 = "CertPathReviewer.certificateNotYetValid"
            r0.<init>((java.lang.String) r14, (java.lang.String) r1, (java.lang.Object[]) r2)
        L_0x027e:
            r10.addError(r0, r5)
        L_0x0281:
            java.security.cert.PKIXParameters r0 = r10.pkixParams
            boolean r0 = r0.isRevocationEnabled()
            if (r0 == 0) goto L_0x0339
            java.lang.String r0 = CRL_DIST_POINTS     // Catch:{ AnnotatedException -> 0x0296 }
            org.bouncycastle.asn1.ASN1Primitive r0 = getExtensionValue(r3, r0)     // Catch:{ AnnotatedException -> 0x0296 }
            if (r0 == 0) goto L_0x02a0
            org.bouncycastle.asn1.x509.CRLDistPoint r0 = org.bouncycastle.asn1.x509.CRLDistPoint.getInstance(r0)     // Catch:{ AnnotatedException -> 0x0296 }
            goto L_0x02a1
        L_0x0296:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r1 = "CertPathReviewer.crlDistPtExtError"
            r0.<init>(r14, r1)
            r10.addError(r0, r5)
        L_0x02a0:
            r0 = 0
        L_0x02a1:
            java.lang.String r1 = AUTH_INFO_ACCESS     // Catch:{ AnnotatedException -> 0x02ae }
            org.bouncycastle.asn1.ASN1Primitive r1 = getExtensionValue(r3, r1)     // Catch:{ AnnotatedException -> 0x02ae }
            if (r1 == 0) goto L_0x02b8
            org.bouncycastle.asn1.x509.AuthorityInformationAccess r1 = org.bouncycastle.asn1.x509.AuthorityInformationAccess.getInstance(r1)     // Catch:{ AnnotatedException -> 0x02ae }
            goto L_0x02b9
        L_0x02ae:
            org.bouncycastle.pkix.util.ErrorBundle r1 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r2 = "CertPathReviewer.crlAuthInfoAccError"
            r1.<init>(r14, r2)
            r10.addError(r1, r5)
        L_0x02b8:
            r1 = 0
        L_0x02b9:
            java.util.Vector r0 = r10.getCRLDistUrls(r0)
            java.util.Vector r11 = r10.getOCSPUrls(r1)
            java.util.Iterator r1 = r0.iterator()
        L_0x02c5:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x02e4
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle
            org.bouncycastle.pkix.util.filter.UntrustedUrlInput r15 = new org.bouncycastle.pkix.util.filter.UntrustedUrlInput
            java.lang.Object r8 = r1.next()
            r15.<init>(r8)
            java.lang.Object[] r8 = new java.lang.Object[r13]
            r8[r12] = r15
            java.lang.String r15 = "CertPathReviewer.crlDistPoint"
            r2.<init>((java.lang.String) r14, (java.lang.String) r15, (java.lang.Object[]) r8)
            r10.addNotification(r2, r5)
            r8 = 5
            goto L_0x02c5
        L_0x02e4:
            java.util.Iterator r1 = r11.iterator()
        L_0x02e8:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0306
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle
            org.bouncycastle.pkix.util.filter.UntrustedUrlInput r8 = new org.bouncycastle.pkix.util.filter.UntrustedUrlInput
            java.lang.Object r15 = r1.next()
            r8.<init>(r15)
            java.lang.Object[] r15 = new java.lang.Object[r13]
            r15[r12] = r8
            java.lang.String r8 = "CertPathReviewer.ocspLocation"
            r2.<init>((java.lang.String) r14, (java.lang.String) r8, (java.lang.Object[]) r15)
            r10.addNotification(r2, r5)
            goto L_0x02e8
        L_0x0306:
            java.security.cert.PKIXParameters r2 = r10.pkixParams     // Catch:{ CertPathReviewerException -> 0x0325 }
            java.util.Date r8 = r10.validDate     // Catch:{ CertPathReviewerException -> 0x0325 }
            r1 = r21
            r15 = r3
            r19 = r4
            r4 = r8
            r8 = r5
            r5 = r16
            r16 = r6
            r13 = r7
            r7 = r0
            r20 = r8
            r8 = r11
            r11 = r9
            r9 = r20
            r1.checkRevocation(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ CertPathReviewerException -> 0x0323 }
            r3 = r20
            goto L_0x0341
        L_0x0323:
            r0 = move-exception
            goto L_0x032f
        L_0x0325:
            r0 = move-exception
            r15 = r3
            r19 = r4
            r20 = r5
            r16 = r6
            r13 = r7
            r11 = r9
        L_0x032f:
            org.bouncycastle.pkix.util.ErrorBundle r0 = r0.getErrorMessage()
            r3 = r20
            r10.addError(r0, r3)
            goto L_0x0341
        L_0x0339:
            r15 = r3
            r19 = r4
            r3 = r5
            r16 = r6
            r13 = r7
            r11 = r9
        L_0x0341:
            if (r13 == 0) goto L_0x036c
            javax.security.auth.x500.X500Principal r0 = r15.getIssuerX500Principal()
            boolean r0 = r0.equals(r13)
            if (r0 != 0) goto L_0x036c
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r1 = r13.getName()
            javax.security.auth.x500.X500Principal r2 = r15.getIssuerX500Principal()
            java.lang.String r2 = r2.getName()
            r4 = 2
            java.lang.Object[] r5 = new java.lang.Object[r4]
            r5[r12] = r1
            r1 = 1
            r5[r1] = r2
            java.lang.String r1 = "CertPathReviewer.certWrongIssuer"
            r0.<init>((java.lang.String) r14, (java.lang.String) r1, (java.lang.Object[]) r5)
            r10.addError(r0, r3)
            goto L_0x036d
        L_0x036c:
            r4 = 2
        L_0x036d:
            int r0 = r10.n
            r1 = r19
            if (r1 == r0) goto L_0x03d1
            java.lang.String r0 = "CertPathReviewer.noCACert"
            if (r15 == 0) goto L_0x0387
            int r1 = r15.getVersion()
            r2 = 1
            if (r1 != r2) goto L_0x0388
            org.bouncycastle.pkix.util.ErrorBundle r1 = new org.bouncycastle.pkix.util.ErrorBundle
            r1.<init>(r14, r0)
            r10.addError(r1, r3)
            goto L_0x0388
        L_0x0387:
            r2 = 1
        L_0x0388:
            java.lang.String r1 = BASIC_CONSTRAINTS     // Catch:{ AnnotatedException -> 0x03ae }
            org.bouncycastle.asn1.ASN1Primitive r1 = getExtensionValue(r15, r1)     // Catch:{ AnnotatedException -> 0x03ae }
            org.bouncycastle.asn1.x509.BasicConstraints r1 = org.bouncycastle.asn1.x509.BasicConstraints.getInstance(r1)     // Catch:{ AnnotatedException -> 0x03ae }
            if (r1 == 0) goto L_0x03a3
            boolean r1 = r1.isCA()     // Catch:{ AnnotatedException -> 0x03ae }
            if (r1 != 0) goto L_0x03b8
            org.bouncycastle.pkix.util.ErrorBundle r1 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ AnnotatedException -> 0x03ae }
            r1.<init>(r14, r0)     // Catch:{ AnnotatedException -> 0x03ae }
            r10.addError(r1, r3)     // Catch:{ AnnotatedException -> 0x03ae }
            goto L_0x03b8
        L_0x03a3:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ AnnotatedException -> 0x03ae }
            java.lang.String r1 = "CertPathReviewer.noBasicConstraints"
            r0.<init>(r14, r1)     // Catch:{ AnnotatedException -> 0x03ae }
            r10.addError(r0, r3)     // Catch:{ AnnotatedException -> 0x03ae }
            goto L_0x03b8
        L_0x03ae:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r1 = "CertPathReviewer.errorProcesingBC"
            r0.<init>(r14, r1)
            r10.addError(r0, r3)
        L_0x03b8:
            boolean[] r0 = r15.getKeyUsage()
            if (r0 == 0) goto L_0x03d2
            int r1 = r0.length
            r5 = 5
            if (r1 <= r5) goto L_0x03c6
            boolean r0 = r0[r5]
            if (r0 != 0) goto L_0x03d3
        L_0x03c6:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r1 = "CertPathReviewer.noCertSign"
            r0.<init>(r14, r1)
            r10.addError(r0, r3)
            goto L_0x03d3
        L_0x03d1:
            r2 = 1
        L_0x03d2:
            r5 = 5
        L_0x03d3:
            javax.security.auth.x500.X500Principal r7 = r15.getSubjectX500Principal()
            java.util.List r0 = r10.certs     // Catch:{ CertPathValidatorException -> 0x03e8 }
            java.security.PublicKey r6 = getNextWorkingKey(r0, r3)     // Catch:{ CertPathValidatorException -> 0x03e8 }
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r0 = getAlgorithmIdentifier(r6)     // Catch:{ CertPathValidatorException -> 0x03ea }
            r0.getAlgorithm()     // Catch:{ CertPathValidatorException -> 0x03ea }
            r0.getParameters()     // Catch:{ CertPathValidatorException -> 0x03ea }
            goto L_0x03f4
        L_0x03e8:
            r6 = r16
        L_0x03ea:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r1 = "CertPathReviewer.pubKeyError"
            r0.<init>(r14, r1)
            r10.addError(r0, r3)
        L_0x03f4:
            int r0 = r3 + -1
            r5 = r0
            r9 = r11
            r16 = r15
            r8 = 5
            r11 = 2
            r13 = 1
            goto L_0x0176
        L_0x03ff:
            r16 = r6
            r11 = r9
            r10.trustAnchor = r11
            r2 = r16
            r10.subjectPublicKey = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pkix.jcajce.PKIXCertPathReviewer.checkSignatures():void");
    }

    private X509CRL getCRL(String str) throws CertPathReviewerException {
        try {
            URL url = new URL(str);
            if (!url.getProtocol().equals("http")) {
                if (!url.getProtocol().equals("https")) {
                    return null;
                }
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                return (X509CRL) CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME).generateCRL(httpURLConnection.getInputStream());
            }
            throw new Exception(httpURLConnection.getResponseMessage());
        } catch (Exception e) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.loadCrlDistPointError", new Object[]{new UntrustedInput(str), e.getMessage(), e, e.getClass().getName()}));
        }
    }

    private boolean processQcStatements(X509Certificate x509Certificate, int i) {
        ErrorBundle errorBundle;
        ErrorBundle errorBundle2;
        int i2 = i;
        try {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) getExtensionValue(x509Certificate, QC_STATEMENT);
            boolean z = false;
            for (int i3 = 0; i3 < aSN1Sequence.size(); i3++) {
                QCStatement instance = QCStatement.getInstance(aSN1Sequence.getObjectAt(i3));
                if (QCStatement.id_etsi_qcs_QcCompliance.equals((ASN1Primitive) instance.getStatementId())) {
                    errorBundle2 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcEuCompliance");
                } else {
                    if (!QCStatement.id_qcs_pkixQCSyntax_v1.equals((ASN1Primitive) instance.getStatementId())) {
                        if (QCStatement.id_etsi_qcs_QcSSCD.equals((ASN1Primitive) instance.getStatementId())) {
                            errorBundle2 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcSSCD");
                        } else if (QCStatement.id_etsi_qcs_LimiteValue.equals((ASN1Primitive) instance.getStatementId())) {
                            MonetaryValue instance2 = MonetaryValue.getInstance(instance.getStatementInfo());
                            instance2.getCurrency();
                            double doubleValue = instance2.getAmount().doubleValue() * Math.pow(10.0d, instance2.getExponent().doubleValue());
                            if (instance2.getCurrency().isAlphabetic()) {
                                errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueAlpha", new Object[]{instance2.getCurrency().getAlphabetic(), new TrustedInput(new Double(doubleValue)), instance2});
                            } else {
                                errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueNum", new Object[]{Integers.valueOf(instance2.getCurrency().getNumeric()), new TrustedInput(new Double(doubleValue)), instance2});
                            }
                            addNotification(errorBundle, i2);
                        } else {
                            addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcUnknownStatement", new Object[]{instance.getStatementId(), new UntrustedInput(instance)}), i2);
                            z = true;
                        }
                    }
                }
                addNotification(errorBundle2, i2);
            }
            return !z;
        } catch (AnnotatedException unused) {
            addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcStatementExtError"), i2);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void addError(ErrorBundle errorBundle) {
        this.errors[0].add(errorBundle);
    }

    /* access modifiers changed from: protected */
    public void addError(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.n) {
            throw new IndexOutOfBoundsException();
        }
        this.errors[i + 1].add(errorBundle);
    }

    /* access modifiers changed from: protected */
    public void addNotification(ErrorBundle errorBundle) {
        this.notifications[0].add(errorBundle);
    }

    /* access modifiers changed from: protected */
    public void addNotification(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.n) {
            throw new IndexOutOfBoundsException();
        }
        this.notifications[i + 1].add(errorBundle);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x025a  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0270  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x028d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkCRLs(java.security.cert.PKIXParameters r22, java.security.cert.X509Certificate r23, java.util.Date r24, java.security.cert.X509Certificate r25, java.security.PublicKey r26, java.util.Vector r27, int r28) throws org.bouncycastle.pkix.jcajce.CertPathReviewerException {
        /*
            r21 = this;
            r1 = r21
            r2 = r22
            r3 = r23
            r4 = r24
            r5 = r26
            r6 = r28
            java.lang.String r7 = "CertPathReviewer.distrPtExtError"
            java.lang.String r8 = "CertPathReviewer.crlExtractionError"
            java.lang.String r9 = "CertPathReviewer.crlIssuerException"
            java.lang.String r10 = "org.bouncycastle.pkix.CertPathReviewerMessages"
            org.bouncycastle.pkix.jcajce.X509CRLStoreSelector r0 = new org.bouncycastle.pkix.jcajce.X509CRLStoreSelector
            r0.<init>()
            javax.security.auth.x500.X500Principal r11 = getEncodedIssuerPrincipal(r23)     // Catch:{ IOException -> 0x0426 }
            byte[] r11 = r11.getEncoded()     // Catch:{ IOException -> 0x0426 }
            r0.addIssuerName(r11)     // Catch:{ IOException -> 0x0426 }
            r0.setCertificateChecking(r3)
            java.util.Set r15 = org.bouncycastle.pkix.jcajce.PKIXCRLUtil.findCRLs((org.bouncycastle.pkix.jcajce.X509CRLStoreSelector) r0, (java.security.cert.PKIXParameters) r2)     // Catch:{ AnnotatedException -> 0x0088 }
            java.util.Iterator r16 = r15.iterator()     // Catch:{ AnnotatedException -> 0x0088 }
            boolean r15 = r15.isEmpty()     // Catch:{ AnnotatedException -> 0x0088 }
            if (r15 == 0) goto L_0x00be
            org.bouncycastle.pkix.jcajce.X509CRLStoreSelector r15 = new org.bouncycastle.pkix.jcajce.X509CRLStoreSelector     // Catch:{ AnnotatedException -> 0x0088 }
            r15.<init>()     // Catch:{ AnnotatedException -> 0x0088 }
            java.util.Set r15 = org.bouncycastle.pkix.jcajce.PKIXCRLUtil.findCRLs((org.bouncycastle.pkix.jcajce.X509CRLStoreSelector) r15, (java.security.cert.PKIXParameters) r2)     // Catch:{ AnnotatedException -> 0x0088 }
            java.util.Iterator r15 = r15.iterator()     // Catch:{ AnnotatedException -> 0x0088 }
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ AnnotatedException -> 0x0088 }
            r12.<init>()     // Catch:{ AnnotatedException -> 0x0088 }
        L_0x0047:
            boolean r17 = r15.hasNext()     // Catch:{ AnnotatedException -> 0x0088 }
            if (r17 == 0) goto L_0x005b
            java.lang.Object r17 = r15.next()     // Catch:{ AnnotatedException -> 0x0088 }
            java.security.cert.X509CRL r17 = (java.security.cert.X509CRL) r17     // Catch:{ AnnotatedException -> 0x0088 }
            javax.security.auth.x500.X500Principal r14 = r17.getIssuerX500Principal()     // Catch:{ AnnotatedException -> 0x0088 }
            r12.add(r14)     // Catch:{ AnnotatedException -> 0x0088 }
            goto L_0x0047
        L_0x005b:
            int r14 = r12.size()     // Catch:{ AnnotatedException -> 0x0088 }
            org.bouncycastle.pkix.util.ErrorBundle r15 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ AnnotatedException -> 0x0088 }
            java.lang.String r13 = "CertPathReviewer.noCrlInCertstore"
            org.bouncycastle.pkix.util.filter.UntrustedInput r11 = new org.bouncycastle.pkix.util.filter.UntrustedInput     // Catch:{ AnnotatedException -> 0x0088 }
            java.util.Collection r0 = r0.getIssuerNames()     // Catch:{ AnnotatedException -> 0x0088 }
            r11.<init>(r0)     // Catch:{ AnnotatedException -> 0x0088 }
            org.bouncycastle.pkix.util.filter.UntrustedInput r0 = new org.bouncycastle.pkix.util.filter.UntrustedInput     // Catch:{ AnnotatedException -> 0x0088 }
            r0.<init>(r12)     // Catch:{ AnnotatedException -> 0x0088 }
            java.lang.Integer r12 = org.bouncycastle.util.Integers.valueOf(r14)     // Catch:{ AnnotatedException -> 0x0088 }
            r14 = 3
            java.lang.Object[] r3 = new java.lang.Object[r14]     // Catch:{ AnnotatedException -> 0x0088 }
            r14 = 0
            r3[r14] = r11     // Catch:{ AnnotatedException -> 0x0088 }
            r11 = 1
            r3[r11] = r0     // Catch:{ AnnotatedException -> 0x0088 }
            r11 = 2
            r3[r11] = r12     // Catch:{ AnnotatedException -> 0x0088 }
            r15.<init>((java.lang.String) r10, (java.lang.String) r13, (java.lang.Object[]) r3)     // Catch:{ AnnotatedException -> 0x0088 }
            r1.addNotification(r15, r6)     // Catch:{ AnnotatedException -> 0x0088 }
            goto L_0x00be
        L_0x0088:
            r0 = move-exception
            org.bouncycastle.pkix.util.ErrorBundle r3 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.Throwable r11 = r0.getCause()
            java.lang.String r11 = r11.getMessage()
            java.lang.Throwable r12 = r0.getCause()
            java.lang.Throwable r0 = r0.getCause()
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getName()
            r13 = 3
            java.lang.Object[] r14 = new java.lang.Object[r13]
            r13 = 0
            r14[r13] = r11
            r11 = 1
            r14[r11] = r12
            r11 = 2
            r14[r11] = r0
            r3.<init>((java.lang.String) r10, (java.lang.String) r8, (java.lang.Object[]) r14)
            r1.addError(r3, r6)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r16 = r0.iterator()
        L_0x00be:
            r0 = 0
        L_0x00bf:
            boolean r11 = r16.hasNext()
            if (r11 == 0) goto L_0x0107
            java.lang.Object r0 = r16.next()
            java.security.cert.X509CRL r0 = (java.security.cert.X509CRL) r0
            java.util.Date r11 = r0.getThisUpdate()
            java.util.Date r12 = r0.getNextUpdate()
            org.bouncycastle.pkix.util.filter.TrustedInput r13 = new org.bouncycastle.pkix.util.filter.TrustedInput
            r13.<init>(r11)
            org.bouncycastle.pkix.util.filter.TrustedInput r11 = new org.bouncycastle.pkix.util.filter.TrustedInput
            r11.<init>(r12)
            r14 = 2
            java.lang.Object[] r15 = new java.lang.Object[r14]
            r14 = 0
            r15[r14] = r13
            r13 = 1
            r15[r13] = r11
            if (r12 == 0) goto L_0x00fa
            boolean r11 = r4.before(r12)
            if (r11 == 0) goto L_0x00ef
            goto L_0x00fa
        L_0x00ef:
            org.bouncycastle.pkix.util.ErrorBundle r11 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r12 = "CertPathReviewer.localInvalidCRL"
            r11.<init>((java.lang.String) r10, (java.lang.String) r12, (java.lang.Object[]) r15)
            r1.addNotification(r11, r6)
            goto L_0x00bf
        L_0x00fa:
            org.bouncycastle.pkix.util.ErrorBundle r11 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r12 = "CertPathReviewer.localValidCRL"
            r11.<init>((java.lang.String) r10, (java.lang.String) r12, (java.lang.Object[]) r15)
            r1.addNotification(r11, r6)
            r11 = r0
            r0 = 1
            goto L_0x0109
        L_0x0107:
            r11 = r0
            r0 = 0
        L_0x0109:
            if (r0 != 0) goto L_0x01f4
            javax.security.auth.x500.X500Principal r12 = r23.getIssuerX500Principal()
            java.util.Iterator r13 = r27.iterator()
            r14 = r0
        L_0x0114:
            boolean r0 = r13.hasNext()
            if (r0 == 0) goto L_0x01ed
            java.lang.Object r0 = r13.next()     // Catch:{ CertPathReviewerException -> 0x01d2 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ CertPathReviewerException -> 0x01d2 }
            java.security.cert.X509CRL r15 = r1.getCRL(r0)     // Catch:{ CertPathReviewerException -> 0x01d2 }
            if (r15 == 0) goto L_0x01c9
            javax.security.auth.x500.X500Principal r3 = r15.getIssuerX500Principal()     // Catch:{ CertPathReviewerException -> 0x01d2 }
            boolean r18 = r12.equals(r3)     // Catch:{ CertPathReviewerException -> 0x01d2 }
            if (r18 != 0) goto L_0x0174
            org.bouncycastle.pkix.util.ErrorBundle r15 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x01d2 }
            r18 = r11
            java.lang.String r11 = "CertPathReviewer.onlineCRLWrongCA"
            r27 = r13
            org.bouncycastle.pkix.util.filter.UntrustedInput r13 = new org.bouncycastle.pkix.util.filter.UntrustedInput     // Catch:{ CertPathReviewerException -> 0x016d }
            java.lang.String r3 = r3.getName()     // Catch:{ CertPathReviewerException -> 0x016d }
            r13.<init>(r3)     // Catch:{ CertPathReviewerException -> 0x016d }
            org.bouncycastle.pkix.util.filter.UntrustedInput r3 = new org.bouncycastle.pkix.util.filter.UntrustedInput     // Catch:{ CertPathReviewerException -> 0x016d }
            r19 = r14
            java.lang.String r14 = r12.getName()     // Catch:{ CertPathReviewerException -> 0x0168 }
            r3.<init>(r14)     // Catch:{ CertPathReviewerException -> 0x0168 }
            org.bouncycastle.pkix.util.filter.UntrustedUrlInput r14 = new org.bouncycastle.pkix.util.filter.UntrustedUrlInput     // Catch:{ CertPathReviewerException -> 0x0168 }
            r14.<init>(r0)     // Catch:{ CertPathReviewerException -> 0x0168 }
            r20 = r12
            r12 = 3
            java.lang.Object[] r0 = new java.lang.Object[r12]     // Catch:{ CertPathReviewerException -> 0x01c7 }
            r12 = 0
            r0[r12] = r13     // Catch:{ CertPathReviewerException -> 0x01c7 }
            r12 = 1
            r0[r12] = r3     // Catch:{ CertPathReviewerException -> 0x01c7 }
            r3 = 2
            r0[r3] = r14     // Catch:{ CertPathReviewerException -> 0x01c7 }
            r15.<init>((java.lang.String) r10, (java.lang.String) r11, (java.lang.Object[]) r0)     // Catch:{ CertPathReviewerException -> 0x01c7 }
            r1.addNotification(r15, r6)     // Catch:{ CertPathReviewerException -> 0x01c7 }
        L_0x0165:
            r14 = 3
            goto L_0x01e3
        L_0x0168:
            r0 = move-exception
            r20 = r12
            goto L_0x01db
        L_0x016d:
            r0 = move-exception
            r20 = r12
            goto L_0x01d9
        L_0x0172:
            r0 = move-exception
            goto L_0x01d5
        L_0x0174:
            r18 = r11
            r20 = r12
            r27 = r13
            r19 = r14
            java.util.Date r3 = r15.getThisUpdate()     // Catch:{ CertPathReviewerException -> 0x01c7 }
            java.util.Date r11 = r15.getNextUpdate()     // Catch:{ CertPathReviewerException -> 0x01c7 }
            org.bouncycastle.pkix.util.filter.TrustedInput r12 = new org.bouncycastle.pkix.util.filter.TrustedInput     // Catch:{ CertPathReviewerException -> 0x01c7 }
            r12.<init>(r3)     // Catch:{ CertPathReviewerException -> 0x01c7 }
            org.bouncycastle.pkix.util.filter.TrustedInput r3 = new org.bouncycastle.pkix.util.filter.TrustedInput     // Catch:{ CertPathReviewerException -> 0x01c7 }
            r3.<init>(r11)     // Catch:{ CertPathReviewerException -> 0x01c7 }
            org.bouncycastle.pkix.util.filter.UntrustedUrlInput r13 = new org.bouncycastle.pkix.util.filter.UntrustedUrlInput     // Catch:{ CertPathReviewerException -> 0x01c7 }
            r13.<init>(r0)     // Catch:{ CertPathReviewerException -> 0x01c7 }
            r14 = 3
            java.lang.Object[] r0 = new java.lang.Object[r14]     // Catch:{ CertPathReviewerException -> 0x01b4 }
            r17 = 0
            r0[r17] = r12     // Catch:{ CertPathReviewerException -> 0x01b4 }
            r12 = 1
            r0[r12] = r3     // Catch:{ CertPathReviewerException -> 0x01b4 }
            r3 = 2
            r0[r3] = r13     // Catch:{ CertPathReviewerException -> 0x01b4 }
            if (r11 == 0) goto L_0x01b6
            boolean r3 = r4.before(r11)     // Catch:{ CertPathReviewerException -> 0x01b4 }
            if (r3 == 0) goto L_0x01a9
            goto L_0x01b6
        L_0x01a9:
            org.bouncycastle.pkix.util.ErrorBundle r3 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x01b4 }
            java.lang.String r11 = "CertPathReviewer.onlineInvalidCRL"
            r3.<init>((java.lang.String) r10, (java.lang.String) r11, (java.lang.Object[]) r0)     // Catch:{ CertPathReviewerException -> 0x01b4 }
            r1.addNotification(r3, r6)     // Catch:{ CertPathReviewerException -> 0x01b4 }
            goto L_0x01e3
        L_0x01b4:
            r0 = move-exception
            goto L_0x01dc
        L_0x01b6:
            org.bouncycastle.pkix.util.ErrorBundle r3 = new org.bouncycastle.pkix.util.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x01c3 }
            java.lang.String r11 = "CertPathReviewer.onlineValidCRL"
            r3.<init>((java.lang.String) r10, (java.lang.String) r11, (java.lang.Object[]) r0)     // Catch:{ CertPathReviewerException -> 0x01c3 }
            r1.addNotification(r3, r6)     // Catch:{ CertPathReviewerException -> 0x01c3 }
            r11 = r15
            r0 = 1
            goto L_0x01f6
        L_0x01c3:
            r0 = move-exception
            r19 = 1
            goto L_0x01dc
        L_0x01c7:
            r0 = move-exception
            goto L_0x01db
        L_0x01c9:
            r18 = r11
            r20 = r12
            r27 = r13
            r19 = r14
            goto L_0x0165
        L_0x01d2:
            r0 = move-exception
            r18 = r11
        L_0x01d5:
            r20 = r12
            r27 = r13
        L_0x01d9:
            r19 = r14
        L_0x01db:
            r14 = 3
        L_0x01dc:
            org.bouncycastle.pkix.util.ErrorBundle r0 = r0.getErrorMessage()
            r1.addNotification(r0, r6)
        L_0x01e3:
            r13 = r27
            r11 = r18
            r14 = r19
            r12 = r20
            goto L_0x0114
        L_0x01ed:
            r18 = r11
            r19 = r14
            r0 = r19
            goto L_0x01f6
        L_0x01f4:
            r18 = r11
        L_0x01f6:
            if (r11 == 0) goto L_0x0416
            if (r25 == 0) goto L_0x0216
            boolean[] r3 = r25.getKeyUsage()
            if (r3 == 0) goto L_0x0216
            int r12 = r3.length
            r13 = 6
            if (r12 <= r13) goto L_0x0209
            boolean r3 = r3[r13]
            if (r3 == 0) goto L_0x0209
            goto L_0x0216
        L_0x0209:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r2 = "CertPathReviewer.noCrlSigningPermited"
            r0.<init>(r10, r2)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r2 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r2.<init>(r0)
            throw r2
        L_0x0216:
            if (r5 == 0) goto L_0x0409
            java.lang.String r3 = "BC"
            r11.verify(r5, r3)     // Catch:{ Exception -> 0x03fb }
            java.math.BigInteger r3 = r23.getSerialNumber()
            java.security.cert.X509CRLEntry r3 = r11.getRevokedCertificate(r3)
            if (r3 == 0) goto L_0x02ac
            boolean r5 = r3.hasExtensions()
            if (r5 == 0) goto L_0x0256
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = org.bouncycastle.asn1.x509.Extension.reasonCode     // Catch:{ AnnotatedException -> 0x0248 }
            java.lang.String r5 = r5.getId()     // Catch:{ AnnotatedException -> 0x0248 }
            org.bouncycastle.asn1.ASN1Primitive r5 = getExtensionValue(r3, r5)     // Catch:{ AnnotatedException -> 0x0248 }
            org.bouncycastle.asn1.ASN1Enumerated r5 = org.bouncycastle.asn1.ASN1Enumerated.getInstance(r5)     // Catch:{ AnnotatedException -> 0x0248 }
            if (r5 == 0) goto L_0x0256
            java.lang.String[] r12 = crlReasons
            int r5 = r5.intValueExact()
            r5 = r12[r5]
            r16 = r5
            goto L_0x0258
        L_0x0248:
            r0 = move-exception
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r3 = "CertPathReviewer.crlReasonExtError"
            r2.<init>(r10, r3)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r3 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r3.<init>(r2, r0)
            throw r3
        L_0x0256:
            r16 = 0
        L_0x0258:
            if (r16 != 0) goto L_0x025f
            java.lang.String[] r5 = crlReasons
            r12 = 7
            r16 = r5[r12]
        L_0x025f:
            r5 = r16
            org.bouncycastle.pkix.util.LocaleString r12 = new org.bouncycastle.pkix.util.LocaleString
            r12.<init>(r10, r5)
            java.util.Date r5 = r3.getRevocationDate()
            boolean r5 = r4.before(r5)
            if (r5 == 0) goto L_0x028d
            org.bouncycastle.pkix.util.ErrorBundle r5 = new org.bouncycastle.pkix.util.ErrorBundle
            org.bouncycastle.pkix.util.filter.TrustedInput r13 = new org.bouncycastle.pkix.util.filter.TrustedInput
            java.util.Date r3 = r3.getRevocationDate()
            r13.<init>(r3)
            r14 = 2
            java.lang.Object[] r3 = new java.lang.Object[r14]
            r15 = 0
            r3[r15] = r13
            r13 = 1
            r3[r13] = r12
            java.lang.String r12 = "CertPathReviewer.revokedAfterValidation"
            r5.<init>((java.lang.String) r10, (java.lang.String) r12, (java.lang.Object[]) r3)
            r1.addNotification(r5, r6)
            goto L_0x02b6
        L_0x028d:
            r13 = 1
            r14 = 2
            r15 = 0
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            org.bouncycastle.pkix.util.filter.TrustedInput r2 = new org.bouncycastle.pkix.util.filter.TrustedInput
            java.util.Date r3 = r3.getRevocationDate()
            r2.<init>(r3)
            java.lang.Object[] r3 = new java.lang.Object[r14]
            r3[r15] = r2
            r3[r13] = r12
            java.lang.String r2 = "CertPathReviewer.certRevoked"
            r0.<init>((java.lang.String) r10, (java.lang.String) r2, (java.lang.Object[]) r3)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r2 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r2.<init>(r0)
            throw r2
        L_0x02ac:
            org.bouncycastle.pkix.util.ErrorBundle r3 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r5 = "CertPathReviewer.notRevoked"
            r3.<init>(r10, r5)
            r1.addNotification(r3, r6)
        L_0x02b6:
            java.util.Date r3 = r11.getNextUpdate()
            if (r3 == 0) goto L_0x02d7
            boolean r4 = r4.before(r3)
            if (r4 != 0) goto L_0x02d7
            org.bouncycastle.pkix.util.ErrorBundle r4 = new org.bouncycastle.pkix.util.ErrorBundle
            org.bouncycastle.pkix.util.filter.TrustedInput r5 = new org.bouncycastle.pkix.util.filter.TrustedInput
            r5.<init>(r3)
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r12 = 0
            r3[r12] = r5
            java.lang.String r5 = "CertPathReviewer.crlUpdateAvailable"
            r4.<init>((java.lang.String) r10, (java.lang.String) r5, (java.lang.Object[]) r3)
            r1.addNotification(r4, r6)
        L_0x02d7:
            java.lang.String r3 = ISSUING_DISTRIBUTION_POINT     // Catch:{ AnnotatedException -> 0x03f0 }
            org.bouncycastle.asn1.ASN1Primitive r3 = getExtensionValue(r11, r3)     // Catch:{ AnnotatedException -> 0x03f0 }
            java.lang.String r4 = DELTA_CRL_INDICATOR     // Catch:{ AnnotatedException -> 0x03e3 }
            org.bouncycastle.asn1.ASN1Primitive r4 = getExtensionValue(r11, r4)     // Catch:{ AnnotatedException -> 0x03e3 }
            if (r4 == 0) goto L_0x0377
            org.bouncycastle.pkix.jcajce.X509CRLStoreSelector r5 = new org.bouncycastle.pkix.jcajce.X509CRLStoreSelector
            r5.<init>()
            javax.security.auth.x500.X500Principal r6 = getIssuerPrincipal(r11)     // Catch:{ IOException -> 0x036b }
            byte[] r6 = r6.getEncoded()     // Catch:{ IOException -> 0x036b }
            r5.addIssuerName(r6)     // Catch:{ IOException -> 0x036b }
            org.bouncycastle.asn1.ASN1Integer r4 = (org.bouncycastle.asn1.ASN1Integer) r4
            java.math.BigInteger r4 = r4.getPositiveValue()
            r5.setMinCRLNumber(r4)
            java.lang.String r4 = CRL_NUMBER     // Catch:{ AnnotatedException -> 0x035d }
            org.bouncycastle.asn1.ASN1Primitive r4 = getExtensionValue(r11, r4)     // Catch:{ AnnotatedException -> 0x035d }
            org.bouncycastle.asn1.ASN1Integer r4 = (org.bouncycastle.asn1.ASN1Integer) r4     // Catch:{ AnnotatedException -> 0x035d }
            java.math.BigInteger r4 = r4.getPositiveValue()     // Catch:{ AnnotatedException -> 0x035d }
            r11 = 1
            java.math.BigInteger r6 = java.math.BigInteger.valueOf(r11)     // Catch:{ AnnotatedException -> 0x035d }
            java.math.BigInteger r4 = r4.subtract(r6)     // Catch:{ AnnotatedException -> 0x035d }
            r5.setMaxCRLNumber(r4)     // Catch:{ AnnotatedException -> 0x035d }
            java.util.Set r2 = org.bouncycastle.pkix.jcajce.PKIXCRLUtil.findCRLs((org.bouncycastle.pkix.jcajce.X509CRLStoreSelector) r5, (java.security.cert.PKIXParameters) r2)     // Catch:{ AnnotatedException -> 0x0351 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ AnnotatedException -> 0x0351 }
        L_0x031f:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0344
            java.lang.Object r4 = r2.next()
            java.security.cert.X509CRL r4 = (java.security.cert.X509CRL) r4
            java.lang.String r5 = ISSUING_DISTRIBUTION_POINT     // Catch:{ AnnotatedException -> 0x0338 }
            org.bouncycastle.asn1.ASN1Primitive r4 = getExtensionValue(r4, r5)     // Catch:{ AnnotatedException -> 0x0338 }
            boolean r4 = org.bouncycastle.util.Objects.areEqual(r3, r4)
            if (r4 == 0) goto L_0x031f
            goto L_0x0377
        L_0x0338:
            r0 = move-exception
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle
            r2.<init>(r10, r7)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r3 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r3.<init>(r2, r0)
            throw r3
        L_0x0344:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r2 = "CertPathReviewer.noBaseCRL"
            r0.<init>(r10, r2)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r2 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r2.<init>(r0)
            throw r2
        L_0x0351:
            r0 = move-exception
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle
            r2.<init>(r10, r8)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r3 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r3.<init>(r2, r0)
            throw r3
        L_0x035d:
            r0 = move-exception
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r3 = "CertPathReviewer.crlNbrExtError"
            r2.<init>(r10, r3)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r3 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r3.<init>(r2, r0)
            throw r3
        L_0x036b:
            r0 = move-exception
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle
            r2.<init>(r10, r9)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r3 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r3.<init>(r2, r0)
            throw r3
        L_0x0377:
            if (r3 == 0) goto L_0x0416
            org.bouncycastle.asn1.x509.IssuingDistributionPoint r2 = org.bouncycastle.asn1.x509.IssuingDistributionPoint.getInstance(r3)
            java.lang.String r3 = BASIC_CONSTRAINTS     // Catch:{ AnnotatedException -> 0x03d5 }
            r4 = r23
            org.bouncycastle.asn1.ASN1Primitive r3 = getExtensionValue(r4, r3)     // Catch:{ AnnotatedException -> 0x03d5 }
            org.bouncycastle.asn1.x509.BasicConstraints r3 = org.bouncycastle.asn1.x509.BasicConstraints.getInstance(r3)     // Catch:{ AnnotatedException -> 0x03d5 }
            boolean r4 = r2.onlyContainsUserCerts()
            if (r4 == 0) goto L_0x03a5
            if (r3 == 0) goto L_0x03a5
            boolean r4 = r3.isCA()
            if (r4 != 0) goto L_0x0398
            goto L_0x03a5
        L_0x0398:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r2 = "CertPathReviewer.crlOnlyUserCert"
            r0.<init>(r10, r2)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r2 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r2.<init>(r0)
            throw r2
        L_0x03a5:
            boolean r4 = r2.onlyContainsCACerts()
            if (r4 == 0) goto L_0x03c1
            if (r3 == 0) goto L_0x03b4
            boolean r3 = r3.isCA()
            if (r3 == 0) goto L_0x03b4
            goto L_0x03c1
        L_0x03b4:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r2 = "CertPathReviewer.crlOnlyCaCert"
            r0.<init>(r10, r2)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r2 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r2.<init>(r0)
            throw r2
        L_0x03c1:
            boolean r2 = r2.onlyContainsAttributeCerts()
            if (r2 != 0) goto L_0x03c8
            goto L_0x0416
        L_0x03c8:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r2 = "CertPathReviewer.crlOnlyAttrCert"
            r0.<init>(r10, r2)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r2 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r2.<init>(r0)
            throw r2
        L_0x03d5:
            r0 = move-exception
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r3 = "CertPathReviewer.crlBCExtError"
            r2.<init>(r10, r3)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r3 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r3.<init>(r2, r0)
            throw r3
        L_0x03e3:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r2 = "CertPathReviewer.deltaCrlExtError"
            r0.<init>(r10, r2)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r2 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r2.<init>(r0)
            throw r2
        L_0x03f0:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            r0.<init>(r10, r7)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r2 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r2.<init>(r0)
            throw r2
        L_0x03fb:
            r0 = move-exception
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r3 = "CertPathReviewer.crlVerifyFailed"
            r2.<init>(r10, r3)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r3 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r3.<init>(r2, r0)
            throw r3
        L_0x0409:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r2 = "CertPathReviewer.crlNoIssuerPublicKey"
            r0.<init>(r10, r2)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r2 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r2.<init>(r0)
            throw r2
        L_0x0416:
            if (r0 == 0) goto L_0x0419
            return
        L_0x0419:
            org.bouncycastle.pkix.util.ErrorBundle r0 = new org.bouncycastle.pkix.util.ErrorBundle
            java.lang.String r2 = "CertPathReviewer.noValidCrlFound"
            r0.<init>(r10, r2)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r2 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r2.<init>(r0)
            throw r2
        L_0x0426:
            r0 = move-exception
            org.bouncycastle.pkix.util.ErrorBundle r2 = new org.bouncycastle.pkix.util.ErrorBundle
            r2.<init>(r10, r9)
            org.bouncycastle.pkix.jcajce.CertPathReviewerException r3 = new org.bouncycastle.pkix.jcajce.CertPathReviewerException
            r3.<init>(r2, r0)
            goto L_0x0433
        L_0x0432:
            throw r3
        L_0x0433:
            goto L_0x0432
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pkix.jcajce.PKIXCertPathReviewer.checkCRLs(java.security.cert.PKIXParameters, java.security.cert.X509Certificate, java.util.Date, java.security.cert.X509Certificate, java.security.PublicKey, java.util.Vector, int):void");
    }

    /* access modifiers changed from: protected */
    public void checkRevocation(PKIXParameters pKIXParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, Vector vector, Vector vector2, int i) throws CertPathReviewerException {
        checkCRLs(pKIXParameters, x509Certificate, date, x509Certificate2, publicKey, vector, i);
    }

    /* access modifiers changed from: protected */
    public void doChecks() {
        if (!this.initialized) {
            throw new IllegalStateException("Object not initialized. Call init() first.");
        } else if (this.notifications == null) {
            int i = this.n;
            this.notifications = new List[(i + 1)];
            this.errors = new List[(i + 1)];
            int i2 = 0;
            while (true) {
                List[] listArr = this.notifications;
                if (i2 < listArr.length) {
                    listArr[i2] = new ArrayList();
                    this.errors[i2] = new ArrayList();
                    i2++;
                } else {
                    checkSignatures();
                    checkNameConstraints();
                    checkPathLength();
                    checkPolicy();
                    checkCriticalExtensions();
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public Vector getCRLDistUrls(CRLDistPoint cRLDistPoint) {
        Vector vector = new Vector();
        if (cRLDistPoint != null) {
            DistributionPoint[] distributionPoints = cRLDistPoint.getDistributionPoints();
            for (DistributionPoint distributionPoint : distributionPoints) {
                DistributionPointName distributionPoint2 = distributionPoint.getDistributionPoint();
                if (distributionPoint2.getType() == 0) {
                    GeneralName[] names = GeneralNames.getInstance(distributionPoint2.getName()).getNames();
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].getTagNo() == 6) {
                            vector.add(((ASN1IA5String) names[i].getName()).getString());
                        }
                    }
                }
            }
        }
        return vector;
    }

    public CertPath getCertPath() {
        return this.certPath;
    }

    public int getCertPathSize() {
        return this.n;
    }

    public List getErrors(int i) {
        doChecks();
        return this.errors[i + 1];
    }

    public List[] getErrors() {
        doChecks();
        return this.errors;
    }

    public List getNotifications(int i) {
        doChecks();
        return this.notifications[i + 1];
    }

    public List[] getNotifications() {
        doChecks();
        return this.notifications;
    }

    /* access modifiers changed from: protected */
    public Vector getOCSPUrls(AuthorityInformationAccess authorityInformationAccess) {
        Vector vector = new Vector();
        if (authorityInformationAccess != null) {
            AccessDescription[] accessDescriptions = authorityInformationAccess.getAccessDescriptions();
            for (int i = 0; i < accessDescriptions.length; i++) {
                if (accessDescriptions[i].getAccessMethod().equals((ASN1Primitive) AccessDescription.id_ad_ocsp)) {
                    GeneralName accessLocation = accessDescriptions[i].getAccessLocation();
                    if (accessLocation.getTagNo() == 6) {
                        vector.add(((ASN1IA5String) accessLocation.getName()).getString());
                    }
                }
            }
        }
        return vector;
    }

    public PolicyNode getPolicyTree() {
        doChecks();
        return this.policyTree;
    }

    public PublicKey getSubjectPublicKey() {
        doChecks();
        return this.subjectPublicKey;
    }

    public TrustAnchor getTrustAnchor() {
        doChecks();
        return this.trustAnchor;
    }

    /* access modifiers changed from: protected */
    public Collection getTrustAnchors(X509Certificate x509Certificate, Set set) throws CertPathReviewerException {
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        X509CertSelector x509CertSelector = new X509CertSelector();
        try {
            x509CertSelector.setSubject(getEncodedIssuerPrincipal(x509Certificate).getEncoded());
            byte[] extensionValue = x509Certificate.getExtensionValue(Extension.authorityKeyIdentifier.getId());
            if (extensionValue != null) {
                AuthorityKeyIdentifier instance = AuthorityKeyIdentifier.getInstance(ASN1Primitive.fromByteArray(((ASN1OctetString) ASN1Primitive.fromByteArray(extensionValue)).getOctets()));
                if (instance.getAuthorityCertSerialNumber() != null) {
                    x509CertSelector.setSerialNumber(instance.getAuthorityCertSerialNumber());
                } else {
                    byte[] keyIdentifier = instance.getKeyIdentifier();
                    if (keyIdentifier != null) {
                        x509CertSelector.setSubjectKeyIdentifier(new DEROctetString(keyIdentifier).getEncoded());
                    }
                }
            }
            while (it.hasNext()) {
                TrustAnchor trustAnchor2 = (TrustAnchor) it.next();
                if (trustAnchor2.getTrustedCert() != null) {
                    if (!x509CertSelector.match(trustAnchor2.getTrustedCert())) {
                    }
                } else if (trustAnchor2.getCAName() != null) {
                    if (trustAnchor2.getCAPublicKey() != null) {
                        if (!getEncodedIssuerPrincipal(x509Certificate).equals(new X500Principal(trustAnchor2.getCAName()))) {
                        }
                    }
                }
                arrayList.add(trustAnchor2);
            }
            return arrayList;
        } catch (IOException unused) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustAnchorIssuerError"));
        }
    }

    public void init(CertPath certPath2, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        if (!this.initialized) {
            this.initialized = true;
            if (certPath2 != null) {
                List<? extends Certificate> certificates = certPath2.getCertificates();
                if (certificates.size() != 1) {
                    HashSet hashSet = new HashSet();
                    for (TrustAnchor trustedCert : pKIXParameters.getTrustAnchors()) {
                        hashSet.add(trustedCert.getTrustedCert());
                    }
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i != certificates.size(); i++) {
                        if (!hashSet.contains(certificates.get(i))) {
                            arrayList.add(certificates.get(i));
                        }
                    }
                    try {
                        this.certPath = CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME).generateCertPath(arrayList);
                        this.certs = arrayList;
                    } catch (GeneralSecurityException unused) {
                        throw new IllegalStateException("unable to rebuild certpath");
                    }
                } else {
                    this.certPath = certPath2;
                    this.certs = certPath2.getCertificates();
                }
                this.n = this.certs.size();
                if (!this.certs.isEmpty()) {
                    this.pkixParams = (PKIXParameters) pKIXParameters.clone();
                    Date date = new Date();
                    this.currentDate = date;
                    this.validDate = getValidityDate(this.pkixParams, date);
                    this.notifications = null;
                    this.errors = null;
                    this.trustAnchor = null;
                    this.subjectPublicKey = null;
                    this.policyTree = null;
                    return;
                }
                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.emptyCertPath"));
            }
            throw new NullPointerException("certPath was null");
        }
        throw new IllegalStateException("object is already initialized!");
    }

    public boolean isValidCertPath() {
        doChecks();
        int i = 0;
        while (true) {
            List[] listArr = this.errors;
            if (i >= listArr.length) {
                return true;
            }
            if (!listArr[i].isEmpty()) {
                return false;
            }
            i++;
        }
    }
}
