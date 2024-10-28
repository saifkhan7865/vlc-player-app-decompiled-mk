package org.bouncycastle.openssl;

import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.asn1.pkcs.RSAPublicKey;
import org.bouncycastle.asn1.sec.ECPrivateKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DSAParameter;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.io.pem.PemHeader;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemObjectParser;
import org.bouncycastle.util.io.pem.PemReader;
import org.fusesource.jansi.AnsiRenderer;

public class PEMParser extends PemReader {
    public static final String TYPE_ATTRIBUTE_CERTIFICATE = "ATTRIBUTE CERTIFICATE";
    public static final String TYPE_CERTIFICATE = "CERTIFICATE";
    public static final String TYPE_CERTIFICATE_REQUEST = "CERTIFICATE REQUEST";
    public static final String TYPE_CMS = "CMS";
    public static final String TYPE_DSA_PRIVATE_KEY = "DSA PRIVATE KEY";
    public static final String TYPE_EC_PARAMETERS = "EC PARAMETERS";
    public static final String TYPE_EC_PRIVATE_KEY = "EC PRIVATE KEY";
    public static final String TYPE_ENCRYPTED_PRIVATE_KEY = "ENCRYPTED PRIVATE KEY";
    public static final String TYPE_NEW_CERTIFICATE_REQUEST = "NEW CERTIFICATE REQUEST";
    public static final String TYPE_PKCS7 = "PKCS7";
    public static final String TYPE_PRIVATE_KEY = "PRIVATE KEY";
    public static final String TYPE_PUBLIC_KEY = "PUBLIC KEY";
    public static final String TYPE_RSA_PRIVATE_KEY = "RSA PRIVATE KEY";
    public static final String TYPE_RSA_PUBLIC_KEY = "RSA PUBLIC KEY";
    public static final String TYPE_TRUSTED_CERTIFICATE = "TRUSTED CERTIFICATE";
    public static final String TYPE_X509_CERTIFICATE = "X509 CERTIFICATE";
    public static final String TYPE_X509_CRL = "X509 CRL";
    protected final Map parsers;

    private static class DSAKeyPairParser implements PEMKeyPairParser {
        private DSAKeyPairParser() {
        }

