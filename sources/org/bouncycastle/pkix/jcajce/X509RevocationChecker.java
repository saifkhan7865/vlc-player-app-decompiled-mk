package org.bouncycastle.pkix.jcajce;

import androidx.core.os.EnvironmentCompat;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.cert.CRL;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.Certificate;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.jcajce.PKIXCRLStore;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.util.CollectionStore;
import org.bouncycastle.util.Iterable;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;

public class X509RevocationChecker extends PKIXCertPathChecker {
    public static final int CHAIN_VALIDITY_MODEL = 1;
    private static Logger LOG = Logger.getLogger(X509RevocationChecker.class.getName());
    public static final int PKIX_VALIDITY_MODEL = 0;
    protected static final String[] crlReasons = {"unspecified", "keyCompromise", "cACompromise", "affiliationChanged", "superseded", "cessationOfOperation", "certificateHold", EnvironmentCompat.MEDIA_UNKNOWN, "removeFromCRL", "privilegeWithdrawn", "aACompromise"};
    private final boolean canSoftFail;
    private final List<CertStore> crlCertStores;
    private final List<Store<CRL>> crls;
    private Date currentDate;
    private final long failHardMaxTime;
    private final long failLogMaxTime;
    private final Map<X500Principal, Long> failures;
    private final JcaJceHelper helper;
    private final boolean isCheckEEOnly;
    private X509Certificate signingCert;
    private final Set<TrustAnchor> trustAnchors;
    private final Date validationDate;
    private final int validityModel;
    private X500Principal workingIssuerName;
    private PublicKey workingPublicKey;

    public static class Builder {
        /* access modifiers changed from: private */
        public boolean canSoftFail;
        /* access modifiers changed from: private */
        public List<CertStore> crlCertStores;
        /* access modifiers changed from: private */
        public List<Store<CRL>> crls;
        /* access modifiers changed from: private */
        public long failHardMaxTime;
        /* access modifiers changed from: private */
        public long failLogMaxTime;
        /* access modifiers changed from: private */
        public boolean isCheckEEOnly;
        /* access modifiers changed from: private */
        public Provider provider;
        /* access modifiers changed from: private */
        public String providerName;
        /* access modifiers changed from: private */
        public Set<TrustAnchor> trustAnchors;
        /* access modifiers changed from: private */
        public Date validityDate;
        /* access modifiers changed from: private */
        public int validityModel;

        public Builder(KeyStore keyStore) throws KeyStoreException {
            this.crlCertStores = new ArrayList();
            this.crls = new ArrayList();
            this.validityModel = 0;
            this.validityDate = new Date();
            this.trustAnchors = new HashSet();
            Enumeration<String> aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                String nextElement = aliases.nextElement();
                if (keyStore.isCertificateEntry(nextElement)) {
                    this.trustAnchors.add(new TrustAnchor((X509Certificate) keyStore.getCertificate(nextElement), (byte[]) null));
                }
            }
        }

        public Builder(TrustAnchor trustAnchor) {
            this.crlCertStores = new ArrayList();
            this.crls = new ArrayList();
            this.validityModel = 0;
            this.validityDate = new Date();
            this.trustAnchors = Collections.singleton(trustAnchor);
        }

        public Builder(Set<TrustAnchor> set) {
            this.crlCertStores = new ArrayList();
            this.crls = new ArrayList();
            this.validityModel = 0;
            this.validityDate = new Date();
            this.trustAnchors = new HashSet(set);
        }

        public Builder addCrls(CertStore certStore) {
            this.crlCertStores.add(certStore);
            return this;
        }

        public Builder addCrls(Store<CRL> store) {
            this.crls.add(store);
            return this;
        }

        public X509RevocationChecker build() {
            return new X509RevocationChecker(this);
        }

        public Builder setCheckEndEntityOnly(boolean z) {
            this.isCheckEEOnly = z;
            return this;
        }

        public Builder setDate(Date date) {
            this.validityDate = new Date(date.getTime());
            return this;
        }

        public Builder setSoftFail(boolean z, long j) {
            this.canSoftFail = z;
            this.failLogMaxTime = j;
            this.failHardMaxTime = -1;
            return this;
        }

