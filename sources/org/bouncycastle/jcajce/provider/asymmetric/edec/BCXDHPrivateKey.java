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
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X25519PublicKeyParameters;
import org.bouncycastle.crypto.params.X448PrivateKeyParameters;
import org.bouncycastle.crypto.params.X448PublicKeyParameters;
import org.bouncycastle.crypto.util.PrivateKeyInfoFactory;
import org.bouncycastle.jcajce.interfaces.XDHPrivateKey;
import org.bouncycastle.jcajce.interfaces.XDHPublicKey;
import org.bouncycastle.jcajce.spec.XDHParameterSpec;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Properties;

public class BCXDHPrivateKey implements XDHPrivateKey {
    static final long serialVersionUID = 1;
    private final byte[] attributes;
    private final boolean hasPublicKey;
    transient int hashCode;
    transient AsymmetricKeyParameter xdhPrivateKey;
    transient AsymmetricKeyParameter xdhPublicKey;

    BCXDHPrivateKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        this.hasPublicKey = privateKeyInfo.hasPublicKey();
        this.attributes = privateKeyInfo.getAttributes() != null ? privateKeyInfo.getAttributes().getEncoded() : null;
        populateFromPrivateKeyInfo(privateKeyInfo);
    }

    BCXDHPrivateKey(AsymmetricKeyParameter asymmetricKeyParameter) {
        this.hasPublicKey = true;
        this.attributes = null;
        this.xdhPrivateKey = asymmetricKeyParameter;
        this.xdhPublicKey = asymmetricKeyParameter instanceof X448PrivateKeyParameters ? ((X448PrivateKeyParameters) asymmetricKeyParameter).generatePublicKey() : ((X25519PrivateKeyParameters) asymmetricKeyParameter).generatePublicKey();
        this.hashCode = calculateHashCode();
    }

    private int calculateHashCode() {
        AsymmetricKeyParameter asymmetricKeyParameter = this.xdhPublicKey;
        return (getAlgorithm().hashCode() * 31) + Arrays.hashCode(asymmetricKeyParameter instanceof X448PublicKeyParameters ? ((X448PublicKeyParameters) asymmetricKeyParameter).getEncoded() : ((X25519PublicKeyParameters) asymmetricKeyParameter).getEncoded());
    }

    private PrivateKeyInfo getPrivateKeyInfo() {
        try {
            ASN1Set instance = ASN1Set.getInstance(this.attributes);
            PrivateKeyInfo createPrivateKeyInfo = PrivateKeyInfoFactory.createPrivateKeyInfo(this.xdhPrivateKey, instance);
            return (!this.hasPublicKey || Properties.isOverrideSet("org.bouncycastle.pkcs8.v1_info_only")) ? new PrivateKeyInfo(createPrivateKeyInfo.getPrivateKeyAlgorithm(), createPrivateKeyInfo.parsePrivateKey(), instance) : createPrivateKeyInfo;
        } catch (IOException unused) {
            return null;
        }
    }

    private void populateFromPrivateKeyInfo(PrivateKeyInfo privateKeyInfo) throws IOException {
        AsymmetricKeyParameter asymmetricKeyParameter;
        int privateKeyLength = privateKeyInfo.getPrivateKeyLength();
        byte[] octets = ((privateKeyLength == 32 || privateKeyLength == 56) ? privateKeyInfo.getPrivateKey() : ASN1OctetString.getInstance(privateKeyInfo.parsePrivateKey())).getOctets();
        if (EdECObjectIdentifiers.id_X448.equals((ASN1Primitive) privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm())) {
            X448PrivateKeyParameters x448PrivateKeyParameters = new X448PrivateKeyParameters(octets);
            this.xdhPrivateKey = x448PrivateKeyParameters;
            X448PrivateKeyParameters x448PrivateKeyParameters2 = x448PrivateKeyParameters;
            asymmetricKeyParameter = x448PrivateKeyParameters.generatePublicKey();
        } else {
            X25519PrivateKeyParameters x25519PrivateKeyParameters = new X25519PrivateKeyParameters(octets);
            this.xdhPrivateKey = x25519PrivateKeyParameters;
            X25519PrivateKeyParameters x25519PrivateKeyParameters2 = x25519PrivateKeyParameters;
            asymmetricKeyParameter = x25519PrivateKeyParameters.generatePublicKey();
        }
        this.xdhPublicKey = asymmetricKeyParameter;
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
        return this.xdhPrivateKey;
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
        PrivateKeyInfo privateKeyInfo2 = privateKey instanceof BCXDHPrivateKey ? ((BCXDHPrivateKey) privateKey).getPrivateKeyInfo() : PrivateKeyInfo.getInstance(privateKey.getEncoded());
        if (!(privateKeyInfo == null || privateKeyInfo2 == null)) {
            try {
                return Arrays.constantTimeAreEqual(privateKeyInfo.getPrivateKey().getEncoded(), privateKeyInfo2.getPrivateKey().getEncoded()) & Arrays.constantTimeAreEqual(privateKeyInfo.getPrivateKeyAlgorithm().getEncoded(), privateKeyInfo2.getPrivateKeyAlgorithm().getEncoded());
            } catch (IOException unused) {
            }
        }
        return false;
    }

    public String getAlgorithm() {
        return Properties.isOverrideSet(Properties.EMULATE_ORACLE) ? "XDH" : this.xdhPrivateKey instanceof X448PrivateKeyParameters ? XDHParameterSpec.X448 : XDHParameterSpec.X25519;
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

    public XDHPublicKey getPublicKey() {
        return new BCXDHPublicKey(this.xdhPublicKey);
    }

    public int hashCode() {
        return this.hashCode;
    }

    public String toString() {
        return Utils.keyToString("Private Key", getAlgorithm(), this.xdhPublicKey);
    }
}
