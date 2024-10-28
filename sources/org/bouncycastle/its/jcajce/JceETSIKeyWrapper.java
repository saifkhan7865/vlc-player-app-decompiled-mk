package org.bouncycastle.its.jcajce;

import java.security.Provider;
import java.security.interfaces.ECPublicKey;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.its.ETSIKeyWrapper;
import org.bouncycastle.jcajce.spec.IESKEMParameterSpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.oer.its.ieee1609dot2.EncryptedDataEncryptionKey;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccP256CurvePoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EciesP256EncryptedKey;
import org.bouncycastle.util.Arrays;

public class JceETSIKeyWrapper implements ETSIKeyWrapper {
    private final JcaJceHelper helper;
    private final byte[] recipientHash;
    private final ECPublicKey recipientKey;

    public static class Builder {
        private JcaJceHelper helper = new DefaultJcaJceHelper();
        private final byte[] recipientHash;
        private final ECPublicKey recipientKey;

        public Builder(ECPublicKey eCPublicKey, byte[] bArr) {
            this.recipientKey = eCPublicKey;
            this.recipientHash = bArr;
        }

        public JceETSIKeyWrapper build() {
            return new JceETSIKeyWrapper(this.recipientKey, this.recipientHash, this.helper);
        }

        public Builder setProvider(String str) {
            this.helper = new NamedJcaJceHelper(str);
            return this;
        }

        public Builder setProvider(Provider provider) {
            this.helper = new ProviderJcaJceHelper(provider);
            return this;
        }
    }

    private JceETSIKeyWrapper(ECPublicKey eCPublicKey, byte[] bArr, JcaJceHelper jcaJceHelper) {
        this.recipientKey = eCPublicKey;
        this.recipientHash = bArr;
        this.helper = jcaJceHelper;
    }

    public EncryptedDataEncryptionKey wrap(byte[] bArr) {
        try {
            Cipher createCipher = this.helper.createCipher("ETSIKEMwithSHA256");
            createCipher.init(3, this.recipientKey, new IESKEMParameterSpec(this.recipientHash, true));
            byte[] wrap = createCipher.wrap(new SecretKeySpec(bArr, "AES"));
            int fieldSize = (this.recipientKey.getParams().getCurve().getField().getFieldSize() + 7) / 8;
            if (wrap[0] == 4) {
                fieldSize *= 2;
            }
            int i = fieldSize + 1;
            ASN1ObjectIdentifier instance = ASN1ObjectIdentifier.getInstance(SubjectPublicKeyInfo.getInstance(this.recipientKey.getEncoded()).getAlgorithm().getParameters());
            EciesP256EncryptedKey createEciesP256EncryptedKey = EciesP256EncryptedKey.builder().setV(EccP256CurvePoint.createEncodedPoint(Arrays.copyOfRange(wrap, 0, i))).setC(Arrays.copyOfRange(wrap, i, bArr.length + i)).setT(Arrays.copyOfRange(wrap, i + bArr.length, wrap.length)).createEciesP256EncryptedKey();
            if (instance.equals((ASN1Primitive) SECObjectIdentifiers.secp256r1)) {
                return EncryptedDataEncryptionKey.eciesNistP256(createEciesP256EncryptedKey);
            }
            if (instance.equals((ASN1Primitive) TeleTrusTObjectIdentifiers.brainpoolP256r1)) {
                return EncryptedDataEncryptionKey.eciesBrainpoolP256r1(createEciesP256EncryptedKey);
            }
            throw new IllegalStateException("recipient key curve is not P-256 or Brainpool P256r1");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
