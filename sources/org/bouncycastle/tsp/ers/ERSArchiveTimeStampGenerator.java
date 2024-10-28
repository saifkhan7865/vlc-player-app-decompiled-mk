package org.bouncycastle.tsp.ers;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.tsp.ArchiveTimeStamp;
import org.bouncycastle.asn1.tsp.ArchiveTimeStampSequence;
import org.bouncycastle.asn1.tsp.PartialHashtree;
import org.bouncycastle.asn1.tsp.TSTInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TimeStampRequest;
import org.bouncycastle.tsp.TimeStampRequestGenerator;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.util.Arrays;

public class ERSArchiveTimeStampGenerator {
    private List<ERSData> dataObjects = new ArrayList();
    private final DigestCalculator digCalc;
    private byte[] previousChainHash;
    private ERSRootNodeCalculator rootNodeCalculator = new BinaryTreeRootCalculator();

    private static class IndexedPartialHashtree extends PartialHashtree {
        final int order;

        private IndexedPartialHashtree(int i, byte[] bArr) {
            super(bArr);
            this.order = i;
        }

        private IndexedPartialHashtree(int i, byte[][] bArr) {
            super(bArr);
            this.order = i;
        }
    }

    public ERSArchiveTimeStampGenerator(DigestCalculator digestCalculator) {
        this.digCalc = digestCalculator;
    }

    private IndexedPartialHashtree[] getPartialHashtrees() {
        List<IndexedHash> buildIndexedHashList = ERSUtil.buildIndexedHashList(this.digCalc, this.dataObjects, this.previousChainHash);
        IndexedPartialHashtree[] indexedPartialHashtreeArr = new IndexedPartialHashtree[buildIndexedHashList.size()];
        HashSet hashSet = new HashSet();
        for (int i = 0; i != this.dataObjects.size(); i++) {
            if (this.dataObjects.get(i) instanceof ERSDataGroup) {
                hashSet.add((ERSDataGroup) this.dataObjects.get(i));
            }
        }
        for (int i2 = 0; i2 != buildIndexedHashList.size(); i2++) {
            byte[] bArr = buildIndexedHashList.get(i2).digest;
            ERSData eRSData = this.dataObjects.get(buildIndexedHashList.get(i2).order);
            if (eRSData instanceof ERSDataGroup) {
                List<byte[]> hashes = ((ERSDataGroup) eRSData).getHashes(this.digCalc, this.previousChainHash);
                indexedPartialHashtreeArr[i2] = new IndexedPartialHashtree(buildIndexedHashList.get(i2).order, (byte[][]) hashes.toArray(new byte[hashes.size()][]));
            } else {
                indexedPartialHashtreeArr[i2] = new IndexedPartialHashtree(buildIndexedHashList.get(i2).order, bArr);
            }
        }
        return indexedPartialHashtreeArr;
    }

    public void addAllData(List<ERSData> list) {
        this.dataObjects.addAll(list);
    }

    public void addData(ERSData eRSData) {
        this.dataObjects.add(eRSData);
    }

    /* access modifiers changed from: package-private */
    public void addPreviousChains(ArchiveTimeStampSequence archiveTimeStampSequence) throws IOException {
        OutputStream outputStream = this.digCalc.getOutputStream();
        outputStream.write(archiveTimeStampSequence.getEncoded(ASN1Encoding.DER));
        outputStream.close();
        this.previousChainHash = this.digCalc.getDigest();
    }

