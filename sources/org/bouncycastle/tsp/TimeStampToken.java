package org.bouncycastle.tsp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.ess.ESSCertID;
import org.bouncycastle.asn1.ess.ESSCertIDv2;
import org.bouncycastle.asn1.ess.SigningCertificate;
import org.bouncycastle.asn1.ess.SigningCertificateV2;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.tsp.TSTInfo;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.IssuerSerial;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSTypedData;
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Store;

public class TimeStampToken {
    CertID certID;
    CMSSignedData tsToken;
    SignerInformation tsaSignerInfo;
    TimeStampTokenInfo tstInfo;

    private static class CertID {
        private ESSCertID certID;
        private ESSCertIDv2 certIDv2;

        CertID(ESSCertID eSSCertID) {
            this.certID = eSSCertID;
            this.certIDv2 = null;
        }

        CertID(ESSCertIDv2 eSSCertIDv2) {
            this.certIDv2 = eSSCertIDv2;
            this.certID = null;
        }

        public byte[] getCertHash() {
            ESSCertID eSSCertID = this.certID;
            return eSSCertID != null ? eSSCertID.getCertHash() : this.certIDv2.getCertHash();
        }

        public AlgorithmIdentifier getHashAlgorithm() {
            return this.certID != null ? new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1) : this.certIDv2.getHashAlgorithm();
        }

