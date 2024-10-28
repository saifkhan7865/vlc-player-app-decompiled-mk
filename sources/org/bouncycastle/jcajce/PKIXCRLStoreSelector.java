package org.bouncycastle.jcajce;

import java.math.BigInteger;
import java.security.cert.CRL;
import java.security.cert.CRLSelector;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509Certificate;
import java.util.Collection;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Selector;

public class PKIXCRLStoreSelector<T extends CRL> implements Selector<T> {
    /* access modifiers changed from: private */
    public final CRLSelector baseSelector;
    private final boolean completeCRLEnabled;
    private final boolean deltaCRLIndicator;
    private final byte[] issuingDistributionPoint;
    private final boolean issuingDistributionPointEnabled;
    private final BigInteger maxBaseCRLNumber;

    public static class Builder {
        /* access modifiers changed from: private */
        public final CRLSelector baseSelector;
        /* access modifiers changed from: private */
        public boolean completeCRLEnabled = false;
        /* access modifiers changed from: private */
        public boolean deltaCRLIndicator = false;
        /* access modifiers changed from: private */
        public byte[] issuingDistributionPoint = null;
        /* access modifiers changed from: private */
        public boolean issuingDistributionPointEnabled = false;
        /* access modifiers changed from: private */
        public BigInteger maxBaseCRLNumber = null;

        public Builder(CRLSelector cRLSelector) {
            this.baseSelector = (CRLSelector) cRLSelector.clone();
        }

        public PKIXCRLStoreSelector<? extends CRL> build() {
            return new PKIXCRLStoreSelector<>(this);
        }

        public Builder setCompleteCRLEnabled(boolean z) {
            this.completeCRLEnabled = z;
            return this;
        }

        public Builder setDeltaCRLIndicatorEnabled(boolean z) {
            this.deltaCRLIndicator = z;
            return this;
        }

        public void setIssuingDistributionPoint(byte[] bArr) {
            this.issuingDistributionPoint = Arrays.clone(bArr);
        }

        public void setIssuingDistributionPointEnabled(boolean z) {
            this.issuingDistributionPointEnabled = z;
        }

        public void setMaxBaseCRLNumber(BigInteger bigInteger) {
            this.maxBaseCRLNumber = bigInteger;
        }
    }

    private static class SelectorClone extends X509CRLSelector {
        private final PKIXCRLStoreSelector selector;

        SelectorClone(PKIXCRLStoreSelector pKIXCRLStoreSelector) {
            this.selector = pKIXCRLStoreSelector;
            if (pKIXCRLStoreSelector.baseSelector instanceof X509CRLSelector) {
                X509CRLSelector x509CRLSelector = (X509CRLSelector) pKIXCRLStoreSelector.baseSelector;
                setCertificateChecking(x509CRLSelector.getCertificateChecking());
                setDateAndTime(x509CRLSelector.getDateAndTime());
                setIssuers(x509CRLSelector.getIssuers());
                setMinCRLNumber(x509CRLSelector.getMinCRL());
                setMaxCRLNumber(x509CRLSelector.getMaxCRL());
            }
        }

        public boolean match(CRL crl) {
            PKIXCRLStoreSelector pKIXCRLStoreSelector = this.selector;
            return pKIXCRLStoreSelector == null ? crl != null : pKIXCRLStoreSelector.match(crl);
        }
    }

    private PKIXCRLStoreSelector(Builder builder) {
        this.baseSelector = builder.baseSelector;
        this.deltaCRLIndicator = builder.deltaCRLIndicator;
        this.completeCRLEnabled = builder.completeCRLEnabled;
        this.maxBaseCRLNumber = builder.maxBaseCRLNumber;
        this.issuingDistributionPoint = builder.issuingDistributionPoint;
        this.issuingDistributionPointEnabled = builder.issuingDistributionPointEnabled;
    }

    public static Collection<? extends CRL> getCRLs(PKIXCRLStoreSelector pKIXCRLStoreSelector, CertStore certStore) throws CertStoreException {
        return certStore.getCRLs(new SelectorClone(pKIXCRLStoreSelector));
    }