        public Builder setSoftFailHardLimit(boolean z, long j) {
            this.canSoftFail = z;
            this.failLogMaxTime = (3 * j) / 4;
            this.failHardMaxTime = j;
            return this;
        }

        public Builder setValidityModel(int i) {
            this.validityModel = i;
            return this;
        }

        public Builder usingProvider(String str) {
            this.providerName = str;
            return this;
        }

        public Builder usingProvider(Provider provider2) {
            this.provider = provider2;
            return this;
        }
    }

    private static class LocalCRLStore implements PKIXCRLStore<CRL>, Iterable<CRL> {
        private Collection<CRL> _local;

        public LocalCRLStore(Store<CRL> store) {
            this._local = new ArrayList(store.getMatches((Selector<CRL>) null));
        }

        public Collection<CRL> getMatches(Selector<CRL> selector) {
            if (selector == null) {
                return new ArrayList(this._local);
            }
            ArrayList arrayList = new ArrayList();
            for (CRL next : this._local) {
                if (selector.match(next)) {
                    arrayList.add(next);
                }
            }
            return arrayList;
        }

        public Iterator<CRL> iterator() {
            return getMatches((Selector<CRL>) null).iterator();
        }
    }

    private X509RevocationChecker(Builder builder) {
        JcaJceHelper namedJcaJceHelper;
        this.failures = new HashMap();
        this.crls = new ArrayList(builder.crls);
        this.crlCertStores = new ArrayList(builder.crlCertStores);
        this.isCheckEEOnly = builder.isCheckEEOnly;
        this.validityModel = builder.validityModel;
        this.trustAnchors = builder.trustAnchors;
        this.canSoftFail = builder.canSoftFail;
        this.failLogMaxTime = builder.failLogMaxTime;
        this.failHardMaxTime = builder.failHardMaxTime;
        this.validationDate = builder.validityDate;
        if (builder.provider != null) {
            namedJcaJceHelper = new ProviderJcaJceHelper(builder.provider);
        } else if (builder.providerName != null) {
            namedJcaJceHelper = new NamedJcaJceHelper(builder.providerName);
        } else {
            this.helper = new DefaultJcaJceHelper();
            return;
        }
        this.helper = namedJcaJceHelper;
    }

    private void addIssuers(final List<X500Principal> list, CertStore certStore) throws CertStoreException {
        certStore.getCRLs(new X509CRLSelector() {
            public boolean match(CRL crl) {
                if (!(crl instanceof X509CRL)) {
                    return false;
                }
                list.add(((X509CRL) crl).getIssuerX500Principal());
                return false;
            }
        });
    }

    private void addIssuers(final List<X500Principal> list, Store<CRL> store) {
        store.getMatches(new Selector<CRL>() {
            public Object clone() {
                return this;
            }

            public boolean match(CRL crl) {
                if (!(crl instanceof X509CRL)) {
                    return false;
                }
                list.add(((X509CRL) crl).getIssuerX500Principal());
                return false;
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Set<java.security.cert.CRL> downloadCRLs(javax.security.auth.x500.X500Principal r17, java.util.Date r18, org.bouncycastle.asn1.ASN1Primitive r19, org.bouncycastle.jcajce.util.JcaJceHelper r20) {
        /*
            r16 = this;
            org.bouncycastle.asn1.x509.CRLDistPoint r0 = org.bouncycastle.asn1.x509.CRLDistPoint.getInstance(r19)
            org.bouncycastle.asn1.x509.DistributionPoint[] r1 = r0.getDistributionPoints()
            java.lang.String r0 = "X.509"
            r3 = r20
            java.security.cert.CertificateFactory r3 = r3.createCertificateFactory(r0)     // Catch:{ Exception -> 0x00e5 }
            java.security.cert.X509CRLSelector r0 = new java.security.cert.X509CRLSelector
            r0.<init>()
            r4 = r17
            r0.addIssuer(r4)
            org.bouncycastle.jcajce.PKIXCRLStoreSelector$Builder r4 = new org.bouncycastle.jcajce.PKIXCRLStoreSelector$Builder
            r4.<init>(r0)
            org.bouncycastle.jcajce.PKIXCRLStoreSelector r4 = r4.build()
            java.util.HashSet r5 = new java.util.HashSet
            r5.<init>()
            r7 = 0
        L_0x0029:
            int r0 = r1.length
            if (r7 == r0) goto L_0x00e2
            r0 = r1[r7]
            org.bouncycastle.asn1.x509.DistributionPointName r0 = r0.getDistributionPoint()
            if (r0 == 0) goto L_0x00da
            int r8 = r0.getType()
            if (r8 != 0) goto L_0x00da
            org.bouncycastle.asn1.ASN1Encodable r0 = r0.getName()
            org.bouncycastle.asn1.x509.GeneralNames r0 = org.bouncycastle.asn1.x509.GeneralNames.getInstance(r0)
            org.bouncycastle.asn1.x509.GeneralName[] r8 = r0.getNames()
            r9 = 0
        L_0x0047:
            int r0 = r8.length
            if (r9 == r0) goto L_0x00da
            r0 = r8[r9]
            int r10 = r0.getTagNo()
            r11 = 6
            if (r10 != r11) goto L_0x00d2
            java.net.URI r10 = new java.net.URI     // Catch:{ Exception -> 0x0082 }
            org.bouncycastle.asn1.ASN1Encodable r0 = r0.getName()     // Catch:{ Exception -> 0x0082 }
            org.bouncycastle.asn1.ASN1String r0 = (org.bouncycastle.asn1.ASN1String) r0     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = r0.getString()     // Catch:{ Exception -> 0x0082 }
            r10.<init>(r0)     // Catch:{ Exception -> 0x0082 }
            r11 = r16
            java.util.Date r0 = r11.validationDate     // Catch:{ Exception -> 0x007e }
            org.bouncycastle.jcajce.PKIXCRLStore r0 = org.bouncycastle.pkix.jcajce.CrlCache.getCrl(r3, r0, r10)     // Catch:{ Exception -> 0x007e }
            if (r0 == 0) goto L_0x00d4
            java.util.List r12 = java.util.Collections.EMPTY_LIST     // Catch:{ Exception -> 0x007e }
            java.util.List r0 = java.util.Collections.singletonList(r0)     // Catch:{ Exception -> 0x007e }
            r13 = r18
            java.util.Set r0 = org.bouncycastle.pkix.jcajce.PKIXCRLUtil.findCRLs(r4, r13, r12, r0)     // Catch:{ Exception -> 0x007c }
            r5.addAll(r0)     // Catch:{ Exception -> 0x007c }
            goto L_0x00d6
        L_0x007c:
            r0 = move-exception
            goto L_0x0088
        L_0x007e:
            r0 = move-exception
            r13 = r18
            goto L_0x0088
        L_0x0082:
            r0 = move-exception
            r11 = r16
            r13 = r18
            r10 = 0
        L_0x0088:
            java.util.logging.Logger r12 = LOG
            java.util.logging.Level r14 = java.util.logging.Level.FINE
            boolean r12 = r12.isLoggable(r14)
            java.lang.String r14 = " ignored: "
            java.lang.String r15 = "CrlDP "
            if (r12 == 0) goto L_0x00b4
            java.util.logging.Logger r12 = LOG
            java.util.logging.Level r6 = java.util.logging.Level.FINE
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r15)
            r2.append(r10)
            r2.append(r14)
            java.lang.String r10 = r0.getMessage()
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            r12.log(r6, r2, r0)
            goto L_0x00d6
        L_0x00b4:
            java.util.logging.Logger r2 = LOG
            java.util.logging.Level r6 = java.util.logging.Level.INFO
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>(r15)
            r12.append(r10)
            r12.append(r14)
            java.lang.String r0 = r0.getMessage()
            r12.append(r0)
            java.lang.String r0 = r12.toString()
            r2.log(r6, r0)
            goto L_0x00d6
        L_0x00d2:
            r11 = r16
        L_0x00d4:
            r13 = r18
        L_0x00d6:
            int r9 = r9 + 1
            goto L_0x0047
        L_0x00da:
            r11 = r16
            r13 = r18
            int r7 = r7 + 1
            goto L_0x0029
        L_0x00e2:
            r11 = r16
            return r5
        L_0x00e5:
            r0 = move-exception
            r11 = r16
            java.util.logging.Logger r1 = LOG
            java.util.logging.Level r2 = java.util.logging.Level.FINE
            boolean r1 = r1.isLoggable(r2)
            java.lang.String r2 = "could not create certFact: "
            if (r1 == 0) goto L_0x010c
            java.util.logging.Logger r1 = LOG
            java.util.logging.Level r3 = java.util.logging.Level.FINE
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r2)
            java.lang.String r2 = r0.getMessage()
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r1.log(r3, r2, r0)
            goto L_0x0123
        L_0x010c:
            java.util.logging.Logger r1 = LOG
            java.util.logging.Level r3 = java.util.logging.Level.INFO
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r2)
            java.lang.String r0 = r0.getMessage()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r1.log(r3, r0)
        L_0x0123:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pkix.jcajce.X509RevocationChecker.downloadCRLs(javax.security.auth.x500.X500Principal, java.util.Date, org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.jcajce.util.JcaJceHelper):java.util.Set");
    }

    static List<PKIXCRLStore> getAdditionalStoresFromCRLDistributionPoint(CRLDistPoint cRLDistPoint, Map<GeneralName, PKIXCRLStore> map) throws AnnotatedException {
        if (cRLDistPoint == null) {
            return Collections.emptyList();
        }
        try {
            DistributionPoint[] distributionPoints = cRLDistPoint.getDistributionPoints();
            ArrayList arrayList = new ArrayList();
            for (DistributionPoint distributionPoint : distributionPoints) {
                DistributionPointName distributionPoint2 = distributionPoint.getDistributionPoint();
                if (distributionPoint2 != null && distributionPoint2.getType() == 0) {
                    GeneralName[] names = GeneralNames.getInstance(distributionPoint2.getName()).getNames();
                    for (GeneralName generalName : names) {
                        PKIXCRLStore pKIXCRLStore = map.get(generalName);
                        if (pKIXCRLStore != null) {
                            arrayList.add(pKIXCRLStore);
                        }
                    }
                }
            }
            return arrayList;
        } catch (Exception e) {
            throw new AnnotatedException("could not read distribution points could not be read", e);
        }
    }

    public void check(Certificate certificate, Collection<String> collection) throws CertPathValidatorException {
        StringBuilder sb;
        Level level;
        X509Certificate x509Certificate = (X509Certificate) certificate;
        if (!this.isCheckEEOnly || x509Certificate.getBasicConstraints() == -1) {
            if (this.workingIssuerName == null) {
                this.workingIssuerName = x509Certificate.getIssuerX500Principal();
                TrustAnchor trustAnchor = null;
                for (TrustAnchor next : this.trustAnchors) {
                    if (this.workingIssuerName.equals(next.getCA()) || this.workingIssuerName.equals(next.getTrustedCert().getSubjectX500Principal())) {
                        trustAnchor = next;
                    }
                }
                if (trustAnchor != null) {
                    X509Certificate trustedCert = trustAnchor.getTrustedCert();
                    this.signingCert = trustedCert;
                    this.workingPublicKey = trustedCert.getPublicKey();
                } else {
                    throw new CertPathValidatorException("no trust anchor found for " + this.workingIssuerName);
                }
            }
            ArrayList arrayList = new ArrayList();
            try {
                PKIXParameters pKIXParameters = new PKIXParameters(this.trustAnchors);
                pKIXParameters.setRevocationEnabled(false);
                pKIXParameters.setDate(this.validationDate);
                for (int i = 0; i != this.crlCertStores.size(); i++) {
                    if (LOG.isLoggable(Level.INFO)) {
                        addIssuers((List<X500Principal>) arrayList, this.crlCertStores.get(i));
                    }
                    pKIXParameters.addCertStore(this.crlCertStores.get(i));
                }
                PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder(pKIXParameters);
                builder.setValidityModel(this.validityModel);
                for (int i2 = 0; i2 != this.crls.size(); i2++) {
                    if (LOG.isLoggable(Level.INFO)) {
                        addIssuers((List<X500Principal>) arrayList, this.crls.get(i2));
                    }
                    builder.addCRLStore(new LocalCRLStore(this.crls.get(i2)));
                }
                if (arrayList.isEmpty()) {
                    LOG.log(Level.INFO, "configured with 0 pre-loaded CRLs");
                } else if (LOG.isLoggable(Level.FINE)) {
                    for (int i3 = 0; i3 != arrayList.size(); i3++) {
                        Logger logger = LOG;
                        Level level2 = Level.FINE;
                        logger.log(level2, "configuring with CRL for issuer \"" + arrayList.get(i3) + "\"");
                    }
                } else {
                    Logger logger2 = LOG;
                    Level level3 = Level.INFO;
                    logger2.log(level3, "configured with " + arrayList.size() + " pre-loaded CRLs");
                }
                PKIXExtendedParameters build = builder.build();
                Date validityDate = RevocationUtilities.getValidityDate(build, this.validationDate);
                try {
                    checkCRLs(build, this.currentDate, validityDate, x509Certificate, this.signingCert, this.workingPublicKey, new ArrayList(), this.helper);
                } catch (AnnotatedException e) {
                    throw new CertPathValidatorException(e.getMessage(), e.getCause());
                } catch (CRLNotFoundException e2) {
                    if (x509Certificate.getExtensionValue(Extension.cRLDistributionPoints.getId()) != null) {
                        try {
                            Set<CRL> downloadCRLs = downloadCRLs(x509Certificate.getIssuerX500Principal(), validityDate, RevocationUtilities.getExtensionValue(x509Certificate, Extension.cRLDistributionPoints), this.helper);
                            if (!downloadCRLs.isEmpty()) {
                                try {
                                    builder.addCRLStore(new LocalCRLStore(new CollectionStore(downloadCRLs)));
                                    PKIXExtendedParameters build2 = builder.build();
                                    checkCRLs(build2, this.currentDate, RevocationUtilities.getValidityDate(build2, this.validationDate), x509Certificate, this.signingCert, this.workingPublicKey, new ArrayList(), this.helper);
                                } catch (AnnotatedException e3) {
                                    throw new CertPathValidatorException(e3.getMessage(), e3.getCause());
                                }
                            } else if (this.canSoftFail) {
                                X500Principal issuerX500Principal = x509Certificate.getIssuerX500Principal();
                                Long l = this.failures.get(issuerX500Principal);
                                if (l != null) {
                                    long currentTimeMillis = System.currentTimeMillis() - l.longValue();
                                    long j = this.failHardMaxTime;
                                    if (j == -1 || j >= currentTimeMillis) {
                                        int i4 = (currentTimeMillis > this.failLogMaxTime ? 1 : (currentTimeMillis == this.failLogMaxTime ? 0 : -1));
                                        Logger logger3 = LOG;
                                        if (i4 < 0) {
                                            level = Level.WARNING;
                                            sb = new StringBuilder("soft failing for issuer: \"");
                                        } else {
                                            level = Level.SEVERE;
                                            sb = new StringBuilder("soft failing for issuer: \"");
                                        }
                                        sb.append(issuerX500Principal);
                                        sb.append("\"");
                                        logger3.log(level, sb.toString());
                                    } else {
                                        throw e2;
                                    }
                                } else {
                                    this.failures.put(issuerX500Principal, Long.valueOf(System.currentTimeMillis()));
                                }
                            } else {
                                throw e2;
                            }
                        } catch (AnnotatedException e4) {
                            throw new CertPathValidatorException(e4.getMessage(), e4.getCause());
                        }
                    } else {
                        throw e2;
                    }
                }
                this.signingCert = x509Certificate;
                this.workingPublicKey = x509Certificate.getPublicKey();
                this.workingIssuerName = x509Certificate.getSubjectX500Principal();
            } catch (GeneralSecurityException e5) {
                throw new RuntimeException("error setting up baseParams: " + e5.getMessage());
            }
        } else {
            this.workingIssuerName = x509Certificate.getSubjectX500Principal();
            this.workingPublicKey = x509Certificate.getPublicKey();
            this.signingCert = x509Certificate;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x011b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkCRLs(org.bouncycastle.jcajce.PKIXExtendedParameters r22, java.util.Date r23, java.util.Date r24, java.security.cert.X509Certificate r25, java.security.cert.X509Certificate r26, java.security.PublicKey r27, java.util.List r28, org.bouncycastle.jcajce.util.JcaJceHelper r29) throws org.bouncycastle.pkix.jcajce.AnnotatedException, java.security.cert.CertPathValidatorException {
        /*
            r21 = this;
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = org.bouncycastle.asn1.x509.Extension.cRLDistributionPoints     // Catch:{ Exception -> 0x01ad }
            r12 = r25
            org.bouncycastle.asn1.ASN1Primitive r0 = org.bouncycastle.pkix.jcajce.RevocationUtilities.getExtensionValue(r12, r0)     // Catch:{ Exception -> 0x01ad }
            org.bouncycastle.asn1.x509.CRLDistPoint r0 = org.bouncycastle.asn1.x509.CRLDistPoint.getInstance(r0)     // Catch:{ Exception -> 0x01ad }
            org.bouncycastle.pkix.jcajce.CertStatus r13 = new org.bouncycastle.pkix.jcajce.CertStatus
            r13.<init>()
            org.bouncycastle.pkix.jcajce.ReasonsMask r14 = new org.bouncycastle.pkix.jcajce.ReasonsMask
            r14.<init>()
            r11 = 0
            r10 = 0
            r9 = 11
            if (r0 == 0) goto L_0x00b3
            org.bouncycastle.asn1.x509.DistributionPoint[] r8 = r0.getDistributionPoints()     // Catch:{ Exception -> 0x00a9 }
            if (r8 == 0) goto L_0x00b3
            org.bouncycastle.jcajce.PKIXExtendedParameters$Builder r1 = new org.bouncycastle.jcajce.PKIXExtendedParameters$Builder
            r7 = r22
            r1.<init>((org.bouncycastle.jcajce.PKIXExtendedParameters) r7)
            java.util.Map r2 = r22.getNamedCRLStoreMap()     // Catch:{ AnnotatedException -> 0x00a0 }
            java.util.List r0 = getAdditionalStoresFromCRLDistributionPoint(r0, r2)     // Catch:{ AnnotatedException -> 0x00a0 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ AnnotatedException -> 0x00a0 }
        L_0x0035:
            boolean r2 = r0.hasNext()     // Catch:{ AnnotatedException -> 0x00a0 }
            if (r2 == 0) goto L_0x0045
            java.lang.Object r2 = r0.next()     // Catch:{ AnnotatedException -> 0x00a0 }
            org.bouncycastle.jcajce.PKIXCRLStore r2 = (org.bouncycastle.jcajce.PKIXCRLStore) r2     // Catch:{ AnnotatedException -> 0x00a0 }
            r1.addCRLStore(r2)     // Catch:{ AnnotatedException -> 0x00a0 }
            goto L_0x0035
        L_0x0045:
            org.bouncycastle.jcajce.PKIXExtendedParameters r6 = r1.build()
            r5 = r23
            java.util.Date r16 = org.bouncycastle.pkix.jcajce.RevocationUtilities.getValidityDate(r6, r5)
            r0 = r10
            r4 = 0
            r17 = 0
        L_0x0053:
            int r1 = r8.length
            if (r4 >= r1) goto L_0x009d
            int r1 = r13.getCertStatus()
            if (r1 != r9) goto L_0x009d
            boolean r1 = r14.isAllReasons()
            if (r1 != 0) goto L_0x009d
            r1 = r8[r4]     // Catch:{ AnnotatedException -> 0x0085 }
            r2 = r6
            r3 = r23
            r18 = r4
            r4 = r16
            r5 = r25
            r19 = r6
            r6 = r26
            r7 = r27
            r20 = r8
            r8 = r13
            r15 = 11
            r9 = r14
            r10 = r28
            r11 = r29
            org.bouncycastle.pkix.jcajce.RFC3280CertPathUtilities.checkCRL(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ AnnotatedException -> 0x0083 }
            r17 = 1
            goto L_0x008e
        L_0x0083:
            r0 = move-exception
            goto L_0x008e
        L_0x0085:
            r0 = move-exception
            r18 = r4
            r19 = r6
            r20 = r8
            r15 = 11
        L_0x008e:
            int r4 = r18 + 1
            r7 = r22
            r5 = r23
            r6 = r19
            r8 = r20
            r9 = 11
            r10 = 0
            r11 = 0
            goto L_0x0053
        L_0x009d:
            r15 = 11
            goto L_0x00b8
        L_0x00a0:
            r0 = move-exception
            org.bouncycastle.pkix.jcajce.AnnotatedException r1 = new org.bouncycastle.pkix.jcajce.AnnotatedException
            java.lang.String r2 = "no additional CRL locations could be decoded from CRL distribution point extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x00a9:
            r0 = move-exception
            r1 = r0
            org.bouncycastle.pkix.jcajce.AnnotatedException r0 = new org.bouncycastle.pkix.jcajce.AnnotatedException
            java.lang.String r2 = "cannot read distribution points"
            r0.<init>(r2, r1)
            throw r0
        L_0x00b3:
            r15 = 11
            r0 = 0
            r17 = 0
        L_0x00b8:
            int r1 = r13.getCertStatus()
            if (r1 != r15) goto L_0x0107
            boolean r1 = r14.isAllReasons()
            if (r1 != 0) goto L_0x0107
            javax.security.auth.x500.X500Principal r1 = r25.getIssuerX500Principal()     // Catch:{ AnnotatedException -> 0x0106 }
            org.bouncycastle.asn1.x509.DistributionPoint r2 = new org.bouncycastle.asn1.x509.DistributionPoint     // Catch:{ AnnotatedException -> 0x0106 }
            org.bouncycastle.asn1.x509.DistributionPointName r3 = new org.bouncycastle.asn1.x509.DistributionPointName     // Catch:{ AnnotatedException -> 0x0106 }
            org.bouncycastle.asn1.x509.GeneralNames r4 = new org.bouncycastle.asn1.x509.GeneralNames     // Catch:{ AnnotatedException -> 0x0106 }
            org.bouncycastle.asn1.x509.GeneralName r5 = new org.bouncycastle.asn1.x509.GeneralName     // Catch:{ AnnotatedException -> 0x0106 }
            byte[] r1 = r1.getEncoded()     // Catch:{ AnnotatedException -> 0x0106 }
            org.bouncycastle.asn1.x500.X500Name r1 = org.bouncycastle.asn1.x500.X500Name.getInstance(r1)     // Catch:{ AnnotatedException -> 0x0106 }
            r6 = 4
            r5.<init>((int) r6, (org.bouncycastle.asn1.ASN1Encodable) r1)     // Catch:{ AnnotatedException -> 0x0106 }
            r4.<init>((org.bouncycastle.asn1.x509.GeneralName) r5)     // Catch:{ AnnotatedException -> 0x0106 }
            r1 = 0
            r3.<init>(r1, r4)     // Catch:{ AnnotatedException -> 0x0106 }
            r1 = 0
            r2.<init>(r3, r1, r1)     // Catch:{ AnnotatedException -> 0x0106 }
            java.lang.Object r1 = r22.clone()     // Catch:{ AnnotatedException -> 0x0106 }
            r3 = r1
            org.bouncycastle.jcajce.PKIXExtendedParameters r3 = (org.bouncycastle.jcajce.PKIXExtendedParameters) r3     // Catch:{ AnnotatedException -> 0x0106 }
            r1 = r2
            r2 = r3
            r3 = r23
            r4 = r24
            r5 = r25
            r6 = r26
            r7 = r27
            r8 = r13
            r9 = r14
            r10 = r28
            r11 = r29
            org.bouncycastle.pkix.jcajce.RFC3280CertPathUtilities.checkCRL(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ AnnotatedException -> 0x0106 }
            r17 = 1
            goto L_0x0107
        L_0x0106:
            r0 = move-exception
        L_0x0107:
            if (r17 != 0) goto L_0x011b
            boolean r1 = r0 instanceof org.bouncycastle.pkix.jcajce.AnnotatedException
            java.lang.String r2 = "no valid CRL found"
            if (r1 == 0) goto L_0x0115
            org.bouncycastle.pkix.jcajce.CRLNotFoundException r1 = new org.bouncycastle.pkix.jcajce.CRLNotFoundException
            r1.<init>(r2, r0)
            throw r1
        L_0x0115:
            org.bouncycastle.pkix.jcajce.CRLNotFoundException r0 = new org.bouncycastle.pkix.jcajce.CRLNotFoundException
            r0.<init>(r2)
            throw r0
        L_0x011b:
            int r0 = r13.getCertStatus()
            if (r0 != r15) goto L_0x0141
            boolean r0 = r14.isAllReasons()
            r1 = 12
            if (r0 != 0) goto L_0x0132
            int r0 = r13.getCertStatus()
            if (r0 != r15) goto L_0x0132
            r13.setCertStatus(r1)
        L_0x0132:
            int r0 = r13.getCertStatus()
            if (r0 == r1) goto L_0x0139
            return
        L_0x0139:
            org.bouncycastle.pkix.jcajce.AnnotatedException r0 = new org.bouncycastle.pkix.jcajce.AnnotatedException
            java.lang.String r1 = "certificate status could not be determined"
            r0.<init>(r1)
            throw r0
        L_0x0141:
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat
            java.lang.String r1 = "yyyy-MM-dd HH:mm:ss Z"
            r0.<init>(r1)
            java.lang.String r1 = "UTC"
            java.util.TimeZone r1 = j$.util.DesugarTimeZone.getTimeZone(r1)
            r0.setTimeZone(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "certificate [issuer=\""
            r1.<init>(r2)
            javax.security.auth.x500.X500Principal r2 = r25.getIssuerX500Principal()
            r1.append(r2)
            java.lang.String r2 = "\",serialNumber="
            r1.append(r2)
            java.math.BigInteger r2 = r25.getSerialNumber()
            r1.append(r2)
            java.lang.String r2 = ",subject=\""
            r1.append(r2)
            javax.security.auth.x500.X500Principal r2 = r25.getSubjectX500Principal()
            r1.append(r2)
            java.lang.String r2 = "\"] revoked after "
            r1.append(r2)
            java.util.Date r2 = r13.getRevocationDate()
            java.lang.String r0 = r0.format(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = ", reason: "
            r1.append(r0)
            java.lang.String[] r0 = crlReasons
            int r2 = r13.getCertStatus()
            r0 = r0[r2]
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            org.bouncycastle.pkix.jcajce.AnnotatedException r1 = new org.bouncycastle.pkix.jcajce.AnnotatedException
            r1.<init>(r0)
            throw r1
        L_0x01ad:
            r0 = move-exception
            org.bouncycastle.pkix.jcajce.AnnotatedException r1 = new org.bouncycastle.pkix.jcajce.AnnotatedException
            java.lang.String r2 = "cannot read CRL distribution point extension"
            r1.<init>(r2, r0)
            goto L_0x01b7
        L_0x01b6:
            throw r1
        L_0x01b7:
            goto L_0x01b6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pkix.jcajce.X509RevocationChecker.checkCRLs(org.bouncycastle.jcajce.PKIXExtendedParameters, java.util.Date, java.util.Date, java.security.cert.X509Certificate, java.security.cert.X509Certificate, java.security.PublicKey, java.util.List, org.bouncycastle.jcajce.util.JcaJceHelper):void");
    }

    public Object clone() {
        return this;
    }

    public Set<String> getSupportedExtensions() {
        return null;
    }

    public void init(boolean z) throws CertPathValidatorException {
        if (!z) {
            this.currentDate = new Date();
            this.workingIssuerName = null;
            return;
        }
        throw new IllegalArgumentException("forward processing not supported");
    }

    public boolean isForwardCheckingSupported() {
        return false;
    }
}
