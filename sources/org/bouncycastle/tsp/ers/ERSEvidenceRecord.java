package org.bouncycastle.tsp.ers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.SignedData;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.tsp.ArchiveTimeStamp;
import org.bouncycastle.asn1.tsp.ArchiveTimeStampChain;
import org.bouncycastle.asn1.tsp.EvidenceRecord;
import org.bouncycastle.asn1.tsp.TSTInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TimeStampRequest;
import org.bouncycastle.tsp.TimeStampRequestGenerator;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.util.io.Streams;

public class ERSEvidenceRecord {
    private final DigestCalculator digCalc;
    private final DigestCalculatorProvider digestCalculatorProvider;
    private final EvidenceRecord evidenceRecord;
    private final ERSArchiveTimeStamp firstArchiveTimeStamp;
    private final ERSArchiveTimeStamp lastArchiveTimeStamp;
    private final byte[] previousChainsDigest;
    private final ArchiveTimeStamp primaryArchiveTimeStamp;

    public ERSEvidenceRecord(InputStream inputStream, DigestCalculatorProvider digestCalculatorProvider2) throws TSPException, ERSException, IOException {
        this(EvidenceRecord.getInstance(Streams.readAll(inputStream)), digestCalculatorProvider2);
    }

    public ERSEvidenceRecord(EvidenceRecord evidenceRecord2, DigestCalculatorProvider digestCalculatorProvider2) throws TSPException, ERSException {
        this.evidenceRecord = evidenceRecord2;
        this.digestCalculatorProvider = digestCalculatorProvider2;
        ArchiveTimeStampChain[] archiveTimeStampChains = evidenceRecord2.getArchiveTimeStampSequence().getArchiveTimeStampChains();
        this.primaryArchiveTimeStamp = archiveTimeStampChains[0].getArchiveTimestamps()[0];
        validateChains(archiveTimeStampChains);
        ArchiveTimeStamp[] archiveTimestamps = archiveTimeStampChains[archiveTimeStampChains.length - 1].getArchiveTimestamps();
        this.lastArchiveTimeStamp = new ERSArchiveTimeStamp(archiveTimestamps[archiveTimestamps.length - 1], digestCalculatorProvider2);
        if (archiveTimeStampChains.length > 1) {
            try {
                ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                for (int i = 0; i != archiveTimeStampChains.length - 1; i++) {
                    aSN1EncodableVector.add(archiveTimeStampChains[i]);
                }
                DigestCalculator digestCalculator = digestCalculatorProvider2.get(this.lastArchiveTimeStamp.getDigestAlgorithmIdentifier());
                this.digCalc = digestCalculator;
                OutputStream outputStream = digestCalculator.getOutputStream();
                outputStream.write(new DERSequence(aSN1EncodableVector).getEncoded(ASN1Encoding.DER));
                outputStream.close();
                this.previousChainsDigest = digestCalculator.getDigest();
            } catch (Exception e) {
                throw new ERSException(e.getMessage(), e);
            }
        } else {
            this.digCalc = null;
            this.previousChainsDigest = null;
        }
        this.firstArchiveTimeStamp = new ERSArchiveTimeStamp(this.previousChainsDigest, archiveTimestamps[0], digestCalculatorProvider2);
    }

    public ERSEvidenceRecord(byte[] bArr, DigestCalculatorProvider digestCalculatorProvider2) throws TSPException, ERSException {
        this(EvidenceRecord.getInstance(bArr), digestCalculatorProvider2);
    }

    private ERSArchiveTimeStampGenerator buildTspRenewalGenerator() throws ERSException {
        try {
            DigestCalculator digestCalculator = this.digestCalculatorProvider.get(this.lastArchiveTimeStamp.getDigestAlgorithmIdentifier());
            ArchiveTimeStamp[] archiveTimeStamps = getArchiveTimeStamps();
            int i = 0;
            if (digestCalculator.getAlgorithmIdentifier().equals(archiveTimeStamps[0].getDigestAlgorithmIdentifier())) {
                ERSArchiveTimeStampGenerator eRSArchiveTimeStampGenerator = new ERSArchiveTimeStampGenerator(digestCalculator);
                ArrayList arrayList = new ArrayList(archiveTimeStamps.length);
                while (i != archiveTimeStamps.length) {
                    try {
                        arrayList.add(new ERSByteData(archiveTimeStamps[i].getTimeStamp().getEncoded(ASN1Encoding.DER)));
                        i++;
                    } catch (IOException e) {
                        throw new ERSException("unable to process previous ArchiveTimeStamps", e);
                    }
                }
                eRSArchiveTimeStampGenerator.addData(new ERSDataGroup((List<ERSData>) arrayList));
                return eRSArchiveTimeStampGenerator;
            }
            throw new ERSException("digest mismatch for timestamp renewal");
        } catch (OperatorCreationException e2) {
            throw new ERSException(e2.getMessage(), e2);
        }
    }

