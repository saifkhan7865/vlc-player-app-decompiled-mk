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
import org.bouncycastle.its.ITSPublicVerificationKey;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccCurvePoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccP256CurvePoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.EccP384CurvePoint;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Point256;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Point384;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicVerificationKey;

public class JcaITSPublicVerificationKey extends ITSPublicVerificationKey {
    private final JcaJceHelper helper;

    public static class Builder {
        private JcaJceHelper helper = new DefaultJcaJceHelper();

        public JcaITSPublicVerificationKey build(PublicKey publicKey) {
            return new JcaITSPublicVerificationKey(publicKey, this.helper);
        }

        public JcaITSPublicVerificationKey build(PublicVerificationKey publicVerificationKey) {
            return new JcaITSPublicVerificationKey(publicVerificationKey, this.helper);
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

    JcaITSPublicVerificationKey(PublicKey publicKey, JcaJceHelper jcaJceHelper) {
        super(fromKeyParameters((ECPublicKey) publicKey));
        this.helper = jcaJceHelper;
    }

    JcaITSPublicVerificationKey(PublicVerificationKey publicVerificationKey, JcaJceHelper jcaJceHelper) {
        super(publicVerificationKey);
        this.helper = jcaJceHelper;
    }

    static PublicVerificationKey fromKeyParameters(ECPublicKey eCPublicKey) {
        ASN1ObjectIdentifier instance = ASN1ObjectIdentifier.getInstance(SubjectPublicKeyInfo.getInstance(eCPublicKey.getEncoded()).getAlgorithm().getParameters());
        if (instance.equals((ASN1Primitive) SECObjectIdentifiers.secp256r1)) {
            return new PublicVerificationKey(0, EccP256CurvePoint.uncompressedP256(Point256.builder().setX(eCPublicKey.getW().getAffineX()).setY(eCPublicKey.getW().getAffineY()).createPoint256()));
        }
        if (instance.equals((ASN1Primitive) TeleTrusTObjectIdentifiers.brainpoolP256r1)) {
            return new PublicVerificationKey(1, EccP256CurvePoint.uncompressedP256(Point256.builder().setX(eCPublicKey.getW().getAffineX()).setY(eCPublicKey.getW().getAffineY()).createPoint256()));
        }
        if (instance.equals((ASN1Primitive) TeleTrusTObjectIdentifiers.brainpoolP384r1)) {
            return new PublicVerificationKey(2, EccP384CurvePoint.uncompressedP384(Point384.builder().setX(eCPublicKey.getW().getAffineX()).setY(eCPublicKey.getW().getAffineY()).createPoint384()));
        }
        throw new IllegalArgumentException("unknown curve in public encryption key");
    }

    public PublicKey getKey() {
        X9ECParameters x9ECParameters;
        ASN1ObjectIdentifier aSN1ObjectIdentifier;
        int choice = this.verificationKey.getChoice();
        if (choice != 0) {
            if (choice == 1) {
                aSN1ObjectIdentifier = TeleTrusTObjectIdentifiers.brainpoolP256r1;
            } else if (choice == 2) {
                aSN1ObjectIdentifier = TeleTrusTObjectIdentifiers.brainpoolP384r1;
            } else {
                throw new IllegalStateException("unknown key type");
            }
            x9ECParameters = TeleTrusTNamedCurves.getByOID(aSN1ObjectIdentifier);
        } else {
            x9ECParameters = NISTNamedCurves.getByOID(SECObjectIdentifiers.secp256r1);
        }
        ECCurve curve = x9ECParameters.getCurve();
        if (this.verificationKey.getPublicVerificationKey() instanceof EccCurvePoint) {
            EccCurvePoint eccCurvePoint = (EccCurvePoint) this.verificationKey.getPublicVerificationKey();
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