    public Object clone() {
        return this;
    }

    public X509Certificate getCertificateChecking() {
        CRLSelector cRLSelector = this.baseSelector;
        if (cRLSelector instanceof X509CRLSelector) {
            return ((X509CRLSelector) cRLSelector).getCertificateChecking();
        }
        return null;
    }

    public byte[] getIssuingDistributionPoint() {
        return Arrays.clone(this.issuingDistributionPoint);
    }

    public BigInteger getMaxBaseCRLNumber() {
        return this.maxBaseCRLNumber;
    }

    public boolean isCompleteCRLEnabled() {
        return this.completeCRLEnabled;
    }

    public boolean isDeltaCRLIndicatorEnabled() {
        return this.deltaCRLIndicator;
    }

    public boolean isIssuingDistributionPointEnabled() {
        return this.issuingDistributionPointEnabled;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0068, code lost:
        if (org.bouncycastle.util.Arrays.areEqual(r0, r2) == false) goto L_0x006a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean match(java.security.cert.CRL r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof java.security.cert.X509CRL
            if (r0 != 0) goto L_0x000b
        L_0x0004:
            java.security.cert.CRLSelector r0 = r4.baseSelector
            boolean r5 = r0.match(r5)
            return r5
        L_0x000b:
            r0 = r5
            java.security.cert.X509CRL r0 = (java.security.cert.X509CRL) r0
            r1 = 0
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = org.bouncycastle.asn1.x509.Extension.deltaCRLIndicator     // Catch:{ Exception -> 0x006a }
            java.lang.String r2 = r2.getId()     // Catch:{ Exception -> 0x006a }
            byte[] r2 = r0.getExtensionValue(r2)     // Catch:{ Exception -> 0x006a }
            if (r2 == 0) goto L_0x0028
            org.bouncycastle.asn1.ASN1OctetString r2 = org.bouncycastle.asn1.ASN1OctetString.getInstance(r2)     // Catch:{ Exception -> 0x006a }
            byte[] r2 = r2.getOctets()     // Catch:{ Exception -> 0x006a }
            org.bouncycastle.asn1.ASN1Integer r2 = org.bouncycastle.asn1.ASN1Integer.getInstance(r2)     // Catch:{ Exception -> 0x006a }
            goto L_0x0029
        L_0x0028:
            r2 = 0
        L_0x0029:
            boolean r3 = r4.isDeltaCRLIndicatorEnabled()
            if (r3 == 0) goto L_0x0032
            if (r2 != 0) goto L_0x0032
            return r1
        L_0x0032:
            boolean r3 = r4.isCompleteCRLEnabled()
            if (r3 == 0) goto L_0x003b
            if (r2 == 0) goto L_0x003b
            return r1
        L_0x003b:
            if (r2 == 0) goto L_0x004f
            java.math.BigInteger r3 = r4.maxBaseCRLNumber
            if (r3 == 0) goto L_0x004f
            java.math.BigInteger r2 = r2.getPositiveValue()
            java.math.BigInteger r3 = r4.maxBaseCRLNumber
            int r2 = r2.compareTo(r3)
            r3 = 1
            if (r2 != r3) goto L_0x004f
            return r1
        L_0x004f:
            boolean r2 = r4.issuingDistributionPointEnabled
            if (r2 == 0) goto L_0x0004
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = org.bouncycastle.asn1.x509.Extension.issuingDistributionPoint
            java.lang.String r2 = r2.getId()
            byte[] r0 = r0.getExtensionValue(r2)
            byte[] r2 = r4.issuingDistributionPoint
            if (r2 != 0) goto L_0x0064
            if (r0 == 0) goto L_0x0004
            return r1
        L_0x0064:
            boolean r0 = org.bouncycastle.util.Arrays.areEqual((byte[]) r0, (byte[]) r2)
            if (r0 != 0) goto L_0x0004
        L_0x006a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.PKIXCRLStoreSelector.match(java.security.cert.CRL):boolean");
    }
}
