package org.bouncycastle.jcajce.provider.asymmetric.dstu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.ua.DSTU4145Params;
import org.bouncycastle.asn1.ua.DSTU4145PointEncoder;
import org.bouncycastle.asn1.ua.UAObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9ECPoint;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jce.interfaces.ECPointEncoder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;

public class BCDSTU4145PublicKey implements ECPublicKey, org.bouncycastle.jce.interfaces.ECPublicKey, ECPointEncoder {
    static final long serialVersionUID = 7026240464295649314L;
    private String algorithm;
    private transient DSTU4145Params dstuParams;
    private transient ECPublicKeyParameters ecPublicKey;
    private transient ECParameterSpec ecSpec;
    private boolean withCompression;

    public BCDSTU4145PublicKey(String str, ECPublicKeyParameters eCPublicKeyParameters) {
        this.algorithm = str;
        this.ecPublicKey = eCPublicKeyParameters;
        this.ecSpec = null;
    }

    public BCDSTU4145PublicKey(String str, ECPublicKeyParameters eCPublicKeyParameters, ECParameterSpec eCParameterSpec) {
        this.algorithm = "DSTU4145";
        ECDomainParameters parameters = eCPublicKeyParameters.getParameters();
        this.algorithm = str;
        this.ecPublicKey = eCPublicKeyParameters;
        if (eCParameterSpec == null) {
            this.ecSpec = createSpec(EC5Util.convertCurve(parameters.getCurve(), parameters.getSeed()), parameters);
        } else {
            this.ecSpec = eCParameterSpec;
        }
    }

    public BCDSTU4145PublicKey(String str, ECPublicKeyParameters eCPublicKeyParameters, org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec) {
        this.algorithm = "DSTU4145";
        ECDomainParameters parameters = eCPublicKeyParameters.getParameters();
        this.algorithm = str;
        this.ecSpec = eCParameterSpec == null ? createSpec(EC5Util.convertCurve(parameters.getCurve(), parameters.getSeed()), parameters) : EC5Util.convertSpec(EC5Util.convertCurve(eCParameterSpec.getCurve(), eCParameterSpec.getSeed()), eCParameterSpec);
        this.ecPublicKey = eCPublicKeyParameters;
    }

    public BCDSTU4145PublicKey(ECPublicKeySpec eCPublicKeySpec) {
        this.algorithm = "DSTU4145";
        ECParameterSpec params = eCPublicKeySpec.getParams();
        this.ecSpec = params;
        this.ecPublicKey = new ECPublicKeyParameters(EC5Util.convertPoint(params, eCPublicKeySpec.getW()), EC5Util.getDomainParameters((ProviderConfiguration) null, this.ecSpec));
    }

