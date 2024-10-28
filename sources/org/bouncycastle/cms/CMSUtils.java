package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BEROctetStringGenerator;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DLSet;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.OtherRevocationInfoFormat;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.ocsp.OCSPResponse;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.io.Streams;
import org.bouncycastle.util.io.TeeInputStream;
import org.bouncycastle.util.io.TeeOutputStream;

class CMSUtils {
    private static final Set<String> des;
    private static final Set ecAlgs;
    private static final Set gostAlgs;
    private static final Set mqvAlgs;

    static {
        HashSet hashSet = new HashSet();
        des = hashSet;
        HashSet hashSet2 = new HashSet();
        mqvAlgs = hashSet2;
        HashSet hashSet3 = new HashSet();
        ecAlgs = hashSet3;
        HashSet hashSet4 = new HashSet();
        gostAlgs = hashSet4;
        hashSet.add("DES");
        hashSet.add("DESEDE");
        hashSet.add(OIWObjectIdentifiers.desCBC.getId());
        hashSet.add(PKCSObjectIdentifiers.des_EDE3_CBC.getId());
        hashSet.add(PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId());
        hashSet2.add(X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme);
        hashSet2.add(SECObjectIdentifiers.mqvSinglePass_sha224kdf_scheme);
        hashSet2.add(SECObjectIdentifiers.mqvSinglePass_sha256kdf_scheme);
        hashSet2.add(SECObjectIdentifiers.mqvSinglePass_sha384kdf_scheme);
        hashSet2.add(SECObjectIdentifiers.mqvSinglePass_sha512kdf_scheme);
        hashSet3.add(X9ObjectIdentifiers.dhSinglePass_cofactorDH_sha1kdf_scheme);
        hashSet3.add(X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme);
        hashSet3.add(SECObjectIdentifiers.dhSinglePass_cofactorDH_sha224kdf_scheme);
        hashSet3.add(SECObjectIdentifiers.dhSinglePass_stdDH_sha224kdf_scheme);
        hashSet3.add(SECObjectIdentifiers.dhSinglePass_cofactorDH_sha256kdf_scheme);
        hashSet3.add(SECObjectIdentifiers.dhSinglePass_stdDH_sha256kdf_scheme);
        hashSet3.add(SECObjectIdentifiers.dhSinglePass_cofactorDH_sha384kdf_scheme);
        hashSet3.add(SECObjectIdentifiers.dhSinglePass_stdDH_sha384kdf_scheme);
        hashSet3.add(SECObjectIdentifiers.dhSinglePass_cofactorDH_sha512kdf_scheme);
        hashSet3.add(SECObjectIdentifiers.dhSinglePass_stdDH_sha512kdf_scheme);
        hashSet4.add(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_ESDH);
        hashSet4.add(RosstandartObjectIdentifiers.id_tc26_agreement_gost_3410_12_256);
        hashSet4.add(RosstandartObjectIdentifiers.id_tc26_agreement_gost_3410_12_512);
    }

    CMSUtils() {
    }

    static void addDigestAlgs(Set<AlgorithmIdentifier> set, SignerInformation signerInformation, DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder) {
        set.add(CMSSignedHelper.INSTANCE.fixDigestAlgID(signerInformation.getDigestAlgorithmID(), digestAlgorithmIdentifierFinder));
        Iterator<SignerInformation> it = signerInformation.getCounterSignatures().iterator();
        while (it.hasNext()) {
            set.add(CMSSignedHelper.INSTANCE.fixDigestAlgID(it.next().getDigestAlgorithmID(), digestAlgorithmIdentifierFinder));
        }
    }

