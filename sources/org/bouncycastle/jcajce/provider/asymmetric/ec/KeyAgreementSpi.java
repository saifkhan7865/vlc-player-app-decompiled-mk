package org.bouncycastle.jcajce.provider.asymmetric.ec;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PublicKey;
import org.bouncycastle.asn1.x9.X9IntegerConverter;
import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.agreement.ECDHBasicAgreement;
import org.bouncycastle.crypto.agreement.ECDHCBasicAgreement;
import org.bouncycastle.crypto.agreement.ECDHCUnifiedAgreement;
import org.bouncycastle.crypto.agreement.ECMQVBasicAgreement;
import org.bouncycastle.crypto.agreement.kdf.ConcatenationKDFGenerator;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.generators.KDF2BytesGenerator;
import org.bouncycastle.crypto.params.ECDHUPublicParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.MQVPublicParameters;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi;
import org.bouncycastle.jcajce.spec.DHUParameterSpec;
import org.bouncycastle.jcajce.spec.MQVParameterSpec;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.interfaces.MQVPublicKey;
import org.bouncycastle.util.Arrays;

public class KeyAgreementSpi extends BaseAgreementSpi {
    private static final X9IntegerConverter converter = new X9IntegerConverter();
    private Object agreement;
    private DHUParameterSpec dheParameters;
    private String kaAlgorithm;
    private MQVParameterSpec mqvParameters;
    private ECDomainParameters parameters;
    private byte[] result;

