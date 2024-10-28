package org.bouncycastle.pqc.jcajce.provider.ntruprime;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PublicKey;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.pqc.crypto.ntruprime.NTRULPRimePublicKeyParameters;
import org.bouncycastle.pqc.crypto.util.PublicKeyFactory;
import org.bouncycastle.pqc.crypto.util.SubjectPublicKeyInfoFactory;
import org.bouncycastle.pqc.jcajce.interfaces.NTRULPRimeKey;
import org.bouncycastle.pqc.jcajce.spec.NTRULPRimeParameterSpec;
import org.bouncycastle.util.Arrays;

public class BCNTRULPRimePublicKey implements PublicKey, NTRULPRimeKey {
    private static final long serialVersionUID = 1;
    private transient NTRULPRimePublicKeyParameters params;

    public BCNTRULPRimePublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        init(subjectPublicKeyInfo);
    }

    public BCNTRULPRimePublicKey(NTRULPRimePublicKeyParameters nTRULPRimePublicKeyParameters) {
        this.params = nTRULPRimePublicKeyParameters;
    }

    private void init(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        this.params = (NTRULPRimePublicKeyParameters) PublicKeyFactory.createKey(subjectPublicKeyInfo);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        init(SubjectPublicKeyInfo.getInstance((byte[]) objectInputStream.readObject()));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BCNTRULPRimePublicKey) {
            return Arrays.areEqual(this.params.getEncoded(), ((BCNTRULPRimePublicKey) obj).params.getEncoded());
        }
        return false;
    }

    public final String getAlgorithm() {
        return "NTRULPRime";
    }

    public byte[] getEncoded() {
        try {
            return SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(this.params).getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    public String getFormat() {
        return "X.509";
    }

    /* access modifiers changed from: package-private */
    public NTRULPRimePublicKeyParameters getKeyParams() {
        return this.params;
    }

    public NTRULPRimeParameterSpec getParameterSpec() {
        return NTRULPRimeParameterSpec.fromName(this.params.getParameters().getName());
    }

    public int hashCode() {
        return Arrays.hashCode(this.params.getEncoded());
    }
}