        public PEMKeyPair parse(byte[] bArr) throws IOException {
            try {
                ASN1Sequence instance = ASN1Sequence.getInstance(bArr);
                if (instance.size() == 6) {
                    ASN1Integer instance2 = ASN1Integer.getInstance(instance.getObjectAt(1));
                    ASN1Integer instance3 = ASN1Integer.getInstance(instance.getObjectAt(2));
                    ASN1Integer instance4 = ASN1Integer.getInstance(instance.getObjectAt(3));
                    return new PEMKeyPair(new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, new DSAParameter(instance2.getValue(), instance3.getValue(), instance4.getValue())), (ASN1Encodable) ASN1Integer.getInstance(instance.getObjectAt(4))), new PrivateKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_dsa, new DSAParameter(instance2.getValue(), instance3.getValue(), instance4.getValue())), ASN1Integer.getInstance(instance.getObjectAt(5))));
                }
                throw new PEMException("malformed sequence in DSA private key");
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                throw new PEMException("problem creating DSA private key: " + e2.toString(), e2);
            }
        }
    }

    private static class ECCurveParamsParser implements PemObjectParser {
        private ECCurveParamsParser() {
        }

        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                ASN1Primitive fromByteArray = ASN1Primitive.fromByteArray(pemObject.getContent());
                if (fromByteArray instanceof ASN1ObjectIdentifier) {
                    return ASN1Primitive.fromByteArray(pemObject.getContent());
                }
                if (fromByteArray instanceof ASN1Sequence) {
                    return X9ECParameters.getInstance(fromByteArray);
                }
                return null;
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                throw new PEMException("exception extracting EC named curve: " + e2.toString());
            }
        }
    }

    private static class ECDSAKeyPairParser implements PEMKeyPairParser {
        private ECDSAKeyPairParser() {
        }

        public PEMKeyPair parse(byte[] bArr) throws IOException {
            try {
                ECPrivateKey instance = ECPrivateKey.getInstance(ASN1Sequence.getInstance(bArr));
                AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, instance.getParametersObject());
                PrivateKeyInfo privateKeyInfo = new PrivateKeyInfo(algorithmIdentifier, instance);
                return instance.getPublicKey() != null ? new PEMKeyPair(new SubjectPublicKeyInfo(algorithmIdentifier, instance.getPublicKey().getBytes()), privateKeyInfo) : new PEMKeyPair((SubjectPublicKeyInfo) null, privateKeyInfo);
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                throw new PEMException("problem creating EC private key: " + e2.toString(), e2);
            }
        }
    }

    private static class EncryptedPrivateKeyParser implements PemObjectParser {
        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                return new PKCS8EncryptedPrivateKeyInfo(EncryptedPrivateKeyInfo.getInstance(pemObject.getContent()));
            } catch (Exception e) {
                throw new PEMException("problem parsing ENCRYPTED PRIVATE KEY: " + e.toString(), e);
            }
        }
    }

    private static class KeyPairParser implements PemObjectParser {
        private final PEMKeyPairParser pemKeyPairParser;

        public KeyPairParser(PEMKeyPairParser pEMKeyPairParser) {
            this.pemKeyPairParser = pEMKeyPairParser;
        }

        public Object parseObject(PemObject pemObject) throws IOException {
            boolean z = false;
            String str = null;
            for (PemHeader pemHeader : pemObject.getHeaders()) {
                if (pemHeader.getName().equals("Proc-Type") && pemHeader.getValue().equals("4,ENCRYPTED")) {
                    z = true;
                } else if (pemHeader.getName().equals("DEK-Info")) {
                    str = pemHeader.getValue();
                }
            }
            byte[] content = pemObject.getContent();
            if (!z) {
                return this.pemKeyPairParser.parse(content);
            }
            try {
                StringTokenizer stringTokenizer = new StringTokenizer(str, AnsiRenderer.CODE_LIST_SEPARATOR);
                return new PEMEncryptedKeyPair(stringTokenizer.nextToken(), Hex.decode(stringTokenizer.nextToken()), content, this.pemKeyPairParser);
            } catch (IOException e) {
                if (z) {
                    throw new PEMException("exception decoding - please check password and data.", e);
                }
                throw new PEMException(e.getMessage(), e);
            } catch (IllegalArgumentException e2) {
                if (z) {
                    throw new PEMException("exception decoding - please check password and data.", e2);
                }
                throw new PEMException(e2.getMessage(), e2);
            }
        }
    }

    private static class PKCS10CertificationRequestParser implements PemObjectParser {
        private PKCS10CertificationRequestParser() {
        }

        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                return new PKCS10CertificationRequest(pemObject.getContent());
            } catch (Exception e) {
                throw new PEMException("problem parsing certrequest: " + e.toString(), e);
            }
        }
    }

    private static class PKCS7Parser implements PemObjectParser {
        private PKCS7Parser() {
        }

        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                return ContentInfo.getInstance(new ASN1InputStream(pemObject.getContent()).readObject());
            } catch (Exception e) {
                throw new PEMException("problem parsing PKCS7 object: " + e.toString(), e);
            }
        }
    }

    private static class PrivateKeyParser implements PemObjectParser {
        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                return PrivateKeyInfo.getInstance(pemObject.getContent());
            } catch (Exception e) {
                throw new PEMException("problem parsing PRIVATE KEY: " + e.toString(), e);
            }
        }
    }

    private static class PublicKeyParser implements PemObjectParser {
        public Object parseObject(PemObject pemObject) throws IOException {
            return SubjectPublicKeyInfo.getInstance(pemObject.getContent());
        }
    }

    private static class RSAKeyPairParser implements PEMKeyPairParser {
        private RSAKeyPairParser() {
        }

        public PEMKeyPair parse(byte[] bArr) throws IOException {
            try {
                ASN1Sequence instance = ASN1Sequence.getInstance(bArr);
                if (instance.size() == 9) {
                    RSAPrivateKey instance2 = RSAPrivateKey.getInstance(instance);
                    RSAPublicKey rSAPublicKey = new RSAPublicKey(instance2.getModulus(), instance2.getPublicExponent());
                    AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE);
                    return new PEMKeyPair(new SubjectPublicKeyInfo(algorithmIdentifier, (ASN1Encodable) rSAPublicKey), new PrivateKeyInfo(algorithmIdentifier, instance2));
                }
                throw new PEMException("malformed sequence in RSA private key");
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                throw new PEMException("problem creating RSA private key: " + e2.toString(), e2);
            }
        }
    }

    private static class RSAPublicKeyParser implements PemObjectParser {
        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                return new SubjectPublicKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE), (ASN1Encodable) RSAPublicKey.getInstance(pemObject.getContent()));
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                throw new PEMException("problem extracting key: " + e2.toString(), e2);
            }
        }
    }

    private static class X509AttributeCertificateParser implements PemObjectParser {
        private X509AttributeCertificateParser() {
        }

        public Object parseObject(PemObject pemObject) throws IOException {
            return new X509AttributeCertificateHolder(pemObject.getContent());
        }
    }

    private static class X509CRLParser implements PemObjectParser {
        private X509CRLParser() {
        }

        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                return new X509CRLHolder(pemObject.getContent());
            } catch (Exception e) {
                throw new PEMException("problem parsing cert: " + e.toString(), e);
            }
        }
    }

    private static class X509CertificateParser implements PemObjectParser {
        private X509CertificateParser() {
        }

        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                return new X509CertificateHolder(pemObject.getContent());
            } catch (Exception e) {
                throw new PEMException("problem parsing cert: " + e.toString(), e);
            }
        }
    }

    private static class X509TrustedCertificateParser implements PemObjectParser {
        private X509TrustedCertificateParser() {
        }

        public Object parseObject(PemObject pemObject) throws IOException {
            try {
                return new X509TrustedCertificateBlock(pemObject.getContent());
            } catch (Exception e) {
                throw new PEMException("problem parsing cert: " + e.toString(), e);
            }
        }
    }

    public PEMParser(Reader reader) {
        super(reader);
        HashMap hashMap = new HashMap();
        this.parsers = hashMap;
        hashMap.put(TYPE_CERTIFICATE_REQUEST, new PKCS10CertificationRequestParser());
        hashMap.put(TYPE_NEW_CERTIFICATE_REQUEST, new PKCS10CertificationRequestParser());
        hashMap.put(TYPE_CERTIFICATE, new X509CertificateParser());
        hashMap.put(TYPE_TRUSTED_CERTIFICATE, new X509TrustedCertificateParser());
        hashMap.put(TYPE_X509_CERTIFICATE, new X509CertificateParser());
        hashMap.put(TYPE_X509_CRL, new X509CRLParser());
        hashMap.put(TYPE_PKCS7, new PKCS7Parser());
        hashMap.put(TYPE_CMS, new PKCS7Parser());
        hashMap.put(TYPE_ATTRIBUTE_CERTIFICATE, new X509AttributeCertificateParser());
        hashMap.put(TYPE_EC_PARAMETERS, new ECCurveParamsParser());
        hashMap.put(TYPE_PUBLIC_KEY, new PublicKeyParser());
        hashMap.put(TYPE_RSA_PUBLIC_KEY, new RSAPublicKeyParser());
        hashMap.put(TYPE_RSA_PRIVATE_KEY, new KeyPairParser(new RSAKeyPairParser()));
        hashMap.put(TYPE_DSA_PRIVATE_KEY, new KeyPairParser(new DSAKeyPairParser()));
        hashMap.put(TYPE_EC_PRIVATE_KEY, new KeyPairParser(new ECDSAKeyPairParser()));
        hashMap.put(TYPE_ENCRYPTED_PRIVATE_KEY, new EncryptedPrivateKeyParser());
        hashMap.put(TYPE_PRIVATE_KEY, new PrivateKeyParser());
    }

    public Set<String> getSupportedTypes() {
        return Collections.unmodifiableSet(this.parsers.keySet());
    }

    public Object readObject() throws IOException {
        PemObject readPemObject = readPemObject();
        if (readPemObject == null) {
            return null;
        }
        String type = readPemObject.getType();
        Object obj = this.parsers.get(type);
        if (obj != null) {
            return ((PemObjectParser) obj).parseObject(readPemObject);
        }
        throw new IOException("unrecognised object: " + type);
    }
}
