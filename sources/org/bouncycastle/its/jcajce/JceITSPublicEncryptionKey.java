package org.bouncycastle.its.jcajce;

import java.security.Provider;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPublicKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.nist.NISTNamedCurves;
import org.bouncycastle.asn1.sec.SECObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTNamedCurves;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.its.ITSPublicEncryptionKey;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.BasePublicEncryptionKey;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccCurvePoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccP256CurvePoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccP384CurvePoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicEncryptionKey;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.SymmAlgorithm;

public class JceITSPublicEncryptionKey extends ITSPublicEncryptionKey {
    private final JcaJceHelper helper;

    public static class Builder {
        private JcaJceHelper helper = new DefaultJcaJceHelper();

        public JceITSPublicEncryptionKey build(PublicKey publicKey) {
            return new JceITSPublicEncryptionKey(publicKey, this.helper);
        }

        public JceITSPublicEncryptionKey build(PublicEncryptionKey publicEncryptionKey) {
            return new JceITSPublicEncryptionKey(publicEncryptionKey, this.helper);
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

    JceITSPublicEncryptionKey(PublicKey publicKey, JcaJceHelper jcaJceHelper) {
        super(fromPublicKey(publicKey));
        this.helper = jcaJceHelper;
    }

    JceITSPublicEncryptionKey(PublicEncryptionKey publicEncryptionKey, JcaJceHelper jcaJceHelper) {
        super(publicEncryptionKey);
        this.helper = jcaJceHelper;
    }

    static PublicEncryptionKey fromPublicKey(PublicKey publicKey) {
        if (publicKey instanceof ECPublicKey) {
            ECPublicKey eCPublicKey = (ECPublicKey) publicKey;
            ASN1ObjectIdentifier instance = ASN1ObjectIdentifier.getInstance(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()).getAlgorithm().getParameters());
            if (instance.equals((ASN1Primitive) SECObjectIdentifiers.secp256r1)) {
                return new PublicEncryptionKey(SymmAlgorithm.aes128Ccm, new BasePublicEncryptionKey.Builder().setChoice(0).setValue(EccP256CurvePoint.uncompressedP256(eCPublicKey.getW().getAffineX(), eCPublicKey.getW().getAffineY())).createBasePublicEncryptionKey());
            }
            if (instance.equals((ASN1Primitive) TeleTrusTObjectIdentifiers.brainpoolP256r1)) {
                return new PublicEncryptionKey(SymmAlgorithm.aes128Ccm, new BasePublicEncryptionKey.Builder().setChoice(1).setValue(EccP256CurvePoint.uncompressedP256(eCPublicKey.getW().getAffineX(), eCPublicKey.getW().getAffineY())).createBasePublicEncryptionKey());
            }
            throw new IllegalArgumentException("unknown curve in public encryption key");
        }
        throw new IllegalArgumentException("must be ECPublicKey instance");
    }

    public PublicKey getKey() {
        X9ECParameters x9ECParameters;
        BasePublicEncryptionKey publicKey = this.encryptionKey.getPublicKey();
        int choice = publicKey.getChoice();
        if (choice == 0) {
            x9ECParameters = NISTNamedCurves.getByOID(SECObjectIdentifiers.secp256r1);
        } else if (choice == 1) {
            x9ECParameters = TeleTrusTNamedCurves.getByOID(TeleTrusTObjectIdentifiers.brainpoolP256r1);
        } else {
            throw new IllegalStateException("unknown key type");
        }
        if (this.encryptionKey.getPublicKey().getBasePublicEncryptionKey() instanceof EccCurvePoint) {
            EccCurvePoint eccCurvePoint = (EccCurvePoint) publicKey.getBasePublicEncryptionKey();
            ECCurve curve = x9ECParameters.getCurve();
            if (!(eccCurvePoint instanceof EccP256CurvePoint) && !(eccCurvePoint instanceof EccP384CurvePoint)) {
                throw new IllegalStateException("unknown key type");
            }
            ECPoint normalize = curve.decodePoint(eccCurvePoint.getEncodedPoint()).normalize();
            try {
                return this.helper.createKeyFactory("EC").generatePublic(new ECPublicKeySpec(ECUtil.convertPoint(normalize), ECUtil.convertToSpec(x9ECParameters)));
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        } else {
            throw new IllegalStateException("extension to public verification key not supported");
        }
    }
}