    static InputStream attachDigestsToInputStream(Collection collection, InputStream inputStream) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            inputStream = new TeeInputStream(inputStream, ((DigestCalculator) it.next()).getOutputStream());
        }
        return inputStream;
    }

    static OutputStream attachSignersToOutputStream(Collection collection, OutputStream outputStream) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            outputStream = getSafeTeeOutputStream(outputStream, ((SignerInfoGenerator) it.next()).getCalculatingOutputStream());
        }
        return outputStream;
    }

    static ASN1Set convertToDlSet(Set<AlgorithmIdentifier> set) {
        return new DLSet((ASN1Encodable[]) (AlgorithmIdentifier[]) set.toArray(new AlgorithmIdentifier[set.size()]));
    }

    static OutputStream createBEROctetOutputStream(OutputStream outputStream, int i, boolean z, int i2) throws IOException {
        BEROctetStringGenerator bEROctetStringGenerator = new BEROctetStringGenerator(outputStream, i, z);
        return i2 != 0 ? bEROctetStringGenerator.getOctetOutputStream(new byte[i2]) : bEROctetStringGenerator.getOctetOutputStream();
    }

    static ASN1Set createBerSetFromList(List list) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            aSN1EncodableVector.add((ASN1Encodable) it.next());
        }
        return new BERSet(aSN1EncodableVector);
    }

    static ASN1Set createDerSetFromList(List list) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            aSN1EncodableVector.add((ASN1Encodable) it.next());
        }
        return new DERSet(aSN1EncodableVector);
    }

    static ASN1Set createDlSetFromList(List list) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            aSN1EncodableVector.add((ASN1Encodable) it.next());
        }
        return new DLSet(aSN1EncodableVector);
    }

    static List getAttributeCertificatesFromStore(Store store) throws CMSException {
        ArrayList arrayList = new ArrayList();
        try {
            for (X509AttributeCertificateHolder aSN1Structure : store.getMatches((Selector) null)) {
                arrayList.add(new DERTaggedObject(false, 2, (ASN1Encodable) aSN1Structure.toASN1Structure()));
            }
            return arrayList;
        } catch (ClassCastException e) {
            throw new CMSException("error processing certs", e);
        }
    }

    static List getCRLsFromStore(Store store) throws CMSException {
        ArrayList arrayList = new ArrayList();
        try {
            for (Object next : store.getMatches((Selector) null)) {
                if (next instanceof X509CRLHolder) {
                    next = ((X509CRLHolder) next).toASN1Structure();
                } else if (next instanceof OtherRevocationInfoFormat) {
                    OtherRevocationInfoFormat instance = OtherRevocationInfoFormat.getInstance(next);
                    validateInfoFormat(instance);
                    arrayList.add(new DERTaggedObject(false, 1, (ASN1Encodable) instance));
                } else if (!(next instanceof ASN1TaggedObject)) {
                }
                arrayList.add(next);
            }
            return arrayList;
        } catch (ClassCastException e) {
            throw new CMSException("error processing certs", e);
        }
    }

    static List getCertificatesFromStore(Store store) throws CMSException {
        ArrayList arrayList = new ArrayList();
        try {
            for (X509CertificateHolder aSN1Structure : store.getMatches((Selector) null)) {
                arrayList.add(aSN1Structure.toASN1Structure());
            }
            return arrayList;
        } catch (ClassCastException e) {
            throw new CMSException("error processing certs", e);
        }
    }

    static Collection getOthersFromStore(ASN1ObjectIdentifier aSN1ObjectIdentifier, Store store) {
        ArrayList arrayList = new ArrayList();
        for (ASN1Encodable otherRevocationInfoFormat : store.getMatches((Selector) null)) {
            OtherRevocationInfoFormat otherRevocationInfoFormat2 = new OtherRevocationInfoFormat(aSN1ObjectIdentifier, otherRevocationInfoFormat);
            validateInfoFormat(otherRevocationInfoFormat2);
            arrayList.add(new DERTaggedObject(false, 1, (ASN1Encodable) otherRevocationInfoFormat2));
        }
        return arrayList;
    }

    static OutputStream getSafeOutputStream(OutputStream outputStream) {
        return outputStream == null ? new NullOutputStream() : outputStream;
    }

    static OutputStream getSafeTeeOutputStream(OutputStream outputStream, OutputStream outputStream2) {
        return outputStream == null ? getSafeOutputStream(outputStream2) : outputStream2 == null ? getSafeOutputStream(outputStream) : new TeeOutputStream(outputStream, outputStream2);
    }

    static boolean isDES(String str) {
        return des.contains(Strings.toUpperCase(str));
    }

    static boolean isEC(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return ecAlgs.contains(aSN1ObjectIdentifier);
    }

    static boolean isEquivalent(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) {
        if (algorithmIdentifier == null || algorithmIdentifier2 == null || !algorithmIdentifier.getAlgorithm().equals((ASN1Primitive) algorithmIdentifier2.getAlgorithm())) {
            return false;
        }
        ASN1Encodable parameters = algorithmIdentifier.getParameters();
        ASN1Encodable parameters2 = algorithmIdentifier2.getParameters();
        return parameters != null ? parameters.equals(parameters2) || (parameters.equals(DERNull.INSTANCE) && parameters2 == null) : parameters2 == null || parameters2.equals(DERNull.INSTANCE);
    }

    static boolean isGOST(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return gostAlgs.contains(aSN1ObjectIdentifier);
    }

    static boolean isMQV(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return mqvAlgs.contains(aSN1ObjectIdentifier);
    }

    static boolean isRFC2631(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return aSN1ObjectIdentifier.equals((ASN1Primitive) PKCSObjectIdentifiers.id_alg_ESDH) || aSN1ObjectIdentifier.equals((ASN1Primitive) PKCSObjectIdentifiers.id_alg_SSDH);
    }

    static ContentInfo readContentInfo(InputStream inputStream) throws CMSException {
        return readContentInfo(new ASN1InputStream(inputStream));
    }

    private static ContentInfo readContentInfo(ASN1InputStream aSN1InputStream) throws CMSException {
        try {
            ContentInfo instance = ContentInfo.getInstance(aSN1InputStream.readObject());
            if (instance != null) {
                return instance;
            }
            throw new CMSException("No content found.");
        } catch (IOException e) {
            throw new CMSException("IOException reading content.", e);
        } catch (ClassCastException e2) {
            throw new CMSException("Malformed content.", e2);
        } catch (IllegalArgumentException e3) {
            throw new CMSException("Malformed content.", e3);
        }
    }

    static ContentInfo readContentInfo(byte[] bArr) throws CMSException {
        return readContentInfo(new ASN1InputStream(bArr));
    }

    public static byte[] streamToByteArray(InputStream inputStream) throws IOException {
        return Streams.readAll(inputStream);
    }

    public static byte[] streamToByteArray(InputStream inputStream, int i) throws IOException {
        return Streams.readAllLimited(inputStream, i);
    }

    static void validateInfoFormat(OtherRevocationInfoFormat otherRevocationInfoFormat) {
        if (CMSObjectIdentifiers.id_ri_ocsp_response.equals((ASN1Primitive) otherRevocationInfoFormat.getInfoFormat()) && OCSPResponse.getInstance(otherRevocationInfoFormat.getInfo()).getResponseStatus().getIntValue() != 0) {
            throw new IllegalArgumentException("cannot add unsuccessful OCSP response to CMS SignedData");
        }
    }
}
