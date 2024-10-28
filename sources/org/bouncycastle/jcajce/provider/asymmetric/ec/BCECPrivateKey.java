package org.bouncycastle.jcajce.provider.asymmetric.ec;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPrivateKeySpec;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X962Parameters;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jce.interfaces.ECPointEncoder;
import org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;

public class BCECPrivateKey implements ECPrivateKey, org.bouncycastle.jce.interfaces.ECPrivateKey, PKCS12BagAttributeCarrier, ECPointEncoder {
    static final long serialVersionUID = 994553197664784084L;
    private String algorithm = "EC";
    private transient PKCS12BagAttributeCarrierImpl attrCarrier = new PKCS12BagAttributeCarrierImpl();
    private transient ECPrivateKeyParameters baseKey;
    private transient ProviderConfiguration configuration;
    private transient BigInteger d;
    private transient ECParameterSpec ecSpec;
    private transient byte[] encoding;
    private transient PrivateKeyInfo privateKeyInfo;
    private transient ASN1BitString publicKey;
    private boolean withCompression;

    protected BCECPrivateKey() {
    }

    public BCECPrivateKey(String str, ECPrivateKeySpec eCPrivateKeySpec, ProviderConfiguration providerConfiguration) {
        this.algorithm = str;
        this.d = eCPrivateKeySpec.getS();
        this.ecSpec = eCPrivateKeySpec.getParams();
        this.configuration = providerConfiguration;
        this.baseKey = convertToBaseKey(this);
    }

    BCECPrivateKey(String str, PrivateKeyInfo privateKeyInfo2, ProviderConfiguration providerConfiguration) throws IOException {
        this.algorithm = str;
        this.configuration = providerConfiguration;
        populateFromPrivKeyInfo(privateKeyInfo2);
    }