    private TSTInfo extractTimeStamp(ContentInfo contentInfo) throws TSPException {
        SignedData instance = SignedData.getInstance(contentInfo.getContent());
        if (instance.getEncapContentInfo().getContentType().equals((ASN1Primitive) PKCSObjectIdentifiers.id_ct_TSTInfo)) {
            return TSTInfo.getInstance(ASN1OctetString.getInstance(instance.getEncapContentInfo().getContent()).getOctets());
        }
        throw new TSPException("cannot parse time stamp");
    }

    private void validateChains(ArchiveTimeStampChain[] archiveTimeStampChainArr) throws ERSException, TSPException {
        for (int i = 0; i != archiveTimeStampChainArr.length; i++) {
            ArchiveTimeStamp[] archiveTimestamps = archiveTimeStampChainArr[i].getArchiveTimestamps();
            ArchiveTimeStamp archiveTimeStamp = archiveTimestamps[0];
            AlgorithmIdentifier digestAlgorithmIdentifier = archiveTimeStamp.getDigestAlgorithmIdentifier();
            int i2 = 1;
            while (i2 != archiveTimestamps.length) {
                ArchiveTimeStamp archiveTimeStamp2 = archiveTimestamps[i2];
                if (digestAlgorithmIdentifier.equals(archiveTimeStamp2.getDigestAlgorithmIdentifier())) {
                    ContentInfo timeStamp = archiveTimeStamp2.getTimeStamp();
                    if (timeStamp.getContentType().equals((ASN1Primitive) CMSObjectIdentifiers.signedData)) {
                        try {
                            new ERSArchiveTimeStamp(archiveTimeStamp2, this.digestCalculatorProvider.get(digestAlgorithmIdentifier)).validatePresent(new ERSByteData(archiveTimeStamp.getTimeStamp().getEncoded(ASN1Encoding.DER)), extractTimeStamp(timeStamp).getGenTime().getDate());
                            i2++;
                            archiveTimeStamp = archiveTimeStamp2;
                        } catch (Exception e) {
                            throw new ERSException("invalid timestamp renewal found: " + e.getMessage(), e);
                        }
                    } else {
                        throw new TSPException("cannot identify TSTInfo");
                    }
                } else {
                    throw new ERSException("invalid digest algorithm in chain");
                }
            }
        }
    }

    public TimeStampRequest generateHashRenewalRequest(DigestCalculator digestCalculator, ERSData eRSData, TimeStampRequestGenerator timeStampRequestGenerator) throws ERSException, TSPException, IOException {
        return generateHashRenewalRequest(digestCalculator, eRSData, timeStampRequestGenerator, (BigInteger) null);
    }

    public TimeStampRequest generateHashRenewalRequest(DigestCalculator digestCalculator, ERSData eRSData, TimeStampRequestGenerator timeStampRequestGenerator, BigInteger bigInteger) throws ERSException, TSPException, IOException {
        try {
            this.firstArchiveTimeStamp.validatePresent(eRSData, new Date());
            ERSArchiveTimeStampGenerator eRSArchiveTimeStampGenerator = new ERSArchiveTimeStampGenerator(digestCalculator);
            eRSArchiveTimeStampGenerator.addData(eRSData);
            eRSArchiveTimeStampGenerator.addPreviousChains(this.evidenceRecord.getArchiveTimeStampSequence());
            return eRSArchiveTimeStampGenerator.generateTimeStampRequest(timeStampRequestGenerator, bigInteger);
        } catch (Exception unused) {
            throw new ERSException("attempt to hash renew on invalid data");
        }
    }

    public TimeStampRequest generateTimeStampRenewalRequest(TimeStampRequestGenerator timeStampRequestGenerator) throws TSPException, ERSException {
        return generateTimeStampRenewalRequest(timeStampRequestGenerator, (BigInteger) null);
    }

    public TimeStampRequest generateTimeStampRenewalRequest(TimeStampRequestGenerator timeStampRequestGenerator, BigInteger bigInteger) throws ERSException, TSPException {
        try {
            return buildTspRenewalGenerator().generateTimeStampRequest(timeStampRequestGenerator, bigInteger);
        } catch (IOException e) {
            throw new ERSException(e.getMessage(), e);
        }
    }