    BCDSTU4145PublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.algorithm = "DSTU4145";
        populateFromPubKeyInfo(subjectPublicKeyInfo);
    }

    public BCDSTU4145PublicKey(BCDSTU4145PublicKey bCDSTU4145PublicKey) {
        this.algorithm = "DSTU4145";
        this.ecPublicKey = bCDSTU4145PublicKey.ecPublicKey;
        this.ecSpec = bCDSTU4145PublicKey.ecSpec;
        this.withCompression = bCDSTU4145PublicKey.withCompression;
        this.dstuParams = bCDSTU4145PublicKey.dstuParams;
    }

    public BCDSTU4145PublicKey(org.bouncycastle.jce.spec.ECPublicKeySpec eCPublicKeySpec, ProviderConfiguration providerConfiguration) {
        this.algorithm = "DSTU4145";
        if (eCPublicKeySpec.getParams() != null) {
            EllipticCurve convertCurve = EC5Util.convertCurve(eCPublicKeySpec.getParams().getCurve(), eCPublicKeySpec.getParams().getSeed());
            this.ecPublicKey = new ECPublicKeyParameters(eCPublicKeySpec.getQ(), ECUtil.getDomainParameters(providerConfiguration, eCPublicKeySpec.getParams()));
            this.ecSpec = EC5Util.convertSpec(convertCurve, eCPublicKeySpec.getParams());
            return;
        }
        ECParameterSpec eCParameterSpec = null;
        this.ecPublicKey = new ECPublicKeyParameters(providerConfiguration.getEcImplicitlyCa().getCurve().createPoint(eCPublicKeySpec.getQ().getAffineXCoord().toBigInteger(), eCPublicKeySpec.getQ().getAffineYCoord().toBigInteger()), EC5Util.getDomainParameters(providerConfiguration, (ECParameterSpec) null));
        this.ecSpec = null;
    }

    private ECParameterSpec createSpec(EllipticCurve ellipticCurve, ECDomainParameters eCDomainParameters) {
        return new ECParameterSpec(ellipticCurve, EC5Util.convertPoint(eCDomainParameters.getG()), eCDomainParameters.getN(), eCDomainParameters.getH().intValue());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: org.bouncycastle.jce.spec.ECNamedCurveSpec} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: java.security.spec.ECParameterSpec} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: java.security.spec.ECParameterSpec} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v29, resolved type: org.bouncycastle.jce.spec.ECNamedCurveSpec} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v30, resolved type: org.bouncycastle.jce.spec.ECNamedCurveSpec} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: org.bouncycastle.jce.spec.ECNamedCurveSpec} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void populateFromPubKeyInfo(org.bouncycastle.asn1.x509.SubjectPublicKeyInfo r17) {
        /*
            r16 = this;
            r0 = r16
            org.bouncycastle.asn1.ASN1BitString r1 = r17.getPublicKeyData()
            java.lang.String r2 = "DSTU4145"
            r0.algorithm = r2
            byte[] r1 = r1.getBytes()     // Catch:{ IOException -> 0x0164 }
            org.bouncycastle.asn1.ASN1Primitive r1 = org.bouncycastle.asn1.ASN1Primitive.fromByteArray(r1)     // Catch:{ IOException -> 0x0164 }
            org.bouncycastle.asn1.ASN1OctetString r1 = (org.bouncycastle.asn1.ASN1OctetString) r1     // Catch:{ IOException -> 0x0164 }
            byte[] r1 = r1.getOctets()
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r2 = r17.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r2.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r3 = org.bouncycastle.asn1.ua.UAObjectIdentifiers.dstu4145le
            boolean r2 = r2.equals((org.bouncycastle.asn1.ASN1Primitive) r3)
            if (r2 == 0) goto L_0x002b
            r0.reverseBytes(r1)
        L_0x002b:
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r2 = r17.getAlgorithm()
            org.bouncycastle.asn1.ASN1Encodable r2 = r2.getParameters()
            org.bouncycastle.asn1.ASN1Sequence r2 = org.bouncycastle.asn1.ASN1Sequence.getInstance(r2)
            r3 = 0
            org.bouncycastle.asn1.ASN1Encodable r3 = r2.getObjectAt(r3)
            boolean r3 = r3 instanceof org.bouncycastle.asn1.ASN1Integer
            r4 = 0
            if (r3 == 0) goto L_0x0061
            org.bouncycastle.asn1.x9.X9ECParameters r2 = org.bouncycastle.asn1.x9.X9ECParameters.getInstance(r2)
            org.bouncycastle.jce.spec.ECParameterSpec r3 = new org.bouncycastle.jce.spec.ECParameterSpec
            org.bouncycastle.math.ec.ECCurve r6 = r2.getCurve()
            org.bouncycastle.math.ec.ECPoint r7 = r2.getG()
            java.math.BigInteger r8 = r2.getN()
            java.math.BigInteger r9 = r2.getH()
            byte[] r10 = r2.getSeed()
            r5 = r3
            r5.<init>(r6, r7, r8, r9, r10)
            goto L_0x0101
        L_0x0061:
            org.bouncycastle.asn1.ua.DSTU4145Params r2 = org.bouncycastle.asn1.ua.DSTU4145Params.getInstance(r2)
            r0.dstuParams = r2
            boolean r2 = r2.isNamedCurve()
            if (r2 == 0) goto L_0x0098
            org.bouncycastle.asn1.ua.DSTU4145Params r2 = r0.dstuParams
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r2.getNamedCurve()
            org.bouncycastle.crypto.params.ECDomainParameters r3 = org.bouncycastle.asn1.ua.DSTU4145NamedCurves.getByOID(r2)
            org.bouncycastle.jce.spec.ECNamedCurveParameterSpec r12 = new org.bouncycastle.jce.spec.ECNamedCurveParameterSpec
            java.lang.String r6 = r2.getId()
            org.bouncycastle.math.ec.ECCurve r7 = r3.getCurve()
            org.bouncycastle.math.ec.ECPoint r8 = r3.getG()
            java.math.BigInteger r9 = r3.getN()
            java.math.BigInteger r10 = r3.getH()
            byte[] r11 = r3.getSeed()
            r5 = r12
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r2 = r4
            r3 = r12
            goto L_0x0101
        L_0x0098:
            org.bouncycastle.asn1.ua.DSTU4145Params r2 = r0.dstuParams
            org.bouncycastle.asn1.ua.DSTU4145ECBinary r2 = r2.getECBinary()
            byte[] r3 = r2.getB()
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r5 = r17.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = r5.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r6 = org.bouncycastle.asn1.ua.UAObjectIdentifiers.dstu4145le
            boolean r5 = r5.equals((org.bouncycastle.asn1.ASN1Primitive) r6)
            if (r5 == 0) goto L_0x00b5
            r0.reverseBytes(r3)
        L_0x00b5:
            org.bouncycastle.asn1.ua.DSTU4145BinaryField r5 = r2.getField()
            org.bouncycastle.math.ec.ECCurve$F2m r15 = new org.bouncycastle.math.ec.ECCurve$F2m
            int r7 = r5.getM()
            int r8 = r5.getK1()
            int r9 = r5.getK2()
            int r10 = r5.getK3()
            java.math.BigInteger r11 = r2.getA()
            java.math.BigInteger r12 = new java.math.BigInteger
            r5 = 1
            r12.<init>(r5, r3)
            r13 = 0
            r14 = 0
            r6 = r15
            r6.<init>((int) r7, (int) r8, (int) r9, (int) r10, (java.math.BigInteger) r11, (java.math.BigInteger) r12, (java.math.BigInteger) r13, (java.math.BigInteger) r14)
            byte[] r3 = r2.getG()
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r5 = r17.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = r5.getAlgorithm()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r6 = org.bouncycastle.asn1.ua.UAObjectIdentifiers.dstu4145le
            boolean r5 = r5.equals((org.bouncycastle.asn1.ASN1Primitive) r6)
            if (r5 == 0) goto L_0x00f2
            r0.reverseBytes(r3)
        L_0x00f2:
            org.bouncycastle.jce.spec.ECParameterSpec r5 = new org.bouncycastle.jce.spec.ECParameterSpec
            org.bouncycastle.math.ec.ECPoint r3 = org.bouncycastle.asn1.ua.DSTU4145PointEncoder.decodePoint(r15, r3)
            java.math.BigInteger r2 = r2.getN()
            r5.<init>(r15, r3, r2)
            r2 = r4
            r3 = r5
        L_0x0101:
            org.bouncycastle.math.ec.ECCurve r5 = r3.getCurve()
            byte[] r6 = r3.getSeed()
            java.security.spec.EllipticCurve r9 = org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util.convertCurve(r5, r6)
            org.bouncycastle.asn1.ua.DSTU4145Params r6 = r0.dstuParams
            if (r6 == 0) goto L_0x014c
            org.bouncycastle.math.ec.ECPoint r2 = r3.getG()
            java.security.spec.ECPoint r10 = org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util.convertPoint(r2)
            org.bouncycastle.asn1.ua.DSTU4145Params r2 = r0.dstuParams
            boolean r2 = r2.isNamedCurve()
            if (r2 == 0) goto L_0x013a
            org.bouncycastle.asn1.ua.DSTU4145Params r2 = r0.dstuParams
            org.bouncycastle.asn1.ASN1ObjectIdentifier r2 = r2.getNamedCurve()
            java.lang.String r8 = r2.getId()
            org.bouncycastle.jce.spec.ECNamedCurveSpec r2 = new org.bouncycastle.jce.spec.ECNamedCurveSpec
            java.math.BigInteger r11 = r3.getN()
            java.math.BigInteger r12 = r3.getH()
            r7 = r2
            r7.<init>((java.lang.String) r8, (java.security.spec.EllipticCurve) r9, (java.security.spec.ECPoint) r10, (java.math.BigInteger) r11, (java.math.BigInteger) r12)
            goto L_0x0150
        L_0x013a:
            java.security.spec.ECParameterSpec r2 = new java.security.spec.ECParameterSpec
            java.math.BigInteger r6 = r3.getN()
            java.math.BigInteger r3 = r3.getH()
            int r3 = r3.intValue()
            r2.<init>(r9, r10, r6, r3)
            goto L_0x0150
        L_0x014c:
            java.security.spec.ECParameterSpec r2 = org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util.convertToSpec((org.bouncycastle.asn1.x9.X9ECParameters) r2)
        L_0x0150:
            r0.ecSpec = r2
            org.bouncycastle.crypto.params.ECPublicKeyParameters r2 = new org.bouncycastle.crypto.params.ECPublicKeyParameters
            org.bouncycastle.math.ec.ECPoint r1 = org.bouncycastle.asn1.ua.DSTU4145PointEncoder.decodePoint(r5, r1)
            java.security.spec.ECParameterSpec r3 = r0.ecSpec
            org.bouncycastle.crypto.params.ECDomainParameters r3 = org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util.getDomainParameters(r4, r3)
            r2.<init>(r1, r3)
            r0.ecPublicKey = r2
            return
        L_0x0164:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "error recovering public key"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.asymmetric.dstu.BCDSTU4145PublicKey.populateFromPubKeyInfo(org.bouncycastle.asn1.x509.SubjectPublicKeyInfo):void");
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        populateFromPubKeyInfo(SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray((byte[]) objectInputStream.readObject())));
    }

    private void reverseBytes(byte[] bArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            byte b = bArr[i];
            bArr[i] = bArr[(bArr.length - 1) - i];
            bArr[(bArr.length - 1) - i] = b;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    /* access modifiers changed from: package-private */
    public ECPublicKeyParameters engineGetKeyParameters() {
        return this.ecPublicKey;
    }

    /* access modifiers changed from: package-private */
    public org.bouncycastle.jce.spec.ECParameterSpec engineGetSpec() {
        ECParameterSpec eCParameterSpec = this.ecSpec;
        return eCParameterSpec != null ? EC5Util.convertSpec(eCParameterSpec) : BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCDSTU4145PublicKey)) {
            return false;
        }
        BCDSTU4145PublicKey bCDSTU4145PublicKey = (BCDSTU4145PublicKey) obj;
        return this.ecPublicKey.getQ().equals(bCDSTU4145PublicKey.ecPublicKey.getQ()) && engineGetSpec().equals(bCDSTU4145PublicKey.engineGetSpec());
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public byte[] getEncoded() {
        ASN1Encodable aSN1Encodable = this.dstuParams;
        if (aSN1Encodable == null) {
            ECParameterSpec eCParameterSpec = this.ecSpec;
            if (eCParameterSpec instanceof ECNamedCurveSpec) {
                aSN1Encodable = new DSTU4145Params(new ASN1ObjectIdentifier(((ECNamedCurveSpec) this.ecSpec).getName()));
            } else {
                ECCurve convertCurve = EC5Util.convertCurve(eCParameterSpec.getCurve());
                aSN1Encodable = new X962Parameters(new X9ECParameters(convertCurve, new X9ECPoint(EC5Util.convertPoint(convertCurve, this.ecSpec.getGenerator()), this.withCompression), this.ecSpec.getOrder(), BigInteger.valueOf((long) this.ecSpec.getCofactor()), this.ecSpec.getCurve().getSeed()));
            }
        }
        try {
            return KeyUtil.getEncodedSubjectPublicKeyInfo(new SubjectPublicKeyInfo(new AlgorithmIdentifier(UAObjectIdentifiers.dstu4145be, aSN1Encodable), (ASN1Encodable) new DEROctetString(DSTU4145PointEncoder.encodePoint(this.ecPublicKey.getQ()))));
        } catch (IOException unused) {
            return null;
        }
    }

    public String getFormat() {
        return "X.509";
    }

    public org.bouncycastle.jce.spec.ECParameterSpec getParameters() {
        ECParameterSpec eCParameterSpec = this.ecSpec;
        if (eCParameterSpec == null) {
            return null;
        }
        return EC5Util.convertSpec(eCParameterSpec);
    }

    public ECParameterSpec getParams() {
        return this.ecSpec;
    }

    public ECPoint getQ() {
        ECPoint q = this.ecPublicKey.getQ();
        return this.ecSpec == null ? q.getDetachedPoint() : q;
    }

    public byte[] getSbox() {
        DSTU4145Params dSTU4145Params = this.dstuParams;
        return dSTU4145Params != null ? dSTU4145Params.getDKE() : DSTU4145Params.getDefaultDKE();
    }

    public java.security.spec.ECPoint getW() {
        return EC5Util.convertPoint(this.ecPublicKey.getQ());
    }

    public int hashCode() {
        return this.ecPublicKey.getQ().hashCode() ^ engineGetSpec().hashCode();
    }

    public void setPointFormat(String str) {
        this.withCompression = !"UNCOMPRESSED".equalsIgnoreCase(str);
    }

    public String toString() {
        return ECUtil.publicKeyToString(this.algorithm, this.ecPublicKey.getQ(), engineGetSpec());
    }
}
