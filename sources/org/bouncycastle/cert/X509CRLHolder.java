package org.bouncycastle.cert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.AltSignatureAlgorithm;
import org.bouncycastle.asn1.x509.AltSignatureValue;
import org.bouncycastle.asn1.x509.CertificateList;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.TBSCertList;
import org.bouncycastle.asn1.x509.Time;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.util.Encodable;

public class X509CRLHolder implements Encodable, Serializable {
    private static final long serialVersionUID = 20170722001L;
    private transient Extensions extensions;
    private transient boolean isIndirect;
    private transient GeneralNames issuerName;
    private transient CertificateList x509CRL;

    public X509CRLHolder(InputStream inputStream) throws IOException {
        this(parseStream(inputStream));
    }

    public X509CRLHolder(CertificateList certificateList) {
        init(certificateList);
    }

    public X509CRLHolder(byte[] bArr) throws IOException {
        this(parseStream(new ByteArrayInputStream(bArr)));
    }

    private void init(CertificateList certificateList) {
        this.x509CRL = certificateList;
        Extensions extensions2 = certificateList.getTBSCertList().getExtensions();
        this.extensions = extensions2;
        this.isIndirect = isIndirectCRL(extensions2);
        this.issuerName = new GeneralNames(new GeneralName(certificateList.getIssuer()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0004, code lost:
        r2 = r2.getExtension(org.bouncycastle.asn1.x509.Extension.issuingDistributionPoint);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean isIndirectCRL(org.bouncycastle.asn1.x509.Extensions r2) {
        /*
            r0 = 0
            if (r2 != 0) goto L_0x0004
            return r0
        L_0x0004:
            org.bouncycastle.asn1.ASN1ObjectIdentifier r1 = org.bouncycastle.asn1.x509.Extension.issuingDistributionPoint
            org.bouncycastle.asn1.x509.Extension r2 = r2.getExtension(r1)
            if (r2 == 0) goto L_0x001b
            org.bouncycastle.asn1.ASN1Encodable r2 = r2.getParsedValue()
            org.bouncycastle.asn1.x509.IssuingDistributionPoint r2 = org.bouncycastle.asn1.x509.IssuingDistributionPoint.getInstance(r2)
            boolean r2 = r2.isIndirectCRL()
            if (r2 == 0) goto L_0x001b
            r0 = 1
        L_0x001b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.cert.X509CRLHolder.isIndirectCRL(org.bouncycastle.asn1.x509.Extensions):boolean");
    }

    private static CertificateList parseStream(InputStream inputStream) throws IOException {
        try {
            ASN1Primitive readObject = new ASN1InputStream(inputStream, true).readObject();
            if (readObject != null) {
                return CertificateList.getInstance(readObject);
            }
            throw new IOException("no content found");
        } catch (ClassCastException e) {
            throw new CertIOException("malformed data: " + e.getMessage(), e);
        } catch (IllegalArgumentException e2) {
            throw new CertIOException("malformed data: " + e2.getMessage(), e2);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        init(CertificateList.getInstance(objectInputStream.readObject()));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof X509CRLHolder)) {
            return false;
        }
        return this.x509CRL.equals(((X509CRLHolder) obj).x509CRL);
    }

    public Set getCriticalExtensionOIDs() {
        return CertUtils.getCriticalExtensionOIDs(this.extensions);
    }

    public byte[] getEncoded() throws IOException {
        return this.x509CRL.getEncoded();
    }

    public Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Extensions extensions2 = this.extensions;
        if (extensions2 != null) {
            return extensions2.getExtension(aSN1ObjectIdentifier);
        }
        return null;
    }

    public List getExtensionOIDs() {
        return CertUtils.getExtensionOIDs(this.extensions);
    }

    public Extensions getExtensions() {
        return this.extensions;
    }

    public X500Name getIssuer() {
        return X500Name.getInstance(this.x509CRL.getIssuer());
    }

    public Date getNextUpdate() {
        Time nextUpdate = this.x509CRL.getNextUpdate();
        if (nextUpdate != null) {
            return nextUpdate.getDate();
        }
        return null;
    }

    public Set getNonCriticalExtensionOIDs() {
        return CertUtils.getNonCriticalExtensionOIDs(this.extensions);
    }

    public X509CRLEntryHolder getRevokedCertificate(BigInteger bigInteger) {
        Extension extension;
        GeneralNames generalNames = this.issuerName;
        Enumeration revokedCertificateEnumeration = this.x509CRL.getRevokedCertificateEnumeration();
        while (revokedCertificateEnumeration.hasMoreElements()) {
            TBSCertList.CRLEntry cRLEntry = (TBSCertList.CRLEntry) revokedCertificateEnumeration.nextElement();
            if (cRLEntry.getUserCertificate().hasValue(bigInteger)) {
                return new X509CRLEntryHolder(cRLEntry, this.isIndirect, generalNames);
            }
            if (this.isIndirect && cRLEntry.hasExtensions() && (extension = cRLEntry.getExtensions().getExtension(Extension.certificateIssuer)) != null) {
                generalNames = GeneralNames.getInstance(extension.getParsedValue());
            }
        }
        return null;
    }

    public Collection getRevokedCertificates() {
        ArrayList arrayList = new ArrayList(this.x509CRL.getRevokedCertificates().length);
        GeneralNames generalNames = this.issuerName;
        Enumeration revokedCertificateEnumeration = this.x509CRL.getRevokedCertificateEnumeration();
        while (revokedCertificateEnumeration.hasMoreElements()) {
            X509CRLEntryHolder x509CRLEntryHolder = new X509CRLEntryHolder((TBSCertList.CRLEntry) revokedCertificateEnumeration.nextElement(), this.isIndirect, generalNames);
            arrayList.add(x509CRLEntryHolder);
            generalNames = x509CRLEntryHolder.getCertificateIssuer();
        }
        return arrayList;
    }

    public Date getThisUpdate() {
        return this.x509CRL.getThisUpdate().getDate();
    }

    public boolean hasExtensions() {
        return this.extensions != null;
    }

    public int hashCode() {
        return this.x509CRL.hashCode();
    }

    public boolean isAlternativeSignatureValid(ContentVerifierProvider contentVerifierProvider) throws CertException {
        int i;
        TBSCertList tBSCertList = this.x509CRL.getTBSCertList();
        AltSignatureAlgorithm fromExtensions = AltSignatureAlgorithm.fromExtensions(tBSCertList.getExtensions());
        AltSignatureValue fromExtensions2 = AltSignatureValue.fromExtensions(tBSCertList.getExtensions());
        try {
            ContentVerifier contentVerifier = contentVerifierProvider.get(AlgorithmIdentifier.getInstance(fromExtensions.toASN1Primitive()));
            OutputStream outputStream = contentVerifier.getOutputStream();
            ASN1Sequence instance = ASN1Sequence.getInstance(tBSCertList.toASN1Primitive());
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            if (instance.getObjectAt(0) instanceof ASN1Integer) {
                aSN1EncodableVector.add(instance.getObjectAt(0));
                i = 2;
            } else {
                i = 1;
            }
            while (i != instance.size() - 1) {
                aSN1EncodableVector.add(instance.getObjectAt(i));
                i++;
            }
            aSN1EncodableVector.add(CertUtils.trimExtensions(0, tBSCertList.getExtensions()));
            new DERSequence(aSN1EncodableVector).encodeTo(outputStream, ASN1Encoding.DER);
            outputStream.close();
            return contentVerifier.verify(fromExtensions2.getSignature().getOctets());
        } catch (Exception e) {
            throw new CertException("unable to process signature: " + e.getMessage(), e);
        }
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) throws CertException {
        TBSCertList tBSCertList = this.x509CRL.getTBSCertList();
        if (CertUtils.isAlgIdEqual(tBSCertList.getSignature(), this.x509CRL.getSignatureAlgorithm())) {
            try {
                ContentVerifier contentVerifier = contentVerifierProvider.get(tBSCertList.getSignature());
                OutputStream outputStream = contentVerifier.getOutputStream();
                tBSCertList.encodeTo(outputStream, ASN1Encoding.DER);
                outputStream.close();
                return contentVerifier.verify(this.x509CRL.getSignature().getOctets());
            } catch (Exception e) {
                throw new CertException("unable to process signature: " + e.getMessage(), e);
            }
        } else {
            throw new CertException("signature invalid - algorithm identifier mismatch");
        }
    }

    public CertificateList toASN1Structure() {
        return this.x509CRL;
    }
}