    public ERSArchiveTimeStamp generateArchiveTimeStamp(TimeStampResponse timeStampResponse) throws TSPException, ERSException {
        IndexedPartialHashtree[] partialHashtrees = getPartialHashtrees();
        if (partialHashtrees.length == 1) {
            byte[] computeRootHash = this.rootNodeCalculator.computeRootHash(this.digCalc, partialHashtrees);
            if (timeStampResponse.getStatus() == 0) {
                TSTInfo aSN1Structure = timeStampResponse.getTimeStampToken().getTimeStampInfo().toASN1Structure();
                if (!aSN1Structure.getMessageImprint().getHashAlgorithm().equals(this.digCalc.getAlgorithmIdentifier())) {
                    throw new ERSException("time stamp imprint for wrong algorithm");
                } else if (Arrays.areEqual(aSN1Structure.getMessageImprint().getHashedMessage(), computeRootHash)) {
                    return partialHashtrees[0].getValueCount() == 1 ? new ERSArchiveTimeStamp(new ArchiveTimeStamp((AlgorithmIdentifier) null, (PartialHashtree[]) null, timeStampResponse.getTimeStampToken().toCMSSignedData().toASN1Structure()), this.digCalc) : new ERSArchiveTimeStamp(new ArchiveTimeStamp(this.digCalc.getAlgorithmIdentifier(), partialHashtrees, timeStampResponse.getTimeStampToken().toCMSSignedData().toASN1Structure()), this.digCalc);
                } else {
                    throw new ERSException("time stamp imprint for wrong root hash");
                }
            } else {
                throw new TSPException("TSP response error status: " + timeStampResponse.getStatusString());
            }
        } else {
            throw new ERSException("multiple reduced hash trees found");
        }
    }

    public List<ERSArchiveTimeStamp> generateArchiveTimeStamps(TimeStampResponse timeStampResponse) throws TSPException, ERSException {
        IndexedPartialHashtree[] partialHashtrees = getPartialHashtrees();
        byte[] computeRootHash = this.rootNodeCalculator.computeRootHash(this.digCalc, partialHashtrees);
        if (timeStampResponse.getStatus() == 0) {
            TSTInfo aSN1Structure = timeStampResponse.getTimeStampToken().getTimeStampInfo().toASN1Structure();
            if (!aSN1Structure.getMessageImprint().getHashAlgorithm().equals(this.digCalc.getAlgorithmIdentifier())) {
                throw new ERSException("time stamp imprint for wrong algorithm");
            } else if (Arrays.areEqual(aSN1Structure.getMessageImprint().getHashedMessage(), computeRootHash)) {
                ContentInfo aSN1Structure2 = timeStampResponse.getTimeStampToken().toCMSSignedData().toASN1Structure();
                ArrayList arrayList = new ArrayList();
                if (partialHashtrees.length == 1 && partialHashtrees[0].getValueCount() == 1) {
                    arrayList.add(new ERSArchiveTimeStamp(new ArchiveTimeStamp((AlgorithmIdentifier) null, (PartialHashtree[]) null, aSN1Structure2), this.digCalc));
                } else {
                    ERSArchiveTimeStamp[] eRSArchiveTimeStampArr = new ERSArchiveTimeStamp[partialHashtrees.length];
                    for (int i = 0; i != partialHashtrees.length; i++) {
                        eRSArchiveTimeStampArr[partialHashtrees[i].order] = new ERSArchiveTimeStamp(new ArchiveTimeStamp(this.digCalc.getAlgorithmIdentifier(), this.rootNodeCalculator.computePathToRoot(this.digCalc, partialHashtrees[i], i), aSN1Structure2), this.digCalc);
                    }
                    for (int i2 = 0; i2 != partialHashtrees.length; i2++) {
                        arrayList.add(eRSArchiveTimeStampArr[i2]);
                    }
                }
                return arrayList;
            } else {
                throw new ERSException("time stamp imprint for wrong root hash");
            }
        } else {
            throw new TSPException("TSP response error status: " + timeStampResponse.getStatusString());
        }
    }

    public TimeStampRequest generateTimeStampRequest(TimeStampRequestGenerator timeStampRequestGenerator) throws TSPException, IOException {
        return timeStampRequestGenerator.generate(this.digCalc.getAlgorithmIdentifier(), this.rootNodeCalculator.computeRootHash(this.digCalc, getPartialHashtrees()));
    }

    public TimeStampRequest generateTimeStampRequest(TimeStampRequestGenerator timeStampRequestGenerator, BigInteger bigInteger) throws TSPException, IOException {
        return timeStampRequestGenerator.generate(this.digCalc.getAlgorithmIdentifier(), this.rootNodeCalculator.computeRootHash(this.digCalc, getPartialHashtrees()), bigInteger);
    }
}