        public IssuerSerial getIssuerSerial() {
            ESSCertID eSSCertID = this.certID;
            return eSSCertID != null ? eSSCertID.getIssuerSerial() : this.certIDv2.getIssuerSerial();
        }
    }

    public TimeStampToken(ContentInfo contentInfo) throws TSPException, IOException {
        this(getSignedData(contentInfo));
    }

    public TimeStampToken(CMSSignedData cMSSignedData) throws TSPException, IOException {
        CertID certID2;
        this.tsToken = cMSSignedData;
        if (cMSSignedData.getSignedContentTypeOID().equals(PKCSObjectIdentifiers.id_ct_TSTInfo.getId())) {
            Collection<SignerInformation> signers = this.tsToken.getSignerInfos().getSigners();
            if (signers.size() == 1) {
                this.tsaSignerInfo = signers.iterator().next();
                try {
                    CMSTypedData signedContent = this.tsToken.getSignedContent();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    signedContent.write(byteArrayOutputStream);
                    this.tstInfo = new TimeStampTokenInfo(TSTInfo.getInstance(ASN1Primitive.fromByteArray(byteArrayOutputStream.toByteArray())));
                    Attribute attribute = this.tsaSignerInfo.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificate);
                    if (attribute != null) {
                        certID2 = new CertID(ESSCertID.getInstance(SigningCertificate.getInstance(attribute.getAttrValues().getObjectAt(0)).getCerts()[0]));
                    } else {
                        Attribute attribute2 = this.tsaSignerInfo.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificateV2);
                        if (attribute2 != null) {
                            certID2 = new CertID(ESSCertIDv2.getInstance(SigningCertificateV2.getInstance(attribute2.getAttrValues().getObjectAt(0)).getCerts()[0]));
                        } else {
                            throw new TSPValidationException("no signing certificate attribute found, time stamp invalid.");
                        }
                    }
                    this.certID = certID2;
                } catch (CMSException e) {
                    throw new TSPException(e.getMessage(), e.getUnderlyingException());
                }
            } else {
                throw new IllegalArgumentException("Time-stamp token signed by " + signers.size() + " signers, but it must contain just the TSA signature.");
            }
        } else {
            throw new TSPValidationException("ContentInfo object not for a time stamp.");
        }
    }

    private static CMSSignedData getSignedData(ContentInfo contentInfo) throws TSPException {
        try {
            return new CMSSignedData(contentInfo);
        } catch (CMSException e) {
            throw new TSPException("TSP parsing error: " + e.getMessage(), e.getCause());
        }
    }

    public Store<X509AttributeCertificateHolder> getAttributeCertificates() {
        return this.tsToken.getAttributeCertificates();
    }

    public Store<X509CRLHolder> getCRLs() {
        return this.tsToken.getCRLs();
    }

    public Store<X509CertificateHolder> getCertificates() {
        return this.tsToken.getCertificates();
    }

    public byte[] getEncoded() throws IOException {
        return this.tsToken.getEncoded(ASN1Encoding.DL);
    }

    public byte[] getEncoded(String str) throws IOException {
        return this.tsToken.getEncoded(str);
    }

    public SignerId getSID() {
        return this.tsaSignerInfo.getSID();
    }

    public AttributeTable getSignedAttributes() {
        return this.tsaSignerInfo.getSignedAttributes();
    }

    public TimeStampTokenInfo getTimeStampInfo() {
        return this.tstInfo;
    }

    public AttributeTable getUnsignedAttributes() {
        return this.tsaSignerInfo.getUnsignedAttributes();
    }

    public boolean isSignatureValid(SignerInformationVerifier signerInformationVerifier) throws TSPException {
        try {
            return this.tsaSignerInfo.verify(signerInformationVerifier);
        } catch (CMSException e) {
            if (e.getUnderlyingException() != null) {
                throw new TSPException(e.getMessage(), e.getUnderlyingException());
            }
            throw new TSPException("CMS exception: " + e, e);
        }
    }

    public CMSSignedData toCMSSignedData() {
        return this.tsToken;
    }

    public void validate(SignerInformationVerifier signerInformationVerifier) throws TSPException, TSPValidationException {
        if (signerInformationVerifier.hasAssociatedCertificate()) {
            try {
                X509CertificateHolder associatedCertificate = signerInformationVerifier.getAssociatedCertificate();
                DigestCalculator digestCalculator = signerInformationVerifier.getDigestCalculator(this.certID.getHashAlgorithm());
                OutputStream outputStream = digestCalculator.getOutputStream();
                outputStream.write(associatedCertificate.getEncoded());
                outputStream.close();
                if (Arrays.constantTimeAreEqual(this.certID.getCertHash(), digestCalculator.getDigest())) {
                    if (this.certID.getIssuerSerial() != null) {
                        IssuerAndSerialNumber issuerAndSerialNumber = new IssuerAndSerialNumber(associatedCertificate.toASN1Structure());
                        if (this.certID.getIssuerSerial().getSerial().equals((ASN1Primitive) issuerAndSerialNumber.getSerialNumber())) {
                            GeneralName[] names = this.certID.getIssuerSerial().getIssuer().getNames();
                            int i = 0;
                            while (i != names.length) {
                                if (names[i].getTagNo() != 4 || !X500Name.getInstance(names[i].getName()).equals(X500Name.getInstance(issuerAndSerialNumber.getName()))) {
                                    i++;
                                }
                            }
                            throw new TSPValidationException("certificate name does not match certID for signature. ");
                        }
                        throw new TSPValidationException("certificate serial number does not match certID for signature.");
                    }
                    TSPUtil.validateCertificate(associatedCertificate);
                    if (!associatedCertificate.isValidOn(this.tstInfo.getGenTime())) {
                        throw new TSPValidationException("certificate not valid when time stamp created.");
                    } else if (!this.tsaSignerInfo.verify(signerInformationVerifier)) {
                        throw new TSPValidationException("signature not created by certificate.");
                    }
                } else {
                    throw new TSPValidationException("certificate hash does not match certID hash.");
                }
            } catch (CMSException e) {
                if (e.getUnderlyingException() != null) {
                    throw new TSPException(e.getMessage(), e.getUnderlyingException());
                }
                throw new TSPException("CMS exception: " + e, e);
            } catch (IOException e2) {
                throw new TSPException("problem processing certificate: " + e2, e2);
            } catch (OperatorCreationException e3) {
                throw new TSPException("unable to create digest: " + e3.getMessage(), e3);
            }
        } else {
            throw new IllegalArgumentException("verifier provider needs an associated certificate");
        }
    }
}
