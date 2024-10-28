package org.bouncycastle.tsp.ers;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import org.bouncycastle.asn1.tsp.ArchiveTimeStamp;
import org.bouncycastle.asn1.tsp.PartialHashtree;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Store;

public class ERSArchiveTimeStamp {
    private final ArchiveTimeStamp archiveTimeStamp;
    private final DigestCalculator digCalc;
    private final byte[] previousChainsDigest;
    private ERSRootNodeCalculator rootNodeCalculator;
    private final TimeStampToken timeStampToken;

    ERSArchiveTimeStamp(ArchiveTimeStamp archiveTimeStamp2, DigestCalculator digestCalculator) throws TSPException, ERSException {
        this.rootNodeCalculator = new BinaryTreeRootCalculator();
        this.previousChainsDigest = null;
        try {
            this.archiveTimeStamp = archiveTimeStamp2;
            this.timeStampToken = new TimeStampToken(archiveTimeStamp2.getTimeStamp());
            this.digCalc = digestCalculator;
        } catch (IOException e) {
            throw new ERSException(e.getMessage(), e);
        }
    }

    public ERSArchiveTimeStamp(ArchiveTimeStamp archiveTimeStamp2, DigestCalculatorProvider digestCalculatorProvider) throws TSPException, ERSException {
        this.rootNodeCalculator = new BinaryTreeRootCalculator();
        this.previousChainsDigest = null;
        try {
            this.archiveTimeStamp = archiveTimeStamp2;
            this.timeStampToken = new TimeStampToken(archiveTimeStamp2.getTimeStamp());
            this.digCalc = digestCalculatorProvider.get(archiveTimeStamp2.getDigestAlgorithmIdentifier());
        } catch (IOException e) {
            throw new ERSException(e.getMessage(), e);
        } catch (OperatorCreationException e2) {
            throw new ERSException(e2.getMessage(), e2);
        }
    }

    ERSArchiveTimeStamp(byte[] bArr, ArchiveTimeStamp archiveTimeStamp2, DigestCalculatorProvider digestCalculatorProvider) throws TSPException, ERSException {
        this.rootNodeCalculator = new BinaryTreeRootCalculator();
        this.previousChainsDigest = bArr;
        try {
            this.archiveTimeStamp = archiveTimeStamp2;
            this.timeStampToken = new TimeStampToken(archiveTimeStamp2.getTimeStamp());
            this.digCalc = digestCalculatorProvider.get(archiveTimeStamp2.getDigestAlgorithmIdentifier());
        } catch (IOException e) {
            throw new ERSException(e.getMessage(), e);
        } catch (OperatorCreationException e2) {
            throw new ERSException(e2.getMessage(), e2);
        }
    }

    public ERSArchiveTimeStamp(byte[] bArr, DigestCalculatorProvider digestCalculatorProvider) throws TSPException, ERSException {
        this(ArchiveTimeStamp.getInstance(bArr), digestCalculatorProvider);
    }

    public static ERSArchiveTimeStamp fromTimeStampToken(TimeStampToken timeStampToken2, DigestCalculatorProvider digestCalculatorProvider) throws TSPException, ERSException {
        return new ERSArchiveTimeStamp(new ArchiveTimeStamp(timeStampToken2.toCMSSignedData().toASN1Structure()), digestCalculatorProvider);
    }

    /* access modifiers changed from: package-private */
    public void checkContainsHashValue(boolean z, byte[] bArr, DigestCalculator digestCalculator) throws ArchiveTimeStampValidationException {
        PartialHashtree[] reducedHashTree = this.archiveTimeStamp.getReducedHashTree();
        if (reducedHashTree != null) {
            PartialHashtree partialHashtree = reducedHashTree[0];
            if (!z && partialHashtree.containsHash(bArr)) {
                return;
            }
            if (partialHashtree.getValueCount() <= 1 || !Arrays.areEqual(bArr, ERSUtil.calculateBranchHash(digestCalculator, partialHashtree.getValues()))) {
                throw new ArchiveTimeStampValidationException("object hash not found");
            }
        } else if (!Arrays.areEqual(bArr, this.timeStampToken.getTimeStampInfo().getMessageImprintDigest())) {
            throw new ArchiveTimeStampValidationException("object hash not found in wrapped timestamp");
        }
    }

    /* access modifiers changed from: package-private */
    public void checkTimeStampValid(TimeStampToken timeStampToken2, byte[] bArr) throws ArchiveTimeStampValidationException {
        if (bArr != null && !Arrays.areEqual(bArr, timeStampToken2.getTimeStampInfo().getMessageImprintDigest())) {
            throw new ArchiveTimeStampValidationException("timestamp hash does not match root");
        }
    }

    public AlgorithmIdentifier getDigestAlgorithmIdentifier() {
        return this.archiveTimeStamp.getDigestAlgorithmIdentifier();
    }

    public byte[] getEncoded() throws IOException {
        return this.archiveTimeStamp.getEncoded();
    }

    public Date getExpiryTime() {
        X509CertificateHolder signingCertificate = getSigningCertificate();
        if (signingCertificate != null) {
            return signingCertificate.getNotAfter();
        }
        return null;
    }

    public Date getGenTime() {
        return this.timeStampToken.getTimeStampInfo().getGenTime();
    }

    public X509CertificateHolder getSigningCertificate() {
        Store<X509CertificateHolder> certificates = this.timeStampToken.getCertificates();
        if (certificates == null) {
            return null;
        }
        Collection<X509CertificateHolder> matches = certificates.getMatches(this.timeStampToken.getSID());
        if (!matches.isEmpty()) {
            return matches.iterator().next();
        }
        return null;
    }

    public TimeStampToken getTimeStampToken() {
        return this.timeStampToken;
    }

    public boolean isContaining(ERSData eRSData, Date date) throws ERSException {
        if (!this.timeStampToken.getTimeStampInfo().getGenTime().after(date)) {
            try {
                validatePresent(eRSData, date);
                return true;
            } catch (Exception unused) {
                return false;
            }
        } else {
            throw new ArchiveTimeStampValidationException("timestamp generation time is in the future");
        }
    }

    public ArchiveTimeStamp toASN1Structure() {
        return this.archiveTimeStamp;
    }

    public void validate(SignerInformationVerifier signerInformationVerifier) throws TSPException {
        this.timeStampToken.validate(signerInformationVerifier);
    }

    public void validatePresent(ERSData eRSData, Date date) throws ERSException {
        validatePresent(eRSData instanceof ERSDataGroup, eRSData.getHash(this.digCalc, this.previousChainsDigest), date);
    }

    public void validatePresent(boolean z, byte[] bArr, Date date) throws ERSException {
        if (!this.timeStampToken.getTimeStampInfo().getGenTime().after(date)) {
            checkContainsHashValue(z, bArr, this.digCalc);
            if (this.archiveTimeStamp.getReducedHashTree() != null) {
                bArr = this.rootNodeCalculator.recoverRootHash(this.digCalc, this.archiveTimeStamp.getReducedHashTree());
            }
            checkTimeStampValid(this.timeStampToken, bArr);
            return;
        }
        throw new ArchiveTimeStampValidationException("timestamp generation time is in the future");
    }
}