    public BCECPrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters, BCECPublicKey bCECPublicKey, ECParameterSpec eCParameterSpec, ProviderConfiguration providerConfiguration) {
        this.algorithm = str;
        this.d = eCPrivateKeyParameters.getD();
        this.configuration = providerConfiguration;
        this.baseKey = eCPrivateKeyParameters;
        if (eCParameterSpec == null) {
            ECDomainParameters parameters = eCPrivateKeyParameters.getParameters();
            eCParameterSpec = new ECParameterSpec(EC5Util.convertCurve(parameters.getCurve(), parameters.getSeed()), EC5Util.convertPoint(parameters.getG()), parameters.getN(), parameters.getH().intValue());
        }
        this.ecSpec = eCParameterSpec;
        this.publicKey = getPublicKeyDetails(bCECPublicKey);
    }

    public BCECPrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters, BCECPublicKey bCECPublicKey, org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec, ProviderConfiguration providerConfiguration) {
        this.algorithm = str;
        this.d = eCPrivateKeyParameters.getD();
        this.configuration = providerConfiguration;
        this.baseKey = eCPrivateKeyParameters;
        if (eCParameterSpec == null) {
            ECDomainParameters parameters = eCPrivateKeyParameters.getParameters();
            this.ecSpec = new ECParameterSpec(EC5Util.convertCurve(parameters.getCurve(), parameters.getSeed()), EC5Util.convertPoint(parameters.getG()), parameters.getN(), parameters.getH().intValue());
        } else {
            this.ecSpec = EC5Util.convertSpec(EC5Util.convertCurve(eCParameterSpec.getCurve(), eCParameterSpec.getSeed()), eCParameterSpec);
        }
        try {
            this.publicKey = getPublicKeyDetails(bCECPublicKey);
        } catch (Exception unused) {
            this.publicKey = null;
        }
    }

    public BCECPrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters, ProviderConfiguration providerConfiguration) {
        this.algorithm = str;
        this.d = eCPrivateKeyParameters.getD();
        this.ecSpec = null;
        this.configuration = providerConfiguration;
        this.baseKey = eCPrivateKeyParameters;
    }

    public BCECPrivateKey(String str, BCECPrivateKey bCECPrivateKey) {
        this.algorithm = str;
        this.d = bCECPrivateKey.d;
        this.ecSpec = bCECPrivateKey.ecSpec;
        this.withCompression = bCECPrivateKey.withCompression;
        this.attrCarrier = bCECPrivateKey.attrCarrier;
        this.publicKey = bCECPrivateKey.publicKey;
        this.configuration = bCECPrivateKey.configuration;
        this.baseKey = bCECPrivateKey.baseKey;
    }

    public BCECPrivateKey(String str, org.bouncycastle.jce.spec.ECPrivateKeySpec eCPrivateKeySpec, ProviderConfiguration providerConfiguration) {
        this.algorithm = str;
        this.d = eCPrivateKeySpec.getD();
        this.ecSpec = eCPrivateKeySpec.getParams() != null ? EC5Util.convertSpec(EC5Util.convertCurve(eCPrivateKeySpec.getParams().getCurve(), eCPrivateKeySpec.getParams().getSeed()), eCPrivateKeySpec.getParams()) : null;
        this.configuration = providerConfiguration;
        this.baseKey = convertToBaseKey(this);
    }

    public BCECPrivateKey(ECPrivateKey eCPrivateKey, ProviderConfiguration providerConfiguration) {
        this.d = eCPrivateKey.getS();
        this.algorithm = eCPrivateKey.getAlgorithm();
        this.ecSpec = eCPrivateKey.getParams();
        this.configuration = providerConfiguration;
        this.baseKey = convertToBaseKey(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0014, code lost:
        r1 = ((org.bouncycastle.jce.spec.ECNamedCurveParameterSpec) r11.getParameters()).getName();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.bouncycastle.crypto.params.ECPrivateKeyParameters convertToBaseKey(org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey r11) {
        /*
            org.bouncycastle.jce.spec.ECParameterSpec r0 = r11.getParameters()
            if (r0 != 0) goto L_0x000c
            org.bouncycastle.jcajce.provider.config.ProviderConfiguration r0 = org.bouncycastle.jce.provider.BouncyCastleProvider.CONFIGURATION
            org.bouncycastle.jce.spec.ECParameterSpec r0 = r0.getEcImplicitlyCa()
        L_0x000c:
            org.bouncycastle.jce.spec.ECParameterSpec r1 = r11.getParameters()
            boolean r1 = r1 instanceof org.bouncycastle.jce.spec.ECNamedCurveParameterSpec
            if (r1 == 0) goto L_0x0048
            org.bouncycastle.jce.spec.ECParameterSpec r1 = r11.getParameters()
            org.bouncycastle.jce.spec.ECNamedCurveParameterSpec r1 = (org.bouncycastle.jce.spec.ECNamedCurveParameterSpec) r1
            java.lang.String r1 = r1.getName()
            if (r1 == 0) goto L_0x0048
            org.bouncycastle.crypto.params.ECPrivateKeyParameters r2 = new org.bouncycastle.crypto.params.ECPrivateKeyParameters
            java.math.BigInteger r11 = r11.getD()
            org.bouncycastle.crypto.params.ECNamedDomainParameters r10 = new org.bouncycastle.crypto.params.ECNamedDomainParameters
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = org.bouncycastle.asn1.x9.ECNamedCurveTable.getOID(r1)
            org.bouncycastle.math.ec.ECCurve r5 = r0.getCurve()
            org.bouncycastle.math.ec.ECPoint r6 = r0.getG()
            java.math.BigInteger r7 = r0.getN()
            java.math.BigInteger r8 = r0.getH()
            byte[] r9 = r0.getSeed()
            r3 = r10
            r3.<init>(r4, r5, r6, r7, r8, r9)
            r2.<init>(r11, r10)
            return r2
        L_0x0048:
            org.bouncycastle.crypto.params.ECPrivateKeyParameters r1 = new org.bouncycastle.crypto.params.ECPrivateKeyParameters
            java.math.BigInteger r11 = r11.getD()
            org.bouncycastle.crypto.params.ECDomainParameters r8 = new org.bouncycastle.crypto.params.ECDomainParameters
            org.bouncycastle.math.ec.ECCurve r3 = r0.getCurve()
            org.bouncycastle.math.ec.ECPoint r4 = r0.getG()
            java.math.BigInteger r5 = r0.getN()
            java.math.BigInteger r6 = r0.getH()
            byte[] r7 = r0.getSeed()
            r2 = r8
            r2.<init>(r3, r4, r5, r6, r7)
            r1.<init>(r11, r8)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey.convertToBaseKey(org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey):org.bouncycastle.crypto.params.ECPrivateKeyParameters");
    }

    private PrivateKeyInfo getPrivateKeyInfo() {
        if (this.privateKeyInfo == null) {
            X962Parameters domainParametersFromName = ECUtils.getDomainParametersFromName(this.ecSpec, this.withCompression);
            ECParameterSpec eCParameterSpec = this.ecSpec;
            int orderBitLength = eCParameterSpec == null ? ECUtil.getOrderBitLength(this.configuration, (BigInteger) null, getS()) : ECUtil.getOrderBitLength(this.configuration, eCParameterSpec.getOrder(), getS());
            try {
                this.privateKeyInfo = new PrivateKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, domainParametersFromName), this.publicKey != null ? new org.bouncycastle.asn1.sec.ECPrivateKey(orderBitLength, getS(), this.publicKey, domainParametersFromName) : new org.bouncycastle.asn1.sec.ECPrivateKey(orderBitLength, getS(), (ASN1Encodable) domainParametersFromName));
            } catch (IOException unused) {
                return null;
            }
        }
        return this.privateKeyInfo;
    }

    private ASN1BitString getPublicKeyDetails(BCECPublicKey bCECPublicKey) {
        try {
            return SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(bCECPublicKey.getEncoded())).getPublicKeyData();
        } catch (IOException unused) {
            return null;
        }
    }

    private void populateFromPrivKeyInfo(PrivateKeyInfo privateKeyInfo2) throws IOException {
        X962Parameters instance = X962Parameters.getInstance(privateKeyInfo2.getPrivateKeyAlgorithm().getParameters());
        this.ecSpec = EC5Util.convertToSpec(instance, EC5Util.getCurve(this.configuration, instance));
        ASN1Encodable parsePrivateKey = privateKeyInfo2.parsePrivateKey();
        if (parsePrivateKey instanceof ASN1Integer) {
            this.d = ASN1Integer.getInstance(parsePrivateKey).getValue();
        } else {
            org.bouncycastle.asn1.sec.ECPrivateKey instance2 = org.bouncycastle.asn1.sec.ECPrivateKey.getInstance(parsePrivateKey);
            this.d = instance2.getKey();
            this.publicKey = instance2.getPublicKey();
        }
        this.baseKey = convertToBaseKey(this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.configuration = BouncyCastleProvider.CONFIGURATION;
        populateFromPrivKeyInfo(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray((byte[]) objectInputStream.readObject())));
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public ECPrivateKeyParameters engineGetKeyParameters() {
        return this.baseKey;
    }

    /* access modifiers changed from: package-private */
    public org.bouncycastle.jce.spec.ECParameterSpec engineGetSpec() {
        ECParameterSpec eCParameterSpec = this.ecSpec;
        return eCParameterSpec != null ? EC5Util.convertSpec(eCParameterSpec) : this.configuration.getEcImplicitlyCa();
    }

    public boolean equals(Object obj) {
        if (obj instanceof ECPrivateKey) {
            ECPrivateKey eCPrivateKey = (ECPrivateKey) obj;
            PrivateKeyInfo privateKeyInfo2 = getPrivateKeyInfo();
            PrivateKeyInfo privateKeyInfo3 = eCPrivateKey instanceof BCECPrivateKey ? ((BCECPrivateKey) eCPrivateKey).getPrivateKeyInfo() : PrivateKeyInfo.getInstance(eCPrivateKey.getEncoded());
            if (!(privateKeyInfo2 == null || privateKeyInfo3 == null)) {
                try {
                    return Arrays.constantTimeAreEqual(getS().toByteArray(), eCPrivateKey.getS().toByteArray()) & Arrays.constantTimeAreEqual(privateKeyInfo2.getPrivateKeyAlgorithm().getEncoded(), privateKeyInfo3.getPrivateKeyAlgorithm().getEncoded());
                } catch (IOException unused) {
                }
            }
        }
        return false;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public ASN1Encodable getBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return this.attrCarrier.getBagAttribute(aSN1ObjectIdentifier);
    }

    public Enumeration getBagAttributeKeys() {
        return this.attrCarrier.getBagAttributeKeys();
    }

    public BigInteger getD() {
        return this.d;
    }

    public byte[] getEncoded() {
        if (this.encoding == null) {
            PrivateKeyInfo privateKeyInfo2 = getPrivateKeyInfo();
            if (privateKeyInfo2 == null) {
                return null;
            }
            try {
                this.encoding = privateKeyInfo2.getEncoded(ASN1Encoding.DER);
            } catch (IOException unused) {
                return null;
            }
        }
        return Arrays.clone(this.encoding);
    }

    public String getFormat() {
        return "PKCS#8";
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

    public BigInteger getS() {
        return this.d;
    }

    public int hashCode() {
        return getD().hashCode() ^ engineGetSpec().hashCode();
    }

    public void setBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.attrCarrier.setBagAttribute(aSN1ObjectIdentifier, aSN1Encodable);
    }

    public void setPointFormat(String str) {
        this.withCompression = !"UNCOMPRESSED".equalsIgnoreCase(str);
    }

    public String toString() {
        return ECUtil.privateKeyToString("EC", this.d, engineGetSpec());
    }
}
