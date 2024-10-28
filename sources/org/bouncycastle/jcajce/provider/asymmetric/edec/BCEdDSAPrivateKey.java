package org.bouncycastle.jcajce.provider.asymmetric.edec;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PrivateKey;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.edec.EdECObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.params.Ed448PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed448PublicKeyParameters;
import org.bouncycastle.crypto.util.PrivateKeyInfoFactory;
import org.bouncycastle.jcajce.interfaces.EdDSAPrivateKey;
import org.bouncycastle.jcajce.interfaces.EdDSAPublicKey;
import org.bouncycastle.jcajce.spec.EdDSAParameterSpec;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Properties;

public class BCEdDSAPrivateKey implements EdDSAPrivateKey {
    static final long serialVersionUID = 1;
    private final byte[] attributes;
    transient AsymmetricKeyParameter eddsaPrivateKey;
    transient AsymmetricKeyParameter eddsaPublicKey;
    private final boolean hasPublicKey;
    transient int hashCode;

    BCEdDSAPrivateKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        this.hasPublicKey = privateKeyInfo.hasPublicKey();
        this.attributes = privateKeyInfo.getAttributes() != null ? privateKeyInfo.getAttributes().getEncoded() : null;
        populateFromPrivateKeyInfo(privateKeyInfo);
    }

    BCEdDSAPrivateKey(AsymmetricKeyParameter asymmetricKeyParameter) {
        this.hasPublicKey = true;
        this.attributes = null;
        this.eddsaPrivateKey = asymmetricKeyParameter;
        this.eddsaPublicKey = asymmetricKeyParameter instanceof Ed448PrivateKeyParameters ? ((Ed448PrivateKeyParameters) asymmetricKeyParameter).generatePublicKey() : ((Ed25519PrivateKeyParameters) asymmetricKeyParameter).generatePublicKey();
        this.hashCode = calculateHashCode();
    }

    private int calculateHashCode() {
        AsymmetricKeyParameter asymmetricKeyParameter = this.eddsaPublicKey;
        return (getAlgorithm().hashCode() * 31) + Arrays.hashCode(asymmetricKeyParameter instanceof Ed448PublicKeyParameters ? ((Ed448PublicKeyParameters) asymmetricKeyParameter).getEncoded() : ((Ed25519PublicKeyParameters) asymmetricKeyParameter).getEncoded());
    }

    private PrivateKeyInfo getPrivateKeyInfo() {
        try {
            ASN1Set instance = ASN1Set.getInstance(this.attributes);
            PrivateKeyInfo createPrivateKeyInfo = PrivateKeyInfoFactory.createPrivateKeyInfo(this.eddsaPrivateKey, instance);
            return (!this.hasPublicKey || Properties.isOverrideSet("org.bouncycastle.pkcs8.v1_info_only")) ? new PrivateKeyInfo(createPrivateKeyInfo.getPrivateKeyAlgorithm(), createPrivateKeyInfo.parsePrivateKey(), instance) : createPrivateKeyInfo;
        } catch (IOException unused) {
            return null;
        }
    }

    private void populateFromPrivateKeyInfo(PrivateKeyInfo privateKeyInfo) throws IOException {
        AsymmetricKeyParameter asymmetricKeyParameter;
        byte[] octets = ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey()).getOctets();
        if (EdECObjectIdentifiers.id_Ed448.equals((ASN1Primitive) privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm())) {
            Ed448PrivateKeyParameters ed448PrivateKeyParameters = new Ed448PrivateKeyParameters(octets);
            this.eddsaPrivateKey = ed448PrivateKeyParameters;
            Ed448PrivateKeyParameters ed448PrivateKeyParameters2 = ed448PrivateKeyParameters;
            asymmetricKeyParameter = ed448PrivateKeyParameters.generatePublicKey();
        } else {
            Ed25519PrivateKeyParameters ed25519PrivateKeyParameters = new Ed25519PrivateKeyParameters(octets);
            this.eddsaPrivateKey = ed25519PrivateKeyParameters;
            Ed25519PrivateKeyParameters ed25519PrivateKeyParameters2 = ed25519PrivateKeyParameters;
            asymmetricKeyParameter = ed25519PrivateKeyParameters.generatePublicKey();
        }
        this.eddsaPublicKey = asymmetricKeyParameter;
        this.hashCode = calculateHashCode();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        populateFromPrivateKeyInfo(PrivateKeyInfo.getInstance((byte[]) objectInputStream.readObject()));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    /* access modifiers changed from: package-private */
    public AsymmetricKeyParameter engineGetKeyParameters() {
        return this.eddsaPrivateKey;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PrivateKey)) {
            return false;
        }
        PrivateKey privateKey = (PrivateKey) obj;
        PrivateKeyInfo privateKeyInfo = getPrivateKeyInfo();
        PrivateKeyInfo privateKeyInfo2 = privateKey instanceof BCEdDSAPrivateKey ? ((BCEdDSAPrivateKey) privateKey).getPrivateKeyInfo() : PrivateKeyInfo.getInstance(privateKey.getEncoded());
        if (!(privateKeyInfo == null || privateKeyInfo2 == null)) {
            try {
                return Arrays.constantTimeAreEqual(privateKeyInfo.getPrivateKey().getEncoded(), privateKeyInfo2.getPrivateKey().getEncoded()) & Arrays.constantTimeAreEqual(privateKeyInfo.getPrivateKeyAlgorithm().getEncoded(), privateKeyInfo2.getPrivateKeyAlgorithm().getEncoded());
            } catch (IOException unused) {
            }
        }
        return false;
    }

    public String getAlgorithm() {
        return Properties.isOverrideSet(Properties.EMULATE_ORACLE) ? "EdDSA" : this.eddsaPrivateKey instanceof Ed448PrivateKeyParameters ? EdDSAParameterSpec.Ed448 : EdDSAParameterSpec.Ed25519;
    }

    public byte[] getEncoded() {
        try {
            PrivateKeyInfo privateKeyInfo = getPrivateKeyInfo();
            if (privateKeyInfo == null) {
                return null;
            }
            return privateKeyInfo.getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public EdDSAPublicKey getPublicKey() {
        return new BCEdDSAPublicKey(this.eddsaPublicKey);
    }

    public int hashCode() {
        return this.hashCode;
    }

    public String toString() {
        return Utils.keyToString("Private Key", getAlgorithm(), this.eddsaPublicKey);
    }
}
