package org.bouncycastle.openssl;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.DSAParameter;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.io.pem.PemGenerationException;
import org.bouncycastle.util.io.pem.PemHeader;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemObjectGenerator;
import org.fusesource.jansi.AnsiRenderer;

public class MiscPEMGenerator implements PemObjectGenerator {
    private static final ASN1ObjectIdentifier[] dsaOids = {X9ObjectIdentifiers.id_dsa, OIWObjectIdentifiers.dsaWithSHA1};
    private static final byte[] hexEncodingTable = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};
    private final PEMEncryptor encryptor;
    private final Object obj;

    public MiscPEMGenerator(Object obj2) {
        this.obj = obj2;
        this.encryptor = null;
    }

    public MiscPEMGenerator(Object obj2, PEMEncryptor pEMEncryptor) {
        this.obj = obj2;
        this.encryptor = pEMEncryptor;
    }

    private PemObject createPemObject(Object obj2) throws IOException {
        byte[] bArr;
        String str;
        if (obj2 instanceof PemObject) {
            return (PemObject) obj2;
        }
        if (obj2 instanceof PemObjectGenerator) {
            return ((PemObjectGenerator) obj2).generate();
        }
        if (obj2 instanceof X509CertificateHolder) {
            bArr = ((X509CertificateHolder) obj2).getEncoded();
            str = PEMParser.TYPE_CERTIFICATE;
        } else if (obj2 instanceof X509CRLHolder) {
            bArr = ((X509CRLHolder) obj2).getEncoded();
            str = PEMParser.TYPE_X509_CRL;
        } else if (obj2 instanceof X509TrustedCertificateBlock) {
            bArr = ((X509TrustedCertificateBlock) obj2).getEncoded();
            str = PEMParser.TYPE_TRUSTED_CERTIFICATE;
        } else if (obj2 instanceof PrivateKeyInfo) {
            PrivateKeyInfo privateKeyInfo = (PrivateKeyInfo) obj2;
            ASN1ObjectIdentifier algorithm = privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm();
            if (algorithm.equals((ASN1Primitive) PKCSObjectIdentifiers.rsaEncryption)) {
                bArr = privateKeyInfo.parsePrivateKey().toASN1Primitive().getEncoded();
                str = PEMParser.TYPE_RSA_PRIVATE_KEY;
            } else {
                ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr = dsaOids;
                if (algorithm.equals((ASN1Primitive) aSN1ObjectIdentifierArr[0]) || algorithm.equals((ASN1Primitive) aSN1ObjectIdentifierArr[1])) {
                    DSAParameter instance = DSAParameter.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
                    ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                    aSN1EncodableVector.add(new ASN1Integer(0));
                    aSN1EncodableVector.add(new ASN1Integer(instance.getP()));
                    aSN1EncodableVector.add(new ASN1Integer(instance.getQ()));
                    aSN1EncodableVector.add(new ASN1Integer(instance.getG()));
                    BigInteger value = ASN1Integer.getInstance(privateKeyInfo.parsePrivateKey()).getValue();
                    aSN1EncodableVector.add(new ASN1Integer(instance.getG().modPow(value, instance.getP())));
                    aSN1EncodableVector.add(new ASN1Integer(value));
                    bArr = new DERSequence(aSN1EncodableVector).getEncoded();
                    str = PEMParser.TYPE_DSA_PRIVATE_KEY;
                } else if (algorithm.equals((ASN1Primitive) X9ObjectIdentifiers.id_ecPublicKey)) {
                    bArr = privateKeyInfo.parsePrivateKey().toASN1Primitive().getEncoded();
                    str = PEMParser.TYPE_EC_PRIVATE_KEY;
                } else {
                    bArr = privateKeyInfo.getEncoded();
                    str = PEMParser.TYPE_PRIVATE_KEY;
                }
            }
        } else if (obj2 instanceof SubjectPublicKeyInfo) {
            bArr = ((SubjectPublicKeyInfo) obj2).getEncoded();
            str = PEMParser.TYPE_PUBLIC_KEY;
        } else if (obj2 instanceof X509AttributeCertificateHolder) {
            bArr = ((X509AttributeCertificateHolder) obj2).getEncoded();
            str = PEMParser.TYPE_ATTRIBUTE_CERTIFICATE;
        } else if (obj2 instanceof PKCS10CertificationRequest) {
            bArr = ((PKCS10CertificationRequest) obj2).getEncoded();
            str = PEMParser.TYPE_CERTIFICATE_REQUEST;
        } else if (obj2 instanceof PKCS8EncryptedPrivateKeyInfo) {
            bArr = ((PKCS8EncryptedPrivateKeyInfo) obj2).getEncoded();
            str = PEMParser.TYPE_ENCRYPTED_PRIVATE_KEY;
        } else if (obj2 instanceof ContentInfo) {
            bArr = ((ContentInfo) obj2).getEncoded();
            str = PEMParser.TYPE_PKCS7;
        } else {
            throw new PemGenerationException("unknown object passed - can't encode.");
        }
        PEMEncryptor pEMEncryptor = this.encryptor;
        if (pEMEncryptor == null) {
            return new PemObject(str, bArr);
        }
        String upperCase = Strings.toUpperCase(pEMEncryptor.getAlgorithm());
        if (upperCase.equals("DESEDE")) {
            upperCase = "DES-EDE3-CBC";
        }
        byte[] iv = this.encryptor.getIV();
        byte[] encrypt = this.encryptor.encrypt(bArr);
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new PemHeader("Proc-Type", "4,ENCRYPTED"));
        arrayList.add(new PemHeader("DEK-Info", upperCase + AnsiRenderer.CODE_LIST_SEPARATOR + getHexEncoded(iv)));
        return new PemObject(str, arrayList, encrypt);
    }

    private String getHexEncoded(byte[] bArr) throws IOException {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i != bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            byte[] bArr2 = hexEncodingTable;
            cArr[i2] = (char) bArr2[(b & 255) >>> 4];
            cArr[i2 + 1] = (char) bArr2[b & Ascii.SI];
        }
        return new String(cArr);
    }

    public PemObject generate() throws PemGenerationException {
        try {
            return createPemObject(this.obj);
        } catch (IOException e) {
            throw new PemGenerationException("encoding exception: " + e.getMessage(), e);
        }
    }
}