    /* access modifiers changed from: package-private */
    public ArchiveTimeStamp[] getArchiveTimeStamps() {
        ArchiveTimeStampChain[] archiveTimeStampChains = this.evidenceRecord.getArchiveTimeStampSequence().getArchiveTimeStampChains();
        return archiveTimeStampChains[archiveTimeStampChains.length - 1].getArchiveTimestamps();
    }

    /* access modifiers changed from: package-private */
    public DigestCalculatorProvider getDigestAlgorithmProvider() {
        return this.digestCalculatorProvider;
    }

    public byte[] getEncoded() throws IOException {
        return this.evidenceRecord.getEncoded();
    }

    public byte[] getPrimaryRootHash() throws TSPException, ERSException {
        ContentInfo timeStamp = this.primaryArchiveTimeStamp.getTimeStamp();
        if (timeStamp.getContentType().equals((ASN1Primitive) CMSObjectIdentifiers.signedData)) {
            return extractTimeStamp(timeStamp).getMessageImprint().getHashedMessage();
        }
        throw new ERSException("cannot identify TSTInfo for digest");
    }

    public X509CertificateHolder getSigningCertificate() {
        return this.lastArchiveTimeStamp.getSigningCertificate();
    }

    public boolean isContaining(ERSData eRSData, Date date) throws ERSException {
        return this.firstArchiveTimeStamp.isContaining(eRSData, date);
    }

    public boolean isRelatedTo(ERSEvidenceRecord eRSEvidenceRecord) {
        return this.primaryArchiveTimeStamp.getTimeStamp().equals(eRSEvidenceRecord.primaryArchiveTimeStamp.getTimeStamp());
    }

    public ERSEvidenceRecord renewHash(DigestCalculator digestCalculator, ERSData eRSData, TimeStampResponse timeStampResponse) throws ERSException, TSPException {
        try {
            this.firstArchiveTimeStamp.validatePresent(eRSData, new Date());
            try {
                ERSArchiveTimeStampGenerator eRSArchiveTimeStampGenerator = new ERSArchiveTimeStampGenerator(digestCalculator);
                eRSArchiveTimeStampGenerator.addData(eRSData);
                eRSArchiveTimeStampGenerator.addPreviousChains(this.evidenceRecord.getArchiveTimeStampSequence());
                return new ERSEvidenceRecord(this.evidenceRecord.addArchiveTimeStamp(eRSArchiveTimeStampGenerator.generateArchiveTimeStamp(timeStampResponse).toASN1Structure(), true), this.digestCalculatorProvider);
            } catch (IOException e) {
                throw new ERSException(e.getMessage(), e);
            } catch (IllegalArgumentException e2) {
                throw new ERSException(e2.getMessage(), e2);
            }
        } catch (Exception unused) {
            throw new ERSException("attempt to hash renew on invalid data");
        }
    }

    public ERSEvidenceRecord renewTimeStamp(TimeStampResponse timeStampResponse) throws ERSException, TSPException {
        try {
            return new ERSEvidenceRecord(this.evidenceRecord.addArchiveTimeStamp(buildTspRenewalGenerator().generateArchiveTimeStamp(timeStampResponse).toASN1Structure(), false), this.digestCalculatorProvider);
        } catch (IllegalArgumentException e) {
            throw new ERSException(e.getMessage(), e);
        }
    }

    public EvidenceRecord toASN1Structure() {
        return this.evidenceRecord;
    }

    public void validate(SignerInformationVerifier signerInformationVerifier) throws TSPException {
        if (this.firstArchiveTimeStamp != this.lastArchiveTimeStamp) {
            ArchiveTimeStamp[] archiveTimeStamps = getArchiveTimeStamps();
            int i = 0;
            while (i != archiveTimeStamps.length - 1) {
                try {
                    this.lastArchiveTimeStamp.validatePresent(new ERSByteData(archiveTimeStamps[i].getTimeStamp().getEncoded(ASN1Encoding.DER)), this.lastArchiveTimeStamp.getGenTime());
                    i++;
                } catch (Exception e) {
                    throw new TSPException("unable to process previous ArchiveTimeStamps", e);
                }
            }
        }
        this.lastArchiveTimeStamp.validate(signerInformationVerifier);
    }

    public void validatePresent(ERSData eRSData, Date date) throws ERSException {
        this.firstArchiveTimeStamp.validatePresent(eRSData, date);
    }

    public void validatePresent(boolean z, byte[] bArr, Date date) throws ERSException {
        this.firstArchiveTimeStamp.validatePresent(z, bArr, date);
    }
}