    public static class CDHwithSHA1KDFAndSharedInfo extends KeyAgreementSpi {
        public CDHwithSHA1KDFAndSharedInfo() {
            super("ECCDHwithSHA1KDF", (BasicAgreement) new ECDHCBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA1()));
        }
    }

    public static class CDHwithSHA224KDFAndSharedInfo extends KeyAgreementSpi {
        public CDHwithSHA224KDFAndSharedInfo() {
            super("ECCDHwithSHA224KDF", (BasicAgreement) new ECDHCBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA224()));
        }
    }

    public static class CDHwithSHA256KDFAndSharedInfo extends KeyAgreementSpi {
        public CDHwithSHA256KDFAndSharedInfo() {
            super("ECCDHwithSHA256KDF", (BasicAgreement) new ECDHCBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class CDHwithSHA384KDFAndSharedInfo extends KeyAgreementSpi {
        public CDHwithSHA384KDFAndSharedInfo() {
            super("ECCDHwithSHA384KDF", (BasicAgreement) new ECDHCBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA384()));
        }
    }

    public static class CDHwithSHA512KDFAndSharedInfo extends KeyAgreementSpi {
        public CDHwithSHA512KDFAndSharedInfo() {
            super("ECCDHwithSHA512KDF", (BasicAgreement) new ECDHCBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA512()));
        }
    }

    public static class DH extends KeyAgreementSpi {
        public DH() {
            super("ECDH", (BasicAgreement) new ECDHBasicAgreement(), (DerivationFunction) null);
        }
    }

    public static class DHC extends KeyAgreementSpi {
        public DHC() {
            super("ECDHC", (BasicAgreement) new ECDHCBasicAgreement(), (DerivationFunction) null);
        }
    }

    public static class DHUC extends KeyAgreementSpi {
        public DHUC() {
            super("ECCDHU", new ECDHCUnifiedAgreement(), (DerivationFunction) null);
        }
    }

    public static class DHUwithSHA1CKDF extends KeyAgreementSpi {
        public DHUwithSHA1CKDF() {
            super("ECCDHUwithSHA1CKDF", new ECDHCUnifiedAgreement(), (DerivationFunction) new ConcatenationKDFGenerator(DigestFactory.createSHA1()));
        }
    }

    public static class DHUwithSHA1KDF extends KeyAgreementSpi {
        public DHUwithSHA1KDF() {
            super("ECCDHUwithSHA1KDF", new ECDHCUnifiedAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA1()));
        }
    }

    public static class DHUwithSHA224CKDF extends KeyAgreementSpi {
        public DHUwithSHA224CKDF() {
            super("ECCDHUwithSHA224CKDF", new ECDHCUnifiedAgreement(), (DerivationFunction) new ConcatenationKDFGenerator(DigestFactory.createSHA224()));
        }
    }

    public static class DHUwithSHA224KDF extends KeyAgreementSpi {
        public DHUwithSHA224KDF() {
            super("ECCDHUwithSHA224KDF", new ECDHCUnifiedAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA224()));
        }
    }

    public static class DHUwithSHA256CKDF extends KeyAgreementSpi {
        public DHUwithSHA256CKDF() {
            super("ECCDHUwithSHA256CKDF", new ECDHCUnifiedAgreement(), (DerivationFunction) new ConcatenationKDFGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class DHUwithSHA256KDF extends KeyAgreementSpi {
        public DHUwithSHA256KDF() {
            super("ECCDHUwithSHA256KDF", new ECDHCUnifiedAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class DHUwithSHA384CKDF extends KeyAgreementSpi {
        public DHUwithSHA384CKDF() {
            super("ECCDHUwithSHA384CKDF", new ECDHCUnifiedAgreement(), (DerivationFunction) new ConcatenationKDFGenerator(DigestFactory.createSHA384()));
        }
    }

    public static class DHUwithSHA384KDF extends KeyAgreementSpi {
        public DHUwithSHA384KDF() {
            super("ECCDHUwithSHA384KDF", new ECDHCUnifiedAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA384()));
        }
    }

    public static class DHUwithSHA512CKDF extends KeyAgreementSpi {
        public DHUwithSHA512CKDF() {
            super("ECCDHUwithSHA512CKDF", new ECDHCUnifiedAgreement(), (DerivationFunction) new ConcatenationKDFGenerator(DigestFactory.createSHA512()));
        }
    }

    public static class DHUwithSHA512KDF extends KeyAgreementSpi {
        public DHUwithSHA512KDF() {
            super("ECCDHUwithSHA512KDF", new ECDHCUnifiedAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA512()));
        }
    }

    public static class DHwithSHA1CKDF extends KeyAgreementSpi {
        public DHwithSHA1CKDF() {
            super("ECDHwithSHA1CKDF", (BasicAgreement) new ECDHCBasicAgreement(), (DerivationFunction) new ConcatenationKDFGenerator(DigestFactory.createSHA1()));
        }
    }

    public static class DHwithSHA1KDF extends KeyAgreementSpi {
        public DHwithSHA1KDF() {
            super("ECDHwithSHA1KDF", (BasicAgreement) new ECDHBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA1()));
        }
    }

    public static class DHwithSHA1KDFAndSharedInfo extends KeyAgreementSpi {
        public DHwithSHA1KDFAndSharedInfo() {
            super("ECDHwithSHA1KDF", (BasicAgreement) new ECDHBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA1()));
        }
    }

    public static class DHwithSHA224KDFAndSharedInfo extends KeyAgreementSpi {
        public DHwithSHA224KDFAndSharedInfo() {
            super("ECDHwithSHA224KDF", (BasicAgreement) new ECDHBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA224()));
        }
    }

    public static class DHwithSHA256CKDF extends KeyAgreementSpi {
        public DHwithSHA256CKDF() {
            super("ECDHwithSHA256CKDF", (BasicAgreement) new ECDHCBasicAgreement(), (DerivationFunction) new ConcatenationKDFGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class DHwithSHA256KDFAndSharedInfo extends KeyAgreementSpi {
        public DHwithSHA256KDFAndSharedInfo() {
            super("ECDHwithSHA256KDF", (BasicAgreement) new ECDHBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class DHwithSHA384CKDF extends KeyAgreementSpi {
        public DHwithSHA384CKDF() {
            super("ECDHwithSHA384CKDF", (BasicAgreement) new ECDHCBasicAgreement(), (DerivationFunction) new ConcatenationKDFGenerator(DigestFactory.createSHA384()));
        }
    }

    public static class DHwithSHA384KDFAndSharedInfo extends KeyAgreementSpi {
        public DHwithSHA384KDFAndSharedInfo() {
            super("ECDHwithSHA384KDF", (BasicAgreement) new ECDHBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA384()));
        }
    }

    public static class DHwithSHA512CKDF extends KeyAgreementSpi {
        public DHwithSHA512CKDF() {
            super("ECDHwithSHA512CKDF", (BasicAgreement) new ECDHCBasicAgreement(), (DerivationFunction) new ConcatenationKDFGenerator(DigestFactory.createSHA512()));
        }
    }

    public static class DHwithSHA512KDFAndSharedInfo extends KeyAgreementSpi {
        public DHwithSHA512KDFAndSharedInfo() {
            super("ECDHwithSHA512KDF", (BasicAgreement) new ECDHBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA512()));
        }
    }

    public static class ECKAEGwithRIPEMD160KDF extends KeyAgreementSpi {
        public ECKAEGwithRIPEMD160KDF() {
            super("ECKAEGwithRIPEMD160KDF", (BasicAgreement) new ECDHBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(new RIPEMD160Digest()));
        }
    }

    public static class ECKAEGwithSHA1KDF extends KeyAgreementSpi {
        public ECKAEGwithSHA1KDF() {
            super("ECKAEGwithSHA1KDF", (BasicAgreement) new ECDHBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA1()));
        }
    }

    public static class ECKAEGwithSHA224KDF extends KeyAgreementSpi {
        public ECKAEGwithSHA224KDF() {
            super("ECKAEGwithSHA224KDF", (BasicAgreement) new ECDHBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA224()));
        }
    }

    public static class ECKAEGwithSHA256KDF extends KeyAgreementSpi {
        public ECKAEGwithSHA256KDF() {
            super("ECKAEGwithSHA256KDF", (BasicAgreement) new ECDHBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class ECKAEGwithSHA384KDF extends KeyAgreementSpi {
        public ECKAEGwithSHA384KDF() {
            super("ECKAEGwithSHA384KDF", (BasicAgreement) new ECDHBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA384()));
        }
    }

    public static class ECKAEGwithSHA512KDF extends KeyAgreementSpi {
        public ECKAEGwithSHA512KDF() {
            super("ECKAEGwithSHA512KDF", (BasicAgreement) new ECDHBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA512()));
        }
    }

    public static class MQV extends KeyAgreementSpi {
        public MQV() {
            super("ECMQV", (BasicAgreement) new ECMQVBasicAgreement(), (DerivationFunction) null);
        }
    }

    public static class MQVwithSHA1CKDF extends KeyAgreementSpi {
        public MQVwithSHA1CKDF() {
            super("ECMQVwithSHA1CKDF", (BasicAgreement) new ECMQVBasicAgreement(), (DerivationFunction) new ConcatenationKDFGenerator(DigestFactory.createSHA1()));
        }
    }

    public static class MQVwithSHA1KDF extends KeyAgreementSpi {
        public MQVwithSHA1KDF() {
            super("ECMQVwithSHA1KDF", (BasicAgreement) new ECMQVBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA1()));
        }
    }

    public static class MQVwithSHA1KDFAndSharedInfo extends KeyAgreementSpi {
        public MQVwithSHA1KDFAndSharedInfo() {
            super("ECMQVwithSHA1KDF", (BasicAgreement) new ECMQVBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA1()));
        }
    }

    public static class MQVwithSHA224CKDF extends KeyAgreementSpi {
        public MQVwithSHA224CKDF() {
            super("ECMQVwithSHA224CKDF", (BasicAgreement) new ECMQVBasicAgreement(), (DerivationFunction) new ConcatenationKDFGenerator(DigestFactory.createSHA224()));
        }
    }

    public static class MQVwithSHA224KDF extends KeyAgreementSpi {
        public MQVwithSHA224KDF() {
            super("ECMQVwithSHA224KDF", (BasicAgreement) new ECMQVBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA224()));
        }
    }

    public static class MQVwithSHA224KDFAndSharedInfo extends KeyAgreementSpi {
        public MQVwithSHA224KDFAndSharedInfo() {
            super("ECMQVwithSHA224KDF", (BasicAgreement) new ECMQVBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA224()));
        }
    }

    public static class MQVwithSHA256CKDF extends KeyAgreementSpi {
        public MQVwithSHA256CKDF() {
            super("ECMQVwithSHA256CKDF", (BasicAgreement) new ECMQVBasicAgreement(), (DerivationFunction) new ConcatenationKDFGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class MQVwithSHA256KDF extends KeyAgreementSpi {
        public MQVwithSHA256KDF() {
            super("ECMQVwithSHA256KDF", (BasicAgreement) new ECMQVBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class MQVwithSHA256KDFAndSharedInfo extends KeyAgreementSpi {
        public MQVwithSHA256KDFAndSharedInfo() {
            super("ECMQVwithSHA256KDF", (BasicAgreement) new ECMQVBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA256()));
        }
    }

    public static class MQVwithSHA384CKDF extends KeyAgreementSpi {
        public MQVwithSHA384CKDF() {
            super("ECMQVwithSHA384CKDF", (BasicAgreement) new ECMQVBasicAgreement(), (DerivationFunction) new ConcatenationKDFGenerator(DigestFactory.createSHA384()));
        }
    }

    public static class MQVwithSHA384KDF extends KeyAgreementSpi {
        public MQVwithSHA384KDF() {
            super("ECMQVwithSHA384KDF", (BasicAgreement) new ECMQVBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA384()));
        }
    }

    public static class MQVwithSHA384KDFAndSharedInfo extends KeyAgreementSpi {
        public MQVwithSHA384KDFAndSharedInfo() {
            super("ECMQVwithSHA384KDF", (BasicAgreement) new ECMQVBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA384()));
        }
    }

    public static class MQVwithSHA512CKDF extends KeyAgreementSpi {
        public MQVwithSHA512CKDF() {
            super("ECMQVwithSHA512CKDF", (BasicAgreement) new ECMQVBasicAgreement(), (DerivationFunction) new ConcatenationKDFGenerator(DigestFactory.createSHA512()));
        }
    }

    public static class MQVwithSHA512KDF extends KeyAgreementSpi {
        public MQVwithSHA512KDF() {
            super("ECMQVwithSHA512KDF", (BasicAgreement) new ECMQVBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA512()));
        }
    }

    public static class MQVwithSHA512KDFAndSharedInfo extends KeyAgreementSpi {
        public MQVwithSHA512KDFAndSharedInfo() {
            super("ECMQVwithSHA512KDF", (BasicAgreement) new ECMQVBasicAgreement(), (DerivationFunction) new KDF2BytesGenerator(DigestFactory.createSHA512()));
        }
    }

    protected KeyAgreementSpi(String str, BasicAgreement basicAgreement, DerivationFunction derivationFunction) {
        super(str, derivationFunction);
        this.kaAlgorithm = str;
        this.agreement = basicAgreement;
    }

    protected KeyAgreementSpi(String str, ECDHCUnifiedAgreement eCDHCUnifiedAgreement, DerivationFunction derivationFunction) {
        super(str, derivationFunction);
        this.kaAlgorithm = str;
        this.agreement = eCDHCUnifiedAgreement;
    }

    private static String getSimpleName(Class cls) {
        String name = cls.getName();
        return name.substring(name.lastIndexOf(46) + 1);
    }

    /* access modifiers changed from: protected */
    public byte[] bigIntToBytes(BigInteger bigInteger) {
        X9IntegerConverter x9IntegerConverter = converter;
        return x9IntegerConverter.integerToBytes(bigInteger, x9IntegerConverter.getByteLength(this.parameters.getCurve()));
    }

    /* access modifiers changed from: protected */
    public byte[] doCalcSecret() {
        return Arrays.clone(this.result);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: org.bouncycastle.crypto.params.ECPublicKeyParameters} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: org.bouncycastle.crypto.params.ECPublicKeyParameters} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r3v13 */
    /* JADX WARNING: type inference failed for: r3v14 */
    /* JADX WARNING: type inference failed for: r3v15 */
    /* JADX WARNING: type inference failed for: r3v16 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void doInitFromKey(java.security.Key r5, java.security.spec.AlgorithmParameterSpec r6, java.security.SecureRandom r7) throws java.security.InvalidKeyException, java.security.InvalidAlgorithmParameterException {
        /*
            r4 = this;
            if (r6 == 0) goto L_0x0017
            boolean r7 = r6 instanceof org.bouncycastle.jcajce.spec.MQVParameterSpec
            if (r7 != 0) goto L_0x0017
            boolean r7 = r6 instanceof org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec
            if (r7 != 0) goto L_0x0017
            boolean r7 = r6 instanceof org.bouncycastle.jcajce.spec.DHUParameterSpec
            if (r7 == 0) goto L_0x000f
            goto L_0x0017
        L_0x000f:
            java.security.InvalidAlgorithmParameterException r5 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r6 = "No algorithm parameters supported"
            r5.<init>(r6)
            throw r5
        L_0x0017:
            java.lang.Object r7 = r4.agreement
            boolean r0 = r7 instanceof org.bouncycastle.crypto.agreement.ECMQVBasicAgreement
            java.lang.String r1 = " for initialisation"
            java.lang.String r2 = " key agreement requires "
            r3 = 0
            if (r0 == 0) goto L_0x00bc
            r4.mqvParameters = r3
            boolean r7 = r5 instanceof org.bouncycastle.jce.interfaces.MQVPrivateKey
            if (r7 != 0) goto L_0x0050
            boolean r0 = r6 instanceof org.bouncycastle.jcajce.spec.MQVParameterSpec
            if (r0 == 0) goto L_0x002d
            goto L_0x0050
        L_0x002d:
            java.security.InvalidAlgorithmParameterException r5 = new java.security.InvalidAlgorithmParameterException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = r4.kaAlgorithm
            r6.append(r7)
            r6.append(r2)
            java.lang.Class<org.bouncycastle.jcajce.spec.MQVParameterSpec> r7 = org.bouncycastle.jcajce.spec.MQVParameterSpec.class
            java.lang.String r7 = getSimpleName(r7)
            r6.append(r7)
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L_0x0050:
            if (r7 == 0) goto L_0x007a
            org.bouncycastle.jce.interfaces.MQVPrivateKey r5 = (org.bouncycastle.jce.interfaces.MQVPrivateKey) r5
            java.security.PrivateKey r6 = r5.getStaticPrivateKey()
            org.bouncycastle.crypto.params.AsymmetricKeyParameter r6 = org.bouncycastle.jcajce.provider.asymmetric.ec.ECUtils.generatePrivateKeyParameter(r6)
            org.bouncycastle.crypto.params.ECPrivateKeyParameters r6 = (org.bouncycastle.crypto.params.ECPrivateKeyParameters) r6
            java.security.PrivateKey r7 = r5.getEphemeralPrivateKey()
            org.bouncycastle.crypto.params.AsymmetricKeyParameter r7 = org.bouncycastle.jcajce.provider.asymmetric.ec.ECUtils.generatePrivateKeyParameter(r7)
            org.bouncycastle.crypto.params.ECPrivateKeyParameters r7 = (org.bouncycastle.crypto.params.ECPrivateKeyParameters) r7
            java.security.PublicKey r0 = r5.getEphemeralPublicKey()
            if (r0 == 0) goto L_0x00a8
            java.security.PublicKey r5 = r5.getEphemeralPublicKey()
            org.bouncycastle.crypto.params.AsymmetricKeyParameter r5 = org.bouncycastle.jcajce.provider.asymmetric.ec.ECUtils.generatePublicKeyParameter(r5)
            r3 = r5
            org.bouncycastle.crypto.params.ECPublicKeyParameters r3 = (org.bouncycastle.crypto.params.ECPublicKeyParameters) r3
            goto L_0x00a8
        L_0x007a:
            org.bouncycastle.jcajce.spec.MQVParameterSpec r6 = (org.bouncycastle.jcajce.spec.MQVParameterSpec) r6
            java.security.PrivateKey r5 = (java.security.PrivateKey) r5
            org.bouncycastle.crypto.params.AsymmetricKeyParameter r5 = org.bouncycastle.jcajce.provider.asymmetric.ec.ECUtils.generatePrivateKeyParameter(r5)
            org.bouncycastle.crypto.params.ECPrivateKeyParameters r5 = (org.bouncycastle.crypto.params.ECPrivateKeyParameters) r5
            java.security.PrivateKey r7 = r6.getEphemeralPrivateKey()
            org.bouncycastle.crypto.params.AsymmetricKeyParameter r7 = org.bouncycastle.jcajce.provider.asymmetric.ec.ECUtils.generatePrivateKeyParameter(r7)
            org.bouncycastle.crypto.params.ECPrivateKeyParameters r7 = (org.bouncycastle.crypto.params.ECPrivateKeyParameters) r7
            java.security.PublicKey r0 = r6.getEphemeralPublicKey()
            if (r0 == 0) goto L_0x009f
            java.security.PublicKey r0 = r6.getEphemeralPublicKey()
            org.bouncycastle.crypto.params.AsymmetricKeyParameter r0 = org.bouncycastle.jcajce.provider.asymmetric.ec.ECUtils.generatePublicKeyParameter(r0)
            r3 = r0
            org.bouncycastle.crypto.params.ECPublicKeyParameters r3 = (org.bouncycastle.crypto.params.ECPublicKeyParameters) r3
        L_0x009f:
            r4.mqvParameters = r6
            byte[] r6 = r6.getUserKeyingMaterial()
            r4.ukmParameters = r6
            r6 = r5
        L_0x00a8:
            org.bouncycastle.crypto.params.MQVPrivateParameters r5 = new org.bouncycastle.crypto.params.MQVPrivateParameters
            r5.<init>(r6, r7, r3)
            org.bouncycastle.crypto.params.ECDomainParameters r6 = r6.getParameters()
            r4.parameters = r6
            java.lang.Object r6 = r4.agreement
            org.bouncycastle.crypto.agreement.ECMQVBasicAgreement r6 = (org.bouncycastle.crypto.agreement.ECMQVBasicAgreement) r6
            r6.init(r5)
            goto L_0x015c
        L_0x00bc:
            boolean r0 = r6 instanceof org.bouncycastle.jcajce.spec.DHUParameterSpec
            if (r0 == 0) goto L_0x0126
            boolean r7 = r7 instanceof org.bouncycastle.crypto.agreement.ECDHCUnifiedAgreement
            if (r7 == 0) goto L_0x0104
            org.bouncycastle.jcajce.spec.DHUParameterSpec r6 = (org.bouncycastle.jcajce.spec.DHUParameterSpec) r6
            java.security.PrivateKey r5 = (java.security.PrivateKey) r5
            org.bouncycastle.crypto.params.AsymmetricKeyParameter r5 = org.bouncycastle.jcajce.provider.asymmetric.ec.ECUtils.generatePrivateKeyParameter(r5)
            org.bouncycastle.crypto.params.ECPrivateKeyParameters r5 = (org.bouncycastle.crypto.params.ECPrivateKeyParameters) r5
            java.security.PrivateKey r7 = r6.getEphemeralPrivateKey()
            org.bouncycastle.crypto.params.AsymmetricKeyParameter r7 = org.bouncycastle.jcajce.provider.asymmetric.ec.ECUtils.generatePrivateKeyParameter(r7)
            org.bouncycastle.crypto.params.ECPrivateKeyParameters r7 = (org.bouncycastle.crypto.params.ECPrivateKeyParameters) r7
            java.security.PublicKey r0 = r6.getEphemeralPublicKey()
            if (r0 == 0) goto L_0x00e9
            java.security.PublicKey r0 = r6.getEphemeralPublicKey()
            org.bouncycastle.crypto.params.AsymmetricKeyParameter r0 = org.bouncycastle.jcajce.provider.asymmetric.ec.ECUtils.generatePublicKeyParameter(r0)
            r3 = r0
            org.bouncycastle.crypto.params.ECPublicKeyParameters r3 = (org.bouncycastle.crypto.params.ECPublicKeyParameters) r3
        L_0x00e9:
            r4.dheParameters = r6
            byte[] r6 = r6.getUserKeyingMaterial()
            r4.ukmParameters = r6
            org.bouncycastle.crypto.params.ECDHUPrivateParameters r6 = new org.bouncycastle.crypto.params.ECDHUPrivateParameters
            r6.<init>(r5, r7, r3)
            org.bouncycastle.crypto.params.ECDomainParameters r5 = r5.getParameters()
            r4.parameters = r5
            java.lang.Object r5 = r4.agreement
            org.bouncycastle.crypto.agreement.ECDHCUnifiedAgreement r5 = (org.bouncycastle.crypto.agreement.ECDHCUnifiedAgreement) r5
            r5.init(r6)
            goto L_0x015c
        L_0x0104:
            java.security.InvalidAlgorithmParameterException r5 = new java.security.InvalidAlgorithmParameterException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = r4.kaAlgorithm
            r6.append(r7)
            java.lang.String r7 = " key agreement cannot be used with "
            r6.append(r7)
            java.lang.Class<org.bouncycastle.jcajce.spec.DHUParameterSpec> r7 = org.bouncycastle.jcajce.spec.DHUParameterSpec.class
            java.lang.String r7 = getSimpleName(r7)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L_0x0126:
            boolean r7 = r5 instanceof java.security.PrivateKey
            if (r7 == 0) goto L_0x015d
            org.bouncycastle.crypto.DerivationFunction r7 = r4.kdf
            if (r7 != 0) goto L_0x013b
            boolean r7 = r6 instanceof org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec
            if (r7 != 0) goto L_0x0133
            goto L_0x013b
        L_0x0133:
            java.security.InvalidAlgorithmParameterException r5 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r6 = "no KDF specified for UserKeyingMaterialSpec"
            r5.<init>(r6)
            throw r5
        L_0x013b:
            java.security.PrivateKey r5 = (java.security.PrivateKey) r5
            org.bouncycastle.crypto.params.AsymmetricKeyParameter r5 = org.bouncycastle.jcajce.provider.asymmetric.ec.ECUtils.generatePrivateKeyParameter(r5)
            org.bouncycastle.crypto.params.ECPrivateKeyParameters r5 = (org.bouncycastle.crypto.params.ECPrivateKeyParameters) r5
            org.bouncycastle.crypto.params.ECDomainParameters r7 = r5.getParameters()
            r4.parameters = r7
            boolean r7 = r6 instanceof org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec
            if (r7 == 0) goto L_0x0153
            org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec r6 = (org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec) r6
            byte[] r3 = r6.getUserKeyingMaterial()
        L_0x0153:
            r4.ukmParameters = r3
            java.lang.Object r6 = r4.agreement
            org.bouncycastle.crypto.BasicAgreement r6 = (org.bouncycastle.crypto.BasicAgreement) r6
            r6.init(r5)
        L_0x015c:
            return
        L_0x015d:
            java.security.InvalidKeyException r5 = new java.security.InvalidKeyException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = r4.kaAlgorithm
            r6.append(r7)
            r6.append(r2)
            java.lang.Class<org.bouncycastle.jce.interfaces.ECPrivateKey> r7 = org.bouncycastle.jce.interfaces.ECPrivateKey.class
            java.lang.String r7 = getSimpleName(r7)
            r6.append(r7)
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.asymmetric.ec.KeyAgreementSpi.doInitFromKey(java.security.Key, java.security.spec.AlgorithmParameterSpec, java.security.SecureRandom):void");
    }

    /* access modifiers changed from: protected */
    public Key engineDoPhase(Key key, boolean z) throws InvalidKeyException, IllegalStateException {
        CipherParameters cipherParameters;
        if (this.parameters == null) {
            throw new IllegalStateException(this.kaAlgorithm + " not initialised.");
        } else if (z) {
            Object obj = this.agreement;
            if (obj instanceof ECMQVBasicAgreement) {
                if (!(key instanceof MQVPublicKey)) {
                    cipherParameters = new MQVPublicParameters((ECPublicKeyParameters) ECUtils.generatePublicKeyParameter((PublicKey) key), (ECPublicKeyParameters) ECUtils.generatePublicKeyParameter(this.mqvParameters.getOtherPartyEphemeralKey()));
                } else {
                    MQVPublicKey mQVPublicKey = (MQVPublicKey) key;
                    cipherParameters = new MQVPublicParameters((ECPublicKeyParameters) ECUtils.generatePublicKeyParameter(mQVPublicKey.getStaticKey()), (ECPublicKeyParameters) ECUtils.generatePublicKeyParameter(mQVPublicKey.getEphemeralKey()));
                }
            } else if (obj instanceof ECDHCUnifiedAgreement) {
                cipherParameters = new ECDHUPublicParameters((ECPublicKeyParameters) ECUtils.generatePublicKeyParameter((PublicKey) key), (ECPublicKeyParameters) ECUtils.generatePublicKeyParameter(this.dheParameters.getOtherPartyEphemeralKey()));
            } else if (key instanceof PublicKey) {
                cipherParameters = ECUtils.generatePublicKeyParameter((PublicKey) key);
            } else {
                throw new InvalidKeyException(this.kaAlgorithm + " key agreement requires " + getSimpleName(ECPublicKey.class) + " for doPhase");
            }
            try {
                Object obj2 = this.agreement;
                if (obj2 instanceof BasicAgreement) {
                    this.result = bigIntToBytes(((BasicAgreement) obj2).calculateAgreement(cipherParameters));
                    return null;
                }
                this.result = ((ECDHCUnifiedAgreement) obj2).calculateAgreement(cipherParameters);
                return null;
            } catch (Exception e) {
                throw new InvalidKeyException("calculation failed: " + e.getMessage()) {
                    public Throwable getCause() {
                        return e;
                    }
                };
            }
        } else {
            throw new IllegalStateException(this.kaAlgorithm + " can only be between two parties.");
        }
    }
}
