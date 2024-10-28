package org.bouncycastle.pqc.jcajce.provider.falcon;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.falcon.FalconPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconPublicKeyParameters;
import org.bouncycastle.pqc.crypto.util.PrivateKeyFactory;
import org.bouncycastle.pqc.jcajce.interfaces.FalconPrivateKey;
import org.bouncycastle.pqc.jcajce.interfaces.FalconPublicKey;
import org.bouncycastle.pqc.jcajce.provider.util.KeyUtil;
import org.bouncycastle.pqc.jcajce.spec.FalconParameterSpec;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

public class BCFalconPrivateKey implements FalconPrivateKey {
    private static final long serialVersionUID = 1;
    private transient String algorithm;
    private transient ASN1Set attributes;
    private transient byte[] encoding;
    private transient FalconPrivateKeyParameters params;

    public BCFalconPrivateKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        init(privateKeyInfo);
    }

    public BCFalconPrivateKey(FalconPrivateKeyParameters falconPrivateKeyParameters) {
        init(falconPrivateKeyParameters, (ASN1Set) null);
    }

    private void init(PrivateKeyInfo privateKeyInfo) throws IOException {
        init((FalconPrivateKeyParameters) PrivateKeyFactory.createKey(privateKeyInfo), privateKeyInfo.getAttributes());
    }

    private void init(FalconPrivateKeyParameters falconPrivateKeyParameters, ASN1Set aSN1Set) {
        this.attributes = aSN1Set;
        this.params = falconPrivateKeyParameters;
        this.algorithm = Strings.toUpperCase(falconPrivateKeyParameters.getParameters().getName());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        init(PrivateKeyInfo.getInstance((byte[]) objectInputStream.readObject()));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BCFalconPrivateKey) {
            return Arrays.areEqual(getEncoded(), ((BCFalconPrivateKey) obj).getEncoded());
        }
        return false;
    }

    public final String getAlgorithm() {
        return this.algorithm;
    }

    public byte[] getEncoded() {
        if (this.encoding == null) {
            this.encoding = KeyUtil.getEncodedPrivateKeyInfo((AsymmetricKeyParameter) this.params, this.attributes);
        }
        return Arrays.clone(this.encoding);
    }

    public String getFormat() {
        return "PKCS#8";
    }

    /* access modifiers changed from: package-private */
    public FalconPrivateKeyParameters getKeyParams() {
        return this.params;
    }

    public FalconParameterSpec getParameterSpec() {
        return FalconParameterSpec.fromName(this.params.getParameters().getName());
    }

    public FalconPublicKey getPublicKey() {
        return new BCFalconPublicKey(new FalconPublicKeyParameters(this.params.getParameters(), this.params.getPublicKey()));
    }

    public int hashCode() {
        return Arrays.hashCode(getEncoded());
    }
}
